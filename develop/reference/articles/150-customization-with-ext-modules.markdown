# Customization with Ext Modules

+$$$

**Ext modules are deprecated for @product-ver@ and should only be used if
absolutely necessary.**

The following app servers should be used for Ext module development in
@product@:

- Tomcat 8.0

<!--
- Wildfly 10.0
-->

In most cases, Ext modules are not necessary. There are, however, certain cases
that require the use of an Ext module. Liferay only supports the following Ext
module use cases:

- Providing custom implementations for any beans declared in @product@'s
  Spring files (when possible, use
  [service wrappers](/develop/tutorials/-/knowledge_base/7-1/customizing-liferay-services-service-wrappers)
  instead of an Ext module). @product-ver@ removed many beans, so make sure your
  overridden beans are still relevant if converting your legacy Ext module.
- Overwriting a class in a @product-ver@ core JAR. For a list of core JARs, see
  the [Finding Core @product@ Artifacts](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies#finding-core-liferay-portal-artifacts)
  section.
- Modifying @product@'s `web.xml` file.
- Adding to @product@'s `web.xml` file.

$$$

Ext modules are powerful tools used to extend @product@. They, however, increase
the complexity of your @product@ instance and are not recommended unless there
is absolutely no other way to accomplish your task. @product-ver@ provides many
extension points that let you customize almost every detail of @product@. If
there's a way to customize what you want with an extension point, do it that way
instead. See the
[More Extensible, Easier to Maintain](/develop/tutorials/-/knowledge_base/7-1/benefits-of-liferay-7-for-liferay-6-developers#more-extensible-easier-to-maintain)
section for more details on the advantages of using @product@'s extension
points.

Before deciding to use an Ext module, weigh the cost. Ext modules let you use
internal APIs and even let you overwrite @product@ core files. This puts
your deployment at risk of being incompatible with security, performance, or
feature updates released by Liferay. When upgrading to a new version of
@product@ (even if it's a maintenance version or a service pack), you have to
review all changes and manually modify your Ext module to merge your changes
with @product@'s. Additionally, Ext modules aren't hot deployable. To deploy an
Ext module, you must restart your server. Additional steps are also required to
deploy or redeploy to production systems.

<!-- Are Ext modules really not hot-deployable? Or was that just for Ext
modules? -Cody -->

In this tutorial, you'll learn how to

- [Create an Ext module](#creating-an-ext-module)
- [Develop an Ext module](#developing-an-ext-module)
- [Deploy an Ext module in Production](#deploying-in-production)

Before diving into creating an Ext module, however, first consider if an Ext
module is even necessary at all.

## Making the Decision to Use Ext Modules [](id=making-the-decision-to-use-ext-modules)

There are many parts of @product@ that now provide an extension point via OSGi
bundle. You should follow this three step process to decide whether an Ext
module is necessary:

1.  Find the OSGi extension point that you need. You can follow the
    [Finding Extension Points](/develop/tutorials/-/knowledge_base/7-1/finding-extension-points)
    tutorial as a guide.

2.  If an OSGi extension point does not exist, use an Ext module.

3.  Research new extension points after every release of @product@. When a new
    version of @product@ provides the extension point you need, always use the
    extension point to replace the existing Ext module.

So how do you find an OSGi extension point?

Your first step is to examine the custom module projects that extend popular
@product@ extension points stored in the
[Liferay Blade Samples](https://github.com/liferay/liferay-blade-samples)
repository. For
more information on these sample projects, see the
[Liferay Sample Projects](/develop/tutorials/-/knowledge_base/7-1/liferay-sample-projects)
tutorial. Usable extension points are also documented throughout Liferay's
Developer Network categorized by the @product@ section involved. For example,
[Overriding MVC Commands](/develop/tutorials/-/knowledge_base/7-1/overriding-mvc-commands)
and
[Customizing the Product Menu](/develop/tutorials/-/knowledge_base/7-1/customizing-the-product-menu)
are articles describing how to extend a @product@ extension point. Want to
learn how to
[customize JSPs](/develop/tutorials/-/knowledge_base/7-1/customizing-jsps)?
Those processes are documented too!

There are a few corner cases where you may need an Ext module to customize a
part of @product@ that does not provide an extension point. Refer to the top of
this tutorial for Ext module use cases supported by Liferay.

+$$$

**Note:** In previous versions of Liferay Portal, you needed an Ext plugin to
specify classes as portal property values (e.g.,
`global.starup.events.my.custom.MyStartupAction`), since the custom class had to
be added to the portal class loader. This is no longer the case in @product-ver@
since all lifecycle events can use OSGi services with no need to edit these
legacy properties.

$$$

Now that you know how to make an informed decision on using Ext modules, you'll
learn how to create one next.

## Creating an Ext Module [](id=creating-an-ext-module)

An Ext module is a powerful tool for extending @product@. Because it increases
the complexity of your @product@ installation, you should only use an Ext module
if you're sure you can't accomplish your goal in a different way.

You can create an Ext module based off the pre-configured
[modules-ext](/develop/reference/-/knowledge_base/7-1/modules-ext-template)
project template/archetype using
[Dev Studio](/develop/tutorials/-/knowledge_base/7-1/creating-modules-with-liferay-ide),
[Blade CLI (Liferay Workspace)](/develop/tutorials/-/knowledge_base/7-1/creating-projects-with-blade-cli),
or
[Maven](/develop/tutorials/-/knowledge_base/7-1/generating-new-projects-using-archetypes).

<!--
### Anatomy of an Ext Module [](id=anatomy-of-an-ext-module)

There are a few things to note about an Ext module's folder structure. Below
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
global classloader upon deployment of the Ext module. 

- `ext-lib/portal`: Contains libraries to be copied inside Liferay's main 
application. These libraries are usually necessary because they're invoked from 
classes you add in `ext-impl/src`. 

- `ext-kernel/src`: Contains classes that should be available to other modules.
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

By default, several files are also added to the module. Here are the most 
significant files: 

- `build.xml`: The Ant build file for the Ext module project. 

- [`docroot/WEB-INF/liferay-module-package.properties`](@platform-ref@/7.0-latest/propertiesdoc/liferay-module-package_7_0_0.properties.html):
Contains module-specific properties, including the module's display name,
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

**Note:** After creating an Ext module, remove the files from
`docroot/WEB-INF/ext-web/docroot/WEB-INF` that you don't need to customize.
Removing files you're not customizing prevents incompatibilities that could
manifest from @product@ updates. 

$$$

Great! You've now created an Ext module and are familiar with its folder
structure and its most significant files. Next, you'll use your Ext module to
customize @product@. 

-->

## Developing an Ext Module [](id=developing-an-ext-module)

An Ext module changes @product@ itself when the module is deployed; it's not a
separate component that you can easily remove at any time. For this reason, the
Ext module development process is different from other module types. It's
important to remember that once an Ext module is deployed, some of its files are
copied *inside* the Liferay installation; the only way to remove the changes is
by *redeploying* an unmodified Liferay application. You're also responsible for
checking that patches and fix packs do not conflict with your Ext module.

<!--

The @product-ver@ compatible Plugins SDK is designed to only develop/deploy one
Ext module. This means that all your customizations should live inside one Ext
module. The Plugins SDK does not check for conflicts among multiple Ext modules
stored in the `/ext` folder, so do **not** develop/deploy multiple Ext modules
at once.

The Plugins SDK lets you deploy and redeploy your Ext module during your
development phase. Redeployment involves *cleaning* (i.e., removing) your
application server and unzipping your specified Liferay bundle to start from
scratch. That way, any changes made to the Ext module during development are
properly applied, and files removed from your module by previous changes aren't
left behind in the @product@ application. Because of this added complexity, you
should use another module type to accomplish your goals whenever possible. 

Before digging in to the details, here's an overview of the Ext module
development processes described below: 

- *Configure* your Plugins SDK environment to develop Ext modules for
  @product@ on your application server. 
- *Deploy* and *publish* your Ext modules for the first time. 
- *Redeploy* normally or use a *clean redeployment* process after making
  changes to your Ext modules.
- *Package* your Ext modules for distribution. 
- A list of advanced customization techniques. 

It's time to learn each step of the development process. 

### Set Up the Build Environment [](id=set-up-the-build-environment)

Before deploying an Ext module, you must edit the `build.[username].properties`
file in the root folder of your Plugins SDK. If the file doesn't yet exist,
create it now. Substitute `[username]` with your user ID on your computer.
Once you've opened your build properties file, add the following
properties--make sure the individual paths reflect the right locations on your
system: 

    ext.work.dir=[work]

    app.server.dir=[work]/liferay-ce-portal-[version]/[app server]

    app.server.zip.name=[...]/liferay-ce-portal-[app server].zip

Your `app.server.zip.name` property must specify the path to your @product@
`.zip` file. The `work` folder you specify for the `ext.work.dir` property is
where you've unzipped your @product@ runtime. The `app.server.dir` property must
point to your application server's directory in your work folder. Look in your
@product@ bundle at the path to the application server to determine the value to
use for your `app.server.dir` property. 

For example, if your work folder is `C:/work`, specify it as your
`ext.work.dir` property's value. If your @product@ bundle `.zip` file is
`C:/downloads/liferay-ce-portal-tomcat-7.0-ga4-[timestamp].zip`, set the
`app.server.zip.name` property to that file's path. If the *relative path* to
the application server *within* the @product@ bundle `.zip` file is
`liferay-ce-portal-7.0-ga4\tomcat-8.0.32`, specify an `app.server.dir` property
value `C:/work/liferay-ce-portal-7.0-ga4/tomcat-8.0.32`. 

Next you'll modify the Ext module you created and deploy it. 

### Initial Deployment [](id=initial-deployment)

Your environment is set up and you're ready to start customizing. Once you're
finished, you can deploy the module. 

#### Deploy the Module [](id=deploy-the-module)

Now you'll learn how to deploy your module using Liferay @ide@ or the command
line. 

##### Deploying in Liferay @ide@ [](id=deploying-in-liferay-ide)

Drag your Ext module project from your Package Explorer onto your server to
deploy your module. Liferay @ide@ automatically restarts the server for the
server to detect and publish your Ext module. 

After deploying, if you don't see your customizations in the portal, your Ext
module may not have successfully deployed. To confirm whether the Ext module
deploys successfully, try deploying from the command line; the Ant deployment
targets report success or failure. 

##### Deploying via the Command Line [](id=deploying-via-the-command-line)

1.  Open a command line window in your Ext module project folder and enter one
    of these commands: 

        ant deploy

        ant direct-deploy

    The `direct-deploy` target deploys all changes directly to the appropriate
    folders in the Liferay application. The `deploy` target creates a `.war`
    file with your changes and then deploys it to your server.  Either way, your
    server must be restarted after the deployment. Using `direct-deploy` is
    preferred for deploying an Ext module during development if your application
    server supports it. Direct deploy doesn't work in WebLogic or WebSphere
    application server environments.

    A `BUILD SUCCESSFUL` message indicates your module is now being deployed.
    Your console window running @product@ shows a message like this:

		    Extension environment for [your project]-ext has been applied. You must
		    reboot the server and redeploy all other modules

    If any changes applied through the Ext module affect the deployment process
    itself, you must redeploy all other modules. Even if the Ext module doesn't
    affect the deployment process, it's a best practice to redeploy all your
    other modules following initial deployment of the Ext module. 

    The `ant deploy` target builds a `.war` file with your changes and copies
    them to the auto-deploy folder inside the @product@ installation. When the
    server starts, it detects the `.war` file, inspects it, and copies its
    contents to the appropriate destinations inside the deployed and running
    Liferay application.

2.  Restart the server so that it detects and publishes your Ext module. 

    Once your server restarts, open @product@ to see the customizations you
    made with your Ext module.
    
Frequently, you'll want to add further customizations to your original Ext
module. You can make unlimited customizations to an Ext module that has already
been deployed, but the redeployment process is different from other project
types. You'll learn more on redeploying your Ext module next.

### Redeployment [](id=redeployment)

So far, Ext module development has been similar to the development of other
module types. You've now reached the point of divergence. When the module is
first deployed, some of its files are copied into the @product@ installation.
After changing an Ext module, you'll either *clean redeploy* or *redeploy*,
depending on the modifications you made to your module following the initial
deployment. You'll learn about each redeployment method and when to use each
one.

#### Clean Redeployment [](id=clean-redeployment)

If you removed part(s) of your module, if there are changes to your module that
can affect module deployment, or if you want to start with a clean @product@
environment, *undeploy* your module and *clean* your application server before
redeploying your Ext module. By cleaning the application server, the existing
@product@ installation is removed and the bundle specified in your Plugins SDK
environment is unzipped in its place. See the instructions below to learn more
about this process.

##### Using Liferay @ide@ [](id=using-liferay-ide)

1.  Remove the module from the server. While selecting the Ext module in the
    *Servers* view, select the module's *Remove* option. 

2.  Clean the application server. Remember to shut down the application server
    before cleaning it. This is required on Windows because the server process
    locks the installed Liferay bundle's files. 

    While selecting the Ext module project in the *Package Explorer* view,
    select the module's *Liferay* &rarr; *Clean App Server...* option. 

    ![Figure 2: How to clean your app server](../../../images/ext-modules-clean-app-server.png)

3.  Start the Liferay server. 

4.  Drag the Ext module and drop it onto the Liferay server. 

5.  While selecting the Liferay server in the *Servers* view, click the
    *Publish* option. 

##### Using the Command Line [](id=using-the-command-line)

1.  Stop the @product@ server. 

2.  To deploy your Ext module, enter the following commands into your console: 

        cd [your-module-ext]
        ant clean-app-server
        ant direct-deploy

3.  Start the @product@ server. 

#### Redeployment [](id=redeployment-0)

If you only added to your module or made modifications that don't affect the
module deployment process, you can redeploy the Ext module. Follow the steps
based on the tool you're using.

##### Using Liferay @ide@ [](id=using-liferay-ide-0)

Right-click your module located underneath your server and select *Redeploy*. 

##### Using the Command Line [](id=using-the-command-line-0)

Using the same procedure as for initial deployment. Open a command line window
in your Ext module project's directory and execute either `ant deploy` or `ant
direct-deploy`.

After your module is published to @product@, verify that your customizations are
available.

Next you'll learn how to package an Ext module for distribution and production.

### Distribution [](id=distribution)

Once you're finished developing an Ext module, you can package it in a `.war`
file for distribution and production deployment. 

#### Using Liferay @ide@ [](id=using-liferay-ide-1)

With your Ext module project selected in the *Package Explorer* view, select the
project's *Liferay* &rarr; *SDK* &rarr; *war* option. 

#### Using the Command Line [](id=using-the-command-line-1)

From your Ext module's directory execute

    ant war

The `.war` file is written to your `[liferay-modules]/dist` directory.

Now that you've learned the basics of Ext module development, you'll look at
some advanced customizations.

### Using Advanced Configuration Files [](id=using-advanced-configuration-files)

@product@ uses several internal configuration files for its own architecture; in
addition, there are configuration files for the libraries and frameworks
@product@ depends on, like Struts and Spring. Configuration could be
accomplished using fewer files with more properties in each, but maintenance and
use is made easier by splitting up the configuration properties into several
files. For advanced customization needs, it may be useful to override the
configuration specified in multiple configuration files. @product@ provides a
clean way to do this from an Ext module without modifying the original files. 

All the configuration files in @product@ are listed below by their path in an
Ext module folder. Here are descriptions of what each file is for and the path
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
      is best done outside of an Ext module, in @product@'s' UI or a Log4j XML
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

You'll learn how to deploy your Ext module in production next.

## Deploying in Production [](id=deploying-in-production)

Often, you can't use Ant to deploy web applications in production or
pre-production environments. Additionally, some application servers such as
WebSphere or WebLogic have their own deployment tools and don't work with
@product@'s auto-deployment process. Here are two methods to consider for
deploying and redeploying an Ext module in production. 

### Method 1: Redeploying Liferay's Web Application [](id=method-1-redeploying-liferays-web-application)

You can use this method in any application server that supports auto-deployment;
What's the benefit? The only artifact that needs to be transferred to the
production system is your Ext module's `.war` file, produced using the `ant war`
target. This `.war` file is usually small and easy to transport. Execute these
steps on the server: 

1.  Redeploy @product@:

    If this is your first time deploying your Ext module to this server, skip
    this step. Otherwise, start by executing the same steps you first used to
    deploy @product@ on your app server. If you're using a bundle, unzip it
    again. If you installed @product@ manually on an existing application
    server, you must redeploy the @product@ `.war` file and copy both the
    libraries required globally by @product@ and your Ext module to the
    appropriate folder within the application server. 

2.  Copy the Ext module `.war` into the auto-deploy folder. For a bundled
    @product@ distribution, the `deploy` folder is in Liferay's *root* folder of
    your bundle (e.g., `liferay-ce-portal-[version]/`).

3.  Once the Ext module is detected and deployed by @product@, restart your
    @product@ server. 

### Method 2: Generate an Aggregated WAR File [](id=method-2-generate-an-aggregated-war-file)

Some application servers don't support auto-deploy; WebSphere and WebLogic are
two examples. With an aggregated `.war` file, the Ext module is merged before
deployment to production. A single `.war` file then contains @product@ plus the
changes from your Ext module. Before you deploy the aggregated @product@
`.war` file, copy the dependency `.jar` files for @product@ and your Ext module
to the global application server class loader in the production server. The
precise location varies from server to server; refer to
[Deployment](/discover/deployment) to get the details for your application
server. 

To create the aggregated `.war` file, deploy the Ext module first to the
@product@ bundle you are using in your development environment. Once it's
deployed, restart the server so that the module is fully deployed and shut it
down again. Now the aggregated file is ready. 

Create a `.war` file by zipping the Liferay web application folder from within
the app server. Copy into your application server's global classpath all of the
libraries on which your Ext module depends. 

Now, perform these actions on your server: 

1.  Redeploy @product@ using the aggregated `.war` file. 

2.  Stop the server and copy the new version of the global libraries to the
    appropriate directory in the application server. 

Next, you'll learn about Liferay's licensing and contributing standards.

-->

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

In summary, an Ext module is a powerful way to extend @product@. There are no
limits to what you can customize, so use it carefully. Before using
an Ext module, see if you can implement all or part of the desired functionality
through
[Application Display Templates](/discover/portal/-/knowledge_base/7-1/styling-apps-and-assets)
or a different module type. OSGi modules offer you a lot of extension
capabilities themselves, without introducing the complexity that's inherent with
Ext modules. If you need to use an Ext module, make your customization as small
as possible and follow the instructions in this tutorial carefully to avoid
issues.