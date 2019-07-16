---
header-id: adding-a-third-party-librarys-capabilities-to-the-resolvers-capabilities
---

# Adding a Third Party Library's Capabilities to the Resolver's Capabilities

[TOC levels=1-4]

You can add your third party dependencies to the target platform's default list
of capabilities by listing them as provided modules.

1.  Open your workspace's root `build.gradle` file.

2.  Add a code snippet similar to this:

    ```groovy
    dependencies {
        providedModules group: "GROUP_ID", name: "NAME", version: "VERSION"
    }
    ```

    For example, if you wanted to add
    [Google Guava](https://opensource.google.com/projects/guava) as a provided
    module, it would look like this:

    ```groovy
    dependencies {
        providedModules group: "com.google.guava", name: "guava", version: "23.0"
    }
    ```

This both provides the third party dependency to the resolver, and it downloads
and includes it in your @product@ bundle's `osgi/modules` folder when you
initialize it (e.g., `blade server init`).
