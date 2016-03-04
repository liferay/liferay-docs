# Creating iOS List Screenlets [](id=creating-ios-list-screenlets)

One of the most common features in mobile apps (and in software in general) is to show lists of specific entities to the user.
Since Liferay Portal handles a lot of types of entities, you would need to create your own screenlet to show a list of one specific entity in your app. This element type may be an standard type (such User, or Bookmark), or maybe a custom type created by your own plugin.

This tutorial explains how to create your own list screenlets. As an example, it
references code from the sample
[Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/BookmarkListScreenlet), 
that lists bookmarks from Liferay's Bookmarks portlet.

To understand the concepts explained here, you might want to examine the
[architecture](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)
of Liferay Screens for iOS. The tutorial
[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-ios-screenlets)
is also highly recomended since we're going to create a new screenlet from scratch.


## Pagination [](id=pagination)

Since your lists may be potentially quite large, the screenlet needs to support pagination. In some rare cases, you can skip this pagination feature, but it's quite unusual. If you want to list the number of days in a week, it clearly seems the paginations can be bypassed, but I bet you won't find this case usually.

Liferay Screens gives you some tools to implement [fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
with configurable page size. Both `AssetListScreenlet` and `DDLListScreenlet` use this approach.

## Creating the Screenlet [](id=creating-the-screenlet)

As you already know, one screenlet contains several sub-components which can be built in a separated manner. This allows us to focus in different responsibilities one by one

### Creating the View [](id=creating-the-view)

Let's start by the view layer. In Xcode, create a new XIB file and construct your Screenlet's UI with Interface Builder. Since the screenlet will show a list of items, you probably need an `UITableView`. 
    
For example, the Bookmark List Screenlet's XIB file `BookmarkListView_default.xib` uses a simple `UITableView` to show the list of bookmarks.

Now create a new View class with a name that matches the prefix of your XIB file's name. Since the the XIB file uses an `UITableView`, the class must extend `BaseListTableView`. Don't forget to set this new class as your XIB's Custom Class

And also to assign the `tableView` outlet to your `UITableView` component.

Next, override the following methods in the `BookmarkListView_default` class:
	    
		override public func doFillLoadedCell(row row: Int, cell: UITableViewCell, object: AnyObject) {
			// fill here the normal cell
		}
	
		override public func doFillInProgressCell(row row: Int, cell: UITableViewCell) {
			// fill here the progress cell
		}
		
Let's keep them empty by now, we'll complete them later.

Here you can notice two different types of cells:

  * Normal cells: the cells (rows) that shows the entities. This typically will use `UILabel`, `UIImage` or any other UI component to show the entity's attributes. Do this in the `doFillLoadedCell` method.
  * Progress cell: the cell that will be shown at the bottom of the list to show the list is loading the next page. This typically may include an `UIActivityIndicator` or similar. Do this in the `doFillInProgressCell` method.

You can also override other methods in order to configure the XIB file to be used in the cells. If you want to use one single cell style, then override the method `doRegisterCellNib` and load and register there the XIB file intented to be used for all of your cells. In case of our BookmarkListScreenlet, you wrote the following code:

		override public func doRegisterCellNib(id id: String) {
    		let nib = UINib(nibName: "BookmarkCell_default", bundle: NSBundle.mainBundle())

	    	tableView?.registerNib(nib, forCellReuseIdentifier: id)
		}

But one typical scenario is when you need to show several cell styles, one for each state of you data. For instance, this is useful if you want to show a different style if the bookmark's url is not valid. In such case, you need to override the preview method and register there all the necessary XIB files using your own identifiers, and then override method `doDequeueReusableCell` to dequeue de cell for the identifier supplied.
 	
		public func doDequeueReusableCell(row row: Int) -> UITableViewCell {
		    let cellId = (row == 1) ? "firstCellRow" : "regularCell"

		    return tableView?.dequeueReusableCellWithIdentifier(cellId)
		        ?? UITableViewCell(style: .Default, reuseIdentifier: cellId)
		}


### Creating the Screenlet Class [](id=creating-the-screenlet-class)

Once the view is prepared, we're going to create the screenlet itself. You just need to create a class extending from `BaseListScreenlet`. In this class you will add the configuration parameters of the screenlet, as `IBInspectable` properties. In our case, we just need to configure the `groupId` and the `folderId`. Remember to annotate your class as `IBDesignable`. 

		@IBDesignable public class BookmarkListScreenlet: BaseListScreenlet {

		    @IBInspectable public var groupId: Int64 = 0
		    @IBInspectable public var folderId: Int64 = 0
 		    
		}
 		
This is enough for the moment. We'll add more code later.

### Creating the Interactor [](id=creating-the-interactor)

Now the screenlet needs to respond to user actions. In our case, the only action is "load the bookmarks". This action is triggered by the app's programmer calling methods `loadList` or `refreshList`, or automatically when the screenlet appears in the screen (depending on the value of the property `autoLoad`). As you already know, to respond to actions the screenlet uses Interactor classes. So we'd need one interactor to load the list. In our case, given that we're extending from `BaseListScreenlet`, we need to extend from an specific interactor class, `BaseListPageLoadInteractor`, which helps us to request list's pages. This interactor will have all the parameters it needs to perform its duty (`groupId` and `folderId` in our case) and override two methods:

    	override public func createConnector() -> PaginationLiferayConnector {
    	    // create here the connector to do the actual request
    	}

    	override public func convertResult(serverResult: [String:AnyObject]) -> AnyObject {
    	    // convert the result received from the connector into one object ready to be used.
    	}

Let's start by the latter: `convertResult` method will be called several times, one by each entity received by the server and its duty is to convert that entity (in server-side format) into a politer object. So first we need to create a new class to represent one bookmark. I'll do this in the BookmarkListScreenlet.swift file (just above the BookmarkListScreenlet definition):

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

and convert the result of the server (a key-value collection) into that object:
    
    	override public func convertResult(serverResult: [String:AnyObject]) -> AnyObject {
    	    return Bookmark(attributes: serverResult)
    	}

Now the former: `createConnector` is a method where you have to create and connector class which will do the actual server requests. So let's go to the next section to explain how to create your connector class.

### Creating the Connector [](id=creating-the-connector)

So we need to send a page request to one specific server and we know that this have to be done using a connector class. So first you need to create a class extending from `PaginationLiferayConnector`, pass all the necessary arguments, and override two methods: one to retrieve the actual page's rows and the other to retrieve the row count.

	override public func doAddPageRowsServiceCall(session session: LRBatchSession, startRow: Int, endRow: Int) {
	    // create a Liferay Mobile SDK service to retrieve records from startRow and endRow and call it using supplied session
	}

	override public func doAddRowCountServiceCall(session session: LRBatchSession) {
	    // create a Liferay Mobile SDK service to retrieve the row count and call it using supplied session
	}

In our case, this is the code used to retrieve bookmarks and bookmark count:

	override public func doAddPageRowsServiceCall(session session: LRBatchSession, startRow: Int, endRow: Int) {
		let service = LRBookmarksEntryService_v62(session: session)

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

	override public func doAddRowCountServiceCall(session session: LRBatchSession) {
		let service = LRBookmarksEntryService_v62(session: session)

		do {
			try service.getEntriesCountWithGroupId(groupId, folderId: folderId)
		}
		catch  {
			// ignore error: the method returns nil (converted to an error) because
			// the request is not actually sent
		}
	}
	
### Connecting the Dots [](id=connecting-the-dots)

So then, you have your connector ready to be used. Now go one level back and create one instance of this connector in the interactor's `createConnector` method. The only tricky part here is that you need to convert from the page number (interactor's `page` attribute) to the corresponding start and end record numbers. This is done using a `firstRowForPage` method.
	
	override public func createConnector() -> PaginationLiferayConnector {
		let screnlet = self.screenlet as! BaseListScreenlet

		return BookmarkListPageLiferayConnector(
			startRow: screnlet.firstRowForPage(self.page),
			endRow: screnlet.firstRowForPage(self.page + 1),
			computeRowCount: self.computeRowCount,
			groupId: groupId,
			folderId: folderId)
	}
	
And again: go one level back to your screenlet and now override the method that creates the interactor for one specific page. This method will be called when the screenlet needs to load one new page:

	override public func createPageLoadInteractor(
			page page: Int,
			computeRowCount: Bool) -> BaseListPageLoadInteractor {

		return BookmarkListPageLoadInteractor(
			screenlet: self,
			page: page,
			computeRowCount: computeRowCount,
			groupId: self.groupId,
			folderId: self.folderId)
	}


And few final details: in your view class (`BookmarkListView_default`), you have to complete the overriden methods:
	    
		override public func doFillLoadedCell(row row: Int, cell: UITableViewCell, object: AnyObject) {
			let bookmarkCell = cell as! BookmarkCell_default
			let bookmark = object as! Bookmark

			bookmarkCell.nameLabel.text = bookmark.name
			bookmarkCell.urlLabel.text = bookmark.url
		}
	
		override public func doFillInProgressCell(row row: Int, cell: UITableViewCell) {
			let bookmarkCell = cell as! BookmarkCell_default

			bookmarkCell.nameLabel.text = "Loading..."
		}

Now you're ready to add your brand-new screenlet to your app and test it.


### Extra Ball: Add a Delegate [](id=extra-add-delegate)

Even though your screenlet can be used now, we missed some useful features, so let's add them now. The first one is the delegate: you need to notify the app's developer when an relevant event occurrs inside the screenlet. And as you already know, in iOS world this is done using the delegate pattern.

You first need to define a protocol, which will be the contract (aka the set of methods) that any delegate class will conform. You can do it in the `BookmarkListScreenlet.swift` file.

	@objc public protocol BookmarkListScreenletDelegate : BaseScreenletDelegate {

		optional func screenlet(screenlet: BookmarkListScreenlet,
			onBookmarkListResponse bookmarks: [Bookmark])

		optional func screenlet(screenlet: BookmarkListScreenlet,
			onBookmarkListError error: NSError)

		optional func screenlet(screenlet: BookmarkListScreenlet,
			onBookmarkSelected bookmark: Bookmark)

	}
	
Notice some methods are marked as optional, which means that the delegate class may or may not implement them.

The class `BaseScreenlet` (our direct ancestor) already defines the property `delegate` to store the delegate object, so any screenlet (ours aswell) will have that property and any app develop will be able to assign an object to that property.

Next, we need to invoke the delegate (if any) when the relevant event occurrs. In our case we have three events:

1. When the screenlet loads a page successfully: override this method to invoke the delegate

		override public func onLoadPageResult(page page: Int, rows: [AnyObject], rowCount: Int) {
			super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)
		
			guard let delegate = self.delegate as? BookmarkListScreenletDelegate else {
				return
			}

			delegate.screenlet?(self, onBookmarkListResponse: self.rows as! [Bookmark])
		}

2. When the screenlet fails trying to load a page: override this method to invoke the delegate

		override public func onLoadPageError(page page: Int, error: NSError) {
			super.onLoadPageError(page: page, error: error)
		
			guard let delegate = self.delegate as? BookmarkListScreenletDelegate else {
				return
			}

			delegate.screenlet?(self, onBookmarkListError: error)
		}

3. When the user selects an item in the list: override this method to invoke the delegate

		override public func onSelectedRow(row: AnyObject) {
			guard let delegate = self.delegate as? BookmarkListScreenletDelegate else {
				return
			}

			delegate.screenlet?(self, onBookmarkSelected: row as! Bookmark)
		}


### Extra Ball: Add Offline Support [](id=extra-add-offline-support)

One of the most powerful capabilities of the screenlets is that they can work in offline scenarios.
In case of list screenlets, this is quite straghtforward.

1. Add one new attribute to the screenlet in order to allow the app developer to configure the offline policy she wants for this specific screenlet.

		@IBDesignable public class BookmarkListScreenlet: BaseListScreenlet {

			...

			@IBInspectable public var offlinePolicy: String? = CacheStrategyType.RemoteFirst.rawValue
			
			...

2. Pass this value to the interactor, when it's created. The interactor will know how to use it properly:

		override public func createPageLoadInteractor(
				page page: Int,
				computeRowCount: Bool) -> BaseListPageLoadInteractor {

			let interactor = BookmarkListPageLoadInteractor(...)

			interactor.cacheStrategy = CacheStrategyType(rawValue: self.offlinePolicy ?? "") ?? .RemoteFirst

			return interactor
		}

3. In the interactor, override this method. You have to return a cache key unique for this screenlet. Since this screenlet shows bookmarks for one groupId and folderId, the cache key must include this values:

		override public func cacheKey(op: PaginationLiferayConnector) -> String {
			return "\(groupId)-\(folderId)"
		}

4. And voilà! That's it, just add your screenlet to your app, configure the cache policy, and eveything will be handled under the hood.



You're done! Your Screenlet is a ready-to-use component that you can add to your
storyboard. You can even
[package](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes#publish-your-themes-using-cocoapods)
it to contribute to the Screens project or distribute with CocoaPods.


## Related Topics [](id=related-topics)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-ios-screenlets)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)

[Packaging iOS Themes](/develop/tutorials/-/knowledge_base/6-2/packaging-ios-themes)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-themes-in-ios-screenlets)


