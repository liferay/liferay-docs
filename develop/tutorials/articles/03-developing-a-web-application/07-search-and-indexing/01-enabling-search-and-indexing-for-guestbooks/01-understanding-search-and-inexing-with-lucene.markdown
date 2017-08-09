# Understanding Search and Indexing

By default, Liferay uses Elasticsearch, a search engine backed by the popular
Lucene Java search library, to implement its search and indexing functionality.
To avoid the resource-hogging table merges that would be necessary to just
search the relevant fields in the database, using a search engine like
Elasticsearch lets you convert searchable entities into *documents*. Documents
are not documents in the ordinary English sense of the word. In Elasticsearch,
they are your searchable database entities converted into JSON objects. After
you implement an indexer for guestbook entries, a document will be created for
each guestbook entry. When you implement the guestbook entry indexer, you'll
specify which guestbook entry fields should be added to each guestbook entry
document. All the guestbook entry documents are added to an index. When the
index is searched, a *hits* object is returned that contains pointers to the
documents that match the search query. Searching for entities with a search
engine, via an index, is faster than searching for entities in the database, and
Elasticsearch provides some additional features like relevancy scoring and fuzzy
search queries. Along with the search engine, Liferay has its own search
infrastructure. Why does Liferay adds to the existing Elasticsearch API? There
are a few reasons:

- Ensure indexed documents include the fields needed by @product@
    (for example, `entryClassName`, `entryClassPK`, `assetTagNames`, `assetCategories`,
    `companyId`, `groupId`, staging status).
- Ensure the scope of returned search results is appropriate by applying the
  right filters to search requests.
- Provide permission checking and hit summaries to display in the search portlet.
