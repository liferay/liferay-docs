# Get Started: Invoke a Service [](id=get-started-invoke-a-service)

Once you know which API you want to call via the [OpenAPI profile](/01-discover-api.markdown), you can access do a request to that resource's URL. Suppose you want to retrieve all the blog entries of a site. If you consult the OpenAPI profile for the delivery API you can find this endpoint:

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

The only required parameter is the `siteId`, the identifier of the Site where the content is located. Internally in Liferay is modelled as a groupId and you can retrieve the id from the database, URL or the interface. To retrieve this content, you can make a GET request to the url, including the siteId, like this:

    curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/blog-postings/" \
         -u 'test@liferay.com:test'

If you do a request to your site with some sample content created you'll get something like this response:

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
    
The response is a JSON object with information about the collection of blogs and the attributes in this response contain information about the resource: 


-   `totalCount`: The total number of this resource's items. Since this example
    is for the blog posting resource, it lists the total number of blog postings
    in that site, which in this case is `1`. 
-   `pageSize`: The number of this resource's items to be included in this
    response. 
-   `page`: The number of the current page.
-   `lastPage`: The last page's number. 
-   `id`: Each item has an id, to allow a request to retrieve more information.
   
         
To get information on a specific blog, issue a GET request to the `blogPostingId` 
resource's URL ("/blog-postings/{blogPostingId}").

This is the response:

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

The API's consumer can select other formats to use (like XML). The [content negotiation tutorial]() discusses this further. 

Note that all the web APIs in @product@ are secured by basic authentication (a header in each request). Next, you'll learn how to make authenticated requests with OAuth or other mechanisms. 

## Related Topics [](id=related-topics)

