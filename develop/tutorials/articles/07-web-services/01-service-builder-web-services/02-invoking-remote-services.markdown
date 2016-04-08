# Invoking Remote Services

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
via a convenient web interface. You can view all the JSON web services in your 
Liferay instance at 
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws). By 
default, this page shows the JSON web services in the `portal` context. You can 
select a different context in the *Context Name* selector menu. For example, 
selecting `journal` in *Context Name* shows you the JSON web services in 
Liferay's Blogs app (this app's entities all begin with `Journal*`). You can 
also access a context's JSON web services via a direct URL. For example, the URL 
for the Blogs app's JSON web services is 
[http://localhost:8080/api/jsonws?contextName=journal](http://localhost:8080/api/jsonws?contextName=journal).

Each entity's available service methods are listed in the left column of the 
JSON web services page. To view details about a service method, click it. You 
can also invoke the service from this page. For example, in the `portal` context 
click the `AnnouncementsEntry` entity's `get-entry` method. This brings up that 
service method's details page, where you can also invoke the service:

![Figure 1: The JSON web services page for an entity's remote service method also lets you invoke that service.](../../../images/jsonws-details.png)

The only parameter required to invoke the `get-entry` method is an `entryId`. To 
invoke this web service, you could enter an announcement entry's ID in the 
`entryId` field and then click *Invoke*. Liferay returns feedback from each 
invocation that indicates, for example, whether the service invocation succeeded 
or failed. Invoking remote services in this manner is a great way to test your 
app's remote services. 

Service Builder can also make your project's web services available via SOAP
using Apache Axis. After you've built your `*-service` project's WSDD (web 
service deployment descriptor) and deployed your project's modules, its 
services are available on your Liferay server. You can view your portal's and 
app's SOAP services in a browser as described in 
[the tutorial on creating remote services](https://www.liferay.com/). 

When viewing your SOAP services in a browser, Liferay lists the services 
available for all your entities and provides links to their WSDL documents. For 
example, clicking on the WSDL link for the User service takes you to the 
following URL: 

    http://localhost:8080/api/axis/Portal_UserService?wsdl

This WSDL document lists the entity's SOAP web services. Once the web service's 
WSDL is available, any SOAP web service client can access it. To see examples of 
SOAP web service client implementations, see 
[the tutorial on SOAP web services](https://www.liferay.com/). 
