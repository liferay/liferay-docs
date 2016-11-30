# Creating iOS List Screenlets [](id=creating-ios-list-screenlets)

It's very common for mobile apps to display lists. Liferay Screens 
lets you display asset lists and DDL lists in your iOS app by using 
[Asset List Screenlet](/develop/reference/-/knowledge_base/7-0/assetlistscreenlet-for-ios) 
and 
[DDL List Screenlet](/develop/reference/-/knowledge_base/7-0/ddllistscreenlet-for-ios), 
respectively. Screens also includes list Screenlets for displaying lists of 
other Liferay entities like web content articles, images, and more. 
[The Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios) 
lists all the Screenlets included with Liferay Screens. If there’s not a list 
Screenlet for the entity you want to display in a list, you must create your own 
list Screenlet. A list Screenlet can display any entity from a Liferay instance. 
For example, you can create a list Screenlet that displays standard Liferay 
entities like `User`, or custom entities from custom Liferay apps. 

This tutorial uses code from the sample Bookmark List Screenlet to show you how 
to create your own list Screenlet. This Screenlet displays a list of bookmarks 
from Liferay’s Bookmarks portlet. You can find this Screenlet’s complete code 
[here in GitHub](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/BookmarkListScreenlet). 

Note that because this tutorial focuses on creating a list Screenlet, it doesn’t 
explain general Screenlet concepts and components. Before beginning, you should 
therefore read the following tutorials: 

- [Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)
- [Supporting Multiple Themes in Your Screenlet](/develop/tutorials/-/knowledge_base/7-0/supporting-multiple-themes-in-your-screenlet)
- [Create and Use a Connector with Your Screenlet ](/develop/tutorials/-/knowledge_base/7-0/create-and-use-a-connector-with-your-screenlet)
- [Add a Screenlet Delegate](/develop/tutorials/-/knowledge_base/7-0/add-a-screenlet-delegate)
- [Creating and Using Your Screenlet's Model Class](/develop/tutorials/-/knowledge_base/7-0/creating-and-using-your-screenlets-model-class)

This tutorial uses the following steps to show you how to create a list 
Screenlet: 

1. Creating the Model class
2. Creating the Theme
3. Creating the Connector
4. Creating the Interactor
5. Creating the Delegate
6. Creating the Screenlet class

First though, you should understand how pagination works with list Screenlets. 

## Pagination [](id=pagination)

To ensure that users can scroll smoothly through large lists of items, list 
Screenlets support 
[fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination). 
Support for this is built into the list Screenlet framework. You’ll see this as 
you construct your list Screenlet. 

Now you're ready to begin! 

## Creating the Model Class [](id=creating-the-model-class)

Recall that a model class transforms each `[String:AnyObject]` entity Screens 
receives into a model object that represents the corresponding Liferay entity. 
For instructions on creating your model class, see the tutorial 
[Creating and Using Your Screenlet's Model Class](/develop/tutorials/-/knowledge_base/7-0/creating-and-using-your-screenlets-model-class). 
The example model class in that tutorial is identical to Bookmark List 
Screenlet’s. 

Next, you'll create your Screenlet's Theme. 

## Creating the Theme [](id=creating-the-view)

Recall that each Screenlet requires a Theme to serve as its UI. In Xcode, first 
create a new XIB file for your Theme and name it according to 
[these naming conventions](/develop/tutorials/-/knowledge_base/7-0/ios-best-practices#naming-conventions). 
For example, the XIB for Bookmark List Screenlet's Default Theme is 
`BookmarkListView_default.xib`. Use Interface Builder to construct your 
Screenlet's UI in your XIB. Since the Screenlet must show a list, add a 
`UITableView` to this XIB. For example, 
[Bookmark List Screenlet's XIB file](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/Themes/Default/BookmarkListView_default.xib) 
uses a `UITableView` to show the list of bookmarks. 

Now create your Theme's View class, and name it according to 
[these naming conventions](/develop/tutorials/-/knowledge_base/7-0/ios-best-practices#naming-conventions). 
Since the XIB uses `UITableView`, your View class must extend 
`BaseListTableView`. For example, this is Bookmark List Screenlet's View class 
declaration: 

    public class BookmarkListView_default: BaseListTableView {...

In Interface Builder, set this new class as your XIB's Custom Class, and assign 
the `tableView` outlet to your `UITableView` component. 

Next, you must override the View class methods that fill the table cells' 
contents. There are two methods for this, depending on the cell type: 

- **Normal cells:** the cells that show the entities. These cells typically use 
  `UILabel`, `UIImage`, or another UI component to show the entity. Override the 
  `doFillLoadedCell` method to fill these cells. For example, Bookmark List 
  Screenlet's View class overrides `doFillLoadedCell` to set each cell's 
  `textLabel` to a bookmark's name: 

        override public func doFillLoadedCell(row row: Int, cell: UITableViewCell, object: AnyObject) {
            let bookmark = object as! Bookmark

            cell.textLabel?.text = bookmark.name
        }

- **Progress cell:** the cell at the bottom of the list that indicates the list 
  is loading the next page of items. Override the `doFillInProgressCell` method 
  to fill this cell. For example, Bookmark List Screenlet's View class overrides 
  this method to set the cell's `textLabel` to the string `"Loading..."`: 

        override public func doFillInProgressCell(row row: Int, cell: UITableViewCell) {
            cell.textLabel?.text = "Loading..."
        }

That's it! Now that your Theme is finished, you can create the Connector. 

## Creating the Connector [](id=creating-the-connector)

Recall that Connectors make a server call. To support pagination, a List 
Screenlet's Connector class must extend the 
[`PaginationLiferayConnector` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/PaginationLiferayConnector.swift). 
The Connector class must also contain any properties it needs to make the server 
call, and an initializer that sets them. To support pagination, the initializer 
must also contain the following arguments, which you'll pass to the superclass 
initializer: 

- `startRow`: The number representing the page's first row. 
- `endRow`: The number representing the page's last row. 
- `computeRowCount`: Whether to call the Connector’s `doAddRowCountServiceCall` 
  method (you'll learn about this method shortly). 

For example, Bookmark List Screenlet must retrieve bookmarks from a Bookmarks 
portlet folder in a specific site. The Screenlet's Connector class must 
therefore have properties for the `groupId` (site ID) and `folderId` (Bookmarks 
folder ID), and an initializer that sets them. The initializer also calls the 
superclass initializer with the `startRow`, `endRow`, and `computeRowCount` 
arguments: 

    import UIKit
    import LiferayScreens

    public class BookmarkListPageLiferayConnector: PaginationLiferayConnector {

        public let groupId: Int64
        public let folderId: Int64

        //MARK: Initializer

        public init(startRow: Int, endRow: Int, computeRowCount: Bool, groupId: Int64, folderId: Int64) {
            self.groupId = groupId
            self.folderId = folderId

            super.init(startRow: startRow, endRow: endRow, computeRowCount: computeRowCount)
        }
        ...

Next, override the `validateData` method as described in 
[the tutorial on creating Connectors](/develop/tutorials/-/knowledge_base/7-0/create-and-use-a-connector-with-your-screenlet#creating-connectors). 
Note that Bookmark List Screenlet only needs to validate the `folderId`: 

    override public func validateData() -> ValidationError? {
        let error = super.validateData()

        if error == nil {
            if folderId <= 0 {
                return ValidationError("Undefined folderId")
            }
        }

        return error
    }

Lastly, you must override the following two methods in the Connector class: 

- `doAddPageRowsServiceCall`: calls the Liferay Mobile SDK service method that 
  retrieves a page of entities. The `doAddPageRowsServiceCall` method's 
  `startRow` and `endRow` arguments specify the page's first and last entities, 
  respectively. Make the service call as you would in any Screenlet. For 
  example, the `doAddPageRowsServiceCall` method in 
  `BookmarkListPageLiferayConnector` calls the service's `getEntriesWithGroupId` 
  method to retrieve a page of bookmarks from the folder specified by 
  `folderId`: 

        public override func doAddPageRowsServiceCall(session session: LRBatchSession, 
            startRow: Int, endRow: Int, obc: LRJSONObjectWrapper?) {
                let service = LRBookmarksEntryService_v7(session: session)

            do {
                try service.getEntriesWithGroupId(groupId,
                                                  folderId: folderId,
                                                  start: Int32(startRow),
                                                  end: Int32(endRow))
            }
            catch  {
                // ignore error: the method returns nil (converted to an error) because
                // the request is not actually sent
            }
        }

- `doAddRowCountServiceCall`: calls the Liferay Mobile SDK service method that 
  retrieves the total number of entities. This supports pagination. Make the 
  service call as you would in any Screenlet. For example, the 
  `doAddRowCountServiceCall` in `BookmarkListPageLiferayConnector` calls the 
  service's `getEntriesCountWithGroupId` method to retrieve the total number of 
  bookmarks in the folder specified by `folderId`: 

        override public func doAddRowCountServiceCall(session session: LRBatchSession) {
            let service = LRBookmarksEntryService_v7(session: session)

            do {
                try service.getEntriesCountWithGroupId(groupId, folderId: folderId)
            }
            catch  {
                // ignore error: the method returns nil (converted to an error) because
                // the request is not actually sent
            }
        }

Now that you have your Connector class, you're ready to create the Interactor. 

## Creating the Interactor [](id=creating-the-interactor)

Recall that Interactors implement your Screenlet's actions. In list Screenlets, 
loading entities is usually the only action a user can take. The Interactor 
class of a list Screenlet that implements fluent pagination must extend the 
[`BaseListPageLoadInteractor` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/BaseListPageLoadInteractor.swift). 
Your Interactor class must also contain any properties the Screenlet needs, and 
an initializer that sets them. This initializer also needs arguments for the 
following properties, which it passes to the superclass initializer: 

- `screenlet`: A `BaseListScreenlet` reference. This ensures the Interactor 
  always has a Screenlet reference. 
- `page`: The page number to retrieve. 
- `computeRowCount`: Whether to call the Connector’s `doAddRowCountServiceCall` 
  method. 

For example, Bookmark List Screenlet's Interactor class contains the same 
`groupId` and `folderId` properties as the Connector, and an initializer that 
sets them. This initializer also passes the `screenlet`, `page`, and 
`computeRowCount` arguments to the superclass initializer: 

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
        ...

The Interactor class must also initiate the server request by instantiating the 
Connector, and convert the results into model objects. Override the 
`createListPageConnector` method to create and return an instance of your 
Connector. This method must first get a reference to the Screenlet via the 
`screenlet` property. When calling the Connector's initializer, use 
`screenlet.firstRowForPage` to convert the page number (`page`) to the page's 
start and end indices. You must also pass the initializer any other properties 
it needs, like `groupId`. For example, `BookmarkListPageLoadInteractor`'s 
`createListPageConnector` method does this to create a 
`BookmarkListPageLiferayConnector` instance: 

    public override func createListPageConnector() -> PaginationLiferayConnector {
        let screenlet = self.screenlet as! BaseListScreenlet

        return BookmarkListPageLiferayConnector(
            startRow: screenlet.firstRowForPage(self.page),
            endRow: screenlet.firstRowForPage(self.page + 1),
            computeRowCount: self.computeRowCount,
            groupId: groupId,
            folderId: folderId)
    }

Next, override the `convertResult` method to convert each `[String:AnyObject]` 
result into a model object. The Screenlet calls this method once for each entity 
retrieved from the server. For example, `BookmarkListPageLoadInteractor`'s 
`convertResult` method converts the `[String:AnyObject]` result into a 
`Bookmark` object: 

    override public func convertResult(serverResult: [String:AnyObject]) -> AnyObject {
        return Bookmark(attributes: serverResult)
    }

You may also want to support 
[offline mode](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens) 
in your Interactor. To do so, the Interactor must override the `cacheKey` method 
to return a cache key unique to your Screenlet. For example, 
`BookmarkListPageLoadInteractor`'s `cacheKey` method returns a cache key that 
includes the Screenlet's `groupId` and `folderId` settings: 

    override public func cacheKey(op: PaginationLiferayConnector) -> String {
        return "\(groupId)-\(folderId)"
    }

Great! Next, you'll create your Screenlet's delegate. 

## Creating the Delegate [](id=creating-the-delegate)

Recall that a delegate is required if you want other classes to respond to your 
Screenlet's actions. Create your delegate by following the first step in 
[the tutorial on adding a Screenlet delegate](/develop/tutorials/-/knowledge_base/7-0/add-a-screenlet-delegate). 
A list Screenlet's delegate must also define a method for responding to a list 
item selection. For example, Bookmark List Screenlet's delegate needs the 
following methods: 

- `screenlet(_:onBookmarkListResponse:)`: Returns the `Bookmark` results when 
  the server call succeeds. 
- `screenlet(_:onBookmarkListError:)`: Returns the `NSError` object when the 
  server call fails. 
- `screenlet(_:onBookmarkSelected:)`: Returns the `Bookmark` when a user selects 
  it in the list. 

The `BookmarkListScreenletDelegate` protocol defines these methods: 

    @objc public protocol BookmarkListScreenletDelegate : BaseScreenletDelegate {

        optional func screenlet(screenlet: BookmarkListScreenlet,
                                onBookmarkListResponse bookmarks: [Bookmark])

        optional func screenlet(screenlet: BookmarkListScreenlet,
                                onBookmarkListError error: NSError)

        optional func screenlet(screenlet: BookmarkListScreenlet,
                                onBookmarkSelected bookmark: Bookmark)

    }

Nice work! Next, you'll create the Screenlet class. 

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

Now that your list Screenlet's other components exist, you can create the 
Screenlet class. A list Screenlet's Screenlet class must extend the 
[`BaseListScreenlet` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/BaseListScreenlet.swift) 
and define the configurable properties the Screenlet needs. You should define 
these as `IBInspectable` properties. If you want to support offline mode, you 
should also add an `offlinePolicy` property. 

For example, 
[Bookmark List Screenlet's Screenlet class](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/BookmarkListScreenlet.swift) 
contains the `IBInspectable` properties `groupId`, `folderId`, and 
`offlinePolicy`: 

    public class BookmarkListScreenlet: BaseListScreenlet {

        @IBInspectable public var groupId: Int64 = 0
        @IBInspectable public var folderId: Int64 = 0
        @IBInspectable public var offlinePolicy: String? = CacheStrategyType.RemoteFirst.rawValue

        ...

Next, override the `createPageLoadInteractor` method to create and return the 
Interactor. If your Screenlet supports offline mode, you should also use 
`offlinePolicy` to pass a `CacheStrategyType` object to the Interactor. For 
example, the `createPageLoadInteractor` method in `BookmarkListScreenlet` 
creates and returns a `BookmarkListPageLoadInteractor` instance. This method 
also sets the Interactor’s `cacheStrategy` property to a `CacheStrategyType` 
object created with `offlinePolicy`: 

    override public func createPageLoadInteractor(
        page page: Int, 
        computeRowCount: Bool) -> BaseListPageLoadInteractor {

        let interactor = BookmarkListPageLoadInteractor(screenlet: self,
                                                        page: page,
                                                        computeRowCount: computeRowCount,
                                                        groupId: self.groupId,
                                                        folderId: self.folderId)

        interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .RemoteFirst

        return interactor
    }

Now get a reference to your delegate. The `BaseScreenlet` class, which 
`BaseListScreenlet` extends, already defines the `delegate` property for the 
delegate object. Every list Screenlet therefore has this property, and any app 
developer using the Screenlet can access it. To avoid casting this property to 
your delegate every time you use it, add a computed property to your Screenlet 
class that does so. For example, the following `bookmarkListDelegate` property 
in `BookmarkListScreenlet` casts the `delegate` property to 
`BookmarkListScreenletDelegate`: 

    public var bookmarkListDelegate: BookmarkListScreenletDelegate? {
        return delegate as? BookmarkListScreenletDelegate
    }

Next, override the `BaseListScreenlet` methods that handle the Screenlet’s 
events. Because these events correspond to the events your delegate methods 
handle, you'll call your delegate methods in these `BaseListScreenlet` methods: 

- `onLoadPageResult`: Called when the Screenlet loads a page successfully. 
  Override this method to call your delegate's 
  `screenlet(_:onBookmarkListResponse:)` method. For example, here's 
  `BookmarkListScreenlet`'s `onLoadPageResult` method: 

        override public func onLoadPageResult(page page: Int, rows: [AnyObject], rowCount: Int) {
            super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)

            bookmarkListDelegate?.screenlet?(self, onBookmarkListResponse: rows as! [Bookmark])
        }

- `onLoadPageError`: Called when the Screenlet fails to load a page. Override 
  this method to call your delegate's `screenlet(_:onBookmarkListError:)` 
  method. For example, here's `BookmarkListScreenlet`'s `onLoadPageError` 
  method: 

        override public func onLoadPageError(page page: Int, error: NSError) {
            super.onLoadPageError(page: page, error: error)

            bookmarkListDelegate?.screenlet?(self, onBookmarkListError: error)
        }

- `onSelectedRow`: Called when an item is selected in the list. Override this 
  method to call your delegate's `screenlet(_:onBookmarkSelected:)` method. For 
  example, here's `BookmarkListScreenlet`'s `onSelectedRow` method: 

        override public func onSelectedRow(row: AnyObject) {
            bookmarkListDelegate?.screenlet?(self, onBookmarkSelected: row as! Bookmark)
        }

Awesome! You're done! Your list Screenlet, like any other Screenlet, is a 
ready-to-use component that you can add to your storyboard. You can even 
[package it using the same steps you use to package a Theme](/develop/tutorials/-/knowledge_base/7-0/packaging-ios-themes), 
and then contribute it to the Liferay Screens project or distribute it with 
CocoaPods. 

## Related Topics [](id=related-topics)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)

[Supporting Multiple Themes in Your Screenlet](/develop/tutorials/-/knowledge_base/7-0/supporting-multiple-themes-in-your-screenlet)

[Create and Use a Connector with Your Screenlet ](/develop/tutorials/-/knowledge_base/7-0/create-and-use-a-connector-with-your-screenlet)

[Add a Screenlet Delegate](/develop/tutorials/-/knowledge_base/7-0/add-a-screenlet-delegate)

[Creating and Using Your Screenlet's Model Class](/develop/tutorials/-/knowledge_base/7-0/creating-and-using-your-screenlets-model-class)

[Using Custom Cells with List Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-custom-cells-with-list-screenlets)

[Sorting Your List Screenlet](/develop/tutorials/-/knowledge_base/7-0/sorting-your-list-screenlet)

[Creating Complex Lists in Your List Screenlet](/develop/tutorials/-/knowledge_base/7-0/creating-complex-lists-in-your-list-screenlet)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)
