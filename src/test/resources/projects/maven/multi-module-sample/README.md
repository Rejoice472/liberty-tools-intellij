# Multi-Module Jakarta EE Test Sample

This test project simulates a real-world Maven multi-module project structure where source files exist in both the parent project and child modules, with distinct and multiple Jakarta EE versions per module, producing targeted Jakarta diagnostics.

## Project Structure & Diagnostics Matrix

```
multi-module-sample/                              --> Parent Project (Jakarta EE 11 + EE 10)
├── pom.xml                                           - jakarta.ws.rs-api:4.0.0 (EE 11)
├── src/main/java/org/example/parent/                  - jakarta.annotation-api:2.1.1 (EE 10)
│   ├── ParentApplication.java                        --> Diagnostics: PostConstructReturnType, PostConstructParams
│   └── ParentConfig.java                             --> Diagnostics: NonPublicResourceMethod
├── module-a/                                     --> Module A (Jakarta EE 10 + EE 9)
│   ├── pom.xml                                       - jakarta.persistence-api:3.1.0 (EE 10)
│   └── src/main/java/org/example/modulea/            - jakarta.servlet-api:5.0.0 (EE 9)
│       ├── ModuleAService.java                       --> Diagnostics: InvalidFinalModifierOnEntityAnnotatedClass, InvalidFinalMethodInEntityAnnotatedClass
│       └── ModuleAHelper.java                        --> Diagnostics: WebServletAnnotationMissingAttributes
└── module-b/                                     --> Module B (Jakarta EE 11 + EE 9)
    ├── pom.xml                                       - jakarta.persistence-api:3.2.0 (EE 11)
    └── src/main/java/org/example/moduleb/            - jakarta.websocket-api:2.0.0 (EE 9)
        ├── ModuleBService.java                       --> Diagnostics: InvalidEndpointPathWithNoStartingSlash
        └── ModuleBHelper.java                        --> Diagnostics: MissingPrimaryKey
```
