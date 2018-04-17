# Providing Entity-Specific Local Services for Staging

When creating your data handlers, you must leverage your app's local services to
perform Staging-related tasks for its entities. When the Staging framework
operates on entities (i.e., staged models), it doesn't have the information
about the entity's local services by default; instead, you're forced to reinvent
basic functionality so the Staging framework can access it. This is caused by
services not sharing a common ancestor (i.e., interface or base class).

The *Staged Model Repository* framework removes this barrier by linking an app's
staged model to a local service. This lets the Staging framework call a
staged model repository independently based on which entity is currently being
processed. This gives you access to entity-specific methods tailored
specifically for the staged model data you're handling.

[Image: (StagedModel) -> StagedModelRepository -> LocalService]

So what kind of *entity-specific* methods are we talking about here? Your data
handlers only expose a specific sets of actions, like export and import methods.
The Staged Model Repository framework provides CRUD operations for a specific
staged model that are not exposed using local services.

<!-- Set up SMR classes in -service bundle. -->

Before you can leverage the  Staged Model Repository framework, you must
implement the
[StagedModelRepository](@app-ref@/web-experience/latest/javadocs/com/liferay/exportimport/staged/model/repository/StagedModelRepository.html)
interface.

You'll step through a quick example below to demonstrate using the
Staged Model Repository framework in a staged model data handler. The code
snippets originate from Liferay's Bookmarks app. Specifically, its staged model
data handler for Bookmarks entries.

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









