---
header-id: api-vocabulary
---

# API Vocabulary

[TOC levels=1-4]

When defining an API, the developer must decide how to expose the representation
of its resources. This determines its ease of use and how it can evolve. 
Traditionally, there are two approaches: 

**Contract Last:** The code is written first and features are exposed as web or 
REST services. This approach is typically easier for developers, as they must 
only implement and expose the business logic. 
[Service Builder](/develop/tutorials/-/knowledge_base/7-2/service-builder-web-services) 
is an example of this. 

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

## Related Topics

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-2/service-builder-web-services)

[API Formats and Content Negotiation](/develop/tutorials/-/knowledge_base/7-2/api-formats-and-content-negotiation)
