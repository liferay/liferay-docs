# Model Entity Indexing Framework

[TOC levels=1-4]

<!-- cover the pitfalls of bad localization -->

Unless you're searching for model entities using database queries (not
recommended in most cases), each asset in @product@ must be indexed in the
search engine. The indexing code is specific to each asset, as the asset's
developers know what fields to index and what filters to apply to the search
query.

In past versions of @product@, when your asset required indexing, you would
implement a new Indexer by extending
`com.liferay.portal.kernel.search.BaseIndexer<T>`. @product@ version 7.1
introduced a new pattern that relies on 
[composition instead of inheritance](https://stackoverflow.com/questions/2399544/difference-between-inheritance-and-composition).
If you want to use the old approach, feel free to extend `BaseIndexer`. It's
still supported. 

## Search and Indexing Overview

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

-   Ensure all indexed documents include the fields needed by @product@ (e.g., 
    `entryClassName`, `entryClassPK`, `assetTagNames`, `assetCategories`,
    `companyId`, `groupId`, staging status). 
-   Ensure the scope of returned search results is appropriate by applying 
    the right filters to search requests. 
-   Provide permission checking and hit summaries for display in the built-in
    [search application](/docs/7-2/user/-/knowledge_base/u/search).

## Mapping the Composite Search and Indexing Framework to `Indexer`/`BaseIndexer` Code

If you're used to the old way of indexing custom entities (extending
`BaseIndexer`, the abstract implementation of `Indexer`), the table below
provides a quick overview about how the methods of the `Indexer` interface were
decomposed into several new classes and methods.

<!--UPDATE LINKS FOR 7.2. BREAK ACCORDING TO SEARCH/INEXING LIFECYCLE, LIKE THE
HOW-TO ARTICLES-->
 Indexer/BaseIndexer method | Composite Indexer Equivalent | Example |
:-------------------------- | :-------------------------- | :--------------- |
 Class Constructor | `SearchRegistrar` | [`GuestbookSearchRegistrar`](/docs/7-1/tutorials/-/knowledge_base/t/registering-guestbooks-with-the-search-framework) |
 `setDefaultSelectedFieldNames` | `SearchRegistrar.activate` | [`GuestbookSearchRegistrar`](/docs/7-1/tutorials/-/knowledge_base/t/registering-guestbooks-with-the-search-framework) |
 `setDefaultSelectedLocalizedFieldNames` | `SearchRegistrar.activate` | [`GuestbookSearchRegistrar`](/docs/7-1/tutorials/-/knowledge_base/t/registering-guestbooks-with-the-search-framework) |
 `setPermissionAware`  | `ModelResourcePermissionRegistrar` | [`GuestbookModelResourcePermissionRegistrar`](/docs/7-1/tutorials/-/knowledge_base/t/registering-your-defined-permissions#registering-your-entities-with-the-permissions-service) |
  `setFilterSearch` | `ModelResourcePermissionRegistrar` | [`GuestbookModelResourcePermissionRegistrar`](/docs/7-1/tutorials/-/knowledge_base/t/registering-your-defined-permissions#registering-your-entities-with-the-permissions-service) |
 `getDocument`/`doGetDocument` | `ModelDocumentContributor` | [`GuestbookModelDocumentContributor`](/docs/7-1/tutorials/-/knowledge_base/t/indexing-guestbooks#implementing-modeldocumentcontributor)
 `reindex`/`doReindex` | `ModelIndexerWriterContributor` | [`GuestbookModelIndexerWriterContributor`](/docs/7-1/tutorials/-/knowledge_base/t/indexing-guestbooks#implementing-modelindexerwritercontributor)
 `addRelatedEntryFields` | `RelatedEntryIndexer` | [`DLFileEntryRelatedEntryIndexer`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryRelatedEntryIndexer.java)
 `postProcessContextBooleanFilter`/`PostProcessContextQuery` | `ModelPreFilterContributor` | [`DLFileEntryModelPreFilterContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryModelPreFilterContributor.java) |
  `postProcessSearchQuery` | `KeywordQueryContributor` | [`GuestbookKeywordQueryContributor`](/docs/7-1/tutorials/-/knowledge_base/t/querying-for-guestbook-documents#implementing-keywordquerycontributor) |
 `getFullQuery` | `SearchContextContributor` | [`DLFileEntryModelSearchContextContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryModelSearchContextContributor.java) |
 `isVisible`/`isVisibleRelatedEntry` | `ModelVisibilityContributor` | [`DLFileEntryModelVisibilityContributor`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryModelVisibilityContributor.java) |
 `getSummary`/`createSummary`/`doGetSummary` | `ModelSummaryContributor` | [`GuestbookModelSummaryContributor`](/docs/7-1/tutorials/-/knowledge_base/t/generating-results-summaries) |
 `Indexer.search`/`searchCount` | No change | [Guestbook `view_search.jsp`](https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-0/creating-a-search-results-jsp-for-the-guestbook-portlet) |	
 `Indexer.delete`/`doDelete` | No change | [`MBMessageLocalServiceImpl.deleteDiscussionMessages`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/message-boards/message-boards-service/src/main/java/com/liferay/message/boards/service/impl/MBMessageLocalServiceImpl.java#L687) |

In addition, you can index `ExpandoBridge` attributes. This was previously
accomplished in `BaseIndexer`'s `getBaseModelDocument`. Now you implement an
`ExpandoBridgeRetriever`. See 
[`DLFileEntryExpandoBridgeRetriever`](https://github.com/liferay/liferay-portal/blob/7.1.1-ga2/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryExpandoBridgeRetriever.java)
for an example implementation.

## Permissions Aware Searching and Indexing

In previous versions of @product@, search was only _permissions
aware_ (indexed with the entity's permissions and searched with those
permissions intact) if the application developer specified this line in the
`Indexer` class's constructor:

    setPermissionAware(true);

Now, search is permissions aware by default _if the new permissions approach_,
as described in
[these tutorials](/docs/7-2/frameworks/-/knowledge_base/f/defining-application-permissions), 
is implemented for an application.

## Annotating Service Methods to Trigger Indexing

Having entities translated into database entities _and_ search engine documents
means that there's a possibility for a state mismatch between the database and
search engine. For example, when a Blogs Entry is added, updated or removed from
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

<!--For step-by-step instructions on indexing model entities, visit the 
Search and Indexing section of the Developing a Web Application tutorials/docs/7-1/tutorials/-/knowledge_base/t/search-and-indexing)-->
