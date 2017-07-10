# Enabling Search and Indexing for Guestbooks [](id=enabling-search-and-indexing-for-guestbooks)

To enable search and indexing for guestbook entities, you need to follow these
three steps:

1. Create an `GuestbookIndexer` class that extends Liferay's `BaseIndexer`
   abstract class.

2. Update the `addGuestbook`, `updateGuestbook`, and `deleteGuestbook` methods
   of `GuestbookLocalServiceImpl` to invoke the guestbook indexer.

3. Add status fields to the `service.xml` to notify the indexer and Asset 
   Publisher whether an asset can be published.

In this section, you'll follow these three steps. Notice that you're not
updating the user interface of either the Guestbook portlet or the Guestbook
Admin portlet. You're only adding back-end functionality that will allow
guestbooks to take advantage of Liferay's asset framework. You'll explore the
sets of functionality provided by Liferay's asset framework in the next Learning
Path, and you'll update your guestbook-portlet project to use some of them.