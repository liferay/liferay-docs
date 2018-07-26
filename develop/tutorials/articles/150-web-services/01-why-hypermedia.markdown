# Why Hypermedia REST APIs? [](id=why-hypermedia-rest-apis)

Before explaining how to use and create hypermedia REST APIs, you should first 
understand what *hypermedia* is. In short, hypermedia is the ability to 
represent information and link or refer to related resources. This isn't a new 
concept. When a browser requests a web page it retrieves an HTML document that 
contains the page's contents. Pages typically contain links to other resources 
like other web pages and files. The browser knows how to interpret such links, 
rendering the page and its content. A web page is therefore a hypermedia 
document that a browser knows how to parse. 

Liferay's hypermedia REST APIs are based on hypermedia. They expose resources in 
such a way that consumers can identify and navigate relationships between 
resources via links. Consumers can also use the links to discover the operations 
available on each resource. Again, this isn’t a new concept. 
[Hypermedia as the Engine of Application State (HATEOAS)](https://en.wikipedia.org/wiki/HATEOAS) 
specifies that an API uses hypermedia to provide the information that clients 
need to use the API. 

+$$$

**Note:** Hypermedia REST APIs are available starting with Liferay DXP 7.1 Fix 
Pack 1, and Liferay Portal CE 7.1 GA2. To work with web services in earlier 
releases, you must do so via 
[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services), 
JAX-RS, or JAX-WS. 

$$$

Hypermedia REST APIs will progressively offer all the features that @product@ 
has in its built-in user interface, which lets developers connect any other kind 
of app or external system to the core portal features. In other words, 
hypermedia REST APIs let developers create client apps that can invoke portal 
features without the need to use the standard mechanism of developing @product@ 
apps. This lets developers choose the tools and programming language that best 
fits their needs for the task at hand. This also enables headless use of 
@product@, where users access portal features entirely through a custom app that 
leverages the hypermedia REST APIs. 
<!-- Link to "Going Headless with Hypermedia REST APIs" article, once it exists. -->

@product@'s hypermedia REST APIs can be broken down into two main parts: 

1.  **Management API:** Lets clients perform administrative functions in the 
    portal. Note, however, that you'll typically want to use the portal UI for 
    performing administrative tasks. 

2.  **Delivery API:** Lets clients access and manipulate portal content. This is 
    the typical use case for @product@'s hypermedia REST APIs. Here are some 
    specific examples: 

    -   Retrieve content from the portal (e.g., blog posts, web content, media 
        files, etc.) and present it according to the client app's needs. 
    -   Access 
        [Documents and Media](/discover/portal/-/knowledge_base/7-1/managing-documents-and-media) 
        assets from a remote client, effectively using the portal as a content 
        repository. 
    -   Retrieve and modify portal content metadata, such as asset 
        [tags and categories](/discover/portal/-/knowledge_base/7-1/organizing-content-with-tags-and-categories). 
    -   Access forms created with 
        [Liferay Forms](/discover/portal/-/knowledge_base/7-1/forms). 
    -   And much more! API consumers can discover API features by navigating 
        hypermedia links in the responses. You'll see examples of this in the 
        tutorials that follow. 

## Related Topics

[Consuming Web Services](/develop/tutorials/-/knowledge_base/7-1/consuming-web-services)
