# Getting Started with Entry List Screenlet [](id=getting-started-with-entry-list-screenlet-ios)

Like Guestbook List Screenlet, you'll create Entry List Screenlet in its own 
folder inside your app's project. Create this folder now: 

1.  In the Finder, create the `EntryListScreenlet` folder inside your root 
    project folder (on the same level as the `GuestbookListScreenlet` folder). 

2.  Drag and drop the `EntryListScreenlet` folder from the Finder into your 
    Xcode project, under the root project (on the same level as the 
    `GuestbookListScreenlet` folder). In the dialog that appears, select *Copy 
    items if needed*, *Create groups*, and the *Liferay Guestbook* target. Then 
    click *Finish*. The `EntryListScreenlet` folder now appears in your project. 

    ![Figure 1: After adding the `EntryListScreenlet` folder, your project should look something like this.](../../../images/ios-lp-entry-proj-nav.png)

Now you're ready to begin! 

## Creating the Model Class [](id=creating-the-model-class)

Recall that you need a model class to represent entities retrieved from 
@product@. The model class you'll create for guestbook entries, `EntryModel`, 
creates `EntryModel` objects that represent guestbook entries retrieved from the 
Guestbook portlet. 

Create the following `EntryModel` class alongside the `GuestbookModel` class in 
the `model` folder: 

1.  In the Project navigator, right-click the `model` folder and select *New 
    File*. In the dialog that appears, fill out each screen as follows: 

    - Select *iOS* &rarr; *Source* &rarr; *Cocoa Touch Class*, and click *Next*. 
    - Name the class `EntryModel`, set it to extend `NSObject`, select *Swift* 
      for the language, and click *Next*. 
    - Make sure the `model` folder and group is selected, as well as the 
      *Liferay Guestbook* target (these should be selected by default). Click 
      *Create*. 

    The new class now opens in the editor. 

2.  Replace the class file's contents with this code: 

        import UIKit

        @objc public class EntryModel: NSObject {

            public let attributes: [String:AnyObject]

            public var entryId: Int64 {
                return attributes["entryId"]?.int64Value ?? 0
            }

            public var groupId: Int64 {
                return attributes["groupId"]?.int64Value ?? 0
            }

            public var companyId: Int64 {
                return attributes["companyId"]?.int64Value ?? 0
            }

            public var userId: Int64 {
                return attributes["userId"]?.int64Value ?? 0
            }

            public var userName: String {
                return attributes["userName"] as? String ?? ""
            }

            public var createDate: Int64 {
                return attributes["createDate"]?.int64Value ?? 0
            }

            public var modifiedDate: Int64 {
                return attributes["modifiedDate"]?.int64Value ?? 0
            }

            public var name: String {
                return attributes["name"] as? String ?? ""
            }

            public var email: String {
                return attributes["email"] as? String ?? ""
            }

            public var message: String {
                return attributes["message"] as? String ?? ""
            }

            public var guestbookId: Int64 {
                return attributes["guestbookId"]?.int64Value ?? 0
            }

            //MARK: Initializer

            public init(attributes: [String:AnyObject]) {
                self.attributes = attributes
            }

        }

Besides working with entries instead of guestbooks, this class is almost 
identical to `GuestbookModel`. For an explanation of the code, see 
[the article on getting started with Guestbook List Screenlet](/develop/tutorials/-/knowledge_base/7-0/getting-started-with-guestbook-list-screenlet-ios). 

Next, you'll create the Screenlet's UI. 
