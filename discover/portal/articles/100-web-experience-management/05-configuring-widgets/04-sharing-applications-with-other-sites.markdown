# Sharing Widgets with Other Sites [](id=sharing-widgets-with-other-sites)

@product@ supports sharing its widgets with other sites. You can embed a particular instance of a widget running on your site into another site, such as Facebook. This opens up a whole new avenue of exposure to your web site that you would not have had otherwise. In fact, this is how all those Facebook games work.

![Figure 1: The Sharing tab in your widget's Configuration menu lets you share your widget in a variety of ways.](../../../images/collaboration-app-configuration-sharing.png)

To share one of your widgets, open the *Configuration* dialog box from
the widget's *Options* icon (![Options](../../../images/icon-options.png)) and
select the *Sharing* tab. There are five sub-tabs under sharing: Any Website,
Facebook, OpenSocial Gadget, and Netvibes.

## Any Web Site [](id=any-web-site)

Copy and paste the provided snippet of JavaScript code into the web site to
which you want to add the widget. That's all you need to do. When a user loads 
the page on the other website, the code will pull the relevant widget from your 
site and display it.

## Facebook [](id=facebook)

You can add any widget as a Facebook app. To do this, you must first get a 
developer key. A link for doing this is provided to you in the Facebook tab. 
You'll have to create the application on Facebook and get the API key and 
canvas page URL from Facebook. Once you've done this, you can copy and
paste their values into the Liferay's Facebook tab. Save the configuration and
navigate back to the Facebook tab in Liferay. You're given the Callback URL,
which you can copy and paste into Facebook. By doing this, when opening your app
in Facebook, the correct callback URL is used to render the application. You can
also enable the *Allow users to add [application-name] to Facebook*. Then you
can navigate to your app's Options menu and select *Add to Facebook*.

By having the ability to share your widget, this makes @product@ a fantastic
platform upon which to build applications for Facebook.

## OpenSocial Gadget [](id=opensocial-gadget)

OpenSocial comprises a container and a set of APIs for social networking and
other web applications. @product@ can serve up applications to be used as 
OpenSocial Gadgets on any OpenSocial-compatible pages.

To serve a Liferay widget on an OpenSocial platform, copy and paste the provided
gadget URL and add it to the appropriate configuration page of the OpenSocial
platform you're using. Your Liferay instance will serve that widget directly 
onto that platform's page. The URL provided is unique to the specific instance 
of the app, so you could serve multiple instances of the same app as different
OpenSocial Gadgets.

You could use this feature to allow users to view what's happening on your
Liferay instance at a glance, using Asset Publishers or custom RSS feeds. You
could also use Liferay's API to build your own app and provide the URL for users
to place on their OpenSocial pages.

From the Sharing tab in the Configuration menu, you can also enable the selector
*Allow users to add [application-name] to an OpenSocial platform*. Click *Save*
and revisit the *Options* button of your widget in @product@. You'll notice a 
new button named *Add to an OpenSocial Platform*. When selecting this new 
button, the URL is provided to you for sharing the widget to an OpenSocial 
platform. This option provides a shortcut for obtaining the URL and also gives 
a way for users to copy the URL that wouldn't have permission to access the 
widget's Configuration menu.

## Netvibes [](id=netvibes)

Netvibes offers a similar where users can log in, create their own
personal portal, called a *dashboard*, and add customizable widgets to the
dashboard that they create. To set up Netvibes support for a particular widget,
enable the *Allow users to add [application-name] to Netvibes pages* selector.
You can then use the provided URL to create a custom Netvibes widget based on
the instance of the Liferay widget that you're using.

Next, you'll learn how to set permissions for Liferay applications.
