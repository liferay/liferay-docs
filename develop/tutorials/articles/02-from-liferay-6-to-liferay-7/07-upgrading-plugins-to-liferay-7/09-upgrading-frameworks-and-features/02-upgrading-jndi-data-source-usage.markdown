---
header-id: upgrading-jndi-data-source-usage
---

# Upgrading JNDI Data Source Usage

[TOC levels=1-4]

In @product@'s OSGi environment, you must use the portal's class loader to load
the application server's JNDI classes. An OSGi bundle's attempt to connect to a
JNDI data source without using @product@'s class loader results in a
`java.lang.ClassNotFoundException`.

For more information on how to do this, see the
[Connecting to JNDI Data Sources](/docs/7-1/tutorials/-/knowledge_base/t/connecting-to-data-sources-using-jndi)
article.
