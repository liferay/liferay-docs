# Filtering and Sorting Items with the Management Toolbar [](id=filtering-and-sorting-items-with-the-management-toolbar)

The Management Toolbar lets you filter and sort your search container results. 
While you can configure the toolbar's filters in the JSP, this can quickly crowd 
the JSP. We recommend instead that you move this functionality to a separate 
java class, which we refer to as a Display Context throughout this tutorial.  

There are two main types of filters: navigation and order. Both of these are 
contained within the same dropdown menu. Follow the steps below to create your 
filters.

1.  Depending on your needs, there are two classes that you can extend for your 
    management toolbar Display Context. These base classes provide the required 
    methods to create your navigation and order filters: 
    
    - [`BaseManagementToolbarDisplayContext`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-taglib/frontend-taglib-clay/src/main/java/com/liferay/frontend/taglib/clay/servlet/taglib/display/context/BaseManagementToolbarDisplayContext.java): 
      for apps without a search container
    - [`SearchContainerManagementToolbarDisplayContext`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-taglib/frontend-taglib-clay/src/main/java/com/liferay/frontend/taglib/clay/servlet/taglib/display/context/SearchContainerManagementToolbarDisplayContext.java): 
      for apps with a search container (extends 
      `BaseManagementToolbarDisplayContext` and provides additional logic for 
      search containers)

    An example configuration for each is shown below:
    
    `BaseManagementToolbarDisplayContext` example:

        public class MyManagementToolbarDisplayContext
          extends BaseManagementToolbarDisplayContext {

          public MyManagementToolbarDisplayContext(
            LiferayPortletRequest liferayPortletRequest,
            LiferayPortletResponse liferayPortletResponse,
            HttpServletRequest request) {

            super(liferayPortletRequest, liferayPortletResponse, request);
          }
          ...
        }

    `SearchContainerManagementToolbarDisplayContext` example:

        public class MyManagementToolbarDisplayContext
          extends SearchContainerManagementToolbarDisplayContext {

          public MyManagementToolbarDisplayContext(
            LiferayPortletRequest liferayPortletRequest,
            LiferayPortletResponse liferayPortletResponse,
            HttpServletRequest request, SearchContainer searchContainer) {

            super(
              liferayPortletRequest, liferayPortletResponse, request,
              searchContainer);
          }
        }

2.  Override the `getNavigationKeys()` method to return the navigation filter 
    dropdown item(s). If your app doesn't require any navigation filters, you 
    can just provide the *all* filter to display everything. An example 
    configuration is shown below:

        @Override
        protected String[] getNavigationKeys() {
          return new String[] {"all", "pending", "done"};
        }

3.  override the `getOrderByKeys()` method to return the columns to order. An 
    example configuration is shown below:

        @Override
        protected String[] getOrderByKeys() {
          return new String[] {"name", "items", "status"};
        }

4.  Open the JSP view that contains the Clay Management Toolbar and set its 
    `displayContext` attribute to the Display Context you created. An example 
    configuration is shown below:

        <clay:management-toolbar
        	displayContext="<%= myManagementToolbarDisplayContext %>"
        />
        
Now you know how to configure the Management Toolbar's filters via a Display 
Context. 

## Related Topics [](id=related-topics)

[Configuring Filtering and Sorting Management Toolbar Attributes](/develop/tutorials/-/knowledge_base/7-1/clay-management-toolbar#filtering-and-sorting-search-results)

[Implementing the View Types](/develop/tutorials/-/knowledge_base/7-1/implementing-the-view-types)
