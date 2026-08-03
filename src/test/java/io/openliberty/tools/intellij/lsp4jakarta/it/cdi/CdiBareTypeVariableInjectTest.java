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

package io.openliberty.tools.intellij.lsp4jakarta.it.cdi;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import io.openliberty.tools.intellij.lsp4jakarta.it.core.BaseJakartaTest;
import io.openliberty.tools.intellij.lsp4mp4ij.psi.core.utils.IPsiUtils;
import io.openliberty.tools.intellij.lsp4mp4ij.psi.internal.core.ls.PsiUtilsLSImpl;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.eclipse.lsp4jakarta.commons.JakartaJavaDiagnosticsParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.util.Arrays;

import static io.openliberty.tools.intellij.lsp4jakarta.it.core.JakartaForJavaAssert.*;

/**
 * Tests for CDI §2.2.1 legal bean type diagnostics — bare type variable at injection points.
 *
 * According to CDI specification section 2.2.1:
 * "A type variable is not a legal bean type."
 *
 * Tests cover:
 * - @Inject fields whose type is a bare type variable (T or T[])
 * - @Inject method parameters whose type is a bare type variable (T or T[])
 * - Valid cases that must produce no diagnostics
 */
@RunWith(JUnit4.class)
public class CdiBareTypeVariableInjectTest extends BaseJakartaTest {

    @Test
    public void bareTypeVariableAtInjectField() throws Exception {
        Module module = createMavenModule(new File("src/test/resources/projects/maven/jakarta-sample"));
        IPsiUtils utils = PsiUtilsLSImpl.getInstance(getProject());

        VirtualFile javaFile = LocalFileSystem.getInstance().refreshAndFindFileByPath(ModuleUtilCore.getModuleDirPath(module)
                + "/src/main/java/io/openliberty/sample/jakarta/cdi/BareTypeVariableInjectBean.java");
        String uri = VfsUtilCore.virtualToIoFile(javaFile).toURI().toString();

        JakartaJavaDiagnosticsParams diagnosticsParams = new JakartaJavaDiagnosticsParams();
        diagnosticsParams.setUris(Arrays.asList(uri));

        // Line 39 (0-based 38): T bareTypeField — field name "bareTypeField" at col 6..19
        Diagnostic injectBareTypeField = d(38, 6, 19,
                "A type variable is not a legal bean type. Injection point fields must not use a bare type variable (T or T[]).",
                DiagnosticSeverity.Error, "jakarta-cdi", "InvalidBareTypeVariableInInjectField");

        // Line 43 (0-based 42): T[] bareTypeArrayField — field name "bareTypeArrayField" at col 8..26
        Diagnostic injectBareTypeArrayField = d(42, 8, 26,
                "A type variable is not a legal bean type. Injection point fields must not use a bare type variable (T or T[]).",
                DiagnosticSeverity.Error, "jakarta-cdi", "InvalidBareTypeVariableInInjectField");

        // Line 47 (0-based 46): setBareType(T value) — param name "value" at col 30..35
        Diagnostic injectBareTypeMethodParam = d(46, 30, 35,
                "A type variable is not a legal bean type. Injection method parameters must not use a bare type variable (T or T[]).",
                DiagnosticSeverity.Error, "jakarta-cdi", "InvalidBareTypeVariableInInjectMethodParam");

        // Line 52 (0-based 51): setBareTypeArray(T[] values) — param name "values" at col 36..42
        Diagnostic injectBareTypeArrayMethodParam = d(51, 37, 43,
                "A type variable is not a legal bean type. Injection method parameters must not use a bare type variable (T or T[]).",
                DiagnosticSeverity.Error, "jakarta-cdi", "InvalidBareTypeVariableInInjectMethodParam");

        assertJavaDiagnostics(diagnosticsParams, utils,
                injectBareTypeField,
                injectBareTypeArrayField,
                injectBareTypeMethodParam,
                injectBareTypeArrayMethodParam);
    }
}
