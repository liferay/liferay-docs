# Using the Staged Model Repository Framework [](id=using-the-staged-model-repository-framework)

Leveraging the Staged Model Repository framework in your app is easy once you've
[created staged model repository implementation classes](/develop/tutorials/-/knowledge_base/7-1/implementing-the-staged-model-repository-framework).

You'll step through a quick example to demonstrate leveraging the
`StagedModelRepository` interface in a staged model data handler. The code
snippets originate from Liferay's Bookmarks app and Bookmarks Entries.

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

2.  Call your `_stagedModelRepository` object to leverage its specialized
    staging logic. Now that you have access to CRUD operations via the
    `_stagedModelRepository` object, you can skip the headache of providing a
    slew of parameters and additional functionality in the local service to do
    simple things like add a Bookmarks entry. For example, here's the old way:

        serviceContext.setUuid(entry.getUuid());

        newEntry = _bookmarksEntryLocalService.addEntry(
            userId, portletDataContext.getScopeGroupId(), folderId, entry.getName(), entry.getUrl(), entry.getDescription(), serviceContext);

    Now with access to the entry's staged model repository, updating an entry
    the data handler can use looks like this:

        newEntry = _stagedModelRepository.updateStagedModel(portletDataContext, importedEntry);

    The large number of parameters and UUID setter the local service method
    requires aren't needed when leveraging the staged model repository, because
    the staged model repository abstracts these requirements away from the data
    handler. The `_bookmarksEntryLocalService.addEntry(...)` method is called
    from the `BookmarksEntryStagedModelRepository` class.

Great! You've successfully leveraged your staged model repository from a data
handler!
