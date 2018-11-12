# Accessing ThemeDisplay Information [](id=liferay-themedisplay)

The `Liferay` global JavaScript Object exposes several useful methods, objects, 
and properties, each containing a wealth of information, one of which is 
`ThemeDisplay`. If you have experience with Java development in @product@, you 
may be familiar with ThemeDisplay. The JavaScript object exposes the same 
information as the ThemeDisplay Java Class. It gives you access to valuable 
information that you can use in your applications, such as the Portal instance, 
the current user, the user's language, whether the user is signed in or being 
impersonated, the file path to the theme's resources, and much more. 

The `Liferay` global object is automatically available in @product@ at runtime. 
To access the `ThemeDisplay` object, simply use the following dot notation in 
your app:

        `Liferay.ThemeDisplay.method-name`

This tutorial describes some of the most commonly used `ThemeDisplay` methods 
for retrieving IDs, file paths, and login information. An exhaustive list of all 
of the available methods is displayed in the table at the end of this tutorial. 

## Retrieving IDs [](id=retrieving-ids)

The methods below retrieve various Portal elements related to the current user:

**getCompanyId:** Returns the
[company ID](/participate/liferaypedia/-/wiki/Main/Company+ID).

**getLanguageId:** Returns the user's language ID.

**getScopeGroupId:** Returns the
[group ID](/participate/liferaypedia/-/wiki/Main/Group+ID) of the current site.

**getUserId:** Returns the
[user's ID](/participate/liferaypedia/-/wiki/Main/User+ID).

**getUserName:** Returns the user's name.

Next you can learn how to access file paths for various deployed entities.

## Retrieving File Paths [](id=retrieving-file-paths)

The methods below retrieve file paths for various theme resources:

**getPathImage:** Returns the relative path of the portlet's image directory.

**getPathJavaScript:** Returns the relative path of the directory containing the 
portlet's JavaScript source files.

**getPathMain:** Returns the path of the portal instance's main directory.

**getPathThemeImages:** Returns the path of the current theme's image directory.

**getPathThemeRoot:** Returns the relative path of the current theme's root 
directory.

Next you can learn how to retrieve information for the current user. 

## Retrieving Login Information [](id=retrieving-login-information)

The methods below return a boolean value indicating whether the current user is 
signed in or being impersonated:

**isImpersonated:** Returns `true` if the current user is being impersonated. 
Authorized administrative users can
[impersonate](/discover/portal/-/knowledge_base/6-2/the-users-section-of-the-control-panel#user-management) 
act as another user to test that user's account. 

**isSignedIn:** Returns `true` if the user is logged in to the portal. 

The example configuration below alerts the user with a standard message if they 
are a guest or a personal greeting if they are signed in. This is a basic 
example, and perhaps a bit invasive, but it illustrates how you can create 
unique experiences for each user with the `ThemeDisplay` APIs:

    if(Liferay.ThemeDisplay.isSignedIn()){
        alert('Hello ' + Liferay.ThemeDisplay.getUserName() + '. Welcome Back.')
    }
    else {
        alert('Hello Guest.')
    }

## Liferay ThemeDisplay Methods [](id=liferay-themedisplay-methods)

A complete list of the available `Liferay.ThemeDisplay` methods is shown in the 
table below:

| Method | Type | Description |
| --- | --- | --- |
| getLayoutId | number | |
| getLayoutRelativeURL | string | |
| getLayoutURL | string | |
| getParentLayoutId | number | |
| isControlPanel | boolean | |
| isPrivateLayout | boolean | |
| isVirtualLayout | boolean | |
| getBCP47LanguageId | number | |
| getCDNBaseURL | string | |
| getCDNDynamicResourcesHost | string | |
| getCDNHost | string | |
| getCompanyGroupId | number | |
| getCompanyId | number | Returns the [company ID](/participate/liferaypedia/-/wiki/Main/Company+ID) |
| getDefaultLanguageId | number | |
| getDoAsUserIdEncoded | string | |
| getLanguageId | number | Returns the user's language ID |
| getParentGroupId | number | |
| getPathContext | string | |
| getPathImage | string | Returns the relative path of the portlet's image directory |
| getPathJavaScript | string | Returns the relative path of the directory containing the
portlet's JavaScript source files |
| getPathMain | string | Returns the path of the portal instance's main directory |
| getPathThemeImages | string | Returns the path of the current theme's image directory |
| getPathThemeRoot | string | Returns the relative path of the current theme's root
directory |
| getPlid | string | |
| getPortalURL | string | |
| getScopeGroupId | number | Returns the [group ID](/participate/liferaypedia/-/wiki/Main/Group+ID) of the current site |
| getScopeGroupIdOrLiveGroupId | number | |
| getSessionId | number | |
| getSiteGroupId | number | |
| getURLControlPanel | string | |
| getURLHome | string | |
| getUserId | number | Returns the [user's ID](/participate/liferaypedia/-/wiki/Main/User+ID) |
| getUserName | string | Returns the user's name |
| isAddSessionIdToURL | boolean | |
| isFreeformLayout | boolean | |
| isImpersonated | boolean | Returns `true` if the current user is being impersonated. Authorized administrative users can [impersonate](/discover/portal/-/knowledge_base/7-1/adding-editing-and-deleting-users#editing-users) act as another user to test that user's account |
| isSignedIn | boolean | Returns `true` if the user is logged in to the portal |
| isStateExclusive | boolean | |
| isStateMaximized | boolean | |
| isStatePopUp | boolean | |

## Related Topics [](id=related-topics)

[@product@ JavaScript Utilities](/develop/tutorials/-/knowledge_base/7-1/javascript-utilities)
