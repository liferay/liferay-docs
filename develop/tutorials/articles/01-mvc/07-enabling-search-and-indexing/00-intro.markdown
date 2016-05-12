# Enabling Search and Indexing [](id=enabling-search-and-indexing)

Now you have a working Guestbook portlet as well as a completed Guestbook Admin
portlet. The Guestbook portlet allows users to create guestbooks and to add,
edit, configure permissions for, and delete guestbook entries. The Guestbook
Admin portlet allows site administrators to create, edit, configure permissions
for, and delete guestbooks. To increase the usability of the Guestbook portlet,
especially for situations where many entries have been added to one or more
guestbooks, you'll next make guestbook entries searchable. When you're done,
users will be able to enter search queries into a search bar on the Guestbook
portlet. If a search query matches an entry's message or name, the entry will
appear in the search results.

You'll not only add an indexer for guestbook entries, but also for guestbooks
themselves. Although you don't anticipate having so many guestbooks in a single
site that searching for them would be useful, creating a guestbook indexer has
other benefits. In a later learning path, you'll asset-enable both guestbook
entries and guestbooks themselves. For performance reasons, Liferay's Asset
Publisher portlet queries for assets to display via search indexes instead of
via database queries. In order to fully asset-enable an entity, it must have an
indexer. In this learning path, you'll create a guestbook indexer and update the
guestbook service layer to use it since it's a prerequisite for asset-enabling
guestbooks.

![Figure 1: You'll add a search bar to the Guestbook portlet so that users can search for guestbook entries. If a guestbook entry's message or name matches the search query, it's displayed in the search results.](../../images/guestbook-portlet-search.png)

