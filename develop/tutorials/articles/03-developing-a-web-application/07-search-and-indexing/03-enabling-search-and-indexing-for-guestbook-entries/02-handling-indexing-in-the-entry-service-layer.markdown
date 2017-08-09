# Handling Indexing in the Entry Service Layer [](id=handling-indexing-in-the-entry-service-layer)

Whenever a guestbook entry is added, updated, or deleted, the guestbook entry
index should also be updated. To accomplish this, you'll update each of the
`addEntry`, `updateEntry`, and `deleteEntry` service methods for guestbook
entries.

Follow these steps to update the methods:

1.  Open `EntryLocalServiceImpl` in your `guestbook-service` module's 
    `com.liferay.docs.guestbook.service.impl` package and add the 
    `@Indexable(type = IndexableType.REINDEX)` annotation above the method 
    signature for the `addEntry` and `updateEntry` methods:

        @Indexable(type = IndexableType.REINDEX)
        public Entry addEntry(...)

        @Indexable(type = IndexableType.REINDEX)
        public Entry updateEntry(...)

    The `@Indexable` annotation indicates that the method should result in an 
    index update. Your `EntryIndexer` class has a `getClassName` method which 
    returns a `String` containing `Entry.class.getName()`. So
    `IndexerRegistryUtil.nullSafeGetIndexer(Entry.class)` unambiguously 
    specifies your indexer. Setting the `@Indexable` annotation `type` to 
    `IndexableType.REINDEX` updates the document in the index that corresponds 
    to the updated guestbook entry.

2.  Add the `@Indexable(type = IndexableType.DELETE)` annotation above the 
    method signature for the `deleteEntry` method. Here, you want to remove the 
    entry, so you set the indexable type to `IndexableType.DELETE` instead of 
    `IndexableType.REINDEX`:

        @Indexable(type = IndexableType.DELETE)
        public Entry deleteEntry(...)
    
3.  Finally, add the following imports:

        import com.liferay.portal.kernel.search.Indexable;
        import com.liferay.portal.kernel.search.IndexableType;
        
Next you can update your service XML.