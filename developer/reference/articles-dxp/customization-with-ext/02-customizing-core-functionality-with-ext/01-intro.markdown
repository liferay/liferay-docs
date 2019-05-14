---
header-id: customizing-core-functionality-with-ext
---

# Customizing Core Functionality with Ext

[TOC levels=1-4]

| **Ext plugins are deprecated for @product-ver@ and should only be used if
| absolutely necessary.**
| 
| The following app servers should be used for Ext plugin development in
| @product@:
| 
| - Tomcat 9.x
| 
| In most cases, Ext plugins are not necessary. There are, however, certain
| cases that require the use of an Ext plugin. Liferay only supports the
| following Ext plugin use cases:
| 
| - Providing custom implementations for any beans declared in @product@'s
|   Spring files (when possible, use
|   [service wrappers](/docs/7-2/customization/-/knowledge_base/c/customizing-liferay-services-service-wrappers)
|   instead of an Ext plugin). @product-ver@ removed many beans, so make sure
|   your overridden beans are still relevant if converting your legacy Ext
|   plugin
|   ([how to](/docs/7-2/reference/-/knowledge_base/r/extending-core-classes-using-spring-with-ext-plugins)).
| - Overwriting a class in a @product-ver@ core JAR. For a list of core JARs,
|   see the
|   [Finding Core @product@ Artifacts](/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies#finding-core-liferay-portal-artifacts)
|   section
|   ([how to](/docs/7-2/reference/-/knowledge_base/r/overriding-core-classes-with-ext-plugins)).
| - Modifying @product@'s `web.xml` file
|   ([how to](/docs/7-2/reference/-/knowledge_base/r/modifying-the-web-xml-with-ext-plugins)).
| - Adding to @product@'s `web.xml` file
|   ([how to](/docs/7-2/reference/-/knowledge_base/r/adding-to-the-web-xml-with-ext-plugins)).
| 
| **Note:** In previous versions of Liferay Portal, you needed an Ext plugin to
| specify classes as portal property values (e.g.,
| `global.starup.events.my.custom.MyStartupAction`), since the custom class had
| to be added to the portal class loader. This is no longer the case in
| @product-ver@ since all lifecycle events can use OSGi services with no need to
| edit these legacy properties.

Ext plugins are used to customize @product@'s core functionality. You can learn
more about what the core encompasses in the
[Finding Core @product@ Artifacts](/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies#finding-core-liferay-portal-artifacts)
article section. In this section, you'll learn how to

- [Create an Ext plugin](/docs/7-2/reference/-/knowledge_base/r/creating-an-ext-plugin)
- [Develop an Ext plugin](/docs/7-2/reference/-/knowledge_base/r/developing-an-ext-plugin)
- [Deploy an Ext plugin](/docs/7-2/reference/-/knowledge_base/r/deploying-an-ext-plugin)
- [Redeploy an Ext plugin](/docs/7-2/reference/-/knowledge_base/r/redeploying-an-ext-plugin)

You can also dive into the
[Anatomy of an Ext Plugin](/docs/7-2/reference/-/knowledge_base/r/anatomy-of-an-ext-plugin)
to familiarize yourself with its structure.

You'll start by creating an Ext plugin.
