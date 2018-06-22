# Clay Management Toolbar [](id=clay-management-toolbar)

The Management Toolbar gives administrators control over search container 
results in their apps. It lets you filter, sort, and choose a view type for 
search results, so you can quickly identify the document, web content, asset 
entry, or whatever you're looking for. The Management Toolbar is fully 
customizable, so you can implement all the controls or just the ones your app 
requires. 

![Figure 1: The Management ToolBar lets the user customize how the app displays content.](../../../images/clay-taglib-management-toolbar.png)

To create the Management Toolbar, use the `clay:management-toolbar` taglib. The 
toolbar contains a few key sections. Each section is grouped and configured
using different attributes. These attributes are described in more detail
below. 

## Checkbox and Actions [](id=checkbox-and-actions)

The `actionItems`, `searchContainerId`, and `selectable` attributes let you 
include a checkbox in the toolbar to select all search container results and run 
bulk actions on them. Actions display when either an individual result is 
checked, or when the master checkbox is checked in the toolbar. 

`actionItems`: The list of dropdown items to display when a result is checked or 
the master checkbox in the management toolbar is checked. You can select 
multiple results between pages. The management toolbar keeps track of the number 
of selected results for you.

`searchContainerId`: The ID of the search container connected to the management toolbar 

`selectable`: Whether to include a checkbox in the management toolbar

An example configuration is shown below:

    actionItems="<%=
        new JSPDropdownItemList(pageContext) {
            {
              add(
                dropdownItem -> {
                  dropdownItem.setHref("#edit");
                  dropdownItem.setLabel("Edit");
                });
      
              add(
                dropdownItem -> {
                  dropdownItem.setHref("#download");
                  dropdownItem.setIcon("download");
                  dropdownItem.setLabel("Download");
                  dropdownItem.setQuickAction(true);
                });
      
              add(
                dropdownItem -> {
                  dropdownItem.setHref("#delete");
                  dropdownItem.setLabel("Delete");
                  dropdownItem.setIcon("trash");
                  dropdownItem.setQuickAction(true);
                });
            }
        }
    %>"

Action items are listed in the Actions menu.

![Figure 2: Actions are also listed in the management toolbar's dropdown menu when an item, multiple items, or the master checkbox is checked.](../../../images/clay-taglib-management-toolbar-actions.png)

If an action has an icon specified, such as the Delete and Download actions in 
the example above, the icon is displayed next to the action menu as well. 

![Figure 3: The management toolbar keeps track of the results selected and displays the actions to execute on them.](../../../images/clay-taglib-management-toolbar-selectable.png)

## Filtering and Sorting Search Results [](id=filtering-and-sorting-search-results)

The `filterItems`, `sortingOrder`, and `sortingURL` attributes let you filter 
and sort search container results. Filtering and sorting are grouped together in 
one convenient dropdown menu. 

`filterItems`: Sets the search container's filtering options. This filter should 
be included in all control panel applications. Filtering options can include 
sort criteria, sort ordering, and more.

`sortingOrder`: The current sorting order: ascending or descending.

`sortingURL`: The URL to change the sorting order

The example below adds two filter options and two sorting options:

    filterItems="<%=
        new DropdownItemList(_request) {
            {
                addGroup(
                    dropdownGroupItem -> {
                        dropdownGroupItem.setDropdownItemList(
                            new DropdownItemList(_request) {
                                {
                                    add(
                                        dropdownItem -> {
                                            dropdownItem.setHref("#1");
                                            dropdownItem.setLabel("Filter 1");
                                        });

                                    add(
                                        dropdownItem -> {
                                            dropdownItem.setHref("#2");
                                            dropdownItem.setLabel("Filter 2");
                                        });
                                }
                            }
                        );
                        dropdownGroupItem.setLabel("Filter By");
                    });
                    
                addGroup(
                    dropdownGroupItem -> {
                        dropdownGroupItem.setDropdownItemList(
                            new DropdownItemList(_request) {
                                {
                                    add(
                                        dropdownItem -> {
                                            dropdownItem.setHref("#3");
                                            dropdownItem.setLabel("Order 1");
                                        });

                                    add(
                                        dropdownItem -> {
                                            dropdownItem.setHref("#4");
                                            dropdownItem.setLabel("Order 2");
                                        });
                                }
                            }
                        );
                        dropdownGroupItem.setLabel("Order By");
                    });
            }
        }
    %>"

![Figure 4: You can also sort and filter search container results.](../../../images/clay-taglib-management-toolbar-filter-and-sort.png)

## Search Form [](id=search-form)

The `clearResultsURL`, `searchActionURL`, `searchFormName`, `searchInputName`, 
and `searchValue` attributes let you configure the search form. The main portion 
of the management toolbar is reserved for the search form.

`clearResultsURL`: The URL to reset the search

`searchActionURL`: The action URL to send the search form

`searchFormName`: The search form's name

`searchInputName`: The search input's name

`searchValue`: The search input's value

An example configuration is shown below:

    <clay:management-toolbar
        clearResultsURL="<%= searchURL %>"
        disabled="<%= isDisabled %>"
        namespace="<%= renderResponse.getNamespace() %>"
        searchActionURL="<%= searchURL %>"
        searchFormName="fm"
        searchInputName="<%= DisplayTerms.KEYWORDS %>"
        searchValue="<%= ParamUtil.getString(request, searchInputName) %>"
        selectable="<%= false %>"
        totalItems="<%= totalItems %>"
    />

![Figure 5: The search form comprises most of the management toolbar, letting users search through the search container results.](../../../images/clay-taglib-management-toolbar-search-form.png)

## Info Panel [](id=info-panel)

The `infoPanelId` and `showInfoButton` attributes let you add a retractable 
sidebar panel that displays additional information related to a search container 
result.

`infoPanelId`: The ID of the info panel to toggle

`showInfoButton`: Whether to show the info button

An example configuration is shown below:

    <clay:management-toolbar
        disabled="<%= journalDisplayContext.isDisabledManagementBar() %>"
        infoPanelId="infoPanelId"
        namespace="<%= renderResponse.getNamespace() %>"
        searchContainerId="<=% searchContainerId %>"
        selectable='<%= !user.isDefaultUser() && 
        journalDisplayContext.isShowEditActions() && 
        !Objects.equals(journalDisplayContext.getTabs1(), "versions") %>'
        showInfoButton="<%= journalDisplayContext.isShowInfoPanel() %>"
    />

![Figure 6: The info panel keeps your UI clutter-free.](../../../images/clay-taglib-management-toolbar-info-panel.png)

## View Types [](id=view-types)

The `viewTypes` attribute specifies the display options for the search container 
results. There are three display options to choose from, but only one must 
be implemented:

**Cards:** Displays search result columns on a horizontal or vertical card. 

![Figure 7: The Management Toolbar's icon display view gives a quick summary of the content's description and status.](../../../images/clay-taglib-management-toolbar-view-type-card.png)

**List:** Displays a detailed description along with summarized details for 
the search result columns. 

![Figure 8: The Management Toolbar's List view type gives the content's full description.](../../../images/clay-taglib-management-toolbar-view-type-list.png)

**Table:** The default view. Lists the search result columns from left to 
right. 

![Figure 9: The Management Toolbar's Table view type list the content's information in individual columns.](../../../images/clay-taglib-management-toolbar-view-type-table.png)

An example configuration is shown below:

    viewTypes="<%=
        new JSPViewTypeItemList(pageContext, baseURL, selectedType) {
            {
            	addCardViewTypeItem(
            		viewTypeItem -> {
            			viewTypeItem.setActive(true);
            			viewTypeItem.setLabel("Card");
            		});

            	addListViewTypeItem(
            		viewTypeItem -> {
            			viewTypeItem.setLabel("List");
            		});

            	addTableViewTypeItem(
            		viewTypeItem -> {
            			viewTypeItem.setLabel("Table");
            		});
            }
        }
    %>"

![Figure 10: The Management Toolbar offers three view type options.](../../../images/clay-taglib-management-toolbar-view-types.png)

## Creation Menu [](id=creation-menu)

The `creationMenu` attribute creates an add menu button for one or multiple 
items. It's used for creating new entities (e.g. a new blog entry).

Use the `addPrimaryDropdownItem()` method to add the top level items to the 
dropdown menu, or use the `addFavoriteDropdownItem()` method to add secondary 
items to the dropdown menu. 

The example configuration below adds two primary creation menu items and two 
secondary creation menu items:

    creationMenu="<%= 
        new JSPCreationMenu(pageContext) {
      			{
      				addPrimaryDropdownItem(
      					dropdownItem -> {
      						dropdownItem.setHref("#1");
      						dropdownItem.setLabel("Sample 1");
      					});
      
      				addPrimaryDropdownItem(
      					dropdownItem -> {
      						dropdownItem.setHref("#2");
      						dropdownItem.setLabel("Sample 2");
      					});
      
      				addFavoriteDropdownItem(
      					dropdownItem -> {
      						dropdownItem.setHref("#3");
      						dropdownItem.setLabel("Favorite 1");
      					});
      
      				addFavoriteDropdownItem(
      					dropdownItem -> {
      						dropdownItem.setHref("#4");
      						dropdownItem.setLabel("Other item");
      					});
      			}
      	};
    %>"

![Figure 11: The management toolbar lets you optionally add a Creation Menu for creating new entities.](../../../images/clay-taglib-management-toolbar-creation-menu.png)

## Related Topics [](id=related-topics)

[Clay Dropdown Menus and Action Menus](/develop/tutorials/-/knowledge_base/7-1/clay-dropdown-menus-and-action-menus)

[Clay Icons](/develop/tutorials/-/knowledge_base/7-1/clay-icons)

[Clay Navigation Bars](/develop/tutorials/-/knowledge_base/7-1/clay-navigation-bars)
