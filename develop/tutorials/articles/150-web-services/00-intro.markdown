# Web Services [](id=web-services)

It's important for software on one machine to be able to communicate with 
software on other machines. To enable this, an app can expose an API with the 
features that remote components can invoke. Such remote components can include 
client apps that present information to a user, server apps that process data in 
B2B setting, or an IoT device that needs data to do its work. @product@ is no 
exception. It exposes a web API that lets developers consume its features from 
external apps. Developers can also extend that API or develop new services so 
external apps can use them. 

There are two different approaches for client apps to connect to @product@'s web 
APIs: 

-   **Hypermedia REST APIs:** Services that are designed and built in an 
    opinionated way, and thus decoupled from the internal model. These APIs 
    follow well-known industry standards and are intended evolve as much as 
    possible without breaking clients. This is the modern, preferred way to work 
    with web services in @product@. These services will progressively enable 
    headless operation of @product@. Hypermedia REST APIs are available starting 
    with Liferay DXP 7.1 Fix Pack 1, and Liferay Portal CE 7.1 GA2. 

-   **Plain Web/REST Services:** This is the old way to build and consume web 
    services, but is still supported in @product-ver@. For example, you can use 
    JAX-RS, JAX-WS, or Liferay's Service Builder to implement plain REST or SOAP 
    web services. 

The tutorials that follow show you how to consume and create web services in 
@product@, beginning with hypermedia REST APIs. 
