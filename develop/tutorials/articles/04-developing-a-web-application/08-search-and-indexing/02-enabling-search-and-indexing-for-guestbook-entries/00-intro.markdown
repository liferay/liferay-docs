# Enabling Search and Indexing for Entries [](id=enabling-search-and-indexing-for-entries)

In this section, you'll create the classes that control these aspects of the
search functionality:

- Registration:

    - `EntrySearchRegistrar` registers the search service for the Entry
        entity.

- Indexing:

    - `EntryModelDocumentContributor` controls which Entry fields are
      indexed in the search engine.

    - `EntryModelIndexerWriterContributor` configures the re-indexing and
      batch re-indexing behavior for Entries.

    - `EntryBatchReindexer`, an interface, and its `EntryBatchReindexerImpl`, 
	  for re-indexing Entries when their Guestbook is updated.

- Querying:

    - `EntryKeywordQueryContributor` contributes clauses to the ongoing
      search query.

    - `EntryModelPreFilterContributor` controls how search results are filtered
      before they're returned from the search engine.

- Generating Result Summaries:

    - `EntryModelSummaryContributor` constructs the result summary for
      Entries, including specifying which fields to use.

After creating the search classes, modify the service layer to update the search
index when an Entry is persisted:

- Update `EntryLocalServiceImpl`'s `addEntry`, `updateEntry`, and
  `deleteEntry` methods to update the index so it matches the databse.

+$$$

**Note:** In prior versions of @product@, search and indexing was accomplished
with one `*Indexer` class that extended `BaseIndexer`. This Learning Path
demonstrates a new pattern that relies on [composition instead of
inheritance](https://stackoverflow.com/questions/2399544/difference-between-inheritance-and-composition).
If you desire to use the old approach, feel free to extend `BaseIndexer`. It's
still supported. 

$$$

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-1/registering-entries-with-the-search-framework">Let's Go!<span class="icon-circle-arrow-right"></span></a>
