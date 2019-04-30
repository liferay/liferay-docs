# Customizing Core Functionality with Ext [](id=customizing-core-functionality-with-ext)

+$$$

**Ext plugins are deprecated for @product-ver@ and should only be used if
absolutely necessary.**

The following app servers should be used for Ext plugin development in
@product@:

- Tomcat 9.0

In most cases, Ext plugins are not necessary. There are, however, certain cases
that require the use of an Ext plugin. Liferay only supports the following Ext
plugin use cases:

- Providing custom implementations for any beans declared in @product@'s
  Spring files (when possible, use
  [service wrappers](/develop/tutorials/-/knowledge_base/7-1/customizing-liferay-services-service-wrappers)
  instead of an Ext plugin). @product-ver@ removed many beans, so make sure your
  overridden beans are still relevant if converting your legacy Ext plugin
  ([how to](/develop/reference/-/knowledge_base/7-1/extending-core-classes-using-spring-with-ext-plugins)).
- Overwriting a class in a @product-ver@ core JAR. For a list of core JARs, see
  the [Finding Core @product@ Artifacts](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies#finding-core-liferay-portal-artifacts)
  section
  ([how to](/develop/reference/-/knowledge_base/7-1/overriding-core-classes-with-ext-plugins)).
- Modifying @product@'s `web.xml` file
  ([how to](/develop/reference/-/knowledge_base/7-1/modifying-the-web-xml-with-ext-plugins)).
- Adding to @product@'s `web.xml` file
  ([how to](/develop/reference/-/knowledge_base/7-1/adding-to-the-web-xml-with-ext-plugins)).

**Note:** In previous versions of Liferay Portal, you needed an Ext plugin to
specify classes as portal property values (e.g.,
`global.starup.events.my.custom.MyStartupAction`), since the custom class had to
be added to the portal class loader. This is no longer the case in @product-ver@
since all lifecycle events can use OSGi services with no need to edit these
legacy properties.

$$$

Ext plugins are used to customize @product@'s core functionality. You can learn
more about what the core encompasses in the
[Finding Core @product@ Artifacts](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies#finding-core-liferay-portal-artifacts)
article section. In this section, you'll learn how to

- [Create an Ext plugin](/develop/reference/-/knowledge_base/7-1/creating-an-ext-plugin)
- [Develop an Ext plugin](/develop/reference/-/knowledge_base/7-1/developing-an-ext-plugin)
- [Deploy an Ext plugin](/develop/reference/-/knowledge_base/7-1/deploying-an-ext-plugin)
- [Redeploy an Ext plugin](/develop/reference/-/knowledge_base/7-1/redeploying-an-ext-plugin)

You can also dive into the
[Anatomy of an Ext Plugin](/develop/reference/-/knowledge_base/7-1/anatomy-of-an-ext-plugin)
to familiarize yourself with its structure.

You'll start by creating an Ext plugin.
