# Search and Indexing [](id=search-and-indexing)

Suppose you're developing a data-driven application. You anticipate that your
application will have to store a huge number of database records. You could
provide some canned queries to your users, but you know you can't mind-read
every way they'll want to find their data. Why not create an index for your
custom entities so they can search against the index? Liferay's search and
indexing functionality is provided by Lucene, a Java search framework. Lucene
works by converting searchable entities into documents. Lucene documents are not
documents in the ordinary English sense of the word. Rather, they are custom
objects that correspond to searchable entities. When a Lucene index is searched,
a hits object is returned that contains pointers to the documents that match the
search query. Searching for guestbook entries using an index can be faster than
searching for entities in the database. If an indexed document contains the data
you're interested in, you can avoid having to make a database query altogether.

Suppose, for example, that you want to retrieve custom entities that have
specific values in specific fields. Without an index, you'd have to retrieve all
of the custom entities from the database and check the specific fields of each
entity for the specific values. With an index, you just have to search the
indexed documents' specific fields for the specific values. Once you have the
matching documents in the index, you can retrieve the values of any other fields
of the matching documents. If you need to, you can run a database query to
retrieve the entities that correspond to the indexed documents. This database
query should be much less expensive than a query that has to retrieve all the
custom entities. In this section of tutorials, you'll explore Liferay's search
and indexing APIs and learn how to use them in your applications.
