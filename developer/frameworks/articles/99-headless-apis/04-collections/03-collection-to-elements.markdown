# Navigating from a Collection to its Elements [](id=navigating-from-a-collection-to-its-elements)

When you [get a collection](), 
you can use the response to get an element of that collection. Follow these 
steps to do so: 

1.  Get a collection. This example gets a list of Sites by sending 
    [an authenticated request]() 
    to the `user-account` collection: 

        curl "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts" \
             -u 'test@liferay.com:test'

    Recall from the 
    [Getting Collections tutorial](/develop/tutorials/-/knowledge_base/7-1/getting-collections) 
    that the response's `items` attribute contains the collection elements. In
    this case, there are two User: Test Test and Javier Gamarra: 

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

2.  In the response, locate the id of the element you want and look in the OpenAPI profile the GET item request corresponding to UserAccount: "/user-accounts/{userAccountId}". 

3.  Send a GET request to that URL. For example, this request gets 
    information from the *Livingstone Hotels & Resorts* Site: 

        curl "http://localhost:8080/o/headless-admin-user/v1.0/user-accounts/20130" \
             -u 'test@liferay.com:test'

## Related Topics [](id=related-topics)

[Working with Collections of Data]()

[Pagination]()

[Making Authenticated Requests]()