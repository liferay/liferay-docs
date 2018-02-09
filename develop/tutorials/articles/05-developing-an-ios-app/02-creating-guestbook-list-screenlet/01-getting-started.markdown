# Getting Started with Guestbook List Screenlet [](id=getting-started-with-guestbook-list-screenlet-ios)

Before creating a Screenlet, you should know how you'll use it. If you plan to 
use it in only one app, then you can create it in that app's project. If you 
need to use it in several apps, however, then it's best to create it in a 
separate project for redistribution. For information on creating Screenlets for 
redistribution, see the tutorial 
[Packaging iOS Themes](/develop/tutorials/-/knowledge_base/7-0/packaging-ios-themes). 
Even though that tutorial is for packaging Themes, you can use the same steps to 
package Screenlets. 

Since you'll use Guestbook List Screenlet in only this app, you can create it in 
a new folder inside the app's project. Create this folder now: 

1.  In the Finder, create the `GuestbookListScreenlet` folder inside the root 
    project folder. 

    ![Figure 1: The new `GuestbookListScreenlet` folder should be inside your root project folder.](../../../images/ios-lp-guestbook-screenlet-finder.png)

2.  Drag and drop the `GuestbookListScreenlet` folder from the Finder into your 
    Xcode project, under the root project. In the dialog that appears, select 
    *Copy items if needed*, *Create groups*, and the *Liferay Guestbook* target. 
    Then click *Finish*. The `GuestbookListScreenlet` folder now appears in your 
    project. 

    ![Figure 2: After adding the `GuestbookListScreenlet` folder, your project should look something like this.](../../../images/ios-lp-gb-screenlet-proj-nav.png)

Great! Now you have a folder to create Guestbook List Screenlet in. Before 
getting started, you should understand how pagination works in in list 
Screenlets. 

## Pagination [](id=pagination)

To ensure that users can scroll smoothly through large lists of items, list 
Screenlets support 
[fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination). 
Support for this is built into the list Screenlet framework. You'll see this as 
you construct your list Screenlet. For example, several methods have parameters 
for the start row and end row of a page in the list. 

Now you're ready to begin creating the Screenlet! 

## Creating the Model Class [](id=creating-the-model-class)

Liferay Screens typically receives entities from a @product@ instance as a 
`[String:AnyObject]` dictionary, where `String` is the entity's attribute and 
`AnyObject` is the attribute's value. Although your Screenlet can use these 
dictionary objects, it's often easier to create a *model class* that converts 
each into an object that represents the corresponding entity in the portal. 
Model classes are especially convenient for complex entities composed of many 
attribute-value pairs, like guestbooks in the Guestbook portlet. 

Your model class must contain all the code necessary to transform each 
`[String:AnyObject]` dictionary that comes back from the server into a model 
object that represents a guestbook. This includes a public constant for holding 
each `[String:AnyObject]` dictionary, a public initializer that sets this 
constant, and a public property for each attribute value. 

The model class you'll create for Guestbook List Screenlet, `GuestbookModel`, 
creates `GuestbookModel` objects that represent guestbooks retrieved from the 
Guestbook portlet. You'll create this model class in a separate folder outside 
of the `GuestbookListScreenlet` folder. In this case, it makes sense to organize 
your code this way because other Screenlets may also use the model class. For 
example, if a Screenlet that edits guestbooks existed, it would also need 
`GuestbookModel` objects. Putting the model class in a separate folder makes it 
clear that this class doesn't belong exclusively to a single Screenlet. 

Follow these steps to create Guestbook List Screenlet's model class: 

1.  In the Finder, create the `model` folder inside your root project folder. 

    ![Figure 3: The new `model` folder should be inside your root project folder.](../../../images/ios-lp-model-finder.png)

2.  Drag and drop the `model` folder from the Finder into your Xcode project, 
    under the root project. In the dialog that appears, select *Copy items if 
    needed*, *Create groups*, and the *Liferay Guestbook* target. Then click 
    *Finish*. The `model` folder now appears in your project. 

    ![Figure 4: After adding the `model` folder, your project should look something like this.](../../../images/ios-lp-model-project-nav.png)

3.  In the Project navigator, right-click the `model` folder and select *New 
    File*. In the dialog that appears, fill out each screen as follows: 

    - Select *iOS* &rarr; *Source* &rarr; *Cocoa Touch Class*, and click *Next*. 
    - Name the class `GuestbookModel`, set it to extend `NSObject`, select 
      *Swift* for the language, and click *Next*.
    - Make sure the `model` folder and group is selected, as well as the 
      *Liferay Guestbook* target (these should be selected by default). Click 
      *Create*.

    The new class now opens in the editor. 

4.  Replace the class file's contents with this code: 

        import UIKit

        @objc public class GuestbookModel: NSObject {

            public let attributes: [String:AnyObject]

            public var guestbookId: Int64 {
                return attributes["guestbookId"]?.int64Value ?? 0
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

            //MARK: Initializer

            public init(attributes: [String:AnyObject]) {
                self.attributes = attributes
            }

        }

This class creates `GuestbookModel` objects that represent guestbooks from the 
Guestbook portlet. The `[String:AnyObject]` dictionary contains the data of a 
guestbook retrieved from the portlet. The initializer sets this dictionary to 
the `attributes` property. Each computed property returns the value of a 
guestbook parameter in `attributes`. For example, the `guestbookId` property 
returns the value of the `guestbookId` parameter, the `groupId` property returns 
the value of the `groupId` parameter, and so on. To see how the Guestbook 
portlet defines these parameters, see the 
[section on generating the portlet's back end](/develop/tutorials/-/knowledge_base/7-0/generating-the-back-end) 
in the Liferay Web Application Learning Path. 

Also note that each computed property defaults to an empty string or `0`, 
depending on the property's type, if the parameter contains a value that can't 
be represented as that type. This prevents the app from crashing if the 
parameter doesn't have an appropriate value. For example, if the `guestbookId` 
parameter contains `nil`, then the `guestbookId` property returns `0`. 

Great! Now you have a model class for guestbooks. Next, you'll create the 
Screenlet's UI. 
