# Filtering and Sorting Items with the Management Toolbar [](id=filtering-and-sorting-items-with-the-management-toolbar)

The Management Toolbar lets you filter and sort your search container results. 
You can choose a navigation filter or a order by filter and sort your search 
container results in ascending or descending order. The configuration for the 
filters and sorting order can be included in the JSP, but that can crowd the JSP 
pretty quick. We recommend instead that you move this functionality to a 
separate java class, which we refer to as a display context throughout this 
tutorial. A Display Context is a Java class that defines the configuration for 
an app's UI elements. 

Creating filters and sorting results involves the following process: 

- Creating the Filters
- Creating a Comparator class for each field you want to sort
- Including the selected column and sorting order in the portlet URL
- Including the selected column and sorting order in the search container

## Creating Filters for the Management Toolbar [](id=creating-filters-for-the-management-toolbar)

There are two main types of filters: navigation and order. Both of these are 
contained within the same dropdown menu. Follow the steps below to create your 
filters.

1.  Depending on your needs, there are two classes that you can extend for your 
    management toolbar display context. These base classes provide the required 
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

Now that you've defined the filter keys, you can provide the logic to sort the 
columns. 

## Setting the Sorting Order for Columns [](id=setting-the-sorting-order-for-columns)

Defining the Navigation and Order filters is just one side of the equation. Once 
an option is selected, you must then sort the results by the selected option, in 
either ascending or descending order. To do this, you must create a comparator 
class for each field that you want to sort: An implementation of the standard 
[`Comparator` Interface](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html), 
with some additional methods provided by the 
[`OrderByComparator` class](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-kernel/src/com/liferay/portal/kernel/util/OrderByComparator.java). 

Follow these steps to provide the logic for sorting your columns:

1.  Create a comparator for each field you want to compare. These should be 
    defined in their own class. The example comparator below compares 
    `create-date` columns:
        
        public class MyCreateDateComparator
        	extends OrderByComparator<MyEntity> {

        	public static final String ORDER_BY_ASC = "MYEntity.createDate ASC";

        	public static final String ORDER_BY_DESC = "MYEntity.createDate DESC";

        	public static final String[] ORDER_BY_FIELDS = {"createDate"};

        	public MyCreateDateComparator() {
        		this(false);
        	}

        	public MyCreateDateComparator(boolean ascending) {
        		_ascending = ascending;
        	}

        	@Override
        	public int compare(MYEntity myEntity1, MYEntity myEntity2) {
        		int value = DateUtil.compareTo(
        			myEntity1.getCreateDate(), myEntity2.getCreateDate());

        		if (_ascending) {
        			return value;
        		}
        		else {
        			return -value;
        		}
        	}

        	@Override
        	public String getOrderBy() {
        		if (_ascending) {
        			return ORDER_BY_ASC;
        		}
        		else {
        			return ORDER_BY_DESC;
        		}
        	}

        	@Override
        	public String[] getOrderByFields() {
        		return ORDER_BY_FIELDS;
        	}

        	@Override
        	public boolean isAscending() {
        		return _ascending;
        	}

        	private final boolean _ascending;

        }

2.  Specify the Comparator to use for each column. Note that each comparator is 
    passed the sorting order (ascending or descending). An example configuration 
    is shown below:

        public static OrderByComparator getOrderByComparator(
          String orderByCol, String orderByType) {
            
            if (Validator.isNull(orderByCol) || Validator.isNull(orderByType)) {
              return null;
            }
            
            boolean ascending = false;
            
            if (orderByType.equals("asc")) {
              ascending = true;
            }
            
            if (orderByCol.equals("create-date")) {
              return new MyCreateDateComparator(ascending);
            }
            else if (orderByCol.equals("name")) {
              return new MyNameComparator(ascending);
            }
            
            return null;
          }
  
3.  Define the method to retrieve the sorting URL. Below is an example 
    configuration:

        public String getSortingURL() {
        	PortletURL sortingURL = getPortletURL();

        	sortingURL.setParameter(
        		"orderByType",
        		Objects.equals(getOrderByType(), "asc") ? "desc" : "asc");

        	return sortingURL.toString();
        }

4.  Return the order type (ascending or descending). If a value doesn't exist, 
    the example below sets the default order type to ascending:

        public String getOrderByType() {
          if (Validator.isNotNull(_orderByType)) {
            return _orderByType;
          }

          _orderByType = ParamUtil.getString(_request, "orderByType", "asc");

          return _orderByType;
        }

5.  Open the JSP view that contains the Clay Management Toolbar and set its 
    `displayContext` attribute to the display context you created. An example 
    configuration is shown below:
    
        displayContext= "<%= myManagementToolbarDisplayContext %>" 

Next you can update the portlet URL. 

## Including the Selected Column and Sorting Order in the Portlet URL [](id=including-the-selected-column-and-sorting-order-in-the-portlet-url)

Each time a filter is chosen or the sorting order is changed, you must update 
the portlet URL to reflect the changes. You can see this in the previous 
sections where the method `getPortletURL()` is called before setting a 
parameter. Follow the pattern below to define the `getPortletURL()` method to 
build your portlet URL:

    public PortletURL getPortletURL() {
      PortletURL portletURL = _renderResponse.createRenderURL();

      portletURL.setParameter("mvcPath", "/view_article_history.jsp");
      portletURL.setParameter("redirect", _getRedirect());
      portletURL.setParameter("orderByCol", getOrderByCol());
      portletURL.setParameter("orderByType", getOrderByType());

      return portletURL;
    }

Now that the portlet URL is updated, you can update the search container to 
reflect the changes.

## Including the Selected Column and Sorting Order in the Search Container [](id=including-the-selected-column-and-sorting-order-in-the-search-container)

Finally, you must update the search container to reflect the current order type 
(ascending or descending), the column to sort by, and the comparator to use. An 
example configuration is shown below:

    public SearchContainer getSearchContainer() {
      ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
        WebKeys.THEME_DISPLAY);

      SearchContainer searchContainer = new SearchContainer(
        _renderRequest, _renderResponse.createRenderURL(), null,
        "there-are-no-page-templates");

      searchContainer.setId("layoutPrototype");
      searchContainer.setRowChecker(
        new EmptyOnClickRowChecker(_renderResponse));

      boolean orderByAsc = false;

      if (getOrderByType().equals("asc")) {
        orderByAsc = true;
      }

      OrderByComparator<LayoutPrototype> orderByComparator =
        new LayoutPrototypeCreateDateComparator(orderByAsc);

      searchContainer.setOrderByCol(getOrderByCol());
      searchContainer.setOrderByComparator(orderByComparator);
      searchContainer.setOrderByType(getOrderByType());

      searchContainer.setTotal(getTotal());

      List results = LayoutPrototypeLocalServiceUtil.search(
        themeDisplay.getCompanyId(), getActive(),
        searchContainer.getStart(), searchContainer.getEnd(),
        searchContainer.getOrderByComparator());

      searchContainer.setResults(results);

      return searchContainer;
    }

## Related Topics [](id=related-topics)

[Configuring Filtering and Sorting Management Toolbar Attributes](/develop/tutorials/-/knowledge_base/7-1/clay-management-toolbar#filtering-and-sorting-search-results)

[Implementing the View Types](/develop/tutorials/-/knowledge_base/7-1/implementing-the-view-types)
