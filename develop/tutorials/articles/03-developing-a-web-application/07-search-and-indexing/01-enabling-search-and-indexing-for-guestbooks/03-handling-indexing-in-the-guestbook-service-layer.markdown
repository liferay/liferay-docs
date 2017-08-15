# Handling Indexing in the Guestbook Service Layer [](id=handling-indexing-in-the-guestbook-service-layer)

Whenever a guestbook database entity is added, updated, or deleted, the search
index must be updated accordingly. There's a convenient set of annotations in
@product@, `@Indexable` and `@IndexableType`, that mark your service methods, so
that documents can be updated or deleted. For the guestbook, you need to update
each of the `addGuestbook`, `updateGuestbook`, and `deleteGuestbook` service
methods.

1.  Open `GuestbookLocalServiceImpl` in the `guestbook-service` module's 
    `com.liferay.docs.guestbook.service.impl` package and add the following 
    annotation above the method signature for the `addGuestbook` and 
    `updateGuestbook` methods:

        @Indexable(type = IndexableType.REINDEX)
        public Guestbook addGuestbook(...)

        @Indexable(type = IndexableType.REINDEX)
        public Guestbook updateGuestbook(...)

    The `@Indexable` annotation indicates that an index update is required
    following the method execution. Your `GuestbbokIndexer` controls exactly
    how the indexing happens, of course. Setting the `@Indexable` annotation
    type to `IndexableType.REINDEX` updates the document in the index that
    corresponds to the updated guestbook.

2.  Add the following annotation above the method signature for the 
    `deleteGuestbook` method:

        @Indexable(type = IndexableType.DELETE)
        public Guestbook deleteGuestbook(...)

    When a guestbook is deleted from the database, its document shouldn't
    remain in the search index. This ensures that it will be deleted.

3.  Finally, add the necessary imports:

        import com.liferay.portal.kernel.search.Indexable;
        import com.liferay.portal.kernel.search.IndexableType;

Next you can update your service XML.
