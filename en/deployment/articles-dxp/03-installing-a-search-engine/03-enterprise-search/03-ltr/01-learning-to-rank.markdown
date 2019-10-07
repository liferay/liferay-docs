# Applying Elasticsearch's Learning to Rank Capabilities to Liferay Queries

## Doc Resources:

Andre's doc with resources, called Learning To Rank for Absolute Beginners: https://docs.google.com/document/d/1Z75qjCYcKs1DUce6HDiZ_nFhwavzvXCgBi-0UNwBKeU/edit

Meeting Notes from Sept 3, 2019 led by Andre Oliveira

User queries, the top 1000 results are returned and scored, then run through a
"sltr" query (scored by learning to rank)

_must use rescore, because it is too expensive to sltr everything_

scores calculated through machine learning

deploy the elasticsearch library module to the elasticsearch server 

training a model is the important part---here you must us a third party training
tool.

Adam used RankLib

Check this with team or research:
?So you create a judgment list that you feed to the third party tool, and your
model is created. This model is what you feed into the Elasticsearch module?

The judgment is a huge list
This is where the work of the organization using learning to rank comes in.

?Will we have utilities for creating a judgment list?
?How do you know what fields the judgment list should have?
?Is the format of the judgment list set by the tool that generates the model?

The predictive fields you use are called features

So you set the grade and the features, and the tool generates the model

?How many lists do you need? 1 for every expected query? 1 for every asset type?
could use suggestions to see the common queries

judgment lists are re-populated often since feature values change

## Introduction

Search engines like Elasticsearch have well-tuned relevance algorithms, good for
general search purposes. They can't always match your users' expected "perfect"
search behavior, though. While you'll never be perfect (sorry to break it to you),
you can get your search results more perfect by employing Machine Learning in
your cause. 

Learning to Rank is the name for applying machine learning to search results. It
combines the expertise of data scientists with machine learning, to
reverse-engineer a smarter scoring function that's applied to specific search
queries.

Liferay DXP 7.2 supports Learning to Rank through it's support of Elasticsearch
7.3.

## Prerequisites 

Prerequisites for using Learning to Rank to re-score Liferay queries sent to Elasticsearch:

Liferay DXP Version 7.2 (you must have the latest Elasticsearch Connector application: GIVE FULL NAME)

Elasticsearch 7.3, with your data indexed into it.

The [Elasticsearch LTR](https://github.com/o19s/elasticsearch-learning-to-rank) plugin installed into Elasticsearch.

A model trained to re-rank results from Elasticsearch, which will involve these
components:

- The Learning to Rank algorithm you wish to use for creating a training model.
    This demonstration uses
    [RankLib](https://sourceforge.net/p/lemur/wiki/RankLib/).
- A _judgment list_, containing a list of search results and all the _features_ to
    hand to the Learning to Rank algorithm. One will be provided for this
    example.

    [Judgment lists](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/core-concepts.html#judgments-expression-of-the-ideal-ordering)
    are lists of graded search results.

    [Features](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/core-concepts.html#features-the-raw-material-of-relevance) 
    are the columns in a judgment list, if each result is a row in the list.
    They're the variables that the algorithm uses to create a function that can
    score results in a smarter way. If you don't give enough, or the correct,
    relevant 

### Technical Overview

In a normal search, the User sends a query to the search engine via Liferay
DXP's [Search Bar](LINK). Results are returned according to the search engine's
[relevance algorithm](LINK).

Here's a high level view of what will happen with Learning to Rank:

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
in this paradigm is in creating the trained model. That's beyond the scope of
Liferay's role, but we'll help you get all the pieces in place to orchestrate
the magic.

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

### Step 2: Training a Model

Model training is hard, even if you're already used to walking in high heeled
shoes.

Discovering fields to use as features in the model

Keep reworking those judgment lists!

### Step 3: Whatever you do to Liferay to configure each query to run the top 1000 results make the query re-scored and then run through the SLTR query to apply your model.


For example, this is the JSON for a query that is rescored through the SLTR
query, using a model called `test_6`.

```json
{"query": {"multi_match": {"query": "alien", "fields": ["title", "overview"]}}, "rescore": {"query": {"rescore_query": {"sltr": {"params": {"keywords": "alien"}, "model": "test_6"}}}}}
```

You won't need to write the whole query, but you'll need to update the Liferay
search mappings to that this structure is provided when the keywords entered in
the search bar are sent via the proper query.
