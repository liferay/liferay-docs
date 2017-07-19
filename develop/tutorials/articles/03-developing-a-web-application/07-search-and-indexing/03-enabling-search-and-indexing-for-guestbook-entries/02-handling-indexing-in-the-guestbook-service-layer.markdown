# Handling Indexing in the Guestbook Service Layer [](id=handling-indexing-in-the-guestbook-service-layer)

You need to make sure that whenever a guestbook is added, updated, or deleted,
its index is updated accordingly. To accomplish this, you need to update each of
the `addGuestbook`, `updateGuestbook`, and `deleteGuestbook` service methods for
guestbooks.

1.  Open `GuestbookLocalServiceImpl` in the `guestbook-service` module's 
    `com.liferay.docs.guestbook.service.impl` package and add the following 
    annotation above the method signature for the `addGuestbook` and 
    `updateGuestbook` methods:

        @Indexable(type = IndexableType.REINDEX)
        public Entry addGuestbook(...)

        @Indexable(type = IndexableType.REINDEX)
        public Entry updateGuestbook(...)

2.  Add the following annotation above the method signature for the  
    `deleteGuestbook` method:

        @Indexable(type = IndexableType.DELETE)
        public Entry deleteGuestbook(...)

3.  Finally, add the following imports:

        import com.liferay.portal.kernel.search.Indexable;
        import com.liferay.portal.kernel.search.IndexableType;

These updates are virtually identical to the ones you made to the Guestbook 
entry service layer. If you have questions about them, please refer to the 
explanation for the Guestbook entry service layer update that appeared earlier 
in this Learning Path.

Next you can update your service XML.