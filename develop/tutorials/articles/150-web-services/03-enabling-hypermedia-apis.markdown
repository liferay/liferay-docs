# Enabling Hypermedia REST APIs [](id=enabling-hypermedia-rest-apis)

Liferay's Hypermedia REST APIs are an implementation of 
[Apio Architect](https://github.com/liferay/com-liferay-apio-architect), 
which is pre-installed @product@. Apio Architect is a server-side library 
that facilitates the creation of Apio REST APIs. 

+$$$

**Note:** To install Liferay's Hypermedia REST APIs, you must be running 
Liferay CE Portal 7.1 GA2+, or Liferay DXP 7.1 Fix Pack 3+.

$$$

To use Liferay's Hypermedia REST APIs, you must install them in your @product@ 
instance. Follow these steps to do so: 

1.  Download and install the Hypermedia REST APIs app from Liferay Marketplace. 
    Be sure to install the app that matches your Liferay product (CE or DXP): 

    -   [Liferay Hypermedia REST APIs - CE](https://web.liferay.com/marketplace/-/mp/application/116742743)
    -   [Liferay Hypermedia REST APIs - DXP](https://web.liferay.com/marketplace/-/mp/application/116775839)

    If you need help installing the app from Marketplace, see the 
    [documentation on using Marketplace](/discover/portal/-/knowledge_base/7-1/using-the-liferay-marketplace). 

2.  After installing the app, test the installation by making a request to the 
    API's root URL: 

        curl http://localhost:8080/o/api

    You should get a response with the root resources available in the API: 

        {
        "@id" : "http://localhost:8080/o/api",
        "collection" : [ {
            "@id" : "http://localhost:8080/o/api/p/person",
            "manages" : {
            "property" : "rdf:type",
            "object" : "schema:Liferay:UserAccount"
            },
            "@type" : [ "Collection" ]
        }, {
            "@id" : "http://localhost:8080/o/api/p/content-space",
            "manages" : {
            "property" : "rdf:type",
            "object" : "schema:ContentSpace"
            },
            "@type" : [ "Collection" ]
        }, {
            "@id" : "http://localhost:8080/o/api/p/web-site",
            "manages" : {
            "property" : "rdf:type",
            "object" : "schema:WebSite"
            },
            "@type" : [ "Collection" ]
        }, {
            "@id" : "http://localhost:8080/o/api/p/organization",
            "manages" : {
            "property" : "rdf:type",
            "object" : "schema:Organization"
            },
            "@type" : [ "Collection" ]
        }, {
            "@id" : "http://localhost:8080/o/api/p/roles",
            "manages" : {
            "property" : "rdf:type",
            "object" : "schema:Role"
            },
            "@type" : [ "Collection" ]
        } ],
        "@type" : "EntryPoint",
        "@context" : [ {
            "@vocab" : "http://schema.org/"
        }, "https://www.w3.org/ns/hydra/core#" ]
        }

That's it! The Liferay Hypermedia REST APIs are now installed. 

## Related Topics [](id=related-topics)

[Consuming Web Services](/develop/tutorials/-/knowledge_base/7-1/consuming-web-services)
