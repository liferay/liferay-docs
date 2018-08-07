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
    to call it instead of the local service. See the section below for 
    instructions on getting a service reference. 

-   **Entity Interfaces:** These interfaces represent entities in the Documents 
    and Media Library. Here are the primary ones you'll work with: 

    -   `FileEntry`: Represents a file. 
    -   `Folder`: Represents a folder.
    -   `FileShortcut`: Represents a shortcut to a file.

## Getting a Service Reference

Before you can do anything with the Documents and Media API, you must get a 
service reference. You must use the `@Reference` annotation to 
[get a service reference in an OSGi component via Declarative Services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services). 
For example, this code gets such a reference to `DLAppService`: 

    @Reference
    private DLAppService _dlAppService;

Getting the reference this way ensures that you leverage OSGi's 
[dependency management](/develop/tutorials/-/knowledge_base/7-1/leveraging-dependencies) 
features. If you need to get a reference somewhere outside of an OSGi component 
(e.g., in a JSP), then you should use a 
[service tracker](/develop/tutorials/-/knowledge_base/7-1/service-trackers). 

