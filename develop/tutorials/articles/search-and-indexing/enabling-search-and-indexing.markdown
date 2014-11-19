# Enabling Search and Indexing

To implement search and indexing functionality for a custom entity, you need to
follow these three steps:

1. Create an `*Indexer` class and register this class in your project's
   `liferay-portlet.xml` file.

2. Update your custom entity's service layer so that the index is updated upon
   the creation, modification, or deletion of an entity.

3. Provide a mechanism to actually perform a search. For example, you could
   create a JSP for entering search queries and another JSP for displaying the
   search results. Or you could simply configure Liferay's search portlet to
   search for your custom entities.

You'll explore each of these steps in the following sections.

## Creating and Registering an Indexer Class

The indexer class that you need to create is responsible for creating the Lucene
documents that represent your custom entities. When creating an indexer class,
you need to decide which fields your documents should contain.
