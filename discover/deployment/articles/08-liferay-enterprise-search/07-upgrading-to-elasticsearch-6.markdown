# Upgrading to Elasticsearch 6

Elasticsearch 6 is supported for Digital Enterprise subscribers running Fix Pack
XX or later, and for Community Edition users running 7.0 CE GA 6 or greater. If
you're just getting started, follow the [installation guide](/discover/deployment/-/knowledge_base/7-0/installing-elasticsearch) to install
Elasticsearch 6 and the [configuration guide](/discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch-for-liferay-0) to configure the Elasticsearch
Adapter. In this article, learn to upgrade an existing Elasticsearch 2.4.x
server (or cluster) to Elasticsearch 6.1.x. The general steps are as follows:

1.  Install and configure Elasticsearch 6.1.x.
2.  [Upgrade the Elasticsearch 2.4 indexes](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-upgrade.html) to 6.1.
3.  Download the Elasticsearch 6 Adapter from [Liferay Marketplace](LINK WHEN
    RELEASED)
4.  Stop the default Elasticsearch Adapter.
5.  Stop the Elasticsearch 2.4 server.
6.  Install and configure the Elasticsearch 6 Adapter.
7.  Start Elasticsearch 6.
8.  Reindex all search indexes.

+$$$

**Before Proceeding:** Back up your existing data before upgrading
Elasticsearch. If something goes wrong during or after the upgrade, roll
back to 2.4 using the uncorrupted index snapshots. See
[here](https://dev.liferay.com/discover/deployment/-/knowledge_base/7-0/backing-up-elasticsearch)
for more information.

$$$

## Installing Elasticsearch 6.1

Download [Elasticsearch 6.1.x](https://www.elastic.co/downloads/past-releases) and unzip it wherever you please.

Name the cluster whatever you please by configuring the `cluster.name` property
in `elasticsearch.yml`:

    cluster.name: LiferayElasticsearchCluster

Install the following required Elasticsearch plugins:

-  `analysis-icu`
-  `analysis-kuromoji`
-  `analysis-smartcn`
-  `analysis-stempel`

Install these plugins by navigating to Elasticsearch Home and entering

    ./bin/elasticsearch-plugin install [plugin-name]

Replace *[plugin-name]* with the Elasticsearch plugin's name.

## Upgrade the Elasticsearch 2.4 Indexes

This is likely the trickiest part of the upgrade process. Fortunately.  [Elastic
extensively documents the
process](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-upgrade.html).
Consider [upgrading from a remote
cluster](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/reindex-upgrade-remote.html)
as that will allow you to upgrade indexes without interrupting service. Once you
have upgraded indexes ready to use with @product@ and your new Elasticsearch 6.1
server, come back here and continue with this guide.

## Download the Elasticsearch 6 Adapter

Download the [Elasticsearch 6 Adapter from Liferay Marketplace](LINK WHEN
RELEASED). You'll be left with a LPKG file. 

## Stop the Elasticsearch Adapter and Elasticsearch 2.x

Before installing the Elasticsearch 6 Adapter, you must stop the running
Elasticsearch Adapter that ships with @product@. Use the App Manager: 

- Navigate to
Control Panel &rarr; Apps &rarr; App Manager.
- Search for *elasticsearch*. Find the Liferay Portal Search Elasticsearch
    module and click the edit ((![Edit](../../images/icon-edit.png))) button.
    Choose the Deactivate option. This leaves the bundle installed, but stops
    it in the OSGi runtime.

Then stop Elasticsearch 2.x.

## Install and Configure the Elasticsearch 6 Adapter

Once the default adapter is stopped, install the Elasticsearch 6 Adapter (the
LPKG you donwloaded) by placing it in your Liferay Home folder's `deploy`
directory.
See[here](https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/installing-apps-manually#using-your-file-system-to-install-apps)
for more information.

It starts automatically with log messages like this:

<!-- Add when possible -->

Now configure the adapter to talk to find your Elasticsearch 6.1.x cluster by
specifying the correct *Cluster Name* and Setting *Operation Mode* to *REMOTE*.
Make sure the *Transport Address* matches the one Elasticsearch is using. If
testing locally with Elasticsearch's default settings, the default value in the
adapter should work fine (*localhost:9300*).

## Start Elasticsearch 6 and Reindex

Once the Elasticsearch adapter is looking for the cluster, start Elasticsearch
by running 

    ./bin/elasticsearch 

from Elasticsearch Home. Then navigate to Control Panel &rarr; Configuration
&rarr; Server Administration, and click *Execute* for the *Reindex all search
indexes* entry.


