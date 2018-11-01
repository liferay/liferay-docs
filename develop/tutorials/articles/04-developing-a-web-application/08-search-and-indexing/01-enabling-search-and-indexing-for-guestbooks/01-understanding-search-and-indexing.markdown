# Understanding Search and Indexing [](id=understanding-search-and-indexing)

<div class="learn-path-step">
    <p>Enabling Search and Indexing for Guestbooks<br>Step 1 of 6</p>
</div>

By default, @product@ uses Elasticsearch, a search engine backed by the popular
Lucene search library, to implement its search and indexing functionality. To 
avoid the resource-hogging table merges necessary to search the database, using 
a search engine like Elasticsearch lets you convert searchable entities into 
*documents*. In Elasticsearch, documents are searchable database entities 
converted into JSON objects. After you implement indexing for guestbook 
entries, @product@ creates a document for each entry. The indexing code specifies 
which guestbook entry fields to add to each guestbook entry document. All the 
guestbook entry documents are then added to an index. When the index is 
searched, a *hits* object is returned that contains pointers to the documents 
matching the search query. Searching for entities with a search engine via an 
index is faster than searching for entities in the database. Elasticsearch 
provides some additional features like relevancy scoring and fuzzy search 
queries. 

Along with the search engine, @product@ has its own search infrastructure. 
@product@ adds to the existing Elasticsearch API for a few reasons: 

-   To ensure indexed documents include the fields needed by @product@ (e.g., 
    `entryClassName`, `entryClassPK`, `assetTagNames`, `assetCategories`, 
    `companyId`, `groupId`, staging status). 
-   To ensure the scope of returned search results is appropriate by applying 
    the right filters to search requests. 
-   To provide permission checking and hit summaries to display in the search 
    portlet. 

Let's index some Guestbooks, shall we? 
