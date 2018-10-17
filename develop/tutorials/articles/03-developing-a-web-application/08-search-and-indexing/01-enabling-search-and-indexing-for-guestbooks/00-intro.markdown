# Enabling Search and Indexing for Guestbooks [](id=enabling-search-and-indexing-for-guestbooks)

In this section, create the classes that control these aspects of the search
functionality:

- Registration:

    - `GuestbookSearchRegistrar` registers the search service for the Guestbook
        entity.

- Indexing:

    - `GuestbookModelDocumentContributor` controls which Guestbook fields are
        indexed in the search engine.

    - `GuestbookModelIndexerWriterContributor` configures the re-indexing and
        batch re-indexing behavior for Guestbooks.

- Querying:

    - `GuestbookKeywordQueryContributor`  contributes clauses to the ongoing
        search query.

    - `GuestbookModelPreFilterContributor` controls how search results are filtered
        before they're returned from the search engine.

- Generating Result Summaries:

    - `GuestbookModelSummaryContributor` constructs the result summary for
        Guestbooks, including specifying which fields to use.

After creating the search classes, modify the service layer to update the search
index when a guestbook is persisted:

- Update `GuestbookLocalServiceImpl`'s `addGuestbook`, `updateGuestbook`, and
   `deleteGuestbook` methods to invoke the guestbook indexer.

+$$$

**Note:** In prior versions of @product@, search and indexing was accomplished
with one `*Indexer` class that extended `BaseIndexer`. This Learning Path
demonstrates a new pattern that relies on composition instead of inheritance. If
you desire to use the old approach, feel free to extend `BaseIndexer`. It's
still supported. 

$$$

Since there's no reason to search for guestbooks in the UI, only back-end work
is necessary. 

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/understanding-search-and-indexing">Let's Go!<span class="icon-circle-arrow-right"></span></a>
