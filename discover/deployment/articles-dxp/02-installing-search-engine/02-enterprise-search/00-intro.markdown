# Installing Liferay Enterprise Search [](id=installing-x-pack)

X-Pack is an 
[Elasticsearch extension](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/setup-xpack.html)
for securing and monitoring Elasticsearch clusters. If you use Elasticsearch,
you should secure it with X-Pack. The security features of X-Pack include
authenticating access to the Elasticsearch cluster's data and encrypting
Elasticsearch's internal and external communications. These are necessary
security features for most production systems. A Liferay Enterprise Search
Premium subscription gets you access to two X-Pack Connectors for @product@:
monitoring and security. A Liferay Enterprise Search Standard subscription gets
you the monitoring integration. Contact
[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales).

Here's an overview of using X-Pack with @product@:

1.  Get an [Enterprise Search subscription](https://help.liferay.com/hc/en-us/articles/360014400932).

2.  Download and install the X-Pack connectors you purchased. Access the
    connector corresponding to your subscription level:

    [Enterprise Search Standard](https://customer.liferay.com/group/customer/downloads?_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_formDate=1543422323440&p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_product=enterpriseSearchStandard&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileType=product)

    [Enterprise Search Premium](https://customer.liferay.com/group/customer/downloads?_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_formDate=1543422331595&p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_product=enterpriseSearchPremium&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileType=product)

3.  Configure the connectors with the proper credentials and encryption
    information.

4.  Restart Elasticsearch. These steps require a full cluster restart.

Following these instructions gives you a working installation of Elasticsearch
communicating freely with @product@. Read Elastic's documentation to learn about
additional configuration options, features, and the architecture of
[X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/configuring-security.html). 

Now configure security and/or monitoring, depending on your needs.
