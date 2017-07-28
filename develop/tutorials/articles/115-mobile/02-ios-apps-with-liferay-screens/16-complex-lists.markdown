# Creating Complex Lists in Your List Screenlet [](id=creating-complex-lists-in-your-list-screenlet)

Most list Screenlets' Themes use 
[iOS's `UITableView`](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UITableView_Class/) 
to display simple lists. Although `UITableView` is great for this, it's not so 
great for complex lists like grids or stacks. To create complex lists, you 
should use 
[iOS's `UICollectionView`](https://developer.apple.com/reference/uikit/uicollectionview) 
in your list Screenlet's Theme. 

This tutorial shows you how to create such a Theme, using 
[the sample Bookmark List Screenlet’s Collection Theme](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/BookmarkListScreenlet/Themes/CollectionView) 
as an example. First, you'll create the list's cell. 

## Creating the Cell [](id=creating-the-cell)

You'll create your list's cell with 
[the same sequence of steps](link-to-custom-cells-tutorial) 
used to create any list Screenlet’s cell. Note, however, that how you perform 
these steps is a bit different: 

1.  Define your cell’s UI in a new XIB file. Because this cell is part of a Theme 
    that uses `UICollectionView`, you can shape it however you want. For example, 
    here's the `BookmarkCell_default-collection.xib` file for the cell in 
    Bookmark List Screenlet's Collection Theme. It's a simple square that 
    displays the bookmark's URL and the URL's first letter. 

    ![Figure 1: The XIB file for the cell in Bookmark List Screenlet's custom View.](../../../images/screens-ios-collectionview-cell.png)

2.  Create your XIB file's class by extending `UICollectionViewCell`. Create as 
    many outlets and actions as you need for your UI components and write the 
    logic required for your cell's UI to function. For example, 
    `BookmarkCell_default_collection` is the XIB file’s class in Bookmark List 
    Screenlet’s Custom Theme. This class extends `UICollectionViewCell` and 
    contains outlets for the URL (`urlLabel`) and the URL's first letter 
    (`centerLabel`). The `bookmark` variable’s `didSet` observer sets the 
    bookmark's name and URL to the respective label. Also note that the 
    overridden `prepareForReuse` method resets the labels for reuse: 

        import UIKit
        import LiferayScreens

        public class BookmarkCell_default_collection: UICollectionViewCell {

            //MARK: Outlets

            @IBOutlet weak var centerLabel: UILabel?
            @IBOutlet weak var urlLabel: UILabel?

            //MARK: Public properties

            public var bookmark: Bookmark? {
                didSet {
                    if let bookmark = bookmark, url = NSURL(string: bookmark.url),
                        firstLetter = url.host?.remove("www.").characters.first {

                            self.centerLabel?.text = String(firstLetter).uppercaseString
                            self.urlLabel?.text = bookmark.url.remove("http://").remove("https://").remove("www.")
                    }
                }
            }

            //MARK: UICollectionViewCell

            override public func prepareForReuse() {
                super.prepareForReuse()

                centerLabel?.text = "..."
                urlLabel?.text = "..."
            }
        }

Now that your cell exists, you can create the rest of your Theme. 

## Creating the Theme’s XIB and View Class [](id=creating-the-themes-xib-and-view-class)

You'll create the rest of your Theme with 
[the same sequence of steps](/develop/tutorials/-/knowledge_base/7-0/creating-ios-list-screenlets#creating-the-view) 
used to create any list Screenlet’s Theme. Like creating the cell, how you 
perform these steps is a bit different because your Theme uses 
`UICollectionView` instead of `UITableView`. 

First, define your Theme’s UI in a new XIB file. Add a `UICollectionView` 
instead of a `UITableView` to this file. For example, the 
[`BookmarkListView_default-collection.xib` file](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/BookmarkListScreenlet/Themes/CollectionView/BookmarkListView_default-collection.xib) 
for Bookmark List Screenlet’s Custom Theme contains a collection view. 

Next, create the View class. Instead of extending `BaseListTableView`, this 
class must extend Screens's 
[`BaseListCollectionView` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/CollectionView/BaseListCollectionView.swift). 
The `BaseListCollectionView` class implements most of the code necessary to use 
`UICollectionView` in your Screenlet. By extending it, you can focus on the code 
unique to your Screenlet. Your View class should also contain a string constant 
to serve as the cell ID. You’ll use this constant when you register your cell. 
For example, the View class in Bookmark List Screenlet's Collection Theme 
(`BookmarkListView_default_collection`) extends `BaseListCollectionView` and 
defines the string constant `BookmarkCellId`: 

    public class BookmarkListView_default_collection : BaseListCollectionView {

        let BookmarkCellId = "bookmarkCell"
        …


In Interface Builder, set this new class as the XIB's Custom Class. 

Next, override the `doRegisterCellNibs` method to register the cell you created 
in the previous section. In this method, create a `UINib` instance for your cell 
and then register it with the `UICollectionView` instance (`collectionView`) 
inherited from `BaseListCollectionView`. When registering the nib file, you must 
use the string constant you created earlier as the `forCellReuseIdentifier`. For 
example, here’s the `doRegisterCellNibs` method in 
`BookmarkListView_default_collection`: 

    public override func doRegisterCellNibs() {
        let cellNib = UINib(nibName: "BookmarkCell_default-collection", bundle: nil)
        collectionView?.registerNib(cellNib, forCellWithReuseIdentifier: BookmarkCellId)
    }

Also in your View class, override the `doGetCellId` method to return the ID you 
registered the cell with. For example, the `doGetCellId` method in 
`BookmarkListView_default_collection` returns the string constant 
`BookmarkCellId`: 

    public override func doGetCellId(indexPath indexPath: NSIndexPath, object: AnyObject?) -> String {
        return BookmarkCellId
    }

Next, override the `doFillLoadedCell` method to fill the cell with data. This 
method's `object` argument contains the data as `AnyObject`. You must cast this 
to your desired type and then set it to the appropriate cell variable. For 
example, the `doFillLoadedCell` method in `BookmarkListView_default_collection` 
casts the `object` argument to `Bookmark` and then sets it to the cell's 
`bookmark` variable: 

    public override func doFillLoadedCell(
            indexPath indexPath: NSIndexPath,
            cell: UICollectionViewCell,
            object: AnyObject) {

        if let cell = cell as? BookmarkCell_default_collection, bookmark = object as? Bookmark {
            cell.bookmark = bookmark
        }
    }

Next, you'll create the layout. 

## Creating the Layout [](id=creating-the-layout)

The layout object is a key part of `UICollectionView`. This object controls the 
position of the UI elements, their size, and more. To customize the layout 
object, override the `doCreateLayout` method in your View class. For example, 
the `doCreateLayout` method in Bookmark List Screenlet's View class 
(`BookmarkListView_default_collection`) returns a `UICollectionViewFlowLayout` 
for the layout object. This is a basic layout that gives you a simple way to 
customize things like item size, spacing between items, scroll direction, and 
more: 

    public override func doCreateLayout() -> UICollectionViewLayout {
        let layout = UICollectionViewFlowLayout()
        layout.itemSize = CGSize(width: 110, height: 110)
        layout.minimumLineSpacing = 10
        layout.minimumInteritemSpacing = 10

        return layout
    }

Great! You're done! You can now use your new Theme 
[the same way you would any other](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets). 

If you want to package your Theme to contribute it to the Liferay Screens 
project or distribute it with CocoaPods, see 
[the tutorial on packaging Themes](/develop/tutorials/-/knowledge_base/7-0/packaging-ios-themes). 

## Related Topics [](id=related-topics)

[Creating iOS List Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-list-screenlets)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes)

[Sorting Your List Screenlet](/develop/tutorials/-/knowledge_base/7-0/sorting-your-list-screenlet)

[Using Custom Cells with List Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-custom-cells-with-list-screenlets)

[iOS Best Practices](/develop/tutorials/-/knowledge_base/7-0/ios-best-practices)
