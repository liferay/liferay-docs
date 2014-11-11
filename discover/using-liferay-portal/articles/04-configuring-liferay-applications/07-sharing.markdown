# Sharing [](id=sharing)

The web was once thought of as a number of islands of applications in a vast
universe of "cyberspace." Many web sites attempted to make their island the
biggest. Some succeeded to a large extent and some failed. More recently, the
concept of the web as an application itself has taken hold, and so widgets have
become very popular nowadays. This concept is part of the "Web 2.0" concept and
is very much enabled by widgets. What is a widget? A widget is a small piece of
code which provides a piece of functionality, can be included on any web site,
but does not necessarily have to be hosted by that web site. If you have ever
embedded a YouTube video on your own web site so that users could watch a video
without actually having to visit [http://youtube.com](http://youtube.com/),
then you've already used a widget.

Liferay supports serving its portlets as widgets. You can embed a particular
instance of a portlet running on your site into another site, such as Facebook.
This opens up a whole new avenue of exposure to your web site that you would
not have had otherwise. In fact, this is how all those Facebook games work.

![Figure 4.11: The Sharing tab of the Portlet Configuration Dialog Box allows you to share your portlet in a variety of ways.](../../images/liferay-collaboration-portlet-configuration-sharing.png)

To share one of your portlets as a widget, open the *Configuration* dialog box
from the portlet's title bar and select the *Sharing* tab. There are five
sub-tabs under sharing: Any Web Site, Facebook, OpenSocial Gadget, Netvibes, and
Friends.

## Any Web Site [](id=any-web-site)

Copy and paste the provided snippet of JavaScript code into the web site to
which you want to add the portlet as a widget. That's all you need to do. When
a user loads the page on the other web site, the code will pull the relevant
portlet from your site and display it.

## Facebook [](id=facebook)

You can add any Liferay portlet as an application on Facebook. To do this, you
must first get a developer key. A link for doing this is provided to you in the
Facebook tab. You'll have to create the application on Facebook and get the key
and canvas page URL from Facebook. Once you've done this, you can copy and
paste their values into the Facebook tab. Your portlet will now be available on
Facebook as a Facebook application.

![Figure 4.12: Liferay's Forums on Facebook is an example of sharing the Message Boards portlet.](../../images/05-liferay-forum-facebook.png)

Incidentally, this makes Liferay a fantastic platform upon which to build
applications for Facebook. See the *Liferay Developer's Guide* or [*Liferay in
Action*](http://manning.com/sezov) for more details. 

## OpenSocial Gadget [](id=opensocial-gadget)

OpenSocial comprises a container and a set of APIs for social networking and
other web applications. iGoogle is a service provided by Google that lets users
create a customizable page and add *Gadgets* to that page. Liferay can serve up
portlets to be used as Open Social Gadgets on iGoogle or other
OpenSocial-compatible pages.

To serve a Liferay portlet on iGoogle, check the box labeled *Allow users to
add [portlet-name] to iGoogle*. Then copy and paste the URL provided into
Google's *Add a feed or gadget* feature on the iGoogle configuration page. Your
Liferay portal instance will serve that portlet directly onto your iGoogle
page. The URL provided is unique to the specific instance of the portlet, so
you could serve multiple instances of the same portlet as different Google
Gadgets.

You could use this feature to allow users to view what's happening on your
portal at a glance, using asset publishers or custom RSS feeds. You could also
use Liferay's API to build your own portlet and provide the URL for users to
place on their iGoogle pages.

### Netvibes [](id=netvibes)

Netvibes offers a similar service to iGoogle--users can log in, create their
own personal portal, called a *dashboard*, and add customizable widgets to the
dashboard that they create. To set up Netvibes support for a particular
portlet, check the *Allow users to add [portlet-name] to Netvibes pages* box.
You can then use the provided URL to create a custom Netvibes widget based on
the instance of the portlet that you're using.

### Friends [](id=friends)

The final sub-tab of the *Sharing* tab is called *Friends*. This tab has a
single check box that allows you to give your friends permission to add the
application as a widget to another web site. This could be particularly useful
for your blog or calendar if you wish to share them.

Next, let's explore what the Recycle Bin does for your Liferay applications.
