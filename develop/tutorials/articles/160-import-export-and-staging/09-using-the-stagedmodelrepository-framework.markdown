# Providing Entity-Specific Local Services for Staging

When creating your data handlers, you must leverage your app's local services to
perform Staging-related tasks for its entities. When the Staging framework
operates on entities (i.e., staged models), it often cannot manage important
information from the entity's local services alone; instead, you're forced to
reinvent basic functionality so the Staging framework can access it. This is
caused by services not sharing a common ancestor (i.e., interface or base
class).

The *Staged Model Repository* framework removes this barrier by linking an app's
staged model to a local service. This lets the Staging framework call a
staged model repository independently based on which entity is currently being
processed. This gives you access to entity-specific methods tailored
specifically for the staged model data you're handling.

[Image: (StagedModel) -> StagedModelRepository -> LocalService]

So what kind of *entity-specific* methods are we talking about here? Your data
handlers only expose a specific set of actions, like export and import methods.
The Staged Model Repository framework provides CRUD operations for a specific
staged model that are not exposed using local services.

Before you can leverage the Staged Model Repository framework, you must
implement the
[StagedModelRepository](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/staged/model/repository/StagedModelRepository.html)
interface. You'll step through a quick example to demonstrate implementing the
`StagedModelRepository` interface to use for a staged model. This example
references Liferay's Bookmarks app, specifically, for Bookmarks Entry
entities.

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

    Be sure to also include the staged model type parameter this repository
    should operate on (e.g., `BookmarksEntry`).

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
    is used to populate the LAR file with your application’s data during the
    export process. Next it sets the UUID, which is required to differentiate
    staged content between sites. Lastly, the entity's local service is called.

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

Awesome! You've implemented the Staged Model Repository framework for your app!
If you're interested in leveraging this framework after the implementation
process, see the
[Leveraging Entity-Specific Local Services for Staging]()
tutorial.

Now you'll use the Staged Model Repository framework in a staged model
data handler. The code snippets originate from Liferay's Bookmarks app,
specifically, for Bookmarks Entry entities.

1.  Create a getter and setter method to make a `StagedModelRepository` object
    available for the `BookmarksEntry` entity:

        @Override
        protected StagedModelRepository<BookmarksEntry> getStagedModelRepository() {
            return _stagedModelRepository;
        }

        @Reference(
            target = "(model.class.name=com.liferay.bookmarks.model.BookmarksEntry)",
            unbind = "-"
        )
        protected void setStagedModelRepository(
            StagedModelRepository<BookmarksEntry> stagedModelRepository) {

            _stagedModelRepository = stagedModelRepository;
        }

        private StagedModelRepository<BookmarksEntry> _stagedModelRepository;

    This instantiates a `_stagedModelRepository` object that the staged model
    data handler can use to access `BookmarksEntry` CRUD operations. Notice that
    the setter method's `@Reference` annotation specifies the fully qualified
    class name of the model.

2.  Now that you have access to CRUD operations via the `_stagedModelRepository`
    object, you can skip the headache of providing a slew of parameters to the
    local service to, for example, update a Bookmarks
    entry. For instance, the following code snippet exemplifies updating an
    existing imported entry only using the provided local services:

        importedEntry = _bookmarksEntryLocalService.updateEntry(
            userId, existingEntry.getEntryId(), portletDataContext.getScopeGroupId(), folderId, entry.getName(), entry.getUrl(), entry.getDescription(),	serviceContext);

    Now with access to the entry's staged model repository, updating an entry
    usable by the data handler would look like this:

        importedEntry = _stagedModelRepository.updateStagedModel(portletDataContext, importedEntry);

The large number of parameters required for using the local service method are
not required when leveraging the staged model repository. This is because the
staged model repository abstracts these requirements away from the data
handlers. The `_bookmarksEntryLocalService.updateEntry(...)` method is called
from the `BookmarksEntryStagedModelRepository` class.

The staged model repository does not avoid using your app's local services. It
only provides an additional layer that provides Staging-specific functionality.
So how does this work? A brief Staging process outline is outlined below:

- `*StagedModelDataHandler` deserializes the provided
  [LAR file's](/develop/tutorials/-/knowledge_base/7-1/understanding-data-handlers#liferay-archive-lar-file)
  XML into a model.
- `*StagedModelRepository` updates the model based on the environment and
  business logic, providing entity-specific CRUD operations for Staging purposes
  (e.g., UUID manipulation).
- Local services are called from the `*StagedModelRepository` and handles the
  remainder of the process.
