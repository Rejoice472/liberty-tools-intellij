/*******************************************************************************
 * Copyright (c) 2026 IBM Corporation.
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

package io.openliberty.tools.intellij.lsp4jakarta.lsp4ij.cdi;

import com.intellij.psi.*;
import io.openliberty.tools.intellij.lsp4jakarta.lsp4ij.AbstractDiagnosticsCollector;
import io.openliberty.tools.intellij.lsp4jakarta.lsp4ij.Messages;
import io.openliberty.tools.intellij.lsp4mp4ij.psi.core.utils.AnnotationUtils;
import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DiagnosticSeverity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static io.openliberty.tools.intellij.lsp4jakarta.lsp4ij.cdi.ManagedBeanConstants.*;

/**
 * Diagnostics collector for CDI producer type-variable and wildcard rules.
 *
 * <p>Rules enforced (CDI 3.0 spec sections 2.2.1, 3.2, and 3.3):
 * <ul>
 * <li>A parameterized type containing a wildcard is not a legal bean type.</li>
 * <li>A producer method/field whose type is a bare type variable (e.g. {@code T}) or
 * an array of one (e.g. {@code T[]}) is always a definition error.</li>
 * <li>A producer method/field whose type is a parameterized type with a type variable
 * (e.g. {@code List<T>}) must declare scope {@code @Dependent}.</li>
 * </ul>
 */
public class CdiWildcardDiagnosticsCollector extends AbstractDiagnosticsCollector {

    public CdiWildcardDiagnosticsCollector() {
        super();
    }

    @Override
    protected String getDiagnosticSource() {
        return DIAGNOSTIC_SOURCE;
    }

    @Override
    public void collectDiagnostics(PsiJavaFile unit, List<Diagnostic> diagnostics) {
        if (unit == null)
            return;

        String[] scopeFQNames = SCOPE_FQ_NAMES.toArray(String[]::new);

        for (PsiClass type : unit.getClasses()) {
            // Hoisted once per type — used by both field and method branches.
            Set<String> typeParamNames = getTypeParameterNames(type);

            for (PsiField field : type.getFields()) {
                boolean hasInject = AnnotationUtils.hasAnnotation(field, INJECT_FQ_NAME);
                boolean hasProduces = AnnotationUtils.hasAnnotation(field, PRODUCES_FQ_NAME);

                if (hasInject) {
                    if (containsWildcard(field.getType())) {
                        diagnostics.add(createDiagnostic(field, unit,
                                Messages.getMessage("InvalidWildcardTypeInInjectField"),
                                DIAGNOSTIC_CODE_WILDCARD_INJECT, null, DiagnosticSeverity.Error));
                    }
                } else if (hasProduces) {
                    String[] annotationNames = Stream.of(field.getAnnotations())
                            .map(PsiAnnotation::getQualifiedName).toArray(String[]::new);
                    checkProducerMember(type, field, field.getType(), unit, diagnostics,
                            annotationNames, typeParamNames, scopeFQNames,
                            new String[]{ DIAGNOSTIC_CODE_WILDCARD_PRODUCER_FIELD,
                                          DIAGNOSTIC_CODE_PRODUCER_FIELD_BARE_TYPE_VAR,
                                          DIAGNOSTIC_CODE_PRODUCER_FIELD_TYPE_VAR_NON_DEPENDENT },
                            new String[]{ "InvalidWildcardTypeInProducerField",
                                          "InvalidProducerFieldWithBareTypeVariableType",
                                          "InvalidProducerFieldWithTypeVariableAndNonDependentScope" });
                }
            }

            for (PsiMethod method : type.getMethods()) {
                boolean hasInject = AnnotationUtils.hasAnnotation(method, INJECT_FQ_NAME);
                boolean hasProduces = AnnotationUtils.hasAnnotation(method, PRODUCES_FQ_NAME);

                if (hasInject) {
                    for (PsiParameter param : method.getParameterList().getParameters()) {
                        if (containsWildcard(param.getType())) {
                            diagnostics.add(createDiagnostic(param, unit,
                                    Messages.getMessage("InvalidWildcardTypeInInjectMethod"),
                                    DIAGNOSTIC_CODE_WILDCARD_INJECT, null, DiagnosticSeverity.Error));
                        }
                    }
                } else if (hasProduces) {
                    PsiType returnType = method.getReturnType();
                    if (returnType == null) continue;
                    String[] annotationNames = Stream.of(method.getAnnotations())
                            .map(PsiAnnotation::getQualifiedName).toArray(String[]::new);
                    checkProducerMember(type, method, returnType, unit, diagnostics,
                            annotationNames, typeParamNames, scopeFQNames,
                            new String[]{ DIAGNOSTIC_CODE_WILDCARD_PRODUCER_METHOD,
                                          DIAGNOSTIC_CODE_PRODUCER_METHOD_BARE_TYPE_VAR,
                                          DIAGNOSTIC_CODE_PRODUCER_METHOD_TYPE_VAR_NON_DEPENDENT },
                            new String[]{ "InvalidWildcardTypeInProducerMethod",
                                          "InvalidProducerMethodWithBareTypeVariableReturnType",
                                          "InvalidProducerMethodWithTypeVariableAndNonDependentScope" });
                }
            }
        }
    }

    /**
     * Applies the three CDI type-variable rules for a single {@code @Produces} member.
     *
     * <p>{@code codes[0]} / {@code msgKeys[0]} — wildcard in type (always invalid)<br>
     * {@code codes[1]} / {@code msgKeys[1]} — bare type variable or array of one (always invalid)<br>
     * {@code codes[2]} / {@code msgKeys[2]} — parameterized type with type variable and non-{@code @Dependent} scope
     */
    private void checkProducerMember(PsiClass type, PsiElement element, PsiType psiType,
                                     PsiJavaFile unit, List<Diagnostic> diagnostics,
                                     String[] annotationNames, Set<String> typeParamNames,
                                     String[] scopeFQNames, String[] codes, String[] msgKeys) {
        // Rule 0: wildcard in type
        if (containsWildcard(psiType)) {
            diagnostics.add(createDiagnostic(element, unit, Messages.getMessage(msgKeys[0]),
                    codes[0], null, DiagnosticSeverity.Error));
        }

        if (typeParamNames.isEmpty()) {
            return;
        }

        // Rule 1: bare type variable (T or T[]) — always invalid
        if (isBareTypeVariable(psiType, typeParamNames)) {
            diagnostics.add(createDiagnostic(element, unit, Messages.getMessage(msgKeys[1]),
                    codes[1], null, DiagnosticSeverity.Error));
        }
        // Rule 2: parameterized type with type variable — requires @Dependent scope
        else if (containsTypeVariable(psiType, typeParamNames)) {
            boolean hasNonDependentScope = getMatchedJavaElementNames(type, annotationNames, scopeFQNames)
                    .stream().anyMatch(s -> !DEPENDENT_FQ_NAME.equals(s));
            if (hasNonDependentScope) {
                diagnostics.add(createDiagnostic(element, unit, Messages.getMessage(msgKeys[2]),
                        codes[2], null, DiagnosticSeverity.Error));
            }
        }
    }

    /**
     * Returns the set of type parameter names declared directly on {@code psiClass}
     * (e.g. {@code {"T", "K", "V"}} for {@code class Foo<T, K, V>}).
     */
    private Set<String> getTypeParameterNames(PsiClass psiClass) {
        Set<String> names = new HashSet<>();
        for (PsiTypeParameter tp : psiClass.getTypeParameters()) {
            names.add(tp.getName());
        }
        return names;
    }

    /**
     * Returns {@code true} if {@code psiType} is a bare type variable (e.g. {@code T})
     * or an array whose ultimate element type is a type variable (e.g. {@code T[]}).
     */
    private boolean isBareTypeVariable(PsiType psiType, Set<String> typeParamNames) {
        if (psiType == null) {
            return false;
        }
        // Resolved type variable (PsiClassType whose resolved class is a PsiTypeParameter)
        if (psiType instanceof PsiClassType) {
            PsiClass resolved = ((PsiClassType) psiType).resolve();
            if (resolved instanceof PsiTypeParameter) {
                return typeParamNames.contains(resolved.getName());
            }
        }
        // Array type: check element type recursively
        if (psiType instanceof PsiArrayType) {
            return isBareTypeVariable(((PsiArrayType) psiType).getComponentType(), typeParamNames);
        }
        return false;
    }

    /**
     * Returns {@code true} if {@code psiType} is a parameterized type that contains
     * at least one type variable in its type arguments (recursively).
     */
    private boolean containsTypeVariable(PsiType psiType, Set<String> typeParamNames) {
        if (psiType instanceof PsiClassType) {
            for (PsiType typeArg : ((PsiClassType) psiType).getParameters()) {
                if (isBareTypeVariable(typeArg, typeParamNames) || containsTypeVariable(typeArg, typeParamNames)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns {@code true} if {@code type} contains a wildcard type parameter
     * ({@code ?}, {@code ? extends}, or {@code ? super}) anywhere in the type tree.
     */
    private boolean containsWildcard(PsiType type) {
        if (type == null) {
            return false;
        }
        if (type instanceof PsiWildcardType) {
            return true;
        }
        if (type instanceof PsiClassType) {
            for (PsiType param : ((PsiClassType) type).getParameters()) {
                if (containsWildcard(param)) {
                    return true;
                }
            }
        }
        if (type instanceof PsiArrayType) {
            return containsWildcard(((PsiArrayType) type).getComponentType());
        }
        if (type instanceof PsiIntersectionType) {
            for (PsiType conjunct : ((PsiIntersectionType) type).getConjuncts()) {
                if (containsWildcard(conjunct)) {
                    return true;
                }
            }
        }
        return false;
    }
}
