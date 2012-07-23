# Properties Reference [](id=configuring-liferay-s-properties)

Liferay is configured by a combination of settings that are stored in the database (configured by the use of the control panel) and settings which are stored in properties (text) files. These files can be modified to change Liferay's behavior in certain ways. In this chapter we discuss the main configuration file, which is stored in the Liferay Home directory, and is called `portal-ext.properties` if you've got a custom file, or `portal-setup-wizard.properties` if you've used Liferay's setup wizard. This chapter has a wide-ranging set of topics since you can make many different kinds of customizations. We list a few of these below.

-   Changing Portal Defaults

-   Modifying Properties of Users, Organizations, Sites, Roles, etc.

-   Configuring Security Settings

-   Configuring Deployment Settings

-   Configuring Validation Settings

-   Accessing Liferay's Web Services 

Remember that your customizations in the `portal-ext.properties` or `portal-setup-wizard.properties` file override the settings in the `portal.properties` file. You should never modify the `portal.properties` file directly. 

## Using an overridden configuration file [](id=using-an-overridden-configuration-file)

Liferay's properties files differ from the configuration files of most other products in that changing the default configuration file is discouraged. In fact, the file that contains all of the defaults is stored inside of a `.jar` file, making it more difficult to customize. Why is it set up this way? Because Liferay uses the concept of *overriding* the defaults in a separate file, rather than going in and customizing the default configuration file. You put just the settings you want to customize in your own configuration file, keeping the portal configuration file uncluttered and containing only the settings you need. This makes it far easier to determine whether a particular setting has been customized and it makes the settings more portable across different installations of Liferay.

The default configuration file is called `portal.properties` and it resides inside the `portal-impl.jar` file. This `.jar` file is in Liferay Portal's `WEB-INF/lib` folder. The file used to override the configuration is `portal-ext.properties` if you've created a custom file, or `portal-setup-wizard.properties` if you've used Liferay's setup wizard. This file is created in your Liferay Home folder (please see chapter 14 on installing Liferay for the location of this folder for your application server). What follows are brief descriptions of the options that you can customize, thus overriding the defaults from the `portal.properties` file. These are presented in a logical order, not an alphabetical one, as many properties relate to other properties in the system.

## Properties Override [](id=properties-override)

This property specifies where to get the overridden properties. By default, it is `portal-ext.properties`. Updates should not be made in `portal.properties` or in `portal-bundles.properties`, but in `portal-ext.properties`. Furthermore, each portal instance can have its own overridden property file following the convention `portal-companyid.properties`. For example, one read order might be: `portal.properties`, then `portal-ext.properties` and then `portal-test.properties`.

The default read order is: `portal.properties`, then `portal-bundle.properties`, `portal-ext.properties` and then `portal-setup-wizard.properties`.

*Examples:*

	include-and-override=portal-bundle.properties
	include-and-override=${liferay.home}/portal-bundle.properties
	include-and-override=portal-ext.properties
	include-and-override=${liferay.home}/portal-ext.properties
	include-and-override=portal-setup-wizard.properties
	include-and-override=${liferay.home}/portal-setup-wizard.properties

Each portal instance can have its own overriden property file following the convention `portal-companyWebId.properties`. To enable this feature, set the `company-id-properties` system property to `true`:

	java ... -Dcompany-id-properties=true

The read order will now be: `portal.properties`, then `portal-ext.properties` and then `portal-liferay.com.properties`.

Note that not all properties can have different values per company. This functionality is only available for legacy reasons. The preferred way to configure a portal instance is through the control panel.

	include-and-override=portal-${easyconf:companyId}.properties
	include-and-override=${liferay.home}/portal-${easyconf:companyId}.properties

Additional property files can be used by setting the `external-properties` system property.

A common use case is to keep legacy property values when upgrading to newer versions of Liferay. To enable:

	java ... -Dexternal-properties=portal-legacy-5.1.properties

The read order will now be: portal.properties, then portal-ext.properties and then portal-legacy-5.1.properties.

	include-and-override=${external-properties}
	include-and-override=${liferay.home}/${external-properties}

## Liferay Home [](id=liferay-home)

This property specifies the Liferay home directory.

	liferay.home=${resource.repositories.root}

This property is available for backwards compatibility. Please set the property `liferay.home` instead.

	resource.repositories.root=${default.liferay.home}

## Portal Context [](id=portal-context)

This property specifies the path of the portal servlet context. This is needed because `javax.servlet.ServletContext` did not have access to the context path until Java EE 5.

Set this property if you deploy the portal to another path besides root.

*Examples:*

	portal.ctx=/
	portal.ctx=/portal

This property specifies the application server's HTTP port. This value should be different from the value in the property `web.server.http.port`. That property refers to the web server, this property refers to the application server. This property should only be set when the application server is sitting behind a web server like Apache. See [http://issues.liferay.com/browse/LPS-17106](http://issues.liferay.com/browse/LPS-17106) for more information.

    portal.instance.http.port=


Set this property if the application server is served behind a proxy and a prefix needs to be added to the portal servlet context path. This prefix will also be added to static resources served by layout templates, portlets and themes.

    portal.proxy.path=

## TCK (Technology Compatibility Kit) [](id=tck-technology-compatibility-kit-)

Set this to `true` to enable programmatic configuration to let the Portlet TCK obtain a URL for each test. This should never be set to `true` unless you are running the TCK tests.

	tck.url=false

## Schema [](id=schema)

Set this to `true` to automatically create tables and populate with default data if the database is empty.

	schema.run.enabled=true

Set this to to `true` to populate with the minimal amount of data. Set this to `false` to populate with a larger amount of sample data.

	schema.run.minimal=true

## Upgrade [](id=upgrade)

Input a list of comma delimited class names that implement `com.liferay.portal.upgrade.UpgradeProcess`. These classes will run on startup to upgrade older data to match with the latest version. Escape the comma so that the property can be properly interpolated. For example, the property "upgrade.processes.6100" will not properly reference the property "upgrade.processes.default" unless the commas are escaped. See `StartupHelper#getUpgradeProcessClassNames(String)`.

    upgrade.processes.master=\
        com.liferay.portal.upgrade.UpgradeProcess_5_0_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_1_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_1_2\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_2_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_2_1\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_2_2\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_2_3\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_1\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_2\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_3\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_5\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_6\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_1

    upgrade.processes.5000=${upgrade.processes.master}
    upgrade.processes.5001=${upgrade.processes.master}
    upgrade.processes.5100=${upgrade.processes.master}
    upgrade.processes.5101=${upgrade.processes.master}
    upgrade.processes.5102=${upgrade.processes.master}
    upgrade.processes.5200=${upgrade.processes.master}
    upgrade.processes.5201=${upgrade.processes.master}
    upgrade.processes.5202=${upgrade.processes.master}
    upgrade.processes.5203=${upgrade.processes.master}
    upgrade.processes.6000=${upgrade.processes.master}
    upgrade.processes.6001=${upgrade.processes.master}
    upgrade.processes.6002=${upgrade.processes.master}
    upgrade.processes.6003=${upgrade.processes.master}
    upgrade.processes.6004=${upgrade.processes.master}
    upgrade.processes.6005=${upgrade.processes.master}
    upgrade.processes.6006=${upgrade.processes.master}
    upgrade.processes.6100=${upgrade.processes.master}
    upgrade.processes.6101=${upgrade.processes.master}
    upgrade.processes.6110=${upgrade.processes.master}
    upgrade.processes.6120=${upgrade.processes.master}

This property is deprecated and only provided for backwards compatibility. If this property is specified with a list of classes, then the portal will use this list of upgrade processes without taking into account any of the other "upgrade.processes.\*" property settings.

    upgrade.processes=

The following are upgrade processes for EE releases.

    upgrade.processes.5103=\
        com.liferay.portal.upgrade.UpgradeProcess_5_1_5\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_1_6\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_1_7\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_1_7_to_5_2_7\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_2_7_to_6_0_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_1\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_2\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_3\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_5\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_6\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_1

    upgrade.processes.5104=${upgrade.processes.5103}
    upgrade.processes.5105=${upgrade.processes.5103}
    upgrade.processes.5106=${upgrade.processes.5103}
    upgrade.processes.5107=${upgrade.processes.5103}

    upgrade.processes.5108=\
        com.liferay.portal.upgrade.UpgradeProcess_5_1_8_to_5_2_8\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_2_8_to_6_0_5\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_6\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_1

    upgrade.processes.5204=\
        com.liferay.portal.upgrade.UpgradeProcess_5_2_5\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_2_5_to_6_0_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_1\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_2\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_3\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_5\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_6\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_1

    upgrade.processes.5205=${upgrade.processes.5204}

    upgrade.processes.5206=\
        com.liferay.portal.upgrade.UpgradeProcess_5_2_7\,\
        com.liferay.portal.upgrade.UpgradeProcess_5_2_7_to_6_0_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_1\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_2\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_3\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_5\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_6\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_1

    upgrade.processes.5207=${upgrade.processes.5206}

    upgrade.processes.5208=\
        com.liferay.portal.upgrade.UpgradeProcess_5_2_8_to_6_0_5\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_6\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_1

    upgrade.processes.5209=\
        com.liferay.portal.upgrade.UpgradeProcess_5_2_9_to_6_0_11\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_12\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_12_to_6_1_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_1

    upgrade.processes.6010=${upgrade.processes.master}

    upgrade.processes.6011=\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_12\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_0_12_to_6_1_0\,\
        com.liferay.portal.upgrade.UpgradeProcess_6_1_1

    upgrade.processes.6012=${upgrade.processes.6011}

## Verify [](id=verify)

Input a list of comma delimited class names that implement `com.liferay.portal.integrity.VerifyProcess`. These classes will run on startup to verify and fix any integrity problems found in the database.

	verify.processes=com.liferay.portal.verify.VerifyProcessSuite

Specify the frequency for verifying the integrity of the database.

Constants in `VerifyProcess`:

	`public static final int ALWAYS = -1;`
	`public static final int NEVER = 0;`
	`public static final int ONCE = 1;`

	verify.frequency=1
	
## Convert [](id=convert)

Input a list of comma delimited class names that implement `com.liferay.portal.convert.ConvertProcess`. These classes can be run from within the Admin portlet to convert older data to match a new configuration of the portal.

    convert.processes=\
		com.liferay.portal.convert.ConvertDatabase,\
		com.liferay.portal.convert.ConvertDocumentLibrary,\
		com.liferay.portal.convert.ConvertDocumentLibraryExtraSettings,\
		com.liferay.portal.convert.ConvertPermissionAlgorithm,\
		com.liferay.portal.convert.ConvertPermissionTuner,\
		com.liferay.portal.convert.ConvertWikiCreole	

## Auto Deploy [](id=auto-deploy)

Input a list of comma delimited class names that implement `com.liferay.portal.kernel.deploy.auto.AutoDeployListener`. These classes are used to process the auto deployment of WARs.

    auto.deploy.listeners=\
        com.liferay.portal.deploy.auto.OSGiAutoDeployListener,\
		com.liferay.portal.deploy.auto.ExtAutoDeployListener,\  
		com.liferay.portal.deploy.auto.HookAutoDeployListener,\  
		com.liferay.portal.deploy.auto.LayoutTemplateAutoDeployListener,\  
		com.liferay.portal.deploy.auto.LiferayPackageAutoDeployListener,\ 
		com.liferay.portal.deploy.auto.PortletAutoDeployListener,\
		com.liferay.portal.deploy.auto.ThemeAutoDeployListener,\
		com.liferay.portal.deploy.auto.WebAutoDeployListener,\
		com.liferay.portal.deploy.auto.exploded.tomcat.HookExplodedTomcatListener,\ 
		com.liferay.portal.deploy.auto.exploded.tomcat.LayoutTemplateExplodedTomcatListener,\ 
		com.liferay.portal.deploy.auto.exploded.tomcat.PortletExplodedTomcatListener,\
		com.liferay.portal.license.deploy.auto.LicenseAutoDeployListener,\
		com.liferay.portal.deploy.auto.exploded.tomcat.ThemeExplodedTomcatListener
        
Set this to `true` to enable auto deploy of layout templates, portlets and themes.

	auto.deploy.enabled=true

Set the directory to scan for layout templates, portlets and themes to auto deploy.

	auto.deploy.deploy.dir=${liferay.home}/deploy

Set the directory where auto deployed WARs are copied to. The application server or servlet container must know to listen on that directory. Different containers have different hot deploy paths. For example, Tomcat listens on `${catalina.base}/webapps` whereas JBoss listens on `${jboss.home.dir}/deploy`. Set a blank directory to automatically use the application server specific directory.

*Examples: *

    auto.deploy.dest.dir=
	auto.deploy.default.dest.dir=../webapps
	auto.deploy.geronimo.dest.dir=${org.apache.geronimo.home.dir}/deploy
	auto.deploy.glassfish.dest.dir=${com.sun.aas.instanceRoot}/autodeploy
	auto.deploy.jboss.dest.dir=${jboss.home.dir}/standalone/deployments   
	auto.deploy.jetty.dest.dir=${jetty.home}/webapps    
	auto.deploy.jonas.dest.dir=${jonas.base}/deploy
	auto.deploy.resin.dest.dir=${resin.home}/webapps
	auto.deploy.tomcat.dest.dir=${catalina.base}/webapps
	auto.deploy.weblogic.dest.dir=${env.DOMAIN_HOME}/autodeploy

Set the interval in milliseconds for how often to scan the directory for changes.

	auto.deploy.interval=3000

Set the number of attempts to deploy a file before blacklisting it.

	auto.deploy.blacklist.threshold=10

Set this to `true` if deployed WARs are unpacked. Set this to `false` if your application server has concurrency issues with deploying large WARs.

	auto.deploy.unpack.war=true

Set this to `true` if plugins should automatically copy Commons Logging.

    auto.deploy.copy.commons.logging=true
    
Set this to `true` if plugins should automatically copy Log4j.

    auto.deploy.copy.log4j=true

Set this to `true` if you want the deployer to rename `portlet.xml` to `portlet-custom.xml`. This is only needed when deploying the portal on WebSphere 6.1.x with a version before 6.1.0.7 because WebSphere's portlet container will try to process a portlet at the same time that Liferay is trying to process a portlet.

Note that according to IBM, on versions *after* 6.1.0.9, you need to add a context parameter to the `web.xml` descriptor in your portlet application called `com.ibm.websphere.portletcontainer.PortletDeploymentEnabled` and set it to `false`. This parameter causes WebSphere's built-in portlet container to ignore your portlet application when it is deployed, enabling Liferay to pick it up.

	auto.deploy.custom.portlet.xml=false

Set this to `1` if you are using JBoss' `PrefixDeploymentSorter`. This will append a `1` in front of your WAR name. For example, if you are deploying a portlet called `test-portlet.war`, it will deploy it to `1test-portlet.war`. JBoss now knows to load this portlet after the other WARs have loaded. However, it will remove the `1` from the context path.

Modify `/server/default/conf/jboss-service.xml`. See `org.jboss.deployment.scanner.PrefixDeploymentSorter`.

	auto.deploy.jboss.prefix=
	
Set the Glassfish settings to enable JSR 88 application deployment.

    auto.deploy.glassfish.jee.deployment.enabled=false
    auto.deploy.glassfish.jee.dm.id=deployer:Sun:AppServer::localhost:4848
    auto.deploy.glassfish.jee.dm.user=admin
    auto.deploy.glassfish.jee.dm.passwd=adminadmin
    auto.deploy.glassfish.jee.df.classname=com.sun.enterprise.deployapi.SunDeploymentFactory

Set the path to Tomcat's configuration directory. This property is used to auto deploy exploded WARs. Tomcat context XML files found in the auto deploy directory will be copied to Tomcat's configuration directory. The context XML file must have a docBase attribute that points to a valid WAR directory.

	auto.deploy.tomcat.conf.dir=../conf/Catalina/localhost

Set the path to Tomcat's global class loader. This property is only used by Tomcat in a standalone environment.

	auto.deploy.tomcat.lib.dir=../common/lib/ext

Set the URLs of Libraries that might be needed to download during the auto deploy process.

	library.download.url.resin.jar=http://cdn.files.liferay.com/public/quercus/4.0.11/resin.jar
	library.download.url.script-10.jar=http://cdn.files.liferay.com/public/quercus/3.1.9/script-10.jar

## Hot Deploy [](id=hot-deploy)

Input a list of comma delimited class names that implement `com.liferay.portal.kernel.deploy.hot.HotDeployListener`. These classes are used to process the deployment and undeployment of WARs at runtime.

Note: `PluginPackageHotDeployListener` must always be first.

	hot.deploy.listeners=\
		com.liferay.portal.deploy.hot.PluginPackageHotDeployListener,\
		\
		com.liferay.portal.deploy.hot.ExtHotDeployListener,\
		com.liferay.portal.deploy.hot.HookHotDeployListener,\
		com.liferay.portal.deploy.hot.LayoutTemplateHotDeployListener,\
		com.liferay.portal.deploy.hot.PortletHotDeployListener,\
		com.liferay.portal.deploy.hot.SocialHotDeployListener,\
		com.liferay.portal.deploy.hot.ThemeHotDeployListener,\
		com.liferay.portal.deploy.hot.ThemeLoaderHotDeployListener,\
		\
		com.liferay.portal.deploy.hot.MessagingHotDeployListener

## Hot Undeploy [](id=hot-undeploy)

Set this to `true` to enable undeploying plugins.

	hot.undeploy.enabled=true

Set the undeploy interval in milliseconds on how long to wait for the undeploy process to finish.

	hot.undeploy.interval=0

Set this to `true` to undeploy a plugin before deploying a new version. This property will only be used if the property `hot.undeploy.enabled` is set to `true`.

	hot.undeploy.on.redeploy=false

## Sandbox Deploy [](id=sandbox-deploy)

Input a list of comma delimited class names that implement `com.liferay.portal.kernel.deploy.sandbox.SandboxDeployListener`. These classes are used to process sandbox style plugins.

    sandbox.deploy.listeners=\
		com.liferay.portal.deploy.sandbox.PortletSandboxDeployListener,\
		com.liferay.portal.deploy.sandbox.ThemeSandboxDeployListener

Set this to `true` to enable sandbox style plugin development.

	sandbox.deploy.enabled=false

Set the directory to scan for sand box style plugins.

	sandbox.deploy.dir=${liferay.home}/sandbox

Set the interval in milliseconds on how often to scan the directory for changes.

	sandbox.deploy.interval=3000

## Plugin [](id=plugin)

Input a list of comma delimited supported plugin types.

	plugin.types=portlet,theme,layout-template,hook,web

Input a list of Liferay plugin repositories separated by `\n` characters.

	plugin.repositories.trusted=http://plugins.liferay.com/official
	plugin.repositories.untrusted=http://plugins.liferay.com/community

Set this property to `false` to avoid receiving on-screen notifications when there is a new version of an installed plugin.

	plugin.notifications.enabled=true

Input a list of plugin packages ids separated by `\n` characters. Administrators won't be notified when a new version of these plugins are available. The ids are of the form `groupId/artifactId`. You can also end the id with an asterisk to match any id that starts with the previous character.

	plugin.notifications.packages.ignored=liferay/sample-jsp-portlet

## Portlet [](id=portlet)

This property sets the default virtual path for all hot deployed portlets. See [liferay-portlet-app_6_1_0.dtd](http://docs.liferay.com/portal/6.1/definitions/liferay-portlet-app_6_1_0.dtd.html) and the `virtual-path` element for more information.

    portlet.virtual.path=

Set this property to `true` to validate `portlet.xml` against the portlet schema.

    portlet.xml.validate=true

Portlets that have configured `liferay-portlet.xml` with the element `add-default-resource` set to `true` will allow those portlets to be dynamically added to any page by any user. This is useful (and necessary) for some portlets that need to be dynamically added to a page, but it can also pose a security risk because it allows any user to do it.

Set this property to `true` to add a security check around this behavior. If set to `true`, then portlets can only be dynamically added to a page if they contain a proper security token. This security token is automatically passed when using a portlet URL from one portlet to another portlet.

Modify the property `portlet.add.default.resource.check.whitelist` to whitelist certain portlets from this security check.

The security check utilizes the implementation set in the property `auth.token.impl`.

    portlet.add.default.resource.check.enabled=true

Set a list of comma delimited portlet ids for portlets that will bypass the security check set in the property `portlet.add.default.resource.check.enabled`.

    portlet.add.default.resource.check.whitelist=3,56_INSTANCE_0000,58,82,86,87,103,113,145,164,166,170,177

Input a list of comma delimited struts actions that will bypass the security check set in the property `portlet.add.default.resource.check.enabled`.

    portlet.add.default.resource.check.whitelist.actions=\
        /journal/rss,\
        /language/view

Input a regular expression to ban paths that cannot be used to serve resources in portlets.

    portlet.resource.id.banned.paths.regexp=.*/(?:META-INF|WEB-INF)/.*
    
## Portlet Coordination [](id=portlet-coordination)

Set this property to specify how events are distributed. If the value is `layout-set`, then events will be distributed to all portlets contained in a layout set. If the value is `layout`, then events will be distributed to all portlets that are present in a layout.

    portlet.event.distribution=layout

Set this property to specify how public render parameters are distributed. If the value is `layout-set`, then public render parameters will be distributed to all portlets contained in a layout set. This will only work correctly if the property `layout.default.p_l_reset` is set to `false`. If the value is `layout`, then public render parameters will be distributed to all portlets that are present in a layout.

    portlet.public.render.parameter.distribution=layout

## Theme [](id=theme)

Set this property to `true` to load the theme's merged CSS files for faster loading for production. Set this property to `false` for easier debugging for development. You can also disable fast loading by setting the URL parameter `css_fast_load` to `0`.

    theme.css.fast.load=true

Set this property to `true` to load the theme's merged image files for
faster loading for production. Set this property to `false` for easier debugging for development. You can also disable fast loading by setting the URL parameter `images_fast_load` to `0`.

    theme.images.fast.load=true

Set this property to `true` to allow directly invoked JSPs to be overridden by a different version of the JSP that exists in a theme.

    theme.jsp.override.enabled=false

Set the theme's shortcut icon.

    theme.shortcut.icon=favicon.ico

Set this property to set the default virtual path for all hot deployed themes. See [http://docs.liferay.com/portal/6.1/definitions/liferay-look-and-feel_6_1_0.dtd.html](liferay-look-and-feel_6_1_0.dtd) and the `virtual-path` element for more information.

<!-- I think this link above is broken. It simply redirects to the "Liferay Portal 6.1 - User Guide" table of contents. -->

    theme.virtual.path=

Set this with an absolute path to specify where imported theme files from a LAR will be stored. This path will override the `file-storage` path specified in `liferay-theme-loader.xml`.

    theme.loader.storage.path=

Themes can be imported via LAR files. Set this to `true` if imported themes should use a new theme id on every import. This will ensure that a copy of the old theme is preserved in the theme loader storage path. However, this also means that a lot of themes that are no longer used remain in the file system. It is recommended that you set this to `false`.

    theme.loader.new.theme.id.on.import=false

Set this to `true` to decorate portlets by default.

    theme.portlet.decorate.default=true

Set this to `true` to expose sharing icons for portlets by default.

    theme.portlet.sharing.default=false

## Resource Actions [](id=resource-actions)

Input a list of comma delimited resource action configurations that will be read from the class path.

	resource.actions.configs=resource-actions/default.xml

This should only be set to `false` when it is called by Service Builder outside of an application server or servlet container.

    resource.actions.read.portlet.resources=true

## Model Hints [](id=model-hints)

Input a list of comma delimited model hints configurations.

	model.hints.configs=\
		META-INF/portal-model-hints.xml,\
		META-INF/workflow-model-hints.xml,\
		META-INF/ext-model-hints.xml,\
		META-INF/portlet-model-hints.xml

## Service Builder [](id=service-build-3)

Input a list of comma delimited method prefixes designated for read-only transactions. Service Builder will use these prefixes to annotate methods that are to run in read-only transactions.

	service.builder.service.read.only.prefixes=\
		fetch,\    
		get,\    
		has,\    
		is,\    
		load,\    
		reindex,\    
		search

## Spring [](id=spring)

Input a list of comma delimited Spring configurations. These will be loaded after the bean definitions specified in the `contextConfigLocation` parameter in `web.xml`.

Note that there is a special case for `hibernate-spring.xml` and `jpa-spring.xml`. Even though both files are specified, only one will actually load at runtime based on the property `persistence.provider`.

    spring.configs=\
        META-INF/base-spring.xml,\
        \
        META-INF/hibernate-spring.xml,\
        META-INF/infrastructure-spring.xml,\
        META-INF/management-spring.xml,\
        \
        META-INF/util-spring.xml,\
        \
        META-INF/jpa-spring.xml,\
        \
        META-INF/audit-spring.xml,\
        META-INF/cluster-spring.xml,\
        META-INF/editor-spring.xml,\
        META-INF/executor-spring.xml,\
        META-INF/jcr-spring.xml,\
        META-INF/ldap-spring.xml,\
        META-INF/messaging-core-spring.xml,\
        META-INF/messaging-misc-spring.xml,\
        META-INF/mobile-device-spring.xml,\
        META-INF/notifications-spring.xml,\
        META-INF/poller-spring.xml,\
        META-INF/rules-spring.xml,\
        META-INF/scheduler-spring.xml,\
        META-INF/scripting-spring.xml,\
        META-INF/search-spring.xml,\
        META-INF/workflow-spring.xml,\
        \
        META-INF/counter-spring.xml,\
        META-INF/mail-spring.xml,\
        META-INF/portal-spring.xml,\
        META-INF/portlet-container-spring.xml,\
        META-INF/staging-spring.xml,\
        \
        #META-INF/dynamic-data-source-spring.xml,\
        #META-INF/shard-data-source-spring.xml,\
        #META-INF/memcached-spring.xml,\
        #META-INF/monitoring-spring.xml,\
        \
        META-INF/ext-spring.xml

Set the bean name for the Liferay data source.

	spring.hibernate.data.source=liferayDataSource

Set the bean name for the Liferay session factory.

	spring.hibernate.session.factory=&liferaySessionFactory
	
Set this property to `false` to manually open and close sessions.

    spring.hibernate.session.delegated=true	
    
## Persistence [](id=persistence)

Set the provider used for ORM persistence. If this property is set to `jpa`, then the properties with the prefix `jpa.` will be read. If this property is set to `hibernate`, then the properties with the prefix `hibernate.` will be read.

Note that this property affects the loading of `hibernate-spring.xml` or `jpa-spring.xml` in the property `spring.configs`.

*Examples:*

	persistence.provider=hibernate
	persistence.provider=jpa    

## Hibernate [](id=hibernate)

Input a list of comma delimited Hibernate configurations.

    hibernate.configs=\
        META-INF/mail-hbm.xml,\
        META-INF/portal-hbm.xml,\
        META-INF/ext-hbm.xml

Liferay will automatically detect the Hibernate dialect in `com.liferay.portal.spring.PortalHibernateConfiguration`. Set this property to manually override the automatically detected dialect.

*Example:*

	hibernate.dialect=

Set the Hibernate connection release mode. You should not modify this unless you know what you're doing. The default setting works best for Spring managed transactions. See the method `buildSessionFactory` in class `org.springframework.orm.hibernate3.LocalSessionFactoryBean` and search for the phrase `on_close` to understand how this works.

*Example:*

	hibernate.connection.release_mode=on_close

Set the Hibernate cache region factory. Ehcache is recommended in a clustered environment. See the property `net.sf.ehcache.configurationResourceName` for detailed configuration.

    hibernate.cache.region.factory_class=com.liferay.portal.dao.orm.hibernate.region.SingletonLiferayEhcacheRegionFactory

This property is used if Hibernate is configured to use Ehcache's region factory.

    net.sf.ehcache.configurationResourceName=/ehcache/hibernate-clustered.xml

Configure this property if using Ehcache's default replication method for Hibernate's region cache.

    net.sf.ehcache.configurationResourceName.peerProviderProperties=peerDiscovery=automatic,multicastGroupAddress=${multicast.group.address["hibernate"]},multicastGroupPort=${multicast.group.port["hibernate"]},timeToLive=1

Set other Hibernate cache settings.

    hibernate.cache.use_query_cache=true
    hibernate.cache.use_second_level_cache=true
    hibernate.cache.use_minimal_puts=true
    hibernate.cache.use_structured_entries=false

Uncomment these properties to disable Hibernate caching.

*Examples:*

    hibernate.cache.use_query_cache=false
    hibernate.cache.use_second_level_cache=false

Set the JDBC batch size to improve performance.

If you're using Hypersonic, you SHOULD set the batch size to `0` as a workaround for a logging bug in the Hypersonic driver. See [http://issues.liferay.com/browse/LPS-5426](LPS-5426) for more information.

If you're using Oracle 9i, you MUST set the batch size to `0` as a workaround for a hanging bug in the Oracle driver. See [http://issues.liferay.com/browse/LEP-1234](LEP-1234) for more information.

<!--  These two links above appear to be broken.  -->

*Examples:*

	hibernate.jdbc.batch_size=20
	hibernate.jdbc.batch_size=0

Set other miscellaneous Hibernate properties.

    hibernate.jdbc.use_scrollable_resultset=true
    hibernate.bytecode.use_reflection_optimizer=true
    hibernate.show_sql=false

Use the classic query factory until WebLogic and Hibernate 3 can get along. See [http://www.hibernate.org/250.html#A23](http://www.hibernate.org/250.html#A23) for more information.

<!-- Another possible broken link? This redirects to Hibernate 3 migration guides.  --> 

    hibernate.query.factory_class=org.hibernate.hql.classic.ClassicQueryTranslatorFactory

Set this property to `true` to enable Hibernate cache monitoring. See [http://issues.liferay.com/browse/LPS-2056](LPS-2056) for more information.

<!--  This link above appear to be broken.  -->

    hibernate.generate_statistics=false
    
## JPA [](id=jpa)

Set this property to `none` to disable the JPA validation. Some application servers have the validation JARs in the global class path which causes conflicts with Hibernate. See LPS-22453 for more information.

	javax.persistence.validation.mode=none

Input a list of comma delimited JPA configurations.

    jpa.configs=\
        META-INF/mail-orm.xml,\
        META-INF/portal-orm.xml

Set the name of the JPA provider.

*Examples:*

    jpa.provider=eclipselink
    jpa.provider=hibernate
    jpa.provider=openjpa
    jpa.provider=toplink

Specify provider specific properties prefixed with `jpa.provider.property.`

*Examples:

    jpa.provider.property.eclipselink.allow-zero-id=true
    jpa.provider.property.eclipselink.logging.level=FINEST
    jpa.provider.property.eclipselink.logging.timestamp=true

The LoadTimeWeaver interface is a Spring class that allows JPA ClassTransformer instances to be plugged in a specific manner depending on the environment.

Not all JPA providers require a JVM agent (Hibernate is an example). If your provider does not require an agent or you have other alternatives (such as applying enhancements at build time through a custom compiler or an Ant task), then the loadtime weaver should not be used.

*Examples:*

    jpa.load.time.weaver=org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver
    jpa.load.time.weaver=org.springframework.instrument.classloading.glassfish.GlassFishLoadTimeWeaver
    jpa.load.time.weaver=org.springframework.instrument.classloading.oc4j.OC4JLoadTimeWeaver
    jpa.load.time.weaver=org.springframework.instrument.classloading.weblogic.WebLogicLoadTimeWeaver

Specify a specific database platform setting if the JPA provider is not able to detect the database platform.

Valid values for the Hibernate and OpenJPA providers are: DB2, DERBY, HSQL, INFORMIX, MYSQL, ORACLE, POSTGRESQL, SQL_SERVER and SYBASE.

Valid values for the EclipseLink provider are:
	org.eclipse.persistence.platform.database.DB2MainframePlatform,
	org.eclipse.persistence.platform.database.DB2Platform,
	org.eclipse.persistence.platform.database.DerbyPlatform,
	org.eclipse.persistence.platform.database.HSQLPlatform,
	org.eclipse.persistence.platform.database.InformixPlatform,
	org.eclipse.persistence.platform.database.MySQLPlatform,
	org.eclipse.persistence.platform.database.OraclePlatform,
	org.eclipse.persistence.platform.database.PostgreSQLPlatform,
	org.eclipse.persistence.platform.database.SQLServerPlatform, or
	org.eclipse.persistence.platform.database.SybasePlatform.

Check with JPA provider's documentation for details and all possible values.

	jpa.database.platform=

Liferay will automatically detect the database type by initializing DBUtil. You can override the value here if needed. Expected values are: `db2`, `derby`, `firebird`, `hypersonic`, `informix`, `ingres`, `interbase`, `jdatastore`, `mysql`, `oracle`, `postgresql`, `sap`, `sqlserver` and `sybase`.

	jpa.database.type=    

## JDBC [](id=jdbc)

Set the JNDI name to lookup the JDBC data source. If none is set, then the portal will attempt to create the JDBC data source based on the properties prefixed with `jdbc.default.`.

*Example:*

    jdbc.default.jndi.name=jdbc/LiferayPool

Set the properties used to create the JDBC data source. These properties will only be read if the property `jdbc.default.jndi.name` is not set.

The default settings are configured for an in-memory database called Hypersonic that is not recommended for production use. Please change the properties to use another database.

Add `dynamic-data-source-spring.xml` to the property `spring.configs` to configure the portal to use one database cluster for read calls and another database cluster for write calls. The convention is to create a set of properties prefixed with `jdbc.read.` to handle read calls and another set of properties prefixed with `jdbc.write.` to handle write calls. These data sources can also be created via JNDI by setting the properties `jdbc.read.jndi.name` and `jdbc.write.jndi.name`. Note that these settings, though separate, are a copy of the default settings with the newly overridden values.

DB2

*Examples:*

	jdbc.default.driverClassName=com.ibm.db2.jcc.DB2Driver
	jdbc.default.url=jdbc:db2://localhost:50000/lportal:deferPrepares=false;fullyMaterializeInputStreams=true;fullyMaterializeLobData=true;progresssiveLocators=2;progressiveStreaming=2;
	jdbc.default.username=db2admin
	jdbc.default.password=lportal

Derby

*Examples:*

	jdbc.default.driverClassName=org.apache.derby.jdbc.EmbeddedDriver
	jdbc.default.url=jdbc:derby:lportal
	jdbc.default.username=
	jdbc.default.password=

Hypersonic

	jdbc.default.driverClassName=org.hsqldb.jdbcDriver
	jdbc.default.url=jdbc:hsqldb:${liferay.home}/data/hsql/lportal
	jdbc.default.username=sa
	jdbc.default.password=

Ingres

*Examples:*

	jdbc.default.driverClassName=com.ingres.jdbc.IngresDriver
	jdbc.default.url=jdbc:ingres://localhost:II7/lportal
	jdbc.default.username=
	jdbc.default.password=

MySQL

*Examples:*

	jdbc.default.driverClassName=com.mysql.jdbc.Driver
	jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
	jdbc.default.username=
	jdbc.default.password=

Oracle

*Examples:*

	jdbc.default.driverClassName=oracle.jdbc.driver.OracleDriver
	jdbc.default.url=jdbc:oracle:thin:@localhost:1521:xe
	jdbc.default.username=lportal
	jdbc.default.password=lportal

P6Spy

*Examples:*

	jdbc.default.driverClassName=com.p6spy.engine.spy.P6SpyDriver
	jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
	jdbc.default.username=
	jdbc.default.password=

PostgreSQL

*Examples:*

	jdbc.default.driverClassName=org.postgresql.Driver
	jdbc.default.url=jdbc:postgresql://localhost:5432/lportal
	jdbc.default.username=sa
	jdbc.default.password=

SQL Server

*Examples:*

	jdbc.default.driverClassName=net.sourceforge.jtds.jdbc.Driver
	jdbc.default.url=jdbc:jtds:sqlserver://localhost/lportal
	jdbc.default.username=sa
	jdbc.default.password=

Sybase

*Examples:*

	jdbc.default.driverClassName=net.sourceforge.jtds.jdbc.Driver
	jdbc.default.url=jdbc:jtds:sybase://localhost:5000/lportal
	jdbc.default.username=sa
	jdbc.default.password=

Liferay can use C3PO, DBCP, or Primrose for connection pooling. See `com.liferay.portal.dao.jdbc.util.DataSourceFactoryBean` for the actual implementation. It is important to understand the strengths and weaknesses of each provider so that you can choose the best one that fits your deployment scenario. Provider specific properties can also be passed along directly to the provider. For example, the property `jdbc.default.acquireIncrement` is read by C3PO, the property `jdbc.default.maxActive` is read by DBCP and the property `jdbc.default.base` is read by Primrose.

The default provider is C3PO.

*Examples:*

    jdbc.default.liferay.pool.provider=c3po
    jdbc.default.liferay.pool.provider=dbcp
    jdbc.default.liferay.pool.provider=primrose
    jdbc.default.liferay.pool.provider=tomcat

The following properties will be read by C3PO if Liferay is configured to use C3PO in the property `jdbc.default.liferay.pool.provider`. See [http://www.mchange.com/projects/c3p0/index.html#configuration](http://www.mchange.com/projects/c3p0/index.html#configuration) for a list of additional fields used by C3PO for configuring database connections.

*Examples:*

    jdbc.default.acquireIncrement=5
    jdbc.default.acquireRetryAttempts=3
    jdbc.default.acquireRetryDelay=1000
    jdbc.default.connectionCustomizerClassName=com.liferay.portal.dao.jdbc.pool.c3p0.PortalConnectionCustomizer
    jdbc.default.idleConnectionTestPeriod=60
    jdbc.default.maxIdleTime=3600
    jdbc.default.maxPoolSize=100
    jdbc.default.minPoolSize=10
    jdbc.default.numHelperThreads=10
    jdbc.default.transactionIsolation=1

The following properties will be read by both DBCP and Tomcat JDBC Connection Pool if Liferay is configured to use DBCP or Tomcat JDBC Connection Pool in the property `jdbc.default.liferay.pool.provider`. See [http://commons.apache.org/dbcp/configuration.html](http://commons.apache.org/dbcp/configuration.html) and [http://people.apache.org/~fhanik/jdbc-pool/jdbc-pool.html](http://people.apache.org/~fhanik/jdbc-pool/jdbc-pool.html) for a list of additional fields used by DBCP or Tomcat JDBC Connection Pool for configuring database connections.

    jdbc.default.defaultTransactionIsolation=READ_UNCOMMITTED
    jdbc.default.maxActive=100
    jdbc.default.minIdle=10
    jdbc.default.removeAbandonedTimeout=3600

The following properties will be read by Primrose if Liferay is configured to use Primrose in the property `jdbc.default.liferay.pool.provider`. See [http://www.primrose.org.uk/primrose3/primroseConfig.html](http://www.primrose.org.uk/primrose3/primroseConfig.html) for a list of additional fields used by Primrose for configuring database connections.

<!--  This link above appear to be broken.  -->

*Examples:*

    jdbc.default.base=100
    jdbc.default.connectionTransactionIsolation=TRANSACTION_READ_UNCOMMITTED
    jdbc.default.idleTime=1800000
    jdbc.default.numberOfConnectionsToInitializeWith=10

The following properties will be read by Tomcat JDBC Connection Pool if Liferay is configured to use Tomcat JDBC Connection Pool in the property `jdbc.default.liferay.pool.provider`. See [http://people.apache.org/~fhanik/jdbc-pool/jdbc-pool.html](http://people.apache.org/~fhanik/jdbc-pool/jdbc-pool.html) for a list of additional fields used by Tomcat JDBC Connection Pool for configuring database connections.

    jdbc.default.jdbcInterceptors=org.apache.tomcat.jdbc.pool.interceptor.ConnectionState
    jdbc.default.jmxEnabled=true
    jdbc.default.fairQueue=true
    jdbc.default.timeBetweenEvictionRunsMillis=30000
    jdbc.default.useEquals=false

## Custom SQL [](id=custom-sql)

Input a list of comma delimited custom SQL configurations. Liferay Administrators should never need to customize this; this is more of an option for developers who are customizing Liferay's behavior.

	custom.sql.configs=custom-sql/default.xml

Some databases do not recognize a NULL IS NULL check. Set the `custom.sql.function.isnull` and `custom.sql.function.isnotnull` properties for your specific database.

There is no need to manually set these properties because `com.liferay.portal.spring.PortalHibernateConfiguration` already sets it. These properties are available, however, so that you can see how you can override it for a database that `PortalHibernateConfiguration` does not yet know how to auto configure.

### DB2 [](id=lp-6-1-ugen14-db2-0)

	custom.sql.function.isnull=CAST(? AS VARCHAR(32672)) IS NULL
	custom.sql.function.isnotnull=CAST(? AS VARCHAR(32672)) IS NOT NULL
	
### MySQL (for testing only) [](id=lp-6-1-ugen14-mysql-for-testing-only-0)

	custom.sql.function.isnull=IFNULL(?, '1') = '1'
	custom.sql.function.isnotnull=IFNULL(?, '1') = '0'

### Sybase [](id=lp-6-1-ugen14-sybase-0)

	custom.sql.function.isnull=CONVERT(VARCHAR,?) IS NULL
	custom.sql.function.isnotnull=CONVERT(VARCHAR,?) IS NOT NULL	

### Database [](id=lp-6-1-ugen14-database-0)

Specify any database vendor specific settings.

## MySQL [](id=mysql)

Set the MySQL engine type. InnoDB is the recommended engine because it provides transactional integrity.

	database.mysql.engine=InnoDB
	
## Transaction [](id=transaction)

Set the transaction isolation level for the Counter service. See `transaction.isolation.portal` for more information. The preferred value is `8` when the transaction manager supports it. If the transaction manager does not support it, (i.e. when using JPA and JpaTransactionManager) set this to `-1` to use the database's default isolation level.

	transaction.isolation.counter=8

Set the definition of the `portal` transaction isolation level. The `portal` transaction isolation level is not a real isolation level. It is just a pointer to a real isolation level that can be configured by setting this property. Set the value to `-1` to use the database's default isolation level. Set the value to `2` to use `read committed`. Set the value to `1` to use `read uncommitted`. Set the value to `4` to use `repeatable read`. Set the value to `8` to use `serializable`.

	transaction.isolation.portal=2

Set the transaction manager. It must be a class that extends `org.springframework.transaction.support.AbstractPlatformTransactionManager`.

The application server specific transaction managers provide XA transactions by leveraging application server specific data sources and thus require additional application server specific configuration. You should not modify this unless you know what you're doing.

*Examples:*

	transaction.manager.impl=org.springframework.orm.hibernate3.HibernateTransactionManager
	transaction.manager.impl=org.springframework.transaction.jta.JtaTransactionManager
	transaction.manager.impl=org.springframework.transaction.jta.OC4JJtaTransactionManager
	transaction.manager.impl=org.springframework.transaction.jta.WebLogicJtaTransactionManager
	transaction.manager.impl=org.springframework.transaction.jta.WebSphereUowTransactionManager

Additional properties that follow the pattern `transaction.manager.property.\*` will be read to call the setters on the transaction manager. For example, the property `transaction.manager.property.globalRollbackOnParticipationFailure`, will call the setter `setGlobalRollbackOnParticipationFailure` on the transaction manager. The list of available setters depends on the implementation specified in the property `transaction.manager.impl`.

`allowCustomIsolationLevels` should be set to `true` when using the `JtaTransactionManager`.

*Examples:*

	transaction.manager.property.allowCustomIsolationLevels=true
	transaction.manager.property.globalRollbackOnParticipationFailure=false	
	
## Transactional Cache [](id=transactional-cache)

Set this property to `true` to enable transactional cache. When enabled, changes to caches take effect when transactions commit successfully. Caches rollback when transactions rollback.

	transactional.cache.enable=true
	
## Sharded Databases [](id=sharded-databases)

Set the database that is to be used for the default company and globally used tables in a sharded environment.

	shard.default.name=default

Specify an algorithm for selecting a new shard on portal instance creation. Use `ManualShardSelector` for shard selection via the web interface.

*Examples:*

	shard.selector=com.liferay.portal.dao.shard.RoundRobinShardSelector
	shard.selector=com.liferay.portal.dao.shard.ManualShardSelector	

## Ehcache [](id=ehcache)

Set the classpath to the location of the Ehcache config file for internal caches. Edit the file specified in the property `ehcache.multi-vm.config.location` to enable clustered cache.

	ehcache.single.vm.config.location=/ehcache/liferay-single-vm.xml
	ehcache.multi.vm.config.location=/ehcache/liferay-multi-vm-clustered.xml

Uncomment the following in a clustered environment.

	ehcache.multi.vm.config.location.peerProviderProperties=peerDiscovery=automatic,multicastGroupAddress=${multicast.group.address["multi-vm"]},multicastGroupPort=${multicast.group.port["multi-vm"]},timeToLive=1


Input a class name that extends `net.sf.ehcache.bootstrap.BootstrapCacheLoaderFactory`. This class is used by Ehcache to determine how caches communicate with each other in a clustered environment.

*Examples:*

	ehcache.bootstrap.cache.loader.factory=net.sf.ehcache.distribution.RMIBootstrapCacheLoaderFactory
	ehcache.bootstrap.cache.loader.factory=com.liferay.portal.cache.ehcache.JGroupsBootstrapCacheLoaderFactory

Input a class name that extends `net.sf.ehcache.event.CacheEventListenerFactory`. This class is used by Ehcache to determine how caches communicate with each other in a clustered environment.

*Examples:*

	ehcache.cache.event.listener.factory=net.sf.ehcache.distribution.RMICacheReplicatorFactory
	ehcache.cache.event.listener.factory=net.sf.ehcache.distribution.jgroups.JGroupsCacheReplicatorFactory

Input a class name that extends `net.sf.ehcache.distribution.CacheManagerPeerProviderFactory`. This class is used by Ehcache to determine how caches communicate with each other in a clustered environment.

*Examples:*

	ehcache.cache.manager.peer.provider.factory=net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory
	ehcache.cache.manager.peer.provider.factory=net.sf.ehcache.distribution.jgroups.JGroupsCacheManagerPeerProviderFactory

Set this to `true` to enable JMX integration in `com.liferay.portal.cache.EhcachePortalCacheManager`. Set this to `false` if the portal needs to be reloaded under WebSphere.

*Example:*

	ehcache.portal.cache.manager.jmx.enabled=true

Set this to `true` to allow Ehcache to use blocking caches. This improves performance significantly by locking on keys instead of the entire cache. The drawback is that threads can hang if the cache is not used properly. Make sure that all queries that return a miss also immediately populate the cache, or else other threads that are blocked on a query of that same key will continue to hang. Reference Ehcache's BlockingCache for more information. The blocking cache is no longer implemented by Ehcache's BlockingCache, but by Liferay's `BlockingPortalCache` for better safety and faster performance.

	ehcache.blocking.cache.allowed=true

Set this to `true` to enable Ehcache statistics.

	ehcache.statistics.enabled=false

## JavaScript [](id=javascri-3)

Set a list of JavaScript files that will be loaded automatically in `/html/common/themes/top_js.jsp`.

There are two lists of files specified in the properties `javascript.barebone.files` and `javascript.everything.files`.

As the name suggests, the barebone list is the minimum list of JavaScript files required for most cases. The everything list includes everything else not listed in the barebone list.

The two lists of files exist for performance reasons because unauthenticated users usually do not utilize all the JavaScript that is available. See the property `javascript.barebone.enabled` for more information on the logic of when the barebone list is used and when the everything list is used and how to customize that logic.

The list of files are also merged and packed for further performance improvements. See the property `javascript.fast.load` for more details.

Specify the list of barebone files.

The ordering of the JavaScript files is important.

The Liferay scripts are grouped in such a way, that the first grouping denotes utility scripts that are used by the second and third groups. The second grouping denotes utility classes that rely on the first group, but does not rely on the second or third group. The third grouping denotes modules that rely on the first and second group.

    javascript.barebone.files=\
        \
        # 
        # YUI core
        # 
        \
        aui/yui/yui.js,\
        \
        # 
        # YUI modules
        # 
        \
        aui/anim-base/anim-base.js,\
        aui/anim-color/anim-color.js,\
        aui/anim-curve/anim-curve.js,\
        aui/anim-easing/anim-easing.js,\
        aui/anim-node-plugin/anim-node-plugin.js,\
        aui/anim-scroll/anim-scroll.js,\
        aui/anim-xy/anim-xy.js,\
        aui/arraylist-add/arraylist-add.js,\
        aui/arraylist-filter/arraylist-filter.js,\
        aui/arraylist/arraylist.js,\
        aui/array-extras/array-extras.js,\
        aui/array-invoke/array-invoke.js,\
        aui/attribute-base/attribute-base.js,\
        aui/attribute-complex/attribute-complex.js,\
        aui/base-base/base-base.js,\
        aui/base-build/base-build.js,\
        aui/base-pluginhost/base-pluginhost.js,\
        aui/classnamemanager/classnamemanager.js,\
        aui/datatype-xml-format/datatype-xml-format.js,\
        aui/datatype-xml-parse/datatype-xml-parse.js,\
        aui/dom-base/dom-base.js,\
        aui/dom-core/dom-core.js,\
        aui/dom-screen/dom-screen.js,\
        aui/dom-style/dom-style.js,\
        aui/event-base/event-base.js,\
        aui/event-custom-base/event-custom-base.js,\
        aui/event-custom-complex/event-custom-complex.js,\
        aui/event-delegate/event-delegate.js,\
        aui/event-focus/event-focus.js,\
        aui/event-hover/event-hover.js,\
        aui/event-key/event-key.js,\
        aui/event-mouseenter/event-mouseenter.js,\
        aui/event-mousewheel/event-mousewheel.js,\
        aui/event-outside/event-outside.js,\
        aui/event-resize/event-resize.js,\
        aui/event-simulate/event-simulate.js,\
        aui/event-synthetic/event-synthetic.js,\
        aui/intl/intl.js,\
        aui/io-base/io-base.js,\
        aui/io-form/io-form.js,\
        aui/io-queue/io-queue.js,\
        aui/io-upload-iframe/io-upload-iframe.js,\
        aui/io-xdr/io-xdr.js,\
        aui/json-parse/json-parse.js,\
        aui/json-stringify/json-stringify.js,\
        aui/node-base/node-base.js,\
        aui/node-core/node-core.js,\
        aui/node-event-delegate/node-event-delegate.js,\
        aui/node-event-simulate/node-event-simulate.js,\
        aui/node-focusmanager/node-focusmanager.js,\
        aui/node-pluginhost/node-pluginhost.js,\
        aui/node-screen/node-screen.js,\
        aui/node-style/node-style.js,\
        aui/oop/oop.js,\
        aui/overlay/overlay.js,\
        aui/plugin/plugin.js,\
        aui/pluginhost-base/pluginhost-base.js,\
        aui/pluginhost-config/pluginhost-config.js,\
        aui/querystring-stringify-simple/querystring-stringify-simple.js,\
        aui/queue-promote/queue-promote.js,\
        aui/selector-css2/selector-css2.js,\
        aui/selector-css3/selector-css3.js,\
        aui/selector-native/selector-native.js,\
        aui/selector/selector.js,\
        aui/widget-base/widget-base.js,\
        aui/widget-htmlparser/widget-htmlparser.js,\
        aui/widget-position-align/widget-position-align.js,\
        aui/widget-position-constrain/widget-position-constrain.js,\
        aui/widget-position/widget-position.js,\
        aui/widget-skin/widget-skin.js,\
        aui/widget-stack/widget-stack.js,\
        aui/widget-stdmod/widget-stdmod.js,\
        aui/widget-uievents/widget-uievents.js,\
        aui/yui-throttle/yui-throttle.js,\
        \
        # 
        # Alloy core
        # 
        \
        aui/aui-base/aui-base.js,\
        \
        # 
        # Alloy modules
        #
        \
        aui/aui-aria/aui-aria.js,\
        aui/aui-classnamemanager/aui-classnamemanager.js,\
        aui/aui-component/aui-component.js,\
        aui/aui-debounce/aui-debounce.js,\
        aui/aui-delayed-task/aui-delayed-task.js,\
        aui/aui-event/aui-event-base.js,\
        aui/aui-event/aui-event-input.js,\
        aui/aui-form/aui-form-validator.js,\
        aui/aui-live-search/aui-live-search.js,\
        aui/aui-node/aui-node-base.js,\
        aui/aui-node/aui-node-html5-print.js,\
        aui/aui-node/aui-node-html5.js,\
        aui/aui-overlay/aui-overlay-base.js,\
        aui/aui-overlay/aui-overlay-context-panel.js,\
        aui/aui-overlay/aui-overlay-context.js,\
        aui/aui-overlay/aui-overlay-manager.js,\
        aui/aui-overlay/aui-overlay-mask.js,\
        aui/aui-selector/aui-selector.js,\
        aui/aui-task-manager/aui-task-manager.js,\
        \
        #
        # Liferay module definitions
        # 
        \
        liferay/modules.js,\
        \
        # 
        # Liferay base utility scripts
        # 
        \
        liferay/dependency.js,\
        liferay/events.js,\
        liferay/language.js,\
        liferay/liferay.js,\
        liferay/util.js,\
        \
        # 
        # Liferay utility scripts
        # 
        \
        liferay/portal.js,\
        liferay/portlet.js,\
        liferay/portlet_sharing.js,\
        liferay/workflow.js,\
        \
        #
        # Liferay modules
        #
        \
        liferay/form.js,\
        liferay/icon.js,\
        liferay/menu.js,\
        liferay/notice.js,\
        liferay/poller.js

Specify the list of *everything files* (everything else not already in the list of barebone files).

    javascript.everything.files=\
        \
        #
        # YUI modules
        #
        \
        aui/async-queue/async-queue.js,\
        aui/cookie/cookie.js,\
        aui/event-touch/event-touch.js,\
        aui/querystring-stringify/querystring-stringify.js,\
        aui/widget-child/widget-child.js,\
        \
        #
        # Alloy modules
        #
        \
        aui/aui-button-item/aui-button-item.js,\
        aui/aui-io/aui-io-plugin.js,\
        aui/aui-io/aui-io-request.js,\
        aui/aui-loading-mask/aui-loading-mask.js,\
        aui/aui-parse-content/aui-parse-content.js,\
        aui/aui-state-interaction/aui-state-interaction.js,\
        \
        #
        # Liferay modules
        # 
        \
        liferay/address.js,\
        liferay/dockbar.js,\
        liferay/layout_configuration.js,\
        liferay/layout_exporter.js,\
        liferay/session.js,\
        \
        # 
        # Deprecated JS
        # 
        \
        liferay/deprecated.js

Set this property to `false` to always load JavaScript files listed in the property `javascript.everything.files`. Set this to `true` to sometimes load `javascript.barebone.files` and sometimes load `javascript.everything.files`.

The default logic is coded in `com.liferay.portal.events.ServicePreAction` in such a way that unauthenticated users get the list of barebone JavaScript files whereas authenticated users get both the list of barebone JavaScript files and the list of everything JavaScript files.

    javascript.barebone.enabled=true

Set this property to `true` to load the packed version of files listed in the properties `javascript.barebone.files` or `javascript.everything.files`.

Set this property to `false` for easier debugging for development. You can also disable fast loading by setting the URL parameter `js_fast_load` to `0`.

    javascript.fast.load=true

Input a list of comma delimited properties that are a valid bundle ids for the JavaScript minifier.

    javascript.bundle.ids=\
        javascript.barebone.files,\
        javascript.everything.files

Define a bundle directory for each property listed in `javascript.bundle.ids`.

    javascript.bundle.dir[javascript.barebone.files]=/html/js
    javascript.bundle.dir[javascript.everything.files]=/html/js

Define the bundle dependencies using any property listed in `javascript.bundle.ids`.

    javascript.bundle.dependencies[javascript.everything.files]=javascript.barebone.files

Set this to `true` to enable the display of JavaScript logging.

    javascript.log.enabled=false

## SQL Data [](id=sql-data)

Set the default SQL IDs for common objects.

    sql.data.com.liferay.portal.model.Country.country.id=19
    sql.data.com.liferay.portal.model.Region.region.id=5
    sql.data.com.liferay.portal.model.ListType.account.address=10000
    sql.data.com.liferay.portal.model.ListType.account.email.address=10004
    sql.data.com.liferay.portal.model.ListType.contact.email.address=11003
    sql.data.com.liferay.portal.model.ListType.organization.status=12017

Set the maximum parameters that a SQL transaction query can contain. Some databases do not allow for more than 2000 paremters in a query.

    sql.data.max.parameters=1000

## Company [](id=company)

This sets the default web id. Omniadmin users must belong to the company with this web id.

	company.default.web.id=liferay.com

This sets the default home URL of the portal.

	company.default.home.url=/web/guest
	
This sets the default locale of the portal.

    company.default.locale=en_US

This sets the default time zone of the portal.

    company.default.time.zone=GMT

Set this to the appropriate encryption algorithm to be used for company level encryption algorithms (except password encryption which is defined via the property `passwords.encryption.algorithm`).

If you change this setting, you may need to also change the property `company.encryption.key.size` to configure the desired encryption key size.

For more details about encryption keys, see the Java Cryptography Extension documentation.

*Examples:*

	company.encryption.algorithm=AES
	company.encryption.algorithm=ARCFOUR
	company.encryption.algorithm=Blowfish
	company.encryption.algorithm=DES
	company.encryption.algorithm=DESede
	company.encryption.algorithm=HmacMD5
	company.encryption.algorithm=HmacSHA1
	company.encryption.algorithm=HmacSHA256
	company.encryption.algorithm=HmacSHA384
	company.encryption.algorithm=HmacSHA512
	company.encryption.algorithm=RC2

Set this to define the size used for the company wide encryption key. If you change this setting, you may need to also change the property `company.encryption.algorithm` to ensure the algorithm supports your key size. 

For more details about supported key sizes for encryption keys, see the Java Cryptography Extension documentation

*Examples:*

	company.encryption.key.size=56
	company.encryption.key.size=128
	company.encryption.key.size=168
	company.encryption.key.size=256
	company.encryption.key.size=384
	company.encryption.key.size=512

The login field is prepopulated with the company's domain name if the login is unpopulated and user authentication is based on email addresses.
 
Set this to `false` to disable the behavior.

	company.login.prepopulate.domain=true

The portal can authenticate users based on their email address, screen name, or user id.
 
	company.security.auth.type=emailAddress
	company.security.auth.type=screenName
	company.security.auth.type=userId

Set this to `true` to ensure users login with https. If this is set to `true` and you want your HTTP session to contain your credentials after logging in, then the property `session.enable.phishing.protection` must be set to `false` or your credentials will only be available in the HTTPS session.

	company.security.auth.requires.https=false

Set this to `true` to allow users to select the *remember me* feature to automatically login to the portal.

	company.security.auto.login=true

Set this to the maximum age (in number of seconds) of the browser cookie that enables the *remember me* feature. A value of `31536000` signifies lifespan of one year. A value of `-1` signifies a lifespan of a browser session.

Rather than setting this to `0`, set the property `company.security.auto.login` to `false` to disable the "remember me" feature.

	company.security.auto.login.max.age=31536000

Set this to `true` to allow users to ask the portal to send them their password.

	company.security.send.password=true

Set this to `true` to allow users to ask the portal to send them a password reset link.

	company.security.send.password.reset.link=true

Set this to `true` to allow strangers to create accounts and register themselves on the portal.

	company.security.strangers=true

Enter a friendly URL of a page that will be used to create new accounts whenever the user clicks the *create account* link in the login portlet. This allows providing custom portlets to create accounts. By default, the portal's *create account* will be used.

*Example:*

	company.security.strangers.url=/create_account

Set this to `true` if strangers can create accounts with email addresses that match the company mail suffix. This property is not used unless `company.security.strangers` is also set to `true`.

	company.security.strangers.with.mx=true

Set this to `true` if strangers who create accounts need to be verified via email.

	company.security.strangers.verify=false

Set this to `true` to allow site administrators to use their own logo instead of the enterprise logo.

	company.security.site.logo=true

Input a list of sections that will be included as part of the company settings form.
 
	company.settings.form.configuration=general,authentication,users,mail-host-names,email-notifications
	company.settings.form.identification=addresses,phone-numbers,additional-email-addresses,websites
	company.settings.form.miscellaneous=display-settings,google-apps

## Users [](id=users)

Set this to `false` if users cannot be deleted.

	users.delete=true

Set this to `true` when you want the validation to allow for creation of numeric screen names.

	users.screen.name.allow.numeric=false

Set this to `true` to always autogenerate user screen names even if the user gives a specific user screen name. If this is set to `true`, the LDAP importer will fetch users by their email address even if the property `company.security.auth.type` is set to `screenName`.

	users.screen.name.always.autogenerate=false

Input a class name that implements `com.liferay.portal.security.auth.ScreenNameGenerator`. This class will be called to generate user screen names.

	users.screen.name.generator=com.liferay.portal.security.auth.DefaultScreenNameGenerator

Input a class name that implements `com.liferay.portal.security.auth.ScreenNameValidator`. This class will be called to validate user screen names.

*Examples:*

	users.screen.name.validator=com.liferay.portal.security.auth.DefaultScreenNameValidator
	users.screen.name.validator=com.liferay.portal.security.auth.LiberalScreenNameValidator

Set this to `false` if you want to be able to create users without an email address. An email address will be automatically assigned to a user based on the property `users.email.address.auto.suffix`.

	users.email.address.required=true

Set the suffix of the email address that will be automatically generated for a user that does not have an email address. This property is not used unless the property `users.email.address.required` is set to `false`. The autogenerated email address will be the user id plus the specified suffix.

	users.email.address.auto.suffix=@no-emailaddress.com

Input a class name that implements `com.liferay.portal.security.auth.EmailAddressGenerator`. This class will be called to generate an email address for a user that does not specify an email address. This class will only be used if the property `users.email.address.required` is set to `false`.

	users.email.address.generator=com.liferay.portal.security.auth.DefaultEmailAddressGenerator

Set this to `true` if you want the last name to be required when creating users.

	users.last.name.required=false

Input a class name that implements `com.liferay.portal.security.auth.FullNameGenerator`. This class will be called to generate a full name from the user's first, middle and last names.

*Examples:*

	users.full.name.generator=com.liferay.portal.security.auth.DefaultFullNameGenerator
	users.full.name.generator=com.liferay.portal.security.auth.FamilyNameFirstFullNameGenerator

Input a class name that implements `com.liferay.portal.security.auth.FullNameValidator`. This class will be called to validate user first, middle and last names.

	users.full.name.validator=com.liferay.portal.security.auth.DefaultFullNameValidator

Set the maximum file size for user portraits. A value of `0` for the maximum file size can be used to indicate unlimited file size. However, the maximum file size allowed is set in the property `com.liferay.portal.upload.UploadServletRequestImpl.max.size`.

	users.image.max.size=307200

Set the maximum user portrait height and width in pixels. A value of `0` indicates no restictions on user portrait dimensions.

	users.image.max.height=120
	users.image.max.width=100

Set this to `true` to record last login information for a user.

	users.update.last.login=true

Input a list of sections that will be included as part of the user form when adding a user.

	users.form.add.main=details,organizations,personal-site
	users.form.add.identification=
	users.form.add.miscellaneous=

Input a list of sections that will be included as part of the user form when updating a user.

	users.form.update.main=details,password,organizations,sites,user-groups,roles,personal-site,categorization
	users.form.update.identification=addresses,phone-numbers,additional-email-addresses,websites,instant-messenger,social-network,sms,open-id
	users.form.update.miscellaneous=announcements,display-settings,comments,custom-fields

Input a list of sections that will be included as part of the user form when updating a user in the My Account portlet.

	users.form.my.account.main=details,password,organizations,sites,user-groups,roles,categorization
	users.form.my.account.identification=addresses,phone-numbers,additional-email-addresses,websites,instant-messenger,social-network,sms,open-id
	users.form.my.account.miscellaneous=announcements,display-settings,comments,custom-fields

Set the allowed user list views.

	users.list.views=tree,flat-user-groups

Set this to `true` to enable reminder queries that are used to help reset a user's password.

	users.reminder.queries.enabled=true
	users.reminder.queries.custom.question.enabled=true

Set this to `false` to enable users without a reminder query to reset their password.

	users.reminder.queries.required=false

Input a list of questions used for reminder queries.

	users.reminder.queries.questions=what-is-your-primary-frequent-flyer-number,what-is-your-library-card-number,what-was-your-first-phone-number,what-was-your-first-teacher's-name,what-is-your-father's-middle-name

Set this to false to disable the user indexer.

	users.indexer.enabled=true

Set this to `true` to search users from the index. Set this to `false` to search users from the database. Note that setting this to `false` will disable the ability to search users based on Expando attributes. This setting is not used unless the property "users.indexer.enabled" is set to true.

	users.search.with.index=true

Set a property with the prefix `users.update.user.name.` and a suffix with the class name that should be updated whenever a user's name has changed.

	users.update.user.name.com.liferay.portlet.messageboards.model.MBMessage=true

Input a list of user attributes that will be included when exporting users to a CSV file. You can include custom fields by adding the prefix `expando:` to the attribute name.

	users.export.csv.fields=fullName,emailAddress   

## Groups and Roles [](id=groups-and-roles)

Input a list of comma delimited system group names that will exist in addition to the standard system groups. When the server starts, the portal checks to ensure all system groups exist. Any missing system group will be created by the portal.

	system.groups=

Input a list of comma delimited system role names that will exist in addition to the standard system roles. When the server starts, the portal checks to ensure all system roles exist. Any missing system role will be created by the portal.

The standard system roles are: Administrator, Guest, Power User and User. These roles cannot be removed or renamed.

	system.roles=

Set the description of the Administrator system role.

	system.role.Administrator.description=Administrators are super users who can do anything.


Set the description of the Guest system role.

	system.role.Guest.description=Unauthenticated users always have this role.

Set the description of the Owner system role.

	system.role.Owner.description=This is an implied role with respect to the objects users create.

Set the description of the Power User system role.

	system.role.Power.User.description=Power Users have their own personal site.


Set the description of the User system role.

	system.role.User.description=Authenticated users should be assigned this role.


Input a list of comma delimited system organization role names that will exist in addition to the standard system organization roles. When the server starts, the portal checks to ensure all system organization roles exist. Any missing system organization role will be created by the portal.

The standard system organization roles are: Organization Administrator, Organization Member and Organization Owner. These roles cannot be removed or renamed.

	system.organization.roles=

Set the description of the Organization Administrator system organization role.

	system.organization.role.Organization.Administrator.description=Organization Administrators are super users of their organization but cannot make other users into Organization Administrators.

Set the description of the Organization Member system organization role.

	system.organization.role.Organization.Member.description=All users who belong to an organization have this role within that organization.

Set the description of the Organization Owner system organization role.

	system.organization.role.Organization.Owner.description=Organization Owners are super users of their organization and can assign organization roles to users.

Input a list of comma delimited system site role names that will exist in addition to the standard system site roles. When the server starts, the portal checks to ensure all system site roles exist. Any missing system site role will be created by the portal.

The standard system site roles are: Site Administrator, Site Member and Site Owner. These roles cannot be removed or renamed.

	system.site.roles=

Set the description of the Site Administrator system site role.

	system.site.role.Site.Administrator.description=Site Administrators are super users of their site but cannot make other users into Site Administrators.

Set the description of the Site Member system site role.

	system.site.role.Site.Member.description=All users who belong to a site have this role within that site.

Set the description of the Site Owner system site role.

	system.site.role.Site.Owner.description=Site Owners are super users of their site and can assign site roles to users.

Omniadmin users can administer the portal's core functionality: gc, shutdown, etc. Omniadmin users must belong to the default company.

Multiple portal instances might be deployed on one application server and not all of the administrators should have access to this core functionality. Input the ids of users who are omniadmin users.

Leave this field blank if users who belong to the right company and have the Administrator role are allowed to administer the portal's core functionality.

	omniadmin.users=

Set this to `true` if all users are required to agree to the terms of use.

	terms.of.use.required=true

Specify the group id and the article id of the Journal article that will be displayed as the terms of use. The default text will be used if no Journal article is specified.

	terms.of.use.journal.article.group.id=
	terms.of.use.journal.article.id=

Specify subtypes of roles if you want to be able to search for roles using your custom criteria.

	roles.site.subtypes=
	roles.organization.subtypes=
	roles.regular.subtypes=

Set this to `true` when you want the validation to allow for creation of roles with numeric names.

	roles.name.allow.numeric=false

## Organizations [](id=organizations)

Specify the names of your organization(s). For example, you could use Teams, Clubs, Parishes, or anything which describes your hierarchical structure.

	organizations.types=regular-organization,location

	organizations.rootable[regular-organization]=true
	organizations.children.types[regular-organization]=regular-organization,location
	organizations.country.enabled[regular-organization]=false
	organizations.country.required[regular-organization]=false

*Examples:*	
	
	organizations.rootable[location]=false
	organizations.children.types[location]=
	organizations.country.enabled[location]=true
	organizations.country.required[location]=true


Input a list of sections that will be included as part of the organization form when adding an organization.

	organizations.form.add.main=details,organization-site
	organizations.form.add.identification=
	organizations.form.add.miscellaneous=

Input a list of sections that will be included as part of the organization form when updating an organization.

	organizations.form.update.main=details,organization-site,categorization
	organizations.form.update.identification=addresses,phone-numbers,additional-email-addresses,websites,services
	organizations.form.update.miscellaneous=comments,reminder-queries,custom-fields

Set this property to `true` if you want any administrator that creates an organization to be automatically assigned to that organization.

	organizations.assignment.auto=false

Set this property to `false` if you want any administrator of an organization to be able to assign any user to that organization. By default, he will only be able to assign the users of the organizations and suborganizations that he can manage.

	organizations.assignment.strict=true

Set this property to `true` if you want users to only be members of the organizations to which they are assigned explicitly. By default they will also become implicit members of the ancestors of those organizations. for example if a user belongs to Liferay Spain he will implicitly be a member of the ancestors Liferay Europe and Liferay Global and will be able to access their private pages.

	organizations.membership.strict=false

Set this property to `true` to allow user groups to be a member of
organizations.

	organizations.user.group.membership.enabled=true
	
Set this to false to disable the organization indexer.

	organizations.indexer.enabled=true

Set this to `true` to search organizations from the index. Set this to `false` to search organizations from the database. Note that setting this to `false` will disable the ability to search organizations based on Expando attributes. This setting is not used unless the property `organizations.indexer.enabled` is set to `true`.

	organizations.search.with.index=true
	
## User Groups [](id=user-groups)
    
Set this to `true` when you want the validation to allow for creation of user groups with numeric names.
    
    user.groups.name.allow.numeric=false

## Languages and Time Zones [](id=languages-and-time-zones)

Specify the available locales. Messages corresponding to a specific language are specified in properties files with file names matching that of `content/Language_*.properties`. These values can also be overridden in properties files with file names matching that of `content/Language-ext_*.properties`. Use a comma to separate each entry.

If there is more than one locale for the same language, the first one in the list will be used when a translation is requested in another locale of the same language but a translated value cannot be found. For example, if there are two locales such as `pt_BR` and `pt_PT` (in this order), any key not found in `pt_PT` will be looked for in `pt_BR`.

All locales must use UTF-8 encoding.

See the following links specifiy language and country codes:
-   [http://ftp.ics.uci.edu/pub/ietf/http/related/iso639.txt](http://ftp.ics.uci.edu/pub/ietf/http/related/iso639.txt)
-   [http://userpage.chemie.fu-berlin.de/diverse/doc/ISO_3166.html](http://userpage.chemie.fu-berlin.de/diverse/doc/ISO_3166.html)

    locales=ar_SA,eu_ES,bg_BG,ca_AD,ca_ES,zh_CN,zh_TW,hr_HR,cs_CZ,nl_NL,nl_BE,en_US,en_GB,et_EE,fi_FI,fr_FR,gl_ES,de_DE,el_GR,iw_IL,hi_IN,hu_HU,in_ID,it_IT,ja_JP,ko_KR,nb_NO,fa_IR,pl_PL,pt_BR,pt_PT,ro_RO,ru_RU,sr_RS,sr_RS_latin,sl_SI,sk_SK,es_ES,sv_SE,tr_TR,uk_UA,vi_VN

Specify the locales that are in beta. Go to [http://translate.liferay.com](http://translate.liferay.com) to participate in the translation efforts.

	locales.beta=ar_SA,eu_ES,bg_BG,ca_AD,ca_ES,zh_TW,hr_HR,cs_CZ,nl_NL,nl_BE,en_GB,et_EE,fi_FI,fr_FR,gl_ES,el_GR,iw_IL,hi_IN,in_ID,it_IT,ja_JP,ko_KR,nb_NO,fa_IR,pl_PL,pt_PT,ro_RO,ru_RU,sr_RS,sr_RS_latin,sl_SI,sk_SK,sv_SE,tr_TR,uk_UA,vi_VN

Set this to `true` if unauthenticated users get their preferred language
from the Accept-Language header. Set this to `false` if unauthenticated
users get their preferred language from their company.

	locale.default.request=false


Set this to `0` if the locale is not automatically prepended to a URL. This means that each URL could potentially point to many different languages. For example, the URL [http://localhost:8080/web/guest/home](http://localhost:8080/web/guest/home) could then be viewed by users in many different languages.

Set this to `1` if the locale is automatically prepended to a URL when the requested locale is not the default locale. This means that each URL points to just one language. For example, the URL [http://localhost:8080/web/guest/home](http://localhost:8080/web/guest/home) would point to the default language. The URL [http://localhost:8080/zh/web/guest/home](http://localhost:8080/zh/web/guest/home) and
[http://localhost:8080/zh_CN/web/guest/home](http://localhost:8080/zh_CN/web/guest/home) would both point to the Chinese language.

In cases where the prepended locale is `zh` and not complete locale `zh_CN`, then the full locale returned will be based on the order in which the locales appear in the property `locales`. If `zh_CN` appears before `zh_TW`, then `zh` will be a short hand for `zh_TW`.

The default language is set in system.properties with the properties `user.country` and `user.language`.

Set this to `2` if the locale is automatically prepended to every URL. This means that each URL points to just one language.

Note that each language requires an entry in the property `locales` and a servlet mapping in `web.xml` for the `I18n Servlet`.

	locale.prepend.friendly.url.style=1

Set this to `true` to disable language translations. When a translation is requested for the key `first-name`, instead of returning "First Name" in English (or in its relevant locale), it will return `first-name`.

	translations.disabled=false

Specify the available time zones. The specified ids must match those from the class `java.util.TimeZone`.

	time.zones=\
        Pacific/Midway,\
        Pacific/Honolulu,\
        America/Anchorage,\
        America/Los_Angeles,\
        America/Denver,\
        America/Chicago,\
        America/New_York,\
        America/Caracas,\
        America/Puerto_Rico,\
        America/St_Johns,\
        America/Sao_Paulo,\
        America/Noronha,\
        Atlantic/Azores,\
        UTC,\
        Europe/Lisbon,\
        Europe/Paris,\
        Europe/Istanbul,\
        Asia/Jerusalem,\
        Asia/Baghdad,\
        Asia/Tehran,\
        Asia/Dubai,\
        Asia/Kabul,\
        Asia/Karachi,\
        Asia/Calcutta,\
        Asia/Katmandu,\
        Asia/Dhaka,\
        Asia/Rangoon,\
        Asia/Saigon,\
        Asia/Shanghai,\
        Asia/Tokyo,\
        Asia/Seoul,\
        Australia/Darwin,\
        Australia/Sydney,\
        Pacific/Guadalcanal,\
        Pacific/Auckland,\
        Pacific/Enderbury,\
        Pacific/Kiritimati
        
## Look and Feel [](id=look-and-feel)

Set this to `false` if the system does not allow users to modify the look and feel.

	look.and.feel.modifiable=true

Set the default layout template id.

	default.layout.template.id=2_columns_ii


Set the default theme id for regular themes.

	default.regular.theme.id=classic

Set the default color scheme id for regular themes.

	default.regular.color.scheme.id=01

Set the default theme id for wap themes.

	default.wap.theme.id=mobile

Set the default color scheme id for wap themes.

	default.wap.color.scheme.id=01

Set this to `true` if you want a change in the theme selection of the public or private group to automatically be applied to the other (i.e. if public and private group themes should always be the same).

	theme.sync.on.group=false
	
## Request [](id=request)

Portlets that have been configured to use private request attributes in `liferay-portlet.xml` may still want to share some request attributes. This property allows you to configure which request attributes will be shared. Set a comma delimited list of attribute names that will be shared when the attribute name starts with one of the specified attribute names. For example, if you set the value to `hello_,world_`, then all attribute names that start with `hello_` or `world_` will be shared.

	request.shared.attributes=LIFERAY_SHARED_

## Session [](id=session)

Specify the delimiter for parsing compound session ids.

This addresses an issue with Weblogic and all application servers where the application server appends a unique JVM code to the session id. See [http://issues.liferay.com/browse/LPS-18587](LPS-18587).

<!--  This link above appear to be broken.  -->

Set a blank delimiter for the portal to attempt to detect a delimiter based on the application server.

	session.id.delimiter=
	session.id.weblogic.delimiter=!

Specify the number of minutes before a session expires. This value is always overridden by the value set in `web.xml`.

	session.timeout=30

Specify the number of minutes before a warning is sent to the user informing the user of the session expiration. Specify 0 to disable any warnings.
 
	session.timeout.warning=1

Set the auto-extend mode to `true` to avoid having to ask the user whether to extend the session or not. Instead it will be automatically extended. The purpose of this mode is to keep the session open as long as the user browser is open and with a portal page loaded. It is recommended to use this setting along with a smaller `session.timeout`, such as 5 minutes for better performance.

	session.timeout.auto.extend=false

Set this to `true` if the user is redirected to the default page when the session expires.

	session.timeout.redirect.on.expire=false

Portlets that have been configured to use private session attributes in `liferay-portlet.xml` may still want to share some session attributes. This property allows you to configure which session attributes will be shared.

Set a comma delimited list of attribute names that will be shared when the attribute name starts with one of the specified attribute names. For example, if you set the value to `hello_,world_`, then all attribute names that start with `hello_` or `world_` will be shared.

Note that this property is used to specify the sharing of session attributes from the portal to the portlet. This is not used to specify session sharing between portlet WARs or from the portlet to the portal.

	session.shared.attributes=COMPANY_,LIFERAY_SHARED_,org.apache.struts.action.LOCALE,PORTLET_RENDER_PARAMETERS_,PUBLIC_RENDER_PARAMETERS_POOL_,USER_

Explicitly exclude attributes that are shared from the portal to portlets.

	session.shared.attributes.excludes=USER_PASSWORD

Set this to `true` to store the user's password in the session.

	session.store.password=false

Set this to `false` to disable all persistent cookies. Features like automatically logging in will not work.

	session.enable.persistent.cookies=true

Set this to `true` to enable sessions when cookies are disabled. See [http://issues.liferay.com/browse/LEP-4787](LEP-4787). This behavior is configurable because enabling it can break certain setups.

<!--  This link above appear to be broken.  -->

	session.enable.url.with.session.id=true

The login process sets several cookies if persistent cookies are enabled. Set this property to set the domain of those cookies.

*Example:*

	session.cookie.domain=

Set this to `true` to invalidate the session when a user logs into the portal. This helps prevents phishing. Set this to `false` if you need the guest user and the authenticated user to have the same session.

Set this to `false` if the property `company.security.auth.requires.https` is set to `true` and you want to maintain the same credentials across HTTP and HTTPS sessions.

	session.enable.phishing.protection=true

Set a comma delimited list of attribute names that will be copied to the new session when the property `session.enable.phishing.protection` is set to `true`.

	session.phishing.protected.attributes=HTTPS_INITIAL,LAST_PATH
	
Set this to `true` to test whether users have cookie support before allowing them to sign in. This test will always fail if `tck.url` is set to `true` because that property disables session cookies.

	session.test.cookie.support=true

Set this to `true` to allow
`com.liferay.portal.kernel.servlet.SerializableSessionAttributeListener` to test if an added session variable is serializable. Unserializable values stored into the session will cause a session to not replicate if session replication was enabled by the application server.

	session.verify.serializable.attribute=true

Set this to `true` to disable sessions. Doing this will use cookies to remember the user across requests. This is useful if you want to scale very large sites where the user may be sent to a different server for each request. The drawback to this approach is that you must not rely on the API for sessions provided by the servlet and portlet specs.

This feature is only available for Tomcat and requires that you set Tomcat's Manager class to `com.liferay.support.tomcat.session.SessionLessManagerBase`.

	session.disabled=false

Input a list of comma delimited class names that extend `com.liferay.portal.struts.SessionAction`. These classes will run at the specified event.

Servlet session create event

	servlet.session.create.events=com.liferay.portal.events.SessionCreateAction

Servlet session destroy event

	servlet.session.destroy.events=com.liferay.portal.events.SessionDestroyAction,com.liferay.portal.events.ChannelSessionDestroyAction

Set this to `true` to track user clicks in memory for the duration of a user's session. Setting this to `true` allows you to view all live sessions in the Admin portlet.

	session.tracker.memory.enabled=true

Set this to `true` to track user clicks in the database after a user's session is invalidated. Setting this to `true` allows you to generate usage reports from the database. Use this cautiously because this will store a lot of usage data.

	session.tracker.persistence.enabled=false

Set this to `true` to convert the tracked paths to friendly URLs.

	session.tracker.friendly.paths.enabled=false
	
Enter a list of comma delimited paths that should not be tracked.

	session.tracker.ignore.paths=\
		/portal/render_portlet,\
		\
		/document_library/get_file

## JAAS [](id=jaas)

Set this to false to disable JAAS security checks. Disabling JAAS speeds up login. JAAS must be disabled if administrators are to be able to impersonate other users.

	portal.jaas.enable=false

JAAS can authenticate users based on their email address, screen name, user id, or login as determined by the property `company.security.auth.type`.

*Examples:*

	portal.jaas.auth.type=emailAddress
	portal.jaas.auth.type=screenName
	portal.jaas.auth.type=userId
	portal.jaas.auth.type=login

By default, `com.liferay.portal.security.jaas.PortalLoginModule` loads the correct JAAS login module based on what application server or servlet container the portal is deployed on. Set a JAAS implementation class to override this behavior.

*Example:*

	portal.jaas.impl=

The JAAS process may pass in an encrypted password and the authentication will only succeed if there is an exact match. Set this property to `false` to relax that behavior so the user can input an unencrypted password.

	portal.jaas.strict.password=false

Set this to `true` to enable administrators to impersonate other users.

	portal.impersonation.enable=true

## Security Manager [](id=security-manager)

Set this property to `default` to use the default security manager configured by the application server. A security manager will not be used if the application server did not configure one.

Set this property to `liferay` to use Liferay's security manager if the application server has not configured a security manager.

Set this property to `none` to explicitly remove the security manager regardless of whether one is configured. This ensures that the portal is run in a JVM without a security manager.

Set this property to `smart` to allow the portal decide which strategy to use based on which application server it is on.

	portal.security.manager.strategy=smart

## LDAP [](id=ldap)

Set the values used to connect to a LDAP store.

	ldap.factory.initial=com.sun.jndi.ldap.LdapCtxFactory
	ldap.referral=follow

Settings for `com.liferay.portal.security.auth.LDAPAuth` can be configured from the Admin portlet. It provides out-of-the box support for Apache Directory Server, Microsoft Active Directory Server, Novell eDirectory and OpenLDAP. The default settings are for Apache Directory Server.

The `LDAPAuth` class must be specified in the property `auth.pipeline.pre` to be executed.

Encryption is implemented by `com.liferay.util.Encryptor.provider.class` in `system.properties`.

	ldap.auth.enabled=false
	ldap.auth.required=false

Set the page size for directory servers that support paging. This value needs to be `1000` or less for Microsoft Active Directory Server.

	ldap.page.size=1000

Set the number of values to return in each query to a multivalued attribute for directory servers that support range retrieval. The range size must be `1000` or less for Windows 2000 and `1500` or less for Windows Server 2003.

	ldap.range.size=1000

Set either `bind` or `password-compare` for the LDAP authentication method. Bind is preferred by most vendors so that you don't have to worry about encryption strategies.

*Examples:*

	ldap.auth.method=bind
	ldap.auth.method=password-compare

Set the password encryption to used to compare passwords if the property `ldap.auth.method` is set to `password-compare`. If set to `NONE`, which is the default value, passwords are stored in the database as plain text. The SHA-512 algorithm is currently unsupported.

*Examples:*

	ldap.auth.password.encryption.algorithm=BCRYPT
	ldap.auth.password.encryption.algorithm=MD2
	ldap.auth.password.encryption.algorithm=MD5
	ldap.auth.password.encryption.algorithm=NONE
	ldap.auth.password.encryption.algorithm=SHA
	ldap.auth.password.encryption.algorithm=SHA-256
	ldap.auth.password.encryption.algorithm=SHA-384
	ldap.auth.password.encryption.algorithm=SSHA
	ldap.auth.password.encryption.algorithm=UFC-CRYPT

You can write your own class that implements
`com.liferay.portal.security.ldap.AttributesTransformer` to transform the LDAP attributes before a user or group is imported to the LDAP store.

	ldap.attrs.transformer.impl=com.liferay.portal.security.ldap.DefaultAttributesTransformer

Settings for importing users and groups from LDAP to the portal.

	ldap.import.enabled=false
	ldap.import.on.startup=false
	ldap.import.interval=10

Set either `user` or `group` for import method. If set to `user`, the portal will import all users and the groups associated with those users. If set to `group`, the portal import all groups and the users associated those groups. This value should be set based on how your LDAP server stores group membership information.

*Examples:*

	ldap.import.method=user
	ldap.import.method=group

If set to `true`, the group filter will be applied, but only to groups in the specified base DN. If set to `false`, the filter will not be applied and all groups that are associated with the imported users will be imported regardless of the base DN.

	ldap.import.group.search.filter.enabled=true

Specify whether group DN lookups will be cached during LDAP import and login. If set to `true`, this will speed up LDAP import and login, but updates to group attributes will not be recognized until the cache entry expires. The cache size and timeout may be configured in the configuration file specifed in the property `ehcache.single.vm.config.location`.

	ldap.import.group.cache.enabled=true

Set this to `true` if the portal should automatically create a role per group imported from LDAP. The role will be assigned to the group so that users can automatically inherit that role when they are assigned to the group.

	ldap.import.create.role.per.group=false


Set these values to be a portion of the error message returned by the appropriate directory server to allow the portal to recognize messages from the LDAP server. The default values will work for Fedora DS.

	ldap.error.password.age=age
	ldap.error.password.expired=expired
	ldap.error.password.history=history
	ldap.error.password.not.changeable=not allowed to change
	ldap.error.password.syntax=syntax
	ldap.error.password.trivial=trivial
	ldap.error.user.lockout=retry limit

Set this to `true` to use the LDAP's password policy instead of the portal password policy.

	ldap.password.policy.enabled=false

Set this to `false` when the LDAP user's password should not be imported.

	ldap.import.user.password.enabled=true

Set this to `true` to autogenerate the password for imported users from LDAP. This property is only in use if the property `ldap.import.user.password.enabled` is set to `false`.

	ldap.import.user.password.autogenerated=false

Set either `screenName` or plain text as the default password for the imported LDAP user. Setting the value to `screenName` will use the user's screen name as the password for the imported LDAP user. Setting the value to any other plain text value will use that value as the password for the imported LDAP user. This property is only in use if the properties `ldap.import.user.password.enabled` and `ldap.import.user.password.autogenerated` are both set to `false`.

*Examples:*

	ldap.import.user.password.default=test
	ldap.import.user.password.default=screenName

Settings for exporting users from the portal to LDAP. This allows a user to modify his first name, last name, etc. in the portal and have that change pushed to the LDAP server. This setting is not used unless the property `ldap.auth.enabled` is set to `true`.

	ldap.export.enabled=false

Set this to `true` if groups and their associations should be exported from the portal to LDAP. This setting is not used unless the property `ldap.auth.enabled` is set to `true`.

	ldap.export.group.enabled=true

Set the values used to connect to a LDAP store.

The list of properties must end with a subsequent integer (`0`, `1`, etc.) and it is assumed that the list has reached an end when the pattern or replacement is not set.

	ldap.base.provider.url.0=ldap://localhost:10389
	ldap.base.dn.0=dc=example,dc=com
	ldap.security.principal.0=uid=admin,ou=system
	ldap.security.credentials.0=secret

Active Directory stores information about the user account as a series of bit fields in the `UserAccountControl` attribute.

If you want to prevent disabled accounts from logging into the portal you need to use a search filter similiar to the following:

	(&(objectclass=person)(userprincipalname=@email_address@)(!(UserAccountControl:1.2.840.113556.1.4.803:=2)))

See the following links:

-   [http://support.microsoft.com/kb/305144/](http://support.microsoft.com/kb/305144/)
-   [http://support.microsoft.com/?kbid=269181](http://support.microsoft.com/?kbid=269181)

*Example:*

	ldap.auth.search.filter.0=(mail=@email_address@)

When importing and exporting users, the portal will use this mapping to connect LDAP user attributes and portal contact attributes.

See `com.liferay.portal.model.ContactModel` for a list of attributes.

*Example:*

	ldap.contact.mappings.0=

When importing and exporting users, the portal will use this mapping to connect LDAP user attributes and portal contact's custom attributes.

*Example:*

	ldap.contact.custom.mappings.0=

When a user is exported to LDAP and the user does not exist, the user will be created with the following default object classes.

*Example:*

	ldap.user.default.object.classes.0=top,person,inetOrgPerson,organizationalPerson

When importing and exporting users, the portal will use this mapping to connect LDAP user attributes and portal user attributes.

See `com.liferay.portal.model.UserModel` for a list of attributes.

*Example:*

	ldap.user.mappings.0=uuid=uuid\nscreenName=cn\npassword=userPassword\nemailAddress=mail\nfirstName=givenName\nlastName=sn\njobTitle=title\ngroup=groupMembership

When importing and exporting users, the portal will use this mapping to connect LDAP user attributes and portal user's custom attributes.

*Example:*

	ldap.user.custom.mappings.0=

Set the user attributes that are controlled from the portal. When adding or updating a user from LDAP, these attributes will be skipped.

*Examples:*

	ldap.user.ignore.attributes=
	ldap.user.ignore.attributes=aimSn,comments,facebookId,facebookSn,greeting,icqSn,jabberSn,jobTitle,languageId,msnSn,mySpaceSn,openId,prefixId,reminderQueryAnswer,reminderQueryQuestion,skypeSn,smsSn,suffixId,timeZoneId,twitterSn,ymSn

When a group is exported to LDAP and the group does not exist, the group will be created with the following default object classes.

*Example:*

	ldap.group.default.object.classes.0=top,groupOfUniqueNames

When importing groups, the portal will use this mapping to connect LDAP group attributes and portal user group attributes.

*Example:* 

	ldap.group.mappings.0=groupName=cn\ndescription=description\nuser=uniqueMember

Settings for importing users and groups from LDAP to the portal. These setttings are not used unless the property `ldap.import.enabled` is set to `true`.

	ldap.import.user.search.filter.0=(objectClass=inetOrgPerson)
	ldap.import.group.search.filter.0=(objectClass=groupOfUniqueNames)

New users and groups will be created at the specified DN. This will only be active if the property `ldap.auth.enabled` and `ldap.export.enabled` are set to `true`.

	ldap.users.dn.0=ou=users,dc=example,dc=com
	ldap.groups.dn.0=ou=groups,dc=example,dc=com
	
## Basic Authentication [](id=basic-authentication)

Set this to `true` to require a password when using basic authentication. Only set this to `false` if additional security measures are in place to ensure users have been properly authenticated.

	basic.auth.password.required=true	

## CAS [](id=cas)

Set this to `true` to enable CAS single sign on. NTLM will work only if LDAP authentication is also enabled and the authentication is made by screen name. If set to `true`, then the property `auto.login.hooks` must contain a reference to the class `com.liferay.portal.security.auth.CASAutoLogin` and the filter `com.liferay.portal.servlet.filters.sso.cas.CASFilter` must be referenced in `web.xml`.

	cas.auth.enabled=false

A user may be authenticated from CAS and not yet exist in the portal. Set this to `true` to automatically import users from LDAP if they do not exist in the portal.

	cas.import.from.ldap=false

Set the default values for the required CAS URLs. Set either `cas.server.name` or `cas.service.url`. Setting `cas.server.name` allows deep linking. See [http://issues.liferay.com/browse/LEP-4423](LEP-4423).

<!-- This link above appears to be broken.  -->

*Examples:*

	cas.login.url=https://localhost:8443/cas-web/login
	cas.logout.url=https://localhost:8443/cas-web/logout
	cas.server.name=localhost:8080
	cas.server.url=https://localhost:8443/cas-web
	cas.service.url=
	cas.service.url=http://localhost:8080/c/portal/login
	cas.no.such.user.redirect.url=http://localhost:8080

Set this to `true` to log out the user from CAS when the portal session expires.

	cas.logout.on.session.expiration=false

## Facebook Connect SSO [](id=facebook-connect-sso)

    facebook.connect.auth.enabled=false
    facebook.connect.app.id=
    facebook.connect.app.secret=
    facebook.connect.graph.url=https://graph.facebook.com
    facebook.connect.oauth.auth.url=https://graph.facebook.com/oauth/authorize
    facebook.connect.oauth.redirect.url=http://localhost:8080/c/login/facebook_connect_oauth
    facebook.connect.oauth.token.url=https://graph.facebook.com/oauth/access_token	
	
## NTLM [](id=ntlm)

Set this to `true` to enable NTLM single sign on. NTLM will work only if LDAP authentication is also enabled and the authentication is made by screen name. If set to `true`, then the property `auto.login.hooks` must contain a reference to the class `com.liferay.portal.security.auth.NtlmAutoLogin` and the filter `com.liferay.portal.servlet.filters.sso.ntlm.NtlmFilter` must be referenced in `web.xml`.

Negotiate flags are set according to the the client's requested capabilities and the server's ServerCapabilities.

See the following link: [http://msdn.microsoft.com/en-us/library/cc717152%28v=PROT.10%29.aspx](http://msdn.microsoft.com/en-us/library/cc717152%28v=PROT.10%29.aspx)

	ntlm.auth.enabled=false
	ntlm.auth.domain.controller=127.0.0.1
	ntlm.auth.domain.controller.name=EXAMPLE
	ntlm.auth.domain=EXAMPLE
	ntlm.auth.negotiate.flags=0x600FFFFF
	ntlm.auth.service.account=LIFERAY$@EXAMPLE.COM
	ntlm.auth.service.password=test


See [http://jcifs.samba.org/src/docs/ntlmhttpauth.html](http://jcifs.samba.org/src/docs/ntlmhttpauth.html) for more available properties.

	jcifs.netbios.cachePolicy=30
	jcifs.smb.client.soTimeout=35000

## OpenID [](id=openid)

Set this to `true` to enable OpenId authentication. If set to `true`, then the property `auto.login.hooks` must contain a reference to the class `com.liferay.portal.security.auth.OpenIdAutoLogin`.

	open.id.auth.enabled=true

## OpenSSO [](id=opensso)

These properties control Liferay's integration with OpenSSO.

Set this to `true` to enable OpenSSO authentication.

	open.sso.auth.enabled=false

Set this to `true` if you want to import the users from LDAP after successful login through OpenSSO. The LDAP settings need to be configured properly in the LDAP section. If this is set to `false`, the users will be created from OpenSSO provided data.

	open.sso.ldap.import.enabled=false

	open.sso.login.url=http://openssohost.example.com:8080/opensso/UI/Login?goto=http://portalhost.example.com:8080/c/portal/login
	open.sso.logout.url=http://openssohost.example.com:8080/opensso/UI/Logout?goto=http://portalhost.example.com:8080/web/guest/home
	open.sso.service.url=http://openssohost.example.com:8080/opensso
	open.sso.screen.name.attr=uid
	open.sso.email.address.attr=mail
	open.sso.first.name.attr=givenname
	open.sso.last.name.attr=sn

Set this to `true` to log out the user off OpenSSO when the portal session expires.

	open.sso.logout.on.session.expiration=false
	
## Request Header Authentication [](id=request-header-authentication)

Set this to `true` to automatically import users from LDAP if they do not exist in the portal. The property `auto.login.hooks` must contain a reference to the class `com.liferay.portal.security.auth.RequestHeaderAutoLogin` to enable request header authentication.

	request.header.auth.import.from.ldap=false	

## SiteMinder [](id=siteminder)

Set this to `true` to enable CA SiteMinder single sign on. If set to `true`, then the property `auto.login.hooks` must contain a reference to the class `com.liferay.portal.security.auth.SiteMinderAutoLogin` and the `logout.events.post` must have a reference to `com.liferay.portal.events.SiteMinderLogoutAction` for logout to work.

	siteminder.auth.enabled=false

A user may be authenticated from SiteMinder and not yet exist in the portal. Set this to `true` to automatically import users from LDAP if they do not exist in the portal.

	siteminder.import.from.ldap=false

Set this to the name of the user header that SiteMinder passes to the portal.

	siteminder.user.header=SM_USER

## Authentication Pipeline [](id=authentication-pipeline)

Input a list of comma delimited class names that implement `com.liferay.portal.security.auth.Authenticator`. These classes will run before or after the portal authentication begins.

The `Authenticator` class defines the constant values that should be used as return codes from the classes implementing the interface. If authentication is successful, return `SUCCESS`; if the user exists but the passwords do not match, return `FAILURE`; and if the user does not exist on the system, return `DNE`.

Constants in Authenticator:

    public static final int SUCCESS = 1;
    public static final int FAILURE = -1;
    public static final int DNE = 0;

In case you have several classes in the authentication pipeline, all of them have to return `SUCCESS` if you want the user to be able to login. If one of the authenticators returns `FAILURE` or `DNE`, the login fails.

Under certain circumstances, you might want to keep the information in the portal database in sync with an external database or an LDAP server. This can easily be achieved by implementing a class via `LDAPAuth` that updates the information stored in the portal user database whenever a user signs in.

Each portal instance can be configured at run time to either authenticate based on user ids or email addresses. See the Admin portlet for more information.

Available authenticators are:

    com.liferay.portal.security.auth.LDAPAuth

See the LDAP properties to configure the behavior of the `LDAPAuth` class.

*Examples:*

	auth.pipeline.pre=com.liferay.portal.security.auth.LDAPAuth
	auth.pipeline.post=

Set this to `true` to enable password checking by the internal portal authentication. If set to `false`, you're essentially delegating password checking to the authenticators configured in `auth.pipeline.pre` and `auth.pipeline.post` settings.

	auth.pipeline.enable.liferay.check=true

Input a list of comma delimited class names that implement `com.liferay.portal.security.auth.AuthFailure`. These classes will run when a user has a failed login or when a user has reached the maximum number of failed logins.

	auth.failure=com.liferay.portal.security.auth.LoginFailure
	auth.max.failures=com.liferay.portal.security.auth.LoginMaxFailures

Set the following to `true` if users are allowed to have simultaneous logins from different sessions. This property is not used unless the property `live.users.enabled` is set to `true`.

	auth.simultaneous.logins=true

Set this to `true` if users are forwarded to the last visited path upon successful login. If set to `false`, users will be forwarded to their default layout page.

	auth.forward.by.last.path=true

The login page reads a redirect by a parameter named `redirect`. If this property is set to `true`, then users will be redirected to the given redirect path upon successful login. If the user does not have permission to view that page, then the rule set by the property `auth.forward.by.last.path` will apply.

You can set the redirect manually from another application, by appending the `redirect` parameter in a url that looks like this: `/c/portal/login?redirect=%2Fgroup%2Femployees%2Fcalendar`. This url will redirect the user to the path `/group/employees/calendar` upon successful login.

	auth.forward.by.redirect=true

Enter a list of comma delimited paths that can be considered part of the last visited path.

	auth.forward.last.paths=/document_library/get_file

Enter a URL that will be used to login portal users whenever needed. By default, the portal's login page is used.

*Example:*

	auth.login.url=/web/guest/home

Enter a friendly URL of a page that will be used to login portal users whenever the user is navigating a site and authentication is needed. By default, the portal's login page or the URL set in the property `auth.login.url` is used.

	auth.login.site.url=/login

Enter the name of the login portlet used in a page identified by the URL of the previous property (if one has been set). This will allow the portlet to have access to the redirect parameter and thus forward the user to the page where he was trying to access when necessary. You should leave the default value unless you have your own custom login portlet.

	auth.login.portlet.name=58

Set this to `true` to disable any users from logging into the portal. Preventing users from logging into the portal provides a read-only version of the portal that can be used to minimize site outages during upgrades.

	auth.login.disabled=false
	auth.login.disabled.path=/portal/login_disabled

Enter a list of comma delimited paths that do not require authentication.

	auth.public.paths=\
		/asset/get_categories,\
		\
		/blogs/find_entry,\
		/blogs/rss,\
		/blogs/trackback,\
		\
		/blogs_aggregator/rss,\
		\
		/bookmarks/open_entry,\
		\
		/calendar/find_event,\
		\
		/document_library/find_file_entry,\
		/document_library/find_folder,\
		/document_library/get_file,\
		\
		/flags/edit_flag,\
		\
		/iframe/proxy,\
		\
		/image_gallery_display/find_folder,\
		/image_gallery_display/find_image,\
		\
		/journal/get_article,\
		/journal/get_articles,\
		/journal/get_latest_article_content,\
		/journal/get_structure,\
		/journal/get_template,\
		/journal/rss,\
		/journal/view_article_content,\
		/journal_articles/view_article_content,\
		\
		/layouts_admin/robots,\
		/layouts_admin/sitemap,\
		\
		/login/facebook_connect_oauth,\
		\
		/message_boards/find_category,\
		/message_boards/find_message,\
		/message_boards/find_thread,\
		/message_boards/get_message_attachment,\
		/message_boards/rss,\
		\
		/my_sites/view,\
		\
		/polls/view_chart,\
		\
		/portal/emoticons,\
		/portal/expire_session,\
		/portal/extend_session,\
		/portal/extend_session_confirm,\
		/portal/json_service,\
		/portal/license,\
		/portal/logout,\
		/portal/open_id_request,\
		/portal/open_id_response,\
		/portal/portlet_url,\
		/portal/session_click,\
		/portal/session_tree_js_click,\
		/portal/status,\
		\
		/search/open_search,\
		/search/open_search_description.xml,\
		\
		/shopping/notify,\
		\
		/wiki/find_page,\
		/wiki/get_page_attachment,\
		/wiki/rss
		
## Authentication Token [](id=authentication-token)

Set this to `true` to enable authentication token security checks. The checks can be disabled for specific actions via the property `auth.token.ignore.actions` or for specific portlets via the init parameter `check-auth-token` in `portlet.xml`.

	auth.token.check.enabled=true

Set the authentication token class. This class must implement `com.liferay.portal.security.auth.AuthToken`. This class is used to prevent CSRF attacks. See [http://issues.liferay.com/browse/LPS-8399](http://issues.liferay.com/browse/LPS-8399) for more information.

	auth.token.impl=com.liferay.portal.security.auth.SessionAuthToken

Input a list of comma delimited struts actions that will not be checked for an authentication token.

	auth.token.ignore.actions=\
		/asset/rss,\
		\
		/asset_publisher/edit_article_discussion,\
		/asset_publisher/edit_entry_discussion,\
		/asset_publisher/edit_file_entry_discussion,\
		/asset_publisher/edit_page_discussion,\
		\
		/blogs/edit_entry,\
		/blogs/edit_entry_discussion,\
		/blogs/rss,\
		\
		/blogs_aggregator/edit_entry,\
		/blogs_aggregator/edit_entry_discussion,\
		/blogs_aggregator/rss,\
		\
		/calendar/edit_event_discussion,\
		\
		/document_library/edit_file_entry,\
		/document_library/edit_file_entry_discussion,\
		\
		/document_library_display/edit_file_entry,\
		/document_library_display/edit_file_entry_discussion,\
		\
		/journal/edit_article_discussion,\
		/journal/rss,\
		\
		/journal_content/edit_article_discussion,\
		\
		/image_gallery_display/edit_file_entry,\
		/image_gallery_display/edit_image,\
		\
		/login/login,\
		\
		/message_boards/edit_discussion,\
		/message_boards/edit_message,\
		/message_boards/rss,\
		\
		/my_sites/view,\
		\
		/page_comments/edit_page_discussion,\
		\
		/shopping/edit_order_discussion,\
		\
		/software_catalog/edit_product_entry_discussion,\
		\
		/wiki/edit_page,\
		/wiki/edit_page_attachment,\
		/wiki/edit_page_discussion,\
		/wiki/get_page_attachment,\
		/wiki/rss,\
		\
		/wiki_admin/edit_page_attachment,\
		\
		/wiki_display/edit_page_discussion


Set a list of comma delimited portlet ids that will not be checked for an authentication token.

	auth.token.ignore.portlets=82


Set the shared secret that is used for requests where it is not possible to generate an authentication token (i.e. WSRP).

	auth.token.shared.secret=BAHyWOT9TbPB

## Auto Login [](id=auto-login)

Input a list of comma delimited class names that implement `com.liferay.portal.security.auth.AutoLogin`. These classes will run in consecutive order for all unauthenticated users until one of them returns a valid user id and password combination. If no valid combination is returned, then the request continues to process normally. If a valid combination is returned, then the portal will automatically login that user with the returned user id and password combination.

For example, `com.liferay.portal.security.auth.RememberMeAutoLogin` reads from a cookie to automatically log in a user who previously logged in while checking the *Remember Me* box.

This interface allows deployers to easily configure the portal to work with other SSO servers. See `com.liferay.portal.security.auth.CASAutoLogin` for an example of how to configure the portal with Yale's SSO server.

	auto.login.hooks=com.liferay.portal.security.auth.CASAutoLogin,com.liferay.portal.security.auth.FacebookAutoLogin,com.liferay.portal.security.auth.NtlmAutoLogin,com.liferay.portal.security.auth.OpenIdAutoLogin,com.liferay.portal.security.auth.OpenSSOAutoLogin,com.liferay.portal.security.auth.RememberMeAutoLogin,com.liferay.portal.security.auth.SiteMinderAutoLogin

Set the hosts that will be ignored for auto login.

	auto.login.ignore.hosts=

Set the paths that will be ignored for auto login.

	auto.login.ignore.paths=

## SSO with MAC (Message Authentication Code) [](id=sso-with-mac-message-authentication-code-)

To use SSO with MAC, post to an URL like:

    http://localhost:8080/c/portal/login?cmd=already-registered&login=<userId|emailAddress>&password=<MAC>

Pass the MAC in the password field. Make sure the MAC gets URL encoded because it might contain characters not allowed in a URL.

SSO with MAC also requires that you set the following property in `system.properties`:

    com.liferay.util.servlet.SessionParameters=false

See the following links:

-	[http://issues.liferay.com/browse/LEP-1288](http://issues.liferay.com/browse/LEP-1288)
-	[http://en.wikipedia.org/wiki/Message_authentication_code](http://en.wikipedia.org/wiki/Message_authentication_code)

Set this to `true` to enable SSO with MAC.

	auth.mac.allow=false

Set the algorithm to use for MAC encryption.

	auth.mac.algorithm=MD5

Set the shared key used to generate the MAC.

	auth.mac.shared.key=

## Passwords [](id=passwords)

Set the following encryption algorithm to encrypt passwords. The default algorithm is SHA (SHA-1). If set to `NONE`, passwords are stored in the database as plain text. The SHA-512 algorithm is currently unsupported.

*Examples:*

	passwords.encryption.algorithm=BCRYPT
	passwords.encryption.algorithm=UFC-CRYPT
	passwords.encryption.algorithm=MD2
	passwords.encryption.algorithm=MD5
	passwords.encryption.algorithm=NONE
	passwords.encryption.algorithm=SHA
	passwords.encryption.algorithm=SHA-256
	passwords.encryption.algorithm=SHA-384
	passwords.encryption.algorithm=SSHA

Digested passwords are encoded via base64 or hex encoding. The default is base64.

*Examples:*

	passwords.digest.encoding=base64
	passwords.digest.encoding=hex

Input a class name that extends `com.liferay.portal.security.pwd.BasicToolkit`. This class will be called to generate and validate passwords.

*Examples:*

	passwords.toolkit=com.liferay.portal.security.pwd.PasswordPolicyToolkit
	passwords.toolkit=com.liferay.portal.security.pwd.RegExpToolkit

If you choose to use `com.liferay.portal.security.pwd.PasswordPolicyToolkit` as your password toolkit, you can choose either static or dynamic password generation. Static is set through the property `passwords.passwordpolicytoolkit.static` and dynamic uses the class `com.liferay.util.PwdGenerator` to generate the password. If you are using LDAP password syntax checking, you will also have to use the static generator so that you can guarantee that passwords obey its rules.

*Examples:*

	passwords.passwordpolicytoolkit.generator=static
	passwords.passwordpolicytoolkit.generator=dynamic
	passwords.passwordpolicytoolkit.static=iheartliferay

Set the character sets for password validation.

	passwords.passwordpolicytoolkit.charset.lowercase=abcdefghjkmnpqrstuvwxyz
	passwords.passwordpolicytoolkit.charset.numbers=0123456789
	passwords.passwordpolicytoolkit.charset.symbols=_.!@$*=-?
	passwords.passwordpolicytoolkit.charset.uppercase=ABCDEFGHJKLMNPQRSTUVWXYZ

If you choose to use `com.liferay.portal.security.pwd.RegExpToolkit` as your password toolkit, set the regular expression pattern that will be used to generate and validate passwords.

Note that `\` is replaced with `\\` to work in Java.

The first pattern ensures that passwords must have at least 4 valid characters consisting of digits or letters.

The second pattern ensures that passwords must have at least 8 valid characters consisting of digits or letters.

*Examples:*

	passwords.regexptoolkit.pattern=(?=.{4})(?:[a-zA-Z0-9]*)
	passwords.regexptoolkit.pattern=(?=.{8})(?:[a-zA-Z0-9]*)


Set the length and key for generating passwords.

*Examples:*

	passwords.regexptoolkit.charset=0123456789
	passwords.regexptoolkit.charset=0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz
	passwords.regexptoolkit.length=4
	passwords.regexptoolkit.length=8


Set the name of the default password policy.

	passwords.default.policy.name=Default Password Policy

## Permissions [](id=permissio-4)

Set the default permission checker class used by `com.liferay.portal.security.permission.PermissionCheckerFactory` to check permissions for actions on objects. This class can be overriden with a custom class that implements `com.liferay.portal.security.permission.PermissionChecker`.

*Examples:*

	permissions.checker=com.liferay.portal.security.permission.SimplePermissionChecker
	permissions.checker=com.liferay.portal.security.permission.AdvancedPermissionChecker

Set the algorithm used to check permissions for a user. This is useful so that you can optimize the search for different databases. See `com.liferay.portal.service.impl.PermissionLocalServiceImpl`.

Algorithms 1 through 4 are essentially the same but make calls in different orders depending on how the database is optimized and how the portal permissions are used. Algorithm 5 moves to a completely role based permissions check for better performance. Permissions by users are no longer supported, yet it uses the same table structure as algorithms 1-4.

Algorithm 6 is the current algorithm for Liferay 6 and above. It supports role based permissions like algorithm 5, but does so by using only one table and bitwise operations. This makes it perform far better than the other algorithms.

*Examples:*

	permissions.user.check.algorithm=1
	permissions.user.check.algorithm=2
	permissions.user.check.algorithm=3
	permissions.user.check.algorithm=4
	permissions.user.check.algorithm=5
	permissions.user.check.algorithm=6

Set the default permissions list filter class. This class must implement `com.liferay.portal.kernel.security.permission.PermissionsListFilter`. This is used if you want to filter the list of permissions before it is actually persisted. For example, if you want to make sure that all users who create objects never have the UPDATE action, then you can filter that list and remove any permissions that have the UPDATE action before it is persisted.

	permissions.list.filter=com.liferay.portal.security.permission.PermissionsListFilterImpl

Set this to `true` to configure permission caching to block. See the property `ehcache.blocking.cache.allowed` for more information.

	permissions.object.blocking.cache=false

Configure this threshold to indicate when to use the custom SQL finder to check resource permissions.

	permissions.role.resource.permission.query.threshold=10

The permissions cache uses a thread local map to store the most frequently accessed items to lower the number of queries to the underlying cache. Set the maximum map size to `0` to disable the thread level cache.

	permissions.thread.local.cache.max.size=100

Set this to `true` to enable inline SQL permission checks.

	permissions.inline.sql.check.enabled=true

Set this to `true` to use permission checking when reading custom attributes by default.

	permissions.custom.attribute.read.check.by.default=true

Set this to `true` to use permission checking when writing custom attributes by default.

	permissions.custom.attribute.write.check.by.default=true

Set the following to `true` to automatically check the view permission on parent categories or folders when checking the permission on an specific item.

For example, if set to `true`, to be able to have access to a document, a user must have the view permission on the document's folder and all its parent folders. Or, to have access to a comment, a user must have the view permission on the comments's category and all its parent categories.

	permissions.view.dynamic.inheritance=true

Set the following to `true` to enable propagation of permissions between models.

For example, when setting the permissions on a a specific Wiki node, if you assign a role a permission (e.g. `DELETE`), then the assignment of that permission is also propagated to all Wiki pages that belong to that Wiki node.

The actual logic of how permissions are propagated among models is specified per portlet. See `liferay-portlet.xml`'s use of the element `permission-propagator`.

	permissions.propagation.enabled=false

## Captcha [](id=captcha)

Set the maximum number of captcha checks per portlet session. Set this value to `0` to always check. Set this value to a number less than `0` to never check. Unauthenticated users will always be checked on every request if captcha checks is enabled.

	captcha.max.challenges=1

Set whether or not to use captcha checks for the following actions.

	captcha.check.portal.create_account=true
	captcha.check.portal.send_password=true
	captcha.check.portlet.message_boards.edit_category=false
	captcha.check.portlet.message_boards.edit_message=false

Set the engine used to generate captchas. reCAPTCHA uses an external service that must be configured independently but provides an audible alternative which makes the captcha accessible to the visually impaired.

*Examples:*

	captcha.engine.impl=com.liferay.portal.captcha.recaptcha.ReCaptchaImpl
	captcha.engine.impl=com.liferay.portal.captcha.simplecaptcha.SimpleCaptchaImpl

reCAPTCHA

	captcha.engine.recaptcha.key.private=
	captcha.engine.recaptcha.key.public=
	captcha.engine.recaptcha.url.script=http://api.recaptcha.net/challenge?k=
	captcha.engine.recaptcha.url.noscript=http://api.recaptcha.net/noscript?k=
	captcha.engine.recaptcha.url.verify=http://api-verify.recaptcha.net/verify

SimpleCaptcha

Set the height and width for captcha images generated by SimpleCaptcha.

	captcha.engine.simplecaptcha.height=50
	captcha.engine.simplecaptcha.width=150

Input a list of comma delimited class names that implement `nl.captcha.backgrounds.BackgroundProducer`. These classes will be randomly used by SimpleCaptcha to generate a background for a captcha image.

	captcha.engine.simplecaptcha.background.producers=nl.captcha.backgrounds.FlatColorBackgroundProducer,nl.captcha.backgrounds.GradiatedBackgroundProducer,nl.captcha.backgrounds.SquigglesBackgroundProducer,nl.captcha.backgrounds.TransparentBackgroundProducer

Input a list of comma delimited class names that implement `nl.captcha.gimpy.GimpyRenderer`. These classes will be randomly used by SimpleCaptcha to gimp a captcha image.

*Examples:*

	captcha.engine.simplecaptcha.gimpy.renderers=nl.captcha.gimpy.RippleGimpyRenderer
	captcha.engine.simplecaptcha.gimpy.renderers=nl.captcha.gimpy.BlockGimpyRenderer,nl.captcha.gimpy.DropShadowGimpyRenderer,nl.captcha.gimpy.FishEyeGimpyRenderer,nl.captcha.gimpy.RippleGimpyRenderer,nl.captcha.gimpy.ShearGimpyRenderer

Input a list of comma delimited class names that implement
`nl.captcha.noise.NoiseProducer`. These classes will be randomly
used by SimpleCaptcha to add noise to a captcha image.

*Examples:*

captcha.engine.simplecaptcha.noise.producers=nl.captcha.noise.CurvedLineNoiseProducer
captcha.engine.simplecaptcha.noise.producers=nl.captcha.noise.CurvedLineNoiseProducer,nl.captcha.noise.StraightLineNoiseProducer

Input a list of comma delimited class names that implement `nl.captcha.text.producer.TextProducer`. These classes will be randomly used by SimpleCaptcha to generate text for a captcha image.

*Examples:*

	captcha.engine.simplecaptcha.text.producers=com.liferay.portal.captcha.simplecaptcha.PinNumberTextProducer
	captcha.engine.simplecaptcha.text.producers=com.liferay.portal.captcha.simplecaptcha.DictionaryWordTextProducer,com.liferay.portal.captcha.simplecaptcha.PinNumberTextProducer,nl.captcha.text.producer.DefaultTextProducer,nl.captcha.text.producer.FiveLetterFirstNameTextProducer

Input a list of comma delimited class names that implement `nl.captcha.text.renderer.WordRenderer`. These classes will be randomly used by SimpleCaptcha to render text for a captcha image.

*Examples:*

	captcha.engine.simplecaptcha.word.renderers=nl.captcha.text.renderer.DefaultWordRenderer
	captcha.engine.simplecaptcha.word.renderers=nl.captcha.text.renderer.ColoredEdgesWordRenderer,nl.captcha.text.renderer.DefaultWordRenderer

## Startup Events [](id=startup-events)

Input a list of comma delimited class names that extend `com.liferay.portal.struts.SimpleAction`. These classes will run at the specified event.

Global startup event that runs once when the portal initializes.

	global.startup.events=com.liferay.portal.events.GlobalStartupAction

Application startup event that runs once for every web site instance of the portal that initializes.

*Examples:*

	application.startup.events=com.liferay.portal.events.AddDefaultDataAction,com.liferay.portal.events.AppStartupAction,com.liferay.portal.events.ChannelHubAppStartupAction
	application.startup.events=com.liferay.portal.events.AppStartupAction,com.liferay.portal.events.SampleAppStartupAction

## Shutdown Events [](id=shutdown-events)

Input a list of comma delimited class names that extend `com.liferay.portal.struts.SimpleAction`. These classes will run at the specified event.

Global shutdown event that runs once when the portal shuts down.

	global.shutdown.events=com.liferay.portal.events.GlobalShutdownAction

Application shutdown event that runs once for every web site instance of the portal that shuts down.

	application.shutdown.events=com.liferay.portal.events.AppShutdownAction,com.liferay.portal.events.ChannelHubAppShutdownAction

Programmatically kill the Java process on shutdown. This is a workaround for a bug in Tomcat and Linux where the process hangs on forever.

See [http://issues.liferay.com/browse/LEP-2048](http://issues.liferay.com/browse/LEP-2048) for more information.

	shutdown.programmatically.exit=false

## Portal Events [](id=portal-events)


Input a list of comma delimited class names that extend `com.liferay.portal.kernel.events.Action`. These classes will run before or after the specified event.

Servlet service event (the pre-service events have an associated error page and will forward to that page if an exception is thrown during excecution of the events). The pre-service events process before Struts processes the request. The post-service events process after Struts processes the request.

*Examples:* 
 
	servlet.service.events.pre=com.liferay.portal.events.ServicePreAction,com.liferay.portal.events.DeviceServicePreAction,com.liferay.portal.events.ThemeServicePreAction
    servlet.service.events.pre=com.liferay.portal.events.LogMemoryUsageAction,com.liferay.portal.events.LogThreadCountAction,com.liferay.portal.events.ServicePreAction,com.liferay.portal.events.DeviceServicePreAction,com.liferay.portal.events.ThemeServicePreAction
    servlet.service.events.pre=com.liferay.portal.events.LogSessionIdAction,com.liferay.portal.events.ServicePreAction,com.liferay.portal.events.DeviceServicePreAction,com.liferay.portal.events.ThemeServicePreAction
    servlet.service.events.pre=com.liferay.portal.events.ServicePreAction,com.liferay.portal.events.DeviceServicePreAction,com.liferay.portal.events.ThemeServicePreAction,com.liferay.portal.events.RandomLayoutAction
    servlet.service.events.pre=com.liferay.portal.events.ServicePreAction,com.liferay.portal.events.DeviceServicePreAction,com.liferay.portal.events.ThemeServicePreAction,com.liferay.portal.events.RandomLookAndFeelAction
    servlet.service.events.pre=com.liferay.portal.events.ServicePreAction,com.liferay.portal.events.DeviceServicePreAction,com.liferay.portal.events.ThemeServicePreAction,com.liferay.portal.events.SecureRequestAction
    servlet.service.events.pre.error.page=/common/error.jsp
    servlet.service.events.post=com.liferay.portal.events.ServicePostAction

Login event

Define events that can occur pre-login and post-login.

	login.events.pre=com.liferay.portal.events.LoginPreAction
	login.events.post=com.liferay.portal.events.ChannelLoginPostAction,com.liferay.portal.events.DefaultLandingPageAction,com.liferay.portal.events.LoginPostAction

Logout event

Similarly, events can be defined for the logout event.
 
*Examples:*

	logout.events.pre=com.liferay.portal.events.LogoutPreAction
	logout.events.post=com.liferay.portal.events.LogoutPostAction,com.liferay.portal.events.DefaultLogoutPageAction,com.liferay.portal.events.SiteMinderLogoutAction
	logout.events.post=com.liferay.portal.events.LogoutPostAction,com.liferay.portal.events.GarbageCollectorAction

## Default Landing Page [](id=default-landing-page)

Set the default landing page path for logged in users relative to the server path. This is the page users are automatically redirected to after logging in. For example, if you want the default landing page to be [http://localhost:8080/web/guest/login](http://localhost:8080/web/guest/login), set this to `/web/guest/login`. To activate this feature, set `auth.forward.by.last.path` to `true`. To customize the behavior, see `com.liferay.portal.events.DefaultLandingPageAction` in the `login.events.post` property above.

*Examples:*

	default.landing.page.path=
	default.landing.page.path=/web/guest/login

## Default Logout Page [](id=default-logout-page)

Set the default logout page path for users relative to the server path. This is the page users are automatically redirected to after logging out. For example, if you want the default logout page to be [http://localhost:8080/web/guest/logout](http://localhost:8080/web/guest/logout), set this to `/web/guest/logout`. To activate this feature, set `auth.forward.by.last.path` to `true`. To customize the behavior, see `com.liferay.portal.events.DefaultLogoutPageAction` in the `logout.events.post` property above.

*Examples:*

	default.logout.page.path=
	default.logout.page.path=/web/guest/logout

## Default Guest Public Layouts [](id=default-guest-public-layouts)

The Guest group must have at least one public page. The settings for the initial public page are specified in the following properties.

If you need to add more than one page, set the property `default.guest.public.layouts.lar` to specifiy a LAR file instead.

For even more complex behavior, override the `addDefaultGuestPublicLayouts` method in `com.liferay.portal.service.impl.GroupLocalServiceImpl`.

Set the name of the public layout.

	default.guest.public.layout.name=Welcome

Set the layout template id of the public layout.

	default.guest.public.layout.template.id=2_columns_ii

Set the portlet ids for the columns specified in the layout template.

	default.guest.public.layout.column-1=58
	default.guest.public.layout.column-2=47
	default.guest.public.layout.column-3=
	default.guest.public.layout.column-4=

Set the friendly url of the public layout.

	default.guest.public.layout.friendly.url=/home

Set the regular theme id for the public layout.

*Example:*

	default.guest.public.layout.regular.theme.id=classic

Set the regular color scheme id for the public layout.

*Example:*

	default.guest.public.layout.regular.color.scheme.id=01

Set the wap theme id for the public layout.

*Example:*

	default.guest.public.layout.wap.theme.id=mobile

Set the wap color scheme for the public layout.

*Example:*

	default.guest.public.layout.wap.color.scheme.id=01

Specify a LAR file that can be used to create the guest public layouts. If this property is set, the previous layout properties will be ignored.

*Example:*

	default.guest.public.layouts.lar=${liferay.home}/deploy/default_guest_public.lar

## Default User Private Layouts [](id=default-user-private-layouts)

If the properties `layout.user.private.layouts.enabled` and `layout.user.private.layouts.auto.create` are both set to `true`, then users will have private layouts and they will be automatically created. The settings below are used for the creation of the initial private pages.

If you need to add more than one page, set the property `default.user.private.layouts.lar` to specifiy a LAR file instead.

For even more complex behavior, override the `addDefaultUserPrivateLayouts` method in `com.liferay.portal.events.ServicePreAction`.

Set the name of the private layout.

	default.user.private.layout.name=Welcome

Set the layout template id of the private layout.

	default.user.private.layout.template.id=2_columns_ii

Set the portlet ids for the columns specified in the layout template.

	default.user.private.layout.column-1=82,23,11
	default.user.private.layout.column-2=29,8
	default.user.private.layout.column-3=
	default.user.private.layout.column-4=

Set the friendly url of the private layout.

	default.user.private.layout.friendly.url=/home

Set the regular theme id for the private layout.

*Example:*

	default.user.private.layout.regular.theme.id=classic

Set the regular color scheme id for the private layout.

*Example:*

	default.user.private.layout.regular.color.scheme.id=01

Set the wap theme id for the private layout.

*Example:*

	default.user.private.layout.wap.theme.id=mobile

Set the wap color scheme for the private layout.

*Example:*

	default.user.private.layout.wap.color.scheme.id=01

Specify a LAR file that can be used to create the user private layouts. If this property is set, the previous layout properties will be ignored.

*Example:*

	default.user.private.layouts.lar=${liferay.home}/deploy/default_user_private.lar

## Default User Public Layouts [](id=default-user-public-layouts)

If the properties `layout.user.public.layouts.enabled` and `layout.user.public.layouts.auto.create` are both set to `true`, then users will have public layouts and they will be automatically created. The settings below are used for the creation of the initial public pages.

If you need to add more than one page, set the property `default.user.public.layouts.lar` to specifiy a LAR file instead.

For even more complex behavior, override the `addDefaultUserPublicLayouts` method in `com.liferay.portal.events.ServicePreAction`.

Set the name of the public layout.

	default.user.public.layout.name=Welcome

Set the layout template id of the public layout.

	default.user.public.layout.template.id=2_columns_ii

Set the portlet ids for the columns specified in the layout template.

	default.user.public.layout.column-1=82,3
	default.user.public.layout.column-2=33
	default.user.public.layout.column-3=
	default.user.public.layout.column-4=

Set the friendly url of the public layout.

	default.user.public.layout.friendly.url=/home

Set the regular theme id for the public layout.

*Example:* 

	default.user.public.layout.regular.theme.id=classic

Set the regular color scheme id for the public layout.

*Example:*

	default.user.public.layout.regular.color.scheme.id=01

Set the wap theme id for the public layout.

*Example:*

	default.user.public.layout.wap.theme.id=mobile

Set the wap color scheme for the public layout.

*Example:*

	default.user.public.layout.wap.color.scheme.id=01

Specify a LAR file that can be used to create the user public layouts. If this property is set, the previous layout properties will be ignored.

*Example:*

	default.user.public.layouts.lar=${liferay.home}/deploy/default_user_public.lar

## Default Admin [](id=default-admin)

Set the default admin password.

	default.admin.password=test

Set the default admin screen name prefix.

	default.admin.screen.name=test

Set the default admin email address prefix.

	default.admin.email.address.prefix=test

Set the default admin first name.

	default.admin.first.name=Test

Set the default admin middle name.

	default.admin.middle.name=

Set the default admin last name.

	default.admin.last.name=Test

## Layouts [](id=layouts)

Set the list of layout types. The display text of each of the layout types is set in `content/Language.properties` and prefixed with `layout.types.`.

You can create new layout types and specify custom settings for each layout type. End users input dynamic values as designed in the edit page. End users see the layout as designed in the view page. The generated URL can reference properties set in the edit page. Parentable layouts can contain child layouts. You can also specify a comma delimited list of configuration actions that will be called for your layout when it is updated or deleted.

	layout.types=portlet,panel,embedded,url,link_to_layout

Input a list of sections that will be included as part of the layout form when adding a layout.

	layout.form.add=details

Input a list of sections that will be included as part of the layout form when updating a layout.

	layout.form.update=details,seo,look-and-feel,layout,javascript,custom-fields,advanced,mobile-rule-groups

Input a list of sections that will be included as part of the layout set form when updating a layout set.

	layout.set.form.update=look-and-feel,logo,javascript,advanced,mobile-rule-groups

Set whether or not private layouts are enabled. Set whether or not private layouts are modifiable. Set whether or not private layouts should be auto created if a user has no private layouts. If private layouts are not enabled, the other two properties are assumed to be `false`.

	layout.user.private.layouts.enabled=true
	layout.user.private.layouts.modifiable=true
	layout.user.private.layouts.auto.create=true

Set this to `true` if users must have the Power User role to have private pages.

	layout.user.private.layouts.power.user.required=false

Set whether or not public layouts are enabled. Set whether or not public layouts are modifiable. Set whether or not public layouts should be auto created if a user has no public layouts. If public layouts are not enabled, the other two properties are assumed to be `false`.

	layout.user.public.layouts.enabled=true
	layout.user.public.layouts.modifiable=true
	layout.user.public.layouts.auto.create=true

Set this to `true` if users must have the Power User role to have public pages.

	layout.user.public.layouts.power.user.required=false

Default settings layouts.

*Examples:*

	layout.edit.page=/portal/layout/edit/portlet.jsp
	layout.view.page=/portal/layout/view/portlet.jsp
	layout.url=${liferay:mainPath}/portal/layout?p_l_id=${liferay:plid}
	layout.url.friendliable=true
	layout.parentable=true
	layout.sitemapable=true
	layout.first.pageable=true
	layout.configuration.action.update=
	layout.configuration.action.delete=

Settings for portlet layouts are inherited from the default settings.

*Examples:*

	layout.edit.page[portlet]=/portal/layout/edit/portlet.jsp
	layout.view.page[portlet]=/portal/layout/view/portlet.jsp
	layout.url[portlet]=${liferay:mainPath}/portal/layout?p_l_id=${liferay:plid}
	layout.url.friendliable[portlet]=true
	layout.parentable[portlet]=true
	layout.configuration.action.update[portlet]=
	layout.configuration.action.delete[portlet]=

Settings for panel layouts.

	layout.edit.page[panel]=/portal/layout/edit/panel.jsp
	layout.view.page[panel]=/portal/layout/view/panel.jsp
	layout.url[panel]=${liferay:mainPath}/portal/layout?p_l_id=${liferay:plid}
	layout.url.friendliable[panel]=true
	layout.parentable[panel]=true
	layout.first.pageable[panel]=true

Settings for control_panel layouts.

	layout.edit.page[control_panel]=/portal/layout/edit/control_panel.jsp
	layout.view.page[control_panel]=/portal/layout/view/control_panel.jsp
	layout.url[control_panel]=${liferay:mainPath}/portal/layout?p_l_id=${liferay:plid}
	layout.url.friendliable[control_panel]=true
	layout.parentable[control_panel]=true
	layout.first.pageable[control_panel]=true


Settings for embedded layouts.

*Examples:*

	layout.edit.page[embedded]=/portal/layout/edit/embedded.jsp
	layout.view.page[embedded]=/portal/layout/view/embedded.jsp
	layout.url[embedded]=${liferay:mainPath}/portal/layout?p_l_id=${liferay:plid}
	layout.url.friendliable[embedded]=true
	layout.parentable[embedded]=false
	layout.sitemapable[embedded]=true
	layout.first.pageable[embedded]=true
	layout.configuration.action.update[embedded]=
	layout.configuration.action.delete[embedded]=

Settings for article layouts.

	layout.edit.page[article]=/portal/layout/edit/article.jsp
	layout.view.page[article]=/portal/layout/view/article.jsp
	layout.url.friendliable[article]=true
	layout.url[article]=${liferay:mainPath}/portal/layout?p_l_id=${liferay:plid}
	layout.parentable[article]=false
	layout.sitemapable[article]=true
	layout.first.pageable[article]=true
	layout.configuration.action.update[article]=com.liferay.portal.model.LayoutTypeArticleConfigurationUpdateAction
	layout.configuration.action.delete[article]=com.liferay.portal.model.LayoutTypeArticleConfigurationDeleteAction

Settings for URL layouts.

*Examples:*

	layout.edit.page[url]=/portal/layout/edit/url.jsp
	layout.view.page[url]=
	layout.url[url]=${url}
	layout.url.friendliable[url]=true
	layout.parentable[url]=false
	layout.sitemapable[url]=false
	layout.first.pageable[url]=false
	layout.configuration.action.update[url]=
	layout.configuration.action.delete[url]=

Settings for page layouts.

*Examples:*

	layout.edit.page[link_to_layout]=/portal/layout/edit/link_to_layout.jsp
	layout.view.page[link_to_layout]=
	layout.url[link_to_layout]=${liferay:mainPath}/portal/layout?groupId=${liferay:groupId}&privateLayout=${privateLayout}&layoutId=${linkToLayoutId}
	layout.url.friendliable[link_to_layout]=true
	layout.parentable[link_to_layout]=true
	layout.sitemapable[link_to_layout]=false
	layout.first.pageable[link_to_layout]=false
	layout.configuration.action.update[link_to_layout]=
	layout.configuration.action.delete[link_to_layout]=

Specify static portlets that cannot be moved and will always appear on every layout. Static portlets will take precedence over portlets that may have been dynamically configured for the layout.

For example, if you want the Hello World portlet to always appear at the start of the iteration of the first column for user layouts, set the property `layout.static.portlets.start.column-1[user]` to `47`. If you want the Hello World portlet to always appear at the end of the second column for user layouts, set the property `layout.static.portlets.end.column-2[user]` to `47`. You can input a list of comma delimited portlet ids to specify more than one portlet. If the portlet is instanceable, add the suffix `_INSTANCE_abcd` to the portlet id, where `abcd` is any random alphanumeric string.

The static portlets are fetched based on the properties controlled by custom filters using EasyConf. By default, the available filters are `user`, `site` and `organization`.

*Examples:*

	layout.static.portlets.start.column-1[user]=3,6
	layout.static.portlets.end.column-1[user]=14
	layout.static.portlets.start.column-2[user]=71_INSTANCE_abcd,7
	layout.static.portlets.end.column-2[user]=34,70
	layout.static.portlets.start.column-3[user]=
	layout.static.portlets.end.column-3[user]=

It is also possible to set static portlets based on the layout's friendly URL.

*Examples:*

	layout.static.portlets.start.column-1[user][/home]=3,6
	layout.static.portlets.end.column-2[regular-site][/home]=14

Set the static portlets for the pages of regular sites.

*Examples:*

	layout.static.portlets.start.column-1[regular-site]=
	layout.static.portlets.end.column-1[regular-site]=
	layout.static.portlets.start.column-2[regular-site]=
	layout.static.portlets.end.column-2[regular-site]=
	layout.static.portlets.start.column-3[regular-site]=
	layout.static.portlets.end.column-3[regular-site]=

Set the static portlets for the pages of organization sites.

*Examples:*

	layout.static.portlets.start.column-1[organization]=
	layout.static.portlets.end.column-1[organization]=
	layout.static.portlets.start.column-2[organization]=
	layout.static.portlets.end.column-2[organization]=
	layout.static.portlets.start.column-3[organization]=
	layout.static.portlets.end.column-3[organization]=

Set the static portlets that will appear for every layout. See `/html/portal/layout/view/portlet.jsp` for the logic of when these portlets will be shown. For example, these portlets will only show for layouts that can contain portlets and are not in a pop up state.

	layout.static.portlets.all=

Set the private group, private user and public servlet mapping for `com.liferay.portal.servlet.FriendlyURLServlet`. This value must match the servlet mapping set in `web.xml`.

For example, if the private group pages are mapped to `/group` and the group's friendly URL is set to `/guest` and the layout's friendly URL is set to `/company/site`, then the friendly URL for the page will be `http://www.liferay.com/group/guest/company/site`. Private group pages map to a site's private pages and are only available to authenticated users with the proper permissions.

For example, if the public pages are mapped to `/web` and the group or user's friendly URL is set to "/guest" and the layout's friendly URL is set to `/company/site`, then the friendly URL for the page will be `http://www.liferay.com/web/guest/company/site`. Public pages are available to unauthenticated users.

The friendly URL's for users, groups and layouts can be set during runtime.

	layout.friendly.url.private.group.servlet.mapping=/group
	layout.friendly.url.private.user.servlet.mapping=/user
	layout.friendly.url.public.servlet.mapping=/web

Redirect to this resource if the user requested a friendly URL that does not exist. Leave it blank to display nothing.

*Note:* For backward compatibility, this overrides the property
`layout.show.http.status` for the 404 status code.

*Example:*

	layout.friendly.url.page.not.found=/html/portal/404.html

Set the reserved keywords that cannot be used in a friendly URL.

	layout.friendly.url.keywords=c,combo,delegate,display_chart,dtd,facebook,google_gadget,group,html,image,language,netvibes,page,pbhs,poller,public,private,rss,sharepoint,sitemap.xml,tags,software_catalog,_vti_,wap,web,widget,wsrp,xmlrpc

Set this to `true` if guest users should see the maximize window icon.

	layout.guest.show.max.icon=false

Set this to `true` if guest users should see the minimize window icon.

	layout.guest.show.min.icon=false

Set this to `true` if users are shown that they do not have access to a portlet. The portlet init parameter `show-portlet-access-denied` will override this setting.

	layout.show.portlet.access.denied=true

Set this to `true` if users are shown that a portlet is inactive. The portlet init parameter `show-portlet-inactive` will override this setting.

	layout.show.portlet.inactive=true

Set this to `true` if the portal should show HTTP status codes like 404 if the requested page is not found.

	layout.show.http.status=true

Set the default layout template id used when creating layouts.

	layout.default.template.id=2_columns_ii

Set this to `false` to disable parallel rendering. You can also disable it on a per request basis by setting the attribute key `com.liferay.portal.util.WebKeys.PORTLET_PARALLEL_RENDER` to the `Boolean.FALSE` in a pre service event or by setting the URL parameter `p_p_parallel` to `0`.

	layout.parallel.render.enable=true

Set the name of a class that implements `com.liferay.portal.util.LayoutClone`. This class is used to remember maximized and minimized states on shared pages. The default implementation persists the state in the browser session.

	layout.clone.impl=com.liferay.portal.util.SessionLayoutClone

Set this to `true` to cache the content of layout templates. This is recommended because it improves performance for production servers. Setting it to `false` is useful during development if you need to make a lot of changes.

	layout.template.cache.enabled=true

Set the default value for the `p_l_reset` parameter. If set to `true`, then render parameters are cleared when different pages are hit. This is not the behavior promoted by the portlet specification, but is the one that most end users seem to prefer.

	layout.default.p_l_reset=true

Set this to `true` to enable comments for pages.

	layout.comments.enabled=true

Set this to `true` to remember maximized window states across different pages.

	layout.remember.maximized.window.state=false

Set this to specify the initial number of child pages to display in the Manage Pages tree. Set this to `-1` to show all.

	layout.manage.pages.initial.children=20
	
## Layout Prototypes

Set this property to true to enable the propagation of changes to a page from its page template by default.

    layout.prototype.link.enabled.default=true

In the case that a page template would fail to merge, make sure that we prevent repeated attempts that will inevitably fail by having a fail threshold.

    layout.prototype.merge.fail.threshold=3

The number of seconds we should expect a lock to last before we recognize that an error occured and some other process should be able to attempt to take it over.

    layout.prototype.merge.lock.max.time=300

## Layout Set Prototypes

In the case that a site template would fail to merge, make sure that we prevent repeated attempts that will inevitably fail by having a fail threshold.

    layout.set.prototype.merge.fail.threshold=3

The number of seconds we should expect a lock to last before we recognize that an error occured and some other process should be able to attempt to take it over.

    layout.set.prototype.merge.lock.max.time=600

## Portlet URL [](id=portlet-url)

Set this to `true` if calling `setParameter` on a portlet URL appends the parameter value versus replacing it. There is some disagreement in the interpretation of the JSR 168 spec among portlet developers over this specific behavior. Liferay Portal successfully passes the portlet TCK tests whether this value is set to `true` or `false`.

See [http://issues.liferay.com/browse/LEP-426](http://issues.liferay.com/browse/LEP-426) for more information.

	portlet.url.append.parameters=false

Set this to `true` to allow portlet URLs to generate with an anchor tag.

	portlet.url.anchor.enable=false

JSR 286 specifies that portlet URLs are escaped by default. Set this to `false` to provide for better backwards compatibility.

If this is set to `true`, but a specific portlet application requires that its portlet URLs not be escaped by default, then modify `portlet.xml` and set the container runtime option `javax.portlet.escapeXml` to `false`.

	portlet.url.escape.xml=false
	
Set a list of comma delimited reserved parameters that will not be added to refresh URL.

	portlet.url.refresh.url.reserved.parameters=password,password1,password2,pop3Password,properties--jdbc.default.password,settings--google.apps.password,smtpPassword,settings--ldap.security.credentials

## Preferences [](id=preferences)

Set the following to `true` to validate portlet preferences on startup.

	preference.validate.on.startup=false

## Redirect [](id=redirect)

Set this property to `ip` or `domain` for the redirect security method. If set to `domain`, the portal will only redirect users to domains listed in the property `redirect.url.domain.allowed`. If set to `ip`, the portal will only redirect to domains whose IP address resolves to an IP address listed in the property `redirect.url.ip.allowed`.

*Examples:*

	redirect.url.security.mode=domain
	redirect.url.security.mode=ip

Input a list of comma delimited domains which the portal is allowed to redirect to. Input a blank list to allow any domain.

	redirect.url.domains.allowed=

Input a list of comma delimited IPs which the portal is allowed to redirect to. Input a blank list to allow any IP. SERVER_IP will be replaced with the IP of the host server.

	redirect.url.ips.allowed=127.0.0.1,SERVER_IP
	
## Struts [](id=struts)

Input the custom Struts request processor that will be used by Struts based portlets. The custom class must extend `com.liferay.portal.struts.PortletRequestProcessor` and have the same constructor.

	struts.portlet.request.processor=com.liferay.portal.struts.PortletRequestProcessor

## Images [](id=images)

Set this to `true` if the Image servlet will automatically scale an image based on the request `height` and `width` parameters.

	image.auto.scale=false

Set the location of the default spacer image that is used for missing images. This image must be available in the class path.

	image.default.spacer=com/liferay/portal/dependencies/spacer.gif

Set the location of the default company logo image that is used for missing company logo images. This image must be available in the class path.

	image.default.company.logo=com/liferay/portal/dependencies/company_logo.png

Set the location of the default organization logo image that is used for missing organization logo images. This image must be available in the class path.

	image.default.organization.logo=com/liferay/portal/dependencies/organization_logo.png

Set the locations of the default user portrait images that are used for missing user portrait images. This image must be available in the class path.

	image.default.user.female.portrait=com/liferay/portal/dependencies/user_female_portrait.png
	image.default.user.male.portrait=com/liferay/portal/dependencies/user_male_portrait.png


In versions prior to 6.1, the Image Gallery portlet supported persisting images via `com.liferay.portal.image.DatabaseHook`, `com.liferay.portal.image.DLHook`, or `com.liferay.portal.image.FileSystemHook`. Since 6.1, only `DLHook` is supported.

Set this property to the hook implementation to trigger automatic data migration during an upgrade.

*Examples:*

	image.hook.impl=com.liferay.portal.image.DatabaseHook
	image.hook.impl=com.liferay.portal.image.DLHook
	image.hook.impl=com.liferay.portal.image.FileSystemHook

This is a legacy property used by the `FileSystemHook` to allow data migration from other hooks.

	image.hook.file.system.root.dir=${liferay.home}/data/images

## Editors [](id=editors)

You can configure individual JSP pages to use a specific implementation of the available WYSIWYG editors: `bbcode`, `ckeditor`, `ckeditor_bbcode`, `ckeditor_creole`, `fckeditor`, `simple`, `tinymce`, or `tinymce_simple`.

	editor.wysiwyg.default=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.announcements.edit_entry.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.blogs.configuration.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.blogs.edit_entry.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.calendar.configuration.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.calendar.edit_event.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.invitation.configuration.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.journal.configuration.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.journal.edit_article_content.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.journal.edit_article_content_xsd_el.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.login.configuration.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.mail.edit.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.mail.edit_message.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.message_boards.configuration.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.message_boards.edit_message.bb_code.jsp=ckeditor_bbcode
	editor.wysiwyg.portal-web.docroot.html.portlet.message_boards.edit_message.html.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.portal_settings.email_notifications.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.shopping.configuration.jsp=ckeditor
	editor.wysiwyg.portal-web.docroot.html.portlet.wiki.edit.creole.jsp=ckeditor_creole
	editor.wysiwyg.portal-web.docroot.html.portlet.wiki.edit.html.jsp=ckeditor

## Fields [](id=fields)

Set the following fields to `false` so users cannot see them. Some company policies require gender and birthday information to always be hidden.

	field.enable.com.liferay.portal.model.Contact.male=true
	field.enable.com.liferay.portal.model.Contact.birthday=true
	field.enable.com.liferay.portal.model.Organization.status=false

Input a list of comma delimited user types who can edit their own fields. Valid types are `administrator`, `user-with-mx` and `user-without-mx`.

Set a value of `administrator` if an administrator can edit the specified field. An administrator is anyone who has the Administrator role.

Set a value of `user-with-mx` if a user who has an email address that matches the company mail suffix can edit the specified field.

Set a value of `user-without-mx` if a user who does not have an email address that matches the company mail suffix can edit the specified field.

Set all three values if all users can edit the specified field. Set a combination of the three values if only a combination of the users can edit the specified field.

	field.editable.com.liferay.portal.model.User.screenName=administrator,user-with-mx,user-without-mx
	field.editable.com.liferay.portal.model.User.emailAddress=administrator,user-with-mx,user-without-mx


## Mime Types [](id=mime-types)

Input a list of comma delimited extensions for which the content disposition header has to be set to `inline`.

	mime.types.content.disposition.inline=flv,gif,jpg,pdf,png,swf,wmv

## Amazon [](id=amazon)

Enter an Amazon access key ID and an Amazon associate tag. This is made available only for personal use. Please see the Amazons license at [http://www.amazon.com](http://www.amazon.com) for more information.

*Examples:*

	amazon.access.key.id=
	amazon.associate.tag=
	amazon.secret.access.key=
	
## Browser Cache [](id=browser-cache)

Set this to `true` if you want the portal to force the browser cache to be disabled. It will only disable the cache for the rendered HTML response for logged in users. It will not have impact on static content or other resources. This is useful to ensure that logged in users cannot go to the sign in page by clicking on the back button in their browsers.

	browser.cache.signed.in.disabled=false	

## Browser Launcher [](id=browser-launcher)

Enter a URL to automatically launch a browser to that URL when the portal has fully initialized. Enter a blank URL to disable this feature.

	browser.launcher.url=http://localhost:8080

## Control Panel [](id=control-pan-2)

Set the name of the layout.

	control.panel.layout.name=Control Panel

Set the friendly URL of the layout.

	control.panel.layout.friendly.url=/manage

Set the theme of the layout.

	control.panel.layout.regular.theme.id=controlpanel

Set the maximum number of sites that will be shown in the navigation menus. A large value might cause performance problems if the number of sites that the user can administrate is very large.

	control.panel.navigation.max.sites=100

Set the name of a class that implements `com.liferay.portlet.ControlPanelEntry`. This class denotes the default value of of the element `control-panel-entry-class` in `liferay-portlet.xml` and is called by the control panel to decide whether the portlet should be shown to a specific user in a specific context.

	control.panel.default.entry.class=com.liferay.portlet.DefaultControlPanelEntry

## Instant Messenger [](id=instant-messenger)

Set the AIM login and password by which the system will use AIM to communicate with users.

	aim.login=
	aim.password=

Due to a bug in JOscarLib 0.3b1, you must set the full path to the ICQ jar.

See the following posts:

-   [http://sourceforge.net/projects/ooimlib/forums/forum/166562/topic/843299?message=1972697](http://sourceforge.net/projects/ooimlib/forums/forum/166562/topic/843299?message=1972697)
-   [http://sourceforge.net/projects/ooimlib/forums/forum/166562/topic/843299?message=1990487](http://sourceforge.net/projects/ooimlib/forums/forum/166562/topic/843299?message=1990487)

    icq.jar=C:/Java/orion-2.0.7/lib/icq.jar

Set the ICQ login and password by which the system will use ICQ to communicate with users.

	icq.login=
	icq.password=

Set the MSN login and password by which the system will use MSN to communicate with users.

	msn.login=
	msn.password=

Set the YM login and password by which the system will use YM to communicate with users.

	ym.login=
	ym.password=

## Lucene Search [](id=lucene-search)

Set this to `true` to enable highlighting of search results.

	index.search.highlight.enabled=true

Set the fragment size returned from the search result highlighter

	index.search.highlight.fragment.size=80

Set the number of lines for the snippet returned by search engine.

	index.search.highlight.snippet.size=3
	
Set the limit for results used when performing index searches.

	index.search.limit=10000

Set this to `true` to enable scoring of results

	index.search.scoring.enabled=true

Set the limit for results used when performing index searches that are subsequently filtered by permissions.

	index.filter.search.limit=5000

Set this to `true` if you want to avoid any writes to the index. This is useful in some clustering environments where there is a shared index and only one node of the cluster updates it.

	index.read.only=false

Set this to `true` if you want to index your entire library of files on startup. This property is available so that automated test environments index on startup. Do not set this to `true` on production systems or else your index will be indexed on every startup.

	index.on.startup=false

Set this to `true` to add a delay before indexing on startup. A delay may be necessary if a lot of plugins need to be loaded and reindexed. This property is only valid if `index.on.startup` is set to `true`.

	index.on.startup.delay=60

Set this to `true` if you want to index your entire library of files after an upgrade. Only set this property to `false` if you are running a small upgrade and you do not need to reindex everything.

	index.on.upgrade=true

Set this to `true` if you want the indexing on startup to be executed on a separate thread to speed up execution.

	index.with.thread=true

Set the date format used for storing dates as text in the index.

	index.date.format.pattern=yyyyMMddHHmmss

Set this to `true` if you want to use compress index files before they are dumped.

	index.dump.compression.enabled=true

Designate whether Lucene stores indexes in a database via JDBC, file system, or in RAM.

*Examples:*

	lucene.store.type=jdbc
	lucene.store.type=file
	lucene.store.type=ram

When using file store, turning this flag on will tell Lucene to use `MMapDirectory` rather than using the default selection (`SimpleFSDirectory` for Windows and `NIOFSDirectory` for Linux). It only makes sense to use `MMapDirectory` for 64-bit JVM with a lot of memory. On a 32-bit JVM, if the index files are huge, this could easily lead to an out of memory for non-heap memory.

	lucene.store.type.file.force.mmap=false

Lucene's storage of indexes via JDBC has a bug where temp files are not removed. This can eat up disk space over time. Set this to `true` to automatically clean up the temporary files regularly. See [http://issues.liferay.com/browse/LEP-2180](LEP-2180).

<!-- This link above appears to be broken.  -->

*Example:*

	lucene.store.jdbc.auto.clean.up.enabled=true

Set the interval on which the lucene automatic clean up is set to run. The value is set in one minute increments.

	lucene.store.jdbc.auto.clean.up.interval=1440

Set the JDBC dialect that Lucene uses to store indexes in the database. This is only referenced if Lucene stores indexes in the database. Liferay will attempt to load the proper dialect based on the URL of the JDBC connection. For example, the property `lucene.store.jdbc.dialect.mysql` is read for the JDBC connection URL `jdbc:mysql://localhost/lportal`.

*Example:*

	lucene.store.jdbc.dialect.db2=org.apache.lucene.store.jdbc.dialect.DB2Dialect
	lucene.store.jdbc.dialect.derby=org.apache.lucene.store.jdbc.dialect.DerbyDialect
	lucene.store.jdbc.dialect.hsqldb=org.apache.lucene.store.jdbc.dialect.HSQLDialect
	lucene.store.jdbc.dialect.jtds=org.apache.lucene.store.jdbc.dialect.SQLServerDialect
	lucene.store.jdbc.dialect.microsoft=org.apache.lucene.store.jdbc.dialect.SQLServerDialect
	lucene.store.jdbc.dialect.mysql=org.apache.lucene.store.jdbc.dialect.MySQLDialect
	lucene.store.jdbc.dialect.mysql=org.apache.lucene.store.jdbc.dialect.MySQLInnoDBDialect
	lucene.store.jdbc.dialect.mysql=org.apache.lucene.store.jdbc.dialect.MySQLMyISAMDialect
	lucene.store.jdbc.dialect.oracle=org.apache.lucene.store.jdbc.dialect.OracleDialect
	lucene.store.jdbc.dialect.postgresql=org.apache.lucene.store.jdbc.dialect.PostgreSQLDialect

Set the directory where Lucene indexes are stored. This is only referenced if Lucene stores indexes in the file system.

	lucene.dir=${liferay.home}/data/lucene/

Input a class name that extends `com.liferay.portal.search.lucene.LuceneFileExtractor`. This class is called by Lucene to extract text from complex files so that they can be properly indexed.

	lucene.file.extractor=com.liferay.portal.search.lucene.LuceneFileExtractor

The file extractor can sometimes return text that is not valid for Lucene. This property expects a regular expression. Any character that does not match the regular expression will be replaced with a blank space. Set an empty regular expression to disable this feature.

*Example:*

	lucene.file.extractor.regexp.strip=
	lucene.file.extractor.regexp.strip=[\\d\\w]

Set how often index updates will be committed. Set the batch size to configure how many consecutive updates will trigger a commit. If the value is `0`, then the index will be committed on every update. Set the time interval in milliseconds to configure how often to commit the index. The time interval is not read unless the batch size is greater than `0` because the time interval works in conjunction with the batch size to guarantee that the index is committed after a specified time interval. Set the time interval to `0` to disable committing the index by a time interval.

	lucene.commit.batch.size=0
	lucene.commit.time.interval=0

Set Lucene's buffer size in megabytes. Higher numbers mean indexing goes faster but uses more memory.

	lucene.buffer.size=16

Set Lucene's merge factor. Higher numbers mean indexing goes faster but uses more memory. The default value from Lucene is `10`. This should never be set to a number lower than `2`.

	lucene.merge.factor=10

Set how often to run Lucene's optimize method. Optimization speeds up searching but slows down writing. Set this property to `0` to always optimize. Set this property to an integer greater than `0` to optimize every X writes.

	lucene.optimize.interval=100

Set this to `true` if you want the portal to replicate an index write across all members of the cluster. This is useful in some clustered environments where you wish each server instance to have its own copy of the Lucene search index. This is only relevant when using the default Lucene indexing engine.

	lucene.replicate.write=false
	
## Multi Value Map [](id=multi-value-map)

Set the multi value map property to `0` to store the map in memory and `1` to store the map in the file system.

	multi.value.map.com.liferay.portal.convert.ConvertPermissionAlgorithm.convertResourcePermission=0
	multi.value.map.com.liferay.portal.convert.ConvertPermissionAlgorithm.convertRoles=0
	
## Setup Wizard [](id=setup-wizard)

Set a comma delimited list of supported databases.

	setup.database.types=db2,derby,ingres,mysql,oracle,postgresql,sqlserver,sybase

	setup.database.driverClassName[db2]=com.ibm.db2.jcc.DB2Driver
	setup.database.driverClassName[derby]=org.apache.derby.jdbc.EmbeddedDriver
	setup.database.driverClassName[ingres]=com.ingres.jdbc.IngresDriver
	setup.database.driverClassName[mysql]=com.mysql.jdbc.Driver
	setup.database.driverClassName[oracle]=oracle.jdbc.driver.OracleDriver
	setup.database.driverClassName[postgresql]=org.postgresql.Driver
	setup.database.driverClassName[sqlserver]=net.sourceforge.jtds.jdbc.Driver
	setup.database.driverClassName[sybase]=net.sourceforge.jtds.jdbc.Driver

	setup.database.url[db2]=jdbc:db2://localhost:50000/lportal:deferPrepares=false;fullyMaterializeInputStreams=true;fullyMaterializeLobData=true;progresssiveLocators=2;progressiveStreaming=2;
	setup.database.url[derby]=jdbc:derby:lportal
	setup.database.url[ingres]=jdbc:ingres://localhost:II7/lportal
	setup.database.url[mysql]=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
	setup.database.url[oracle]=jdbc:oracle:thin:@localhost:1521:xe
	setup.database.url[postgresql]=jdbc:postgresql://localhost:5432/lportal
	setup.database.url[sqlserver]=jdbc:jtds:sqlserver://localhost/lportal
	setup.database.url[sybase]=jdbc:jtds:sybase://localhost:5000/lportal

Set this property to true if the Setup Wizard should be displayed the first the portal is started.

	setup.wizard.enabled=false	

## SourceForge [](id=sourceforge)

    source.forge.mirrors=\
        http://downloads.sourceforge.net,\      # Redirect
        http://internap.dl.sourceforge.net,\    # San Jose, CA
        http://superb-east.dl.sourceforge.net,\ # McLean, Virginia
        http://superb-west.dl.sourceforge.net,\ # Seattle, Washington
        http://easynews.dl.sourceforge.net,\    # Phoenix, AZ
        http://kent.dl.sourceforge.net,\        # Kent, UK
        http://ufpr.dl.sourceforge.net,\        # Curitiba, Brazil
        http://belnet.dl.sourceforge.net,\      # Brussels, Belgium
        http://switch.dl.sourceforge.net,\      # Lausanne, Switzerland
        http://mesh.dl.sourceforge.net,\        # Duesseldorf, Germany
        http://ovh.dl.sourceforge.net,\         # Paris, France
        http://dfn.dl.sourceforge.net,\         # Berlin, Germany
        http://heanet.dl.sourceforge.net,\      # Dublin, Ireland
        http://garr.dl.sourceforge.net,\        # Bologna, Italy
        http://surfnet.dl.sourceforge.net       # Amsterdam, The Netherlands
        http://jaist.dl.sourceforge.net,\       # Ishikawa, Japan
        http://nchc.dl.sourceforge.net,\        # Tainan, Taiwan
        http://optusnet.dl.sourceforge.net      # Sydney, Australia

## Value Object [](id=value-object)

You can add a listener for a specific class by setting the property `value.object.listener` with a list of comma delimited class names that implement `com.liferay.portal.model.ModelListener`. These classes are pooled and reused and must be thread safe.

	value.object.listener.com.liferay.portal.model.Contact=com.liferay.portal.model.ContactListener
	value.object.listener.com.liferay.portal.model.Layout=com.liferay.portal.model.LayoutListener
	value.object.listener.com.liferay.portal.model.LayoutSet=com.liferay.portal.model.LayoutSetListener
	value.object.listener.com.liferay.portal.model.PortletPreferences=com.liferay.portal.model.PortletPreferencesListener
	value.object.listener.com.liferay.portal.model.User=com.liferay.portal.model.UserListener
	value.object.listener.com.liferay.portal.model.UserGroup=com.liferay.portal.model.UserGroupListener
	value.object.listener.com.liferay.portlet.journal.model.JournalArticle=com.liferay.portlet.journal.model.JournalArticleListener
	value.object.listener.com.liferay.portlet.journal.model.JournalTemplate=com.liferay.portlet.journal.model.JournalTemplateListener

Value objects are cached at three levels. They first level is *entity*, the second level is *finder* and the third level is *Hibernate*.

The entity level cache stores a value object's primary key to the value object itself.

The finder level cache stores the many paths that return a value object and the many paths that return a list of value objects. The finder level cache only caches primary keys and is further helped by the entity level cache that caches the value object to the primary key.

The Hibernate level cache is provided by the `hibernate.cache.provider_class` property.

Set this to `true` to enable entity level caching.

	value.object.entity.cache.enabled=true

Set this to `true` to configure entity level caching to block. See the property `ehcache.blocking.cache.allowed` for more information.

	value.object.entity.blocking.cache=true

The entity level cache uses a thread local map to store the most frequently accessed items to lower the number of queries to the underlying cache. Set the maximum map size to `0` to disable the thread level cache.

	value.object.entity.thread.local.cache.max.size=100

Entity level caching for a specific type of value object can be configured by using a property name that includes the value object's class name.

	value.object.entity.cache.enabled.com.liferay.portal.model.Layout=true
	value.object.entity.cache.enabled.com.liferay.portal.model.User=true

Set this to `true` to enable finder level caching.

	value.object.finder.cache.enabled=true

Set this to `true` to configure finder level caching to block. See the property `ehcache.blocking.cache.allowed` for more information.

	value.object.finder.blocking.cache=true

The finder level cache uses a thread local map to store the most frequently accessed items to lower the number of queries to the underlying cache. Set the maximum map size to `0` to disable the thread level cache.

	value.object.finder.thread.local.cache.max.size=100

Finder level caching for a specific type of value object can be configured by using a property name that includes the value object's class name. Mapping tables can also be specified to configure the caching of value object relationships.

	value.object.finder.cache.enabled.com.liferay.portal.model.Layout=true
	value.object.finder.cache.enabled.com.liferay.portal.model.User=true
	value.object.finder.cache.enabled.Users_Roles=true

## Audit Message [](id=audit-message)

    audit.message.com.liferay.portal.model.Layout.VIEW=false

## Buffered Increment [](id=buffered-increment)

Set the queue size for the message bus destinations used to buffer increments.

	buffered.increment.parallel.queue.size=100000
	buffered.increment.serial.queue.size=100000

## Cache [](id=cache)

The cache filter caches processed web content. Set the threshold size to prevent caching resources that are too large. The default value is 500 kb.
    
    cache.content.threshold.size=512000
	
## Cluster Link [](id=cluster-link)

Set this to `true` to enable the cluster link. This is required if you want to cluster indexing and other features that depend on the cluster link.

	cluster.link.enabled=false

Set the JGroups properties for each channel, we support up to 10 transport channels and 1 single required control channel. Use as few transport channels as possible for best performance. By default, only one UDP control channel and one UDP transport channel are enabled. Channels can be configured by XML files that are located in the class path or by inline properties.

*Examples:*

	cluster.link.channel.properties.control=UDP(bind_addr=localhost;mcast_addr=${multicast.group.address["cluster-link-control"]};mcast_port=${multicast.group.port["cluster-link-control"]};ip_ttl=8;mcast_send_buf_size=150000;mcast_recv_buf_size=80000):PING(timeout=2000;num_initial_members=3):MERGE2(min_interval=5000;max_interval=10000):FD_SOCK:VERIFY_SUSPECT(timeout=1500):pbcast.NAKACK(gc_lag=50;retransmit_timeout=300,600,1200,2400,4800;max_xmit_size=8192;discard_delivered_msgs=true):UNICAST(timeout=300,600,1200,2400):pbcast.STABLE(desired_avg_gossip=20000):FRAG(frag_size=8096;down_thread=false;up_thread=false):pbcast.GMS(join_timeout=5000;join_retry_timeout=2000;shun=false;print_local_addr=true)
	cluster.link.channel.properties.transport.0=UDP(bind_addr=localhost;mcast_addr=${multicast.group.address["cluster-link-udp"]};mcast_port=${multicast.group.port["cluster-link-udp"]};ip_ttl=8;mcast_send_buf_size=150000;mcast_recv_buf_size=80000):PING(timeout=2000;num_initial_members=3):MERGE2(min_interval=5000;max_interval=10000):FD_SOCK:VERIFY_SUSPECT(timeout=1500):pbcast.NAKACK(gc_lag=50;retransmit_timeout=300,600,1200,2400,4800;max_xmit_size=8192;discard_delivered_msgs=true):UNICAST(timeout=300,600,1200,2400):pbcast.STABLE(desired_avg_gossip=20000):FRAG(frag_size=8096;down_thread=false;up_thread=false):pbcast.GMS(join_timeout=5000;join_retry_timeout=2000;shun=false;print_local_addr=true)
	cluster.link.channel.properties.transport.1=udp.xml
	cluster.link.channel.properties.transport.2=mping.xml

Set JGroups' system properties. System properties have higher priority than individual properties given to each channel. That means system properties will override individual properties.

	cluster.link.channel.system.properties=\
		#
		# Common
		#
		\
		jgroups.bind_addr:localhost,\
		#jgroups.bind_interface:eth0,\
		\
		#
		# Multicast
		#
		\
		jgroups.mping.mcast_addr:${multicast.group.address["cluster-link-mping"]},\
		jgroups.mping.mcast_port:${multicast.group.port["cluster-link-mping"]},\
		jgroups.mping.ip_ttl:8

Set this property to autodetect the default outgoing IP address so that JGroups can bind to it. The property must point to an address that is accessible to the portal server, [www.google.com](www.google.com) or your local gateway.

	cluster.link.autodetect.address=www.google.com:80

## Cluster Executor [](id=cluster-executor)

	cluster.executor.heartbeat.interval=5000

Set this to `true` to enable the cluster executor debugging. This will attach a debugging listener which will log every cluster event it receives.

	cluster.executor.debug.enabled=false

## Combo [](id=combo)

The combo servlet combines multiple JavaScript files into a bundle based on shared dependencies. This makes loading JavaScript files much faster. Set this to `false` if the combination should refresh when one of its JavaScript files has changed. This property should be set to `true` during development for easier debugging but set to `false` during production for faster performance.

	combo.check.timestamp=false

Set the minimum wait interval in milliseconds between timestamp checks on resource files. This property is only used if the property `combo.check.timestamp` is set to `true`.

	combo.check.timestamp.interval=1000

## Content Delivery Network [](id=content-delivery-network)

Set the hostname that will be used to serve static content via a CDN for requests made over the HTTP protocol. This property can be overridden dynamically at runtime by setting the URL parameter `cdn_host`. The value must always include the full protocol.

	cdn.host.http=

Set the hostname that will be used to serve static content via a CDN for requests made over the HTTPS protocol. This property can be overridden dynamically at runtime by setting the URL parameter `cdn_host`. The value must always include the full protocol.

	cdn.host.https=

## Counter [](id=counter)

The counter operates with its own data source to prevent deadlocks. By default, the data source created for the counter uses the same settings as those used to create the data source used for the rest of the portal. That happens because the counter service will look up the properties prefixed with `jdbc.default.` to create its data source. See the JDBC properties prefixed with `jdbc.default.` for more information.

Setting a different value for the counter JDBC prefix allows you to better fine tune the counter data source with its own set of configuration settings for high availability installations. Note that these settings, though separate, are a copy of the default settings with the newly overridden values.

	counter.jdbc.prefix=jdbc.default.

Set the number of increments between database updates to the Counter table. Set this value to a higher number for better performance.

	counter.increment=100

You can further fine tune the counter increment for specific counter names. This entry will ensure that the counter name `com.liferay.portal.model.Layout` or anything that starts with `com.liferay.portal.model.Layout#` will only increment by 1.

	counter.increment.com.liferay.portal.model.Layout=1
	
## Direct Servlet Context [](id=direct-servlet-context)

Set this to `true` to enable dispatching to a servlet directly to speed up request dispatching. This property only takes effect when a developer programmatically wraps a normal `ServletContext` with a `DirectServletContext`. See [http://issues.liferay.com/browse/LPS-13776](LPS-13776) for more information.

<!-- This link above appears to be broken.  -->

	direct.servlet.context.enabled=true

Set this to `true` to refresh the servlet associated with a JSP when the JSP has been modified. This property is not used unless the property `direct.servlet.context.enabled` is set to `true`.

	direct.servlet.context.reload=true

## Finalize Manager [](id=finalize-manager)

Set this to `true` to enable the finalize manager to use a separate thread to do clean up. Otherwise, finalize manager will only do clean up during registration.

	finalize.manager.thread.enabled=false	

## FreeMarker Engine [](id=freemarker-engine)


	freemarker.engine.cache.storage=com.liferay.portal.freemarker.LiferayCacheStorage
	freemarker.engine.localized.lookup=false
	freemarker.engine.modification.check.interval=60

Exception handler can have it's value set to the name of a class implementing FreeMarker `TemplateExceptionHandler` or `rethrow`, `debug`, `debug_html`, `ignore`.

	freemarker.engine.template.exception.handler=rethrow

Input a list of comma delimited class names that extend `com.liferay.portal.freemarker.FreeMarkerTemplateLoader`. These classes will run in sequence to allow you to find the applicable `TemplateLoader` to load a FreeMarker template.

	freemarker.engine.template.loaders=com.liferay.portal.freemarker.ServletTemplateLoader,com.liferay.portal.freemarker.JournalTemplateLoader,com.liferay.portal.freemarker.ThemeLoaderTemplateLoader

Input a list of comma delimited macros that will be loaded. These files must exist in the class path.

	freemarker.engine.macro.library=FTL_liferay.ftl as liferay	
	
## Google Apps [](id=google-apps)

Set the default user name and password for Google Apps integration. The domain used by Google Apps is retrieved from the portal's mail domain. See [http://code.google.com/apis/apps](http://code.google.com/apis/apps) for more information.

Google Apps integration is not used unless the property `mail.hook.impl` is set with the value `com.liferay.mail.util.GoogleHook`.

	google.apps.username=
	google.apps.password=	
	
## GZip [](id=gzip)

The GZip filter will compress files using the specified compression level. Set the value to `-1` to use the default compression level. Set the value between `0` and `9` for other compression levels as documented in `java.util.zip.Deflater`.

	gzip.compression.level=-1

## HTTP [](id=http)

See `system.properties` for more HTTP settings.

Set the maximum number of connections per host.

*Examples:*

	com.liferay.portal.util.HttpImpl.max.connections.per.host=2
	com.liferay.portal.util.HttpImpl.max.connections.per.host[rss.news.yahoo.com]=2

Set the maximum number of connections.

*Example:*

	com.liferay.portal.util.HttpImpl.max.total.connections=20

Set the proxy authentication type.

*Examples:*

	com.liferay.portal.util.HttpImpl.proxy.auth.type=username-password
	com.liferay.portal.util.HttpImpl.proxy.auth.type=ntlm

Set user name and password used for HTTP proxy authentication.

*Examples:*

	com.liferay.portal.util.HttpImpl.proxy.username=
	com.liferay.portal.util.HttpImpl.proxy.password=


Set additional properties for NTLM authentication.

*Examples:*

	com.liferay.portal.util.HttpImpl.proxy.ntlm.domain=
	com.liferay.portal.util.HttpImpl.proxy.ntlm.host=

Set the connection timeout when fetching HTTP content.

*Examples:*

	com.liferay.portal.util.HttpImpl.timeout=10000
	com.liferay.portal.util.HttpImpl.timeout[rss.news.yahoo.com]=10000
	
## HTTP Header Response [](id=http-header-response)

Set the level of verbosity to use for the Liferay-Portal field in the HTTP header response. Valid values are `full`, which gives all of the version information (e.g. Liferay Portal Community Edition 6.1.0 CE etc.) or `partial`, which gives only the name portion (e.g. Liferay Portal Community Edition).

	http.header.version.verbosity=full

## HTTP Tunneling [](id=http-tunneling)

Do not set this property to `false` in production environments. It overrides the SSL hostname verification and is used for self assigned certificates. See [http://issues.liferay.com/browse/LPS-18038](http://issues.liferay.com/browse/LPS-18038).

	com.liferay.portal.service.http.TunnelUtil.verify.ssl.hostname=true

## ImageMagick [](id=imagemagick)

Set this to `true` to enable ImageMagick. You must install Ghostscript and Imagemagick. See [http://www.ghostscript.com](http://www.ghostscript.com) and [http://www.imagemagick.org](http://www.imagemagick.org) for more information.

    imagemagick.enabled=false
    imagemagick.global.search.path[apple]=/opt/local/bin
    imagemagick.global.search.path[unix]=/usr/local/bin
    imagemagick.global.search.path[windows]=C:\\Program Files\\ImageMagick

## Invoker [](id=invoker)

The invoker filter will attempt to cache `InvokerFilterChain` objects based on the request URI and dispatcher. Set this property configure the maximum number of cached `InvokerFilterChain` objects. Set this property to `0` to disable caching of `InvokerFilterChain` objects.

	invoker.filter.chain.cache.size=10000	

## JCR [](id=jcr)

Liferay includes Jackrabbit [http://jackrabbit.apache.org](http://jackrabbit.apache.org) by default as its JSR-170 Java Content Repository.

    jcr.initialize.on.startup=false
    jcr.wrap.session=true

    jcr.workspace.name=liferay
    jcr.node.documentlibrary=documentlibrary

    jcr.jackrabbit.repository.root=${liferay.home}/data/jackrabbit
    jcr.jackrabbit.config.file.path=${jcr.jackrabbit.repository.root}/repository.xml
    jcr.jackrabbit.repository.home=${jcr.jackrabbit.repository.root}/home
    jcr.jackrabbit.credentials.username=none
    jcr.jackrabbit.credentials.password=none
    
## JSON [](id=json)

Input a list of comma delimited class names that cannot be invoked via JSON.
    
    json.service.invalid.class.names=\
        com.liferay.documentlibrary.service.DLLocalServiceUtil,\
        com.liferay.documentlibrary.service.DLServiceUtil,\
        com.liferay.mail.service.MailServiceUtil,\
        com.liferay.portal.service.CompanyServiceUtil,\
        com.liferay.portal.service.PortalServiceUtil,\
        com.liferay.portal.service.PortletServiceUtil    

## Live Users [](id=live-users)

Set this to `true` to enable tracking via Live Users.

	live.users.enabled=false

## Lock [](id=lock)

Set the lock expiration time for each class.

Locks for document library folders should expire after 1 day.

	lock.expiration.time.com.liferay.portlet.documentlibrary.model.DLFolder=86400000
	
Locks for document library files should never expire.
	
	lock.expiration.time.com.liferay.portlet.documentlibrary.model.DLFileEntry=0

Locks for message board threads should never expire.

	lock.expiration.time.com.liferay.portlet.messageboards.model.MBThread=0

## Mail [](id=ma-5)

Set the JNDI name to lookup the Java Mail session. If none is set, then the portal will attempt to create the Java Mail session based on the properties prefixed with `mail.session.`.

*Example:*

	mail.session.jndi.name=mail/MailSession

Set the properties used to create the Java Mail session. The property prefix `mail.session.` will be removed before it is used to create the session object. These properties will only be read if the property `mail.session.jndi.name` is not set.

	mail.session.mail.pop3.host=localhost
	mail.session.mail.pop3.password=
	mail.session.mail.pop3.port=110
	mail.session.mail.pop3.user=
	mail.session.mail.smtp.auth=false
	mail.session.mail.smtp.host=localhost
	mail.session.mail.smtp.password=
	mail.session.mail.smtp.port=25
	mail.session.mail.smtp.user=
	mail.session.mail.store.protocol=pop3
	mail.session.mail.transport.protocol=smtp

Set this to `false` if administrator should not be allowed to change the mail domain via the Admin portlet.

	mail.mx.update=true

Input a list of comma delimited email addresses that will receive a BCC of every email sent through the mail server.

	mail.audit.trail=

Set the name of a class that implements `com.liferay.mail.util.Hook`. The mail server will use this class to ensure that the mail and portal servers are synchronized on user information. The portal will not know how to add, update, or delete users from the mail server except through this hook.

*Examples:*

	mail.hook.impl=com.liferay.mail.util.CyrusHook
	mail.hook.impl=com.liferay.mail.util.DummyHook
	mail.hook.impl=com.liferay.mail.util.FuseMailHook
	mail.hook.impl=com.liferay.mail.util.GoogleHook
	mail.hook.impl=com.liferay.mail.util.SendmailHook
	mail.hook.impl=com.liferay.mail.util.ShellHook

## CyrusHook [](id=cyrushook)

Set the commands for adding, updating and deleting a user where %1% is the user id. Replace the password with the password for the cyrus user.

*Examples:*

	mail.hook.cyrus.add.user=cyrusadmin password create %1%
	mail.hook.cyrus.add.user=cyrus_adduser password %1%
	mail.hook.cyrus.delete.user=cyrusadmin password delete %1%
	mail.hook.cyrus.delete.user=cyrus_userdel password %1%
	mail.hook.cyrus.home=/home/cyrus

## FuseMailHook [](id=fusemailhook)

See [http://www.fusemail.com/support/email-hosting/api-documentation/](http://www.fusemail.com/support/email-hosting/api-documentation/) for more information. You must also update the `mail.account.finder` property.

	mail.hook.fusemail.url=https://www.fusemail.com/api/request.html
	mail.hook.fusemail.username=
	mail.hook.fusemail.password=
	mail.hook.fusemail.account.type=group_subaccount
	mail.hook.fusemail.group.parent=
	
## GoogleHook [](id=googlehook)

See the properties `google.apps.username` and `google.apps.password`.

## SendMailHook [](id=sendmailhook)

Set the commands for adding, updating and deleting a user where %1% is the user id and %2% is the password. Set the home and virtual user table information.

	mail.hook.sendmail.add.user=adduser %1% -s /bin/false
	mail.hook.sendmail.change.password=autopasswd %1% %2%
	mail.hook.sendmail.delete.user=userdel -r %1%
	mail.hook.sendmail.home=/home
	mail.hook.sendmail.virtusertable=/etc/mail/virtusertable
	mail.hook.sendmail.virtusertable.refresh=bash -c "makemap hash /etc/mail/virtusertable < /etc/mail/virtusertable"

## ShellHook [](id=shellhook)

Set the location of the shell script that will interface with any mail server.

	mail.hook.shell.script=/usr/sbin/mailadmin.ksh
	
## Minifier [](id=minifier)

The strip filter will attempt to cache inline minified CSS and JavaScript content. Set this property to configure the maximum pieces of cached content. Set this property to `0` to disable caching of inline minified content.

	minifier.inline.content.cache.size=10000

Input a list of comma delimited values that will cause the minified CSS to not be cached if those values are contained in the content.

	minifier.inline.content.cache.skip.css=

Input a list of comma delimited values that will cause the minified JavaScript to not be cached if those values are contained in the content.

	minifier.inline.content.cache.skip.javascript=getSessionId,encryptedUserId	
	
## Mobile Device Rules [](id=mobile-device-rules)

This property defines the postfix that will be localized and appended to the name of a copied rule group.

	mobile.device.rules.rule.group.copy.postfix=copy
	
## Monitoring [](id=monitoring)

Configure the appropriate level for monitoring Liferay. Valid values are: `HIGH`, `LOW`, `MEDIUM`, `OFF`.

	monitoring.level.com.liferay.monitoring.Portal=HIGH
	monitoring.level.com.liferay.monitoring.Portlet=HIGH

Set this to `true` to store data samples of the current request as a thread local variable. This allows you to obtain each request's statistics for further processing.

This property does not take effect unless you add `monitoring-spring.xml` to the property `spring.configs`.

	monitoring.data.sample.thread.local=false

Set this to `true` to monitor portal requests.

	monitoring.portal.request=false

Set this to `true` to monitor portlet action requests.

	monitoring.portlet.action.request=false

Set this to `true` to monitor portlet event requests.

	monitoring.portlet.event.request=false

Set this to `true` to monitor portlet render requests.

	monitoring.portlet.render.request=false

Set this to `true` to monitor portlet resource requests.

	monitoring.portlet.resource.request=false

Set this to `true` to show data samples at the bottom of each portal page as HTML comments. In order for data to show, the property `monitoring.data.sample.thread.local` must be set to `true`.

	monitoring.show.per.request.data.sample=false
	

	
## Multicast [](id=multicast)

Consolidate multicast address and port settings in one location for easier maintenance. These settings must correlate to your physical network configuration (i.e. firewall, switch and other network hardware matter) to ensure speedy and accurate communication across a cluster.

Each address and port combination represent a conversation that is made between different nodes. If they are not unique or correctly set, there will be a potential of unnecessary network traffic that may cause slower updates or inaccurate updates.

See the property `cluster.link.channel.properties.control`.

	multicast.group.address["cluster-link-control"]=239.255.0.1
	multicast.group.port["cluster-link-control"]=23301

See the properties `cluster.link.channel.properties.transport.0` and `cluster.link.channel.system.properties`.

	multicast.group.address["cluster-link-udp"]=239.255.0.2
	multicast.group.port["cluster-link-udp"]=23302

See the property `cluster.link.channel.system.properties`.

	multicast.group.address["cluster-link-mping"]=239.255.0.3
	multicast.group.port["cluster-link-mping"]=23303

See the properties `net.sf.ehcache.configurationResourceName` and `net.sf.ehcache.configurationResourceName.peerProviderProperties`.

	multicast.group.address["hibernate"]=239.255.0.4
	multicast.group.port["hibernate"]=23304

See the properties `ehcache.multi.vm.config.location` and `ehcache.multi.vm.config.location.peerProviderProperties`.

	multicast.group.address["multi-vm"]=239.255.0.5
	multicast.group.port["multi-vm"]=23305
	
## Notifications [](id=notifications)

Set the maximum number of transient events allowed to queue for a user. In case of excess events, the events with the closest expiration time are removed.

	notifications.max.events=100

## OpenOffice [](id=openoffice)

Enabling OpenOffice integration allows the Document Library portlet and the Wiki portlet to provide conversion functionality. This is tested with OpenOffice 2.3.x through 3.2.x. It is recommended that you have OpenOffice on the same machine. Using a remote host for the instance is not fully supported and could lead to various problems. To start OpenOffice as a service, run the command: 

	soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;"

	openoffice.server.enabled=false
	openoffice.server.host=127.0.0.1
	openoffice.server.port=8100
	openoffice.cache.enabled=true

Specify the file extensions of files to allow conversions from. Entries must be limited by what is supported by OpenOffice.

	openoffice.conversion.source.extensions[drawing]=odg
	openoffice.conversion.source.extensions[presentation]=odp,ppt,pptx,sxi
	openoffice.conversion.source.extensions[spreadsheet]=csv,ods,sxc,tsv,xls,xlsx
	openoffice.conversion.source.extensions[text]=doc,docx,html,odt,rtf,sxw,txt,wpd

Specify the file extensions of files to allow conversions to. Entries must be limited by what is supported by OpenOffice.

	openoffice.conversion.target.extensions[drawing]=pdf,svg,swf
	openoffice.conversion.target.extensions[presentation]=odp,pdf,ppt,swf,sxi
	openoffice.conversion.target.extensions[spreadsheet]=csv,ods,pdf,sxc,tsv,xls
	openoffice.conversion.target.extensions[text]=doc,odt,pdf,rtf,sxw,txt

## OSGi [](id=osgi)

Set this property to true to enable OSGi. Set this to false for compatibility with older application servers.

	osgi.enabled=false

Set the OSGi framework beginning start level. The framework will proceed to this start level before the Spring context is initialized.

	osgi.framework.beginning.start.level=6

Set the OSGi framework runtime start level. The framework will proceed to this start level after the Spring context is initialized.

	osgi.framework.runtime.start.level=10

Set the directory where the OSGi framework will store it's files.

	osgi.framework.storage=${liferay.home}/data/osgi

Set this to true to register Liferay's services into the OSGi framework.

	osgi.register.liferay.services=true

Set a comma delimited list of java packages that are exported to the OSGi framework as bundle symbolic names.

	osgi.system.bundle.export.packages=\
        com.liferay.portal.impl,\
        com.liferay.portal.service,\
        com.liferay.util.bridges,\
        com.liferay.util.java,\
        com.liferay.util.taglib,\
        org.springframework.aop,\
        org.springframework.asm,\
        org.springframework.aspects,\
        org.springframework.beans,\
        org.springframework.context,\
        org.springframework.context.support,\
        org.springframework.core,\
        org.springframework.expression,\
        org.springframework.jdbc,\
        org.springframework.jms,\
        org.springframework.orm,\
        org.springframework.oxm,\
        org.springframework.transaction,\
        org.springframework.web,\
        org.springframework.web.portlet,\
        org.springframework.web.servlet,\
        org.springframework.web.struts

Set a comma delimited list of java packages that are exported to the OSGi framework as extra system packages.

    osgi.system.packages.extra=\
        javax.annotation;version="1.0",\
        javax.annotation.security;version="1.0",\
        javax.el;version="2.2",\
        javax.mail;version="1.4",\
        javax.mail.event;version="1.4",\
        javax.mail.internet;version="1.4",\
        javax.mail.search;version="1.4",\
        javax.mail.util;version="1.4",\
        javax.portlet;version="2.0",\
        javax.portlet.filter;version="2.0",\
        javax.servlet;version="3.0.0",\
        javax.servlet.annotation;version="3.0.0",\
        javax.servlet.descriptor;version="3.0.0",\
        javax.servlet.http;version="3.0.0",\
        javax.servlet.jsp;version="2.2",\
        javax.servlet.jsp.el;version="2.2",\
        javax.servlet.jsp.tagext;version="2.2",\
        org.aopalliance.aop,\
        org.aopalliance.intercept,\
        org.apache.commons.logging;version="1.1.1",\
        org.slf4j;version="1.5.11",\
        org.slf4j.helpers;version="1.5.11",\
        org.slf4j.spi;version="1.5.11"	
	
## Poller [](id=poller)

Specify the notification events timout in milliseconds. This prevents the poller from locking up the application server.

	poller.notifications.timeout=1000

Specify the poller request timeout in milliseconds. This prevents the poller from locking up the application server.

	poller.request.timeout=1000

## POP [](id=pop)

Set this to `true` to enable polling of email notifications from a POP server. The user credentials are the same used for SMTP authentication and are specified in the `mail/MailSession` configuration for each application server.

	pop.server.notifications.enabled=false

Set the interval on which the `POPNotificationsMessageListener` will run. The value is set in one minute increments.

	pop.server.notifications.interval=1

Set this property to create a special MX subdomain to receive all portal related email (e.g. `events.liferay.com`). This means configuring a default inbox for the domain and receiving all emails into that inbox.

This approach may not be allowed for some organizations. If you cannot use the subdomain approach, unset this value and Liferay will use the `replyTo` address specified in the portlet preferences.

	pop.server.subdomain=events

## Quartz [](id=quartz)

	memory.cluster.scheduler.lock.cache.enabled=false

    memory.scheduler.org.quartz.jobStore.class=org.quartz.simpl.RAMJobStore
    memory.scheduler.org.quartz.scheduler.instanceId=AUTO
    memory.scheduler.org.quartz.scheduler.instanceName=MemoryQuartzSchedulerEngineInstance
    memory.scheduler.org.quartz.threadPool.class=org.quartz.simpl.SimpleThreadPool
    memory.scheduler.org.quartz.threadPool.threadCount=5
    memory.scheduler.org.quartz.threadPool.threadPriority=5

    persisted.scheduler.org.quartz.dataSource.ds.connectionProvider.class=com.liferay.portal.scheduler.quartz.QuartzConnectionProvider
    persisted.scheduler.org.quartz.jobStore.class=com.liferay.portal.scheduler.quartz.PortalJobStore
    persisted.scheduler.org.quartz.jobStore.dataSource=ds
    persisted.scheduler.org.quartz.jobStore.misfireThreshold=60000
    persisted.scheduler.org.quartz.jobStore.tablePrefix=QUARTZ_
    persisted.scheduler.org.quartz.jobStore.useProperties=false
    persisted.scheduler.org.quartz.scheduler.instanceId=AUTO
    persisted.scheduler.org.quartz.scheduler.instanceName=PersistedQuartzSchedulerEngineInstance
    persisted.scheduler.org.quartz.scheduler.skipUpdateCheck=true
    persisted.scheduler.org.quartz.threadPool.class=org.quartz.simpl.SimpleThreadPool
    persisted.scheduler.org.quartz.threadPool.threadCount=5
    persisted.scheduler.org.quartz.threadPool.threadPriority=5
    
## REST Proxy [](id=rest-proxy)

Input a list of comma delimited domains which the portal is allowed to make proxy request to. Input a blank list to allow any domain.

	rest.proxy.domains.allowed=search.yahooapis.com

## Robots.txt [](id=robots-txt)

    robots.txt.with.sitemap=com/liferay/portal/dependencies/robots_txt_with_sitemap.tmpl
    robots.txt.without.sitemap=com/liferay/portal/dependencies/robots_txt_without_sitemap.tmpl

## RSS [](id=12597699)

See the properties `main.servlet.hosts.allowed` and `main.servlet.https.required` on how to protect RSS feeds.

	rss.feeds.hosts.allowed=
	rss.feeds.https.required=false

## Sanitizer [](id=sanitizer)

Set the name of a class that implements `com.liferay.portal.kernel.sanitizer.Sanitizer`. This class is used to sanitize content.

	sanitizer.impl=com.liferay.portal.sanitizer.DummySanitizerImpl    

## Scheduler [](id=scheduler)

Set this to `false` to disable all scheduler classes defined in `liferay-portlet.xml` and in the property `scheduler.classes`.

	scheduler.enabled=true

Input a list of comma delimited class names that implement `com.liferay.portal.kernel.job.Scheduler`. These classes allow jobs to be scheduled on startup. These classes are not associated to any one portlet.

	scheduler.classes=

Set the maximum length of description, group name and job name fields.

	scheduler.description.max.length=120
	scheduler.group.name.max.length=80
	scheduler.job.name.max.length=80
	
## Scripting [](id=scripting)

Input a list of comma delimited class names that will never be available to user defined server side scripts. The special string `all_classes` disables access to any Java class. This property is only effective when using the Scripting service and invoking `eval` or `exec` methods.

	scripting.forbidden.classes=com.liferay.portal.kernel.scripting.ScriptingUtil,com.liferay.portal.scripting.ScriptingImpl,java.lang.System
	
Set the compile mode for the JRuby scripting engine.

*Examples:*

	scripting.jruby.compile.mode=force
	scripting.jruby.compile.mode=jit
	scripting.jruby.compile.mode=off

Set the threshold at which methods will be compiled when `jit` compile mode is enabled.

	scripting.jruby.compile.threshold=5

Set the load paths for the jruby engine. These paths allow jruby to locate gems and ruby code libraries in the classpath.

    scripting.jruby.load.paths=\
        classpath:/META-INF/jruby.home/lib/ruby/site_ruby/1.8,\
        classpath:/META-INF/jruby.home/lib/ruby/site_ruby/shared,\
        classpath:/META-INF/jruby.home/lib/ruby/1.8,\
        classpath:/gems/chunky_png-1.2.1/lib,\
        classpath:/gems/compass-0.11.5/lib,\
        classpath:/gems/fssm-0.2.7/lib,\
        classpath:/gems/sass-3.1.7/lib

## Search Container [](id=search-container)

Set the default number of entries to display per page.

	search.container.page.default.delta=20

Set the available values for the number of entries to display per page. An empty value, or commenting out the value, will disable delta resizing.

Always include the value specified in the property `search.container.page.default.delta`, since it is the default page size when no delta is specified. The absolute maximum allowed delta is `200`.

	search.container.page.delta.values=5,10,20,30,50,75

Set the maximum number of pages available above and below the currently displayed page.

	search.container.page.iterator.max.pages=25

Set this to `false` to remove the pagination controls above or below results.

	search.container.show.pagination.top=true
	search.container.show.pagination.bottom=true

## Sharepoint [](id=sharepoint)

Set the tokens for supported Sharepoint storage paths.

	sharepoint.storage.tokens=document_library

Set the class names for supported Sharepoint storage classes.

	sharepoint.storage.class[document_library]=com.liferay.portlet.documentlibrary.sharepoint.DLSharepointStorageImpl

## Sprite [](id=sprite)

Set the file names used for the auto generated sprites. The default file name used to be `.sprite.png` but is now `_sprite.png` because SiteMinder does not allow file names to start with a period. This property will not need to be changed unless your deployment has a conflict with file names that start with an underline.

	sprite.file.name=_sprite.png
	sprite.properties.file.name=_sprite.properties
	
Set the root directory for the autogenerated sprites. Not setting the directory means that generated sprites reside in the same location as the original source images.

	sprite.root.dir=	

## Strip [](id=strip)

Enter a list of comma delimited paths that should not have its content stripped by the strip filter.

	strip.ignore.paths=/document_library/get_file	
	
## Social Activity [](id=social-activity)

Set the length of the counter period for social activities. The value is in a number of days or the special keyword "month" to specify a month.

	social.activity.counter.period.length=month

Enter a list of comma separated values to use in drop down lists in the Social Activity portlet.

	social.activity.contribution.increments=0,1,2,3,4,5,10,20,50,100
	social.activity.contribution.limit.values=0,1,2,3,4,5,10,20
	social.activity.participation.increments=0,1,2,3,4,5,10,20,50,100
	social.activity.participation.limit.values=0,1,2,3,4,5,10,20	
	
## Social Bookmarks [](id=social-bookmarks)

The Blogs portlet allows for the posting of entries to various popular social bookmarking sites. The example ones are the defaults; to configure more, just add the site in the format below.

    social.bookmark.types=twitter,facebook,plusone

    social.bookmark.jsp[facebook]=/html/taglib/ui/social_bookmark/facebook.jsp
    social.bookmark.jsp[plusone]=/html/taglib/ui/social_bookmark/plusone.jsp
    social.bookmark.jsp[twitter]=/html/taglib/ui/social_bookmark/twitter.jsp

## Text Extraction [](id=text-extraction)

Set this to `true` if you want text extraction of certain MIME types to use a separate Java process. This will utilize extra resources from the operating system while improving the portal's stability.

	text.extraction.fork.process.enabled=false

Input a list of comma delimited MIME types that will trigger text extraction using a separate Java process.

	text.extraction.fork.process.mime.types=application/x-tika-ooxml

## Thread Dump [](id=thread-dump)

The thread dump filter will log a thread dump if the portal takes longer than the specified number of seconds to process. The thread dump filter must be enabled via the property `com.liferay.portal.servlet.filters.threaddump.ThreadDumpFilter`.

	thread.dump.speed.threshold=5

## User Notifications [](id=user-notifications)

Set this to `true` if you want users to acknowledge receipt of user notification events.

	user.notification.event.confirmation.enabled=false

## Vaadin [](id=vaadin)

Specify the location of the portal wide Vaadin themes and widget set (client side JavaScript).

	vaadin.resources.path=/html

Specify the base Vaadin theme to load automatically for all Vaadin portlets. A portlet can include an additional theme that is loaded after the shared theme.

	vaadin.theme=liferay

Specify the shared widget set (client side JavaScript) that is used by all Vaadin portlets running in the portal.

	vaadin.widgetset=com.vaadin.portal.gwt.PortalDefaultWidgetSet
    
## Velocity Engine [](id=velocity-engine)

Input a list of comma delimited class names that extend `com.liferay.util.velocity.VelocityResourceListener`. These classes will run in sequence to allow you to find the applicable `ResourceLoader` to load a Velocity template.

	velocity.engine.resource.listeners=com.liferay.portal.velocity.ServletVelocityResourceListener,com.liferay.portal.velocity.JournalTemplateVelocityResourceListener,com.liferay.portal.velocity.ThemeLoaderVelocityResourceListener,com.liferay.portal.velocity.ClassLoaderVelocityResourceListener

Set the Velocity resource managers. We extend the Velocity's default resource managers for better scalability.

Note that the modification check interval is not respected because the resource loader implementation does not know the last modified date of a resource. This means you will need to turn off caching if you want to be able to modify VM templates in themes and see the changes right away.

*Examples:*

	velocity.engine.resource.manager=com.liferay.portal.velocity.LiferayResourceManager
	velocity.engine.resource.manager.cache=com.liferay.portal.velocity.LiferayResourceCache
	velocity.engine.resource.manager.cache.enabled=true
	velocity.engine.resource.manager.modification.check.interval=0

Input a list of comma delimited macros that will be loaded. These files must exist in the class path.

	velocity.engine.velocimacro.library=VM_global_library.vm,VM_liferay.vm

Set the Velocity logging configuration.

	velocity.engine.logger=org.apache.velocity.runtime.log.SimpleLog4JLogSystem
	velocity.engine.logger.category=org.apache.velocity

## Virtual Hosts [](id=virtual-hosts)

Set the extensions that will be ignored for virtual hosts.

    virtual.hosts.ignore.extensions=\
        /c,\
        .css,\
        .gif,\
        .image/company_logo,\
        .ico,\
        .js,\
        .jpeg,\
        .jsp,\
        .png,\
        /portal/layout,\
        /portal/login,\
        /portal/logout

Set the hosts that will be ignored for virtual hosts.

	virtual.hosts.ignore.hosts=\
        127.0.0.1,\
        localhost

Set the paths that will be ignored for virtual hosts.

    virtual.hosts.ignore.paths=\
        /c,\
        \
        /c/portal/change_password,\
        /c/portal/extend_session,\
        /c/portal/extend_session_confirm,\
        /c/portal/json_service,\
        /c/portal/layout,\
        /c/portal/login,\
        /c/portal/logout,\
        /c/portal/portlet_url,\
        /c/portal/render_portlet,\
        /c/portal/reverse_ajax,\
        /c/portal/session_tree_js_click,\
        /c/portal/status,\
        /c/portal/update_layout,\
        /c/portal/update_terms_of_use,\
        /c/portal/upload_progress_poller,\
        \
        /c/layouts_admin/update_page

Specify the site name that will default to the company's virtual host. If the specified site has a virtual host, then that will take precedence. If it does not, then it will use the company's virtual host. This property is useful to remove `/web/guest` (or any other site) from the default URL. For example, if this property is not set, then the default URL may be [http://localhost:8080/web/guest/home](http://localhost:8080/web/guest/home). If this property is set, then the default URL may be [http://localhost:8080/home](http://localhost:8080/home).

	virtual.hosts.default.site.name=Guest
	
## XML Validation [](id=xml-validation)

Set this property to `false` to disable XML validation in the portal. XML validation should only be disabled if validation cannot be performed because the servers hosting the external DTD or XSD files are not available.

	xml.validation.enabled=true

## Xuggler [](id=xuggler)

Set this to `true` to enable conversion and previewing of videos in the Document Library portlet. See [http://www.xuggle.com/xuggler/downloads/build.jsp](http://www.xuggle.com/xuggler/downloads/build.jsp) for more information.

<!-- This link above appears to be broken.  -->

	xuggler.enabled=false

## JSP [](id=jsp)

Set the `JspWriter` buffer size. `JspFactoryWrapper` reads this value as the buffer size when creating new JspWriters. This value should be kept at `0` for performance reasons.

	jsp.writer.buffer.size=0	

## Servlet Filters [](id=servlet-filters)

The absolute redirects filter is used to ensure that all redirects are absolute. It should not be disabled because it also sets the company id in the request so that subsequent calls in the thread have the company id properly set. This filter should also always be the first filter in the list of filters.

	com.liferay.portal.servlet.filters.absoluteredirects.AbsoluteRedirectsFilter=true

The audit filter populates the `AuditRequestThreadLocal` with the appropriate request values to generate audit requests.

	com.liferay.portal.servlet.filters.audit.AuditFilter=false

The auto login filter processes the classes in the property `auto.login.hooks` to provide auto login functionality.

	com.liferay.portal.servlet.filters.autologin.AutoLoginFilter=true

The cache filter caches processed web content. See `ehcache.xml` to modify the cache expiration time to live.

	com.liferay.portal.servlet.filters.cache.CacheFilter=true

The CAS filter is used to provide CAS based single sign on.

	com.liferay.portal.servlet.filters.sso.cas.CASFilter=true

The char buffer pool filter enables char buffer pooling to minimize garbage collection of large char arrays. See the system property `com.liferay.portal.kernel.util.StringBundler.unsafe.create.limit`.

	com.liferay.portal.servlet.filters.charbufferpool.CharBufferPoolFilter=false

This double click filter will prevent double clicks at the server side. Prevention of double clicks is already in place on the client side. However, some sites require a more robust solution. This is turned off by default since most sites will not need it.

	com.liferay.portal.servlet.filters.doubleclick.DoubleClickFilter=false

The dynamic CSS filter is used to parse Sass CSS.

	com.liferay.portal.servlet.filters.dynamiccss.DynamicCSSFilter=true

The ETag filter is used to generate ETag headers.

	com.liferay.portal.servlet.filters.etag.ETagFilter=true

If the user can unzip compressed HTTP content, the GZip filter will zip up the HTTP content before sending it to the user. This will speed up page rendering for users that are on dial up.

	com.liferay.portal.servlet.filters.gzip.GZipFilter=true

The header filter is used to set request headers.

	com.liferay.portal.servlet.filters.header.HeaderFilter=true

The ignore filter will ignore certain files from being accessed directly from a browser.

	scom.liferay.portal.servlet.filters.ignore.IgnoreFilter=true

The I18n filter is used to internationalize URLs. See the property `locale.prepend.friendly.url.style` for more information.

	com.liferay.portal.servlet.filters.i18n.I18nFilter=true

The Language filter replaces JavaScript code that make a client side call to translate a piece of text with the actual translated value. For example, a typical piece of JavaScript code fits the pattern `Liferay.Language.get('key')` where `'key'` is the text to translate. This filter will replace the entire piece of code with the translated text. This is very useful because it will lower the number of client calls by translating the text before the browser receives the JavaScript file.

	com.liferay.portal.servlet.filters.language.LanguageFilter=true

The minifier filter is used to minify CSS and JavaScript.

	com.liferay.portal.servlet.filters.minifier.MinifierFilter=true

The monitoring filter monitors portal request performance.

	com.liferay.portal.servlet.filters.monitoring.MonitoringFilter=true

The NTLM filter is used to provide NTLM based single sign on.

	com.liferay.portal.servlet.filters.sso.ntlm.NtlmFilter=true

The NTLM post filter is used to fix known issues with NTLM and ajax requests. See [http://issues.liferay.com/browse/LPS-3795](http://issues.liferay.com/browse/LPS-3795).

	com.liferay.portal.servlet.filters.sso.ntlm.NtlmPostFilter=true

The OpenSSO filter is used to provide OpenSSO based single sign on.

	com.liferay.portal.servlet.filters.sso.opensso.OpenSSOFilter=true

The secure filter is used to protect servlets based on IP and protocol. See the properties `*.servlet.hosts.allowed` and `*.servlet.https.required`.

	com.liferay.portal.servlet.filters.secure.SecureFilter=true

The servlet authorizing filter allows external servlets to be authorized by the portal. See [http://issues.liferay.com/browse/LEP-4682](http://issues.liferay.com/browse/LEP-4682).

	com.liferay.portal.servlet.filters.servletauthorizing.ServletAuthorizingFilter=true

The session id filter ensures that only one session is created between http and https sessions. This is useful if you want users to login via https but have them view the rest of the site via http. This is disabled by default. Do not enable this unless you thoroughly understand how cookies, http and https work.

	com.liferay.portal.servlet.filters.sessionid.SessionIdFilter=false

The Sharepoint filter allows users to access documents in the Document Library directly from Microsoft Office using the Sharepoint protocol.

	com.liferay.portal.sharepoint.SharepointFilter=true

The strip filter will remove blank lines from the outputted content. This will speed up page rendering for users that are on dial up.

	com.liferay.portal.servlet.filters.strip.StripFilter=true

The theme preview filter generates a preview of the currently applied theme that can be used by the Dreamweaver Theming plugin. This is disabled by default. Set the `themePreview` parameter to `1` in the URL to access the theme preview for any page. For example, a URL can be [http://localhost:8080/web/guest?themePreview=1](http://localhost:8080/web/guest?themePreview=1).

	com.liferay.portal.servlet.filters.themepreview.ThemePreviewFilter=false

The thread dump filter will automatically log thread dumps when the portal is too slow. Behavior can be configured via the property `thread.dump.speed.threshold`.

	com.liferay.portal.servlet.filters.threaddump.ThreadDumpFilter=false

The thread local filter cleans up short lived thread locals managed by `CentralizedThreadLocal` to prevent memory leaks.

	com.liferay.portal.servlet.filters.threadlocal.ThreadLocalFilter=true

The unsynchronized print writer pool filter enables pooling print writers to minimize the creation of `java.io.PrintWriter` instances because it is an expensive action.

	com.liferay.portal.servlet.filters.unsyncprintwriterpool.UnsyncPrintWriterPoolFilter=true

The valid HTML filter will move JavaScript that is outside of the closing body tag to its proper place inside the body tag. Most sites will prefer to leave this filter disabled because having JavaScript outside of the body tag causes the page to render faster. However, the side effect is that it will also make the site unaccessible to screen readers because the HTML is technically invalid. Setting this property to `true` optimizes for accessibility while setting this property to `false` optimzes for browser performance.

	com.liferay.portal.servlet.filters.validhtml.ValidHtmlFilter=false 

The virtual host filter maps hosts to public and private pages. For example, if the public virtual host is `www.helloworld.com` and the friendly URL is `/helloworld`, then [http://www.helloworld.com](http://www.helloworld.com) is mapped to [http://localhost:8080/web/helloworld](http://localhost:8080/web/helloworld).

	com.liferay.portal.servlet.filters.virtualhost.VirtualHostFilter=true
    
## Upload Servlet Request [](id=upload-servlet-request)

Set the maximum file size. Default is 1024 * 1024 * 100.

	com.liferay.portal.upload.UploadServletRequestImpl.max.size=104857600

Set the temp directory for uploaded files.

*Example:*

	com.liferay.portal.upload.UploadServletRequestImpl.temp.dir=C:/Temp

Set the threshold size to prevent extraneous serialization of uploaded data.

	com.liferay.portal.upload.LiferayFileItem.threshold.size=262144

Set the threshold size to prevent out of memory exceptions caused by caching excessively large uploaded data. Default is 1024 * 1024 * 10.

	com.liferay.portal.upload.LiferayInputStream.threshold.size=10485760

## Web Server [](id=web-server)

Set the HTTP and HTTPs ports when running the portal in a J2EE server that is sitting behind another web server like Apache. Set the values to `-1` if the portal is not running behind another web server like Apache.

	web.server.http.port=-1
	web.server.https.port=-1

Set the hostname that will be used when the portlet generates URLs. Leaving this blank will mean the host is derived from the servlet container.

	web.server.host=

Set the preferred protocol.

*Example:*

	web.server.protocol=https

Set this to `true` to display the server name at the bottom of every page. This is useful when testing clustering configurations so that you can know which node you are accessing.

	web.server.display.node=false

Set this to `true` to enable support for legacy proxy servers (Apache 1.x).

	web.server.proxy.legacy.mode=false

## WebDAV [](id=webdav)

Set a list of files for the WebDAV servlet to ignore processing.

	webdav.ignore=.DS_Store,.metadata_index_homes_only,.metadata_never_index,.Spotlight-V100,.TemporaryItems,.Trashes,Backups.backupdb
	
Specify the number of minutes before a generated nonce expires. When a client contacts the server with an expired nonce, the server will send back a HTTP error 401 with stale=true.

	webdav.nonce.expiration=30	

## Main Servlet [](id=main-servlet)

Servlets can be protected by `com.liferay.portal.servlet.filters.secure.SecureFilter`.

Input a list of comma delimited IPs that can access this servlet. Input a blank list to allow any IP to access this servlet. `SERVER_IP` will be replaced with the IP of the host server.

	main.servlet.hosts.allowed=

Set this to `true` if this servlet can only be accessed via https.

	main.servlet.https.required=false
	
## Atom Servlet [](id=atom-servlet)

See the properties `main.servlet.hosts.allowed` and `main.servlet.https.required` on how to protect this servlet.

	atom.servlet.hosts.allowed=127.0.0.1,SERVER_IP
	atom.servlet.https.required=false	

## Axis Servlet [](id=axis-servlet)

See the properties `main.servlet.hosts.allowed` and `main.servlet.https.required` on how to protect this servlet.

	axis.servlet.hosts.allowed=127.0.0.1,SERVER_IP
	axis.servlet.https.required=false

## Google Gadget Servlet [](id=google-gadget-servlet)

Set the servlet mapping for the Google Gadget servlet.

	google.gadget.servlet.mapping=/google_gadget

## JSON Servlet [](id=json-servlet)

See the properties `main.servlet.hosts.allowed` and `main.servlet.https.required` on how to protect this servlet.

	json.servlet.hosts.allowed=
	json.servlet.https.required=false
	
## JSON Web Service Servlet [](id=json-web-service-servlet)

See the properties `main.servlet.hosts.allowed` and `main.servlet.https.required` on how to protect this servlet.

	jsonws.servlet.hosts.allowed=
	jsonws.servlet.https.required=false	

## Liferay Tunnel Servlet [](id=liferay-tunnel-servlet)

See the properties `main.servlet.hosts.allowed` and `main.servlet.https.required` on how to protect this servlet.

	tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP
	tunnel.servlet.https.required=false

## Netvibes Servlet [](id=netvibes-servlet)

Set the servlet mapping for the Netvibes servlet.

	netvibes.servlet.mapping=/netvibes

## Spring Remoting Servlet [](id=spring-remoting-servlet)

See the properties `main.servlet.hosts.allowed` and `main.servlet.https.required` on how to protect this servlet.

	spring.remoting.servlet.hosts.allowed=127.0.0.1,SERVER_IP
	spring.remoting.servlet.https.required=false
	
## Tunnel Servlet

See the properties `main.servlet.hosts.allowed` and `main.servlet.https.required` on how to protect this servlet.

    tunnel.servlet.hosts.allowed=127.0.0.1,SERVER_IP
    tunnel.servlet.https.required=false	
	
## Web Server Servlet [](id=web-server-servlet)

Set this to a comma delimited list of MIME types to send an Accept-Ranges header.

    web.server.servlet.accept.ranges.mime.types=audio/basic,audio/mid,audio/midi,audio/mod,audio/mp3,audio/mpeg,audio/mpeg3,audio/ogg,audio/vorbis,audio/wav,audio/x-mid,audio/x-midi,audio/x-mod,audio/x-mpeg,audio/x-pn-realaudio,audio/x-realaudio,audio/x-wav,video/avi,video/mp4,video/mpeg,video/ogg,video/quicktime,video/x-flv,video/x-m4v,video/x-ms-wmv,video/x-msvideo

Set this property to true to enable directory indexing.

    web.server.servlet.directory.indexing.enabled=false

Set the maximum range fields that are allowed to be requested by a browser.

    web.server.servlet.max.range.fields=10

Set the level of verbosity to use in the server information line printed by the web server servlet. Valid values are `full`, which gives all of the version information (e.g. Liferay Portal Community Edition 6.1.0 CE etc.) or `partial`, which gives only the name portion (e.g. Liferay Portal Community Edition).

    web.server.servlet.version.verbosity=full
    
## WebDAV Servlet [](id=webdav-servlet)

See the properties `main.servlet.hosts.allowed` and `main.servlet.https.required` on how to protect this servlet.

	webdav.servlet.hosts.allowed=
	webdav.servlet.https.required=false    

## Widget Servlet [](id=lp-6-1-ugen20-widget-servlet-0)

Set the servlet mapping for the widget servlet.

	widget.servlet.mapping=/widget

## Admin Portlet [](id=admin-portlet)

Input a list of default group names separated by `\n` characters that are associated with newly created users.

	admin.default.group.names=

Input a list of default role names separated by `\n` characters that are associated with newly created users.

	admin.default.role.names=Power User\nUser

Input a list of default user group names separated by `\n` characters that are associated with newly created users.

	admin.default.user.group.names=

Set this to `true` to ensure that a user is synchronized with the default associations of groups, roles and user groups upon every login. Set this to `false` if default associations should only be applied to a user when a user is created.

	admin.sync.default.associations=false

Input a list of mail host names separated by \n characters.

	admin.mail.host.names=

Input a list of reserved screen names separated by \n characters.

	admin.reserved.screen.names=

Input a list of reserved email addresses separated by \n characters.

	admin.reserved.email.addresses=

Configure email notification settings.

	admin.email.from.name=Joe Bloggs
	admin.email.from.address=test@liferay.com
	
	admin.email.user.added.enabled=true
	admin.email.user.added.subject=com/liferay/portlet/admin/dependencies/email_user_added_subject.tmpl
	admin.email.user.added.body=com/liferay/portlet/admin/dependencies/email_user_added_body.tmpl
	admin.email.user.added.no.password.body=com/liferay/portlet/admin/dependencies/email_user_added_no_password_body.tmpl
	
	admin.email.password.reset.subject=com/liferay/portlet/admin/dependencies/email_password_reset_subject.tmpl
	admin.email.password.reset.body=com/liferay/portlet/admin/dependencies/email_password_reset_body.tmpl
	
	admin.email.password.sent.subject=com/liferay/portlet/admin/dependencies/email_password_sent_subject.tmpl
	admin.email.password.sent.body=com/liferay/portlet/admin/dependencies/email_password_sent_body.tmpl
	
	admin.email.verification.subject=com/liferay/portlet/admin/dependencies/email_verification_subject.tmpl
	admin.email.verification.body=com/liferay/portlet/admin/dependencies/email_verification_body.tmpl

## Announcements Portlet [](id=announcements-portlet)

Configure email notification settings.

	announcements.email.from.name=
	announcements.email.from.address=

	announcements.email.to.name=
	announcements.email.to.address=noreply@liferay.com

	announcements.email.subject=com/liferay/portlet/announcements/dependencies/email_subject.tmpl
	announcements.email.body=com/liferay/portlet/announcements/dependencies/email_body.tmpl

Set the list of announcement types. The display text of each of the announcement types is set in `content/Language.properties`.

	announcements.entry.types=general,news,test

Set the interval on which the `CheckEntryMessageListener` will run. The value is set in one minute increments.

	announcements.entry.check.interval=15

## Asset [](id=asset)

Input a list of comma delimited default properties for new categories. Each item of the list should have the following format: `key:value`.

	asset.categories.properties.default=

Set the following to `false` to specify that searching and browsing using categories should only show assets that have been assigned the selected category explicitly. When set to `true`, the children categories are also included in the search.

	asset.categories.search.hierarchical=true

Set this to `true` to enable incrementing the view counter for assets.

	asset.entry.increment.view.counter.enabled=true

Input a class name that extends `com.liferay.portlet.asset.util.BaseAssetEntryValidator`. This class will be called to validate entries. The `DefaultAssetEntryValidator` class is just an empty class that doesn't actually do any validation. The `MinimalAssetEntryValidator` requires all entities to have at least one tag.

*Examples:*

	asset.entry.validator=com.liferay.portlet.asset.util.DefaultAssetEntryValidator
	asset.entry.validator=com.liferay.portlet.asset.util.MinimalAssetEntryValidator

Set the limit for results used when performing asset searches that are subsequently filtered by permissions.

	asset.filter.search.limit=5000

Input a list of comma delimited default tag properties for new tags. Each item of the list should have the following format: `key:value`.

	asset.tag.properties.default=

Set this to `true` to enable tag suggestions.

	asset.tag.suggestions.enabled=true

Set the name of the default vocabulary which will be created by default.

	asset.vocabulary.default=Topic

Set a property with the prefix `asset.renderer.enabled.` and a suffix with the asset renderer factory class name to enable or disable an asset renderer factory. The default setting is `true`. See [http://issues.liferay.com/browse/LPS-6085](http://issues.liferay.com/browse/LPS-6085) for more information.

*Examples:*

	asset.renderer.enabled.com.liferay.portlet.documentlibrary.asset.DLFileEntryAssetRendererFactory=false
	asset.renderer.enabled.com.liferay.portlet.messageboards.asset.MBDiscussionAssetRendererFactory=false

## Asset Publisher Portlet [](id=asset-publisher-portlet)

Input a list of comma separated display styles that will be available in the configuration screen of Asset Publisher portlet.

	asset.publisher.display.styles=table,title-list,abstracts,full-content	
	
## Blogs Portlet [](id=blogs-portlet)

Configure email notification settings.

	blogs.email.from.name=
	blogs.email.from.address=

	blogs.email.entry.added.enabled=true
	blogs.email.entry.added.subject=com/liferay/portlet/blogs/dependencies/email_entry_added_subject.tmpl
	blogs.email.entry.added.body=com/liferay/portlet/blogs/dependencies/email_entry_added_body.tmpl

	blogs.email.entry.updated.enabled=true
	blogs.email.entry.updated.subject=com/liferay/portlet/blogs/dependencies/email_entry_updated_subject.tmpl
	blogs.email.entry.updated.body=com/liferay/portlet/blogs/dependencies/email_entry_updated_body.tmpl

	blogs.page.abstract.length=400

	blogs.rss.abstract.length=200

Set the excerpt length for linkbacks.

	blogs.linkback.excerpt.length=200

Set the interval on which the `LinkbackMessageListener` will run. The value is set in one minute increments.

	blogs.linkback.job.interval=5

Set this to `true` to enable pingbacks.

	blogs.pingback.enabled=true

Set this to `true` to enable trackbacks.

	blogs.trackback.enabled=true

Set this to `true` to enable pinging Google on new and updated blog entries.

	blogs.ping.google.enabled=true
	
Set the interval on which the CheckEntryMessageListener will run. The value is set in one minute increments.

    blogs.entry.check.interval=1

Set this to `true` to enable comments for blogs entries.

	blogs.entry.comments.enabled=true

Set this to `true` to enable previous and next navigation for blogs entries.

	blogs.entry.previous.and.next.navigation.enabled=true

Set the maximum file size and valid file extensions for images. A value of `0` for the maximum file size can be used to indicate unlimited file size. However, the maximum file size allowed is set in the property `com.liferay.portal.upload.UploadServletRequestImpl.max.size`.

	blogs.image.small.max.size=51200

A file extension of `*` will permit all file extensions.

	blogs.image.extensions=.gif,.jpeg,.jpg,.png

Set this to `true` if blogs should be published to live by default.

	blogs.publish.to.live.by.default=false

## Breadcrumb Portlet [](id=breadcrumb-portlet)

Set this to `true` to show the Guest Site as the top level parent in the breadcrumbs. It will only show if it has at least one page.

	breadcrumb.show.guest.group=true

Set this to `true` to show the path of parent sites or organizations in the breadcrumbs. A site will only be shown if it has at least one page.

	breadcrumb.show.parent.groups=true
	
Specify the options that will be provided to the user in the edit configuration mode of the portlet.

	breadcrumb.display.style.options=horizontal,vertical

## Calendar Portlet [](id=calendar-portlet)

Set the list of event types. The display text of each of the event types is set in `content/Language.properties`.

	calendar.event.types=anniversary,appointment,bill-payment,birthday,breakfast,call,chat,class,club-event,concert,dinner,event,graduation,happy-hour,holiday,interview,lunch,meeting,movie,net-event,other,party,performance,press-release,reunion,sports-event,training,travel,tv-show,vacation,wedding

Set the interval on which the `CheckEventMessageListener` will run. The value is set in one minute increments.

	calendar.event.check.interval=15

Set this to `true` to enable ratings for calendar events.

	calendar.event.ratings.enabled=true

Set this to `true` to enable comments for calendar events.

	calendar.event.comments.enabled=true

Configure email notification settings.

	calendar.email.from.name=
	calendar.email.from.address=

	calendar.email.event.reminder.enabled=true
	calendar.email.event.reminder.subject=com/liferay/portlet/calendar/dependencies/email_event_reminder_subject.tmpl
	calendar.email.event.reminder.body=com/liferay/portlet/calendar/dependencies/email_event_reminder_body.tmpl

## Discussion Tag Library [](id=discussion-tag-library)

Configure email notification settings for discussions.

	discussion.email.comments.added.enabled=true
	discussion.email.subject=com/liferay/portlet/messageboards/dependencies/discussion_email_subject.tmpl
	discussion.email.body=com/liferay/portlet/messageboards/dependencies/discussion_email_body.tmpl

Set this to `true` if the subscription checkbox for discussion comments will be checked by default.

	discussion.subscribe.by.default=true

Set the thread view for discussion comments. This will affect Blogs, Document Library and other portlets that use the Discussion tag library to provide comments. Set the value to `flat` to paginate comments. Set the value to `combination` to show all comments in one page along with a tree view of the comments.

*Examples:*

	discussion.thread.view=combination
	discussion.thread.view=flat
	
Set this property to `true` if users can edit their own discussion comments even if they do not have the permissions defined through Site Members role.

    discussion.comments.always.editable.by.owner=false	

## Document Library Portlet [](id=document-library-portlet)

Set this property to `true` to enable execution of antivirus check when files are submitted into a store. Setting this value to `true` will prevent any potential virus files from entering the store but will not allow for file quarantines.

	dl.store.antivirus.enabled=false

Set the name of a class that implements `com.liferay.portlet.documentlibrary.antivirus.AntivirusScanner`. The document library server will use this to scan documents for viruses.

*Example:*

	dl.store.antivirus.impl=com.liferay.portlet.documentlibrary.antivirus.DummyAntivirusScannerImpl
	dl.store.antivirus.impl=com.liferay.portlet.documentlibrary.antivirus.ClamAntivirusScannerImpl

Set the name of a class that implements `com.liferay.portlet.documentlibrary.store.Store`. The document library server will use this to persist documents.

*Examples:*

	dl.store.impl=com.liferay.portlet.documentlibrary.store.AdvancedFileSystemStore
	dl.store.impl=com.liferay.portlet.documentlibrary.store.CMISStore
	dl.store.impl=com.liferay.portlet.documentlibrary.store.DBStore
	dl.store.impl=com.liferay.portlet.documentlibrary.store.FileSystemStore
	dl.store.impl=com.liferay.portlet.documentlibrary.store.JCRStore
	dl.store.impl=com.liferay.portlet.documentlibrary.store.S3Store

CMISStore

	dl.store.cmis.credentials.username=none
	dl.store.cmis.credentials.password=none
	dl.store.cmis.repository.url=http://localhost:8080/alfresco/service/api/cmis
	dl.store.cmis.system.root.dir=Liferay Home

FileSystemStore

	dl.store.file.system.root.dir=${liferay.home}/data/document_library

JCRStore

	dl.store.jcr.fetch.delay=500
	dl.store.jcr.fetch.max.failures=5

Set this to `true` to allow version labels to be moved when updated. This provides a workaround to duplicate version labels that may result when using JCRStore.

	dl.store.jcr.move.version.labels=false

S3Store

	dl.store.s3.access.key=
	dl.store.s3.secret.key=
	dl.store.s3.bucket.name=

Set the maximum file size and valid file extensions for documents. A value of `0` for the maximum file size can be used to indicate unlimited file size. However, the maximum file size allowed is set in the property `com.liferay.portal.upload.UploadServletRequestImpl.max.size`.

*Examples:*

	dl.file.max.size=307200
	dl.file.max.size=1024000
	dl.file.max.size=3072000

A file extension of `*` will permit all file extensions.

*Examples:*

	dl.file.extensions=*
	dl.file.extensions=.bmp,.css,.doc,.docx,.dot,.gif,.gz,.htm,.html,.jpg,.js,.lar,.odb,.odf,.odg,.odp,.ods,.odt,.pdf,.png,.ppt,.pptx,.rtf,.swf,.sxc,.sxi,.sxw,.tar,.tiff,.tgz,.txt,.vsd,.xls,.xlsx,.xml,.zip,.jrxml

Set this to `false` to allow users to update file entries by uploading a file with an extension different from the one of the originally uploaded file. There is a known issue where setting this to `true` will break OSX compatibility. See [http://issues.liferay.com/browse/LPS-10770](http://issues.liferay.com/browse/LPS-10770) for more information.

	dl.file.extensions.strict.check=false
	
You can map a PNG for generic thumbnails by adding the image to the theme's file system folder. For example, the generic thumbnail for documents can be found in: `/html/themes/_unstyled/images/file_system/large/document.png`.

	dl.file.generic.names=compressed,document,flash,image,music,pdf,presentation,spreadsheet,video
	dl.file.generic.extensions[compressed]=lar,rar,zip
	dl.file.generic.extensions[document]=doc,docx,pages,rtf,odt
	dl.file.generic.extensions[flash]=flv,swf
	dl.file.generic.extensions[image]=bmp,gif,jpeg,jpg,odg,png,svg
	dl.file.generic.extensions[music]=acc,mid,mp3,ogg,wav,wma
	dl.file.generic.extensions[pdf]=pdf
	dl.file.generic.extensions[presentation]=key,keynote,odp,pps,ppt,pptx
	dl.file.generic.extensions[spreadsheet]=csv,numbers,ods,xls,xlsx
	dl.file.generic.extensions[video]=avi,m4v,mov,mp4,mpg,qt,rm,wmv

Set the maximum file size for indexing file contents. Files larger than this property will not have their contents indexed, only their metadata will be indexed. A value of `-1` indicates that all file contents will be indexed. A value of `0` indicates that no file contents will be indexed.

	dl.file.indexing.max.size=10485760

Set the file extensions that will be ignored when indexing file contents. Files with these extensions will not have their contents indexed, only their metadata will be indexed.

*Examples:*

	dl.file.indexing.ignore.extensions=
	dl.file.indexing.ignore.extensions=.pdf,.ppt

You can map a GIF for the extension by adding the image to the theme's image display and document library folder. The wildcard extension of `*` will be ignored. For example, the default image for the DOC extension would be found in: `/html/themes/_unstyled/images/file_system/small/doc.gif`.

	dl.file.icons=.bmp,.css,.doc,.docx,.dot,.gif,.gz,.htm,.html,.jpeg,.jpg,.js,.lar,.odb,.odf,.odg,.odp,.ods,.odt,.pdf,.png,.ppt,.pptx,.rtf,.swf,.sxc,.sxi,.sxw,.tar,.tiff,.tgz,.txt,.vsd,.xls,.xlsx,.xml,.zip,.jrxml

Set which files extensions are comparable by the diff tool. Any binary files listed here will only be comparable if OpenOffice is enabled and the the file is convertable to text.

	dl.comparable.file.extensions=.css,.doc,.docx,.js,.htm,.html,.odt,.rtf,.sxw,.txt,.xml

Set this to `true` to enable comments for document library files.

	dl.file.entry.comments.enabled=true

Set this to `true` to enable drafts for document library files.

	dl.file.entry.drafts.enabled=false

Input a list of comma delimited class names that implement `com.liferay.portlet.documentlibrary.util.DLProcessor`. These classes will trigger asynchronous processing for document library files.

`com.liferay.portlet.documentlibrary.util.PDFProcessor` will process all PDF files and, if OpenOffice is enabled, all formats convertable to PDF. Image generation will use PDFBox by default unless ImageMagick is enabled. PDFBox is less accurate in image generation and has trouble with certain fonts.

	dl.file.entry.processors=com.liferay.portlet.documentlibrary.util.AudioProcessor,com.liferay.portlet.documentlibrary.util.ImageProcessor,com.liferay.portlet.documentlibrary.util.PDFProcessor,com.liferay.portlet.documentlibrary.util.RawMetadataProcessor,com.liferay.portlet.documentlibrary.util.VideoProcessor

Set this to `true` to enable the read count for document library files.

	dl.file.entry.read.count.enabled=true

Set the interval on which the `CheckFileRankMessageListener` will run. The value is set in one minute increments.

	dl.file.rank.check.interval=15

Set this to `true` to enable file rank for document library files.

	dl.file.rank.enabled=true

Set this to the maximum number of file ranks to maintain.

	dl.file.rank.max.size=5

Set the values related to preview and thumbnail generation for document library files. The classes enabled under `dl.file.entry.processors` will utilize these parameters to generate the necessary files.

Some parameters are applied universally across all processors (e.g., `dl.file.entry.thumbnail.max.height`) while others are specific to certain types of generation, as specified (e.g., `dl.file.entry.thumbnail.document.depth`).

See the properties `imagemagick.enabled`, `openoffice.server.enabled` and `xuggler.enabled`.

*Examples:*

	dl.file.entry.preview.enabled=true
    dl.file.entry.preview.document.depth=8
    dl.file.entry.preview.document.dpi=300
    dl.file.entry.preview.document.max.height=0
    dl.file.entry.preview.document.max.width=1000
    dl.file.entry.preview.video.height=360
    dl.file.entry.preview.video.width=640
    dl.file.entry.thumbnail.enabled=true
    dl.file.entry.thumbnail.max.height=128
    dl.file.entry.thumbnail.max.width=128
    dl.file.entry.thumbnail.custom1.max.height=100
    dl.file.entry.thumbnail.custom1.max.width=100
    dl.file.entry.thumbnail.custom2.max.height=0
    dl.file.entry.thumbnail.custom2.max.width=0
    dl.file.entry.thumbnail.document.depth=8
    dl.file.entry.thumbnail.document.dpi=72
    dl.file.entry.thumbnail.video.frame.percentage=25

Input a list of comma delimited audio MIME types that will trigger
generation of video previews.

	dl.file.entry.preview.audio.mime.types=audio/basic,audio/mid,audio/midi,audio/mod,audio/mp3,audio/mpeg,audio/mpeg3,audio/wav,audio/x-mid,audio/x-midi,audio/x-mod,audio/x-mpeg,audio/x-pn-realaudio,audio/x-realaudio,audio/x-wav

Input a list of comma delimited video MIME types that will trigger generation of image previews.

	dl.file.entry.preview.image.mime.types=image/bmp,image/gif,image/jpeg,image/png,image/tiff,image/x-ms-bmp,image/x-tiff

Input a list of comma delimited video MIME types that will trigger generation of video previews.

	dl.file.entry.preview.video.mime.types=video/avi,video/mp4,video/mpeg,video/quicktime,video/x-flv,video/x-ms-wmv,video/x-msvideo

Set this to `true` if document library should be published to live by default.

	dl.publish.to.live.by.default=true

Input a list of comma delimited class names of the third party repositories that extend `com.liferay.portal.kernel.repository.BaseRepositoryImpl`.

	dl.repository.impl=com.liferay.portal.repository.cmis.CMISAtomPubRepository,com.liferay.portal.repository.cmis.CMISWebServicesRepository

Sets the depth of mapped CMIS entries to remove when a deletion is called from within the portal. The deeper the setting, the more calls to the server required. When a deletion is called from outside the portal, mapped CMIS entries remain stagnant in the database. Expected values are: `-1` for deep, `0` for none and `1` for shallow.

	dl.repository.cmis.delete.depth=1

Set the list of supported display views.

	dl.display.views=icon,descriptive,list
	
Set the default display view.

	dl.default.display.view=icon

## Dockbar Portlet [](id=dockbar-portlet)

Set the portlet ids that will be shown directly in the *Add Application* menu.

	dockbar.add.portlets=56,101,110,71
	
## Dynamic Data Lists Portlet [](id=dynamic-data-lists-portlet)

Set this to `true` if dynamic data lists record set keys should always be autogenerated.

	dynamic.data.lists.record.set.force.autogenerate.key=false

Set the storage type that will be used to store the dynamic data lists records. Valid values are: `expando` and `xml`.

	dynamic.data.lists.storage.type=xml

Specify the path to the template used for providing error messages on Dynamic Data Lists templates.

	dynamic.data.lists.error.template.freemarker=com/liferay/portlet/dynamicdatalists/dependencies/error.ftl
	dynamic.data.lists.error.template.velocity=com/liferay/portlet/dynamicdatalists/dependencies/error.vm

Set the parser for each language type. A parser is only needed for language types that require parsing. The classes must implement `com.liferay.portal.kernel.templateparser.TemplateParser`.

	dynamic.data.lists.template.language.parser[ftl]=com.liferay.portlet.dynamicdatalists.util.FreeMarkerTemplateParser
	dynamic.data.lists.template.language.parser[vm]=com.liferay.portlet.dynamicdatalists.util.VelocityTemplateParser

Input a list of comma delimited class names that extend `com.liferay.portal.kernel.templateparser.BaseTransformerListener`. These classes will run in sequence to allow you to modify the XML and XSL before it is transformed and allow you to modify the final output.

	dynamic.data.lists.transformer.listener=	
	
## Dynamic Data Mapping Portlet [](id=dynamic-data-mapping-portlet)

Set this to `true` if dynamic data mapping structure keys should always be autogenerated.

	dynamic.data.mapping.structure.force.autogenerate.id=false

Set the language types available for templates.

	dynamic.data.mapping.template.language.types=ftl,vm


Set the location of the default content for each language type.

	dynamic.data.mapping.template.language.content[ftl]=com/liferay/portlet/dynamicdatamapping/dependencies/template.ftl
	dynamic.data.mapping.template.language.content[vm]=com/liferay/portlet/dynamicdatamapping/dependencies/template.vm
	
## Flags Portlet [](id=flags-portlet)

Input a list of questions used for flag reasons.

	flags.reasons=sexual-content,violent-or-repulsive-content,hateful-or-abusive-content,harmful-dangerous-acts,spam,infringes-my-rights

Configure email notification settings.

	flags.email.from.name=
	flags.email.from.address=

	flags.email.subject=com/liferay/portlet/flags/dependencies/email_flag_subject.tmpl
	flags.email.body=com/liferay/portlet/flags/dependencies/email_flag_body.tmpl

Set this to `true` to enable guest users to flag content.

	flags.guest.users.enabled=false

## Icon Menu Tag Library [](id=icon-menu-tag-library)

Configure maximum number of items to display when using the Icon Menu tag library. If the number of items is higher than the maximum allowed, then the icon menu will provide an autocomplete search box to display the items.

	icon.menu.max.display.items=15

## IFrame Portlet [](id=iframe-portlet)

Specify a role name that a user must be associated with to configure the IFrame portlet to use the `@password@` token. This token is used to post the password of users who access this portlet to automatically login to the framed site.

No role is required by default. However, it is recommended that you specify a role in high security environments where users who configure this portlet may attempt password theft. See [http://issues.liferay.com/browse/LPS-5272](http://issues.liferay.com/browse/LPS-5272) for more information.

	iframe.password.token.role=
	
## Invitation Portlet [](id=invitation-portlet)

    invitation.email.max.recipients=20
    invitation.email.message.body=com/liferay/portlet/invitation/dependencies/email_message_body.tmpl
    invitation.email.message.subject=com/liferay/portlet/invitation/dependencies/email_message_subject.tmpl

## Journal Portlet [](id=journal-portlet)
    
Input a list of sections that will be included as part of the article form when adding an article.

	journal.article.form.add=content,abstract,categorization,schedule,display-page,related-assets,permissions,custom-fields

Input a list of sections that will be included as part of the layout form when translating an article.

	journal.article.form.translate=content,abstract

Input a list of sections that will be included as part of the article form when updating an article.

	journal.article.form.update=content,abstract,categorization,schedule,display-page,related-assets,custom-fields

Set this to `true` if article ids should always be autogenerated.

	journal.article.force.autogenerate.id=true

Set the list of article types. The display text of each of the article types is set in `content/Language.properties`.

	journal.article.types=announcements,blogs,general,news,press-release,test

Set a list of custom tokens that will be replaced when article content is rendered. For example, if set to `custom_token_1`, then `@custom_token_1@` will be replaced with its token value before an article is displayed.

*Examples:*

	journal.article.custom.tokens=custom_token_1,custom_token_2
	journal.article.custom.token.value[custom_token_1]=This is the first custom token.
	journal.article.custom.token.value[custom_token_2]=This is the second custom token.

Set the token used when inserting simple page breaks in articles.

	journal.article.token.page.break=@page_break@

Set the interval on which the `CheckArticleMessageListener` will run. The value is set in one minute increments.

	journal.article.check.interval=15

Set this to `true` to check that a user has the VIEW permission on a Journal article when its content is rendered.

	journal.article.view.permission.check.enabled=false

Set this to `true` to enable comments for journal articles.

	journal.article.comments.enabled=true

Set this to `true` if feed ids should always be autogenerated.

	journal.feed.force.autogenerate.id=true

Set this to `true` if structure ids should always be autogenerated.

	journal.structure.force.autogenerate.id=true

Set this to `true` if template ids should always be autogenerated.

	journal.template.force.autogenerate.id=true

Set the language types available for templates.

	journal.template.language.types=css,ftl,vm,xsl

Set the location of the default content for each language type.

	journal.template.language.content[css]=com/liferay/portlet/journal/dependencies/template.css
	journal.template.language.content[ftl]=com/liferay/portlet/journal/dependencies/template.ftl
	journal.template.language.content[vm]=com/liferay/portlet/journal/dependencies/template.vm
	journal.template.language.content[xsl]=com/liferay/portlet/journal/dependencies/template.xsl

Set the parser for each language type. A parser is only needed for language types that require parsing. The classes must implement `com.liferay.portal.kernel.templateparser.TemplateParser`.

	journal.template.language.parser[css]=
	journal.template.language.parser[ftl]=com.liferay.portlet.journal.util.FreeMarkerTemplateParser
	journal.template.language.parser[vm]=com.liferay.portlet.journal.util.VelocityTemplateParser
	journal.template.language.parser[xsl]=com.liferay.portlet.journal.util.XSLTemplateParser

Input a comma delimited list of variables which are restricted from the context in FreeMarker based Journal templates.

	journal.template.freemarker.restricted.variables=serviceLocator

Input a comma delimited list of variables which are restricted from the context in Velocity based Journal templates.

	journal.template.velocity.restricted.variables=serviceLocator

Set the maximum file size and valid file extensions for images. A value of `0` for the maximum file size can be used to indicate unlimited file size. However, the maximum file size allowed is set in the property `com.liferay.portal.upload.UploadServletRequestImpl.max.size`.

	journal.image.small.max.size=51200

A file extension of `*` will permit all file extensions.

	journal.image.extensions=.gif,.jpeg,.jpg,.png

Input a list of comma delimited class names that extend `com.liferay.portal.kernel.templateparser.BaseTransformerListener`. These classes will run in sequence to allow you to modify the XML and XSL before it is transformed and allow you to modify the final output.

*Examples:*

    journal.transformer.listener=\
		com.liferay.portlet.journal.util.TokensTransformerListener,\
		com.liferay.portlet.journal.util.PropertiesTransformerListener,\
		com.liferay.portlet.journal.util.ContentTransformerListener,\
		com.liferay.portlet.journal.util.LocaleTransformerListener,\
		com.liferay.portlet.journal.util.RegexTransformerListener,\
		com.liferay.portlet.journal.util.ViewCounterTransformerListener

Enter a list of regular expression patterns and replacements that will be applied to outputted Journal content. The list of  properties must end with a subsequent integer (`0`, `1`, etc.) and it is assumed the list has reached an end when the pattern or replacement is not set. See `com.liferay.portlet.journal.util.RegexTransformerListener` for implementation details.

*Examples:*

	journal.transformer.regex.pattern.0=beta.sample.com
	journal.transformer.regex.replacement.0=production.sample.com
	journal.transformer.regex.pattern.1=staging.sample.com
	journal.transformer.regex.replacement.1=production.sample.com

Set this to `true` if journal articles should be published to live by default.

	journal.publish.to.live.by.default=true

Set whether to synchronize content searches when the server starts.

	journal.sync.content.search.on.startup=false

Configure email notification settings.

	journal.email.from.name=
	journal.email.from.address=

	journal.email.article.added.enabled=false
	journal.email.article.added.subject=com/liferay/portlet/journal/dependencies/email_article_added_subject.tmpl
	journal.email.article.added.body=com/liferay/portlet/journal/dependencies/email_article_added_body.tmpl

	journal.email.article.approval.denied.enabled=false
	journal.email.article.approval.denied.subject=com/liferay/portlet/journal/dependencies/email_article_approval_denied_subject.tmpl
	journal.email.article.approval.denied.body=com/liferay/portlet/journal/dependencies/email_article_approval_denied_body.tmpl

	journal.email.article.approval.granted.enabled=false
	journal.email.article.approval.granted.subject=com/liferay/portlet/journal/dependencies/email_article_approval_granted_subject.tmpl
	journal.email.article.approval.granted.body=com/liferay/portlet/journal/dependencies/email_article_approval_granted_body.tmpl
	
	journal.email.article.approval.requested.enabled=false
	journal.email.article.approval.requested.subject=com/liferay/portlet/journal/dependencies/email_article_approval_requested_subject.tmpl
	journal.email.article.approval.requested.body=com/liferay/portlet/journal/dependencies/email_article_approval_requested_body.tmpl

	journal.email.article.review.enabled=false
	journal.email.article.review.subject=com/liferay/portlet/journal/dependencies/email_article_review_subject.tmpl
	journal.email.article.review.body=com/liferay/portlet/journal/dependencies/email_article_review_body.tmpl

	journal.email.article.updated.enabled=false
	journal.email.article.updated.subject=com/liferay/portlet/journal/dependencies/email_article_updated_subject.tmpl
	journal.email.article.updated.body=com/liferay/portlet/journal/dependencies/email_article_updated_body.tmpl

Specify the strategy used when Journal content is imported using the LAR system.

	journal.lar.creation.strategy=com.liferay.portlet.journal.lar.JournalCreationStrategyImpl

Specify the path to the template used for providing error messages on\ Journal templates.

	journal.error.template.freemarker=com/liferay/portlet/journal/dependencies/error.ftl
	journal.error.template.velocity=com/liferay/portlet/journal/dependencies/error.vm
	journal.error.template.xsl=com/liferay/portlet/journal/dependencies/error.xsl    
    
## Journal Articles Portlet [](id=journal-articles-portlet)

Set the available values for the number of articles to display per page.

	journal.articles.page.delta.values=5,10,25,50,100

Set this to `true` to search articles from the index. Set this to `false` to search articles from the database. Note that setting this to `false` will disable the ability to search articles based on Expando attributes. Do not set this to `true` since the functionality for searching articles from the index is experimental.

	journal.articles.search.with.index=false	
	
## Journal Content Search Portlet [](id=journal-content-search-portlet)

Set whether unlisted articles are excluded from search results.

	journal.content.search.show.listed=true	
	
## Login Portlet [](id=login-portlet)

Configure email notification settings.

	login.email.from.name=
	login.email.from.address=

Set this to `true` to allow the user to choose a password during account creation.

	login.create.account.allow.custom.password=false

## Message Boards Portlet [](id=message-boards-portlet)

Configure email notification settings.

	message.boards.email.from.name=
	message.boards.email.from.address=
	message.boards.email.html.format=true

	message.boards.email.message.added.enabled=true
	message.boards.email.message.added.subject.prefix=com/liferay/portlet/messageboards/dependencies/email_message_added_subject_prefix.tmpl
	message.boards.email.message.added.body=com/liferay/portlet/messageboards/dependencies/email_message_added_body.tmpl
	message.boards.email.message.added.signature=com/liferay/portlet/messageboards/dependencies/email_message_added_signature.tmpl

	message.boards.email.message.updated.enabled=true
	message.boards.email.message.updated.subject.prefix=com/liferay/portlet/messageboards/dependencies/email_message_updated_subject_prefix.tmpl
	message.boards.email.message.updated.body=com/liferay/portlet/messageboards/dependencies/email_message_updated_body.tmpl
	message.boards.email.message.updated.signature=com/liferay/portlet/messageboards/dependencies/email_message_updated_signature.tmpl

Set the list of supported category display styles and the default category display style.

	message.boards.category.display.styles=default,question
	message.boards.category.display.styles.default=default

Set the list of supported message board formats and the default message board format.

	message.boards.message.formats=bbcode,html
	message.boards.message.formats.default=bbcode

Set this to `true` to allow anonymous posting.

	message.boards.anonymous.posting.enabled=true

Set this to `true` if users are subscribed by default to a thread they participate in.

	message.boards.subscribe.by.default=true

Enter time in minutes on how often this job is run. If a user's ban is set to expire at 12:05 PM and the job runs at 2 PM, the expire will occur during the 2 PM run.

	message.boards.expire.ban.job.interval=120

Enter time in days to automatically expire bans on users. Set this property or the property `message.boards.expire.ban.job.interval` to `0` to disable auto expire.

*Examples:*

	message.boards.expire.ban.interval=10
	message.boards.expire.ban.interval=0

Set this to `true` to enable pingbacks.

	message.boards.pingback.enabled=true

	message.boards.rss.abstract.length=200

Set this to `true` to enable previous and next navigation for message boards threads.

	message.boards.thread.previous.and.next.navigation.enabled=true

Set the allowed thread views and the default thread view.

	message.boards.thread.views=combination,flat,tree
	message.boards.thread.views.default=combination

Set this to `true` if message boards should be published to live by default.

	message.boards.publish.to.live.by.default=false

## My Places Portlet [](id=my-places-portlet)

Set the display style for the My Places navigation menu.

*Examples:*

	my.sites.display.style=simple
	my.sites.display.style=classic

Set this to `true` to show user public sites with no layouts.

	my.sites.show.user.public.sites.with.no.layouts=true

Set this to `true` to show user private sites with no layouts.

	my.sites.show.user.private.sites.with.no.layouts=true

Set this to `true` to show public sites with no layouts.

	my.sites.show.public.sites.with.no.layouts=true

Set this to `true` to show private sites with no layouts.

	my.sites.show.private.sites.with.no.layouts=true

Set the maximum number of elements that will be shown in the My Sites navigation menu. For example, if the maximum is set to `10`, then, at most, 10 sites will be shown.

	my.sites.max.elements=10

## Navigation Portlet [](id=navigation-portlet)

Specify the options that will be provided to the user in the edit configuration mode of the portlet.

	navigation.display.style.options=relative-with-breadcrumb,from-level-2-with-title,from-level-1-with-title,from-level-1,from-level-1-to-all-sublevels,from-level-0

Define each mode with 4 comma delimited strings that represent the form: `headerType`, `rootLayoutType`, `rootLayoutLevel`, `includedLayouts` and `nestedChildren`.

	navigation.display.style[relative-with-breadcrumb]=breadcrumb,relative,0,auto,true
	navigation.display.style[from-level-2-with-title]=root-layout,absolute,2,auto,true
	navigation.display.style[from-level-1-with-title]=root-layout,absolute,1,auto,true
	navigation.display.style[from-level-1]=none,absolute,1,auto,true
	navigation.display.style[from-level-1-to-all-sublevels]=none,absolute,1,all,true
	navigation.display.style[from-level-0]=none,absolute,0,auto,true

## Nested Portlets Portlet [](id=nested-portlets-portlet)

	nested.portlets.layout.template.default=2_columns_i

Add a comma separated list of layout template ids that should not be allowed in the Nested Portlets Portlet.

	nested.portlets.layout.template.unsupported=freeform,1_column

## Portlet CSS Portlet [](id=portlet-css-portlet)

Set this to `true` to enable the ability to modify portlet CSS at runtime via the Look and Feel icon. Disabling it can speed up performance.

	portlet.css.enabled=true
	
## Ratings Tag Library [](id=ratings-tag-library)

Set the number of stars that will be used by the ratings tag library by default.

	ratings.default.number.of.stars=5
	
Set the number of max score and min score that will be used by the ratings tag library for each model.

    ratings.max.score[com.liferay.portlet.messageboards.model.MBDiscussion]=1
    ratings.min.score[com.liferay.portlet.messageboards.model.MBDiscussion]=-1

    ratings.max.score[com.liferay.portlet.messageboards.model.MBMessage]=1
    ratings.min.score[com.liferay.portlet.messageboards.model.MBMessage]=-1	

## RSS Portlet [](id=rss-portlet)

Set the HTTP connection timeout in milliseconds for reading RSS feeds.

	rss.connection.timeout=2000	

## Search Portlet [](id=search-portlet)

Set any of these to `false` to disable the portlet from being searched by the Search portlet.

	com.liferay.portlet.blogs.util.BlogsOpenSearchImpl=true
	com.liferay.portlet.bookmarks.util.BookmarksOpenSearchImpl=true
	com.liferay.portlet.calendar.util.CalendarOpenSearchImpl=true
	com.liferay.portlet.directory.util.DirectoryOpenSearchImpl=true
	com.liferay.portlet.documentlibrary.util.DLOpenSearchImpl=true
	com.liferay.portlet.journal.util.JournalOpenSearchImpl=true
	com.liferay.portlet.messageboards.util.MBOpenSearchImpl=true
	com.liferay.portlet.wiki.util.WikiOpenSearchImpl=true

## Sites Admin Portlet [](id=sites-admin-portlet)

Set this to `true` to allow site members to see the Sites Admin portlet and the sites he is a member of in the control panel. Setting this to `false` will only allow administrators to see this portlet in the control panel.

	sites.control.panel.members.visible=true

Input a list of sections that will be included as part of the form when adding a site.

	sites.form.add.main=details,pages
	sites.form.add.seo=
	sites.form.add.advanced=

Input a list of sections that will be included as part of the form when updating a site.

	sites.form.update.main=details,pages,site-url,site-template
	sites.form.update.seo=sitemap,robots
	sites.form.update.advanced=staging,analytics

Configure email notification settings.

	sites.email.from.name=
	sites.email.from.address=

	sites.email.membership.reply.subject=com/liferay/portlet/sites/dependencies/email_membership_reply_subject.tmpl
	sites.email.membership.reply.body=com/liferay/portlet/sites/dependencies/email_membership_reply_body.tmpl

	sites.email.membership.request.subject=com/liferay/portlet/sites/dependencies/email_membership_request_subject.tmpl
	sites.email.membership.request.body=com/liferay/portlet/sites/dependencies/email_membership_request_body.tmpl
	
## Shopping Portlet [](id=shopping-portlet)

Set this to `true` if cart quantities must be a multiple of the item's minimum quantity.

	shopping.cart.min.qty.multiple=true

Set this to `true` to forward to the cart page when adding an item from the category page. The item must not have dynamic fields. All items with dynamic fields will forward to the item's details page regardless of the following setting.

	shopping.category.forward.to.cart=false

Set this to `true` to show special items when browsing a category.

	shopping.category.show.special.items=false

Set this to `true` to show availability when viewing an item.

	shopping.item.show.availability=true

Set the maximum file size and valid file extensions for images. A value of `0` for the maximum file size can be used to indicate unlimited file size. However, the maximum file size allowed is set in the property `com.liferay.portal.upload.UploadServletRequestImpl.max.size`.

	shopping.image.small.max.size=51200
	shopping.image.medium.max.size=153600
	shopping.image.large.max.size=307200
	
A file extension of `*` will permit all file extensions.

	shopping.image.extensions=.gif,.jpeg,.jpg,.png

Configure email notification settings.

	shopping.email.from.name=
	shopping.email.from.address=

	shopping.email.order.confirmation.enabled=true
	shopping.email.order.confirmation.subject=com/liferay/portlet/shopping/dependencies/email_order_confirmation_subject.tmpl
	shopping.email.order.confirmation.body=com/liferay/portlet/shopping/dependencies/email_order_confirmation_body.tmpl

	shopping.email.order.shipping.enabled=true
	shopping.email.order.shipping.subject=com/liferay/portlet/shopping/dependencies/email_order_shipping_subject.tmpl
	shopping.email.order.shipping.body=com/liferay/portlet/shopping/dependencies/email_order_shipping_body.tmpl

Set this to `true` to enable comments for shopping orders.

	shopping.order.comments.enabled=true

## Software Catalog Portlet [](id=software-catalog-portlet)

Set the maximum file size and dimensions for thumbnnails. A value of `0` for the maximum file size can be used to indicate unlimited file size. However, the maximum file size allowed is set in the property `com.liferay.portal.upload.UploadServletRequestImpl.max.size`.

	sc.image.max.size=307200
	sc.image.thumbnail.max.height=200
	sc.image.thumbnail.max.width=160

Set this to `true` to enable comments for software catalog products.

	sc.product.comments.enabled=true

## Tags Compiler Portlet [](id=tags-compiler-portlet)

Set this to `true` to enable the ability to compile tags from the URL. Disabling it can speed up performance.

	tags.compiler.enabled=true
	
## Translator Portlet [](id=translator-portlet)

Set the default languages to translate a given text.

	translator.default.languages=en_es

## Wiki Portlet [](id=wiki-portlet)

Set the name of the default page for a wiki node. The name for the default page must be a valid wiki word. A wiki word follows the format of having an upper case letter followed by a series of lower case letters followed by another upper case letter and another series of lower case letters. See [http://www.usemod.com/cgi-bin/wiki.pl?WhatIsaWiki](http://www.usemod.com/cgi-bin/wiki.pl?WhatIsaWiki) for more information on wiki naming conventions.

	wiki.front.page.name=FrontPage

Set the name of the default node that will be automatically created when the Wiki portlet is first used in a site.

	wiki.initial.node.name=Main

Specify the requirements for the names of wiki pages. By default only a few characters are forbidden. Uncomment the regular expression below to allow only CamelCase titles.

*Examples:*

	wiki.page.titles.regexp=([^\\\\\\[\\]\\|:;%<>]+)
	#wiki.page.titles.regexp=(((\\p{Lu}\\p{Ll}+)_?)+)

Specify the characters that will be automatically removed from the titles when importing wiki pages. This regexp should remove any characters that are forbidden in the regexp specified in the property `wiki.page.titles.regexp`.

	wiki.page.titles.remove.regexp=([\\\\\\[\\]\\|:;%<>]+)

Set this to `true` to enable ratings for wiki pages.

	wiki.page.ratings.enabled=true

Set this to `true` to enable comments for wiki pages.

	wiki.page.comments.enabled=true
	
Set this to true to enable social activity notifications on minor edits of a wiki page.

    wiki.page.minor.edit.add.social.activity=true

Set this to true to enable email notifications on minor edits of a wiki page.

    wiki.page.minor.edit.send.email=false

Set the list of supported wiki formats and the default wiki format.

	wiki.formats=creole,html,mediawiki
	wiki.formats.default=creole
	
Configure settings for each of the wiki formats.

*Examples:*

	wiki.formats.engine[creole]=com.liferay.portlet.wiki.engines.antlrwiki.CreoleWikiEngine
	wiki.formats.engine[creole]=com.liferay.portlet.wiki.engines.jspwiki.JSPWikiEngine
	wiki.formats.configuration.main[creole]=jspwiki.properties
	wiki.formats.edit.page[creole]=/html/portlet/wiki/edit/wiki.jsp
	wiki.formats.help.page[creole]=/html/portlet/wiki/help/creole.jsp
	wiki.formats.help.url[creole]=http://www.wikicreole.org/wiki/Creole1.0
	
	wiki.formats.engine[html]=com.liferay.portlet.wiki.engines.HtmlEngine
	wiki.formats.edit.page[html]=/html/portlet/wiki/edit/html.jsp
	
	wiki.formats.engine[mediawiki]=com.liferay.portlet.wiki.engines.mediawiki.MediaWikiEngine
	wiki.formats.edit.page[mediawiki]=/html/portlet/wiki/edit/wiki.jsp
	wiki.formats.help.page[mediawiki]=/html/portlet/wiki/help/mediawiki.jsp
	wiki.formats.help.url[mediawiki]=http://www.mediawiki.org/wiki/Help:Formatting
	
	wiki.formats.engine[plain_text]=com.liferay.portlet.wiki.engines.TextEngine
	wiki.formats.edit.page[plain_text]=/html/portlet/wiki/edit/plain_text.jsp

Set the list of supported wiki importers.

	wiki.importers=MediaWiki

Configure settings for each of the wiki importers.

	wiki.importers.page[MediaWiki]=/html/portlet/wiki/import/mediawiki.jsp
	wiki.importers.class[MediaWiki]=com.liferay.portlet.wiki.importers.mediawiki.MediaWikiImporter

Configure email notification settings.

	wiki.email.from.name=
	wiki.email.from.address=

	wiki.email.page.added.enabled=true
	wiki.email.page.added.subject.prefix=com/liferay/portlet/wiki/dependencies/email_page_added_subject_prefix.tmpl
	wiki.email.page.added.body=com/liferay/portlet/wiki/dependencies/email_page_added_body.tmpl
	wiki.email.page.added.signature=com/liferay/portlet/wiki/dependencies/email_page_added_signature.tmpl

	wiki.email.page.updated.enabled=true
	wiki.email.page.updated.subject.prefix=com/liferay/portlet/wiki/dependencies/email_page_updated_subject_prefix.tmpl
	wiki.email.page.updated.body=com/liferay/portlet/wiki/dependencies/email_page_updated_body.tmpl
	wiki.email.page.updated.signature=com/liferay/portlet/wiki/dependencies/email_page_updated_signature.tmpl

	wiki.rss.abstract.length=200

## Summary [](id=summ-35)

In this very long chapter we looked at all of the options which can be customized in your `portal-ext.properties` file. This chapter serves as a reference section for the file, so you can quickly find documentation for any property you might encounter. Also, if you would like to make a customization to Liferay, you can use this section to search for a property that does what you want. Once you find it, just add the appropriate line(s) to your `portal-ext.properties` file.
