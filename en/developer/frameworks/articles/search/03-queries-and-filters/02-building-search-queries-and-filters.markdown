---
header-id: building-search-queries-and-filters
---

# Building Search Queries and Filters

[TOC levels=1-4]

Each filter and query has a [different
purpose](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl.html),
but the way you'll add the information to the search request is similar between
all queries and filters.

## Queries

A mostly-complete code snippet for building Queries is provided for your
copying and pasting convenience [below](#example).

### Declare Gradle Dependencies

Add the following to your `build.gradle` file:

```gradle
dependencies {
	compileOnly group: "biz.aQute.bnd", name: "biz.aQute.bndlib", version: "3.5.0"
	compileOnly group: "com.liferay.portal", name: "release.portal.api", version: "7.2.0"
	compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
}
```

With this you can import all the Search APIs.

### Reference the Search Services

To satisfy the dependencies of the example code presented here, get references
to

- `com.liferay.portal.search.searcher.SearchRequestBuilderFactory`
- `com.liferay.portal.search.searcher.Searcher`
- `com.liferay.portal.search.query.Queries`

```java
@Reference
protected Queries queries;

@Reference
protected Searcher searcher;

@Reference
protected SearchRequestBuilderFactory searchRequestBuilderFactory;
```

### Build the Search Query

1.  Use the `com.liferay.portal.search.query.Queries` interface to instantiate
    the queries you'll construct. For example,

    ```java
    TermsQuery termsQuery = queries.terms("fieldName");
    MatchQuery matchQuery = queries.match("fieldName", "value");
    BooleanQuery booleanQuery = queries.booleanQuery();
    ```

    To discover what parameters each query must have (e.g., `String field` in the
    case of the above `com.liferay.portal.search.query.TermsQuery`), see the
    [`Queries`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search-api/src/main/java/com/liferay/portal/search/query/Queries.java)
    interface.

2.  Build out the queries to get the desired response. This looks different for
    each query type, as explained in [Elasticsearch's Query
    documentation](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl.html).

    ```java
    termsQuery.addValues("value1", "value2");
    ```

3.  You might want to wrap queries. For example, use the queries constructed
    above as MUST clauses in a `BooleanQuery` wrapper:

    ```java
    booleanQuery.addMustQueryClauses(termsQuery, matchQuery);
    ```

Once the query itself is in good shape, feed it to the search request.

### Build the Search Request

1.  Use `com.liferay.portal.search.searcher.SearchRequestBuilderFactory`
    to get an instance of
    `com.liferay.portal.search.searcher.SearchRequestBuilder`:

    ```java
    SearchRequestBuilder searchRequestBuilder =
        searchRequestBuilderFactory.builder();
    ```

    If not setting search keywords into the `SearchContext` (covered below), make
    sure the request builder enables empty search. 

    ```java
    searchRequestBuilder.emptySearchEnabled(true);
    ```

    Set the `long companyId` and, optionally, `String keywords` into the
    `com.liferay.portal.kernel.search.SearchContext`:

    ```java
    searchRequestBuilder.withSearchContext(
        searchContext -> {
            searchContext.setCompanyId(companyId);
            searchContext.setKeywords(keywords);
        });
    ```

    Setting the Company ID into the `SearchContext` is required to ensure the
    correct index is searched.

    Setting "keywords" on the `SearchContext` is necessary if you want to
    search via user input. For example, if providing a Search bar in an
    application's view layer, pass its input into the search context. Liferay's
    search framework adds the user input keywords and any other data in the
    `SearchContext` object to its own queries, searching the appropriate fields of
    each indexed entity, as defined by its
    [`KeywordQueryContributor`](/docs/7-2/frameworks/-/knowledge_base/f/searching-the-index-for-model-entities#adding-your-model-entitys-terms-to-the-query)
    or by the `postProcessSearchQuery` method of its `Indexer`.

2.  To execute the query, get a
    `com.liferay.portal.search.searcher.SearchRequest` instance from the
    builder by adding the query to it and running its `build` method:

    ```java
    SearchRequest searchRequest = 
        searchRequestBuilder.query(booleanQuery).build();
    ```

3.  To use a constructed query in a filter context, call the `postFilterQuery`
    method while building the request:

    ```java
    SearchRequest searchRequest = 
        searchRequestBuilder.postFilterQuery(termsQuery).build();
    ```


4.  When constructing a search request, you'll often find it necessary to chain
    the builder methods together:

    ```java
    SearchRequest searchRequest = 
        searchRequestBuilder.postFilterQuery(myQuery1).query(myQuery2).build();
    ```

    Chaining allows you to add filters and queries (and anything else from the
    builder API) to the same request in one fell swoop.

### Execute the Search Request

Perform a search using the `com.liferay.portal.search.searcher.Searcher`
service and the `SearchRequest` to get a
`com.liferay.portal.search.searcher.SearchResponse`:

```java
SearchResponse searchResponse = searcher.search(searchRequest);
```

### Process the Search Response

What you'll do with the `SearchResponse` returned by the `searcher.search`
call is dependent on the type of query and your specific use case. Much of the
time you'll want to loop through the `com.liferay.portal.search.hits.SearchHit`
and `com.liferay.portal.search.document.Document` objects, so that's what's
shown here, with a simple message printed in the log for each one.

1.  Get the `SearchHits` from the response:

    ```java
    SearchHits searchHits = searchResponse.getSearchHits();
    ```

2.  Get a List of the `SearchHit` objects:

    ```java
    List<SearchHit> searchHitsList = searchHits.getSearchHits();
    ```

3.  Loop through the `SearchHit` objects in the List, get the `Document`
    associated with each one, printing its score and UID to the
    console:

    ```java
    searchHitsList.forEach(
        searchHit -> {
            float hitScore = searchHit.getScore();

            Document doc = searchHit.getDocument();

            String uid = doc.getString(Field.UID);

            System.out.println(
                StringBundler.concat(
                    "Document ", uid, " had a score of ", hitScore));
        });
    ```

### Search Insights: Request and Response Strings

When building a search application, it can be useful to inspect the request
string (translated into the search engine's dialect), and subsequently see the
response string returned by the search server.

Retrieve these from the `SearchResponse` as

```java
searchResponse.getRequestString();
searchResponse.getResponseString();
``` 

The format depends on your search engine: with Elasticsearch, both are JSON.

| **Note:** The JSON returned as a request string is pruned from several
| Elasticsearch query defaults for clarity. To see the full request JSON that
| Elasticsearch processed, adjust the [Elasticsearch server's
| logging](https://www.elastic.co/guide/en/elasticsearch/reference/6.x/logging.html).

Inspecting the request string produced by the code example included
[here](#example) reveals two main `"bool":"must"` query clauses in the JSON
being sent to the search engine: 

1.  The `BooleanQuery` explicitly declared in the code example.

2.  A (very long) query determined by the logic embedded in the
    [`SearcherImpl#doSearch`](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/portal-search/portal-search/src/main/java/com/liferay/portal/search/internal/searcher/SearcherImpl.java#L137)
    method.

How you construct the `SearchRequest` determines how the `Searcher` API
processes it, which in turn influences the request String sent to
Elasticsearch. For example, sending `keywords` into the `SearchContext` object
passed to the `SearchRequest` ensures that queries for certain fields are
executed on all searchable documents.

### Queries Example

The code below performs a `MatchQuery` on the `title_en_US` field for the value
provided via the `keywords` String. In addition, a `TermsQuery` on the
`folderId` field is executed to match a value of `0` (root `JournalFolder`s are
identified by `JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID`, which
evaluates to `0`). Both queries are wrapped in a `BooleanQuery` must clause.

Because this example passes `keywords` to the `SearchContext`,
`emptySearchEnabled(true)` is not called on the `SearchRequestBuilder`. The
`keywords` variable is not explicitly declared because this should come from
user input. Therefore the example `search` method receives `keywords` as a
parameter, along with the `companyId`:

```java
package com.liferay.docs.search;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.MatchQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.TermsQuery;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	service = MySearchComponent.class
)
public class MySearchComponent {

	public List<String> search(long companyId, String keywords)
		throws PortalException {

		MatchQuery titleQuery = queries.match(
			Field.getLocalizedName(LocaleUtil.US, Field.TITLE), keywords);

		TermsQuery rootFolderQuery = queries.terms(Field.FOLDER_ID);

		rootFolderQuery.addValues(String.valueOf(
				JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID));

		BooleanQuery booleanQuery = queries.booleanQuery();

		booleanQuery.addMustQueryClauses(rootFolderQuery, titleQuery);

		SearchRequestBuilder searchRequestBuilder =
			searchRequestBuilderFactory.builder();

		// Uncomment this line below if you aren't setting "keywords"
		// on the SearchContext
		//		searchRequestBuilder.emptySearchEnabled(true);

		searchRequestBuilder.withSearchContext(
			searchContext -> {
				searchContext.setCompanyId(companyId);
				searchContext.setKeywords(keywords);
			});

		SearchRequest searchRequest = searchRequestBuilder.query(
			booleanQuery
		).build();

		SearchResponse searchResponse = searcher.search(searchRequest);

		SearchHits searchHits = searchResponse.getSearchHits();

		List<SearchHit> searchHitsList = searchHits.getSearchHits();

		List<String> resultsList = new ArrayList<>(searchHitsList.size());

		searchHitsList.forEach(
			searchHit -> {
				float hitScore = searchHit.getScore();

				Document doc = searchHit.getDocument();

				String uid = doc.getString(Field.UID);

				System.out.println(
					StringBundler.concat(
						"Document ", uid, " had a score of ", hitScore));

				resultsList.add(uid);
			});

		System.out.println(StringPool.EIGHT_STARS);

		/*
		 *  // Uncomment to inspect the Request and Response Strings
         * System.out.println( "Request String:\n" + searchResponse.getRequestString() +
		 * "\n" + StringPool.EIGHT_STARS);
		 * System.out.println( "Response String:\n" +
		 * searchResponse.getResponseString() + "\n" + StringPool.EIGHT_STARS);
		 */

		return resultsList;
	}

	@Reference
	protected Queries queries;

	@Reference
	protected Searcher searcher;

	@Reference
	protected SearchRequestBuilderFactory searchRequestBuilderFactory;

}
```
## Filters

Filters as a distinct API-level object in @product@ are going away. It's best to
mirror the APIs of the search engine, and neither supported search engine makes
an API level distinction between queries and filters. In recognition of this,
there's a new way to perform post-filtering, which is filtering the returned
search documents at the end of the search request (after calculating any
aggregations). Add the filter to the query using the `postFilterQuery` method in
the request builder:

```java 
SearchRequestBuilder.postFilterQuery(Query);
```

As you can see, this takes a `Query` object, not a `Filter`. Therefore,
construct the `Query` as in the previous section, and specify it as
a post-filter while building the request. All of the legacy `Filter` objects
from `portal-kernel` can now be constructed as queries, per the above
query-building documentation.

### Legacy Filters in Legacy Search Calls

Constructing the filters found in `portal-kernel`'s
`com.liferay.portal.kernel.search.filter` package is demonstrated by this `new`
term filter:

```java
TermFilter termFilter = new TermFilter("fieldName", "filterValue");
```

Filters are added in legacy search calls by going through the `Indexer`
framework's `postProcessContextBooleanFilter` method, which is invoked while the
search framework is constructing the main search query. See the 
[`UserIndexer`'s `addContextQueryParams` method](https://github.com/liferay/liferay-portal/blob/7.2.x/modules/apps/users-admin/users-admin-impl/src/main/java/com/liferay/users/admin/internal/search/UserIndexer.java), 
which is called in the overridden `postProcessContextBooleanFilter` to add the
filter logic.

## Discovering Indexed Fields

To find the fields to use in your Queries, navigate to *Control Panel* &rarr;
*Configuration* &rarr; *Search* in a running portal. From there, open the Field Mappings tab and
browse the mappings for the `liferay-[companyId]` index. Scroll to the
[`properties`](https://www.elastic.co/guide/en/elasticsearch/reference/current/properties.html)
section of the mapping.

A summary of the text fields that are localized can be found
[here](/docs/7-2/user/-/knowledge_base/u/searching-for-localized-content).
