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
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiJavaFile;

import java.util.Map;

/**
 * Pluggable backend that controls <em>which</em> {@link PsiClass types} are visited
 * during a {@link ProjectWideNameScanner} scan.
 *
 * <p>Implementations differ only in which types they deliver to the
 * {@link NameExtractorStrategy}; they do not interpret the types themselves.
 * Two built-in implementations are provided:
 * <ul>
 *   <li>{@link AnnotatedElementsScanBackend} — visits every source type in the project.</li>
 *   <li>{@link PsiFileScanBackend} — visits only types in the current file.</li>
 * </ul>
 *
 * <p>Custom backends can be supplied directly to
 * {@link ProjectWideNameScanner#scan(Project, PsiJavaFile, NameExtractorStrategy, ScanBackend)}.
 */
public interface ScanBackend {

    /**
     * Visit the types determined by this backend and for each one call
     * {@code extractor.extract(psiClass, nameCount)}.
     *
     * @param project   the IntelliJ project
     * @param file      the current PSI file (always available as fallback context)
     * @param extractor caller-supplied extraction logic — element-level agnostic
     * @param nameCount mutable accumulator to pass through to the extractor
     */
    void visitTypes(Project project,
                    PsiJavaFile file,
                    NameExtractorStrategy extractor,
                    Map<String, Integer> nameCount);
}
