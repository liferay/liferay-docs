# Migrating Legacy Application to New Plugins SDK

With the new modularity focus for Liferay 7.0, many of Liferay's applications
have migrated to OSGi modules. Although Liferay 7.0 has modularized most of its
components to fit the modular vision, it has also remained backwards compatible
for WAR-style applications that are created using the Plugins SDK. Therefore,
both module and WAR-style applications are supported in Liferay 7.0. As an
overview on this topic, you have two routes you can travel down for updating
your applications for Liferay 7.0:

- Transform your legacy applications to OSGi modules.
- Keep your applications as legacy WAR-style applications generated from the
  Plugins SDK, and consume the API from the new Liferay modules.

<!-- To transform your legacy applications to OSGi modules, you can follow the
[tutorial link]() tutorial. This is the recommended way for long-term stability.


Use the text above, when we have a tutorial that shows how to convert legacy
apps to OSGi modules. -Cody -->

The recommended route for long-term stability is to convert your legacy
applications to OSGi modules. You can, however, keep your legacy WAR-style
plugins and use them in Liferay 7.0 just as you've used them in previous
versions.

In this tutorial, you'll learn how to migrate your legacy application to Liferay
7.0 as a WAR-style application in the Plugins SDK. Follow the instructions below
to begin the migration process:

1.  Use the following [reference document](/develop/reference/-/knowledge_base/7-0/calling-migrated-services-from-legacy-plugins)
    to find classes that were exposed as Liferay Portal API in 6.2, but have
    been moved into separate modules. Make a note of any APIs that your legacy
    plugin is consuming from this list. If your legacy plugin does not use any
    of the services listed in the reference document, you can skip to step 6.

    <!-- Check the reference doc link above and verify that the header ID has
    not changed. The reference doc had not been reviewed/published during the
    writing of this article, and the link may have changed. --Cody -->

2.  Resolve your application's dependencies with other modules by adding the
    required libraries to your application's `ivy.xml` file. Within the
    `<dependencies>` tag, you'll need to define the module `<dependency>` that
    your application relies on. For example, suppose your application is using a
    Wiki service. You would add this dependency to the `ivy.xml` file by
    inserting the following `<dependency>` tag within `<dependencies>`:

        <dependencies defaultconf="default">
            <dependency name="com.liferay.wiki.api" org="com.liferay" rev="1.0.0-SNAPSHOT" />
        </dependencies>

    If your legacy application does not have any `ivy.xml` file, you can create
    one and place it in the root directory of your application. You can view the
    [`ivy.xml`](https://github.com/liferay/liferay-plugins/blob/master/portlets/knowledge-base-portlet/ivy.xml)
    file of the Knowledge Base portlet for an example.

3.  Run `ant clean` from your application's root folder. This command generates
    the necessary JAR files that your application can reference in the
    `WEB-INF/lib` folder. If you were already using an `ivy.xml` file, `ant
    clean` may not generate the necessary JARs you defined automatically. To
    have Ivy regenerate the libraries defined in your `ivy.xml` file, delete the
    `ivy.xml.MD5` file from your root folder and retry `ant clean`.

4.  Refactor your imports so they match the new package names residing in the
    modules. For example, if one of your application's classes imports the
    `WikiPage` class, you would need to update the import from:

        import com.liferay.portlet.wiki.model.WikiPage;

    to:

        import com.liferay.wiki.model.WikiPage;

5.  Open your application's `liferay-plugin-package.properties` file and update
    the `liferay-versions` property to include Liferay 7.0.
