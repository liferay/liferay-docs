# Getting Browser and Platform Details in JavaScript [](id=getting-browser-and-platform-details-in-javascript)

As you design apps, you have to consider the user's browser and platform. Often,
you find that you must tailor an app's UI to the features and limitations of
user environments. You might, for example, display one set of controls to a user
on browser/platform A and a different set to a user on browser/platform B. The
`Liferay.Browser` object's methods can determine a user's browser, operating
system, and device type. This tutorial describes these methods and shows how to
use them. 

First, you can determine a browser's type and version. 

## Getting Browser Details [](id=getting-browser-details)

The following methods return `true` if the user's browser matches the type:

**isChrome:** Returns `true` if the browser is Google Chrome. 

**isFirefox:** Returns `true` if the browser is Mozilla Firefox. 

**isIe:** Returns `true` if the browser is Microsoft Internet Explorer. 

**isSafari:** Returns `true` if the browser is Apple Safari. 

**isOpera:** Returns `true` if the browser is Opera. 

The following methods return information about a user's browser version and
revision: 

**getVersion:** Returns the browser's version number (e.g., `44.0`). 

**getMajorVersion:** Returns the starting whole number portion of the browser's
version number (e.g., `44`).

**getRevision:** Returns the browser's revision number (e.g., `537.29`). The
revision number might have no resemblance to the version number. 

Now that you're familiar with the browser type and version methods, you can
learn how to get a user's platform information. 

## Getting Platform Details [](id=getting-platform-details)

The `Liferay.Browser` object has methods that can help you determine the user's
platform, including the user's device type and operating system. 

Here are the platform detail methods: 

**isMobile:** Returns `true` if it's a mobile device. 

**isIphone:** Returns `true` if the device is an Apple iPhone. 

**isMac:** Returns `true` if the device is an Apple Mac. 

**isLinux:** Returns `true` if the operating system is Linux. 

**isWindows:** Returns `true` if the operating system is Microsoft Windows. 

Below is a code snippet that demonstrates how to use these methods in a
`main.js` file in a portlet's `docroot/js/` folder: 

    function checkBrowser() {
        if(Liferay.Browser.isFirefox() && Liferay.Browser.getMajorVersion() > 30) {
            //code to execute goes here
        }
    }

Such JavaScript functions can be referenced in the portlet's JSP's. In a JSP,
the following `input` tag associates the previous function with a button's click
event: 

    <input type="button" value="click me" onclick="checkBrowser()" />

You can use `Liferay.Browser` object's methods to cater to users on various
browsers and platforms. 

## Related Topics [](id=related-topics)

[Getting IDs, Paths, and Sign-in Details in JavaScript](/develop/tutorials/-/knowledge_base/6-2/getting-ids-paths-and-sign-in-details-in-javascript)

[User Interfaces with AlloyUI](/develop/tutorials/-/knowledge_base/6-2/alloyui)

[User Interfaces with the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)
