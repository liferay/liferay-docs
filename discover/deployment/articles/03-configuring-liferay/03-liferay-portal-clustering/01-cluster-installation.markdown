# Building Clustering for @product@ [](id=building-clustering)

In order to use RMI-based clustering with @product@, a series of modules will need to be 
compiled from source and several modules will need to be blacklisted to prevent them from
starting up with the platform.

## Build Liferay Portal from source

These instructions assume building against master but will also work with the latest branch version.

1.  Clone @product@ repo on Github:

        git clone https://github.com/liferay/liferay-portal —branch master —single-branch --depth 1

2.  Build master from source:

        cd liferay-portal
        ant all

3.  Remove the following jars from `../bundles/osgi/portal`:

        rm -f ../bundles/osgi/portal/com.liferay.portal.cache.single.jar
        rm -f ../bundles/osgi/portal/com.liferay.portal.cluster.single.jar
        rm -f ../bundles/osgi/portal/com.liferay.portal.scheduler.single.jar 

## Build and Deploy Clustering Modules
Build and deploy the following modules to `../bundles/osgi/portal`:

        cd modules
        ../gradlew :apps:foundation:portal:portal-cluster-multiple:deploy
        ../gradlew :apps:foundation:portal-cache:portal-cache-ehcache-multiple:deploy
        ../gradlew :apps:foundation:portal-scheduler:portal-scheduler-multiple:deploy

## Enable Clustering
Add the following to portal-ext.properties:

        cluster.link.enabled=true


If all goes well the following should appear in the bundle logs when starting Liferay Portal:

        ------------------------------------------------------------------- 
        GMS: address=oz-52865, cluster=liferay-channel-control, physical address=192.168.1.10:50643 
        -------------------------------------------------------------------