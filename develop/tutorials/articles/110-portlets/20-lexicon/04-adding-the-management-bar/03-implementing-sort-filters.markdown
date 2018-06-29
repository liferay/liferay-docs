# Implementing a Management Bar Sort Filter [](id=implementing-a-management-bar-sort-filter)

The Management Bar Sort Filters let you compare entries for a search container 
field, and sort them by ascending or descending. To do this, you must create a 
comparator class for each field that you want to sort.

The sort filters are an implementation of the standard [`Comparator` Interface](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html),
with some additional methods provided by the [`OrderByComparator` class](https://github.com/liferay/liferay-portal/blob/7.0.6-ga7/portal-kernel/src/com/liferay/portal/kernel/util/OrderByComparator.java). 

Once the class is created you can use it in your view to add the sort 
filters to the UI.

Go ahead and get started by creating the Comparator class next.

## Creating the Comparator Class [](id=creating-the-comparator-class)

The `OrderByComparator` class is a `Comparator` implementation that you can 
extend to create sort filters. Follow these steps to create the 
`*OrderByComparator` class:

1.  Right-click on your API module's folder in the package explorer and select 
    *New*&rarr;*Package* to create a new package.
    
2.  Right-click the package you just created and select *New*&rarr;*Class*. 
    Enter *EntryNameComparator* for the class Name, check the *Constructors from 
    superclass* option, and click *Finish*.

3. Update the class declaration to extend the `OrderByComparator` class and use 
   a proper asset type, `<Entry>` for example>. Below is an example 
   configuration for an entry name field comparator:

        public class EntryNameComparator extends OrderByComparator<Entry>{
          ...
        }

5.  Add variables for the ascending, descending, and column name field (name for example) sorters:

        public static final String ORDER_BY_ASC = "[asset].[column name] ASC";

        public static final String ORDER_BY_DESC = "[asset].[column name] DESC";

        public static final String[] ORDER_BY_FIELDS = {"[field name]"};
        
    Below is an example configuration for an Entry asset's name field:
    
        public static final String ORDER_BY_ASC = "Entry.name ASC";

        public static final String ORDER_BY_DESC = "Entry.name DESC";

        public static final String[] ORDER_BY_FIELDS = {"name"};

6.  Replace the public constructor with the following constructors:

        public *Comparator() {
        	this(false);
        }

        public *Comparator(boolean ascending) {
        	_ascending = ascending;
        }
        
    Below is an example configuration for an EntryNameComparator:

        public EntryNameComparator() {
          this(false);
        }

        public EntryNameComparator(boolean ascending) {
          _ascending = ascending;
        }

7.  Add the `compare()` method to compare search container asset entries. Below 
    is an example configuration for entry assets:

        @Override
        public int compare(Entry entry1, Entry entry2) {
          String name1 = entry1.getName();
          String name2 = entry2.getName();

          int value = name1.compareTo(name2);

          if (_ascending) {
            return value;
          }
          else {
            return -value;
          }
        }

8.  Add the following code to return the order fields and check whether the 
    order is ascending or descending:

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

9.  Finally, resolve imports for the class:

        import com.liferay.docs.guestbook.model.Entry;
        import com.liferay.portal.kernel.util.OrderByComparator;

Now that your `*Comparator` class is written you must update the service layer 
to use it.

## Updating the Service Layer [](id=updating-guestbook-services)

Follow these steps to update services:

1.  Open your `*EntryLocalServiceImpl` class in your service module and import 
    the `OrderByComparator` class:

        import com.liferay.portal.kernel.util.OrderByComparator;

<!-- Verify that it is the getEntries method -->
2.  Update the `getEntries()` method with the start and end integers to include 
    the `OrderByComparator` parameter. Below is an example configuraiton:

        public List<Entry> getEntries(
        	long groupId, long guestbookId, int start, int end,
        	OrderByComparator<Entry> obc) {

        	return entryPersistence.findByG_G(
        		groupId, guestbookId, start, end, obc);
        }

3.  Rebuild services for your App. Right-click the service module in the Project 
    Explorer and select *Liferay* &rarr; *build-service*.

4.  Export the comparator package in the API module's BND.

Now that the services are updated and your exports are in order, you can 
configure the view to use the comparator next.

## Configuring the View [](id=configuring-the-view)

Follow these steps to configure the view to use the Comparator:

1.  Import the `EntryNameComparator` and `Comparator` classes into the web 
    module project's `init.jsp`:

        page import="com.liferay.docs.guestbook.util.comparator.EntryNameComparator"
        
        page import="com.liferay.portal.kernel.util.OrderByComparator"

2.  Open the view and add the comparator code below the `displayStyle` variable 
    in the java scriplet at the top. Below is an example configuration that uses 
    the EntryNameComparator class:

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
    
3.  Add `orderByCol` and `orderByType` portlet parameters for your order 
    comparator to the view's render URL. The `orderByCol` parameter specifies 
    the column to order by and the `orderByType` column specifies whether the 
    order is ascending or descending. Below is the configuration for the 
    EntryNameComparator:
    
        <liferay-portlet:renderURL varImpl="viewPageURL">
            <portlet:param name="mvcPath" value="/html/guestbookmvcportlet/view.jsp" />
            <portlet:param name="guestbookId" value="<%= String.valueOf(guestbookId) %>" />
            <portlet:param name="displayStyle" value="<%= displayStyle %>" />
            <portlet:param name="orderByCol" value="<%= orderByCol %>" />
            <portlet:param name="orderByType" value="<%= orderByType %>" />
        </liferay-portlet:renderURL>
    
4.  Add the sort filters below the navigation filters, using the 
    `<liferay-frontend:management-bar-sort />` taglib. Pass the name of the 
    column you specified in the *Comparator class. Below is the example 
    configuration for the EntryNameComparator class:

        <liferay-frontend:management-bar-sort
          orderByCol="<%= orderByCol %>"
          orderByType="<%= orderByType %>"
          orderColumns='<%= new String[] {"name"} %>'
          portletURL="<%= viewPageURL %>"
        />

5.  Finally, pass the `orderByComparator` in as an argument in the search 
    container results to match the updated method signature you modified. Below 
    is the configuration for the example EntryNameComparator:

        <liferay-ui:search-container-results
       		results="<%= EntryLocalServiceUtil.getEntries(scopeGroupId,
          guestbookId, searchContainer.getStart(), searchContainer.getEnd(),
          orderByComparator) %>"
       	/>

The Management Bar Sort Filters are finished!

## Related Topics [](id=related-topics)

[Implementing a Management Bar Navigation Filter](/develop/tutorials/-/knowledge_base/7-0/implementing-a-management-bar-navigation-filter)

[Disabling the Management Bar](/develop/tutorials/-/knowledge_base/7-0/disabling-the-management-bar)
