# API Vocabulary [](id=api-vocabulary)

When defining an API, the developer must decide how to expose the representation 
of its resources. This determines how easy it is to use the API, and how the API 
can evolve over time. Traditionally, the developer must choose between two 
approaches: 

-   **Contract Last:** The code is written first and features are exposed as web 
    or REST services. This approach is typically easier for developers, as they 
    only need to implement the business logic and then expose it via service 
    endpoints. @product@'s 
    [Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services) 
    is an example of this. However, this approach also couples the service 
    contract to the business logic and internal model. Any change to the 
    internal model, even attribute renaming, can break the exposed contract and 
    could even expose attribute types that are specific to the technology that 
    implements the services. The contract-last approach can therefore result in 
    [leaky abstractions](https://en.wikipedia.org/wiki/Leaky_abstraction). 

-   **Contract First:** The structure for client-server messages is written 
    before the code that implements the services. Such messages are defined 
    independently of the code. This therefore avoids the coupling of the 
    contract-last approach and leads to a design that supports API evolution 
    without breaking clients (as much as possible). The contract-first approach 
    is used for hypermedia web APIs in @product-ver@.

Therefore, the main effort that Liferay put into designing its hypermedia web 
APIs was to define how the APIs' resources are represented in client-server 
messages. In other words, Liferay defined the APIs' schemas first and named the 
attributes, resources, and operations to clearly define what they represent and 
how they should be used. 

<!-- 

Once schema is defined and schema.liferay.org is live, add a new section on the 
schema, and examples.

-->

## Related Topics

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services)
