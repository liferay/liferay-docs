#Using the ThemeDisplay Methods of the Liferay JS Object

The ThemeDisplay module provides methods to easily retrieve information about 
the user. If you're familar with the Java ThemeDisplay object, then you'll 
recognize a lot of the objects available to you through the JavaScript 
ThemeDisplay module. 

This tutorial covers some of the available ThemeDisplay methods provided to 
you through the liferay.js object, and how you can apply them to your app.

## Retrieving Ids

Using the ThemeDisplay methods below, you can quickly get the id object you need: 

**getCompanyId:** Returns the company id of the user, written as
`Liferay.ThemeDisplay.getCompanyId()`.

**getLanguageId:** Returns the language id of the user, written as
`Liferay.ThemeDisplay.getLanguageId()`.

**getScopeGroupId:** Returns the group id of the user, written as
`Liferay.ThemeDisplay.getScopeGroupId()`.

**getUserId:** Returns the user id of the user, written as
`Liferay.ThemeDisplay.getUserId()`.

**getUserName:** Returns the username of the user, written as
`Liferay.ThemeDisplay.getUserName()`.

Now that you know how to retrieve id objects using JavaScript, you can learn how
to get file paths next.

## Retrieving File Paths

The liferay.js object has a few methods that you can use to retrieve file paths,
so you don't have to write out the entire path each time. Below are a few of the
methods:

**getPathImage:** Returns the relative path of the image directory for the 
portlet, written as `Liferay.ThemeDisplay.getPathImage()`.

**getPathJavaScript:** Returns the relative path of the JavaScript directory for
the portlet, written as `Liferay.ThemeDisplay.getPathJavaScript()`.

**getPathMain:** Returns the relative path of the main directory, written as
`Liferay.ThemeDisplay.getPathMain()`.

**getPathThemeImages:** Returns the path of the current theme's image directory, 
written as `Liferay.ThemeDisplay.getPathThemeImages()`.

**getPathThemeRoot:** Returns the relative path of the current theme's root 
directory, written as `Liferay.ThemeDisplay.getThemeRoot()`.

Now that you know how to retrieve file paths, you can learn how to get more
information on the user's login next.

## Retrieving Login Information

There are a couple login methods that can be very useful when writing your app:

**isImpersonated:** Returns `true` if the user currenty logged in is 
impersonated, written as `Liferay.ThemeDisplay.isImpersonated()`.

**isSignedIn:** Returns `true` if the user is logged in, written as 
`Liferay.ThemeDisplay.isSignedIn()`.

Below is a snippet that shows one possible use of the methods covered in this
tutorial:

    if(Liferay.ThemeDisplay.isSignedIn()){
        alert('Hello ' + Liferay.ThemeDisplay.getUserName() + '. Welcome Back.')
    }
    else {
        alert('Hello Guest.')
    }
    
The example above alerts the user with a personalized greeting if they're signed 
in, otherwise it defaults to a guest greeting. Although this example is a basic 
one, it shows how you can easily define unique user experiences with the
liferay.js ThemeDisplay module.

## Related Topics

[Using the Browser Methods of the Liferay JS Object](/develop/tutorials/-/knowledge_base/6-2/using-browser-methods-of-liferay-js)
