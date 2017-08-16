# Enabling Search and Indexing for Guestbook Entries [](id=enabling-search-and-indexing-for-guestbook-entries)

Enabling search for guestbook entries in the Guestbook portlet takes three
steps:

1. Create an `EntryIndexer` class that extends Liferay's `BaseIndexer` abstract
   class.

2. Update the `addEntry` and `deleteEntry` methods of `EntryLocalServiceImpl` to
   invoke the guestbook entry indexer.

<!--3.  Add status fields to the `service.xml` to notify the indexer and Asset 
    Publisher whether an asset can be published.
-->

3. Add a search bar to the Guestbook portlet so users can enter search terms,
   and add a JSP to display the returned search results.

When you're done with this section, all the backend search and indexing work is
complete for both entities. Next oyu'll update the Guestbook portlet's UI to add
frontend search functionality. More search functionality will be added when you
explore @product@'s asset framework in the next Learning Path.
