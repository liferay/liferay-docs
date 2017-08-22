# Handling Indexing in the Entry Service Layer [](id=handling-indexing-in-the-entry-service-layer)

Whenever a guestbook entry is added, updated, or deleted, the corresponding
document should also be updated or deleted. A minor update to each of the
`addEntry`, `updateEntry`, and `deleteEntry` service methods for guestbook
entries is all it takes.

Follow these steps to update the methods:

1.  Open `EntryLocalServiceImpl` in the `guestbook-service` module's 
    `com.liferay.docs.guestbook.service.impl` package and add the 
    `@Indexable(type = IndexableType.REINDEX)` annotation above the method 
    signature for the `addEntry` and `updateEntry` methods:

        @Indexable(type = IndexableType.REINDEX)
        public Entry addEntry(...)

        @Indexable(type = IndexableType.REINDEX)
        public Entry updateEntry(...)

    The `@Indexable` annotation indicates that an index update is required
    following the method execution. The `EntryIndexer` controls exactly how the
    indexing happens. Setting the `@Indexable` annotation type to
    `IndexableType.REINDEX` updates the document in the index that corresponds
    to the updated entry.

2.  Add the `@Indexable(type = IndexableType.DELETE)` annotation above the 
    method signature for the `deleteEntry` method. Here, you want to remove the 
    entry, so you set the indexable type to `IndexableType.DELETE` instead of 
    `IndexableType.REINDEX`:

        @Indexable(type = IndexableType.DELETE)
        public Entry deleteEntry(...)

    When an entry is deleted from the database, this deletes it from the index.

3.  Finally, add the required imports:

        import com.liferay.portal.kernel.search.Indexable;
        import com.liferay.portal.kernel.search.IndexableType;

Next you can update your service XML.
