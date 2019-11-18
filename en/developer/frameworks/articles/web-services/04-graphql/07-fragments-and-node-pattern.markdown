---
header-id: fragments-and-node-pattern
---

@product@'s GraphQL APIs also supports [GraphQL fragments](https://graphql.org/learn/queries/#fragments), reusable sets of fields that are needed in different requests. A special type of fragments are [inline fragments](https://graphql.org/learn/queries/#inline-fragments), which allows us to access the underlying concrete type when querying generic types or interfaces.

We'll use inline fragments to query objects that inherit from a common interface, like the kind of objects returned from a ContentSet. ContentSets are a 7.2 feature that allows defining lists of assets that comply with a set of rules, a segment or are manually selected. ContentSets can return any type of assets and, as such, they are a perfect fit for inline fragments.

An example of GraphQL querying ContentSets is the following:

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

There is a special query that leverages the power of inline fragments, `graphQLNode`. This query accepts a dataType and an id and returns any kind of entity that has a query of the type `{dataType}` and receives an `id` as a parameter. Inline fragments allow us to specify the fields we want to return in this special case:

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

There is also another use of `graphQLNode`, as a field in entities that contain a `contentType` and `id` properties. Those entities have a generated property called GraphQLNode that can return any type, queried by using inline fragments. A common use case is returning an asset linked as relatedContents (asset links).

## Related Topics
