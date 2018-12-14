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
-   It ensures the scope of returned search results is appropriate by applying 
    the right filters to search requests. 
-   It provides permission checking and hit summaries to display in the search 
    portlet. 

To understand how the search and indexing code presented here makes your custom
models seamlessly searchable, you must know how to influence each portion of the
search and indexing cycle:

**Indexing**: Model entities store data fields in the database. For example,
Guestbooks store the _name_ field. During the cycle's Indexing step, you prepare
the model entity to be searchable by defining the model's fields that are sent
to the search engine, later used during a search.

**To influence the way model entity fields are indexed in search engine
documents,**

`ModelDocumentContributor` classes specify which database fields are indexed in
the model entity's search engine documents. This class's `contribute` method is
called each time the `add` and `update` methods in the entity's service layer
are called.

`ModelIndexerWriterContributor` classes configure the re-indexing and batch
re-indexing behavior for the model entity. This class's method is called when
a re-index is triggered from the Search administrative application found in
Control Panel &rarr; Configuration &rarr; Search.

**Searching:** Most searches start with a user entering keywords into a search
bar. The entered keywords are processed by the back-end search infrastructure,
transformed into a *query* the search engine can understand, and used to match
against each search document's fields.

**To exert control over the way your model entity documents are searched,**

`KeywordQueryContributor` classes contribute clauses to the ongoing search
query. This is called at search time, and ensures that all the fields you
indexed are also the ones searched. For example, if you index fields with the
Site locale appended to them (`title_en_us`, for example), you want the search
query to include the same locale when the document is searched. If the search
query contain another locale (like `title_es_ES`) or searches the plain field
(`title`), inaccurate results are returned.

`ModelPreFilterContributor`s control how search results are filtered before
they're returned from the search engine. For example, adding the workflow status
to the query ensures that an entity in the trash isn't returned in the search
results. For the Guestbook application, a `ModelPrefilterContributor` isn't
necessary until you get to the section on workflow-enabling Guestbooks.

**Returning Results:** When a model entity's indexed search document is obtained
during a search request, it's converted into a summary of the model entity.

**To influence the result summaries for your model entity documents,**

`ModelSummaryContributor` classes get the `Summary` object created for each
search document, so you can manipulate it by adding specific fields or setting
the length of the displayed content.

`ModelVisibilityContributor` classes control the visibility of model entities
that can be attached to other asset types (for example, File Entries can be
attached to Wiki Pages), in the search context. Since Guestbooks and Guestbook
entries won't be attached to other assets, a model visibility contributor isn't
necessary.

One important step must occur to make sure the above classes are discovered by
the search framework. 

**Registration**

To register the model entity with Liferay's search framework,

`SearchRegistrar`s use the search framework's registry to define certain things
about your model entity's `ModelSearchDefinition`: which fields are used by
default to retrieve documents from the search engine, and which optional search
services are registered for your entity. Registration occurs as soon as the
Component is activated (during portal startup).

Let's index some Guestbooks, shall we? 
