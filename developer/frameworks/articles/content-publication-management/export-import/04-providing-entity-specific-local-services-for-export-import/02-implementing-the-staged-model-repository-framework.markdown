---
header-id: implementing-the-staged-model-repository-framework
---

# Implementing the Staged Model Repository Framework

Providing specialized local services for your app's staging functionality lets
you abstract the additional staging-specific information away from your data
handlers. Before you can begin using the Staged Model Repository framework in
your app, you must implement it.

Below is a quick example that demonstrates implementing the
`StagedModelRepository` interface to use for a staged model. This example
references Liferay's Bookmarks app and Bookmarks Entry entities.

1.  In your app's `-service` bundle, create a package that holds your Staged
    Model Repository classes (e.g.,
    `com.liferay.bookmarks.exportimport.staged.model.repository`). If you do not
    have a `-service` bundle, visit the
    [Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder)
    tutorials for info on generating an app's services. You must have them to
    leverage most Staging features.

2.  Create your `-StagedModelRepository` class in the new package and implement
    the `StagedModelRepository` interface in the class' declaration. For
    example,

        public class BookmarksEntryStagedModelRepository
            implements StagedModelRepository<BookmarksEntry> {

    Be sure also to include the staged model type parameter for this repository
    (e.g., `BookmarksEntry`).

3.  Add an `@Component` annotation for your staged model repository class that
    looks like this:

        @Component(
            immediate = true,
            property = "model.class.name=FULLY_QUALIFIED_MODEL_CLASS",
            service = StagedModelRepository.class
        )

    There are a few annotation attributes you should set:

    - The `immediate` element directs the container to activate the component
      immediately once its provided module has started.
    - The `property` element sets various properties for the component service.
      You must associate the model class you wish to handle with this service so
      it's recognized by the data handlers leveraging it. You'll learn more
      about this later.
    - The `service` element should point to the `StagedModelRepository.class`
      interface.

    The `BookmarksEntryStagedModelRepository`'s `@Component` annotation looks
    like this:

        @Component(
            immediate = true,
            property = "model.class.name=com.liferay.bookmarks.model.BookmarksEntry",
            service = StagedModelRepository.class
        )

4.  Implement the `StagedModelRepository` interface's methods in your staged
    model repository. You can reference the
    [Javadoc](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/staged/model/repository/StagedModelRepository.html)
    for this interface to learn what each method is intended for.

    As an example, you'll step through a couple method implementations to get a
    taste for how it works.

    Implementing the `addStagedModel(...)` method for a Bookmarks entry looks
    like this:

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

    This method sets the user ID and service context based on the
    portlet data context. The
    [`PortletDataContext`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataContext.html)
    is used to populate the LAR file with your application's data during the
    export process. Next it sets the UUID, which is required to differentiate
    staged content between Sites. Lastly, the entity's local service is called.

    Just calling the `BookmarksEntryLocalService.addEntry(...)` method would not
    have been enough to satisfy the staged model data handler's needs (i.e., the
    UUID requirement). With the staged model repository layer, however, you can
    add staging specific requirements on top of the present local services to
    serve your data handlers' needs.

    Not every method implementation requires additional staging information. For
    example, deleting Bookmarks Entries and deleting Bookmarks Entry staged
    models are functionally the same, so your staged model repository's method
    would look like this:

        @Override
        public void deleteStagedModels(PortletDataContext portletDataContext)
            throws PortalException {

            _bookmarksEntryLocalService.deleteEntries(
                portletDataContext.getScopeGroupId(),
                BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);
        }

    Since nothing additional is required for deleting staged models, the staged
    model repository calls the local service's `deleteEntries(...)` method with
    no additional changes.

    Finish implementing the `StagedModelRepository` so it's usable in your data
    handlers.

Awesome! You've implemented the Staged Model Repository framework for your app!
If you're interested in leveraging this framework after the implementation
process, see the
[Using the Staged Model Repository Framework](/develop/tutorials/-/knowledge_base/7-1/using-the-staged-model-repository-framework)
tutorial.
