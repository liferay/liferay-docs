---
header-id: upgrading-to-elasticsearch-6-5
---

# Upgrading to Elasticsearch 6.5

[TOC levels=1-4]

Elasticsearch 6.5.x is the default, most up-to-date supported version of
Elasticsearch for @product-ver@. If you're upgrading @product@ and still running
Elasticsearch 6.1, it's time to upgrade your Elasticsearch servers too. If
you're setting up a new system and not already running a remote Elasticsearch
6.1.x server, follow the 
[installation guide](/docs/7-2/deploy/-/knowledge_base/d/installing-elasticsearch) to install
Elasticsearch and the 
[configuration guide](/docs/7-2/deploy/-/knowledge_base/d/configuring-the-liferay-elasticsearch-connector)
to configure the Elasticsearch adapter. Here, you'll learn to upgrade an
existing Elasticsearch 6.1.x server (or cluster) to Elasticsearch 6.5.x: 

1.  [Install and configure Elasticsearch 6.5.x](/docs/7-2/deploy/-/knowledge_base/d/elasticsearch).

2. Disable X-Pack Security in `elasticsearch.yml` unless you have an Liferay
   Enterprise Search subscription, which gives you access to the Liferay
   Enterprise Search Security app:

    ```yml
    xpack.security.enabled: false
    ```

3.  Configure the bundled Liferay Connector to Elasticsearch 6.

4.  Re-index all search  and spell check indexes.

| **Before Proceeding,** back up your existing data before upgrading
| Elasticsearch. If something goes wrong during or after the upgrade, roll back
| to the previous version using the uncorrupted index snapshots. See
| [here](/docs/7-2/deploy/-/knowledge_base/d/backing-up-elasticsearch) for more
| information.

Learn about configuring Elasticsearch [here](/docs/7-2/deploy/-/knowledge_base/d/configuring-the-liferay-elasticsearch-connector).

## Re-index

Once the Elasticsearch adapter is installed and talking to the Elasticsearch
cluster, navigate to *Control Panel* &rarr; *Configuration* &rarr; *Server
Administration*, and click *Execute* for the *Reindex all search indexes* entry.

You must also re-index the spell check indexes.

## Reverting to Elasticsearch 6.1 

Stuff happens. If that stuff involves an unrecoverable failure during the
upgrade to Elasticsearch 6.5, roll back to Elasticsearch 6.1 and regroup.

Since your 6.1 and 6.5 are currently two separate installations, this procedure
is straightforward:

1.  Stop the Liferay Connector to Elasticsearch 6.

2.  Stop Elasticsearch 6.5 and make sure that the Elasticsearch 6.1
    `elasticsearch.yml` and the connector app are configured to use the same
    port (9200 by default).

3.  Start the Elasticsearch server, and then restart the Liferay Connector to
    Elasticsearch 6.

