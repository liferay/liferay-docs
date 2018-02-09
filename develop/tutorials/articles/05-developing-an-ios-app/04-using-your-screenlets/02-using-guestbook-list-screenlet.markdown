# Using Guestbook List Screenlet [](id=using-guestbook-list-screenlet-ios)

The steps for using Guestbook List Screenlet are the same as those for using any 
Screenlet: 

1.  Insert the Screenlet in the storyboard scene where you want it to appear. 
    You do this by adding an empty view to the scene, and then setting the 
    Screenlet class as the view's custom class. 

2.  Conform the scene's view controller class to the Screenlet's delegate 
    protocol. This lets the view controller respond to the Screenlet's events. 

You'll follow these steps to use Guestbook List Screenlet in the guestbooks 
scene. You'll also take an extra step to trigger the segue to the entries scene 
when a user selects a guestbook. 

## Adding Guestbook List Screenlet to the Guestbooks Scene [](id=adding-guestbook-list-screenlet-to-the-guestbooks-scene)

Follow these steps to add Guestbook List Screenlet to the guestbooks scene: 

1.  In your storyboard, first select the guestbooks scene's view controller. 
    Then drag and drop a plain view (`UIView`) from the Object Library to the 
    view controller. In the outline view, this new view should be nested under 
    the view controller's existing view. 

    ![Figure 1: The new view is nested under the view controller's existing view.](../../../images/ios-lp-gb-view-outline.png)

2.  Resize the new view to take up all the space below the navigation bar. With 
    the new view selected, open the *Add New Constraints* menu at the 
    bottom-right of the canvas. In this menu, set *Spacing to nearest neighbor* 
    to 0 on all sides, and click the *Add 4 Constraints* button. 

    ![Figure 2: Set the new view's *Spacing to nearest neighbor* constraints to 0 on all sides.](../../../images/ios-lp-gb-constraints.png)

3.  With the new view still selected, open the Identity inspector and set the 
    view's custom class to `GuestbookListScreenlet`. The view now appears as 
    Guestbook List Screenlet in the outline view. 

Fantastic! The guestbooks scene now contains Guestbook List Screenlet. Next, 
you'll conform the scene's view controller class to the Screenlet's delegate. 

## Conforming to the Screenlet's Delegate Protocol [](id=conforming-to-the-screenlets-delegate-protocol)

Recall that a view controller can respond to a Screenlet's events by conforming 
to the Screenlet's delegate protocol. To respond to Guestbook List Screenlet's 
events, `GuestbooksViewController` (the guestbooks scene's view controller 
class) must conform to the `GuestbookListScreenletDelegate` protocol. You 
created this delegate when creating the Screenlet. This delegate defines methods 
for responding to the success or failure to retrieve guestbooks, and the 
selection of a guestbook in the list. 

Follow these steps to conform `GuestbooksViewController` to the 
`GuestbookListScreenletDelegate` protocol: 

1.  Import `LiferayScreens`, and in the class declaration set 
    `GuestbooksViewController` to adopt the `GuestbookListScreenletDelegate` 
    protocol. The first few lines of the class should look like this: 

        import UIKit
        import LiferayScreens

        class GuestbooksViewController: UIViewController, GuestbookListScreenletDelegate {...

2.  Implement the `GuestbookListScreenletDelegate` method 
    `screenlet(_:onGuestbookListResponse:)`. Recall that this method lets you 
    respond to a successful server call. Its arguments include the 
    `GuestbookModel` objects that result from such a call. Since the Screenlet 
    already displays these objects, you don't need to do anything in this 
    method: 

        func screenlet(screenlet: GuestbookListScreenlet, 
            onGuestbookListResponse guestbooks: [GuestbookModel]) {

        }

3.  Implement the `GuestbookListScreenletDelegate` method 
    `screenlet(_:onGuestbookListError:)`. Recall that this method lets you 
    respond to a failed server call. Its arguments include the `NSError` object 
    that results from such a call. You don't have to do anything in this method, 
    but it's a good idea to print the error: 

        func screenlet(screenlet: GuestbookListScreenlet, onGuestbookListError error: NSError) {
            print("Failed to retrieve guestbooks: \(error.localizedDescription)")
        }

4.  Implement the `GuestbookListScreenletDelegate` method 
    `screenlet(_:onGuestbookSelected:)`. Recall that this method lets you 
    respond when a user selects a guestbook in the list. It does so by including 
    the selected `GuestbookModel` object in its arguments. When a user selects a 
    guestbook, the app should transition to the entries scene and display that 
    guestbook's entries with Entry List Screenlet. To do this, you must trigger 
    the segue to the entries scene by using the method 
    `performSegue(withIdentifier:sender:)` with the segue's ID and the selected 
    `GuestbookModel`. Recall that you assigned the segue's ID, `entriessegue`, 
    when you created the segue. Including the selected `GuestbookModel` lets 
    Entry List Screenlet know which guestbook to display entries from: 

        func screenlet(screenlet: GuestbookListScreenlet, 
            onGuestbookSelected guestbook: GuestbookModel) {

            performSegue(withIdentifier: "entriessegue", sender: guestbook)
        }

5.  Next, you must set the segue's destination view controller to an 
    `EntriesViewController` instance, and set that instance's 
    `selectedGuestbook` variable to the selected guestbook. This ensures that 
    Entry List Screenlet receives the guestbook you sent in 
    `performSegue(withIdentifier:sender:)`. 

    You do this by overriding the `prepare(for:sender:)` method. This method is 
    called by `performSegue(withIdentifier:sender:)` before the segue occurs. 
    The `prepare(for:sender:)` method's `sender` parameter receives the 
    guestbook sent by `performSegue(withIdentifier:sender:)`. Currently, 
    `prepare(for:sender:)` is commented out at the bottom of 
    `GuestbooksViewController`. Xcode created this method for you when you 
    used the Cocoa Touch Class template to create a view controller class. 
    Uncomment the method, and replace it with this: 

        override func prepare(for segue: UIStoryboardSegue, sender: Any?) {

            guard segue.identifier ==  "entriessegue",
                let entriesViewController = segue.destination as? EntriesViewController,
                let selectedGuestbook = sender as? GuestbookModel
                else {return}

            entriesViewController.selectedGuestbook = selectedGuestbook
        }

    The `guard` statement ensures that the code only runs when the segue ID is 
    `entriessegue`, the destination view controller is `EntriesViewController`, 
    and the `sender` is a `GuestbookModel`. In other words, the code only runs 
    in preparation for the segue to the entries scene. The code that runs when 
    that condition is met is only one line long: 

        entriesViewController.selectedGuestbook = selectedGuestbook

    This sets the guestbook received by `prepare(for:sender:)` to the guestbook 
    in `EntriesViewController`. As the variable names indicate, this is the 
    guestbook the user selects in Guestbook List Screenlet. 

6.  Now you must get a Guestbook List Screenlet reference. You'll do this by 
    creating an outlet to the Screenlet. Return to your storyboard and enter the 
    Assistant editor to display `GuestbooksViewController`'s code and the 
    storyboard side by side. With Guestbook List Screenlet selected in the 
    storyboard, Control-drag from the Screenlet to the 
    `GuestbooksViewController` class, just below the class declaration. Release 
    your mouse button, enter the following information in the dialog that 
    appears, and click *Connect*: 

    - **Connection:** Outlet
    - **Name:** screenlet
    - **Type:** GuestbookListScreenlet
    - **Storage:** Weak

    Xcode then adds the following code inside the `GuestbooksViewController` 
    class: 

        @IBOutlet weak var screenlet: GuestbookListScreenlet!

7.  Use this new `screenlet` variable to set the view controller as the 
    Screenlet's delegate. Do this in the `viewDidLoad()` method by deleting the 
    placeholder comment and inserting this code below the call to 
    `super.viewDidLoad()`:

        self.screenlet.delegate = self

Great! The guestbooks scene now contains Guestbook List Screenlet. Next, you'll 
use Entry List Screenlet in the entries scene. 
