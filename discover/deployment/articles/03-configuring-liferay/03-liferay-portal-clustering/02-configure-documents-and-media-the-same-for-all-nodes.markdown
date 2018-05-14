# Configure Documents and Media the Same for all Nodes 

@product@'s Documents and Media Library can mount several repositories at a time
while presenting a unified interface to the user. By default, users can use the
@product@ repository, which is already mounted. This repository is built into
@product@ and can use one of 
[several different store implementations](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration) 
as its back-end. In addition to this, users can mount many different kinds of
third party repositories. In a cluster, Documents and Media must have the exact
same configuration on all nodes. If you have a separate repository you've
mounted, all nodes of the cluster must point to this repository. Your avenue for
improving performance at this point is to cluster your third party repository,
using the documentation for the repository you have chosen. If you don't have a
third party repository, you can configure the @product@ repository to perform
well in a clustered configuration. 

The main thing to keep in mind is you need to make sure that every node of the
cluster has the same access to the file store as every other node. For this
reason, you must look at your store configuration. 

Note that the file systems used by the `File System` or `Advanced File System`
stores must support concurrent requests and file locking.

+$$$

**Checkpoint**: To test if the sharing works well, execute the following steps:

1. On Node 1 upload a document to the Documents and Media.
2. On Node 2 download the document. The download should be successful.
3. Repeat the test with reversed roles.

$$$
