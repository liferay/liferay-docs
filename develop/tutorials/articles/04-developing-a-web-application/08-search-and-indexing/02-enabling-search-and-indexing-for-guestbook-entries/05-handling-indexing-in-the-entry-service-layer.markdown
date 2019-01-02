# Handling Indexing in the Entry Service Layer [](id=handling-indexing-in-the-entry-service-layer)

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Entries<br>Step 5 of 5</p>
</div>

Whenever an Entry is added, updated, or deleted, the corresponding document
should also be updated or deleted. A minor update to each of the `addEntry`,
`updateEntry`, and `deleteEntry` service methods for Entries is all it takes. 

Follow these steps to update the methods: 

1.  Open `EntryLocalServiceImpl` in the `guestbook-service` module's 
    `com.liferay.docs.guestbook.service.impl` package, and add the annotation 
    `@Indexable(type = IndexableType.REINDEX)` above the signature for the 
    `addEntry` and `updateEntry` methods:

        @Indexable(type = IndexableType.REINDEX)
        public Entry addEntry(...)

        @Indexable(type = IndexableType.REINDEX)
        public Entry updateEntry(...)

    The `@Indexable` annotation indicates that an index update is required
    following method execution. The indexing classes control exactly how the
    indexing happens. Setting the `@Indexable` annotation's type to
    `IndexableType.REINDEX` updates the indexed document that corresponds to the
    updated Entry. 

2.  Add the `@Indexable(type = IndexableType.DELETE)` annotation above the 
    signature for the `deleteEntry` method. The indexable type 
    `IndexableType.DELETE` ensures that the Entry is deleted from the index: 

        @Indexable(type = IndexableType.DELETE)
        public Entry deleteEntry(...)

3.  Add the required imports:

        import com.liferay.portal.kernel.search.Indexable;
        import com.liferay.portal.kernel.search.IndexableType;

    Save the file. 

4.  In the Gradle Tasks pane on the right-hand side of Liferay @ide@, 
    double-click `buildService` in `guestbook-service` &rarr; `build`. This 
    re-runs Service Builder to incorporate your changes to 
    `EntryLocalServiceImpl`. 

Guestbooks and their Entries now have search and indexing support in the
back-end. Next, you'll enable search in the Guestbook portlet's front-end. 
