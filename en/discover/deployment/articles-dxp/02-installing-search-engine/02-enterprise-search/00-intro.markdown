---
header-id: installing-x-pack
---

# Installing Liferay Enterprise Search

[TOC levels=1-4]

A Liferay Enterprise Search (LES) subscription provides access to additional
search features beyond what's available out of the box with your Liferay DXP
subscription. To see a detailed description of the services and features
included with LES on @product@, refer to the official description of LES in the
[Liferay DXP Components resource](https://help.liferay.com/hc/en-us/articles/360014400932).

| **Note:** To install Cross-Cluster Replication with @product-ver@, follow the
| instructions found on
| [Liferay Learn](https://learn.liferay.com/dxp/latest/en/using-search/liferay-enterprise-search/cross-cluster-replication/cross-cluster-replication.html).
| The procedure is nearly identical between Liferay 7.1 and 7.2, with any
| differences noted inline in the Liferay Learn articles.

LES customers receive a
[platinum Elasticsearch license](https://www.elastic.co/subscriptions)
from Liferay. There can be a delay between your subscription and receipt of the
license, but you can enable a
[30-day trial](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/start-trial.html)
to work with in the meantime.

Detailed installation and usage instructions are available in the article for
each LES feature, including

-  [Liferay Learn] [Cross-Cluster Replication](https://learn.liferay.com/dxp/latest/en/using-search/liferay-enterprise-search/cross-cluster-replication/cross-cluster-replication.html)
-  [Monitoring Elasticsearch](/docs/7-1/deploy/-/knowledge_base/d/monitoring-product)
-  [Securing Elasticsearch](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay-enterprise-search-security) [Free without LES for Liferay CE and DXP with Elasticsearch 7] 

Always check the
[LES compatibility matrix](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-Enterprise-Search)
for compatibility information.

Here's an overview of using LES with @product@:

1.  Get an [Enterprise Search subscription](https://help.liferay.com/hc/en-us/articles/360014400932).

2.  You'll receive a license for the Elasticsearch features. Install it on your Elasticsearch servers.

2.  Download and install the [Liferay Enterprise Search apps](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191013&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191060)
    you purchased.

3.  Configure the connectors with the proper credentials and encryption
    information.

4.  Restart Elasticsearch.
