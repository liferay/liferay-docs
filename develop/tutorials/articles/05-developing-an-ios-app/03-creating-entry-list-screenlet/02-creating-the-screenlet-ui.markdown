# Creating Entry List Screenlet's UI [](id=creating-entry-list-screenlets-ui-ios)

Recall that in Liferay Screens for iOS, Screenlet UIs are called *Themes*, and 
every Screenlet must have at least one Theme. You'll create Entry List 
Screenlet's Theme with the same steps you used to create Guestbook List 
Screenlet's Theme: 

1.  Create your Theme's folder and add it to your Xcode project. 
2.  Create an XIB file and use it to construct the UI. 
3.  Create your Theme's View class and set it as the XIB file's custom class. 

## Creating Your Theme's Folder [](id=creating-your-themes-folder)

Like Guestbook List Screenlet's Theme, you'll create Entry List Screenlet's 
Theme in a `Themes/Default` folder. Follow these steps to create this folder 
structure in Entry List Screenlet's folder: 

1.  In the Finder, create the `Themes` folder inside your project's 
    `EntryListScreenlet` folder. Then create the `Default` folder inside the 
    new `Themes` folder. 

2.  Drag and drop the `Themes` folder from the Finder into your Xcode project, 
    under the `EntryListScreenlet` folder. In the dialog that appears, select 
    *Copy items if needed*, *Create groups*, and the *Liferay Guestbook* target 
    (these should be selected by default). Then click *Finish*. The 
    `Themes/Default` folder structure now appears in your project. 

    ![Figure 1: After adding the `Themes` folder to Entry List Screenlet, the `Themes/Default` folder structure should look like this in the Project navigator.](../../../images/ios-lp-themes-proj-nav-02.png)

Now you're ready to start creating your Theme. First, you'll create its XIB 
file. 

## Creating the XIB File [](id=creating-the-xib-file)

A Theme requires an XIB file to define the UI's components and layout. Use these 
steps to create your Theme's XIB file: 

1.  In the Project navigator, right-click the `Default` folder you added above 
    and select *New File*. In the dialog that appears, select *iOS* &rarr; *User 
    Interface* &rarr; *Empty*, and click *Next*. Name the file 
    `EntryListView_default.xib`, and ensure that *Default* is selected for 
    the save location and group. The *Liferay Guestbook* target should also be 
    selected. Click *Create*. The file then opens in Interface Builder. 

2.  In Interface Builder, drag and drop a View from the Object Library onto the 
    canvas. Then add a Table View to the View. Set the Table View to take up the 
    entire View. 

3.  With the Table View selected, open the *Add New Constraints* menu at the 
    bottom-right of the canvas. In this menu, set *Spacing to nearest neighbor* 
    to 0 on all sides, select *Constrain to margins*, and then click the *Add 4 
    Constraints* button. 

    ![Figure 2: Add these constraints to the Table View in the XIB.](../../../images/ios-lp-xib-constraints.png)

Your Theme's XIB is now finished. Next, you'll create your View class. 

## Creating the Theme's View Class [](id=creating-the-themes-view-class)

Every Theme needs a View class that controls its behavior. Recall that a list 
Screenlet's View class gets most of its functionality by extending 
[the `BaseListTableView` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/TableView/BaseListTableView.swift). 
This lets you focus on the parts of your View class that are unique to your 
Screenlet. 

Follow these steps to create your Screenlet's View class: 

1.  In the Project navigator, right-click Entry List Screenlet's `Default` 
    folder and select *New File*. In the dialog that appears, fill out each 
    screen as follows: 

    - Select *iOS* &rarr; *Source* &rarr; *Cocoa Touch Class*, and click *Next*. 
    - Name the class `EntryListView_default`, set it to extend 
      `BaseListTableView`, select *Swift* for the language, and click *Next*.
    - Make sure the `Default` folder and group is selected, as well as the 
      *Liferay Guestbook* target (these should be selected by default). Click 
      *Create*. 

2.  Replace the class file's contents with this code: 

        import UIKit
        import LiferayScreens

        class EntryListView_default: BaseListTableView {

            override public func doFillLoadedCell(row: Int, cell: UITableViewCell, object: AnyObject) {
                let entry = object as! EntryModel

                cell.textLabel?.text = entry.message
                cell.detailTextLabel?.text = entry.name
            }

            override open func doCreateCell(_ cellId: String) -> UITableViewCell {
                return UITableViewCell(style: .subtitle, reuseIdentifier: cellId)
            }

            override public func doFillInProgressCell(row: Int, cell: UITableViewCell) {
                cell.textLabel?.text = "Loading..."
            }

        }

    Note that this class is almost identical to Guestbook List Screenlet's View 
    class, `GuestbookListView_default`. The only difference is that 
    `EntryListView_default` handles entries (`EntryModel`) instead of guestbooks 
    (`GuestbookModel`). The `doFillLoadedCell` method sets the cell's main text 
    label to the entry's message, and sets the cell's secondary text label to 
    the name of the person who left the message. This way, a single cell 
    displays both pieces of information. For a description of the code shared 
    with `GuestbookListView_default`, see 
    [the article on creating Guestbook List Screenlet's Theme](/develop/tutorials/-/knowledge_base/7-0/creating-guestbook-list-screenlets-ui-ios). 

3.  Return to the Theme's XIB in Interface Builder and set 
    `EntryListView_default` as the the parent View's custom class. To do this, 
    select the Table View's parent View, click the Identity inspector, and enter 
    `EntryListView_default` as the custom class. 

    ![Figure 3: In the XIB file, set the custom class of the Table View's parent View to `EntryListView_default`.](../../../images/ios-lp-entry-theme-custom-class.png)

4.  With the Theme's XIB still open in Interface Builder, set the parent View's 
    `tableView` outlet to the Table View. To do this, select the parent View and 
    click the Connections inspector. In the *Outlets* section, drag and drop 
    from the `tableView`'s circle icon (on mouseover, it turns into a plus icon) 
    to the Table View in the XIB. The new outlet then appears in the Connections 
    inspector. 

    ![Figure 4: Drag and drop from the `tableView` outlet to the Table View in the XIB.](../../../images/ios-lp-table-view-outlet-01.png)

    ![Figure 5: After creating the connection, the outlet looks like this in the Connections inspector.](../../../images/ios-lp-table-view-outlet-02.png)

Great! Your Theme is finished. Next, you'll create Entry List Screenlet's 
Connector. 
