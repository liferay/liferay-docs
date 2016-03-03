# Sharing Applications with Other Sites [](id=sharing-applications-with-other-sites)

The web was once thought of as a number of islands of applications in a vast
universe of "cyberspace." Many web sites attempted to make their island the
biggest. Some succeeded to a large extent and some failed. More recently, the
concept of the web as an application itself has taken hold, and so widgets have
become very popular nowadays. This concept is part of the "Web 2.0" concept and
is very much enabled by widgets. What is a widget? A widget is a small piece of
code which provides a piece of functionality, can be included on any web site,
but does not necessarily have to be hosted by that web site. If you have ever
embedded a YouTube video on your own web site so that users could watch a video
without actually having to visit [www.youtube.com](http://youtube.com/), then
you've already used a widget.

Liferay supports serving its apps as widgets. You can embed a particular
instance of an application running on your site into another site, such as
Facebook. This opens up a whole new avenue of exposure to your web site that you
would not have had otherwise. In fact, this is how all those Facebook games
work.

![Figure 1: The Sharing tab in your app's Configuration menu lets you share your app in a variety of ways.](../../../images/collaboration-app-configuration-sharing.png)

To share one of your apps as a widget, open the *Configuration* dialog box from
the app's *Options* icon (![Options](../../../images/icon-options.png)) and
select the *Sharing* tab. There are five sub-tabs under sharing: Any Website,
Facebook, OpenSocial Gadget, and Netvibes.

## Any Web Site [](id=any-web-site)

Copy and paste the provided snippet of JavaScript code into the web site to
which you want to add the application as a widget. That's all you need to do.
When a user loads the page on the other website, the code will pull the relevant
app from your site and display it.

## Facebook [](id=facebook)

You can add any Liferay app as an application on Facebook. To do this, you must
first get a developer key. A link for doing this is provided to you in the
Facebook tab. You'll have to create the application on Facebook and get the API
key and canvas page URL from Facebook. Once you've done this, you can copy and
paste their values into the Liferay's Facebook tab. Save the configuration and
navigate back to the Facebook tab in Liferay. You're given the Callback URL,
which you can copy and paste into Facebook. By doing this, when opening your app
in Facebook, the correct callback URL is used to render the application. You can
also enable the *Allow users to add [application-name] to Facebook*. Then you
can navigate to your app's Options menu and select *Add to Facebook*.

By having the ability to share your apps, this makes Liferay a fantastic
platform upon which to build applications for Facebook.

## OpenSocial Gadget [](id=opensocial-gadget)

OpenSocial comprises a container and a set of APIs for social networking and
other web applications. iGoogle is a service provided by Google that lets users
create a customizable page and add *Gadgets* to that page. Liferay can serve up
applications to be used as Open Social Gadgets on iGoogle or other
OpenSocial-compatible pages.

To serve a Liferay app on iGoogle, check the box labeled *Allow users to
add [application-name] to iGoogle*. Then copy and paste the URL provided into
Google's *Add a feed or gadget* feature on the iGoogle configuration page. Your
Liferay instance will serve that app directly onto your iGoogle
page. The URL provided is unique to the specific instance of the app, so
you could serve multiple instances of the same app as different Google
Gadgets.

You could use this feature to allow users to view what's happening on your
Liferay instance at a glance, using Asset Publishers or custom RSS feeds. You
could also use Liferay's API to build your own app and provide the URL for users
to place on their iGoogle pages.

<!-- Check back about providing access to iGoogle. This site was closed down by
Google in Nov 2013, and probably shouldn't be referenced anymore. -Cody -->

### Netvibes [](id=netvibes)

Netvibes offers a similar service to iGoogle--users can log in, create their own
personal portal, called a *dashboard*, and add customizable widgets to the
dashboard that they create. To set up Netvibes support for a particular app,
enable the *Allow users to add [application-name] to Netvibes pages* selector.
You can then use the provided URL to create a custom Netvibes widget based on
the instance of the app that you're using.

Next, you'll learn how to set permissions for Liferay applications.
