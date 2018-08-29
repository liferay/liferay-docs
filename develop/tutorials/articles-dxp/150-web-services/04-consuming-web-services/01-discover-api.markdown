# Get Started: Discover the API [](id=get-started-discover-the-api)

+$$$

**Note:** Hypermedia REST APIs are currently only available as a limited private 
alpha. 

$$$

To begin consuming web services, you must first know where they are (e.g.,
a service catalog), what operations you can invoke, and how to invoke them.
Because hypermedia REST APIs leverage
[HATEOAS](https://en.wikipedia.org/wiki/HATEOAS), you don't need a service
catalog. You only need a home URL from which to discover the rest of the API. 

Here's @product@'s home URL: 

    http://[host]:[port]/o/api. 

For example, if you're running @product@ locally on port `8080`, the home
URL for discovering the hypermedia APIs is `http://localhost:8080/o/api`. To
access this URL, you can use a browser or
other tools like [Postman](https://www.getpostman.com), [Advanced REST
Client](https://install.advancedrestclient.com/#/install), or even the `curl`
command in your system console. For simplicity, the examples in this
documentation use the `curl` command and send requests to a @product@ instance
running locally on port `8080`. 

Run this `curl` command to access the home URL: 

    curl http://localhost:8080/o/api

You should get a response like this: 

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

This response follows the JSON-LD/Hydra syntax to specify the root (or entry
point) of the web API. It specifies four root resources and their accompanying
links: `person`, `content-space`, `web-site`, and `organization`. You can
traverse the resource URLs much like the links on a web site's home page. For
example, if you want the `person` resource, send a GET request to
`http://localhost:8080/o/api/p/person`. You can send such requests to any of the
listed resource URLs. 

The home URL shows the API's root resources and includes URLs to access them.
You don't need to remember specific URLs other than the home URL. This dynamic
discovery is a key advantage of hypermedia REST APIs. 

## Related Topics [](id=related-topics)

[Get Started: Invoke a Service](/develop/tutorials/-/knowledge_base/7-1/get-started-invoke-a-service)

[Navigating Between Related Resources](/develop/tutorials/-/knowledge_base/7-1/navigating-between-related-resources)
