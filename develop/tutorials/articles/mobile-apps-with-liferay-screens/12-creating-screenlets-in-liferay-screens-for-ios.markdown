# Creating Screenlets in Liferay Screens for iOS [](id=creating-screenlets-in-liferay-screens-for-ios)

Liferay Screens' built-in screenlets cover many common use cases in mobile 
apps that connect to Liferay. For example, you can use them to authenticate 
users, interact with Dynamic Data Lists, and view assets. However, what if 
there's not a screenlet for *your* specific use case? No sweat! You can write 
your own screenlet. Screenlets can also be written by others and contributed to 
the Screens project. This extensibility is one of Screens' key strengths. 

In this tutorial, you'll create an example bookmarks screenlet for iOS that has 
the following features: 

- Allows entry of a URL in a text box. 
- Checks if the URL is valid and extracts its title value. 
- Shows a preview image and title for user confirmation. 
- Lets the user modify the title. 
- Upon user confirmation, the URL and title is sent back to the Liferay 
  instance's Bookmark services to be saved. 

Before proceeding, you may want to read the tutorial 
[Architecture of Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios) 
to understand in detail the concepts underlying screenlets. You may also want to 
read the tutorial [Creating Themes in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-themes-in-liferay-screens-for-ios) 
to support your new screenlet from the theme that you want to use. Without any 
further ado, let the screenlet creation begin! 

## Creating the Screenlet [](id=creating-the-screenlet)

Use the following steps to create the screenlet: 

1. Create a new `xib` file for your screenlet. In this example the file is named 
   `BookmarkView_default.xib`. You'll build your UI here using Interface 
   Builder. Since the bookmarkds screenlet needs text box fields for the 
   bookmark's URL and title, put in two text box fields (`UITextField`). Also, 
   add two buttons to let the user retrieve the title and save the bookmark. To 
   differentiate between these two user actions, assign a value for the 
   `restorationIdentifier` property in each button. 

    ![Figure 1: The new `xib` file for the new screenlet.](../../images/screens-ios-xcode-add-bookmark.png)

2. Create a new interface (protocol) for the screenlet, containing any needed 
   attributes. Here, this is called `BookmarkViewModel` and the associated 
   attributes are `url` and `title`.

    ![Figure 2: The new view model for the new screenlet.](../../images/screens-ios-xcode-bookmark-viewmodel.png)

3. Create a new class that extends `BaseScreenletView` and conforms the protocol 
   you created in the previous step. This new class must wire all UI components 
   and events from the `xib` using the standard `@IBOutlet` and `@IBAction`. In 
   this example, this class is called `BookmarkView_default` and conforms 
   `BookmarkViewModel`. Getters and setters from `BookmarkViewModel` should 
   respectively get and set the data from the UI components. Also be sure to 
   write any animations or front end code here. 

4. Set the class you created in the previous step as your `xib` file's custom 
   class. In this example, `BookmarkView_default` is set as the 
   `BookmarkView_default.xib` file's custom class. 

5. Create a screenlet class that extends `BaseScreenlet`. Here, this is called 
   `BookmarkScreenlet`. 

6. Optionally, you can add any `@IBInspectable` properties to configure the 
   screenlet's behavior. For example, you could use a boolean property to 
   configure whether the user can save broken URLs. 

7. Override the `onUserAction` method so that it receives user actions. In this 
   example, it needs to receive both button actions. Use the `name` parameter to 
   differentiate between the following actions. These strings should be set in 
   the trigger component's `Restoration ID` attribute (for example, `UIButton` 
   or others): 

    - `"check"`: Occurs when the "Add bookmark" button is touched. 
    - `"save"`: Occurs when the "Confirm" button is touched. 

    ![Figure 3: The restoration ID for trigger components.](../../images/screens-ios-xcode-restoration-id.png)

8. Write two operation classes that extend `ServerOperation`. Override the 
   `doRun` method to perform the operation. Also override the `validateData` 
   method to check if the data stored in `BookmarkViewModel` is valid. These two 
   operation classes are described here: 

    - `GetSiteTitleOperation`: Retrieves content from start of the HTML to the 
      `<title>` tag. This results in the title's extraction from the HTML.
    - `LiferaySaveBookmarkOperation`: Sends the URL and title to the Liferay 
      instance's Bookmark services.

9. In the screenlet's `onUserAction` method, create and start these operations:

    - When the `name` argument is `"check"`: Create and start 
      `GetSiteTitleOperation`. The specified closure should get the retrieved 
      title and set it to the associated `BookmarkViewModel` using the 
      `screenletView`. If the operation fails, show the error to the user.
    - When the `name` argument is `"save"`: Get the URL and title from 
      `BookmarkViewModel` and create a `LiferaySaveBookmarkOperation` object 
      with these values. Start the operation and set the closure to show the 
      success or failure to the user.

10. You're done! Now you can add your new screenlet to your storyboard as usual 
    and use it as a ready-to-use component.

Great! By creating this example bookmarks screenlet, you now know how to create 
screenlets in Screens for iOS. 

## Related Topics [](id=related-topics)

[Using Screenlets in Your iOS Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-ios-project)

[Architecture of Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)

[Creating Themes in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-themes-in-liferay-screens-for-ios)

[Liferay Screens Overview](/tutorials/-/knowledge_base/6-2/liferay-screens-overview)
