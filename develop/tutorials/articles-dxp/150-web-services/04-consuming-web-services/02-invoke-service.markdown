# Get Started: Invoke a Service [](id=get-started-invoke-a-service)

+$$$

**Note:** Hypermedia REST APIs are currently only available as a limited private 
alpha. 

$$$

Once you identify the resource you want via the 
[API's root entry point](/develop/tutorials/-/knowledge_base/7-1/get-started-discover-the-api), 
you can access that resource's URL. Suppose you want to manage organizations. If
you first send a request to the API's home URL, it returns these resources: 

    {
        "@id" : "http://localhost:8080/o/api",
        "collection" : [ {
            "@id" : "http://localhost:8080/o/api/p/person",
            "@type" : [ "Collection" ]
        }, {
            "@id" : "http://localhost:8080/o/api/p/content-space",
            "@type" : [ "Collection" ]
        }, {
            "@id" : "http://localhost:8080/o/api/p/web-site",
            "@type" : [ "Collection" ]
        }, {
            "@id" : "http://localhost:8080/o/api/p/organization",
            "@type" : [ "Collection" ]
        } ],
        "@type" : "EntryPoint",
        "@context" : [ {
            "@vocab" : "http://schema.org/"
        }, "https://www.w3.org/ns/hydra/core#" ]
    }

To get information on organizations, issue a GET request to the `organization` 
resource's URL. Note that the following request isn't authenticated. Although 
you'll typically want to send authenticated requests, an unauthenticated request 
is made here for simplicity: 

    curl http://localhost:8080/o/api/p/organization

This is the response: 

    {
        "totalItems": 0,
        "numberOfItems": 0,
        "view": {
            "@id": "http://localhost:8080/o/api/p/organization?page=1&per_page=30",
            "first": "http://localhost:8080/o/api/p/organization?page=1&per_page=30",
            "last": "http://localhost:8080/o/api/p/organization?page=1&per_page=30",
            "@type": [
                "PartialCollectionView"
            ],
        },
        "@id": "http://localhost:8080/o/api/p/organization",
        "@context": [
            {
                "@vocab": "http://schema.org/"
            },
            "https://www.w3.org/ns/hydra/core#"
        ],
        "@type": [
            "Collection"
        ],
    }

The attributes in this response contain information about the resource: 

-   `@type`: The resource's type. This resource is a collection. 
-   `@id`: The resource's URL. Note that this is a self-reference: it's the same
    URL the GET request was sent to. 
-   `totalItems`: The total number of this resource's items. Since this example
    is for the organization resource, it lists the total number of organizations
    in the portal, which in this case is `0`. 
-   `numberOfItems`: The number of this resource's items included in this
    response. Since returning the total number of items impacts performance for
    large data sets, returning a subset of items is typically more efficient. In
    this example, the number is `0` because the collection is empty. 
-   `view`: The 
    [pagination](/develop/tutorials/-/knowledge_base/7-1/pagination) 
    info. Collections are paginated. The `totalItems` are paginated into 
    `numberOfItems`. The `view` attribute contains the following URLs for the 
    pages: 
    -   `@id`: The current page's URL. 
    -   `first`: The first page's URL. 
    -   `last`: The last page's URL. 
    -   `type`: The page's type. In this example, this is a 
        `PartialCollectionView`. 

Since this example is an empty collection, there are no elements. It serves as a 
simple example of the typical collection structure in the 
[JSON-LD hypermedia format](https://json-ld.org). 
The format is specified on the response headers, which you can see by requesting 
only the headers: 

    curl --head http://localhost:8080/o/api/p/organization

The `Content-Type` header's value is the JSON-LD MIME type's
`application/ld+json`: 

    HTTP/1.1 200 
    X-Content-Type-Options: nosniff
    X-Frame-Options: SAMEORIGIN
    X-XSS-Protection: 1
    Set-Cookie: JSESSIONID=F3971727C9C8607E194AB63E50BC667B; Path=/; HttpOnly
    Vary: Accept,Accept-Language,Accept-Encoding
    Date: Mon, 02 Jul 2018 11:44:00 GMT
    Content-Type: application/ld+json
    Transfer-Encoding: chunked

The API's consumer can select other hypermedia formats to use. The 
[content negotiation tutorial](/develop/tutorials/-/knowledge_base/7-1/hypermedia-formats-and-content-negotiation) 
discusses this further. 

Note that all the web APIs in @product@ are secured. When you make
unauthenticated requests like those above, the API service filters the response
to remove any items that the user making the request doesn't have permission to
view. Unauthenticated requests access the API as a generic guest user, and
therefore retrieve only public items. Next, you'll learn how to make
authenticated requests. 

## Related Topics [](id=related-topics)

[Get Started: Discover the API](/develop/tutorials/-/knowledge_base/7-1/get-started-discover-the-api)

[Navigating Between Related Resources](/develop/tutorials/-/knowledge_base/7-1/navigating-between-related-resources)
