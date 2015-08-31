# Interacting with Liferay's Atom Collections

Liferay's asset framework allows many different kinds of content to be created
and published. Web content articles, blog entries, wiki articles, and documents
are a few of the asset types that Liferay supports out of the box. Developers
can also create custom asset types. For more information about Liferay's asset
framework, please see the [Asset Framework]() tutorial. Liferay also supports
web feeds in several different formats: Atom 1.0, RSS 2.0, and RSS 1.0. To learn
how to manage RSS feeds in Liferay, please see the
[Managing RSS Feeds in Liferay]() tutorial.

Liferay also includes a bundled Atom server and provides Atom collections for
several portal entities including web content articles, blog entries, documents,
document folders, and users. Liferay's Atom server implementation is based on
[Apache Abdera](). Abdera is intended to be a functionally-complete,
high-performance implementation of the IETF Atom Syndication Format
([RFC 4287](http://www.ietf.org/rfc/rfc4287.txt)) and Atom Publishing Protocol ([RFC 5023](http://www.ietf.org/rfc/rfc5023.txt)) specifications. 

## Atom Service Document

Liferay's Atom server allows third-party software to be used for reading or
publishing content. The first step that must be taken by any third-party
software that's intended to be used for authoring or publishing content is to
discover the locations and capabilities of Liferay's available Atom collections.
Service documents are designed to support this discovery process. Liferay's
service documents is available at the following address:

    http://[server]:[port]/api/atom

E.g., if you're running Liferay locally, navigate to this address to download
Liferay's Atom service document:

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

There's an Atom collection of portal users but it's read-only and is not listed
in the `atom` XML document:

- Users Atom collection

## Atom URL Conventions

The URLs for Liferay's Atom collections and collection entries follow a simple
convention. Each URL starts with the same base:

    http://[server]:[port]/api/atom

Each of Liferay's Atom collections has a unique which become part of the URL:

    http://[server]:[port]/api/atom/[collection-name]

E.g., if you're running Liferay locally, the URL for blogs is

    http://localhost:8080/api/atom/blogs

Each Atom collection entry has its own ID which is unique in its collection
context. This ID is appended to the URL:

    http://[server]:[port]/api/atom/[collection-name]/[entry-id]

The entry URL is used for all of the entry operations: view, update, and delete.

Some collections are media-oriented and entries contain enclosed media content
(e.g., images). To access media content, append `:media` after the entry ID:

    http://[server]:[port]/api/atom/[collection-name]/[entry-id]:media

The `atom` XML document is publicly available at this URL:

    http://[server]:[port]/api/atom

All of the other URLs described in this section require authentication.

## Authentication

If you try to navigate to the URL of an Atom collection or entry without
authenticating, your request will fail with the status code of *500: Internal
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
authenticate, you need to include the appropriate header in your HTTP request.
There are various tools that allow you to construct and send HTTP requests with
custom headers. For example, you could use the
[cURL](http://curl.haxx.se) command line tool or the Firefox
[RESTClient](https://addons.mozilla.org/en-Us/firefox/addon/restclient) plugin.

To use HTTP basic authentication, you first need to follow these steps:

1. Construct a string of the form `[username]:[password]`.
2. Convert this string to use base 64 encoding.
3. Finally, you need to construct a request header of the form `Authorization:
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
Thus, HTTP basic authentication is typically used alongside of SSL.

Liferay's Atom collections not only allow entries to be read, but also to be
published, edited, or deleted. Let's learn how to interact with Liferay's
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

E.g., if you're running Liferay locally, you can retrieve the first five blog
entries from a site with a site ID of `20146` by using curl like this:

    curl -v 'http://localhost:8080/api/atom/blogs?groupId=20146&page=1&max=5' -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0'

Here is an example of the returned XML (in this example, there is only one blog
entry):

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

This XML document can be read by any Atom feed reader and the blog entry or
entries will be displayed correctly.
