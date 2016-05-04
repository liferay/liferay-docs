# Asset List Screenlet for iOS [](id=assetlistscreenlet-for-ios)

## Requirements [](id=requirements)

- Xcode 7.2
- iOS 9 SDK
- Liferay Portal 6.2 (CE or EE), 7.0 (CE) 
- Liferay Screens Compatibility Plugin
  ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [EE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your portal edition). 

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

The `AssetListScreenlet` can be used to show lists of [assets](/tutorials/-/knowledge_base/6-2/asset-framework) 
from a Liferay instance. For example, you can use the Screenlet to show a 
scrollable collection of assets. It also implements [fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
with configurable page size. The `AssetListScreenlet` can show assets of the 
following classes: 

- `Group`
- `Layout`
- `Organization`
- `User`
- `UserGroup`
- `BlogsEntry`
- `BookmarksEntry`
- `BookmarksFolder`
- `CalendarEvent`
- `DLFileEntry`
- `DLFileEntryMetadata`
- `DLFileEntryType`
- `DLFileRank`
- `DLFileShortcut`
- `DLFileVersion`
- `DDLRecord`
- `DDLRecordSet`
- `JournalArticle` (Web Content)
- `JournalFolder`
- `MBMessage`
- `MBThread`
- `MBCategory`
- `MBDiscussion`
- `MBMailingList`
- `WikiPage`
- `WikiPageResource`
- `WikiNode`

The `AssetListScreenlet` also supports i18n in asset values.

## Module [](id=module)

- None

## Themes [](id=themes)

The Default Theme uses a standard `UITableView` to show the scrollable list. 
Other Themes may use a different component, such as `UICollectionView` or 
others, to show the items.

![Figure 1: Asset List Screenlet using the Default (`default`) Theme.](../../images/screens-ios-assetlist.png)

### Custom cells [](id=custom-cells)

Since Default theme for AssetListScreenlet uses `UITableView` as UI component, you probably may need to use a custom cell to show the content of your asset.
For that, you'll need to do the following:

- In your theme, create a child class extending from `DDLListView_default`. Call it `DDLListView_mytheme`.
- Create the `xib` file for your cell. As usual, you need to create the xib file and the companion class and create as many `IBOutlets` and `IBActions` as you need. Also, you may want to create several xibs and classes, for example if you want to use a different layout for different rows.
- In your `DDLListView_mytheme` class, override the following method to register your custom cell:

```
override public func doRegisterCellNibs() {
	let customNib = UINib(nibName: "MyCellName", bundle: nil)

	tableView?.registerNib(customNib, forCellReuseIdentifier: "myCellId")

	// register other cells...
}
```

- Again, override the following method to realize which identifier should be used for one specific row:

```
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
```

- Next, you need to override this other method, in order to create the cell when needed, depending on the cell type:

```
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
```

- And finally, you need to fill the cell with the row's data. This method will only be called for cells with data, not for "in progress" cells:

```
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
```

- If you want to show some specific UI for "in progress" cells, you need to override a different method. Remember this step is optional and if you don't do it, the Default theme's style will be used:

```
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
```

- Remember that you also have the typical `UITableViewDelegate` and `UITableViewDataSource` methods available for you, so you can override any of them if you need it (but check before if it's already overriden!). One typical case is when you need to use a different cell height for one row:

```
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
```


## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The Screenlet loads the list from the portal. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error. If the Screenlet successfully loads the list, it stores the data in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `cache-only` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `remote-first` | The Screenlet loads the list from the portal. If this succeeds, the Screenlet shows the list to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the list from the local cache. If the list doesn't exist there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `cache-first` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet requests it from the portal and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `autoLoad` | `boolean` | Defines whether the list should be loaded when it's presented on the screen. The default value is `true`. |
| `refreshControl` | `boolean` | Defines whether a standard [UIRefreshControl](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UIRefreshControl_class/) is shown when the user does the pull to refresh gesture. The default value is `true`. |
| `firstPageSize` | `number` | The number of items retrieved from the server for display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items retrieved from the server for display on the second and subsequent pages. The default value is `25`. |
| `groupId` | `number` | The ID of the site (group) where the asset is stored. If set to `0`, the `groupId` specified in `LiferayServerContext` is used. The default value is `0`. |
| `classNameId` | `number` | The ID of the asset's class name. Use values from the `AssetClassNameId` class or the portal's `classname_` database table. |
| `customEntryQuery` | `Dictionary` | The set of keys (string) and values (string or number) to be used in the [`AssetEntryQuery` object](/portal/6.2/javadocs/com/liferay/portlet/asset/service/persistence/AssetEntryQuery.html). These values filter the assets returned by the portal. |
| `portletItemName` | `string` | The archive name you used in the AssetPublisher. To use this feature, add an AssetPublisher to one of your site's pages (it may be a hidden page), configure the AssetPublisher filter (Asset Selection on configuration page), and then use the "Archive Setup" option to save that configuration with a name. Use that name in this attribute. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `loadList()` | `boolean` | Starts the request to load the list of assets. This list is shown when the response is received. Returns `true` if the request is sent. |

## Delegate [](id=delegate)

The `AssetListScreenlet` delegates some events to an object that conforms to the 
`AssetListScreenletDelegate` protocol. This protocol lets you implement the 
following methods: 

- `- screenlet:onAssetListResponse:`: Called when a page of assets is 
  received. Note that this method may be called more than once; one call for 
  each page received.

- `- screenlet:onAssetListError:`: Called when an error occurs in the process. 
  The `NSError` object describes the error.

- `- screenlet:onAssetSelectedEntry:`: Called when an item in the list is 
  selected.
