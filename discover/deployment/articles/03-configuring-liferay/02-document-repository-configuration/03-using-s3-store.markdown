# Using Amazon Simple Storage Service [](id=using-amazon-simple-storage-service)

Amazon's simple storage service (S3) is a cloud-based storage solution that you
can use with Documents and Media. All you need is an account, and you can store
your documents to the cloud from all nodes, seamlessly. 

When you sign up for the service, Amazon assigns you unique keys that link you
to your account. In Amazon's interface, you can create "buckets" of data
optimized by region. 

Here are the steps for configuring @product@ to use your S3 account for file
storage:

1.  Amazon S3 requires a `SAXParser` from the application server to operate. If
    you are using an app server like Apache Tomcat that have one, you must
    include this property in a `system-ext.properties` file: 

        org.xml.sax.driver=com.sun.org.apache.xerces.internal.parsers.SAXParser

2.  Place your `system-ext.properties` file in a folder that resides in your 
    @product@ installation's class path  (e.g., `/WEB-INF/classes/`).

3.  Set the following property in a `portal-ext.properties` file in your
    [Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home)
    folder: 

        dl.store.impl=com.liferay.portal.store.cmis.CMISStore

4.  Restart @product@.

5.  In the Control Panel, navigate to *Configuration* &rarr; *System
    Settings* &rarr; *File Storage*.

6.  In the *S3 Store Configuration* screen, configure the store your way.

Your @product@ instance is using the Amazon S3 store. 

To use the S3 store in a cluster, follow these steps: 

1.  Export the configuration from the *S3 Store Configuration* screen to a 
    [`.config` file](/discover/portal/-/knowledge_base/7-1/configuration-files).

2.  Copy the `.config` file to each node's `[Liferay Home]/osgi/configs` 
    folder. 

3.  Copy the `portal-ext.properties` to each node's
    [Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home)
    folder. 

4.  Copy the `system-ext.properties` (if you're using one) to a folder in the 
    app server class path on each node. 

5.  Restart @product@ on the nodes. 

@product@ is using the Amazon S3 store throughout your cluster.

+$$$

**Warning:** If a database transaction rollback occurs in a Document Library
that uses a file system based store, file system changes that have occurred
since the start of the transaction aren't reversed. Inconsistencies between
Document Library files and those in the file system store can occur and may
require manual synchronization. All stores except DBStore are vulnerable to this
limitation.

$$$

Consult the Amazon Simple Storage documentation for additional details on using
Amazon's service.
