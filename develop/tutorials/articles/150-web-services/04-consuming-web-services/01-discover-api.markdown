# Get Started: Discover the API [](id=get-started-discover-the-api)

To begin consuming services you must first know where they are (e.g., a service 
catalog), what operations you can invoke, and how to invoke them. With 
Hypermedia REST APIs in @product@, you don't need a service catalog. Because the 
APIs leverage hypermedia, you only need a home URL from which to discover the 
rest of the API. In @product@, this is `http://[host]:[port]/o/api`. For 
example, if you're running your portal locally on port `8080`, then the home URL 
for discovering the portal's hypermedia APIs is `http://localhost:8080/o/api`. 
To access this URL, you can use a browser, other tools like 
[Postman](https://www.getpostman.com/) or 
[Advanced REST Client](https://install.advancedrestclient.com/#/install), 
or even the `curl` command in your system console. For simplicity, the examples 
in this documentation use the `curl` command. 

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
point) of the web APIs catalog. It specifies three root resources and their 
accompanying links: `person`, `content-space`, `web-site`, and `organization`. 

You can traverse the API's resource URLs much like you would the links on a web 
site's home page. For example, if you're interested in the person resource, send 
a GET request to `http://localhost:8080/o/api/p/person`. You can send such GET 
requests to any of the listed resource URLs. 

As you can see, accessing the home URL lets you know the root resources handled 
by the API and includes URLs to access them. You don't need to remember specific 
URLs other than the home URL. This discoverability is a key advantage of 
@product@'s web APIs. 

## Related Topics

[Get Started: Invoke a Service](/develop/tutorials/-/knowledge_base/7-1/get-started-invoke-a-service)

[Navigating Between Related Resources](/develop/tutorials/-/knowledge_base/7-1/navigating-between-related-resources)
