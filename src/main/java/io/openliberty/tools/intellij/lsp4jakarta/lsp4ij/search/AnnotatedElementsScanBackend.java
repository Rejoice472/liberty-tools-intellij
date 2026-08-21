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
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.AllClassesSearch;

import java.util.Map;
import java.util.logging.Logger;

/**
 * {@link ScanBackend} that visits <em>every</em> source type in the project using
 * {@link AllClassesSearch}.
 *
 * <p><b>Performance note:</b> every diagnostic call pays the full scan cost,
 * regardless of how many types actually carry the elements of interest.
 * With a large source set (e.g. 500+ annotated classes) this cost is measurable
 * and grows linearly with project size — this is the performance bottleneck that
 * {@link ProjectWideNameScanner} is designed to expose and allow comparison
 * against {@link PsiFileScanBackend}.
 */
public class AnnotatedElementsScanBackend implements ScanBackend {

    private static final Logger LOGGER = Logger.getLogger(AnnotatedElementsScanBackend.class.getName());

    @Override
    public void visitTypes(Project project,
                           PsiJavaFile file,
                           NameExtractorStrategy extractor,
                           Map<String, Integer> nameCount) {
        GlobalSearchScope scope = GlobalSearchScope.allScope(project);
        int[] typesVisited = { 0 };

        AllClassesSearch.search(scope, project).forEach(psiClass -> {
            typesVisited[0]++;
            extractor.extract(psiClass, nameCount);
            return true;
        });

        LOGGER.info(String.format(
                "[ProjectWideNameScanner][AllClasses] types_visited=%d | unique_names=%d",
                typesVisited[0], nameCount.size()));
    }
}
