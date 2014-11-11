#Using the Browser Methods of the Liferay JS Object

When designing your app, it's important to consider the browser and platform the 
user is on. You may wish to offer one set of features to users on 
browser/platform A and a different set of features to users on browser/platform 
B; the liferay.js object provides several browser methods that allow you to do 
just that! 

This tutorial covers some of the browser methods available to you in the 
liferay.js object, and how you can use them in your app.

You can get started by learning how to determine the browser and version the
user is on next.

## Determining the Browser and Version

The following methods return boolean values that determine which browser the 
user is currently on:

**isChrome:** Returns `true` if the browser is Chrome, written as
`Liferay.Browser.isChrome()`.

**isFirefox:** Returns `true` if the browser is Firefox, written as
`Liferay.Browser.isFirefox()`.

**isIe:** Returns `true` if the browser is Internet Explorer, written as
`Liferay.Browser.isIe()`.

**isSafari:** Returns `true` if the browser is Safari, written as
`Liferay.Browser.isSafari()`.

**isOpera:** Returns `true` if the browser is Opera, written as
`Liferay.Browser.isOpera()`.

Furthermore, you can determine what version of the browser the user has by
running the following methods:

**getMajorVersion:** Gets the major(whole integer) version number of the browser, 
written as `Liferay.Browser.getMajorVersion()`.

**getRevision:** Gets the revision number of the browser, written as
`Liferay.Browser.getRevision()`.

**getVersion:** Gets the browser version number, written as
`Liferay.Browser.getVersion()`.

Now that you are aware of some of the methods that allow you to retrieve basic 
information about the user's browser and version, you can learn how to figure
out what platform the user is on next. 

## Determining the Platform

In addition to determining the browser the user is on, and the version it is,
you can also determine the platform the user is viewing your app on.

The methods below determine what platform the user is on:

**isIphone:** Returns `true` if the platform is iPhone, written as
`Liferay.Browser.isIphone()`.

**isLinux:** Returns `true` if the platform is Linux, written as
`Liferay.Browser.isLinux()`.

**isMac:** Returns `true` if the platform is Mac, written as
`Liferay.Browser.isMac()`.

**isMobile:** Returns `true` if the platform is mobile, written as
`Liferay.Browser.isMobile()`.

**isWindows:** Returns `true` if the platform is Windows, written as
`Liferay.Browser.isWindows()`.

Below is a code snippet that shows how you could implement the methods above in
the `main.js` file of your portlet's `docroot/js/` directory:

    function checkBrowser() {
        if(Liferay.Browser.isFirefox() && Liferay.Browser.getMajorVersion() > 30)
            //code to execute goes here
    }
    
Once the JavaScript is written in your `main.js`, you need to call the function 
in your JSP. The snippet below shows one possible method of calling the function 
via the click event of a button:

    <input type="button" value="click me" onclick="checkBrowser()" />

As you can see, using a combination of the methods above, you can easily cater 
your app to the user's browser and platform.

## Related Topics

[Using the ThemeDisplay methods of the Liferay JS Object](/develop/tutorials/-/knowledge_base/6-2/using-themedisplay-methods-of-liferay-js)
