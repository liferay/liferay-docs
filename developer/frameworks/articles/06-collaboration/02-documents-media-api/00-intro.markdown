---
header-id: documents-and-media-api
---

# Documents and Media API

A powerful API underlies the 
[Documents and Media library](/discover/portal/-/knowledge_base/7-2/managing-documents-and-media). 
You can leverage this API in your own apps. For example, you could create an app 
that lets users upload files to the Documents and Media library. Your app could 
even let users update, delete, and copy files. 

Here, you'll learn how to use the Documents and Media library's API. Note that 
this is a large API and it may seem daunting at first. To keep backwards 
compatibility, the API has different entry points and multiple methods or 
classes with similar functionality. Fortunately, you don't need to learn all of 
them. The content here focuses on the API's most useful classes and methods. 

Also note that the Documents and Media app is itself a consumer of this 
API---Liferay's developers used the API to implement the app's functionality.
Therefore, code from this app is used as an example of how to use the API. 

## Getting Started with the Documents and Media API

Before you start using the Documents and Media API, you must learn these things: 

[**Key Interfaces:**](#key-interfaces) 
The interfaces you'll use most while using the API. 

[**Getting a Service Reference:**](#getting-a-service-reference) 
A service reference is required for calling the API's services. 

[**Specifying Repositories:**](#specifying-repositories) 
How to specify which Documents and Media repository to work with. 

[**Specifying Folders:**](#specifying-folders)
How to specify which Documents and Media folder to work with. 

## Key Interfaces

The Documents and Media API contains several key interfaces: 

**Documents and Media Services:** These interfaces expose all the available 
Documents and Media functionality: 

-   [`DLAppLocalService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppLocalService.html): 
    The local service. 
-   [`DLAppService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html): 
    The remote service. This service wraps the local service methods in 
    permission checks. 

    Note that Liferay used 
    [Service Builder](/develop/tutorials/-/knowledge_base/7-2/service-builder) 
    to create these services. Because the remote service contains permission 
    checks, it's a 
    [best practice](/develop/tutorials/-/knowledge_base/7-2/creating-remote-services#using-service-builder-to-generate-remote-services) 
    to call it instead of the local service. See below for instructions on 
    getting a service reference. 

**Entity Interfaces:** These interfaces represent entities in the Documents and 
Media library. Here are the primary ones you'll use: 

-   `FileEntry`: Represents a file. 
-   `Folder`: Represents a folder. 
-   `FileShortcut`: Represents a shortcut to a file. 

## Getting a Service Reference

Before you can do anything with the Documents and Media API, you must get a 
service reference. If you're using OSGi modules, use the `@Reference` annotation 
to 
[get a service reference in an OSGi component via Declarative Services](/develop/tutorials/-/knowledge_base/7-2/osgi-services-and-dependency-injection-with-declarative-services).
For example, this code gets a reference to `DLAppService`: 

    @Reference
    private DLAppService _dlAppService;

If you're using a standard web module (WAR file), use a 
[Service Tracker](/develop/tutorials/-/knowledge_base/7-2/service-trackers) 
to get a reference to the service instead. 

Getting the reference this way ensures that you leverage OSGi's 
[dependency management](/develop/tutorials/-/knowledge_base/7-2/leveraging-dependencies)
features. If you must use the Documents and Media services outside of an OSGi
component (e.g., in a JSP), then you can use the services' static `*Util` 
classes: 

-   [`DLAppServiceUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppServiceUtil.html)
-   [`DLAppLocalServiceUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppLocalServiceUtil.html)

## Specifying Repositories

Many methods in the Documents and Media API contain a `repositoryId` parameter
that identifies the Documents and Media repository where the operation is
performed. A Site (group) can have multiple repositories, but only one can be
accessed via the portal UI. This is called the Site repository, which is 
effectively a Site's default repository. To access this repository via the API, 
provide the group ID as the `repositoryId`. 

You can also get the `repositoryId` via file (`FileEntry`), folder (`Folder`), 
and file shortcut (`FileShortcut`) entities. Each of these entities has a 
`getRepositoryId` method that gets its repository's ID. For example, this code 
gets the repository ID of the `FileEntry` object `fileEntry`: 

    long repositoryId = fileEntry.getRepositoryId();

There may also be cases that require a `Repository` object. You can get one by 
creating a `RepositoryProvider` reference and passing the repository ID to its 
`getRepository` method: 

    @Reference
    private RepositoryProvider repositoryProvider;

    Repository repository = repositoryProvider.getRepository(repositoryId);

Even if you only have an entity ID (e.g., a file or folder ID), you can still 
use `RepositoryProvider` to get a `Repository` object. To do so, call the 
`RepositoryProvider` method for the entity type with the entity ID as its 
argument. For example, this code gets a folder's `Repository` by calling the 
`RepositoryProvider` method `getFolderRepository` with the folder's ID: 

    Repository repository = repositoryProvider.getFolderRepository(folderId);

See the `RepositoryProvider` 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/RepositoryProvider.html) 
for a list of the methods for other entity types. 

Note that there are ways to create repositories programmatically, including 
repositories private to specific apps. For simplicity, however, the examples 
here access the default site repository. 

## Specifying Folders

Many API methods require the ID of a folder that they perform operations in or 
on. For example, such methods may contain parameters like `folderId` or 
`parentFolderId`. Also note that you can use the constant 
`DLFolderConstants.DEFAULT_PARENT_FOLDER_ID` to specify the root folder of your 
current repository. 
