# Creating Entry List Screenlet's Interactor [](id=creating-entry-list-screenlets-interactor-ios)

Recall that list Screenlets require an Interactor to instantiate the Connector 
and receive the server call's results. In this article, you'll create Entry List 
Screenlet's Interactor. 

Because this Interactor is so similar to that of Guestbook List Screenlet, the 
steps to create it aren't explained in detail. Focus is instead placed on the 
few places in the code where the Interactors diverge. For a full explanation of 
the code, see 
[the article on creating Guestbook List Screenlet's Interactor](/develop/tutorials/-/knowledge_base/7-0/creating-guestbook-list-screenlets-interactor-ios). 

## Creating Your Interactor's Folder [](id=creating-your-interactors-folder)

Follow these steps to create your Interactor's folder: 

1.  In the Finder, create the `Interactor` folder inside your project's 
    `EntryListScreenlet` folder. 

2.  Drag and drop the `Interactor` folder from the Finder into your Xcode 
    project, under the `EntryListScreenlet` folder. In the dialog that appears, 
    select *Copy items if needed*, *Create groups*, and the *Liferay Guestbook* 
    target. Then click *Finish*. The `Interactor` folder now appears in your 
    project. 

Now you're ready to create the Interactor. 

## Creating the Interactor [](id=creating-the-interactor)

Recall that the Interactor class of a list Screenlet that implements fluent 
pagination must extend 
[the `BaseListPageLoadInteractor` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/BaseListPageLoadInteractor.swift). 
Your Interactor class must also contain any properties the Screenlet needs, and 
an initializer that sets them. This initializer also needs arguments for the 
following properties, which it passes to the superclass initializer: 

- `screenlet`: A `BaseListScreenlet` reference. This ensures the Interactor 
  always has a Screenlet reference. 
- `page`: The page number to retrieve. 
- `computeRowCount`: Whether to call the Connector's `doAddRowCountServiceCall` 
  method. 

Follow these steps to create Entry List Screenlet's Interactor: 

1.  In the Project navigator, right-click the `Interactor` folder you added 
    above and select *New File*. In the dialog that appears, fill out each 
    screen as follows: 

    - Select *iOS* &rarr; *Source* &rarr; *Cocoa Touch Class*, and click *Next*. 
    - Name the class `EntryListPageLoadInteractor`, set it to extend 
      `BaseListPageLoadInteractor`, select *Swift* for the language, and click 
      *Next*. 
    - Make sure the `Interactor` folder and group is selected, as well as the 
      *Liferay Guestbook* target (these should be selected by default). Click 
      *Create*. 

2.  Replace the class file's contents with this code: 

        import UIKit
        import LiferayScreens

        class EntryListPageLoadInteractor: BaseListPageLoadInteractor {

            private let groupId: Int64
            private let guestbookId: Int64

            init(screenlet: BaseListScreenlet,
                 page: Int,
                 computeRowCount: Bool,
                 groupId: Int64,
                 guestbookId: Int64) {

                self.groupId = (groupId != 0) ? groupId : LiferayServerContext.groupId
                self.guestbookId = guestbookId

                super.init(screenlet: screenlet, page: page, computeRowCount: computeRowCount)
            }

            public override func createListPageConnector() -> PaginationLiferayConnector {
                let screenlet = self.screenlet as! BaseListScreenlet

                return EntryListPageLiferayConnector(
                    startRow: screenlet.firstRowForPage(self.page),
                    endRow: screenlet.firstRowForPage(self.page + 1),
                    computeRowCount: self.computeRowCount,
                    groupId: groupId,
                    guestbookId: guestbookId)
            }

            override public func convertResult(_ serverResult: [String:AnyObject]) -> AnyObject {

                return EntryModel(attributes: serverResult)
            }

            override public func cacheKey(_ op: PaginationLiferayConnector) -> String {
                return "\(groupId)-\(guestbookId)"
            }

        }

    This class is almost identical to Guestbook List Screenlet's Interactor, 
    `GuestbookListPageLoadInteractor`. The only real difference is that 
    `EntryListPageLoadInteractor` handles entries. It therefore needs a 
    `guestbookId` variable to define the guestbook to retrieve entries from. 
    This variable is set in the initializer and then used in the 
    `createListPageConnector` method to create a `EntryListPageLiferayConnector` 
    instance. The `convertResult` method receives each `[String:AnyObject]` 
    entry from the server and transforms it into an `EntryModel` object. Also 
    recall that the `cacheKey` method must return a key that can be used with 
    online mode. For entries, a combination of the `groupId` and `guestbookId` 
    is a sufficient key. 

Great! Your Interactor is finished. Next, you'll create the delegate. 
