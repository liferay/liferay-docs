# Android Best Practices [](id=android-best-practices)

When developing Android projects with Liferay Screens, there are a few best 
practices that you should follow to ensure your code is as clean and bug-free as 
possible. This tutorial lists these. 

## Update Your Tools [](id=update-your-tools)

You should first make sure that you have the latest tools installed. 
You should use the latest Android API level with the latest version of Android 
Studio. Although Screens *may* work with Eclipse ADT or manual Gradle builds, 
Android Studio is the preferred IDE. 

## See the Breaking Changes Document [](id=see-the-breaking-changes-document)

When updating an app or Screenlet to a new version of Liferay Screens, make sure 
to see the 
[Android breaking changes reference article](/develop/reference/-/knowledge_base/7-0/android-breaking-changes). 
This article lists changes to Screens that break functionality in prior 
versions. In most cases, updating your code is relatively straightforward. 

## Naming Conventions [](id=naming-conventions)

Using the naming conventions described here leads to consistency and a better 
understanding of the Screens library. This makes working with your Screenlets 
much simpler. 

Also note that Liferay Screens follows 
[Square's Java conventions](https://github.com/square/java-code-styles) 
for Android, with tabs as separator. The configuration for IDEA, findbugs, PMD, 
and checkstyle is available in the project's source code. 

### Screenlet Folder [](id=screenlet-folder)

Your Screenlet folder's name should indicate your Screenlet's functionality. For 
example, 
[Login Screenlet's folder is named `login`](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/java/com/liferay/mobile/screens/auth/login). 

If you have multiple Screenlets that operate on the same entity, you can place 
them inside a folder named for that entity. For example, 
[Asset Display Screenlet](/develop/reference/-/knowledge_base/7-0/asset-display-screenlet-for-android) 
and 
[Asset List Screenlet](/develop/reference/-/knowledge_base/7-0/assetlistscreenlet-for-android) 
both work with Liferay assets. They're therefore in the Screens library's 
[`asset` folder](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/java/com/liferay/mobile/screens/asset). 

### Screenlets [](id=screenlets)

Naming Screenlets properly is very important; they're the main focus of Liferay 
Screens. You should name your Screenlet with its principal action first, 
followed by *Screenlet*. Its Screenlet class should also follow this pattern. 
For example, 
[Login Screenlet's](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-android) 
principal action is to log users into a @product@ installation. This Screenlet's 
Screenlet class is therefore `LoginScreenlet`. 

### View Models [](id=view-models)

Name your View models the same way you name Screenlets, but substitute 
`ViewModel` for `Screenlet`. Also, place your View Models in a `view` folder in 
your Screenlet's root folder. For example, Login Screenlet's View Model is named 
`LoginViewModel` and is in the 
[`login/view` folder](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/java/com/liferay/mobile/screens/auth/login/view). 

### Interactors [](id=interactors)

Place your Screenlet's Interactors in a folder named `interactor` in your 
Screenlet's root folder. Name each Interactor first with the object it operates 
on, followed by its action and the suffix *Interactor*. If you wish, you can 
also put each Interactor in its own folder named after its action. For example, 
[Rating Screenlet](/develop/reference/-/knowledge_base/7-0/rating-screenlet-for-android) 
has three Interactors. Each is in its own folder inside 
[the `interactor` folder](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/java/com/liferay/mobile/screens/rating/interactor): 

- `delete/RatingDeleteInteractor`: Deletes an asset's ratings
- `load/RatingLoadInteractor`: Loads an asset's ratings
- `update/RatingUpdateInteractor`: Updates an asset's ratings

### Views [](id=views)

Place Views in a `view` folder in the Screenlet's root folder. If you're 
creating a View Set, however, you can place its Views in a separate `viewsets`
folder outside your Screenlets' root folders. This is what the Screens Library
does for 
[its Material and Westeros View Sets](https://github.com/liferay/liferay-screens/tree/master/android/viewsets). 
The `material` and `westeros` folders contain those View Sets, respectively. 
Also note that in each View, each Screenlet's View class is in its own folder. 
For example, the View class for 
[Forgot Password Screenlet's](/develop/reference/-/knowledge_base/7-0/forgotpasswordscreenlet-for-android) 
Material View is in 
[the folder `viewsets/material/src/main/java/com/liferay/mobile/screens/viewsets/material/auth/forgotpassword`](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material/src/main/java/com/liferay/mobile/screens/viewsets/material/auth/forgotpassword). 
Note that the `auth` folder in this path is the Screenlet's module. Creating 
your Screenlets and Views in modules isn't required. Also note that the 
[View's layout file `forgotpassword_material.xml`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/material/src/main/res/layout/forgotpassword_material.xml) 
is in `viewsets/material/src/main/res/layout`. 

Name a View's layout XML and View class after your Screenlet, substituting 
*View* for *Screenlet* where necessary. The layout's filename should also be 
suffixed with `_yourViewName`. For example, the XIB file and View class for 
Forgot Password Screenlet's Material View are `forgotpassword_material.xml` and 
`ForgotPasswordView.java`, respectively. 

## Avoid Hard Coded Elements [](id=avoid-hard-coded-elements)

Using constants instead of hard-coded elements is a simple way to avoid bugs. 
Constants reduce the likelihood that you'll make a typo when referring to common 
elements. They also gather these elements in a single location. For example, 
[DDL Form Screenlet's Screenlet class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/ddl/form/DDLFormScreenlet.java) 
defines the following constants for the user action names: 

    public static final String LOAD_FORM_ACTION = "loadForm";
    public static final String LOAD_RECORD_ACTION = "loadRecord";
    public static final String ADD_RECORD_ACTION = "addRecord";
    public static final String UPDATE_RECORD_ACTION = "updateRecord";
    public static final String UPLOAD_DOCUMENT_ACTION = "uploadDocument";

## Avoid State in Interactors [](id=avoid-state-in-interactors)

Liferay Screens uses 
[EventBus](http://greenrobot.org/eventbus/) 
to ensure that the network or background operation isn't lost when the device 
changes orientation. For this to work, however, you must ensure that your 
Interactor's request is stateless. 

If an Interactor needs some piece of information, you should pass it to the 
Interactor via the `start` call and then attach it to the event. You can see an 
example of this in the sample Add Bookmark Screenlet from 
[the Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets). 
The `onUserAction` method in the Screenlet class (`AddBookmarkScreenlet`) passes 
a Bookmark's URL and title from the View Model to the Interactor via the 
Interactor's `start` method: 

    @Override
    protected void onUserAction(String userActionName, AddBookmarkInteractor interactor, 
        Object... args) {
            AddBookmarkViewModel viewModel = getViewModel();
            String url = viewModel.getURL();
            String title = viewModel.getTitle();

            interactor.start(url, title, folderId);
    }

The `start` method calls the Interactor's `execute` method in a background 
thread. The `execute` method in Add Bookmark Screenlet's Interactor 
(`AddBookmarkInteractor`) creates a `BasicEvent` object that contains the 
`start` method’s arguments: 

    @Override
    public BasicEvent execute(Object[] args) throws Exception {
        String url = (String) args[0];
        String title = (String) args[1];
        long folderId = (long) args[2];

        validate(url, folderId);

        JSONObject jsonObject = getJSONObject(url, title, folderId);
        return new BasicEvent(jsonObject);
    }

## Stay in Your Layer [](id=stay-in-your-layer)

When accessing variables that belong to other Screenlet components, you should 
avoid those outside your current Screenlet layer. This achieves better 
decoupling between the layers, which tends to reduce bugs and simplify 
maintenance. For an explanation of the layers in Liferay Screens, see 
[the architecture tutorial](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android). 
For example, don't directly access View variables from an Interactor. Instead, 
pass data from a View Model to the Interactor via the Interactor's `start` 
method. The example `onUserAction` method in the previous section illustrates 
this. 

## Related Topics [](id=related-topics)

[Liferay Screens for Android Troubleshooting and FAQs](/develop/tutorials/-/knowledge_base/7-0/liferay-screens-for-android-troubleshooting-and-faqs)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets)

[Android Breaking Changes](/develop/reference/-/knowledge_base/7-0/android-breaking-changes)
