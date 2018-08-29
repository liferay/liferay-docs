# Hypermedia Formats and Content Negotiation [](id=hypermedia-formats-and-content-negotiation)

+$$$

**Note:** Hypermedia REST APIs are currently only available as a limited private 
alpha. 

$$$

The responses in the preceding examples use the 
[JSON-LD hypermedia format](https://json-ld.org/), 
which is the default response format for @product@'s hypermedia REST APIs. 
Alternatively, you can use other formats. Other well-known hypermedia formats 
include HAL, Siren, Collection+JSON, and Atom. Formats typically differ in the 
structure or semantics of resource metadata. There's no best hypermedia 
format; use the one that best fits your use case. 

+$$$

**Note:** Currently, Liferay's hypermedia REST APIs only support the JSON-LD and 
HAL hypermedia formats. Support for more hypermedia formats may be added in the
future. 

$$$

You use *content negotiation* to specify different hypermedia formats for use.
Content negotiation is how the client and server establish the format they use
to exchange messages. The client tells the server its preferred format via the
HTTP headers `Accept` and `Content-Type`. Each hypermedia format has a string
identifier (its MIME type) that you can use in the HTTP headers to specify the
format. The following table lists the MIME type for each supported format. 

| Hypermedia Format | &nbsp;MIME Type | 
| --------- | ----------------------- | 
| JSON-LD | [application/ld+json](https://www.iana.org/assignments/media-types/application/ld+json) |
| HAL | [application/vnd.hal+json, application/hal+json](https://www.iana.org/assignments/media-types/application/vnd.hal+json) |

When you send a request without specifying the hypermedia format, the server 
responds with the default JSON-LD. For example, here's such a request for a 
site: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/web-site/20126

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
            {"embeddedWebPages": {"@type": "@id"}},
            {"@vocab": "http://schema.org/"},
            "https://www.w3.org/ns/hydra/core#"
        ],
        "members": "http://localhost:8080/o/api/p/web-site/20126/person",
        "webSites": "http://localhost:8080/o/api/p/web-site/20126/web-site",
        "embeddedWebPages": "http://localhost:8080/o/api/p/web-site/20126/embedded-web-page"
    }

If you request the headers, the `Content-Type` lists the hypermedia format: 

    curl --head -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/web-site/20126

    HTTP/1.1 200 
    X-Content-Type-Options: nosniff
    Vary: Accept,Accept-Language,Accept-Encoding
    Date: Thu, 12 Jul 2018 14:26:03 GMT
    Content-Type: application/ld+json
    Transfer-Encoding: chunked

To get the response in HAL instead, specify `application/hal+json` in the 
request's `Accept` header. Note that the HAL response includes the same 
information as JSON-LD, but is structured differently: 

    curl -H "Accept: application/hal+json" -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/web-site/20126

    {
        "active": true,
        "name": "Livingstone Hotels & Resorts",
        "membershipType": "open",
        "publicUrl": "http://localhost:8080/web/guest",
        "_links": {
            "self": {"href": "http://localhost:8080/o/api/p/web-site/20126"},
            "contentSpace": {"href": "http://localhost:8080/o/api/p/content-space/20126"},
            "members": {"href": "http://localhost:8080/o/api/p/web-site/20126/person"},
            "webSites": {"href": "http://localhost:8080/o/api/p/web-site/20126/web-site"},
            "embeddedWebPages": {"href": "http://localhost:8080/o/api/p/web-site/20126/embedded-web-page"}
        }
    }

Requesting the headers, you can see that the response is in the HAL format: 

    curl --head -H "Accept: application/hal+json" -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/web-site/20126

    HTTP/1.1 200 
    Vary: Accept-Language,Accept-Encoding,Accept
    Date: Thu, 12 Jul 2018 14:36:19 GMT
    Content-Type: application/hal+json
    Transfer-Encoding: chunked

## Related Topics [](id=related-topics)

[API Vocabulary](/develop/tutorials/-/knowledge_base/7-1/api-vocabulary)

[Navigating Between Related Resources](/develop/tutorials/-/knowledge_base/7-1/navigating-between-related-resources)
