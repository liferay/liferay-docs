# Using Amazon Simple Storage Service [](id=using-amazon-simple-storage-service)

Amazon's simple storage service (S3) is a cloud-based storage solution that you
can use with @product@. All you need is an account, and you can store your
documents to the cloud from all nodes, seamlessly. 

When you sign up for the service, Amazon assigns you unique keys that link
you to your account. In Amazon's interface, you can create "buckets" of data
optimized by region. Once you've created these to your specifications, use [these instructions](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration#s3) 
to connect your S3 account to @product@. 

If you are using Tomcat as your app server, it doesn't contain a `SAXParser`.
You must include this property in `system-ext.properties`: 

    org.xml.sax.driver=com.sun.org.apache.xerces.internal.parsers.SAXParser

Other app servers also need this configuration if they don't contain a
`SAXParser`. Remember to place your `system-ext.properties` file in a folder
that resides in your @product@ installation's class path  (e.g.,
`/WEB-INF/classes/`).

+$$$

**Note:** Amazon S3 requires a SAXParser from the application server to operate.
For some application servers (e.g. Tomcat), it's necessary to define a SAXParser
in order to prevent errors while utilizing this store. This may be set in
`system-ext.properties`. For example,

    org.xml.sax.driver=com.sun.org.apache.xerces.internal.parsers.SAXParser

**Warning:** If a database transaction rollback occurs in a Document Library
that uses a file system based store, file system changes that have occurred
since the start of the transaction won't be reversed. Inconsistencies between
Document Library files and those in the file system store can occur and may
require manual synchronization. All stores except DBStore are vulnerable to this
limitation.

$$$

Consult the Amazon Simple Storage documentation for additional details on using
Amazon's service.
