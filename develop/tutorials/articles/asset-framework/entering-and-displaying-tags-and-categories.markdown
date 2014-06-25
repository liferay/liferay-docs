# Entering and Displaying Tags and Categories 

Once you've let the asset framework know about the tags and categories you've 
associated with a given asset, you might think your work is done. Vacation time, 
right? Not quite yet. Content authors still can't specify the tags and 
categories for their content in the UI. Don't fret! 

Liferay provides a set of JSP tags you can use to make this task very easy. Go 
ahead and get started! 

## Implementing Tags and Categories in the UI 

You can put the following Liferay UI tags in your forms to create content that 
can be associated with new or existing tags or predefined categories: 

    <label>Tags</label>
    <liferay-ui:asset-tags-selector
        className="<%= entry.getClass().getName() %>"
        classPK="<%= entry.getPrimaryKey() %>"
    />

    <label>Categories</label>
        <liferay-ui:asset-categories-selector
            className="<%= entry.getClass().getName() %>"
            classPK="<%= entry.getPrimaryKey() %>"
    />

These two taglibs create appropriate form controls that allow the user to 
search for a tag, create a new one, or select an existing category. 

---

 ![Tip](../../images/tip-pen-paper.png) **Tip:** If you're using Liferay's
 AlloyUI Form taglibs, creating fields to enter tags and categories is even
 simpler. You just use `<aui:input name="tags" type="assetTags" />` and
 `<aui:input name="categories" type="assetCategories" />`, respectively. 

---

Once the tags and categories have been entered, you'll want to show them along
with the content of the asset. Here's how to display the tags and categories: 

    <label>Tags</label>
    <liferay-ui:asset-tags-summary
        className="<%= entry.getClass().getName() %>"
        classPK="<%= entry.getPrimaryKey() %>"
    />

    <label>Categories</label>
    <liferay-ui:asset-categories-summary
        className="<%= entry.getClass().getName() %>"
        classPK="<%= entry.getPrimaryKey() %>"
    />

You can also support navigation of tags and categories within your portlet by 
specifying a `portletURL` parameter in the JSP tags. Each tag that uses this 
parameter will be a link containing the `portletURL` *and* `tag` or `categoryId` 
parameter value, respectively. To implement this, you need to implement the 
look-up functionality in your portlet code. Do this by reading the values of 
those two parameters and using `AssetEntryService` to query the database for 
entries based on the specified tag or category. 

<!-- An example of this would be really nice. -Rich -->

Great! Now you know how to make tags and categories available to content authors 
in your portlets. 

## Next Steps 
<!--
[Rating Assets](http://www.liferay.com/)
-->

[Publishing Assets](http://www.liferay.com/)
