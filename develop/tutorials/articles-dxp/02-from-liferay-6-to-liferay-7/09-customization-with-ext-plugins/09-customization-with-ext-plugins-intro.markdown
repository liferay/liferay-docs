# Customization with Ext Plugins [](id=advanced-customization-with-ext-plugins)

+$$$

**Ext plugins are deprecated for @product-ver@ and should only be used if
absolutely necessary. They are deployable to Liferay Digital Enterprise 7.0
Service Pack (SP) 4+.**

**For those upgrading via fix packs, Ext plugins are deployable to Liferay
Digital Enterprise 7.0 Fix Pack 16+. If you haven't upgraded to SP4+ and are
using a Tomcat app server, visit the		
[App Server Configuration](#app-server-configuration) section for details on		
modifications required to allow Ext plugins to function properly in that		
environment.**

The following app servers are supported for Ext plugin development in
@product@:

- Tomcat 8.0

<!--
- JBoss EAP 7.0
- Wildfly 10.0
- WebLogic 12.2
- WebSphere 8.5.5
-->

In most cases, Ext plugins are no longer necessary. There are, however, certain
cases that require the use of an Ext plugin. Liferay only supports the following
Ext plugin use cases:

- Providing custom implementations for any beans declared in @product@'s
  Spring files (when possible, use
  [service wrappers](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services-service-wrappers)
  instead of an Ext plugin). @product-ver@ removed many beans, so make sure your
  overridden beans are still relevant if converting your legacy Ext plugin
  ([tutorial](https://customer.liferay.com/documentation/knowledge-base/-/kb/1255605)).
- Overwriting a class in a @product-ver@ core JAR. For a list of core JARs, see
  the [Finding Core @product@ Artifacts](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies#finding-core-liferay-portal-artifacts)
  section
  ([tutorial](https://customer.liferay.com/documentation/knowledge-base/-/kb/1255575)).
- Modifying @product@'s `web.xml` file
  ([tutorial](https://customer.liferay.com/documentation/knowledge-base/-/kb/1255591)).
- Adding to @product@'s `web.xml` file
  ([tutorial](https://customer.liferay.com/documentation/knowledge-base/-/kb/1255563)).

Refer to each use case's linked tutorial for further information on that topic.

$$$

Ext plugins are powerful tools used to extend @product@. They, however, increase
the complexity of your @product@ instance and are not recommended unless there
is absolutely no other way to accomplish your task. @product-ver@ provides many
extension points that let you customize almost every detail of @product@. If
there's a way to customize what you want with an extension
point, do it that way instead. See the
[More Extensible, Easier to Maintain](/develop/tutorials/-/knowledge_base/7-0/benefits-of-liferay-7-for-liferay-6-developers#more-extensible-easier-to-maintain)
section for more details on the advantages of using @product@'s extension
points.

Before deciding to use an Ext plugin, weigh the cost. Ext plugins let you use
internal APIs and even let you overwrite @product@ core files. This puts
your deployment at risk of being incompatible with security, performance, or
feature updates released by Liferay. When upgrading to a new version of
@product@ (even if it's a maintenance version or a service pack), you have to
review all changes and manually modify your Ext plugin to merge your changes
with @product@'s. Additionally, Ext plugins aren't hot deployable. To deploy an
Ext plugin, you must restart your server. Additional steps are also required to
deploy or redeploy to production systems.

In this tutorial, you'll learn how to

- [Create an Ext plugin](#creating-an-ext-plugin)
- [Develop an Ext plugin](#developing-an-ext-plugin)
- [Deploy an Ext plugin in Production](#deploying-in-production)

Before diving into creating an Ext plugin, however, first consider if an Ext
plugin is necessary.

## Making the Decision to Use Ext Plugins [](id=making-the-decision-to-use-ext-plugins)

There are many parts of @product@ that now provide extension points via OSGi
bundle. You should follow this three step process to decide whether an Ext
plugin is necessary:

1.  Find the OSGi extension point that you need. You can follow the
    [Finding Extension Points](/develop/tutorials/-/knowledge_base/7-0/finding-extension-points)
    tutorial as a guide.
2.  If an OSGi extension point does not exist, use an Ext plugin.
3.  Research new extension points after every release of @product@. When a new
    version of @product@ provides the extension point need, always use the
    extension point to replace the existing Ext plugin.

So how do you find an OSGi extension point?

Your first step is to examine the custom module projects that extend popular
@product@ extension points stored in the
[Liferay Blade Samples](https://github.com/liferay/liferay-blade-samples)
repository. For
more information on these sample projects, see the
[Liferay Sample Modules](/develop/tutorials/-/knowledge_base/7-0/liferay-sample-modules)
tutorial. Usable extension points are also documented throughout Liferay's
Customer Portal categorized by the @product@ section involved. For example,
[Overriding MVC Commands](/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands)
and
[Customizing the Product Menu](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu)
are articles describing how to extend a @product@ extension point. Want to
learn how to
[override module JSPs](/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-jsps)
or [@product@ core JSPs](/develop/tutorials/-/knowledge_base/7-0/overriding-core-jsps)?
Those processes are documented too!

There are a few corner cases where you may need an Ext plugin to customize a
part of @product@ that does not provide an extension point. Refer to the top of
this tutorial for Ext plugin use cases supported by Liferay.

+$$$

**Note:** In previous versions of Liferay Portal, you needed an Ext plugin to
specify classes as portal property values (e.g.,
`global.starup.events.my.custom.MyStartupAction`), since the custom class had to
be added to the portal class loader. This is no longer the case in @product-ver@
since all lifecycle events can use OSGi services with no need to edit these
legacy properties.

$$$

Now that you know how to make an informed decision on using Ext plugins, if you
still need to use them, you'll learn how to create one next.

## Creating an Ext Plugin [](id=creating-an-ext-plugin)

An Ext plugin is a powerful tool for extending @product@. Because it increases
the complexity of your @product@ installation, you should only use an Ext plugin
if you're sure you can't accomplish your goal in a different way. You can only
create Ext plugins from a Plugins SDK. If you're using a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
to create your @project@ projects, you can
[merge a Plugins SDK instance](/develop/tutorials/-/knowledge_base/7-0/configuring-a-liferay-workspace#using-a-plugins-sdk-from-your-workspace)
into the workspace.

### Creating an Ext Plugin Using Liferay @ide@ [](id=creating-an-ext-plugin-using-liferay-ide)

Follow the steps below, replacing the project name with your own, to create a
custom Ext plugin:

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  Enter your project's name and its display name. In the figure below,
    *example* is used for both. Note that the Display name field is
    automatically filled in with the capitalized version of the Project name.

    ![Figure 1: You can create an Ext plugin project with Liferay IDE.](../../../images-dxp/ext-create-an-ext-plugin-project.png)

3.  Select the *Ant (liferay-plugins-sdk)* option for your build type.

4.  Select the *Ext* plugin type. Then click *Next*.

5.  If you have not yet configured a Plugins SDK, select *Next* and choose the
    Plugins SDK for which you want to generate the Ext plugin. Once you have
    your Plugins SDK configured, click *Finish*.

The Plugins SDK automatically appends `-ext` to the plugin project name when it
creates your Ext plugin's project folder. Your Ext plugin project is added to
your configured Plugins SDK instance and is also available via @ide@'s Package
Explorer for further development.

### Creating an Ext Plugin Using the Command Line [](id=creating-an-ext-plugin-using-the-command-line)

To create a new Ext plugin from the command line, navigate to the `ext` folder
in your Liferay Plugins SDK and enter the command below appropriate for your
operating system. The two arguments after the `create` command are the project
name and display name, respectively. The examples below use the project name
*example* and the display name *Example*. Make sure to specify values you want
to use for your Ext plugin's project name and display name.

In Linux or Mac OS, enter:

    ./create.sh example "Example"

In Windows, enter:

    create.bat example "Example"

A `BUILD SUCCESSFUL` message from Ant tells you there's a new folder (e.g.,
folder `example-ext` for a project named *example*) inside your Plugins SDK's
`ext` folder. The Plugins SDK automatically appends `-ext` to the project name. 

### Anatomy of an Ext Plugin [](id=anatomy-of-an-ext-plugin)

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
application. These libraries are usually necessary because they're invoked from 
classes you add in `ext-impl/src`. 

- `ext-kernel/src`: Contains classes that should be available to other plugins.
In advanced scenarios, this folder can be used to hold classes that overwrite
classes from `portal-kernel.jar`.

- `ext-web/docroot`: Contains the web application's configuration files, including
`WEB-INF/struts-config-ext.xml`, which lets you customize Liferay's core
struts classes. Note that for @product-ver@, there are very few entities left to
override in the `struts-config.xml` file. Any JSPs that you're customizing also
belong here. 

- `ext-util-bridges`, `ext-util-java` and `ext-util-taglib`: These folders are
needed only in scenarios where you must customize these Liferay
libraries: `util-bridges.jar`, `util-java.jar` and `util-taglib.jar`,
respectively. If you're not customizing any of these libraries, you can ignore
these folders. 

By default, several files are also added to the plugin. Here are the most 
significant files: 

- `build.xml`: The Ant build file for the Ext plugin project. 

- [`docroot/WEB-INF/liferay-plugin-package.properties`](https://docs.liferay.com/portal/7.0/propertiesdoc/liferay-plugin-package_7_0_0.properties.html):
Contains plugin-specific properties, including the plugin's display name,
version, author, and license type. 

- `docroot/WEB-INF/ext-web/docroot/WEB-INF` files: 

   - `portlet-ext.xml`: Used to overwrite the definition of a built-in portlet.
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
Ext plugin development process is different from other plugin types. It's
important to remember that once an Ext plugin is deployed, some of its files are
copied *inside* the Liferay installation; the only way to remove the changes is
by *redeploying* an unmodified Liferay application. You're also responsible for
checking that patches and fix packs do not conflict with your Ext plugin.

The @product-ver@ compatible Plugins SDK is designed to only develop/deploy one
Ext plugin. This means that all your customizations should live inside one Ext
plugin. The Plugins SDK does not check for conflicts among multiple Ext plugins
stored in the `/ext` folder, so do **not** develop/deploy multiple Ext plugins
at once.

The Plugins SDK lets you deploy and redeploy your Ext plugin during your
development phase. Redeployment involves *cleaning* (i.e., removing) your
application server and unzipping your specified Liferay bundle to start from
scratch. That way, any changes made to the Ext plugin during development are
properly applied, and files removed from your plugin by previous changes aren't
left behind in the @product@ application. Because of this added complexity, you
should use another plugin type to accomplish your goals whenever possible. 

Before digging in to the details, here's an overview of the Ext plugin
development processes described below: 

- *Configure* your Plugins SDK environment to develop Ext plugins for
  @product@ on your application server. 
- *Deploy* and *publish* your Ext plugins for the first time. 
- *Redeploy* normally or use a *clean redeployment* process after making
  changes to your Ext plugin.
- *Package* your Ext plugin for distribution. 
- A list of advanced customization techniques. 

It's time to learn each step of the development process. 

### Set Up the Build Environment [](id=set-up-the-build-environment)

Before deploying an Ext plugin, you must edit the `build.[username].properties`
file in the root folder of your Plugins SDK. If the file doesn't yet exist,
create it now. Substitute `[username]` with your user ID on your computer.
Once you've opened your build properties file, add the following
properties--make sure the individual paths reflect the right locations on your
system: 

    ext.work.dir=[work]

    app.server.dir=[work]/liferay-dxp-digital-enterprise-[version]/[app server]

    app.server.zip.name=[...]/liferay-dxp-digital-enterprise-[app server].zip

Your `app.server.zip.name` property must specify the path to your @product@
`.zip` file. The `work` folder you specify for the `ext.work.dir` property is
where you've unzipped your @product@ runtime. The `app.server.dir` property must
point to your application server's directory in your work folder. Look in your
@product@ bundle at the path to the application server to determine the value to
use for your `app.server.dir` property. 

For example, if your work folder is `C:/work`, specify it as your
`ext.work.dir` property's value. If your @product@ bundle `.zip` file is
`C:/downloads/liferay-dxp-digital-enterprise-tomcat-7.0-sp2-[timestamp].zip`,
set the `app.server.zip.name` property to that file's path. If the *relative
path* to the application server *within* the @product@ bundle `.zip` file is
`liferay-dxp-digital-enterprise-7.0-sp2\tomcat-8.0.32`, specify an
`app.server.dir` property value
`C:/work/liferay-dxp-digital-enterprise-7.0-sp2/tomcat-8.0.32`.

Next you'll modify the Ext plugin you created and deploy it. 

### Initial Deployment [](id=initial-deployment)

Your environment is set up and you're ready to start customizing. Once you're
finished, you can deploy the plugin. 

#### Deploy the Plugin [](id=deploy-the-plugin)

Now you'll learn how to deploy your plugin using Liferay @ide@ or the command
line. 

##### Deploying in Liferay IDE [](id=deploying-in-liferay-ide)

Drag your Ext plugin project from your Package Explorer onto your server to
deploy your plugin. Liferay @ide@ automatically restarts the server for the
server to detect and publish your Ext plugin. 

After deploying, if you don't see your customizations in the portal, your Ext
plugin may not have successfully deployed. To confirm whether the Ext plugin
deploys successfully, try deploying from the command line; the Ant deployment
targets report success or failure. 

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
types. You’ll learn more on redeploying your Ext plugin next.

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

##### Using Liferay IDE [](id=using-liferay-ide)

1.  Remove the plugin from the server. While selecting the Ext plugin in the
    *Servers* view, select the plugin's *Remove* option. 

2.  Clean the application server. Remember to shut down the application server
    before cleaning it. This is required on Windows because the server process
    locks the installed Liferay bundle's files. 

    While selecting the Ext plugin project in the *Package Explorer* view,
    select the plugin's *Liferay* &rarr; *Clean App Server...* option. 

    ![Figure 2: How to clean your app server](../../../images-dxp/ext-plugins-clean-app-server.png)

3.  Start the Liferay server. 

4.  Drag the Ext plugin and drop it onto the Liferay server. 

5.  While selecting the Liferay server in the *Servers* view, click the
    *Publish* option. 

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

##### Using Liferay @ide@ [](id=using-liferay-ide-0)

Right-click your plugin located underneath your server and select *Redeploy*. 

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

#### Using Liferay @ide@ [](id=using-liferay-ide-1)

With your Ext plugin project selected in the *Package Explorer* view, select the
project's *Liferay* &rarr; *SDK* &rarr; *war* option. 

#### Using the Command Line [](id=using-the-command-line-1)

From your Ext plugin's directory execute

    ant war

The `.war` file is written to your `[liferay-plugins]/dist` directory.

Now that you've learned the basics of Ext plugin development, you'll look at
some advanced customizations. 

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
    - **Description:** Overrides the default field properties of
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
- `ext-web/docroot/WEB-INF/portlet-ext.xml`
    - **Description:** Overrides the core portlets' declarations. It's most commonly
      used to change the init parameters or the roles specified. 
    - **Original file in @product@:** `portal-web/docroot/WEB-INF/portlet-custom.xml`
- `ext-web/docroot/WEB-INF/liferay-portlet-ext.xml`
    - **Description:** Overrides the Liferay-specific core portlets'
      declarations. It core portlets included in @product@. Refer to the
      [liferay-portlet-app_7_0_0.dtd](@platform-ref@/7.0/definitions/liferay-portlet-app_7_0_0.dtd.html)
      file for details on all the available options. Use this file with care;
      the code of the portlets may be assuming some of these options to be set
      to certain values. 
    - **Original file in @product@:** `portal-web/docroot/WEB-INF/liferay-portlet.xml`
- `ext-web/docroot/WEB-INF/liferay-display.xml`
    - **Description:** Overrides the portlets that are shown in the interface
      for adding applications and the categories in which they're organized.
    - **Original file in @product@:** `portal-web/docroot/WEB-INF/liferay-display.xml`

You’ll learn how to deploy your Ext plugin in production next.

## Deploying in Production [](id=deploying-in-production)

Often, you can't use Ant to deploy web applications in production or
pre-production environments. Additionally, some application servers such as
WebSphere or WebLogic have their own deployment tools and don't work with
@product@'s auto-deployment process. Here are two methods to consider for
deploying and redeploying an Ext plugin in production. 

### Method 1: Redeploying Liferay's Web Application [](id=method-1-redeploying-liferays-web-application)

You can use this method in any application server that supports auto-deployment;
What's the benefit? The only artifact that needs to be transferred to the
production system is your Ext plugin's `.war` file, produced using the `ant war`
target. This `.war` file is usually small and easy to transport. Execute these
steps on the server: 

1.  Redeploy @product@:

    If this is your first time deploying your Ext plugin to this server, skip
    this step. Otherwise, start by executing the same steps you first used to
    deploy @product@ on your app server. If you're using a bundle, unzip it
    again. If you installed @product@ manually on an existing application
    server, you must redeploy the @product@ `.war` file and copy both the
    libraries required globally by @product@ and your Ext plugin to the
    appropriate folder within the application server. 

2.  Copy the Ext plugin `.war` into the auto-deploy folder. For a bundled
    @product@ distribution, the `deploy` folder is in Liferay's *root* folder of
    your bundle (e.g., `liferay-dxp-digital-enterprise-[version]/`).

3.  Once the Ext plugin is detected and deployed by @product@, restart your
    @product@ server. 

### Method 2: Generate an Aggregated WAR File [](id=method-2-generate-an-aggregated-war-file)

Some application servers don't support auto-deploy; WebSphere and WebLogic are
two examples. With an aggregated `.war` file, the Ext plugin is merged before
deployment to production. A single `.war` file then contains @product@ plus the
changes from your Ext plugin. Before you deploy the aggregated @product@
`.war` file, copy the dependency `.jar` files for @product@ and your Ext plugin
to the global application server class loader in the production server. The
precise location varies from server to server; refer to
[Deployment](/discover/deployment) to get the details for your application
server. 

To create the aggregated `.war` file, deploy the Ext plugin first to the
@product@ bundle you are using in your development environment. Once it's
deployed, restart the server so that the plugin is fully deployed and shut it
down again. Now the aggregated file is ready. 

Create a `.war` file by zipping the Liferay web application folder from within
the app server. Copy into your application server's global classpath all of the
libraries on which your Ext plugin depends.

Now, perform these actions on your server: 

1.  Redeploy @product@ using the aggregated `.war` file. 

2.  Stop the server and copy the new version of the global libraries to the
    appropriate directory in the application server. 

Next, you'll learn about Liferay's licensing and contributing standards.

## App Server Configuration [](id=app-server-configuration)

If you're using the Tomcat app server and have not upgraded to Liferay DE SP4+,
you must modify your app server's `conf/Catalina/localhost/ROOT.xml` file. Add
the following code to that file:

    <Resources>
        <PreResources
            className="com.liferay.support.tomcat.webresources.ExtResourceSet"
            base="${catalina.base}/lib/ext/portal"
            webAppMount="/WEB-INF/lib"
        />
    </Resources>

Be sure to place this code within the existing `<Context>` tags.

## Licensing and Contributing [](id=licensing-and-contributing)

@product@ is Open Source software licensed under the 
[LGPL 2.1 license](http://www.gnu.org/licenses/lgpl-2.1.html).
If you reuse any code snippet and redistribute it, whether publicly or to a
specific customer, make sure your modifications are compliant with the license.
One common way is to make the source code of your modifications are available to
the community under the same license. Make sure you read the license text
yourself to find the option that best fits your needs. 

If your goal in making changes is fixing a bug or improving @product@, it could
be of interest to a broader audience. Consider contributing it back to the
project. That benefits all users of the product including you, since you won't
have to maintain the changes with each newly released version of @product@. You
can notify Liferay of bugs or improvements at
[issues.liferay.com](http://issues.liferay.com). Check out the
[Participate](/participate/contribute-a-feature) section of
[dev.liferay.com](https://dev.liferay.com), to learn all the ways that you can
contribute to Liferay projects. 

In summary, an Ext plugin is a powerful way to extend @product@. There are no
limits to what you can customize, so use it carefully. Before using
an Ext plugin, see if you can implement all or part of the desired functionality
through
[Application Display Templates](/develop/tutorials/-/knowledge_base/7-0/application-display-templates)
or a different plugin type. OSGi modules offer you a lot of extension
capabilities themselves, without introducing the complexity that's inherent with
Ext plugins. If you need to use an Ext plugin, make your customization as small
as possible and follow the instructions in this tutorial carefully to avoid
issues.
