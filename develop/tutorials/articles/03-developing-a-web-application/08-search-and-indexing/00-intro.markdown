# Leveraging Search [](id=leveraging-search)

Now you have working Guestbook and Guestbook Admin portlets. The Guestbook 
portlet lets users add, edit, delete, and configure permissions for guestbook 
entries. The Guestbook Admin portlet lets site administrators create, edit, 
delete, and configure permissions for guestbooks. In the case of a very popular 
event (maybe a *Lunar Luau* dinner at the Lunar Resort), there could be many 
guestbook entries in the portlet, and users might want to search for entries 
that mentioned the delicious low-gravity ham that was served (melts in your 
mouth). Searching for the word *ham* should display these entries. In short, 
guestbook entries must be searchable via a search bar in the Guestbook portlet. 

To enable search, you'll add an indexer for guestbooks and their entries. 
Although you probably won't have enough guestbooks in a site to warrant 
searching the Guestbook Admin portlet, creating a guestbook indexer has other 
benefits. In a later section, you'll asset-enable guestbooks and guestbook 
entries so @product@'s Asset Publisher can display them. Enabling search is a 
prerequisite for this--you must index any entity that you want to make an asset. 

But assets are for later. Right now it's time to create those indexers. Ready? 

![Figure 1: You'll add a search bar to the Guestbook portlet so that users can search for guestbook entries. If a guestbook entry's message or name matches the search query, the entry is displayed in the search results.](../../../images/guestbook-portlet-search.png)

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/enabling-search-and-indexing-for-guestbooks">Let's Go!<span class="icon-circle-arrow-right"></span></a>
