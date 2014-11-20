# Errata for Using Liferay Portal 6.1

## Page 123: Microsoft Office Integration

The Microsoft Office integration features are supported only on Windows XP with
32-bit versions of Microsoft Internet Explorer and 32-bit versions of Microsoft
Office.

## Page 473: Installing Liferay on GlassFish 3

The following sections are related to configuring Liferay Faces to function
properly, allowing JSF applications to successfully work in GlassFish 3.

### Clustering JSF Portlets

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

## Page 665: Configuring Liferay for High Availability

In the *Properties File Changes* subsection of the chapter *19.2 Performance 
Tuning* section, text for the `last.modified.check` property has been removed
because the property is obsolete.

