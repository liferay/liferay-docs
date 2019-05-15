# Getting Collections [](id=getting-collections)

Requests for collection resources are the same as those for non-collection 
resources. For example, an 
[authenticated request](liferay.com) 
to the `UserAccount` endpoint returns a collection containing the portal's 
users. When sending this request, use the credentials of an administrative user 
who has permission to view other portal users: 

    curl "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts" \
         -u 'test@liferay.com:test'

The response (below) has two main parts: 

-   The list of collection elements, inside the `items` attribute. This example 
    contains data on two users: an administrator (Test), and a user named Javier 
    Gamarra. 

-   A set of metadata about the collection. This is the rest of the data in the 
    response. This lets clients know how to use the collection. 

This response is in JSON, which is the default response format for web APIs in 
@product@. For information on specifying other response formats, see 
[API Formats and Content Negotiation](liferay.com). 

    {
      "items": [
        {
          "alternateName": "test",
          "birthDate": "1970-01-01T00:00:00Z",
          "contactInformation": {},
          "dashboardURL": "/user/test",
          "dateCreated": "2019-04-17T20:37:19Z",
          "dateModified": "2019-04-22T09:56:35Z",
          "emailAddress": "test@liferay.com",
          "familyName": "Test",
          "givenName": "Test",
          "id": 20130,
          "name": "Test Test",
          "profileURL": "/web/test",
          ...
        },
        {
          "alternateName": "nhpatt",
          "birthDate": "1970-01-01T00:00:00Z",
          "contactInformation": {},
          "dateCreated": "2019-04-22T10:38:36Z",
          "dateModified": "2019-04-22T10:38:37Z",
          "emailAddress": "nhpatt@gmail.com",
          "familyName": "Gamarra",
          "givenName": "Javier",
          "id": 59347,
          "name": "Javier Gamarra",
          ...
        }
      ],
      "lastPage": 1,
      "page": 1,
      "pageSize": 20,
      "totalCount": 2
    }

## Related Topics [](id=related-topics)

[Pagination]()

[Making Authenticated Requests]()

[API Formats and Content Negotiation]()
