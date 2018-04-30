# Deciding to Create Local and Remote Services [](id=deciding-to-create-local-and-remote-services)

[Defining your object model](/develop/tutorials/-/knowledge_base/7-1/defining-an-object-relational-map-with-service-builder)
involves choosing whether to generate local and or remote service interfaces.
Local services can only be invoked from the Liferay server on which they're
deployed. Remote services are accessible to clients outside of the Liferay
server. Before implementing local or remote services, consider the best
practices described here:

1.  If you plan to have remote services, enable local services too.

2.  Implement your business logic in `*LocalServiceImpl`. 

3.  [Create corresponding remote services methods](/develop/tutorials/-/knowledge_base/7-1/creating-remote-services)
    in your `*ServiceImpl`.

4.  Use the remote service methods to call the local service, wrapping the calls
    in permission checks. 

5.  In your application, call only the remote services. This ensures that
    your service methods are secured and that you don't have to duplicate
    permissions code.

If you are turning on local or remote services in your `service.xml` file just
now, make sure to
[run Service Builder](/develop/tutorials/-/knowledge_base/7-1/running-service-builder)
again to generate the service interfaces. 
