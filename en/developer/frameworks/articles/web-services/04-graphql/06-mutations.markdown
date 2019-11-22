---
header-id: mutations
---

# Mutations

[TOC levels=1-4]

The GraphQL spec differentiates between retrieve operations (`query`) and
create/update/delete operations (`mutations`). 

![Figure 1: The GraphQL Mutations list for Blog postings shows the possible operations.](../../../images/graphql-mutation.png)

To perform a mutation, do a `POST` request as you did with `query` operations,
using cURL, a REST client, or a GraphQL Client like Apollo. The only
difference is that create/update `mutations` require an Input type, a JSON
object to create or update the content.

A create `mutation` to insert a new blog posting looks like this:

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

Auto-complete also works as expected filling the `blogPosting` object. Here's
a cURL request to create the same entry:

```bash
curl 'http://localhost:8080/o/graphql' -H 'Content-Type: application/json' -H 'Accept: application/json' -H 'Authorization: Basic dGVzdEBsaWZlcmF5LmNvbTp0ZXN0' --data-binary '{"query":"mutation {createSiteBlogPosting(blogPosting: {headline: \"New GraphQL APIs!\" articleBody: \"WoW! This is cool!\"} siteKey: \"guest\") {id headline}}","variables":{}}'
```

