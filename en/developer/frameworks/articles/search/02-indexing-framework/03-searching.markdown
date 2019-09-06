---
header-id: searching-the-index-for-model-entities
---

# Searching the Index for Model Entities

[TOC levels=1-4]

The heart of searching for your model entity documents is querying for what you
indexed. To do this, contribute search terms to the @product@ search query.

The extension points (i.e., the interfaces to implement) on this page are
provided by the `com.liferay.portal.search.spi` bundle.

Here's the Gradle dependency for @product@ 7.2.0 GA1:

```groovy
dependencies {
	compileOnly group: "com.liferay", name: "com.liferay.portal.search.spi", version: "3.2.1"
}
```

## Adding your Model Entity's Terms to the Query

`KeywordQueryContributor` classes contribute clauses to the ongoing search
query, to control the way model entities are searched. If you're storing
localized fields in the index (a good idea, as covered in the example code for
your `ModelDocumentContributor`), query the localized fields at search time. 

**Extension Point (SPI):** [com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/query/contributor/KeywordQueryContributor.java)

```java
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.foo.model.FooEntry",
	service = KeywordQueryContributor.class
)
public class FooEntryKeywordQueryContributor
	implements KeywordQueryContributor {

	@Override
	public void contribute(
		String keywords, BooleanQuery booleanQuery,
		KeywordQueryContributorHelper keywordQueryContributorHelper) {

		SearchContext searchContext =
			keywordQueryContributorHelper.getSearchContext();

		queryHelper.addSearchLocalizedTerm(
			booleanQuery, searchContext, Field.CONTENT, false);
		queryHelper.addSearchTerm(
			booleanQuery, searchContext, Field.SUBTITLE, false);
		queryHelper.addSearchLocalizedTerm(
			booleanQuery, searchContext, Field.TITLE, false);
	}

	@Reference
	protected QueryHelper queryHelper;

}
```

The entity in this example has a title, subtitle, and content field. The
subtitle field wasn't stored as a localized field, so it's not searched that
way, either.

## Contributing Query Clauses to Every Search

It's a less common need, but to contribute query clauses to every search,
regardless of what base entity is being searched, implement a
`KeywordQueryContributor` class registered without an `indexer.class.name`
component property. For example, see
[`AlwaysPresentFieldsKeywordQueryContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/contributor/query/AlwaysPresentFieldsKeywordQueryContributor.java).

It includes a String array that includes the fields that are always searched:

```java
private static final String[] _ALWAYS_PRESENT_FIELDS = {
    Field.COMMENTS, Field.CONTENT, Field.DESCRIPTION, Field.PROPERTIES,
    Field.TITLE, Field.URL, Field.USER_NAME
};
```

## Pre-Filtering

`*PreFilterContributor` classes control how search results are filtered before
they're returned from the search engine. For example, adding the workflow status
to the query ensures that an entity in the trash isn't returned in the search
results. They're constructed each time a query for the model entity is made. 

**Extension Pointi (SPI):** [`ModelPreFilterContributor`s](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/query/contributor/ModelPreFilterContributor.java)

To contribute filter clauses specific to a model entity, use
a `ModelPreFilterContributor`. This one adds a filter for workflow status:

```java
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.foo.model.FooEntry",
	service = ModelPreFilterContributor.class
)
public class FooEntryModelPreFilterContributor
	implements ModelPreFilterContributor {

	@Override
	public void contribute(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		addWorkflowStatusFilter(
			booleanFilter, modelSearchSettings, searchContext);
	}

	protected void addWorkflowStatusFilter(
		BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
		SearchContext searchContext) {

		workflowStatusModelPreFilterContributor.contribute(
			booleanFilter, modelSearchSettings, searchContext);
	}

	@Reference(target = "(model.pre.filter.contributor.id=WorkflowStatus)")
	protected ModelPreFilterContributor workflowStatusModelPreFilterContributor;

}
```

**Extension Point (SPI):** [`com.liferay.portal.search.spi.model.query.contributor.QueryPreFilterContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/query/contributor/QueryPreFilterContributor.java)

To contribute Filter clauses to every search, regardless of what base entity is
being searched, implement a `QueryPreFilterContributor`.
`QueryPreFilterContributor` is constructed only once under the root filter
during a search. For example, see 
[`AssetCategoryTitlesKeywordQueryContributor`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/contributor/query/AssetCategoryTitlesKeywordQueryContributor.java).

What's the difference between `QueryPreFilterContributor` and
`ModelPreFilterContributor`? `QueryPreFilterContributor` is constructed only
once under the root filter during a search, while `ModelPreFilterContributor`
is constructed once per model entity and added under each specific entity's
sub-filter.

