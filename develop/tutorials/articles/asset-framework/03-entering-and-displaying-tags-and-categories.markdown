# Implementing Asset Categorization and Tagging [](id=implementing-asset-categorization-and-tagging)

<!--
Testing Notes:

The starting example portlet for this tutorial is at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-02-asset-enable-insults-portlet

On completing this tutorial, the example portlet looks like the portlet at ...
liferay-docs\develop\tutorials\tutorials-sdk-6.2-ga3\portlets\asset-framework-03-end-insults-portlet

Make sure to read their README files.
-->

At this point, you've [asset-enabled](/develop/tutorials/-/knowledge_base/6-2/adding-updating-and-deleting-assets-for-custom-entities)
your entities. In addition to that, you should make sure to [implement asset renderers](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers)
for them. Content authors, however, still can't specify the tags and categories
for these entities in the UI. Don't fret! Liferay provides a set of JSP tags for
showing category and tag inputs in your UI. 

![Figure 1: Adding category and tag input options lets authors aggregate and label custom entities.](../../images/asset-fw-categories-and-tags-options.png)

Go ahead and get started! 

You can use the following tags in the JSPs you provide for adding/editing custom
entities. Here's what the tags look like in a the [`edit_insult.jsp`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/portlets/asset-framework-03-end-insults-portlet/docroot/html/insult/edit_insult.jsp) 
for a custom Insults portlet that's used as an example. 

    <liferay-ui:asset-categories-error />
    <liferay-ui:asset-tags-error />
    <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="insultsCategorizationPanel" persistState="<%= true %>" title="categorization">
        <aui:fieldset>
            <aui:input name="categories" type="assetCategories" />

            <aui:input name="tags" type="assetTags" />
         </aui:fieldset>
    </liferay-ui:panel>

These category and tag `aui:input` tags generate form controls that let users
browse/select a categories for the entity, browse/select tags, and/or create new
tags to associate with the entity. 

The `liferay-ui:asset-categories-error` and `liferay-ui:asset-tags-error` tags
show messages for errors occurring during the asset category or tag
input process. The `liferay-ui:panel` tag uses a container that lets users hide
or show the category and tag input options.

For styling purposes, give the `liferay-ui:panel` an arbitrary `id` value that
relates to your custom entity. 

<!--

Until we implement this in a solution portlet, we'll leave it out. - Jim

Actually, the whole point of tutorials is not to have solution portlets, but
just snippets of code. Solution portlets are for Learning Paths. If we want to
use solution portlets, that's fine, but we definitely shouldn't keep from
documenting something in a tutorial just because we don't have it in a working
portlet. -Rich
-->

Once the tags and categories have been entered, you'll want to show them along
with the content of the asset. Here's how to display the tags and categories: 
 
    <label>Categories</label>
    <liferay-ui:asset-categories-summary
        className="<%= insult.getClass().getName() %>"
        classPK="<%= insult.getPrimaryKey() %>"
    />

    <label>Tags</label>
    <liferay-ui:asset-tags-summary
        className="<%= insult.getClass().getName() %>"
        classPK="<%= insult.getPrimaryKey() %>"
    />

You can also support navigation of tags and categories by specifying a
`portletURL` parameter in the JSP tags. Each tag that uses this parameter
becomes a link containing the `portletURL` *and* `tag` or `categoryId` parameter
value. To implement this, you need to implement the look-up functionality in
your portlet code. Do this by reading the values of those two parameters and
using `AssetEntryService` to query the database for entries based on the
specified tag or category. 

Deploy your changes and add/edit a custom entity in your UI. Your form shows the
categorization and tag input options in a panel that the user can hide/show. 

Great! Now you know how to make category and tag input options available to your
portlet's content authors. 

**Related Topics**

[Asset Enabling Custom Entities](/develop/learning-paths/-/knowledge_base/6-2/asset-enabling-custom-entities)

[Implementing Asset Renderers](/develop/learning-paths/-/knowledge_base/6-2/implementing-asset-renderers)

[Liferay UI Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[User Interfaces with AlloyUI](/develop/tutorials/-/knowledge_base/6-2/alloyui)
