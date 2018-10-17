# Search and Indexing

The Guestbook and Guestbook Admin portlets are up and running. The Guestbook
portlet lets users add, edit, delete, and configure permissions for Guestbook
Entries. The Guestbook Admin portlet lets site administrators create, edit,
delete, and configure permissions for Guestbooks. In the case of a very popular
event (maybe a *Lunar Luau* dinner at the Lunar Resort), there could be many
Guestbook Entries in the portlet, and users might want to search for Entries
that mentioned the delicious low-gravity ham that was served (melts in your
mouth). Searching for the word *ham* should display these Entries. In short,
Guestbook Entries must be searchable via a search bar in the Guestbook portlet. 

To enable search, you'll index Guestbooks and their Entries.  Although you
probably won't have enough Guestbooks in a site to warrant searching the
Guestbook Admin portlet, indexing Guestbooks has other benefits. In a later
section, you'll asset-enable Guestbooks and Guestbook Entries so @product@'s
Asset Publisher can display them. Enabling search is a prerequisite for
this--you must index any entity that you want to make an asset. 

But assets are for later. Right now it's time to index those Guestbooks. Ready? 

![Figure 1: Add a search bar so users can search for Guestbook Entries. If a message or name matches the search query, the Entry is displayed in the search results.](../../../images/guestbook-portlet-search.png)

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/enabling-search-and-indexing-for-guestbooks">Let's Go!<span class="icon-circle-arrow-right"></span></a>



NOTES: must mention, here and in the permissions articles, that search is
permissions aware by default, IF the new permissions approach is used. See
BEngler's post in slack

heres my attempt at explaining #1: the registrar basically defines the
contributor that builds (or contributes to) a `ModelSearchDefinition`. the
`ModelSearchDefinition` is used to initialize a `ModelSearchConfigurator`. the
`ModelSearchRegistrarHelper` registers that `ModelSearchConfigurator`, which
causes the `ModelSearchConfiguratorServiceTrackerCustomizer` to pick up the
`ModelSearchConfigurator` and build a `DefaultIndexer` with it. that
`DefaultIndexer` is then registered under the classname that was defined in the
registrar, and then used for indexing/searching objects of that class
