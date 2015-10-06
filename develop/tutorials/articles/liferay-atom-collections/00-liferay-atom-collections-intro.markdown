# Interacting with Liferay's Atom Collections

Liferay's asset framework allows many different kinds of content to be created
and published. Web content articles, blog entries, wiki articles, and documents
are a few of the asset types that Liferay supports out of the box. Developers
can also create custom asset types. For more information about Liferay's asset
framework, please see the [Asset Framework]() [not yet written] tutorial.
Liferay also supports web feeds in several different formats: Atom 1.0, RSS 2.0,
and RSS 1.0. To learn how to manage RSS feeds in Liferay, please see the
[Managing RSS Feeds in Liferay]() [not yet written] tutorial.

Liferay also includes a bundled Atom server and provides Atom collections for
several portal entities including web content articles, blog entries, documents,
document folders, and users. Liferay's Atom server implementation is based on
[Apache Abdera](https://abdera.apache.org). Abdera is intended to be a
functionally-complete, high-performance implementation of the IETF Atom
Syndication Format ([RFC 4287](http://www.ietf.org/rfc/rfc4287.txt)) and Atom
Publishing Protocol ([RFC 5023](http://www.ietf.org/rfc/rfc5023.txt))
specifications. 

## Atom Service Document

Liferay's Atom server allows third-party software to be used for reading or
publishing content. The first step that must be taken by any third-party
software that's intended to be used for authoring or publishing content is to
discover the locations and capabilities of Liferay's available Atom collections.
Service documents are designed to support this discovery process. Liferay's
service documents is available at the following address:

    http://[server]:[port]/api/atom

For example, if you're running Liferay locally, navigate to this address to
download Liferay's Atom service document:

    http://localhost:8080/api/atom

Navigating to this address prompts you to download an XML document named `atom`
with contents like this:

    <?xml version='1.0' encoding='UTF8'?>
    <service xmlns="http://www.w3.org/2007/app">
        <workspace>
            <title xmlns="http://www.w3.org/2005/Atom" type="text">Liferay Workspace</title>
            <collection href="http://localhost:8080/api/atom/blogs">
                <title xmlns="http://www.w3.org/2005/Atom" type="text">Liferay javax.portlet.title.com_liferay_blogs_web_portlet_BlogsPortlet</title>
                <accept>application/atom+xml;type=entry</accept>
            </collection>
            <collection href="http://localhost:8080/api/atom/files"><title xmlns="http://www.w3.org/2005/Atom" type="text">Liferay javax.portlet.title.com_liferay_document_library_web_portlet_DLPortlet files</title>
                <accept>application/atom+xml;type=entry</accept>
            </collection>
            <collection href="http://localhost:8080/api/atom/folders">
                <title xmlns="http://www.w3.org/2005/Atom" type="text">Liferay javax.portlet.title.com_liferay_document_library_web_portlet_DLPortlet folders</title>
                <accept>application/atom+xml;type=entry</accept>
            </collection>
            <collection href="http://localhost:8080/api/atom/web-content">
                <title xmlns="http://www.w3.org/2005/Atom" type="text">Liferay javax.portlet.title.com_liferay_journal_web_portlet_JournalPortlet</title>
                <accept>application/atom+xml;type=entry</accept>
            </collection>
        </workspace>
    </service>

This document lists four available Liferay Atom collections:

- Blogs Atom collection
- Documents and Media Files Atom collection
- Documents and Media Folders Atom collection
- Web Content Atom collection

There's also an Atom collection of portal users but it's read-only and is not
listed in the `atom` XML document:

- Users Atom collection

## Atom URL Conventions

The URLs for Liferay's Atom collections and collection entries follow a simple
convention. Each URL starts with the same base:

    http://[server]:[port]/api/atom

Each of Liferay's Atom collections has a unique name which becomes part of the
URL:

    http://[server]:[port]/api/atom/[collection-name]

For example, if you're running Liferay locally, the URL for blogs is

    http://localhost:8080/api/atom/blogs

Each Atom collection entry has its own ID, unique in its collection
context. This ID is appended to the URL:

    http://[server]:[port]/api/atom/[collection-name]/[entry-id]

The entry URL is used for all of the entry operations: view, update, and delete.

Some collections are media-oriented, and entries contain enclosed media content
(e.g., images). To access media content, append `:media` after the entry ID:

    http://[server]:[port]/api/atom/[collection-name]/[entry-id]:media

The `atom` XML document is publicly available at this URL:

    http://[server]:[port]/api/atom

All the other URLs described in this section require authentication.

## Authentication

If you try to navigate to the URL of an Atom collection or entry without
authenticating, your request fails with the status code of *500: Internal
Server Error*. For example, if you're running Liferay locally, try to navigate
to the blogs collection URL in your browser:

    http://localhost:8080/api/atom/blogs

You'll see that the request fails. If you check your Liferay log, you'll see
that an exception occurred:

    16:21:50,800 WARN  [http-bio-8080-exec-9][AbstractEntityCollectionAdapter:199] A ResponseException was thrown.
    org.apache.abdera.protocol.server.context.ResponseContextException
            at com.liferay.portal.atom.AtomCollectionAdapterWrapper.getEntries(AtomCollectionAdapterWrapper.java:125)
    ...
    Caused by: java.lang.SecurityException: Access denied to com.liferay.portlet.blogs.service.BlogsEntryService#getCompanyEntries
            at com.liferay.service.access.policy.SAPAccessControlPolicy.onServiceRemoteAccess(SAPAccessControlPolicy.java:155)

In order for this kind of request to succeed, you need to authenticate. To
authenticate, you must include the appropriate header in your HTTP request.
There are various tools that allow you to construct and send HTTP requests with
custom headers. For example, you could use the
[cURL](http://curl.haxx.se) command line tool or the Firefox
[RESTClient](https://addons.mozilla.org/en-Us/firefox/addon/restclient) plugin.

To use HTTP basic authentication, you must first follow these steps:

1.  Construct a string of the form `[username]:[password]`.
2.  Convert this string to use base 64 encoding.
3.  Finally, you need to construct a request header of the form `Authorization:
    Basic [base-64-encoded-string]`.

Suppose that you want to view Liferay's Blogs Atom collection using the
`test@liferay.com` user whose password is `test`. Here's how you could
accomplish this using curl:

1. Liferay's default authentication type uses users' email addresses for
   authentication. Other options include using users' screen names or user IDs.
   Assuming that Liferay's authentication type is still set to use email
   addresses, the string of the form `[username]:[password]` would be
   `test@liferay.com:test`.
2. To convert the string `test@liferay.com:test` to base 64 encoding, use any
   conversion tool. For example, you could use
   [this web application](https://www.base64encode.org/). The base 64 encoding
   of `test@liferay.com:test` is `dGVzdEBsaWZlcmF5LmNvbTp0ZXN0`.
3. So your request header should take the form `Authorization: Basic
   dGVzdEBsaWZlcmF5LmNvbTp0ZXN0`. Use the following curl command to view
   Liferay's Blogs Atom collection:

        curl -v http://localhost:8080/api/atom/blogs -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0'

    (curl's `-v` parameter enables verbose mode. It's useful if you want to
    see details about the HTTP request and response but it's not necessary.)

Note that the HTTP basic authentication mechanism simply transmits credentials
in base 64 encoding but provides no confidentiality via encryption or hashing.
Thus, HTTP basic authentication is typically used alongside SSL.

Liferay's Atom collections not only allow entries to be read, but also to be
published, edited, or deleted. Next, you'll learn how to interact with Liferay's
specific Atom collections in more detail.

## Liferay's Blogs Atom Collection

As demonstrated above, Liferay's Blogs Atom collection is located here:

    http://localhost:8080/api/atom/blogs

This chart demonstrates some ways to interact with the blogs collection:

<!-- Edit below image to remove reference to organization. This image was created when organizations could have pages and content just like sites. -->
![Atom Blogs Summary](../../images/atom-blogs-summary.png)

There are two different views for viewing blogs collections:

- *Group blog entries* are displayed if the `groupId` URL parameter is provided.
  This view supports pagination via the `page` and `max` URL parameters.
- *Company blog entries* are displayed by default or if the `companyId` URL
  parameter is provided. Only `max` entries are displayed.

For example, if you're running Liferay locally, you can retrieve the first five
blog entries from a site with a site ID of `20146` by using curl like this:

    curl -v 'http://localhost:8080/api/atom/blogs?groupId=20146&page=1&max=5' -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0'

Here is an example of the returned XML feed document (in this example, there is
only one blog entry):

    <?xml version='1.0' encoding='UTF-8'?>
    <feed xmlns="http://www.w3.org/2005/Atom">
      <author>
        <name>Liferay</name>
      </author>
      <link href="http://localhost:8080/api/atom/blogs?groupId=20146&amp;page=1&amp;max=5" />
      <link href="http://localhost:8080/api/atom/blogs?groupId=20146&amp;page=1&amp;max=5" rel="self" />
      <id>tag:liferay.com:blogs:feed</id>
      <title type="text">Liferay javax.portlet.title.com_liferay_blogs_web_portlet_BlogsPortlet</title>
      <updated>2015-08-29T03:28:33.750Z</updated>
      <link href="http://localhost:8080/api/atom/blogs?groupId=20146&amp;max=5&amp;page=1" rel="first" />
      <link href="http://localhost:8080/api/atom/blogs?groupId=20146&amp;max=5&amp;page=1" rel="last" />
      <entry>
        <link href="http://localhost:8080/api/atom/blogs/20801" />
        <author>
          <name>Test Test</name>
        </author>
        <id>tag:liferay.com:blogs:entry:20801</id>
        <summary type="text">
        </summary>
        <title type="text">Title</title>
        <updated>2015-08-26T19:55:31.000Z</updated>
        <content type="html">&lt;p&gt;Content&lt;/p&gt;</content>
      </entry>
    </feed>

This XML document can be read by any Atom feed reader, and the blog entry or
entries will be displayed correctly.

### Liferay Blogs Atom Client

In addition to allowing entries to be read, Liferay's Blogs Atom collection
allows entries to added, updated, or deleted. In this section, you'll learn how
to create a client that can manipulate Liferay's blogs collection using the
[AtomPub](https://en.wikipedia.org/wiki/Atom_%28standard%29) protocol. As
mentioned before, Liferay's Atom server implementation is based on
[Apache Abdera](https://abdera.apache.org). Abdera can also be used to create
Atom clients. See
[Abdera's documentation](https://cwiki.apache.org/confluence/display/ABDERA/AtomPub+Client)
for more information. In order to add, update, or delete blog posts, the Abdera
client builds the appropriate Atom XML documents and uses the
[Apache Commons HTTP Client](http://hc.apache.org/httpclient-3.x)
to send requests to Liferay.

In order to create an create an Atom client using Abdera, you should declare the
[abdera-client](http://search.maven.org/#artifactdetails|org.apache.abdera|abdera-client|1.1.2|bundle) as a dependency of your project.

For example, here's a code snippet that demonstrates how to add a new blog post
to Liferay. To add a new blog post, an HTTP POST request must be used. Note that
in this snippet, the `groupId` of the Liferay site where you're adding the blog
post is hard-coded.

    Abdera abdera = new Abdera();
    Factory factory = abdera.getFactory();
    Entry entry = factory.newEntry();
    entry.setId(FOMHelper.generateUuid());
    entry.setUpdated(new Date());
    entry.addAuthor("Joe Bloggs");
    entry.setTitle("Title");
    entry.setSummary("Summary");
    entry.setContentAsHtml(
        "<p>This is post was <b>created</b> by the Atom client!</p>");

    AbderaClient client = new AbderaClient(abdera);
    RequestOptions options = createRequestOptions(client);
    BaseRequestEntity requestEntity =
        new BaseRequestEntity(entry, false);

    String blogsUrl = "http://localhost:8080/api/atom/blogs";

    ClientResponse response =
        client.post(blogsUrl + "?groupId=20146", requestEntity, options);

Here, `createRequestOptions` is a helper method defined like this:

    private static RequestOptions createRequestOptions(AbderaClient client) {
        String encodedCredential =
            Base64.encode("test@liferay.com:test".getBytes());
        RequestOptions options = client.getDefaultRequestOptions();
        options.setHeader("Authorization", "Basic " + encodedCredential);

        return options;
    }

Next, here's a code snippet that demonstrates how to update an existing blog
post in Liferay. To update a blog post, an HTTP PUT request must be used. Note
that in this snippet, the `companyId` of the portal instance where the blog post
can be found and the blog post's `entryId` are hard-coded. When updating a blog
entry (or any portal entry in an Atom collection), you have to specify the entry
ID.

    Abdera abdera = new Abdera();
    Entry entry = abdera.newEntry();
    entry.setId("tag:liferay.com:blogs:entry:21308");
    entry.setUpdated(new Date());
    entry.addAuthor("Joseph Blogger");
    entry.setTitle("Updated title");
    entry.setSummary("Updated summary");
    entry.setContentAsHtml(
        "<p>This post was <b>updated</b> by the Atom client!</p>");

    AbderaClient client = new AbderaClient(abdera);
    RequestOptions options = createRequestOptions(client);
    BaseRequestEntity requestEntity =
        new BaseRequestEntity(entry, false);

    String blogsUrl = "http://localhost:8080/api/atom/blogs";

    ClientResponse response = client.put(
        blogsUrl + "/21308?companyId=20118", requestEntity, options);

The same `createRequestOptions` helper method is used here.

Lastly, here's a code snippet that demonstrates how to delete a blog post in
Liferay. To delete a blog post, an HTTP DELETE request must be used. Note that
in this snippet, the `companyId` of the portal instance where the blog post
exists and the blog post's `entryId` are hard-coded.

    Abdera abdera = new Abdera();
    AbderaClient client = new AbderaClient(abdera);
    RequestOptions options = createRequestOptions(client);

    String blogsUrl = "http://localhost:8080/api/atom/blogs";

    ClientResponse response = client.delete(
        blogsUrl + "/21315?companyId=20118", options);

You can find the Atom client examples in this section in the
`com.liferay.docs.atomclient` project on
[Github](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/com.liferay.docs.atomclient).
To create a runnable JAR file of the Atom client including all of its
dependencies, run `gradle shadowJar`.
[Gradle Shadow](https://github.com/johnrengelman/shadow) provides an easy and
efficient way to package the Atom client's dependencies alongside the Atom
client code in a runnable JAR file. To run the client from the project
directory, use one of the following commands:

- `java -jar build/libs/com.liferay.docs.atomclient-all.jar "BLOGS_POST"`
- `java -jar build/libs/com.liferay.docs.atomclient-all.jar "BLOGS_PUT"`
- `java -jar build/libs/com.liferay.docs.atomclient-all.jar "BLOGS_DELETE"`

Before running the Atom client, however, make sure to update any hard-coded
values (company ID, group ID, or entry ID) to match your portal's content. Then
rebuild your Atom client JAR file. This Atom client isn't a real-world example,
but it does demonstrate how a real-world client could interact with Liferay's
Atom collections. A simple improvement to the Atom client would be to update it
so that values like company ID, group ID, and entry ID could be supplied as
command line arguments instead of being hard-coded.

Of course, Liferay's Blogs Atom collection isn't the only collection that you
can interact with via a client. You can also create clients to interact with the
Web Content Atom collection, the Documents and Media Files Atom collection, and
the Documents and Media Folders Atom collection. (The Users Atom collection is
read-only.)

## Liferay's Web Content Atom Collection

Just as with Blogs, Liferay's Web Content Atom collection can be listed using
the Atom protocol. For example, if you're running Liferay locally, the URL for
web content is

    http://localhost:8080/api/atom/web-content

This chart demonstrates how to interact with the web content collection:

![Atom Web Content Summary](../../images/atom-blogs-summary.png)

There is only one view for viewing web content:

*Group web content entries* are displayed if the `groupId` URL parameter is
provided. This view supports pagination via the `page` and `max` URL
parameters.

Since there is only one view, the `groupId` URL parameter must be appended to
the URL.

<!-- There is one optional parameter, `type`, that indicates the type of web content.
By default its value is `general`. -->

Suppose you wanted to view a feed of all the web content articles belonging to a
site with a `groupId` of `20146`. If you're running Liferay locally, you can do
so with curl like this:

    curl -v 'http://localhost:8080/api/atom/web-content?groupId=20146' -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0'

Liferay returns an XML feed document like this:

    <?xml version='1.0' encoding='UTF-8'?>
    <feed xmlns="http://www.w3.org/2005/Atom">
      <author>
        <name>Liferay</name>
      </author>
      <link href="http://localhost:8080/api/atom/web-content?groupId=20146" />
      <link href="http://localhost:8080/api/atom/web-content?groupId=20146" rel="self" />
      <id>tag:liferay.com:web-content:feed</id>
      <title type="text">Liferay javax.portlet.title.com_liferay_journal_web_portlet_JournalPortlet</title>
      <updated>2015-09-02T19:23:35.263Z</updated>
      <link href="http://localhost:8080/api/atom/web-content?groupId=20146&amp;page=1" rel="first" />
      <link href="http://localhost:8080/api/atom/web-content?groupId=20146&amp;page=1" rel="last" />
      <entry>
        <link href="http://localhost:8080/api/atom/web-content/21401" />
        <author>
          <name>Test Test</name>
        </author>
        <id>tag:liferay.com:web-content:entry:21401</id>
        <summary type="text" />
        <title type="text">&lt;?xml version='1.0' encoding='UTF-8'?&gt;&lt;root available-locales="en_US" default-locale="en_US"&gt;&lt;Title language-id="en_US"&gt;Web Content Title&lt;/Title&gt;&lt;/root&gt;</title>
        <updated>2015-09-02T19:23:18.000Z</updated>
        <content type="application/xml">&lt;?xml version="1.0"?>
 
    <root available-locales="en_US" default-locale="en_US">
    	<dynamic-element name="content" type="text_area" index-type="keyword"
        index="0" instance-id="byoi">
    		<dynamic-content
            language-id="en_US"><![CDATA[Content]]></dynamic-content>
    	</dynamic-element>
    </root></content>
      </entry>
    </feed>

In this example, there's only one web content article in the specified site and
thus only one entry.

Creating new Web Content articles via an Atom client is similar to creating new
Blog posts. Note that entries created or updated using AtomPub have many
attributes set by default: they never expire, are never reviewed, and are
indexable. If workflow has been enabled, however, it is executed normally.
Deleting web content articles with an Atom client works the same way as for
deleting blog posts. 

## Liferay's Documents and Media Files Atom Collection

Liferay's Documents and Media Files Atom collection can be listed using the Atom
protocol. If you're running Liferay locally, the base URL for files is

    http://localhost:8080/api/atom/files

You need to append a `folderId` parameter to this URL to specify a specific
folder. For root folders (where `folderId` is `0`), you must specify the
`repository` request parameter instead of the `folderId` parameter. The value of
the `repositoryId` parameter should be the group ID of the site containing the
root folder. The files collection supports pagination, using the `page` and
`max` request parameters. 

This chart demonstrates how to interact with the files collection:

![Atom Files Summary](../../images/atom-files-summary.png)

There is one view for viewing a file collection and another view for viewing
individual files:

- *Folder file entries* are displayed if the `folderId` (or `repositoryId` in
  the case of root folders) URL parameter is provided. This view supports
  pagination via the `page` and `max` URL parameters.
- A *file entry* is displayed if `/[entryId]` is appended to the base URL.

Suppose you wanted to view a feed of all the documents belonging to the root
documents and media folder of a site with a `groupId` of `20146`. If you're
running Liferay locally, you can do so with curl like this:

    curl -v 'http://localhost:8080/api/atom/files?repositoryId=20146' -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0'

Liferay returns an XML feed document like this:

    <?xml version='1.0' encoding='UTF-8'?>
    <feed xmlns="http://www.w3.org/2005/Atom">
      <author>
        <name>Liferay</name>
      </author>
      <link href="http://localhost:8080/api/atom/files?repositoryId=20146" />
      <link href="http://localhost:8080/api/atom/files?repositoryId=20146" rel="self" />
      <id>tag:liferay.com:files:feed</id>
      <title type="text">Liferay javax.portlet.title.com_liferay_document_library_web_portlet_DLPortlet files</title>
      <updated>2015-09-09T18:29:28.856Z</updated>
      <link href="http://localhost:8080/api/atom/files?repositoryId=20146&amp;page=1" rel="first" />
      <link href="http://localhost:8080/api/atom/files?repositoryId=20146&amp;page=1" rel="last" />
      <entry>
        <link href="http://localhost:8080/api/atom/files/21519" />
        <author>
          <name>Test Test</name>
        </author>
        <id>tag:liferay.com:files:entry:21519</id>
        <summary type="text">
        </summary>
        <title type="text">test.txt</title>
        <updated>2015-09-09T18:28:03.000Z</updated>
        <content type="application/xml" src="http://localhost:8080/api/atom/files/21519:media" />
      </entry>
    </feed>

In this example, there's only one file in the specified folder and thus only one
entry. (The inclusion of the `respositoryId=20146` URL parameter means that the
root folder of the site whose `groupId` is `20146` is specified.) 

If you wanted to view a feed of all the documents inside a specific (non-root)
Documents and Media folder, you could do so with curl like this:

    curl -v 'http://localhost:8080/api/atom/files?folderId=21503' -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0'

A file entry can be viewed either as an Atom entry (i.e., as Atom XML content)
or as media (i.e., as binary content).

-  To view a file as an Atom entry, use a URL like this:

        http://localhost:8080/api/atom/files/21519

    Here, `21519` is the `entryId` of a file. Here's an example using curl:

        curl -v 'http://localhost:8080/api/atom/files/21519' -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0'

- To view the actual media content of a file, use a URL like this:

        http://localhost:8080/api/atom/files/21519:media

    You can test this using curl:

        curl -v 'http://localhost:8080/api/atom/files/21519:media' -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0'

Next, you'll learn how to create an Atom client to add, update, and delete
Liferay documents.

### Liferay Documents and Media Files Atom Client

In this section, you'll learn how to create an [Abdera](https://abdera.apache.org)
client that can add, update, and delete entries from Liferay's Documents and
Media files collection via the
[AtomPub](https://en.wikipedia.org/wiki/Atom_%28standard%29) protocol. See
[Abdera's documentation](https://cwiki.apache.org/confluence/display/ABDERA/AtomPub+Client)
for more information. In order to add, update, or delete files, the Abdera
client uses the [Apache Commons HTTP Client](http://hc.apache.org/httpclient-3.x) 
to send requests to Liferay.

In order to create an Atom client using Abdera, you should declare the
[abdera-client](http://search.maven.org/#artifactdetails|org.apache.abdera|abdera-client|1.1.2|bundle)
as a dependency of your project.

There are two ways to add new files to Liferay's Atom file collection:

1. Submit an Atom XML document.
2. Submit the file directly.

You'll learn both ways below.

For example, here's a code snippet that demonstrates how to add a new image file
to Liferay by submitting an Atom XML document. To add a new file, an HTTP POST
request must be used. Note that in this snippet, the `groupId` of the Liferay
site where you're adding the file is hard-coded.

    Abdera abdera = new Abdera();
    Factory factory = abdera.getFactory();
    Entry entry = factory.newEntry();
    entry.setId(FOMHelper.generateUuid());
    entry.setUpdated(new Date());
    entry.setTitle("cat.jpg");
    entry.setSummary("This is a cat!");

    File f = new File("/home/jrao/Pictures/cat.jpg");
    FileInputStream fis = new FileInputStream(f);
    entry.setContent(fis, "image/jpeg");

    AbderaClient client = new AbderaClient(abdera);
    RequestOptions options = createRequestOptions(client);
    options.setSlug(f.getName());
    options.setContentType("application/atom+xml");
    options.setHeader("Media-Content-Type", "image/jpeg");

    String filesUrl = "http://localhost:8080/api/atom/files";

    // If you want to specify a non-root folder: 
    // ClientResponse response = client.post(filesUrl + "?folderId=21503", entry, options);

    // If you want to specify the root folder: 
    ClientResponse response = client.post(filesUrl + "?repositoryId=20146", entry, options);

Here, `createRequestOptions` is the same helper method that was defined earlier:

    private static RequestOptions createRequestOptions(AbderaClient client) {
        String encodedCredential =
            Base64.encode("test@liferay.com:test".getBytes());
        RequestOptions options = client.getDefaultRequestOptions();
        options.setHeader("Authorization", "Basic " + encodedCredential);

        return options;
    }

Next, here's a code snippet that demonstrates how to add a new image file to
Liferay by submitting it directly:

    Abdera abdera = new Abdera();

    File f = new File("/home/jrao/Pictures/cat.jpg");
    FileInputStream fis = new FileInputStream(f);

    AbderaClient client = new AbderaClient(abdera);
    RequestOptions options = createRequestOptions(client);
    options.setSlug(f.getName());
    options.setContentType("image/jpeg");
    options.setHeader("Title", "cat");
    options.setHeader("Summary", "This is another cat!");

    InputStreamRequestEntity streamRequest = new InputStreamRequestEntity(fis, "image/jpeg");

    String filesUrl = "http://localhost:8080/api/atom/files";

    // If you want to specify a non-root folder: 
    // ClientResponse response = client.post(filesUrl + "?folderId=21503", streamRequest, options);

    // If you want to specify the root folder: 
    ClientResponse response = client.post(filesUrl + "?repositoryId=20146", streamRequest, options);

Here's a code snippet that demonstrates how to update an image file by
submitting an Atom XML document:

    Abdera abdera = new Abdera();

    Factory factory = abdera.getFactory();
    Entry entry = factory.newEntry();
    entry.setId("tag:liferay.com:dl:entry:20776");
    entry.setUpdated(new Date());
    entry.setTitle("updated-title.jpg");
    entry.setSummary("Updated summary");

    File f = new File("/local/path/cat.jpg");
    FileInputStream fis = new FileInputStream(f);
    entry.setContent(fis, "image/jpeg");

    AbderaClient client = new AbderaClient(abdera);
    RequestOptions options = createRequestOptions(client);
    options.setContentType("application/atom+xml");
    options.setHeader("Media-Content-Type", "image/jpeg");
    options.setSlug(f.getName());

    ClientResponse response = client.put(filesUrl + "/20776", entry, options);

Next, here's a code snippet that demonstrates how to update an image file by
submitting a new one directly:

    Abdera abdera = new Abdera();

    File f = new File("/local/path/cat.jpg");
    FileInputStream fis = new FileInputStream(f);

    AbderaClient client = new AbderaClient(abdera);
    RequestOptions options = createRequestOptions(client);
    options.setSlug(f.getName());
    options.setContentType("image/jpeg");
    options.setHeader("Title", "updated-directly.jpg");
    options.setHeader("Summary", "Summary updated directly!");

    InputStreamRequestEntity streamRequest = new InputStreamRequestEntity(fis, "image/jpeg");
    ClientResponse response = client.put(filesUrl + "/20789:media", streamRequest, options);

Finally, here's a code snippet that demonstrates how to delete an image file:

    Abdera abdera = new Abdera();
    AbderaClient client = new AbderaClient(abdera);
    RequestOptions options = createRequestOptions(client);

    ClientResponse response = client.delete(
        filesUrl + "/20776", options);

Remember that you can find the Atom client examples in this section in the
`com.liferay.docs.atomclient` project on
[Github](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/com.liferay.docs.atomclient).
To create a runnable JAR file of the Atom client including all of its
dependencies, run `gradle shadowJar`.
[Gradle Shadow](https://github.com/johnrengelman/shadow) provides an easy and
efficient way to package the Atom client's dependencies alongside of the Atom
client code in a runnable JAR file. To run the client from the project
directory, use one of the following commands:

- `java -jar build/libs/com.liferay.docs.atomclient-all.jar "FILES_POST"`
- `java -jar build/libs/com.liferay.docs.atomclient-all.jar "FILES_POST_DIRECT"`
- `java -jar build/libs/com.liferay.docs.atomclient-all.jar "FILES_PUT"`
- `java -jar build/libs/com.liferay.docs.atomclient-all.jar "FILES_PUT_DIRECT"`
- `java -jar build/libs/com.liferay.docs.atomclient-all.jar "FILES_DELETE"`

Before running the Atom client, remember to update any hard-coded values
(company ID, group ID, or entry ID) to match your portal's content. Then rebuild
your Atom client JAR file. Also, remember that this Atom client isn't a
real-world example but it does demonstrate how a real-world client could
interact with Liferay's Atom collections. (A simple improvement to the Atom
client would be to update it so that values like company ID, group ID, and entry
ID could be supplied as command line arguments instead of being hard-coded.)

The last Liferay Atom collection that supports adding, updating, and deleting entries via a client is the Documents and Media Folders Atom collection.

## Liferay's Documents and Media Folders Atom Collection

As with the other Liferay Atom collections that you've seen (Blogs, Web Content,
and Documents and Media Files), Liferay's Documents and Media Folders Atom
collection can be listed using the Atom protocol. For example, if you're running
Liferay locally, the URL for documents and media folders is

    http://localhost:8080/api/atom/folders

This chart demonstrates how to interact with the documents and media folders
collection:

![Atom Documents and Media Folders Summary](../../images/atom-folders-summary.png)

There is only one view for viewing Documents and Media folders:

*Folder entries* are displayed if either the `parentFolderId` or the
`repositoryId` URL parameter is provided. This view lists all the parent
folder's subfolders. Either the `parentFolderId` or the `repositoryId` parameter
must be provided. Use the `repositoryId` parameter to list subfolders of a root
folder; otherwise use the `parentFolderId` parameter.

Atom entries that represent folders do not contain content. Instead, they point
to the Files Atom collections using the `src` attribute. Although folders do not
contain content, the Abdera client requires that content be provided when
creating or updating folders. You can just pass an empty string to satisfy this
requirement.

Suppose you wanted to view all the subfolders belonging to the root folder of a
site with a `groupId` of `20146`. If you're running Liferay locally, you can do
so with curl like this:

    curl -v 'http://localhost:8080/api/atom/folders?repositoryId=20146' -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0'

Liferay returns an XML feed document like this:

    <?xml version='1.0' encoding='UTF-8'?>
    <feed xmlns="http://www.w3.org/2005/Atom">
      <author>
        <name>Liferay</name>
      </author>
      <link href="http://localhost:8080/api/atom/folders?repositoryId=20146" />
      <link href="http://localhost:8080/api/atom/folders?repositoryId=20146" rel="self" />
      <id>tag:liferay.com:folders:feed</id>
      <title type="text">Liferay javax.portlet.title.com_liferay_document_library_web_portlet_DLPortlet folders</title>
      <updated>2015-09-11T03:20:24.300Z</updated>
      <link href="http://localhost:8080/api/atom/folders?repositoryId=20146&amp;page=1" rel="first" />
      <link href="http://localhost:8080/api/atom/folders?repositoryId=20146&amp;page=1" rel="last" />
      <entry>
        <link href="http://localhost:8080/api/atom/folders/20816" />
        <author>
          <name>Test Test</name>
        </author>
        <id>tag:liferay.com:folders:entry:20816</id>
        <summary type="text">
        </summary>
        <title type="text">My Folder</title>
        <updated>2015-09-11T03:20:14.000Z</updated>
        <content type="application/xml" src="http://localhost:8080/api/atom/files?folderId=20816" />
      </entry>
    </feed>

In this example, there's only one subfolder (named *My Folder*) of the root
folder in the specified site and thus only one entry.

Creating, updating, and deleting Documents and Media folders via an Atom client
is similar to creating new blog posts, web content articles, or documents and
media files.

## Liferay's Users Atom Collection

Liferay's Users atom collection is read-only. There are several different
collection views:

- Company users (a.k.a. portal users)
- Organization users (a.k.a. organization members)
- Site users (a.k.a. site members)
- User group users (a.k.a. user group members)

Company users is the default view. If no URL parameter is supplied, this view is
used. To use another view, supply the appropriate URL parameter:
`organizationId`, `userGroupId`, or `groupId`. Pagination is also supported via
the`page` and `max` parameters.

![Atom Users Summary](../../images/atom-users-summary.png)

Excellent! Now you know how to work with Liferay's Atom collections.

