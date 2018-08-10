# Building Clustering for Liferay Portal [](id=building-clustering-for-liferay)

+$$$

**Note:** These instructions are for adding the clustering modules to **Liferay
Portal**. Skip them if you're running Liferay DXP, as clustering is built-in out
of the box. 

$$$

RMI-based clustering with Liferay Portal requires building and deploying
clustering modules and blacklisting the single node modules. 

## Build Liferay Portal from source [](id=build-liferay-portal-from-source)

These instructions assume building against the latest tagged release but also
work with any branch.

1.  Clone the Liferay Portal GitHub repository for the branch or tag you 
    want. For example, this command clones the `7.1.0-ga1` tag:

        git clone https://github.com/liferay/liferay-portal --branch 7.1.0-ga1 --single-branch --depth 1

2.  Build from source:

        cd liferay-portal
        ant all

A Liferay Portal bundle is built to the `../bundles/` folder. 

## Blacklist Single Node Modules [](id=blacklist-single-node-modules)

Prevent the single node modules from deploying by
[blacklisting](/discover/portal/-/knowledge_base/7-1/blacklisting-osgi-modules-and-components)
them or removing them from your Liferay Portal installation's `/osgi/portal`
folder.

For example, here are the commands for removing them from the `../bundles/`
folder:

    rm -f ../bundles/osgi/portal/com.liferay.portal.cache.single.jar
    rm -f ../bundles/osgi/portal/com.liferay.portal.cluster.single.jar
    rm -f ../bundles/osgi/portal/com.liferay.portal.scheduler.single.jar 

## Build and Deploy the Clustering Modules [](id=build-and-deploy-clustering-modules)

Now build and deploy the clustering modules:

1.  Build and deploy the clustering modules to the `../bundles/osgi/portal` 
    folder:

        cd modules
        ../gradlew :apps:portal:portal-cluster-multiple:deploy
        ../gradlew :apps:portal-cache:portal-cache-ehcache-multiple:deploy
        ../gradlew :apps:portal-scheduler:portal-scheduler-multiple:deploy

2.  Copy the clustering modules (listed below) from `../bundles/osgi/portal` 
    to each node's `[Liferay Home]/osgi/portal` folder:

        com.liferay.portal.cache.ehcache.multiple.jar 
        com.liferay.portal.cluster.multiple.jar 
        com.liferay.portal.scheduler.multiple.jar

Your cluster nodes have the modules needed for running in a cluster. 

+$$$

**Important:** Make sure to complete the steps for 
[configuring clustering](/discover/portal/-/knowledge_base/7-1/liferay-clustering).

$$$

## Related Topics [](id=related-topics)

[@product@ Clustering](/discover/portal/-/knowledge_base/7-1/liferay-clustering)

[Blacklisting Modules](/discover/portal/-/knowledge_base/7-1/blacklisting-osgi-modules-and-components)
