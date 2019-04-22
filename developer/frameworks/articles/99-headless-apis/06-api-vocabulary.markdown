# API Vocabulary [](id=api-vocabulary)

When defining an API, the developer must decide how to expose the representation
of its resources. This determines its ease of use and how it can evolve.
Traditionally, there are two approaches: 

-   **Contract Last:** The code is written first and features are exposed as web 
    or REST services. This approach is typically easier for developers, as they
    must only implement and expose the business logic. 
    [Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services)
    is an example of this.

-   **Contract First:** The structure for client-server messages is written 
    before the code that implements the services. Such messages are defined
    independent of the code. This avoids tight coupling and is less likely to
    break clients as APIs evolve. 

Liferay's Headless web APIs use a mixture of both approaches, an OpenAPI profile to do contract first approach that generates an API automatically, like Service Builder, allowing fast and easy development for developers.

With this mixed approach we get the best of both worlds, allowing a step of conscious API design and then simplifying developers experience by exposing only the business logic to implement.

Therefore, the API design effort focuses on defining how client-server messages represent the APIs' resources. In other words, the APIs' schemas are defined first and the attributes, resources, and operations are named to define clearly what they represent and how they should be used. 

<!-- 

Once schema is defined and schema.liferay.org is live, add a new section on the
schema with examples.

-->

## Related Topics [](id=related-topics)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services)

[API Formats and Content Negotiation]()