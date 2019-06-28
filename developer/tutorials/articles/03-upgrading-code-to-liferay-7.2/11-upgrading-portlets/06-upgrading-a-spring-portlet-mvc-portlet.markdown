---
header-id: upgrading-a-spring-portlet-mvc-portlet
---

# Upgrading a Spring Portlet MVC Portlet

[TOC levels=1-4]

<div class="learn-path-step">
    <p>Upgrading Portlets<br>Step 5 of 6</p>
</div>

Upgraded portlets that use Spring Portlet MVC should be migrated to use
PortletMVC4Spring. The main reason is that PortletMVC4Spring is maintained for
compatibility with the latest versions of the Spring Framework.

| **Note:** The PortletMVC4Spring project began as Spring Portlet MVC and was
| part of the [Spring Framework](https://spring.io/projects/spring-framework).
| When the project was pruned from version 5.0.x of the Spring Framework under
| [SPR-14129](https://github.com/spring-projects/spring-framework/issues/18701),
| it became necessary to fork and rename the project. This made it possible to
| improve and maintain the project for compatibility with the latest versions of
| the Spring Framework and the Portlet API.

| [Liferay](http://www.liferay.com/) adopted Spring Portlet MVC in March of 2019
| and the project was renamed to PortletMVC4Spring.

For more information on PortletMVC4Spring, see its dedicated
[section of articles](/docs/7-2/appdev/-/knowledge_base/a/portletmvc4spring).
For specific information on migrating a portlet using Spring Portlet MVC to
PortletMVC4Spring, see the
[Migrating to PortletMVC4Spring](/docs/7-2/appdev/-/knowledge_base/a/migrating-to-portletmvc4spring)
article.

Once you've migrated your portlet to leverage the PortletMVC4Spring framework,
you must also adapt your Liferay-specific APIs and dependencies. To do this,
complete the following steps:

1.  Adapt the portlet to @product-ver@'s API using the Liferay Upgrade Planner.
    When running the planner's *Fix Upgrade Problems* step, many of the existing
    issues are autocorrected. For remaining issues, the planner identifies code
    affected by the new API and ways to adapt it.

2.  [Resolve its dependencies](/docs/7-2/tutorials/-/knowledge_base/t/resolving-a-projects-dependencies)

3.  [Deploy it](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project)

After deploying the upgraded portlet, the server prints messages that indicate
the following portlet status:

-   WAR processing
-   WAB startup
-   Availability to users

You've migrated your Spring Portlet MVC portlet to the updated PortletMVC4Spring
framework, updated any additional APIs and dependencies, and deployed it to your
@product-ver@ instance. Your portlet's upgrade process is complete!
