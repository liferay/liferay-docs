# Customizing Core Functionality with Ext Plugins

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
- [Deploy an Ext plugin in Production](#deploying-in-production)

You'll start by creating an Ext plugin.

### Creating an Ext Plugin

An Ext plugin is a powerful tool for extending @product@. Because it increases		
the complexity of your @product@ installation, you should only use an Ext plugin		
if you're sure you can't accomplish your goal in a different way. You can		
create Ext plugins using the pre-configured `plugins-ext` project
template/archetype. See the
[plugins-ext](/develop/reference/-/knowledge_base/7-1/plugins-ext-template)
project template article for information on how to create an Ext plugin, its
folder structure, and other important details.

<!-- TODO: Verify new project template (plugins-ext?) name when it's available.
-->

It's recommended to create and develop your Ext plugin in a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace).
Workspace is preconfigured with an `ext` folder, which applies important
settings (via the `LiferayExtPlugin`) to your Ext plugin when it's deployed to
@product@. You'll learn more about this in the next section.

<!-- TODO: Finish section once there's more info on template and how we want to
describe folder structure. -->

#### Anatomy of an Ext Plugin [](id=anatomy-of-an-ext-plugin)

There are a few things to note about an Ext plugin's folder structure. Below
is a listing of an Ext folder structure:

- `[project name]-ext/`
    - `docroot/`
        - `WEB-INF/`
            - `ext-impl/`
                - `src/`
            -  `ext-lib/`
                - `global/`
                - `portal/`
            - `ext-service/`
                - `src/`
            - `ext-util-bridges/`
                - `src/`
            - `ext-util-java/`
                - `src/`
            - `ext-util-taglib/`
                - `src/`
            - `ext-web/`
                - `docroot/`

Here are detailed explanations of the `/docroot/WEB-INF/` subfolders: 

- `ext-impl/src`: Contains the custom implementation classes and classes that
  override core classes from `portal-impl.jar`. 

- `ext-lib/global`: Contains libraries to be copied to the application server's
  global classloader upon deployment of the Ext plugin. 

- `ext-lib/portal`: Contains libraries to be copied inside Liferay's main 
  application. These libraries are usually necessary because they're invoked
  from  classes you add in `ext-impl/src`. 

- `ext-kernel/src`: Contains classes that should be available to other plugins.
  In advanced scenarios, this folder can be used to hold classes that overwrite
  classes from `portal-kernel.jar`. 

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

- [`docroot/WEB-INF/liferay-plugin-package.properties`](@platform-ref@/7.1-latest/propertiesdoc/liferay-plugin-package_7_1_0.properties.html):
  Contains plugin-specific properties, including the plugin's display name,
  version, author, and license type. 

- `docroot/WEB-INF/ext-web/docroot/WEB-INF` files: 

   - `portlet-ext.xml`: Used to overwrite the definition of a build-in portlet.
     The `portlet.xml` file contains very few portlet configurations in
     @product-ver@ because portlets were modularized and moved out of core. To
     override this file, copy the complete definition of the desired portlet
     from `portlet-custom.xml` in Liferay's source code, then apply the
     necessary changes.
   - `liferay-portlet-ext.xml`: This file is similar to `portlet-ext.xml`, but
     is for additional definition elements specific to Liferay. The
     `liferay-portlet.xml` file contains very few definition elements in
     @product-ver@ because portlets were modularized and moved out of core. To
     override the remaining definition elements, copy the complete definition of
     the desired portlet from `liferay-portlet.xml` in Liferay's source code,
     then apply the necessary changes.
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

It's strongly recommended to only develop/deploy one Ext plugin. This means that
all your customizations should live inside one Ext plugin. Liferay Workspace
does not check for conflicts among multiple Ext plugins stored in the `/ext`
folder, so do **not** develop/deploy multiple Ext plugins at once.

<!-- TODO: Verify:

The Plugins SDK lets you deploy and redeploy your Ext plugin during your
development phase. Redeployment involves *cleaning* (i.e., removing) your
application server and unzipping your specified Liferay bundle to start from
scratch. That way, any changes made to the Ext plugin during development are
properly applied, and files removed from your plugin by previous changes aren't
left behind in the @product@ application. Because of this added complexity, you
should use another plugin type to accomplish your goals whenever possible. -->

Before digging in to the details, here's an overview of the Ext plugin
development processes described below: 

- *Configure* your Plugins SDK environment to develop Ext plugins for
  @product@ on your application server. 
- *Deploy* and *publish* your Ext plugins for the first time. 
- *Redeploy* normally or use a *clean redeployment* process after making
  changes to your Ext plugins.
- *Package* your Ext plugins for distribution. 
- A list of advanced customization techniques. 

It's time to learn each step of the development process. 

### Set Up the Build Environment [](id=set-up-the-build-environment)

If you're leveraging
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace),
you should put your Ext module project in the `/ext` folder (default); you can
specify a different Ext folder name in workspace's `gradle.properties` by adding

    liferay.workspace.ext.dir=EXT_DIR

If you are developing an Ext module project in standalone mode (not associated
with Liferay Workspace), you must declare the Ext Gradle plugin in your
`build.gradle`:

    apply plugin: 'com.liferay.ext.plugin'

Next you'll modify the Ext plugin you created and deploy it. 

## Deploying an Ext Plugin

<!--

### Initial Deployment [](id=initial-deployment)

Your environment is set up and you're ready to start customizing. Once you're
finished, you can deploy the plugin. 

#### Deploy the Plugin [](id=deploy-the-plugin)

Now you'll learn how to deploy your plugin using Liferay @ide@ or the command
line. 

##### Deploying via the Command Line [](id=deploying-via-the-command-line)

1.  Open a command line window in your Ext plugin project folder and enter one
    of these commands: 

        ant deploy

        ant direct-deploy

    The `direct-deploy` target deploys all changes directly to the appropriate
    folders in the Liferay application. The `deploy` target creates a `.war`
    file with your changes and then deploys it to your server.  Either way, your
    server must be restarted after the deployment. Using `direct-deploy` is
    preferred for deploying an Ext plugin during development if your application
    server supports it. Direct deploy doesn't work in WebLogic or WebSphere
    application server environments.

    A `BUILD SUCCESSFUL` message indicates your plugin is now being deployed.
    Your console window running @product@ shows a message like this:

		    Extension environment for [your project]-ext has been applied. You must
		    reboot the server and redeploy all other plugins

    If any changes applied through the Ext plugin affect the deployment process
    itself, you must redeploy all other plugins. Even if the Ext plugin doesn't
    affect the deployment process, it's a best practice to redeploy all your
    other plugins following initial deployment of the Ext plugin. 

    The `ant deploy` target builds a `.war` file with your changes and copies
    them to the auto-deploy folder inside the @product@ installation. When the
    server starts, it detects the `.war` file, inspects it, and copies its
    contents to the appropriate destinations inside the deployed and running
    Liferay application.

2.  Restart the server so that it detects and publishes your Ext plugin. 

    Once your server restarts, open @product@ to see the customizations you
    made with your Ext plugin.
    
Frequently, you'll want to add further customizations to your original Ext
plugin. You can make unlimited customizations to an Ext plugin that has already
been deployed, but the redeployment process is different from other project
types. You'll learn more on redeploying your Ext plugin next.

### Redeployment [](id=redeployment)

So far, Ext plugin development has been similar to the development of other
plugin types. You've now reached the point of divergence. When the plugin is
first deployed, some of its files are copied into the @product@ installation.
After changing an Ext plugin, you'll either *clean redeploy* or *redeploy*,
depending on the modifications you made to your plugin following the initial
deployment. You'll learn about each redeployment method and when to use each
one.

#### Clean Redeployment [](id=clean-redeployment)

If you removed part(s) of your plugin, if there are changes to your plugin that
can affect plugin deployment, or if you want to start with a clean @product@
environment, *undeploy* your plugin and *clean* your application server before
redeploying your Ext plugin. By cleaning the application server, the existing
@product@ installation is removed and the bundle specified in your Plugins SDK
environment is unzipped in its place. See the instructions below to learn more
about this process.

##### Using the Command Line [](id=using-the-command-line)

1.  Stop the @product@ server. 

2.  To deploy your Ext plugin, enter the following commands into your console: 

        cd [your-plugin-ext]
        ant clean-app-server
        ant direct-deploy

3.  Start the @product@ server. 

#### Redeployment [](id=redeployment-0)

If you only added to your plugin or made modifications that don't affect the
plugin deployment process, you can redeploy the Ext plugin. Follow the steps
based on the tool you're using.

##### Using the Command Line [](id=using-the-command-line-0)

Using the same procedure as for initial deployment. Open a command line window
in your Ext plugin project's directory and execute either `ant deploy` or `ant
direct-deploy`.

After your plugin is published to @product@, verify that your customizations are
available.

Next you'll learn how to package an Ext plugin for distribution and production.

### Distribution [](id=distribution)

Once you're finished developing an Ext plugin, you can package it in a `.war`
file for distribution and production deployment. 

#### Using the Command Line [](id=using-the-command-line-1)

From your Ext plugin's directory execute

    ant war

The `.war` file is written to your `[liferay-plugins]/dist` directory.

Now that you've learned the basics of Ext plugin development, you'll look at
some advanced customizations.

-->

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
      [Increasing or decreasing the log level of a class or class hierarchy](/develop/tutorials/-/knowledge_base/7-1/adjusting-plugin-logging)
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

You'll learn how to deploy your Ext plugin in production next.

