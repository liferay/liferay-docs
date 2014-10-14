# Ext plugins [](id=ext-plugins)

Ext plugins are powerful tools for extending Liferay. Because they increase the
complexity of your Liferay instance, you should only use an Ext plugin if you're
sure you can't accomplish your goal using a different tool. Check out Chapter 6,
Hooks for the available alternatives. If a hook won't suffice, keep reading to
discover the use cases for Ext plugins and how to set one up. First let's talk
about why you should avoid Ext plugins when possible. 

As someone once said, "With great power comes great responsibility" (okay, many
people have said that many times).  Before deciding to use an Ext plugin, weigh
the cost of using such a powerful tool. Ext plugins allow the use of internal
APIs or even overwriting files from the Liferay core. When upgrading to a new
version of Liferay (even if it's a maintenance version or a service pack), you
have to review all changes and manually modify your Ext plugin to merge your
changes with Liferay's. Additionally, Ext plugins aren't hot deployable. To
deploy an Ext plugin, you must restart your server. Lastly, with Ext plugins,
additional steps are required to deploy or redeploy to production systems. 

Now that you know the limitations of Ext plugins, let's look at why you'd want
to use them: 

- To specify custom classes as portal property values. For example, to specify a
  property that needs a custom class (e.g.,
  `global.startup.events=my.custom.MyStartupAction`), you need an Ext plugin to
  add your custom class to the portal class loader. 
- To provide custom implementations for any Liferay beans declared in Liferay's
  Spring files (when possible, use service wrappers from a hook instead of an
  Ext plugin). 
- To add JSPs referenced from portal properties that can only be changed
  from an Ext plugin (check whether the property can be modified from a hook
  plugin first). 
- To Overwrite a class (not recommended unless you have no other choice). 

With these use cases in mind, we'll discuss the following topics: 

- Creating an Ext plugin 
- Developing an Ext plugin 
- Deploying in production 
- Migrating old extension environments 

Let's create an Ext plugin. 
