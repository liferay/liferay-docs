# Creating the Guestbooks Scene [](id=creating-the-guestbooks-scene)

In the previous article, you used Login Screenlet to implement authentication in 
the login scene. Now you must create the scene you want the user to see 
following login: the guestbooks scene. Later in this Learning Path, you'll 
display guestbooks in this scene via Guestbook List Screenlet. For now, though, 
all you need to do is create the scene and take the user to it after login. This 
article shows you how to do this with the following steps: 

1.  Add a view controller to your storyboard. You'll then embed the view 
    controller in a navigation controller. The navigation controller gives the 
    scene a navigation bar and automatically implements back-navigation in the 
    entries scene you'll create later. 

2.  Create the guestbook scene's view controller class. This class controls the 
    view controller's behavior. 

3.  Create a segue from the login scene to the guestbooks scene. Upon login, 
    this segue takes the user to the guestbooks scene. 

First, you'll add a view controller to the storyboard. 

## Adding a View Controller to the Storyboard [](id=adding-a-view-controller-to-the-storyboard)

Follow these steps to add a view controller to the storyboard:

1.  Open your storyboard and drag and drop a *View Controller* from the Object 
    Library to the right of the login scene. 

    ![Figure 1: The arrow shows where to drag and drop the View Controller to create the new guestbooks scene.](../../../images/ios-lp-add-gb-vc.png)

2.  Now you must embed this view controller in a 
    [navigation controller](https://developer.apple.com/library/content/documentation/WindowsViews/Conceptual/ViewControllerCatalog/Chapters/NavigationControllers.html). 
    Navigation controllers in iOS implement a *navigation stack*. You can 
    loosely think of a navigation stack as a deck of cards where each card is a 
    view controller with a navigation bar. The navigation bar contains a back 
    button that lets you navigate to the previous view controller in the stack. 
    You can also change the navigation bar's title to reflect the scene's 
    content. 

    With the new view controller selected in the storyboard, select *Editor* 
    &rarr; *Embed In* &rarr; *Navigation Controller*. Your storyboard now shows 
    the navigation controller with a segue to the guestbooks scene. Also, the 
    guestbooks scene now contains an empty navigation bar. 

3.  Select the Navigation Item in the guestbooks scene's navigation bar. In the 
    Attributes inspector, enter *Guestbooks* in the *Title* field, then enter a 
    single space in the *Back Button* field and press return. This labels the 
    scene via the navigation bar, and ensures that the back button in the 
    Navigation controller has no label. The back button's default left chevron 
    indicates the button's purpose without the need for additional text. 

    ![Figure 2: Label the scene in the navigation bar, and set the back button's label to an empty space.](../../../images/ios-lp-back-button-label.png)

5.  With the guestbooks scene's view controller selected in the storyboard, open 
    the Attributes inspector and uncheck *Adjust Scroll View Insets*. This 
    ensures that the scene's contents are flush with the navigation bar. 

    ![Figure 3: Make sure the view controller's *Adjust Scroll View Insets* option is unchecked in the Attributes inspector.](../../../images/ios-lp-adj-scroll-view-ins.png)

Great! You now have the guestbooks scene, embedded in a navigation controller. 
For this scene's view controller to work, it must have a class that controls its 
behavior. You'll create this class next. 

## Creating the Guestbooks Scene's View Controller Class [](id=creating-the-guestbooks-scenes-view-controller-class)

Each view controller must have a class that controls its behavior. In this 
section, you'll create this class for the guestbooks scene's view controller. In 
the storyboard, you'll then set this class as the view controller's custom 
class. 

1.  Right-click the *Liferay Guestbook* folder in Xcode's project navigator and 
    select *New File*. In the *iOS* &rarr; *Source* section of the dialog that 
    appears, select *Cocoa Touch Class* and click *Next*. 

2.  The next screen in the dialog lets you set the class's name, subclass, and 
    language. You can also choose whether to create an XIB file for the class. 
    Enter the following information and click *Next*: 

    - **Class:** `GuestbooksViewController`
    - **Subclass of:** `UIViewController`
    - **Also create XIB file:** Unchecked
    - **Language:** Swift

    ![Figure 4: Set these options when creating the view controller's class.](../../../images/ios-lp-gb-vc-class.png)

3.  The final screen in the dialog lets you set the class's location, group, and 
    targets. Make sure *Liferay Guestbook* is selected for both the *Group* and 
    *Targets* menus, and click *Create*. 

4.  In the storyboard, select the guestbooks scene's view controller. In the 
    Identity inspector, set `GuestbooksViewController` as the Custom Class. 

    ![Figure 5: Set `GuestbooksViewController` as the custom class of the guestbooks scene's view controller.](../../../images/ios-lp-gb-vc-cc.png)

Nice! The guestbooks scene's view controller now has a class that governs its 
behavior. You may have noticed a big problem, though. There's no way for the 
user to get from the login scene to the guestbooks scene. This is because 
there's no segue from the login scene to the navigation controller the 
guestbooks scene is embedded in. You'll fix this next. 

## Creating the Segue [](id=creating-the-segue)

Follow these steps to create and trigger the segue: 

1.  Control-drag from the login scene's view controller to the navigation 
    controller. In the dialog that appears when you release your mouse button, 
    select *show* for the segue type. The segue now connects the login scene's 
    view controller and the navigation controller. 

    ![Figure 6: A segue now exists from the login scene to the navigation controller.](../../../images/ios-lp-login-segue.png)

2.  Now you must tell the login scene's view controller when to perform this 
    segue. You'll do this programmatically in the `ViewController` class. To 
    perform a segue programmatically, you must first give it an identifier in 
    your storyboard. You'll then use this identifier in `ViewController` to 
    perform the segue when a user logs in. 

    In your storyboard, select the segue and then enter the Attributes 
    inspector. Enter *loginsegue* in the *Identifier* field, and press 
    *return*. 

    ![Figure 7: Set the segue's ID in the Attributes inspector.](../../../images/ios-lp-login-segue-id.png)

3.  Recall that the `ViewController` class's 
    `screenlet(_:onLoginResponseUserAttributes:)` method is called upon 
    successful login. You'll therefore trigger the segue in this method. 
    Currently, this method only prints a success message. Below the line that 
    prints this message, add the following code: 

        performSegue(withIdentifier: "loginsegue", sender: nil)

    The `performSegue(withIdentifier:sender:)` method performs the segue with 
    the specified identifier and includes any additional sender code. You send 
    `nil` here since you don't need to send any information with the segue. Your 
    `screenlet(_:onLoginResponseUserAttributes:)` method should now look like 
    this: 

        func screenlet(_ screenlet: BaseScreenlet, 
            onLoginResponseUserAttributes attributes: [String:AnyObject]) {
                print("Login Successful!")
                performSegue(withIdentifier: "loginsegue", sender: nil)
        }

Great! Your app can now navigate to the guestbooks scene after login. To verify 
this, run the app and log in. 

![Figure 8: Following successful login, the app now navigates to the empty guestbooks scene.](../../../images/ios-lp-gb-scene-empty.png)

Awesome! You've successfully added a scene for displaying guestbooks, and set 
the app to take the user there after login. Now you're ready to develop 
Guestbook List Screenlet. The next section in this Learning Path walks you 
through this. 
