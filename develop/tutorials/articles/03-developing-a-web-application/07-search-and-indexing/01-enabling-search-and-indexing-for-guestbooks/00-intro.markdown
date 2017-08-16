# Enabling Search and Indexing for Guestbooks [](id=enabling-search-and-indexing-for-guestbooks)

Enable search and indexing for guestbook entities in two steps:

1. Create a `GuestbookIndexer` class that extends Liferay's `BaseIndexer`
   abstract class.

2. Update the `addGuestbook`, `updateGuestbook`, and `deleteGuestbook` methods
   of `GuestbookLocalServiceImpl` to invoke the guestbook indexer.

In this section, you're just making sure that guestbook documents are indexed
with the proper fields, and updating the service layer so that the search index
is updated when the entity's database table is updated. Since there's not real
reason to search for guestbooks in the UI, only the backend work is necessary.
You'll get to implement UI updates to enable searching for guestbook entries at
the end of the Search and Indexing section.
