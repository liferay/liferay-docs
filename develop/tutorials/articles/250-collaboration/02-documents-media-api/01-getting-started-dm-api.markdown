# Getting Started with the Documents and Media API

Before you start using the Documents and Media API, you should make note of the 
main interfaces you'll use. 

-   **Documents and Media Services:** These interfaces expose all the available 
    Documents and Media functionality: 

    -   `DLAppLocalService`: The local service. 
    -   `DLAppService`: The remote service. This service wraps the local service 
        methods in permission checks. 

    Note that Liferay used 
    [Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder) 
    to create these services. Because the remote service contains permission 
    checks, it's 
    [best practice](/develop/tutorials/-/knowledge_base/7-1/creating-remote-services#using-service-builder-to-generate-remote-services) 
    to call the remote service instead of the local service. See the section 
    below for instructions on getting a service reference. 

-   **Entity Interfaces:** These interfaces represent entities in the Documents 
    and Media Library. Here are the primary ones you'll work with: 

        -   `FileEntry`: Represents a file. 
        -   `Folder`: Represents a folder.
        -   `FileShortcut`: Represents a shortcut to a file.

## Getting a Service Reference

Before you can do anything with the Documents and Media API, you must get a 
service reference. 
[Getting a service reference in OSGi](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services) 
is straightforward. For example, here's a reference to `DLAppService`:

    @Reference
    private DLAppService _dlAppService;

Getting the reference via the `@Reference` annotation ensures that you leverage 
OSGi's dependency management features. Although you can access the service via 
its static `*Util` class (e.g., `DLAppServiceUtil` or `DLAppLocalServiceUtil`), 
doing so bypasses OSGi's dependency management features, which could result in a 
`NullPointerException`. Therefore, the only place you should use these `*Util` 
classes is somewhere not managed by OSGi (e.g., JSPs). But even then, you should 
avoid leveraging these `*Util` classes if at all possible. 
