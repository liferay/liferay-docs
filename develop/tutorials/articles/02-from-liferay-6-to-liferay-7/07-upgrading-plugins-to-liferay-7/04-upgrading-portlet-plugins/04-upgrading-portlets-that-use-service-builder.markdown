# Upgrading Portlets that use Service Builder [](id=upgrading-portlets-that-use-service-builder)

@product-ver@ lets you continue developing portlets that use [Service
Builder](/develop/tutorials/-/knowledge_base/7-0/service-builder). You can focus
on your application's business logic instead of its persistence details. And
Service Builder still generates model classes, local and remote services, and
permissioned services. 

This tutorial demonstrates upgrading a Liferay Plugins SDK 6.2 sample portlet
called Guestbook portlet (project `guestbook-portlet`). It's from the
[Writing a Data-Driven Application](/develop/tutorials/-/knowledge_base/6-2/writing-a-data-driven-application)
section of the Liferay Portal 6.2 Learning Path
[Writing a Liferay MVC Application](/develop/tutorials/-/knowledge_base/6-2/writing-a-liferay-mvc-application).

![Figure 1: The Guestbook portlet to model guestbooks and guestbook entries.](../../../../images/upgrade-service-builder-portlet-guestbook.png)

To get the most from this tutorial, you can download and refer to the
[original portlet source code](https://dev.liferay.com/documents/10184/656312/guestbook-portlet-pre-7-0-upgrade.zip)
and the 
[upgraded source code](https://dev.liferay.com/documents/10184/656312/guestbook-portlet-post-7-0-upgrade.zip).

The Guestbook portlet has the following characteristics:

-   Extends `MVCPortlet`
-   Separate Model, View, and Control layers
-   Persistence by Hibernate
-   View layer implemented by JSPs
-   Manages dependencies via Ant/Ivy
-   Developed in a Liferay Plugins SDK 6.2

Upgrading most Service Builder Portlets involves these steps: 

1.  [Adapt the code to @product-ver@'s API](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
2.  [Resolve dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)
3.  [Build the services](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)

Since the Guestbook portlet's dependencies haven't changed, upgrading it
involves only adapting the code to @product-ver@'s API and building the
services. The
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
facilitates updating the code and resolving compilation issues quickly. 

+$$$

**Note**: Refer to tutorial
[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)
if you need to adapt to dependency changes. 

$$$

The Guestbook portlet has the following compilation error:

    /html/guestbook/view.jsp(58,1) PWC6131: Attribute total invalid for tag search-container-results according to TLD

The `view.jsp` file specifies a tag library attribute `total` that doesn't exist
in @product-ver@'s `liferay-ui` tag library.

    <liferay-ui:search-container-results
        results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                        guestbookId, searchContainer.getStart(),
                        searchContainer.getEnd())%>"
        total="<%=EntryLocalServiceUtil.getEntriesCount(scopeGroupId,
                        guestbookId)%>" />

Remove the `total` attribute declaration to make the tag like this:

    <liferay-ui:search-container-results
        results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                        guestbookId, searchContainer.getStart(),
                        searchContainer.getEnd())%>" />

If you specify exception classes in the `service.xml` file, they're generated in
a folder called `exceptions` in the Service Builder `package-path`. 

For example, the Micro Blogs portlet's exception class for `exception` element
`UnsupportedMicroblogsEntry` is generated to
`docroot/WEB-INF/service/com/liferay/microblogs/exception`. Here's an excerpt
from its `service.xml` file:

    <service-builder auto-namespace-tables="false" package-path="com.liferay.microblogs">
        ...
        <exceptions>
            <exception>UnsupportedMicroblogsEntry</exception>
        </exceptions>
    </service-builder>

On deploying an upgraded portlet, the server prints messages indicating the
following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

Deploying the Guestbook portlet produces these messages:

    19:36:27,591 INFO  [RMI TCP Connection(27)-192.168.1.110][BaseAutoDeployListener:42] Copying portlets for C:\portals\liferay-ce-portal-7.0-ga3-upgrading-portlets\tomcat-8.0.32\temp\20170710193627556LFQNRFGO\guestbook-portlet-7.0.0.1.war
    19:36:27,973 INFO  [RMI TCP Connection(27)-192.168.1.110][BaseDeployer:873] Deploying guestbook-portlet-7.0.0.1.war
    19:36:29,449 INFO  [RMI TCP Connection(27)-192.168.1.110][BaseAutoDeployListener:50] Portlets for C:\portals\liferay-ce-portal-7.0-ga3-upgrading-portlets\tomcat-8.0.32\temp\20170710193627556LFQNRFGO\guestbook-portlet-7.0.0.1.war copied successfully
    19:36:31,231 INFO  [pool-23-thread-2][BundleStartStopLogger:35] STARTED guestbook-portlet_7.0.0.1 [496]
    19:36:31,459 INFO  [pool-23-thread-2][HotDeployImpl:226] Deploying guestbook-portlet from queue
    19:36:31,459 INFO  [pool-23-thread-2][PluginPackageUtil:1006] Reading plugin package for guestbook-portlet
    10-Jul-2017 19:36:31.470 INFO [pool-23-thread-2] org.apache.catalina.core.ApplicationContext.log Initializing Spring root WebApplicationContext
    19:36:31,934 INFO  [pool-23-thread-2][PortletHotDeployListener:202] Registering portlets for guestbook-portlet
    19:36:32,008 INFO  [pool-23-thread-2][PortletHotDeployListener:331] 1 portlet for guestbook-portlet is available for use

The portlet is now available on @product@.

Congratulations on upgrading and deploying a portlet that uses Service Builder.

## Related Topics [](id=related-topics)

[Migrating Plugins SDK Projects to Workspace and Gradle](/develop/tutorials/-/knowledge_base/7-0/migrating-traditional-plugins-to-workspace-web-applications)

[Using Dependency Management Tools](/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies#managing-dependencies-with-ivy)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator)

[Migrating Data Upgrade Processes](/develop/tutorials/-/knowledge_base/7-0/optimizing-app-upgrade-processes)
