---
header-id: handling-indexing-in-the-entry-service-layer
---

# Handling Indexing in the Entry Service Layer

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Enabling Search and Indexing for Entries</p><p>Step 5 of 5</p>
</div>

Whenever a Guestbook entry is added, updated, or deleted, the corresponding
document should also be updated or deleted. A minor update to each of the
`addEntry`, `updateEntry`, and `deleteEntry` service methods for Entries is all
it takes. 

Follow these steps to update the methods: 

1.  Open `GuestbookEntryLocalServiceImpl` in the `guestbook-service` module's
    `com.liferay.docs.guestbook.service.impl` package, and add the annotation
    `@Indexable(type = IndexableType.REINDEX)` above the signature for the
    `addGuestbookEntry` and `updateGuestbookEntry` methods:

    ```java
    @Indexable(type = IndexableType.REINDEX)
    public GuestbookEntry addGuestbookEntry(...)

    @Indexable(type = IndexableType.REINDEX)
    public GuestbookEntry updateGuestbookEntry(...)
    ```

    The `@Indexable` annotation indicates that an index update is required
    following method execution. The indexing classes control exactly how the
    indexing happens. Setting the `@Indexable` annotation's type to
    `IndexableType.REINDEX` updates the indexed document that corresponds to the
    updated `GuestbookEntry`. 

2.  Add the `@Indexable(type = IndexableType.DELETE)` annotation above the 
    signature for the `deleteEntry` method. The indexable type 
    `IndexableType.DELETE` ensures that the `GuestbookEntry` is deleted from the
    index: 

    ```java
    @Indexable(type = IndexableType.DELETE)
    public GuestbookEntry deleteGuestbookEntry(...)
    ```

3.  Use Ctrl-Shift-O to add the required imports:

    ```java
    import com.liferay.portal.kernel.search.Indexable;
    import com.liferay.portal.kernel.search.IndexableType;
    ```

    Save the file. 

4.  In the Gradle Tasks pane on the right-hand side of Liferay @ide@, 
    double-click `buildService` in `guestbook-service` &rarr; `build`. This 
    re-runs Service Builder to incorporate your changes to 
    `GuestbookEntryLocalServiceImpl`. 

Guestbooks and their entries now have search and indexing support in the
back-end. Next, you'll enable search in the Guestbook portlet's front-end. 
