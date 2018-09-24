# Using and Creating Progress Presenters [](id=using-and-creating-progress-presenters)

Many apps display a progress indicator while performing an operation. For 
example, you've likely seen the spinners in iOS apps that let you know the app 
is performing some kind of work. For more information, see 
[the iOS Human Interface Guidelines article on Progress Indicators](https://developer.apple.com/ios/human-interface-guidelines/ui-controls/progress-indicators/). 

You can display these in Screenlets by using classes that conform the 
[`ProgressPresenter` protocol](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/ProgressPresenter.swift). 
Liferay Screens includes two such classes: 

- [`MBProgressHUDPresenter`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/MBProgressHUDPresenter.swift): 
  Shows a message with a spinner in the middle of the screen. Liferay Screens 
  shows this presenter by default. 

- [`NetworkActivityIndicatorPresenter`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/NetworkActivityIndicatorPresenter.swift): 
  Shows the progress using the iOS network activity indicator. This presenter 
  doesn't support messages. 

This tutorial shows you how to use and create progress presenters, using code 
from the 
[advanced version of the sample Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced) 
as an example. First, you'll learn how to use progress presenters. 

## Using Progress Presenters [](id=using-progress-presenters)

[The `BaseScreenletView` class](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/BaseScreenletView.swift) 
contains the default progress presenter functionality. To show a presenter other 
than the default `MBProgressHUDPresenter`, your View class must therefore 
override certain `BaseScreenletView` functionality. Follow these steps to do 
this: 

1. In your View class, override the `BaseScreenletView` method 
   `createProgressPresenter` to return an instance of the desired presenter. For 
   example, to use `NetworkActivityIndicatorPresenter` in the sample Add 
   Bookmark Screenlet, you must override the `createProgressPresenter` method in 
   `AddBookmarkView_default` to return a `NetworkActivityIndicatorPresenter` 
   instance:

        override func createProgressPresenter() -> ProgressPresenter {
            return NetworkActivityIndicatorPresenter()
        }

2. In your View class, override the `BaseScreenletView` property
   `progressMessages` to return the messages you want to use in the presenter. 
   If the presenter doesn't display messages, then return an empty string. The 
   `progressMessages` property should return the messages as 
   `[String : ProgressMessages]`, where `String` is the Screenlet's action name. 
   [`ProgressMessages` is a type alias](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/ProgressPresenter.swift) 
   representing a dictionary where the progress type is the key, and the actual 
   message is the value. The three possible progress types correspond to the 
   Screenlet action's status: `Working`, `Failure`, or `Success`. The 
   `progressMessages` property therefore lets the presenter display the 
   appropriate message for the Screenlet action's current status. 

    For example, the following code overrides the `progressMessages` property in 
    Add Bookmark Screenlet's View class (`AddBookmarkView_default`). For each 
    Screenlet action (`AddBookmarkAction` and `GetTitleAction`), a message 
    (`NoProgressMessage`) is assigned to the Screenlet operation's `Working` 
    status. Since `NoProgressMessage` is an alias for an empty string, this 
    tells the presenter to display no message when the Screenlet attempts to add 
    a bookmark or get a title. Note, however, that the presenter still displays 
    its progress indicator: 

        override var progressMessages: [String : ProgressMessages] {
            return [
                AddBookmarkScreenlet.AddBookmarkAction : [.Working: NoProgressMessage],
                AddBookmarkScreenlet.GetTitleAction : [.Working: NoProgressMessage],
            ]
        }

    To display a message, replace `NoProgressMessage` with your message. For 
    example, the following code defines separate messages for `Working`, 
    `Success`, and `Failure`: 

        override var progressMessages: [String : ProgressMessages] {
            return [
                AddBookmarkScreenlet.AddBookmarkAction : [
                    .Working: "Saving bookmark...",
                    .Success: "Bookmark saved!",
                    .Failure: "An error occurred saving the bookmark"
                ],
                AddBookmarkScreenlet.GetTitleAction : [
                    .Working: "Getting site title...",
                    .Failure: "An error occurred retrieving the title"
                ],
            ]
        }

Great! Now you know how to use progress presenters. Next, you'll learn how to 
create your own. 

## Creating Progress Presenters [](id=creating-progress-presenters)

Creating your own progress presenter isn't as complicated as you might think. 
Recall that a presenter in Liferay Screens is a class that conforms the 
[`ProgressPresenter` protocol](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Base/ProgressPresenter.swift). 
You can create your presenter by conforming this protocol from scratch, or by 
extending one of Screens's existing presenters that already conform this 
protocol (`MBProgressHUDPresenter` or `NetworkActivityIndicatorPresenter`). In 
most cases, extending `MBProgressHUDPresenter` is sufficient. 

For example, 
[Add Bookmark Screenlet's `AddBookmarkProgressPresenter`](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Bookmark/AddBookmarkScreenlet/Advanced/ProgressPresenter/AddBookmarkProgressPresenter.swift) 
extends `MBProgressHUDPresenter` to display a different progress indicator for 
the Screenlet's get title action. Use the following steps to create a progress 
presenter that extends from an existing presenter. As an example, these steps 
extend `MBProgressHUDPresenter` to add a progress indicator for the get title 
button: 

1. In your View's XIB file, add the activity indicator you want to use. For 
   example, the XIB file in Add Bookmark Screenlet contains an iOS 
   `UIActivityIndicatorView` over the get title button: 

    ![Figure 1: The updated Add Bookmark Screenlet's XIB file contains a new activity indicator over the get title button.](../../../images/screens-ios-xcode-add-bookmark-advanced-progress.png)

2. In your View class, create an outlet for the XIB's new activity indicator. 
   For example, Add Bookmark Screenlet's View class (`AddBookmarkView_default`) 
   contains an `@IBOutlet` for the `UIActivityIndicatorView` from the XIB:

        @IBOutlet weak var activityIndicatorView: UIActivityIndicatorView?

Now you must create your presenter class. You'll do this here by extending an 
existing presenter class. Use the following steps to do this: 

1. Extend the existing presenter class you want to base your presenter on. Your 
   presenter class must contain properties for your presenter's activity 
   indicator and any other UI components. It must also contain an initializer 
   that sets these properties. For example, `AddBookmarkProgressPresenter` 
   extends `MBProgressHUDPresenter` and contains properties for the get title 
   button and `UIActivityIndicatorView`. Its initializer sets these properties: 

        public class AddBookmarkProgressPresenter: MBProgressHUDPresenter {

            let button: UIButton?

            let activityIndicator: UIActivityIndicatorView?

            public init(button: UIButton?, activityIndicator: UIActivityIndicatorView?) {
                self.button = button
                self.activityIndicator = activityIndicator
                super.init()
            }
            ...

2. Implement your presenter's behavior by overriding the appropriate methods 
   from the presenter class that you're extending. For example, 
   `AddBookmarkProgressPresenter` overrides `MBProgressHUDPresenter`'s 
   `showHUDInView` and `hideHUDFromView` methods. The overridden `showHUDInView` 
   method hides the button and starts animating the activity indicator. The 
   overridden `hideHUDFromView` method stops this animation and restores the 
   button:

        public override func showHUDInView(view: UIView, message: String?, 
            forInteractor interactor: Interactor) {
                guard interactor is GetWebTitleInteractor else {
                    return super.showHUDInView(view, message: message, 
                        forInteractor: interactor)
                }

                button?.hidden = true
                activityIndicator?.startAnimating()
        }

        public override func hideHUDFromView(view: UIView?, message: String?, 
            forInteractor interactor: Interactor, withError error: NSError?) {
                guard interactor is GetWebTitleInteractor else {
                    return super.hideHUDFromView(view, message: message, 
                        forInteractor: interactor, withError: error)
                }

                activityIndicator?.stopAnimating()
                button?.hidden = false
            }

        }

Great, that's it! Now you can use your presenter the same way you would any 
other. 

## Related Topics [](id=related-topics)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)

[Creating iOS List Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-list-screenlets)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)
