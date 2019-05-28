---
header-id: anatomy-of-an-ext-plugin
---

# Anatomy of an Ext Plugin

[TOC levels=1-4]

There are two ways you can structure your Ext plugin. The
[`war-core-ext`](/docs/7-2/reference/-/knowledge_base/r/war-core-ext-template)
project template/archetype creates the default layout:

- `[project name]-ext/`
    - `src/`
        - `extImpl/`
            - `resources/`
        - `extKernel/`
            - `resources/`
        - `extUtilBridges/`
            - `resources/`
        - `extUtilJava/`
            - `resources/`
        - `extUtilTaglib/`
            - `resources/`
        - `main/`
            - `webapp/`
                - `WEB-INF/`
                    - `ext-web/`
                        - `docroot/`

You can also follow the legacy layout that was used for Ext plugins created with
the Plugins SDK in past versions:

- `[project name]-ext/`
    - `docroot/`
        - `WEB-INF/`
            - `ext-impl/`
                - `src/`
            - `ext-kernel/`
                - `src/`
            - `ext-util-bridges/`
                - `src/`
            - `ext-util-java/`
                - `src/`
            - `ext-util-taglib/`
                - `src/`
            - `ext-web/`
                - `docroot/`
                    - `WEB-INF/`

Although the folder names are slightly different, they work the same. This
article will refer to the default structure and naming. Here are detailed
explanations of the subfolders: 

- `extImpl`: Contains the custom implementation classes and classes that
  override core classes from `portal-impl.jar`. 

- `extKernel`: Contains classes that should be available to other plugins. In
  advanced scenarios, this folder can be used to hold classes that overwrite
  classes from `portal-kernel.jar`. 

- `extUtilBridges`, `extUtilJava` and `extUtilTaglib`: These folders are needed
  only in scenarios where you must customize these Liferay libraries:
  `util-bridges.jar`, `util-java.jar` and `util-taglib.jar`, respectively. If
  you're not customizing any of these libraries, you can ignore these folders. 

- `main/webapp/WEB-INF/ext-web/docroot`: Contains the web application's
  configuration files, including `WEB-INF/struts-config-ext.xml`, which lets you
  customize Liferay's core struts classes. Note that for @product-ver@, there
  are very few entities left to override in the `struts-config.xml` file. Any
  JSPs that you're customizing also belong here. 

By default, several files are also added to the plugin. Here are the most 
significant files: 

- `build.gradle`/`pom.xml`: The build file for the Ext plugin project. 

- [`src/main/webapp/WEB-INF/liferay-plugin-package.properties`](@platform-ref@/7.1-latest/propertiesdoc/liferay-plugin-package_7_1_0.properties.html):
  Contains plugin-specific properties, including the plugin's display name,
  version, author, and license type. 

- `src/main/webapp/WEB-INF/ext-web/docroot/WEB-INF` files: 

    - `liferay-portlet-ext.xml`: This file is similar to `portlet-ext.xml`, but
     is for additional definition elements specific to Liferay. The
     `liferay-portlet.xml` file contains very few definition elements in
     @product-ver@ because portlets were modularized and moved out of core. To
     override the remaining definition elements, copy the complete definition of
     the desired portlet from `liferay-portlet.xml` in Liferay's source code,
     then apply the necessary changes.

    - `portlet-ext.xml`: Used to overwrite the definition of a build-in portlet.
     The `portlet.xml` file contains very few portlet configurations in
     @product-ver@ because portlets were modularized and moved out of core. To
     override this file, copy the complete definition of the desired portlet
     from `portlet-custom.xml` in Liferay's source code, then apply the
     necessary changes.

    - `struts-config-ext.xml` and `tiles-defs-ext.xml`: These files are used to
     customize the struts actions used by core portlets. Since most portlets
     were modularized and moved out of core in @product-ver@, the
     `struts-config.xml` and `tiles-defs.xml` files are sparsely used.

    - `web.xml`: Used to overwrite web application configurations and servlet
     information for @product-ver@.

| **Note:** After creating an Ext plugin, remove the files from
| `docroot/WEB-INF/ext-web/docroot/WEB-INF` that you don't need to customize.
| Removing files you're not customizing prevents incompatibilities that could
| manifest from @product@ updates.

Great! Now you're familiar with an Ext plugin's folder structure and its most
significant files.
