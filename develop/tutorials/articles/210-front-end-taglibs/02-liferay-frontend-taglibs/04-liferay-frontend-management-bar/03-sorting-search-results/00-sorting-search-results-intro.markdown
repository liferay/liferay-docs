# Sorting Search Results [](id=sorting-search-results-with-the-management-bar)

The Management Bar Sort Filters let you compare entries for a search container 
field and sort them in ascending or descending order. To do this, you must 
create a comparator class for each field that you want to sort.

The sort filters are an implementation of the standard 
[`Comparator` Interface](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html),
with some additional methods provided by the 
[`OrderByComparator` class](https://github.com/liferay/liferay-portal/blob/master/portal-kernel/src/com/liferay/portal/kernel/util/OrderByComparator.java). 

Follow these steps to create your sort filters:

1.  [Create a Comparator class](/develop/tutorials/-/knowledge_base/7-1/creating-the-comparator-class).

2.  Import the Comparator and `OrderByComparator` classes into the web module 
    project's `init.jsp`. An example is shown below:

        page import="com.liferay.docs.guestbook.util.comparator.EntryNameComparator"
        
        page import="com.liferay.portal.kernel.util.OrderByComparator"

3.  Open the view and add the comparator code below the `displayStyle` variable 
    in the java scriplet at the top. The example below uses the 
    EntryNameComparator class:

        String orderByCol = ParamUtil.getString(request, "orderByCol", "name");

        boolean orderByAsc = false;

        String orderByType = ParamUtil.getString(request, "orderByType", "asc");

        if (orderByType.equals("asc")) {
        	orderByAsc = true;
        }

        OrderByComparator orderByComparator = null;

        if (orderByCol.equals("name")) {
        	orderByComparator = new EntryNameComparator(orderByAsc);
        }

    This sets up the configuration for the comparators.
    
4.  Add `orderByCol` and `orderByType` portlet parameters for your order 
    comparator to the view's render URL. The `orderByCol` parameter specifies 
    the column to order by and the `orderByType` column specifies whether the 
    order is ascending or descending. Below is an example configuration:
    
        <liferay-portlet:renderURL varImpl="viewPageURL">
            <portlet:param name="mvcPath" value="/html/guestbookmvcportlet/view.jsp" />
            <portlet:param name="guestbookId" value="<%= String.valueOf(guestbookId) %>" />
            <portlet:param name="displayStyle" value="<%= displayStyle %>" />
            <portlet:param name="orderByCol" value="<%= orderByCol %>" />
            <portlet:param name="orderByType" value="<%= orderByType %>" />
        </liferay-portlet:renderURL>
    
5.  Add the sort filters below the navigation filters, using the 
    `<liferay-frontend:management-bar-sort />` taglib. Pass the name of the 
    column you specified in the *Comparator class. Below is the example 
    configuration for an `EntryNameComparator` class:

        <liferay-frontend:management-bar-sort
          orderByCol="<%= orderByCol %>"
          orderByType="<%= orderByType %>"
          orderColumns='<%= new String[] {"name"} %>'
          portletURL="<%= viewPageURL %>"
        />

6.  Finally, pass the `orderByComparator` in as an argument in the search 
    container results to match the updated method signature you modified. Below 
    is the configuration for the example EntryNameComparator:

        <liferay-ui:search-container-results
       		results="<%= EntryLocalServiceUtil.getEntries(scopeGroupId,
          guestbookId, searchContainer.getStart(), searchContainer.getEnd(),
          orderByComparator) %>"
       	/>

The Management Bar Sort Filters are finished!

## Related Topics [](id=related-topics)

[Creating the Comparator Class](/develop/tutorials/-/knowledge_base/7-1/creating-the-comparator-class)

[Creating Display Options for Search Results](/develop/tutorials/-/knowledge_base/7-1/creating-display-options-for-search-results)

[Filtering Search Results with the Management Bar](/develop/tutorials/-/knowledge_base/7-1/filtering-search-results-with-the-management-bar)
