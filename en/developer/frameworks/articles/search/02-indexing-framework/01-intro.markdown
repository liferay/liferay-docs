---
header-id: model-entity-indexing-framework
---

# Model Entity Indexing Framework

[TOC levels=1-4]

Unless you're searching for model entities using database queries (not
recommended in most cases), each asset in @product@ must be indexed in the
search engine. The indexing code is specific to each asset, as the asset's
developers know what fields to index and what filters to apply to the search
query. This paradigm applies to Liferay's own developers and anyone developing
model entities for use with @product@.

In past versions of @product@, when your asset required indexing, you would
implement a new Indexer by extending
`com.liferay.portal.kernel.search.BaseIndexer<T>`. @product@ version 7.1
introduced a new pattern that relies on 
[composition instead of inheritance](https://stackoverflow.com/questions/2399544/difference-between-inheritance-and-composition).
That said, if you want to use the old approach, feel free to extend
`BaseIndexer`. It's still supported. 

## Search and Indexing Overview

Starting with the 7.0 version of @product@, the Search API has become less tied
to Lucene. Elasticsearch support was added (in addition to Solr), and most of
the newer searching and indexing APIs aim to leverage/map Elasticsearch APIs.
This means that in many cases (for example the `Query` types) there is
a one-to-one mapping between the Liferay and Elasticsearch APIs.

In addition to the Elasticsearch-centered APIs, Liferay's Search Infrastructure
includes additional APIs serving these purposes: 

-   Ensure all indexed documents include some required fields (e.g., 
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

|  Indexer/BaseIndexer method | Composite Indexer Equivalent | Example |
| :-------------------------- | :-------------------------- | :--------------- |
|  Class Constructor | `SearchRegistrar` | [`BlogsEntrySearchRegistrar`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/search/BlogsEntrySearchRegistrar.java) |
|  `setDefaultSelectedFieldNames` | `SearchRegistrar.activate` | [`BlogsEntrySearchRegistrar`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/search/BlogsEntrySearchRegistrar.java) |
|  `setDefaultSelectedLocalizedFieldNames` | `SearchRegistrar.activate` | [`BlogsEntrySearchRegistrar`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/search/BlogsEntrySearchRegistrar.java) |
|  `setPermissionAware`  | `ModelResourcePermissionRegistrar` | [`DLFileEntryModelResourcePermissionRegistrar`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/security/permission/resource/DLFileEntryModelResourcePermissionRegistrar.java) |
|   `setFilterSearch` | `ModelResourcePermissionRegistrar` | [`DLFileEntryModelResourcePermissionRegistrar`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/security/permission/resource/DLFileEntryModelResourcePermissionRegistrar.java) |
|  `getDocument`/`doGetDocument` | `ModelDocumentContributor` | [`BlogsEntryModelDocumentContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/search/spi/model/index/contributor/BlogsEntryModelDocumentContributor.java) |
|  `reindex`/`doReindex` | `ModelIndexerWriterContributor` | [`BlogsEntryModelIndexerWriterContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/search/spi/model/index/contributor/BlogsEntryModelIndexerWriterContributor.java) |
|  `addRelatedEntryFields` | `RelatedEntryIndexer` | [`DLFileEntryRelatedEntryIndexer`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryRelatedEntryIndexer.java) |
|  `postProcessContextBooleanFilter`/`PostProcessContextQuery` | `ModelPreFilterContributor` | [`BlogsEntryModelPreFilterContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/search/spi/model/query/contributor/BlogsEntryModelPreFilterContributor.java) |
|   `postProcessSearchQuery` | `KeywordQueryContributor` | [`BlogsEntryKeywordQueryContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/search/spi/model/query/contributor/BlogsEntryKeywordQueryContributor.java) |
|  `getFullQuery` | `SearchContextContributor` | [`DLFileEntryModelSearchContextContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryModelSearchContextContributor.java) |
|  `isVisible`/`isVisibleRelatedEntry` | `ModelVisibilityContributor` | [`BlogsEntryModelVisibilityContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/search/spi/model/result/contributor/BlogsEntryModelVisibilityContributor.java) |
|  `getSummary`/`createSummary`/`doGetSummary` | `ModelSummaryContributor` | [`BlogsEntryModelSummaryContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/blogs/blogs-service/src/main/java/com/liferay/blogs/internal/search/spi/model/result/contributor/BlogsEntryModelSummaryContributor.java) |
|  `Indexer.search`/`searchCount` | No change | [`BlogEntriesDisplayContext`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/display/context/BlogEntriesDisplayContext.java) |	
|  `Indexer.delete`/`doDelete` | No change | [`MBMessageLocalServiceImpl.deleteMessage`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/message-boards/message-boards-service/src/main/java/com/liferay/message/boards/service/impl/MBMessageLocalServiceImpl.java#L703) |

In addition, you can index `ExpandoBridge` attributes. This was previously
accomplished in `BaseIndexer`'s `getBaseModelDocument`. Now you implement an
`ExpandoBridgeRetriever`. See 
[`DLFileEntryExpandoBridgeRetriever`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/document-library/document-library-service/src/main/java/com/liferay/document/library/internal/search/DLFileEntryExpandoBridgeRetriever.java)
for an example implementation.

## Permissions-Aware Searching and Indexing

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

Having objects translated into database entities _and_ search engine documents
means that there's a possibility for a state mismatch between the database and
search engine. For example, when a Blogs Entry is added, updated, or removed from
the database, corresponding changes must be made in the search engine. To do
this, intervention must be made in the service layer. For Service Builder
entities, this occurs in the `LocalServiceImpl` classes. An annotation
simplifies this: `@Indexable`. It takes a `type` property that can have two
values: `REINDEX` or `DELETE`. Commonly, a `deleteEntity` method in the service
layer is annotated like this:

```java
@Indexable(type = IndexableType.DELETE)
@Override
@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
public BlogsEntry deleteEntry(BlogsEntry entry) throws PortalException {
    ...
}
```

The `@Indexable` annotation is executed by Liferay's AOP infrastructure, so if
you have a method with that annotation, you must call it using a service
instance variable injected by your dependency injector, and not using the `this`
keyword. Whether using OSGi's Declarative Services (DS) or Spring for dependency
injection, there's a protected variable declared in the superclass
(`*LocalServiceBaseImpl`) that can be used in the `*LocalServiceImpl`, like
this.

```java
blogsEntryLocalService.deleteEntry(entry);
```

Since you're using the injected service variable, that means you must not call

```java
this.deleteEntry(...) 
```

in your `*LocalServiceImpl` methods. The annotation won't be executed and you'll
be left with a state mismatch between the search engine document and the
database column.

## Search and Localization: a Cheat Sheet

[Localization](/docs/7-2/frameworks/-/knowledge_base/f/localization) is
important. Search and localization can play nicely together, if you take some
precautions:

- For each field that should be localized (e.g., `content`), index a separate
  field for each of the site's languages (e.g., `content_en_US`,
  `content_ja_JP`, `content_es_ES`, ...).
- Search the localized fields. Whatever you index, that's what you should be
  querying for.
- Don't index content in plain (unlocalized) fields if you expect the content to
  be present in multiple locales.
- Don't index both the plain and the localized field.

The indexing and searching articles included in this section demonstrate how to
handle localized fields in the search code properly. 
