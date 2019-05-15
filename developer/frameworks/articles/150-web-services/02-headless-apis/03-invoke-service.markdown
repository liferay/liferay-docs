---
header-id: get-started-invoke-a-service
---

# Get Started: Invoke a Service

[TOC levels=1-4]

Once you know which API you want to call via the 
[OpenAPI profile](/develop/tutorials/-/knowledge_base/7-2/get-started-discover-the-api), 
you can send a request to that resource's URL. For example, suppose you want to 
retrieve all the blog entries from a Site. If you consult the OpenAPI profile 
for @product@'s delivery API, you can find this endpoint: 

    "/sites/{siteId}/blog-postings":
            get:
                operationId: getSiteBlogPostingsPage
                parameters:
                    - in: path
                      name: siteId
                      required: true
                      schema:
                          format: int64
                          type: integer
                    - in: query
                      name: filter
                      schema:
                          type: string
                    - in: query
                      name: page
                      schema:
                          type: integer
                    - in: query
                      name: pageSize
                      schema:
                          type: integer
                    - in: query
                      name: search
                      schema:
                          type: string
                    - in: query
                      name: sort
                      schema:
                          type: string
                responses:
                    200:
                        content:
                            application/json:
                                schema:
                                    items:
                                        $ref: "#/components/schemas/BlogPosting"
                                    type: array
                        description: ""
                tags: ["BlogPosting"]

The only required parameter is `siteId`, the ID of the blog postings' Site. 
Internally, the `siteId` is a `groupId` that you can retrieve from the database, 
a URL, or @product@'s UI via the Site Administration menu. The following GET 
request gets the site's blog postings by providing the site ID (`20124`) in the 
URL: 

    curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/blog-postings/" \
         -u 'test@liferay.com:test'

If you send such a request to a site that contains some blog entries, the 
response should look something like this: 

    {
      "items": [
        {
          "alternativeHeadline": "The power of OpenAPI & Liferay",
          "articleBody": "<p>We are happy to announce...</p>",
          "creator": {
            "familyName": "Test",
            "givenName": "Test",
            "id": 20130,
            "name": "Test Test",
            "profileURL": "/web/test"
          },
          "dateCreated": "2019-04-22T07:04:47Z",
          "dateModified": "2019-04-22T07:04:51Z",
          "datePublished": "2019-04-22T07:02:00Z",
          "encodingFormat": "text/html",
          "friendlyUrlPath": "new-headless-apis",
          "headline": "New Headless APIs",
          "id": 59301,
          "numberOfComments": 0,
          "siteId": 20124
        }
      ],
      "lastPage": 1,
      "page": 1,
      "pageSize": 20,
      "totalCount": 1
    }

This response is a JSON object with information about the collection of blogs. 
The response's attributes contain information about the resource (blogs, in this 
case). Also note that the results are paginated. The `*page*` attributes refer 
to pages of results. Here's a description of some common attributes: 

`id`: Each item has an ID. You can use the ID to retrieve more information about 
that item. For example, there are two `id` attributes in the above response: one 
for the blog posting (`59301`) and one for the blog post's creator (`20130`). 

`lastPage`: The page number of the final page of results. The above response 
only contains a single page, so its last page is `1`. 

`page`: The page number of the current page. The page in the above response is 
`1`. 

`pageSize`: The possible number of this resource's items to be included in a 
single page. In the above response this is `20`. 

`totalCount`: The total number of this resource's existing items (independent of 
pagination). The above response lists the total number of blog postings (`1`) in 
a Site. 

To get information on a specific blog posting, send a GET request to the 
`blogPostingId` resource's URL with the blog posting's ID 
(`/blog-postings/{blogPostingId}`). For example, the URL for such a request to 
the blog posting in the above response is `/blog-postings/59301`. Here's an 
example response: 

    {
      "alternativeHeadline": "The power of OpenAPI & Liferay",
      "articleBody": "<p>We are happy to announce...</p>",
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T07:04:47Z",
      "dateModified": "2019-04-22T07:04:51Z",
      "datePublished": "2019-04-22T07:02:00Z",
      "encodingFormat": "text/html",
      "friendlyUrlPath": "new-headless-apis",
      "headline": "New Headless APIs",
      "id": 59301,
      "numberOfComments": 0,
      "siteId": 20124
    }

Although this response is JSON, the API's consumer can select other formats to 
use (like XML). For more information, see 
[API Formats and Content Negotiation](/develop/tutorials/-/knowledge_base/7-2/api-formats-and-content-negotiation). 

## Related Topics

[Get Started: Discover the API](/develop/tutorials/-/knowledge_base/7-2/get-started-discover-the-api)

[API Formats and Content Negotiation](/develop/tutorials/-/knowledge_base/7-2/api-formats-and-content-negotiation)
