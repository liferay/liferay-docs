---
header-id: rest-builder
---

# REST Builder

[TOC levels=1-4]

| **Note:** This documentation is in beta. Stay tuned for more to come! 

@product@'s headless REST APIs follow the 
[OpenAPI](https://swagger.io/docs/specification/about/) 
specification and let your apps consume RESTful web services. These APIs are 
developed using a mixture of the Contract First and Contract Last development 
approaches. This presents a best-of-both-worlds approach to API development. For 
more detailed information, see 
[Headless REST APIs](/docs/7-2/frameworks/-/knowledge_base/f/headless-rest-apis). 

Here, you'll learn how to use Liferay's 
[REST Builder tool](https://github.com/liferay/liferay-portal/tree/master/modules/util/portal-tools-rest-builder) 
to create headless REST APIs for your own apps. REST Builder is an API generator 
that consumes OpenAPI profiles and generates the API scaffolding: JAX-RS 
endpoints, parsing, XML generation, and advanced features like filtering or 
multipart (binary file) support. The developer only has to fill in the resource 
implementations, calling @product@'s remote services. 

Read on to learn how to generate REST services with REST Builder! 
