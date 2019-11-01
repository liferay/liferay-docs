---
header-id: installing-liferay-enterprise-search
---

# Installing Liferay Enterprise Search

[TOC levels=1-4]

A Liferay Enterprise Search (LES) subscription gets you additional features
beyond what's available out of the box with your @product@ subscription. It
includes

- Connector to use X-Pack Security*
- Connector to use X-Pack Monitoring
- Learning to Rank

\* A LES subscription is not necessary if using Elasticsearch 7 via the _Liferay Connector to
    Elasticsearch 7_. X-Pack's security features are bundled. See the [LES
    compatibility matrix](https://help.liferay.com/hc/en-us/articles/360016511651-Liferay-Enterprise-Search-Compatibility-Matrix)
    for more information.

X-Pack is an 
[Elasticsearch extension](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/setup-xpack.html)
for securing and monitoring Elasticsearch clusters. If you use Elasticsearch,
you should secure it with X-Pack. The security features of X-Pack include
authenticating access to the Elasticsearch cluster's data and encrypting
Elasticsearch's internal and external communications. These are necessary
security features for most production systems. A LES subscription gets you
access to two X-Pack Connectors if you're using Elasticsearch 6: monitoring and
security. Elasticsearch 7 bundles X-Pack's security features, and Liferay has
followed suit. Therefore, X-Pack security is bundled with the Liferay Connector
to Elasticsearch 7, and no LES subscription is necessary. Because of this, the
documentation for
[installing X-Pack security](/docs/7-2/deploy/-/knowledge_base/d/installing-liferay-enterprise-search-security) 
on @product@ has been moved from the LES documentation section (this section) to
the
[Elasticsearch](/docs/7-2/deploy/-/knowledge_base/d/elasticsearch) 
installation and configuration guide. Contact
[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales).

Here's an overview of using the LES applications with @product@:

1.  Get an [Enterprise Search subscription](https://help.liferay.com/hc/en-us/articles/360014400932).

2.  You'll receive a license for X-Pack monitoring. Install it on your
    Elasticsearch servers.

    **Note:** If using Elasticsearch 6, you'll also need a LES subscription for
    X-Pack security.

3.  Download and install the Liferay connectors you purchased. Access the
    connectors in the
    [Help Center Downloads page](https://customer.liferay.com/en/downloads),
    choosing Enterprise Search from the Product drop-down menu.

4.  Configure the connectors with the proper credentials, encryption
    information, and settings.

5.  Restart Elasticsearch. These steps require a full cluster restart.

More detailed installation instructions are available in the article for each
LES feature.

Elastic's documentation explains additional configuration options, features,
and the architecture of
[X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.8/configuring-security.html). 

| **Note:** Out of the box, X-Pack comes with a [30-day
| trial](https://www.elastic.co/guide/en/x-pack/current/license-management.html).
| This can be useful if there's a delay between your subscription and receipt of
| your production X-Pack license.

Now configure security, monitoring, and/or Learning to Rank, depending on your
needs.
