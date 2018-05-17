# Implementing Asset Categorization and Tagging [](id=implementing-asset-categorization-and-tagging)

In this tutorial, you'll enable tags and categories entities in the UI through
a set of JSP tags. Before beginning, your entities should be
[asset-enabled](/develop/tutorials/-/knowledge_base/7-1/adding-updating-and-deleting-assets)
and you should have asset renderers enabled for them.

![Figure 1: Adding category and tag input options lets authors aggregate and label custom entities.](../../images/asset-fw-categories-and-tags-options.png)

Now it's time to get started! 

## Adding Tags and Categories [](id=adding-tags-and-categories)

You can use the following tags in the JSPs you provide for adding/editing custom
entities. Here's what the tags look like in the
[edit_entry.jsp](https://github.com/liferay/liferay-portal/blob/master/modules/apps/blogs/blogs-web/src/main/resources/META-INF/resources/blogs/edit_entry.jsp) 
for the Blogs portlet: 

    <liferay-ui:asset-categories-error />
    <liferay-ui:asset-tags-error />
    ...
    <aui:fieldset-group markupView="lexicon">
        ...
        <aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="categorization">
            <aui:input name="categories" type="assetCategories" />

            <aui:input name="tags" type="assetTags" />
        </aui:fieldset>
        ...
    </aui:fieldset-group>

These category and tag `aui:input` tags generate form controls that let users
browse/select categories for the entity, browse/select tags, and/or create new
tags to associate with the entity. 

The `liferay-ui:asset-categories-error` and `liferay-ui:asset-tags-error` tags
show messages for errors occurring during the asset category or tag
input process. The `aui:fieldset` tag uses a container that lets users hide
or show the category and tag input options.

For styling purposes, the `aui:fieldset-group` tag is given the `lexicon`
markup view.

## Displaying Tags and Categories [](id=displaying-tags-and-categories)

Tags and categories should be displayed with the content of the asset. Here's
how to display the tags and categories: 

    <p><liferay-ui:message key="categories" />:</p>

    <div class="entry-categories">
        <liferay-ui:asset-categories-summary
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
            portletURL="<%= renderResponse.createRenderURL() %>"
        />
    </div>

    ...

    <div class="entry-tags">
        <p><liferay-ui:message key="tags" />:</p>

        <liferay-ui:asset-tags-summary
            className="<%= BlogsEntry.class.getName() %>"
            classPK="<%= entry.getEntryId() %>"
            portletURL="<%= renderResponse.createRenderURL() %>"
        />
    </div>

The `portletURL` parameter is used for both tags and categories. Each tag that
uses this parameter becomes a link containing the `portletURL` *and* `tag` or
`categoryId` parameter value. To implement this, you must implement the look-up
functionality in your portlet code. Do this by reading the values of those two
parameters and using `AssetEntryService` to query the database for entries
based on the specified tag or category. 

Deploy your changes and add/edit a custom entity in your UI. Your form shows the
categorization and tag input options in a panel that the user can hide/show. 

Great! Now you know how to make category and tag input options available to your
app's content authors. 

## Related Topics [](id=related-topics)

[Relating Assets](/develop/tutorials/-/knowledge_base/7-1/relating-assets)

[Adding, Updating, and Deleting Assets for Custom Entities](/develop/tutorials/-/knowledge_base/7-1/adding-updating-and-deleting-assets-for-custom-entities)

[What is Service Builder?](/develop/tutorials/-/knowledge_base/7-1/what-is-service-builder)
