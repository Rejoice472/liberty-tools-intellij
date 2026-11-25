package io.openliberty.tools.intellij.lsp4jakarta.version;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum JakartaDiagnostic {

    // --- EE 9 Diagnostics (grouped by diagnostics collector) ---

    // --- AnnotationDiagnosticsCollector ---
    INVALID_DATE_FORMAT("InvalidDateFormat", JakartaVersion.EE_9),
    MISSING_RESOURCE_TYPE_ATTRIBUTE("MissingResourceTypeAttribute", JakartaVersion.EE_9),
    MISSING_RESOURCE_NAME_ATTRIBUTE("MissingResourceNameAttribute", JakartaVersion.EE_9),
    RESOURCE_NAME_MUST_START_WITH_SET("ResourceNameMustStartWithSet", JakartaVersion.EE_9),
    RESOURCE_MUST_DECLARE_EXACTLY_ONE_PARAM("ResourceMustDeclareExactlyOneParam", JakartaVersion.EE_9),
    RESOURCE_RETURN_TYPE_MUST_BE_VOID("ResourceReturnTypeMustBeVoid", JakartaVersion.EE_9),
    GENERATED_VALUE_EMPTY("GeneratedValueEmpty", JakartaVersion.EE_9),
    GENERATED_VALUE_INVALID_FORMAT("GeneratedValueInvalidFormat", JakartaVersion.EE_9),
    RESOURCE_TYPE_MISMATCH("ResourceTypeMismatch", JakartaVersion.EE_9),
    POST_CONSTRUCT_PARAMS("PostConstructParams", JakartaVersion.EE_9),
    POST_CONSTRUCT_RETURN_TYPE("PostConstructReturnType", JakartaVersion.EE_9),
    POST_CONSTRUCT_EXCEPTION("PostConstructException", JakartaVersion.EE_9),
    PRE_DESTROY_PARAMS("PreDestroyParams", JakartaVersion.EE_9),
    PRE_DESTROY_EXCEPTION("PreDestroyException", JakartaVersion.EE_9),
    PRE_DESTROY_STATIC("PreDestroyStatic", JakartaVersion.EE_9),
    PRIORITY_SHOULD_BE_NON_NEGATIVE("PriorityShouldBeNonNegative", JakartaVersion.EE_9),

    // --- BeanValidationDiagnosticsCollector ---
    BEAN_VALIDATION_INVALID_TYPE("FixTypeOfElement", JakartaVersion.EE_9),
    BEAN_VALIDATION_MAKE_NOT_STATIC("MakeNotStatic", JakartaVersion.EE_9),
    CONFLICTING_CONSTRAINTS("ConflictingConstraints", JakartaVersion.EE_9),
    INVALID_VALID_ANNOTATION_ON_NON_CASCADABLE_TYPE("InvalidValidAnnotationOnNonCascadableType", JakartaVersion.EE_9),

    // --- ManagedBeanDiagnosticsCollector (CDI) ---
    INVALID_MANAGED_BEAN_ANNOTATION("InvalidManagedBeanAnnotation", JakartaVersion.EE_9),
    INVALID_SCOPE_DECL("InvalidScopeDecl", JakartaVersion.EE_9),
    INVALID_SINGLETON_SESSION_BEAN_SCOPE("InvalidSingletonSessionBeanScope", JakartaVersion.EE_9),
    REMOVE_PRODUCES_OR_INJECT("RemoveProducesOrInject", JakartaVersion.EE_9),
    INVALID_STATELESS_SESSION_BEAN_SCOPE("InvalidStatelessSessionBeanScope", JakartaVersion.EE_9),
    INVALID_PRODUCER_FIELD_WITH_NAMED_ANNOTATION("InvalidProducerFieldWithNamedAnnotation", JakartaVersion.EE_9),
    INVALID_NAMED_ANNOTATION_ON_NON_FIELD_INJECTION_POINT("InvalidNamedAnnotationOnNonFieldInjectionPoint", JakartaVersion.EE_9),
    INVALID_SPECIALIZED_BEAN_WITH_NAMED_ANNOTATION("InvalidSpecializedBeanWithNamedAnnotation", JakartaVersion.EE_9),
    INVALID_PASSIVATING_SCOPED_BEAN_WITHOUT_SERIALIZABLE("InvalidPassivatingScopedBeanWithoutSerializable", JakartaVersion.EE_9),
    INVALID_MANAGED_BEAN_CONSTRUCTOR("InvalidManagedBeanConstructor", JakartaVersion.EE_9),
    INVALID_INJECT_ANNOTATION_ON_MULTIPLE_METHOD_PARAMS("InvalidInjectAnnotationOnMultipleMethodParams", JakartaVersion.EE_9),
    INVALID_OBSERVES_OBSERVES_ASYNC_METHOD_PARAMS("InvalidObservesObservesAsyncMethodParams", JakartaVersion.EE_9),
    INVALID_INTERCEPTOR_OR_DECORATOR_WITH_OBSERVER_METHOD("InvalidInterceptorOrDecoratorWithObserverMethod", JakartaVersion.EE_9),
    INVALID_INTERCEPTOR_OR_DECORATOR_WITH_DISPOSER_METHOD("InvalidInterceptorOrDecoratorWithDisposerMethod", JakartaVersion.EE_9),
    INVALID_DECORATOR_DELEGATE_INJECTION_POINTS("InvalidDecoratorDelegateInjectionPoints", JakartaVersion.EE_9),
    INVALID_DELEGATE_INJECTION_POINT("InvalidDelegateInjectionPoint", JakartaVersion.EE_9),
    INVALID_DELEGATE_ON_NON_INJECTION_POINT("InvalidDelegateOnNonInjectionPoint", JakartaVersion.EE_9),
    INVALID_DECORATOR_DELEGATE_TYPE_ASSIGNABILITY("InvalidDecoratorDelegateTypeAssignability", JakartaVersion.EE_9),
    INVALID_DECORATOR_WITH_NO_DECORATED_TYPES("InvalidDecoratorWithNoDecoratedTypes", JakartaVersion.EE_9),
    INVALID_DEPENDENT_SCOPE_WITH_CONDITIONAL_OBSERVER("InvalidDependentScopeWithConditionalObserver", JakartaVersion.EE_9),
    INVALID_MULTIPLE_OBSERVER_PARAMS("InvalidMultipleObserverParams", JakartaVersion.EE_9),
    INVALID_INTERCEPTOR_OR_DECORATOR("InvalidInterceptorOrDecorator", JakartaVersion.EE_9),
    REMOVE_EXTRA_DISPOSES("RemoveExtraDisposes", JakartaVersion.EE_9),
    INVALID_WILDCARD_TYPE_IN_INJECT_FIELD("InvalidWildcardTypeInInjectField", JakartaVersion.EE_9),
    INVALID_WILDCARD_TYPE_IN_INJECT_METHOD("InvalidWildcardTypeInInjectMethod", JakartaVersion.EE_9),
    INVALID_WILDCARD_TYPE_IN_PRODUCER_FIELD("InvalidWildcardTypeInProducerField", JakartaVersion.EE_9),
    INVALID_WILDCARD_TYPE_IN_PRODUCER_METHOD("InvalidWildcardTypeInProducerMethod", JakartaVersion.EE_9),
    INVALID_PRODUCER_METHOD_WITH_BARE_TYPE_VARIABLE_RETURN_TYPE("InvalidProducerMethodWithBareTypeVariableReturnType", JakartaVersion.EE_9),
    INVALID_PRODUCER_METHOD_WITH_TYPE_VARIABLE_AND_NON_DEPENDENT_SCOPE("InvalidProducerMethodWithTypeVariableAndNonDependentScope", JakartaVersion.EE_9),
    INVALID_PRODUCER_FIELD_WITH_BARE_TYPE_VARIABLE_TYPE("InvalidProducerFieldWithBareTypeVariableType", JakartaVersion.EE_9),
    INVALID_PRODUCER_FIELD_WITH_TYPE_VARIABLE_AND_NON_DEPENDENT_SCOPE("InvalidProducerFieldWithTypeVariableAndNonDependentScope", JakartaVersion.EE_9),
    INVALID_ORPHAN_DISPOSER_METHOD("InvalidOrphanDisposerMethod", JakartaVersion.EE_9),
    INVALID_INCONSISTENT_SPECIALIZATION("InvalidInconsistentSpecialization", JakartaVersion.EE_9),
    INVALID_RAW_EVENT_TYPE_INJECTION_POINT("InvalidRawEventTypeInjectionPoint", JakartaVersion.EE_9),
    INVALID_SPECIALIZES_ANNOTATION_ON_NON_BEAN_SUPERCLASS("InvalidSpecializesAnnotationOnNonBeanSuperclass", JakartaVersion.EE_9),
    INVALID_OBSERVER_METHOD_WITHOUT_NOTIFY("InvalidObserverMethodWithoutNotify", JakartaVersion.EE_9),
    INVALID_USER_TRANSACTION_INJECTION_IN_CDI_BEAN("InvalidUserTransactionInjectionInCDIBean", JakartaVersion.EE_9),
    INVALID_TYPED_ANNOTATION_NON_MATCHING_BEAN_TYPE("InvalidTypedAnnotationNonMatchingBeanType", JakartaVersion.EE_9),
    REMOVE_INJECT_OR_CONFLICTED_ANNOTATIONS("RemoveInjectOrConflictedAnnotations", JakartaVersion.EE_9),
    REMOVE_PRODUCES_OR_CONFLICTED_ANNOTATIONS("RemoveProducesOrConflictedAnnotations", JakartaVersion.EE_9),
    REMOVE_DISPOSES_OR_CONFLICTED_ANNOTATIONS("RemoveDisposesOrConflictedAnnotations", JakartaVersion.EE_9),

    // --- DependencyInjectionDiagnosticsCollector ---
    REMOVE_INJECT_OR_FINAL("RemoveInjectOrFinal", JakartaVersion.EE_9),
    REMOVE_INJECT("RemoveInject", JakartaVersion.EE_9),
    REMOVE_INJECT_OR_ABSTRACT("RemoveInjectOrAbstract", JakartaVersion.EE_9),
    REMOVE_INJECT_OR_STATIC("RemoveInjectOrStatic", JakartaVersion.EE_9),
    REMOVE_INJECT_FOR_GENERIC("RemoveInjectForGeneric", JakartaVersion.EE_9),
    REMOVE_INJECT_FOR_INNER_CLASS("RemoveInjectForInnerClass", JakartaVersion.EE_9),
    REMOVE_INVALID_QUALIFIER("RemoveInvalidQualifier", JakartaVersion.EE_9),
    REMOVE_INVALID_SCOPE_ATTRIBUTE("RemoveInvalidScopeAttribute", JakartaVersion.EE_9),

    // --- EjbDiagnosticsCollector ---
    IMPLEMENT_MESSAGE_LISTENER("ImplementMessageListener", JakartaVersion.EE_9),
    MISSING_PUBLIC_NO_ARG_CONSTRUCTOR("MissingPublicNoArgConstructor", JakartaVersion.EE_9),
    INVALID_SESSION_SYNC_METHOD_FINAL("InvalidSessionSyncMethodFinal", JakartaVersion.EE_9),
    INVALID_SESSION_SYNC_METHOD_STATIC("InvalidSessionSyncMethodStatic", JakartaVersion.EE_9),
    INVALID_SESSION_SYNC_METHOD_NON_VOID("InvalidSessionSyncMethodNonVoid", JakartaVersion.EE_9),
    INVALID_SESSION_SYNC_METHOD_NO_PARAM_ANNOTATION("InvalidSessionSyncMethodNoParamAnnotation", JakartaVersion.EE_9),
    INVALID_AFTER_COMPLETION_METHOD_PARAMS("InvalidAfterCompletionMethodParams", JakartaVersion.EE_9),
    INVALID_MODIFIER_NOT_PUBLIC("InvalidModifierNotPublic", JakartaVersion.EE_9),
    INVALID_MODIFIER_FINAL("InvalidModifierFinal", JakartaVersion.EE_9),
    INVALID_MODIFIER_ABSTRACT("InvalidModifierAbstract", JakartaVersion.EE_9),
    INVALID_NON_TOP_LEVEL_CLASS("InvalidNonTopLevelClass", JakartaVersion.EE_9),
    INVALID_SESSION_BEAN_WITH_INTERCEPTOR_OR_DECORATOR("InvalidSessionBeanWithInterceptorOrDecorator", JakartaVersion.EE_9),
    SESSION_BEAN_FINALIZE_METHOD("SessionBeanFinalizeMethod", JakartaVersion.EE_9),
    CONFLICTING_SESSION_BEAN_ANNOTATIONS("ConflictingSessionBeanAnnotations", JakartaVersion.EE_9),

    // --- FacesDiagnosticsCollector ---
    FACES_VALIDATOR_ANNOTATED_CLASS_NO_VALIDATOR_INTERFACE_IMPL("FacesValidatorAnnotatedClassNoValidatorInterfaceImpl", JakartaVersion.EE_9),

    // --- InterceptorDiagnosticsCollector ---
    REMOVE_INTERCEPTOR_ANNOTATION_ON_ABSTRACT_CLASS("RemoveInterceptorAnnotationOnAbstractClass", JakartaVersion.EE_9),
    REMOVE_INTERCEPTOR_ANNOTATION_ON_NO_ARGS_CONSTRUCTOR("RemoveInterceptorAnnotationOnNoArgsConstructor", JakartaVersion.EE_9),
    INVALID_INTERCEPTOR_METHODS_PROCEED_MISSING("InvalidInterceptorMethodsProceedMissing", JakartaVersion.EE_9),
    INVALID_INTERCEPTOR_METHOD_ANNOTATION_ON_FINAL_METHOD("InvalidInterceptorMethodAnnotationOnFinalMethod", JakartaVersion.EE_9),
    INVALID_INTERCEPTOR_METHOD_ANNOTATION_ON_ABSTRACT_METHOD("InvalidInterceptorMethodAnnotationOnAbstractMethod", JakartaVersion.EE_9),
    INVALID_INTERCEPTOR_METHOD_ANNOTATION_ON_STATIC_METHOD("InvalidInterceptorMethodAnnotationOnStaticMethod", JakartaVersion.EE_9),
    INTERCEPTOR_NEGATIVE_PRIORITY("InterceptorNegativePriority", JakartaVersion.EE_9),
    INVALID_MULTIPLE_INTERCEPTOR_METHODS_OF_SAME_TYPE("InvalidMultipleInterceptorMethodsOfSameType", JakartaVersion.EE_9),
    INVALID_INTERCEPTOR_MISSING_INTERCEPTOR_BINDING("InvalidInterceptorMissingInterceptorBinding", JakartaVersion.EE_9),
    INVALID_FINAL_INTERCEPTOR_BINDING_CLASS("InvalidFinalInterceptorBindingClass", JakartaVersion.EE_9),
    INVALID_METHOD_ON_INTERCEPTOR_BINDING_CLASS("InvalidMethodOnInterceptorBindingClass", JakartaVersion.EE_9),
    INVALID_AROUND_CONSTRUCT_IN_TARGET_CLASS("InvalidAroundConstructInTargetClass", JakartaVersion.EE_9),
    INVALID_LIFECYCLE_CALLBACK_INTERCEPTOR_METHOD_SIGNATURE("InvalidLifecycleCallbackInterceptorMethodSignature", JakartaVersion.EE_9),

    // --- Jax_RSDiagnosticsCollector ---
    NON_PUBLIC_RESOURCE_METHOD("NonPublicResourceMethod", JakartaVersion.EE_9),
    RESOURCE_METHOD_MULTIPLE_ENTITY_PARAMS("ResourceMethodMultipleEntityParams", JakartaVersion.EE_9),
    UNUSED_CONSTRUCTOR("UnusedConstructor", JakartaVersion.EE_9),
    AMBIGUOUS_CONSTRUCTORS("AmbiguousConstructors", JakartaVersion.EE_9),
    NO_PUBLIC_CONSTRUCTORS("NoPublicConstructors", JakartaVersion.EE_9),
    INVALID_CONSTRAINT_TARGET("InvalidConstraintTarget", JakartaVersion.EE_9),

    // --- JsonbDiagnosticsCollector ---
    MULTIPLE_JSONB_CREATOR_ANNOTATIONS("MultipleJsonbCreatorAnnotations", JakartaVersion.EE_9),
    NON_MUTUAL_JSONB_TRANSIENT_ANNOTATION("NonmutualJsonbTransientAnnotation", JakartaVersion.EE_9),
    NON_MUTUAL_JSONB_TRANSIENT_ANNOTATION_ON_ACCESSOR("NonmutualJsonbTransientAnnotationOnAccessor", JakartaVersion.EE_9),
    DUPLICATE_PROPERTY_NAMES_ON_JSONB_FIELDS("DuplicatePropertyNamesOnJsonbFields", JakartaVersion.EE_9),
    INVALID_JSON_B_NO_ARGS_CONSTRUCTOR_MISSING("InvalidJsonBNoArgsConstructorMissing", JakartaVersion.EE_9),
    INVALID_JSON_B_NON_STATIC_INNER_CLASS("InvalidJsonBNonStaticInnerClass", JakartaVersion.EE_9),
    INVALID_JSON_B_NON_PUBLIC_PROTECTED_STATIC_NESTED_CLASS("InvalidJsonBNonPublicProtectedStaticNestedClass", JakartaVersion.EE_9),
    INVALID_JSONB_FROM_JSON_NULL_PARAMETER("InvalidJsonbFromJsonNullParameter", JakartaVersion.EE_9),
    JSONB_CLOSABLE_CLOSE_WARNING("JsonbClosableCloseWarning", JakartaVersion.EE_9),

    // --- JsonpDiagnosticsCollector ---
    INVALID_CREATE_POINTER_ARG("InvalidCreatePointerArg", JakartaVersion.EE_9),
    INVALID_JSON_OBJECT_BUILDER_KEY("InvalidJsonObjectBuilderKey", JakartaVersion.EE_9),
    INVALID_JSON_ARRAY_BUILDER_VALUE("InvalidJsonArrayBuilderValue", JakartaVersion.EE_9),

    // --- PersistenceDiagnosticsCollector ---
    INVERSE_SIDE_MISSING_MAPPED_BY("InverseSideMissingMappedBy", JakartaVersion.EE_9),
    JOIN_TABLE_ON_INVERSE_SIDE("JoinTableOnInverseSide", JakartaVersion.EE_9),
    JOIN_COLUMN_ON_INVERSE_SIDE("JoinColumnOnInverseSide", JakartaVersion.EE_9),
    MISSING_EMPTY_CONSTRUCTOR("MissingEmptyConstructor", JakartaVersion.EE_9),
    REMOVE_FINAL_METHODS("RemoveFinalMethods", JakartaVersion.EE_9),
    REMOVE_FINAL_VARIABLES("RemoveFinalVariables", JakartaVersion.EE_9),
    INVALID_ENTITY_CLASS("InvalidClass", JakartaVersion.EE_9),
    MISSING_PRIMARY_KEY("MissingPrimaryKey", JakartaVersion.EE_9),
    INVALID_VALUE_IN_TEMPORAL_ANNOTATION("InvalidValueInTemporalAnnotation", JakartaVersion.EE_9),
    MISSING_TEMPORAL_ANNOTATION("MissingTemporalAnnotation", JakartaVersion.EE_9),
    MULTIPLE_VERSION_ANNOTATIONS("MultipleVersionAnnotations", JakartaVersion.EE_9),
    VERSION_ANNOTATION_IN_HIERARCHY("VersionAnnotationInHierarchy", JakartaVersion.EE_9),
    INVALID_VERSION_FIELD_OR_PROPERTY_TYPE("InvalidVersionFieldOrPropertyType", JakartaVersion.EE_9),
    EMBEDDED_TYPE_NOT_ANNOTATED_WITH_EMBEDDABLE("EmbeddedTypeNotAnnotatedWithEmbeddable", JakartaVersion.EE_9),
    INVALID_ID_TYPE("InvalidIdType", JakartaVersion.EE_9),
    MULTIPLE_EMBEDDED_ID_ANNOTATIONS("MultipleEmbeddedIdAnnotations", JakartaVersion.EE_9),
    MIXED_IDENTIFIER_ANNOTATIONS("MixedIdentifierAnnotations", JakartaVersion.EE_9),
    ID_CLASS_MEMBER_MISSING_IN_KEY_CLASS("IdClassMemberMissingInKeyClass", JakartaVersion.EE_9),
    ID_CLASS_MEMBER_TYPE_MISMATCH("IdClassMemberTypeMismatch", JakartaVersion.EE_9),
    NAMED_ENTITY_GRAPH_ON_NON_ENTITY_CLASS("NamedEntityGraphOnNonEntityClass", JakartaVersion.EE_9),
    NAMED_ENTITY_GRAPHS_ON_NON_ENTITY_CLASS("NamedEntityGraphsOnNonEntityClass", JakartaVersion.EE_9),
    NAMED_QUERY_ON_INVALID_CLASS("NamedQueryOnInvalidClass", JakartaVersion.EE_9),
    NAMED_QUERIES_ON_INVALID_CLASS("NamedQueriesOnInvalidClass", JakartaVersion.EE_9),
    NAMED_NATIVE_QUERY_ON_INVALID_CLASS("NamedNativeQueryOnInvalidClass", JakartaVersion.EE_9),
    NAMED_NATIVE_QUERIES_ON_INVALID_CLASS("NamedNativeQueriesOnInvalidClass", JakartaVersion.EE_9),
    INHERITANCE_ANNOTATION_ON_NON_ENTITY_CLASS("InheritanceAnnotationOnNonEntityClass", JakartaVersion.EE_9),
    INHERITANCE_ANNOTATION_ON_NON_ROOT_ENTITY("InheritanceAnnotationOnNonRootEntity", JakartaVersion.EE_9),
    TABLE_GENERATOR_INVALID_EMPTY_NAME("TableGeneratorInvalidEmptyName", JakartaVersion.EE_9),
    TABLE_GENERATORS_MISSING_TABLE_GENERATOR_MAPPING("TableGeneratorsMissingTableGeneratorMapping", JakartaVersion.EE_9),
    SEQUENCE_GENERATOR_INVALID_EMPTY_NAME("SequenceGeneratorInvalidEmptyName", JakartaVersion.EE_9),
    SEQUENCE_GENERATORS_MISSING_SEQUENCE_GENERATOR_MAPPING("SequenceGeneratorsMissingSequenceGeneratorMapping", JakartaVersion.EE_9),
    SECONDARY_TABLE_INVALID_EMPTY_NAME("SecondaryTableInvalidEmptyName", JakartaVersion.EE_9),
    SECONDARY_TABLES_MISSING_SECONDARY_TABLE_MAPPING("SecondaryTablesMissingSecondaryTableMapping", JakartaVersion.EE_9),
    CONVERTER_MUST_IMPLEMENT_ATTRIBUTE_CONVERTER("ConverterMustImplementAttributeConverter", JakartaVersion.EE_9),
    ID_CLASS_MUST_BE_PUBLIC("IdClassMustBePublic", JakartaVersion.EE_9),
    ID_CLASS_MUST_HAVE_PUBLIC_NO_ARG_CONSTRUCTOR("IdClassMustHavePublicNoArgConstructor", JakartaVersion.EE_9),
    ID_CLASS_MUST_BE_SERIALIZABLE("IdClassMustBeSerializable", JakartaVersion.EE_9),
    ID_CLASS_MUST_DECLARE_EQUALS("IdClassMustDeclareEquals", JakartaVersion.EE_9),
    ID_CLASS_MUST_DECLARE_HASH_CODE("IdClassMustDeclareHashCode", JakartaVersion.EE_9),
    INVALID_CONSTRUCTOR_IN_ENTITY_LISTENER("InvalidConstructorInEntityListener", JakartaVersion.EE_9),
    INVALID_ENTITY_LISTENER_TYPE("InvalidEntityListenerType", JakartaVersion.EE_9),
    INVALID_ATTRIBUTE_OVERRIDE_NAME("InvalidAttributeOverrideName", JakartaVersion.EE_9),
    INVALID_ASSOCIATION_OVERRIDE_NAME("InvalidAssociationOverrideName", JakartaVersion.EE_9),
    PERSISTENCE_CONTEXT_NOT_IN_MANAGED_COMPONENT("PersistenceContextNotInManagedComponent", JakartaVersion.EE_9),
    EXTENDED_PERSISTENCE_CONTEXT_IN_NON_STATEFUL_BEAN("ExtendedPersistenceContextInNonStatefulBean", JakartaVersion.EE_9),
    INVALID_CONVERT_ANNOTATION_MISSING_CONVERTER_OR_DISABLE("InvalidConvertAnnotationMissingConverterOrDisable", JakartaVersion.EE_9),
    INVALID_CONVERT_ANNOTATION_ON_RESTRICTED_TARGET("InvalidConvertAnnotationOnRestrictedTarget", JakartaVersion.EE_9),
    MULTIPLE_CONVERT_ANNOTATION_ON_SAME_ATTRIBUTE("MultipleConvertAnnotationOnSameAttribute", JakartaVersion.EE_9),
    MAPS_ID_ON_INVALID_RELATIONSHIP("MapsIdOnInvalidRelationship", JakartaVersion.EE_9),
    MAPS_ID_REQUIRES_COMPOSITE_KEY("MapsIdRequiresCompositeKey", JakartaVersion.EE_9),
    MAPS_ID_VALUE_NOT_IN_EMBEDDED_ID_CLASS("MapsIdValueNotInEmbeddedIdClass", JakartaVersion.EE_9),
    MAPS_ID_VALUE_TYPE_MISMATCH("MapsIdValueTypeMismatch", JakartaVersion.EE_9),
    MAPS_ID_PRIMARY_KEY_TYPE_MISMATCH("MapsIdPrimaryKeyTypeMismatch", JakartaVersion.EE_9),
    DUPLICATE_NAMED_ENTITY_GRAPH_NAME("DuplicateNamedEntityGraphName", JakartaVersion.EE_9),

    // --- PersistenceMapKeyDiagnosticsCollector ---
    MAP_KEY_ENUMERATED_ON_NON_MAP_TYPE("MapKeyEnumeratedOnNonMapType", JakartaVersion.EE_9),
    MAP_KEY_ENUMERATED_ON_NON_ENUM_TYPE("MapKeyEnumeratedOnNonEnumType", JakartaVersion.EE_9),
    REMOVE_MAP_KEY_OR_MAP_KEY_CLASS("RemoveMapKeyorMapKeyClass", JakartaVersion.EE_9),
    SUPPLY_ATTRIBUTES_TO_ANNOTATIONS("SupplyAttributesToAnnotations", JakartaVersion.EE_9),
    INVALID_METHOD_ACCESS_SPECIFIER("InvalidMethodAccessSpecifier", JakartaVersion.EE_9),
    INVALID_METHOD_NAME("InvalidMethodName", JakartaVersion.EE_9),
    INVALID_MAP_KEY_ANNOTATIONS_FIELD_NOT_FOUND("InvalidMapKeyAnnotationsFieldNotFound", JakartaVersion.EE_9),
    INVALID_RETURN_TYPE_OF_METHOD("InvalidReturnTypeOfMethod", JakartaVersion.EE_9),
    INVALID_TYPE_OF_FIELD("InvalidTypeOfField", JakartaVersion.EE_9),
    MAP_KEY_TEMPORAL_NOT_ON_TEMPORAL_TYPE("MapKeyTemporalNotOnTemporalType", JakartaVersion.EE_9),

    // --- SecurityDiagnosticsCollector ---
    MISSING_APPLICATION_SCOPED_ON_IDENTITY_STORE_DEFINITION("MissingApplicationScopedOnIdentityStoreDefinition", JakartaVersion.EE_9),
    INVALID_SCOPE_ON_IDENTITY_STORE_DEFINITION("InvalidScopeOnIdentityStoreDefinition", JakartaVersion.EE_9),

    // --- ServletDiagnosticsCollector ---
    EXTEND_HTTP_SERVLET("ExtendHttpServlet", JakartaVersion.EE_9),
    COMPLETE_HTTP_SERVLET_ATTRIBUTES("CompleteHttpServletAttributes", JakartaVersion.EE_9),
    INVALID_HTTP_SERVLET_ATTRIBUTE("InvalidHttpServletAttribute", JakartaVersion.EE_9),
    IMPLEMENT_FILTER("ImplementFilter", JakartaVersion.EE_9),
    COMPLETE_WEB_FILTER_ATTRIBUTES("CompleteWebFilterAttributes", JakartaVersion.EE_9),
    INVALID_WEB_FILTER_ATTRIBUTE("InvalidWebFilterAttribute", JakartaVersion.EE_9),
    IMPLEMENT_LISTENER("ImplementListener", JakartaVersion.EE_9),

    // --- WebSocketDiagnosticsCollector ---
    ON_OPEN_CHANGE_INVALID_PARAM("OnOpenChangeInvalidParam", JakartaVersion.EE_9),
    ON_CLOSE_CHANGE_INVALID_PARAM("OnCloseChangeInvalidParam", JakartaVersion.EE_9),
    ON_MESSAGE_DUPLICATE_METHOD("OnMessageDuplicateMethod", JakartaVersion.EE_9),
    CHANGE_INVALID_SERVER_ENDPOINT("ChangeInvalidServerEndpoint", JakartaVersion.EE_9),
    INVALID_ENDPOINT_WITHOUT_STARTING_SLASH("InvalidEndpointWithoutStartingSlash", JakartaVersion.EE_9),
    MISSING_PUBLIC_NO_ARG_CONSTRUCTOR_WEB_SOCKET("missingPublicNoArgConstructor", JakartaVersion.EE_9),
    DUPLICATE_LIFE_CYCLE_ANNOTATION("DuplicateLifeCycleAnnotation", JakartaVersion.EE_9),
    CHANGE_PATH_PARAM_VALUE("ChangePathParamValue", JakartaVersion.EE_9),
    ADD_PATH_PARAMS_ANNOTATION("AddPathParamsAnnotation", JakartaVersion.EE_9);

    // --- EE 10 Diagnostics ---

    // --- EE 11 Diagnostics ---

    private final String code;
    private final JakartaVersion minVersion;
    private final JakartaVersion maxVersion;

    JakartaDiagnostic(String code, JakartaVersion minVersion) {
        this(code, minVersion, null);
    }

    JakartaDiagnostic(String code, JakartaVersion minVersion, JakartaVersion maxVersion) {
        this.code = code;
        this.minVersion = minVersion;
        this.maxVersion = maxVersion;
    }

    public String getCode() {
        return code;
    }

    public JakartaVersion getMinVersion() {
        return minVersion;
    }

    /**
     * Checks if this diagnostic is relevant for the given project version.
     * Usually, a rule introduced in EE9 is also valid in EE10 and EE11.
     */
    public boolean isApplicableTo(JakartaVersion projectVersion) {
        if (projectVersion == JakartaVersion.UNKNOWN)
            return false;

        int projectLevel = projectVersion.getLevel();
        int minLevel = this.minVersion.getLevel();

        // 1. Check if the project is too old for this rule
        if (projectLevel < minLevel) {
            return false;
        }

        // 2. Check if the project is too new for this rule (if a max limit exists)
        if (this.maxVersion != null) {
            int maxLevel = this.maxVersion.getLevel();
            if (projectLevel > maxLevel) {
                return false;
            }
        }

        return true;
    }

    /**
     * Helper to get all diagnostics for a specific version strictly.
     */
    public static List<JakartaDiagnostic> getRulesForExactly(JakartaVersion version) {
        return Arrays.stream(values()).filter(d -> d.minVersion == version).collect(Collectors.toList());
    }

    public static JakartaDiagnostic getByCodeOrNull(String code) {
        return Arrays.stream(values()).filter(d -> d.code.equals(code)).findFirst().orElse(null);
    }
}
