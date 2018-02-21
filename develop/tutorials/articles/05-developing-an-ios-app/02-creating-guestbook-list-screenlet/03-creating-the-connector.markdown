# Creating Guestbook List Screenlet's Connector [](id=creating-guestbook-list-screenlets-connector)

Connectors are Screenlet components that make server calls. Non-list Screenlets 
don't require Connectors--they can make server calls in Interactors instead. 
Connectors, however, provide a layer of abstraction by making the server call 
outside the Interactor. This leaves the Interactor to instantiate the Connector 
and receive the server call's results. List Screenlets exploit this 
architectural advantage by requiring Connectors. 

First, you'll create a folder for the Connector. 

## Creating Your Connector's Folder [](id=creating-your-connectors-folder)

Follow these steps to create your Connector's folder: 

1.  In the Finder, create the `Connector` folder inside your project's 
    `GuestbookListScreenlet` folder. 

2.  Drag and drop the `Connector` folder from the Finder into your Xcode 
    project, under the `GuestbookListScreenlet` folder. In the dialog that 
    appears, select *Copy items if needed*, *Create groups*, and the *Liferay 
    Guestbook* target. Then click *Finish*. The `Connector` folder now appears 
    in your project. 

    ![Figure 1: The new `Connector` folder should be inside the Screenlet's folder.](../../../images/ios-lp-connector-folder.png)

Now you're ready to create the Connector. 

## Creating the Connector [](id=creating-the-connector)

List Screenlet Connectors must extend 
[the `PaginationLiferayConnector` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/PaginationLiferayConnector.swift), 
which Liferay Screens provides to enable most of the functionality required by 
list Screenlet Connectors. Extending this class lets you focus on the 
functionality unique to your Connector. Your list Screenlet's Connector class 
must contain any properties it needs to make the server call, and an initializer 
that sets them. To support pagination, the initializer must also contain the 
following arguments, which you'll pass to the superclass initializer: 

- `startRow`: The number representing the page's first row. 
- `endRow`: The number representing the page's last row. 
- `computeRowCount`: Whether to call the Connector's `doAddRowCountServiceCall` 
  method (you'll learn about this method shortly). 

Follow these steps to create Guestbook List Screenlet's Connector: 

1.  In the Project navigator, right-click the `Connector` folder and select 
    *New File*. In the dialog that appears, fill out each screen as follows: 

    - Select *iOS* &rarr; *Source* &rarr; *Cocoa Touch Class*, and click *Next*. 
    - Name the class `GuestbookListPageLiferayConnector`, set it to extend 
      `PaginationLiferayConnector`, select *Swift* for the language, and click 
      *Next*. 
    - Make sure the `Connector` folder and group is selected, as well as the 
      *Liferay Guestbook* target (these should be selected by default). Click 
      *Create*. 

2.  In the new class, import `LiferayScreens` and add a public `Int64` constant 
    called `groupId`. This constant holds the ID of the site the Connector 
    retrieves guestbooks from. Your Connector should now look like this: 

        import UIKit
        import LiferayScreens

        class GuestbookListPageLiferayConnector: PaginationLiferayConnector {

            public let groupId: Int64

        }

3.  Create an initializer that takes the arguments `startRow`, `endRow`, 
    `computeRowCount`, and `groupId`. In this initializer, set the `groupId` 
    constant to the corresponding argument, then call the superclass initializer 
    with the remaining arguments. Add the initializer as follows: 

        public init(startRow: Int, endRow: Int, computeRowCount: Bool, groupId: Int64) {
            self.groupId = groupId

            super.init(startRow: startRow, endRow: endRow, computeRowCount: computeRowCount)
        }

4.  Override the `doAddPageRowsServiceCall` method to make the server call that 
    retrieves guestbooks from the portlet. This method must call the Guestbook 
    SDK service method `getGuestbooksWithGroupId`, which retrieves guestbooks. 
    To do this, you must first create a `LRGuestbookService_v7` instance from 
    the session. Then call the service's `getGuestbooksWithGroupId` method with 
    `groupId`, `startRow`, and `endRow`: 

        public override func doAddPageRowsServiceCall(session: LRBatchSession, startRow: Int, endRow: Int, 
            obc: LRJSONObjectWrapper?) {
                let service = LRGuestbookService_v7(session: session)

                do {
                    try service!.getGuestbooksWithGroupId(groupId, start: Int32(startRow), end: Int32(endRow))
                }
                catch {
                    // ignore error: the service method returns nil because 
                    // the request is sent later, in batch
                }

        }

    Note that you don't need to do anything in the `catch` statement because the 
    request is sent later, in batch. The `session` type `LRBatchSession` handles 
    this for you. You'll receive the request's results elsewhere, once the 
    request completes. 

5.  Override the `doAddRowCountServiceCall` method to make the server call that 
    retrieves the total number of guestbooks from the portlet. This enables 
    pagination. This method must call the Guestbook SDK service method 
    `getGuestbooksCount`, which retrieves the total number of guestbooks. To do 
    this, you must first create a `LRGuestbookService_v7` service instance from 
    the session. Then call the service's `getGuestbooksCount` method with 
    `groupId`:

        override public func doAddRowCountServiceCall(session: LRBatchSession) {
            let service = LRGuestbookService_v7(session: session)

            do {
                try service!.getGuestbooksCount(withGroupId: groupId)
            }
            catch {
                // ignore error: the service method returns nil because 
                // the request is sent later, in batch
            }
        }

    As in the previous step, you don't need to do anything in the `catch` 
    statement. 

Awesome! Your Connector is finished. Now you're ready to create the Interactor. 
