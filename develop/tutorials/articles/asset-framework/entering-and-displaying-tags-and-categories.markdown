### Entering and Displaying Tags and Categories [](id=entering-and-displaying-tags-and-categories-liferay-portal-6-2-dev-guide-en)

In the last section, we let the asset framework know about the tags and
categories that we associated with a given asset; but how does a content author
specify the tags and categories? 

Liferay provides a set of JSP tags you can use to make this task very easy. You
can put the following Liferay UI tags in your forms to create content that can
be associated with new or existing tags or predefined categories:

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
search for a tag or create a new one or select an existing category. 

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

In both JSP tags, you can also specify a `portletURL` parameter; each tag that
uses it will be a link containing the `portletURL` *and* `tag` or `categoryId`
parameter value, respectively. This supports tags navigation and categories
navigation within your portlet. You'll need to implement the look-up
functionality in your portlet code; do this by reading the values of those two
parameters and using the `AssetEntryService` to query the database for entries
based on the specified tag or category. 

<!-- An example of this would be really nice. -Rich -->

Great job! You'll have no problem associating tags and categories with your
assets. Before we go further with our example, let's take a look at more JSP
tags you can use to leverage the asset framework's features. 
