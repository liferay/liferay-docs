# Enabling Search and Indexing [](id=enabling-search-and-indexing)

Now you have a working Guestbook portlet and a completed Guestbook Admin
portlet. The Guestbook portlet allows users to add, edit, delete, and configure
permissions for guestbook entries. The Guestbook Admin portlet allows site
administrators to create, edit, delete, and configure permissions for
guestbooks. In the case of a very popular event (maybe a *Lunar Luau* dinner at
the Lunar Resort), there could be lots of guestbook entries in the portlet, and
users might want to search for only the entries that mentioned the delicious
low-gravity ham that was served. They should be able to search for the word
*ham* so they can peruse the pertinent entries. In short, guestbook entries need
to be searchable, and the Guestbook portlet needs a search bar where users enter
search queries. 

You'll not only add an indexer for guestbook entries, but also for guestbooks
themselves. Although you don't anticipate having so many guestbooks in a single
site that searching for them in the Guestbook Admin portlet is useful, creating
a guestbook indexer has other benefits. In a later section, you'll asset-enable
both guestbook entries and guestbooks themselves. Search is a prerequisite for
doing that, so @product@'s Asset Publisher can find assets to display. To make
this work, it's important to index any entity that you want to make an asset. 

But assets are for later. Right now it's time to create those indexers. Ready? 

![Figure 1: You'll add a search bar to the Guestbook portlet so that users can search for guestbook entries. If a guestbook entry's message or name matches the search query, it's displayed in the search results.](../../../images/guestbook-portlet-search.png)
