# Understanding Search and Indexing with Lucene [](id=understanding-search-and-indexing-with-lucene)

Under the hood, Liferay uses Lucene, a Java search library, to implement its
search and indexing functionality. Lucene works by converting searchable
entities into *documents*. Lucene documents are not documents in the ordinary
English sense of the word. Rather, they are Lucene constructs that correspond to
searchable entities. After you implement an indexer for guestbook entries, a
document will be created for each guestbook entry. When you implement the
guestbook entry indexer, you'll specify which guestbook entry fields should be
added to each guestbook entry document. All the guestbook entry documents are
added to an index. When a Lucene index is searched, a *hits* object is returned
that contains pointers to the documents that match the search query. Searching
for guestbook entries via an index is faster than searching for entities
in the database.