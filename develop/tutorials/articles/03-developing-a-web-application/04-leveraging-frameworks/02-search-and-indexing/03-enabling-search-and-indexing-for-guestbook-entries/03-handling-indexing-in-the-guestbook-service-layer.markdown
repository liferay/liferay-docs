# Handling Indexing in the Guestbook Service Layer [](id=handling-indexing-in-the-guestbook-service-layer)

You need to make sure that whenever a guestbook is added, updated, or deleted,
its index is updated accordingly. To accomplish this, you need to update each of
the `addGuestbook`, `updateGuestbook`, and `deleteGuestbook` service methods for
guestbooks. Open `GuestbookLocalServiceImpl` and add the following annotation to 
the top of the `addEntry` and `updateEntry` methods:

    @Indexable(type = IndexableType.REINDEX)

Finally, add the following annotation to the top of the `deleteGuestbook` method:

    @Indexable(type = IndexableType.DELETE)

These updates are virtually identical to the ones you made to the Guestbook 
entry service layer. If you have questions about them, please refer to the 
explanation for the Guestbook entry service layer update that appeared earlier 
in this Learning Path.