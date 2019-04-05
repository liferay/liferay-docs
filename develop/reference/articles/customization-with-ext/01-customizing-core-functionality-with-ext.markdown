# Customizing Core Functionality with Ext [](id=customizing-core-functionality-with-ext)

+$$$

**Ext plugins are deprecated for @product-ver@ and should only be used if
absolutely necessary.**

The following app servers should be used for Ext plugin development in
@product@:

- Tomcat 9.0

In most cases, Ext plugins are not necessary. There are, however, certain cases
that require the use of an Ext plugin. Liferay only supports the following Ext
plugin use cases:

- Providing custom implementations for any beans declared in @product@'s
  Spring files (when possible, use
  [service wrappers](/develop/tutorials/-/knowledge_base/7-1/customizing-liferay-services-service-wrappers)
  instead of an Ext plugin). @product-ver@ removed many beans, so make sure your
  overridden beans are still relevant if converting your legacy Ext plugin.
- Overwriting a class in a @product-ver@ core JAR. For a list of core JARs, see
  the [Finding Core @product@ Artifacts](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies#finding-core-liferay-portal-artifacts)
  section.
- Modifying @product@'s `web.xml` file.
- Adding to @product@'s `web.xml` file.

**Note:** In previous versions of Liferay Portal, you needed an Ext plugin to
specify classes as portal property values (e.g.,
`global.starup.events.my.custom.MyStartupAction`), since the custom class had to
be added to the portal class loader. This is no longer the case in @product-ver@
since all lifecycle events can use OSGi services with no need to edit these
legacy properties.

$$$

Ext plugins are used to customize @product@'s core functionality. You can learn
more about what the core encompasses in the
[Finding Core @product@ Artifacts](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies#finding-core-liferay-portal-artifacts)
section. In this tutorial, you'll learn how to

- [Create an Ext plugin](#creating-an-ext-plugin)
- [Develop an Ext plugin](#developing-an-ext-plugin)
- [Deploy an Ext plugin](#deploying-an-ext-plugin)
- [Redeploy an Ext plugin](#redeploying-an-ext-plugin)

You'll start by creating an Ext plugin.

## Creating an Ext Plugin [](id=creating-an-ext-plugin)

An Ext plugin is a powerful tool for extending @product@. Because it increases		
the complexity of your @product@ installation, you should only use an Ext plugin		
if you're sure you can't accomplish your goal in a different way. You can		
create Ext plugins using the pre-configured `plugins-ext` project
template/archetype. See the
[plugins-ext](/develop/reference/-/knowledge_base/7-1/plugins-ext-template)
project template article for information on how to create an Ext plugin, its
folder structure, and other important details.

<!-- TODO: Verify new project template (plugins-ext?) name when it's available.
Also fix link.
-->

It's recommended to create and develop your Ext plugin in a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace).
Workspace is preconfigured with an `ext` folder, which applies important
settings (via the `LiferayExtPlugin`) to your Ext plugin when it's deployed to
@product@. You'll learn more about this in the
[Set Up the Build Environment](#set-up-the-build-environment) section.

Next you'll learn the anatomy of an Ext plugin.

### Anatomy of an Ext Plugin [](id=anatomy-of-an-ext-plugin)

There are two ways you can structure your Ext plugin. The `plugins-ext` project
template/archetype creates the default layout:

- `[project name]-ext/`
    - `docroot/`
        - `WEB-INF/`
            - `ext-impl/`
                - `src/`
            -  `ext-lib/`
                - `global/`
                - `portal/`
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

There is also an alternative layout you can follow:

- `[project name]-ext/`
    - `src/`
        - `extImpl/`
            - `resources/`
        - `extLib/`
            - `global/`
            - `portal/` 
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

Although the folder names are slightly different, they work the same. This
article will refer to the default structure and naming. Here are detailed
explanations of the subfolders: 

- `ext-impl/src`: Contains the custom implementation classes and classes that
  override core classes from `portal-impl.jar`. 

- `ext-lib/global`: Contains libraries to be copied to the application server's
  global classloader upon deployment of the Ext plugin. 
    
- `ext-lib/portal`: Contains libraries to be copied inside Liferay's main 
  application. These libraries are usually necessary because they're invoked
  from classes you add in `ext-impl/src`. 

- `ext-kernel/src`: Contains classes that should be available to other
  plugins. In advanced scenarios, this folder can be used to hold classes that
  overwrite classes from `portal-kernel.jar`. 

- `ext-util-bridges`, `ext-util-java` and `ext-util-taglib`: These folders are
  needed only in scenarios where you must customize these Liferay libraries:
  `util-bridges.jar`, `util-java.jar` and `util-taglib.jar`, respectively. If
  you're not customizing any of these libraries, you can ignore these folders. 

- `ext-web/docroot`: Contains the web application's configuration files,
  including `WEB-INF/struts-config-ext.xml`, which lets you customize Liferay's
  core struts classes. Note that for @product-ver@, there are very few entities
  left to override in the `struts-config.xml` file. Any JSPs that you're
  customizing also belong here. 

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

+$$$

**Note:** After creating an Ext plugin, remove the files from
`docroot/WEB-INF/ext-web/docroot/WEB-INF` that you don't need to customize.
Removing files you're not customizing prevents incompatibilities that could
manifest from @product@ updates. 

$$$

Great! You've now created an Ext plugin and are familiar with its folder
structure and its most significant files. Next, you'll use your Ext plugin to
customize @product@. 

## Developing an Ext Plugin [](id=developing-an-ext-plugin)

An Ext plugin changes @product@ itself when the plugin is deployed; it's not a
separate component that you can easily remove at any time. For this reason, the
Ext plugin development process is different from other project types. It's
important to remember that once an Ext plugin is deployed, some of its files are
copied *inside* the Liferay installation; the only way to remove the changes is
by *redeploying* an unmodified @product@ application. You're also responsible
for checking that patches and fix packs do not conflict with your Ext plugin.
Additionally, Ext plugins aren't hot deployable. To deploy an	 Ext plugin, you
must restart your server. Additional steps are also required to	deploy or
redeploy to production systems.

It's strongly recommended to only develop/deploy one Ext plugin. This means that
all your customizations should live inside one Ext plugin. Liferay Workspace
does not check for conflicts among multiple Ext plugins stored in the `/ext`
folder, so do **not** develop/deploy multiple Ext plugins at once.

You can deploy and redeploy your Ext plugin during your development phase.
Redeployment involves manually removing the Ext plugin and *cleaning* your
application server (i.e., remove it ); this is recommended so any changes made
to the Ext plugin during development are properly applied, and files removed
from your plugin by previous changes aren't left behind in the @product@
application. Because of this added complexity, you should use another plugin
type to accomplish your goals whenever possible. 

Now it's time to set up the build environment. 

### Set Up the Build Environment [](id=set-up-the-build-environment)

If you're leveraging
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace),
you should create/develop your Ext module project in the `/ext` folder
(default); you can specify a different Ext folder name in workspace's
`gradle.properties` by adding

    liferay.workspace.ext.dir=EXT_DIR

If you are developing an Ext module project in standalone mode (not associated
with Liferay Workspace), you must declare the Ext Gradle plugin in your
`build.gradle`:

    apply plugin: 'com.liferay.ext.plugin'

Next you'll explore an Ext plugin's advanced configuration files.

### Using Advanced Configuration Files [](id=using-advanced-configuration-files)

@product@ uses several internal configuration files for its own architecture; in
addition, there are configuration files for the libraries and frameworks
@product@ depends on, like Struts and Spring. Configuration could be
accomplished using fewer files with more properties in each, but maintenance and
use is made easier by splitting up the configuration properties into several
files. For advanced customization needs, it may be useful to override the
configuration specified in multiple configuration files. @product@ provides a
clean way to do this from an Ext plugin without modifying the original files. 

All the configuration files in @product@ are listed below by their path in an
Ext plugin folder. Here are descriptions of what each file is for and the path
to the original file in @product@: 

- `ext-impl/src/META-INF/ext-model-hints.xml`
    - **Description:** Overrides the default properties of the fields of
      the data models used by @product@'s core portlets. These properties
      determine how the form fields for each model are rendered. 
    - **Original file in @product@:**
      `portal-impl/src/META-INF/portal-model-hints.xml` 
- `ext-impl/src/META-INF/ext-spring.xml`
    - **Description:** Overrides the Spring configuration used by
      @product@ and any of its core portlets. It's most commonly used to
      configure specific data sources or swap the implementation of a default
      service with a custom one.
    - **Original file in @product@:** `portal-impl/src/META-INF/*-spring.xml`
- `ext-impl/src/META-INF/portal-log4j-ext.xml`
    - **Description:** Allows overriding the Log4j configuration. It can be used
      to configure appenders for log file location, naming, and rotation. See
      the
      [Log4j XML Configuration Primer](https://wiki.apache.org/logging-log4j/Log4jXmlFormat). 
      [Increasing or decreasing the log level of a class or class hierarchy](/develop/tutorials/-/knowledge_base/7-1/adjusting-module-logging)
      is best done outside of an Ext plugin, in @product@'s' UI or a Log4j XML
      file in a module or the `osgi/log4j` folder. 
    - **Original file in Liferay:** `portal-impl/src/META-INF/portal-log4j.xml`
- `ext-web/docroot/WEB-INF/portlet-ext.xml`
    - **Description:** Overrides the core portlets' declarations. It's most commonly
      used to change the init parameters or the roles specified. 
    - **Original file in @product@:** `portal-web/docroot/WEB-INF/portlet-custom.xml`
- `ext-web/docroot/WEB-INF/liferay-portlet-ext.xml`
    - **Description:** Overrides the Liferay-specific core portlets'
      declarations. It core portlets included in @product@. Refer to the
      [liferay-portlet-app_7_0_0.dtd](@platform-ref@/7.0-latest/definitions/liferay-portlet-app_7_0_0.dtd.html)
      file for details on all the available options. Use this file with care;
      the code of the portlets may be assuming some of these options to be set
      to certain values. 
    - **Original file in @product@:** `portal-web/docroot/WEB-INF/liferay-portlet.xml`
- `ext-web/docroot/WEB-INF/liferay-display.xml`
    - **Description:** Overrides the portlets that are shown in the interface
      for adding applications and the categories in which they're organized.
    - **Original file in @product@:** `portal-web/docroot/WEB-INF/liferay-display.xml`

Next you'll deploy your Ext plugin. 

## Deploying an Ext Plugin [](id=deploying-an-ext-plugin)

Deploying an Ext plugin is similar to deploying any other @product@ project. For
example, you'll step through deploying an Ext plugin in Liferay Workspace.

1.  From your Ext plugin root folder, run `blade deploy` (Gradle or Maven) or
    `mvn verify` (Maven only).

    The `deploy` target creates a `.war` file with your changes and then deploys
    it to your server. A `BUILD SUCCESSFUL` message indicates your plugin is now
    being deployed. Your console window running @product@ shows a message like
    this:

        Extension environment for sample-ext has been applied. You must
        reboot the server and redeploy all other plugins.

2.  Restart the server so that it detects and publishes your Ext plugin. Your
    server must be restarted after the initial deployment and every
    redeployment.

    If any changes applied through the Ext plugin affect the deployment process
    itself, you must redeploy all other plugins. Even if the Ext plugin doesn't
    affect the deployment process, it's a best practice to redeploy all your
    other plugins following initial deployment of the Ext plugin. 

    When the server starts, it detects the `.war` file, inspects it, and copies
    its contents to the appropriate destinations inside @product@.

3.  Once your server restarts, open @product@ to see the customizations you
    made with your Ext plugin.

Frequently, you'll want to add further customizations to your original Ext
plugin. You can make unlimited customizations to an Ext plugin that has already
been deployed, but the redeployment process is different from other project
types. You'll learn more on redeploying your Ext plugin next.

## Redeploying an Ext Plugin [](id=redeploying-an-ext-plugin)

After editing an Ext plugin, you must follow a slightly different process to
redeploy your Ext plugin. This section assumes you're redeploying an Ext plugin
in Liferay Workspace.

1.  Stop the @product@ server. 

2.  Delete your Ext plugin bundle, which resides in your app server's `webapps`
    folder.

3.  (Optional) If you removed part(s) of your plugin, if there are changes to
    your plugin that can affect plugin deployment, or if you want to start with
    a clean @product@ environment, you must **also** clean your application
    server. You can clean the application server by deleting your Liferay Home
    and regenerating the bundle. This is done in Liferay Workspace by calling
    `blade server init`.

4.  From your Ext plugin root folder, run `blade deploy` (Gradle or Maven) or
    `mvn verify` (Maven only).

    The `deploy` target creates a `.war` file with your changes and then deploys
    it to your server. A `BUILD SUCCESSFUL` message indicates your plugin is now
    being deployed. Your console window running @product@ shows a message
    like this:

        Extension environment for sample-ext has been applied. You must
        reboot the server and redeploy all other plugins.

5.  After your plugin is published to @product@, verify that your customizations
    are available.

You're all set to create, develop, deploy, and redeploy Ext plugins!
