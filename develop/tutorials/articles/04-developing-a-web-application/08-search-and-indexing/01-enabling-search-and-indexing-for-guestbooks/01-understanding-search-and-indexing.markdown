# Understanding Search and Indexing [](id=understanding-search-and-indexing)

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Guestbooks<br>Step 1 of 6</p>
</div>

By default, @product@ uses Elasticsearch, a search engine backed by the popular
Lucene search library, to implement its search and indexing functionality. You
could search the database, but that requires resource-hogging table merges.
Instead, a search engine like Elasticsearch converts searchable entities into
*documents*. In Elasticsearch, documents are searchable database entities
converted into JSON objects. After you implement indexing for guestbook entries,
@product@ creates a document for each entry. The indexing code specifies which
guestbook entry fields to add to each guestbook entry document, and it adds all
the guestbook entry documents to an index. A search returns a *hits* object
containing pointers to documents matching the search query. Searching for
entities with a search engine via an index is faster than searching for entities
in the database. Elasticsearch provides some additional features like relevancy
scoring and fuzzy search queries. 

Along with the search engine, @product@ has its own search infrastructure.
@product@ adds the following features to the existing Elasticsearch API: 

-   Indexed documents include the fields needed by @product@ (e.g., 
    `entryClassName`, `entryClassPK`, `assetTagNames`, `assetCategories`, 
    `companyId`, `groupId`, staging status). 
-   Ensures the scope of returned search results is appropriate by applying 
    the right filters to search requests. 
-   Provides permission checking and hit summaries to display in the search 
    portlet. 

Let's index some Guestbooks, shall we? 
