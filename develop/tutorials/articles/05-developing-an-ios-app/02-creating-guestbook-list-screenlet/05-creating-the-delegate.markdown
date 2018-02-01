# Creating Guestbook List Screenlet's Delegate [](id=creating-guestbook-list-screenlets-delegate)

A delegate is a Screenlet component that lets other classes to respond to a 
Screenlet's actions. For example, 
[Login Screenlet's delegate](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-ios#delegate) 
lets the app developer implement methods that respond to login success or 
failure. Note that the 
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios) 
for each Screenlet that comes with Liferay Screens lists the Screenlet's 
delegate methods. 

You can also create a delegate for your own Screenlet. Guestbook List Screenlet 
should have a delegate protocol that defines the following methods: 

-   `screenlet(_:onGuestbookListResponse:)`: Receives the `GuestbookModel` 
    results when the server call succeeds. This lets app developers respond to a 
    successful server call. 
-   `screenlet(_:onGuestbookListError:)`: Receives the `NSError` object when the 
    server call fails. This lets app developers respond to a failed server call. 
-   `screenlet(_:onGuestbookSelected:)`: Receives the `GuestbookModel` when a 
    user selects it in the list. This lets app developers respond to a guestbook 
    selection by the user. 

You'll create this delegate in the same file as the Screenlet class. Later, 
you'll finish creating the Screenlet class itself. 

Follow these steps to create Guestbook List Screenlet's delegate: 

1.  In the Project navigator, right-click the `GuestbookListScreenlet` folder 
    and select *New File*. In the dialog that appears, fill out each screen as 
    follows: 

    - Select *iOS* &rarr; *Source* &rarr; *Cocoa Touch Class*, and click *Next*. 
    - Name the class `GuestbookListScreenlet`, set it to extend 
      `BaseListScreenlet`, select *Swift* for the language, and click *Next*.
    - Make sure the `GuestbookListScreenlet` folder and group is selected, as 
      well as the *Liferay Guestbook* target (these should be selected by 
      default). Click *Create*. 

2.  In the new `GuestbookListScreenlet` class, import `LiferayScreens`, make the 
    class `public`, and delete any placeholder comments in the class body. 

3.  In between the `import` statements and the class declaration, add the 
    following code: 

        @objc public protocol GuestbookListScreenletDelegate : BaseScreenletDelegate {

            @objc optional func screenlet(screenlet: GuestbookListScreenlet,
                                    onGuestbookListResponse guestbooks: [GuestbookModel])

            @objc optional func screenlet(screenlet: GuestbookListScreenlet,
                                    onGuestbookListError error: NSError)

            @objc optional func screenlet(screenlet: GuestbookListScreenlet,
                                    onGuestbookSelected guestbook: GuestbookModel)

        }

    This defines the `GuestbookListScreenletDelegate` protocol, which extends 
    [the `BaseScreenletDelegate` protocol](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenlet.swift). 
    Delegates for custom Screenlets, like Guestbook List Screenlet, must extend 
    `BaseScreenletDelegate`. The rest of `GuestbookListScreenletDelegate` 
    defines the delegate methods you'll use later to respond to the Screenlet's 
    events. 

The contents of `GuestbookListScreenlet.swift` should now look like this: 

    import UIKit
    import LiferayScreens

    @objc public protocol GuestbookListScreenletDelegate : BaseScreenletDelegate {

        @objc optional func screenlet(screenlet: GuestbookListScreenlet,
                                  onGuestbookListResponse guestbooks: [GuestbookModel])

        @objc optional func screenlet(screenlet: GuestbookListScreenlet,
                                  onGuestbookListError error: NSError)

        @objc optional func screenlet(screenlet: GuestbookListScreenlet,
                                  onGuestbookSelected guestbook: GuestbookModel)

    }

    public class GuestbookListScreenlet: BaseListScreenlet {

    }

Nice work! Now you're ready to complete the Screenlet class. 
