---
header-id: enabling-search-and-indexing-for-guestbook-entries
---

# Enabling Search and Indexing for Guestbook Entries

[TOC levels=1-4]

Enabling search for guestbook entries in the Guestbook portlet takes two steps: 

1.  Create an `EntryIndexer` class that extends @product@'s `BaseIndexer` 
    abstract class. 

2.  Update `EntryLocalServiceImpl`'s `addEntry` and `deleteEntry` methods to
    invoke the guestbook entry indexer. 

When you finish, all the back-end search and indexing work for both entities 
will be complete, leaving only the UI changes to complete. 

<a class="go-link btn btn-primary" href="/docs/7-0/tutorials/-/knowledge_base/t/creating-an-entry-indexer">Let's Go!<span class="icon-circle-arrow-right"></span></a>
