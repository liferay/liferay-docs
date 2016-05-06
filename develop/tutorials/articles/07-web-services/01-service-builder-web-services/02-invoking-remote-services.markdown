# Invoking Remote Services [](id=invoking-remote-services)

You can invoke the remote services of any installed Liferay application the same
way that you invoke your local services. Doing so could be described as
"invoking remote services locally." One reason to invoke a remote service
instead of the corresponding local service might be to take advantage of the
remote service's permission checks. Consider the following common scenario:

- Both a local service implementation and a remote service implementation have
  been created for a particular service.
- The remote service performs a permission check and then invokes the
  corresponding local service.

In the above scenario, it's a best practice to invoke the remote service instead
of the local service. Doing so ensures that you don't need to duplicate
permission checking code. This is the practice followed by the services in 
[Liferay's Blogs app](https://github.com/liferay/liferay-portal/tree/master/modules/apps/web-experience/journal/journal-service/src/main/java/com/liferay/journal/service/impl). 

Of course, the main reason for creating remote services is to invoke them 
remotely. Service Builder can expose your project's remote web services both via 
a JSON API and via SOAP. By default, running Service Builder with 
`remote-service` set to `true` for your entities generates a JSON web services
API for your project. You can access your project's JSON-based RESTful services
via a convenient web interface. 

## Invoking Liferay Services Remotely [](id=invoking-liferay-services-remotely)

Many default Liferay services are available as web services. Liferay exposes its
web services via SOAP and JSON web services. If you're running Liferay locally
on port 8080, visit the following URL to browse Liferay's default SOAP web
services:

    http://localhost:8080/api/axis

To browse Liferay's default JSON web services, visit this URL:

    http://localhost:8080/api/jsonws/

By default, the context path is set to `/` which means that core Liferay
services are listed. By default, the *http://localhost:8080/api/jsonws/* page
shows the JSON web services in the `portal` context. You can select a different
context in the *Context Name* selector menu. For example, selecting `journal` in
*Context Name* shows you the JSON web services in Liferay's Web Content app
(this app's entities all begin with `Journal*`). You can also access a context's
JSON web services via a direct URL. For example, the URL for the Web Content
app's JSON web services is
[http://localhost:8080/api/jsonws?contextName=journal](http://localhost:8080/api/jsonws?contextName=journal).

+$$$

**Important:** To invoke Liferay services remotely, your Liferay instance must
be configured to allow remote web service access. Please see the
[Understanding Liferay's Service Security Model](/develop/tutorials/-/knowledge_base/7-0/service-security-layers) 
tutorial for details.

$$$

Each entity's available service methods are listed in the left column of the
JSON web services page. To view details about a service method, click it. The
full package path to the service's `*Impl` class is displayed along with the
method's parameters, return type, and possible exceptions. You can also invoke
the service from this page. For example, in the `portal` context click the
`AnnouncementsEntry` entity's `get-entry` method. This brings up that service
method's details page, where you can also invoke the service:

![Figure 1: The JSON web services page for an entity's remote service method also lets you invoke that service.](../../../images/jsonws-details.png)

The only parameter required to invoke the `get-entry` method is an `entryId`. To 
invoke this web service, you could enter an announcement entry's ID in the 
`entryId` field and then click *Invoke*. Liferay returns feedback from each 
invocation that indicates, for example, whether the service invocation succeeded 
or failed. Invoking remote services in this manner is a great way to test your 
app's remote services. 

Service Builder can also make your project's web services available via SOAP
using Apache Axis. After you've built your `*-service` project's WSDD (web 
service deployment descriptor) and deployed your project's modules, its services 
are available on your Liferay server. You can view your Liferay instance's and 
app's SOAP services in a browser as described in the tutorial 
[Creating Remote Services](/develop/tutorials/-/knowledge_base/7-0/creating-remote-services). 

When viewing your SOAP services in a browser, Liferay lists the services 
available for all your entities and provides links to their WSDL documents. For 
example, clicking on the WSDL link for the User service takes you to the 
following URL: 

    http://localhost:8080/api/axis/Portal_UserService?wsdl

This WSDL document lists the entity's SOAP web services. Once the web service's 
WSDL is available, any SOAP web service client can access it. To see examples of 
SOAP web service client implementations, see the tutorial 
[SOAP Web Services](/develop/tutorials/-/knowledge_base/7-0/soap-web-services).

Liferay web services are designed to be invoked by client applications.
Liferay's web services APIs can be accessed by many different kinds of clients,
including non-portlet and even non-Java clients. For information on how to
develop client applications that can access Liferay's JSON web services, please
see the
[Invoking JSON Web Services](/develop/tutorials/-/knowledge_base/7-0/invoking-json-web-services)
tutorial. For information on how to develop
client applications that access Liferay's SOAP web services, please see the
[SOAP Web Services](/develop/tutorials/-/knowledge_base/7-0/soap-web-services)
tutorial. To learn how to create remote web services for your own application,
please refer to the
[Creating Remote Services](/develop/tutorials/-/knowledge_base/7-0/creating-remote-services)
tutorial. 

For more information on Liferay services, see the Liferay Portal CE Javadocs at
[http://docs.liferay.com/portal/7.0/javadocs/](http://docs.liferay.com/portal/7.0/javadocs/).

## Related Topics [](id=related-topics)

[Invoking JSON Web Services](/develop/tutorials/-/knowledge_base/7-0/invoking-json-web-services)

[JSON Web Services Invoker](/develop/tutorials/-/knowledge_base/7-0/json-web-services-invoker)

[JSON Web Services Invocation Examples](/develop/tutorials/-/knowledge_base/7-0/json-web-services-invocation-examples)

[SOAP Web Services](/develop/tutorials/-/knowledge_base/7-0/soap-web-services)

[Creating Remote Services](/develop/tutorials/-/knowledge_base/7-0/creating-remote-services)
