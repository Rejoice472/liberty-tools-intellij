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
import java.util.logging.Logger;

/**
 * {@link ScanBackend} that visits only the types declared in the current
 * {@link PsiJavaFile}.
 *
 * <p>O(1) types visited per diagnostic call — negligible cost regardless of
 * project size. Cannot detect names declared in other files; use this as a
 * fast performance baseline or when cross-file detection is intentionally
 * disabled.
 *
 * <p>Toggle between this backend and {@link AnnotatedElementsScanBackend} via
 * {@link ProjectWideNameScanner#USE_SEARCH_ENGINE}.
 */
public class PsiFileScanBackend implements ScanBackend {

    private static final Logger LOGGER = Logger.getLogger(PsiFileScanBackend.class.getName());

    @Override
    public void visitTypes(Project project,
                           PsiJavaFile file,
                           NameExtractorStrategy extractor,
                           Map<String, Integer> nameCount) {
        PsiClass[] classes = file.getClasses();
        for (PsiClass psiClass : classes) {
            extractor.extract(psiClass, nameCount);
        }
        LOGGER.info(String.format(
                "[ProjectWideNameScanner][PsiFile] types_visited=%d | unique_names=%d",
                classes.length, nameCount.size()));
    }
}
