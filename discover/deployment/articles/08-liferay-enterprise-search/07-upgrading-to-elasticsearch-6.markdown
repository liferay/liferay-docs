# Upgrading to Elasticsearch 6 [](id=upgrading-to-elasticsearch-6)

Elasticsearch 6 is supported for Digital Enterprise subscribers running Fix Pack
42 or later and for Community Edition users running 7.0 CE GA 7 or greater. If
you're not already running a remote Elasticsearch 2.x server, follow the 
[installation guide](/discover/deployment/-/knowledge_base/7-0/installing-elasticsearch) 
to install Elasticsearch 6 and the 
[configuration guide](/discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch-for-liferay-0) 
to configure the Elasticsearch adapter. Here, you'll learn to upgrade an
existing Elasticsearch 2.x server (or cluster) to Elasticsearch 6.1.x: 

1.  Install and configure Elasticsearch 6.1.x.

3.  Download the Elasticsearch 6 adapter from 
    [Liferay Marketplace](https://web.liferay.com/marketplace).

4.  Stop the default Elasticsearch adapter.

5.  Stop the Elasticsearch 2.4 server.

6.  Start Elasticsearch 6.

7.  Install and configure the Elasticsearch 6 adapter.

8.  Re-index all search  and spell check indexes.

**Before Proceeding:** Back up your existing data before upgrading
Elasticsearch. If something goes wrong during or after the upgrade, roll
back to 2.x using the uncorrupted index snapshots. See
[here](/discover/deployment/-/knowledge_base/7-0/backing-up-elasticsearch)
for more information.

+$$$

**Key Changes:** This list compiles known changes within Elasticsearch that are
likely to affect @product@ users. For a more complete reckoning of what's
changed in Elasticsearch, see the 
[breaking changes for Elasticsearch 6](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/breaking-changes.html),
and 
[Elasticsearch 5](https://www.elastic.co/guide/en/elasticsearch/reference/5.0/breaking-changes-5.0.html),
since the breaking changes from 2.x to 5.x also apply to 2.x to 6.1.

*Custom Mappings*

**Aggregations:** Aggregating on analyzed `String` fields was possible in
Elasticsearch 2.x, but aggregating on analyzed `text` fields is not advised
in Elasticsearch 6.1. Instead, use a `keyword` field or add 
[field data](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/fielddata.html).
Beware; `fielddata` is disabled by default because it is memory-intensive.

*Spell Check and Query Suggestions*

Due to Elastic's 
[removal of mapping types](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/removal-of-types.html)
from search documents, spell check indexes and suggestion dictionaries now
use a single document type. If your installation was leveraging either
functionality, you must re-index all spell check indexes (from Control Panel
&rarr; Configuration &rarr; Server Administration).

$$$

## Installing Elasticsearch 6.1 [](id=installing-elasticsearch-6-1)

1.  Download 
    [Elasticsearch 6.1.x](https://www.elastic.co/downloads/past-releases)
    and unzip it wherever you please.

2.  Name the cluster by configuring the `cluster.name` property in
    `elasticsearch.yml`:

        cluster.name: LiferayElasticsearchCluster

3.  Install the following required Elasticsearch plugins:

    -  `analysis-icu`
    -  `analysis-kuromoji`
    -  `analysis-smartcn`
    -  `analysis-stempel`

4.  To install these plugins, navigate to Elasticsearch Home and enter

        ./bin/elasticsearch-plugin install [plugin-name]

5.  Replace *[plugin-name]* with the Elasticsearch plugin's name.

6.  Once installed, start Elasticsearch 6 by running

        ./bin/elasticsearch

    from Elasticsearch Home.

## Download the Elasticsearch 6 Adapter [](id=download-the-elasticsearch-6-adapter)

Download the 
[Elasticsearch 6 adapter LPKG from Liferay Marketplace](https://web.liferay.com/marketplace).

## Stop the Elasticsearch Adapter and Elasticsearch 2.x [](id=stop-the-elasticsearch-adapter-and-elasticsearch-2-x)

Before installing the Elasticsearch 6 adapter, you must stop the running
Elasticsearch adapter that ships with @product@. 
 
[Blacklist](/discover/portal/-/knowledge_base/7-0/blacklisting-osgi-modules) 
the Elasticsearch, Shield, and Marvel plugins. Create a 

    com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config

file with these contents:

    blacklistBundleSymbolicNames=["com.liferay.portal.search.elasticsearch","com.liferay.portal.search.elasticsearch.shield","com.liferay.portal.search.elasticsearch.marvel.web"]

Place the file in `Liferay Home/osgi/configs`.

+$$$

**Note:** If you're a Digital Enterprise customer, use the blacklist feature
described above. The App Manager relies on the `osgi/state` folder to "remember"
the state of the bundle. If you delete this folder (recommended during patching)
the Elasticsearch connector will be reinstalled and started automatically.

$$$

Alternatively, use the App Manager.

1.  Navigate to Control Panel &rarr; Apps &rarr; App Manager.

2.  Search for *elasticsearch*. Find the Liferay Portal Search Elasticsearch
    module and click the *edit* ((![Edit](../../images/icon-edit.png))) button.
    Choose the *Deactivate* option. This leaves the bundle installed, but stops
    it in the OSGi runtime.

3.  If you're using the Shield and Marvel integration plugins, make sure you
    uninstall those, too.

Then stop Elasticsearch 2.x. If you're wondering whether your log should be
complaining vociferously at this point, the answer is a definitive *yes*. You'll
resolve that in the next step.

## Install and Configure the Elasticsearch 6 Adapter [](id=install-and-configure-the-elasticsearch-6-adapter)

Once the default adapter is stopped, install the Elasticsearch 6 adapter (the
LPKG you downloaded) by placing it in your Liferay Home folder's `deploy`
folder.
See 
[here](/discover/portal/-/knowledge_base/7-0/installing-apps-manually#using-your-file-system-to-install-apps)
for more information.

<!--It starts automatically with log messages like this:

Add when possible -->
Now configure the adapter to find your Elasticsearch 6.1.x cluster by specifying
the correct *Cluster Name* and setting *Operation Mode* to *REMOTE*. Make sure
the *Transport Address* matches the one Elasticsearch is using. If testing
locally with Elasticsearch's default settings, the default value in the adapter
works fine (*localhost:9300*).

## Reindex [](id=reindex)

Once the Elasticsearch adapter is installed and talking to the Elasticsearch
cluster, navigate to *Control Panel* &rarr; *Configuration* &rarr; *Server
Administration*, and click *Execute* for the *Reindex all search indexes* entry.

You must also re-index the spell check indexes.

## Reverting to Elasticsearch 2 [](id=reverting-to-elasticsearch-2)

Stuff happens. If that stuff involves an unrecoverable failure during the
upgrade to Elasticsearch 6, roll back to Elasticsearch 2 and regroup.

Since Elasticsearch 2 and Elasticsearch 6 are two separate installations, this
procedure is straightforward:

1.  Stop and remove the Elasticsearch 6 adapter.

2.  Reinstall the Elasticsearch 2 adapter.

3.  Make sure that `elasticsearch.yml` and the Elasticsearch 2 adapter's configuration
    are configured to use the same port (9200 by default).

Learn more about configuring Elasticsearch in [this article](/discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch-for-liferay-0).
