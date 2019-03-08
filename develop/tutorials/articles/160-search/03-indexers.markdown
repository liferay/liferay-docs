# Indexing Framework [](id=indexing-framework)

Unless you're searching for model entities using database queries (not
recommended in most cases), each asset in @product@ must be indexed in the
search engine. The indexing code is specific to each asset, as the asset's
developers know what fields to index and what filters to apply to the search
query.

In past versions of @product@, when your asset required indexing, you would
implement a new Indexer by extending
`com.liferay.portal.kernel.search.BaseIndexer<T>`. @product-ver@ introduces
a new pattern that relies on 
[composition instead of inheritance](https://stackoverflow.com/questions/2399544/difference-between-inheritance-and-composition).
If you want to use the old approach, feel free to extend `BaseIndexer`. It's
still supported. 

## Search and Indexing Overview [](id=search-and-indexing-overview)

Liferay's original Search API was built around the Lucene search and indexing
library. To this day, familiarity with Lucene will jump-start your understanding
of Liferay's Search API. However, starting with the 7.0 version of @product@,
the Search API is being reworked, so that the parts closely tied to Lucene are
becoming more generic. Elasticsearch support was added (in addition to Solr),
and most of the newer searching and indexing APIs aim to leverage/map
Elasticsearch APIs. This means that in many cases (for example the `Query`
types) there is a one-to-one mapping between the Liferay and Elasticsearch APIs.

In addition to the Elasticsearch centered APIs, Liferay's Search Infrastructure
includes additional APIs serving these purposes: 

-   Indexed documents include the fields needed by @product@ (e.g., 
    `entryClassName`, `entryClassPK`, `assetTagNames`, `assetCategories`, 
    `companyId`, `groupId`, staging status). 
-   It ensures the scope of returned search results is appropriate by applying 
    the right filters to search requests. 
-   It provides permission checking and hit summaries to display in the search 
    portlet. 

To understand how search and indexing code makes your custom models seamlessly
searchable, you must know how to influence each portion of the search and
indexing cycle.

### Indexing [](id=indexing)

Model entities store data fields in the database. For example, Guestbooks store
a _name_ field. During the cycle's Indexing step, you prepare the model entity
to be searchable by defining the model's fields that are sent to the search
engine, later used during a search.

**To control how model entity fields are indexed in search engine documents,**

[`ModelDocumentContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/index/contributor/ModelDocumentContributor.java) 
classes specify which database fields are indexed in the model entity's search
engine document. This class's `contribute` method is called each time the `add`
and `update` methods in the entity's service layer are called.

[`ModelIndexerWriterContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/index/contributor/ModelIndexerWriterContributor.java) 
classes configure the re-indexing and batch re-indexing behavior for the model
entity. This class's method is called when a re-index is triggered from the
Search administrative application found in Control Panel &rarr; Configuration
&rarr; Search, or when a CRUD operation is made on the entity, _if_ the
`modelIndexed` method is implemented in your contributor.

`DocumentContributor` classes (without any `indexer.class.name`  component
property or type parameter) contribute certain fields to every index document,
regardless of what base entity is being indexed. For example, the
[`GroupedModelDocumentContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/contributor/document/GroupedModelDocumentContributor.java)
contributes the `GROUP_ID` and `SCOPE_GROUP_ID` fields for all documents with a
backing entity that's also a `GroupedModel`.

### Searching [](id=searching)

Searches start with a user entering keywords into a search bar. The entered
keywords are processed by the back-end search infrastructure, transformed into a
*query* the search engine can understand, and used to match against each search
document's fields.

**To control the way model entity documents are searched,**

[`KeywordQueryContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/query/contributor/KeywordQueryContributor.java) 
classes contribute clauses to the ongoing search query. This is called at search
time and ensures that all the fields you indexed are also the ones searched.
For example, if you index fields with the Site locale appended to them
(`title_en_us`, for example), you want the search query to include the same
locale when the document is searched. If the search query contains another
locale (like `title_es_ES`) or searches the plain field (`title`), inaccurate
results are returned.

[`ModelPreFilterContributor`s](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/query/contributor/ModelPreFilterContributor.java) 
control how search results are filtered before they're returned from the search
engine. For example, adding the workflow status to the query ensures that an
entity in the trash isn't returned in the search results. 

#### How to contribute Query clauses to every search, regardless of what base entity is being searched [](id=searching-global-keyword-query-contributor)

You can implement a `KeywordQueryContributor` as an OSGi _@Component_ and register without an `indexer.class.name` property.

**Example:** `AlwaysPresentFieldsKeywordQueryContributor` ([link-to-source](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/contributor/query/AlwaysPresentFieldsKeywordQueryContributor.java))

#### How to contribute Filter clauses to every search, regardless of what base entity is being searched [](id=searching-global-query-pre-filter-contributor)

You can implement a `QueryPreFilterContributor` as an OSGi _@Component_. QueryPreFilterContributor is constructed only once under the root filter during a search.

**Example:** `AssetCategoryTitlesKeywordQueryContributor` ([link-to-source](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/contributor/query/AssetCategoryTitlesKeywordQueryContributor.java))

#### What's the difference between QueryPreFilterContributor and ModelPreFilterContributor [](id=searching-difference-between-QueryPreFilterContributor-and-ModelPreFilterContributor)

QueryPreFilterContributor is constructed only once under the root filter during a search, while ModelPreFilterContributor filter is constructed once per entity, and added under each specific entity’s subfilter.

### Returning Results [](id=returning-results)

When a model entity's indexed search document is obtained during a search
request, it's converted into a summary of the model entity.

**To control the result summaries for model entity documents,**

[`ModelSummaryContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/result/contributor/ModelSummaryContributor.java) 
classes get the `Summary` object created for each search document, so you can
manipulate it by adding specific fields or setting the length of the displayed
content.

[`ModelVisibilityContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/result/contributor/ModelVisibilityContributor.java) 
classes control the visibility of model entities that can be attached to other
asset types (for example, File Entries can be attached to Wiki Pages), in the
search context. 

One important step must occur to make sure the above classes are discovered by
the search framework. 

### Search Service Registration [](id=search-service-registration)

**To register model entities with Liferay's search framework,**

`SearchRegistrar`s use the 
[search framework's registry](https://github.com/liferay/liferay-portal/tree/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/registrar) 
to define certain things about your model entity's 
[`ModelSearchDefinition`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/registrar/ModelSearchDefinition.java):
which fields are used by default to retrieve documents from the search engine,
and which optional search services are registered for your entity. Registration
occurs as soon as the Component is activated (during portal startup or
deployment of the bundle).

## Mapping the Composite Search and Indexing Framework to `Indexer`/`BaseIndexer` Code [](id=mapping-the-composite-search-and-indexing-framework-to-indexer-baseindexer-)

If you're used to the old way of indexing custom entities (extending
`BaseIndexer`, the abstract implementation of `Indexer`), the table below
provides a quick overview about how the methods of the `Indexer` interface were
decomposed into several new classes and methods.

 Indexer/BaseIndexer method | Composite Indexer Equivalent | Example |
:-------------------------- | :-------------------------- | :--------------- |
 Class Constructor | `SearchRegistrar` | [`GuestbookSearchRegistrar`](/develop/tutorials/-/knowledge_base/7-1/registering-guestbooks-with-the-search-framework) |
 `setDefaultSelectedFieldNames` | `SearchRegistrar.activate` | [`GuestbookSearchRegistrar`](/develop/tutorials/-/knowledge_base/7-1/registering-guestbooks-with-the-search-framework) |
 `setDefaultSelectedLocalizedFieldNames` | `SearchRegistrar.activate` | [`GuestbookSearchRegistrar`](/develop/tutorials/-/knowledge_base/7-1/registering-guestbooks-with-the-search-framework) |
 `setPermissionAware`  | `ModelResourcePermissionRegistrar` | [`GuestbookModelResourcePermissionRegistrar`](/develop/tutorials/-/knowledge_base/7-1/registering-your-defined-permissions#registering-your-entities-with-the-permissions-service) |
  `setFilterSearch` | `ModelResourcePermissionRegistrar` | [`GuestbookModelResourcePermissionRegistrar`](/develop/tutorials/-/knowledge_base/7-1/registering-your-defined-permissions#registering-your-entities-with-the-permissions-service) |
 `getDocument`/`doGetDocument` | `ModelDocumentContributor` | [`GuestbookModelDocumentContributor`](/develop/tutorials/-/knowledge_base/7-1/indexing-guestbooks#implementing-modeldocumentcontributor)
 `reindex`/`doReindex` | `ModelIndexerWriterContributor` | [`GuestbookModelIndexerWriterContributor`](/develop/tutorials/-/knowledge_base/7-1/indexing-guestbooks#implementing-modelindexerwritercontributor)
 `addRelatedEntryFields` | `RelatedEntryIndexer` | [`DLFileEntryRelatedEntryIndexer`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryRelatedEntryIndexer.java)
 `postProcessContextBooleanFilter`/`PostProcessContextQuery` | `ModelPreFilterContributor` | [`DLFileEntryModelPreFilterContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryModelPreFilterContributor.java) |
  `postProcessSearchQuery` | `KeywordQueryContributor` | [`GuestbookKeywordQueryContributor`](/develop/tutorials/-/knowledge_base/7-1/querying-for-guestbook-documents#implementing-keywordquerycontributor) |
 `getFullQuery` | `SearchContextContributor` | [`DLFileEntryModelSearchContextContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryModelSearchContextContributor.java) |
 `isVisible`/`isVisibleRelatedEntry` | `ModelVisibilityContributor` | [`DLFileEntryModelVisibilityContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryModelVisibilityContributor.java) |
 `getSummary`/`createSummary`/`doGetSummary` | `ModelSummaryContributor` | [`GuestbookModelSummaryContributor`](/develop/tutorials/-/knowledge_base/7-1/generating-results-summaries) |
 `Indexer.search`/`searchCount` | No change | [Guestbook `view_search.jsp`](https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-0/creating-a-search-results-jsp-for-the-guestbook-portlet) |	
 `Indexer.delete`/`doDelete` | No change | [`MBMessageLocalServiceImpl.deleteDiscussionMessages`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/message-boards/message-boards-service/src/main/java/com/liferay/message/boards/service/impl/MBMessageLocalServiceImpl.java#L687) |

In addition, you can index `ExpandoBridge` attributes. This was previously
accomplished in `BaseIndexer`'s `getBaseModelDocument`. Now you implement an
`ExpandoBridgeRetriever`. See 
[`DLFileEntryExpandoBridgeRetriever`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryExpandoBridgeRetriever.java)
for an example implementation.

## Permissions Aware Searching and Indexing [](id=permissions-aware-searching-and-indexing)

In previous versions of @product@, search was only _permissions
aware_ (indexed with the entity's permissions and searched with those
permissions intact) if the application developer specified this line in the
`Indexer` class's constructor:

    setPermissionAware(true);

Now, search is permissions aware by default _if the new permissions approach_,
as described in
[these tutorials](/develop/tutorials/-/knowledge_base/7-1/defining-application-permissions), 
is implemented for an application.

## Annotating Service Methods to Trigger Indexing [](id=annotating-service-methods-to-trigger-indexing)

Having entities translated into database entities _and_ search engine documents
means that there's a possibility for a state mismatch between the database and
search engine. For example, when a Blogs entry is added, updated or removed from
the database, corresponding changes must be made in the search engine. To do
this, intervention must be made into the service layer. For Service Builder
entities, this occurs in the `LocalServiceImpl` classes. There's an annotation
that simplifies this: `@Indexable`. It takes a `type` property that can have two
values: `REINDEX` or `DELETE`. Commonly, a `deleteEntity` method in the service
layer is annotated like this:


	@Indexable(type = IndexableType.DELETE)
	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public BlogsEntry deleteEntry(BlogsEntry entry) throws PortalException {
        ...
    }

The `@Indexable` annotation is executed by Liferay's Spring infrastructure, so
if you have a method with that annotation, you must call it using a service
instance variable with the spring wrapped logic. The reference is available by
default in Service Builder generated `*LocalServiceImpl` classes because of this
declaration in the parent `*LocalServiceBaseImpl`:

    @BeanReference(type = BlogsEntryLocalService.class)
        protected BlogsEntryLocalService blogsEntryLocalService;

This means that in the `*LocalServiceImpl`, you must not call

    this.deleteEntry(...) 

The annotation won't be executed and you'll be left with a state mismatch
between the search engine document and the database column. Instead follow the
pattern in @product@'s code, using the service instance variable to call service
methods:

    blogsEntryLocalService.deleteEntry(entry);

For step-by-step instructions on indexing model entities, visit the 
[Search and Indexing section of the Developing a Web Application tutorials](/develop/tutorials/-/knowledge_base/7-1/search-and-indexing).
