# Installing X-Pack (6.1) [](id=installing-x-pack-6-1)

X-Pack is an 
[Elasticsearch extension](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-xpack.html)
for securing and monitoring Elasticsearch clusters. If you use Elasticsearch,
you should secure it with X-Pack. The security features of X-Pack include
authenticating access to the Elasticsearch cluster's data and encrypting
Elasticsearch's internal and external communications. These are necessary
security features for most production systems. A Liferay Enterprise Search
Premium subscription gets you access to two X-Pack Connectors for @product@:
monitoring and security. A Liferay Enterprise Search Standard subscription gets
you the monitoring integration. Contact
[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales).

Here's an overview of using X-Pack to secure the data indexed in Elasticsearch:

1.  Get an Enterprise Search subscription.

2.  [Install X-Pack into Elasticsearch](https://www.elastic.co/guide/en/x-pack/6.1/installing-xpack.html) 
    and configure it to require authentication and 
    [encryption](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-tls.html#configuring-tls).

3.  Download and install the 
[Liferay Connector to X-Pack Security](https://web.liferay.com/group/customer/dxp/downloads/enterprise-search) 
[Elastic Stack 6.x]. 

4.  Configure the X-Pack connector with the proper credentials and encryption
    information.

5.  Restart Elasticsearch. These steps require a full cluster restart.

Following these instructions gives you a working installation of Elasticsearch
communicating freely with @product@. Read Elastic's documentation to learn about
additional configuration options, features, and the architecture of
[X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-security.html). 
