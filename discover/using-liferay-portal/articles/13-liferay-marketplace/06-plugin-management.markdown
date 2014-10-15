# Plugin Management [](id=plugin-management)

One of the primary ways of extending the functionality of Liferay Portal is by
the use of plugins. *Plugin* is an umbrella term for installable portlet, theme,
layout template, hook, Ext and web module Java EE `.war` files. Though Liferay
comes bundled with a number of functional portlets, themes, layout templates,
hooks and web modules, plugins provide a means of extending Liferay to be able
to do almost anything.

## Portlets [](id=portlets)

Portlets are small web applications that run in a portion of a web page. The
heart of any portal implementation is its portlets, because all of the
functionality of a portal resides in its portlets. Liferay's core is a portlet
container. The container's job is to manage the portal's pages and to aggregate
the set of portlets that are to appear on any particular page. This means the
core doesn't contain application code. Instead, all of the features and
functionality of your portal application must reside in its portlets.

+$$$

**Tip:** Liferay 4.4.2 and below support the Portlet 1.0 standard: JSR-168. 
Liferay 5.0 and above support the Portlet 2.0 standard: JSR-286. You cannot run 
Portlet 2.0 portlets in Liferay 4.4.2, but because the Portlet 2.0 standard is 
backwards-compatible, portlets written to the 1.0 standard will run in Liferay 
5.x and above.

$$$

Portlet applications, like servlet applications, have become a Java standard
which various portal server vendors have implemented. The JSR-168 standard
defines the portlet 1.0 specification and the JSR-286 standard defines the
portlet 2.0 specification. A Java standard portlet should be deployable on any
portlet container which supports the standard. Portlets are placed on the page
in a certain order by the end user and are served up dynamically by the portal
server. This means certain *givens* that apply to servlet-based projects, such
as control over URLs or access to the `HttpServletRequest` object, don't apply
in portlet projects, because the portal server generates these objects
dynamically. 

Portal applications come generally in two flavors: 1) portlets can be written to
provide small amounts of functionality and then aggregated by the portal server
into a larger application or 2) whole applications can be written to reside in
only one or a few portlet windows. The choice is up to those designing the
application. The developer only has to worry about what happens inside of the
portlet itself; the portal server handles building out the page as it is
presented to the user.

Most developers nowadays like to use certain frameworks to develop their
applications, because those frameworks provide both functionality and structure
to a project. For example, Struts enforces the Model-View-Controller design
pattern and provides lots of functionality, such as custom tags and form
validation, that make it easier for a developer to implement certain standard
features. With Liferay, developers are free to use all of the leading frameworks
in the Java EE space, including Struts, Spring MVC and Java Server Faces. This
allows developers familiar with those frameworks to more easily implement
portlets and also facilitates the quick porting of an application using those
frameworks over to a portlet implementation.

Additionally, Liferay allows for the consuming of PHP and Ruby applications as
portlets so you do not need to be a Java developer in order to take advantage of
Liferay's built-in features (such as user management, sites, organizations, page
building and content management). You can also use scripting languages such as
Groovy if you wish. You can use the Plugins SDK to deploy your PHP or Ruby
application as a portlet and it will run seamlessly inside of Liferay. We have
plenty of examples of this; to see them, check out the Plugins SDK from
Liferay's public code repository.

Does your organization make use of any Enterprise Planning (ERP) software that
exposes its data via web services? You could write a portlet plugin for Liferay
that can consume that data and display it as part of a dashboard page for your
users. Do you subscribe to a stock service? You could pull stock quotes from
that service and display them on your page, instead of using Liferay's built-in
Stocks portlet. Do you have a need to combine the functionality of two or more
servlet-based applications on one page? You could make them into portlet plugins
and have Liferay display them in whatever layout you want. Do you have existing
Struts, Spring MVC or JSF applications you want to integrate with your portal?
It is a straightforward task to migrate these applications into Liferay, then
they can take advantage of the layout, security and administration
infrastructure that Liferay provides.

## Themes [](id=themes)

![Figure 13.15: Envision Theme from Liferay's Theme
Repository](../../images/marketplace-envision-theme.png)

Themes are hot deployable plugins which can completely transform the look and
feel of the portal. Most organizations have their own look and feel standards
which go across all of the web sites and web applications in the infrastructure.
Liferay makes it possible for a site designer to create a theme plugin which can
be installed, allowing for the complete transformation of the portal to whatever
look and feel is needed. There are lots of available theme plugins on Liferay's
web site and more are being added every day. This makes it easier for those who
wish to develop themes for Liferay, as you can now choose a theme which most
closely resembles what you want to do and then customize it. This is much easier
than starting a theme from scratch. You can learn more about theme development
in [*Liferay in Action*](http://manning.com/sezov).

![Figure 13.16: Murali Theme from Liferay's Theme
Repository](../../images/marketplace-murali-theme.png)

## Layout Templates [](id=layout-templates)

Layout Templates are ways of choosing how your portlets will be arranged on a
page. They make up the body of your page, the large area into which you can drag
and drop portlets. Liferay Portal comes with several built-in layout templates.
If you have a complex page layout (especially for your home page), you may wish
to create a custom layout template of your own. This is covered in [*Liferay in
Action*](http://manning.com/sezov).

## Hook Plugins [](id=hook-plugins)

Hook plugins were introduced with Liferay 5.2. As the name implies, they allow
"hooking" into Liferay's core functionality. This means they enable developers
to override or replace functionality that is in the core of the system. You can
hook into the eventing system, model listeners and portal properties. You can
also override Liferay's core JSPs with your own. Hooks are very powerful and
have been designed to replace most of the reasons for using the extension
environment with something that is easier to use and hot deployable.

## Web Plugins [](id=web-plugins)

Web plugins are regular Java EE web modules designed to work with Liferay.
Liferay supports integration with various Enterprise Service Bus (ESB)
implementations, as well as Single Sign-On implementations, workflow engines and
so on. These are implemented as web modules used by Liferay portlets to provide
functionality.

## Installing Plugins from Repositories [](id=installing-plugins-from-repositories)

Liferay Portal has a section of the control panel called Plugins Installation,
which you can find under the Server heading. This section not only allows you to
see what plugins are installed in your portal, but also it enables you to run
the search indexer on those portlets that support it and install new portlets.

Use the dockbar's *Go to* menu to select *Control Panel*. Under the Server
heading, select *Plugins Installation*. You should now see the page which allows
you to configure and install portlets.

The default view of the Plugins Installation page shows which plugins are
already installed on the system and whether or not they are active. The Portlet
Plugins tab allows you reindex certain portlets to improve their searchability.
The Theme and Layout Template Plugins tabs display which portal roles can access
them.

![Figure 13.17: Plugins Installation Portlet Tab Default
View](../../images/marketplace-plugins-installation-portlet-tab.png)

![Figure 13.18: Plugins Installation Theme Tab Default
View](../../images/marketplace-plugins-installation-theme-tab.png)

If you would like to see what plugins are available, you can do so by clicking
the *Install More <Plugin Type>* button, where <Plugin Type> changes based on
which tab you are viewing. Please note the machine running Liferay must have
access to the Internet to read the Official and Community repositories. If the
machine does not have Internet access, you will need to download the plugins
from the site and install them manually. We will discuss how to do this later in
this chapter.

It's easy to navigate from the initial page of the Plugin Installer to different
pages since the plugins are listed alphabetically. You can also change the
number of items per page and navigate to a specific page if you know where a
particular plugin appears in the list. This is a standard feature of Liferay and
you will see it in most of Liferay's portlets.

![Figure 13.19: Installing
Plugins](../../images/marketplace-plugins-installer.png)

After you click the *Install More <Plugin Type>* button, a new view appears.
This view has multiple tabs, and by default, displays the *Portlet Plugins* tab.
Note the list displayed is a list of all of the plugins available across all of
the repositories to which the server is subscribed. Above this is a search
mechanism which allows you to search for plugins by their name, by whether or
not they are installed, by tag or by which repository they belong to. To install
a plugin, choose the plugin by clicking on its name. For example, if you want to
use online web forms on your web site, you might want to install the Web Form
portlet. This portlet provides a handy interface which allows you to create
forms for users to fill out. You can specify an address to which the results
will be emailed.

Find the Web Form Portlet in the list by searching for it or browsing to it.
Once you have found it, click on its name. Another page will be displayed which
describes the portlet plugin in more detail. Below the description is an
*Install* button. Click this button to install your plugin.

![Figure 13.20: Installing the Web Form
Portlet](../../images/marketplace-installing-web-form-portlet.png)

Once you click *Install*, your chosen plugin will automatically download and be
installed on your instance of Liferay. If you have the Liferay console open, you
can view the deployment as it happens. When it is finished, you should be able
to go back to the Add Application window and add your new plugin to a page in
your portal.

The same procedure is used for installing new Liferay themes, layout templates,
hooks and web modules. Instead of the *Portlet Plugins* tab, you would use the
appropriate tab for the type of plugin you wish to install to view the list of
plugins of that type. For themes, convenient thumbnails (plus a larger version
when you click on the details of a particular theme) are shown in the list.

After clicking on the *Install* button for a theme, the theme becomes available
on the *Look and Feel* tab of any page.

## Installing Plugins Manually [](id=installing-plugins-manually)

Installing plugins manually is almost as easy as installing plugins via the
Plugin Installer. There are several scenarios in which you would need to install
plugins manually rather than from Liferay's repositories:

-   Your server is firewalled without access to the Internet. This makes it
    impossible for your instance of Liferay to connect to the plugin
    repositories.

-   You are installing portlets which you have either purchased from a vendor,
    downloaded separately or developed yourself.

-   For security reasons, you do not want to allow portal administrators to
    install plugins from the Internet before they are evaluated.

You can still use the control panel to install plugins that are not available
from the online repositories. This is by far the easiest way to install plugins.

If your server is firewalled, you will not see any plugins displayed in the
Portlet Plugins or Theme Plugins tabs. Instead, you will need to click the
*Upload File* tab. This gives you a simple interface for uploading a `.war` file
containing a plugin to your Liferay Portal.

![Figure 13.21: Installing a Plugin
Manually](../../images/marketplace-plugin-installer-upload-file.png)

Click the *Browse* button and navigate your file system to find the portlet or
theme `.war` you have downloaded. The other field on the page is optional: you
can specify your own context for deployment. If you leave this field blank, the
default context defined in the plugin (or the `.war` file name itself) will be
used.

That's all the information the Plugin Installer needs in order to deploy your
portlet, theme, layout template, hook or web module. Click the *Install* button
and your plugin will be uploaded to the server and deployed. If it is a portlet,
you should see it in the *Add Content* window. If it is a theme, it will be
available on the *Look and Feel* tab in the page definition.

If you do not wish to use the Update Manager or Plugin Installer to deploy
plugins, you can also deploy them at the operating system level. The first time
Liferay starts, it creates a *hot deploy* folder which is, by default, created
inside the Liferay Home folder. This folder generally resides one directory up
from where your application server is installed, though it may be elsewhere
depending on which application server you are running. To find out where the
Liferay Home folder is for your application server, please see the section on
your server in chapter 1. The first time Liferay is launched, it will create a
folder structure in Liferay Home to house various configuration and
administrative data. One of the folders it creates is called *deploy*. If you
copy a portlet or theme plugin into this folder, Liferay will deploy it and make
it available for use just as though you'd installed it via the Plugin Installer
in the control panel. In fact, this is what the Plugin Installer is doing behind
the scenes.

You can change the defaults for this directory structure so it is stored
anywhere you like by modifying the appropriate properties in your
`portal-ext.properties` file. Please see the above section on the
`portal-ext.properties` file for more information.

To have Liferay hot deploy a portlet or theme plugin, copy the plugin into your
hot deploy folder, which by default is in `[Liferay Home]/deploy`. If you are
watching the Liferay console, you should see messages like the following:

	16:11:47,616 INFO [PortletAutoDeployListener:71] Copying portlets for
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/deploy/weather-portlet-6.0.4.1.war
	
	Expanding:
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/deploy/weather-portlet-6.0.4.1.war
	into
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694/WEB-INF
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694/WEB-INF/classes
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694/WEB-INF/classes
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694/META-INF
	
	Copying 37 files to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/webapps/weather-portlet
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/webapps/weather-portlet
	
	Deleting directory
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694
	
	16:11:48,072 INFO [PortletAutoDeployListener:81] Portlets for
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/deploy/weather-portlet-6.0.4.1.war
	copied successfully. Deployment will start in a few seconds.
	
	Jul 29, 2010 4:11:50 PM org.apache.catalina.startup.HostConfig
	deployDirectory
	
	INFO: Deploying web application directory weather-portlet
	
	16:11:50,585 INFO [PortletHotDeployListener:222] Registering portlets
	for weather-portlet
	
	16:11:50,784 INFO [PortletHotDeployListener:371] 1 portlet for
	weather-portlet is available for use

The *available for use* message means your plugin was installed correctly and is
available for use in the portal.

## Plugin Troubleshooting [](id=plugin-troubleshooting)

Sometimes plugins fail to install. There can be different reasons for
installation failure based on several factors, including

-   Liferay configuration

-   The container upon which Liferay is running

-   Changing the configuration options in multiple places

-   How Liferay is being launched

You can often tell whether or not you have a plugin deployment problem by
looking at the Liferay server console. If the hot deploy listener recognizes the
plugin, you'll see a *plugin copied successfully* message. If this message is
not followed up by an *available for use* message then you have an issue with
your plugin deployment configuration, probably due to one of the factors listed
above.

Let's take a look at each of these factors.

### Liferay Configuration Issues [](id=liferay-configuration-issues)

+$$$

**Tip:** This applies to Liferay versions prior to version 4.3.5. Liferay 
versions above 4.3.5 are able to auto detect the type of server it is running 
on, which makes things a lot easier. If you are running a newer version of 
Liferay, you can skip this section. If you are upgrading from one of these 
versions, continue reading.

$$$

Liferay by default comes as a bundle or as a `.war` file. Though every effort
has been made to make the `.war` file as generic as possible, sometimes the
default settings are inappropriate for the container upon which Liferay is
running. Most of these problems were resolved in Liferay 4.3.5 with the addition
of code that allows Liferay to determine which application server it is running
on and adjust the way it deploys plugins as a result. If you have upgraded from
one of these older versions, you may still have settings in your
`portal.ext.properties` file that are no longer needed. One of these settings is
the manual override of the default value of `auto.deploy.dest.dir`.

In versions of Liferay prior to 4.3.5, there is a property called
`auto.deploy.dest.dir` that defines the folder where plugins are deployed after
the hot deploy utilities have finished preparing them. This folder maps to a
folder the container defines as an auto-deploy or a hot deploy folder. By
default in older versions of Liferay, this property is set to `../webapps`. This
default value works for Tomcat containers (if Tomcat has been launched from its
`bin` folder) but will not work for other containers that define their hot
deploy folders in a different place. In newer versions of Liferay, this value is
automatically set to the default for the application server upon which Liferay
is running.

For example, Glassfish defines the hot deploy folder as a folder called
`autodeploy` inside of the domain folder in which your server is running. By
default, this is in `<Glassfish Home>/domains/domain1/autodeploy`. JBoss defines
the hot deploy folder as a root folder inside the particular server
configuration you are using. By default, this is in `<JBoss
Home>/server/default/deploy`. WebLogic defines this folder inside of the domain
directory. By default, this is in `<Bea Home>/user_projects/domains/<domain
name>/autodeploy`.

The best thing to do when upgrading to newer versions of Liferay Portal is to
remove this property altogether. It is not needed, as the autodetection of the
container handles the hot deploy location. If, for whatever reason, you need to
customize the location of the hot deploy folder, follow the instructions below.

You will first need to determine where the hot deploy folder is for the
container you are running. Consult your product documentation for this. Once you
have this value, there are two places in which you can set it: in the
`portal-ext.properties` file and in the Plugin Installer portlet.

To change this setting in the `portal-ext.properties` file, browse to where
Liferay was deployed in your application server. Inside of this folder should be
a `WEB-INF/classes` folder. Here you will find the `portal-ext.properties` file.
Open this file in a text editor and look for the property
`auto.deploy.dest.dir`. If it does not appear in the file, you can add it. The
safest way to set this property, as we will see later, is to define the property
using an absolute path from the root of your file system to your application
server's hot deploy folder. For example, if you are using Glassfish, and you
have the server installed in `/java/glassfish`, your `auto.deploy.dest.dir`
property would look like the following:

	auto.deploy.dest.dir=/java/glassfish/domains/domain1/autodeploy

Remember, if you are on a Windows system, use forward slashes instead of back
slashes, like so:

	auto.deploy.dest.dir=C:/java/glassfish/domains/domain1/autodeploy

Save the file and then restart your container. Now plugins should install
correctly.

Instead of changing the hot deploy destination directory in your
`portal-ext.properties` file, you can do it via the Plugin Installer. To change
the setting this way, navigate to the Plugins Installation page of the control
panel, click the *Install More <Plugins>* button. This will bring you to the
Plugin Installer page. Next, click on the *Configuration* tab of the Plugin
Installer page. There are a number of settings you can change on this tab,
including the default folders for hot deploy, where Liferay should look for
plugin repositories and so on.

![Figure 13.22: Changing the Hot Deploy Destination
Directory](../../images/marketplace-plugin-installer-configuration.png)

The setting to change is the field labeled *Destination Directory*. Change this
to the full path to your container's auto deploy folder from the root of your
file system. When you are finished, click the *Save* button at the bottom of the
form. The setting will now take effect without your having to restart your
container. Note the setting in the control panel overrides the setting in the
properties file.

If you are having hot deploy trouble in Liferay versions 4.3.5 and greater, it
is possible the administrator of your application server has changed the default
folder for auto deploy in your application server. In this case, you would want
to set `auto.deploy.dest.dir` to the customized folder location as you would
with older versions of Liferay. In Liferay 4.3.5 and greater, this setting still
exists but is blank. Add the property to your `portal-ext.properties` file and
set its value to the fully qualified path to the auto deploy folder configured
in your application server.

### Deploy Issues for Specific Containers [](id=deploy-issues-for-specific-containers)

Some containers, such as WebSphere®, don't have a hot deploy feature.
Unfortunately, these containers do not work with Liferay's hot deploy system.
But this does not mean you cannot install plugins on these containers. You can
deploy plugins manually using the application server's deployment tools. Liferay
is able to pick up the portlet plugins once they get deployed to the container
manually, especially if you add it to the same Enterprise Application project
that was created for Liferay.

When Liferay hot deploys portlet and theme `.war` files, it sometimes makes
modifications to those files right before deployment. In order to successfully
deploy plugins using an application server vendor's tools, you will want to run
your plugins through this process before you attempt to deploy them.

Navigate back to the *Configuration* tab of the Plugin Installer. Enter the
location you would like plugin `.war` files to be copied to after they are
processed by Liferay's plugin installer process into the *Destination Directory*
field. You will use this as a staging directory for your plugins before you
install them manually with your server's deployment tools. When you are
finished, click *Save*.

Now you can deploy plugins using the Plugin Installer portlet or by dropping
`.war` files into your auto deploy directory. Liferay will pick up the files,
modify them and then copy the result into the destination directory you have
configured. You may then deploy them from here to your application server.

#### Example: WebSphere &#174; Application Server [](id=example-websphere-174-application-server)

1.  If you don't have one already, create a `portal-ext.properties` file in the
    Liferay Home folder of your Liferay installation. Add the following
    directive to it:

	auto.deploy.dest.dir=${liferay.home}/websphere-deploy

2.  Create a folder called `websphere-deploy` inside your `$LIFERAY_HOME`
    folder. This is the folder where the Lucene index, Jackrabbit config and
    deploy folders are.

3.  Make sure the `web.xml` file inside the plugin you want to install has the
    following context parameter in it:

	<context-param\>
	
	<param-name\>com.ibm.websphere.portletcontainer.PortletDeploymentEnabled</param-name\>
	
	<param-value\>false</param-value\>
	
	</context-param\>

Liferay versions 5.2.2 and higher will automatically inject this into the
`web.xml` file on WebSphere containers.

4.  The WebSphere deploy occurs in two steps. You will first use Liferay's tools
    to "pre-deploy" the file and then use WebSphere's tools to do the actual
    deployment. This is because Liferay makes deployment-time modifications to
    the plugins right before they are actually deployed to the application
    server. For other application servers, this can usually be done in one step,
    because Liferay can make the modifications and then copy the resulting
    `.war` file into an autodeploy folder to have it actually deployed. Because
    WebSphere does not have an autodeploy feature, we need to separate these two
    steps.

5.  Deploy your .war file using Liferay's Plugin Installer or by copying it into
    `$LIFERAY_HOME/deploy`. Liferay will make its modifications, and because we
    changed the `auto.deploy.dest.dir` in the first step, it will copy the
    resulting `.war` file into `$LIFERAY_HOME/websphere-deploy`. You will see a
    *copied successfully* message in the log.

6.  Use WebSphere's tools to deploy the `.war` file. Make the context root for
    the `.war` file equal to the file name (i.e., `/my-first-portlet`). Once the
    `.war` file is deployed, save it to the master configuration.

7.  Go back to the *Applications &rarr; Enterprise Applications* screen in the
    WebSphere Admin Console. You will see your portlet is deployed but not yet
    started. Start it.

8.  Liferay will immediately recognize the portlet has been deployed and
    register it. The portlet will be automatically started and registered upon
    subsequent restarts of WebSphere.

Experienced WebSphere system administrators can further automate this by writing
a script which watches the `websphere-deploy` directory and uses `wsadmin`
commands to then deploy plugins automatically.

### Changing the Configuration Options in Multiple Places [](id=changing-the-configuration-options-in-multiple-places)

Sometimes, especially during development when several people have administrative
access to the server at the same time, the auto deploy folder location may
inadvertently be customized in both the `portal-ext.properties` file and in the
control panel. If this happens, the value in the control panel takes precedence
over the value in the properties file. If you go into the control panel and
change the value to the correct setting, plugin deployment will start working
again.
