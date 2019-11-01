---
header-id: querying-for-guestbook-entry-documents
---

# Querying for Guestbook Entry Documents

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Enabling Search and Indexing for Entries</p><p>Step 3 of 5</p>
</div>

The code is in place for indexing Guestbook entries to the search engine. Next
code the behavior necessary for querying the indexed documents.

Implement two classes:

1.  `GuestbookEntryKeywordQueryContributor` contributes clauses to the ongoing search
    query.

2.  `GuestbookEntryModelPreFilterContributor` controls how search results are filtered
    before they're returned from the search engine.

## Implementing `KeywordQueryContributor`

Create `GuestbookEntryKeywordQueryContributor` and populate it with this:

```java
@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.docs.guestbook.model.GuestbookEntry",
        service = KeywordQueryContributor.class
)
public class GuestbookEntryKeywordQueryContributor implements KeywordQueryContributor {

    @Override
    public void contribute(
        String keywords, BooleanQuery booleanQuery,
        KeywordQueryContributorHelper keywordQueryContributorHelper) {

        SearchContext searchContext =
    keywordQueryContributorHelper.getSearchContext();

        queryHelper.addSearchLocalizedTerm(
    booleanQuery, searchContext, Field.TITLE, false);
        queryHelper.addSearchLocalizedTerm(
    booleanQuery, searchContext, Field.CONTENT, false);
        queryHelper.addSearchLocalizedTerm(
    booleanQuery, searchContext, "entryEmail", false);
    }

    @Reference
    protected QueryHelper queryHelper;

}
```

Adding the localized search terms is important. For all localized
`GuestbookEntry` fields in the index, retrieve the localized value from the
search engine.

Use Ctrl-Shift-O to add these imports, and then save the file: 

- `com.liferay.portal.kernel.search.BooleanQuery`
- `com.liferay.portal.kernel.search.Field`

Now that the query code is in place, you can define how returned
`GuestbookEntry` documents are summarized. 
