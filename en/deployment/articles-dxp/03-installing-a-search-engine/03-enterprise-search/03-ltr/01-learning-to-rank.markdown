# Using Learning to Rank with Liferay Queries

Search engines like Elasticsearch have well-tuned relevance algorithms, good for
general search purposes. They can't always match your users' expected "perfect"
search behavior, though. While you'll never be perfect (sorry to break it to you),
you can get your search results more perfect by employing Machine Learning in
your cause. 

Learning to Rank is a technique for harnessing machine learning to improve
search result rankings. It combines the expertise of data scientists with
machine learning, to produce a smarter scoring function that's applied to search
queries.

@product-ver@, Service Pack 1/Fix Pack 2 and later, supports Learning to Rank
through its support of Elasticsearch 7.3.

## Prerequisites 

Prerequisites for using Learning to Rank to re-score Liferay queries sent to
Elasticsearch:

- @product-ver@ Service Pack 1/Fix Pack 2 or later, with the latest
    Elasticsearch Connector application installed: _Liferay Connector to
    Elasticsearch 7.3_.

- Elasticsearch 7.3, with your data indexed into it.

- The [Elasticsearch LTR](https://github.com/o19s/elasticsearch-learning-to-rank) plugin installed into Elasticsearch.

- The LTR plugin must have a model uploaded that's trained to re-rank results
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

How does it all work?

### Technical Overview

In a normal search, the User sends a query to the search engine via Liferay
DXP's [Search Bar](LINK). Results are returned according to the search engine's
[relevance algorithm](LINK).

Here's where Learning to Rank intervenes and makes that process different:

1.  User enters a query into the search bar.

2.  Liferay sends the query to Elasticsearch, and retrieves the first 1000
    results as usual, using the search engine's relevance algorithm. 

3.  The top 1000 results are not returned as search hits, but are used by Elasticsearch for [re-scoring](https://www.elastic.co/guide/en/elasticsearch/reference/7.3/search-request-body.html#request-body-search-rescore) via the [rescore query](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/searching-with-your-model.html#rescore-top-n-with-sltr).

4.  The re-scoring happens using:

    - The [SLTR query](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/logging-features.html#sltr-query).
    - A trained model to execute on the query.

5.  The model re-ranks the results and they're returned in Liferay's [Search
    Results](LINK) in their new order.

Though it's just a sub-bullet point in the ordered list above, much of the work
in this paradigm is in creating and honing the trained model. That's beyond the
scope of Liferay's role, but we'll help you get all the pieces in place to
orchestrate the magic of machine learning on your Liferay queries.

## Configure Learning to Rank

This set of instructions starts by assuming your have a remote Elasticsearch 7.3
cluster communicating with @product-ver@.

Helpful hint: Use Suggestions to discover the most common queries (this can be
one way to decide which queries to create LTR models for).

### Step 1: Install the LTR Plugin on Elasticsearch

See 
[the Elasticsearch documentation](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/#installing)
to learn about installing the LTR plugin.

You'll be running a command like this one, depending on the plugin verison
you're installing:

```sh
./bin/elasticsearch-plugin install http://es-learn-to-rank.labs.o19s.com/ltr-1.1.0-es6.5.4.zip
```

If you're using X-Pack security in your Elasticsearch cluster, there 
[may be additional steps you need to take.](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/x-pack.html)

### Step 2: Training and Uploading a Model

Model training is hard, even if you're already used to walking in high heeled
shoes.

Discovering fields to use as features in the model

Selecting pre-built feature sets compatible with the Liferay Index? 

Keep reworking those judgment lists!

### Step 3: Whatever you do to Liferay to configure each query to run the top 1000 results make the query re-scored and then run through the SLTR query to apply your model.

Enable Learning to Rank from the search page. There's a simple on/off
configuration.

Questions from the writer:

Is there anything done on the backend to make sure the necessary infrastructure
is in place? For example, if there's no LTR plugin detected in Elasticsearch, we
disable the LTR button? 
Will it be disabled in Embedded mode?
