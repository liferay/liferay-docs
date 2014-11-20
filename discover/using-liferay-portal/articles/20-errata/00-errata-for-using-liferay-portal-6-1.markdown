# Errata for Using Liferay Portal 6.1

## Page 123: Microsoft Office Integration

The Microsoft Office integration features are supported only on Windows XP with
32-bit versions of Microsoft Internet Explorer and 32-bit versions of Microsoft
Office.

## Page 473: Installing Liferay on GlassFish 3

The following sections are related to configuring Liferay Faces to function
properly, allowing JSF applications to successfully work in GlassFish 3.

### Clustering JSF Portlets in GlassFish 3

To successfully cluster JSF portlets on your GlassFish application server,
follow the instructions below:

1. Make sure you've created a JSF portlet project that can be deployed to
   Liferay.

2. Create a cluster and at least 3 instances in it. This is needed for GlassFish
   to ensure session replication when one node becomes unavailable.

3. As any other Liferay cluster installation, you have to set Liferay to work in
   a cluster. Visit the [Liferay Clustering](https://dev.liferay.com/discover/portal/-/knowledge_base/6-1/liferay-clustering)
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

### Upgrading Mojarra in GlassFish 3

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

### Upgrading Weld in GlassFish 3

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

## Page 492: Installing Liferay on JBoss 7

The following sections are related to configuring Liferay Faces to function
properly, allowing JSF applications to successfully work in JBoss 7.

### Upgrading Mojarra in JBoss 7

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

### Upgrading Weld in JBoss 7

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

## Page 665: Configuring Liferay for High Availability

In the *Properties File Changes* subsection of the chapter *19.2 Performance 
Tuning* section, text for the `last.modified.check` property has been removed
because the property is obsolete.

