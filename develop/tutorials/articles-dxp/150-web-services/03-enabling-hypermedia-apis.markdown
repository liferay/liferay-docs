# Enabling Hypermedia REST APIs [](id=enabling-hypermedia-rest-apis)

+$$$

**Note:** Hypermedia REST APIs are currently only available as a limited private 
alpha. 

$$$

Liferay's Hypermedia REST APIs are an implementation of 
[Apio Architect](https://github.com/liferay/com-liferay-apio-architect). 
Apio Architect is a server-side library that facilitates the creation of Apio 
REST APIs. Although Apio Architect is pre-installed in Liferay CE Portal 7.1 and 
Liferay DXP 7.1, you may need to update it before installing Hypermedia REST 
APIs. 

To use Hypermedia REST APIs in Liferay CE Portal 7.1 GA1 or versions of Liferay 
DXP 7.1 prior to Fix Pack 1, you must follow all these steps. To use the APIs in 
Liferay CE Portal GA2+ or Liferay DXP 7.1 Fix Pack 1+, you only need to follow 
the last step: 

1.  Remove the pre-installed Apio Architect modules.
2.  Install the latest version of Apio Architect.
3.  Install the Hypermedia REST APIs. 

The following sections walk you through these steps. 

## Remove the Pre-installed Apio Architect Modules [](id=remove-the-pre-installed-apio-architect-modules)

Follow these steps to remove the pre-installed Apio Architect modules: 

1.  Navigate to `[Liferay Home]/osgi/marketplace`. The 
    [Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home) 
    folder is typically the application server's parent folder. 

2.  Delete the Apio packages. For example, here are the Apio packages for 
    Liferay Portal: 

        Liferay CE Foundation - Liferay CE APIO Architect - API.lpkg 
        Liferay CE Foundation - Liferay CE APIO Architect - Impl.lpkg

    If the portal is running, you should see in the console that the modules in 
    those packages have been stopped: 

        2018-07-16 12:42:22.186 INFO  [fileinstall-$LIFERAY_HOME/osgi/marketplace][BundleStartStopLogger:38] STOPPED Liferay CE Foundation - Liferay CE Apio Architect - API_1.0.0 [387]
        2018-07-16 12:42:22.199 INFO  [fileinstall-$LIFERAY_HOME/osgi/marketplace][BundleStartStopLogger:38] STOPPED com.liferay.apio.architect.api_1.0.2 [388]
        2018-07-16 12:42:22.216 INFO  [Refresh Thread: Equinox Container: c0a2f090-f388-0018-1c45-fc3bc84c1049][BundleStartStopLogger:38] STOPPED com.liferay.apio.architect.uri.mapper.impl_1.0.0 [715]
        2018-07-16 12:42:22.323 INFO  [Refresh Thread: Equinox Container: c0a2f090-f388-0018-1c45-fc3bc84c1049][BundleStartStopLogger:38] STOPPED com.liferay.apio.architect.impl_1.0.1 [714]
        2018-07-16 12:42:22.335 INFO  [Refresh Thread: Equinox Container: c0a2f090-f388-0018-1c45-fc3bc84c1049][BundleStartStopLogger:38] STOPPED com.liferay.apio.architect.exception.mapper.impl_1.0.1 [713]
        2018-07-16 12:42:22.347 INFO  [fileinstall-$LIFERAY_HOME/osgi/marketplace][BundleStartStopLogger:38] STOPPED Liferay CE Foundation - Liferay CE Apio Architect - Impl_1.0.0 [712]

3.  Use the 
    [Felix Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell) 
    to verify that the bundles have been removed. To do so, run this command in 
    the Gogo shell: 

        lb apio

    If this returns results for the `*apio*` bundles, then you must delete them
    via the Gogo shell. Take note of each bundle's ID and run `uninstall
    [BUNDLE_ID]` for each. For example, if the bundle IDs were 388, 713, 714,
    and 715, then you would run these commands in the Gogo shell: 

        uninstall 388
        uninstall 713
        uninstall 714
        uninstall 715

4.  Finally, remove the `*apio*` configuration file in 
    `[Liferay Home]/osgi/configs`. 

## Install the Latest Version of Apio Architect [](id=install-the-latest-version-of-apio-architect)

Now you must download and install the latest version of the Apio Architect 
modules: 

1.  Download the modules by clicking the link for each: 

    -   [`com.liferay.apio.architect.api-1.5.0.jar`](http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.api/1.5.0/com.liferay.apio.architect.api-1.5.0.jar)
    -   [`com.liferay.apio.architect.impl-1.0.9.jar`](http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.impl/1.0.9/com.liferay.apio.architect.impl-1.0.9.jar)
    -   [`com.liferay.apio.architect.uri.mapper.impl-1.0.1.jar`](http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.uri.mapper.impl/1.0.1/com.liferay.apio.architect.uri.mapper.impl-1.0.1.jar)
    -   [`com.liferay.apio.architect.exception.mapper.impl-1.0.3.jar`](http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.exception.mapper.impl/1.0.3/com.liferay.apio.architect.exception.mapper.impl-1.0.3.jar) 

2.  Deploy these files to the `[Liferay Home]/deploy` folder. The console should 
    show that the modules are starting: 

        2018-07-16 13:01:26.477 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.liferay.apio.architect.api-1.5.0.jar
        2018-07-16 13:01:26.483 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.liferay.apio.architect.impl-1.0.9.jar
        2018-07-16 13:01:26.484 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.liferay.apio.architect.exception.mapper.impl-1.0.3.jar
        2018-07-16 13:01:26.484 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.liferay.apio.architect.uri.mapper.impl-1.0.1.jar
        2018-07-16 13:01:31.818 INFO  [fileinstall-$LIFERAY_HOME/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.apio.architect.exception.mapper.impl_1.0.3 [948]
        2018-07-16 13:01:31.898 INFO  [fileinstall-$LIFERAY_HOME/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.apio.architect.impl_1.0.9 [949]
        2018-07-16 13:01:32.831 INFO  [fileinstall-$LIFERAY_HOME/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.apio.architect.api_1.5.0 [947]
        2018-07-16 13:01:32.839 INFO  [fileinstall-$LIFERAY_HOME/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.apio.architect.uri.mapper.impl_1.0.1 [950]

    The modules should then appear in `[Liferay Home]/osgi/modules`. 

## Install the API Implementation [](id=install-the-api-implementation)

Now you're ready to install the Hypermedia REST APIs. Follow these steps to do 
so: 

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

3.  By default, Apio security restricts access to the APIs. To enable access, 
    you must deploy a specific configuration file. This file is included in the 
    ZIP file you downloaded. Locate the files you unzipped in the first step 
    and copy the file 
    `com.liferay.apio.architect.impl.application.ApioApplication-default.config` 
    to `[Liferay Home]/osgi/configs`. 

4.  Once the OSGi container loads the configuration, make a request to the API's 
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

    If you instead receive a permissions error, then the configuration didn't 
    load. In this case, try restarting the portal. 

## Related Topics [](id=related-topics)

[Consuming Web Services](/develop/tutorials/-/knowledge_base/7-1/consuming-web-services)
