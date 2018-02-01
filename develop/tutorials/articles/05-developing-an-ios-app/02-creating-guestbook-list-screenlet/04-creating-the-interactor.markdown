# Creating Guestbook List Screenlet's Interactor [](id=creating-guestbook-list-screenlets-interactor-ios)

Interactors implement your Screenlet's actions. In non-list Screenlets, this can 
include making the server call. List Screenlets, however, make server calls via 
Connectors. Also, loading entities is usually the only action a user can take in 
a list Screenlet. Therefore, list Screenlet Interactors typically only need to 
instantiate the Connector and receive the server call's results. This is the 
case for Guestbook List Screenlet's Interactor. You'll create this Interactor 
now. 

## Creating Your Interactor's Folder [](id=creating-your-interactors-folder)

Follow these steps to create your Interactor's folder: 

1.  In the Finder, create the `Interactor` folder inside your project's 
    `GuestbookListScreenlet` folder. 

2.  Drag and drop the `Interactor` folder from the Finder into your Xcode 
    project, under the `GuestbookListScreenlet` folder. In the dialog that 
    appears, select *Copy items if needed*, *Create groups*, and the *Liferay 
    Guestbook* target. Then click *Finish*. The `Interactor` folder now appears 
    in your project. 

    ![Figure 1: The new `Interactor` folder should be inside the Screenlet's folder.](../../../images/ios-lp-interactor-folder.png)

Now you're ready to create the Interactor. 

## Creating the Interactor [](id=creating-the-interactor)

The Interactor class of a list Screenlet that implements fluent pagination must 
extend 
[Liferay Screens's `BaseListPageLoadInteractor` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseListScreenlet/BaseListPageLoadInteractor.swift). 
This class provides most of the functionality required by list Screenlet 
Interactors. Your Interactor class must also contain any properties your 
Screenlet needs and an initializer that sets them. This initializer needs 
arguments for the following properties, which it passes to the superclass 
initializer: 

- `screenlet`: A `BaseListScreenlet` reference. This ensures the Interactor 
  always has a Screenlet reference. 
- `page`: The page number to retrieve. 
- `computeRowCount`: Whether to call the Connector's `doAddRowCountServiceCall` 
  method. 

Follow these steps to create Guestbook List Screenlet's Interactor: 

1.  In the Project navigator, right-click the `Interactor` folder and select 
    *New File*. In the dialog that appears, fill out each screen as follows: 

    - Select *iOS* &rarr; *Source* &rarr; *Cocoa Touch Class*, and click *Next*. 
    - Name the class `GuestbookListPageLoadInteractor`, set it to extend 
      `BaseListPageLoadInteractor`, select *Swift* for the language, and click 
      *Next*. 
    - Make sure the `Interactor` folder and group is selected, as well as the 
      *Liferay Guestbook* target (these should be selected by default). Click 
      *Create*. 

2.  In the new class, import `LiferayScreens` and add a private `Int64` constant 
    called `groupId`. This constant holds the ID of the site guestbooks are 
    retrieved from. Your Interactor should now look like this: 

        import UIKit
        import LiferayScreens

        class GuestbookListPageLoadInteractor: BaseListPageLoadInteractor {

            private let groupId: Int64

        }

3.  Create an initializer that takes the arguments `screenlet`, `page`, 
    `computeRowCount`, and `groupId`. In this initializer, set the `groupId` 
    constant to the corresponding argument, then call the superclass initializer 
    with the remaining arguments. Note that if the the `groupId` is `0`, the 
    `groupId` setting in `liferay-server-context.plist` is used instead. Add the 
    initializer as follows: 

        init(screenlet: BaseListScreenlet,
            page: Int,
            computeRowCount: Bool,
            groupId: Int64) {

            self.groupId = (groupId != 0) ? groupId : LiferayServerContext.groupId

            super.init(screenlet: screenlet, page: page, computeRowCount: computeRowCount)
        }

4.  Override the `createListPageConnector` method to create and return an 
    instance of your Connector, `GuestbookListPageLiferayConnector`. This method 
    must first get a reference to the Screenlet via the `screenlet` property. 
    When calling the Connector's initializer, use `screenlet.firstRowForPage` to 
    convert the page number (`page`) to the page's start and end indices. You 
    must also pass the initializer any other properties it needs, like 
    `groupId`. Add this `createListPageConnector` method to your Interactor 
    class: 

        public override func createListPageConnector() -> PaginationLiferayConnector {
            let screenlet = self.screenlet as! BaseListScreenlet

            return GuestbookListPageLiferayConnector(
                startRow: screenlet.firstRowForPage(self.page),
                endRow: screenlet.firstRowForPage(self.page + 1),
                computeRowCount: self.computeRowCount,
                groupId: groupId)
        }

5.  Override the `convertResult` method to convert each `[String:AnyObject]` 
    result into a `GuestbookModel` object. The Screenlet calls this method once 
    for each guestbook retrieved from the server. Add this method as follows: 

        override public func convertResult(_ serverResult: [String:AnyObject]) -> AnyObject {

            return GuestbookModel(attributes: serverResult)
        }

6.  Override the `cacheKey` method to return a key that can be used with 
    [offline mode](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
    Although Guestbook List Screenlet won't initially support offline mode, this 
    method is still required. For this Screenlet, the `groupId` serves as a 
    sufficient key. Add this method as follows: 

        override public func cacheKey(_ op: PaginationLiferayConnector) -> String {
            return "\(groupId)"
        }

Great! Your Interactor is finished. Next, you'll create the delegate. 
