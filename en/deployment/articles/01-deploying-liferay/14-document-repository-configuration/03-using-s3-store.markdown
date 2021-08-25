---
header-id: using-amazon-simple-storage-service
---

# Using Amazon Simple Storage Service

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/system-administration/file-storage/other-file-store-types/amazon-s3-store.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

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
    include this property in a
    [`system-ext.properties`](/docs/7-2/deploy/-/knowledge_base/d/system-properties)
    file: 

    ```properties
    org.xml.sax.driver=com.sun.org.apache.xerces.internal.parsers.SAXParser
    ```

2.  Place your `system-ext.properties` file in a folder that resides in your 
    @product@ installation's class path (e.g., `/WEB-INF/classes/`).

3.  Set the following property in a
    [`portal-ext.properties`](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
    file in your [Liferay
    Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home) folder: 

    ```properties
    dl.store.impl=com.liferay.portal.store.s3.S3Store
    ```

4.  Restart @product@.

5.  In the Control Panel, navigate to *Configuration* &rarr; *System
    Settings* &rarr; *File Storage*.

6.  In the *S3 Store Configuration* screen, configure the store your way.

7.  Click *Save*.

Your @product@ instance is using the Amazon S3 store. 

| **Warning:** If a database transaction rollback occurs in a Document Library
| that uses a file system based store, file system changes that have occurred
| since the start of the transaction aren't reversed. Inconsistencies between
| Document Library files and those in the file system store can occur and may
| require manual synchronization. All stores except DBStore are vulnerable to 
| this limitation.

| **Note:** No action is required to support AWS Signature Version 4 request
| authorization. 

Consult the Amazon Simple Storage documentation for additional details on using
Amazon's service. 
