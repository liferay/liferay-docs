# Enabling Hypermedia REST APIs [](id=enabling-hypermedia-rest-apis)

+$$$

**Note:** Hypermedia REST APIs are currently only available as a limited private 
alpha for Liferay DXP 7.1 Fix Pack 3+. 

$$$

Liferay's Hypermedia REST APIs are an implementation of 
[Apio Architect](https://github.com/liferay/com-liferay-apio-architect), 
which is pre-installed in Liferay DXP. Apio Architect is a server-side library 
that facilitates the creation of Apio REST APIs. 

To use Liferay's Hypermedia REST APIs, you must install them in your Liferay DXP 
instance. Follow these steps to do so: 

1.  Download the Hypermedia REST APIs ZIP file from the link you received via 
    your participation in the private alpha release. Unzip the file to a 
    temporary folder. 

2.  Copy all the unzipped JAR files to the `[Liferay Home]/deploy` folder. The 
    console should show that the modules are starting: 

        2018-08-28 12:54:16.635 INFO  [BundleStartStopLogger:35] STARTED com.liferay.portal.apio.impl_1.0.6 [978]
        2018-08-28 12:54:16.640 INFO  [BundleStartStopLogger:35] STARTED com.liferay.role.apio.api_1.0.1 [979]
        2018-08-28 12:54:16.669 INFO  [BundleStartStopLogger:35] STARTED com.liferay.site.apio.impl_1.0.5 [982]
        2018-08-28 12:54:16.719 INFO  [BundleStartStopLogger:35] STARTED com.liferay.workflow.apio.impl_1.0.5 [996]
        2018-08-28 12:54:16.754 INFO  [BundleStartStopLogger:35] STARTED com.liferay.web.page.element.apio.impl_1.0.5 [992]
        2018-08-28 12:54:16.757 INFO  [BundleStartStopLogger:35] STARTED com.liferay.keyword.apio.api_1.0.1 [965]
        2018-08-28 12:54:16.780 INFO  [BundleStartStopLogger:35] STARTED com.liferay.layout.apio.impl_1.0.3 [968]
        2018-08-28 12:54:16.784 INFO  [BundleStartStopLogger:35] STARTED com.liferay.category.apio.api_1.0.1 [953]
        2018-08-28 12:54:16.786 INFO  [BundleStartStopLogger:35] STARTED com.liferay.workflow.apio.api_1.1.3 [995]
        2018-08-28 12:54:16.927 INFO  [BundleStartStopLogger:35] STARTED com.liferay.category.apio.impl_1.0.9 [954]
        2018-08-28 12:54:16.994 INFO  [BundleStartStopLogger:35] STARTED com.liferay.folder.apio.impl_1.0.4 [962]
        2018-08-28 12:54:16.997 INFO  [BundleStartStopLogger:35] STARTED com.liferay.vocabulary.apio.api_1.0.1 [989]
        2018-08-28 12:54:17.006 INFO  [BundleStartStopLogger:35] STARTED com.liferay.forms.apio.api_1.1.0 [963]
        2018-08-28 12:54:17.009 INFO  [BundleStartStopLogger:35] STARTED com.liferay.organization.apio.api_1.0.1 [971]
        2018-08-28 12:54:17.047 INFO  [BundleStartStopLogger:35] STARTED com.liferay.phone.apio.impl_1.0.1 [976]
        2018-08-28 12:54:17.049 INFO  [BundleStartStopLogger:35] STARTED com.liferay.web.page.element.apio.api_1.0.1 [991]
        2018-08-28 12:54:17.101 INFO  [BundleStartStopLogger:35] STARTED com.liferay.media.object.apio.impl_1.0.9 [970]
        [...]

3.  Once the OSGi container loads the configuration, make a request to the API's 
    root URL: 

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
