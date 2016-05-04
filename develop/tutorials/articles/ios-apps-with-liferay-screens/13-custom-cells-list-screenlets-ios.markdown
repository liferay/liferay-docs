# Using Custom Cells with List Screenlets [](id=using-custom-cells-with-list-screenlets)

Liferay Screens's Default theme uses the 
[`UITableView` class](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UITableView_Class/) 
as a UI component for list Screenlets. Although this works fine for showing 
simple lists of items, you may want to use custom cells to spruce things up a 
bit or show more complex content in the list. 

To do this, you must 
[create a custom theme](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes) 
that extends the Default theme of the list Screenlet you want to use. For 
example, if you're using 
[Asset List Screenlet](/develop/reference/-/knowledge_base/6-2/assetlistscreenlet-for-ios), 
create a child class in your theme that extends from the 
[`AssetListView_default` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Themes/Default/AssetListScreenlet/AssetListView_default.swift). 
You can call your class `AssetListView_mytheme`. 

Then create the XIB file for your custom cell. As usual, create this file and 
its companion class, and create as many outlets and actions as you need. If you 
want to use different layouts for different rows, create several XIB files and 
companion classes. 

Now you're ready to implement your theme's functionality. In your 
`*ListView_mytheme` class, override the following method to register your custom 
cell:

    override public func doRegisterCellNibs() {
        let customNib = UINib(nibName: "MyCellName", bundle: nil)

        tableView?.registerNib(customNib, forCellReuseIdentifier: "myCellId")

        // register other cells...
    }

To get the cell ID for each row, override the `doGetCellId` method. This 
method's contents depend on your use case. For example, if you intend to use 
your theme with Asset List Screenlet, the method could look like this: 

    override public func doGetCellId(row row: Int, object: AnyObject?) -> String {
        // Return the identifier for this row and object.
        // "object" may be an instance of Asset or WebContent, depending the actual object retrieved
        // If "object" is nil, this row is "in progress".

        // sample implementation:
        if object == nil {
            return "wipCell"
        }

        return (object is WebContent) ? "webcontentCell" : "assetCell"
    }

Alternatively, if you intend to use the theme with 
[Web Content List Screenlet](/develop/reference/-/knowledge_base/6-2/web-content-list-screenlet-for-ios), 
`doGetCellId` could look like this: 

    override public func doGetCellId(row row: Int, object: AnyObject?) -> String {
        // Return the identifier for this row and object.
        // "object" will be an instance of WebContent or nil if this row is "in progress".

        // sample implementation:
        return (object == nil) ? "wipCell" : "webcontentCell"
    }

Next, you must override the `doCreateCell` method to create the cell when 
needed, depending on the cell type. Again, this method's contents depend on your 
use case. For example, if you intend to use your theme with Asset List 
Screenlet, the method could look like this: 

    override public func doCreateCell(cellId: String) -> UITableViewCell {
        // Create a new cell when needed.

        // sample implementation:
        switch cellId {
        case "webcontentCell":
            return MyWebContentCell(style: .Default, reuseIdentifier: cellId)
        case "wipCell":
            return MyInProgressCell(style: .Default, reuseIdentifier: cellId)
        }

        // calling super will create a regular UITableViewCell
        return super.doCreateCell(cellId)
    }

Alternatively, if you intend to use the theme with Web Content List Screenlet, 
`doCreateCell` could look like this: 

    override public func doCreateCell(cellId: String) -> UITableViewCell {
        // Create a new cell when needed.

        // sample implementation:
        if cellId == "webcontentCell" {
            return MyWebContentCell(style: .Default, reuseIdentifier: cellId)
        }

        return MyInProgressCell(style: .Default, reuseIdentifier: cellId)
    }

To fill the cell with the row's data, override the `doFillLoadedCell` method. 
Note that this method isn't called for in-progress cells; it's only called for 
cells with data: 

    override public func doFillLoadedCell(row row: Int, cell: UITableViewCell, object: AnyObject) {
        // Fill the cell from the object supplied.

        // sample implementation:
        if let entry = object as? WebContent, cell as? MyWebContentCell {
            cell.outlet = entry.property
            cell.outlet = entry.property
            cell.outlet = entry.property
            cell.outlet = entry.property
            ...
            cell.accessoryType = .None
            cell.accessoryView = nil
        }
    }

To show something different for in-progress cells, you must override a different 
method: `doFillInProgressCell`. Remember this step is optional and if you don't 
do it, the Default theme's style is used for in-progress cells: 

    override public func doFillInProgressCell(row row: Int, cell: UITableViewCell) {
        // Because we created a concrete cell type for "in progress" cells, we need to cast it here.
        // Otherwise, a regular UITableViewCell will be received
        let wipCell = cell as! MyInProgressCell

        wipCell.textLabel?.text = "Loading..."
        wipCell.accessoryType = .None
        let myImage = ...
        wipCell.accessoryView = UIImageView(image: myImage)
        wipCell.accessoryView?.frame = CGRectMake(0, 0, myImage.size.width, myImage.size.height)
    }

Remember that you also have the typical 
[`UITableViewDelegate` protocol](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UITableViewDelegate_Protocol/) 
and 
[`UITableViewDataSource` protocol](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UITableViewDataSource_Protocol/) 
methods available to you, so you can override any of them if you need to (check 
first to make sure they're not already overridden). For example, implement the 
following method to use a different cell height for one row: 

    public func tableView(tableView: UITableView, heightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {

        // sample implementation:
        let cellId = doGetCellId(row: indexPath.row, object: rows[indexPath.row])

        switch cellId {
        case "webcontentCell":
            return 50.0
        case "wipCell":
            return 40.0
        }

        return 75.0
    }

Great! Now you know how to implement your own custom cells for use in list 
Screenlets. 

**Related Topics**

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes)

[Creating iOS List Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-ios-list-screenlets)

[Asset List Screenlet for iOS](/develop/reference/-/knowledge_base/6-2/assetlistscreenlet-for-ios)

[Web Content List Screenlet for iOS](/develop/reference/-/knowledge_base/6-2/web-content-list-screenlet-for-ios)

[DDL List Screenlet for iOS](/develop/reference/-/knowledge_base/6-2/ddllistscreenlet-for-ios)
