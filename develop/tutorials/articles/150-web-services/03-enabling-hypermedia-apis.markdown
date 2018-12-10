# Enabling Hypermedia REST APIs [](id=enabling-hypermedia-rest-apis)

Liferay's Hypermedia REST APIs are an implementation of 
[Apio Architect](https://github.com/liferay/com-liferay-apio-architect), 
which is pre-installed @product@. Apio Architect is a server-side library 
that facilitates the creation of Apio REST APIs. 

+$$$

**Note:** To use Liferay's Hypermedia REST APIs, you must be running Liferay CE 
Portal 7.1 GA3+, or Liferay DXP 7.1 Fix Pack 5+. 

$$$

Liferay's Hypermedia REST APIs are disabled by default. Follow these steps to 
enable them: 

1.  Create the config file 
    `com.liferay.apio.architect.internal.application.ApioApplication-default.config`. 
    Add this code to the file:

        auth2.scopechecker.type="none"
        auth.verifier.auth.verifier.BasicAuthHeaderAuthVerifier.urls.includes="*"
        auth.verifier.auth.verifier.OAuth2RestAuthVerifier.urls.includes="*"
        auth.verifier.guest.allowed="true"

    Note that the last property (`auth.verifier.guest.allowed`) lets guests 
    access public content via the APIs. To turn this off, set the property to 
    `false`. 

2.  Deploy the config file to `[Liferay Home]/osgi/configs`. Note that 
    [Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home) 
    is typically the application server's parent folder. 

3.  Test the APIs by making a request to their root URL: 

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

That's it! The Liferay Hypermedia REST APIs are now ready for use. 

## Related Topics [](id=related-topics)

[Consuming Web Services](/develop/tutorials/-/knowledge_base/7-1/consuming-web-services)
