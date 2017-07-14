package com.liferay.docs.guestbook.util.comparator;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.portal.kernel.util.OrderByComparator;

public class EntryNameComparator extends OrderByComparator<Entry>{

    public static final String ORDER_BY_ASC = "Entry.name ASC";

    public static final String ORDER_BY_DESC = "Entry.name DESC";

    public static final String[] ORDER_BY_FIELDS = {"name"};
	
    public EntryNameComparator() {
    	this(false);
    }

    public EntryNameComparator(boolean ascending) {
    	_ascending = ascending;
    }
    
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
