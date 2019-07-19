---
header-id: filtering-and-searching-uad-marked-entities
---

# Filtering and Searching UAD-Marked Entities

[TOC levels=1-4]

When you 

![Figure 1: The UAD UI has filtering and searching elements you can leverage in
custom entities.](../../images/uad-filter-search.png)

To add filtering and searching for your custom entities, implement three
methods in the `UADDisplay` class (in your application's `-uad` module):

## Filtering

The `isSiteScoped` method returns a boolean based on whether or not the
entities can be associated with a particular site: return `false` if not, and
`true` if the entities are scoped to a site. This is used to determine which
filter they will be associated with ("instance", "personal-site", or
"regular-sites").

```java
@Override
public boolean isSiteScoped() {

    return false;
}
```

## Search

Implement the `search` and `searchCount` methods to enable search in the UAD
interface:

<!--Is this realistic dummy code for third party devs? -->
<!-- Straight out of DummyUADDisplay.java -->

1.  The `search` method must return a `List` of entities associated with the
    `userId`:

    ```java
    @Override
    public List<T> search(
        long userId, long[] groupIds, String keywords, String orderByField,
        String orderByType, int start, int end) {

        FooService<T> fooService = getFooService();

        return dummyService.getEntities(userId);
    }
    ```

2.  The `searchCount` method  returns a long of the result `List`'s `size`
    method.

    ```java
    @Override
    public long searchCount(long userId, long[] groupIds, String keywords) {
        List<T> results = search(
            userId, groupIds, keywords, null, null, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS);

        return results.size();
    }
    ```

