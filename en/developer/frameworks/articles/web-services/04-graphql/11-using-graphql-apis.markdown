---
header-id: using-graphql-apis
---

# Using GraphQL APIs

[TOC levels=1-4]

@product@'s GraphQL APIs are independent of clients and can be used with any
GraphQL client you want. The only usual requirements are setting up the
`Authentication` header using OAuth, Cookie, Basic, etc.

For JavaScript applications, we recommend using 
[Apollo Client](https://www.apollographql.com/) or
[graphql-request](https://github.com/prisma-labs/graphql-request), like this:

```javascript
const { GraphQLClient } = require('graphql-request');

const graphQLClient = new GraphQLClient('http://localhost:8080/o/graphql', {
    headers: {
        authorization: `Basic ${AUTHORIZATION_TOKEN}`
    }
});

const getDestinationsQuery = ` {
  destinations: contentSetContentSetElements(contentSetId: ${DESTINATION_CONTENTSET_ID}) {
    items {
        id
        title
    }
    page
    pageSize
    totalCount
  }
}`;

...

const response = await graphQLClient.request(getDestinationsQuery);
```

Here are several examples of JavaScript applications using GraphQL APIs:

* [Alexa skill using GraphQL APIs](https://github.com/dgomezg/liferay-frontend-samples/blob/master/lifeair-alexa-skill/) 
* [React application using Apollo Client for React](https://github.com/dgomezg/liferay-frontend-samples/tree/master/lifeair-react-showroom)
* [Vue application using Apollo Client for Vue](https://github.com/dgomezg/liferay-frontend-samples/tree/master/lifeair-vue-showroom)
