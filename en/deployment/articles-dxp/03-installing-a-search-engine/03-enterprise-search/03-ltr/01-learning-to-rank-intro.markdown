---
header-id: liferay-enterprise-search-learning-to-rank
---

# Liferay Enterprise Search: Learning to Rank

[TOC levels=1-4]

Search engines like Elasticsearch have well-tuned relevance algorithms, good for
general search purposes. Sometimes, this "generally good" relevance scoring just
isn't good enough. You can attain more perfect search results by employing
machine learning. 

Learning to Rank harnesses machine learning to improve search result rankings.
It combines the expertise of data scientists with machine learning to produce
a smarter scoring function that's applied to search queries.

@product-ver@, Service Pack 1/Fix Pack 2 and later, supports Learning to Rank
through its support of Elasticsearch versions 6.x and 7.4.x. It requires a
[LES](https://help.liferay.com/hc/en-us/articles/360014400932) 
subscription. It's important to understand that the
[Elasticsearch Learning to Rank plugin](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/index.html)
is not produced by Elastic, and there is not a pre-built plugin for all of
@product@'s supported Elasticsearch versions. 

## Disabling Learning to Rank on a Search Page

Learning to Rank does not work with the
[Sort widget](/docs/7-2/user/-/knowledge_base/u/sorting-search-results-with-the-sort-widget).

If you must use Learning to Rank in your @product@ instance, but want to disable
it on a particular Search page (perhaps to use the Sort widget), you can:

1.  Add a
    [Low Level Search Options](/docs/7-2/user/-/knowledge_base/u/low-level-search-options-searching-additional-or-alternate-indexes)
    widget to the Search page.

2.  Open the widget's Configuration screen by clicking 

    _Configure additional low level search options in this page._

3.  In the _Contributors to Exclude_ field, enter

    `com.liferay.portal.search.learning.to.rank`

Now the Learning to Rank re-scoring process is skipped for queries entered into
the page's Search Bar, and results are sortable in the Sort widget and returned
using the default relevance algorithm.

## Prerequisites 

There are some prerequisites for using Learning to Rank to re-score Liferay
queries sent to Elasticsearch:

- If using Elasticsearch 7, @product-ver@ Service Pack 1/Fix Pack 2 or later is
  required, with the appropriate Elasticsearch Connector version installed.

- If using Elasticsearch 6, @product-ver@ Fix Pack 3 or later is
  required, with the appropriate Elasticsearch Connector version installed.

- A [Liferay Enterprise Search](https://help.liferay.com/hc/en-us/articles/360014400932) 
  (LES) subscription is required for Learning to Rank. Once you have a
  subscription,
  [download the Liferay Enterprise Search Learning to Rank](https://customer.liferay.com/downloads)
  LPKG file and
  [install it to your @product@ server.](/docs/7-2/user/-/knowledge_base/u/installing-apps-manually#installing-apps-manually)

- A remote Elasticsearch server, with your data indexed into it.

- The corresponding version of the [Elasticsearch Learning to Rank](https://github.com/o19s/elasticsearch-learning-to-rank) plugin installed into Elasticsearch.

- A 
    [trained model](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/training-models.html)
    uploaded into the Learning to Rank plugin. 

To understand more about the compatibility requirements for LES, see its
[compatibility matrix](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-Enterprise-Search).

How does Learning to Rank work?

### Technical Overview

In a normal search, the User sends a query to the search engine via Liferay
DXP's [Search Bar](/docs/7-2/user/-/knowledge_base/u/searching-for-assets#search-bar).
The order of returned results is dictated by the search engine's
[relevance scoring algorithm](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/index-modules-similarity.html#bm25).

Here's where Learning to Rank intervenes and makes that process different:

1.  User enters a query into the search bar.

2.  Liferay sends the query to Elasticsearch, and retrieves the first 1000
    results as usual, using the search engine's relevance algorithm. 

3.  The top 1000 results are not returned as search hits, but are used by Elasticsearch for
    [re-scoring](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/search-request-body.html#request-body-search-rescore)
    via the
    [rescore functionality](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/searching-with-your-model.html#rescore-top-n-with-sltr).

4.  The results are re-scored by the 
    [SLTR query](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/searching-with-your-model.html),
    which includes the keywords and the trained model to use for re-scoring.

5.  Once the trained model re-ranks the results, they're returned in Liferay's
    [Search Results](/docs/7-2/user/-/knowledge_base/u/search-results)
    in their new order.

Though it's just a sub-bullet point in the ordered list above, much of the work
in this paradigm is in creating and honing the trained model. That's beyond the
scope of Liferay's role, but we'll help you get all the pieces in place to
orchestrate the magic of machine learning on your Liferay queries. Here's a
brief overview of what constitutes _model training_.

### Model Training

A useful trained model is produced when a good judgment list and a good feature
set are fed to a Learning to Rank algorithm (this is the machine learning part
of the puzzle). Therefore, it's incumbent on you to assemble

- The Learning to Rank algorithm you wish to use for creating a training
  model. This demonstration uses
  [RankLib](https://sourceforge.net/p/lemur/wiki/RankLib/).

- A _judgment list_, containing a graded list of search results. The algorithm
  is designed to produce a model that honors the ordering of the judgment
  list.

- A feature set, containing all the _features_ you're handing to the Learning to
  Rank algorithm, which it uses in conjunction with the judgment list to
  produce a reliable model. An example feature set for @product@ data is shown
  in the next article.


[Judgment lists](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/core-concepts.html#judgments-expression-of-the-ideal-ordering)
are lists of graded search results.

[Features](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/core-concepts.html#features-the-raw-material-of-relevance) 
are the variables that the algorithm uses to create a function that can
score results in a smarter way. If you don't give enough, or the correct,
relevant features, your model will not be "smart" enough to provide improved
results.

In the next article you'll see the steps required to configure Learning to Rank
with @product@.
