# Filtering Search Results with the Management Bar [](id=filtering-search-results-with-the-management-bar)

Navigation filters can display search results that match a given criteria, such 
as content created only by yourself or recently modified, or provide additional 
information, such as the available document types. You can add as many 
navigation filters to your app's management bar as you need. 

Follow these steps to create a navigation filter:

1.  Define a default navigation filter. You'll create the filters in the next 
    step. For example, the configuration below sets the default navigation 
    filter to *all*:
    
        String navigation = ParamUtil.getString(request, "navigation", "all");

2.  Add a navigation key for each navigation filter your app requires. If your 
    app doesn't require any navigation filters, you can just provide the *all* 
    filter to display the default view:

        <liferay-frontend:management-bar-buttons>
            ...
        </liferay-frontend:management-bar-buttons>
        
        <liferay-frontend:management-bar-filters>

            <liferay-frontend:management-bar-navigation
            navigationKeys='<%= new String[] {"all", ["navigation-title"]...} %>'
            navigationParam="myCustomNavigationVariable"
            portletURL="<%= portletURL %>"
            />
        </liferay-frontend:management-bar-filters>
        
    `navigationKeys`: Specifies the available navigation filters.
    
    `navigationParam`: An optional parameter that declares a custom variable to 
    identify the navigation filter. The default value is `navigation`.
    
    `portletURL`: Specifies the portlet URL for the page.

3.  Provide the proper view (JSP) for each navigation filter you defined. Below 
    is an example code snippet from the Wiki portlet that uses the default 
    navigation parameter `navigation` to check the current value. If you defined 
    a custom `navigationParam` variable, you must use that value instead:

        if (navigation.equals("all-pages")) {
        	portletURL.setParameter("mvcRenderCommandName", "/wiki/view_pages");

        	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request,
          "all-pages"), portletURL.toString());
        }
        else if (navigation.equals("categorized-pages")) {
        	portletURL.setParameter("mvcRenderCommandName",
          "/wiki/view_categorized_pages");

        	portletURL.setParameter("categoryId", String.valueOf(categoryId));
        }
        else if (navigation.equals("draft-pages")) {
        	portletURL.setParameter("mvcRenderCommandName", "/wiki/view_draft_pages");

        	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request,
          "draft-pages"), portletURL.toString());
        }

![Figure 1: Navigation filters let you refine results further by a selected criteria.](../../../../images/liferay-frontend-taglib-management-bar-navigation-filters-wiki.png)

Now you know how to add navigation filters to your app's management bar!

## Related Topics [](id=related-topics)

[Creating Display Options for Search Results](/develop/tutorials/-/knowledge_base/7-1/creating-display-options-for-search-results)

[Including Actions in the Management Bar](/develop/tutorials/-/knowledge_base/7-1/including-actions-in-the-management-bar)

[Sorting Search Results with the Management Bar](/develop/tutorials/-/knowledge_base/7-1/sorting-search-results-with-the-management-bar)
