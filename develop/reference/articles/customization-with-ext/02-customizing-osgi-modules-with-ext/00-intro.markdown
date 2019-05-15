# Customizing OSGi Modules with Ext [](id=customizing-osgi-modules-with-ext)

An Ext module is a powerful tool for extending @product@'s OSGi modules. For
example, if you want to overwrite a default module's JSP to display a different
view, you can create an Ext module to customize the original module's JSP (for
example, see the
[Login Web Ext sample](/develop/reference/-/knowledge_base/7-1/login-web-ext)).
Because this increases the complexity of your @product@ installation, you should
only use an Ext module if you're sure you can't accomplish your goal in a
different way (e.g., leveraging an extension point).

The following app servers should be used for Ext module development in
@product@:

- Tomcat 9.0

In this section, you'll learn how to

- [Create an Ext module](/develop/reference/-/knowledge_base/7-1/creating-an-ext-module)
- [Develop an Ext module](/develop/reference/-/knowledge_base/7-1/developing-an-ext-module)
- [Deploy an Ext module](/develop/reference/-/knowledge_base/7-1/deploying-an-ext-module)

You'll start by creating an Ext module.
