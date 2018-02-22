# Creating Entry List Screenlet's Delegate [](id=creating-entry-list-screenlets-delegate)

Recall that a delegate lets other classes respond to your Screenlet's actions. 
Like you did for Guestbook List Screenlet, you'll create a delegate for Entry 
List Screenlet that can respond to a successful server call, a failed server 
call, and an item selection in the list. This delegate must therefore define 
these methods: 

-   `screenlet(_:onEntryListResponse:)`: Receives the `EntryModel` results when 
    the server call succeeds. This lets app developers respond to a successful 
    server call. 
-   `screenlet(_:onEntryListError:)`: Receives the `NSError` object when the 
    server call fails. This lets app developers respond to a failed server call. 
-   `screenlet(_:onEntrySelected:)`: Receives the `EntryModel` when a user 
    selects it in the list. This lets app developers respond to an entry 
    selection by the user. 

You'll create this delegate in the same file as the Screenlet class. Later, 
you'll finish creating the Screenlet class itself. 

Follow these steps to create Entry List Screenlet's delegate: 

1.  In the Project navigator, right-click the `EntryListScreenlet` folder and 
    select *New File*. In the dialog that appears, fill out each screen as 
    follows: 

    - Select *iOS* &rarr; *Source* &rarr; *Cocoa Touch Class*, and click *Next*. 
    - Name the class `EntryListScreenlet`, set it to extend `BaseListScreenlet`, 
      select *Swift* for the language, and click *Next*. 
    - Make sure the `EntryListScreenlet` folder and group is selected, as well 
      as the *Liferay Guestbook* target (these should be selected by default). 
      Click *Create*. 

2.  In the new `EntryListScreenlet` class, import `LiferayScreens`, make the 
    class `public`, and delete any placeholder comments in the class body. 

3.  In between the `import` statements and the class declaration, add the 
    following code: 

        @objc public protocol EntryListScreenletDelegate : BaseScreenletDelegate {

            @objc optional func screenlet(screenlet: EntryListScreenlet,
                                  onEntryListResponse entries: [EntryModel])

            @objc optional func screenlet(screenlet: EntryListScreenlet,
                                  onEntryListError error: NSError)

            @objc optional func screenlet(screenlet: EntryListScreenlet,
                                  onEntrySelected entry: EntryModel)

        }

    This delegate is almost identical to that of Guestbook List Screenlet, 
    `GuestbookListScreenletDelegate`. The only difference is that 
    `EntryListScreenletDelegate` works with entries instead of guestbooks. 

The contents of `EntryListScreenlet.swift` should now look like this: 

    import UIKit
    import LiferayScreens

    @objc public protocol EntryListScreenletDelegate : BaseScreenletDelegate {

        @objc optional func screenlet(screenlet: EntryListScreenlet,
                                  onEntryListResponse entries: [EntryModel])

        @objc optional func screenlet(screenlet: EntryListScreenlet,
                                  onEntryListError error: NSError)

        @objc optional func screenlet(screenlet: EntryListScreenlet,
                                  onEntrySelected entry: EntryModel)

    }

    public class EntryListScreenlet: BaseListScreenlet {

    }

Nice work! Now you're ready to complete the Screenlet class. 
