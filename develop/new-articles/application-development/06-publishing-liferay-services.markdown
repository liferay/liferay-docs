# Publishing Liferay Services

To publish a service to Liferay, the service must be registered in Liferay's
module framework. The easiest way to publish services to Liferay is to publish
them as [Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
components. 

When publishing services to Liferay, it's a best practice to package your
service API and service implementation as separate bundles. If you'd like to
create a service client, the client should also be packaged as a separate
bundle. Since Liferay follows this practice, most modularized Liferay
applications consist of at least two or three bundles. In this tutorial, you'll
learn how to create a service API, a service implementation, and a service
client. The service client takes the form of a command which can be invoked from
Liferay's Felix Gogo shell. You can find the code referenced by this
tutorial in complete projects on Github here:
[https://github.com/jrao/liferay-docs/tree/liferay7-development-tutorials/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets](https://github.com/jrao/liferay-docs/tree/liferay7-development-tutorials/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets). The specific projects referenced by this tutorial are `example-api`, `example-service`, and `example-command`.

