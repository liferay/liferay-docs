# Creating the Comparator Class [](id=creating-the-comparator-class)

The `OrderByComparator` class is a `Comparator` implementation that you can 
extend to 
[create sort filters for the Management Bar](/develop/tutorials/-/knowledge_base/7-1/sorting-search-results-with-the-management-bar). 
Follow these steps to create the `*OrderByComparator` class:

1.  Right-click on your API module's folder in the package explorer and select 
    *New*&rarr;*Package* to create a new package.
    
2.  Right-click the package you just created and select *New*&rarr;*Class*. 
    Enter *EntryNameComparator* for the class Name, check the *Constructors from 
    superclass* option, and click *Finish*.

3. Update the class declaration to extend the `OrderByComparator` class and use 
   a proper asset type, `<Entry>` for example. Below is an example 
   configuration for an entry name field comparator:

        public class EntryNameComparator extends OrderByComparator<Entry>{
          ...
        }

4.  Add variables for the ascending, descending, and field (name for example) 
    sorters:

        public static final String ORDER_BY_ASC = "[asset].[column name] ASC";

        public static final String ORDER_BY_DESC = "[asset].[column name] DESC";

        public static final String[] ORDER_BY_FIELDS = {"[field name]"};
        
    Below is an example configuration for an Entry asset's name field:
    
        public static final String ORDER_BY_ASC = "Entry.name ASC";

        public static final String ORDER_BY_DESC = "Entry.name DESC";

        public static final String[] ORDER_BY_FIELDS = {"name"};

5.  Replace the public constructor with the following constructors:

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

6.  Add the `compare()` method to compare search container asset entries. Below 
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

7.  Add the ascending and descending order logic and resolve imports:

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

8.  Update the service layer. Open your `*EntryLocalServiceImpl` class in your 
    service module and import the `OrderByComparator` class:

        import com.liferay.portal.kernel.util.OrderByComparator;

<!-- Verify that it is the getEntries method -->
9.  Update the `getEntries()` method with the start and end integers to include 
    the `OrderByComparator` parameter. Below is an example configuraiton:

        public List<Entry> getEntries(
        	long groupId, long guestbookId, int start, int end,
        	OrderByComparator<Entry> obc) {

        	return entryPersistence.findByG_G(
        		groupId, guestbookId, start, end, obc);
        }

10.  Rebuild services for your app and export the comparator package in the API 
     module's BND.

 ## Related Topics [](id=related-topics)

 [Disabling All or Portions of the Management Bar](/develop/tutorials/-/knowledge_base/7-1/disabling-all-or-portions-of-the-management-bar)

 [Filtering Search Results with the Management Bar](/develop/tutorials/-/knowledge_base/7-1/filtering-search-results-with-the-management-bar)

 [Sorting Search Results with the Management Bar](/develop/tutorials/-/knowledge_base/7-1/sorting-search-results-with-the-management-bar)
