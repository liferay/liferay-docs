# Creating Guestbook List Screenlet's UI [](id=creating-guestbook-list-screenlets-ui-ios)

In Liferay Screens for iOS, Screenlet UIs are called *Themes*. Every Screenlet 
must have at least one Theme. You'll use the following steps to create a Theme 
for Guestbook List Screenlet: 

1.  Create your Theme's folder and add it to your Xcode project. 
2.  Create an XIB file and use it to construct the UI. 
3.  Create your Theme's View class and set it as the XIB file's custom class. 

## Creating Your Theme's Folder [](id=creating-your-themes-folder)

Even if you only plan on creating one Theme, it's best practice to create it in 
its own folder inside a parent `Themes` folder. The parent `Themes` folder gives 
you a place to put any additional Themes you create. You'll create a single 
Theme for Guestbook List Screenlet: the Default Theme. You'll therefore create 
the `Themes/Default` folder path inside the `GuestbookListScreenlet` folder. 

Follow these steps to create your Theme's folder: 

1.  In the Finder, create the `Themes` folder inside your project's 
    `GuestbookListScreenlet` folder. Then create the `Default` folder inside the 
    new `Themes` folder. 

    ![Figure 1: The new `Themes/Default` folder structure should be inside the Screenlet's folder.](../../../images/ios-lp-theme-folders-finder.png)

2.  Drag and drop the `Themes` folder from the Finder into your Xcode project, 
    under the `GuestbookListScreenlet` folder. In the dialog that appears, 
    select *Copy items if needed*, *Create groups*, and the *Liferay Guestbook* 
    target. Then click *Finish*. The `Themes/Default` folder structure now 
    appears in your project. 

    ![Figure 2: After adding the `Themes` folder to your project, the `Themes/Default` folder structure should appear in the Project navigator.](../../../images/ios-lp-themes-proj-nav.png)

Now you're ready to start creating your Theme. First, you'll create its XIB 
file. 

## Creating the XIB File [](id=creating-the-xib-file)

A Theme requires an XIB file to define the UI's components and layout. Use these 
steps to create your Theme's XIB file: 

1.  In the Project navigator, right-click the `Default` folder and select 
    *New File*. In the dialog that appears, select *iOS* &rarr; *User Interface* 
    &rarr; *Empty*, and click *Next*. Name the file 
    `GuestbookListView_default.xib`, and ensure that *Default* is selected for 
    the save location and group. The *Liferay Guestbook* target should also be 
    selected. Click *Create*. The file should then open in Interface Builder. 

2.  In Interface Builder, drag and drop a View from the Object Library to the 
    canvas. Then add a Table View to the View. Set the Table View to take up the 
    entire View. 

3.  With the Table View selected, open the *Add New Constraints* menu at the 
    bottom-right of the canvas. In this menu, set *Spacing to nearest neighbor* 
    to 0 on all sides, select *Constrain to margins*, and then click the *Add 4 
    Constraints* button. 

    ![Figure 3: Add these constraints to the Table View in the XIB.](../../../images/ios-lp-xib-constraints.png)

Your Theme's XIB is now finished. Next, you'll create your View class. 

## Creating the Theme's View Class [](id=creating-the-themes-view-class)

Every Theme needs a View class that controls its behavior. Since the XIB file 
uses a `UITableView` to show a list of guestbooks, your View class must extend 
[the `BaseListTableView` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/TableView/BaseListTableView.swift). 
Liferay Screens provides this class to serve as the base class for list 
Screenlets' View classes. Since `BaseListTableView` provides most of the 
required functionality, extending it lets you focus on the parts of your View 
class that are unique to your Screenlet. 

Follow these steps to create your Screenlet's View class: 

1.  In the Project navigator, right-click the `Default` folder and select *New 
    File*. In the dialog that appears, fill out each screen as follows: 

    - Select *iOS* &rarr; *Source* &rarr; *Cocoa Touch Class*, and click *Next*. 
    - Name the class `GuestbookListView_default`, set it to extend 
      `BaseListTableView`, select *Swift* for the language, and click *Next*.
    - Make sure the `Default` folder and group is selected, as well as the 
      *Liferay Guestbook* target (these should be selected by default). Click 
      *Create*. 

2.  In `GuestbookListView_default`, add an import for `LiferayScreens` and 
    delete any placeholder comments in the class body. 

3.  Now you must override the View class methods that fill the table cells' 
    contents. There are two methods for this, depending on the cell type: 

    - **Normal cells:** the cells that show the entities. These cells typically 
    use `UILabel`, `UIImage`, or another UI component to show the entity. 
    Override the `doFillLoadedCell` method to fill these cells. Guestbook List 
    Screenlet's View class must override `doFillLoadedCell` to set each cell's 
    `textLabel` to a guestbook's name: 

            override public func doFillLoadedCell(row: Int, cell: UITableViewCell, 
                object: AnyObject) {

                    let guestbook = object as! GuestbookModel
                    cell.textLabel?.text = guestbook.name
            }

    - **Progress cell:** the cell at the bottom of the list that indicates the 
    list is loading the next page of items. Override the `doFillInProgressCell` 
    method to fill this cell. Guestbook List Screenlet's View class must 
    override this method to set the cell's `textLabel` to the string 
    `"Loading..."`: 

            override public func doFillInProgressCell(row: Int, cell: UITableViewCell) {
                cell.textLabel?.text = "Loading..."
            }

    Your complete View class should look like this: 

        import UIKit
        import LiferayScreens

        class GuestbookListView_default: BaseListTableView {

            override public func doFillLoadedCell(row: Int, cell: UITableViewCell, 
                object: AnyObject) {

                    let guestbook = object as! GuestbookModel
                    cell.textLabel?.text = guestbook.name
            }

            override public func doFillInProgressCell(row: Int, cell: UITableViewCell) {
                cell.textLabel?.text = "Loading..."
            }

        }

4.  Return to the Theme's XIB in Interface Builder, and set 
    `GuestbookListView_default` as the the parent View's custom class. To do 
    this, select the Table View's parent View, click the Identity inspector, and 
    enter `GuestbookListView_default` as the Custom Class. 

    ![Figure 4: In the XIB file, set the Custom Class of the Table View's parent View to `GuestbookListView_default`.](../../../images/ios-lp-gb-theme-custom-class.png)

5.  With the Theme's XIB still open in Interface Builder, set the parent View's 
    `tableView` outlet to the Table View. To do this, select the parent View and 
    click the Connections inspector. In the Outlets section, drag and drop from 
    the `tableView`'s circle icon (it turns into a plus icon on mouseover) to 
    the Table View in the XIB. The new outlet then appears in the Connections 
    inspector. 

    ![Figure 5: In the XIB, drag and drop from the `tableView` outlet to the Table View.](../../../images/ios-lp-table-view-outlet-01.png)

    ![Figure 6: After creating the connection, it appears in the Connections inspector.](../../../images/ios-lp-table-view-outlet-02.png)

Great! Your Theme is finished. Next, you'll create Guestbook List Screenlet's 
Connector. 
