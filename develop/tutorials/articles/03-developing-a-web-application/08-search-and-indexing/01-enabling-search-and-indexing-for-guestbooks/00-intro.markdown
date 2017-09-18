# Enabling Search and Indexing for Guestbooks [](id=enabling-search-and-indexing-for-guestbooks)

In this section, you first create an indexer for guestbooks. You then modify the 
service layer to use this indexer to update the search index when a guestbook is 
persisted:

1. Create a `GuestbookIndexer` class that extends Liferay's `BaseIndexer`
   abstract class.

2. Update `GuestbookLocalServiceImpl`'s `addGuestbook`, `updateGuestbook`, and
   `deleteGuestbook` methods to invoke the guestbook indexer.

Since there's no reason to search for guestbooks in the UI, only the back-end 
work is necessary. 

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/understanding-search-and-indexing">Let's Go!<span class="icon-circle-arrow-right"></span></a>
