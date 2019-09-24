---
header-id: enabling-search-and-indexing-for-entries
---

# Enabling Search and Indexing for Entries

[TOC levels=1-4]

Now you'll create the classes that control these aspects of the search
functionality:

- Registration:

    - `GuestbookEntrySearchRegistrar` registers the search service for the
    `GuestbookEntry` entity.

- Indexing:

    - `GuestbookEntryModelDocumentContributor` controls which `GuestbookEntry` fields are
      indexed in the search engine.

    - `GuestbookEntryModelIndexerWriterContributor` configures the re-indexing and
      batch re-indexing behavior for `GuestbookEntry`s.

    - `GuestbookEntryBatchReindexer`, an interface, and its `GuestbookEntryBatchReindexerImpl`, 
	  for re-indexing `GuestbookEntries `when their Guestbook is updated.

- Querying:

    - `GuestbookEntryKeywordQueryContributor` contributes clauses to the ongoing
      search query.

    - `GuestbookEntryModelPreFilterContributor` controls how search results are filtered
      before they're returned from the search engine.

- Generating Result Summaries:

    - `GuestbookEntryModelSummaryContributor` constructs the result summary for
      `GuestbookEntry`s, including specifying which fields to use.

After creating the search classes, modify the service layer to update the search
index when a `GuestbookEntry `is persisted:

- Update `GuestbookEntryLocalServiceImpl`'s `addEntry`, `updateEntry`, and
  `deleteEntry` methods to update the index so it matches the database.

| **Note:** In prior versions of @product@, search and indexing was accomplished
| with one `*Indexer` class that extended `BaseIndexer`. This tutorial
| demonstrates a new pattern that relies on 
| [composition instead of inheritance](https://stackoverflow.com/questions/2399544/difference-between-inheritance-and-composition).
| If you desire to use the old approach, feel free to extend `BaseIndexer`. It's
| still supported.

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/registering-entries-with-the-search-framework">Let's Go!<span class="icon-circle-arrow-right"></span></a>
