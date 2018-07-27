#Enabling Hypermedia REST APIs

Liferay Hypermedia REST APIs are not automatically enabled in Liferay Portal CE 7.1 GA1 (it will be enabled from version 7.1sp1 for EE users, and from version 7.1GA2).

But it is possible to update and enable the APIs in Liferay Portal CE 7.1 GA1 through the following steps:

## 1. Remove the pre-installed APIO Architect API & implementation.

The first thing we need to do is remove the default version of APIO from @product@. In order to do that we can open a console and remove both the APIO Architect API and Impl from the list of pre-installed packages.

Navigate to the directory where all the packages are pre-installed:

    $ cd $LIFERAY_HOME/osgi/marketplace

Once there, list and remove the APIO Architect API and Impl packages:

    $ ls *Apio*
    Liferay CE Foundation - Liferay CE Apio Architect - API.lpkg 
    Liferay CE Foundation - Liferay CE Apio Architect - Impl.lpkg
    $ rm *Apio*

If the Liferay Portal instance is running, on the console, you should see that the packages have been stopped:

    2018-07-16 12:42:22.186 INFO  [fileinstall-$LIFERAY_HOME/osgi/marketplace][BundleStartStopLogger:38] STOPPED Liferay CE Foundation - Liferay CE Apio Architect - API_1.0.0 [387]
    2018-07-16 12:42:22.199 INFO  [fileinstall-$LIFERAY_HOME/osgi/marketplace][BundleStartStopLogger:38] STOPPED com.liferay.apio.architect.api_1.0.2 [388]
    2018-07-16 12:42:22.216 INFO  [Refresh Thread: Equinox Container: c0a2f090-f388-0018-1c45-fc3bc84c1049][BundleStartStopLogger:38] STOPPED com.liferay.apio.architect.uri.mapper.impl_1.0.0 [715]
    2018-07-16 12:42:22.323 INFO  [Refresh Thread: Equinox Container: c0a2f090-f388-0018-1c45-fc3bc84c1049][BundleStartStopLogger:38] STOPPED com.liferay.apio.architect.impl_1.0.1 [714]
    2018-07-16 12:42:22.335 INFO  [Refresh Thread: Equinox Container: c0a2f090-f388-0018-1c45-fc3bc84c1049][BundleStartStopLogger:38] STOPPED com.liferay.apio.architect.exception.mapper.impl_1.0.1 [713]
    2018-07-16 12:42:22.347 INFO  [fileinstall-$LIFERAY_HOME/osgi/marketplace][BundleStartStopLogger:38] STOPPED Liferay CE Foundation - Liferay CE Apio Architect - Impl_1.0.0 [712]

Once that the module is stopped, the last thing to do is remove the configuration file for that APIO version:

    $ rm $LIFERAY_HOME/osgi/configs/*apio*

## 2. Install latest version of APIO Architect

Before installing the new version of APIO Architect, we should download the jar files that contains the updated version of OSGI modules that support APIO. You should create a temporary folder to download the packages:

    $ mkdir -p $LIFERAY_HOME/../packages/apio
    $ cd $LIFERAY_HOME/../packages/apio

To download the updated version of APIO Architect simply execute the following command (be sure that all is written in the same line):

    $ wget http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.impl/1.0.6/com.liferay.apio.architect.impl-1.0.6.jar  http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.api/1.3.0/com.liferay.apio.architect.api-1.3.0.jar http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.uri.mapper.impl/1.0.1/com.liferay.apio.architect.uri.mapper.impl-1.0.1.jar http://central.maven.org/maven2/com/liferay/com.liferay.apio.architect.exception.mapper.impl/1.0.3/com.liferay.apio.architect.exception.mapper.impl-1.0.3.jar

Once all the artifacts are downloaded, simply deploy them by moving to the deploy folder inside @product@ installation:

    $ mv *apio.architect*.jar $LIFERAY_HOME/deploy

The @product@ logs should show that the modules are starting:

    2018-07-16 13:01:26.477 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.liferay.apio.architect.api-1.3.0.jar
    2018-07-16 13:01:26.483 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.liferay.apio.architect.impl-1.0.6.jar
    2018-07-16 13:01:26.484 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.liferay.apio.architect.exception.mapper.impl-1.0.3.jar
    2018-07-16 13:01:26.484 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:261] Processing com.liferay.apio.architect.uri.mapper.impl-1.0.1.jar
    2018-07-16 13:01:31.818 INFO  [fileinstall-$LIFERAY_HOME/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.apio.architect.exception.mapper.impl_1.0.3 [948]
    2018-07-16 13:01:31.898 INFO  [fileinstall-$LIFERAY_HOME/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.apio.architect.impl_1.0.6 [949]
    2018-07-16 13:01:32.831 INFO  [fileinstall-$LIFERAY_HOME/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.apio.architect.api_1.3.0 [947]
    2018-07-16 13:01:32.839 INFO  [fileinstall-$LIFERAY_HOME/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.apio.architect.uri.mapper.impl_1.0.1 [950]

And the modules should appear on the OSGi modules folder:

    $ ls $LIFERAY_HOME/osgi/modules
    com.liferay.apio.architect.api-1.3.0.jar 
    com.liferay.apio.architect.impl-1.0.6.jar
    com.liferay.apio.architect.exception.mapper.impl-1.0.3.jar com.liferay.apio.architect.uri.mapper.impl-1.0.1.jar

## 3. Enable access to the APIs

By default, the access to the APIs is restricted by security. In order to enable the access, a specific configuration should be added for the modules. That configuration is already available on the default config file from the github repository. You can simply download and move it to the OSGi config folder:

    $ mkdir -p $LIFERAY_HOME/../packages/apio/config
    $ cd $LIFERAY_HOME/../packages/apio/config
    $ wget https://raw.githubusercontent.com/liferay/com-liferay-apio-architect/e25b53280939dd1f5bc4eab889c9ad7d512314cb/apio-architect-impl/configs/com.liferay.apio.architect.impl.application.ApioApplication-default.config

    $ mv com.liferay.apio.architect.impl.application.ApioApplication-default.config $LIFERAY_HOME/osgi/configs

After a while, once the config is reloaded by the OSGi container, you can make a request to the API root URL:

    $ curl http://localhost:8080/o/api

And you will see the empty root response for the root API endpoint:

    {  
    "@id":"http://localhost:8080/o/api",
    "@type":"EntryPoint",
    "@context":[  
        {  
            "@vocab":"http://schema.org/"
        },
        "https://www.w3.org/ns/hydra/core#"
        ]
    }

That means that the APIO module is running and ready to provide access to all the API endpoints.

