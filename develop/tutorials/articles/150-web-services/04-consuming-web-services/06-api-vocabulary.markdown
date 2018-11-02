# API Vocabulary [](id=api-vocabulary)

+$$$

**Note:** Hypermedia REST APIs are currently only available as a limited private 
alpha for Liferay DXP 7.1 Fix Pack 3+. 

$$$

When defining an API, the developer must decide how to expose the representation
of its resources. This determines its ease of use and how it can evolve.
Traditionally, there are two approaches: 

-   **Contract Last:** The code is written first and features are exposed as web 
    or REST services. This approach is typically easier for developers, as they
    must only implement and expose the business logic. 
    [Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services)
    is an example of this. This approach, however, tightly couples the service
    contract to the business logic and internal model. Changes to the internal
    model, even attribute renaming, can break the exposed contract and could
    even expose attribute types specific to the technology that implements the
    services. The contract-last approach can therefore result in
    [leaky abstractions](https://en.wikipedia.org/wiki/Leaky_abstraction). 

-   **Contract First:** The structure for client-server messages is written 
    before the code that implements the services. Such messages are defined
    independent of the code. This avoids tight coupling and is less likely to
    break clients as APIs evolve. 

Liferay's hypermedia web APIs use the contract-first approach. Therefore, the 
API design effort focuses on defining how client-server messages represent the 
APIs' resources. In other words, the APIs' schemas are defined first and the 
attributes, resources, and operations are named to define clearly what they 
represent and how they should be used. 

<!-- 

Once schema is defined and schema.liferay.org is live, add a new section on the
schema with examples.

-->

## Related Topics [](id=related-topics)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services)

[Hypermedia Formats and Content Negotiation](/develop/tutorials/-/knowledge_base/7-1/hypermedia-formats-and-content-negotiation)
