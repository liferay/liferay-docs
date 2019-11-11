---
header-id: web-services
---

# Web Services

[TOC levels=1-4]

It's important for apps on different machines to communicate. To enable this, an
app can expose APIs so remote components (other apps or devices) can access the
app's features. For example, one service could have a client app presenting
information to users, a server app processing data in B2B setting, and an IoT
device requesting data to do its work. Exposing web APIs lets external
applications or devices communicate with yours. 

Because @product@ contains so many apps and features, it's prudent for Liferay
to let developers access those apps and features from external apps and devices
by exposing their APIs. Additionally, Liferay's development platform makes it
easy to extend them and create new ones. 

There are three different approaches for clients to connect to @product@'s web 
APIs: 

**Headless REST APIs:** You can consume RESTful web services independent of 
@product@'s front end (hence *headless*). These APIs conform to the 
[OpenAPI](https://swagger.io/docs/specification/about/) 
specification. This is the modern, preferred way to work with web services in 
@product@. 

**GraphQL:** All the power of doing multiple queries in a unique request
following [GraphQL specification](https://graphql.github.io/graphql-spec/June2018/).

**Plain Web/REST Services:** This is the old way to build and consume web 
services in @product@, but is still supported. 

You can also create your own Headless REST and GraphQL APIs through the **REST builder**.

