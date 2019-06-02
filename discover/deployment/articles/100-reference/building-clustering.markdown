---
header-id: building-clustering-for-liferay
---

# Building Clustering for Liferay Portal

[TOC levels=1-4]

| **Note:** These instructions are for adding the clustering modules to **Liferay
| Portal CE**. Skip them if you're running Liferay DXP, as it has clustering
| available out-of-the-box.

RMI-based clustering with Liferay Portal requires building and deploying
clustering modules and blacklisting the single node modules. 

## Build Liferay Portal from source

These instructions assume building against the latest tagged release but also
work with any branch.

1.  Clone the Liferay Portal GitHub repository for the branch or tag you 
    want. For example, this command clones the `7.0.x` branch:

        git clone https://github.com/liferay/liferay-portal --branch 7.0.x --single-branch --depth 1

2.  Build from source:

        cd liferay-portal
        ant all

A Liferay Portal bundle is built to the `../bundles/` folder. 

## Blacklist Single Node Modules

Prevent the single node modules from deploying by
[blacklisting](/docs/7-0/user/-/knowledge_base/u/blacklisting-osgi-modules)
them or removing them from your Liferay Portal installation's `/osgi/portal`
folder.

For example, here are the commands for removing them from the `../bundles/`
folder:

    rm -f ../bundles/osgi/portal/com.liferay.portal.cache.single.jar
    rm -f ../bundles/osgi/portal/com.liferay.portal.cluster.single.jar
    rm -f ../bundles/osgi/portal/com.liferay.portal.scheduler.single.jar 

## Build and Deploy the Clustering Modules

Now build and deploy the clustering modules:

1.  Build and deploy the clustering modules to the `../bundles/osgi/portal` 
    folder:

        cd modules
        ../gradlew :apps:foundation:portal:portal-cluster-multiple:deploy
        ../gradlew :apps:foundation:portal-cache:portal-cache-ehcache-multiple:deploy
        ../gradlew :apps:foundation:portal-scheduler:portal-scheduler-multiple:deploy

2.  Copy the clustering modules (listed below) from `../bundles/osgi/portal` 
    to each node's `[Liferay Home]/osgi/portal` folder:

        com.liferay.portal.cache.ehcache.multiple.jar 
        com.liferay.portal.cluster.multiple.jar 
        com.liferay.portal.scheduler.multiple.jar

Your cluster nodes have the modules needed for running in a cluster. 

| **Important:** Make sure to complete the steps for
| [configuring clustering](/docs/7-0/deploy/-/knowledge_base/d/liferay-clustering).

## Related Topics

[@product@ Clustering](/docs/7-0/deploy/-/knowledge_base/d/liferay-clustering)

[Blacklisting Modules](/docs/7-0/user/-/knowledge_base/u/blacklisting-osgi-modules)
