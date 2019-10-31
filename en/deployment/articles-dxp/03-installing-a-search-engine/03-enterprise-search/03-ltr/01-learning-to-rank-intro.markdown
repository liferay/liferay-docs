# Liferay Enterprise Search: Learning to Rank

[TOC levels=1-4]

Search engines like Elasticsearch have well-tuned relevance algorithms, good for
general search purposes. Sometimes, this "generally good" relevance scoring just
isn't good enough. Attain more perfect search results by employing Machine
Learning in your cause. 

Learning to Rank is a technique for harnessing machine learning to improve
search result rankings. It combines the expertise of data scientists with
machine learning, to produce a smarter scoring function that's applied to search
queries.

@product-ver@, Service Pack 1/Fix Pack 2 and later, supports Learning to Rank
through its support of Elasticsearch versions 6.x and 7.4.x. It requires a
[Liferay Enterprise Search](https://help.liferay.com/hc/en-us/articles/360014400932) 
subscription. It's important to understand that the
[Elasticsearch Learning to Rank plugin](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/index.html)
is not produced by Elasticsearch, and there is not a pre-built plugin for all of
@product@'s supported Elasticsearch versions. 

## Disabling Learning to Rank on a Search Page

Learning to Rank does not work with the
[Sort widget](/docs/7-2/user/-/knowledge_base/u/sorting-search-results-with-the-sort-widget).

If you need to use Learning to Rank in your @product@ instance, but you'd like to disable it
on a particular Search page (perhaps to use the Sort widget), here's how to do
so:

1.  Add a
    [Low Level Search Options](/docs/7-2/user/-/knowledge_base/u/low-level-search-options-searching-additional-or-alternate-indexes)
    widget to the Search page.

2.  Open the widget's Configuration screen by clicking 

    _Configure additional low level search options in this page._

3.  In the _Contributors to Exclude_ field, enter

    `com.liferay.portal.search.learning.to.rank`

Now the Learning to Rank re-scoring process is skipped for queries entered into the page's
Search Bar, and results are sortable in the Sort widget and returned using the
default relevance algorithm.

## Prerequisites 

There are some prerequisites for using Learning to Rank to re-score Liferay
queries sent to Elasticsearch:

- If using Elasticsearch 7, @product-ver@ Service Pack 1/Fix Pack 2 or later is
    required, with the appropriate Elasticsearch Connector version installed.

- If using Elasticsearch 6, @product-ver@ Fix Pack 3 or later is
    required, with the appropriate Elasticsearch Connector version installed.

- A [Liferay Enterprise Search](https://help.liferay.com/hc/en-us/articles/360014400932) 
    (LES) is required for Learning to Rank. Once you have a subscription, 
    [download LES and install](/docs/7-2/user/-/knowledge_base/u/installing-apps-manually#installing-apps-manually)
    to your @product@ server.

- A remote Elasticsearch server, with your data indexed into it.

- The corresponding version of the [Elasticsearch Learning to Rank](https://github.com/o19s/elasticsearch-learning-to-rank) plugin installed into Elasticsearch.

- The Learning to Rank plugin must have a model uploaded that's trained to re-rank results
    from Elasticsearch. The model includes these components:

    - The Learning to Rank algorithm you wish to use for creating a training
        model. This demonstration uses
        [RankLib](https://sourceforge.net/p/lemur/wiki/RankLib/).
    - A _judgment list_, containing a list of search results and all the
        _features_ to hand to the Learning to Rank algorithm. One will be
        provided for this example.

    [Judgment lists](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/core-concepts.html#judgments-expression-of-the-ideal-ordering)
    are lists of graded search results.

    [Features](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/core-concepts.html#features-the-raw-material-of-relevance) 
    are the columns in a judgment list, if each result is a row in the list.
    They're the variables that the algorithm uses to create a function that can
    score results in a smarter way. If you don't give enough, or the correct,
    relevant features, your model will not be "smart" enough to provide improved
    results.

To understand more about the compatibility requirements for LES, see its
[compatibility matrix](https://help.liferay.com/hc/en-us/articles/360016511651-Liferay-Enterprise-Search-Compatibility-Matrix?flash_digest=645af3a9a43c3f505b5dbe67c6f1015e573382a9).

How does Learning to Rank work?

### Technical Overview

In a normal search, the User sends a query to the search engine via Liferay
DXP's [Search Bar](/docs/7-2/user/-/knowledge_base/u/searching-for-assets#search-bar).
The order of returned results is dictated by the search engine's
[relevance scoring algorithm](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/query-filter-context.html#relevance-scores).

Here's where Learning to Rank intervenes and makes that process different:

1.  User enters a query into the search bar.

2.  Liferay sends the query to Elasticsearch, and retrieves the first 1000
    results as usual, using the search engine's relevance algorithm. 

3.  The top 1000 results are not returned as search hits, but are used by Elasticsearch for
[re-scoring](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/search-request-body.html#request-body-search-rescore)
via the
[rescore query](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/searching-with-your-model.html#rescore-top-n-with-sltr).

4.  The re-scoring happens using:

    - The [SLTR query](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/logging-features.html#sltr-query).
    - A trained model to execute on the query.

5.  The model re-ranks the results and they're returned in Liferay's [Search
    Results](/docs/7-2/user/-/knowledge_base/u/search-results) in their new order.

Though it's just a sub-bullet point in the ordered list above, much of the work
in this paradigm is in creating and honing the trained model. That's beyond the
scope of Liferay's role, but we'll help you get all the pieces in place to
orchestrate the magic of machine learning on your Liferay queries.

