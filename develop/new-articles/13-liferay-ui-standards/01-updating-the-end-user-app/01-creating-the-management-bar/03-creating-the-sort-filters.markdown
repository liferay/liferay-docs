# Creating the Sort Filters [](id=creating-the-sort-filters)

The Management Bar Sort Filters let you compare entries for a search container 
field, and sort them by ascending or descending. To do this, you must create a 
comparator class for each field that you want to sort by. You'll create a name 
sort filter in this section.

The sort filters are an implementation of the standard [`Comparator` Interface](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html),
with some additional methods provided by the [`OrderByComparator` class](https://github.com/liferay/liferay-portal/blob/master/portal-kernel/src/com/liferay/portal/kernel/util/OrderByComparator.java). 
Once the class is created you can use it in the Guestbook's main view to add the 
sort filters to the UI.

Go ahead and get started by creating the Comparator class next.

## Creating the Comparator Class [](id=creating-the-comparator-class)

The `OrderByComparator` class is a `Comparator` implementation that you can 
extend to create sort filters. Follow these steps to create the 
`*OrderByComparator` class:

1.  Right-click the `guestbook-api` folder in the package explorer and select 
    *New*&rarr;*Package*.
    
2.  Enter the name `com.liferay.docs.guestbook.util.comparator` and click 
    *Finish*.
    
3.  Right-click the package you just created and select *New*&rarr;*Class*. 
    Enter *EntryNameComparator* for the class Name, check the *Constructors from 
    superclass* option, and click *Finish*.

4. Update the class declaration to extend the `OrderByComparator` class and use 
   a type `<Entry>`:

        public class EntryNameComparator extends OrderByComparator<Entry>{
          ...
        }

5.  Add variables for the ascending, descending, and name field sorters:

        public static final String ORDER_BY_ASC = "Entry.name ASC";

        public static final String ORDER_BY_DESC = "Entry.name DESC";

        public static final String[] ORDER_BY_FIELDS = {"name"};

6.  Replace the public constructor with the following constructors:

        public EntryNameComparator() {
        	this(false);
        }

        public EntryNameComparator(boolean ascending) {
        	_ascending = ascending;
        }

7.  Add the `compare()` method to compare the Guestbook entries:

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

8.  Add the following code to return the order fields and check whether the order 
    is ascending or descending:

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

9.  Finally, Import the Guestbook model Entry class and OrderByComparator 
    class:

        import com.liferay.docs.guestbook.model.Entry;
        import com.liferay.portal.kernel.util.OrderByComparator;

Now that the `EntryNameComparator` class is written you must add support for it
to the Guestbook's services next.

## Updating Guestbook Services [](id=updating-guestbook-services)

Follow these steps to update the Guestbook services:

1.  Open the `com.liferay.docs.guestbook.service.impl.EntryLocalServiceImpl.java` 
    class in the Guestbook-service module and import the `OrderByComparator` 
    class:

        import com.liferay.portal.kernel.util.OrderByComparator;

2.  Update the `getEntries()` method with the start and end integers to include 
    the `OrderByComparator` parameter:

        public List<Entry> getEntries(
        	long groupId, long guestbookId, int start, int end,
        	OrderByComparator<Entry> obc) {

        	return entryPersistence.findByG_G(
        		groupId, guestbookId, start, end, obc);
        }

3.  Rebuild services for the Guestbook App. Right-click the `Guestbook-service` 
    module in the Project Explorer and select *Liferay* &rarr; *build-service*.
<!-- After running this step some code was added that does not match the 
generated code in Eudaldo's PR. It's possible the build service is outdated in 
my gradle wrapper in my workspace?

resolved- service builder jar was outdated. Used latest snapshot for now in 
build.gradle for guestbook-service module. Need to update code eventually, to 
use latest jar once it is up to date.
 -->

4.  Export the comparator package in the Guestbook-api module's BND:
        Export-Package:\
        	com.liferay.docs.guestbook.exception,\
        	com.liferay.docs.guestbook.model,\
        	com.liferay.docs.guestbook.service,\
        	com.liferay.docs.guestbook.service.persistence,\
        	com.liferay.docs.guestbook.util.comparator

Now that the services are updated and your exports are in order, you can
configure the view to use the comparator next.

## Configuring the View [](id=configuring-the-view)

Follow these steps to configure the view to use the Comparator:

1.  Import the `EntryNameComparator` and `Comparator` classes into the
    `guestbook-web` module project's `init.jsp`:

        page import="com.liferay.docs.guestbook.util.comparator.EntryNameComparator"
        
        page import="com.liferay.portal.kernel.util.OrderByComparator"

2.  Open `html/guestbookmvcportlet/view.jsp` and add the following code below 
    the `displayStyle` variable in the java scriplet at the top:

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
    
3.  Add the following portlet parameters below the `guestbookId` one in the 
    `<liferay-portlet:renderURL varImpl="viewPageURL">` tag located below the 
    closing `</aui:button-row>` tag:

        <portlet:param name="displayStyle" value="<%= displayStyle %>" />
        <portlet:param name="orderByCol" value="<%= orderByCol %>" />
        <portlet:param name="orderByType" value="<%= orderByType %>" />
    
4.  Add the sort filters below the navigation filters, using the 
    `<liferay-frontend:management-bar-sort />` taglib: 

        <liferay-frontend:management-bar-sort
          orderByCol="<%= orderByCol %>"
          orderByType="<%= orderByType %>"
          orderColumns='<%= new String[] {"name"} %>'
          portletURL="<%= viewPageURL %>"
        />

5.  Finally, pass the `orderByComparator` in as an argument in the search
     container results:

        <liferay-ui:search-container-results
       		results="<%= EntryLocalServiceUtil.getEntries(scopeGroupId,
          guestbookId, searchContainer.getStart(), searchContainer.getEnd(),
          orderByComparator) %>"
       	/>

The Management Bar Sort Filters are finished. The Management Bar is complete!
The updated Management Bar is shown in the figure below:

![Figure 1: The sort filters let you sort the Guestbook app's entries.](../../../../../images/sort-filters-added.png)

In the next section, you'll make the remaining updates to the Guestbook app's UI
to match the Lexicon guidelines.
