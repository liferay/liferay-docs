---
header-id: get-started-discover-the-api
---

# Get Started: Discover the API

[TOC levels=1-4]

To begin consuming the GraphQL APIs, you must first know where they are, what operations you can invoke, and how to invoke them. 

Because @product@'s GraphQL APIs leverage the [official specification](https://graphql.github.io/graphql-spec/June2018/), you don't need a service catalog. You only need to know the URL from which to discover the rest of the API. 

@product@'s GraphQL APIs are available in an unique endpoint at 

    http://[host]:[port]/o/graphql
     
For example, if you're running @product@ locally on port `8080`, the URL 
for discovering the GraphQL API is: 

    http://localhost:8080/o/graphql

To inspect the GraphQL endpoint, we recommend using [Altair](https://chrome.google.com/webstore/detail/altair-graphql-client/flnheeellpciglgpaodhkhmapeljopja?hl=en), a Chrome extension. But any other [GraphiQL](https://github.com/graphql/graphiql) would also work fine.

![Figure 1: GraphQL APIs loaded in Altair](../../../images/graphql-altair.png)

You don't have to be logged to inspect the live documentation but you do to be able to make requests. There are several ways of authenticating in GraphQL APIs (explained [here](./04-authenticated-requests)) but a simple way of testing the APIs would be using Basic Authentication setting an `Authorization` header in Altair (first icon on the left). Remember that Basic Auth is a BASE64 transformation of `user`:`password`.

And, as other tools that introspect the GraphQL schema, you can autocomplete your query or allow Altair/GraphiQL to fill it for you with all the fields.

There are also many other tools that support GraphQL, such as client generators, validators, parsers, and more. See 
[Awesome GraphQL](https://github.com/chentsulin/awesome-graphql) for a comprehensive list. Leveraging GraphQL provides standards support, extensive automatic documentation and industry-wide conventions. 

## Unique endpoint and versioning

In contrast with the REST APIs, where endpoints are deployed by suite (headless-delivery, headless-admin-user...), all GraphQL APIs are deployed under the same endpoint (/o/graphql). That way we can easily add relationships between entities to leverage GraphQL powerful request characteristics.

@product@'s GraphQL APIs also expose the latest published version of all entities available. If several versions of the same entity are deployed, only the latest one is exposed under the /o/graphql endpoint (REST APIs use different endpoints for different versions). This strategy follows GraphQL standards of trying to avoid breaking versions, by marking deprecated fields and always trying to add properties to an entity.

## Related Topics

