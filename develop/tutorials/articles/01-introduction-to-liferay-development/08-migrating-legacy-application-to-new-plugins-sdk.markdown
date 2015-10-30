# Migrating Legacy Applications to New Plugins SDK

With the new modularity focus for Liferay 7.0, many of Liferay's applications
have migrated to OSGi modules. Although Liferay 7.0 has made most of its
components to fit the modular vision, it has also remained backwards compatible
for WAR-style applications that are created using the Plugins SDK. Therefore,
both module and WAR-style applications are supported in Liferay 7.0. There are
two routes to take for updating your applications for Liferay 7.0:

- Transform your legacy applications to OSGi modules.
- Keep your applications as legacy WAR-style applications generated from the
  Plugins SDK, and consume the API from the new Liferay modules.

<!-- To transform your legacy applications to OSGi modules, you can follow the
[tutorial link]() tutorial. This is the recommended way for long-term stability.


Use the text above, when we have a tutorial that shows how to convert legacy
apps to OSGi modules. This will be covered in LRDOCS-1859 -Cody -->

The recommended route for long-term stability is to convert your legacy
applications to OSGi modules. You can, however, keep your legacy WAR-style
plugins and use them in Liferay 7.0 just as you've used them in previous
versions.

In this tutorial, you'll learn how to migrate a legacy application to Liferay
7.0 as a WAR-style application using the Plugins SDK. Follow the instructions
below to begin the migration process:

1.  Copy your application from your old Plugins SDK to a new 7.0 Plugins SDK.
    You can download a new 7.0 Plugins SDK from
    [SourceForge](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/).

2.  Use this [reference document](/develop/reference/-/knowledge_base/7-0/calling-migrated-services-from-legacy-plugins)
    to find classes that were exposed as Liferay Portal API in 6.2, but have
    been moved into separate modules for Liferay Portal 7.0. Make a note of any
    APIs that your legacy plugin is consuming from this list. If your legacy
    plugin does not use any of the services listed in the reference document,
    you can skip to step 6.

    To check if your legacy application needs updates, run `ant compile` from
    your plugin's root folder. The compilation process will throw errors if any
    dependencies/imports are incorrect.

    <!-- Check the reference doc link above and verify that the header ID has
    not changed. The reference doc had not been reviewed/published during the
    writing of this article, and the link may have changed. -Cody -->

3.  Resolve your application's dependencies with other modules by adding the
    required libraries to your application's `ivy.xml` file. Within the
    `<dependencies>` tag, you'll need to define the module `<dependency>` that
    your application relies on. For example, suppose your application is using a
    Wiki service. You would add this dependency to the `ivy.xml` file by
    inserting the following `<dependency>` tag within `<dependencies>`:

        <dependencies defaultconf="default">
            <dependency name="com.liferay.wiki.api" org="com.liferay" rev="1.0.0-SNAPSHOT" />
        </dependencies>

    If your legacy application does not have an `ivy.xml` file, you can create
    one and place it in the root directory of your application. You can view the
    [`ivy.xml`](https://github.com/liferay/liferay-plugins/blob/master/portlets/knowledge-base-portlet/ivy.xml)
    file of the Knowledge Base portlet for an example.

4.  Run `ant clean` from your application's root folder. This command triggers
    the download process for the necessary JAR files that your application can
    reference in the `WEB-INF/lib` folder. If you were already using an
    `ivy.xml` file, `ant clean` may not generate the necessary JARs you defined
    automatically. To have Ivy regenerate the libraries defined in your
    `ivy.xml` file, delete the `ivy.xml.MD5` file from your root folder and
    retry `ant clean`.

5.  Refactor your imports so they match the new package names residing in the
    modules. For example, if one of your application's classes imports the
    `WikiPage` class, you would need to update the import from:

        import com.liferay.portlet.wiki.model.WikiPage;

    to:

        import com.liferay.wiki.model.WikiPage;

    Make sure to check other files for imports and references, like JSP files.

6.  Open your application's `liferay-plugin-package.properties` file and update
    the `liferay-versions` property to include Liferay 7.0.

7.  Once you think you've satisfied your plugin's dependencies/imports, run `ant
    compile`. If your plugin compiles successfully, you've addressed the
    necessary updates.

8.  Run `ant war` to generate your application's deployable WAR file. The WAR
    file is available in the Plugins SDK's `/dist` folder.

9.  Before deploying your WAR file to a Liferay Portal 7.0 instance, remove the
    JARs that were generated in your WAR's `/lib` folder. Recall that you added
    dependencies in your `ivy.xml` file, which downloaded JAR files that were
    necessary for your application to compile. Now that your application has
    compiled and is a WAR, they must be removed.

    There are two types of dependencies--build-time (i.e., compilation) and
    runtime. If your application consumes Wiki services, for instance, you must
    declare them as build time dependencies via the `ivy.xml` file. This ensures
    that the JARs are downloaded and copied to the `/lib` folder, and the
    application compiles. This was necessary to generate your legacy
    application's WAR file.

    During runtime using Liferay 7.0, the Wiki portlet is, in most cases,
    already installed. If so, your portal's runtime already has the Wiki JARs
    available. Therefore, your application's Wiki-related JARs cannot be
    included in its WAR file because they would conflict with the JARs already
    installed in Portal. If they are not removed, identical JARs would exist in
    two different classloaders, which would throw class cast exceptions.

10. Copy your application's WAR file into your portal instance's
    `/osgi/modules` directory. Your application is now available from the OSGi
    console. To check if your application was deployed and installed
    successfully, open a command prompt and run `telnet localhost 11311` and
    then run `lb`. Your application should be listed, similar to the following:

        310|Active     |    1|Liferay Layout Type Controller Node (1.0.0)
        311|Active     |    1|Liferay Portlet Configuration Icon Edit Guest (1.0.0)
        316|Active     |    1|resources-importer-web-7-0.0.1 (7.0.0)

<!--

For new modules, it's enough to copy them to the deploy folder, since they are
automatically recognized as OSGi modules. At the current time, the legacy way of
deploying WARs is handled by the old plugin deployment mechanism, which causes
the plugin not to work (i.e. services were not properly resolved). The only way
for the legacy WAR to work is to copy the WAR into the `/osgi/modules` folder.
Check and see if/when deploy folder will support 7.0 WARs. -Cody -->

Awesome! You've converted your legacy application to a 7.0 WAR-style
application!
