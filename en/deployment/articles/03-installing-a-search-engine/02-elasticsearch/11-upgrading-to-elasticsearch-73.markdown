---
header-id: upgrading-to-elasticsearch-7
---

# Upgrading to Elasticsearch 7

[TOC levels=1-4]

Elasticsearch 7 is supported for @product-ver@. If you're upgrading
@product@ and still running Elasticsearch 6, consider upgrading your
Elasticsearch servers too. If you're setting up a new system and not already
running a remote Elasticsearch 6 server, follow the 
[installation guide](/docs/7-2/deploy/-/knowledge_base/d/installing-elasticsearch) to install
Elasticsearch and the 
[configuration guide](/docs/7-2/deploy/-/knowledge_base/d/configuring-the-liferay-elasticsearch-connector)
to configure the Elasticsearch adapter.

| **Before Proceeding,** back up your existing data before upgrading
| Elasticsearch. If something goes wrong during or after the upgrade, roll back
| to the previous version using the uncorrupted index snapshots. See
| [here](/docs/7-2/deploy/-/knowledge_base/d/backing-up-elasticsearch) for more
| information.

Here, you'll learn to upgrade an
existing Elasticsearch 6 server (or cluster) to Elasticsearch 7: 

1.  [Install and configure Elasticsearch 7](/docs/7-2/deploy/-/knowledge_base/d/installing-elasticsearch).

2.  Back up the application specific indexes for Workflow Metrics and Result
    Rankings.

3. In @product-ver@, security is now provided out of the box. If you're using
   X-Pack security, enable it (it's disabled by default):

    ```yml
    xpack.security.enabled: true
    ```

4.  Blacklist the bundled Liferay Connector to Elasticsearch 6.

5.  Install and configure the Liferay Connector to Elasticsearch 7.

6.  Re-index all search  and spell check indexes.

| **Known Issue:** See
| [LPS-103938](https://issues.liferay.com/browse/LPS-103938). The Liferay
| Connector to Elasticsearch 7 throws an exception in the log when the LPKG
| file is deployed. There are no known functional impacts. If unexpected errors
| occur, re-start the @product@ server.

Learn about configuring Elasticsearch [here](/docs/7-2/deploy/-/knowledge_base/d/configuring-the-liferay-elasticsearch-connector).

## Backing up Application-Specific Indexes

To preserve data stored in application-specific indexes, use a
[rolling upgrade](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/rolling-upgrades.html)
for each index you need to preserve across the upgrade.

| **Synonym Sets:** If you follow the workaround for the bug
| [LPS-100272](https://issues.liferay.com/browse/LPS-100272), your Synonym sets
| are preserved across the upgrade, as they are stored in the index settings
| directly, and not in their own index.

## Blacklisting Elasticsearch 6

To blacklist Elasticsearch 6,

1.  Create a configuration file named

    ```bash
    com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config
    ```

2.  Give it these contents:

    ```properties
    blacklistBundleSymbolicNames=[ \
        "com.liferay.portal.search.elasticsearch6.api", \
        "com.liferay.portal.search.elasticsearch6.impl", \
        "com.liferay.portal.search.elasticsearch6.spi", \
        "com.liferay.portal.search.elasticsearch6.xpack.security.impl", \
        "Liferay Connector to X-Pack Security [Elastic Stack 6.x] - Impl", \
        "Liferay Enterprise Search Security  - Impl" \
    ]
    ```

## Re-index

Once the Elasticsearch adapter is installed and talking to the Elasticsearch
cluster, navigate to *Control Panel* &rarr; *Configuration* &rarr; *Search*,
and click *Execute* for the *Reindex all search indexes* entry.

You must also re-index the spell check indexes.

## Reverting to Elasticsearch 6

Stuff happens. If that stuff involves an unrecoverable failure during the
upgrade to Elasticsearch 7, roll back to Elasticsearch 6 and regroup.

Since your Elasticsearch 6 and 7 are currently two separate installations, this
procedure takes only a few steps:

1.  Stop the Liferay Connector to Elasticsearch 6.

2.  Stop Elasticsearch 7 and make sure that the Elasticsearch 6
    `elasticsearch.yml` and the connector app are configured to use the same
    port (9200 by default).

3.  Start the Elasticsearch server, and then restart the Liferay Connector to
    Elasticsearch 6.

