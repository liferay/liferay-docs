# Handling Indexing in the Entry Service Layer [](id=handling-indexing-in-the-entry-service-layer)

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Guestbook Entries<br>Step 2 of 2</p>
</div>

Whenever a guestbook entry is added, updated, or deleted, the corresponding
document should also be updated or deleted. A minor update to each of the
`addEntry`, `updateEntry`, and `deleteEntry` service methods for guestbook
entries is all it takes. 

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
    following method execution. The `EntryIndexer` controls exactly how the 
    indexing happens. Setting the `@Indexable` annotation's type to 
    `IndexableType.REINDEX` updates the document in the index that corresponds 
    to the updated entry. 

2.  Add the `@Indexable(type = IndexableType.DELETE)` annotation above the 
    signature for the `deleteEntry` method. The indexable type 
    `IndexableType.DELETE` ensures that the entry is deleted from the index: 

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

Awesome! Both guestbooks and their entries now have search and indexing support 
in the back-end. Next, you'll enable search in the Guestbook portlet's 
front-end. 
