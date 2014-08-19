# Summary

In this chapter, we introduced concepts and instructions for developers to make
their apps available on the Liferay Marketplace. 

We looked at how to create, publish, maintain, and track Liferay Marketplace
apps. You can do this through the App Manager that's available on your personal
[liferay.com](http://liferay.com) home page ([liferay.com](http://liferay.com)
account required!). Then, we covered the requirements for publishing apps, which
did not differ significantly from requirements for general Liferay development.
Next, we showed how you can publish a sample app on the Marketplace and how you
can modify it as the app evolves. Finally, we looked at how to track the
adoption of apps using view, download, and install metrics. 

Regarding plugin security management, we discussed why plugin security
management is necessary, how the Plugin Security Manager checks each plugin
against its portal access control list (PACL), and how to specify PACL
properties for the plugins you create and deploy. We also explained Liferay's
support of the Java Security Policy, in case you need to specify rules above and
beyond what PACL properties support.

Now you have a better understanding of how plugin security works and can use
Liferay Portal's Plugin Security Manager effectively to specify exactly what
services your plugin needs in order to function. Anyone running Liferay Portal
with Security Manager turned on will know you're a "law abiding" citizen,
because you've specified what services your applications need to access in order
to function. We hope this information helps you understand how to develop safe
and powerful Liferay apps. 

Next, we'll talk about using Ext plugins to make customizations that you can't
make with any other Liferay plugin type. 

