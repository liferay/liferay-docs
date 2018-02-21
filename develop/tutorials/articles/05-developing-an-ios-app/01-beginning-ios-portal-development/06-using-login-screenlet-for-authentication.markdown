# Using Login Screenlet for Authentication [](id=using-login-screenlet-for-authentication-ios)

For the app to retrieve data from the Guestbook portlet, the user must first
authenticate to the @product@ instance. You can implement authentication
using the Liferay Mobile SDK, but it takes time to write. Using Liferay Screens
to authenticate takes about ten minutes. In this article, you'll use Login
Screenlet to implement authentication in your app. 

## Adding Login Screenlet to the App [](id=adding-login-screenlet-to-the-app)

To use any Screenlet, you must follow two steps: 

1.  Insert the Screenlet in the storyboard scene where you want it to appear. 
    You do this by adding an empty view to the scene, and then setting the 
    Screenlet class as the view's custom class. 

2.  Conform the scene's view controller's class to the Screenlet's delegate 
    protocol. This lets the view controller respond to the Screenlet's events. 

In this app, you'll use Login Screenlet in the app's first (and at this time, 
only) scene. After adding the Screenlet to this scene, you'll conform 
`ViewController` (the scene's view controller class) to the 
`LoginScreenletDelegate` protocol. 

## Adding Login Screenlet to the Scene [](id=adding-login-screenlet-to-the-scene)

Follow these steps to add Login Screenlet to the scene:

1.  In `Main.storyboard`, first select the scene's view controller. Then drag 
    and drop a plain view (`UIView`) from the Object Library onto the view 
    controller. In the outline view, this new view should be nested under the 
    view controller's existing view. 

    ![Figure 1: The new view is nested under the view controller's existing view.](../../../images/ios-lp-new-view-outline.png)

2.  With the new view selected, open the Identity inspector and set the view's 
    Custom Class as `LoginScreenlet`. Xcode now builds the project and renders 
    Login Screenlet's preview in the view. Also note that the view now appears 
    as Login Screenlet in the outline view. 

    ![Figure 2: You must set the view's Custom Class to `LoginScreenlet`.](../../../images/ios-lp-custom-class.png)

3.  Now you'll set the constraints to center Login Screenlet in the scene. 
    Although this isn't required (you can technically position Login Screenlet 
    anywhere you want), centering an authentication UI is common in mobile apps. 
    Center Login Screenlet in the scene, and click the *Align* menu at the 
    bottom-right of the canvas. In this menu, check the checkboxes for 
    *Horizontally in Container* and *Vertically in Container*, and click the 
    *Add 2 Constraints* button (don't worry about the Auto Layout errors that 
    appear--you'll resolve these in the next step). 

    ![Figure 3: These alignment constraints center Login Screenlet in the scene.](../../../images/ios-lp-alignment-constraints.png)

4.  By default, Login Screenlet stretches or compresses to fill the view. It's 
    compressed at the moment because of the alignment constraints. To avoid any 
    ill-effects caused by automatic resizing, you'll set the Screenlet to a 
    fixed size. With the view selected, open the *Add New Constraints* menu at 
    the bottom-right of the canvas. In this menu, set the *Width* to 270 and the 
    *Height* to 185, and click the *Add 2 Constraints* button. The Screenlet 
    looks better now and the Auto Layout errors are gone. Note that you don't 
    have to use these exact width and height values when using Login Screenlet. 
    You can size the Screenlet however you wish. 

    ![Figure 4: Setting these size constraints ensures that Login Screenlet isn't too stretched out or compressed.](../../../images/ios-lp-size-constraints.png)

    ![Figure 5: With the alignment and size constraints set, Login Screenlet appears in the center of the scene and its UI components aren't too compressed or stretched out.](../../../images/ios-lp-login-scene.png)

Nicely done! The scene now contains Login Screenlet. Next, you'll conform 
`ViewController` (the scene's view controller class) to the 
`LoginScreenletDelegate` protocol. 

## Conforming to the Screenlet's Delegate Protocol [](id=conforming-to-the-screenlets-delegate-protocol)

A view controller can respond to a Screenlet's events by conforming to the 
Screenlet's delegate protocol. This lets the app developer choose how their app 
behaves with the Screenlet. To respond to Login Screenlet's events, 
`ViewController` must conform to the `LoginScreenletDelegate` protocol. The 
Login Screenlet events that need a response are login success and failure. The 
delegate defines methods for both. 

Follow these steps to conform `ViewController` to the `LoginScreenletDelegate` 
protocol: 

1.  Import `LiferayScreens` and set `ViewController` to adopt the 
    `LoginScreenletDelegate` protocol. The first few lines of the class should 
    look like this: 

        import UIKit
        import LiferayScreens

        class ViewController: UIViewController, LoginScreenletDelegate {...

2.  Implement the `LoginScreenletDelegate` method 
    `screenlet(_:onLoginResponseUserAttributes:)`. This method is called when 
    authentication with Login Screenlet succeeds. Right now, you don't need to 
    do anything in this method besides indicate that login succeeded: 

        func screenlet(_ screenlet: BaseScreenlet, 
            onLoginResponseUserAttributes attributes: [String:AnyObject]) {
                print("Login Successful!")
        }

3.  Implement the `LoginScreenletDelegate` method `screenlet(_:onLoginError:)`. 
    This method is called when authentication with Login Screenlet fails. All 
    you need to do in this method is print a message indicating that login 
    failed:

        func screenlet(_ screenlet: BaseScreenlet, onLoginError error: NSError) {
            print("Login Failed!")
        }

4.  Now you must get a Login Screenlet reference in `ViewController`. You'll do 
    this by creating an outlet to the Screenlet. Return to your storyboard and 
    enter the Assistant editor to display `ViewController`'s code and the 
    storyboard side by side. With Login Screenlet selected in the storyboard, 
    Control-drag from the Screenlet to the `ViewController` class to create the 
    outlet. In the dialog that appears upon releasing your mouse button, enter 
    the following information and click *Connect*: 

    - **Connection:** Outlet
    - **Name:** loginScreenlet
    - **Type:** LoginScreenlet
    - **Storage:** Weak

    Xcode then adds the following code inside the `ViewController` class: 

        @IBOutlet weak var loginScreenlet: LoginScreenlet!

    ![Figure 6: Create an outlet from Login Screenlet to the `ViewController` class.](../../../images/ios-lp-login-screenlet-outlet.png)

5.  In the `ViewController` class, use the new `loginScreenlet` variable to set 
    the view controller as the Screenlet's delegate. Do this in the 
    `viewDidLoad()` method by deleting the placeholder comment and inserting 
    this code below the call to `super.viewDidLoad()`:

        self.loginScreenlet?.delegate = self

Great, you're finished! Before running the app, make sure that your 
`ViewController` class looks like this: 

    import UIKit
    import LiferayScreens

    class ViewController: UIViewController, LoginScreenletDelegate {

        @IBOutlet weak var loginScreenlet: LoginScreenlet!

        override func viewDidLoad() {
            super.viewDidLoad()
            self.loginScreenlet?.delegate = self
        }

        override func didReceiveMemoryWarning() {
            super.didReceiveMemoryWarning()
        }

        func screenlet(_ screenlet: BaseScreenlet, onLoginResponseUserAttributes attributes: [String:AnyObject]) {
            print("Login Successful!")
        }

        func screenlet(_ screenlet: BaseScreenlet, onLoginError error: NSError) {
            print("Login Failed!")
        }

    }

Now you're ready to test your work. With your @product@ instance running, launch 
the app using the iPhone simulator (any iPhone version supported by Xcode will 
work). Enter your credentials when Login Screenlet appears and click *SIGN IN*. 
In Xcode, the `Login Successful!` message appears in the console. 

![Figure 7: It worked!](../../../images/ios-lp-login-successful.png)

Nice job! Nothing else happens, though. Your app still displays Login Screenlet. 
This is expected. There aren't any other scenes for your app to navigate to. In 
the next article, you'll create the scene for displaying guestbooks: the 
guestbooks scene. 
