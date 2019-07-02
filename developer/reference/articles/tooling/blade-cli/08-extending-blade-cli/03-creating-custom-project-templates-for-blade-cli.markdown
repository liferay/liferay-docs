---
header-id: creating-custom-project-templates-for-blade-cli
---

# Creating Custom Project Templates for Blade CLI

[TOC levels=1-4]

Blade comes with over 32+ project templates, but many times you may feel that
those are too simple or don't fit the need for your development team. You can
create new custom project templates that fit your team's workflow and have Blade
use it instead.

The setup of a custom project template should mimic that of a Maven archetype.
The best way to illustrate this is by visualizing a
[sample template](https://github.com/liferay/liferay-blade-cli/tree/master/extensions/sample-template)'s
folder structure:

- `src/`
    - `main/resources/`
        - `META-INF`
            - `maven`
                - `archetype-metadata.xml`
            - `archetype-post-generate.groovy`
        - `archetype-resources`
            - Folder structure to be generated
- `bnd.bnd`
- `build.gradle`

You can read more about Maven archetypes and their features and capabilities
[here](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html).

To create a custom project template that can be generated using Blade CLI,
follow these steps:

1.  Create a generic Maven archetype following the folder structure outlined
    above. Follow Maven's documentation to configure the archetype project
    appropriately.

2.  Open the template's `bnd.bnd` file and ensure it sets the following
    configurations:

    ```
    Bundle-Description: TEMPLATE_DESCRIPTION
    Bundle-Name: TEMPLATE_NAME
    Bundle-SymbolicName: SYMBOLIC_NAME
    Bundle-Version: TEMPLATE_VERSION
    Liferay-Versions: LIFERAY_VERSION_RANGE
    -removeheaders:\
        Import-Package,\
        Private-Package,\
        Require-Capability
    ```

    For example, a template's `bnd.bnd` could look like this:

    ```
    Bundle-Description: Creates a Sample as a module project.
    Bundle-Name: Liferay Project Templates Sample
    Bundle-SymbolicName: com.liferay.project.templates.sample
    Bundle-Version: 1.0.0
    Liferay-Versions: [7,8)
    -removeheaders:\
        Import-Package,\
        Private-Package,\
        Require-Capability
    ```

    The `Bundle-SymbolicName` of your template JAR must have the pattern
    `*.project.templates.<name>.*`. The `-removeheaders` definition is a
    packaging requirement for all project templates.

3.  Generate the extension's JAR file (e.g., `gradlew build`).

It's that easy! You've created a custom project template. See the
[Installing New Extensions](/docs/7-2/reference/-/knowledge_base/r/installing-new-extensions-for-blade-cli)
article to install the project template (JAR) to Blade CLI.

You can examine a working custom project template
[here](https://github.com/liferay/liferay-blade-cli/blob/master/extensions/sample-template).
