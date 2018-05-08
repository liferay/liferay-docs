# Clay Management Toolbar [](id=clay-management-toolbar)

The Management Toolbar gives administrators control over search container 
results in their apps. It lets you filter, sort, and choose a view type for 
search results, so you can quickly identify the document, web content, asset 
entry, etc. that you're looking for. The Management Toolbar is fully 
customizable, so you can implement all the controls, or just the ones your app 
requires. 

![Figure 1: The Management Bar lets the user customize how the app displays content.](../../../../images/clay-taglib-management-toolbar.png)

To create the Management Toolbar, use the `clay:management-toolbar` taglib. The 
toolbar is divided into a few key sections. Each section is grouped and 
configured using different attributes. These attributes are described in more 
detail below. 

## Checkbox and Actions

The `actionItems`, `searchContainerId`, and `selectable` attributes let you 
include a checkbox in the toolbar to select all search container results and run 
bulk actions on them. Actions display when either an individual result is 
checked, or when the master checkbox is checked in the toolbar.  

`actionItems`: The list of dropdown items to display when an item is checked or 
the master checkbox in the management toolbar is checked. You can select 
multiple items between pages. The management bar keeps track of the number of 
selected items for you.

`searchContainerId`: The ID of the search container the management toolbar is 
connected to.

`selectable`: Whether to include a checkbox in the management toolbar to run 
actions on the search results. 

![Figure 2: The management toolbar keeps track of the items selected and displays the actions to execute on them.](../../../../images/clay-taglib-management-toolbar-selectable.png)

## Filtering and Sorting Search Results

The `filterItems`, `sortingOrder`, and `sortingURL` attributes let you filter 
and sort search container results. Filtering and sorting are grouped together in 
one convenient dropdown menu. 

`filterItems`: sets the search container's filtering options. This filter should 
be included in all control panel applications. Filtering options can include 
sort criteria, sort ordering, and more.

`sortingOrder`: The current sorting order: ascending or descending.

`sortingURL`: The URL to change the sorting order.

![Figure 3: You can also sort and filter search container results.](../../../../images/clay-taglib-management-toolbar-filter-and-sort.png)

## Search Form

The `clearResultsURL`, `searchActionURL`, `searchFormName`, `searchInputName`, 
and `searchValue` attributes let you configure the search form. The main portion 
of the management toolbar is reserved for the search form.

`clearResultsURL`: The URL to reset the search.

`searchActionURL`: The action URL to send the search form.

`searchFormName`: The search form's name.

`searchInputName`: The search input's name.

`searchValue`: The search input's value.

![Figure 4: The search form comprises most of the management toolbar, letting users search through the search container results.](../../../../images/clay-taglib-management-toolbar-search-form.png)

## Info Panel

The `infoPanelId` and `showInfoButton` attributes let you add a retractable 
sidebar panel that displays additional information related to a search container 
result.

`infoPanelId`: The ID of the info panel to toggle.

`showInfoButton`: Whether to show the info button.

## View Types

The `viewTypes` attribute specifies the display options for the search container 
results. There are three display options to choose from, although only one must 
be implemented:

![Figure 5: The Management Toolbar offers three view type options.](../../../../images/clay-taglib-management-toolbar-view-types.png)

- **Cards:** displays search result columns on a horizontal or vertical card 

![Figure 6: The Management Toolbar's icon display view gives a quick summary of the content's description and status.](../../../../images/clay-taglib-management-toolbar-view-type-card.png)

- **List:** displays a detailed description along with summarized details for 
the search result columns  

![Figure 7: The Management Toolbar's List view type gives the content's full description.](../../../../images/clay-taglib-management-toolbar-view-type-list.png)

- **Table:** the default view, which list the search result columns from left to 
right 

![Figure 8: The Management Toolbar's list display view list the content's information in individual columns.](../../../../images/clay-taglib-management-toolbar-view-type-table.png)

## Creation Menu

The `creationMenu` attribute creates an add menu button for one or multiple 
items. It is used for actions that create entities (e.g. a new blog entry).

![Figure 9: The management toolbar lets you optionally add a Creation Menu for creating new entities.](../../../../images/clay-taglib-management-toolbar-creation-menu.png)

An example Management Toolbar configuration is shown below for the Web Content 
app:

    <clay:management-toolbar
    	actionItems="<%= journalDisplayContext.getActionDropdownItems() %>"
    	clearResultsURL="<%= journalDisplayContext.getClearResultsURL() %>"
    	componentId="journalWebManagementToolbar"
    	creationMenu="<%= journalDisplayContext.getCreationMenu() %>"
    	disabled="<%= journalDisplayContext.isDisabled() %>"
    	filterItems="<%= journalDisplayContext.getFilterDropdownItems() %>"
    	infoPanelId="infoPanelId"
    	searchActionURL="<%= journalDisplayContext.getSearchActionURL() %>"
    	searchContainerId="<%= searchContainerId %>"
    	searchFormName="fm1"
    	showCreationMenu="<%= journalDisplayContext.isShowAddButton() %>"
    	showInfoButton="<%= journalDisplayContext.isShowInfoButton() %>"
    	showSearch="<%= journalDisplayContext.isShowSearch() %>"
    	sortingOrder="<%= journalDisplayContext.getOrderByType() %>"
    	sortingURL="<%= journalDisplayContext.getSortingURL() %>"
    	totalItems="<%= journalDisplayContext.getTotalItems() %>"
    	viewTypes="<%= journalDisplayContext.getViewTypeItems() %>"
    />

## Related Topics [](id=related-topics)

[Clay Dropdown Menus and Action Menus](/develop/tutorials/-/knowledge_base/7-1/clay-dropdown-menus-and-action-menus)

[Clay Icons](/develop/tutorials/-/knowledge_base/7-1/clay-icons)

[Clay Navigation Bars](/develop/tutorials/-/knowledge_base/7-1/clay-navigation-bars)
