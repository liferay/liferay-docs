---
header-id: restrict-properties
---

# Restrict properties

[TOC levels=1-4]

Retrieving large entities or collections increases the response's size and uses 
more bandwidth. You can alleviate this by telling the server via the request 
which fields it should include in the response. This is known as 
*sparse fieldsets*. To make a request with sparse fieldsets, include the 
`fields` parameter in the URL with the name of each field's attribute. 

For example, this request doesn't use sparse fieldsets and therefore returns all 
the fields of a blog posting: 

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

To get only the headline, creation date, and creator, append the `fields` 
parameter to the URL with the fields `headline`, `dateCreated`, and `creator`: 

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

In the response, the `creator` attribute is a nested JSON object. To return only 
the creator's name, specify that nested field via dot notation (`creator.name`): 

    curl "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/59301?fields=headline,dateCreated,creator.name" \
         -u 'test@liferay.com:test'

    {
      "creator": {
        "name": "Test Test"
      },
      "dateCreated": "2019-04-22T07:04:47Z",
      "headline": "New Headless APIs"
    }

The `fields` parameter also works with collection resources to return the 
specified attributes for every collection item. For example, this request gets 
the headlines for all the blog postings in the Site with the ID `20124`: 

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

## Related Topics

[Navigating to a Related Resource]()
