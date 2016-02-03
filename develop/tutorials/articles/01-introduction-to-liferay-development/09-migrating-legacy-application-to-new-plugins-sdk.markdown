# Migrating Legacy Applications to New Plugins SDK [](id=migrating-legacy-applications-to-new-plugins-sdk)

If you have legacy applications for Liferay Portal 6.2 or earlier, you'll be
happy to know there's a clearly defined path for migrating those applications to
Liferay 7. As you know by now, many of Liferay's applications have migrated to
OSGi modules, but Liferay is still a JSR-286, standards compliant portal. For
this reason, it remains backwards compatible for WAR-style applications that are
created using the Plugins SDK. This means two things: you can write both module
and WAR-style applications, and you can easily upgrade your old 6.2 applications
to new 7.0 APIs without converting them to modules first. When considering
migrating your application to Liferay 7, you can divide the task into two steps: 

1.  Convert your 6.2 WAR-style plugin to a 7.0 WAR-style plugin and consume the
    API from the new Liferay modules. 

2.  Convert your legacy applications to OSGi modules.

<!-- To transform your legacy applications to OSGi modules, you can follow the
[tutorial link]() tutorial. This is the recommended way for long-term stability.


Use the text above, when we have a tutorial that shows how to convert legacy
apps to OSGi modules. This will be covered in LRDOCS-1859 -Cody -->

This tutorial shows you the first step: how to get your 6.2 WAR-style plugin
working on Liferay 7.0. Later, you can convert your application to OSGi modules.
Follow the instructions below to begin the migration process:

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

3.  Resolve your application's dependencies on other modules by adding the
    required libraries to your application's `ivy.xml` file. Within the
    `<dependencies>` tag, you'll need to define the module `<dependency>` that
    your application relies on. For example, suppose your application is using a
    Wiki service. You would add this dependency to the `ivy.xml` file by
    inserting the following `<dependency>` tag within `<dependencies>`:

        <dependencies defaultconf="default">
            <dependency name="com.liferay.wiki.api" org="com.liferay" rev="1.0.0-SNAPSHOT" />
        </dependencies>

    If your legacy application does not have an `ivy.xml` file, you can create
    one and place it in your applicatin's root folder. You can view the
    [`ivy.xml`](https://github.com/liferay/liferay-plugins/blob/master/portlets/knowledge-base-portlet/ivy.xml)
    file of the Knowledge Base portlet for an example.

4.  Run `ant clean` from your application's root folder. This command triggers
    the download process for the dependencies that your application can
    reference in the `WEB-INF/lib` folder. If you were already using an
    `ivy.xml` file, `ant clean` may not download the necessary JARs you defined
    automatically. To fix this, delete the `ivy.xml.MD5` file from your root
    folder and retry `ant clean`.

5.  Refactor your imports so they match the new package names in the modules.
    For example, if one of your application's classes imports the
    `WikiPage` class, you would need to update the import from

        import com.liferay.portlet.wiki.model.WikiPage;

    to

        import com.liferay.wiki.model.WikiPage;

    Make sure to check other files--such as JSP files--for imports and
    references.

6.  Open your application's `liferay-plugin-package.properties` file and update
    the `liferay-versions` property to include Liferay 7.0.

7.  Once you think you've satisfied your plugin's dependencies/imports, run `ant
    compile`. If your plugin compiles successfully, you've addressed the
    necessary updates. There are
    [breaking changes](/develop/reference/-/knowledge_base/7-0/what-are-the-breaking-changes-for-liferay-7-0)
    that could cause your application to fail during compilation. If you have
    compilation errors that are unrelated to the migrated services in modules,
    search the breaking changes document for potential causes. There is a
    migration tool that will be able to check your application for breaking
    changes automatically, but this tool is still in development and not
    available at this time.

    <!-- The above statement will need to be removed about breaking changes when
    the migration tool is ready to document. -Cody -->

8.  Run `ant war` to generate your application's deployable WAR file. The WAR
    file is available in the Plugins SDK's `/dist` folder.

9.  Before deploying your WAR file to a Liferay Portal 7.0 instance, remove the
    JARs that were generated in your WAR's `/lib` folder. Recall that you added
    dependencies in your `ivy.xml` file. These downloaded JAR files were
    necessary for your application to compile. Now that your application has
    compiled and is a WAR, they must be removed, because they already exist in
    Liferay's module framework. 

    There are two types of dependencies--build-time (i.e., compilation) and
    runtime. If your application consumes Wiki services, for instance, you must
    declare them as build time dependencies via the `ivy.xml` file. This ensures
    that the JARs are downloaded and copied to the `/lib` folder and the
    application compiles. This was necessary to generate your legacy
    application's WAR file.

    In Liferay 7.0, the Wiki portlet is, in most cases, already installed. If
    so, your portal's runtime already has the Wiki JARs available. Therefore,
    your application's Wiki-related JARs cannot be included in its WAR file
    because they would conflict with the JARs already installed in Portal. If
    they are not removed, identical JARs would exist in two different
    classloaders, which would throw class cast exceptions.

10. Copy your application's WAR file into your portal instance's
    `/osgi/modules` directory. Your application is now available from the OSGi
    console. To check if your application was deployed and installed
    successfully, open a terminal, run `telnet localhost 11311`, and
    then run `lb`. Your application should be listed like this: 

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

## Related Topics [](id=related-topics)

[Introduction to Blade Tools](/develop/tutorials/-/knowledge_base/7-0/introduction-to-blade-tools)

[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)

[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services)
