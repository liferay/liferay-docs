---
header-id: implementing-the-staged-model-repository-framework
---

# Implementing the Staged Model Repository Framework

[TOC levels=1-4]

In this article, you'll step through a quick example that demonstrates
implementing the `StagedModelRepository` interface to use for a staged model.
This example references Liferay's Bookmarks app and Bookmarks Entry entities.

1.  In your app's `-service` bundle, create a package that holds your Staged
    Model Repository classes (e.g.,
    `com.liferay.bookmarks.exportimport.staged.model.repository`). If you do not
    have a `-service` bundle, visit the
    [Service Builder](/developer/frameworks/-/knowledge_base/7-2/service-builder)
    articles for info on generating an app's services. You must have them to
    leverage most Export/Import and Staging features.

2.  Create your `-StagedModelRepository` class in the new package and implement
    the `StagedModelRepository` interface in the class' declaration. For
    example,

    ```java
    public class BookmarksEntryStagedModelRepository
        implements StagedModelRepository<BookmarksEntry> {
    ```

    Be sure also to include the staged model type parameter for this repository
    (e.g., `BookmarksEntry`).

3.  Add an `@Component` annotation for your staged model repository class that
    looks like this:

    ```java
    @Component(
        immediate = true,
        property = "model.class.name=com.liferay.bookmarks.model.BookmarksEntry",
        service = StagedModelRepository.class
    )
    ```

4.  Implement the `StagedModelRepository` interface's methods in your staged
    model repository. You can reference the
    [Javadoc](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/staged/model/repository/StagedModelRepository.html)
    for this interface to learn what each method is intended for.

    As an example, you'll set a couple method implementations to get a taste for
    how it works.

5.  Implement the `addStagedModel(...)` method. The Bookmarks entry example
    looks like this:

    ```java
    @Override
    public BookmarksEntry addStagedModel(
            PortletDataContext portletDataContext,
            BookmarksEntry bookmarksEntry)
        throws PortalException {

        long userId = portletDataContext.getUserId(
            bookmarksEntry.getUserUuid());

        ServiceContext serviceContext = portletDataContext.createServiceContext(
            bookmarksEntry);

        if (portletDataContext.isDataStrategyMirror()) {
            serviceContext.setUuid(bookmarksEntry.getUuid());
        }

        return _bookmarksEntryLocalService.addEntry(
            userId, bookmarksEntry.getGroupId(), bookmarksEntry.getFolderId(),
            bookmarksEntry.getName(), bookmarksEntry.getUrl(),
            bookmarksEntry.getDescription(), serviceContext);
    }
    ```

    This provides the UUID for the local service.

6.  Not every method implementation requires additional staging information.
    Implementing the `deleteStagedModels` method calls the local service
    directly.

    ```java
    @Override
    public void deleteStagedModels(PortletDataContext portletDataContext)
        throws PortalException {

        _bookmarksEntryLocalService.deleteEntries(
            portletDataContext.getScopeGroupId(),
            BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);
    }
    ```

7.  Finish implementing the `StagedModelRepository` so it's usable in your data
    handlers.

Awesome! You've implemented the Staged Model Repository framework for your app!
If you're interested in leveraging this framework after the implementation
process, see the
[Using the Staged Model Repository Framework](/developer/frameworks/-/knowledge_base/7-2/using-the-staged-model-repository-framework)
article.
