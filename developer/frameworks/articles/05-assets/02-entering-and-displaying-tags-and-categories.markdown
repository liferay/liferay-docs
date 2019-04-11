# Implementing Asset Categorization and Tagging 

Now it's time to get started with Tags and Categories.

## Adding Tags and Categories

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
            <liferay-asset:asset-categories-selector name="categories" type="assetCategories" />

            <liferay-asset:asset-tags-selector name="tags" type="assetTags" />
        </aui:fieldset>
        ...
    </aui:fieldset-group>

The `liferay-asset:asset-categories-selector` and 
`liferay-asset:asset-tags-selector` tags generate form controls that let users 
browse/select categories for the entity, browse/select tags, and/or create new 
tags to associate with the entity. 

The `liferay-ui:asset-categories-error` and `liferay-ui:asset-tags-error` tags
show messages for errors occurring during the asset category or tag
input process. The `aui:fieldset` tag uses a container that lets users hide
or show the category and tag input options.

For styling purposes, the `aui:fieldset-group` tag is given the `lexicon`
markup view.

## Displaying Tags and Categories

Tags and categories should be displayed with the content of the asset. Here's
how to display the tags and categories: 

	<liferay-asset:asset-categories-available
		className="<%= [AssetEntry].class.getName() %>"
		classPK="<%= entry.getEntryId() %>"
	>
		<div class="entry-categories">
			<liferay-asset:asset-categories-summary
				className="<%= [AssetEntry].class.getName() %>"
				classPK="<%= entry.getEntryId() %>"
				portletURL="<%= renderResponse.createRenderURL() %>"
			/>
		</div>
	</liferay-asset:asset-categories-available>

    ...

	<liferay-asset:asset-tags-available
		className="<%= [AssetEntry].class.getName() %>"
		classPK="<%= entry.getEntryId() %>"
	>
		<div class="entry-tags">
			<liferay-asset:asset-tags-summary
				className="<%= [AssetEntry].class.getName() %>"
				classPK="<%= entry.getEntryId() %>"
				portletURL="<%= renderResponse.createRenderURL() %>"
			/>
		</div>
	</liferay-asset:asset-tags-available>
    
    
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
