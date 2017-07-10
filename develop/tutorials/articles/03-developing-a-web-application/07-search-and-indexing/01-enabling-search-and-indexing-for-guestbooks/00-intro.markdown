# Enabling Search and Indexing for Guestbook Entries [](id=enabling-search-and-indexing-for-guestbook-entries)

To enable search in the Guestbook portlet for guestbook entries, you need to
follow these four steps:

1. Create an `EntryIndexer` class that extends Liferay's `BaseIndexer` abstract
   class.

2. Update the `addEntry`, `updateEntry`, and `deleteEntry` methods of
   `EntryLocalServiceImpl` to invoke the guestbook entry indexer.

3.  Add status fields to the `service.xml` to notify the indexer and Asset 
    Publisher whether an asset can be published.

4.  Update the Guestbook portlet's user interface to display a search bar into
    which users can enter search terms and a JSP to display search results after
    the search terms are submitted.

In this section, you'll follow the first three steps: creating an indexer, 
updating the service layer to invoke the indexer, and adding status fields to 
notify the indexer. You'll follow the last step, updating the Guestbook 
portlet's user interface, in the next section.