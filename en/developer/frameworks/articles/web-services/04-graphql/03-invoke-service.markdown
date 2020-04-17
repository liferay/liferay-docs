---
header-id: get-started-invoke-a-service
---

# Get Started: Invoke a Service

[TOC levels=1-4]

Once you know which API you want to call via the GraphQL-introspected
documentation, you can send a request using a POST body. For example, suppose
you want to retrieve all the blog entries from a Site. If you consult the
GraphQL documentation you can find this endpoint: 

![Figure 1: GraphQL exposes a definition for BlogPostings.](../../../images/graphql-blog-postings.png)

If you add the full query with Altair/GraphiQL, you'll see a result like this:

```
query{
  blogPostings(filter: ______, page: ______, pageSize: ______, search: ______, siteId: ______, siteKey: ______, sort: ______){
    items
    page
    pageSize
    totalCount
  }
}
```

The only required parameter is `siteId` or `siteKey` (as of 7.2 FP4), the ID, or the internal
name (like *guest*) of the blog posting's Site. Internally, the `siteId` is
a `groupId` that you can retrieve from the database, a URL, or @product@'s UI
via the Site Administration menu. For more information, see 
[How to get SiteId](/docs/7-2/frameworks/-/knowledge_base/f/how-to-get-site-id).

A regular query would ignore optional parameters and return more elements of the
list, identified by the property `items`:

```
query {
  blogPostings(siteKey: "guest") {
    items {
      alternativeHeadline
      articleBody
      creator {
        name
      }
      dateCreated
      dateModified
      datePublished
      description
      encodingFormat
      friendlyUrlPath
      headline
      id
      keywords
      numberOfComments
      relatedContents {
        title
      }
      siteId
      taxonomyCategoryIds
      viewableBy
    }
    page
    pageSize
    totalCount
  }
}
```

In GraphQL, you must list explicitly every field you want to return in the
request. Complex objects, like `items`, `creator`, or `relatedContents` can
not be returned fully: you must specify at least one field (or none).

To execute the query, make a `POST` request with an `Authentication` header and
the query in a JSON object under the key `query`. Don't forget to escape
strings! 

The following request gets the Site's blog postings by providing the site key
(`guest`):

```bash
curl -X "POST" "http://localhost:8080/o/graphql" \
     -H 'Content-Type: text/plain; charset=utf-8' \
     -u 'test@liferay.com:test' \
     -d $'{
  "query": "query { blogPostings(siteKey: \\"guest\\") { items { alternativeHeadline articleBody creator { id name } dateCreated dateModified datePublished description encodingFormat friendlyUrlPath headline id keywords numberOfComments relatedContents { title } siteId taxonomyCategoryIds viewableBy } page pageSize totalCount } }"
  }'
```

If you send this request to a Site that contains some Blog entries, the
response may look like this: 

```json
{
  "data": {
    "blogPostings": {
      "items": [
        {
          "alternativeHeadline": "",
          "articleBody": "<p>Content</p>",
          "creator": {
            "id": 20124,
            "name": "Test Test"
          },
          "dateCreated": "2019-10-29T17:48:03Z",
          "dateModified": "2019-10-29T17:48:03Z",
          "datePublished": "2019-10-29T17:47:00Z",
          "description": "",
          "encodingFormat": "text/html",
          "friendlyUrlPath": "title",
          "headline": "Title",
          "id": 37644,
          "keywords": [],
          "numberOfComments": 0,
          "relatedContents": [],
          "siteId": 20118,
          "taxonomyCategoryIds": null,
          "viewableBy": null
        }
      ],
      "page": 1,
      "pageSize": 20,
      "totalCount": 1
    }
  }
}
```

This response is a JSON object with information about the collection of blogs.
The attributes contain information about the resource (blogs, in this case).
Also, note that the results are paginated. The `*page*` attributes refer to
pages of results. Here's a description of some common attributes: 

`id`: Each item has an ID. You can use the ID to retrieve more information about
that item. For example, there are two `id` attributes in the above response: one
for the blog posting (`37644`) and one for the blog post's creator (`20124`). 

`page`: The current page's page number. The page in the above response is `1`. 

`pageSize`: The possible number of this resource's items to be included in
a single page. In the above response this is `20`. 

`totalCount`: The total number of this resource's existing items (independent of
pagination). The above response lists the total number of blog postings (`1`) in
a Site. 

To get information on a specific blog posting, send a POST request with the
`blogPostingId` to this query:

```
query {
  blogPosting(blogPostingId: 37644) {
    headline
    id
  }
}
``` 
 
## GraphQL Clients

The examples above show the GraphQL requests as cURL operations but you can use
any GraphQL clients available. We have made heavy use of
[Apollo](https://www.apollographql.com/docs/), the official React client and the
[Vue integration](https://github.com/vuejs/vue-apollo) without any issues.
 
