# Creating the iOS List Screenlet's Delegate

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
