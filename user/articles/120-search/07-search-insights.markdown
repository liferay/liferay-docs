---
header-id: search-insights
---

# Search Insights

[TOC levels=1-4]

**[Feature intended for testing and development only]**

**[Works with Elasticsearch only]**

Add the Search Insights Widget to the Search Page to inspect the full query
string that's constructed by the back-end search code when the User enters a
keyword and the response string returned from the search engine.

In @product-ver@, the Insights widget now adds the response string to
the widget's output, and the _Enable Score Explanation_ option (enabled by
default) prints a relevance score explanation for each returned result.

When a search query is processed, results are returned. The concept of
_Relevance_ determines how well results match the query. The Score explanation
for returned search documents helps clarify seemingly odd results, letting you
optionally adjust the relevancy scoring process by making matches in certain
fields count for more (_boosting_ the fields is the term for this).

## Inspecting The Search Query String

To see the Search Insights widget in action, navigate to a Search Page in
your test server and add it from the _Add &rarr; Widgets_ menu.

![Figure 1: The Search Insights widget is helpful during testing and development.](../../images/search-insights-default.png)

Once you search for keywords that return Search Results, the Search Insights
portlet displays the returned query string in all its glory. 

![Figure 2: The full query string isn't for the faint of heart. This example is clipped to spare the reader.](../../images/search-insights-test-search.png)

## Explaining Search Results

To enable or disable the Explain option,

1.  Open the Search Insight widget's Configuration screen.

2.  There's just one option: _Enable Score Explanation_.
    It's a boolean field that's enabled by default.
    De-select it to disable the explanation of each result's relevance score.

Under the hood, the Explain option in the Search Insights widget is exposing an Elasticsearch API: 
[Explain](https://www.elastic.co/guide/en/elasticsearch/reference/current/search-explain.html). 
See the Elasticsearch documentation for more details.

Here's an abbreviated portion of the scoring explanation for the Search Document
of the Test Test User when the searched keyword was _test_:

    ```json
    _explanation":{  
       "value":9.461341,
       "description":"sum of:",
       "details":[  
          {  
             "value":9.461341,
             "description":"sum of:",
             "details":[  
                {  
                   "value":1.0,
                   "description":"emailAddress:*test*",
                   "details":[  

                   ]
                },
                {  
                   "value":5.0,
                   "description":"userName:*test*^5.0",
                   "details":[  

                   ]
                },
                {  
                   "value":0.72928625,
                   "description":"sum of:",
                   "details":[  
                      ... 

                { 
                   "value":1.0027686,
                   "description":"sum of:",
                   "details":[  
                      ...
                      {  
                {  
                   "value":0.72928625,
                   "description":"sum of:",
                   "details":[  
                      ...
                {  
                   "value":1.0,
                   "description":"screenName:*test*",
                   "details":[  

                   ]
                }
             ]
          },
          ...
       ]
    }}]}
    ```

Now you're able to see the entire query string, the response string, and how
each returned Search Document was scored.
