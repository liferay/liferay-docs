# Creating the Screenlet Class [](id=creating-the-screenlet-class)

The Screenlet class is the main component that governs the Screenlet's behavior. 
When using a Screenlet, app developers primarily interact with its Screenlet 
class. A list Screenlet's Screenlet class must extend 
[the `BaseListScreenlet` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/BaseListScreenlet.swift). 
Since `BaseListScreenlet` provides most of the functionality needed by Screenlet 
classes, extending it lets you focus on the functionality unique to your 
Screenlet. The Screenlet class must also define the configurable `IBInspectable` 
properties the Screenlet needs, create and return an instance of your 
Interactor, and respond to the Screenlet's events via the delegate. 

The Screenlet class you created while creating the delegate is currently empty. 
You'll complete it now. 

Follow these steps to complete the `GuestbookListScreenlet` class in 
`GuestbookListScreenlet.swift`: 

1.  Define a public, `IBInspectable`, `Int64` property for the `groupId`. 
    Although the app developer can set this value via 
    `liferay-server-context.plist`, it's also a good idea to let them set it in 
    Interface Builder when using the Screenlet. Give this property an initial 
    value of `0`. Your Screenlet class should now look like this: 

        public class GuestbookListScreenlet: BaseListScreenlet {

            @IBInspectable public var groupId: Int64 = 0

        }

2.  Override the `createPageLoadInteractor` method to create and return an 
    instance of your Interactor, `GuestbookListPageLoadInteractor`. This method 
    includes `page` and `computeRowCount` arguments, which you can pass to the 
    Interactor's constructor along with `groupId`: 

        override public func createPageLoadInteractor(
            page: Int,
            computeRowCount: Bool) -> BaseListPageLoadInteractor {

            return GuestbookListPageLoadInteractor(screenlet: self,
                                                         page: page,
                                                         computeRowCount: computeRowCount,
                                                         groupId: self.groupId)
        }

3.  Create a computed property to get a reference to your delegate, 
    `GuestbookListScreenletDelegate`: 

        public var guestbookListDelegate: GuestbookListScreenletDelegate? {
            return delegate as? GuestbookListScreenletDelegate
        }

    You'll use this property to handle the Screenlet's events via the delegate's 
    methods. 

4.  Override the `BaseListScreenlet` methods that handle the Screenlet's events. 
    Because these methods correspond to your delegate methods, you'll call your 
    delegate methods in them: 

    - `onLoadPageResult`: Called when the Screenlet loads a page successfully. 
      Override this method to call the superclass's `onLoadPageResult` method, 
      then call your delegate's `screenlet(_:onGuestbookListResponse:)` method:

            override public func onLoadPageResult(page: Int, rows: [AnyObject], rowCount: Int) {
                super.onLoadPageResult(page: page, rows: rows, rowCount: rowCount)

                guestbookListDelegate?.screenlet?(screenlet: self, 
                    onGuestbookListResponse: rows as! [GuestbookModel])
            }

    - `onLoadPageError`: Called when the Screenlet fails to load a page. 
      Override this method to call the superclass's `onLoadPageError` method, 
      then call your delegate's `screenlet(_:onGuestbookListError:)` method: 

            override public func onLoadPageError(page: Int, error: NSError) {
                super.onLoadPageError(page: page, error: error)

                guestbookListDelegate?.screenlet?(screenlet: self, 
                    onGuestbookListError: error)
            }

    - `onSelectedRow`: Called when an item is selected in the list. Override 
      this method to call your delegate's `screenlet(_:onGuestbookSelected:)` 
      method: 

            override public func onSelectedRow(_ row: AnyObject) {
                guestbookListDelegate?.screenlet?(screenlet: self, 
                    onGuestbookSelected: row as! GuestbookModel)
            }

Awesome! Your Screenlet class is finished. You're also done with Guestbook List 
Screenlet! The next section in this Learning Path shows you how to create Entry 
List Screenlet to display a guestbook's entries. 
