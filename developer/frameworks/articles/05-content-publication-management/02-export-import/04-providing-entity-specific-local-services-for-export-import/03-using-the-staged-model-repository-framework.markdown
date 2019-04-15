---
header-id: using-the-staged-model-repository-framework
---

# Using the Staged Model Repository Framework

[TOC levels=1-4]

Leveraging the Staged Model Repository framework in your app is easy once you've
[created staged model repository implementation classes](/docs/7-2/frameworks/-/knowledge_base/frameworks/implementing-the-staged-model-repository-framework).

You'll step through a quick example to demonstrate leveraging the
`StagedModelRepository` interface in a staged model data handler. The code
snippets originate from Liferay's Bookmarks app and Bookmarks Entries.

1.  Create a getter and setter method to make a `StagedModelRepository` object
    available for the `BookmarksEntry` entity:

    ```java
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
    ```

2.  Call your `_stagedModelRepository` object to leverage its specialized
    export/import logic. For example,

    ```java
    newEntry = _stagedModelRepository.updateStagedModel(portletDataContext, importedEntry);
    ```

    Without the staged model repository logic, you would've called your local
    service like this:

    ```java
    serviceContext.setUuid(entry.getUuid());

    newEntry = _bookmarksEntryLocalService.addEntry(
        userId, portletDataContext.getScopeGroupId(), folderId, entry.getName(), entry.getUrl(), entry.getDescription(), serviceContext);
    ```

    The large number of parameters and UUID setter the local service method
    requires aren't needed when leveraging the staged model repository.

Great! You've successfully leveraged your staged model repository from a data
handler!
