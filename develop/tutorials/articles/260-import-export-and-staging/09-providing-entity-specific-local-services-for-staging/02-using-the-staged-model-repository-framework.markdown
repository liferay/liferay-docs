# Using the Staged Model Repository Framework [](id=using-the-staged-model-repository-framework)

Leveraging the Staged Model Repository framework in your app is easy once you've
[created staged model repository implementation classes](/develop/tutorials/-/knowledge_base/7-1/implementing-the-staged-model-repository-framework).

You'll step through a quick example to demonstrate leveraging the
`StagedModelRepository` interface in a staged model data handler. The code
snippets originate from Liferay's Bookmarks app, specifically, for Bookmarks
Entries.

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
    data handler can use to access `BookmarksEntry` CRUD operations. Notice the
    setter method's `@Reference` annotation. This injects the component service
    of the `BookmarksEntryStagedModelRepository` into the
    `_stagedModelRepository` object. The component service was created in the
    [Implementing the Staged Model Repository Framework](/develop/tutorials/-/knowledge_base/7-1/implementing-the-staged-model-repository-framework)
    tutorial when setting the `@Component` annotation for the staged model
    repository.

2.  Now that you have access to CRUD operations via the `_stagedModelRepository`
    object, you can skip the headache of providing a slew of parameters and
    additional functionality to the local service to, for example, add a
    Bookmarks entry. For instance, the following code snippet exemplifies
    adding a Bookmarks Entry only using the provided local services:

        serviceContext.setUuid(entry.getUuid());

        newEntry = _bookmarksEntryLocalService.addEntry(
            userId, portletDataContext.getScopeGroupId(), folderId, entry.getName(), entry.getUrl(), entry.getDescription(), serviceContext);

    Now with access to the entry's staged model repository, updating an entry
    usable by the data handler would look like this:

        newEntry = _stagedModelRepository.updateStagedModel(portletDataContext, importedEntry);

The large number of parameters and UUID setter required for using the local
service method are not required when leveraging the staged model repository.
This is because the staged model repository abstracts these requirements away
from the data handler. The `_bookmarksEntryLocalService.addEntry(...)` method
is called from the `BookmarksEntryStagedModelRepository` class.

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

Great! You've successfully leveraged your staged model repository from a data
handler!
