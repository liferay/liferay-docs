# Getting Collections [](id=getting-collections)

Requests for getting a collection are the same as those for non-collection 
resources. For example, an 
[authenticated request](/develop/tutorials/-/knowledge_base/7-1/making-authenticated-requests) 
to the `person` endpoint returns a collection that contains the portal's users. 
When sending a this request, use the credentials of an administrative user who 
has permission to view other portal users: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/person

The response (below) has two main parts: 

-   The list of elements returned as part of the collection. This is present in 
    the `member` attribute. This example contains data on two users: an 
    administrator (Livingstone Admin), and a user named Josiah Copeland. 

-   A set of metadata about the collection itself. This is the rest of the data 
    in the response. This lets any clients know how to operate with the 
    collection. 

This response is in the 
[JSON-LD hypermedia format](https://json-ld.org/spec/latest/json-ld/), 
which is the default response format for web APIs in @product@. For information 
on specifying other response formats, see the 
[content negotiation tutorial](/develop/tutorials/-/knowledge_base/7-1/hypermedia-formats-and-content-negotiation). 

    {
        "totalItems": 2,
        "numberOfItems": 2,
        "view": {
            "@id": "http://localhost:8080/o/api/p/person?page=1&per_page=30",
            "first": "http://localhost:8080/o/api/p/person?page=1&per_page=30",
            "last": "http://localhost:8080/o/api/p/person?page=1&per_page=30",
            "@type": ["PartialCollectionView"],
        },
        "@id": "http://localhost:8080/o/api/p/person",
        "member": [
            {
                "birthDate": "1970-01-01T00:00Z",
                "alternateName": "admin",
                "dashboardURL": "http://localhost:8080/user/admin",
                "email": "admin@livingstone.com",
                "familyName": "Admin",
                "gender": "male",
                "givenName": "Livingstone",
                "name": "Livingstone Admin",
                "profileURL": "http://localhost:8080/web/admin",
                "@type": ["Person"],
                "@id": "http://localhost:8080/o/api/p/person/36901",
                "tasks": "http://localhost:8080/o/api/p/person/36901/workflow-tasks",
                "@context": [{"tasks": {"@type": "@id"}}],
            },
            {
                "honorificPrefix": "Mr",
                "birthDate": "1970-01-01T00:00Z",
                "alternateName": "jcopeland",
                "dashboardURL": "http://localhost:8080/user/jcopeland",
                "email": "jcopeland@livingstone.com",
                "familyName": "Copeland",
                "gender": "male",
                "givenName": "Josiah",
                "jobTitle": "User Management and Platform Administrator",
                "name": "Josiah Copeland",
                "profileURL": "http://localhost:8080/web/jcopeland",
                "@type": ["Person"],
                "@id": "http://localhost:8080/o/api/p/person/37020",
                "tasks": "http://localhost:8080/o/api/p/person/37020/workflow-tasks",
                "@context": [{"tasks": {"@type": "@id"}}],
            }
        ],
        "operation": [
            {
                "expects": "http://localhost:8080/o/api/f/c/person",
                "method": "POST",
                "@id": "_:person/create",
                "@type": "Operation"
            }
        ],
        "@context": [
            {"@vocab": "http://schema.org/"},
            "https://www.w3.org/ns/hydra/core#"
        ],
        "@type": ["Collection"],
    }

## Related Topics

[Pagination](/develop/tutorials/-/knowledge_base/7-1/pagination)

[Making Authenticated Requests](/develop/tutorials/-/knowledge_base/7-1/making-authenticated-requests)

[Hypermedia Formats and Content Negotiation](/develop/tutorials/-/knowledge_base/7-1/hypermedia-formats-and-content-negotiation)
