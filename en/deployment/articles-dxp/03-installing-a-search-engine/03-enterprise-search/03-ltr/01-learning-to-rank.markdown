# Using Learning to Rank with Liferay Queries

Thanks Brian! I can start to envision the steps a customer will take (though this is obviously a guess):
1.  Download the LTR module and deploy to Liferay DXP 7.2 SP1/FP2 or later
2.  Install the LTR plugin onto Elasticsearch.
3.  Use Kibana to add the desired feature set to the \_ltr index.
4.  Also using Kibana, configure the trained model to the \_ltr index.

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
through its support of Elasticsearch versions 6.x and 7.3.

<!-- Not sure about this yet
| **Result Rankings Cannot be Used with Learning to Rank:** In @product-ver@,
| Service Pack 1/Fix Pack 2, new search tuning features were added: [Synonym
| Sets](/docs/7-2/user/-/knowledge_base/u/synonym-sets) and [Result
| Rankings](/docs/7-2/user/-/knowledge_base/u/result-rankings). Result Rankings
| cannot be used with Learning to Rank.
-->

## Prerequisites 

There are some prerequisites for using Learning to Rank to re-score Liferay
queries sent to Elasticsearch:

- @product-ver@ Service Pack 1/Fix Pack 2 or later, with the appropriate
    Elasticsearch Connector version installed.

- [Liferay Enterprise
    Search](https://help.liferay.com/hc/en-us/articles/360014400932) is required
    for Learning to Rank. Once you have a subscription, [download LES and
    install](/docs/7-2/user/-/knowledge_base/u/installing-apps-manually#installing-apps-manually)
    to your @product@ server.

- A remote Elasticsearch server, with your data indexed into it.

- The corresponding version of the [Elasticsearch LTR](https://github.com/o19s/elasticsearch-learning-to-rank) plugin installed into Elasticsearch.

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
shoes. Liferay can't really help you with model training. You require the intervention of data scientists, who will
likely recommend certain tools and have experience with certain models. Use what
works for you. In doing so, you'll almost certainly be compiling Judgment lists
and feature sets that can be used by the training tool you select to generate a
model that will produce good search results. This can be a long journey, but
once you get to the end of it, you'll want to upload the model to the Learning
to Rank plugin.

#### Upload the Model to the LTR Plugin

You'll upload the model using a `POST` request, but first you need to make sure
you have a `_ltr` index and a feature set uploaded to the LTR plugin. Use Kibana
to make these tasks easier.

1.  If you don't already have a `_ltr` index, create one:

    ```http
    PUT _ltr
    ```

2.  Add a feature set to the `_ltr` index. In this example the set is called
    `liferay`:

    ```json
    POST _ltr/_featureset/liferay
    {
      "featureset": {
        "name": "liferay",
        "features": [
          {
            "name": "title",
            "params": [
              "keywords"
            ],
            "template": {
              "match": {
                "title_en_US": "{{keywords}}"
              }
            }
          },
          {
            "name": "content",
            "params": [
              "keywords"
            ],
            "template": {
              "match": {
                "content_en_US": "{{keywords}}"
              }
            }
          },
          {
            "name": "asset tags",
            "params": [
              "keywords"
            ],
            "template": {
              "match": {
                "assetTagNames": "{{keywords}}"
              }
            }
          }
        ]
      }
    }
    ```
3.  Add the trained model to the feature set: 

    ```json
    POST _ltr/_featureset/liferay/_createmodel
    {
      "model": {
        "name": "linearregression",
        "model": {
          "type": "model/ranklib",
          "definition": """
    ## Linear Regression
    ## Lambda = 1.0E-10
    0:-0.717621803830712 1:-0.717621803830712 2:-2.235841905601106 3:19.546816765721594
    """
        }
      }
    }
    ```

This is a very high level set of instructions, because because there's not much
to do in @product@. To learn in more detail about what's required, see the
[LTR plugin's documentation](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/index.html).

Some assistance can be provided in learning about which features are
available for model training. One 

Discovering fields to use as features in the model

Selecting pre-built feature sets compatible with the Liferay Index? 

Keep reworking those judgment lists!

### Step 3: Enable Learning to Rank

Enable Learning to Rank from Control Panel &rarr; Configuration &rarr; System
Settings &rarr; Search &rarr; Learning to Rank. There's a simple on/off
configuration, and a text field where you must enter the name of the trained
model to apply to search queries.

The model in the previous step was named `linearregression`, so that's what
you'd use in the configuration's 

That's all the configuration required in @product@, unless you want to disable
Learning to Rank for a particular Search page, reverting back to the default
relevance algorithm for ranking your search results. In that case, place a Low
Level Search Options widget on the Search page, and 
