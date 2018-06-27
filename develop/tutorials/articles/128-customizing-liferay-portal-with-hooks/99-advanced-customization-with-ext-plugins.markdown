# Advanced Customization with Ext Plugins [](id=advanced-customization-with-ext-plugins)

Ext plugins are powerful tools for extending Liferay. Because they increase the
complexity of your Liferay instance, you should only use an Ext plugin if you're
sure you can't accomplish your goal using a different method. Check out [Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)
for customizing display of apps that support them, or check out [Customizing Liferay Portal with Hooks](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)
for the available alternatives using hook plugins. If an ADT or hook won't
suffice, keep reading to discover the use cases for Ext plugins and how to set
one up. First it's important to consider why you should avoid Ext plugins when
possible. 

As someone once said, "With great power comes great responsibility" (okay, many
people have said that many times).  Before deciding to use an Ext plugin, weigh
the cost. Ext plugins let you use internal APIs and even let you overwrite
Liferay core files. When upgrading to a new version of Liferay (even if it's a
maintenance version or a service pack), you have to review all changes and
manually modify your Ext plugin to merge your changes with Liferay's.
Additionally, Ext plugins aren't hot deployable. To deploy an Ext plugin, you
must restart your server. Lastly, with Ext plugins, additional steps are
required to deploy or redeploy to production systems. 

Now that you know the limitations of Ext plugins, let's look at why you'd want
to use them: 

- To specify custom classes as portal property values. For example, to specify a
  property that needs a custom class (e.g.,
  `global.startup.events=my.custom.MyStartupAction`), you need an Ext plugin to
  add your custom class to the portal class loader. 
- To provide custom implementations for any Liferay beans declared in Liferay's
  Spring files (when possible, use [service wrappers](/develop/tutorials/-/knowledge_base/6-2/overriding-a-portal-service-using-a-hook)
  from a hook instead of an Ext plugin). 
- To add JSPs referenced from portal properties that can only be changed
  from an Ext plugin (check whether the property can be modified from a
  [hook plugin](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)
  first). 
- To Overwrite a class (not recommended unless you have no other choice). 

With these use cases in mind, you'll learn how to use Ext for these things: 

- Creating an Ext plugin 
- Developing an Ext plugin 
- Deploying in Production 
- Migrating Old Extension Environments 

It's time to create an Ext plugin. 

## Creating an Ext Plugin [](id=creating-an-ext-plugin)

Ext plugins are powerful tools for extending Liferay. Because they increase the 
complexity of your Liferay instance, you should only use an Ext plugin if you're 
sure you can't accomplish your goal in a different way. You can create Ext 
plugins in Liferay IDE or in your terminal environment. The Ext plugin is stored 
in the [Plugins SDK's](/develop/tutorials/-/knowledge_base/using-the-plugins-sdk) `ext` directory. 

You can also create an Ext plugin in a Liferay Maven project.

This tutorial covers how to create an Ext plugin in a Plugins SDK project using 
Ant. This is shown from both Liferay IDE and the terminal. To learn how to 
create Liferay Maven projects, see the tutorial 
[Creating Liferay Maven plugins from IDE](/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide) 
or the tutorial [Creating Liferay Maven Plugins from the Command Line](/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin). 

First, follow along with creating an Ext plugin using Liferay IDE!

### Using Liferay IDE to Create an Ext Plugin [](id=using-liferay-ide-to-create-an-ext-plugin)

Follow the steps below, replacing the project name with your own, and you'll 
have an Ext plugin in no time:

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  Enter your project's name and its display name. In the figure below,
    *example* is used for both. Note that the Display name field is automatically
    filled in with the capitalized version of the Project name. 

3.  Leave the *Use default location* checkbox checked. Since you're using Ant, 
    the default location is set to your Liferay Plugins SDK's location. If you'd
    like to change where your plugin project is saved, uncheck the box and
    specify an alternate location. 

4.  Select the *Ant (liferay-plugins-sdk)* option for your build type.

5.  Your configured SDK and Liferay runtime should already be selected. If you
    haven't yet pointed Liferay IDE to a Plugins SDK, click *Configure SDKs* to
    open the Installed Plugin SDKs management wizard. You can also access the
    New Server Runtime Environment wizard if you need to set up your runtime
    server; just click the *New Liferay Runtime* button next to the Liferay
    Portal Runtime selection box. 

6.  Select *Ext* for your Plugin type. 

7.  Click *Finish*. 

![Figure 1: You can create an Ext plugin project with Liferay IDE.](../../images/ext-create-an-ext-plugin-project.png)

The Plugins SDK automatically appends `-ext` to the plugin project name when it
creates your Ext plugin's project folder. In Liferay IDE, you can either create
a completely new plugin or add a new plugin to an existing plugin project. 

### Creating an Ext Plugin Using the Terminal [](id=creating-an-ext-plugin-using-the-terminal)

To create a new Ext plugin from the terminal, navigate to the *ext* directory in
your Liferay Plugins SDK and enter the command below that is appropriate for
your operating system. The two arguments after the `create` command are the
project name and display name, respectively. The examples below use the project
name *example* and the display name *Example*. Make sure to specify values you
want to use for your Ext plugin's project name and display name.

In Linux or Mac OS, enter:

    ./create.sh example "Example"

In Windows, enter:

    create.bat example "Example"

A `BUILD SUCCESSFUL` message from Ant tells you there's a new folder (e.g.,
folder `example-ext` for a project named *example*) inside your Plugins SDK's
`ext` folder. The Plugins SDK automatically appends `-ext` to the project name. 

### Anatomy of an Ext Plugin [](id=anatomy-of-an-ext-plugin)

There are a few things to note about an Ext plugin's directory structure. Below
is a listing of an Ext folder structure. The screenshot that follows this
listing shows the structure of the Ext plugin in Liferay IDE: 

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

Here are detailed explanations of the `/docroot/WEB-INF/` subdirectories: 

`ext-impl/src`: Contains the `portal-ext.properties` configuration file, custom
implementation classes, and classes that override core classes from
`portal-impl.jar`. 

`ext-lib/global`: Contains libraries to be copied to the application server's
global classloader upon deployment of the Ext plugin. 

`ext-lib/portal`: Contains libraries to be copied inside Liferay's main 
application. These libraries are usually necessary because they're invoked from 
classes you add in `ext-impl/src`. 

`ext-service/src`: Contains classes that should be available to other plugins.
In advanced scenarios, this folder can be used to hold classes that overwrite
classes from `portal-service.jar`. Service Builder puts services' interfaces
here. 

`ext-web/docroot`: Contains the web application's configuration files, including 
`WEB-INF/struts-config-ext.xml`, which allows you to customize Liferay's core 
struts classes. Note that [hooks](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)
are recommended for customizing a struts action. Any JSPs that you're
customizing also belong here. 

`ext-util-bridges`, `ext-util-java` and `ext-util-taglib`: These folders are
needed only in scenarios where you need to customize classes of these Liferay
libraries: `util-bridges.jar`, `util-java.jar` and `util-taglib.jar`,
respectively. If you're not customizing any of these libraries, you can ignore
these folders. 

By default, several files are also added to the plugin. Here are the most 
significant files: 

`build.xml`: The Ant build file for the Ext plugin project. 

[`docroot/WEB-INF/liferay-plugin-package.properties`](https://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html):
Contains plugin-specific properties, including the plugin's display name,
version, author, and license type. 
`docroot/WEB-INF/ext-impl/src/portal-ext.properties`: Overrides Liferay's
configuration properties. However, you should use a hook plugin to override
properties whenever it's possible. An example where an Ext plugin is necessary
to override a property is when specifying a custom class as a
[portal property](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html)
value. You can use a `portal-ext.properties` file with each of your Ext 
plugins, but don't override the same property from multiple 
`portal-ext.properties` files--the loading order isn't assured and you can 
cause unintended system behavior as a result. 

`docroot/WEB-INF/ext-web/docroot/WEB-INF` files: 

   - `portlet-ext.xml`: Used to overwrite the definition of a Liferay portlet.
     To do this, copy the complete definition of the desired portlet from
     `portlet-custom.xml` in Liferay's source code, then apply the necessary
     changes. 
   - `liferay-portlet-ext.xml`: This file is similar to `portlet-ext.xml`, but
     is for additional definition elements specific to Liferay. To override
     these definition elements, copy the complete definition of the desired
     portlet from `liferay-portlet.xml` within Liferay's source code, then
     apply the necessary changes. 
   - `struts-config-ext.xml` and `tiles-defs-ext.xml`: These files are used to
     customize the struts actions used by Liferay's core portlets. 

+$$$

**Tip:** After creating an Ext plugin, remove from
`docroot/WEB-INF/ext-web/docroot/WEB-INF` the files you don't need to customize.
Liferay keeps track of the files deployed by each Ext plugin and won't let you
deploy multiple Ext plugins that override the same file. If you remove files
you're not customizing, you'll avoid collisions with Ext plugins deployed
alongside yours. 

$$$

Great! You've now created an Ext plugin and are familiar with its directory
structure and its most significant files. Let's use your Ext plugin to customize
Liferay Portal. 

## Developing an Ext Plugin [](id=developing-an-ext-plugin)

An Ext plugin changes Liferay itself when the plugin is deployed; it's not a
separate component that can you can easily remove at any time. For this reason,
the Ext plugin development process is different from that of other plugin types.
It's important to remember that once an Ext plugin is deployed, some of its
files are copied *inside* the Liferay installation; the only way to remove the
changes is by *redeploying* an unmodified Liferay application. 

The Plugins SDK lets you deploy and redeploy Ext plugins during your
development phase. Redeployment involves *cleaning* (i.e., removing) your
application server and unzipping your specified Liferay bundle to start from
scratch. That way any changes made to the Ext plugin during development are
properly applied, and files removed from your plugin by previous changes aren't
left behind in the Liferay application. Because of this added complexity, you
should use another plugin type to accomplish your goals whenever
possible. 

Before digging in to the details, here's an overview of the Ext plugin
development processes described below: 

- How to *configure* your Plugins SDK environment to develop Ext plugins for
  Liferay Portal on your application server. 
- How to *deploy* and *publish* your Ext plugins for the first time. 
- How to *redeploy* normally or use a *clean redeployment*
  process after making changes to your Ext plugins.
- How to package your Ext plugins for distribution. 
- Examples of Liferay Portal customizations that require advanced customization
  techniques. 

It's time to learn each step of the development process. 

### Set Up the Build Environment [](id=set-up-the-build-environment)

Before deploying an Ext plugin, you must edit the `build.[username].properties`
file in the root folder of your Plugins SDK. If the file doesn't yet exist,
create it now. Substitute `[username]` with your user ID on your computer.
Once you've opened your build properties file, add the following
properties--make sure the individual paths reflect the right locations on your
system: 

    ext.work.dir=[work]

    app.server.dir=[work]/liferay-portal-[version]/[app server]

    app.server.zip.name=[...]/liferay-portal-[app server].zip

Your `app.server.zip.name` property must specify the path to your Liferay
bundle `.zip` file. The `work` directory that you specify for the `ext.work.dir`
property is where you've unzipped your Liferay bundle runtime. The
`app.server.dir` property must point to your application server's directory
in your work directory. Look in your Liferay bundle at the path to the
application server to determine the value to use for your `app.server.dir`
property. 

For example, if your work directory is `C:/work`, specify it as your
`ext.work.dir` property's value. If your Liferay bundle `.zip` file is
`C:/downloads/liferay-portal-tomcat-6.2.0-ce-ga1-[timestamp].zip`, set the
`app.server.zip.name` property to that file's path. If the *relative path* to
the application server *within* the Liferay bundle `.zip` file is
`liferay-portal-6.2-ce-ga3\tomcat-7.0.42`, specify an `app.server.dir`
property value `C:/work/liferay-portal-6.2-ce-ga3/tomcat-7.0.42`. 

+$$$

**Note:** Some Liferay bundles come installed with a sample website. It's useful
for showcasing certain features of Liferay, but if you removed it, you likely
don't want it reinstalled each time you unzip the bundle. To prevent
reinstalling the sample website, unzip the bundle, delete the
`[work]/liferay-portal-[version]/[app server]/webapps/welcome-theme` folder,
then re-zip the bundle.

$$$

Next you'll modify the Ext plugin you created and deploy it. 

### Initial Deployment [](id=initial-deployment)

Your environment is set up and you're ready to start customizing. For this
exercise, you'll customize the sections of a user profile. This example can be
done using a hook and the `users.form.add.main` property, but for demonstration
purposes, you'll learn how to customize it by overriding portal properties using
an Ext plugin. To customize the `users.form.add.main` portal property, open the
`docroot/WEB-INF/ext-impl/src/portal-ext.properties` file and enter a new
setting, like this one: 

    users.form.update.main=details,password,organizations,sites,roles

In the customization above, the `users.form.add.main` property no longer
specifies user profile page sections for user groups, personal sites, and
categorizations. Only the sections named in the value list for the customized
`users.form.add.main` portal property will be shown in the portal's user profile
pages. 

After you've made customizations in the Ext plugin, you can deploy the plugin. 

#### Deploy the Plugin [](id=deploy-the-plugin)

You can deploy your plugin from Liferay IDE or the terminal. 

**Deploying in Liferay IDE:** Drag your Ext plugin project from your Package
Explorer onto your server to deploy your plugin. Liferay IDE automatically
restarts the server, for the server to detect and publish your Ext plugin. 

**Deploying in the terminal:** 

1.  Open a terminal window in your Ext plugin project directory and enter one of
    these commands: 

        ant deploy

        ant direct-deploy

    The `direct-deploy` target deploys all plugin changes directly to the
    appropriate directories in the Liferay application. The `deploy` target
    creates a `.war` file with your changes and then deploys it to your server.
    Either way, your server must be restarted after the deployment. Using
    `direct-deploy` is preferred for deploying Ext plugins during
    development if your application server supports it. Direct deploy 
    doesn't work in WebLogic Server or WebSphere application server
    environments.

	A `BUILD SUCCESSFUL` message indicates your plugin is now being deployed.
	Your console window running Liferay shows a message like the following one:

		Extension environment for [your project]-ext has been applied. You must
		reboot the server and redeploy all other plugins

	If any changes applied through the Ext plugin affect the deployment process
	itself, you must redeploy all other plugins. Even if the Ext plugin doesn't
	affect the deployment process, it's a best practice to redeploy all your
	other plugins following initial deployment of the Ext plugin. 

	The `ant deploy` target builds a `.war` file with your changes and copies
    them to the auto-deploy directory inside the Liferay installation. When the
    server starts, it detects the `.war` file, inspects it, and copies its
    contents to the appropriate destinations inside the deployed and running
    Liferay application.

2.  Restart the server so that it detects and publishes your Ext plugin. 

Once your server restarts, try Liferay Portal to see the customizations you
introduced with your Ext plugin.

+$$$

**Tip:** If you deployed the Ext plugin using Liferay IDE, but you don't see
your customizations in the portal, your Ext plugin may not have successfully
deployed. To confirm whether the Ext plugin deploys successfully, try deploying
from the terminal; the Ant deployment targets report success or failure. 

$$$

If you customized the `users.form.update.main` property, for example, log in as
an administrator and go to *Control Panel* &rarr; *Users and Organizations*. Edit an
existing user and verify that the right navigation menu only shows the sections
that you specified for the `users.form.update.main` property. 

![Figure 2: If you customized the `users.form.update.main` property, Liferay Portal's User Information only shows the sections you specified.](../../images/ext-plugin-user-info-sections.png)

That was a simple application of an Ext plugin. You can proceed with a more
complex customization that illustrates the proper way to redeploy an Ext
plugin, which is different from initial deployment. 

Let's customize the Details section of the user profile. Rather than override
its JSP, you'll use a more powerful method for adding new sections or even
merging existing ones. You can refer to custom sections from the
`portal-ext.properties` and implement them just by creating a JSP. You'll modify
the property `users.form.update.main` again and the property
`users.form.add.main` to set the following properties:

    users.form.add.main=basic,organizations,personal-site
    users.form.update.main=basic,password,organizations,sites,roles

You removed the original Details section and added a custom one called *basic*.
When Liferay reads the property, it looks for the implementation of each section
based on the following conventions:

-  The section is implemented in a JSP in the following directory: 

        ext-web/docroot/html/portlet/users_admin/user/

-  The name of the JSP is the same as the name of the section with the `.jsp`
   extension.  If the section name has a dash, (`-`), replace it with an
   underscore (`_`).  For example, if the section is called *my-info*, the JSP
   should be named `my_info.jsp` to comply with JSP naming standards. 
-  The section name that's shown to the user comes from the language bundles.
   When using a key/value that is not included with Liferay, add it to both your
   Ext plugin's `Language-ext.properties` file and the language-specific
   properties file for each language variant you're providing a translation for.
   These files go in the `ext-impl/src` directory of your Ext plugin. 

For this example, you'll create a file in the Ext plugin with the following path: 

    ext-web/docroot/html/portlet/users_admin/user/basic.jsp

You can write the contents of the file from scratch or just copy the
`details.jsp` file from Liferay's [source code](https://github.com/liferay/liferay-portal)
and modify from there. You can remove some of the fields (e.g., birthday or
gender), leaving the screen name, email address, first name, and last name
fields to simplify user creation and user update. Make sure to completely remove
the tags associated with and surrounding the field. 

You don't need to add a new key to `Language-ext.properties`, because an entry
for the key named *basic* is already included in Liferay's language bundle. 

Let's redeploy the Ext plugin to review the changes. 

### Redeployment [](id=redeployment)

So far, Ext plugin development has been similar to the development of other
plugin types. You've now reached the point of divergence. When the plugin is
first deployed, some of its files are copied into the Liferay installation.
After changing an Ext plugin, you'll either *redeploy* or *clean redeploy*,
depending on the modifications you made to your plugin following the initial
deployment. Let's talk about each redeployment method and when to use each one.

**Clean Redeployment:** If you removed part(s) of your plugin, if there are
changes to your plugin that can affect plugin deployment, or if you want to
start with a clean Liferay environment, *undeploy* your plugin and *clean* your
application server before redeploying your Ext plugins. By cleaning the
application server, the existing Liferay installation is removed and the bundle
specified in your Plugins SDK environment (e.g., the value of
`app.server.zip.name` in `build.[username].properties`) is unzipped in its
place. The exact steps you take differ based on whether you're developing in
Liferay IDE or a terminal: 

**Using Liferay IDE:**

1.  Remove the plugin from the server. While selecting the Ext plugin in the
    *Servers* view, select the plugin's *Remove* option. 

2.  Clean the application server. Remember to shut down the application server
    before cleaning it. This is required on Windows because the server process
    locks the installed Liferay bundle's files. 

    While selecting the Ext plugin project in the *Package Explorer* view,
    select the plugin's *Liferay* &rarr; *Clean App Server...* option. 

    ![Figure 3: How to clean app server](../../images/ext-plugins-clean-app-server.png)

3.  Start the Liferay server. 

4.  Drag the Ext plugin and drop it onto the Liferay server. 

5.  While selecting the Liferay server in the *Servers* view, click the
    *Publish* option. 

**Using the terminal:** 
 
1.  Stop the Liferay server. 

2.  For each Ext plugin you're deploying, enter the following commands into your
    console: 

        cd [your-plugin-ext]
        ant clean-app-server
        ant direct-deploy

3.  Start the Liferay server. 

**Redeployment:** If you only added to your plugin or made modifications that
don't affect the plugin deployment process, you can redeploy using these steps: 

- **Using Liferay IDE:** Right-click your plugin located underneath your
  server and select *Redeploy*. 

- **Using the terminal:** Redeploy in the terminal using the same procedure as
  for initial deployment. Open a terminal window in your Ext plugin project's
  directory and execute either `ant deploy` or `ant direct-deploy`.

After your plugin is published to Liferay Portal, check for the customizations
it produces. If you implemented and deployed the example customizations for the
User Information page, you can see the customizations by viewing an existing
user's information in the Control Panel. 

Implementing and deploying this example customization demonstrates the Ext
plugin development process. Next you'll learn how to package an Ext plugin for
distribution and production. 

### Distribution [](id=distribution)

Once you're finished developing an Ext plugin, you can package it in a `.war`
file for distribution and production deployment. 

**Using Liferay IDE:** With your Ext plugin project selected in the
*Package Explorer* view, select the project's *Liferay* &rarr; *SDK* &rarr;
*war* option. 

**Using the terminal:** From your Ext plugin's directory execute

    ant war

The `.war` file is written to your `[liferay-plugins]/dist` directory.

You really have the hang of building and packaging your Ext plugins! Next,
you'll learn about JBoss 7 requirements for packaging up an Ext plugin that
contains a new tag library. If this doesn't apply to you, feel free to skip over
it. 

### Ext Plugin Packaging Requirements for JBoss 7 [](id=ext-plugin-packaging-requirements-for-jboss-7)

If you're deploying to JBoss 7 and you're developing an Ext plugin that defines
a new tag library, you need to take JBoss's classloading behavior into account.
Before packaging this kind of Ext plugin, create a
`jboss-deployment-structure.xml` file in the Ext plugin's `WEB-INF/` folder and
add the following contents to it:

    <jboss-deployment-structure xmlns="urn:jboss:deployment-structure:1.0">
            <deployment>
                    <dependencies>
                            <module name="deployment.util-taglib"></module>
                            <module name="com.liferay.portal"></module>
                    </dependencies>
            </deployment>
                    <module name="deployment.util-taglib">
                      <resources>
                                    <resource-root path="WEB-INF/ext-util-taglib/ext-util-taglib.jar" />
                                    <resource-root path="WEB-INF/lib/util-taglib.jar"></resource-root>
                                    <resource-root path="WEB-INF/lib/util-java.jar"></resource-root>
                            </resources>
                            <dependencies>
                                    <module name="javax.faces.api"></module>
                                    <module name="javax.servlet.api" />
                                    <module name="javax.servlet.jsp.api" />
                                    <module name="com.liferay.portal"></module>
                            </dependencies>
                    </module>
    </jboss-deployment-structure>

Also, in your Ext plugin's [`liferay-plugin-package.properties`](https://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html)
file, you must add `util-taglib.jar` to your plugin's list of portal dependency
`.jar` files. Here's what the property setting looks like with just
`util-taglib.jar` in its list of values: 

    portal-dependency-jars=util-taglib.jar

Once you've made these updates, you can package your plugin and deploy it using
the process described previously in this tutorial. 

Now that you've learned the basics of Ext plugin development and covered this
requirement for JBoss customizations, you should look at some advanced
customizations that you can do.

### Advanced Customization Techniques [](id=advanced-customization-techniques)

With Ext plugins, you can change anything in Liferay. It's time to look at some
additional customization techniques made possible by Ext plugins. As always, be
careful when using Ext plugins. 

With each new version of Liferay, there can be changes to the implementation
classes. If you change Liferay's source code directly, you'll have to merge your
changes into the newer Liferay version. To minimize such conflicts, the best
approach is not to change anything. Rather, you can extend the class you want to
change and override the required methods. Then use the requisite Liferay
configuration files to reference your subclass as a replacement for the original
class. 

Below, you'll learn about these topics:

- Using advanced configuration files
- Changing the API of a core service
- Replacing core classes in `portal-impl`

First, you'll learn to use advanced configuration files. 

#### Using Advanced Configuration Files [](id=using-advanced-configuration-files)

Liferay uses several internal configuration files for its own architecture; in
addition, there are configuration files for the libraries and frameworks Liferay
depends on, like Struts and Spring. Configuration could be accomplished using
fewer files with more properties in each, but maintenance and use is made easier
by splitting up the configuration properties into several files. For advanced
customization needs, it may be useful to override the configuration specified in
multiple configuration files. Liferay provides a clean way to do this from an
Ext plugin without modifying the original files. 

All the configuration files in Liferay are listed below by their path in an Ext
plugin folder. Here are descriptions of what each file is for and the path to
the original file in Liferay Portal: 

- `ext-impl/src/META-INF/ext-model-hints.xml`
    - **Description:** Allows overriding the default properties of the fields of
      the data models used by Liferay's core portlets. These properties
      determine how the form fields for each model are rendered. 
    - **Original file in Liferay:**
      `portal-impl/src/META-INF/portal-model-hints.xml` 
- `ext-impl/src/META-INF/ext-spring.xml`
    - **Description:** Allows overriding the Spring configuration used by Liferay
      and any of its core portlets. It's most commonly used to configure
      specific data sources or swap the implementation of a default service with
      a custom one.
    - **Original file in Liferay:** `portal-impl/src/META-INF/*-spring.xml`
- `ext-impl/src/content/Language-ext_*.properties`
    - **Description:** Allow overriding the value of any key used by Liferay's UI
      to support internationalization. 
    - **Original file in Liferay:** `portal-impl/src/content/Language-*.properties`
- `ext-impl/src/META-INF/portal-log4j-ext.xml`
    - **Description:** Allows overriding the Log4j configuration. It's most
      commonly used to increase or decrease the log level of a given package or
      class, to obtain more information, or to hide unneeded information from
      the logs. 
    - **Original file in Liferay:** `portal-impl/src/META-INF/portal-log4j.xml`
- `ext-impl/src/com/liferay/portal/jcr/jackrabbit/dependencies/repository-ext.xml`
    - **Description:** Allows overriding the configuration of the Jackrabbit
      repository. Refer to the Jackrabbit configuration documentation for
      details 
      ([http://jackrabbit.apache.org/jackrabbit-](http://jackrabbit.apache.org/jackrabbit-configuration.html)[configuration.html](http://jackrabbit.apache.org/jackrabbit-configuration.html)) 
    - **Original file in Liferay:**
      `portal-impl/src/com/liferay/portal/jcr/jackrabbit/dependencies/repository.xml`
- `ext-web/docroot/WEB-INF/portlet-ext.xml`
    - **Description:** Allows overriding the declaration of the core portlets
      included in Liferay. It's most commonly used to change the init parameters
      or the roles specified. 
    - **Original file in Liferay:** `portal-web/docroot/WEB-INF/portlet-custom.xml`
- `ext-web/docroot/WEB-INF/liferay-portlet-ext.xml`
    - **Description:** Allows overriding the Liferay-specific declaration of the
      core portlets included in Liferay. Refer to the
      [`liferay-portlet-app_6_2_0.dtd`](https://docs.liferay.com/portal/6.2/definitions/liferay-portlet-app_6_2_0.dtd.html)
      file for details on all the available options. Use this file with care;
      the code of the portlets may be assuming some of these options to be set
      to certain values. 
    - **Original file in Liferay:** `portal-web/docroot/WEB-INF/liferay-portlet.xml`
- `ext-web/docroot/WEB-INF/liferay-display.xml`
    - **Description:** Allows overriding the portlets that are shown in the
      interface for adding applications and the categories in which they're
      organized. It's most commonly used to change the categorization, hide
      certain portlets, or make specific Control Panel portlets available to be
      added to a page. 
    - **Original file in Liferay:** `portal-web/docroot/WEB-INF/liferay-display.xml`
- `ext-web/docroot/WEB-INF/liferay-layout-templates-ext.xml`
    - **Description:** Allows specifying custom template files for each of Liferay's
      standard layout templates. This is rarely necessary. 
    - **Original file in Liferay:**
      `portal-web/docroot/WEB-INF/liferay-layout-templates.xml`
- `ext-web/docroot/WEB-INF/liferay-look-and-feel-ext.xml`
    - **Description:** Allows changing the properties of Liferay's default themes.
      This is rarely used. 
    - **Original file in Liferay:**
      `portal-web/docroot/WEB-INF/liferay-look-and-feel.xml`

Next you can learn how to configure a Lucene Analyzer. 

#### Configuring Lucene Analyzers [](id=configuring-lucene-analyzers)

Liferay uses Lucene to facilitate search and indexing within the portal. In old
versions of Liferay, you could configure Lucene analyzers from a
`portal-ext.properties` file. While convenient, it was problematic to have only a
single analyzer for all portal-indexed fields. For example, it was difficult to
provide correct behaviors for handling both keyword and text fields. 

Since Liferay 6.1, you no longer configure Lucene from a `portal-ext.properties`
file. Instead, Lucene analyzers are defined in Spring configuration files. The
default analyzer configuration is defined in
`/portal-impl/src/META-INF/search-spring.xml`. Liferay 6.1 introduced per-field
analyzers, allowing Lucene's query parser to identify the correct analyzer to
handle any field. 

There are two common scenarios where it's useful to configure Lucene analyzers:
when creating custom language analyzers to override a Liferay language analyzer,
and when creating a custom indexer to index new fields. Liferay provides a large
number of language analyzers in `search-spring.xml` out of the box and uses
regular expression matching to map localized fields to specific analyzers. If
your language is not included among the defaults, or you're not satisfied with
one of the default language analyzers, you can override it with a custom
analyzer. If you've created a custom indexer to index new fields, you can use
the default analyzer for your new fields, select a specific one (such as one of
the `KeywordAnalyzers`), or define a custom analyzer. 

To customize the Lucene analyzer configuration, you must create an Ext plugin.
The Analyzer classes reference the Lucene APIs directly, so it's not possible
to configure Lucene analyzers from a hook plugin. In your Ext plugin, create a
file `/docroot/WEB-INF/ext-impl/src/META-INF/ext-spring.xml`
and declare the beans you'll override with a custom configuration. 

Here are a few of the pre-configured analyzers from the
`com.liferay.portal.search.lucene.PerFieldAnalyzerWrapper` bean configuration in
`search-spring.xml`:

    <entry key="tag"
           value-ref="com.liferay.portal.search.lucene.LikeKeywordAnalyzer" /> 
    <entry key="templateId" 
           value-ref="org.apache.lucene.analysis.KeywordAnalyzer" />
    <entry key="treePath"
           value-ref="com.liferay.portal.search.lucene.LikeKeywordAnalyzer" /> 
    <entry key="type" 
           value-ref="org.apache.lucene.analysis.KeywordAnalyzer" /> 
    <entry key="userName"
           value-ref="com.liferay.portal.search.lucene.LikeKeywordAnalyzer" /> 
    <entry key=".*_ar" 
           value-ref="org.apache.lucene.analysis.ar.ArabicAnalyzer" />
    <entry key=".*_de_DE"
           value-ref="org.apache.lucene.analysis.de.GermanAnalyzer" /> 
    <entry key=".*_el_GR" 
           value-ref="org.apache.lucene.analysis.el.GreekAnalyzer" />
    <entry key=".*_fa_IR"
           value-ref="org.apache.lucene.analysis.fa.PersianAnalyzer" /> 
    <entry key=".*_fr_[A-Z]{2}"
           value-ref="org.apache.lucene.analysis.fr.FrenchAnalyzer" />

You can define custom analyzers for any fields, including custom fields. 

You can learn how to change the API of a core service next. 

#### Changing the API of a Core Service [](id=changing-the-api-of-a-core-service)

Sometimes you might need to change the API of a method provided by one of
Liferay's services (e.g., `UserLocalService`). This is an advanced customization
need. 

Changing a core service API under normal circumstances requires modifying
Liferay's source code directly and making manual changes to a slew of files.
But that's not the Liferay way: there's a better way to do it. 

The best way to extend an existing service is by creating a custom service
that's complementary (e.g., a `MyUserLocalService` that includes all the new
methods). Your code can invoke this service instead of the default service, and
the implementation of your service can invoke the original service as needed. 

This technique doesn't require an Ext plugin since it can be done from portlet
plugins. In fact, using Service Builder for an Ext plugin is deprecated, but
it's supported for migration from the old extension environment. 

Sometimes it's desirable to change the implementation of the original service to
call your custom one; that's when you'll need an Ext plugin. Override the Spring
definition for `UserLocalServiceUtil` in `ext-spring.xml` and point it to your
`MyUserLocalServiceImpl` (instead of `UserLocalServiceImpl`). Now both
`MyUserLocalServiceUtil` and `UserLocalServiceUtil` will use the same Spring
bean: your new implementation. 

You can also replace core classes in `portal-impl`. Keep reading to find out
how. 

#### Replacing Core Classes in portal-impl [](id=replacing-core-classes-in-portal-impl)

If you're sure you need to change a core `portal-impl` class, and you're certain
it can't be replaced using a configuration file, here's the best way to do it
while avoiding conflicts when merging with a new portal version: 

1.  Rename the original class (e.g., `DeployUtil` &rarr; `MyDeployUtil`). 

2.  Create a new subclass with the old name (e.g., `DeployUtil extends
    MyDeployUtil`). 

3.  Override any methods you need to change. 

4.  Delegate static methods. 

5.  Use a logger with an appropriate class name for both classes (e.g.,
   `DeployUtil`). 

This strategy helps you determine what you'll need to merge when a new
version of Liferay is released. 

+$$$

**Tip:** This is an advanced technique; it may have a large impact on the
maintainability of your code. Seek alternatives, and if you're sure this is your
only option, think of it as a short term solution. Follow instructions [here](/participate/contribute-a-feature)
on contributing an improvement to Liferay. 

$$$

That's it for advanced customization techniques. It's time to consider how to
deploy Ext plugins in production. 

## Deploying in Production [](id=deploying-in-production)

Often, you can't use Ant to deploy web applications in production or
pre-production environments. Additionally, some application servers such as
WebSphere or WebLogic have their own deployment tools and don't work with
Liferay's auto-deployment process. Here are two methods to consider for
deploying and redeploying Ext plugins in production. 

### Method 1: Redeploying Liferay's Web Application [](id=method-1-redeploying-liferays-web-application)

You can use this method in any application server that supports [auto-deployment](/develop/tutorials/-/knowledge_base/6-2/deploying-your-plugins-hot-deploy-vs-auto-deploy#using-auto-deployment);
What's the benefit? The only artifact that needs to be transferred to the
production system is your Ext plugin's `.war` file, produced using the `ant war`
target. This `.war` file is usually small and easy to transport. Execute these
steps on the server: 

1.  Redeploy Liferay:

    If this is your first time deploying your Ext plugin to this server, skip
    this step. Otherwise, start by executing the same steps you first used to
    deploy Liferay on your app server. If you're using a bundle, unzip it again.
    If you installed Liferay manually on an existing application server, you
    must redeploy the Liferay `.war` file and copy both the libraries
    required globally by Liferay and your Ext plugin, to the appropriate
    directory within the application server. 

2.  Copy the Ext plugin `.war` into the auto-deploy directory. For a bundled
    Liferay distribution, the `deploy` folder is in Liferay's *root* folder of
    your bundle (e.g., `liferay-portal-[version]/`).

3.  Once the Ext plugin is detected and deployed by Liferay, restart your
    Liferay server. 

### Method 2: Generate an Aggregated WAR File [](id=method-2-generate-an-aggregated-war-file)

Some application servers don't support auto-deploy; WebSphere and WebLogic are
two examples. With an aggregated `.war` file, all Ext plugins are merged before
deployment to production. A single `.war` file then contains Liferay plus the
changes from all your Ext plugins. Before you deploy the aggregated Liferay
`.war` file, copy the dependency `.jar` files for Liferay and all Ext plugins to
the global application server class loader in the production server. The precise
location varies from server to server; refer to
[Deployment](/discover/deployment) to get the details for your application
server. 

To create the aggregated `.war` file, deploy the Ext plugin first to the Liferay
bundle you are using in your development environment. Once it's deployed,
restart the server so that the plugin is fully deployed and shut it down again.
Now the aggregated file is ready. 

Create a `.war` file by zipping the Liferay web application folder from within
the app server. Copy into your application server's global classpath all of the
libraries on which your Ext plugins depends. 

Now, perform these actions on your server: 

1.  Redeploy Liferay using the aggregated `.war` file. 

2.  Stop the server and copy the new version of the global libraries to the
    appropriate directory in the application server. 

Next we'll show you how to migrate your extension environment (from older
versions of Liferay) into Ext plugins. 

## Migrating Old Extension Environments [](id=migrating-old-extension-environments)

Because Ext plugins are an evolution of the extension environment provided in
Liferay 5.2 and earlier, you might need to migrate your extension environment
into Ext plugins when upgrading Liferay. If you need to do this, we have good
news; migrating is automated and relatively easy. 

+$$$

**Tip:** When migrating an extension environment, first consider whether any of
the extension environment's features can be moved into other types of plugins.
Portlets and hooks are designed to meet specific needs and they're easier to
learn. Additionally, they're easier to maintain since they often require fewer
changes when upgrading to a new version of Liferay.

$$$

To successfully migrate, execute an Ant target within the `ext` directory of the
Plugins SDK, pointing to the old extension environment and naming the new
plugin. 

    ant upgrade-ext -Dext.dir=/projects/liferay/ext -Dext.name=my-ext -Dext.display.name="My Ext"


Remember the three parameters you saw before: 

- `ext.dir`: The location of the old extension environment. 
- `ext.name`: The name of the Ext plugin that you want to create. 
- `ext.display.name`: The display name. 

After executing the target, you should see the logs of several copy operations
that take files from the extension environment and copy them into the equivalent
directory within the Ext plugin (see the section *Creating an Ext plugin* for an
explanation of the main directories within the plugin).

With the migration process finished, you can upgrade your code to the new
version of Liferay by completing a few additional tasks. Most commonly, you
should do these things: 

- Review the uses of Liferay's APIs and adapt them accordingly. 
- Review any changes to the new version of Liferay's JSPs. Merge your changes
  into the JSPs of the new Liferay version. 
- Run `ant build-service` to use Service Builder. It's also recommended
  to consider moving this code to a portlet plugin, because Service Builder is
  deprecated in Ext, and plugins allow for greater modularity and maintainability.
- If you implemented any portlets in the old extension environment, migrate them
  to portlet plugins; extension environment portlets have been deprecated since
  Liferay Portal 6.0, and support isn't guaranteed in future Liferay Portal
  releases. 

#### Licensing and Contributing [](id=licensing-and-contributing)

Liferay Portal is Open Source software licensed under the LGPL 2.1 license
([http://www.gnu.org/licenses/lgpl-2.1.html](http://www.gnu.org/licenses/lgpl-2.1.html)).
If you reuse any code snippet and redistribute it, whether publicly or to a
specific customer, make sure your modifications are compliant with the license.
One common way is to make the source code of your modifications available to the
community under the same license. Make sure you read the license text yourself
to find the option that best fits your needs. 

If your goal in making changes is fixing a bug or improving Liferay, it could be
of interest to a broader audience. Consider contributing it back to the project.
That benefits all users of the product including you, since you won't have to
maintain the changes with each newly released version of Liferay. You can notify
Liferay of bugs or improvements in
[issues.liferay.com](http://issues.liferay.com). Check out the [Participate](/participate/contribute-a-feature)
section of [dev.liferay.com](https://dev.liferay.com), to learn all the ways
that you can contribute to Liferay projects. 

In summary, Ext plugins are a powerful way to extend Liferay. There are no
limits to what you can customize with them, so use them carefully. Before using
an Ext plugin, see if you can implement all or part of the desired functionality
through [Application Display Templates]](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)
or a different plugin type. Portlets,
[hooks](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal), and
web plugins offer you a lot of extension capabilities themselves, without
introducing the complexity that's inherent with Ext plugins. If you need to use
an Ext plugin, make your customization as small as possible and follow the
instructions in this tutorial carefully to avoid issues.

## Related Topics [](id=related-topics)

[Customizing Liferay Portal with Hooks](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)

[Developing with the Plugins SDK](/develop/tutorials/-/knowledge_base/plugins-sdk)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/liferay-ide)

[Creating Liferay Maven Plugins from Liferay IDE](/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-liferay-ide) 
 
[Creating Liferay Maven Plugins from the Command Line](/develop/tutorials/-/knowledge_base/creating-liferay-maven-plugins-from-the-command-lin) 
