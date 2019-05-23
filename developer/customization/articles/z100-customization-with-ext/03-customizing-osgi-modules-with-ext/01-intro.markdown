---
header-id: customizing-osgi-modules-with-ext
---

# Customizing OSGi Modules with Ext

[TOC levels=1-4]

An Ext module is a powerful tool for extending @product@'s OSGi modules. For
example, if you want to overwrite a default module's JSP to display a different
view, you can create an Ext module to customize the original module's JSP (for
example, see the
[Login Web Ext sample](/docs/7-2/reference/-/knowledge_base/r/login-web-ext)).
Because this increases the complexity of your @product@ installation, you should
only use an Ext module if you're sure you can't accomplish your goal in a
different way (e.g., leveraging an extension point).

The following app servers should be used for Ext module development in
@product@:

- Tomcat 9.x

In this section, you'll learn how to

- [Create an Ext module](/docs/7-2/reference/-/knowledge_base/r/creating-an-ext-module)
- [Develop an Ext module](/docs/7-2/reference/-/knowledge_base/r/developing-an-ext-module)
- [Deploy an Ext module](/docs/7-2/reference/-/knowledge_base/r/deploying-an-ext-module)

You'll start by creating an Ext module.
