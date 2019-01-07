# Navigating from a Collection to its Elements [](id=navigating-from-a-collection-to-its-elements)

When you 
[get a collection](/develop/tutorials/-/knowledge_base/7-1/getting-collections), 
you can use the response to get an element of that collection. Follow these 
steps to do so: 

1.  Get a collection. This example gets a list of Sites by sending 
    [an authenticated request](/develop/tutorials/-/knowledge_base/7-1/making-authenticated-requests) 
    to the `web-site` collection: 

        curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/web-site

    Recall from the 
    [Getting Collections tutorial](/develop/tutorials/-/knowledge_base/7-1/getting-collections) 
    that the response's `member` attribute contains the collection elements. In
    this case, there are two Sites: *Global* and *Livingstone Hotels & Resorts*: 

        {
            "totalItems": 2,
            "numberOfItems": 2,
            "view": {
                "@id": "http://localhost:8080/o/api/p/web-site?page=1&per_page=30",
                "first": "http://localhost:8080/o/api/p/web-site?page=1&per_page=30",
                "last": "http://localhost:8080/o/api/p/web-site?page=1&per_page=30",
                "@type": ["PartialCollectionView"],
            },
            "@id": "http://localhost:8080/o/api/p/web-site",
            "member": [
                {
                    "active": true,
                    "name": "Global",
                    "membershipType": "system",
                    "@type": ["WebSite"],
                    "@id": "http://localhost:8080/o/api/p/web-site/20135",
                    "contentSpace": "http://localhost:8080/o/api/p/content-space/20135",
                    "@context": [
                        {"contentSpace": {"@type": "@id"}},
                        {"members": {"@type": "@id"}},
                        {"webSites": {"@type": "@id"}},
                        {"embeddedWebPages": {"@type": "@id"}}
                    ],
                    "members": "http://localhost:8080/o/api/p/web-site/20135/person",
                    "webSites": "http://localhost:8080/o/api/p/web-site/20135/web-site",
                    "embeddedWebPages": "http://localhost:8080/o/api/p/web-site/20135/embedded-web-page"
                },
                {
                    "active": true,
                    "name": "Livingstone Hotels & Resorts",
                    "membershipType": "open",
                    "publicUrl": "http://localhost:8080/web/guest",
                    "@type": ["WebSite"],
                    "@id": "http://localhost:8080/o/api/p/web-site/20126",
                    "contentSpace": "http://localhost:8080/o/api/p/content-space/20126",
                    "@context": [
                        {"contentSpace": {"@type": "@id"}},
                        {"members": {"@type": "@id"}},
                        {"webSites": {"@type": "@id"}},
                        {"embeddedWebPages": {"@type": "@id"}}
                    ],
                    "members": "http://localhost:8080/o/api/p/web-site/20126/person",
                    "webSites": "http://localhost:8080/o/api/p/web-site/20126/web-site",
                    "embeddedWebPages": "http://localhost:8080/o/api/p/web-site/20126/embedded-web-page"
                }
            ],
            "@context": [
                {"@vocab": "http://schema.org/"},
                "https://www.w3.org/ns/hydra/core#"
            ],
            "@type": ["Collection"],
        }

2.  In the response, locate the URL of the element you want. Since the above
    response is in the 
    [JSON-LD format](https://json-ld.org/), 
    each element's `@id` attribute contains that element's URL. In this example, 
    you'll get data from the *Livingstone Hotels & Resorts* Site, whose URL is 
    `http://localhost:8080/o/api/p/web-site/20126`. 

3.  Send a GET request to that element's URL. For example, this request gets 
    information from the *Livingstone Hotels & Resorts* Site: 

        curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/web-site/20126

## Related Topics [](id=related-topics)

[Working with Collections of Data](/develop/tutorials/-/knowledge_base/7-1/working-with-collections-of-data)

[Pagination](/develop/tutorials/-/knowledge_base/7-1/pagination)

[Making Authenticated Requests](/develop/tutorials/-/knowledge_base/7-1/making-authenticated-requests)
