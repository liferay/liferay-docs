# Creating the Entries Scene [](id=creating-the-entries-scene)

Currently, the login and guestbooks scenes are the only two scenes in your app. 
The login scene contains Login Screenlet, and you'll put Guestbook List 
Screenlet in the guestbooks scene. Before you can use Entry List Screenlet, you 
must create a scene to put it in: the entries scene. 

In this article, you'll use these steps to create the entries scene: 

1.  Add a new view controller to your storyboard, and create a segue to it from 
    the guestbooks scene. 
2.  Create the entries scene's view controller class. 

## Adding a View Controller to the Storyboard [](id=adding-a-view-controller-to-the-storyboard)

Follow these steps to create a view controller for the entries scene: 

1.  Open your storyboard and drag and drop a *View Controller* from the Object 
    Library to the right of the guestbooks scene. 

2.  With the new view controller selected in the storyboard, open the Attributes 
    inspector and uncheck *Adjust Scroll View Insets*. This ensures that the 
    scene's contents are flush with the navigation bar.

3.  Create a segue from the guestbooks scene's view controller to the new view 
    controller. To do this, control-drag from the guestbooks scene's view 
    controller to the new view controller. In the dialog that appears upon 
    releasing your mouse button, select *show* for the segue type. The segue now 
    connects the two view controllers. 

4.  Click the new segue, and then enter the Attributes inspector. Enter 
    *entriessegue* in the *Identifier* field, and press *return*. Later, you'll 
    use this identifier to perform the segue programmatically when a user 
    selects a guestbook in Guestbook List Screenlet. 

![Figure 1: The entries scene now exists to the right of the guestbooks scene, with a segue connecting the two scenes.](../../../images/ios-lp-entries-scene-01.png)

Great! The entries scene now exists, and there's a segue going to it from the 
guestbooks scene. Next, you'll create the entries scene's view controller class. 

## Creating the Entries Scene's View Controller Class [](id=creating-the-entries-scenes-view-controller-class)

Each view controller must have a class that controls its behavior. In this 
section, you'll create this class for the entries scene's view controller. In 
the storyboard, you'll then set this class as the view controller's custom 
class. 

Follow these steps to create the entries scene's view controller class: 

1.  Right-click the `Liferay Guestbook` folder in Xcode's project navigator and 
    select *New File*. In the *iOS* &rarr; *Source* section of the dialog that 
    appears, select *Cocoa Touch Class* and click *Next*. 

2.  The next screen in the dialog lets you set the class's name, subclass, and 
    language. You can also choose whether to create an XIB file for the class. 
    Enter the following information and click *Next*: 

    - **Class:** `EntriesViewController`
    - **Subclass of:** `UIViewController`
    - **Also create XIB file:** Unchecked
    - **Language:** Swift

3.  The final screen in the dialog lets you set the class's location, group, and 
    targets. Make sure *Liferay Guestbook* is selected for both the *Group* and 
    *Targets* menus (it should be by default), and click *Create*. 

4.  `EntriesViewController` needs a `GuestbookModel` variable to hold the 
    guestbook it shows entries from. Add this variable to the top of the class: 

        var selectedGuestbook: GuestbookModel?

    As its name implies, this variable holds the guestbook the user selects in 
    Guestbook List Screenlet. 

5.  In the storyboard, select the entries scene's view controller. In the 
    Identity inspector, set `EntriesViewController` as the custom class. 

Nice work! The entries scene's view controller now has a class that governs its 
behavior. Now you're ready to put your Screenlets to use. 
