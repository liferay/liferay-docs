---
header-id: get-started-discover-the-api
---

# Get Started: Discover the API

[TOC levels=1-4]

To begin consuming the GraphQL APIs, you must first know where they are, what
operations you can invoke, and how to invoke them. 

Because @product@'s GraphQL APIs leverage the 
[official specification](https://graphql.github.io/graphql-spec/June2018/), you don't need
a service catalog. You only need to know the URL from which to discover the rest
of the API. 

@product@'s GraphQL APIs are available here: 

    http://[host]:[port]/o/graphql
 
For example, if you're running @product@ locally on port `8080`, the URL 
for discovering the GraphQL API is 

    http://localhost:8080/o/graphql

To inspect the GraphQL endpoint, use a GraphQL client, such as
[Altair](https://chrome.google.com/webstore/detail/altair-graphql-client/flnheeellpciglgpaodhkhmapeljopja?hl=en) 
(a Chrome extension) or [GraphiQL](https://github.com/graphql/graphiql). 

![Figure 1: GraphQL APIs can be browsed in Altair.](../../../images/graphql-altair.png)

You don't have to be authenticated to inspect the live documentation, but you
must to be able to make requests. There are several ways of authenticating in
GraphQL APIs (explained [here](/docs/7-2/frameworks/-/knowledge_base/f/authenticated-requests)) 
but the simplest way to test APIs locally is to use Basic Authentication,
setting an `Authorization` header in Altair (first icon on the left). Remember
that Basic Auth is a BASE64 transformation of `user`:`password`. This means it's
insecure, and should never be used in production. 

Most tools that introspect the GraphQL schema can autocomplete your query or
fill all the fields in for you. 

For a list of tools such as client generators,
validators, and parsers supporting GraphQL, see [Awesome GraphQL](https://github.com/chentsulin/awesome-graphql). 
Leveraging GraphQL provides standards support, extensive automatic
documentation, and industry-wide conventions. 

## Unique endpoint and versioning

In contrast with the REST APIs, where endpoints are deployed by suite
(headless-delivery, headless-admin-user...), GraphQL APIs are deployed under
the same endpoint (/o/graphql). That way we can easily add relationships between
entities to leverage GraphQL's powerful request characteristics.

@product@'s GraphQL APIs also expose the latest published version of all
entities available. If several versions of the same entity are deployed, only
the latest one is exposed under the `/o/graphql` endpoint (REST APIs use different
endpoints for different versions). This strategy follows GraphQL standards to
avoid breaking versions by marking deprecated fields and always adding
properties to an entity.


