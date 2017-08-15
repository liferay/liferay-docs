# Enabling Search and Indexing for Guestbooks [](id=enabling-search-and-indexing-for-guestbooks)

To enable search and indexing for guestbook entities, you need to follow these
four steps:

1. Create a `GuestbookIndexer` class that extends Liferay's `BaseIndexer`
   abstract class.

2. Update the `addGuestbook`, `updateGuestbook`, and `deleteGuestbook` methods
   of `GuestbookLocalServiceImpl` to invoke the guestbook indexer.

3. Add status fields to the `service.xml` to notify the indexer and Asset 
   Publisher whether an asset can be published.

4.  Update the Guestbook portlet's user interface to display a search bar into
    which users can enter search terms and a JSP to display search results after
    the search terms are submitted.

In this section, you'll follow the first three steps: creating an indexer, 
updating the service layer to invoke the indexer, and adding status fields to 
notify the indexer. You'll follow the last step, updating the Guestbook 
portlet's user interface, in the next section. <!--Explain why -->

