---
header-id: navigating-from-a-collection-to-its-elements
---

# Navigating from a Collection to its Elements

[TOC levels=1-4]

When you 
[get a collection](liferay.com), 
you can use the response to get an element of that collection. Follow these 
steps to do so: 

1.  Get a collection. This example gets a list of users by sending 
    [an authenticated request](liferay.com) 
    to the `user-accounts` collection: 

        curl "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts" \
             -u 'test@liferay.com:test'

    Recall from 
    [Getting Collections](/develop/tutorials/-/knowledge_base/7-2/getting-collections) 
    that the response's `items` attribute contains the collection elements. In
    this case, the collection contains two users: Test Test and Javier Gamarra: 

        {
            "totalItems": 2,
            "numberOfItems": 2,
            "view": {
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
                      "roleBriefs": [
                        {
                          "id": 20108,
                          "name": "Administrator"
                        },
                        {
                          "id": 20111,
                          "name": "Power User"
                        },
                        {
                          "id": 20112,
                          "name": "User"
                        }
                      ],
                      "siteBriefs": [
                        {
                          "id": 20128,
                          "name": "Global"
                        },
                        {
                          "id": 20124,
                          "name": "Guest"
                        }
                      ]
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
                      "roleBriefs": [
                        {
                          "id": 20112,
                          "name": "User"
                        }
                      ],
                      "siteBriefs": [
                        {
                          "id": 20128,
                          "name": "Global"
                        },
                        {
                          "id": 20124,
                          "name": "Guest"
                        }
                      ]
                    }
                  ],
                  "lastPage": 1,
                  "page": 1,
                  "pageSize": 20,
                  "totalCount": 2
                }

2.  In the response, locate the ID of the element you want and look in the 
    OpenAPI profile for the appropriate GET item endpoint. For example, the 
    `user-accounts` GET item endpoint is `/user-accounts/{userAccountId}`. 

3.  Send a GET request to that endpoint. For example, this request gets 
    information for the user with the ID `59347` (Javier Gamarra): 

        curl "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts/59347" \
             -u 'test@liferay.com:test'

## Related Topics

[Working with Collections of Data]()

[Pagination]()

[Making Authenticated Requests]()
