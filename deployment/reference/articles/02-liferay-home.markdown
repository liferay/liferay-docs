---
header-id: liferay-home
---

# Liferay Home

[TOC levels=1-4]

*Liferay Home* is the location from which @product@ launches applications, 
applies configurations, loads JAR files, and generates logs. 

-   *In a @product@ bundle,* Liferay Home is the installation's top-level folder
    and it contains the application server.

-   *In a manual installation,* the Liferay Home folder varies by application
    server. If you're doing a manual installation, please refer to the article
    covering that app server (e.g., *Installing @product@ on [app server]*) for
    the Liferay Home location. 

Bundles contain this folder structure regardless of application server: 

-   **[Liferay Home]**
    -   **[Application Server]**: This folder is named after the application
        server where @product@ is installed. 
    -   `data` (if HSQL database is selected): Stores an embedded HSQL database,
        @product@'s file repository,
        and search indexes. The embedded HSQL database is configured by default,
        but it's intended for demonstration and trial
        purposes only. The
        [Portal property `jdbc.default.url`](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#JDBC)
        sets the Hypersonic embedded HSQL database location.
    -   `deploy`: To auto-deploy plugins, copy them to this folder. 
        It supports application `.lpkg` files from Liferay Marketplace, plugin
        `.war` files, and plugin `.jar` files. The
        [Portal property `auto.deploy.deploy.dir`](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Auto%20Deploy)
        sets the auto-deploy location.
    -   `license`: @product@'s copyright and version files are here.
    -   `logs`: Log files go here. Examine them as you diagnose
        problems. `portal-impl.jar`'s
        `portal-impl/src/META-INF/portal-log4j.xml` file sets the log file
        location. To override the log file location, you must
        [use an `ext-impl/src/META-INF/portal-log4j-ext.xml` file in an Ext plugin](/develop/tutorials/-/knowledge_base/7-0/advanced-customization-with-ext-plugins#using-advanced-configuration-files).
    -   `osgi`: All the JAR files and a few configuration files for the 
        OSGi runtime belong in this folder. The
        [Portal property `module.framework.base.dir`](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework)
        sets the OSGi folder location. Here are its subfolders:

        -   `configs`: Component configuration files.
        -   `core`: @product@'s core modules.
        -   `marketplace`: Marketplace applications and application suites.
        -   `modules`: Modules you've deployed.
        -   `portal`: @product@'s non-core modules.
        -   `state`: Contains OSGi internal state files for such things as OSGi 
            bundle installation, bundle storage, and more.
        -   `target-platform`: Target platform index.
        -   `test`: Modules that support test integration.
        -   `war`: WAR plugins you've deployed.
    -   `patching-tool`: (Liferay DXP only) This folder contains patches and 
        a utility for installing the patches.
    -   `tools`: For @product@ upgrade and target platform indexer.
    -   `work`: Module Jasper work files.

| **Note:** If @product@ is unable to create the resources it needs in the 
| Liferay Home folder or if it finds itself running on certain application
| servers, it creates a folder called `liferay` in the home folder of the
| operating system user that is running @product@. In this case, that `liferay`
| folder becomes Liferay Home. For example, if the operating system user's name
| is jbloggs, the `liferay` folder path is `/home/jbloggs/liferay` or
| `C:\Users\jbloggs\liferay`.
