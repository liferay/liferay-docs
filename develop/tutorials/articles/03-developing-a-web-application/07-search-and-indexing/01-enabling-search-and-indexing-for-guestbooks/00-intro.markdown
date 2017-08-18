# Enabling Search and Indexing for Guestbooks [](id=enabling-search-and-indexing-for-guestbooks)

Enable search and indexing for guestbook entities in two steps:

1. Create a `GuestbookIndexer` class that extends Liferay's `BaseIndexer`
   abstract class.

2. Update `GuestbookLocalServiceImpl`'s `addGuestbook`, `updateGuestbook`, and
   `deleteGuestbook` methods to invoke the guestbook indexer.

In this section, you're just making sure that the proper fields on guestbook
documents are indexed, and then you're modifying the service layer to update the
search index when you persist the entity. Since there's no real reason to search
for guestbooks in the UI, only the back-end work is necessary. You'll get to
implement UI updates to enable searching for guestbook entries at the end of the
Search and Indexing section.
