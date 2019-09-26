---
header-id: search-and-indexing
---

# Search and Indexing

[TOC levels=1-4]

The Guestbook and Guestbook Admin portlets are up and running. The Guestbook
portlet lets users add, edit, delete, and configure permissions for Guestbook
Entries. The Guestbook Admin portlet lets Site administrators create, edit,
delete, and configure permissions for Guestbooks. In the case of a very popular
event (maybe a *Lunar Luau* dinner at the Lunar Resort), there could be many
Guestbook Entries in the portlet, and users might want to search for Entries
that mentioned the delicious low-gravity ham that was served (melts in your
mouth). Searching for the word *ham* should display these Guestbook entries. In
short, Guestbook entries must be searchable via a search bar in the Guestbook
portlet. 

| **Note:** In previous versions of @product@, search was only _permissions
| aware_ (indexed with the entity's permissions and searched with those
| permissions intact) if the application developer specified this line in the
| `Indexer` class's constructor:
| 
|     setPermissionAware(true);
| 
| Now, search is permissions aware by default _if the new permissions approach_,
| as described in the previous step of this tutorial and in
| [these articles](/docs/7-2/frameworks/-/knowledge_base/f/defining-application-permissions),
| is implemented for an application.

To enable search, you must index Guestbooks and Guestbook entries. Although you
probably won't have enough Guestbooks in a Site to warrant searching the
Guestbook Admin portlet, indexing Guestbooks has other benefits. In a later
step, you'll asset-enable Guestbooks and Guestbook entries so the Asset
Publisher can display them. Enabling search is a prerequisite for this, because
the Asset Publisher uses the index to find assets. 

If you get stuck, [source code](https://github.com/liferay/liferay-docs/tree/master/en/developer/tutorials/code/guestbook/08-search/com-liferay-docs-guestbook)
for this step is provided. 

But assets are for later. Right now it's time to index those Guestbooks. Ready? 

![Figure 1: Add a search bar so users can search for Guestbook Entries. If a message or name matches the search query, the Entry is displayed in the search results.](../../../images/guestbook-portlet-search.png)

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/enabling-search-and-indexing-for-guestbooks">Let's Go!<span class="icon-circle-arrow-right"></span></a>

