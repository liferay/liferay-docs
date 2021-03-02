---
header-id: configuring-learning-to-rank
---

# Configuring Learning to Rank

[TOC levels=1-4]

Before beginning, you must have a remote
[Elasticsearch 6 or 7](/docs/7-2/deploy/-/knowledge_base/d/upgrading-to-elasticsearch-7) 
cluster communicating with @product-ver@. See the
[compatibility matrix for more information](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-Enterprise-Search)

| **Helpful hint:** Use
| [Suggestions](/docs/7-2/user/-/knowledge_base/u/searching-for-assets#search-suggestions)
| to discover the most common queries (this can be one way to decide which queries
| to create Learning to Rank models for).

## Step 1: Install the Learning to Rank Plugin on Elasticsearch

See 
[the Elasticsearch Learning to Rank plugin documentation](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/#installing)
to learn about installing the Learning to Rank plugin.

You'll be running a command like this one, depending on the plugin version
you're installing:

```sh
./bin/elasticsearch-plugin install http://es-learn-to-rank.labs.o19s.com/ltr-1.1.0-es6.5.4.zip
```

If using X-Pack security in your Elasticsearch cluster, there 
[may be additional steps.](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/x-pack.html)

## Step 2: Training and Uploading a Model

Detailed instructions on training models is outside the scope of this guide.
This requires the intervention of data scientists, who can recommend
appropriate tools and models. Use what works for you. In doing so, you'll
almost certainly be compiling
[Judgment lists](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/core-concepts.html#judgments-expression-of-the-ideal-ordering)
and
[feature sets](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/building-features.html)
that can be used by the training tool you select to generate a model that 
produces good search results. This can be a long journey, but once you get to the
end of it, you'll want to upload the model to the Learning to Rank plugin.

### Upload the Model to the Learning to Rank Plugin

You'll upload the model using a `POST` request, but first you need to make sure
you have a `_ltr` index and a feature set uploaded to the Learning to Rank
plugin. Use Kibana (or even better, the
[Monitoring widget](/docs/7-2/deploy/-/knowledge_base/d/installing-liferay-enterprise-search-monitoring)),
to make these tasks easier.

1.  If you don't already have an `_ltr` index, create one:

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
    Take note of the syntax used here, since it's required.

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

This is a very high level set of instructions, because there's not much to do in
@product@. To learn in more detail about what's required, see the
[Learning to Rank plugin's documentation](https://elasticsearch-learning-to-rank.readthedocs.io/en/latest/index.html).

Keep reworking those judgment lists!

## Step 3: Enable Learning to Rank

Enable Learning to Rank from Control Panel &rarr; Configuration &rarr; System
Settings &rarr; Search &rarr; Learning to Rank. There's a simple on/off
configuration and a text field where you must enter the name of the trained
model to apply to search queries.

The model in the previous step was named `linearregression`, so that's what
you'd enter. 

![Figure 1: Enable Learning to Rank in @product@ from the System Settings entry.](../../../../images-dxp/search-learning-to-rank.png)

That's all the configuration required to get the Elasticsearch Learning to Rank
plugin ingesting a trained model, a feature set, and search queries from
@product@.
