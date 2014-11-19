# Implementing Search and Indexing

Suppose you're developing a data-driven application. You anticipate that your
application will have to store a huge number of database records. When you need
to search for and retrieve specific database records, you could simply run a
database query. However, your application's performance will greatly improve if
you create an index for your custom entities and search against the index.
Liferay's search and indexing functionality is provided by Lucene, a Java search
framework. Lucene works by converting searchable entities into documents. Lucene
documents are not documents in the ordinary English sense of the word. Rather,
they are custom objects that correspond to searchable entities. When a Lucene
index is searched, a hits object is returned that contains pointers to the
documents that match the search query. Searching for guestbook entries via an
index is faster than searching for entities in the database.

For example, suppose that you want to retrieve custom entities that have 
specific values in specific fields. Without an index, you'd have to retrieve all
of the custom entities from the database and check the specific fields of each
entity for the specific values. With an index, you just have to search the
indexed documents' specific fields for the specific values. Once you have the
matching documents in the index, you can run a database query to retrieve the
entities that correspond to the indexed documents. This database query should be
much less expensive than a query that has to retrieve all of the custom
entities. In this section of tutorials, you'll explore Liferay's search and
indexing APIs and learn how to use them in your applications.
