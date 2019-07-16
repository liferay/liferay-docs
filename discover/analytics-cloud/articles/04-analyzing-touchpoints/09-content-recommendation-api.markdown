---
header-id: content-recommendation-api
---

# Content Recommendation API

The Content Recommendation API in Liferay Analytics Cloud suggests content based 
on user interactions with content in a Liferay DXP instance. This is possible 
because once Liferay DXP is connected to Analytics Cloud, it sends interaction 
events each time a user visits a page. Each of those events contains information 
about the content the user consumes. Analytics Cloud collects and processes this 
information. 

The Content Recommendation API contains services that enable the following: 

-   Discover similar tags (content) based on the current tags the user is 
    browsing. 
-   Discover tags based on the user's interest over time. 

## Discover Similar Tags

This service returns a list of tags (terms) similar to those passed as 
parameters. The list of similar terms is sorted by weight and returned as JSON. 

Here's this API's endpoint: 

**GET:** `{url}/api/1.0/interests/terms/related{?page,size,terms}`

Here are the parameters: 

`int page (defaultValue = 0)`: The page of results. For example, the default 
value `0` specifies the first page of results. 

`int size (defaultValue = 5)`: The number of results to include on each page. 
The default value `5` specifies five results on each page. 

`List<String> terms`: The tags to use for determining the list of similar tags. 
This is the only required parameter. 

`double termWeightThreshold (defaultValue = 0.01)`: The relevance level (weight) 
for determining related terms. The default value of `0.01` returns all tags with 
a weight above 1%. 

Together, the `page` and `size` parameters control the number of similar terms 
to include in the response. Note that this API can return up to 100 terms. If 
the number of terms exceeds that limit, the API returns an error. 

For example, here's a JSON response that contains related terms: 

```json
{
  "_embedded": {
    "interest-terms": [
      "jquery",
      "html",
      "sql",
      "mysql",
      "java"
    ]
  },
  "page": {
    "number": 0,
    "size": 5,
    "totalPages": 7,
    "totalElements": 35
  }
}
```

## Discover Tags Based on Interest

This service returns a list of tags (terms) that are relevant to a specific 
user. You must pass that user's ID as a parameter. The list of similar terms is 
sorted by weight and returned as JSON. 

Here's this API's endpoint: 

**GET:** `{url}/api/1.0/interests/terms/{userId}`

There are also three optional parameters you can use. You can use these 
parameters to fine-tune the terms that the API returns. Note that topics are 
groups of terms: 

`int termsPerTopic (defaultValue = 3)`: The number of terms to consider per 
topic/subject. 

`double termWeightThreshold (defaultValue = 0.01)`: The relevance level for 
determining terms of interest. The default value of `0.01` returns all terms 
with a weight above 1%. 

`int topicsLength (defaultValue = 3)`: The number of topics to consider. 

Decreasing `termsPerTopic` and increasing the `topicsLength` might lead to an 
increment of the subject variation (terms from different topics being 
recommended to users). 

Here's an example request that contains only a user ID: 

    {url}/api/1.0/interests/terms/953be104-5540-abf8-59b8-55f895200acc

And here's an example response in JSON: 

```json
{
  "_embedded": {
    "interest-topics": [
      {
        "terms": [
          {
            "weight": 0.0945945945945946,
            "keyword": "javascript"
          },
          {
            "weight": 0.08648648648648649,
            "keyword": "jquery"
          },
          {
            "weight": 0.07027027027027027,
            "keyword": "html"
          }
        ],
        "weight": 0.08653350323695352,
        "id": 7
      },
      {
        "terms": [
          {
            "weight": 0.1322314049586777,
            "keyword": "php"
          },
          {
            "weight": 0.06060606060606061,
            "keyword": "sql"
          },
          {
            "weight": 0.05509641873278237,
            "keyword": "mysql"
          }
        ],
        "weight": 0.08027610626914822,
        "id": 1
      },
      {
        "terms": [
          {
            "weight": 0.15204678362573099,
            "keyword": "java"
          },
          {
            "weight": 0.10526315789473684,
            "keyword": "android"
          },
          {
            "weight": 0.023391812865497075,
            "keyword": "multithreading"
          }
        ],
        "weight": 0.07511374008317741,
        "id": 9
      }
    ]
  }
}
```

