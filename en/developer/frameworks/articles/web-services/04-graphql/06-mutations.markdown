---
header-id: mutations
---

GraphQL spec differenciates between retrieve operations (`query`) and create/update/delete operations, that are defined as `mutations`. 

![Figure 1: GraphQL Mutations definition](../../../images/graphql-mutation.png)

To perform a mutation you just have to do a `POST` request as you did with `query` operations, either with cURL, a REST client or a GraphQL Client like Apollo. The only difference is that create/update `mutations` require an Input type, a JSON object to create or update the content.

A create `mutation` to insert a new blog posting would be like this:

```
mutation {
  createSiteBlogPosting(
    blogPosting: {
      headline: "New GraphQL APIs!"
      articleBody: "WoW! This is cool!"
    }
    siteKey: "guest"
  ) {
    id
    headline
  }
}
```

Auto-complete also works as expected filling the blogPosting object. The cURL request to create the new entry is:

```
curl 'http://localhost:8080/o/graphql' -H 'Content-Type: application/json' -H 'Accept: application/json' -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' --data-binary '{"query":"mutation {createSiteBlogPosting(blogPosting: {headline: \"New GraphQL APIs!\" articleBody: \"WoW! This is cool!\"} siteKey: \"guest\") {id headline}}","variables":{}}'
```

## Related Topics
