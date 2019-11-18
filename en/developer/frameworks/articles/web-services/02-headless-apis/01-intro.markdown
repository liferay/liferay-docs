---
header-id: headless-rest-apis
---

# Headless REST APIs

[TOC levels=1-4]

@product@'s headless REST APIs follow the 
[OpenAPI](https://swagger.io/docs/specification/about/) 
specification and let your apps consume RESTful web services. What's more, you 
can consume these APIs without being tied to @product@'s UI (hence the term 
*headless*). This gives you a great deal of freedom when designing and 
developing your apps. 

The articles in this section show you how to navigate and consume @product@'s 
headless REST APIs. But first, you'll learn the design approach for these APIs. 

## OpenAPI

[OpenAPI](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.2.md)
(originally called Swagger) is a Linux Foundation project specification that
defines machine-readable files that describe REST APIs and how to consume them. 

OpenAPI has become a widely adopted standard for defining REST APIs and
is supported by major players in the API ecosystem such as Google, Amazon, and
Microsoft. As a spec, it is language-agnostic, and many libraries implement it
or provide code generation to help validate, consume, or produce APIs.

@product@ leverages existing knowledge of OpenAPI to define, create and consume
REST APIs.

## API Vocabulary

When defining an API, the developer must decide how to expose the representation
of its resources. This determines its ease of use and how it can evolve. 
Traditionally, there are two approaches: 

**Contract Last:** The code is written first and features are exposed as web or 
REST services. This approach is typically easier for developers, as they must 
only implement and expose the business logic. Service Builder is an example of 
this. 

<!--Add link back for 'Service Builder' once service-builder-web-services article is available-->

**Contract First:** The structure for client-server messages is written before 
the code that implements the services. Such messages are defined independent of 
the code. This avoids tight coupling and is less likely to break clients as APIs 
evolve. 

@product@'s headless web APIs use a mixture of both approaches. An OpenAPI 
profile uses a contract first approach by defining the paths and schemas before 
writing any code. It then generates an API automatically based on that profile, 
using the contract-last characteristic of code generation (like Service 
Builder). This allows fast development for developers. 

This mixed approach delivers the best of both worlds, allowing a step of 
conscious API design and then simplifying the developer experience by exposing 
only the business logic to implement. 

When writing the OpenAPI profile, the main focus should be on defining how
client-server messages represent the APIs' resources. In other words, the APIs'
schemas are defined first and the attributes, resources, and operations are
named to clearly define what they represent and how they should be used. 
