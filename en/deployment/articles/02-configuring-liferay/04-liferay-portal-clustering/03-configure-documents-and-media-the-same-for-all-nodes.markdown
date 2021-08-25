---
header-id: configure-documents-and-media-the-same-for-all-nodes
---

# Configure Documents and Media the Same for all Nodes

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/setting-up-liferay/clustering-for-high-availability/database-configuration-for-cluster-nodes.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

In a cluster, Documents and Media must use the same
[document repository configuration](/docs/7-2/deploy/-/knowledge_base/d/document-repository-configuration)
on all nodes. 

Note if you are using the `File System` or `Advanced File System` stores, the
file system must be accessible from all nodes (i.e., a network share), support
concurrent requests, and file locking.

**Checkpoint**: Verify sharing works by executing these steps:

1.  On Node 1 upload a document to the Documents and Media.

2.  On Node 2 download the document. The download should be successful.

3.  Repeat the test with reversed roles.
