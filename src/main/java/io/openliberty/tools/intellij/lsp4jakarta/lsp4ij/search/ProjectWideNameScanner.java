/*******************************************************************************
 * Copyright (c) 2026 IBM Corporation and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package io.openliberty.tools.intellij.lsp4jakarta.lsp4ij.search;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiJavaFile;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Entry point for building a <em>name → occurrence-count</em> map by visiting
 * Java types across the project using IntelliJ PSI.
 *
 * <h3>How it works</h3>
 * <p>Two orthogonal concerns are kept separate and independently swappable:
 * <ol>
 *   <li><b>{@link NameExtractorStrategy}</b> — <em>what</em> to collect.
 *       Supplied by the caller; controls which element level (class annotation,
 *       field annotation, method annotation, or any combination) is inspected.</li>
 *   <li><b>{@link ScanBackend}</b> — <em>which</em> types to visit.
 *       Two built-in implementations are provided:
 *       <ul>
 *         <li>{@link AnnotatedElementsScanBackend} — visits every type in the
 *             project via {@code AllClassesSearch}. Correct for cross-file
 *             uniqueness rules; cost grows with project size. <b>Performance
 *             bottleneck under study.</b></li>
 *         <li>{@link PsiFileScanBackend} — visits only types in the current file.
 *             O(1) cost; used as a fast baseline or when cross-file detection is
 *             not needed.</li>
 *       </ul>
 *       Custom backends can be supplied directly to the explicit overload of
 *       {@link #scan}.</li>
 * </ol>
 *
 * <h3>Usage — class-level annotation (e.g. {@code @NamedEntityGraph})</h3>
 * <pre>{@code
 * Map<String, Integer> counts = ProjectWideNameScanner.scan(
 *     unit.getProject(), unit,
 *     (psiClass, nameCount) -> {
 *         PsiAnnotation ann = psiClass.getAnnotation(NAMED_ENTITY_GRAPH);
 *         if (ann != null) {
 *             String name = getNameAttr(ann);
 *             if (name != null) nameCount.merge(name, 1, Integer::sum);
 *         }
 *     });
 * }</pre>
 *
 * <h3>Usage — field-level annotation (e.g. {@code @Inject} uniqueness)</h3>
 * <pre>{@code
 * Map<String, Integer> counts = ProjectWideNameScanner.scan(
 *     unit.getProject(), unit,
 *     (psiClass, nameCount) -> {
 *         for (PsiField field : psiClass.getFields()) {
 *             if (field.getAnnotation(INJECT) != null) {
 *                 nameCount.merge(field.getName(), 1, Integer::sum);
 *             }
 *         }
 *     });
 * }</pre>
 *
 * <h3>Plugging in a custom backend</h3>
 * <pre>{@code
 * ProjectWideNameScanner.scan(project, file, extractor, myCustomBackend);
 * }</pre>
 */
public final class ProjectWideNameScanner {

    private static final Logger LOGGER = Logger.getLogger(ProjectWideNameScanner.class.getName());

    /**
     * Shared instance of the full-project backend.
     * Use via {@link #scan} or pass directly to the explicit-backend overload.
     */
    public static final ScanBackend ALL_CLASSES_BACKEND = new AnnotatedElementsScanBackend();

    /**
     * Shared instance of the current-file backend.
     * Use via {@link #scan} (with {@link #USE_SEARCH_ENGINE}{@code = false}) or
     * pass directly to the explicit-backend overload.
     */
    public static final ScanBackend PSI_FILE_BACKEND = new PsiFileScanBackend();

    /**
     * Controls which built-in backend {@link #scan} selects when no backend is
     * specified explicitly.
     *
     * <ul>
     *   <li>{@code true} (default) → {@link AnnotatedElementsScanBackend}: full project
     *       scan via {@code AllClassesSearch}, correct cross-file semantics, cost
     *       proportional to project size.</li>
     *   <li>{@code false} → {@link PsiFileScanBackend}: current-file only, negligible
     *       cost, no cross-file detection.</li>
     * </ul>
     */
    public static volatile boolean USE_SEARCH_ENGINE = true;

    /**
     * Scans using the backend selected by {@link #USE_SEARCH_ENGINE}.
     *
     * @param project   the IntelliJ project
     * @param file      the current PSI file
     * @param extractor caller-supplied logic — decides what to collect from each class
     * @return name → occurrence count across all types visited by the active backend
     */
    public static Map<String, Integer> scan(Project project,
                                            PsiJavaFile file,
                                            NameExtractorStrategy extractor) {
        ScanBackend backend = USE_SEARCH_ENGINE ? ALL_CLASSES_BACKEND : PSI_FILE_BACKEND;
        LOGGER.info("[ProjectWideNameScanner] active backend: " + backend.getClass().getSimpleName());
        return scan(project, file, extractor, backend);
    }

    /**
     * Scans using the explicitly supplied {@code backend}.
     *
     * <p>Use this overload to supply a custom backend without changing the global
     * {@link #USE_SEARCH_ENGINE} flag.
     *
     * @param project   the IntelliJ project
     * @param file      the current PSI file
     * @param extractor caller-supplied extraction logic
     * @param backend   the scan backend to use
     * @return name → occurrence count
     */
    public static Map<String, Integer> scan(Project project,
                                            PsiJavaFile file,
                                            NameExtractorStrategy extractor,
                                            ScanBackend backend) {
        Map<String, Integer> nameCount = new HashMap<>();
        backend.visitTypes(project, file, extractor, nameCount);
        return nameCount;
    }

    private ProjectWideNameScanner() {
        // utility class — no instances
    }
}
