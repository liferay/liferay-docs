# Using Custom Cells with List Screenlets [](id=using-custom-cells-with-list-screenlets)

In most list Screenlets, including those that come with Liferay Screens, the 
Default Theme uses the default cells in 
[iOS’s `UITableView`](https://developer.apple.com/reference/uikit/uitableview) 
to show the list. 
[The Theme creation steps](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes#creating-the-view) 
in the list Screenlet creation tutorial also instruct you to use these cells. 
You can, however, use custom cells to tailor the list to your needs. To do this, 
you must 
[create an extended Theme](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes)
from a Theme that uses `UITableView`’s default cells. This usually means 
extending a list Screenlet’s Default theme. This tutorial shows you how to 
create such an extended Theme that contains a custom cell for your list 
Screenlet. As an example, this tutorial uses code from the sample Bookmark List 
Screenlet’s Custom Theme. You can refer to this Theme’s finished code 
[here in GitHub](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/BookmarkListScreenlet/Themes/TableView) 
at any time. 

Note that besides creating your custom cell, this tutorial follows the same 
basic steps as the 
[Theme creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes) 
for creating an extended Theme. For example, you must still 
[determine where to create your Theme](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes#determining-your-themes-location), 
and create your Theme’s XIB and View class. 

First, you’ll create your Theme’s custom cell. 

## Creating Your Custom Cell [](id=creating-your-custom-cell)

Once you 
[decide where to create your Theme](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes#determining-your-themes-location), 
you can get started. First, create your custom cell's XIB file and its companion 
class. Name them according to 
[the naming conventions in the best practices tutorial](/develop/tutorials/-/knowledge_base/7-0/ios-best-practices#naming-conventions). 
After defining your cell’s UI in the XIB, create as many outlets and actions as 
you need in its companion class. Also be sure to assign this class as the XIB’s 
custom class in Interface Builder. Note that if you want to use different 
layouts for different rows, you must create an XIB file and companion class for 
each. 

For example, the following screenshot shows the XIB file 
`BookmarkCell_default-custom.xib` for Bookmark List Screenlet's custom cell. 
This cell must show a bookmark's name and URL, so it contains two labels. 

![Figure 1: The XIB file for Bookmark List Screenlet’s custom cell.](../../../images/screens-ios-xcode-custom-cell.png)

This XIB's custom class, `BookmarkCell_default_custom`, contains an outlet for 
each label. The `bookmark` variable also contains a `didSet` observer that sets 
the bookmark's name and URL to the respective label: 

    import UIKit

    class BookmarkCell_default_custom: UITableViewCell {

        @IBOutlet weak var nameLabel: UILabel?
        @IBOutlet weak var urlLabel: UILabel?

        var bookmark: Bookmark? {
            didSet {
                nameLabel?.text = bookmark?.name
                urlLabel?.text = bookmark?.url
            }
        }

    }

Great! Now you have your custom cell. Next, you’ll create the rest of your 
Theme. 

## Creating Your Theme’s XIB and View Class [](id=creating-your-themes-xib-and-view-class)

Now you're ready to create your Theme’s XIB file and View class. Create your XIB 
by copying the parent Theme’s XIB and making any changes you need. You may not 
need to make any changes besides the file name and custom class name. For 
example, the custom cell is the only difference between Bookmark List 
Screenlet’s Custom and Default Themes. These Themes’ XIB files 
(`BookmarkListView_default-custom.xib` and `BookmarkListView_default.xib`) are 
therefore identical besides their name and custom class; the size and position 
of their UI components are the same. 

Now create your View class by extending the parent Theme’s View class. You 
should also add a string constant to serve as the cell ID. In a moment, you’ll 
use this constant to register your custom cell. For example, the View class in 
Bookmark List Screenlet’s Custom Theme (`BookmarkListView_default_custom`) 
extends the Default Theme’s View class (`BookmarkListView_default`) and defines 
the string constant `BookmarkCellId`: 

    public class BookmarkListView_default_custom: BookmarkListView_default {

        let BookmarkCellId = "bookmarkCell"
        …

Next, override the `doRegisterCellNibs` method to register your custom cell. In 
this method, create a `UINib` instance for your cell and then register it with 
the `UITableView` instance (`tableView`) inherited from 
[the `BaseListTableView` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/TableView/BaseListTableView.swift). 
When registering the nib file, you must use the string constant you created 
earlier as the `forCellReuseIdentifier`. For example, here’s the 
`doRegisterCellNibs` method in `BookmarkListView_default-custom`: 

      public override func doRegisterCellNibs() {
          let nib = UINib(nibName: "BookmarkCell_default-custom", bundle: NSBundle.mainBundle())

          tableView?.registerNib(nib, forCellReuseIdentifier: BookmarkCellId)
    }

Also in your View class, override the `doGetCellId` method to return the cell ID 
for each row. All you need to do in this method is return the string constant 
you created earlier. For example, the `doGetCellId` method in 
`BookmarkListView_default-custom` returns the `BookmarkCellId` constant: 

    override public func doGetCellId(row row: Int, object: AnyObject?) -> String {
        return BookmarkCellId
    }

Now override the `doFillLoadedCell` method to fill the cell with data. Note that 
this method isn't called for in-progress cells; it's only called for cells that 
display data. Also note that this method's `object` argument contains the data 
as `AnyObject`. You must cast this to your desired type and then set it to the 
appropriate cell variable. For example, the `doFillLoadedCell` method in 
`BookmarkListView_default-custom` casts the `object` argument to `Bookmark` and 
then sets it to the cell's `bookmark` variable: 

    override public func doFillLoadedCell(row row: Int, cell: UITableViewCell, object:AnyObject) {
        if let bookmarkCell = cell as? BookmarkCell_default_custom, bookmark = object as? Bookmark {
            bookmarkCell.bookmark = bookmark
        }
    }

The typical iOS 
[`UITableViewDelegate` protocol](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UITableViewDelegate_Protocol/) 
and 
[`UITableViewDataSource` protocol](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UITableViewDataSource_Protocol/) 
methods are also available in your View class. You can override any of them if 
you need to (check first to make sure they're not already overridden). For 
example, `BookmarkListView_default-custom` implements the following method to 
use a different cell height for each row: 

    public func tableView(tableView: UITableView, heightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {
        return 80
    }

When you finish, set your View class as your XIB file’s custom class. 

Awesome! You’re done! Now you know how to implement your own custom cells for 
use in list Screenlets. 

## Related Topics [](id=related-topics)

[Creating iOS List Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-list-screenlets)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes)

[Sorting Your List Screenlet](/develop/tutorials/-/knowledge_base/7-0/sorting-your-list-screenlet)

[Creating Complex Lists in Your List Screenlet](/develop/tutorials/-/knowledge_base/7-0/creating-complex-lists-in-your-list-screenlet)

[iOS Best Practices](/develop/tutorials/-/knowledge_base/7-0/ios-best-practices)
