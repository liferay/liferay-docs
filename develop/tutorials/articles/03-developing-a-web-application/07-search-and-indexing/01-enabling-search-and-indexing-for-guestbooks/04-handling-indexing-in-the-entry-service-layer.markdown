# Handling Indexing in the Entry Service Layer [](id=handling-indexing-in-the-entry-service-layer)

Whenever a guestbook entry is added, updated, or deleted, the guestbook entry
index should also be updated. To accomplish this, you'll update each of the
`addEntry`, `updateEntry`, and `deleteEntry` service methods for guestbook
entries. Open `EntryLocalServiceImpl` and add the following annotation to the
top of the `addEntry` and `updateEntry` methods:

    @Indexable(type = IndexableType.REINDEX)

The `@Indexable` annotation indicates that the method should result in an index 
update. Your `EntryIndexer` class has a `getClassName` method which returns a 
`String` containing `Entry.class.getName()`. So
`IndexerRegistryUtil.nullSafeGetIndexer(Entry.class)` unambiguously specifies
your indexer. Setting the `@Indexable` annotation `type` to 
`IndexableType.REINDEX` updates the document in the index that corresponds to 
the updated guestbook entry.

Finally, add the following annotation to the top of the `deleteEntry` method:

    @Indexable(type = IndexableType.DELETE)

Here, you want to remove the entry, so you set the indexable type to 
`IndexableType.DELETE` instead of `IndexableType.REINDEX`.