# Creating the Screenlet Class [](id=creating-the-screenlet-class-ios)

Recall that the Screenlet class is the main component that governs the 
Screenlet's behavior. Also recall that a list Screenlet's class must do the 
following: 

-   Extend `BaseListScreenlet`. Since `BaseListScreenlet` provides most of the 
    functionality needed by Screenlet classes, extending it lets you focus on 
    the functionality unique to your Screenlet. 
-   Define the configurable `IBInspectable` properties the Screenlet needs. 
-   Create and return an instance of your Interactor. 
-   Respond to the Screenlet's events via the delegate. 

The Screenlet class you created when you created the delegate is currently 
empty. You'll complete it now. 

Follow these steps to complete the `EntryListScreenlet` class in 
`EntryListScreenlet.swift`: 

1.  Define public, `IBInspectable`, `Int64` properties for the `groupId` and 
    `guestbookId`. Although the app developer can set `groupId` via 
    `liferay-server-context.plist`, and the `guestbookId` is set dynamically, 
    it's also a good idea to let the developer set their values in Interface 
    Builder. Give both properties an initial value of `0`. Your Screenlet class 
    should now look like this: 

        public class EntryListScreenlet: BaseListScreenlet {

            @IBInspectable public var groupId: Int64 = 0
            @IBInspectable public var guestbookId: Int64 = 0
        }

2.  Override the `createPageLoadInteractor` method to create and return an 
    instance of `EntryListPageLoadInteractor`. This method includes `page` and 
    `computeRowCount` arguments, which you pass to the Interactor's constructor 
    along with `groupId` and `guestbookId`: 

        override public func createPageLoadInteractor(
            page: Int,
            computeRowCount: Bool) -> BaseListPageLoadInteractor {
        
            return EntryListPageLoadInteractor(screenlet: self,
                                                     page: page,
                                                     computeRowCount: computeRowCount,
                                                     groupId: self.groupId,
                                                     guestbookId: self.guestbookId)
        }

3.  Create a computed property to get a reference to 
    `EntryListScreenletDelegate`: 

        public var entryListDelegate: EntryListScreenletDelegate? {
            return delegate as? EntryListScreenletDelegate
        }

    You'll use this property to handle the Screenlet's events via the delegate's 
    methods. 

4.  Override the `BaseListScreenlet` methods that handle the Screenlet's events. 
    Because these events correspond to those handled by your delegate, you'll 
    call the corresponding delegate methods in these `BaseListScreenlet` 
    methods: 

    - `onLoadPageResult`: Called when the Screenlet loads a page successfully. 
      Override this method to call the superclass's `onLoadPageResult` method, 
      then call your delegate's `screenlet(_:onEntryListResponse:)` method: 

            override public func onLoadPageResult(page: Int, rows: [AnyObject], rowCount: Int) {
                super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)

                entryListDelegate?.screenlet?(screenlet: self, onEntryListResponse: rows as! [EntryModel])
            }

    - `onLoadPageError`: Called when the Screenlet fails to load a page. 
      Override this method to call the superclass's `onLoadPageError` method, 
      then call your delegate's `screenlet(_:onEntryListError:)` method: 

            override public func onLoadPageError(page: Int, error: NSError) {
                super.onLoadPageError(page: page, error: error)

                entryListDelegate?.screenlet?(screenlet: self, onEntryListError: error)
            }

    - `onSelectedRow`: Called when an item is selected in the list. Override 
      this method to call your delegate's `screenlet(_:onEntrySelected:)` 
      method: 

            override public func onSelectedRow(_ row: AnyObject) {
                entryListDelegate?.screenlet?(screenlet: self, onEntrySelected: row as! EntryModel)
            }

Awesome! Your Screenlet class is finished. You're also done with Entry List 
Screenlet! Now you're ready to use Entry List Screenlet alongside Guestbook List 
Screenlet. The following section of this Learning Path concludes with both 
Screenlets working together in harmony. 
