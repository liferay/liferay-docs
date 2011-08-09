# Advanced Liferay Configuration

Liferay is configured by a combination of settings which are stored in
the database (configured by the use of the Control Panel) and settings
which are stored in properties (text) files. These files can be modified
to change Liferay's behavior in certain ways. There are a large number
of configuration options that can be set, and so this chapter will have
a wide-ranging set of topics. We will first go over the main
configuration file, which is stored in the Liferay Home directory, and
is called `portal-ext.properties`{.western}*.*

There are also some other settings that you may want to further
customize. They include changing certain out-of-box defaults, security
configuration, adding features to Liferay through plugin management, and
accessing Liferay's web services. We will examine specifically these
topics:

-   *Advanced Liferay Configuration:* This includes the customization of
    the `portal-ext.properties`{.western} file.

-   *Plugin Management:* You will learn how to install Plugins (portlets
    and themes) from Liferay's Official Repository and Liferay's
    Community Repository, as well as how to create your own plugin
    repository.

-   *Liferay SOA:* Accessing Liferay services remotely, from outside the
    portal, will be discussed, as well as how to configure the security
    settings for these services.

### The portal-ext.properties File

Liferay's properties files differ from the configuration files of most
other products in that changing the default configuration file is
discouraged. In fact, the file that contains all of the defaults is
stored inside of a .jar file, making it more difficult to customize. Why
is it set up this way? Because Liferay uses the concept of *overriding*
the defaults in a separate file, rather than going in and customizing
the default configuration file. You put just the settings you want to
customize in your own configuration file, and then the configuration
file for your portal is uncluttered and contains only the settings you
need. This makes it far easier to determine whether a particular setting
has been customized, and it makes the settings more portable across
different installations of Liferay.

The default configuration file is called `portal.properties`{.western},
and it resides inside of the `portal-impl.jar`{.western} file. This .jar
file is located in Liferay Portal's `WEB-INF/lib`{.western}* *folder.
The file which is used to override the configuration is
`portal-ext.properties`{.western}. This file can be created in your
Liferay Home folder (please see Chapter 2: Initial Setup for the
location of this folder for your application server). By default, the
file does not exist at all, unless you are running an older version of
Liferay. What follows is a brief description of the options that can be
placed there, thus overriding the defaults from the
`portal.properties`{.western} file. These are presented in a logical
order, not an alphabetical one, as many properties relate to other
properties in the system.

#### Properties Override

This property specifies where to get the overridden properties. By
default, it is `portal-ext.properties`{.western}*.* Updates should not
be made on the original file (`portal.properties`{.western}) but on the
overridden version of this file. Furthermore, each portal instance can
have its own overridden property file following the convention
`portal-companyid.properties`{.western}.

For example, one read order may be: `portal.properties`{.western}, then
`portal-ext.properties`{.western}, and then
`portal-test.properties`{.western}.

*Examples:*

include-and-override=portal-ext.properties

include-and-override=${liferay.home}/portal-ext.properties

You can add additional property files that overwrite the default values
by using the `external-properties`{.western} system property.

A common use case is to keep legacy property values when upgrading to
newer versions of Liferay. For example:

java ... -Dexternal-properties=portal-legacy-5.1.properties

include-and-override=${external-properties}

#### Liferay Home

Specify the Liferay home directory.

liferay.home=${resource.repositories.root}

This property is available for backwards compatibility. Please set the
property `liferay.home`{.western} instead.

resource.repositories.root=${default.liferay.home}

#### Portal Context

This specifies the path of the portal servlet context. This is needed
because `javax.servlet.ServletContext`{.western} does not have access to
the context path until Java EE 5.

Set this property if you deploy the portal to another path besides root.

*Examples:*

portal.ctx=/

portal.ctx=/portal

#### Resource Repositories Root

Specifies the default root path for various repository and resource
paths. Under this path several directories will be created for the hot
deploy feature, JCR, etc.

*Examples:*

resource.repositories.root=${user.home}/liferay

resource.repositories.root=/home/liferay

#### Technology Compatibility Kit

Set the following to true to enable programmatic configuration to let
the Portlet TCK obtain a URL for each test. This should never be set to
true unless you are running the TCK tests.

tck.url=false

#### Schema

Set this to true to automatically create tables and populate with
default data if the database is empty.

schema.run.enabled=true

Set this to to true to populate with the minimal amount of data. Set
this to false to populate with a larger amount of sample data.

schema.run.minimal=true

#### Upgrade

Input a list of comma delimited class names that implement
`com.liferay.portal.upgrade.UpgradeProcess`{.western}. These classes
will run on startup to upgrade older data to match with the latest
version.

upgrade.processes=\\

com.liferay.portal.upgrade.UpgradeProcess\_4\_3\_0,\\

com.liferay.portal.upgrade.UpgradeProcess\_4\_3\_1,\\

com.liferay.portal.upgrade.UpgradeProcess\_4\_3\_2,\\

com.liferay.portal.upgrade.UpgradeProcess\_4\_3\_3,\\

com.liferay.portal.upgrade.UpgradeProcess\_4\_3\_4,\\

com.liferay.portal.upgrade.UpgradeProcess\_4\_3\_5,\\

com.liferay.portal.upgrade.UpgradeProcess\_4\_4\_0,\\

com.liferay.portal.upgrade.UpgradeProcess\_5\_0\_0,\\

com.liferay.portal.upgrade.UpgradeProcess\_5\_1\_0,\\

com.liferay.portal.upgrade.UpgradeProcess\_5\_1\_2,\\

com.liferay.portal.upgrade.UpgradeProcess\_5\_2\_0,\\

com.liferay.portal.upgrade.UpgradeProcess\_5\_2\_1,\\

com.liferay.portal.upgrade.UpgradeProcess\_5\_2\_2

#### Verify

Input a list of comma delimited class names that implement
`com.liferay.portal.integrity.VerifyProcess`{.western}. These classes
will run on startup to verify and fix any integrity problems found in
the database.

verify.processes=com.liferay.portal.verify.VerifyProcessSuite

Specify the frequency for verifying the integrity of the database.

Constants in `VerifyProcess`{.western}:

`public static final int ALWAYS = -1;`{.western}

`public static final int NEVER = 0;`{.western}

`public static final int ONCE = 1;`{.western}

verify.frequency=1

#### Auto Deploy

Input a list of comma delimited class names that implement
`com.liferay.portal.kernel.deploy.auto.AutoDeployListener`{.western}.
These classes are used to process the auto deployment of WARs.

auto.deploy.listeners=\\

com.liferay.portal.deploy.auto.HookAutoDeployListener,\\

com.liferay.portal.deploy.auto.LayoutTemplateAutoDeployListener,\\

com.liferay.portal.deploy.auto.PortletAutoDeployListener,\\

com.liferay.portal.deploy.auto.ThemeAutoDeployListener,\\

com.liferay.portal.deploy.auto.WebAutoDeployListener,\\

com.liferay.portal.deploy.auto.exploded.tomcat.LayoutTemplateExplodedTomcatListener,\\

com.liferay.portal.deploy.auto.exploded.tomcat.PortletExplodedTomcatListener,\\

com.liferay.portal.deploy.auto.exploded.tomcat.ThemeExplodedTomcatListener

Set the following to true to enable auto deploy of layout templates,
portlets, and themes.

auto.deploy.enabled=true

Set the directory to scan for layout templates, portlets, and themes to
auto deploy.

auto.deploy.deploy.dir=${liferay.home}/deploy

Set the directory where auto deployed WARs are copied to. The
application server or servlet container must know to listen on that
directory.

Different containers have different hot deploy paths. For example,
Tomcat listens on `${catalina.base}/webapps`{.western} whereas JBoss
listens on `${jboss.server.home.dir}/deploy`{.western}. Set a blank
directory to automatically use the application server specific
directory.

*Examples: *

auto.deploy.dest.dir=

auto.deploy.default.dest.dir=../webapps

auto.deploy.geronimo.dest.dir=${org.apache.geronimo.base.dir}/deploy

auto.deploy.geronimo-jetty.dest.dir=${org.apache.geronimo.base.dir}/deploy

auto.deploy.geronimo-tomcat.dest.dir=${org.apache.geronimo.base.dir}/deploy

auto.deploy.glassfish.dest.dir=${com.sun.aas.instanceRoot}/autodeploy

auto.deploy.glassfish-tomcat.dest.dir=${com.sun.aas.instanceRoot}/autodeploy

auto.deploy.jboss-tomcat.dest.dir=${jboss.server.home.dir}/deploy

auto.deploy.jetty.dest.dir=${jetty.home}/webapps

auto.deploy.jonas-jetty.dest.dir=${jonas.base}/webapps/autoload

auto.deploy.jonas-tomcat.dest.dir=${jonas.base}/webapps/autoload

auto.deploy.resin.dest.dir=${resin.home}/webapps

auto.deploy.tomcat.dest.dir=${catalina.base}/webapps

auto.deploy.weblogic.dest.dir=${env.DOMAIN\_HOME}/autodeploy

Set the interval in milliseconds on how often to scan the directory for
changes.

auto.deploy.interval=10000

Set the number of attempts to deploy a file before blacklisting it.

auto.deploy.blacklist.threshold=10

Set the following to true if deployed WARs are unpacked. Set this to
false if your application server has concurrency issues with deploying
large WARs.

auto.deploy.unpack.war=true

Set the following to true if you want the deployer to rename
`portlet.xml`{.western} to `portlet-custom.xml`{.western}. This is only
needed when deploying the portal on WebSphere 6.1.x with a version
before 6.1.0.7 because WebSphere's portlet container will try to process
a portlet at the same time that Liferay is trying to process a portlet.

Note that according to IBM, on versions *after* 6.1.0.9, you need to add
a context parameter to the `web.xml`{.western} descriptor in your
portlet application called
`com.ibm.websphere.portletcontainer.PortletDeploymentEnabled`{.western}
and set it to `false`{.western}. This parameter causes WebSphere's
built-in portlet container to ignore your portlet application when it is
deployed, enabling Liferay to pick it up.

auto.deploy.custom.portlet.xml=false

Set this to 1 if you are using JBoss'
`PrefixDeploymentSorter`{.western}. This will append a 1 in front of
your WAR name. For example, if you are deploying a portlet called
`test-portlet.war`{.western}, it will deploy it to 1test-portlet.war.
JBoss now knows to load this portlet after the other WARs have loaded;
however, it will remove the 1 from the context path.

Modify `/server/default/conf/jboss-service.xml`{.western}.

See `org.jboss.deployment.scanner.PrefixDeploymentSorter`{.western}.

auto.deploy.jboss.prefix=1

Set the path to Tomcat's configuration directory. This property is used
to auto deploy exploded WARs. Tomcat context XML files found in the auto
deploy directory will be copied to Tomcat's configuration directory. The
context XML file must have a docBase attribute that points to a valid
WAR directory.

auto.deploy.tomcat.conf.dir=../conf/Catalina/localhost

Set the path to Tomcat's global class loader. This property is only used
by Tomcat in a standalone environment.

auto.deploy.tomcat.lib.dir=../common/lib/ext

Set the URLs of Libraries that might be needed to download during the
auto deploy process

library.download.url.quercus.jar=http://lportal.svn.sourceforge.net/viewvc/\*checkout\*/lportal/portal/trunk/lib/development/quercus.jar

library.download.url.resin-util.jar=http://lportal.svn.sourceforge.net/viewvc/\*checkout\*/lportal/portal/trunk/lib/development/resin-util.jar

library.download.url.script-10.jar=[http://l](http://l/)portal.svn.sourceforge.net/viewvc/\*checkout\*/lportal/portal/trunk/lib/development/script-10.jar

Set the Glassfish settings to enable JSR 88 application deployment.

auto.deploy.glassfish-tomcat.jee.deployment.enabled=false

auto.deploy.glassfish-tomcat.jee.dm.id=deployer:Sun:AppServer::localhost:4848

auto.deploy.glassfish-tomcat.jee.dm.user=admin

auto.deploy.glassfish-tomcat.jee.dm.passwd=adminadmin

auto.deploy.glassfish-tomcat.jee.df.classname=com.sun.enterprise.deployapi.SunDeploymentFactory

#### Hot Deploy

Input a list of comma delimited class names that implement
`com.liferay.portal.kernel.deploy.hot.HotDeployListener`{.western}.
These classes are used to process the deployment and undeployment of
WARs at runtime.

Note: `PluginPackageHotDeployListener`{.western} must always be first.

hot.deploy.listeners=\\

com.liferay.portal.deploy.hot.PluginPackageHotDeployListener,\\

com.liferay.portal.deploy.hot.ExtHotDeployListener,\\

com.liferay.portal.deploy.hot.HookHotDeployListener,\\

com.liferay.portal.deploy.hot.LayoutTemplateHotDeployListener,\\

com.liferay.portal.deploy.hot.PortletHotDeployListener,\\

com.liferay.portal.deploy.hot.ThemeHotDeployListener,\\

com.liferay.portal.deploy.hot.ThemeLoaderHotDeployListener,\\

com.liferay.portal.deploy.hot.MessagingHotDeployListener

#### Hot Undeploy

Set the following to true to enable undeploying plugins.

hot.undeploy.enabled=true

Set the undeploy interval in milliseconds on how long to wait for the
undeploy process to finish.

hot.undeploy.interval=0

Set the following to true to undeploy a plugin before deploying a new
version. This property will only be used if the property
*hot.undeploy.enabled* is set to true.

hot.undeploy.on.redeploy=false

#### Sandbox Deploy

Input a list of comma delimited class names that implement
`com.liferay.portal.kernel.deploy.sandbox.SandboxDeployListener`{.western}.
These classes are used to process sandbox style plugins.

sandbox.deploy.listeners=\\com.liferay.portal.deploy.sandbox.ThemeSandboxDeployListener

Set this to true to enable sandbox style plugin development.

sandbox.deploy.enabled=false

Set the directory to scan for sand box style plugins.

sandbox.deploy.dir=${liferay.home}/sandbox

Set the interval in milliseconds on how often to scan the directory for

sandbox.deploy.interval=10000

#### Plugin

Input a list of comma delimited supported plugin types.

plugin.types=portlet,theme,layout-template,hook,web

Input a list of Liferay plugin repositories separated by \\n characters.

plugin.repositories.trusted=http://plugins.liferay.com/official

plugin.repositories.untrusted=http://plugins.liferay.com/community

Set this property to false to avoid receiving on screen notifications
when there is a new version of an installed plugin.

plugin.notifications.enabled=true

Input a list of plugin packages ids separated by `\n`{.western}
characters. Administrators won't be notified when a new version of these
plugins are available. The ids are of the form
`groupId/artifactId`{.western}. You can also end the id with an asterisk
to match any id that starts with the previous character.

plugin.notifications.packages.ignored=liferay/sample-jsp-portlet

#### Portlet

\
\

Set this property to define the default virtual path for all hot
deployed portlets. See liferay-portlet-app\_5\_1\_0.dtd and the
virtual-path element for more information.

portlet.virtual.path=

Set this property to true to validate `portlet.xml`{.western} against
the portlet schema.

portlet.xml.validate=true

Set this property to add a security check when portlets are dynamically
added to a page.

portlet.add.default.resource.check.enabled=true

Set a list of comma delimited list of portlet ids that will bypass this
security check set in the previous property.

portlet.add.default.resource.check.whitelist=58,86,87,88,103,113,145

Input a list of comma delimited struts actions that will bypass the
security check set in the property
`portlet.add.default.resource.check.enabled`{.western}.

portlet.add.default.resource.check.whitelist.actions=\\

/journal/rss,\\

/language/view

#### Persistence

Set the provider for ORM persistence. If this property is set to
`jpa`{.western}, then the properties with the prefix `jpa`{.western}.
will be read. If this property is set to `hibernate`{.western}, then the
properties with the prefix `hibernate`{.western} will be read.

\

persistence.provider=hibernate

persistence.provider=jpa

#### JPA

Input a list of comma delimited JPA configurations.

jpa.configs=\\

META-INF/mail-orm.xml,\\

META-INF/portal-orm.xml

Set the name of the JPA provider.

jpa.provider=eclipselink

jpa.provider=hibernate

jpa.provider=openjpa

jpa.provider=toplink

\

Specify provider specific properties prefixed with
`jpa.provider.property`{.western}.

jpa.provider.property.eclipselink.allow-zero-id=true

jpa.provider.property.eclipselink.logging.level=FINEST

jpa.provider.property.eclipselink.logging.timestamp=true

\

The `LoadTimeWeaver`{.western} interface is a Spring class that allows
JPA `ClassTransformer`{.western} instances to be plugged in a specific
manner depending on the environment.

Not all JPA providers require a JVM agent (Hibernate is an example). If
your provider does not require an agent or you have other alternatives
(such as applying enhancements at build time through a custom compiler
or an Ant task), then the loadtime weaver should not be used.

jpa.load.time.weaver=org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver

jpa.load.time.weaver=org.springframework.instrument.classloading.glassfish.GlassFishLoadTimeWeaver

jpa.load.time.weaver=org.springframework.instrument.classloading.oc4j.OC4JLoadTimeWeaver

jpa.load.time.weaver=org.springframework.instrument.classloading.weblogic.WebLogicLoadTimeWeaver

Specify a specific database platform setting if the JPA provider is not
able to detect the database platform.

Valid values for the Hibernate and OpenJPA providers are:
`DB2, DERBY,`{.western}

`HSQL, INFORMIX, MYSQL, ORACLE, POSTGRESQL, SQL_SERVER`{.western}, and
`SYBASE`{.western}.

\

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

\
\

Check with JPA provider's documentation for details and all possible

values.

jpa.database.platform=

\

Liferay will automatically detect the database type by initializing
`DBUtil`{.western}. You can override the value here if needed. Expected
values are:

`db2, derby, firebird, hypersonic, informix, ingres, interbase, jdatastore,`{.western}

`mysql, oracle, postgresql, sap, sqlserver`{.western}, and
`sybase`{.western}.

jpa.database.type=

#### Transaction Manager

Set the transaction manager. It must be a class that extends
`org.springframework.transaction.support.AbstractPlatformTransactionManager`{.western}.

The application server specific transaction managers provide XA
transactions by leveraging application server specific data sources and
thus require additional application server specific configuration. You
should not modify this unless you know what you're doing.

transaction.manager.impl=org.springframework.orm.hibernate3.HibernateTransactionManager

transaction.manager.impl=org.springframework.transaction.jta.JtaTransactionManager

transaction.manager.impl=org.springframework.transaction.jta.OC4JJtaTransactionManager

transaction.manager.impl=org.springframework.transaction.jta.WebLogicJtaTransactionManager

transaction.manager.impl=org.springframework.transaction.jta.WebSphereUowTransactionManager

\

Additional properties that follow the pattern
`transaction.manager.property.*`{.western} will be read to call the
setters on the transaction manager. For example, the property
`transaction.manager.property.globalRollbackOnParticipationFailure`{.western},
will call the setter `setGlobalRollbackOnParticipationFailure`{.western}
on the transaction manager. The list of available setters depends on the
implementation specified in the property
`transaction.manager.impl`{.western}.
`allowCustomIsolationLevels`{.western} should be set to true when using
the `JtaTransactionManager`{.western}.

transaction.manager.property.allowCustomIsolationLevels=true

transaction.manager.property.globalRollbackOnParticipationFailure=false

#### Portlet Coordination

Set this property to specify how events are distributed. If the value is
*layout-set*, then events will be distributed to all portlets contained
in a layout set. If the value is *layout*, then events will be
distributed to all portlets that are present in a layout.

portlet.event.distribution=layout

Set this property to specify how public render parameters are
distributed. If the value is *layout-set*, then public render parameters
will be distributed to all portlets contained in a layout set. This will
only work correctly if the property *layout.default.p\_l\_reset* is set
to false. If the value is *layout*, then public render parameters will
be distributed to all portlets that are present in a layout.

portlet.public.render.parameter.distribution=layout

#### Theme

Set this property to true to load the theme's merged CSS files for
faster loading for production.

Set this property to false for easier debugging for development. You can
also disable fast loading by setting the URL parameter
`css_fast_load`{.western} to `0`{.western}.

theme.css.fast.load=true

Set this property to true to load the theme's merged image files for
faster loading for production.

Set this property to false for easier debugging for development. You can
also disable fast loading by setting the URL parameter
`images_fast_load`{.western} to `0`{.western}.

theme.images.fast.load=true

Set the theme's shorcut icon.

theme.shortcut.icon=liferay.ico

Set this property to set the default virtual path for all hot deployed
themes. See `liferay-look-and-feel_5_1_0.dtd`{.western} and the
virtual-path element for more information.

theme.virtual.path=

Set this with an absolute path to specify where imported theme files
from a LAR will be stored. This path will override the file-storage path
specified in `liferay-theme-loader.xml`{.western}.

theme.loader.storage.path=

Themes can be imported via LAR files. Set this to true if imported
themes should use a new theme id on every import. This will ensure that
a copy of the old theme is preserved in the theme loader storage path.
However, this also means that a lot of themes that are no longer used
remain in the file system. It is recommended that you set this to false.

theme.loader.new.theme.id.on.import=false

Set this to true to decorate portlets by default.

theme.portlet.decorate.default=true

Set this to true to exposing sharing icons for portlets by default.

theme.portlet.sharing.default=false

#### Resource Actions

Input a list of comma delimited resource action configurations that will
be read from the class path.

resource.actions.configs=resource-actions/default.xml

#### Model Hints

Input a list of comma delimited model hints configurations.

model.hints.configs=\\

META-INF/portal-model-hints.xml,\\

META-INF/workflow-model-hints.xml,\\

META-INF/ext-model-hints.xml,\\

META-INF/portlet-model-hints.xml

#### Service Builder

Input a list of common delimited method prefixes designated for
read-only transactions. Service Builder will use these prefixes to
annotate methods that are to run in read-only transactions.

service.builder.service.read.only.prefixes=\\

get,\\

has,\\

is,\\

reindex,\\

search

#### Spring

Input a list of comma delimited Spring configurations. These will be
loaded after the bean definitions specified in the
`contextConfigLocation`{.western} parameter in `web.xml`{.western}.

Note that there is a special case for `hibernate-spring.xml`{.western}
and `jpa-spring.xml`{.western}. Even though both files are specified,
only one will actually load at runtime based on the property
`persistence.provider`{.western}.

spring.configs=\\

META-INF/base-spring.xml,\\

\\

META-INF/hibernate-spring.xml,\\

META-INF/infrastructure-spring.xml,\\

META-INF/management-spring.xml,\\

\\

META-INF/util-spring.xml,\\

\\

META-INF/jpa-spring.xml,\\

\\

META-INF/audit-spring.xml,\\

META-INF/cluster-spring.xml,\\

META-INF/editor-spring.xml,\\

META-INF/jcr-spring.xml,\\

META-INF/ldap-spring.xml,\\

META-INF/messaging-core-spring.xml,\\

META-INF/messaging-misc-spring.xml,\\

META-INF/poller-spring.xml,\\

META-INF/rules-spring.xml,\\

META-INF/scheduler-spring.xml,\\

META-INF/scripting-spring.xml,\\

META-INF/search-spring.xml,\\

META-INF/workflow-spring.xml,\\

\\

META-INF/counter-spring.xml,\\

META-INF/document-library-spring.xml,\\

META-INF/mail-spring.xml,\\

META-INF/portal-spring.xml,\\

META-INF/portlet-container-spring.xml,\\

\\

\#META-INF/dynamic-data-source-spring.xml,\\

\#META-INF/shard-data-source-spring.xml,\\

\#META-INF/memcached-spring.xml,\\

\#META-INF/monitoring-spring.xml,\\

\\

META-INF/ext-spring.xml

Set the bean name for the Liferay data source.

spring.hibernate.data.source=liferayDataSource

Set the bean name for the Liferay session factory.

spring.hibernate.session.factory=&liferaySessionFactory

#### Hibernate

Many of the following properties should only be customized if you have
advanced knowledge of Hibernate. They map to various Hibernate
configuration options which themselves have detailed documentation.
Please see http://www.hibernate.org for more information.

Input a list of comma delimited Hibernate configurations.

hibernate.configs=\\

META-INF/mail-hbm.xml,\\

META-INF/portal-hbm.xml,\\

META-INF/ext-hbm.xml

Liferay will automatically detect the Hibernate dialect in
`com.liferay.portal.spring.PortalHibernateConfiguration`{.western}. Set
this property to manually override the automatically detected dialect.

\#hibernate.dialect=

Set the Hibernate connection release mode. You should not modify this
unless you know what you're doing. The default setting works best for
Spring managed transactions. See the method
`buildSessionFactory`{.western} in class
`org.springframework.orm.hibernate3.LocalSessionFactoryBean`{.western}
and search for the phrase "on\_close" to understand how this works.

hibernate.connection.release\_mode=on\_close

Set the Hibernate cache provider. Ehcache is recommended in a clustered
environment. See the property
`net.sf.ehcache.configurationResourceName`{.western} for detailed
configuration.

*Examples: *

hibernate.cache.provider\_class= \\

com.liferay.portal.dao.orm.hibernate.EhCacheProvider

hibernate.cache.provider\_class= \\

net.sf.hibernate.cache.HashtableCacheProvider

hibernate.cache.provider\_class= \\

com.liferay.portal.dao.orm.hibernate.OSCacheProvider

\#hibernate.cache.provider\_class= \\

com.liferay.portal.dao.orm.hibernate.TerracottaCacheProvider

This property is used if Hibernate is configured to use Ehcache's cache
provider.

net.sf.ehcache.configurationResourceName=/ehcache/hibernate.xml

Use the following ehcache configuration in a clustered environment.

net.sf.ehcache.configurationResourceName=/ehcache/hibernate-clustered.xml

Uncomment the following in a Terracotta environment.

\#net.sf.ehcache.configurationResourceName=/ehcache/hibernate-terracotta.xml

Set other Hibernate cache settings.

hibernate.cache.use\_query\_cache=true

hibernate.cache.use\_second\_level\_cache=true

hibernate.cache.use\_minimal\_puts=true

hibernate.cache.use\_structured\_entries=false

Use these properties to disable Hibernate caching. This may be a
performance hit; you may only want to use these properties for
diagnostic purposes.

hibernate.cache.provider\_class=org.hibernate.cache.NoCacheProvider

hibernate.cache.use\_query\_cache=false

hibernate.cache.use\_second\_level\_cache=false

Set the JDBC batch size to improve performance. If you're using Oracle
9i, however, you must set the batch size to 0 as a workaround for a
hanging bug in the Oracle driver. See LEP-1234 for more information.

*Examples: *

hibernate.jdbc.batch\_size=20

hibernate.jdbc.batch\_size=0

Set other miscellaneous Hibernate properties.

hibernate.jdbc.use\_scrollable\_resultset=true

hibernate.bytecode.use\_reflection\_optimizer=true

hibernate.show\_sql=false

Use the classic query factory until WebLogic and Hibernate 3 can get
along. See http://www.hibernate.org/250.html\#A23 for more information.

hibernate.query.factory\_class=org.hibernate.hql.classic.ClassicQueryTranslatorFactory

Set this property to true to enable Hibernate cache monitoring. See
LPS-2056 for more information.

hibernate.generate\_statistics=false

When using DB2, set:

hibernate.dialect=com.liferay.portal.dao.orm.hibernate.

#### JDBC

Set the JNDI name to lookup the JDBC data source. If none is set, then
the portal will attempt to create the JDBC data source based on the
properties prefixed with `jdbc.default`{.western}.

\#jdbc.default.jndi.name=jdbc/LiferayPool

Set the properties used to create the JDBC data source. These properties
will only be read if the property `jdbc.default.jndi.name`{.western} is
not set.

The default settings are configured for an in-memory database called
Hypersonic that is not recommended for production use. Please change the
properties to use another database.

Add `dynamic-data-source-spring.xml`{.western} to the property
`spring.configs`{.western} to configure the portal to use one database
cluster for read calls and another database cluster for write calls. The
convention is to create a set of properties prefixed with
`jdbc.read`{.western}*.* to handle read calls and another set of
properties prefixed with `jdbc.write`{.western}*.* to handle write
calls. These data sources can also be created via JNDI by setting the
properties `jdbc.read.jndi.name`{.western} and
`jdbc.write.jndi.name`{.western}.

##### DB2

jdbc.default.driverClassName=com.ibm.db2.jcc.DB2Driver

jdbc.default.url=jdbc:db2:lportal

jdbc.default.username=db2admin

jdbc.default.password=lportal

##### Derby

jdbc.default.driverClassName=org.apache.derby.jdbc.EmbeddedDriver

jdbc.default.url=jdbc:derby:lportal

jdbc.default.username=

jdbc.default.password=

##### Hypersonic

jdbc.default.driverClassName=org.hsqldb.jdbcDriver

jdbc.default.url=jdbc:hsqldb:${liferay.home}/data/hsql/lportal

jdbc.default.username=sa

jdbc.default.password=

##### MySQL

jdbc.default.driverClassName=com.mysql.jdbc.Driver

jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false

jdbc.default.username=

jdbc.default.password=

##### Oracle

jdbc.default.driverClassName=oracle.jdbc.driver.OracleDriver

jdbc.default.url=jdbc:oracle:thin:@localhost:1521:xe

jdbc.default.username=lportal

jdbc.default.password=lportal

##### P6Spy

jdbc.default.driverClassName=com.p6spy.engine.spy.P6SpyDriver

jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false

jdbc.default.username=

jdbc.default.password=

##### PostgreSQL

jdbc.default.driverClassName=org.postgresql.Driver

jdbc.default.url=jdbc:postgresql://localhost:5432/lportal

jdbc.default.username=sa

jdbc.default.password=

##### SQL Server

jdbc.default.driverClassName=net.sourceforge.jtds.jdbc.Driver

jdbc.default.url=jdbc:jtds:sqlserver://localhost/lportal

jdbc.default.username=sa

jdbc.default.password=

##### Sybase

jdbc.default.driverClassName=net.sourceforge.jtds.jdbc.Driver

jdbc.default.url=jdbc:jtds:sybase://localhost:5000/lportal

jdbc.default.username=sa

jdbc.default.password=

Liferay uses C3PO by default for connection pooling. The data source
factory can be configured to use JNDI or another pooling implementation
by modifying `infrastructure-spring.xml`{.western}. See
http://www.mchange.com/projects/c3p0/index.html configuration for a list
of additional fields used by C3PO for configuring the database
connection.

jdbc.default.maxPoolSize=50

jdbc.default.minPoolSize=5

#### Custom SQL

Input a list of comma delimited custom SQL configurations. Liferay
Administrators should never need to customize this; this is more of an
option for developers who are customizing Liferay's behavior.

custom.sql.configs=custom-sql/default.xml

Some databases do not recognize a NULL IS NULL check. Set the
`custom.sql.function.isnull`{.western} and
`custom.sql.function.isnotnull`{.western} properties for your specific
database.

There is no need to manually set these properties because
`com.liferay.portal.spring.PortalHibernateConfiguration`{.western}
already sets it. These properties are available, however, so that you
can see how you can override it for a database that
`PortalHibernateConfiguration`{.western} does not yet know how to auto
configure.

##### DB2

custom.sql.function.isnull=CAST(? AS VARCHAR(32672)) IS NULL

custom.sql.function.isnotnull=CAST(? AS VARCHAR(32672)) IS NOT NULL

#### Database

Specify any database vendor specific settings.

##### MySQL

database.mysql.engine=InnoDB

#### Ehcache

Set the classpath to the location of the Ehcache config file for
internal caches. Edit the file specified in the property
`ehcache.multi-vm.config.location`{.western} to enable clustered cache.

ehcache.single.vm.config.location=/ehcache/liferay-single-vm.xml

ehcache.multi.vm.config.location=/ehcache/liferay-multi-vm.xml

Use the following in a clustered environment.

ehcache.multi.vm.config.location=/ehcache/liferay-multi-vm-clustered.xml

#### JavaScript

Set a list of JavaScript files that will be loaded programmatically in
`/html/common/themes/top_js.jsp`{.western}.

There are two lists of files specified in the properties
`javascript.barebone.files`{.western} and
`javascript.everything.files`{.western}.

As the name suggests, the barebone list is the minimum list of
JavaScript files required for most cases. The everything list includes
everything else not listed in the barebone list.

The two lists of files exist for performance reasons because
unauthenticated users usually do not utilize all the JavaScript that is
available. See the property `javascript.barebone.enabled`{.western} for
more information on the logic of when the barebone list is used and when
the everything list is used and how to customize that logic.

The list of files are also merged and packed for further performance
improvements. See the property `javascript.fast.load`{.western} for more
details.

Specify the list of barebone files.

The ordering of the JavaScript files is important.

The Liferay scripts are grouped in such a way that the first grouping
denotes utility scripts that are used by the second and third groups.
The second grouping denotes utility classes that rely on the first
group, but does not rely on the second or third group. The third
grouping denotes modules that rely on the first and second group.

javascript.barebone.files=\\

\\

\#

\# YUI core

\#

\\

aui/yui/yui.js,\\

\\

\#

\# YUI modules

\#

\\

aui/attribute/attribute.js,\\

aui/event-custom/event-custom.js,\\

aui/loader/loader.js,\\

aui/oop/oop.js,\\

\\

\#

\# Alloy core

\#

\\

aui/aui-base/aui-base.js,\\

\\

\#

\# Liferay base utility scripts

\#

\\

liferay/dependency.js,\\

liferay/language.js,\\

liferay/liferay.js,\\

liferay/modules.js,\\

liferay/util.js,\\

\\

\#

\# Liferay utility scripts

\#

\\

liferay/events.js,\\

liferay/portal.js,\\

liferay/portlet.js,\\

liferay/portlet\_sharing.js

\
\

Specify the list of everything files (everything else not already in the
list of barebone files).

javascript.everything.files=\\

\\

\#

\# Liferay modules

\#

\\

liferay/address.js,\\

liferay/layout\_configuration.js,\\

liferay/layout\_exporter.js,\\

liferay/workflow.js,\\

\\

\#

\# Deprecated JS

\#

\\

liferay/deprecated.js

Set this property to false to always load JavaScript files listed in the
property `javascript.everything.files`{.western}. Set this to true to
sometimes load `javascript.barebone.files`{.western} and sometimes load
`javascript.everything.files`{.western}.

The default logic is coded in
`com.liferay.portal.events.ServicePreAction`{.western} in such a way
that unauthenticated users get the list of barebone JavaScript files
whereas authenticated users get both the list of barebone JavaScript
files and the list of everything JavaScript files.

javascript.barebone.enabled=true

Set this property to true to load the packed version of files listed in
the properties `javascript.barebone.files`{.western} or
`javascript.everything.files`{.western}.

Input a list of comma delimited properties that are valid bundle ids for
the JavaScript minifier.

javascript.bundle.ids=\\

javascript.barebone.files,\\

javascript.everything.files

\
\

Define a bundle directory for each property listed in
`javascript.bundle.ids`{.western}.

javascript.bundle.dir[javascript.barebone.files]=/html/js

javascript.bundle.dir[javascript.everything.files]=/html/js

Define the bundle dependencies using any property listed in
`javascript.bundle.ids`{.western}.

javascript.bundle.dependencies[javascript.everything.files]=javascript.barebone.files

Set this property to false for easier debugging for development. You can
also disable fast loading by setting the URL parameter
`js_fast_load`{.western} to `0`{.western}.

javascript.fast.load=true

Set the following to true to enable the display of JavaScript logging.

javascript.log.enabled=false

#### Combo

The combo servlet combines multiple JavaScript files into a bundle based
on shared dependencies. This makes loading JavaScript files much faster.
Set this to false if the combination should refresh when one of its
JavaScript files has changed. This property should be set to true during
development for easier debugging but set to false during production for
faster performance.

combo.check.timestamp=false

#### SQL Data

Set the default SQL IDs for common objects.

sql.data.com.liferay.portal.model.Country.country.id=19

sql.data.com.liferay.portal.model.Region.region.id=5

sql.data.com.liferay.portal.model.ListType.account.address=10000

sql.data.com.liferay.portal.model.ListType.account.email.address=10004

sql.data.com.liferay.portal.model.ListType.contact.email.address=11003

sql.data.com.liferay.portal.model.ListType.organization.status=12017

#### Company

This sets the default web id. Omni admin users must belong to the
company with this web id.

company.default.web.id=liferay.com

This sets the default home URL of the portal.

company.default.home.url=/web/guest

The portal can authenticate users based on their email address, screen
name, or user id.

company.security.auth.type=emailAddress

company.security.auth.type=screenName

company.security.auth.type=userId

Set this to true to ensure users login with `https`{.western}.

company.security.auth.requires.https=false

Set the following to true to allow users to select the *remember me*
feature to automatically login to the portal.

company.security.auto.login=true

Set the following to the maximum age (in number of seconds) of the
browser cookie that enables the *remember me* feature. A value of
31536000 signifies a lifespan of one year. A value of -1 signifies a
lifespan of a browser session.

Rather than setting this to 0, set the property
`company.security.auto.login`{.western} to false to disable the
*remember me* feature.

company.security.auto.login.max.age=31536000

Set the following to true to allow users to ask the portal to send them
their password.

company.security.send.password=true

Set the following to true to allow strangers to create accounts and
register themselves on the portal.

company.security.strangers=true

Enter a friendly URL of a page that will be used to create new accounts
whenever the user clicks the *create account* link in the login portlet.
This allows providing custom portlets to create accounts. By default,
the portal's *create account* will be used.

\#company.security.strangers.url=/create\_account

Set the following to true if strangers can create accounts with email
addresses that match the company mail suffix. This property is not used
unless `company.security.strangers`{.western} is also set to true.

company.security.strangers.with.mx=true

Set the following to true if strangers who create accounts need to be
verified via email.

company.security.strangers.verify=false

Set the following to true to allow community administrators to use their
own logo instead of the enterprise logo.

company.security.community.logo=true

Input a list of sections that will be included as part of the company
settings form.

company.settings.form.configuration=general,authentication,default-user-associations,reserved-credentials,mail-host-names,email-notifications

company.settings.form.identification=addresses,phone-numbers,additional-email-addresses,websites

company.settings.form.miscellaneous=display-settings

#### Users

Set the following to false if users cannot be deleted.

users.delete=true

Set the following to true to always autogenerate user screen names even
if the user gives a specific user screen name.

users.screen.name.always.autogenerate=false

Input a class name that extends
`com.liferay.portal.security.auth.ScreenNameGenerator`{.western}. This
class will be called to generate user screen names.

users.screen.name.generator=com.liferay.portal.security.auth.ScreenNameGenerator

Set this to true when you want the validation to allow for creation of
numeric screen names.

users.screen.name.allow.numeric=false

Input a class name that extends
`com.liferay.portal.security.auth.ScreenNameValidator`{.western}. This
class will be called to validate user ids.

*Examples:*

users.screen.name.validator=com.liferay.portal.security.auth.ScreenNameValidator

users.screen.name.validator=com.liferay.portal.security.auth.LiberalScreenNameValidator

Input a class name that implements
`com.liferay.portal.security.auth.FullNameGenerator`{.western}. This
class will be called to generate a full name from the user's first,
middle and last names.

users.full.name.generator=com.liferay.portal.security.auth.DefaultFullNameGenerator

Set this to false if you want to be able to create users without an
email address. Note that not requiring an email address disables some
features that depend on an email address.

users.email.address.required=true

Set the suffix of the email address that will be automatically generated
for a user that does not have an email address. This property is not
used unless the property `users.email.address.required`{.western} is set
to false. The autogenerated email address will be the user id plus the
specified suffix.

users.email.address.auto.suffix=@no-emailaddress.com

Input a class name that implements
`com.liferay.portal.security.auth.EmailAddressGenerator`{.western}. This
class will be called to generate an email address for a user that does
not specify an email address. This class will only be used if the
property `users.email.address.required`{.western} is set to false.

users.email.address.generator=com.liferay.portal.security.auth.DefaultEmailAddressGenerator

Input a class name that implements
`com.liferay.portal.security.auth.FullNameGenerator`{.western}. This
class will be called to generate a full name from the user's first,
middle and last names.

users.full.name.generator=com.liferay.portal.security.auth.DefaultFullNameGenerator

Input a class name that implements
`com.liferay.portal.security.auth.FullNameValidator`{.western}. This
class will be called to validate user first, middle and last names.

users.full.name.validator=com.liferay.portal.security.auth.DefaultFullNameValidator

Set the maximum file size for user portraits. A value of 0 for the
maximum file size can be used to indicate unlimited file size. However,
the maximum file size allowed is set in property
`com.liferay.portal.upload.UploadServletRequestImpl.max.size`{.western}
found in `system.properties`{.western}.

users.image.max.size=307200

Set the maximum user portrait height and width in pixels. A value of
`0`{.western} indicates no restictions on user portrait dimensions.

users.image.max.height=120

users.image.max.width=100

Set this to true to record last login information for a user.

users.update.last.login=true

Input a list of sections that will be included as part of the user form
when adding a user.

users.form.add.main=details,organizations

users.form.add.identification=

users.form.add.miscellaneous=

Input a list of sections that will be included as part of the user form
when updating a user.

users.form.update.main=details,password,organizations,communities,user-groups,roles,categorization

users.form.update.identification=addresses,phone-numbers,additional-email-addresses,websites,instant-messenger,social-network,sms,open-id

users.form.update.miscellaneous=announcements,display-settings,comments,custom-attributes

Set this to true to enable reminder queries that are used to help reset
a user's password.

users.reminder.queries.enabled=true

users.reminder.queries.custom.question.enabled=true

Input a list of questions used for reminder queries.

users.reminder.queries.questions=what-is-your-primary-frequent-flyer-number,what-is-your-library-card-number,what-was-your-first-phone-number,what-was-your-first-teacher's-name,what-is-your-father's-middle-name

Set this to true to search users from the index. Set this to false to
search users from the database. Note that setting this to false will
disable the ability to search users based on Expando attributes.

users.search.with.index=true

Set a property with the prefix `users.update.user.name.`{.western} and a
suffix with the class name that should be updated whenever a user's name
has changed.

users.update.user.name.com.liferay.portlet.messageboards.model.MBMessage=true

Input a list of user attributes that will be included when exporting
users to a CSV file. You can include custom fields by adding the prefix
`expando:`{.western} to the attribute name.

users.export.csv.fields=fullName,emailAddress

Set this to false to enable users without a reminder query to reset
their password.

users.reminder.queries.required=true

Set a property with the prefix `users.update.user.name.`{.western} and a
suffix with the class name that should be updated whenever a user's name
has changed.

users.update.user.name.com.liferay.portlet.messageboards.model.MBMessage=true

Input a list of user attributes that will be included when exporting
users to a CSV file. You can include custom fields by adding the prefix
`expando:`{.western} to the attribute name.

users.export.csv.fields=fullName,emailAddress

When importing and exporting users, the portal will use this mapping to
connect LDAP user attributes and portal contact attributes.

See `com.liferay.portal.model.ContactModel`{.western} for a list of
attributes.

ldap.contact.mappings=

When importing and exporting users, the portal will use this mapping to
connect LDAP user attributes and portal contact's custom attributes.

ldap.contact.custom.mappings=

See `com.liferay.portal.model.UserModel`{.western} for a list of
attributes.

ldap.user.mappings=uuid=uuid\\nscreenName=cn\\npassword=userPassword\\nemailAddress=mail\\nfirstName=givenName\\nlastName=sn\\njobTitle=title\\ngroup=groupMembership

When importing and exporting users, the portal will use this mapping to
connect LDAP user attributes and portal user's custom attributes.

ldap.user.custom.mappings=

Set this to true if the portal should automatically create a role per
group imported from LDAP. The role will be assigned to the group so that
users can automatically inherit that role when they are assigned to the
group.

ldap.import.create.role.per.group=false

#### Facebook Connection

facebook.connect.auth.enabled=false

facebook.connect.app.id=

facebook.connect.app.secret=

facebook.connect.graph.url=https://graph.facebook.com

facebook.connect.oauth.auth.url=https://graph.facebook.com/oauth/authorize

facebook.connect.oauth.redirect.url=http://localhost:8080/c/login/facebook\_connect\_oauth

facebook.connect.oauth.token.url=https://graph.facebook.com/oauth/access\_token

#### NTLM

\

Set this to true to enable NTLM single sign on. NTLM will work only if
LDAP authentication is also enabled and the authentication is made by
screen name. If set to true, then the property
`auto.login.hooks`{.western} must

contain a reference to the class
`com.liferay.portal.security.auth.NtlmAutoLogin`{.western} and the
filter
`com.liferay.portal.servlet.filters.sso.ntlm.NtlmFilter`{.western} must
be referenced in `web.xml`{.western}.

\

ntlm.auth.enabled=false

ntlm.auth.domain.controller=127.0.0.1

ntlm.auth.domain.controller.name=EXAMPLE

ntlm.auth.domain=EXAMPLE

ntlm.auth.service.account=LIFERAY$@EXAMPLE.COM

ntlm.auth.service.password=test

#### Request Header Authentication

Set this to true to automatically import users from LDAP if they do not
exist in the portal. The property `auto.login.hooks`{.western} must
contain a reference to the class
`com.liferay.portal.security.auth.RequestHeaderAutoLogin`{.western} to
enable request header authentication.

request.header.auth.import.from.ldap=false

#### Authentication Token

Set this to true to enable authentication token security checks. The
checks can be disabled for specific actions via the property
`auth.token.ignore.actions`{.western} or for specific portlets via the
init parameter `check-auth-token`{.western} in `portlet.xml`{.western}.

auth.token.check.enabled=true

Set the authentication token class. This class must implement
`com.liferay.portal.security.auth.AuthToken`{.western}. This class is
used to prevent CSRF attacks. See
http://issues.liferay.com/browse/LPS-8399 for more information.

auth.token.impl=com.liferay.portal.security.auth.SessionAuthToken

Input a list of comma delimited struts actions that will not be checked
for an authentication token.

auth.token.ignore.actions=\\

/asset/rss,\\

\\

/blogs/rss,\\

\\

/document\_library/edit\_file\_entry,\\

\\

/journal/rss,\\

\\

/image\_gallery/edit\_image,\\

\\

/login/login,\\

\\

/message\_boards/rss,\\

\\

/wiki/edit\_page\_attachment,\\

/wiki/rss

\
\

Set the character sets for password validation.

passwords.passwordpolicytoolkit.charset.lowercase=abcdefghjkmnpqrstuvwxyz

passwords.passwordpolicytoolkit.charset.numbers=23456789

passwords.passwordpolicytoolkit.charset.symbols=\_.!@$\*=-?

passwords.passwordpolicytoolkit.charset.uppercase=ABCDEFGHJKLMNPQRSTUVWXYZ

#### Groups and Roles

Input a list of comma delimited system group names that will exist in
addition to the standard system groups. When the server starts, the
portal checks to ensure all system groups exist. Any missing system
group will be created by the portal.

system.groups=

Input a list of comma delimited system role names that will exist in
addition to the standard system roles. When the server starts, the
portal checks to ensure all system roles exist. Any missing system role
will be created by the portal.

The standard system roles are: Administrator, Guest, Power User, and
User. These roles cannot be removed or renamed.

system.roles=

Set the description of the Administrator system role.

system.role.Administrator.description=Administrators are super users who
can do anything.

Set the description of the Guest system role.

system.role.Guest.description=Unauthenticated users always have this
role.

Set the description of the Owner system role.

system.role.Owner.description=This is an implied role with respect to
the objects users create.

Set the description of the Power User system role.

system.role.Power.User.description=Power Users have their own public and
private pages.

Set the description of the User system role.

system.role.User.description=Authenticated users should be assigned this
role.

Input a list of comma delimited system community role names that will
exist in addition to the standard system community roles. When the
server starts, the portal checks to ensure all system community roles
exist. Any missing system community role will be created by the portal.

The standard system community roles are: Community Administrator,
Community Member, and Community Owner. These roles cannot be removed or
renamed.

system.community.roles=

Set the description of the Community Administrator system community
role.

system.community.role.Community.Administrator.description=Community
Administrators are super users of their community but cannot make other
users into Community Administrators.

Set the description of the Community Member system community role.

system.community.role.Community.Member.description=All users who belong
to a community have this role within that community.

Set the description of the Community Owner system community role.

system.community.role.Community.Owner.description=Community Owners are
super users of their community and can assign community roles to users.

Input a list of comma delimited system organization role names that will
exist in addition to the standard system organization roles. When the
server starts, the portal checks to ensure all system organization roles
exist. Any missing system organization role will be created by the
portal.

The standard system organization roles are: Organization Administrator,
Organization Member, and Organization Owner. These roles cannot be
removed or renamed.

system.organization.roles=

Set the description of the Organization Administrator system
organization role.

System.organization.role.Organization.Administrator.description=Organization
Administrators are super users of their organization but cannot make
other users into Organization Administrators.

Set the description of the Organization Member system organization role.

system.organization.role.Organization.Member.description=All users who
belong to a organization have this role within that organization.

Set the description of the Organization Owner system organization role.

system.organization.role.Organization.Owner.description=Organization
Owners are super users of their organization and can assign organization
roles to users.

Omni admin users can administer the portal's core functionality: gc,
shutdown, etc. Omni admin users must belong to the default company.

Multiple portal instances might be deployed on one application server,
and not all of the administrators should have access to this core
functionality. Input the ids of users who are omniadmin users.

Leave this field blank if users who belong to the right company and have
the Administrator role are allowed to administer the portal's core
functionality.

omniadmin.users=

Set the following to true if all users are required to agree to the
terms of use.

terms.of.use.required=true

Specify the group id and the article id of the Web Content article that
will be displayed as the terms of use. The default text will be used if
no Web Content article is specified.

terms.of.use.journal.article.group.id=

terms.of.use.journal.article.id=

Specify subtypes of roles if you want to be able to search for roles
using your custom criteria.

roles.community.subtypes=

roles.organization.subtypes=

roles.regular.subtypes=

#### Organizations

Specify the names of your organization(s). For example, you could use
Teams, Clubs, Parishes, or anything which describes your hierarchical
structure.

organizations.types=regular-organization,location

Specify which organizations can be at the top of the hierarchy.

organizations.rootable[regular-organization]=true

Specify which organizations can be children.

organizations.children.types[regular-organization]=regular-organization,location

Set this to true if organizations can have an associated country.

organizations.country.enabled[regular-organization]=false

Set this to true if organizations must have an associated country.

organizations.country.required[regular-organization]=false

By default, Locations cannot be at the top of the hierarchy, because
they cannot have children. You must specify the following properties for
each organization type you create.

*Example:*

organizations.rootable[location]=false

\#organizations.children.types[location]=

organizations.country.enabled[location]=true

organizations.country.required[location]=true

Input a list of sections that will be included as part of the
organization form when adding an organization.

organizations.form.add.main=details

organizations.form.add.identification=

organizations.form.add.miscellaneous=

Input a list of sections that will be included as part of the
organization form when updating an organization.

organizations.form.update.main=details

organizations.form.update.identification=addresses,phone-numbers,additional-email-addresses,websites,services

organizations.form.update.miscellaneous=comments,reminder-queries,custom-attributes

Set this property to true if you want any administrator that creates an
organization to be automatically assigned to that organization.

organizations.assignment.auto=false

Set this property to false if you want any administrator of an
organization to be able to assign any user to that organization. By
default, he will only be able to assign the users of the organizations
and suborganizations that he can manage.

organizations.assignment.strict=true

Set this property to true if you want users to only be members of the
organizations to which they are assigned explicitly. By default they
will also become implicit members of the ancestors of those
organizations. for example if a user belongs to Liferay Spain he will
implicitly be a member of the ancestors Liferay Europe and Liferay
Global and will be able to access their private pages.

organizations.membership.strict=false

#### Security Manager

Set this to true to use Liferay's `java.lang.SecurityManager`{.western}
implementation. This should never be set to true except for debugging
purposes.

portal.security.manager.enable=false

#### Basic Authentication

Set this to true to require a password when using basic authentication.
Only set this to false if additional security measures are in place to
ensure users have been properly authenticated.

basic.auth.password.required=true

#### Languages and Time Zones

Specify the available locales. Messages corresponding to a specific
language are specified in properties files with file names matching that
of `content/Language_*.properties`{.western}. These values can also be
overridden in properties files with file names matching that of
`content/Language-ext_*.properties`{.western}. Use a comma to separate
each entry.

All locales must use UTF-8 encoding.

See the following links to specify language and country codes:

http://ftp.ics.uci.edu/pub/ietf/http/related/iso639.txt

http://userpage.chemie.fu-berlin.de/diverse/doc/ISO\_3166.html

locales=ar\_SA,ca\_AD,ca\_ES,zh\_CN,zh\_TW,cs\_CZ,nl\_NL,en\_US,fi\_FI,fr\_FR,de\_DE,el\_GR,hu\_HU,it\_IT,ja\_JP,ko\_KR,nb\_NO,fa\_IR,pl\_PL,pt\_BR,pt\_PT,ru\_RU,es\_ES,sv\_SE,tr\_TR,vi\_VN

Set the following to true if unauthenticated users get their preferred
language from the Accept-Language header. Set the following to false if
unauthenticated users get their preferred language from their company.

locale.default.request=false

Specify the available time zones. The specified ids must match those
from the class `java.util.TimeZone`{.western}.

time.zones=\\

Pacific/Midway,\\

Pacific/Honolulu,\\

America/Anchorage,\\

America/Los\_Angeles,\\

America/Denver,\\

America/Chicago,\\

America/New\_York,\\

America/Puerto\_Rico,\\

America/St\_Johns,\\

America/Sao\_Paulo,\\

America/Noronha,\\

Atlantic/Azores,\\

UTC,\\

Europe/Lisbon,\\

Europe/Paris,\\

Europe/Istanbul,\\

Asia/Jerusalem,\\

Asia/Baghdad,\\

Asia/Tehran,\\

Asia/Dubai,\\

Asia/Kabul,\\

Asia/Karachi,\\

Asia/Calcutta,\\

Asia/Katmandu,\\

Asia/Dhaka,\\

Asia/Rangoon,\\

Asia/Saigon,\\

Asia/Shanghai,\\

Asia/Tokyo,\\

Asia/Seoul,\\

Australia/Darwin,\\

Australia/Sydney,\\

Pacific/Guadalcanal,\\

Pacific/Auckland,\\

Pacific/Enderbury,\\

Pacific/Kiritimati

#### Look and Feel

Set the following to false if the system does not allow users to modify
the look and feel.

look.and.feel.modifiable=true

Set the default layout template id.

default.layout.template.id=2\_columns\_ii

Set the default theme id for regular themes.

default.regular.theme.id=classic

Set the default color scheme id for regular themes.

default.regular.color.scheme.id=01

Set the default theme id for wap themes.

default.wap.theme.id=mobile

Set the default color scheme id for wap themes.

default.wap.color.scheme.id=01

Set the following to true if you want a change in the theme selection of
the public or private group to automatically be applied to the other
(i.e. if public and private group themes should always be the same).

theme.sync.on.group=false

#### Layouts

Set this to true to remember maximized window states across different
pages.

layout.remember.maximized.window.state=false

Set this to true to enable comments for pages.

layout.comments.enabled=true

Set this to true to remember maximized window states across different
pages.

layout.remember.maximized.window.state=false

#### Editors

You can configure individual JSP pages to use a specific implementation
of the available WYSIWYG editors:
`ckeditor, fckeditor, liferay, simple, tinymce`{.western}, or
`tinymcesimple`{.western}.

editor.wysiwyg.default=ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.blogs.edit\_entry.jsp=ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.calendar.edit\_configuration.jsp=ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.enterprise\_admin.view.jsp=ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.invitation.edit\_configuration.jsp=ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.journal.edit\_article\_content.jsp=ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.journal.edit\_article\_content\_xsd\_el.jsp=\
ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.journal.edit\_configuration.jsp=ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.login.configuration.jsp=ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.mail.edit.jsp=ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.mail.edit\_message.jsp=ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.message\_boards.edit\_configuration.jsp=\
ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.shopping.edit\_configuration.jsp=ckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.wiki.edit.html.jsp=ckeditor

#### Fields

Input a list of comma delimited user types who can edit their own
fields. Valid types are `administrator`{.western}, `user-mx`{.western},
and `user-without-mx`{.western}.

Set a value of `administrator`{.western} if an administrator can edit
the specified field. An administrator is anyone who has the
Administrator role.

Set a value of `user-mx`{.western} if a user who has an email address
that matches the company mail suffix can edit the specified field.

Set a value of `user-without-mx`{.western} if a user who does not have
an email address that matches the company mail suffix can edit the
specified field.

Set all three values if all users can edit the specified field. Set a
combination of the three values if only a combination of the users can
edit the specified field.

\

field.editable.com.liferay.portal.model.User.screenName=administrator,user-with-mx,user-without-mx

field.editable.com.liferay.portal.model.User.emailAddress=administrator,user-with-mx,user-without-mx

#### Request

Portlets that have been configured to use private request attributes in
`liferay-portlet.xml`{.western} may still want to share some request
attributes. This property allows you to configure which request
attributes will be shared.

Set a comma delimited list of attribute names that will be shared when
the attribute name starts with one of the specified attribute names. For
example, if you set the value to `hello_,world_,`{.western} then all
attribute names that start with `hello_`{.western} or `world_`{.western}
will be shared.

request.shared.attributes=LIFERAY\_SHARED\_

#### Session

Specify the number of minutes before a session expires. This value is
always overridden by the value set in `web.xml`{.western}.

session.timeout=30

Specify the number of minutes before a warning is sent to the user
informing the user of the session expiration. Specify `0`{.western} to
disable any warnings.

session.timeout.warning=1

Set the auto-extend mode to true to avoid having to ask the user whether
to extend the session or not. Instead it will be automatically extended.
The purpose of this mode is to keep the session open as long as the user
browser is open and with a portal page loaded. It is recommended to use
this setting along with a smaller `session.timeout`{.western}, such as 5
minutes for better performance.

session.timeout.auto.extend=false

Set this to true if the user is redirected to the default page when the
session expires.

session.timeout.redirect.on.expire=false

Portlets that have been configured to use private session attributes in
`liferay-portlet.xml`{.western} may still want to share some session
attributes. This property allows you to configure which session
attributes will be shared. Set a comma delimited list of attribute names
that will be shared when the attribute name starts with one of the
specified attribute names. For example, if you set the value to
`hello_,world_,`{.western} then all attribute names that start with
`hello_`{.western} or `world_`{.western} will be shared.

Note that this property is used to specify the sharing of session
attributes from the portal to the portlet. This is not used to specify
session sharing between portlet WARs or from the portlet to the portal.

session.shared.attributes=org.apache.struts.action.LOCALE,COMPANY\_,USER\_,LIFERAY\_SHARED\_

Set this to false to disable all persistent cookies. Features like
automatically logging in will not work.

session.enable.persistent.cookies=true

The login process sets several cookies if persistent cookies are
enabled. Set this property to set the domain of those cookies.

session.cookie.domain=

Set the following to true to invalidate the session when a user logs
into the portal. This helps prevents phishing. Set this to false if you
need the guest user and the authenticated user to have the same session.

session.enable.phishing.protection=true

Set the following to true to test whether users have cookie support
before allowing them to sign in. This test will always fail if
`tck.url`{.western} is set to true because that property disables
session cookies.

session.test.cookie.support=true

Set the following to true to disable sessions. Doing this will use
cookies to remember the user across requests. This is useful if you want
to scale very large sites where the user may be sent to a different
server for each request. The drawback to this approach is that you must
not rely on the API for sessions provided by the servlet and portlet
specs.

This feature is only available for Tomcat and requires that you set
Tomcat's Manager class to
`com.liferay.support.tomcat.session.SessionLessManagerBase`{.western}.

session.disabled=false

Input a list of comma delimited class names that extend
`com.liferay.portal.struts.SessionAction`{.western}. These classes will
run at the specified event.

\#

\# Servlet session create event

\#

servlet.session.create.events=com.liferay.portal.events.SessionCreateAction

\
\

\#

\# Servlet session destroy event

\#

servlet.session.destroy.events=com.liferay.portal.events.SessionDestroyAction

Set the following to true to track user clicks in memory for the
duration of a user's session. Setting this to true allows you to view
all live sessions in the Admin portlet.

session.tracker.memory.enabled=true

Set the following to true to track user clicks in the database after a
user's session is invalidated. Setting this to true allows you to
generate usage reports from the database. Use this cautiously because
this will store a lot of usage data.

session.tracker.persistence.enabled=false

Set the following to true to convert the tracked paths to friendly URLs.

session.tracker.friendly.paths.enabled=false

Enter a list of comma delimited paths that should not be tracked.

session.tracker.ignore.paths=\\

/portal/render\_portlet,\\

\\

/document\_library/get\_file

#### HTTP

Set the maximum number of connections.

com.liferay.portal.util.HttpImpl.max.connections.per.host=2

com.liferay.portal.util.HttpImpl.max.total.connections=20

Set the proxy authentication type.

com.liferay.portal.util.HttpImpl.proxy.auth.type=username-password

com.liferay.portal.util.HttpImpl.proxy.auth.type=ntlm

Set user name and password used for HTTP proxy authentication.

com.liferay.portal.util.HttpImpl.proxy.username=

com.liferay.portal.util.HttpImpl.proxy.password=

Set additional properties for NTLM authentication.

com.liferay.portal.util.HttpImpl.proxy.ntlm.domain=

com.liferay.portal.util.HttpImpl.proxy.ntlm.host=

Set the connection timeout when fetching HTTP content.

com.liferay.portal.util.HttpImpl.timeout=10000

#### JAAS

Set the following to false to disable JAAS security checks. Disabling
JAAS speeds up login. JAAS must be disabled if administrators are to be
able to impersonate other users.

portal.jaas.enable=false

By default,
`com.liferay.portal.security.jaas.PortalLoginModule`{.western} loads the
correct JAAS login module based on what application server or servlet
container the portal is deployed on. Set a JAAS implementation class to
override this behavior.

portal.jaas.impl=

The JAAS process may pass in an encrypted password and the
authentication will only succeed if there is an exact match. Set this
property to false to relax that behavior so the user can input an
unencrypted password.

portal.jaas.strict.password=false

Set the following to true to enable administrators to impersonate other
users.

portal.impersonation.enable=true

#### LDAP

Set the values used to connect to a LDAP store.

ldap.factory.initial=com.sun.jndi.ldap.LdapCtxFactory

ldap.base.provider.url=ldap://localhost:10389

ldap.base.dn=dc=example,dc=com

ldap.security.principal=uid=admin,ou=system

ldap.security.credentials=secret

ldap.referral=follow

Settings for `com.liferay.portal.security.auth.LDAPAuth`{.western} can
be configured from the Admin portlet. It provides out of the box support
for Apache Directory Server, Microsoft Active Directory Server, Novell
eDirectory, and OpenLDAP. The default settings are for Apache Directory
Server.

The `LDAPAuth`{.western} class must be specified in the property
`auth.pipeline.pre`{.western} to be executed.

Encryption is implemented by
`com.liferay.util.Encryptor.provider.class`{.western} in
`system.properties`{.western}.

ldap.auth.enabled=false

ldap.auth.required=false

Set either bind or password-compare for the LDAP authentication method.
Bind is preferred by most vendors so that you don't have to worry about
encryption strategies.

ldap.auth.method=bind

ldap.auth.method=password-compare

Set the password encryption to used to compare passwords if the property
`ldap.auth.method`{.western} is set to password-compare.

ldap.auth.password.encryption.algorithm=

ldap.auth.password.encryption.algorithm.types=MD5,SHA

Active Directory stores information about the user account as a series
of bit fields in the `UserAccountControl`{.western} attribute.

If you want to prevent disabled accounts from logging into the portal
you need to use a search filter similar to the following:

(&(objectclass=person)(userprincipalname=@email\_address@)(!(UserAccountControl:1.2.840.113556.1.4.803:=2)))

See the following links:

[http://support.microsoft.com/kb/305144/](http://support.microsoft.com/kb/305144/)

[http://support.microsoft.com/?kbid=269181](http://support.microsoft.com/?kbid=269181)

ldap.auth.search.filter=(mail=@email\_address@)

You can write your own class that extends*
*`com.liferay.portal.security.ldap.AttributesTransformer`{.western} to
transform the LDAP attributes before a user or group is imported to the
LDAP store.

ldap.attrs.transformer.impl=com.liferay.portal.security.ldap.AttributesTransformer

You can write your own class that extends
`com.liferay.portal.security.ldap.LDAPUser`{.western} to customize the
behavior for exporting portal users to the LDAP store.

ldap.user.impl=com.liferay.portal.security.ldap.LDAPUser

When a user is exported to LDAP and the user does not exist, the user
will be created with the following default object classes.

ldap.user.default.object.classes=top,person,inetOrgPerson,organizationalPerson

When importing and exporting users, the portal will use this mapping to
connect LDAP user attributes and portal user variables.

ldap.user.mappings=screenName=cn\\npassword=userPassword\\nemailAddress=mail\\nfirstName=givenName\\nlastName=sn\\njobTitle=title\\ngroup=groupMembership

When importing groups, the portal will use this mapping to connect LDAP
group attributes and portal user group variables.

ldap.group.mappings=groupName=cn\\ndescription=description\\nuser=uniqueMember

Settings for importing users and groups from LDAP to the portal.

ldap.import.enabled=false

ldap.import.on.startup=false

ldap.import.interval=10

ldap.import.user.search.filter=(objectClass=inetOrgPerson)

ldap.import.group.search.filter=(objectClass=groupOfUniqueNames)

Set either user or group for import method. If set to user, portal will
import all users and the groups associated with those users. If set to
group, the portal import all groups and the users associated those
groups.

This value should be set based on how your LDAP server stores group
membership information.

ldap.import.method=user

ldap.import.method=group

Settings for exporting users from the portal to LDAP. This allows a user
to modify his first name, last name, etc. in the portal and have that
change get pushed to the LDAP server. This will only be active if the
property `ldap.auth.enabled`{.western} is also set to true. New users
and groups will be created at the specified DN.

ldap.export.enabled=true

ldap.users.dn=ou=users,dc=example,dc=com

ldap.groups.dn=ou=groups,dc=example,dc=com

Set this to true to use the LDAP's password policy instead of the portal
password policy.

ldap.password.policy.enabled=false

Set these values to be a portion of the error message returned by the
appropriate directory server to allow the portal to recognize messages
from the LDAP server. The default values will work for Fedora DS.

ldap.error.password.age=age

ldap.error.password.expired=expired

ldap.error.password.history=history

ldap.error.password.not.changeable=not allowed to change

ldap.error.password.syntax=syntax

ldap.error.password.trivial=trivial

ldap.error.user.lockout=retry limit

#### CAS

Set this to true to enable CAS single sign on. NTLM will work only if
LDAP authentication is also enabled and the authentication is made by
screen name. If set to true, then the property
`auto.login.hooks`{.western} must contain a reference to the class
`com.liferay.portal.security.auth.CASAutoLogin`{.western} and the filter
`com.liferay.portal.servlet.filters.sso.cas.CASFilter`{.western} must be
referenced in `web.xml`{.western}.

cas.auth.enabled=false

A user may be authenticated from CAS and not yet exist in the portal.
Set this to true to automatically import users from LDAP if they do not
exist in the portal.

cas.import.from.ldap=false

Set the default values for the required CAS URLs. Set either
`cas.server.name`{.western} or `cas.service.url`{.western}. Setting
`cas.server.name`{.western} allows deep linking. See LEP-4423.

cas.login.url=https://localhost:8443/cas-web/login

cas.logout.url=https://localhost:8443/cas-web/logout

cas.server.name=localhost:8080

cas.service.url=

\#cas.service.url=http://localhost:8080/c/portal/login

cas.service.url=http://localhost:8080/c/portal/login

cas.validate.url=https://localhost:8443/cas-web/proxyValidate

#### NTLM

Set this to true to enable NTLM single sign on. NTLM will work only if
LDAP authentication is also enabled and the authentication is made by
screen name. If set to true, then the property
`auto.login.hooks`{.western} must contain a reference to the class
`com.liferay.portal.security.auth.NtlmAutoLogin`{.western} and the
filter
`com.liferay.portal.servlet.filters.sso.ntlm.NtlmFilter`{.western} must
be referenced in `web.xml`{.western}.

ntlm.auth.enabled=false

ntlm.auth.domain.controller=127.0.0.1

ntlm.auth.domain=EXAMPLE

#### OpenID

Set this to true to enable OpenId authentication. If set to true, then
the property `auto.login.hooks`{.western} must contain a reference to
the class `com.liferay.portal.security.auth.OpenIdAutoLogin`{.western}.

open.id.auth.enabled=true

#### OpenSSO

These properties control Liferay's integration with OpenSSO.

Set this to true to enable OpenSSO authentication.

open.sso.auth.enabled=false

Set the log in URL and log out URL. The first URL is the link to your
OpenSSO server (which can be the same server as the one running
Liferay); the second URL is the link to your Liferay Portal.

open.sso.login.url=http://openssohost.example.com:8080/opensso/UI/Login?goto=http://portalhost.example.com:8080/c/portal/login

open.sso.logout.url=http://openssohost.example.com:8080/opensso/UI/Logout?goto=http://portalhost.example.com:8080/web/guest/home

Set the URL to the OpenSSO service.

open.sso.service.url=http://openssohost.example.com:8080/opensso

Set the HTTP attribute name for the user's screen name.

open.sso.screen.name.attr=uid

Set the HTTP attribute name for the user's email address.

open.sso.email.address.attr=mail

Set the HTTP attribute name for the user's Common Name.

open.sso.first.name.attr=cn

Set the HTTP attribute name for the user's Surname.

open.sso.last.name.attr=sn

#### SiteMinder

Set this to true to enable CA SiteMinder single sign on. If set to true,
then the property `auto.login.hooks`{.western} must contain a reference
to the class
`com.liferay.portal.security.auth.SiteMinderAutoLogin`{.western} and the
`logout.events.post`{.western} must have a reference to
`com.liferay.portal.events.SiteMinderLogoutAction`{.western} for logout
to work.

siteminder.auth.enabled=false

A user may be authenticated from SiteMinder and not yet exist in the
portal. Set this to true to automatically import users from LDAP if they
do not exist in the portal.

siteminder.import.from.ldap=false

Set this to the name of the user header that SiteMinder passes to the
portal.

siteminder.user.header=SM\_USER

#### Authentication Pipeline

Input a list of comma delimited class names that implement
`com.liferay.portal.security.auth.Authenticator`{.western}. These
classes will run before or after the portal authentication begins.

The `Authenticator`{.western} class defines the constant values that
should be used as return codes from the classes implementing the
interface. If authentication is successful, return SUCCESS; if the user
exists but the passwords do not match, return FAILURE; and if the user
does not exist on the system, return DNE.

Constants in Authenticator:

public static final int SUCCESS = 1;

public static final int FAILURE = -1;

public static final int DNE = 0;

In case you have several classes in the authentication pipeline, all of
them have to return SUCCESS if you want the user to be able to login. If
one of the authenticators returns FAILURE or DNE, the login fails.

Under certain circumstances, you might want to keep the information in
the portal database in sync with an external database or an LDAP server.
This can easily be achieved by implementing a class via
`LDAPAuth`{.western} that updates the information stored in the portal
user database whenever a user signs in.

Each portal instance can be configured at run time to either
authenticate based on user ids or email addresses. See the Admin portlet
for more information.

Available authenticators are:

`com.liferay.portal.security.auth.LDAPAuth`{.western}

See the LDAP properties to configure the behavior of the
`LDAPAuth`{.western} class.

auth.pipeline.pre=com.liferay.portal.security.auth.LDAPAuth

auth.pipeline.post=

Set this to true to enable password checking by the internal portal
authentication. If set to false, you're essentially delegating password
checking is delegated to the authenticators configured in
`auth.pipeline.pre`{.western} and `auth.pipeline.post`{.western}
settings.

auth.pipeline.enable.liferay.check=true

Input a list of comma delimited class names that implement
`com.liferay.portal.security.auth.AuthFailure`{.western}. These classes
will run when a user has a failed login or when a user has reached the
maximum number of failed logins.

auth.failure=com.liferay.portal.security.auth.LoginFailure

auth.max.failures=com.liferay.portal.security.auth.LoginMaxFailures

auth.max.failures.limit=5

Set the following to true if users are forwarded to the last visited
path upon successful login. If set to false, users will be forwarded to
their default layout page.

auth.forward.by.last.path=true

The login page reads a redirect by a parameter named
`redirect`{.western}. If this property is set to true, then users will
be redirected to the given redirect path upon successful login. If the
user does not have permission to view that page, then the rule set by
the property `auth.forward.by.last.path`{.western} will apply.

You can set the redirect manually from another application, by appending
the *redirect* parameter in a url that looks like this:
`/c/portal/login?redirect=%2Fgroup%2Femployees%2Fcalendar`{.western}.
This url will redirect the user to the path
`/group/employees/calendar`{.western} upon successful login.

auth.forward.by.redirect=true

Enter a list of comma delimited paths that can be considered part of the
last visited path.

auth.forward.last.paths=/document\_library/get\_file

Enter a URL that will be used to login portal users whenever needed. By
default, the portal's login page is used.

\#auth.login.url=/web/guest/home

Enter a friendly URL of a page that will be used to login portal users
whenever the user is navigating a community and authentication is
needed. By default, the portal's login page or the URL set in the
property `auth.login.url`{.western} is used.

auth.login.community.url=/login

Enter the name of the login portlet used in a page identified by the URL
of the previous property (if one has been set). This will allow the
portlet to have access to the redirect parameter and thus forward the
user to the page where he was trying to access when necessary. You
should leave the default value unless you have your own custom login
portlet.

auth.login.portlet.name=58

Enter a list of comma delimited paths that do not require
authentication.

auth.public.paths=\\

/blogs/find\_entry,\\

/blogs/rss,\\

/blogs/trackback,\\

\\

/bookmarks/open\_entry,\\

\\

/document\_library/get\_file,\\

\\

/journal/get\_article,\\

/journal/get\_articles,\\

/journal/get\_latest\_article\_content,\\

/journal/get\_structure,\\

/journal/get\_template,\\

/journal/view\_article\_content,\\

/journal\_articles/view\_article\_content,\\

\\

/layout\_management/sitemap,\\

\\

/message\_boards/find\_category,\\

/message\_boards/find\_message,\\

/message\_boards/find\_thread,\\

/message\_boards/get\_message\_attachment,\\

/message\_boards/rss,\\

\\

/my\_places/view,\\

\\

/polls/view\_chart,\\

\\

/portal/emoticons,\\

/portal/expire\_session,\\

/portal/extend\_session,\\

/portal/extend\_session\_confirm,\\

/portal/json\_service,\\

/portal/logout,\\

/portal/open\_id\_request,\\

/portal/open\_id\_response,\\

/portal/session\_click,\\

/portal/session\_tree\_js\_click,\\

/portal/status,\\

\\

/search/open\_search,\\

/search/open\_search\_description.xml,\\

\\

/shopping/notify,\\

\\

/tags/rss,\\

\\

/wiki/get\_page\_attachment,\\

/wiki/rss

#### Auto Login

Input a list of comma delimited class names that implement
`com.liferay.portal.security.auth.AutoLogin`{.western}. These classes
will run in consecutive order for all unauthenticated users until one of
them return a valid user id and password combination. If no valid
combination is returned, then the request continues to process normally.
If a valid combination is returned, then the portal will automatically
login that user with the returned user id and password combination.

For example,
`com.liferay.portal.security.auth.RememberMeAutoLogin`{.western} reads
from a cookie to automatically log in a user who previously logged in
while checking the *Remember Me* box.

This interface allows deployers to easily configure the portal to work
with other SSO servers. See
`com.liferay.portal.security.auth.CASAutoLogin`{.western} for an example
of how to configure the portal with Yale's SSO server.

auto.login.hooks=com.liferay.portal.security.auth.CASAutoLogin,com.liferay.portal.security.auth.NtlmAutoLogin,com.liferay.portal.security.auth.OpenIdAutoLogin,com.liferay.portal.security.auth.OpenSSOAutoLogin,com.liferay.portal.security.auth.RememberMeAutoLogin,com.liferay.portal.security.auth.SiteMinderAutoLogin

Set the hosts that will be ignored for auto login.

auto.login.ignore.hosts=

Set the paths that will be ignored for auto login.

auto.login.ignore.paths=

#### SSO with MAC (Message Authentication Code)

To use SSO with MAC, post to an URL like:

`http://localhost:8080/c/portal/login?cmd=already-registered&login=<userId|emailAddress>&password=<MAC>`{.western}

Pass the MAC in the password field. Make sure the MAC gets URL encoded
because it might contain characters not allowed in a URL.

SSO with MAC also requires that you set the following property in
system.properties:

com.liferay.util.servlet.SessionParameters=false

See the following links:

http://issues.liferay.com/browse/LEP-1288

http://en.wikipedia.org/wiki/Message\_authentication\_code

Set the following to true to enable SSO with MAC.

auth.mac.allow=false

Set the algorithm to use for MAC encryption.

auth.mac.algorithm=MD5

Set the shared key used to generate the MAC.

auth.mac.shared.key=

#### Passwords

Set the following encryption algorithm to encrypt passwords. The default
algorithm is SHA (SHA-1). If set to NONE, passwords are stored in the
database as plain text. The SHA-512 algorithm is currently unsupported.

*Examples:*

passwords.encryption.algorithm=CRYPT

passwords.encryption.algorithm=MD2

passwords.encryption.algorithm=MD5

passwords.encryption.algorithm=NONE

passwords.encryption.algorithm=SHA

passwords.encryption.algorithm=SHA-256

passwords.encryption.algorithm=SHA-384

passwords.encryption.algorithm=SSHA

Digested passwords are encoded via base64 or hex encoding. The default
is base64.

passwords.digest.encoding=base64

\#passwords.digest.encoding=hex

Input a class name that extends
`com.liferay.portal.security.pwd.BasicToolkit`{.western}. This class
will be called to generate and validate passwords.

*Examples:*

passwords.toolkit=com.liferay.portal.security.pwd.PasswordPolicyToolkit

passwords.toolkit=com.liferay.portal.security.pwd.RegExpToolkit

If you choose to use
`com.liferay.portal.security.pwd.PasswordPolicyToolkit`{.western} as
your password toolkit, you can choose either static or dynamic password
generation. Static is set through the property
`passwords.passwordpolicytoolkit.static`{.western} and dynamic uses the
class `com.liferay.util.PwdGenerator`{.western} to generate the
password. If you are using LDAP password syntax checking, you will also
have to use the static generator so that you can guarantee that
passwords obey its rules.

*Examples:*

passwords.passwordpolicytoolkit.generator=static

passwords.passwordpolicytoolkit.generator=dynamic

passwords.passwordpolicytoolkit.static=iheartliferay

If you choose to use
`com.liferay.portal.security.pwd.RegExpToolkit`{.western} as your
password toolkit, set the regular expression pattern that will be used
to generate and validate passwords.

Note that `\`{.western} is replaced with `\\`{.western} to work in Java.

The first pattern ensures that passwords must have at least 4 valid
characters consisting of digits or letters.

The second pattern ensures that passwords must have at least 8 valid
characters consisting of digits or letters.

*Examples:*

passwords.regexptoolkit.pattern=(?=.{4})(?:[a-zA-Z0-9]\*)

passwords.regexptoolkit.pattern=(?=.{8})(?:[a-zA-Z0-9]\*)

Set the length and key for generating passwords.

*Examples: *

passwords.regexptoolkit.charset=0123456789

passwords.regexptoolkit.charset=0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz

*Examples: *

passwords.regexptoolkit.length=4

passwords.regexptoolkit.length=8

Set the name of the default password policy.

passwords.default.policy.name=Default Password Policy

#### Permissions

Set the default permission checker class used by
`com.liferay.portal.security.permission.PermissionCheckerFactory`{.western}
to check permissions for actions on objects. This class can be
overridden with a custom class that extends
`com.liferay.portal.security.permission.PermissionCheckerImpl`{.western}.

permissions.checker=com.liferay.portal.security.permission.PermissionCheckerImpl

Set the algorithm used to check permissions for a user. This is useful
so that you can optimize the search for different databases. See
`com.liferay.portal.service.impl.PermissionLocalServiceImpl`{.western}.
The default is method two.

The first algorithm uses several *if* statements to query the database
for these five things in order. If it finds any one of them, it returns*
true: *

-   Is the user connected to one of the permissions via group or
    organization roles?

-   Is the user associated with groups or organizations that are
    directly connected to one of the permissions?

-   Is the user connected to one of the permissions via user roles?

-   Is the user connected to one of the permissions via user group
    roles?

-   Is the user directly connected to one of the permissions?

\
\

The second algorithm (the default) does a database join and checks the
permissions in one step, by calling
`countByGroupsRoles, countByGroupsPermissions, countByUsersRoles, countByUserGroupRole`{.western},
and `countByUsersPermissions`{.western} in one method.

The third algorithm checks the permissions by checking for three things.
It combines the role check into one step. If it finds any of the
following items, it returns *true:*

-   Is the user associated with groups or organizations that are
    directly connected to one of the permissions?

-   Is the user associated with a role that is directly connected to one
    of the permissions?

-   Is the user directly connected to one of the permissions?

\
\

The fourth algorithm does a database join and checks the permissions
that algorithm three checks in one step, by calling
`countByGroupsPermissions, countByRolesPermissions`{.western}, and
`countByUsersPermissions`{.western} in one method.

Algorithm 5 moves to a completely role-based permissions check for
better performance. Permissions by users are no longer supported, yet it
uses the same table structure as Algorithms 1-4.

Algorithm 6 is the current algorithm for Liferay 6 and above. It
supports role-based permissions like Algorithm 5, but does so by using
only one table and bitwise operations. This makes it perform far better
than the other algorithms.

permissions.user.check.algorithm=1

permissions.user.check.algorithm=2

permissions.user.check.algorithm=3

permissions.user.check.algorithm=4

permissions.user.check.algorithm=5

permissions.user.check.algorithm=6

Set the default permissions list filter class. This class must implement
`com.liferay.portal.kernel.security.permission.PermissionsListFilter`{.western}.
This is used if you want to filter the list of permissions before it is
actually persisted. For example, if you want to make sure that all users
who create objects never have the UPDATE action, then you can filter
that list and remove any permissions that have the UPDATE action before
it is persisted.

permissions.list.filter=com.liferay.portal.security.permission.PermissionsListFilterImpl

Set this to true to configure permission caching to block. See the
property `ehcache.blocking.cache.allowed`{.western} for more
information.

permissions.object.blocking.cache=false

The permissions cache uses a thread local map to store the most
frequently accessed items to lower the number of queries to the
underlying cache. Set the maximum map size to 0 to disable the thread
level cache.

permissions.thread.local.cache.max.size=100

Set the following to true to automatically check the view permission on
parent categories or folders when checking the permission on an specific
item.

For example, if set to true, to be able to have access to a document, a
user must have the view permission on the document's folder and all its
parent folders. Or, to have access to a comment, a user must have the
view permission on the comments's category and all its parent
categories.

permissions.view.dynamic.inheritance=true

#### Captcha

Set the maximum number of captcha checks per portlet session. Set this
value to 0 to always check. Set this value to a number less than 0 to
never check. Unauthenticated users will always be checked on every
request if captcha checks is enabled.

captcha.max.challenges=1

Set whether or not to use captcha checks for the following actions.

captcha.check.portal.create\_account=true

captcha.check.portal.send\_password=true

captcha.check.portlet.message\_boards.edit\_category=false

captcha.check.portlet.message\_boards.edit\_message=false

Set the engine used to generate captchas. reCAPTCHA uses an external
service that must be configured independently but provides an audible
alternative which makes the captcha accessible to the visually impaired.

captcha.engine.impl=com.liferay.portal.captcha.recaptcha.ReCaptchaImpl

captcha.engine.impl=com.liferay.portal.captcha.simplecaptcha.SimpleCaptchaImpl

captcha.engine.recaptcha.key.private=

captcha.engine.recaptcha.key.public=

captcha.engine.recaptcha.url.script=http://api.recaptcha.net/challenge?k=

captcha.engine.recaptcha.url.noscript=http://api.recaptcha.net/noscript?k=

captcha.engine.recaptcha.url.verify=http://api-verify.recaptcha.net/verify

##### SimpleCaptcha

Set the height and width for captcha images generated by SimpleCaptcha.

captcha.engine.simplecaptcha.height=50

captcha.engine.simplecaptcha.width=150

Input a list of comma delimited class names that implement
`nl.captcha.backgrounds.BackgroundProducer`{.western}. These classes
will be randomly used by SimpleCaptcha to generate a background for a
captcha image.

captcha.engine.simplecaptcha.background.producers=nl.captcha.backgrounds.FlatColorBackgroundProducer,nl.captcha.backgrounds.GradiatedBackgroundProducer,nl.captcha.backgrounds.SquigglesBackgroundProducer,nl.captcha.backgrounds.TransparentBackgroundProducer

Input a list of comma delimited class names that implement
`nl.captcha.gimpy.GimpyRenderer`{.western}. These classes will be
randomly used by SimpleCaptcha to gimp a captcha image.

captcha.engine.simplecaptcha.gimpy.renderers=nl.captcha.gimpy.RippleGimpyRenderer

captcha.engine.simplecaptcha.gimpy.renderers=nl.captcha.gimpy.BlockGimpyRenderer,nl.captcha.gimpy.DropShadowGimpyRenderer,nl.captcha.gimpy.FishEyeGimpyRenderer,nl.captcha.gimpy.RippleGimpyRenderer,nl.captcha.gimpy.ShearGimpyRenderer

Input a list of comma delimited class names that implement
`nl.captcha.noise.NoiseProducer`{.western}. These classes will be
randomly used by SimpleCaptcha to add noise to a captcha image.

captcha.engine.simplecaptcha.noise.producers=nl.captcha.noise.CurvedLineNoiseProducer

captcha.engine.simplecaptcha.noise.producers=nl.captcha.noise.CurvedLineNoiseProducer,nl.captcha.noise.StraightLineNoiseProducer

Input a list of comma delimited class names that implement
`nl.captcha.text.`{.western}`producer.TextProducer`{.western}. These
classes will be randomly used by SimpleCaptcha to generate text for a
captcha image.

captcha.engine.simplecaptcha.text.producers=com.liferay.portal.captcha.simplecaptcha.PinNumberTextProducer

captcha.engine.simplecaptcha.text.producers=com.liferay.portal.captcha.simplecaptcha.DictionaryWordTextProducer,com.liferay.portal.captcha.simplecaptcha.PinNumberTextProducer,nl.captcha.text.producer.DefaultTextProducer,nl.captcha.text.producer.FiveLetterFirstNameTextProducer

Input a list of comma delimited class names that implement
`nl.captcha.text.renderer.WordRenderer`{.western}. These classes will be
randomly used by SimpleCaptcha to render text for a captcha image.

captcha.engine.simplecaptcha.word.renderers=nl.captcha.text.renderer.DefaultWordRenderer

captcha.engine.simplecaptcha.word.renderers=nl.captcha.text.renderer.ColoredEdgesWordRenderer,nl.captcha.text.renderer.DefaultWordRenderer

#### Startup Events

Input a list of comma delimited class names that extend
`com.liferay.portal.struts.SimpleAction`{.western}. These classes will
run at the specified event.

The following is a global startup event that runs once when the portal
initializes.

global.startup.events=com.liferay.portal.events.GlobalStartupAction

The following is an application startup event that runs once for every
web site instance of the portal that initializes.

application.startup.events=com.liferay.portal.events.AppStartupAction

\#application.startup.events=com.liferay.portal.events.AppStartupAction,com.liferay.portal.events.SampleAppStartupAction

#### Shutdown Events

Input a list of comma delimited class names that extend
`com.liferay.portal.struts.SimpleAction`{.western}. These classes will
run at the specified event.

Global shutdown event that runs once when the portal shuts down.

global.shutdown.events=com.liferay.portal.events.GlobalShutdownAction

Application shutdown event that runs once for every web site instance of
the portal that shuts down.

application.shutdown.events=com.liferay.portal.events.AppShutdownAction

Programmatically kill the Java process on shutdown. This is a workaround
for a bug in Tomcat and Linux where the process hangs on forever.

See http://issues.liferay.com/browse/LEP-2048 for more information.

shutdown.programmatically.exit=false

#### Portal Events

Input a list of comma delimited class names that extend
`com.liferay.portal.struts.Action`{.western}. These classes will run
before or after the specified event.

**Servlet service event:** the pre-service events have an associated
error page and will forward to that page if an exception is thrown
during excecution of the events. The pre-service events process before
Struts processes the request.

*Examples:*

servlet.service.events.pre=com.liferay.portal.events.ServicePreAction

servlet.service.events.pre=com.liferay.portal.events.LogMemoryUsageAction,com.liferay.portal.events.LogThreadCountAction,com.liferay.portal.events.ServicePreAction

servlet.service.events.pre=com.liferay.portal.events.LogSessionIdAction,com.liferay.portal.events.ServicePreAction

servlet.service.events.pre=com.liferay.portal.events.ServicePreAction,com.liferay.portal.events.RandomLayoutAction

servlet.service.events.pre=com.liferay.portal.events.ServicePreAction,com.liferay.portal.events.RandomLookAndFeelAction

Use the following to define the error page.

servlet.service.events.pre.error.page=/common/error.jsp

The post-service events process after Struts processes the request.

servlet.service.events.post=com.liferay.portal.events.ServicePostAction

#### Login event

Define events that can occur pre-login and post-login.

login.events.pre=com.liferay.portal.events.LoginPreAction

login.events.post=com.liferay.portal.events.LoginPostAction,com.liferay.portal.events.DefaultLandingPageAction

#### Logout event

Similarly, events can be defined for the log out event.

logout.events.pre=com.liferay.portal.events.LogoutPreAction

*Example post events: *

logout.events.post=com.liferay.portal.events.LogoutPostAction

logout.events.post=com.liferay.portal.events.LogoutPostAction,com.liferay.portal.events.DefaultLogoutPageAction,com.liferay.portal.events.SiteMinderLogoutAction

\#logout.events.post=com.liferay.portal.events.LogoutPostAction,com.liferay.portal.events.GarbageCollectorAction

#### Default Landing Page

Set the default landing page path for logged in users relative to the
server path. This is the page users are automatically redirected to
after logging in. For example, if you want the default landing page to
be `http://localhost:8080/web/guest/login`{.western}, set this to
`/web/guest/login`{.western}. To activate this feature, set
`auth.forward.by.last.path`{.western} to true. To customize the
behavior, see
`com.liferay.portal.events.DefaultLandingPageAction`{.western} in the
`login.events.post`{.western} property above.

\#default.landing.page.path=/web/guest/login

#### Default Logout Page

Set the default logout page path for users relative to the server path.
This is the page users are automatically redirected to after logging
out. For example, if you want the default logout page to be
`http://localhost:8080/web/guest/logout`{.western}, set this to
`/web/guest/logout`{.western}. To activate this feature, set
`auth.forward.by.last.path`{.western} to true. To customize the
behavior, see
`com.liferay.portal.events.DefaultLogoutPageAction`{.western} in the
`logout.events.post`{.western} property above.

\#default.logout.page.path=/web/guest/logout

#### Default Guest Public Layouts

The Guest group must have at least one public page. The settings for the
initial public page are specified in the following properties.

If you need to add more than one page, set the property
`default.guest.public.layout.lar`{.western} to specify a LAR file
instead.

For even more complex behavior, override the
`addDefaultGuestPublicLayouts`{.western} method in
`com.liferay.portal.service.impl.GroupLocalServiceImpl`{.western}.

Set the name of the public layout.

default.guest.public.layout.name=Welcome

Set the layout template id of the public layout.

default.guest.public.layout.template.id=2\_columns\_ii

Set the portlet ids for the columns specified in the layout template.

default.guest.public.layout.column-1=58

default.guest.public.layout.column-2=47

default.guest.public.layout.column-3=

default.guest.public.layout.column-4=

Set the friendly url of the public layout.

default.guest.public.layout.friendly.url=/home

Set the regular theme id for the public layout.

\#default.guest.public.layout.regular.theme.id=classic

Set the regular color scheme id for the public layout.

\#default.guest.public.layout.regular.color.scheme.id=01

Set the wap theme id for the public layout.

\#default.guest.public.layout.wap.theme.id=mobile

Set the wap color scheme for the public layout.

\#default.guest.public.layout.wap.color.scheme.id=01

Specify a LAR file that can be used to create the guest public layouts.
If this property is set, the previous layout properties will be ignored.

\#default.guest.public.layouts.lar=${liferay.home}/deploy/default\_guest\_public.lar

#### Default User Private Layouts

If the properties *layout.user.private.layouts.enabled* and
*layout.user.private.layouts.auto.create* are both set to true, then
users will have private layouts and they will be automatically created.
The settings below are used for the creation of for the initial private
pages.

If you need to add more than one page, set the property
*default.user.private.layout.lar* to specify a LAR file instead.

For even more complex behavior, override the
*addDefaultUserPrivateLayouts* method in
*com.*liferay*.portal.events.ServicePreAction*.

Set the name of the private layout.

default.user.private.layout.name=Welcome

Set the layout template id of the private layout.

default.user.private.layout.template.id=2\_columns\_ii

Set the portlet ids for the columns specified in the layout template.

default.user.private.layout.column-1=71\_INSTANCE\_OY0d,82,23,61

default.user.private.layout.column-2=11,29,8,19

default.user.private.layout.column-3=

default.user.private.layout.column-4=

Set the friendly url of the private layout.

default.user.private.layout.friendly.url=/home

Set the regular theme id for the private layout.

\#default.user.private.layout.regular.theme.id=classic

Set the regular color scheme id for the private layout.

\#default.user.private.layout.regular.color.scheme.id=01

Set the wap theme id for the private layout.

\#default.user.private.layout.wap.theme.id=mobile

Set the wap color scheme for the private layout.

\#default.user.private.layout.wap.color.scheme.id=01

Specify a LAR file that can be used to create the user private layouts.
If this property is set, the previous layout properties will be ignored.

\#default.user.private.layouts.lar= \\

${liferay.home}/deploy/default\_user\_private.lar

#### Default User Public Layouts

If the properties *layout.user.public.layouts.enabled* and
*layout.user.public.layouts.auto.create* are both set to true, then
users will have public layouts and they will be automatically created.
The settings below are used for the creation of for the initial public
pages.

If you need to add more than one page, set the property
*default.user.public.layout.lar* to specify a LAR file instead.

For even more complex behavior, override the
*addDefaultUserPublicLayouts* method in
*com.liferay.portal.events.ServicePreAction*.

Set the name of the public layout.

default.user.public.layout.name=Welcome

Set the layout template id of the public layout.

default.user.public.layout.template.id=2\_columns\_ii

Set the portlet ids for the columns specified in the layout template.

default.user.public.layout.column-1=82,23

default.user.public.layout.column-2=8,19

default.user.public.layout.column-3=

default.user.public.layout.column-4=

Set the friendly url of the public layout.

default.user.public.layout.friendly.url=/home

Set the regular theme id for the public layout.

\#default.user.public.layout.regular.theme.id=classic

Set the regular color scheme id for the public layout.

\#default.user.public.layout.regular.color.scheme.id=01

Set the wap theme id for the public layout.

\#default.user.public.layout.wap.theme.id=mobile

Set the wap color scheme for the public layout.

\#default.user.public.layout.wap.color.scheme.id=01

Specify a LAR file that can be used to create the user public layouts.
If this property is set, the previous layout properties will be ignored.

\#default.user.public.layouts.lar=${liferay.home}/deploy/default\_user\_public.lar

#### Sanitizer

Set the name of a class that implements
`com.liferay.portal.kernel.sanitizer.Sanitizer`{.western}. This class is
used to sanitize content.

sanitizer.impl=com.liferay.portal.sanitizer.DummySanitizerImpl

#### Social Equity

Set the interval on which the `CheckEquityLogMessageListener`{.western}
will run. The value is set in one minute increments.

social.equity.equity.log.check.interval=1440

Set this to true to enable social equity logs.

social.equity.equity.log.enabled=true

\
\

#### Vaadin

Specify the location of the portal wide Vaadin themes and widget set
(client side JavaScript).

vaadin.resources.path=/html

Specify the base Vaadin theme to load automatically for all Vaadin
portlets. A portlet can include an additional theme that is loaded after
the shared theme.

vaadin.theme=reindeer

Specify the shared widget set (client side JavaScript) that is used by
all Vaadin portlets running in the portal.

vaadin.widgetset=com.vaadin.portal.gwt.PortalDefaultWidgetSet

#### Default Admin

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

#### Layouts

Set the list of layout types. The display text of each of the layout
types is set in *content/Language.properties* and prefixed with
*layout.types*. You can create new layout types and specify custom
settings for each layout type. End users input dynamic values as
designed in the edit page. End users see the layout as designed in the
view page. The generated URL can reference properties set in the edit
page. Parentable layouts can contain child layouts. You can also specify
a comma delimited list of configuration actions that will be called for
your layout when it is updated or deleted.

layout.types=portlet,panel,embedded,article,url,link\_to\_layout

Set whether or not private layouts are enabled. Set whether or not
private layouts are modifiable. Set whether or not private layouts
should be auto created if a user has no private layouts. If private
layouts are not enabled, the other two properties are assumed to be
false.

layout.user.private.layouts.enabled=true

layout.user.private.layouts.modifiable=true

layout.user.private.layouts.auto.create=true

Set whether or not public layouts are enabled. Set whether or not public
layouts are modifiable. Set whether or not public layouts should be auto
created if a user has no public layouts. If public layouts are not
enabled, the other two properties are assumed to be false.

layout.user.public.layouts.enabled=true

layout.user.public.layouts.modifiable=true

layout.user.public.layouts.auto.create=true

#### Default Settings Layouts

These settings allow you to define several attributes on layouts. You
can also specify which JSPs are responsible for editing and viewing
layouts. You will likely never need to change these settings.

layout.edit.page=/portal/layout/edit/portlet.jsp

layout.view.page=/portal/layout/view/portlet.jsp

layout.url=${liferay:mainPath}/portal/layout?p\_l\_id=${liferay:plid}

layout.url.friendliable=true

layout.parentable=true

layout.sitemapable=true

layout.first.pageable=true

layout.configuration.action.update=

layout.configuration.action.delete=

Settings for portlet layouts are inherited from the default settings.

layout.edit.page[portlet]=/portal/layout/edit/portlet.jsp

layout.view.page[portlet]=/portal/layout/view/portlet.jsp

layout.url[portlet]=${liferay:mainPath}/portal/layout?p\_l\_id=${liferay:plid}

layout.url.friendliable[portlet]=true

layout.parentable[portlet]=true

layout.configuration.action.update[portlet]=

layout.configuration.action.delete[portlet]=

Settings for panel layouts.

layout.edit.page[panel]=/portal/layout/edit/panel.jsp

layout.view.page[panel]=/portal/layout/view/panel.jsp

layout.url[panel]=${liferay:mainPath}/portal/layout?p\_l\_id=${liferay:plid}

layout.url.friendliable[panel]=true

layout.parentable[panel]=true

layout.first.pageable[panel]=true

Settings for control\_panel layouts.

layout.edit.page[control\_panel]=/portal/layout/edit/control\_panel.jsp

layout.view.page[control\_panel]=/portal/layout/view/control\_panel.jsp

layout.url[control\_panel]=${liferay:mainPath}/portal/layout?p\_l\_id=${liferay:plid}

layout.url.friendliable[control\_panel]=true

layout.parentable[control\_panel]=true

layout.first.pageable[control\_panel]=true

Settings for embedded layouts.

layout.edit.page[embedded]=/portal/layout/edit/embedded.jsp

layout.view.page[embedded]=/portal/layout/view/embedded.jsp

layout.url[embedded]=${liferay:mainPath}/portal/layout?p\_l\_id=${liferay:plid}

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

layout.url[article]=${liferay:mainPath}/portal/layout?p\_l\_id=${liferay:plid}

layout.parentable[article]=false

layout.sitemapable[article]=true

layout.first.pageable[article]=true

layout.configuration.action.update[article]=com.liferay.portal.model.LayoutTypeArticleConfigurationUpdateAction

layout.configuration.action.delete[article]=com.liferay.portal.model.LayoutTypeArticleConfigurationDeleteAction

Settings for URL layouts.

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

layout.edit.page[link\_to\_layout]=/portal/layout/edit/link\_to\_layout.jsp

layout.view.page[link\_to\_layout]=

layout.url[link\_to\_layout]=${liferay:mainPath}/portal/layout?p\_l\_id=${linkToPlid}

layout.url.friendliable[link\_to\_layout]=true

layout.parentable[link\_to\_layout]=true

layout.sitemapable[link\_to\_layout]=false

layout.first.pageable[link\_to\_layout]=false

layout.configuration.action.update[link\_to\_layout]=

layout.configuration.action.delete[link\_to\_layout]=

Specify static portlets that cannot be moved and will always appear on
every layout. Static portlets will take precedence over portlets that
may have been dynamically configured for the layout.

For example, if you want the Hello World portlet to always appear at the
start of the iteration of the first column for user layouts, set the
property *layout.static.portlets.start.column-1[user]* to *47*. If you
want the Hello World portlet to always appear at the end of the second
column for user layouts, set the property
*layout.static.portlets.end.column-2[user]* to *47*. You can input a
list of comma delimited portlet ids to specify more than one portlet. If
the portlet is instanceable, add the suffix *\_INSTANCE\_abcd* to the
portlet id, where *abcd* is any random alphanumeric string.

The static portlets are fetched based on the properties controlled by
custom filters using EasyConf. By default, the available filters are
user, community, and organization.

layout.static.portlets.start.column-1[user]=3,6

layout.static.portlets.end.column-1[user]=14

layout.static.portlets.start.column-2[user]=71\_INSTANCE\_abcd,7

layout.static.portlets.end.column-2[user]=34,70

layout.static.portlets.start.column-3[user]=

layout.static.portlets.end.column-3[user]=

It is also possible to set static portlets based on the layout's
friendly URL.

layout.static.portlets.start.column-1[user][/home]=3,6

layout.static.portlets.end.column-2[community][/home]=14

Set the static portlets for community layouts.

layout.static.portlets.start.column-1[community]=

layout.static.portlets.end.column-1[community]=

layout.static.portlets.start.column-2[community]=

layout.static.portlets.end.column-2[community]=

layout.static.portlets.start.column-3[community]=

layout.static.portlets.end.column-3[community]=

Set the static portlets for organization layouts.

layout.static.portlets.start.column-1[organization]=

layout.static.portlets.end.column-1[organization]=

layout.static.portlets.start.column-2[organization]=

layout.static.portlets.end.column-2[organization]=

layout.static.portlets.start.column-3[organization]=

layout.static.portlets.end.column-3[organization]=

Set the static portlets that will appear for every layout. See
*/html/portal/layout/view/portlet.jsp* in the Liferay source code for
the logic of when these portlets will be shown. For example, these
portlets will only show for layouts that can contain portlets and are
not in a pop up state.

layout.static.portlets.all=1\_WAR\_chatportlet

Set the private group, private user, and public servlet mapping for
*com.liferay.portal.*servlet*.FriendlyURLServlet*. This value must match
the servlet mapping set in web.xml.

For example, if the private group pages are mapped to */group* and the
group's friendly URL is set to */guest* and the layout's friendly URL is
set to */company/community*, then the friendly URL for the page will be
*http://www.liferay.com/*group*/guest/company/community*. Private group
pages map to a community's private pages and are only available to
authenticated users with the proper permissions.

For example, if the public pages are mapped to */web* and the group or
user's friendly URL is set to */guest* and the layout's friendly URL is
set to */company/community*, then the friendly URL for the page will be
*http://www.liferay.com/web/guest/company/community*. Public pages are
available to unauthenticated users.

The friendly URLs for users, groups, and layouts can be set during
runtime.

layout.friendly.url.private.group.servlet.mapping=/group

layout.friendly.url.private.user.servlet.mapping=/user

layout.friendly.url.public.servlet.mapping=/web

Redirect to this resource if the user requested a friendly URL that does
not exist. Leave it blank to display nothing.

**Note:** For backward compatibility, this overrides the property
*layout.show.http.status* for the 404 status code.

layout.friendly.url.page.not.found=/html/portal/404.html

Set the reserved keywords that cannot be used in a friendly URL.

layout.friendly.url.keywords=c,group,web,image,wsrp,page,public,private,rss,tags

Set the following to true if layouts should remember (across requests)
that a window state was set to maximized.

layout.remember.request.window.state.maximized=false

Set the following to true if guest users should see the maximize window
icon.

layout.guest.show.max.icon=false

Set the following to true if guest users should see the minimize window
icon.

layout.guest.show.min.icon=false

Set the following to true if users are shown that they do not have
access to a portlet. The portlet init parameter
*show-portlet-access-denied* will override this setting.

layout.show.portlet.access.denied=true

Set the following to true if users are shown that a portlet is inactive.
The portlet init parameter *show-portlet-inactive* will override this
setting.

layout.show.portlet.inactive=true

Set the following to true if the portal should show HTTP status codes
like 404 if the requested page is not found.

layout.show.http.status=true

Set the default layout template id used when creating layouts.

layout.default.template.id=2\_columns\_ii

Set the following to false to disable parallel rendering. You can also
disable it on a per request basis by setting the attribute key
*com.liferay.portal.util.WebKeys.PORTLET\_PARALLEL\_RENDER* to the
*Boolean.FALSE* in a pre service event or by setting the URL parameter
*p\_p\_parallel* to *0*.

layout.parallel.render.enable=true

Set the name of a class that implements
*com.liferay.portal.util.LayoutClone*. This class is used to remember
maximized and minimized states on shared pages. The default
implementation persists the state in the browser session.

layout.clone.impl=com.liferay.portal.util.SessionLayoutClone

Set the following to true to cache the content of layout templates. This
is recommended because it improves performance for production servers.
Setting it to false is useful during development if you need to make a
lot of changes.

layout.template.cache.enabled=true

Set the default value for the *p\_l\_reset* parameter. If set to true,
then render parameters are cleared when different pages are hit. This is
not the behavior promoted by the portlet specification, but is the one
that most end users seem to prefer.

layout.default.p\_l\_reset=true

#### Portlet URL

Set the following to true if calling `setParameter`{.western} on a
portlet URL appends the parameter value versus replacing it. There is
some disagreement in the interpretation of the JSR 168 spec among
portlet developers over this specific behavior. Liferay Portal
successfully passes the portlet TCK tests whether this value is set to
true or false.

See http://issues.liferay.com/browse/LEP-426 for more information.

portlet.url.append.parameters=false

Set the following to true to allow portlet URLs to generate with an
anchor tag.

portlet.url.anchor.enable=false

JSR 286 specifies that portlet URLs are escaped by default. Set this to
false to provide for better backwards compatibility.

If this is set to true, but a specific portlet application requires that
its portlet URLs not be escaped by default, then modify portlet.xml and
set the container runtime option *javax.portlet.escape*Xml to false.

portlet.url.escape.xml=false

#### Preferences

Set the following to true to validate portlet preferences on startup.

preference.validate.on.startup=false

#### Struts

Input the custom Struts request processor that will be used by Struts
based portlets. The custom class must extend
*com.liferay.portal.struts.PortletRequestProcessor* and have the same
constructor.

struts.portlet.request.processor=com.liferay.portal.struts.PortletRequestProcessor

#### Redirect

Set this property to `ip`{.western} or `domain`{.western} for the
redirect security method. If set to `domain`{.western}, the portal will
only redirect users to domains listed in the property
`redirect.url.domain.allowed`{.western}. If set to `ip`{.western}, the
portal will only redirect to domains whose IP address resolve to an IP
address listed in the property `redirect.url.ip.allowed`{.western}.

redirect.url.security.mode=domain

redirect.url.security.mode=ip

Input a list of comma delimited domains which the portal is allowed to
redirect to. Input a blank list to allow any domain.

redirect.url.domains.allowed=

Input a list of comma delimited IPs which the portal is allowed to
redirect to. Input a blank list to allow any IP. `SERVER_IP`{.western}
will be replaced with the IP of the host server.

redirect.url.ips.allowed=127.0.0.1,SERVER\_IP

#### Images

Set the location of the default spacer image that is used for missing
images. This image must be available in the class path.

image.default.spacer=com/liferay/portal/dependencies/spacer.gif

Set the location of the default company logo image that is used for
missing company logo images. This image must be available in the class
path.

image.default.company.logo=com/liferay/portal/dependencies/company\_logo.png

Set the location of the default organization logo image that is used for
missing organization logo images. This image must be available in the
class path.

image.default.organization.logo=com/liferay/portal/dependencies/organization\_logo.png

Set the locations of the default user portrait images that are used for
missing user portrait images. This image must be available in the class
path.

image.default.user.female.portrait=com/liferay/portal/dependencies/user\_female\_portrait.png

image.default.user.male.portrait=com/liferay/portal/dependencies/user\_male\_portrait.png

Set the name of a class that implements
`com.liferay.portal.image.Hook`{.western}. The portal will use this
persist images.

Available hooks are:

-   `com.liferay.portal.image.DatabaseHook`{.western}

-   `com.liferay.portal.image.DLHook`{.western}

-   `com.liferay.portal.image.FileSystemHook`{.western}

image.hook.impl=com.liferay.portal.image.DatabaseHook

\#image.hook.impl=com.liferay.portal.image.DLHook

\#image.hook.impl=com.liferay.portal.image.FileSystemHook

#### FileSystemHook

image.hook.file.system.root.dir=${liferay.home}/data/images

#### Editors

You can configure individual JSP pages to use a specific implementation
of the available WYSIWYG editors:
`liferay, fckeditor, simple, tinymce`{.western}, or
`tinymcesimple`{.western}.

editor.wysiwyg.default=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.blogs.edit\_entry.jsp=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.calendar.edit\_configuration.jsp=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.enterprise\_admin.view.jsp=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.invitation.edit\_configuration.jsp=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.journal.edit\_article\_content.jsp=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.journal.edit\_article\_content\_xsd\_el.jsp=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.journal.edit\_configuration.jsp=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.login.configuration.jsp=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.mail.edit.jsp=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.mail.edit\_message.jsp=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.message\_boards.edit\_configuration.jsp=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.shopping.edit\_configuration.jsp=fckeditor

editor.wysiwyg.portal-web.docroot.html.portlet.wiki.edit\_html.jsp=fckeditor

#### Fields

Set the following fields to false so users cannot see them. Some company
policies require gender and birthday information to always be hidden.

field.enable.com.liferay.portal.model.Contact.male=true

field.enable.com.liferay.portal.model.Contact.birthday=true

field.enable.com.liferay.portal.model.Organization.status=false

Input a list of comma delimited user types who can edit their own
fields.

Valid types are `administrator`{.western}, `user-mx`{.western}, and
`user-without-mx`{.western}.

Set a value of `administrator`{.western} if an administrator can edit
the specified field. An administrator is anyone who has the
Administrator role.

Set a value of `user-mx`{.western} if a user who has an email address
that matches the company mail suffix can edit the specified field.

Set a value of `user-without-mx`{.western} if a user who does not have
an email address that matches the company mail suffix can edit the
specified field.

Set all three values if all users can edit the specified field. Set a
combination of the three values if only a combination of the users can
edit the specified field.

field.editable.com.liferay.portal.model.User.screenName=administrator,user-with-mx,user-without-mx

field.editable.com.liferay.portal.model.User.emailAddress=administrator,user-with-mx,user-without-mx

#### Mime Types

Input a list of comma delimited mime types that are not available by
default from `javax.activation.MimetypesFileTypeMap`{.western}.

mime.types=\\

application/msword doc,\\

application/pdf pdf,\\

application/vnd.ms-excel xls,\\

application/vnd.ms-powerpoint ppt,\\

application/x-ms-wmp wmv,\\

application/x-shockwave-flash swf flv

Input a list of comma delimited extensions for which the content
disposition header has to be set to *inline*.

mime.types.content.disposition.inline=flv,pdf,swf,wmv

#### Amazon

Enter an Amazon access key ID and an Amazon associate tag. This is made
available only for personal use. Please see the Amazon's license at
http://www.amazon.com for more information.

\#amazon.access.key.id=

\#amazon.associate.tag=

#### Browser Launcher

Enter a URL to automatically launch a browser to that URL when the
portal has fully initialized. Enter a blank URL to disable this feature.

browser.launcher.url=http://localhost:8080

#### Control Panel

Set the name of the layout.

control.panel.layout.name=Control Panel

Set the friendly URL of the layout.

control.panel.layout.friendly.url=/manage

Set the theme of the layout.

control.panel.layout.regular.theme.id=controlpanel

Set the maximum number of communities that will be shown in the
navigation menus. A large value might cause performance problems if the
number of communities that the user can administer is very large.

control.panel.navigation.max.communities=50

Set the maximum number of organizations that will be shown in the
navigation menus. A large value might cause performance problems if the
number of organizations that the user can administer is very large.

control.panel.navigation.max.organizations=50

Set the name of a class that implements
`com.liferay.portlet.ControlPanelEntry`{.western}. This class denotes
the default value of of the element
`control-panel-entry-class`{.western} in `liferay-portlet.xml`{.western}
and is called by the Control Panel to decide whether the portlet should
be shown to a specific user in a specific context.

control.panel.default.entry.class=com.liferay.portlet.DefaultControlPanelEntry

#### Instant Messenger

Set the AIM login and password which the system will use to communicate
with users.

aim.login=

aim.password=

Due to a bug in JOscarLib 0.3b1, you must set the full path to the
`ICQ jar`{.western}.

See the following posts:

[http://sourceforge.net/forum/message.php?msg\_id=1972697](http://sourceforge.net/forum/message.php?msg_id=1972697)

[http://sourceforge.net/forum/message.php?msg\_id=1990487](http://sourceforge.net/forum/message.php?msg_id=1990487)

icq.jar=C:/Java/orion-2.0.7/lib/icq.jar

Set the ICQ login and password which the system will use to communicate
with users.

icq.login=

icq.password=

Set the MSN login and password which the system will use to communicate
with users.

msn.login=

msn.password=

Set the YM login and password which the system will use to communicate
with users.

ym.login=

ym.password=

#### Lucene Search

Set the limit for results used when performing index searches that are
subsequently filtered by permissions.

index.filter.search.limit=5000

Set the following to true if you want to avoid any writes to the index.
This is useful in some clustering environments where there is a shared
index and only one node of the cluster updates it.

index.read.only=false

Set the following to true if you want to index your entire library of
files on startup.

index.on.startup=false

Set this to true to add a delay before indexing on startup. A delay may
be necessary if a lot of plugins need to be loaded and reindexed. This
property is only valid if *index.on.startup* is set to true.

index.on.startup.delay=60

Set this to true if you want to index your entire library of files after
an upgrade. Only set this property to false if you are running a small
upgrade and you do not need to reindex everything.

index.on.upgrade=true

Set the following to true if you want the indexing on startup to be
executed on a separate thread to speed up execution.

index.with.thread=true

Designate whether Lucene stores indexes in a database via JDBC, file
system, or in RAM.

*Examples:*

lucene.store.type=jdbc

lucene.store.type=file

lucene.store.type=ram

Lucene's storage of indexes via JDBC has a bug where temp files are not
removed. This can eat up disk space over time. Set the following
property to true to automatically clean up the temporary files once a
day. See LEP-2180.

lucene.store.jdbc.auto.clean.up=true

Set the JDBC dialect that Lucene uses to store indexes in the database.
This is only referenced if Lucene stores indexes in the database.
Liferay will attempt to load the proper dialect based on the URL of the
JDBC connection. For example, the property
*lucene.store.jdbc.dialect.mysql* is read for the JDBC connection URL
*jdbc:mysql://localhost/lportal*.

lucene.store.jdbc.dialect.db2=org.apache.lucene.store.jdbc.dialect.DB2Dialect

lucene.store.jdbc.dialect.derby=org.apache.lucene.store.jdbc.dialect.DerbyDialect

lucene.store.jdbc.dialect.hsqldb=org.apache.lucene.store.jdbc.dialect.HSQLDialect

lucene.store.jdbc.dialect.jtds=org.apache.lucene.store.jdbc.dialect.SQLServerDialect

lucene.store.jdbc.dialect.microsoft=org.apache.lucene.store.jdbc.dialect.SQLServerDialect

lucene.store.jdbc.dialect.mysql=org.apache.lucene.store.jdbc.dialect.MySQLDialect

\#lucene.store.jdbc.dialect.mysql=org.apache.lucene.store.jdbc.dialect.MySQLInnoDBDialect

\#lucene.store.jdbc.dialect.mysql=org.apache.lucene.store.jdbc.dialect.MySQLMyISAMDialect

lucene.store.jdbc.dialect.oracle=org.apache.lucene.store.jdbc.dialect.OracleDialect

lucene.store.jdbc.dialect.postgresql=org.apache.lucene.store.jdbc.dialect.PostgreSQLDialect

Set the directory where Lucene indexes are stored. This is only
referenced if Lucene stores indexes in the file system.

lucene.dir=${liferay.home}/lucene/

Input a class name that extends
*com.liferay.portal.search.lucene.LuceneFileExtractor*. This class is
called by Lucene to extract text from complex files so that they can be
properly indexed.

lucene.file.extractor=com.liferay.portal.search.lucene.LuceneFileExtractor

The file extractor can sometimes return text that is not valid for
Lucene. This property expects a regular expression. Any character that
does not match the regular expression will be replaced with a blank
space. Set an empty regular expression to disable this feature.

*Examples: *

lucene.file.extractor.regexp.strip=

lucene.file.extractor.regexp.strip=[\\\\d\\\\w]

Set the default analyzer used for indexing and retrieval.

*Examples:*

lucene.analyzer=org.apache.lucene.analysis.br.BrazilianAnalyzer

lucene.analyzer=org.apache.lucene.analysis.cn.ChineseAnalyzer

lucene.analyzer=org.apache.lucene.analysis.cjk.CJKAnalyzer

lucene.analyzer=org.apache.lucene.analysis.cz.CzechAnalyzer

lucene.analyzer=org.apache.lucene.analysis.nl.DutchAnalyzer

lucene.analyzer=org.apache.lucene.analysis.fr.FrenchAnalyzer

lucene.analyzer=org.apache.lucene.analysis.de.GermanAnalyzer

lucene.analyzer=org.apache.lucene.analysis.KeywordAnalyzer

lucene.analyzer=org.apache.lucene.index.memory.PatternAnalyzer

lucene.analyzer=org.apache.lucene.analysis.PerFieldAnalyzerWrapper

lucene.analyzer=org.apache.lucene.analysis.ru.RussianAnalyzer

lucene.analyzer=org.apache.lucene.analysis.SimpleAnalyzer

lucene.analyzer=org.apache.lucene.analysis.snowball.SnowballAnalyzer

lucene.analyzer=org.apache.lucene.analysis.standard.StandardAnalyzer

lucene.analyzer=org.apache.lucene.analysis.StopAnalyzer

lucene.analyzer=org.apache.lucene.analysis.WhitespaceAnalyzer

Set how often index updates will be committed. Set the batch size to
configure how many consecutive updates will trigger a commit. If the
value is 0, then the index will be committed on every update. Set the
time interval in milliseconds to configure how often to commit the
index. The time interval is not read unless the batch size is greater
than 0 because the time interval works in conjunction with the batch
size to guarantee that the index is committed after a specified time
interval. Set the time interval to 0 to disable committing the index by
a time interval.

lucene.commit.batch.size=0

lucene.commit.time.interval=0

Set Lucene's buffer size in megabytes. Higher numbers mean indexing goes
faster but uses more memory.

lucene.buffer.size=16

Set Lucene's merge factor. Higher numbers mean indexing goes faster but
uses more memory. The default value from Lucene is 10. This should never
be set to a number lower than 2.

lucene.merge.factor=10

Set how often to run Lucene's optimize method. Optimization speeds up
searching but slows down writing. Set this property to 0 to always
optimize. Set this property to an integer greater than 0 to optimize
every X writes.

lucene.optimize.interval=1

Set this to true if you want to index your entire library of files after
an upgrade. Only set this property to false if you are running a small
upgrade and you do not need to reindex everything.

index.on.upgrade=true

Set the interval on which the lucene automatic clean up is set run. The
value is set in one minute increments.

lucene.store.jdbc.auto.clean.up.interval=1440

Set this to true if you want the portal to replicate an index write
across all members of the cluster. This is useful in some clustered
environments where you wish each server instance to have its own copy of
the Lucene search index. This is only relevant when using the default
Lucene indexing engine.

lucene.replicate.write=false

#### SourceForge

source.forge.mirrors=\\

http://downloads.sourceforge.net,\\ \# Redirect

http://internap.dl.sourceforge.net,\\ \# San Jose, CA

http://superb-east.dl.sourceforge.net,\\ \# McLean, Virginia

http://superb-west.dl.sourceforge.net,\\ \# Seattle, Washington

http://easynews.dl.sourceforge.net,\\ \# Phoenix, AZ

http://kent.dl.sourceforge.net,\\ \# Kent, UK

http://ufpr.dl.sourceforge.net,\\ \# Curitiba, Brazil

http://belnet.dl.sourceforge.net,\\ \# Brussels, Belgium

http://switch.dl.sourceforge.net,\\ \# Lausanne, Switzerland

http://mesh.dl.sourceforge.net,\\ \# Duesseldorf, Germany

http://ovh.dl.sourceforge.net,\\ \# Paris, France

http://dfn.dl.sourceforge.net,\\ \# Berlin, Germany

http://heanet.dl.sourceforge.net,\\ \# Dublin, Ireland

http://garr.dl.sourceforge.net,\\ \# Bologna, Italy

http://surfnet.dl.sourceforge.net \# Amsterdam, The Netherlands

http://jaist.dl.sourceforge.net,\\ \# Ishikawa, Japan

http://nchc.dl.sourceforge.net,\\ \# Tainan, Taiwan

http://optusnet.dl.sourceforge.net \# Sydney, Australia

#### Value Object

You can add a listener for a specific class by setting the property
*value.object.listener* with a list of comma delimited class names that
implement *com.liferay.portal.model.ModelListener*. These classes are
pooled and reused and must be thread safe.

value.object.listener.com.liferay.portal.model.Contact=com.liferay.portal.model.ContactListener

value.object.listener.com.liferay.portal.model.Layout=com.liferay.portal.model.LayoutListener

value.object.listener.com.liferay.portal.model.LayoutSet=com.liferay.portal.model.LayoutSetListener

value.object.listener.com.liferay.portal.model.PortletPreferences=com.liferay.portal.model.PortletPreferencesListener

value.object.listener.com.liferay.portal.model.User=com.liferay.portal.model.UserListener

value.object.listener.com.liferay.portlet.journal.model.JournalArticle=com.liferay.portlet.journal.model.JournalArticleListener

value.object.listener.com.liferay.portlet.journal.model.JournalTemplate=com.liferay.portlet.journal.model.JournalTemplateListener

The finder level cache stores the many paths that return a value object
and the many paths that return a list of value objects. The finder level
cache only caches primary keys and is further helped by the entity level
cache that caches the value object to the primary key.

The Hibernate level cache is provided by the
`hibernate.cache.provider_class`{.western} property. Set this to true to
enable entity level caching.

value.object.entity.cache.enabled=true

Set this to true to configure entity level caching to block. See the
property `ehcache.blocking.cache.allowed`{.western} for more
information.

value.object.entity.blocking.cache=true

The entity level cache uses a thread local map to store the most
frequently accessed items to lower the number of queries to the
underlying cache. Set the maximum map size to 0 to disable the thread
level cache.

value.object.entity.thread.local.cache.max.size=100

Entity level caching for a specific type of value object can be
configured by using a property name that includes the value object's
class name.

value.object.entity.cache.enabled.com.liferay.portal.model.Layout=true

value.object.entity.cache.enabled.com.liferay.portal.model.User=true

value.object.entity.cache.enabled.com.liferay.portlet.social.model.SocialEquityAssetEntry=false

value.object.entity.cache.enabled.com.liferay.portlet.social.model.SocialEquityLog=false

value.object.entity.cache.enabled.com.liferay.portlet.social.model.SocialEquityUser=false

Set this to true to enable finder level caching.

value.object.finder.cache.enabled=true

Set this to true to configure finder level caching to block. See the
property `ehcache.blocking.cache.allowed`{.western} for more
information.

value.object.finder.blocking.cache=true

The finder level cache uses a thread local map to store the most
frequently accessed items to lower the number of queries to the
underlying cache. Set the maximum map size to 0 to disable the thread
level cache.

value.object.finder.thread.local.cache.max.size=100

Finder level caching for a specific type of value object can be
configured by using a property name that includes the value object's
class name. Mapping tables can also be specified to configure the
caching of value object relationships.

value.object.finder.cache.enabled.com.liferay.portal.model.Layout=true

value.object.finder.cache.enabled.com.liferay.portal.model.User=true

value.object.finder.cache.enabled.com.liferay.portlet.social.model.SocialEquityAssetEntry=false

value.object.finder.cache.enabled.com.liferay.portlet.social.model.SocialEquityLog=false

value.object.finder.cache.enabled.com.liferay.portlet.social.model.SocialEquityUser=false

value.object.finder.cache.enabled.Users\_Roles=true

#### Communication Link

Set the JGroups properties used by the portal to communicate with other
instances of the portal. This is only needed if the portal is running in
a clustered environment. The JGroups settings provide a mechanism for
the portal to broadcast messages to the other instances of the portal.
The specified multi-cast address should be unique for internal portal
messaging only. You will still need to set the Hibernate and Ehcache
settings for database clustering.

comm.link.properties=UDP(bind\_addr=127.0.0.1;mcast\_addr=231.12.21.102;mcast\_port=45566;ip\_ttl=32;mcast\_send\_buf\_size=150000;mcast\_recv\_buf\_size=80000):PING(timeout=2000;num\_initial\_members=3):MERGE2(min\_interval=5000;max\_interval=10000):FD\_SOCK:VERIFY\_SUSPECT(timeout=1500):pbcast.NAKACK(gc\_lag=50;retransmit\_timeout=300,600,1200,2400,4800;max\_xmit\_size=8192):UNICAST(timeout=300,600,1200,2400):pbcast.STABLE(desired\_avg\_gossip=20000):FRAG(frag\_size=8096;down\_thread=false;up\_thread=false):pbcast.GMS(join\_timeout=5000;join\_retry\_timeout=2000;shun=false;print\_local\_addr=true)

#### Cluster Link

Set this to true to enable the cluster link. This is required if you
want to cluster indexing and other features that depend the cluster
link.

cluster.link.enabled=false

Set the JGroups properties for each channel, we support up to 10
transport channels and 1 single required control channel. Use as few
transport channels as possible for best performance. By default, only
one UDP control channel and one UDP transport channel are enabled.
Channels can be configured by XML files that are located in the class
path or by inline properties.

cluster.link.channel.properties.control=UDP(bind\_addr=localhost;mcast\_addr=${multicast.group.address["cluster-link-control"]};mcast\_port=${multicast.group.port["cluster-link-control"]};ip\_ttl=8;mcast\_send\_buf\_size=150000;mcast\_recv\_buf\_size=80000):PING(timeout=2000;num\_initial\_members=3):MERGE2(min\_interval=5000;max\_interval=10000):FD\_SOCK:VERIFY\_SUSPECT(timeout=1500):pbcast.NAKACK(gc\_lag=50;retransmit\_timeout=300,600,1200,2400,4800;max\_xmit\_size=8192):UNICAST(timeout=300,600,1200,2400):pbcast.STABLE(desired\_avg\_gossip=20000):FRAG(frag\_size=8096;down\_thread=false;up\_thread=false):pbcast.GMS(join\_timeout=5000;join\_retry\_timeout=2000;shun=false;print\_local\_addr=true)

cluster.link.channel.properties.transport.0=UDP(bind\_addr=localhost;mcast\_addr=${multicast.group.address["cluster-link-udp"]};mcast\_port=${multicast.group.port["cluster-link-udp"]};ip\_ttl=8;mcast\_send\_buf\_size=150000;mcast\_recv\_buf\_size=80000):PING(timeout=2000;num\_initial\_members=3):MERGE2(min\_interval=5000;max\_interval=10000):FD\_SOCK:VERIFY\_SUSPECT(timeout=1500):pbcast.NAKACK(gc\_lag=50;retransmit\_timeout=300,600,1200,2400,4800;max\_xmit\_size=8192):UNICAST(timeout=300,600,1200,2400):pbcast.STABLE(desired\_avg\_gossip=20000):FRAG(frag\_size=8096;down\_thread=false;up\_thread=false):pbcast.GMS(join\_timeout=5000;join\_retry\_timeout=2000;shun=false;print\_local\_addr=true)

\#cluster.link.channel.properties.transport.1=udp.xml

\#cluster.link.channel.properties.transport.2=mping.xml

Set JGroups' system properties. System properties have higher priority
than individual properties given to each channel. That means system
properties will override individual properties.

cluster.link.channel.system.properties=\\

\#

\# Common

\#

\\

jgroups.bind\_addr:localhost,\\

\#jgroups.bind\_interface:eth0,\\

\\

\#

\# Multicast

\#

\\

jgroups.mping.mcast\_addr:${multicast.group.address["cluster-link-mping"]},\\

jgroups.mping.mcast\_port:${multicast.group.port["cluster-link-mping"]},\\

jgroups.mping.ip\_ttl:8,\\

\\

\\

#### Cluster Executor

Set this to true to enable the cluster executor debugging. This will
attach a debugging listener which will log every cluster event it
receives.

cluster.executor.debug.enabled=false

#### Minifier

The strip filter will attempt to cache inline minified CSS and
JavaScript content. Set this property configure the maximum pieces of
cached content. Set this property to 0 to disable caching of inline
minified content.

minifier.inline.content.cache.size=10000

Input a list of comma delimited values that will cause the minified CSS
to not be cached if those values are contained in the content.

minifier.inline.content.cache.skip.css=

Input a list of comma delimited values that will cause the minified
JavaScript to not be cached if those values are contained in the
content.

minifier.inline.content.cache.skip.javascript=getSessionId,encryptedUserId

#### Monitoring

Configure the appropriate level for monitoring Liferay. Valid values
are: HIGH, LOW, MEDIUM, OFF.

monitoring.level.com.liferay.monitoring.Portal=HIGH

monitoring.level.com.liferay.monitoring.Portlet=HIGH

Set this to true to store data samples of the current request as a
thread local variable. This allows you to obtain each request's
statistics for further processing

monitoring.data.sample.thread.local=false

Set this to true to monitor portal requests.

monitoring.portal.request=false

Set this to true to monitor portlet action requests.

monitoring.portlet.action.request=false

Set this to true to monitor portlet event requests.

monitoring.portlet.event.request=false

Set this to true to monitor portlet render requests.

monitoring.portlet.render.request=false

Set this to true to monitor portlet resource requests.

monitoring.portlet.resource.request=false

Set this to true to show data samples at the bottom of each portal page.
In order for data to show, the property
`monitoring.data.sample.thread.local`{.western} must be set to true.

monitoring.show.per.request.data.sample=false

#### Multicast

Consolidate multicast address and port settings in one location for
easier maintenance. These settings must correlate to your physical
network configuration (i.e. firewall, switch, and other network hardware
matter) to ensure speedy and accurate communication across a cluster.

Each address and port combination represent a conversation that is made
between different nodes. If they are not unique or correctly set, there
will be a potential of unnecessary network traffic that may cause slower
updates or inaccurate updates.

See the property `cluster.link.channel.properties.control`{.western}.

multicast.group.address["cluster-link-control"]=233.0.0.1

multicast.group.port["cluster-link-control"]=23301

See the properties
`cluster.link.channel.properties.transport.0`{.western} and
`cluster.link.channel.system.properties`{.western}.

multicast.group.address["cluster-link-udp"]=233.0.0.2

multicast.group.port["cluster-link-udp"]=23302

See the property `cluster.link.channel.system.properties`{.western}.

multicast.group.address["cluster-link-mping"]=233.0.0.3

multicast.group.port["cluster-link-mping"]=23303

See the properties `net.sf.ehcache.configurationResourceName`{.western}
and
`net.sf.ehcache.configurationResourceName.peerProviderProperties`{.western}.

multicast.group.address["hibernate"]=233.0.0.4

multicast.group.port["hibernate"]=23304

See the properties `ehcache.multi.vm.config.location`{.western} and
`ehcache.multi.vm.config.location.peerProviderProperties`{.western}.

multicast.group.address["multi-vm"]=233.0.0.5

multicast.group.port["multi-vm"]=23305

#### Content Delivery Network

Set the hostname that will be used to serve static content via a CDN.
This property can be overridden dynamically at runtime by setting the
HTTP parameter *cdn\_host*.

cdn.host=

#### Counter

The counter operates with is own data source to prevent deadlocks. By
default, the data source created for the counter uses the same settings
as those used to create the data source used for the rest of the portal.
That happens by because the counter service will look up the properties
prefixed with `jdbc.default`{.western}. to create its data source. See
the JDBC properties prefixed with `jdbc.default`{.western}. for more
information.

Setting a different value for the counter JDBC prefix allows you to
better fine tune the counter data source with its own set of
configuration settings for high availability installations. Note that
these settings, though separate, are a copy of the default settings with
the newly overridden values.

counter.jdbc.prefix=jdbc.default.

Set the number of increments between database updates to the Counter
table. Set this value to a higher number for better performance.

counter.increment=100

Set the interval in minutes for the `ConnectionHearbeatJob`{.western}.
This will determine how often the database is polled for long running
connections and will prevent the database from disconnecting the socket
prematurely.

counter.connection.heartbeat.job.interval=60

#### Lock

Set the lock expiration time for each class.

*Example: 1 Day*

lock.expiration.time.com.liferay.portlet.documentlibrary.model.DLFileEntry=86400000

*Example: 20 Minutes*

lock.expiration.time.com.liferay.portlet.wiki.model.WikiPage=1200000

#### JBI

Connect to either Mule or ServiceMix as your ESB.

*Examples: *

jbi.workflow.url=http://localhost:8080/mule-web/workflow

jbi.workflow.url=http://localhost:8080/servicemix-web/workflow

#### JCR

Liferay includes Jackrabbit (http://jackrabbit.apache.org) by default as
its JSR-170 Java Content Repository.

jcr.initialize.on.startup=false

jcr.workspace.name=liferay

jcr.node.documentlibrary=documentlibrary

jcr.jackrabbit.repository.root=${liferay.home}/jackrabbit

jcr.jackrabbit.config.file.path=${jcr.jackrabbit.repository.root}/repository.xml

jcr.jackrabbit.repository.home=${jcr.jackrabbit.repository.root}/home

jcr.jackrabbit.credentials.username=none

jcr.jackrabbit.credentials.password=none

#### Live Users

Set this to true to enable tracking via Live Users.

live.users.enabled=false

#### Lock

Set the lock expiration time for each class.

*1 day:*

lock.expiration.time.com.liferay.portlet.documentlibrary.model.DLFolder=86400000

lock.expiration.time.com.liferay.portlet.documentlibrary.model.DLFileEntry=86400000

*20 minutes:*

lock.expiration.time.com.liferay.portlet.wiki.model.WikiPage=1200000

#### Mail

Set the JNDI name to lookup the Java Mail session. If none is set, then
the portal will attempt to create the Java Mail session based on the
properties prefixed with *mail.session*.

\#mail.session.jndi.name=mail/MailSession

Set the properties used to create the Java Mail session. The property
prefix `mail.session`{.western}. will be removed before it is used to
create the session object. These properties will only be read if the
property *mail.session.jndi.name* is not set.

mail.session.mail.imap.host=localhost

mail.session.mail.pop3.host=localhost

\#mail.session.mail.smtp.auth=true

mail.session.mail.smtp.host=localhost

\#mail.session.mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory

\#mail.session.mail.smtp.socketFactory.fallback=false

\#mail.session.mail.smtp.socketFactory.port=465

\#mail.session.mail.smtp.starttls.enable=true

\#mail.session.mail.smtp.password=

\#mail.session.mail.smtp.port=465

\#mail.session.mail.smtp.user=

mail.session.mail.store.protocol=localhost

mail.session.mail.transport.protocol=smtp

Set this to false if administrator should not be allowed to change the
mail domain via the Admin portlet.

mail.mx.update=true

Input a list of comma delimited email addresses that will receive a BCC
of every email sent through the mail server.

mail.audit.trail=

Set the name of a class that implements
`com.liferay.mail.util.Hook`{.western}. The mail server will use this
class to ensure that the mail and portal servers are synchronized on
user information. The portal will not know how to add, update, or delete
users from the mail server except through this hook.

Available hooks are:

mail.hook.impl=com.liferay.mail.util.CyrusHook

mail.hook.impl=com.liferay.mail.util.DummyHook

mail.hook.impl=com.liferay.mail.util.FuseMailHook

mail.hook.impl=com.liferay.mail.util.GoogleHook

mail.hook.impl=com.liferay.mail.util.SendmailHook

mail.hook.impl=com.liferay.mail.util.ShellHook

##### CyrusHook

Set the commands for adding, updating, and deleting a user where %1% is
the user id. Replace the password with the password for the cyrus user.

mail.hook.cyrus.add.user=cyrusadmin password create %1%

\#mail.hook.cyrus.add.user=cyrus\_adduser password %1%

mail.hook.cyrus.delete.user=cyrusadmin password delete %1%

\#mail.hook.cyrus.delete.user=cyrus\_userdel password %1%

mail.hook.cyrus.home=/home/cyrus

##### FuseMailHook

See http://www.fusemail.com/support/api.html for more information. You
must also update the *mail.account.finder* property.

mail.hook.fusemail.url=https://www.fusemail.com/api/request.html

mail.hook.fusemail.username=

mail.hook.fusemail.password=

mail.hook.fusemail.account.type=group\_subaccount

mail.hook.fusemail.group.parent=

##### SendmailHook

Set the commands for adding, updating, and deleting a user where
`%1%`{.western} is the user id and `%2%`{.western} is the password. Set
the home and virtual user table information.

mail.hook.sendmail.add.user=adduser %1% -s /bin/false

mail.hook.sendmail.change.password=autopasswd %1% %2%

mail.hook.sendmail.delete.user=userdel -r %1%

mail.hook.sendmail.home=/home

mail.hook.sendmail.virtusertable=/etc/mail/virtusertable

mail.hook.sendmail.virtusertable.refresh=bash -c "makemap hash
/etc/mail/virtusertable < /etc/mail/virtusertable"

##### ShellHook

Set the location of the shell script that will interface with any mail
server.

mail.hook.shell.script=/usr/sbin/mailadmin.ksh

#### OpenOffice

Enabling OpenOffice integration allows the Document Library portlet to
provide document conversion functionality. To start OpenOffice as a
service, run the command:

soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;"
-nofirststartwizard

This is tested with OpenOffice 2.3.x.

openoffice.server.enabled=false

openoffice.server.host=127.0.0.1

openoffice.server.port=8100

#### Poller

Specify the poller request timeout in milliseconds. This prevents the
poller from locking up the application server.

poller.request.timeout=1000

#### POP

Set this to true to enable polling of email notifications from a POP
server. The user credentials are the same used for SMTP authentication
and is specified in the *mail/MailSession* configuration for each
application server.

pop.server.notifications.enabled=false

Set the interval on which the POPNotificationsJob will run. The value is
set in one minute increments.

pop.server.notifications.interval=1

Set this property to create a special MX subdomain to receive all portal
related email (e.g. `events.liferay.com`{.western}). This means
configuring a default inbox for the domain and receiving all emails into
that inbox.

This approach may not be allowed for some organizations. If you cannot
use the subdomain approach, unset this value and Liferay will use the
`replyTo`{.western} address specified in the portlet preferences.

pop.server.subdomain=events

#### Quartz

These properties define the connection to the built-in Quartz job
scheduling engine.

org.quartz.dataSource.ds.connectionProvider.class=com.liferay.portal.scheduler.quartz.QuartzConnectionProviderImpl

org.quartz.jobStore.class=org.quartz.impl.jdbcjobstore.JobStoreTX

org.quartz.jobStore.dataSource=ds

org.quartz.jobStore.driverDelegateClass=com.liferay.portal.scheduler.quartz.DynamicDriverDelegate

org.quartz.jobStore.isClustered=false

org.quartz.jobStore.misfireThreshold=60000

org.quartz.jobStore.tablePrefix=QUARTZ\_

org.quartz.jobStore.useProperties=true

org.quartz.scheduler.instanceId=AUTO

org.quartz.scheduler.instanceName=QuartzSchedulerEngineInstance

org.quartz.threadPool.class=org.quartz.simpl.SimpleThreadPool

org.quartz.threadPool.threadCount=5

org.quartz.threadPool.threadPriority=5

#### Scheduler

Set this to false to disable all scheduler classes defined in
*liferay-portlet.xml* and in the property *scheduler.classes*.

scheduler.enabled=true

Input a list of comma delimited class names that implement
*com.liferay.portal.kernel.job.Scheduler*. These classes allow jobs to
be scheduled on startup. These classes are not associated to any one
portlet.

scheduler.classes=

#### Search Container

Set the available values for the number of entries to display per page.
An empty value, or commenting out the value, will disable delta
resizing.

The default of 20 will apply in all cases.

Always include 20, since it is the default page size when no delta is
specified. The absolute maximum allowed delta is 200.

search.container.page.delta.values=5,10,20,30,50,75

#### Sharepoint

Set the tokens for supported Sharepoint storage paths.

sharepoint.storage.tokens=document\_library

Set the class names for supported Sharepoint storage classes.

sharepoint.storage.class[document\_library]=com.liferay.portlet.documentlibrary.sharepoint.DLSharepointStorageImpl

#### Social Bookmarks

The Blogs portlet allows for the posting of entries to various popular
social bookmarking sites. The example ones are the defaults; to
configure more, just add the site in the format below.

social.bookmark.types=blinklist,delicious,digg,furl,newsvine,reddit,technorati

social.bookmark.post.url[blinklist]=http://blinklist.com/index.php?Action=Blink/addblink.php&url=${liferay:social-bookmark:url}&Title=${liferay:social-bookmark:title}

social.bookmark.post.url[delicious]=http://del.icio.us/post?url=${liferay:social-bookmark:url}&title=${liferay:social-bookmark:title}

social.bookmark.post.url[digg]=http://digg.com/submit?phase=2&url=${liferay:social-bookmark:url}

social.bookmark.post.url[furl]=http://furl.net/storeIt.jsp?u=${liferay:social-bookmark:url}&t=${liferay:social-bookmark:title}

social.bookmark.post.url[newsvine]=http://www.newsvine.com/\_tools/seed&save?u=${liferay:social-bookmark:url}&h=${liferay:social-bookmark:title}

social.bookmark.post.url[reddit]=http://reddit.com/submit?url=${liferay:social-bookmark:url}&title=${liferay:social-bookmark:title}

social.bookmark.post.url[technorati]=http://technorati.com/cosmos/search.html?url=${liferay:social-bookmark:url}

#### Velocity Engine

Input a list of comma delimited class names that extend
*com.liferay.util.velocity.VelocityResourceListener*. These classes will
run in sequence to allow you to find the applicable ResourceLoader to
load a Velocity template.

velocity.engine.resource.listeners=com.liferay.portal.velocity.ServletVelocityResourceListener,com.liferay.portal.velocity.JournalTemplateVelocityResourceListener,com.liferay.portal.velocity.ThemeLoaderVelocityResourceListener,com.liferay.portal.velocity.ClassLoaderVelocityResourceListener

Set the Velocity resource managers. We extend the Velocity's default
resource managers for better scalability.

Note that the modification check interval is not respected because the
resource loader implementation does not know the last modified date of a
resource. This means you will need to turn off caching if you want to be
able to modify VM templates in themes and see the changes right away.

velocity.engine.resource.manager=com.liferay.portal.velocity.LiferayResourceManager

velocity.engine.resource.manager.cache=com.liferay.portal.velocity.LiferayResourceCache

velocity.engine.resource.manager.cache.enabled=true

\#velocity.engine.resource.manager.modification.check.interval=0

Input a list of comma delimited macros that will be loaded. These files
must exist in the class path.

velocity.engine.velocimacro.library=VM\_global\_library.vm,VM\_liferay.vm

Set the Velocity logging configuration.

velocity.engine.logger=org.apache.velocity.runtime.log.SimpleLog4JLogSystem

velocity.engine.logger.category=org.apache.velocity

#### Virtual Hosts

Set the extensions that will be ignored for virtual hosts.

virtual.hosts.ignore.extensions=\\

/c,\\

.css,\\

.gif,\\

.image/company\_logo,\\

.ico,\\

.js,\\

.jpeg,\\

.jsp,\\

.png,\\

/portal/layout,\\

/portal/login,\\

/portal/logout

Set the hosts that will be ignored for virtual hosts.

virtual.hosts.ignore.hosts=\\

127.0.0.1,\\

localhost

Set the paths that will be ignored for virtual hosts.

virtual.hosts.ignore.paths=\\

/c,\\

\\

/c/portal/change\_password,\\

\
\

/c/portal/extend\_session,\\

/c/portal/extend\_session\_confirm,\\

\
\

/c/portal/json\_service,\\

/c/portal/layout,\\

/c/portal/login,\\

/c/portal/logout,\\

/c/portal/portlet\_url,\\

/c/portal/render\_portlet,\\

/c/portal/reverse\_ajax,\\

/c/portal/session\_tree\_js\_click,\\

/c/portal/status,\\

/c/portal/update\_layout,\\

/c/portal/update\_terms\_of\_use,\\

/c/portal/upload\_progress\_poller,\\

\\

/c/layout\_configuration/templates,\\

/c/layout\_management/update\_page

Specify the community name that will default to the company's virtual
host. If the specified community has a virtual host, then that will take
precedence. If it does not, then it will use the company's virtual host.

This property is useful to remove `/web/guest`{.western} (or any other
community) from the default URL. For example, if this property is not
set, then the default URL may be
`http://localhost:8080/web/guest/home`{.western}. If this property is
set, then the default URL may be http://localhost:8080/home.

virtual.hosts.default.community.name=Guest

#### HTTP

See `system.properties`{.western} for more HTTP settings.

Set the maximum number of connections.

\#com.liferay.portal.util.HttpImpl.max.connections.per.host=2

\#com.liferay.portal.util.HttpImpl.max.total.connections=20

Set the proxy authentication type.

\#com.liferay.portal.util.HttpImpl.proxy.auth.type=username-password

\#com.liferay.portal.util.HttpImpl.proxy.auth.type=ntlm

Set user name and password used for HTTP proxy authentication.

\#com.liferay.portal.util.HttpImpl.proxy.username=

\#com.liferay.portal.util.HttpImpl.proxy.password=

Set additional properties for NTLM authentication.

\#com.liferay.portal.util.HttpImpl.proxy.ntlm.domain=

\#com.liferay.portal.util.HttpImpl.proxy.ntlm.host=

Set the connection timeout when fetching HTTP content.

com.liferay.portal.util.HttpImpl.timeout=10000

#### Servlet Filters

The audit filter populates the `AuditRequestThreadLocal`{.western} with
the appropriate request values to generate audit requests.

com.liferay.portal.servlet.filters.audit.AuditFilter=false

The auto login filter processes the classes in the property
`auto.login.hooks`{.western} to provide auto login functionality.

com.liferay.portal.servlet.filters.autologin.AutoLoginFilter=true

The cache filter will cache content. See *ehcache.xml* to modify the
cache expiration time to live.

com.liferay.portal.servlet.filters.cache.CacheFilter=true

The CAS filter is used to provide CAS based single sign on.

com.liferay.portal.servlet.filters.sso.cas.CASFilter=true

This double click filter will prevent double clicks at the server side.
Prevention of double clicks is already in place on the client side.
However, some sites require a more robust solution. This is turned off
by default since most sites will not need it.

com.liferay.portal.servlet.filters.doubleclick.DoubleClickFilter=false

The ETag filter is used to generate ETag headers.

com.liferay.portal.servlet.filters.etag.ETagFilter=true

If the user can unzip compressed HTTP content, the GZip filter will zip
up the HTTP content before sending it to the user. This will speed up
page rendering for users that are on dial up.

com.liferay.portal.servlet.filters.gzip.GZipFilter=true

The header filter is used to set request headers.

com.liferay.portal.servlet.filters.header.HeaderFilter=true

The I18n filter is used to internationalize URLs. See the property
`locale.prepend.friendly.url.style`{.western} for more information.

com.liferay.portal.servlet.filters.i18n.I18nFilter=true

The Language filter replaces JavaScript code that make a client side
call to translate a piece of text with the actual translated value. For
example, a typical piece of JavaScript code fits the pattern
`Liferay.Language.get('key')`{.western} where `key`{.western} is the
text to translate. This filter will replace the entire piece of code
with the translated text. This is very useful because it will lower the
number of client calls by translating the text before the browser
receives the JavaScript file.

com.liferay.portal.servlet.filters.language.LanguageFilter=true

The minifier filter is used to minify CSS and JavaScript.

com.liferay.portal.servlet.filters.minifier.MinifierFilter=true

The monitoring filter monitors portal request performance.

com.liferay.portal.servlet.filters.monitoring.MonitoringFilter=true

The NTLM filter is used to provide NTLM based single sign on.

com.liferay.portal.servlet.filters.sso.ntlm.NtlmFilter=true

The NTLM post filter is used to fix known issues with NTLM and ajax
requests. See LPS-3795.

com.liferay.portal.servlet.filters.sso.ntlm.NtlmPostFilter=true

The OpenSSO filter is used to provide OpenSSO based single sign on.

com.liferay.portal.servlet.filters.sso.opensso.OpenSSOFilter=true

The secure filter is used to protect servlets based on IP and protocol.
See the properties `*.servlet.hosts.allowed`{.western} and
`*.servlet.https.required`{.western}.

com.liferay.portal.servlet.filters.secure.SecureFilter=true

The servlet authorizing filter allows external servlets to be authorized
by the portal. See LEP-4682.

com.liferay.portal.servlet.filters.servletauthorizing.ServletAuthorizingFilter=true

The strip filter will remove blank lines from the content. This will
speed up page rendering for users that are on dial up.

com.liferay.portal.servlet.filters.strip.StripFilter=true

The layout cache filter will cache pages to speed up page rendering for
guest users. See *ehcache.xml* to modify the cache expiration time to
live.

com.liferay.portal.servlet.filters.layoutcache.LayoutCacheFilter=true

The session id filter ensure that only one session is created between
`http`{.western} and `https`{.western} sessions. This is useful if you
want users to login via `https`{.western} but have them view the rest of
the site via `http`{.western}. This is disabled by default. Do not
enable this unless you thoroughly understand how cookies,
`http`{.western}, and `https`{.western} work.

com.liferay.portal.servlet.filters.sessionid.SessionIdFilter=false

The Sharepoint filter allows users to access documents in the Document
Library directly from Microsoft Office using the Sharepoint protocol.

com.liferay.portal.sharepoint.SharepointFilter=true

The strip filter will remove blank lines from the outputted content.
This will speed up page rendering for users that are on dial up.

com.liferay.portal.servlet.filters.strip.StripFilter=true

The theme preview filter generates a preview of the currently applied
theme that can be used by the Dreamweaver Theming plugin. This is
disabled by default. Set the `themePreview`{.western} parameter to
`1`{.western} in the URL to access the theme preview for any page. For
example, a URL can be
`http://localhost:8080/web/guest?themePreview=1`{.western}.

com.liferay.portal.servlet.filters.themepreview.ThemePreviewFilter=false

The thread local filter cleans up registered thread locals to prevent
memory leaks. Register your thread local with
`com.liferay.portal.kernel.util.ThreadLocalRegistry`{.western}.

com.liferay.portal.servlet.filters.threadlocal.ThreadLocalFilter=true

The valid HTML filter will move JavaScript that is outside of the
closing body tag to its proper place inside the body tag. Most sites
will prefer to leave this filter disabled because having JavaScript
outside of the body tag causes the page to render faster. However, the
side effect is that it will also make the site inaccessible to screen
readers because the HTML is technically invalid. Setting this property
to true optimizes for accessibility while setting this property to false
optimizes for browser performance.

com.liferay.portal.servlet.filters.validhtml.ValidHtmlFilter=false

The Velocity filter will process `*/css/main.css`{.western} as a
Velocity template.

com.liferay.portal.servlet.filters.velocity.VelocityFilter=false

The virtual host filter maps hosts to public and private pages. For
example, if the public virtual host is `www.helloworld.com`{.western}
and the friendly URL is `/helloworld`{.western}, then
`http://www.helloworld.com`{.western} is mapped to
`http://localhost:8080/web/helloworld`{.western}.

com.liferay.portal.servlet.filters.virtualhost.VirtualHostFilter=true

#### Upload Servlet Request

Set the maximum file size. Default is 1024 \* 1024 \* 100.

com.liferay.portal.upload.UploadServletRequestImpl.max.size=104857600

Set the temp directory for uploaded files.

\#com.liferay.portal.upload.UploadServletRequestImpl.temp.dir=C:/Temp

Set the threshold size to prevent extraneous serialization of uploaded
data.

com.liferay.portal.upload.LiferayFileItem.threshold.size=262144

Set the threshold size to prevent out of memory exceptions caused by
caching excessively large uploaded data. Default is 1024 \* 1024 \* 10.

com.liferay.portal.upload.LiferayInputStream.threshold.size=10485760

#### Web Server

Set the HTTP and HTTPs ports when running the portal in a J2EE server
that is sitting behind another web server like Apache. Set the values to
-1 if the portal is not running behind another web server like Apache.

web.server.http.port=-1

web.server.https.port=-1

Set the hostname that will be used when the portlet generates URLs.
Leaving this blank will mean the host is derived from the servlet
container.

web.server.host=

Set the preferred protocol.

web.server.protocol=https

Set this to true to display the server name at the bottom of every page.
This is useful when testing clustering configurations so that you can
know which node you are accessing.

web.server.display.node=false

#### WebDAV

Set a list of files for the WebDAV servlet to ignore processing.

webdav.ignore=.DS\_Store,.metadata\_index\_homes\_only,.metadata\_never\_index,.Spotlight-V100,.TemporaryItems,.Trashes

#### Main Servlet

Servlets can be protected by
*com.liferay.portal.servlet.filters.secure.SecureFilter*.

Input a list of comma delimited IPs that can access this servlet. Input
a blank list to allow any IP to access this servlet.
`SERVER_IP`{.western} will be replaced with the IP of the host server.

main.servlet.hosts.allowed=

Set the following to true if this servlet can only be accessed via
`https`{.western}.

main.servlet.https.required=false

#### Axis Servlet

See Main Servlet on how to protect this servlet.

axis.servlet.hosts.allowed=127.0.0.1,SERVER\_IP

axis.servlet.https.required=false

#### Google Gadget Servlet

Set the servlet mapping for the Google Gadget servlet.

google.gadget.servlet.mapping=/google\_gadget

#### JSON Tunnel Servlet

See Main Servlet on how to protect this servlet.

json.servlet.hosts.allowed=

json.servlet.https.required=false

#### Liferay Tunnel Servlet

See Main Servlet on how to protect this servlet.

tunnel.servlet.hosts.allowed=127.0.0.1,SERVER\_IP

tunnel.servlet.https.required=false

#### Netvibes Servlet

Set the servlet mapping for the Netvibes servlet.

netvibes.servlet.mapping=/netvibes

#### Spring Remoting Servlet

See Main Servlet on how to protect this servlet.

spring.remoting.servlet.hosts.allowed=127.0.0.1,SERVER\_IP

spring.remoting.servlet.https.required=false

#### WebDAV Servlet

See Main Servlet on how to protect this servlet.

webdav.servlet.hosts.allowed=

webdav.servlet.https.required=false

#### Widget Servlet

Set the servlet mapping for the widget servlet.

widget.servlet.mapping=/widget

#### Admin Portlet

You can set some administrative defaults by using these properties. The
first time you bring up your portal, these values will then already be
set in the Admin portlet. All values should be separated by
`\n`{.western} characters.

Set up default group names.

admin.default.group.names=

Set up default role names.

admin.default.role.names=Power User\\nUser

Set up default user group names.

admin.default.user.group.names=

Set this to true to ensure that a user is synchronized with the default
associations of groups, roles, and user groups upon every log in. Set
this to false if default associations should only be applied to a user
when a user is created.

admin.sync.default.associations=false

The rest of these properties map to their values in the Admin portlet.

admin.mail.host.names=

admin.reserved.screen.names=

admin.reserved.email.addresses=

admin.email.from.name=Joe Bloggs

admin.email.from.address=test@liferay.com

admin.email.user.added.enabled=true

admin.email.user.added.subject=com/liferay/portlet/admin/dependencies/email\_user\_added\_subject.tmpl

admin.email.user.added.body=com/liferay/portlet/admin/dependencies/email\_user\_added\_body.tmpl

admin.email.password.sent.enabled=true

admin.email.password.sent.subject=com/liferay/portlet/admin/dependencies/email\_password\_sent\_subject.tmpl

admin.email.password.sent.body=com/liferay/portlet/admin/dependencies/email\_password\_sent\_body.tmpl

#### Announcements Portlet

Configure email notification settings.

announcements.email.from.name=Joe Bloggs

announcements.email.from.address=test@liferay.com

announcements.email.to.name=

announcements.email.to.address=noreply@liferay.com

announcements.email.subject=com/liferay/portlet/announcements/dependencies/email\_subject.tmpl

announcements.email.body=com/liferay/portlet/announcements/dependencies/email\_body.tmpl

Set the list of announcement types. The display text of each of the
announcement types is set in `content/Language.properties`{.western}.

announcements.entry.types=general,news,test

Set the interval on which the `CheckEntryJob`{.western} will run. The
value is set in one minute increments.

announcements.entry.check.interval=15

#### Asset Publisher Portlet

Input a list of comma separated display styles that will be available in
the configuration screen of Asset Publisher portlet.

asset.publisher.display.styles=table,title-list,abstracts,full-content

#### Asset

Input a list of comma delimited default properties for new categories.
Each item of the list should have the following format:
`key:value`{.western}.

asset.categories.properties.default=

Set the following to false to specify that searching and browsing using
categories should only show assets that have been assigned the selected
category explicitly. When set to true, the children categories are also
included in the search.

asset.categories.search.hierarchical=true

Set this to true to enable incrementing the view counter for assets.

asset.entry.increment.view.counter.enabled=true

Input a class name that implements
`com.liferay.portlet.asset.util.AssetEntryValidator`{.western}. This
class will be called to validate entries. The
`DefaultAssetEntryValidator`{.western} class is just an empty class that
doesn't actually do any validation. The
`MinimalAssetEntryValidator`{.western} requires all enties to have at
least one tag.

asset.entry.validator=com.liferay.portlet.asset.util.DefaultAssetEntryValidator

asset.entry.validator=com.liferay.portlet.asset.util.MinimalAssetEntryValidator

Input a list of comma delimited default tag properties for new tags.
Each item of the list should have the following format:
`key:value`{.western}.

asset.tag.properties.default=

Set the name of the default vocabulary which will be created by default.

asset.vocabulary.default=Topic

Set a property with the prefix `asset.renderer.enabled`{.western}. and a
suffix with the asset renderer factory class name to enable or disable
an asset renderer factory. The default setting is true. See LPS-6085 for
more information.

asset.renderer.enabled.com.liferay.portlet.documentlibrary.asset.DLFileEntryAssetRendererFactory=false

#### Blogs Portlet

The following properties affect the Blogs portlet.

blogs.email.comments.added.enabled=true

blogs.email.comments.added.subject=com/liferay/portlet/blogs/dependencies/email\_comments\_added\_subject.tmpl

blogs.email.comments.added.body=com/liferay/portlet/blogs/dependencies/email\_comments\_added\_body.tmpl

blogs.page.abstract.length=400

blogs.rss.abstract.length=200

blogs.trackback.excerpt.length=50

Configure email notification settings.

blogs.email.from.name=Joe Bloggs

blogs.email.from.address=test@liferay.com

blogs.email.entry.added.enabled=true

blogs.email.entry.added.subject=com/liferay/portlet/blogs/dependencies/email\_entry\_added\_subject.tmpl

blogs.email.entry.added.body=com/liferay/portlet/blogs/dependencies/email\_entry\_added\_body.tmpl

blogs.email.entry.updated.enabled=true

blogs.email.entry.updated.subject=com/liferay/portlet/blogs/dependencies/email\_entry\_updated\_subject.tmpl

blogs.email.entry.updated.body=com/liferay/portlet/blogs/dependencies/email\_entry\_updated\_body.tmpl

Set the interval on which the `TrackbackVerifierJob`{.western} will run.
The value is set in one minute increments.

blogs.trackback.verifier.job.interval=5

Set the excerpt length for linkbacks.

blogs.linkback.excerpt.length=200

Set the interval on which the LinkbackMessageListener will run. The
value is set in one minute increments.

blogs.linkback.job.interval=5

Set this to true to enable pingbacks.

blogs.pingback.enabled=true

Set this to true to enable trackbacks.

blogs.trackback.enabled=true

Set this to true to enable pinging Google on new and updated blog
entries.

blogs.ping.google.enabled=true

Set this to true to enable comments for blogs entries.

blogs.entry.comments.enabled=true

Set this to true to enable previous and next navigation for blogs
entries.

blogs.entry.previous.and.next.navigation.enabled=true

#### Breadcrumb Portlet

Set this to true to show the Guest community as the top level parent in
the breadcrumbs. It will only show if it has at least one page.

breadcrumb.show.guest.group=true

Set this to true to show the path of parent communities or organizations
in the breadcrumbs. An community or organization will only be shown if
it has at least one page.

breadcrumb.show.parent.groups=true

#### Calendar Portlet

Set the list of event types. The display text of each of the event types
is set in *content/Language.properties*.

calendar.event.types=anniversary,appointment,bill-payment,birthday,breakfast,call,chat,class,club-event,concert,dinner,event,graduation,happy-hour,holiday,interview,lunch,meeting,movie,net-event,other,party,performance,press-release,reunion,sports-event,training,travel,tv-show,vacation,wedding

Set the interval on which the `CheckEventJob`{.western} will run. The
value is set in one minute increments.

calendar.event.check.interval=15

Configure email notification settings.

calendar.email.from.name=Joe Bloggs

calendar.email.from.address=test@liferay.com

calendar.email.event.reminder.enabled=true

calendar.email.event.reminder.subject=com/liferay/portlet/calendar/dependencies/email\_event\_reminder\_subject.tmpl

calendar.email.event.reminder.body=com/liferay/portlet/calendar/dependencies/email\_event\_reminder\_body.tmpl

Set this to true to enable comments for calendar events.

calendar.event.comments.enabled=true

#### Communities Portlet

Set this to true to allow community members to see the Communities
portlet and the communities he is a member of in the Control Panel.
Setting this to false will only allow administrators to see this portlet
in the Control Panel.

communities.control.panel.members.visible=true

Configure email notification settings.

communities.email.from.name=Joe Bloggs

communities.email.from.address=test@liferay.com

communities.email.membership.reply.subject=com/liferay/portlet/communities/dependencies/email\_membership\_reply\_subject.tmpl

communities.email.membership.reply.body=com/liferay/portlet/communities/dependencies/email\_membership\_reply\_body.tmpl

communities.email.membership.request.subject=com/liferay/portlet/communities/dependencies/email\_membership\_request\_subject.tmpl

communities.email.membership.request.body=com/liferay/portlet/communities/dependencies/email\_membership\_request\_body.tmpl

#### Discussion Tag Library

Set the thread view for discussion comments. This will affect Blogs,
Document Library, and other portlets that use the Discussion tag library
to provide comments. Set the value to `flat`{.western} to paginate
comments. Set the value to `combination`{.western} to show all comments
in one page along with a tree view of the comments.

discussion.thread.view=combination

discussion.thread.view=flat

#### Document Library Portlet

Set the name of a class that implements
`com.liferay.documentlibrary.util.Hook`{.western}. The document library
server will use this persist documents.

dl.hook.impl=com.liferay.documentlibrary.util.AdvancedFileSystemHook

dl.hook.impl=com.liferay.documentlibrary.util.CMISHook

dl.hook.impl=com.liferay.documentlibrary.util.FileSystemHook

dl.hook.impl=com.liferay.documentlibrary.util.JCRHook

dl.hook.impl=com.liferay.documentlibrary.util.S3Hook

FileSystemHook

dl.hook.file.system.root.dir=${liferay.home}/data/document\_library

JCRHook

dl.hook.jcr.fetch.delay=500

dl.hook.jcr.fetch.max.failures=5

A file extension of `*`{.western} will permit all file extensions.

dl.file.extensions=\*

dl.file.extensions=.bmp,.css,.doc,.docx,.dot,.gif,.gz,.htm,.html,.jpg,.js,.lar,.odb,.odf,.odg,.odp,.ods,.odt,.pdf,.png,.ppt,.pptx,.rtf,.swf,.sxc,.sxi,.sxw,.tar,.tiff,.tgz,.txt,.vsd,.xls,.xlsx,.xml,.zip,.jrxml

Set this to false to allow users to update file entries by uploading a
file with an extension different from the one of the originally uploaded
file. There is a known issue where setting this to true will break OSX
compatibility. See LPS-10770 for more information.

dl.file.extensions.strict.check=false

Set the maximum file size for indexing file contents. Files larger than
this property will not have their contents indexed, only their metadata
will be indexed. A value of -1 indicates that all file contents will be
indexed. A value of 0 indicates that no file contents will be indexed.

dl.file.indexing.max.size=10485760

Set this to true to enable the read count for document library files.

dl.file.entry.read.count.enabled=true

Set this to true to enable file rank for document library files.

dl.file.rank.enabled=true

Set this to true if document library should be published to live by
default.

dl.publish.to.live.by.default=true

Set this to true to hold the lock when an unlock is requested through
WebDAV until a user manually releases the lock from the Document Library
portlet. Set this to false to release the lock.

dl.webdav.hold.lock=false

Set this to true to create only one document library file version when
saving multiple times during a WebDAV session.

dl.webdav.save.to.single.version=false

#### Dockbar Portlet

Set the portlet ids that will be shown directly in the *Add Application*
menu.

dockbar.add.portlets=56,101,110,71

#### Flags Portlet

Input a list of questions used for flag reasons.

flags.reasons=sexual-content,violent-or-repulsive-content,hateful-or-abusive-content,harmful-dangerous-acts,spam,infringes-my-rights

#### Email Notification Settings

flags.email.from.name=Joe Bloggs

flags.email.from.address=test@liferay.com

\
\

flags.email.subject=com/liferay/portlet/flags/dependencies/email\_flag\_subject.tmpl

flags.email.body=com/liferay/portlet/flags/dependencies/email\_flag\_body.tmpl

Set this to true to enable guest users to flag content

flags.guest.users.enabled=false

#### IFrame Portlet

Specify a role name that a user must be associated with in order to
configure the IFrame portlet to use the @password@ token. This token is
used to post the password of users who access this portlet in order to
automatically login to the framed site.

No role is required by default. However, it is recommended that you
specify a role in high security environments where users who configure
this portlet may attempt password theft. See LPS-5272 for more
information.

iframe.password.token.role=

\
\

#### Image Gallery Portlet

Set the maximum file size and valid file extensions for images. A value
of 0 for the maximum file size can be used to indicate unlimited file
size. However, the maximum file size allowed is set in the property
`com.liferay.portal.upload.UploadServletRequestImpl.max.size`{.western}.

ig.image.max.size=10240000

A file extension of \* will permit all file extensions.

ig.image.extensions=.bmp,.gif,.jpeg,.jpg,.png,.tif,.tiff

Set the maximum thumbnail height and width in pixels. Set dimension of
the custom images to 0 to disable creating a scaled image of that size.

ig.image.thumbnail.max.dimension=150

ig.image.custom1.max.dimension=100

ig.image.custom2.max.dimension=0

Set this to true if image gallery should be published to live by
default.

ig.publish.to.live.by.default=true

#### Login Portlet

Set this to true to allow the user to choose a password during account
creation.

login.create.account.allow.custom.password=false

#### Invitation Portlet

invitation.email.max.recipients=20

invitation.email.message.body=com/liferay/portlet/invitation/dependencies/email\_message\_body.tmpl

invitation.email.message.subject=com/liferay/portlet/invitation/dependencies/email\_message\_subject.tmpl

#### Journal Portlet

Set this to true if article ids should always be autogenerated.

journal.article.force.autogenerate.id=true

Set this to true so that only the latest version of an article that is
also not approved can be saved without incrementing version.

journal.article.force.increment.version=false

Set the list of article types. The display text of each of the article
types is set in `content/Language.properties`{.western}.

journal.article.types=announcements,blogs,general,news,press-release,test

Set the token used when inserting simple page breaks in articles.

journal.article.token.page.break=@page\_break@

Set the interval on which the CheckArticleJob will run. The value is set
in one minute increments.

journal.article.check.interval=15

Set this to true to check that a user has the VIEW permission on a
Journal article when its content is rendered.

journal.article.view.permission.check.enabled=false

Set this to true if feed ids should always be autogenerated.

journal.feed.force.autogenerate.id=false

Set this to true if structure ids should always be autogenerated.

journal.structure.force.autogenerate.id=false

Set this to true if template ids should always be autogenerated.

journal.template.force.autogenerate.id=false

Input a comma delimited list of variables which are restricted from the
context in Velocity based Journal templates.

journal.template.velocity.restricted.variables=serviceLocator

Set the maximum file size and valid file extensions for images. A value
of 0 for the maximum file size can be used to indicate unlimited file
size. However, the maximum file size allowed is set in the property
*com.liferay.portal.upload.UploadServletRequestImpl.max.size*.

journal.image.small.max.size=51200

A file extension of \* will permit all file extensions.

journal.image.extensions=.gif,.jpeg,.jpg,.png

Input a list of comma delimited class names that extend
*com.liferay.portlet.journal.util.TransformerListener*. These classes
will run in sequence to allow you to modify the XML and XSL before it's
transformed and allow you to modify the final output.

journal.transformer.listener=\\

com.liferay.portlet.journal.util.TokensTransformerListener,\\

\#com.liferay.portlet.journal.util.PropertiesTransformerListener,\\

com.liferay.portlet.journal.util.ContentTransformerListener,\\

com.liferay.portlet.journal.util.LocaleTransformerListener,\\

com.liferay.portlet.journal.util.RegexTransformerListener,\\

com.liferay.portlet.journal.util.ViewCounterTransformerListener

Enter a list of regular expression patterns and replacements that will
be applied to outputted Journal content. The list of properties must end
with a subsequent integer (0, 1, etc.) and it is assumed that the list
has reached an end when the pattern or replacement is not set. See
*com.liferay.portlet.journal.util.RegexTransformerListener* for
implementation details.

\#journal.transformer.regex.pattern.0=beta.sample.com

\#journal.transformer.regex.replacement.0=production.sample.com

\#journal.transformer.regex.pattern.1=staging.sample.com

\#journal.transformer.regex.replacement.1=production.sample.com

Set whether to synchronize content searches when server starts.

journal.sync.content.search.on.startup=false

Configure mail notification settings.

journal.email.from.name=Joe Bloggs

journal.email.from.address=test@liferay.com

journal.email.article.approval.denied.enabled=false

journal.email.article.approval.denied.subject=com/liferay/portlet/journal/dependencies/email\_article\_approval\_denied\_subject.tmpl

journal.email.article.approval.denied.body=com/liferay/portlet/journal/dependencies/email\_article\_approval\_denied\_body.tmpl

journal.email.article.approval.granted.enabled=false

journal.email.article.approval.granted.subject=com/liferay/portlet/journal/dependencies/email\_article\_approval\_granted\_subject.tmpl

journal.email.article.approval.granted.body=com/liferay/portlet/journal/dependencies/email\_article\_approval\_granted\_body.tmpl

journal.email.article.approval.requested.enabled=false

journal.email.article.approval.requested.subject=com/liferay/portlet/journal/dependencies/email\_article\_approval\_requested\_subject.tmpl

journal.email.article.approval.requested.body=com/liferay/portlet/journal/dependencies/email\_article\_approval\_requested\_body.tmpl

journal.email.article.review.enabled=false

journal.email.article.review.subject=com/liferay/portlet/journal/dependencies/email\_article\_review\_subject.tmpl

journal.email.article.review.body=com/liferay/portlet/journal/dependencies/email\_article\_review\_body.tmpl

Specify the strategy used when Journal content is imported using the LAR
system.

journal.lar.creation.strategy=com.liferay.portlet.journal.lar.JournalCreationStrategyImpl

Specify the path to the template used for providing error messages on
Journal templates.

journal.error.template.velocity=com/liferay/portlet/journal/dependencies/error.vm

journal.error.template.xsl=com/liferay/portlet/journal/dependencies/error.xsl

#### Journal Articles Portlet

Set the available values for the number of articles to display per page.

journal.articles.page.delta.values=5,10,25,50,100

#### Journal Content Search Portlet

Set whether unlisted articles are excluded from search results.

journal.content.search.show.listed=true

#### Message Boards Portlet

Configure mail notification settings.

message.boards.email.from.name=Joe Bloggs

message.boards.email.from.address=test@liferay.com

message.boards.email.html.format=true

message.boards.email.message.added.enabled=true

message.boards.email.message.added.subject.prefix=com/liferay/portlet/messageboards/dependencies/email\_message\_added\_subject\_prefix.tmpl

message.boards.email.message.added.body=com/liferay/portlet/messageboards/dependencies/email\_message\_added\_body.tmpl

message.boards.email.message.added.signature=com/liferay/portlet/messageboards/dependencies/email\_message\_added\_signature.tmpl

message.boards.email.message.updated.enabled=true

message.boards.email.message.updated.subject.prefix=com/liferay/portlet/messageboards/dependencies/email\_message\_updated\_subject\_prefix.tmpl

message.boards.email.message.updated.body=com/liferay/portlet/messageboards/dependencies/email\_message\_updated\_body.tmpl

message.boards.email.message.updated.signature=com/liferay/portlet/messageboards/dependencies/email\_message\_updated\_signature.tmpl

Set this to true to allow anonymous posting.

message.boards.anonymous.posting.enabled=true

Enter time in minutes on how often this job is run. If a user's ban is
set to expire at 12:05 PM and the job runs at 2 PM, the expire will
occur during the 2 PM run.

message.boards.expire.ban.job.interval=120

Enter time in days to automatically expire bans on users. Set to 0 to
disable auto expire.

*Examples: *

message.boards.expire.ban.interval=10

message.boards.expire.ban.interval=0

Enter RSS feed abstract length. This value limits what goes in the RSS
feed from the beginning of the message board post. The default is the
first 200 characters.

message.boards.rss.abstract.length=200

Set this to true to enable pingbacks.

message.boards.pingback.enabled=true

Set this to true to enable previous and next navigation for message
boards threads

message.boards.thread.previous.and.next.navigation.enabled=true

Set the allowed thread views and the default thread view.

message.boards.thread.views=combination,flat,tree

message.boards.thread.views.default=combination

#### My Places Portlet

Set the display style for the My Places navigation menu.

my.places.display.style=simple

my.places.display.style=classic

Set this to true to show user public sites with no layouts.

my.places.show.user.public.sites.with.no.layouts=true

Set this to true to show user private sites with no layouts.

my.places.show.user.private.sites.with.no.layouts=true

Set this to true to show organization public sites with no layouts.

my.places.show.organization.public.sites.with.no.layouts=true

Set this to true to show organization private sites with no layouts.

my.places.show.organization.private.sites.with.no.layouts=true

Set this to true to show community public sites with no layouts.

my.places.show.community.public.sites.with.no.layouts=true

Set this to true to show community private sites with no layouts.

my.places.show.community.private.sites.with.no.layouts=true

Set the maximum number of elements that will be shown in the My Places
navigation menu. For example, if the maximum is set to 10, then, at
most, 1 personal community, 10 organizations, and 10 communities will be
shown.

my.places.max.elements=10

#### Navigation Portlet

Specify the options that will be provided to the user in the edit
configuration mode of the portlet.

navigation.display.style.options=1,2,3,4,5,6

Define each mode with 4 comma delimited strings that represent the form:
`headerType, rootLayoutType, rootLayoutLevel,  includedLayouts`{.western},
and `nestedChildren`{.western}.

navigation.display.style[1]=breadcrumb,relative,0,auto,true

navigation.display.style[2]=root-layout,absolute,2,auto,true

navigation.display.style[3]=root-layout,absolute,1,auto,true

navigation.display.style[4]=none,absolute,1,auto,true

navigation.display.style[5]=none,absolute,1,all,true

navigation.display.style[6]=none,absolute,0,auto,true

#### Nested Portlets Portlet

nested.portlets.layout.template.default=2\_columns\_i

Add a comma separated list of layout template ids that should not be
allowed in the Nested Portlets Portlet.

nested.portlets.layout.template.unsupported=freeform,1\_column

#### Portlet CSS Portlet

Set this to true to enable the ability to modify portlet CSS at runtime
via the Look and Feel icon. Disabling it can speed up performance.

portlet.css.enabled=true

#### Search Portlet

Set any of these to false to disable the portlet from being searched by
the Search portlet.

com.liferay.portlet.blogs.util.BlogsOpenSearchImpl=true

com.liferay.portlet.bookmarks.util.BookmarksOpenSearchImpl=true

com.liferay.portlet.calendar.util.CalendarOpenSearchImpl=true

com.liferay.portlet.directory.util.DirectoryOpenSearchImpl=true

com.liferay.portlet.documentlibrary.util.DLOpenSearchImpl=true

com.liferay.portlet.imagegallery.util.IGOpenSearchImpl=true

com.liferay.portlet.journal.util.JournalOpenSearchImpl=true

com.liferay.portlet.messageboards.util.MBOpenSearchImpl=true

com.liferay.portlet.wiki.util.WikiOpenSearchImpl=true

#### Shopping Portlet

Set the following to true if cart quantities must be a multiple of the
item's minimum quantity.

shopping.cart.min.qty.multiple=true

Set the following to true to forward to the cart page when adding an
item from the category page. The item must not have dynamic fields. All
items with dynamic fields will forward to the item's details page
regardless of the following setting.

shopping.category.forward.to.cart=false

Set the following to true to show special items when browsing a
category.

shopping.category.show.special.items=false

Set the following to true to show availability when viewing an item.

shopping.item.show.availability=true

Set the maximum file size and valid file extensions for images. A value
of 0 for the maximum file size can be used to indicate unlimited file
size. However, the maximum file size allowed is set in the property
*com.liferay.portal.upload.UploadServletRequestImpl.max.size*.

shopping.image.small.max.size=51200

shopping.image.medium.max.size=153600

shopping.image.large.max.size=307200

A file extension of \* will permit all file extensions.

shopping.image.extensions=.gif,.jpeg,.jpg,.png

Configure email notification settings.

shopping.email.from.name=Joe Bloggs

shopping.email.from.address=test@liferay.com

shopping.email.order.confirmation.enabled=true

shopping.email.order.confirmation.subject=com/liferay/portlet/shopping/dependencies/email\_order\_confirmation\_subject.tmpl

shopping.email.order.confirmation.body=com/liferay/portlet/shopping/dependencies/email\_order\_confirmation\_body.tmpl

shopping.email.order.shipping.enabled=true

shopping.email.order.shipping.subject=com/liferay/portlet/shopping/dependencies/email\_order\_shipping\_subject.tmpl

shopping.email.order.shipping.body=com/liferay/portlet/shopping/dependencies/email\_order\_shipping\_body.tmpl

Set this to true to enable comments for shopping orders.

shopping.order.comments.enabled=true

#### Software Catalog Portlet

Set the maximum file size and max file dimensions for thumbnnails. A
value of 0 for the maximum file size can be used to indicate unlimited
file size. However, the maximum file size allowed is set in the property
*com.liferay.portal.upload.UploadServletRequestImpl.max.size*.

sc.image.max.size=307200

sc.image.thumbnail.max.height=200

sc.image.thumbnail.max.width=160

Set this to true to enable comments for software catalog products.

sc.product.comments.enabled=true

#### Tags Compiler Portlet

Set this to true to enable the ability to compile tags from the URL.
Disabling it can speed up performance.

tags.compiler.enabled=true

#### Tags Portlet

Input a class name that implements
*com.liferay.portlet.tags.util.TagsAssetValidator*. This class will be
called to validate assets. The `DefaultTagsAssetValidator`{.western}
class is just an empty class that doesn't actually do any validation.

The `MinimalTagsAssetValidator`{.western} requires all assets to have at
least one tag entry.

*Examples: *

tags.asset.validator=com.liferay.portlet.tags.util.DefaultTagsAssetValidator

\#tags.asset.validator=com.liferay.portlet.tags.util.MinimalTagsAssetValidator

Input a list of comma delimited default properties for new tag entries.
Each item of the list should have the following format: *0:key:value*

tags.properties.default=

Set the name of the default tag set where new tags are created by
default.

tags.vocabulary.default=Default Tag Set

#### Tasks Portlet

Specify the default number of approval stages.

tasks.default.stages=2

Specify the default role name for each stage of approval ordered from
lowest level of approval to highest. These Roles must have the
`APPROVE_PROPOSAL`{.western} permission.

tasks.default.role.names=Community Administrator,Community Owner

#### Translator Portlet

Set the default languages to translate a given text.

translator.default.languages=en\_es

#### Wiki Portlet

Set the URL of a page that contains more information about the classic
syntax of the wiki. It will be shown to the user when editing a page.

wiki.classic.syntax.help.url=http://wiki.liferay.com/index.php/Wiki\_Portlet

Set the name of the default page for a wiki node. The name for the
default page must be a valid wiki word. A wiki word follows the format
of having an upper case letter followed by a series of lower case
letters followed by another upper case letter and another series of
lower case letters. See
http://www.usemod.com/cgi-bin/wiki.pl?WhatIsaWiki for more information
on wiki naming conventions.

wiki.front.page.name=FrontPage

Set the name of the default node that will be automatically created when
the Wiki portlet is first used in a community.

wiki.initial.node.name=Main

Set the following property to specify the requirments for the names of
wiki pages. By default only a few characters are forbidden. Uncomment
the regular expression below to allow only CamelCase titles.

wiki.page.titles.regexp=([\^/\\\\[\\\\]%&?@]+)

\#wiki.page.titles.regexp=(((\\\\p{Lu}\\\\p{Ll}+)\_?)+)

Set the following property to specify the characters that will be
automatically removed from the titles when importing wiki pages. This
regexp should remove any characters that are forbidden in the regexp
specified in `wiki.page.titles.regexp`{.western}.

wiki.page.titles.remove.regexp=([/\\\\[\\\\]%&?@]+)

Set this to true to enable ratings for wiki pages.

wiki.page.ratings.enabled=true

Set this to true to enable comments for wiki pages.

wiki.page.comments.enabled=true

Set the list of supported wiki formats and the default wiki format.

wiki.formats=creole,html

wiki.formats.default=creole

Configure settings for each of the wiki formats.

\
\

wiki.formats.engine[creole]=com.liferay.portlet.wiki.engines.jspwiki.JSPWikiEngine

wiki.formats.configuration.main[creole]=jspwiki.properties

wiki.formats.edit.page[creole]=/html/portlet/wiki/edit/wiki.jsp

wiki.formats.help.page[creole]=/html/portlet/wiki/help/creole.jsp

wiki.formats.help.url[creole]=http://www.wikicreole.org/wiki/Creole1.0

\
\

wiki.formats.engine[html]=com.liferay.portlet.wiki.engines.HtmlEngine

wiki.formats.edit.page[html]=/html/portlet/wiki/edit/html.jsp

\
\

wiki.formats.engine[plain\_text]=com.liferay.portlet.wiki.engines.TextEngine

wiki.formats.edit.page[plain\_text]=/html/portlet/wiki/edit/plain\_text.jsp

Set the list of supported wiki importers.

wiki.importers=MediaWiki

Configure settings for each of the wiki importers.

wiki.importers.page[MediaWiki]=/html/portlet/wiki/import/mediawiki.jsp

wiki.importers.class[MediaWiki]=com.liferay.portlet.wiki.importers.mediawiki.MediaWikiImporter

Configure email notification settings.

wiki.email.from.name=Joe Bloggs

wiki.email.from.address=test@liferay.com

\
\

wiki.email.page.added.enabled=true

wiki.email.page.added.subject.prefix=com/liferay/portlet/wiki/dependencies/email\_page\_added\_subject\_prefix.tmpl

wiki.email.page.added.body=com/liferay/portlet/wiki/dependencies/email\_page\_added\_body.tmpl

wiki.email.page.added.signature=com/liferay/portlet/wiki/dependencies/email\_page\_added\_signature.tmpl

\
\

wiki.email.page.updated.enabled=true

wiki.email.page.updated.subject.prefix=com/liferay/portlet/wiki/dependencies/email\_page\_updated\_subject\_prefix.tmpl

wiki.email.page.updated.body=com/liferay/portlet/wiki/dependencies/email\_page\_updated\_body.tmpl

wiki.email.page.updated.signature=com/liferay/portlet/wiki/dependencies/email\_page\_updated\_signature.tmpl

wiki.rss.abstract.length=200

## Plugin Management

One of the primary ways of extending the functionality of Liferay Portal
is by the use of *plugins*. Plugins are an umbrella term for installable
*portlet*, *theme*, *layout template*, *hook*, *Ext*, and *web module*
Java EE .war files. Though Liferay comes bundled with a number of
functional portlets, themes, layout templates, hooks, and web modules,
plugins provide a means of extending Liferay to be able to do almost
anything.

### Portlets

Portlets are small web applications that run in a portion of a web page.
The heart of any portal implementation is its portlets, because all of
the functionality of a portal resides in its portlets. Liferay's core is
a portlet container. The container's job is to manage the portal's pages
and to aggregate the set of portlets that are to appear on any
particular page. This means that the core doesn't contain application
code. Instead, all of the features and functionality of your portal
application must reside in its portlets.

![image](../../images/portal-admin-ch6_html_5c790363.png) **Tip:** Liferay 4.4.2 and
below support the Portlet 1.0 standard: JSR-168. Liferay 5.0 and above
support the Portlet 2.0 standard: JSR-286. You cannot run Portlet 2.0
portlets in Liferay 4.4.2, but because the Portlet 2.0 standard is
backwards-compatible, portlets written to the 1.0 standard will run in
Liferay 5.x and above.

\
\

Portlet applications, like servlet applications, have become a Java
standard which various portal server vendors have implemented. The
JSR-168 standard defines the portlet 1.0 specification, and the JSR-286
standard defines the portlet 2.0 specification. A Java standard portlet
should be deployable on any portlet container which supports the
standard. Portlets are placed on the page in a certain order by the end
user and are served up dynamically by the portal server. This means that
certain “givens” that apply to servlet-based projects, such as control
over URLs or access to the `HttpServletRequest`{.western} object, don’t
apply in portlet projects, because the portal server generates these
objects dynamically.
Portal applications come generally in two flavors: 1) portlets can be
written to provide small amounts of functionality and then aggregated by
the portal server into a larger application, or 2) whole applications
can be written to reside in only one or a few portlet windows. The
choice is up to those designing the application. The developer only has
to worry about what happens inside of the portlet itself; the portal
server handles building out the page as it is presented to the user.

Most developers nowadays like to use certain frameworks to develop their
applications, because those frameworks provide both functionality and
structure to a project. For example, Struts enforces the
Model-View-Controller design pattern and provides lots of
functionality—such as custom tags and form validation—that make it
easier for a developer to implement certain standard features. With
Liferay, developers are free to use all of the leading frameworks in the
Java EE space, including Struts, Spring MVC, and Java Server Faces. This
allows developers familiar with those frameworks to more easily
implement portlets, and also facilitates the quick porting of an
application using those frameworks over to a portlet implementation.

Additionally, Liferay allows for the consuming of PHP and Ruby
applications as “portlets,” so you do not need to be a Java developer in
order to take advantage of Liferay's built-in features (such as user
management, communities, page building and content management). You can
also use scripting languages such as Groovy if you wish. You can use the
Plugins SDK to deploy your PHP or Ruby application as a portlet, and it
will run seamlessly inside of Liferay. We have plenty of examples of
this; to see them, check out the Plugins SDK from Liferay's public code
repository.

Does your organization make use of any Enterprise Planning (ERP)
software that exposes its data via web services? You could write a
portlet plugin for Liferay that can consume that data and display it as
part of a dashboard page for your users. Do you subscribe to a stock
service? You could pull stock quotes from that service and display them
on your page, instead of using Liferay's built-in Stocks portlet. Do you
have a need to combine the functionality of two or more servlet-based
applications on one page? You could make them into portlet plugins and
have Liferay display them in whatever layout you want. Do you have
existing Struts, Spring MVC, or JSF applications that you want to
integrate with your portal? It is a straightforward task to migrate
these applications into Liferay, and then they can take advantage of the
layout, security, and administration infrastructure that Liferay
provides.

### Themes

![image](../../images/portal-admin-ch6_html_m64a2dab6.png)*Illustration 1: Envision
theme from Liferay's theme repository*

\

Themes are hot deployable plugins which can completely transform the
look and feel of the portal. Most organizations have their own look and
feel standards which go across all of the web sites and web applications
in the infrastructure. Liferay makes it possible for a site designer to
create a theme plugin which can then be installed, allowing for the
complete transformation of the portal to whatever look and feel is
needed. There are lots of available theme plugins on Liferay's web site,
and more are being added every day. This makes it easier for those who
wish to develop themes for Liferay, as you can now choose a theme which
most closely resembles what you want to do and then customize it. This
is much easier than starting a theme from scratch. There is more about
theme development in *Liferay in Action*.

![image](../../images/portal-admin-ch6_html_m4080c054.png)*Illustration 2: Murali
theme from Liferay's theme repository*

### Layout Templates

Layout Templates are ways of choosing how your portlets will be arranged
on a page. They make up the body of your page, the large area where you
drag and drop your portlets to create your pages. Liferay Portal comes
with several built-in layout templates, but if you have a complex page
layout (especially for your home page), you may wish to create a custom
layout template of your own. This is covered in *Liferay in Action*.

### Hook Plugins

Hook plugins were introduced with Liferay 5.2. As the name implies, they
allow “hooking” into Liferay's core functionality. This means that they
enable developers to override or replace functionality that is in the
core of the system. You can hook into the eventing system, model
listeners, and portal properties. You can also override Liferay's core
JSPs with your own. Hooks are very powerful and have been designed to
replace most of the reasons for using the extension environment with
something that is easier to use and hot deployable.

### Web Plugins

Web plugins are regular Java EE web modules that are designed to work
with Liferay. Liferay supports integration with various Enterprise
Service Bus (ESB) implementations, as well as Single Sign-On
implementations, workflow engines and so on. These are implemented as
web modules that are used by Liferay portlets to provide functionality.

### Installing Plugins from Repositories

Liferay Portal has a section of the Control Panel which can handle
plugin installation: **Plugins Installation**, which is in the
**Server** category. This not only allows you to see what plugins are
installed in your portal, but also it enables you to run the search
indexer on those portlets that support it and install new portlets.

Go to the Dock and select *Control Panel*. In the Server category,
select *Plugins Installation*.

You should now see the screen which allows you to configure and install
portlets.

The default look of the Plugin Installer shows which plugins are already
installed on the system, whether it is active, and what Portal roles
have access to it.

![image](../../images/portal-admin-ch6_html_5b4a5b5c.png)\
*Illustration 3: Default plugin installer view*

\
\
If you would like to see what plugins are available, you can do so by
clicking the *Install More [Plugin Type]* button, where [Plugin Type]
changes based on which tab you are viewing. Please note that the machine
upon which Liferay is running must have access to the Internet in order
to be able to read the Official and Community repositories. If the
machine does not have access to the Internet, you will need to download
the plugins from the site and install them manually. We will discuss how
to do this later in this chapter.

From the initial page you can navigate to the different pages of
plugins, as they are all in alphabetical order. You can also change the
number of items per page and navigate to a specific page if you know
where a particular plugin appears in the list. This is a standard
feature of Liferay, and you will see it in most of Liferay's portlets.

![image](../../images/portal-admin-ch6_html_32aba477.png)\
*Illustration 4: Installing plugins*

After the *Install More [Plugin Type]* button is clicked, a new view
appears. This view has multiple tabs, and by default, displays the
*Portlet Plugins* tab. Note that the list displayed is a list of all of
the plugins that are available across all of the repositories to which
the server is subscribed. Above this is a search mechanism which allows
you to search for plugins by their name, by whether they are installed,
by tag, or by which repository they are in.
To install a plugin, choose the plugin by clicking on its name. For
example, if you want to use online web forms on your web site, you might
want to install the Web Form portlet. This portlet provides a handy
interface which allows you to create forms for users to fill out. The
results get emailed to an address which you specify.

Find the Web Form Portlet in the list by searching for it or browsing to
it. Once you have found it, click on its name. Another page will be
displayed which describes the portlet plugin in more detail. Below the
description is an *Install* button. Click this button to install your
plugin.

![image](../../images/portal-admin-ch6_html_5af016bf.png)\
*Illustration 5: Installing the Web Form plugin*

\
\
The plugin chosen will be automatically downloaded and installed on your
instance of Liferay. If you have the Liferay console open, you can view
the deployment as it happens. When it is finished, you should be able to
go back to the Add Application window and add your new plugin to a page
in your portal.

The same procedure is used for installing new Liferay Themes, Layout
Templates, hooks, and web modules. Instead of the *Portlet Plugins* tab,
you would use the appropriate tab for the type of plugin you wish to
install to view the list of plugins of that type. For themes, convenient
thumbnails (plus a larger version when you click on the details of a
particular theme) are shown in the list.

After clicking on the *Install* button for a theme, the theme becomes
available on the *Look and Feel* tab of any page.

### Installing Plugins Manually

Installing plugins manually is almost as easy as installing plugins via
the Plugin Installer. There are several scenarios in which you would
need to install plugins manually rather than from Liferay's
repositories:

-   Your server is firewalled without access to the Internet. This makes
    it impossible for your instance of Liferay to connect to the plugin
    repositories.

-   You are installing portlets which you have either purchased from a
    vendor, downloaded separately, or developed yourself.

-   For security reasons, you do not want to allow portal administrators
    to install plugins from the Internet before they are evaluated.

You can still use the Control Panel to install plugins that are not
available from the on line repositories. This is by far the easiest way
to install plugins.

If your server is firewalled, you will not see any plugins displayed in
the *Portlet Plugins* tab or in the* Theme Plugins* tab. Instead, you
will need to click the *Upload File* tab. This gives you a simple
interface for uploading a .war file containing a plugin to your Liferay
Portal.

Click the *Browse* button and navigate your file system to find the
portlet or theme .war you have downloaded. The other field on the page
is optional: you can specify your own context for deployment. If you
leave this field blank, the default context defined in the plugin (or
the .war file name itself) will be used.

That's all the information the Plugin Installer needs in order to deploy
your portlet, theme, layout template, hook, or web module. Click the
*Install* button, and your plugin will be uploaded to the server and
deployed. If it is a portlet, you should see it in the *Add Content*
window. If it is a theme, it will be available on the *Look and Feel*
tab in the page definition.

If you do not wish to use the Update Manager or Plugin Installer to
deploy plugins, you can also deploy them at the operating system level.
The first time Liferay starts, it creates a *hot deploy* folder which is
by default created inside the Liferay Home folder. This folder generally
resides one directory up from where your application server is
installed, though it sometimes is elsewhere depending on which
application server you are running. To find out where the Liferay Home
folder is for your application server, please see the section on your
server in Chapter 1. The first time Liferay is launched, it will create
a folder structure in Liferay Home to house various configuration and
administrative data. One of the folders it creates is called *deploy*.
If you copy a portlet or theme plugin into this folder, Liferay will
deploy it and make it available for use just as though you'd installed
it via the Plugin Installer in the Control Panel. In fact, this is what
the Plugin Installer is doing behind the scenes.

You can change the defaults for this directory structure so that it is
stored anywhere you like by modifying the appropriate properties in your
`portal-ext.properties`{.western} file. Please see the above section on
the `portal-ext.properties`{.western} file for more information.

To have Liferay hot deploy a portlet or theme plugin, copy the plugin
into your hot deploy folder, which by default is in
`[Liferay Home]/deploy`{.western}. If you are watching the Liferay
console, you should see messages like the following:

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

As long as you see the *available for use* message, your plugin was
installed correctly, and will be available for use in the portal.

### Plugin Troubleshooting

Sometimes for various reasons plugins fail to install. There are
different reasons for this based on several factors, including

-   Liferay configuration

-   The container upon which Liferay is running

-   Changing the configuration options in multiple places

-   How Liferay is being launched

You will often be able to tell if you have a plugin deployment problem
by looking at the Liferay server console. If you see the plugin get
recognized by the hot deploy listener, you will see a *plugin copied
successfully* message. If this message is not followed up by an
*available for use* message, you have an issue with your plugin
deployment configuration, and it is likely one of the factors above.

We will look at each of these factors.

#### Liferay Configuration Issues

![image](../../images/portal-admin-ch6_html_5c790363.png) **Tip:** This applies to
Liferay versions prior to version 4.3.5. Liferay versions above 4.3.5
are able to auto detect the type of server it is running on, which makes
things a lot easier. If you are running a newer version of Liferay, you
can skip this section. If you are upgrading from one of these versions,
continue reading.

Liferay by default comes as a bundle or as a .war file. Though every
effort has been made to make the .war file as generic as possible,
sometimes the default settings are inappropriate for the container upon
which Liferay is running. Most of these problems were resolved in
Liferay 4.3.5 with the addition of code that allows Liferay to determine
which application server it is running on and adjust the way it deploys
plugins as a result.
If you have upgraded from one of these older versions, you may still
have settings in your `portal.ext.properties `{.western}file that are no
longer needed. One of these settings is the manual override of the
default value of `auto.deploy.dest.dir`{.western}.

In versions of Liferay prior to 4.3.5, there is a property called
`auto.deploy.dest.dir`{.western} that defines the folder where plugins
are deployed after the hot deploy utilities have finished preparing
them. This folder maps to a folder that the container defines as an
auto-deploy or a hot deploy folder. By default in older versions of
Liferay, this property is set to `../webapps`{.western}. This default
value works for Tomcat containers (if Tomcat has been launched from its
*bin* folder), but will not work for other containers that define their
hot deploy folders in a different place. In newer versions of Liferay,
this value is automatically set to the default for the application
server upon which Liferay is running.

For example, Glassfish defines the hot deploy folder as a folder called
`autodeploy`{.western} inside of the domain folder in which your server
is running. By default, this is in
`<Glassfish Home>/domains/domain1/autodeploy`{.western}. JBoss defines
the hot deploy folder as a root folder inside of the particular server
configuration you are using. By default, this is in
`<JBoss Home>/server/default/deploy`{.western}. WebLogic defines this
folder inside of the domain directory. By default, this is in
`<Bea Home>/user_projects/domains/<domain name>/autodeploy`{.western}.

The best thing to do when upgrading to newer versions of Liferay Portal
is to remove this property altogether. It is not needed, as the
autodetection of the container handles the hot deploy location. If, for
whatever reason, you need to customize the location of the hot deploy
folder, follow the instructions below.

You will first need to determine where the hot deploy folder is for the
container you are running. Consult your product documentation for this.
Once you have this value, there are two places in which you can set it:
the `portal-ext.properties`{.western} file and in the Plugin Installer
portlet.

To change this setting in the `portal-ext.properties`{.western} file,
browse to where Liferay was deployed in your application server. Inside
of this folder should be a *WEB-INF/classes* folder. Here you will find
the `portal-ext.properties`{.western} file. Open this file in a text
editor and look for the property `auto.deploy.dest.dir`{.western}. If it
does not appear in the file, you can add it. The safest way to set this
property—as we will see later—is to define the property using an
absolute path from the root of your file system to your application
server's hot deploy folder. For example, if you are using Glassfish, and
you have the server installed in `/java/glassfish`{.western}, your
`auto.deploy.dest.dir`{.western} property would look like the following:

auto.deploy.dest.dir=/java/glassfish/domains/domain1/autodeploy

Remember, if you are on a Windows system, use forward slashes instead of
back slashes, like so:

auto.deploy.dest.dir=C:/java/glassfish/domains/domain1/autodeploy

Save the file and then restart your container. Now plugins should
install correctly.

If you would rather change this setting via the Plugin Installer in the
Control Panel (because you do not wish to restart your container), you
can do that by clicking on the *Configuration* tab. On this page are a
number of settings you can change, including the default folders for hot
deploy, where Liferay should look for plugin repositories, and so on.

![image](../../images/portal-admin-ch6_html_m6ef9ebaa.png)\
*Illustration 6: Changing the hot deploy destination directory*

The setting to change is the field marked *Destination Directory*.
Change this to the full path to your container's auto deploy folder from
the root of your file system. When you are finished, click the *Save*
button at the bottom of the form. The setting will now take effect
without your having to restart your container.
Note that the setting in the Control Panel overrides the setting in the
properties file.

If you are having hot deploy trouble in Liferay versions 4.3.5 and
greater, it is possible that the administrator of your application
server has changed the default folder for auto deploy in your
application server. In this case, you would want to set
`auto.deploy.dest.dir`{.western} to the customized folder location as
you would with older versions of Liferay. In Liferay 4.3.5 and greater,
this setting still exists, but is blank. Add the property to your
`portal-ext.properties`{.western} file and set its value to the fully
qualified path to the auto deploy folder configured in your application
server.

#### Deploy Issues for Specific Containers

There are some containers, such as WebSphere®, which do not have a hot
deploy feature. Unfortunately, these containers do not work with
Liferay's hot deploy system. But this does not mean that you cannot
install plugins on these containers. You can deploy plugins manually
using the application server's deployment tools. Liferay is able to pick
up the portlet plugins once they get deployed to the container manually,
especially if you add it to the same Enterprise Application project that
was created for Liferay.

When Liferay hot deploys portlet and theme .war files, it sometimes
makes modifications to those files right before deployment. In order to
successfully deploy plugins using an application server vendor's tools,
you will want to run your plugins through this process before you
attempt to deploy them.

In the **Plugin Installer** section of the Control Panel, click the
*Configuration *tab. The second-most field on the form is labeled
**Destination Directory*****. ***Place the path to which you would like
plugin .war files copied after they are processed by Liferay's plugin
installer process. You will use this as a staging directory for your
plugins before you install them manually with your server's deployment
tools. When you are finished, click *Save*.

Now you can deploy plugins using the Plugin Installer portlet or by
dropping .war files into your auto deploy directory. Liferay will pick
up the files, modify them, and then copy the result into the destination
directory you have configured. You may then deploy them from here to
your application server.

##### Example: WebSphere® Application Server

1.  If you don't have one already, create a
    `portal-ext.properties`{.western} file in the Liferay Home folder of
    your Liferay installation. Add the following directive to it:

    auto.deploy.dest.dir=${liferay.home}/websphere-deploy

2.  Create a folder called `websphere-deploy`{.western} inside your
    `$LIFERAY_HOME`{.western} folder. This is the folder where the
    Lucene index, Jackrabbit config, and deploy folders are.

3.  Make sure the `web.xml`{.western} file inside the plugin you want to
    install has the following context parameter in it:

<context-param\>

<param-name\>com.ibm.websphere.portletcontainer.PortletDeploymentEnabled</param-name\>

<param-value\>false</param-value\>

</context-param\>

Liferay versions 5.2.2 and higher will automatically inject this into
the `web.xml`{.western} file on WebSphere containers.

4.  The WebSphere deploy occurs in two steps. You will first use
    Liferay's tools to “pre-deploy” the file, and then use WebSphere's
    tools to do the actual deployment. This is because Liferay makes
    deployment-time modifications to the plugins right before they are
    actually deployed to the application server. For other application
    servers, this can usually be done in one step, because Liferay can
    make the modifications and then copy the resulting .war file into an
    autodeploy folder to have it actually deployed. Because WebSphere
    does not have an autodeploy feature, we need to separate these two
    steps.

5.  Deploy your .war file using Liferay's Plugin Installer or by copying
    it into `$LIFERAY_HOME/deploy`{.western}. Liferay will make its
    modifications and because we changed the
    `auto.deploy.dest.dir`{.western} in the first step, it will copy the
    resulting .war file into `$LIFERAY_HOME/websphere-deploy`{.western}.
    You will see a *copied successfully* message in the log.

6.  Use WebSphere's tools to deploy the .war file. Make the context root
    for the .war file equal to the file name (i.e.,
    `/my-first-portlet`{.western}). Once the .war file is deployed, save
    it to the master configuration.

7.  Go back to the *Applications -\> Enterprise Applications* screen in
    the WebSphere Admin Console. You will see that your portlet is
    deployed, but not yet started. Start it.

8.  Liferay will immediately recognize that the portlet has been
    deployed and register it. The portlet will be automatically started
    and registered upon subsequent restarts of WebSphere.

Experienced WebSphere system administrators can further automate this by
writing a script which watches the `websphere-deploy`{.western}
directory and uses `wsadmin`{.western} commands to then deploy plugins
automatically.

#### Changing the Configuration Options in Multiple Places

Sometimes, especially during development when several people have
administrative access to the server at the same time, the auto deploy
folder location can get customized in both the
`portal-ext.properties`{.western} file and in the Control Panel. If this
happens, the value in the Control Panel takes precedence over the value
in the properties file. If you go into the Control Panel and change the
value to the correct setting, plugin deployment will start working
again.

### Creating Your Own Plugin Repository

As your enterprise builds its own library of portlets for internal use,
you can create your own plugin repository to make it easy to install and
upgrade portlets. This will allow different departments who may be
running different instances of Liferay to share portlets and install
them as needed. If you are a software development house, you may wish to
create a plugin repository for your own products. Liferay makes it easy
for you to create your own plugin repository and make it available to
others.

You can create your plugin repository in two ways:

1.  Use the Software Catalog in the Control Panel to create the
    repository by using its graphical interface and an HTTP server.

2.  Create an XML file using the Liferay Plugin Repository DTD
    (`http://www.liferay.com/dtd/liferay-plugin-repository_6_0_0.dtd`{.western})
    and an HTTP server.

Both methods have their benefits. The first method allows users to
upload their plugins to an HTTP server to which they have access. They
can then register their plugins with the repository by adding a link to
it via the Control Panel's graphical user interface. Liferay will then
generate the XML necessary to connect the repository to a Control Panel
running on another instance of Liferay. This XML file can then be placed
on an HTTP server, and the URL to it can be added to the Plugin
Installer, making the portlets in this repository available to the
server running Liferay.

The second method does not require an instance of Liferay to be running.
You can upload plugins to an HTTP server of your choice, and then create
an XML file called `liferay-plugin-repository.xml`{.western} manually.
If you make this file available on an HTTP server (it can be the same
one upon which the plugins are stored, or a different one altogether),
you can connect the repository to a Plugin Installer in the Control
Panel running on an instance of Liferay.

We will first look at creating a plugin repository using the Software
Catalog in the Control Panel.

#### The Software Catalog

You will want to use the **Software Catalog** if you will have multiple
users submitting portlets into the repository, and if you don't want to
worry about creating the `liferay-plugin-repository.xml`{.western} file
yourself.

![image](../../images/portal-admin-ch6_html_m2cde1a0b.png)\
*Illustration 7: The Software Catalog with nothing installed*

Each community in your portal can have an instance of the Software
Catalog. The Control Panel will keep track of which community or
organization you are in and present you with the software catalog for
that community or organization. This means that different
communities/organizations can have different software repositories, so
you can host several software repositories on the same instance of
Liferay if you wish—they just have to be in different communities or
organizations.
Choose the community that will host the plugin repository and go to the
Control Panel. You will see at the top of the screen a message that says
“Content for [Organization/Community],” where [Organization/Community]
is the organization or community you were on when you selected the
Control Panel from the Dock. If you want to administer the software
catalog for a different community or organization, you can select it
from the selection box.

![image](../../images/portal-admin-ch6_html_8a11bd5.png)\
*Illustration 8: Populated Software Catalog from liferay.com*

\
\
The Software Catalog has several tabs. The first tab is labeled
*Products*. The default view of the portlet, when populated with
software, displays what plugins are available for install or download.
This can be seen in the version on Liferay's home page.

We will use an example community in order to better illustrate how to
use the Software Catalog portlet. Assume you, as the portal
administrator, have created a community called *Old Computers*. This
community will be a web site for users to collaborate on setting up and
using old computers with obsolete hardware and operating systems. Users
who participate in the site will eventually get upgraded to a more
privileged status and get their own blog page. To implement this, you
have created a My Summary portlet which displays the user's name,
picture, and description from his or her user profile. Because this
portlet is generic enough that it could be useful to anyone using
Liferay, you have decided to make it available in your own software
catalog.

The first step in adding a plugin to your software repository is to add
a *license* for your product. A license communicates to users the terms
upon which you are allowing them to download and use your software.
Click the *Licenses* tab and then click the *Add License* button that
appears. You will then see a form which allows you to enter the title of
your license, a URL pointing to the actual license document, and check
boxes denoting whether the license is open source, active, or
recommended.

When you have finished filling out the form, click the *Save* button.
Your license will be saved. Once you have at least one license in the
system, you can begin adding software products to your software catalog.
Click the *Products* tab, and then click the *Add Product* button.

Your next step will be to create the product record in the software
catalog. This will register the product in the software catalog and
allow you to start adding versions of your software for users to
download and / or install directly from their instances of Liferay. You
will first need to put the .war file containing your software on a web
server that is accessible without authentication to the users who will
be installing your software. In the example above, the *Old Computers*
site is on the Internet, so you would place the file on a web server
that is accessible to anyone on the Internet. If you are creating a
software catalog for an internal Intranet, you would place the file on a
web server that is available to anyone inside of your organization's
firewall.

To create the product record in the Software Catalog portlet, click the
*Products* tab, and then click the *Add Product* button. Fill out the
form with information about your product.

![image](../../images/portal-admin-ch6_html_6ce52af5.png)\
*Illustration 9: Adding a product to the Software Catalog*

\
\
**Name: **The name of your software product.

**Type:** Select whether this is a portlet, theme, layout template,
hook, or web plugin.

**Licenses:** Select the license(s) under which you are releasing this
software.

**Author:** Enter the name of the author of the software.

**Page URL:** If the software has a home page, enter its url here.

**Tags:** Enter any tags you would like added to this software.

**Short Description:** Enter a short description. This will be displayed
in the summary table of your software catalog.

**Long Description:** Enter a longer description. This will be displayed
on the details page for this software product.

**Permissions:** Click the *Configure* link to set permissions for this
software product.

**Group ID: **Enter a group ID. A group ID is a name space which usually
identifies the company or organization that made the software. For our
example, we will use *old-computers*.

**Artifact ID:** Enter an Artifact ID. The artifact ID is a unique name
within the name space for your product. For our example, we will use
*my-summary-portlet*.

**Screenshot:*** *Click the *Add Screenshot *button to add a screen shot
of your product for users to view.

When you have finished filling out the form, click the *Save* button.
You will be brought back to the product summary page, and you will see
that your product has been added to the repository.

Notice that in the version column, *N/A* is being displayed. This is
because there are not yet any released *versions* of your product. To
make your product downloadable, you need to create a version of your
product and point it to the file you uploaded to your HTTP server
earlier.

Before you do that, however, you need to add a *Framework Version* to
your software catalog. A Framework version denotes what version of
Liferay your plugin is designed for and works on. You cannot add a
version of your product without linking it to a version of the framework
for which it is designed.

Why is this so important? Because as Liferay gains more and more
features, you may wish to take advantage of those features in future
versions of your product, while still keeping older versions of your
product available for those who are using older versions of Liferay.
This is perfectly illustrated in the example My Summary portlet we are
using. Liferay had a My Summary portlet of its own, which does exactly
what we have described here. This portlet was added to the suite of
portlets which Liferay provides in the Social Networking plugin. This
plugin makes use of the many social networking features which have been
added to Liferay. So rather than just displaying a summary of your
information, the Social Networking portlet adds features such as status
updates, a “wall” for each user in his or her profile that other users
can “write” on, the ability to become “friends” with other users—thereby
granting them access to their profiles—and more.

None of this would work in older versions of Liferay, because the core
engine that enables developers to create features like this is not
there. So in this case, you would want to keep the older My Summary
portlet available for users who have not yet upgraded, and make the
newer social portlets available to those using latest version of
Liferay. This is what *Framework Versions* does for you. If you connect
to Liferay's software repositories with an old version of Liferay
Portal, you will see the My Summary portlet. If you connect to Liferay's
software repositories with new version of Liferay, you will see the
social portlets.

So click the *Framework Versions* tab and then click the *Add Framework
Version* button.

Give the framework a name, a URL, and leave the *Active* check box
checked. For our example, we have entered 6.0.3 for the name, because
our portlet should work on that version and higher, and
[http://www.](http://www.liferay.com/)[liferay](http://www.liferay.com/)[.com](http://www.liferay.com/)
for the URL. Click *Save. *

![image](../../images/portal-admin-ch6_html_5229bbcf.png)\
*Illustration 10: Adding a product version to the Software Catalog*

Now go back to the *Products* tab and click on your product. You will
notice that a message is displayed stating that the product does not
have any released versions. Click the *Add Product Version* button.
**Version Name:** Enter the version of your product.

**Change Log:** Enter some comments regarding what changed between this
version and any previous versions.

**Supported Framework Versions:** Select the framework version for which
your software product is intended. Enter a + at the end of the version
number if you want to specify a version plus any future versions.

**Download Page URL:** If your product has a descriptive web page, enter
its URL here.

**Direct Download URL (Recommended):** Enter a direct download link to
your software product here. The Plugin Installer portlet will follow
this link in order to download your software product.

**Include Artifact in Repository: **To enable others to use the Plugin
Installer portlet to connect to your repository and download your
plugin, select *yes* here.

When you are finished filling out the form, click the *Save* button.
Your product version will be saved, and your product will now be
available in the software repository.

##### Generating The Software Catalog

The Software Catalog works by generating an XML document which the
Plugin Installer reads. Using the data from this XML document, the
Plugin Installer knows where it can download the plugins from, what
version of Liferay the plugins are designed for, and all other data
about the plugins that have been entered into the Software Catalog
portlet.

In order to get your Software Catalog to generate this XML data, you
will need to access a particular URL. If you have created a friendly URL
for your community (for example, the default community, which is called*
guest,* has a friendly URL of `/guest`{.western} already configured for
it), you can use the friendly URL. If not, you will* *first need to know
the Group ID of the community in which your Software Catalog portlet
resides. You can do this by accessing the Manage Pages interface and
looking at the URLs for any of the pages. The URL will look something
like this: `http://localhost:8080/web/10148/1`{.western}.

Obviously, it is much easier if you are using Friendly URLs, and we
highly recommend that you do.

Next, go to your browser and go to the following URL:

http://<server name\>:<port number\>/software\_catalog?<Friendly URL
name or Group ID\>

For example, if you are on the same machine as your Liferay instance,
and that instance is running on port 8080, and your group ID from the
database is 10148, you would use the following URL:

http://localhost:8080/software\_catalog?10148

If you have also created a friendly URL called* old-computers *for this
organization or community, you would use the following URL:

http://localhost:8080/software\_catalog?old-computers

If you have configured everything properly, an XML document should be
returned:

<?xml version="1.0" encoding="UTF-8"?\>

<plugin-repository\>

<settings/\>

<plugin-package\>

<name\>My Summary</name\>

<module-id\>old-computers/my-summary-portlet/1.0/war</module-id\>

<modified-date\>Thu, 23 Apr 2009 20:40:16 +0000</modified-date\>

<types\>

<type\>portlet</type\>

</types\>

<tags\>

<tag\>social</tag\>

<tag\>profile</tag\>

</tags\>

<short-description\>My Summary</short-description\>

<long-description\>My Summary</long-description\>

<change-log\>Initial Version</change-log\>

<download-url\>[http://www.liferay.com/portlets/my-summary-portlet-6.0.4.war](http://www.liferay.com/portlets/my-summary-portlet-5.2.2.war)

</download-url\>

<author\>Rich Sezov</author\>

<screenshots/\>

<licenses\>

<license osi-approved="true"\>MIT License</license\>

</licenses\>

<liferay-versions/\>

</plugin-package\>

</plugin-repository\>

You can now give the URL to your software repository out on your
website, and other administrators of Liferay can enter it into the
Plugins Installation module of their Liferay Control Panels to connect
to your repository.

If you want to serve your repository off of a static web server, you can
save this document to a file called
`liferay-plugin-package.xml`{.western} and put this file on your HTTP
server. You can then give out the URL to the directory which holds this
file on your web site, and anyone with an instance of Liferay will be
able to point their Plugin Installer portlets to it.

##### Benefits of the Software Catalog

As you can see, the Software Catalog makes it easy for you to create a
repository of your software. Users of Liferay can configure their Plugin
Installers to attach to your repository, and the proper versions of your
software will be automatically made available to them by a single click.
This is by far the easiest way for you to keep track of your software,
and for your users to obtain your software.

Another benefit of the Software Catalog is that by using it, you make
available to your users a standard interface for manually downloading
your software. For those who prefer to manually download plugins, your
Software Catalog gives them an interface to go in, find your software
either by browsing or by searching, preview screen shots, and download
your software—and you don't have to build any of those pages yourself.
Simply configure your software in the portlet, and all of that is done
for you.

How can you do this? The Software Catalog is also available as a
portlet. You can add it to any page on your web site through the *Add
Application* menu. You will find the portlet in the *Tools* category.

#### Manually Creating A Software Catalog

If you do not wish to use the Control Panel to create your software
catalog, you can create it manually by manually typing out the XML file
that the Software Catalog section of the Control Panel would normally
generate. Note that if you do this, you will not be able to use the
Software Catalog portlet as a graphical user interface to your software
that end users can use to download your software manually: you will have
to build this yourself. Keep in mind that many instances of Liferay
Portal sit behind a firewall without access to the Internet. Because of
this, if you are making your software available to Internet users, some
of them will have to download it manually anyway, because their
installations are firewalled. In this case, the Software Catalog portlet
is the easiest way to provide a user interface for downloading your
software.

If you still wish to use a text editor to create your software catalog,
you can. To manually create a software catalog, obtain the DTD for the
XML file from Liferay's source code. You will find this DTD in the
*definitions* folder in the Liferay source. It is a file called
`liferay-plugin-package_6_0_0.dtd`{.western}. Use this DTD with a
validating XML editor (a good, free choice is jEdit with all the XML
plugins) to create your software catalog manually.

#### Connecting to a Software Catalog

If there is a software catalog of plugins that you would like to point
your instance of Liferay to, all you need is the URL to the catalog.
Once you have the URL, go to the Plugin Installer in your Control Panel
and click the *Configuration *tab. You will see that there are two
fields in which you can enter URLs to plugin repositories: *Trusted
Plugin Repositories* and *Untrusted Plugin Repositories*. Currently, the
only difference between the two is to provide a visual cue for
administrators as to which repositories are trusted and untrusted.

Enter the URL to the repository to which you wish to connect in one of
the fields and click *Save*. The portlet will connect to the repository,
and items from this repository will be shown in the list.

## Liferay Services Oriented Architecture

Liferay includes a utility called the *Service Builder* which is used to
generate all of the low level code for accessing resources from the
portal database. This utility is further explained in *Liferay in
Action*, but it is mentioned here because of its feature which generates
interfaces not only for Java code, but also for web services and
JavaScript. This means that the method calls for storing and retrieving
portal objects are all the same, and are generated in the same step.

*I![image](../../images/portal-admin-ch6_html_m1232b4ba.png)\
llustration 11: Liferay SOA's first layer of security. *

\
\
Because the actual method calls for retrieving data are the same
regardless of how one gets access to those methods (i.e., locally or
through web services), Liferay provides a consistent interface for
accessing portal data that few other products can match. The actual
interfaces for the various services are covered in *Liferay in Action*,
but before they can be used there are steps that need to be taken to
enable users to access those services remotely.

In the default `portal.properties`{.western} file, there is a section
called **Main Servlet**. This section defines the security settings for
all of the remote services provided by Liferay. Copy this section and
paste it into your custom `portal-ext.properties`{.western} file, and
you can configure security settings for the Axis Servlet, the Liferay
Tunnel Servlet, the Spring Remoting Servlet, the JSON Tunnel Servlet,
and the WebDAV servlet.

By default, a user connecting from the same machine Liferay is running
on can access remote services so long as that user has the permission to
use those services in Liferay's permissions system. Of course, you are
not really “remote” unless you are accessing services from a different
machine. Liferay has two layers of security when it comes to accessing
its services remotely. Without explicit rights to both layers, a remote
exception will be thrown and access to those services will not be
granted.

The first layer of security that a user needs to get through in order to
call a method from the service layer is servlet security. The *Main
Servlet* section of the `portal-ext.properties`{.western} file is used
to enable or disable access to Liferay's remote services. In that
section of the properties file, there are properties for each of
Liferay's remote services.

You can set each service individually with the security settings that
you require. For example, you may have a batch job which runs on another
machine in your network. This job looks in a particular shared folder on
your network and uploads documents to your community's document library
portlet on a regular basis, using Liferay's web services. To enable this
batch job to get through the first layer of security, you would modify
the `portal-ext.properties`{.western} file and put the IP address of the
machine on which the batch job is running in the list for that
particular service. For example, if the batch job uses the Axis web
services to upload the documents, you would enter the IP address of the
machine on which the batch job is running to the
`axis.servlet.hosts.allowed`{.western} property. A typical entry might
look like this:

axis.servlet.hosts.allowed=192.168.100.100, 127.0.0.1, SERVER\_IP

If the machine on which the batch job is running has the IP address
`192.168.100.100`{.western}, this configuration will allow that machine
to connect to Liferay's web services and pass in user credentials to be
used to upload the documents.

![image](../../images/portal-admin-ch6_html_57e67878.png)\
*Illustration 12: Liferay SOA's second layer of security. *

The second layer of security is Liferay's security model that it uses
for every object in the portal. The user ID that accesses the services
remotely must have the proper permission to operate on the objects it
will be accessing. Otherwise, a remote exception will be thrown. The
Portal Administrator will need to make use of Liferay's usual means of
granting access to these resources to the user ID that will be operating
on them remotely.
For example, say that a Document Library folder called *Documents* has
been set up in a community. A role has been created called *Document
Uploaders* which has the rights to add documents to this folder. Your
batch job will be accessing Liferay's web services in order to upload
documents into this folder. In order for this to work, you will have to
call the web service using a user ID that is a member of this group (or
that has individual rights to add documents to this folder). Otherwise,
you will be prevented from using the Web Service.

To call the web service using credentials, you would use the following
URL syntax:

http://" + userIdAsString + ":" + password +
"@<server.com\>:<port\>/tunnel-web/secure/axis/" + serviceName

The user ID is the user's ID from the Liferay database. This may be
obtained by logging in as the user and clicking *My Account* from the
Dock. In the top left corner of the portlet that appears is the user ID.

For example, to get Organization data using a user that has the ID of
*2* with a password of *test*, you would use the following URL:

http://2:test@localhost:8080/tunnel-web/secure/axis/Portal\_OrganizationService

It is important to note here how *Password Policies* (covered in Chapter
3) can be used in combination with this feature. If you are enforcing
password policies on your users (requiring them to change their
passwords on a periodic basis, etc.), any administrative ID which
accesses Liferay's web services in a batch job will have its password
expire too.

To prevent this from happening, you can add a new password policy which
does not enforce the password expiration and add your administrative
user ID to it. Then your batch job can run as many times as you need it
to, and the administrative ID's password will never expire.

In summary, accessing Liferay remotely requires the successful passing
of two security checks:

1.  The IP address must be pre-configured in the server's
    `portal-ext.properties`{.western} file.

2.  The user ID being used must have permission to access the resources
    it is attempting to access.

### Accessing Liferay's WSDL

After configuring the security settings properly, your first step in
obtaining access to remote web services is to access the WSDL. If you
are on a browser on the same machine Liferay is running on, you can do
this by accessing the following URL:

http://localhost:<port number\>/tunnel-web/axis

If, for example, you are running on Tomcat on port 8080, you would
specify this URL:

http://localhost:8080/tunnel-web/axis

If you are accessing a web service that was created as part of a portlet
plugin, the URL is similar, but uses the context of your application
rather than the tunnel-web servlet. You can get a list of your Service
Builder-generated WSDL documents by using the URL pattern below:

http://localhost:8080/your-portlet/axis

If you are on a different machine from the Liferay server, you will need
to pass in your user credentials on the URL to access the WSDL:

http://<user ID\>:<password\>@<server name\>:<port
number\>/tunnel-web/axis

In any case, once you successfully browse to this URL, you will see the
list of web services.

WSDL for each service is available by clicking on the *WSDL* link next
to the name of the service. There are many services; one for each of the
services available from the Liferay API.

Once you click on one of the *WSDL* links, the Web Service Definition
Language document will be displayed. This document can be used to
generate client code in any language that supports it. You can either
save the document to your local machine and then generate the client
code that way, or use your tool to trigger Liferay to generate the
document dynamically by using one of the URLs above.

For further information about developing applications that take
advantage of Liferay's remote services, please see *Liferay in Action*.

## Summary

This very long chapter covered a cornucopia of topics. First, we went
through all of the options which can be customized in your
`portal-ext.properties`{.western} file, exhaustively. This serves as a
reference section for the file, so you can quickly find documentation
for any property you might encounter.

Next, we took a good, long look at plugin management. Every aspect of
plugin management was covered, including plugin types, installing
plugins from a repository, installing plugins manually, troubleshooting,
creating your own plugin repository, and more.

After this, we covered how to configure Liferay's web services in a
secure way so that you can access the back end services you need. We
also saw how to access web services that your own developers have
created with Liferay's Service Builder utility.

Combined with the preceding chapters, the information you've gained so
far gives you the ability to competently install and configure a website
which runs on Liferay. Next, we'll take a look at some of the ways you
can configure Liferay for the enterprise.

\
\

