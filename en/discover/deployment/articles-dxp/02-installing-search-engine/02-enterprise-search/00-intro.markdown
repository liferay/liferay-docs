---
header-id: installing-x-pack
---

# Installing Liferay Enterprise Search

[TOC levels=1-4]

| **Note:** To install Cross-Cluster Replication with @product-ver@, follow the
| instructions found on
| [Liferay Learn](https://learn.liferay.com/dxp/latest/en/using-search/liferay-enterprise-search/cross-cluster-replication/cross-cluster-replication.html).
| The procedure is nearly identical between Liferay 7.1 and 7.2, with any
| differences noted inline in the Liferay Learn articles.

X-Pack is an 
[Elasticsearch extension](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/setup-xpack.html)
that provides
- security (Elasticsearch 6.x only--security is bundled with the Elasticsearch
    7 connector)
- monitoring
- cross-cluster replication
If you use Elasticsearch, you should secure it with X-Pack. The security
features of X-Pack include authenticating access to the Elasticsearch cluster's
data and encrypting Elasticsearch's internal and external communications. These
are necessary security features for most production systems. A Liferay
Enterprise Search subscription gets you access to two X-Pack Connectors for
@product@ when running Elasticsearch 7.x: cross-cluster replication and
monitoring. Security is included on Elasticsearch 7, without a subscription.
Contact
[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales).

Here's an overview of using X-Pack with @product@:

1.  Get an [Enterprise Search subscription](https://help.liferay.com/hc/en-us/articles/360014400932).

2.  You'll receive license for X-Pack. Install it on your Elasticsearch servers.

2.  Download and install the [Liferay Enterprise Search apps](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191013&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191060) you purchased.

3.  Configure the connectors with the proper credentials and encryption
    information.

4.  Restart Elasticsearch. These steps require a full cluster restart.


| **Note:** Out of the box, X-Pack comes with a [30-day
| trial](https://www.elastic.co/guide/en/x-pack/current/license-management.html).
| This can be useful if there's a delay between your subscription and receipt of
| your production X-Pack license.

Following these instructions gives you a working installation of Elasticsearch
communicating freely with @product@. Elastic's documentation explains additional
configuration options, features, and the architecture of
[X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/configuring-security.html). 

