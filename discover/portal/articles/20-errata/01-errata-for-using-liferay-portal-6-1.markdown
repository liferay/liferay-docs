# Errata for Using Liferay Portal 6.1 [](id=errata-for-using-liferay-portal-6-1)

## Page 75: Propagating changes from site templates to sites [](id=page-75-propagating-changes-from-site-templates-to-sites)

In the *Propagating changes from site templates to sites* subsection of the
chapter *3.3 Using Site Templates* section, the following text clarifies how the
feature works and emphasizes its limitations:

**Important: ** Portlet data, related resources, and permissions on resources
are only copied from a site template to a site when that site is *first* created
based on the template. No changes made in a a template's portlet data, related
resources, or permissions are propagated to the site after the site is
created. Neither are such changes propagated to a site by *Reset* or *Reset and
Propagate* features. 

For example, consider a site template administrator including a Message Boards
portlet as part of a site template. She even creates Message Board categories
and configures permissions over the actions of the categories. The first time a
site is created based on the site template, the categories (portlet data) and
related permissions are copied to the site. If the site template administrator
adds, removes, or deletes some categories, however, such changes *aren't*
propagated to the site. 

## Page 97: Displaying Site Pages to mobile devices [](id=page-97-displaying-site-pages-to-mobile-devices)

In section *3.8 Displaying Site Pages to mobile devices* of Chapter 3, the
editions of the Device Recognition Provider application are now out of date. It
is now available only for Liferay Portal Enterprise Edition (EE). The
application is now named Device Recognition Provider EE. 

## Page 123: Microsoft Office Integration [](id=page-123-microsoft-office-integration)

The Microsoft Office integration features are supported only on Windows XP with
32-bit versions of Microsoft Internet Explorer and 32-bit versions of Microsoft
Office.

## Page 468: Xuggler configuration [](id=page-468-xuggler-configuration)

Since Liferay 6.1.1+, you can install Xuggler completely from the Control Panel.
Navigate to the *Server Administration* &rarr; *External Services* page. From
the dropdown, select the Xuggler `.jar` file that matches your operating system.
Then click on *Install*. 

Liferay versions 6.1.0 and earlier still requires manual configuration of
environment variables to use Xuggler. 

## Page 473: Installing Liferay on GlassFish 3 [](id=page-473-installing-liferay-on-glassfish-3)

The following sections are related to configuring Liferay Faces to function
properly, allowing JSF applications to successfully work in GlassFish 3.

### Clustering JSF Portlets in GlassFish 3 [](id=clustering-jsf-portlets-in-glassfish-3)

To successfully cluster JSF portlets on your GlassFish application server,
follow the instructions below:

1. Make sure you've created a JSF portlet project that can be deployed to
   Liferay.

2. Create a cluster and at least 3 instances in it. This is needed for GlassFish
   to ensure session replication when one node becomes unavailable.

3. As any other Liferay cluster installation, you have to set Liferay to work in
   a cluster. Visit the [Liferay Clustering](/discover/portal/-/knowledge_base/6-1/liferay-clustering)
   section for more info.

4. Now you'll prepare the Liferay WAR to be cluster-aware with session
   replication. First, you must update the `WEB-INF/web.xml` file inside the
   Liferay Portal WAR, adding the following element directly after the
   `<web-app/>` element:

        <distributable/>

5. Sometimes normal session replication in GlassFish doesn't work properly
   because of some control cookies validation. To ensure this works as it
   should, open the `WEB-INF/glassfish-web.xml` file and add the following:

        <session-config>
            <session-manager persistence-type="replicated">
                <manager-properties>
                    <property name="persistenceFrequency" value="web-method"/>
                    <property name="relaxCacheVersionSemantics" value="true"/>
                </manager-properties>
                <store-properties>
                    <property name="persistenceScope" value="session"/>
                </store-properties>
            </session-manager>
            <session-properties/>
            <cookie-properties/>
        </session-config>

    You can change the `persistenceFrequency` and `persistenceScope` values to
    fit your needs. For more information about replicated session persistence
    types in GlassFish, visit the following
    [site](https://docs.oracle.com/cd/E18930_01/html/821-2418/beaha.html#gdshu).
    For more information about GlassFish properties, visit the following
    [site](https://docs.oracle.com/cd/E18930_01/html/821-2417/beaum.html#SJSASEEADGbeaum).

6. Navigate to the GlassFish Admin Console and check *Enabled* next to
   *Availability*.

    ![Figure 1: Make sure to check the *Enabled* checkbox in the GlassFish Admin Console.](../../images/glassfish-jsf-clustering.png)

7. Copy your portlet WAR into the `deploy` folder in your `$LIFERAY_HOME`
   directory.

8. Repeat steps 1-7 for each of your JSF portlets.

By following these steps, you're able to cluster JSF portlets using the
GlassFish application server.

### Upgrading Mojarra in GlassFish 3 [](id=upgrading-mojarra-in-glassfish-3)

Some versions of GlassFish 3 are not bundled with the correct Mojarra version
necessary to use Liferay Faces. For example, GlassFish 3.1.2 comes with Mojarra
2.1.6 in the global classpath. Since Liferay Faces used Mojarra 2.1.21, you'll
need to download a newer all-in-one version that has the `jsf-api` and
`jsf-impl` combined into a single artifact named `javax.faces.jar`.

Download version 2.1.21 of the [`javax.faces.jar`](http://search.maven.org/#artifactdetails%7Corg.glassfish%7Cjavax.faces%7C2.1.21%7Cjar)
file and copy it into the following folder:

    $GLASSFISH_HOME/modules/javax.faces.jar

+$$$

**Note:** Sometimes it is necessary to update the timestamp of the file in the
file system in order for GlassFish to recognize that the file has been updated.
If the upgrade doesn't work at first, then run the Unix *touch* command to
update the timestamp of the file. After restarting GlassFish, the upgraded
version will be recognized.

$$$

If you'd like to verify that you're using the correct version of Mojarra at
runtime, download the following
[demo portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
and add it to a portal page. You should see a bulleted list of version info at
the bottom of the portlet.

![Figure 1: Make sure the Mojarra version displayed is the one you configured during the upgrade: Mojarra 2.1.21.](../../images/mojarra-version-verification.png)

Congratulations! You've officially upgraded your Mojarra version!

### Upgrading Weld in GlassFish 3 [](id=upgrading-weld-in-glassfish-3)

Some versions of GlassFish 3 are not bundled with the correct Weld version
necessary to use Liferay Faces. For example, GlassFish 3.1.2 comes with Weld
1.1.4.Final in the global classpath. Since Liferay Faces uses Weld 1.1.10,
you'll need to download a newer version of GlassFish. In order to upgrade Weld
in GlassFish, you'll need to upgrade to the OSGI bundle version of Weld.

Download version 1.1.10.Final of the
[`weld-osgi-bundle.jar`](http://search.maven.org/#artifactdetails%7Corg.jboss.weld%7Cweld-osgi-bundle%7C1.1.10.Final%7Cjar)
file and copy it into the following directory:

    $GLASSFISH_HOME/modules/weld-osgi-bundle.jar

+$$$

**Note:** Sometimes it is necessary to update the timestamp of the file in the
file system in order for GlassFish to recognize that the file has been updated.
If the upgrade doesn't work at first, then run the Unix *touch* command to
update the timestamp of the file. After restarting GlassFish, the upgraded
version will be recognized.

$$$

Additionally, you will need to upgrade to newer versions of the following jars:

- `weld-integration.jar`
- `weld-integration-fragment.jar`

You can find newer versions of these JARs in the ZIP file of [GlassFish 3.1.2.2](http://download.java.net/glassfish/3.1.2.2/release/glassfish-3.1.2.2.zip)
(81 MB) in the `glassfish/modules` folder.

If you'd like to verify that you're using the correct version of Weld at
runtime, download the following
[jsf2-cdi demo portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-cdi-portlet)
and add it to a portal page. You should see a bulleted list of version info at
the bottom of the portlet.

![Figure 2: Make sure the Weld version displayed is the one you configured during the upgrade: Weld OSGi Bundle 1.1.10.Final.](../../images/jsf2-cdi-portlet-weld-version.png)

You're now set to use JSF applications in your Liferay Portal instance running
on GlassFish 3!

## Page 492: Installing Liferay on JBoss 7 [](id=page-492-installing-liferay-on-jboss-7)

The following sections are related to configuring Liferay Faces to function
properly, allowing JSF applications to successfully work in JBoss 7.

### Upgrading Mojarra in JBoss 7 [](id=upgrading-mojarra-in-jboss-7)

Some versions of JBoss 7.1.x are not bundled with the correct Mojarra version
necessary to use Liferay Faces. For example, JBoss AS 7.1.1 comes with Mojarra
2.1.7 in the global classpath. Since Liferay Faces uses Mojarra 2.1.21, you'll
need to download a newer version of the `jsf-api.jar` and `jsf-impl.jar`
artifacts. 

1. Download
[`jsf-api-2.1.21.jar`](http://search.maven.org/#artifactdetails%7Ccom.sun.faces%7Cjsf-api%7C2.1.21%7Cjar)
and copy it to the following location: 

        $JBOSS_HOME/modules/javax/faces/api/main/jsf-api-2.1.21.jar

2. Open the `$JBOSS_HOME/modules/javax/faces/api/main/module.xml` file and
   comment out the reference to the version of the JAR that comes with the
   server. For example: 

        <!-- <resource-root path="jboss-jsf-api_2.1_spec-2.0.1.Final.jar"/> -->

3. Add a reference to the new JAR in the same `module.xml` file: 

        <resource-root path="jsf-api-2.1.21.jar"/>

4. Add the following module to the `<dependencies>` section: 

        <dependencies>
            ...
            <module name="com.sun.jsf-impl"/>
        </dependencies>

5. Download
[`jsf-impl-2.1.21.jar`](http://search.maven.org/#artifactdetails%7Ccom.sun.faces%7Cjsf-impl%7C2.1.21%7Cjar)
and copy it to the following location: 

        $JBOSS_HOME/modules/com/sun/jsf-impl/main/jsf-impl-2.1.21.jar

6. Open the `$JBOSS_HOME/modules/com/sun/jsf-impl/main/module.xml` file and
   comment out the reference to the version of the JAR that comes with the
   server. For example: 

        <!-- <resource-root path="jsf-impl-2.1.7-jbossorg-2.jar"/> -->

7. Add a reference to the new JAR in the same `module.xml` file: 

        <resource-root path="jsf-impl-2.1.21.jar"/>

Congratulations! You've officially upgraded your Mojarra version! If you'd like
to verify that you're using the correct version of Mojarra at runtime, download
the following [demo portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
and add it to a portal page. You should see a bulleted list of version info at
the bottom of the portlet.

![Figure 1: Make sure the Mojarra version displayed is the one you configured during the upgrade: Mojarra 2.1.21.](../../images/jboss-mojarra-upgrade.png)

Congratulations! You've officially upgraded your Mojarra version!

### Upgrading Weld in JBoss 7 [](id=upgrading-weld-in-jboss-7)

Some versions of JBoss 7.1.x are not bundled with the correct Weld version
necessary to use Liferay Faces. For example, JBoss AS 7.1.1 comes with Weld
1.1.5 in the global classpath. Since Liferay Faces uses Weld 1.1.10, you'll
need to download a newer version of the `weld-core.jar` artifact. 

1. Download
[`weld-core-1.1.10.Final.jar`](http://search.maven.org/#artifactdetails%7Corg.jboss.weld%7Cweld-core%7C1.1.10.Final%7Cjar)
and copy it to the following location: 

        $JBOSS_HOME/modules/org/jboss/weld/core/main/weld-core-1.1.10.Final.jar

2. Open the `$JBOSS_HOME/modules/org/jboss/weld/core/main/module.xml` file and
   comment out the reference to the version of the JAR that comes with the
   server. For example: 

        <!-- <resource-root path="weld-core-1.1.5.AS71.Final.jar"/> -->

3. Add a reference to the new JAR in the same `module.xml` file: 

        <resource-root path="weld-core-1.1.10.Final.jar"/>

You're now set to use JSF applications in your Liferay Portal instance running
on JBoss 7!

## Page 504: Installing Liferay on Resin 4 [](id=page-504-installing-liferay-on-resin-4)

The following section is related to configuring Liferay Faces to function
properly, allowing JSF applications to successfully work in Resin 4.

### Upgrading Mojarra in Resin 4 [](id=upgrading-mojarra-in-resin-4)

Some versions of Resin 4 are not bundled with the correct Mojarra version
necessary to use Liferay Faces. For example, Resin 4.0.33 comes with Mojarra
2.1.15 in the global classpath. Since Liferay Faces uses Mojarra 2.1.21, you'll
need to download a newer version of the `jsf-api.jar` and `jsf-impl.jar`
artifacts. 

1. Delete the Mojarra all-in-one JAR located in the following directory:

    $RESIN_HOME/lib/javax.faces-2.1.15.jar

2. Download the following two JARs and copy them into the `$RESIN_HOME/lib`
   directory.

    - [`jsf-api-2.1.21.jar`](http://search.maven.org/#artifactdetails%7Ccom.sun.faces%7Cjsf-api%7C2.1.21%7Cjar)
    - [`jsf-impl-2.1.21.jar`](http://search.maven.org/#artifactdetails%7Ccom.sun.faces%7Cjsf-impl%7C2.1.21%7Cjar)

You're now set to use JSF applications in your Liferay Portal instance running
on Resin 4!

## Page 507: Installing Liferay on Tomcat 7 [](id=page-507-installing-liferay-on-tomcat-7)

It is recommended to also add the `support-tomcat.jar` file to the
`$TOMCAT_HOME/lib/ext` folder of your Tomcat installation. The file can be
downloaded from
[search.maven.org/remotecontent?filepath=com/liferay/portal/support-tomcat/6.1.2/support-tomcat-6.1.2.jar](search.maven.org/remotecontent?filepath=com/liferay/portal/support-tomcat/6.1.2/support-tomcat-6.1.2.jar).
This `JAR` provides classes that extend some Tomcat-specific classes in order to
support Liferay's runtime.

## Page 508: Installing Liferay on Tomcat 7 [](id=page-508-installing-liferay-on-tomcat-7)

The note explaining that Tomcat 6 users should not copy the `ccpp.jar` is no
longer applicable to this section. The runtime error caused by this `JAR` was
resolved in the release of Liferay 6.0.

## Page 512: Installing Liferay on Tomcat 7 [](id=page-512-installing-liferay-on-tomcat-7)

The following section is related to configuring Liferay Faces to function
properly, allowing JSF applications to successfully work in Tomcat 7.

### Adding Mojarra to Tomcat 7 [](id=adding-mojarra-to-tomcat-7)

If you'd like to use JSF applications in your Tomcat application server, you'll
need to add Mojarra. If you do not plan on using JSF applications in your
application server, you can skip this section. 

The typical binary ZIP version of Tomcat does not contain any JSF runtime JARs
like Mojarra or MyFaces. This is because Tomcat is not a Java EE Application
Server like Oracle GlassFish, Oracle WebLogic, JBoss AS, or IBM WebSphere. Also,
Tomcat is not a Java EE Web Profile Server like Apache TomEE or Caucho Resin. 

There are two ways to approach using Mojarra with Tomcat: upgrading Tomcat's
context classpath or upgrading Tomcat's global classpath. Both methods require
adding/editing two JARs, which can be downloaded below: 

- [`jsf-api`](https://maven.java.net/content/repositories/releases/com/sun/faces/jsf-api/2.1.21/)
- [`jsf-impl`](https://maven.java.net/content/repositories/releases/com/sun/faces/jsf-impl/2.1.21/)

The typical approach for using Mojarra with Tomcat is to simply include
`jsf-api.jar` and `jsf-impl.jar` in the `WEB-INF/lib` folder in each JSF
project. You can do this by specifying the `jsf-api` and `jsf-impl` artifacts
without a scope, or with the scope set as `compile` (the default) in each JSF
project: 

    <dependency>
        <groupId>com.sun.faces</groupId>
        <artifactId>jsf-api</artifactId>
        <version>2.1.21</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>com.sun.faces</groupId>
        <artifactId>jsf-impl</artifactId>
        <version>2.1.21</version>
        <scope>compile</scope>
    </dependency>

Although it is possible to install Mojarra in the Tomcat global classpath, it
will not work properly without some small modifications to the `jsf-impl.jar`
dependency. The problem stems from the fact that the Mojarra
[`ConfigureListener`](https://svn.java.net/svn/mojarra~svn/tags/2.1.21/jsf-ri/src/main/java/com/sun/faces/config/ConfigureListener.java)
class is automatically registered for all contexts under `tomcat/webapps`
because it is specified as a `<listener>` in the
[META-INF/jsf-jsf_core.tld](https://svn.java.net/svn/mojarra~svn/tags/2.1.21/jsf-ri/conf/share/jsf_core.tld)
descriptor inside the `jsf-impl.jar` dependency.
Additionally, the
[META-INF/services/javax.faces.ServletContainerInitializer](https://svn.java.net/svn/mojarra~svn/tags/2.1.21/jsf-ri/conf/share/javax.servlet.ServletContainerInitializer)
will cause the
[FacesInitializer](https://svn.java.net/svn/mojarra~svn/tags/2.1.21/jsf-ri/src/main/java/com/sun/faces/config/FacesInitializer.java)
class to auto-register the `ConfigureListener` as well. Consequently, every
request issued in all contexts will invoke the Mojarra `ConfigureListener`. This
can be a potential performance problem in a webapp environment, and causes
incompatibilities with a portlet environment. Therefore, it is necessary to
disable automatic registration of the Mojarra `ConfigureListener` by modifying
the contents of the `jsf-impl.jar` dependency. 

To upgrade Tomcat's global classpath, follow the steps below: 

1. Copy `jsf-api.jar` and `jsf-impl.jar` to the `tomcat/lib` folder. 

2. Open a terminal window and navigate to the `tomcat/lib` folder: 

        cd tomcat/lib

3. Create a temporary folder named `jsf-impl` and navigate into it: 

        mkdir jsf-impl
        cd jsf-impl

4. Extract the Mojarra `jsf-impl.jar` dependency into the temporary folder: 

        jar xf ../jsf-impl-2.1.21.jar

5. Open the `META-INF/jsf_core.tld` file and remove the following lines: 

        <listener>
            <listener-class>com.sun.faces.config.ConfigureListener</listener-class>
        </listener>

6. Remove the Mojarra servlet container initializer: 

        rm META-INF/services/javax.servlet.ServletContainerInitializer

7. Overwrite the Mojarra `jsf-impl.jar` dependency by creating a new archive: 

        jar cf ../jsf-impl-2.1.21.jar META-INF/ com/

8. Remove the temporary folder: 

        cd ../
        rm -rf jsf-impl/

9. Follow only *one* of the following sub-steps below, depending on preference. 

    9.1 Specify the `liferay-faces-init.jar` dependency in each JSF project in
    order for the Mojarra `ConfigureListener` to be automatically started by
    Tomcat:

        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>liferay-faces-init</artifactId>
            <version>3.1.3-ga4</version>
        </dependency>

    9.2 Specify the Mojarra `ConfigureListener` as a listener in the
    `WEB-INF/web.xml` descriptor in each JSF project: 

        <listener>
            <listener-class>com.sun.faces.config.ConfigureListener</listener-class>
        </listener>

10. Specify the `jsf-api` and `jsf-impl` dependencies as provided in each JSF
   project: 

        <dependency>
            <groupId>com.sun.faces</groupId>
            <artifactId>jsf-api</artifactId>
            <version>2.1.21</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.sun.faces</groupId>
            <artifactId>jsf-impl</artifactId>
            <version>2.1.21</version>
            <scope>provided</scope>
        </dependency>

You've officially added Mojarra to your application server.

## Page 525: Installing Liferay on Oracle WebLogic 10.3 [](id=page-525-installing-liferay-on-oracle-weblogic-10-3)

The following sections are related to configuring Liferay Faces to function
properly, allowing JSF applications to successfully work in Oracle WebLogic
10.3 (11g).

### Configuration for Deploying JSF Portlets on WebLogic 10.3 [](id=configuration-for-deploying-jsf-portlets-on-weblogic-10-3)

Complete the following instructions to ensure JSF applications deploy
successfully using your WebLogic application server.

1. To avoid a `ViewExpiredException` with Ajax, disable the Liferay Portal
`ETagFilter` by adding the following property in the `portal-ext.properties`
file: 

        com.liferay.portal.servlet.filters.etag.ETagFilter=false

    For more information on this exception, refer to
    [FACES-1591](https://issues.liferay.com/browse/FACES-1591).

2. You'll need to adjust your memory settings for your environment variables.
   For your memory settings to be permanently set, they need to be hard-coded in
   the `startWebLogic.sh` script. Just above the definition of your home domain,
   add the following lines:

        export MW_HOME=$HOME/Oracle/Middleware
        export USER_MEM_ARGS="-Xms512m -Xmx1024m -XX:CompileThreshold=8000 -XX:PermSize=128m -XX:MaxPermSize=256m"

    Note that if you have many portlet WAR modules, you may need to increase
    memory. For example, the following lines reserves double the amount of
    memory: 

        export MW_HOME=$HOME/Oracle/Middleware
        export USER_MEM_ARGS="-Xms1024m -Xmx2048m -XX:CompileThreshold=8000 -XX:PermSize=256m -XX:MaxPermSize=512m"

3. Due to a deficiency in the XML parser that ships with WebLogic, it is
   necessary to include a custom [Apache Xerces](http://xerces.apache.org/)
   parser as a dependency. In order to include it in the proper position within
   the WebLogic classpath, the Xerces JARs are included in the Mojarra Shared
   Library. Therefore, it is not necessary to add Xerces as a dependency in the
   portlet's `WEB-INF/lib` folder.

    In order to ensure that WebLogic will always invoke the Xerces SAXParser
    implementation before others, add the following to the top of the
    `startWebLogic.sh` script:

        export JAVA_OPTIONS="$JAVA_OPTIONS -Djavax.xml.parsers.SAXParserFactory=org.apache.xerces.jaxp.SAXParserFactoryImpl"

    For more information, visit
    [FACES-1598](https://issues.liferay.com/browse/FACES-1598).

4. If you're running the JSR 329 Portlet Bridge TCK, you'll need to include the
   `trinidad-api.jar` and `trinidad-impl.jar` dependencies in the global
   classpath (within the `lib` folder). For more information, see
   [FACES-1599](https://issues.liferay.com/browse/FACES-1599).

5. In order for JSF 2.1 portlets to deploy correctly in WebLogic, the
   `WEB-INF/weblogic.xml` descriptor must be configured to fine-tune how class
   loading takes place. For a working example, please refer to the
   [weblogic.xml](https://github.com/liferay/liferay-faces/blob/3.2.x/demos/bridge/jsf2-portlet/src/main/webapp/WEB-INF/weblogic.xml)
   descriptor from a demo JSF portlet. 

6. If you're using RichFaces, the following JARs must be present in the global
   classpath in order for it to work properly:

    - `guava.jar`
    - `richfaces-components-api.jar`
    - `richfaces-core-api.jar`

    These can either be copied to `weblogic/lib`, or deployed as a Shared
    Library, such as `richfaces-shared-library` that can be built from the
    Liferay Faces source. You'll learn how to build from Liferay Faces Source
    later on.

7. Aside from the RichFaces JARs that must live in the global classpath, all
   other RichFaces dependencies may exist in `WEB-INF/lib`.

    In order to have a portlet WAR utilize the RichFaces Shared Library, it must
    include the following in `WEB-INF/weblogic.xml`:

        <wls:library-ref>
            <wls:library-name>richfaces</wls:library-name>
        </wls:library-ref>

8. If using ICEfaces or PrimeFaces, the `icefaces.jar` and `primefaces.jar`
   dependencies may exist in the `WEB-INF/lib` directory.

As mentioned before, the next thing you'll need to do to ensure successful
deployment of JSF applications is build the necessary Shared Libraries from the
Liferay Faces source. To complete this, follow the instructions below:

1. It is necessary to build the Shared Libraries from the Liferay Faces source.
   However, the source will not build properly until the WebLogic Injection
   Provider for Mojarra is manually installed into your local
   `$HOME/.m2/repository` folder. Locate the out-of-the-box `jsf-2.0.war`
   artifact, typically located in the
   `Oracle/Middleware/wlserver/common/deployable-libraries` directory.

2. Extract the jsf-2.0.war artifact into a temporary folder:

        cd $MW_HOME/Oracle/Middleware/wlserver/common/deployable-libraries
        mkdir temp
        cd temp
        jar xvf ../jsf-2.0.war

3. Manually install the WebLogic Injection Provider for Mojarra
   (`wls.jsf.di.jar`) into your local `$HOME/.m2/repository` folder.
   For example, if using Oracle WebLogic version 10.3.6.0, type:

        mvn install:install-file -Dfile=WEB-INF/lib/wls.jsf.di.jar -DgroupId=com.oracle.weblogic -DartifactId=wls.jsf.di -Dpackaging=jar -DgeneratePom=true -Dversion=10.3.6.0

4. Build the Shared Libraries from the Liferay Faces source:

        cd liferay-faces/support
        mvn -P weblogic clean package

5. Verify that the Shared Libraries have been built by Maven. For example, the
   following WAR artifacts should exist:

    - `servers/weblogic/jsf-shared-library/target/jsf-shared-library-3.2.4-ga5.war`
    - `servers/weblogic/jstl-shared-library/target/jstl-shared-library-3.2.4-ga5.war`
    - `servers/weblogic/richfaces-shared-library/target/richfaces-shared-library-3.2.4-ga5.war`

Excellent! You've configured your WebLogic application server to successfully
deploy and run JSF applications on Liferay Portal.

### Upgrading Mojarra on Oracle WebLogic 10.3 in Webapps Environment [](id=upgrading-mojarra-on-oracle-weblogic-10-3-in-webapps-environment)

If you're upgrading Mojarra while running in a *webapp* environment, follow the
instructions below. If you're running in a portlet environment, follow the next
section's set of instructions.

Oracle WebLogic 10.3 is a Java EE 5 (Servlet 2.5) application server, which
means that it must include the JSF API and an implementation of JSF. Other
servers in like GlassFish and JBoss ship with Mojarra in the global classpath,
but this is not the case with WebLogic 10.3.x. Instead, Mojarra must be
installed as a Shared Library WAR via the WebLogic Console. The version of
Mojarra that comes with, for example, WebLogic 10.3.6.0 is version 2.0.4,. New
applications will require JSF 2.1/2.2 which will make it necessary to upgrade
Mojarra.

To upgrade Mojarra, follow the instructions below:

1. Locate the out-of-the-box `jsf-2.0.war` artifact, typically located in the
`Oracle/Middleware/wlserver/common/deployable-libraries` directory.

2. Extract the `jsf-2.0.war` artifact into a folder named `jsf-2.1`.

        cd $MW_HOME/Oracle/Middleware/wlserver/common/deployable-libraries
        mkdir jsf-2.1
        cd jsf-2.1
        jar xvf ../jsf-2.0.war

3. Remove the old version of the JSF API and the Mojarra Implementation. For
   example:

        rm WEB-INF/lib/javax.jsf_1.0.0.0_2-0.jar
        rm WEB-INF/lib/glassfish.jsf_1.0.0.0_2-0-4.jar

4. Remove the old version of the JSTL from the `WEB-INF/lib` directory, since
   it's not required for Facelets.

5. Download the following Mojarra 2.1.21 dependencies and place them in the
   `WEB-INF/lib` folder:

    - [`jsf-api-2.1.21.jar`](http://search.maven.org/remotecontent?filepath=com/sun/faces/jsf-api/2.1.21/jsf-api-2.1.21.jar)
    - [`jsf-impl-2.1.21.jar`](http://search.maven.org/remotecontent?filepath=com/sun/faces/jsf-impl/2.1.21/jsf-impl-2.1.21.jar)

6. Create a new `META-INF/MANIFEST.MF` file with the following contents:

        Manifest-Version: 1.0
        Extension-Name: jsf
        Specification-Title: JavaServer Faces
        Specification-Version: 2.1
        Implementation-Title: Mojarra
        Implementation-Vendor: Oracle Corporation
        Implementation-Version: 2.1.21

    The following diagram shows the folder hierarchy of your JSF implementation,
    including the WebLogic Injection Provider you included in the *Configuration
    for Deploying JSF Portlets on WebLogic 10.3* section.

    ![Figure 3: Make sure your folder structure appears at the figure suggests.](../../images/jsf-2.1-folder-hierarchy.png)

7. Create a new WAR archive using the *jar* command with *M* command-line option,
   which retains the` META-INF/MANIFEST.MF` file from Step 6 (rather than
   generating a new one).

        jar cMf ../jsf-2.1.war META-INF/ WEB-INF/

8. Login to the WebLogic Console application and, in the *Domain Structure*
   menu, select *Deployments*. Then click *Install*.

9. In the *Path* textbox, enter the full path to the new `jsf-2.1.war` archive
   that was created in Step 7 and click the *Next* button.

10. Select *Install this deployment as a library* and click the *Next* button.

11. Verify that the Specification Version is *2.1* and that the Implementation
   Version is *2.1.21* and click the *Next* button.

12. Click *Finish* and then click *Save*.

13. Verify that your `WEB-INF/weblogic.xml` file contains the following
   `<wls:library-ref>` element:

        <wls:library-ref>
            <wls:library-name>jsf</wls:library-name>
        </wls:library-ref>

    Make sure to add this code if your `weblogic.xml` file does not contain the
    above code. In order to have a portlet WAR utilize the Mojarra Shared
    Library, this element must be present.

Your WebLogic application server's Mojarra instance is now upgraded in your
webapp environment.

### Upgrading Mojarra on Oracle WebLogic 10.3 in Portlet Environment [](id=upgrading-mojarra-on-oracle-weblogic-10-3-in-portlet-environment)

If you're upgrading Mojarra while running in a *portlet* environment, follow the
instructions below. If you're running in a webapp environment, follow the 
section above this set of instructions.

Oracle WebLogic 10.3. is a Java EE 5 (Servlet 2.5) application server, which
means that it must include the JSF API and an implementation of JSF. Other
servers like GlassFish and JBoss AS ship with Mojarra in the global classpath,
but this is not the case with WebLogic 10.3.x. Instead, Mojarra must be
installed as a Shared Library WAR via the WebLogic Console. The version of
Mojarra that comes with, for example, WebLogic 10.3.6.0 is version 2.0.4,
whereas Liferay Faces requires Mojarra 2.1. Therefore it is necessary to upgrade
Mojarra in WebLogic by creating a new Shared Library WAR with the updated
dependencies.

The source code of the Liferay Faces project contains some sub-projects that produce Shared Libraries for supporting WebLogic:

- `jsf-shared-library.war`
- `jstl-shared-library.war`
- `richfaces-shared-library.war`

However, the WAR artifacts for these Shared Libraries are not distributed to
Maven Central when a release of Liferay Faces is performed. Instead, they must
be built manually from the command-line. 

1. Locate the out-of-the-box `jsf-2.0.war` artifact, typically located in the
`Oracle/Middleware/wlserver/common/deployable-libraries` directory.

2. Extract the `wls.jsf.di.jar` (WebLogic Inject Provider) from the
   `jsf-2.0.war` artifact:

        export MW_HOME=$HOME/Oracle/Middleware
        cd $MW_HOME/wlserver/common/deployable-libraries
        jar xf jsf-2.0.war WEB-INF/lib/wls.jsf.di.jar

3. Install the `wls.jsf.di.jar` dependency in your local Maven repository. For
   example:

        mvn install:install-file -Dfile=WEB-INF/lib/wls.jsf.di.jar -DgroupId=com.oracle.weblogic -DartifactId=wls.jsf.di -Dpackaging=jar -DgeneratePom=true -Dversion=10.3.6.0

4. Make sure you've built the Shared Libraries, which were completed in the
   second set of instructions in the *Configuration for Deploying JSF Portlets
   on WebLogic 10.3* section.

5. Login to the WebLogic Console application and, in the *Domain Structure*
   menu, select *Deployments*. Then click *Install*.

6. In the *Path* textbox, enter the full path to the new
   `jsf-shared-library.war` archive that was created in Step 1 and click the
   *Next* button.

7. Select *Install this deployment as a library* and click the *Next* button.

8. Verify that the Specification Version and Implementation Version are correct
   and click the *Next* button.

9. Click *Finish* and then click *Save*.

10. In order to avoid the ViewExpiredException documented in
   [FACES-1591](https://issues.liferay.com/browse/FACES-1591), copy the Mojarra
   `jsf-api.jar` dependency to the `weblogic/lib` folder.

11. Verify that your `WEB-INF/weblogic.xml` file contains the following
   `<wls:library-ref>` element:

        <wls:library-ref>
            <wls:library-name>jsf</wls:library-name>
        </wls:library-ref>

    Make sure to add this code if your `weblogic.xml` file does not contain the
    above code. In order to have a portlet WAR utilize the Mojarra Shared
    Library, this element must be present.

Your WebLogic application server's Mojarra instance is now upgraded in your
portlet environment.

## Page 536: Installing Liferay on WebSphere 8.0 [](id=page-536-installing-liferay-on-websphere-8-0)

The following sections are related to configuring Liferay Faces to function
properly, allowing JSF applications to successfully work in WebSphere 8.0.

### Configuration for Deploying JSF Portlets on WebSphere 8.0 [](id=configuration-for-deploying-jsf-portlets-on-websphere-8-0)

With servlet containers like Tomcat/Resin/Jetty and application servers like
JBoss/GlassFish, deploying a portlet is as simple as copying the WAR artifact to
the Liferay home's `deploy` folder. However, Liferay's integration with
WebSphere requires a slightly different setup. If you don't have one already,
create a `portal-ext.properties` file in the Liferay home folder, and add the
following property:

    auto.deploy.dest.dir=${resource.repositories.root}/websphere-deploy

Then, when you deploy a portlet WAR artifact to the `deploy` folder, Liferay
Portal will extract the WAR and modify descriptors like `WEB-INF/web.xml` before
it re-zips the WAR and copies it to the `websphere-deploy` folder. Liferay
Portal will then detect the modified WAR and auto-register the portlets with
WebSphere. JSF portlets require some additional steps before the WAR context
will startup properly.

1. In the WebSphere Admin Console, click *Applications* &rarr; *Application
   Types* &rarr; *WebSphere Enterprise Applications*.

2. Click on the hyperlink for the JSF portlet you'd like to deploy.

3. Click on the *Class loading and update detection* link located within the
   Detail Properties heading.

    ![Figure 1: In general, this page helps you configure enterprise applications.](../../images/websphere-class-loading.png)

4. Select the *Classes loaded with local class loader first (parent last)* and
   *Class loader for each WAR file in application* radio selections. Then click
   *OK*.

    ![Figure 2: These two radio options are listed under the *Class loader order* and *WAR class loader policy* headings, respectively.](../../images/class-loading-properties-was.png)

5. If prompted, click on the *Save Directly* link in order to save the master
   configuration.

6. When the Enterprise Applications page appears, click on the hyperlink for
   your JSF portlet again.

7. Click the *JSP and JSF Options* link located beneath the Web Module
   Properties heading.

    ![Figure 3: By clicking this option, you'll be presented with JSF options for your WebSphere application server.](../../images/wab-shared-lib-refs.png)

8. Select *SunRI1.2* and click *OK*.

9. If prompted, click on the *Save Directly* link in order to save the master
   configuration.

10. Now click on the *Shared library references* link.

    ![Figure 4: The *Shared library references* link is located beneath the References header.](../../images/wab-shared-library-references.png)

11. Click on the first checkbox for your portlet, and then click *Reference
   shared libraries*.

    ![Figure 5: The *References shared libraries* button will take you to a screen that allows you to configure your portlet's shared library mapping.](../../images/wab-shared-library-references.png)

12. Select *Mojarra 2.1* from the list on the left and then click the
   right-arrow button to have it selected for your configuration.

13. Verfiy that *Mojarra 2.1* is specified as a Shared Library in your JSF
   portlet. Then click *OK*.

    ![Figure 6: You can verify your Mojarra specification on the far right of the table, beneath the Shared Libraries heading.](../../images/wab-shared-lib-refs-verification.png)

14. Once you're back on the Enterprise Applications page, click on the checkbox
   corresponding to your JSF portlet and click *Start*. You should now be able
   to add the portlet to a Liferay Portal page.

Awesome! By following these instructions, you've configured your WebSphere
application server to deploy your JSF portlet.

### Upgrading Mojarra on WebSphere 8.0 [](id=upgrading-mojarra-on-websphere-8-0)

WebSphere 8.0 ships with
[MyFaces Core 2.0](http://wiki.apache.org/myfaces/Websphere_Installation) as the
default implementation of JSF 2.0. It also ships with Mojarra 1.2 as the default
implementation of JSF 1.2. Since Liferay Faces 3.x requires Mojarra 2.1, it is
necessary to manually install it as a Shared Library. Follow the instruction
below to correctly upgrade your version of Mojarra on WebSphere 8.0.

1. Using your File Explorer, navigate to the `IBM/WebSphere/AppServer` folder.
   Inside this folder, create a new folder named `mojarra-2.1` and copy the
   following files into it:

    - [jsf-api-2.1.21.jar](http://search.maven.org/#artifactdetails|com.sun.faces|jsf-api|2.1.21|jar)
    - [jsf-impl-2.1.21.jar](http://search.maven.org/#artifactdetails|com.sun.faces|jsf-impl|2.1.21|jar)
    - [liferay-faces-init-3.1.3-ga4.jar](http://search.maven.org/#artifactdetails|com.liferay.faces|liferay-faces-init|3.1.3-ga4|jar)

    The `liferay-faces-init.jar` dependency is required for automatic startup of
    the Mojarra `ConfigureListener`.

    +$$$

    **Notes:** If running WebSphere on Windows, make sure to download 2.1.20 (or
    newer) due to
    [JAVASERVERFACES-2765](https://java.net/jira/browse/JAVASERVERFACES-2765).

    $$$

2. In your WebSphere Admin Console, click *Environment* &rarr; *Shared
   libraries*.

    ![Figure 6: This option allows you to add and delete shared libraries.](../../images/wab-environment-shared-libs.png)

3. Click on the *New...* button to create a new shared library.

4. Enter *Mojarra 2.1* in the *Name* field.

5. Enter the full path to the `mojarra-2.1` folder you created in Step 1. For
   example:

    C:\Program Files\IBM\WebSphere\AppServer\mojarra-2.1

6. Click the *Use an isolated class loader for this shared library* checkbox,
   and then click *OK*.

7. If prompted, click on the *Save Directly* link in order to save the master
   configuration.

Congratulations! You've upgraded your WebSphere application server's Mojarra
version to 2.1.

## Page 665: Configuring Liferay for High Availability [](id=page-665-configuring-liferay-for-high-availability)

In the *Properties File Changes* subsection of the chapter *19.2 Performance 
Tuning* section, text for the `last.modified.check` property has been removed
because the property is obsolete.

