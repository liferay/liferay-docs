# Sorting Your List Screenlet [](id=sorting-your-list-screenlet)

To sort your list Screenlet, you must point it to a *comparator class* in your 
portal. A comparator class implements the logic that sorts your entities. You 
can create your own comparator class or use those that already exist in your 
portal. Once your list is sorted, you can split it into sections. This tutorial 
shows you how to sort your list Screenlet with a comparator and create sections
for your sorted list. 

+$$$

**Note:** To create a new comparator, you must create a class that extends the 
portal’s 
[`OrderByComparator` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/OrderByComparator.html) 
with your entity as a type argument. Then you must override the methods that 
implement the sort. For example, the portal’s 
[`EntryURLComparator` class](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/bookmarks/bookmarks-api/src/main/java/com/liferay/bookmarks/util/comparator/EntryURLComparator.java) 
sorts bookmarks in Liferay’s Bookmarks portlet by URL. 

$$$

First, you’ll learn how to use a comparator to sort your list Screenlet. 

## Using a Comparator [](id=using-a-comparator)

To use a comparator, you must set the list Screenlet's `obcClassName` property 
to the comparator's fully qualified class name. Do this in Interface Builder 
when inserting the Screenlet in an app, just as you would set any other 
Screenlet property. For example, to set the sample Bookmark List Screenlet to 
sort its list of bookmarks by URL, you must set *Obc Class Name* to 
*com.liferay.bookmarks.util.comparator.EntryURLComparator* in Interface Builder: 

![Figure 1: To use a comparator, set the *Obc Class Name* property in Interface Builder to the comparator's fully qualified class name.](../../../images/screens-ios-obc-ib.png)

That’s it! Note that although all list Screenlets inherit the `obcClassName` 
property from 
[the `BaseListScreenlet` class](https://github.com/liferay/liferay-screens/blob/develop/ios/Framework/Core/Base/BaseListScreenlet/BaseListScreenlet.swift), 
the list Screenlet must also make its service call with this property. See the 
[Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios) 
to see which list Screenlets included with Liferay Screens support the 
`obcClassName` property. Also, @product@'s comparator classes can change between 
versions. If you're using one of these comparators, make sure you specify the 
one that matches your @product@ version. 

## Create Sections for Your List [](id=create-sections-for-your-list)

Dividing lists into sections that contain like elements is common in iOS apps. 
To do this in list Screenlets, first use a comparator to sort the list by the 
criteria you'll use to create the sections. Then override the 
[`BookmarkListPageLoadInteractor` class’s](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/Interactor/BookmarkListPageLoadInteractor.swift) 
`sectionForRowObject` method in your list Screenlet’s Interactor. This method is 
called for each item in the list and should return the information necessary to 
place the item in a section. For example, 
[the sample Bookmark List Screenlet’s Interactor](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/Interactor/BookmarkListPageLoadInteractor.swift) 
overrides the `sectionForRowObject` method to group results by hostname: 

    public override func sectionForRowObject(object: AnyObject) -> String? {
        guard let bookmark = object as? Bookmark else {
            return nil
        }

        let host = NSURL(string: bookmark.url)?.host?.lowercaseString

        return host?.stringByReplacingOccurrencesOfString("www.", withString: "")
    }

Note that this only produces predictable results when Bookmark List Screenlet is 
sorted by `EntryURLComparator` as detailed in the preceding section. 

And that’s all there is to it! Now you know how to sort and section your list 
Screenlet’s list. 

## Related Topics [](id=related-topics)

[Creating iOS List Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-list-screenlets)

[Using Custom Cells with List Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-custom-cells-with-list-screenlets)

[Creating Complex Lists in Your List Screenlet](/develop/tutorials/-/knowledge_base/7-0/creating-complex-lists-in-your-list-screenlet)

[iOS Best Practices](/develop/tutorials/-/knowledge_base/7-0/ios-best-practices)
