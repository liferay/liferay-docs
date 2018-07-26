# Embedding Related Resources [](id=embedding-related-resources)

[The tutorial on getting related resources](/develop/tutorials/-/knowledge_base/7-1/navigating-to-a-related-resource) 
shows you how to send a request to a resource and identify related resources in 
the response. Getting information on such a related resource, however, requires 
that you send another request to that resource. Instead, you can get information 
on a related resource by embedding it in the first request, which avoids the 
need for a second request. This is more efficient and can improve your API 
client's performance. Note, however, that this is only appropriate for cases in 
which you already know you want to get a related resource. 

Although embedding related resource requests is more efficient, it also 
increases the response's size. Responses that include more than one attribute or 
recurse into higher levels of nested resources can be especially large. You can 
alleviate this by telling the server via the request which fields it should 
include in the response. This is known as sparse fieldsets, which are covered in 
another tutorial. API clients typically use embedded resources and sparse 
fieldsets together to tune latency and performance of client-server 
communication. 
<!-- Link to sparse fieldsets tutorial once it exists. -->

To make a request with an embedded resource, include the `embedded` parameter in 
the URL with the name of the related resource's attribute. For example, to get 
information on a site's `content-space`, send a GET request to the site's API 
endpoint with `embedded=contentSpace` appended to the URL: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/web-site/20126?embedded=contentSpace

In the response, the `contentSpace` attribute is a nested JSON object that 
contains the same information you could retrieve via a separate request to the 
`contentSpace` URL: 

    {
        "active": true,
        "name": "Livingstone Hotels & Resorts",
        "membershipType": "open",
        "publicUrl": "http://localhost:8080/web/guest",
        "contentSpace": {
            "active": true,
            "name": "Livingstone Hotels & Resorts",
            "membershipType": "open",
            "folder": "http://localhost:8080/o/api/p/root-folder/20126",
            "blogPosts": "http://localhost:8080/o/api/p/content-space/20126/blog-posting",
            "vocabularies": "http://localhost:8080/o/api/p/content-space/20126/vocabularies",
            "contentStructures": "http://localhost:8080/o/api/p/content-space/20126/content-structures",
            "keywords": "http://localhost:8080/o/api/p/content-space/20126/keywords",
            "structuredContents": "http://localhost:8080/o/api/p/content-space/20126/structured-contents",
            "@id": "http://localhost:8080/o/api/p/content-space/20126",
            "@type": ["ContentSpace"],
            "@context": [
                {"folder": {"@type": "@id"}},
                {"blogPosts": {"@type": "@id"}},
                {"vocabularies": {"@type": "@id"}},
                {"contentStructures": {"@type": "@id"}},
                {"keywords": {"@type": "@id"}},
                {"structuredContents": {"@type": "@id"}}
            ]
        },
        "members": "http://localhost:8080/o/api/p/web-site/20126/person",
        "webSites": "http://localhost:8080/o/api/p/web-site/20126/web-site",
        "embeddedWebPages": "http://localhost:8080/o/api/p/web-site/20126/embedded-web-page",
        "@id": "http://localhost:8080/o/api/p/web-site/20126",
        "@type": ["WebSite"],
        "@context": [
            {"members": {"@type": "@id"}},
            {"webSites": {"@type": "@id"}},
            {"embeddedWebPages": {"@type": "@id"}},
            {"@vocab": "http://schema.org/"},
            "https://www.w3.org/ns/hydra/core#"
        ]
    }

To get information on multiple attributes, supply them to the `embedded` 
parameter in a comma-delimited list. This example does so by adding the 
hypothetical attributes `field02` and `field03` to `contentSpace`: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/web-site/20126?embedded=contentSpace,field02,field03

You can also embed nested attributes in the request via dot notation. For 
example, to get `contentSpace`'s folder attribute, add `contentSpace.folder` to 
the embedded parameter. Note that this also gets `contentSpace` itself: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/web-site/20126?embedded=contentSpace.folder

Also note that even though the response includes all the requested information 
(e.g., the site and `contentSpace`), this example omits it to focus on the 
folder: 

    {
        "@id": "http://localhost:8080/o/api/p/web-site/20126",
        "name": "Livingstone Hotels & Resorts",
        // ... 
        "contentSpace": {
            "@id": "http://localhost:8080/o/api/p/content-space/20126",
            "name": "Livingstone Hotels & Resorts",
            "membershipType": "open",
            // ... 
            "folder": {
                "@id": "http://localhost:8080/o/api/p/root-folder/20126",
                "folders": "http://localhost:8080/o/api/p/root-folder/20126/folder",
                "mediaObjects": "http://localhost:8080/o/api/p/root-folder/20126/media-object",
                "@type": ["Folder"],
            // ... 
            },
        // ... 
        },
        // ... 
    }

The embedded parameter also works with collection resources to return the 
specified related resource for every item in the collection. The efficiency 
gains of this approach are significant, because it saves the API client from 
sending a different request for each collection element. For example, if you 
send a request to the `web-site` endpoint without specifying a site, and add 
`embedded=contentSpace`, the response includes detailed `contentSpace` 
information for every site: 

    curl -H "Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTpMaWZlcmF5C" http://localhost:8080/o/api/p/web-site/?embedded=contentSpace

## Related Resources

[Navigating to a Related Resource](/develop/tutorials/-/knowledge_base/7-1/navigating-to-a-related-resource)
