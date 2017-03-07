# Liferay APIs [](id=liferay-apis)

An *Application Programming Interface* (API) is a protocol that, when invoked,
performs an action or set of actions. You can invoke an API from your own code
directly through a Java invocation, or through web services. This chapter
provides an overview of several essential Liferay APIs available to you for use
in developing your Liferay Portal. 

Liferay comes with a host of APIs that provide powerful portal and portlet
services. The following table shows you the entities for which services are
available:

 Portal Services      | Portlet Services              |
--------------------- | ----------------------------- |
 *User*               | *Documents and Media Library* |
 *Organization*       | *Blogs*                       |
 *UserGroup*          | *Wikis*                       |
 *Company*            | *Assets*                      |
 *Role*               | *Social Activity*             |
 *Permission*         |                               |
 *ResourcePermission* |                               |
 *Layout*             |                               |
---

The ability to call Liferay APIs with a Java method or web service invocation
gives you the flexibility to implement client code in Java or any language
(even a scripting language such as PHP) that supports web service invocations.
The APIs can be called from within portlet or non-portlet code. You can make
calls to the APIs *locally* from within the portal or *remotely* outside of the
portal's *Java virtual machine* (*JVM*).

This chapter covers the following topics: 

- Finding Services
- Invoking the API Locally
- Invoking the API Remotely
- Service Security Layers
- SOAP Web Services
- JSON Web Services
- Service Context
- Using Message Bus
- Device Detection
- Liferay's Deprecation Policy

You can find Liferay's services by searching for them in the Javadocs:
[http://docs.liferay.com/portal/6.1/javadocs/](http://docs.liferay.com/portal/6.1/javadocs/).
Below we'll show you how to search for portal services and portlet services.

Let's start by finding a portal service. 
