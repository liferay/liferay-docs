# Getting Started with the Documents and Media API [](id=getting-started-with-the-documents-and-media-api)

Before you start using the Documents and Media API, you should make note of the 
main interfaces you'll use. 

-   **Documents and Media Services:** These interfaces expose all the available 
    Documents and Media functionality: 

    -   [`DLAppLocalService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppLocalService.html): 
        The local service. 
    -   [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html): 
        The remote service. This service wraps the local service 
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

## Getting a Service Reference [](id=getting-a-service-reference)

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

## Specifying Repositories [](id=specifying-repositories)

Many methods in the Documents and Media API contain a `repositoryId` parameter 
that identifies the Documents and Media repository in which the operation will 
be performed. A site (group) can have multiple repositories, but only one can be 
accessed via the portal UI. This is called the site (group) repository, which is 
effectively a site's default repository. To access this repository via the API, 
you must provide the group ID as the `repositoryId`. 

You can also get the `repositoryId` via file (`FileEntry`), folder (`Folder`), 
and file shortcut (`FileShortcut`) entities. Each of these entities has a 
`getRepositoryId` method that gets the ID of the repository the entity resides 
in. For example, this code gets the repository ID of the `FileEntry` object 
`fileEntry`: 

    long repositoryId = fileEntry.getRepositoryId();

There may also be cases in which you need to get a `Repository` object. You can 
do this by getting a `RepositoryProvider` reference and passing the repository 
ID to its `getRepository` method: 

    @Reference
    private RepositoryProvider repositoryProvider;

    Repository repository = repositoryProvider.getRepository(repositoryId);

Even if you only have an entity ID (e.g., a file ID or folder ID), you can still 
use `RepositoryProvider` to get a `Repository` object. To do this, call the 
`RepositoryProvider` method for the entity type, with the entity ID as its 
argument. For example, this code gets a `Repository` by calling the 
`RepositoryProvider` method `getFolderRepository` with a folder ID: 

    Repository repository = repositoryProvider.getFolderRepository(folderId);

See the 
[`RepositoryProvider` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/RepositoryProvider.html)
for a list of the other methods you can use. 

Note that you can use the 
[`PortletFileRepository`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portletfilerepository/PortletFileRepository.html) 
API to create repositories programmatically. The tutorials here, however, access 
the default site repository for simplicity. 

## Specifying Folders [](id=specifying-folders)

Many API methods require the ID of a folder that they perform operations in or 
on. For example, such methods may contain parameters like `folderId` or 
`parentFolderId`. Also note that you can use the constant 
`DLFolderConstants.DEFAULT_PARENT_FOLDER_ID` to specify the root folder of the 
repository you're working in. 

## Related Topics

[Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder)

[OSGi Services and Dependency Injection with Declarative Services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services)

[Leveraging Dependencies](/develop/tutorials/-/knowledge_base/7-1/leveraging-dependencies)

[Service Trackers](/develop/tutorials/-/knowledge_base/7-1/service-trackers)
