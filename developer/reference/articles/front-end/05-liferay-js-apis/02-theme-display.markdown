---
header-id: liferay-themedisplay
---

# Accessing ThemeDisplay Information

[TOC levels=1-4]

The `Liferay` global JavaScript Object exposes useful methods, objects, and 
properties, each containing a wealth of information, one of which is 
`ThemeDisplay`. If you have experience with Java development in @product@, you 
may be familiar with ThemeDisplay. The JavaScript object exposes the same 
information as the ThemeDisplay Java Class. It gives you access to valuable 
information that you can use in your applications, such as the Portal instance, 
the current user, the user's language, whether the user is signed in or being 
impersonated, the file path to the theme's resources, and much more. 

The `Liferay` global object is automatically available in @product@ at runtime. 
To access the `ThemeDisplay` object, use the following dot notation in your app:

```javascript
Liferay.ThemeDisplay.method-name
```

This reference describes some of the most commonly used `ThemeDisplay` methods 
for retrieving IDs, file paths, and login information. An exhaustive list of all 
of the available methods is displayed in the table below:

| Method | Type | Description |
| --- | --- | --- |
| getLayoutId | number | |
| getLayoutRelativeURL | string | Returns the relative URL for the page |
| getLayoutURL | string | |
| getParentLayoutId | number | |
| isControlPanel | boolean | |
| isPrivateLayout | boolean | |
| isVirtualLayout | boolean | |
| getBCP47LanguageId | number | |
| getCDNBaseURL | string | Returns the content delivery network (CDN) base URL, or the current portal URL if the CDN base URL is null |
| getCDNDynamicResourcesHost | string | Returns the content delivery network (CDN) dynamic resources host, or the current portal URL if the CDN dynamic resources host is null |
| getCDNHost | string |  |
| getCompanyGroupId | number | |
| getCompanyId | number | Returns the portal instance ID |
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
| getPlid | string | Returns the primary key of the page |
| getPortalURL | string | Returns the portal instance's base URL |
| getScopeGroupId | number | Returns the ID of the scoped or sub-scoped active group (e.g. site) |
| getScopeGroupIdOrLiveGroupId | number | |
| getSessionId | number | Returns the session ID, or a blank string if the session ID is not available to the application |
| getSiteGroupId | number | |
| getURLControlPanel | string | |
| getURLHome | string | |
| getUserId | number | Returns the ID of the user for which the current request is being handled |
| getUserName | string | Returns the user's name |
| isAddSessionIdToURL | boolean | |
| isFreeformLayout | boolean | |
| isImpersonated | boolean | Returns `true` if the current user is being impersonated. Authorized administrative users can [impersonate](/docs/7-1/user/-/knowledge_base/u/adding-editing-and-deleting-users#editing-users) act as another user to test that user's account |
| isSignedIn | boolean | Returns `true` if the user is logged in to the portal |
| isStateExclusive | boolean | |
| isStateMaximized | boolean | |
| isStatePopUp | boolean | |

The example configuration below alerts users with a standard message if they are 
a guest or a personal greeting if they are signed in. This is a basic example, 
and perhaps a bit invasive, but it illustrates how you can create unique 
experiences for each user with the `ThemeDisplay` APIs:

```javascript
if(Liferay.ThemeDisplay.isSignedIn()){
    alert('Hello ' + Liferay.ThemeDisplay.getUserName() + '. Welcome Back.')
}
else {
    alert('Hello Guest.')
}
```
