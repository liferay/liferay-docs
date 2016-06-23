# Creating iOS List Screenlets [](id=creating-ios-list-screenlets)

It's very common for mobile apps to display lists of entities. Liferay Screens 
lets you display asset lists and DDL lists in your iOS app by using 
[Asset List Screenlet](/develop/reference/-/knowledge_base/7-0/assetlistscreenlet-for-ios) 
and 
[DDL List Screenlet](/develop/reference/-/knowledge_base/7-0/ddllistscreenlet-for-ios), 
respectively. For your app to display a list of other entities from a Liferay 
instance, however, you must create your own list Screenlet. You can create this 
Screenlet to display standard Liferay entities such as `User`, or custom 
entities that belong to custom Liferay plugins. 

This tutorial shows you how to create your own list Screenlet. As an example, 
you'll create a Screenlet that displays a list of bookmarks from Liferay's 
Bookmarks portlet--Bookmark List Screenlet. You can find the finished 
Screenlet's code 
[here in GitHub](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/BookmarkListScreenlet). 

Note that this tutorial doesn't explain the general Screenlet concepts and 
components in detail. Focus is instead placed on creating a Screenlet that 
displays lists of entities. Before beginning, you should therefore read the 
[Screens architecture tutorial](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios), 
and the general 
[Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets). 

You'll create the list Screenlet by following these steps:

1. Creating the Model Class.

2. Creating the Screenlet's View.

3. Creating the Screenlet's Connector.

4. Creating the Screenlet's Interactor.

5. Creating the Screenlet's delegate.

6. Creating the Screenlet class.

First though, you should understand how pagination works with list Screenlets. 

## Pagination [](id=pagination)

To ensure that users can scroll smoothly through large lists of items, list 
Screenlets should support 
[fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination). 
In cases where you only have a small list of items, however, you can skip this. 
For example, if you want to list the days of the week, you don't need to 
implement fluent pagination. 

Liferay Screens gives you some tools to implement fluent pagination with 
configurable page size, as described in the above link. Asset List Screenlet and 
DDL List Screenlet use this approach. 

Now you're ready to start creating your list Screenlet! 

## Creating the Model Class [](id=creating-the-model-class)

Each entity retrieved from Liferay typically returns from the server as 
`[String:AnyObject]`, where `String` is the matching Liferay entity's attribute, 
and `AnyObject` is the attribute's value. To work conveniently with these 
results in your Screenlet, you must create a model class that converts each 
entity into an object that represents the corresponding Liferay entity. For 
example, to represent bookmarks in Bookmark List Screenlet, you must create a 
class that creates a `Bookmark` objects for each `[String:AnyObject]` that comes 
back from the server. Create this class now: 

    @objc public class Bookmark : NSObject {

        public let attributes: [String:AnyObject]

        public var name: String {
            return attributes["name"] as! String
        }

        override public var description: String {
            return attributes["description"] as! String
        }

        public var url: String {
            return attributes["url"] as! String
        }

        public init(attributes: [String:AnyObject]) {
            self.attributes = attributes
        }

    }

This class defines computed properties to return the attribute values for each 
bookmark's name and URL. Note that in the example code in GitHub, this class is 
in the same file (`BookmarkListScreenlet.swift`) as the Screenlet class. You 
don't have to create the model class there--you can create it anywhere you like. 

Next, you'll create your Screenlet's View. 

## Creating the View [](id=creating-the-view)

Recall that each Screenlet requires a View to serve as its UI. In Xcode, first 
create a new XIB file called `BookmarkListView_default.xib`. Use Interface 
Builder to construct your Screenlet's UI in this file. Since the Screenlet must 
show a list of items, you should add `UITableView` to this XIB. For example, 
[Bookmark List Screenlet's XIB file](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/BookmarkListView_default.xib) 
uses a simple `UITableView` to show the list of bookmarks. 

Now create a new View class with a name that matches that of the XIB file's 
prefix. Since the XIB uses `UITableView`, your View class must extend 
`BaseListTableView`. For example, this is Bookmark List Screenlet's View class 
declaration: 

    public class BookmarkListView_default: BaseListTableView {...

In Interface Builder, set this new class as your XIB's Custom Class, and assign 
the `tableView` outlet to your `UITableView` component. 

Next, you must set the style and contents of the table's cells (rows). If your 
entities are simple you can use the default table cell style. For example, the 
default works great if you only need to display a single piece of text in each 
cell. To display much more than this, however, you must create a custom table 
cell. This is the case with Bookmark List Screenlet, which must display each 
bookmark's name and URL. To create a custom table cell, first create an XIB 
file and accompanying class. The XIB should contain a table view cell, and the 
class should extend `UITableViewCell`. Be sure to set the class as your XIB's 
Custom Class in Interface Builder. Add any other UI components you need in the 
XIB's table view cell, and create outlets to them in the class. For example, 
`BookmarkCell_default.xib` and `BookmarkCell_default.swift` define the custom 
table cell in Bookmark List Screenlet. The XIB contains a table view cell with 
two labels: one for the bookmark's name and the other for its URL. The class 
extends `UITableViewCell` and contains outlets that store each label's text as 
`UILabel`: 

    class BookmarkCell_default: UITableViewCell {

        @IBOutlet weak var nameLabel: UILabel!
        @IBOutlet weak var urlLabel: UILabel!
    }

Next, you must override the methods in the View class that fill the table cells' 
contents. There are two methods for this, depending on the type of cell being 
filled: 

- **Normal cells:** the cells that show the entities. These cells typically use 
  `UILabel`, `UIImage`, or any other UI component to show the entity's 
  attributes. Override the `doFillLoadedCell` method to fill this type of cell. 
- **Progress cell:** the cell shown at the bottom of the list to indicate that 
  the list is loading the next page of items. This typically includes a 
  `UIActivityIndicator` or similar component. Override the 
  `doFillInProgressCell` method to fill this type of cell. 

In Bookmark List Screenlet, you must override `doFillLoadedCell` to set each 
cell's `nameLabel` and `urlLabel` to a bookmark's name and URL, respectively. 
Add this method in the `BookmarkListView_default` class now: 

    override public func doFillLoadedCell(row row: Int, cell: UITableViewCell, object: AnyObject) {
        let bookmarkCell = cell as! BookmarkCell_default
        let bookmark = object as! Bookmark

        bookmarkCell.nameLabel.text = bookmark.name
        bookmarkCell.urlLabel.text = bookmark.url
    }

Now you'll override Bookmark List Screenlet's `doFillInProgressCell` method. 
There's no need to do anything fancy here to indicate that content is loading. 
Simply set the cell's `nameLabel` to the string `"Loading..."`:

    override public func doFillInProgressCell(row row: Int, cell: UITableViewCell) {
    
        let bookmarkCell = cell as! BookmarkCell_default
        bookmarkCell.nameLabel.text = "Loading..."
    }

Next, you must configure the cells to use the custom table cell's XIB file. If 
your Screenlet only uses one custom table cell, as is the case in Bookmark List 
Screenlet, then you must do this by overriding the `doRegisterCellNib` method. 
This method must load and register the XIB file to use for the cells. Add this 
method to Bookmark List Screenlet's View class: 

    override public func doRegisterCellNib(id id: String) {
        let nib = UINib(nibName: "BookmarkCell_default", bundle: NSBundle.mainBundle())

        tableView?.registerNib(nib, forCellReuseIdentifier: id)
    }

Note that there may be cases where you need to use several custom table cells. 
This is common if your data can exist in multiple states. In this case, you must 
override the `doRegisterCellNib` method to register all the necessary XIB files 
using your own cell identifiers. Then override the `doDequeueReusableCell` 
method to dequeue the cell for the supplied identifier. For example, in Bookmark 
List Screenlet you may want to use a different cell style to display a bookmark 
that has an invalid URL. The following `doDequeueReusableCell` method is an 
example of cells with the IDs `firstCellRow` or `regularCell` being dequeued: 

    public func doDequeueReusableCell(row row: Int) -> UITableViewCell {
        let cellId = (row == 1) ? "firstCellRow" : "regularCell"

        return tableView?.dequeueReusableCellWithIdentifier(cellId)
            ?? UITableViewCell(style: .Default, reuseIdentifier: cellId)
    }

Now that your View is finished, you can create the Connector. 

## Creating the Connector [](id=creating-the-connector)

Recall that a Screenlet's Connector makes the call that retrieves data from the 
server. To make a Connector that supports fluent pagination, your Connector 
class must extend `PaginationLiferayConnector`. Your Connector class should also 
contain any properties it requires to retrieve data. For example, the Bookmark 
List Screenlet must retrieve bookmarks from a Bookmarks portlet in a specific 
site. It must also retrieve bookmarks from a specific folder within that 
Bookmarks portlet. The Screenlet's Connector class must therefore contain 
properties for the `groupId` (site ID) and `folderId` (Bookmarks folder ID), and 
an initializer that sets them. Create this class now as follows:

    public class BookmarkListPageLiferayConnector: PaginationLiferayConnector {

        public let groupId: Int64
        public let folderId: Int64

        public init(startRow: Int, endRow: Int, computeRowCount: Bool, groupId: Int64, folderId: Int64) {
            self.groupId = groupId
            self.folderId = folderId

            super.init(startRow: startRow, endRow: endRow, computeRowCount: computeRowCount)
        }
    }

Next, you must override two methods in the Connector class: one to retrieve the 
page's rows, and the other to retrieve the row count:

    override public func doAddPageRowsServiceCall(session session: LRBatchSession, startRow: Int, 
        endRow: Int) {
            // Write the Liferay Mobile SDK service call to retrieve records from 
            // startRow and endRow.
    }

    override public func doAddRowCountServiceCall(session session: LRBatchSession) {
        // Write the Liferay Mobile SDK service call to retrieve the row count.
    }

In Bookmark List Screenlet, add these methods to the Connector class as follows: 

    override public func doAddPageRowsServiceCall(session session: LRBatchSession, startRow: Int, 
        endRow: Int) {
            let service = LRBookmarksEntryService_v62(session: session)

            do {
                try service.getEntriesWithGroupId(groupId,
                folderId: folderId,
                start: Int32(startRow),
                end: Int32(endRow))
            }
            catch  {
                // ignore error: the method returns nil (converted to an error) 
                // because the request is not actually sent
            }
    }

    override public func doAddRowCountServiceCall(session session: LRBatchSession) {
        let service = LRBookmarksEntryService_v62(session: session)

        do {
            try service.getEntriesCountWithGroupId(groupId, folderId: folderId)
        }
        catch  {
            // ignore error: the method returns nil (converted to an error) 
            // because the request is not actually sent
        }
    }

Now that you have your Connector class, you're ready to create the Screenlet's 
Interactor. 

## Creating the Interactor [](id=creating-the-interactor)

Recall that Screenlet Interactors respond to user actions. In list Screenlets, 
loading entities is usually the only action a user can take. The app developer 
can trigger this action in response to a manual user action by calling the 
`loadList` or `refreshList` methods. Alternatively, the app developer can use 
the `autoLoad` property to trigger the action automatically when the Screenlet 
appears on the screen. 

The Interactor class of a list Screenlet that implements fluent pagination 
must extend `BaseListPageLoadInteractor`. Your Interactor class must also 
contain any properties required for the Screenlet to function, and an 
initializer that sets them. Note that this initializer takes `BaseListScreenlet` 
as an argument. In a couple of steps, you'll create your Screenlet class to 
extend `BaseListScreenlet`. 

As an example, you'll now create Bookmark List Screenlet's Interactor class. 
This class must contain the same `groupId` and `folderId` properties as the 
Connector, and an initializer that sets them. Note that this initializer uses 
`LiferayServerContext` to ensure that it contains a valid `groupId`. Create 
the Interactor class as follows:

    public class BookmarkListPageLoadInteractor : BaseListPageLoadInteractor {

        private let groupId: Int64
        private let folderId: Int64

        init(screenlet: BaseListScreenlet,
            page: Int,
            computeRowCount: Bool,
            groupId: Int64,
            folderId: Int64) {

                self.groupId = (groupId != 0) ? groupId : LiferayServerContext.groupId
                self.folderId = folderId

                super.init(screenlet: screenlet, page: page, computeRowCount: computeRowCount)
        }
    }

The Interactor class must also initiate the server request via the Connector, 
and convert the results into model objects. You'll override the 
`createConnector` method to create a Connector instance that initiates the 
request. If your Screenlet implements fluent pagination, as Bookmark List 
Screenlet does, your `createConnector` method must convert the page number (the 
Interactor's `page` attribute) to the page's start and end record numbers. Use 
the `firstRowForPage` method to do this. Add the following `createConnector` 
method to the `BookmarkListPageLoadInteractor`:

    override public func createConnector() -> PaginationLiferayConnector {
        let screenlet = self.screenlet as! BaseListScreenlet

        return BookmarkListPageLiferayConnector(
            startRow: screenlet.firstRowForPage(self.page),
            endRow: screenlet.firstRowForPage(self.page + 1),
            computeRowCount: self.computeRowCount,
            groupId: groupId,
            folderId: folderId)
    }

Similarly, you'll override the `convertResult` method in the Interactor class to 
convert each result into a model object. The Screenlet calls this method once 
for each entity retrieved from the server, with an entity as the method's only 
argument. For Bookmark List Screenlet, you can therefore override this method to 
create a `Bookmark` instance for each entity:

    override public func convertResult(serverResult: [String:AnyObject]) -> AnyObject {
        return Bookmark(attributes: serverResult)
    }

There's one more thing you may want to add to your Interactor class before 
moving on: support for offline mode. To support offline mode, the Interactor 
must return a cache key unique to your Screenlet. You'll do this by overriding 
the `cacheKey` method in the Interactor class. Since this Screenlet shows 
bookmarks that correspond to a `groupId` and `folderId`, the cache key must 
include these values. Override this method now: 

    override public func cacheKey(op: PaginationLiferayConnector) -> String {
        return "\(groupId)-\(folderId)"
    }

Nice work! Next, you'll create your Screenlet's delegate so that app developers 
can respond to events in your Screenlet. 

## Creating the Delegate [](id=creating-the-delegate)

For the app developer to respond to events in your Screenlet, you must create a 
delegate protocol. This protocol must define the methods required to respond to 
the Screenlet's events. You'll define the following three methods:

- `screenlet(_:onBookmarkListResponse:)`: Returns the `Bookmark` results when 
  the server call succeeds. 

- `screenlet(_:onBookmarkListError:)`: Returns the `NSError` object when the 
  server call fails. 

- `screenlet(_:onBookmarkSelected:)`: Returns the `Bookmark` when a user selects 
  it in the list. 

Create this protocol now:

    @objc public protocol BookmarkListScreenletDelegate : BaseScreenletDelegate {

        optional func screenlet(screenlet: BookmarkListScreenlet,
            onBookmarkListResponse bookmarks: [Bookmark])

        optional func screenlet(screenlet: BookmarkListScreenlet,
            onBookmarkListError error: NSError)

        optional func screenlet(screenlet: BookmarkListScreenlet,
            onBookmarkSelected bookmark: Bookmark)
    }

Note that these methods are optional. This means that the delegate class doesn't 
have to implement them. Also note that in this Screenlet's example code in 
GitHub, this protocol is defined in the same file 
(`BookmarkListScreenlet.swift`) as the Screenlet class. You don't have to create 
the protocol there--you can create it anywhere you like. 

Next, you'll create the Screenlet class. 

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

Now that your Screenlet's other components exist, you can create the Screenlet 
class. A list Screenlet's Screenlet class must extend `BaseListScreenlet`. You 
should also annotate the Screenlet class as `IBDesignable`. Your Screenlet class 
must also define the configuration properties required for the Screenlet to 
work. You should define these as `IBInspectable` properties. 

Bookmark List Screenlet's Screenlet class requires properties for the `groupId` 
and `folderId`. If you want to support offline mode, you should also add an 
`offlinePolicy` property. Create the `BookmarkListScreenlet` class as follows: 

    @IBDesignable public class BookmarkListScreenlet: BaseListScreenlet {

        @IBInspectable public var groupId: Int64 = 0
        @IBInspectable public var folderId: Int64 = 0

        @IBInspectable public var offlinePolicy: String? = CacheStrategyType.RemoteFirst.rawValue
        
    }

Next, override the method that creates the Interactor for a specific page. In 
Bookmark List Screenlet, this is the `createPageLoadInteractor` method. The 
Screenlet calls this method when it needs to load a page. If your Screenlet 
supports offline mode, you should also pass the value of the `offlinePolicy` 
property to the interactor. Add this method to Bookmark List Screenlet's 
Screenlet class as follows:

    override public func createPageLoadInteractor(
        page page: Int, 
        computeRowCount: Bool) -> BaseListPageLoadInteractor {

            let interactor = BookmarkListPageLoadInteractor(
                screenlet: self,
                page: page,
                computeRowCount: computeRowCount,
                groupId: self.groupId,
                folderId: self.folderId)

        interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .RemoteFirst

        return interactor
    }

Next, you must call your delegate's methods to handle the Screenlet's events. 
You first need a reference to the delegate. The class `BaseScreenlet`, which 
`BaseListScreenlet` extends, already defines the `delegate` property to store 
the delegate object. Any list Screenlet therefore has this property, and any app 
developer using the Screenlet can assign an object to the property. To avoid 
casting this `delegate` property to `BookmarkListScreenletDelegate` every time 
you use it, you can add a computed property that does this just once: 

    public var bookmarkListDelegate: BookmarkListScreenletDelegate? {
        return delegate as? BookmarkListScreenletDelegate
    }

Now you must override the `BaseListScreenlet` methods that the Screenlet calls 
to handle events. Because these events correspond to the events your delegate 
methods handle, you'll call your delegate methods in these `BaseListScreenlet` 
methods: 

- `onLoadPageResult`: Called when the Screenlet loads a page successfully. 
  Override this method to call your delegate's 
  `screenlet(_:onBookmarkListResponse:)` method. 

- `onLoadPageError`: Called when the Screenlet fails to load a page. Override 
  this method to call your delegate's `screenlet(_:onBookmarkListError:)` 
  method. 

- `onSelectedRow`: Called when the user selects an item in the list. Override 
  this method to call your delegate's `screenlet(_:onBookmarkSelected:)` method. 

Override these methods now: 

    override public func onLoadPageResult(page page: Int, rows: [AnyObject], rowCount: Int) {
        super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)

        bookmarkListDelegate?.screenlet?(self, onBookmarkListResponse: rows as! [Bookmark])
    }

    override public func onLoadPageError(page page: Int, error: NSError) {
        super.onLoadPageError(page: page, error: error)

        bookmarkListDelegate?.screenlet?(self, onBookmarkListError: error)
    }

    override public func onSelectedRow(row: AnyObject) {
        bookmarkListDelegate?.screenlet?(self, onBookmarkSelected: row as! Bookmark)
    }

Awesome! You're done! Your list Screenlet, like any other Screenlet, is a 
ready-to-use component that you can add to your storyboard. You can even
[package it](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes#publish-your-themes-using-cocoapods)
to contribute to the Liferay Screens project, or distribute it with CocoaPods. 

## Related Topics [](id=related-topics)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)

[Packaging iOS Themes](/develop/tutorials/-/knowledge_base/7-0/packaging-ios-themes)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets)
