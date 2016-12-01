# Creating form Field Types



There



## Anatomy of a Field Type Module

Field Type modules have this structure:

    bnd.bnd
    build.gradle
    src
    └── main
        ├── java
        │   └── com
        │       └── liferay
        │           └── dynamic
        │               └── data
        │                   └── mapping
        │                       └── type
        │                           └── paragraph
        │                               └── internal
        │                                   ├── ParagraphDDMFormFieldRenderer.java
        │                                   ├── ParagraphDDMFormFieldTemplateContextContributor.java
        │                                   ├── ParagraphDDMFormFieldType.java
        │                                   └── ParagraphDDMFormFieldTypeSettings.java
        └── resources
            ├── content
            │   ├── Language.properties
            │   └── Language_xx_XX.properties
            └── META-INF
                └── resources
                    ├── config.js
                    ├── paragraph_field.js
                    ├── paragraph.soy
                    └── paragraph.soy.js

This is the exact structure of the `dynamic-data-mapping-type-paragraph` module
in the Forms and Workflow application suite.
