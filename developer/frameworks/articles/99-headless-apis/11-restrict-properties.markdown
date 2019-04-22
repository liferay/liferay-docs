# Restrict properties [](id=restrict-properties)

Retrieving large entities or collections increase response's time and size. You can alleviate this by telling the server via the request the fields it should include in the response. This is known as *sparse
fieldsets*.

To make a request selecting which fields you want in the response, include the `fields` parameter in 
the URL with the name of the resource's attribute. 

To get the information of a BlogsEntry we would do a request like this:

    curl "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/59301" \
         -u 'test@liferay.com:test'
         
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
    
If we want to obtain just the headline, creator and creation date we can do this request:

    curl "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/59301?fields=headline,dateCreated,creator" \
         -u 'test@liferay.com:test'
         
    {
      "creator": {
        "familyName": "Test",
        "givenName": "Test",
        "id": 20130,
        "name": "Test Test",
        "profileURL": "/web/test"
      },
      "dateCreated": "2019-04-22T07:04:47Z",
      "headline": "New Headless APIs"
    }
    

In the response, the `creator` attribute is a nested JSON object. If we wanted to return just the name of the creator of the blog we can specify nested fields via dot notation. For example, to restrict to the `name` of the `creator` we would do creator.name like this:

    curl "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/59301?fields=headline,dateCreated,creator.name" \
         -u 'test@liferay.com:test'
         
    {
      "creator": {
        "name": "Test Test"
      },
      "dateCreated": "2019-04-22T07:04:47Z",
      "headline": "New Headless APIs"
    }

The `fields` parameter also works with collection resources to return the specified attributes for every collection item.

    curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20124/blog-postings/?fields=headline" \
         -u 'test@liferay.com:test'
    
    {
      "items": [
        {
          "headline": "New Headless APIs"
        },
        {
          "headline": "Authenticated requests"
        }
      ],
      "lastPage": 1,
      "page": 1,
      "pageSize": 20,
      "totalCount": 2
    }

## Related Topics [](id=related-topics)

[Navigating to a Related Resource]()