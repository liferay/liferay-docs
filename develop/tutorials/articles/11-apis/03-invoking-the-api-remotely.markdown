# Invoking the API Remotely [](id=invoking-the-api-remotely)

*Remote* clients run outside of the portal JVM or on a remote machine, but they
can still access Liferay's service APIs. The main benefit of remotely accessing
service APIs is that security checks are performed. Unless you want to avoid
permission checking, develop your client (even if it's local) so it triggers the
front-end security layer.

<!-- We should not be making this argument. The developer calling the service
actually has no idea if the implementation of the service checks permissions or
not (unless, of course, he/she goes and looks at the source code). There could
be a permission check in the service, and we automatically generate one if the
service uses the filterFindBy method from Service Builder. 

There are benefits and drawbacks to using the remote services. Benefits:
services can be called from outside the portal; services can be invoked through
JavaScript; remote services can reply with JSON objects. Drawbacks: remote
services are harder to call because there's no context; there's more overhead
(i.e., memory, processing) in invoking a remote service; remote server
permission has to be configured ahead of time to prevent just anyone invoking a
remote service. -Rich -->

Liferay's API follows a Service Oriented Architecture
[(SOA)](http://en.wikipedia.org/wiki/Service-oriented_architecture). The API
supports Java invocation and a variety of protocols including SOAP and JSON over
HTTP. A limited set of *RESTful* web services, based on the AtomPub protocol,
are also supported--see the [Portal Atom Collections](http://www.liferay.com/community/wiki/-/wiki/Main/Portal+Atom+Collections)
wiki by Igor Spasi&#263; for more details. You can also use the API through
Remote Procedure Calls
([RPC](http://en.wikipedia.org/wiki/Remote_procedure_call)). You have many good
options for leveraging Liferay's API. 

Let's step back now and discuss the security layers of Liferay's *service
oriented* architecture and how you can configure them. 
