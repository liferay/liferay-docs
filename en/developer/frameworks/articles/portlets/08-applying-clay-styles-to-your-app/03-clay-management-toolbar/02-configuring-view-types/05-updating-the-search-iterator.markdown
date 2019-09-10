---
header-id: updating-the-search-iterator
---

# Updating the Search Iterator

[TOC levels=1-4]

Once the view type's display styles are defined, you must update the search 
iterator to show the selected view type. Follow these steps:

1.  If your management toolbar only has one view type, you can set the 
    `displayStyle` attribute to the style you defined, otherwise follow the 
    pattern below:

    ```markup
    <liferay-ui:search-iterator
        displayStyle="<%= displayStyle %>"
        markupView="lexicon"
        searchContainer="<%= searchContainer %>"
    />
    ```

    The `displayStyle`'s value is set to the [current view type](/docs/7-2/frameworks/-/knowledge_base/f/implementing-the-view-types).

2.  Save the changes. 

Great! You've updated your search iterator to use your display styles. 

## Related Topics

- [Configuring the Clay Management Toolbar Taglib](/docs/7-2/reference/-/knowledge_base/r/clay-management-toolbar)
- [Filtering and Sorting Items with the Management Toolbar](/docs/7-2/frameworks/-/knowledge_base/f/filtering-and-sorting-items-with-the-management-toolbar)
