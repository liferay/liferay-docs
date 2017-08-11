# Enabling Search and Indexing [](id=enabling-search-and-indexing)

Now you have a working Guestbook portlet as well as a completed Guestbook Admin
portlet. The Guestbook portlet allows users to add, edit, configure permissions
for, and delete guestbook entries. The Guestbook Admin portlet allows site
administrators to create, edit, configure permissions for, and delete
guestbooks. To increase the Guestbook portlet's usability, especially for
situations where many entries have been added to one or more guestbooks,
guestbook entries should be searchable. Then users can enter search queries into
a search bar and find the entries they want to see. 

You'll not only add an indexer for guestbook entries, but also for guestbooks
themselves. Although you don't anticipate having so many guestbooks in a single
site that searching for them would be useful, creating a guestbook indexer has
other benefits. In a later section, you'll asset-enable both guestbook
entries and guestbooks themselves. Search is a prerequisite for doing that, so
@product@'s Asset Publisher can find assets to display. To make this work, it's
important to index any entity that you want to make an asset. 

But assets are for later. Right now it's time to create those indexers. Ready? 

![Figure 1: You'll add a search bar to the Guestbook portlet so that users can search for guestbook entries. If a guestbook entry's message or name matches the search query, it's displayed in the search results.](../../../images/guestbook-portlet-search.png)
