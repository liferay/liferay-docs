# Why Hypermedia REST APIs? [](id=why-hypermedia-rest-apis)

[Hypermedia](https://en.wikipedia.org/wiki/Hypermedia) 
is hypertext (the H in HTML) extended to other media, like images, audio, video, 
or links. This isn't a new concept. When a browser requests a web page, it 
retrieves an HTML document containing the page's content, including links to 
resources like other web pages and files. The browser knows how to interpret 
such links and renders the page and its content. A web page is therefore a 
hypermedia document that a browser knows how to parse. 

Liferay's hypermedia REST APIs expose resources so consumers can identify and
navigate relationships between resources via links. Consumers can also use the
links to discover the operations available on each resource. Again, this isn't
a new concept. 
[Hypermedia as the Engine of Application State (HATEOAS)](https://en.wikipedia.org/wiki/HATEOAS) 
specifies that an API uses hypermedia to provide the information that clients 
need to use the API. 

## Use Cases for Hypermedia REST APIs [](id=use-cases-for-hypermedia-rest-apis)

Hypermedia REST APIs are being built to cover all of @product@'s APIs. This
enables headless use of @product@: developers can create clients with their
usual tools and programming languages, rather than (or in addition to) native
Liferay apps. <!-- Link to "Going Headless with Hypermedia REST APIs" article,
once it exists. -->

There are two kinds of @product@ hypermedia REST APIs:

1.  **Management API:** Clients can administrative functions in the portal. This
    is not the typical use case: normally, you'd use the native UI. 

2.  **Delivery API:** Clients can access and manipulate portal content. This is
    the typical use case. Here are some specific examples: 

    -   Retrieve portal content (e.g., blog posts, web content, media files, 
        etc.) and present it according to the client app's needs. 
    -   Access 
        [Documents and Media](/discover/portal/-/knowledge_base/7-1/managing-documents-and-media) 
        assets from a remote client, using the portal as a content repository. 
    -   Retrieve and modify portal content metadata, such as asset 
        [tags and categories](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags-and-categories). 
    -   Access forms created with 
        [Liferay Forms](/discover/portal/-/knowledge_base/7-1/forms). 
    -   And much more! API consumers can discover API features by navigating 
        hypermedia links in the responses. You'll see examples of this in the 
        [Consuming Web Services tutorials](/develop/tutorials/-/knowledge_base/7-1/consuming-web-services). 

## Related Topics [](id=related-topics)

[Consuming Web Services](/develop/tutorials/-/knowledge_base/7-1/consuming-web-services)
