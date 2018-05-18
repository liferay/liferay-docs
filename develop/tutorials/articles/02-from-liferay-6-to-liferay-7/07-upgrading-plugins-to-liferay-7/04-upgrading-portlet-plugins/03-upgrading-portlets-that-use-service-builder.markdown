# Upgrading Portlets that use Service Builder [](id=upgrading-portlets-that-use-service-builder)

@product-ver@ continues to use 
[Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder), so
you can focus on your application's business logic instead of its persistence
details. It still generates model classes, local and remote services, and
persistence. 

This tutorial demonstrates upgrading a Liferay Plugins SDK 6.2 portlet
called Guestbook portlet (project `guestbook-portlet`). It's from the
[Writing a Data-Driven Application](/develop/tutorials/-/knowledge_base/6-2/writing-a-data-driven-application)
section of the Liferay Portal 6.2 Learning Path
[Writing a Liferay MVC Application](/develop/tutorials/-/knowledge_base/6-2/writing-a-liferay-mvc-application).

![Figure 1: The Guestbook portlet models guestbooks and guestbook entries.](../../../../images/upgrade-service-builder-portlet-guestbook.png)

To get the most from this tutorial, you can download and refer to the
[original portlet source code](https://dev.liferay.com/documents/10184/656312/guestbook-portlet-pre-7-0-upgrade.zip)
and the 
[upgraded source code](https://dev.liferay.com/documents/10184/1608802/guestbook-portlet-post-7-1-upgrade.zip).

The Guestbook portlet has the following characteristics:

-   Extends `MVCPortlet`
-   Separate Model, View, and Controller layers
-   Persistence by Hibernate under Service Builder
-   View layer implemented by JSPs
-   Relies on manual dependency management
-   Developed in a Liferay Plugins SDK 6.2

Upgrading most Service Builder Portlets involves these steps: 

1.  [Adapt the code to @product-ver@'s API](#1-adapt-the-code-to-product-vers-api)
2.  [Resolve dependencies](#2-resolve-dependencies)
3.  [Build the services](#3-build-the-services)

Start by adapting the code. 

## 1. Adapt the code to @product-ver@'s API [](id=1-adapt-the-code-to-product-vers-api)

Use the
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
to update the code and resolve compilation issues quickly. Then fix any
remaining compilation errors manually. 

The Guestbook portlet has the following compilation error:

    /html/guestbook/view.jsp(58,1) PWC6131: Attribute total invalid for tag search-container-results according to TLD

The `view.jsp` file specifies a tag library attribute `total` that doesn't exist
in @product-ver@'s `liferay-ui` tag library. Notice the second attribute
`total`. 

    <liferay-ui:search-container-results
        results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                        guestbookId, searchContainer.getStart(),
                        searchContainer.getEnd())%>"
        total="<%=EntryLocalServiceUtil.getEntriesCount(scopeGroupId,
                        guestbookId)%>" />

Remove the `total` attribute assignment to make the tag like this:

    <liferay-ui:search-container-results
        results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                        guestbookId, searchContainer.getStart(),
                        searchContainer.getEnd())%>" />

That's the Guestbook portlet's only compilation error you need to fix manually. 

## 2. Resolve dependencies [](id=2-resolve-dependencies)

Since the Guestbook portlet's dependencies haven't changed, there aren't any
dependencies to resolve.  

If you need to adapt a portlet's dependencies, refer to tutorial
[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies).

## 3. Build the services [](id=3-build-the-services)

[Build the services](/develop/tutorials/-/knowledge_base/7-1/running-service-builder)
as you did in Liferay Portal 6.2.

The Guestbook portlet's `service.xml` file specifies exception class names in
`exception` elements.  

    <service-builder package-path="com.liferay.docs.guestbook">
        ...
        <exceptions>
            <exception>GuestbookName</exception>
            <exception>EntryName</exception>
            <exception>EntryMessage</exception>
            <exception>EntryEmail</exception>
        </exceptions>
    </service-builder>

In Liferay Portal 6.2, Service Builder generates exception classes to the path
attribute `package-path` specifies. In @product-ver@, Service Builder generates
them to `[package-path]/exception`. 

Old path:

    [package-path]

New path:

    [package-path]/exception 

For example, the Guestbook portlet's package path is
`com.liferay.docs.guestbook`. Its exception class for `exception` element
`GuestbookName` is generated to
`docroot/WEB-INF/service/com/liferay/docs/guestbook/exception`. Classes that use
the exception must import
`com.liferay.docs.guestbook.exception.GuestbookNameException`. 

Update references to your portlet's exception classes. 

Deploy the portlet as you normally would. The server prints messages indicating
the following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

Deploying the Guestbook portlet produces these messages:

    2018-03-21 18:23:10.032 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:262] Processing guestbook-portlet-7.1.0.1.war
    2018-03-21 18:23:15.355 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BaseAutoDeployListener:43] Copying portlets for C:\portals\liferay-ce-portal-7.1-m1\tomcat-8.0.32\temp\20180321182315333UGEPAGTR\guestbook-portlet.war
    2018-03-21 18:23:15.829 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BaseDeployer:876] Deploying guestbook-portlet.war
    2018-03-21 18:23:17.797 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BaseAutoDeployListener:50] Portlets for C:\portals\liferay-ce-portal-7.1-m1\tomcat-8.0.32\temp\20180321182315333UGEPAGTR\guestbook-portlet.war copied successfully
    2018-03-21 18:23:19.621 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][HotDeployImpl:226] Deploying guestbook-portlet from queue
    2018-03-21 18:23:19.621 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][PluginPackageUtil:1003] Reading plugin package for guestbook-portlet
    2018-03-21 18:23:19.642 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][ServiceComponentLocalServiceImpl:598] Running GB SQL scripts
    21-Mar-2018 18:23:19.669 INFO [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war] org.apache.catalina.core.ApplicationContext.log Initializing Spring root WebApplicationContext
    2018-03-21 18:23:20.066 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][PortletHotDeployListener:186] Registering portlets for guestbook-portlet
    2018-03-21 18:23:20.271 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][PortletHotDeployListener:298] 1 portlet for guestbook-portlet is available for use
    2018-03-21 18:23:20.468 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BundleStartStopLogger:35] STARTED guestbook-portlet_7.1.0.1 [657]

The portlet is now available on @product@.

Congratulations on upgrading and deploying a portlet that uses Service Builder.

## Related Topics [](id=related-topics)

[Migrating Plugins SDK Projects to Workspace and Gradle](/develop/tutorials/-/knowledge_base/7-1/migrating-traditional-plugins-to-workspace-web-applications)

[Using Dependency Management Tools](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies#managing-dependencies-with-ivy)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)

[Migrating Data Upgrade Processes](/develop/tutorials/-/knowledge_base/7-1/optimizing-app-upgrade-processes)
