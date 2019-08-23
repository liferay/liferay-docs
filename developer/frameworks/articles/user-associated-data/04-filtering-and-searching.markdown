---
header-id: filtering-and-searching-uad-marked-entities
---

# Filtering and Searching UAD-Marked Entities

[TOC levels=1-4]

In the data erasure UI, it's important that administrators can find what they're
looking for. The native @product@ entities support filtering and search, and
when you follow the steps here, your entities will, too.

To add filtering and searching for your custom entities, implement three
methods in the `UADDisplay` class (in your application's `-uad` module):

## Filtering

The `isSiteScoped` method returns a boolean denoting if the
entities can be associated with a particular Site: `false` if not, and
`true` if the entities are scoped to a Site. This determines which
filter they are associated with ("instance", "personal-site", or
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

1.  The `search` method must return a `List` of entities associated with the
    `userId`. For example, you could search the database for records
    associated with the `userId`:

    ```java
    @Override
    public List<T> search(
        long userId, long[] groupIds, String keywords, String orderByField,
        String orderByType, int start, int end) {

        FooService<T> fooService = getFooService();

        return dummyService.getEntities(userId);
    }
    ```

    But if you've gone through the trouble of indexing your model entity's
    fields in a search engine, it's more likely you'll want to do the initial
    search, querying for documents matching the `userId`, at the search engine
    level. After the search, retrieve the matching entities from the database.

    ```java
    @Override
    public List<T> search(
        long userId, long[] groupIds, String keywords, String orderByField,
        String orderByType, int start, int end) {

        SearchContext searchContext = new SearchContext();

        searchContext.setStart(start);
        searchContext.setEnd(end);
        searchContext.setGroupIds(groupIds);
        searchContext.setKeywords(keywords);

        BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(
            searchContext);

        booleanQuery.addExactTerm("userId", userId);

        BooleanClause booleanClause = BooleanClauseFactoryUtil.create(
            booleanQuery, BooleanClauseOccur.MUST.getName());

        searchContext.setBooleanClauses(new BooleanClause[] {booleanClause});

        Indexer indexer = IndexerRegistryUtil.getIndexer(FooEntry.class);

        Hits hits = indexer.search(searchContext);

        List<FooEntry> fooEntries = new ArrayList<FooEntry>();

        for (int i = 0; i < hits.getDocs().length; i++) {
                Document doc = hits.doc(i);

                long entryId = GetterUtil
                .getLong(doc.get(Field.ENTRY_CLASS_PK));

                Entry entry = null;

                try {
                        entry = _fooEntryLocalService.getFooEntry(fooEntryId);
                } catch (PortalException pe) {
                        _log.error(pe.getLocalizedMessage());
                } catch (SystemException se) {
                        _log.error(se.getLocalizedMessage());
                }

                fooEntries.add(fooEntry);
        }

        return fooEntries;
    }
    ```

    It largely boils down to instantiating and populating the search context,
    which gets passed to the `indexer.search` call to retrieve the `Hits`.
    Subsequently, populate the `List` by iterating through the `Hits`, using
    each one's `ENTRY_CLASS_PK` field as the primary key of the entity in the
    call to the entity's getter. The `BooleanClause` construction and inclusion
    in the search context ensures that all the results returned correspond to
    the `userId` that's passed to this method.

2.  The `searchCount` method  returns a long of the result `List`'s `size`
    method. You could just invoke the class's `search` method, then call the
    `List` object's `size` method.

    ```java
    @Override
    public long searchCount(long userId, long[] groupIds, String keywords) {
        List<T> results = search(
            userId, groupIds, keywords, null, null, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS);

        return results.size();
    }
    ```

    But, again, if the model entity is being indexed in a search engine, you can
    use it to get a count without ever hitting the database. Using the `Hits`
    object returned from a search (see the code from step 1, but don't include
    `start` and `end` parameters in the `SearchContext`), call
    `hits.getLegnth()` and you get the count, as an `int`.

Now administrators responsible for complying with GDPR or other data erasure
concerns can search and filter your entity from the @product@ UAD interface.
