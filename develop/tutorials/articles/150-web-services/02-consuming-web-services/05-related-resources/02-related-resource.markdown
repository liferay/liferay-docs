# Navigating to a Related Resource [](id=navigating-to-a-related-resource)

Once you retrieve a resource, you can use the response to get its related 
resources. For example, the get request to the *Livingstone Hotels & Resorts* 
site in the 
[previous tutorial](/develop/tutorials/-/knowledge_base/7-1/navigating-from-a-collection-to-its-elements) 
produces a response that contains information about that site, including any 
related resources: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/web-site/20126

    {
        "active": true,
        "name": "Livingstone Hotels & Resorts",
        "membershipType": "open",
        "publicUrl": "http://localhost:8080/web/guest",
        "contentSpace": "http://localhost:8080/o/api/p/content-space/20126",
        "members": "http://localhost:8080/o/api/p/web-site/20126/person",
        "webSites": "http://localhost:8080/o/api/p/web-site/20126/web-site",
        "embeddedWebPages": "http://localhost:8080/o/api/p/web-site/20126/embedded-web-page",
        "@id": "http://localhost:8080/o/api/p/web-site/20126",
        "@type": ["WebSite"],
        "@context": [
            {"contentSpace": {"@type": "@id"}},
            {"members": {"@type": "@id"}},
            {"webSites": {"@type": "@id"}},
            {"embeddedWebPages": {"@type": "@id"}},
            {"@vocab": "http://schema.org/"},
            "https://www.w3.org/ns/hydra/core#"
        ]
    }

The exact attributes in the response depend on the resource. This example 
contains data on a site and has attributes that contain URLs to related 
resources. By sending requests to these URLs, which are API endpoints, you can 
retrieve the corresponding information: 

-   contentSpace
-   `members`: Users that belong to the site. 
-   `webSites`: Child sites. 
-   `embeddedWebPages`: Site pages. 

Also note that the attribute `publicURL` is a URL. However, this is the URL to 
access the site in a browser---it's not an API endpoint. So how can you identify 
which URLs are API endpoints for resources? This is an excellent question! The 
hypermedia format typically has a way to identify resources. This example uses 
the 
[JSON-LD format](https://json-ld.org/), 
which identifies related resources via the `@context` attribute. Note that the 
`@context` attribute in the above response lists the attributes that contain the 
resource URLs. Other formats may use a separate attribute (e.g., `_links`) to 
group related resource URLs separate from other URLs. 

Alternatively, you can identify related resources by consulting the schema 
definition for the resources. The schema sets the attributes' semantics and 
type. For more information about schemas and the model used in the API, see the 
[API Vocabulary](/develop/tutorials/-/knowledge_base/7-1/api-vocabulary) 
tutorial.

Note that it's typically easier to identify related resources via the response, 
because it's easier to automate. Relying on the API vocabulary or schema 
requires understanding and parsing the schema. 

## Related Topics

[Navigating from a Collection to its Elements](/develop/tutorials/-/knowledge_base/7-1/navigating-from-a-collection-to-its-elements)

[API Vocabulary](/develop/tutorials/-/knowledge_base/7-1/api-vocabulary)

[Hypermedia Formats and Content Negotiation](/develop/tutorials/-/knowledge_base/7-1/hypermedia-formats-and-content-negotiation)
