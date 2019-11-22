---
header-id: fragments-and-node-pattern
---

# Fragments and Node Patterns

[TOC levels=1-4]

@product@'s GraphQL APIs also supports [GraphQL
fragments](https://graphql.org/learn/queries/#fragments), reusable sets of
fields that are needed in different requests. A special type of fragments are
[inline fragments](https://graphql.org/learn/queries/#inline-fragments), which
access the underlying concrete type when querying generic types or interfaces.

You'll use inline fragments to query objects that inherit from a common
interface, like the kind of objects returned from a `ContentSet`. `ContentSet`s
allow defining lists of assets that comply with a set of rules, a segment, or are
manually selected. `ContentSet`s can return any type of asset. This makes them
a perfect fit for inline fragments.

Here's an example of GraphQL querying `ContentSet`s: 

```
query {
  contentSetContentSetElements(contentSetId: 42345) {
    items {
      id
      title
      content {
        ... on BlogPosting {
          headline
        }
        ... on StructuredContent {
          relatedContents {
            id
            title
          }
        }
      }
    }
    page
    pageSize
    totalCount
  }
}
```

This query returns a set of objects, each of a different type.

## Node pattern

`graphQLNode` is a special query that leverages the power of inline fragments.
This query accepts a `dataType` and an ID and returns any kind of entity that
has a query of the type `{dataType}` and receives an `id` as a parameter. Inline
fragments can specify the fields you want to return in this special case:

```
query{
  graphQLNode(dataType: ______, id: ______){
    id
    ... on BlogPosting {
      headline
    }
  }
}
``` 

You can also use `graphQLNode` as a field in entities that contain `contentType`
and `id` properties. Those entities have a generated property called `GraphQLNode`
that can return any type, queried by using inline fragments. A common use case
is returning an asset linked as `relatedContents` (asset links).

