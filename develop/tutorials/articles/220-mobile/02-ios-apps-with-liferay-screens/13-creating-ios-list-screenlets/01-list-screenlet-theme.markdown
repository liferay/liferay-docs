# Creating the Theme [](id=creating-the-view)

Recall that each Screenlet needs a Theme to serve as its UI. A Theme needs an 
XIB file to define the UI's components and layout. Since a list Screenlet 
displays a list of entities, its XIB file must contain a Table View. Use these 
steps to create your Theme's XIB file: 

1.  In Xcode, create a new XIB file and name it according to 
    [these naming conventions](/develop/tutorials/-/knowledge_base/7-0/ios-best-practices#naming-conventions). 
    For example, the XIB for Bookmark List Screenlet's Default Theme is 
    `BookmarkListView_default.xib`. 

2.  In Interface Builder, drag and drop a View from the Object Library to the 
    canvas. Then add a Table View to the View. 

3.  Resize the Table View to take up the entire View, and set the constraints 
    the Table View needs to maintain this size dynamically. This ensures that 
    the list fills the Screenlet's UI regardless of the iOS device's size or 
    orientation. 

For example, 
[Bookmark List Screenlet's XIB file](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/Themes/Default/BookmarkListView_default.xib) 
uses a `UITableView` inside a parent View to show the list of bookmarks. 

Now you'll create your Theme's View class. Every Theme needs a View class that 
controls its behavior. Since the XIB file uses a `UITableView` to show a list of 
guestbooks, your View class must extend 
[the `BaseListTableView` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/TableView/BaseListTableView.swift). 
Liferay Screens provides this class to serve as the base class for list 
Screenlets' View classes. Since `BaseListTableView` provides most of the 
required functionality, extending it lets you focus on the parts of your View 
class that are unique to your Screenlet. You must also configure the XIB file to 
use your View class.

Follow these steps to create your Screenlet's View class and configure the XIB 
file to use it: 

1.  Create your Theme's View class, and name it according to 
    [these naming conventions](/develop/tutorials/-/knowledge_base/7-0/ios-best-practices#naming-conventions). 
    Since the XIB uses `UITableView`, your View class must extend 
    `BaseListTableView`. For example, this is Bookmark List Screenlet's View 
    class declaration: 

        public class BookmarkListView_default: BaseListTableView {...

2.  Now you must override the View class methods that fill the table cells' 
    contents. There are two methods for this, depending on the cell type: 

    - **Normal cells:** the cells that show the entities. These cells typically 
    use `UILabel`, `UIImage`, or another UI component to show the entity. 
    Override the `doFillLoadedCell` method to fill these cells. For example, 
    Bookmark List Screenlet's View class overrides `doFillLoadedCell` to set 
    each cell's `textLabel` to a bookmark's name: 

            override public func doFillLoadedCell(row row: Int, cell: UITableViewCell, 
                object: AnyObject) {

                    let bookmark = object as! Bookmark

                    cell.textLabel?.text = bookmark.name
            }

    - **Progress cell:** the cell at the bottom of the list that indicates the 
    list is loading the next page of items. Override the `doFillInProgressCell` 
    method to fill this cell. For example, Bookmark List Screenlet's View class 
    overrides this method to set the cell's `textLabel` to the string 
    `"Loading..."`: 

            override public func doFillInProgressCell(row row: Int, cell: UITableViewCell) {
                cell.textLabel?.text = "Loading..."
            }

3.  Return to the Theme's XIB in Interface Builder, and set the View class as 
    the the parent View's custom class. For example, if you were doing this for 
    Bookmark List Screenlet you'd select the Table View's parent View, click the 
    Identity inspector, and enter `BookmarkListView_default` as the custom 
    class. 

4.  With the Theme's XIB still open in Interface Builder, set the parent View's 
    `tableView` outlet to the Table View. To do this, select the parent View and 
    click the Connections inspector. In the Outlets section, drag and drop from 
    the `tableView`'s circle icon (it turns into a plus icon on mouseover) to 
    the Table View in the XIB. The new outlet then appears in the Connections 
    inspector. 

That's it! Now that your Theme is finished, you can create the Connector. 
