# Configure Documents and Media the Same for all Nodes [](id=configure-documents-and-media-the-same-for-all-nodes)

In a cluster, Documents and Media must use the same
[document repository configuration](/discover/deployment/-/knowledge_base/7-1/document-repository-configuration)
on all nodes. 

Note if you are using the `File System` or `Advanced File System` stores, the
file system must be accessible from all nodes (i.e., a network share), support
concurrent requests, and file locking.

**Checkpoint**: Verify sharing works by executing these steps:

1.  On Node 1 upload a document to the Documents and Media.

2.  On Node 2 download the document. The download should be successful.

3.  Repeat the test with reversed roles.
