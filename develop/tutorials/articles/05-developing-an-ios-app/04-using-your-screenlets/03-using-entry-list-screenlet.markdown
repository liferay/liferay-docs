# Using Entry List Screenlet [](id=using-entry-list-screenlet-ios)

You'll use Entry List Screenlet the same way you use any Screenlet: insert it in 
a storyboard scene, then conform the scene's view controller class to the 
Screenlet's delegate protocol. You'll follow these steps now to use Entry List 
Screenlet in the entries scene. 

## Adding Entry List Screenlet to the Entries Scene [](id=adding-entry-list-screenlet-to-the-entries-scene)

Follow these steps to add Entry List Screenlet to the entries scene: 

1.  In your storyboard, select the entries scene's view controller. Then drag 
    and drop a plain view (`UIView`) from the Object Library to the view 
    controller. In the outline view, this new view should be nested under the 
    view controller's existing view. 

    ![Figure 1: The new view is nested under the view controller's existing view.](../../../images/ios-lp-entries-view-outline.png)

2.  Resize the new view to take up all the space below the navigation bar. Then 
    open the *Add New Constraints* menu at the bottom-right of the canvas. In 
    this menu, set *Spacing to nearest neighbor* to 0 on all sides, and click 
    the *Add 4 Constraints* button. 

    ![Figure 2: Set the new view's *Spacing to nearest neighbor* constraints to 0 on all sides.](../../../images/ios-lp-gb-constraints.png)

3.  With the new view still selected, open the Identity inspector and set the 
    view's custom class to `EntryListScreenlet`. The view now appears as Entry 
    List Screenlet in the outline view. 

Great! The entries scene now contains Entry List Screenlet. Next, you'll conform 
the scene's view controller class to the Screenlet's delegate. 

## Conforming to the Screenlet's Delegate Protocol [](id=conforming-to-the-screenlets-delegate-protocol)

To respond to Entry List Screenlet's events, `EntriesViewController` must 
conform to the `EntryListScreenletDelegate` protocol. You created this delegate 
when creating the Screenlet. This delegate defines methods for responding to the 
success or failure to retrieve entries, and the selection of an entry in the 
list. 

Follow these steps to conform `EntriesViewController` to the 
`EntryListScreenletDelegate` protocol: 

1.  Import `LiferayScreens`, and in the class declaration set 
    `EntriesViewController` to adopt the `EntryListScreenletDelegate` protocol. 
    The first few lines of the class should look like this: 

        import UIKit
        import LiferayScreens

        class EntriesViewController: UIViewController, EntryListScreenletDelegate {...

2.  Implement the `EntryListScreenletDelegate` method 
    `screenlet(_:onEntryListResponse:)`. Recall that this method lets you 
    respond to a successful server call. Its arguments include the `EntryModel` 
    objects that result from such a call. Since the Screenlet already displays 
    these objects, you don't need to do anything in this method: 

        func screenlet(screenlet: EntryListScreenlet, onEntryListResponse entries: [EntryModel]) {

        }

3.  Implement the `EntryListScreenletDelegate` method 
    `screenlet(_:onEntryListError:)`. Recall that this method lets you respond 
    to a failed server call. Its arguments include the resulting `NSError` 
    object. You don't have to do anything in this method, but it's a good idea 
    to print the error: 

        func screenlet(screenlet: EntryListScreenlet, onEntryListError error: NSError) {
            print("Failed to retrieve guestbook entries: \(error.localizedDescription)")
        }

4.  Implement the `EntryListScreenletDelegate` method 
    `screenlet(_:onEntrySelected:)`. Recall that this method lets you respond 
    when a user selects an entry in the list. It does so by including the 
    selected `EntryModel` object in its arguments. Since there's currently not a 
    scene or other action that handles detailed information about an entry, you 
    don't need to do anything in this method: 

        func screenlet(screenlet: EntryListScreenlet, onEntrySelected entry: EntryModel) {

        }

5.  Now you must get an Entry List Screenlet reference. You'll do this by 
    creating an outlet to the Screenlet. Return to your storyboard and enter the 
    Assistant editor to display `EntriesViewController`'s code and the 
    storyboard side by side. With Entry List Screenlet selected in the 
    storyboard, Control-drag from the Screenlet to the `EntriesViewController` 
    class, just below the class declaration. Release your mouse button, enter 
    the following information in the dialog that appears, and click *Connect*: 

    - **Connection:** Outlet
    - **Name:** screenlet
    - **Type:** EntryListScreenlet
    - **Storage:** Weak

    Xcode then adds the following code inside the `EntriesViewController` class: 

        @IBOutlet weak var screenlet: EntryListScreenlet!

6.  Use this new `screenlet` variable to set the view controller as the 
    Screenlet's delegate. Do this in the `viewDidLoad()` method by deleting the 
    placeholder comment and inserting this code below the call to 
    `super.viewDidLoad()`: 

        self.screenlet.delegate = self

7.  Next, you must set the guestbook the Screenlet retrieves entries from. To do 
    this, set the Screenlet's `guestbookId` property to the selected guestbook's 
    ID, immediately below the Screenlet's delegate assignment in the 
    `viewDidLoad()` method: 

        self.screenlet.guestbookId = selectedGuestbook!.guestbookId

8.  Lastly, you must set the selected guestbook's name to the navigation bar's 
    title. This lets the scene reflect the guestbook selection in the UI. To do 
    this, add the following line of code at the end of the `viewDidLoad()` 
    method: 

        self.navigationItem.title = selectedGuestbook!.name

    Your `viewDidLoad()` method should now look like this: 

        override func viewDidLoad() {
            super.viewDidLoad()

            self.screenlet.delegate = self
            self.screenlet.guestbookId = selectedGuestbook!.guestbookId
            self.navigationItem.title = selectedGuestbook!.name
        }

Now you're ready to test your handiwork. Make sure your portal containing the 
Guestbook portlet is running, and that the portlet contains a couple guestbooks 
that have entries. Then run the app and log in with your credentials. You should 
see the list of guestbooks displayed by Guestbook List Screenlet. Selecting a 
guestbook in the list takes you to the entries scene, which uses Entry List 
Screenlet to display a list of that guestbook's entries. Pressing the back 
button returns you to the guestbooks scene, where you can select a different 
guestbook. 

![Figure 3: After login, Guestbook List Screenlet displays the list of guestbooks from the portlet.](../../../images/ios-lp-gb-screenlet.png)

![Figure 4: Selecting a guestbook displays a list of that guestbook's entries via Entry List Screenlet.](../../../images/ios-lp-entries-screenlet.png)

Congratulations! Now you know how to use Liferay Screens and create your own 
Screenlets. This opens up a world of possibilities for developing apps that 
leverage @product@. Although you learned a great deal in this Learning Path, 
there's still more. You can customize your Screenlet's appearance, package 
Screenlets and Themes for redistribution, and even add multiple actions to a 
Screenlet. These topics, and more, are covered in 
[the tutorials on iOS apps with Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/ios-apps-with-liferay-screens). 
