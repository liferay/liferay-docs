# Advanced Customization with Ext Plugins

**Ext plugins are deprecated for @product-ver@ and should only be used if
absolutely necessary.**

Ext plugins are powerful tools used to extend @product@. They, however, increase
the complexity of your @product@ instance and are not recommended unless there
is absolutely no other way to accomplish your task. @product-ver@ provides many
extension points that let you customize almost every detail of @product@. If
there's a way to customize @product@'s default functionality by extending an
extension point, you should never use an Ext plugin. See the
[More Extensible, Easier to Maintain](/develop/tutorials/-/knowledge_base/7-0/benefits-of-liferay-7-for-liferay-6-developers#more-extensible-easier-to-maintain)
section for more details on the advantages of using Liferay's extension points.

There are many parts of @product@ that used to require an Ext plugin that now
provide an extension point via OSGi bundle. You can examine custom module projects
that extend popular @product@ extension points by visiting the
[Liferay Blade Samples](https://github.com/liferay/liferay-blade-samples)
repository. For more information on these sample projects, see the
[Liferay Sample Modules](/develop/tutorials/-/knowledge_base/7-0/liferay-sample-modules)
tutorial. Usable extension points are also documented throughout Liferay's
Developer Network categorized by the @product@ section involved. For example,
[Overriding MVC Commands](),
[Customizing the Product Menu](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu),
and []()
are all articles describing how to extend a @product@ extension point. Want to
learn how to
[override module JSPs](/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-jsps)
or [@product@ core JSPs](/develop/tutorials/-/knowledge_base/7-0/overriding-core-jsps)?
Those processes are documented too!

Before deciding to use an Ext plugin, weigh the cost. Ext plugins let you use
internal APIs and even let you overwrite @product@ core files. When upgrading to
a new version of @product@ (even if it's a maintenance version or a service
pack), you have to review all changes and manually modify your Ext plugin to
merge your changes with @product@'s. Additionally, Ext plugins aren't hot
deployable. To deploy an Ext plugin, you must restart your server. Lastly, with
Ext plugins, additional steps are required to deploy or redeploy to production
systems.

There are a few corner cases where you may need an Ext plugin to customize a
part of @product@ that does not provide an extension point. For example, the
following cases would require an Ext plugin:

- To specify custom classes as portal property values. For example, to specify a
  property that needs a custom class (e.g.,
  `global.startup.events=my.custom.MyStartupAction`), you need an Ext plugin to
  add your custom class to the portal class loader. 
- To provide custom implementations for any Liferay beans declared in Liferay's
  Spring files (when possible, use
  [service wrappers](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services-service-wrappers)
  instead of an Ext plugin).
- To add JSPs referenced from portal properties that can only be changed
  from an Ext plugin.
- To Overwrite a class (not recommended unless you have no other choice). 

<!-- These cases may no longer be accurate for Liferay 7. If so, please provide
any relevant cases for Liferay 7/DXP -->

With these use cases in mind, you'll learn how to use Ext for these things: 

- Creating an Ext plugin 
- Developing an Ext plugin 
- Deploying in Production 
- Migrating Old Extension Environments 

It's time to create an Ext plugin. 

