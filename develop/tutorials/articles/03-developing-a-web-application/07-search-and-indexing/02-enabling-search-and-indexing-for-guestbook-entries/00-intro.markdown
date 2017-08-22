# Enabling Search and Indexing for Guestbook Entries [](id=enabling-search-and-indexing-for-guestbook-entries)

Enabling search for guestbook entries in the Guestbook portlet takes three
steps:

1.  Create an `EntryIndexer` class that extends @product@'s `BaseIndexer` abstract
    class.

2.  Update `EntryLocalServiceImpl`'s `addEntry` and `deleteEntry` methods to
    invoke the guestbook entry indexer.

3.  Add a search bar to the Guestbook portlet so users can enter search terms,
    and add a JSP to display the returned search results.

When you're finished, all the back-end search and indexing work is complete for
both entities. Next you'll update the Guestbook portlet's UI to add front-end
search functionality. More search functionality will be added when you explore
@product@'s asset framework in the next section.
