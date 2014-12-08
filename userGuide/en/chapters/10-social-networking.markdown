
# Social Networking [](id=social-networking-liferay-portal-6-2-user-guide-09-en)

Since the first social networks rose to popularity, concepts such as *Friend*
and later *Like*--previously reserved for direct human interaction--have taken
on new meaning in an always-online, information driven culture. It could be
argued that social networks have transformed the way people interact with their
friends, relatives and colleagues. Friends, connections, followers, circles and
lists have enabled people to connect and stay connected in ways they'd never
been able to before. Initially, these concepts proved to be highly successful
for casual web sites but they didn't take to the business world as quickly. But
many organizations are now realizing the importance of leveraging social
interactions for more than just recreation. Liferay's robust social features
make it a great platform for business web sites, casual web sites and everything
in between.

Social applications have many differences when compared to Standard applications
that are vital to a social networking site. Standard applications have general
and user specific data, whereas social applications can share data within a
defined network. This variation is a huge advantage when trying to communicate
important information to a large group of people. This difference in
communication settings is illustrated below:

![Figure 9.1: The graphic above demonstrates Standard Apps vs. Social Apps.](../../images/15-opensocial-5.png)

Liferay has a constantly improving set of social features which enable you to
encourage social interactions on your own portal and to leverage the power and
popularity of other social networks. In this chapter, we'll discuss:

- General use social portlets
- Social portlets for personal pages
- Configuring personal pages for social networking
- How to connect users using Liferay social relations
- Social equity
- Integrating Liferay with other social sites

When we're finished, you'll be well equipped to use Liferay to power your social
network.

## Leveraging Social Portlets, Activities Tracking, and User Connections [](id=leveraging-social-portlets-activities-t-liferay-portal-6-2-user-guide-09-en)
	
Liferay has many portlets available for social interaction and collaboration.
Some of these portlets are designed to help you work together as a team, while
others are designed to foster social interactions between team members at your
organization.

Some of the social networking portlets should be used on the public pages of
your portal, while others should be used as part of a user's personal site. As
you might expect, the portlets intended for personal page use are more focused
on simple social interactions while the ones which can be placed on any site
help teams interact and improve productivity.

Unless otherwise noted, these portlets are provided with minimal configuration
options. Most of them have at least two configuration options--the option to
change permissions for the portlet view and sharing options for connecting the
portlet to other web sites. Some of the social networking portlets provide
additional options for customizing feed lengths or display styles. Additional
styling changes can be made through custom CSS.

### Using the Core Liferay Social Portlets [](id=using-the-core-liferay-social-portlets-liferay-portal-6-2-user-guide-09-en)

Out-of-the box, Liferay provides four social portlets:

- Activities
- User Statistics
- Group Statistics
- Requests

The Activities portlet displays information about user activity in the site to
which the portlet was added. User activities tracked by the Activities portlet
include updates to the Documents and Media library, blog posts, message boards
posts, wiki pages, and bookmarks. The Activities portlet also tracks information
about web content but only displays this information if the logged-in user is a
site administrator. The Activities portlet functions similarly to Facebook's
news feed: it provides a summary of recent site activity. The Activities portlet
could be used on a site's public or private pages to show what site members have
been up to or it could be used on the public or private pages of a user's
personal site. When added to a personal site, the Activities portlet just shows
the activities of a single user.

![Figure 9.2: The Activities portlet displays information about asset-related user activity in the current site. It only displays information about web content if the current user is a site administrator.](../../images/activities-portlet.png)

Note that the Activities portlet provides links to the assets described in its
activities feed. However, the links to the assets won't work unless there's a
way to display the assets on the page. For example, suppose that the user Joe
Bloggs uploaded a document called *Lunar Resort happenings for August* to a
site. If the Activities portlet has been placed on a page of that site, it will
display a feed entry with a link to the *Lunar Resort happenings for August*
document. However, the link won't work unless there's a Documents and Media
portlet or a Documents and Media Display portlet for the document to be
displayed in. Remember add the appropriate portlets to the page of the
Activities portlet if you want the Activities portlet's links to work. 

The User Statistics and Group Statistics portlets integrate with Liferay's
social activity system which tracks user activity and assigns points for various
actions. They're useful for displaying various kinds of statistical information
such as the most active users, the average number of activities per day and the
highest periods of activity. The exact information that's displayed is
configurable and you can add counters to track many different kinds of
information such as the number of comments on users' assets, number of
subscriptions to users' assets, number of blog entries by the user, number of
blog entry updates by the user, number of message board posts, etc. Although
these are core Liferay portlets, they're only useful if Liferay's social
activity system has been enabled for your site. We'll discuss the User
Statistics and Group Statistics in more detail once after we've discussed
Liferay's social activity system.

Lastly, the Requests portlet can be added to pages of users' personal sites to
allow users to respond to social requests such as friend requests. The Requests
portlet is usually added to the private page set of a personal site since only
one user, the owner of the personal site, is allowed to use it. The Requests
portlet is a core Liferay portlet but is only useful if there's some way to send
social requests, such as friend requests. This functionality can be added by
installing the Social Networking app from Liferay Marketplace. It's also
possible to develop and deploy custom portlets that implement Liferay's social
API to enable social requests. Please refer to the [Liferay Developer's Guide](https://www.liferay.com/documentation/liferay-portal/6.2/development) or
the
[Javadocs](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portlet/social/service/package-summary.html)
for information about Liferay's social API. 

### Installing the Social Networking Portlets [](id=installing-the-social-portlets-liferay-portal-6-2-user-guide-09-en)

Social Activity can be enabled on a per-site basis in Liferay Portal. The
Activities, User Statistics, and Group Statistics portlet can be used out-of-the
box. Liferay provides additional social networking functionality in the form of
the Social Networking app that's available from [Liferay Marketplace](http://www.liferay.com/marketplace). The Social Networking app
provides a number of social portlets that allow you do anything from building an
intranet to improve your team's ability to collaborate to building a public
social networking site to rival Facebook or Linked-In!

The Social Networking app provides the following portlets:

- Friends
- Friends' Activities
- Wall
- Map
- Members
- Members' Activities
- Meetups
- Summary

If these social networking portlets aren't enough to satisfy your needs, make
sure to check out Liferay Social Office which is also available as an app on
Liferay Marketplace. Social Office provides additional portlets and
functionality including contacts, microblogs, events, tasks, private messaging,
site templates, and a custom theme.

You can manage the plugins in your portal from the Control Panel. If you're
logged in as an administrator, go to the Control Panel and click on *App
Manager* in the *Apps* section. From here, you can administer all your plugins.
For now, we'll deal with the *Social Networking* app. Under the Apps heading,
click *Store* to navigate to Liferay Marketplace. Install and download the
*Social Networking* portlet to your portal instance.

Now that we have our social plugins ready to go, let's learn how to use them!

### Using Social Networking on Public Pages [](id=using-social-networking-on-public-pages-liferay-portal-6-2-user-guide-09-en)

There are several social portlets that are designed for use on public portal
pages. The goal of these is to use social connections to help a group work
together more closely. These include the **Members**, **Meetups**, **Summary**,
and **Activities** portlets.

![Figure 9.3: The *Members* portlet displays all the members of a particular site.](../../images/XX-social-networking-members-portlet.png)

The Members portlet is a simple list of all the current site's members. The only
configuration options you have are permissions, which are the same for every
portlet. For example, you might change the permissions so only members of the
current site can view the portlet. 

![Figure 9.4: Meetups are organized in the two tabs *All Meetups* and *My Meetups*.](../../images/XX-social-networking-meetups.png)

The Meetups portlet is a tool for creating casual meetings for users of your
portal. Anyone can create a "meetup" and give it a title, description,
date/time, maximum number of attendees, price and provide an image. Any meetups
that are created are displayed in the portlet for anyone to view. Users can
register for the meetup, which lets the organizer keep track of who's coming. 

The options for creating a meetup are essentially the same as those for creating
a calendar event and the Meetups portlet shares some functionality with the
Calendar. For more information on the Calendar portlet and configuring events,
see chapter 8.

![Figure 9.5: All of your site members' activities are listed with brief descriptions and time stamps.](../../images/XX-social-networking-activities.png)

The Activities portlet comes in two varieties: the standard Activities portlet
and the Members' Activities portlet. The basic function of the portlets are the
same--they both display a feed of what users are doing on the portal. The
difference is that Activities displays what's going on across the entire portal,
while Members' Activities displays only what members of the current site have
been doing. There's also a Friend's Actvities portlet that's intended for use on
users' personal pages. In the Configuration dialog box of any variety of the
Activities portlet, you can use the *Maximum Activities to Display* dropdown
menu to set a limit on how many activities can be displayed at once in the
portlet window.

![Figure 9.6: The Map portlet can be placed on a single user's page to display one location or on the main site page to display all users' location.](../../images/map-portlet.png)

The Map portlet allows you to view the locations of site members, both locally
and internationally. Only members of the site to which the Map portlet has been
added are displayed. In order to configure the Map portlet, you need to install
the IP Geocoder portlet (available from Liferay Marketplace) and configure it to
access MaxMind GeoIP or GeoLite on your server. For more information on
configuring geolocation services, visit the MaxMind support page at
[http://www.maxmind.com/app/installation?city=1](http://www.maxmind.com/app/installation?city=1).
Once you've installed the Geocoder portlet and configured it to access MaxMind
GeoIP or GeoLite, you'll need a key from Google to access Google's Maps API so
your Map portlet will work. Visit
[https://developers.google.com/maps/documentation/javascript/tutorial#api_key](https://developers.google.com/maps/documentation/javascript/tutorial#api_key)
to learn how to obtain a valid Google API key. To configure the Map portlet
using the GeoLite City database, use the following steps:

1. Install the Social Networking plugin, if you haven't already done so.

2. Install the IP Geocoder portlet. (Both the Social Networking and IP Geocoder
   apps can be installed from Martketplace.)

3. Shut down your application server.

4. Download the Geo Lite City database from
   [http://www.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz](http://www.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz).

5. Unzip the `.dat` file to your desired storage location.

6. Create a `portlet-ext.properties` file in the
   `/{ROOT}/webapps/ip-geocoder-portlet/WEB-INF/classes/` directory of your
   Liferay installation.

7. Add the property `maxmind.database.file={GeoLite City .dat file path}` to
   this file.
   
    Note: Make sure your the file's directory path is separated by `\\`. For
    example: `E:\\ce\\bundles\\GeoLiteCity.dat`.

8. Create a `portlet-ext.properties` file in the
   `/{ROOT}/webapps/social-networking-portlet/WEB-INF/classes/` directory of
   your Liferay installation.

9. Add the property `map.google.maps.api.key={Your API Key}` to this file. If
   you haven't done so already, you'll need to generate a [Google Maps API
   Key](https://developers.google.com/maps/documentation/javascript/tutorial#api_key).

10. Restart your application server.

11. Enjoy the Maps portlet!

Next, let's look at the social networking portlets designed for use on personal
pages.

### Using Social Networking on Personal Pages [](id=using-social-networking-on-personal-pag-liferay-portal-6-2-user-guide-09-en)

In addition to the portlets available for general use, there are a handful that
can only be used on personal pages. These include the Summary, Wall, Friends,
and Friends' Activities portlets. These portlets can be used to create profile
pages similar to Facebook's or Google+'s. 

![Figure 9.7: You can get creative with Social Networking portlets and make a custom designed profile page.](../../images/XX-social-networking-personal-portlets.png)

The Summary portlet provides a quick overview of a user's profile. When posted
in a user's personal site, it displays the user's name, profile picture and job
title. Users can add additional personal information by clicking on *Edit* in
the portlet and filling in information in the *About Me* section. This portlet
is essential to any social implementation on Liferay, because it has the *Friend
Request* button. This enables users to initiate social relationships.  Note that
this portlet simplifies a much more powerful underlying social networking API
that defines many different kinds of relationships, including friends. Your
developers can take advantage of this API to create powerful social
applications. For more information on this, see [*Liferay in
Action*](http://manning.com/sezov) (Manning Publications) or the *Liferay
Developer's Guide*. 

The Wall portlet provides a place for users to leave messages on other users'
profiles. The messages can only be plain text as no formatting or HTML is
supported. Once a post is added to their wall, users have the ability delete it
or respond to it with a quick link to post on the original poster's wall.

The Friends portlet shows a list of all the user's friends with links to their
profiles. The Friends' Activities portlet displays information about a user's
friends' activities on the portal.

Now that we've discussed the functions of the suite of social networking
portlets that ships with Liferay, let's put them all together and make a social
web site.

### Liferay's Social Tools in Action [](id=liferays-social-tools-in-action-liferay-portal-6-2-user-guide-09-en)

To get started with Liferay's social features, let's set up the public pages of
our users' personal sites to include social apps. Because of Liferay's flexible
page layout options, we have a large number of options for how to set the pages
up. For simplicity's sake, we'll make something that's fairly similar to the
original Facebook layout.

#### Setting up Users' Personal Pages [](id=setting-up-users-personal-pages-liferay-portal-6-2-user-guide-09-en)

Before we start adding portlets to pages, we should configure Liferay so that
everyone (or some subset of everyone) has the same social features. We have two
ways to do this with advantages and disadvantages to each.

**User Groups:** Placing users into a group enables you to create a user group
site for them. The pages and portlets defined by the user group site are copied
to members' personal sites. With the user group site, you can control whether
users can modify pages and you can push changes out to users in the future. Once
the site template is assigned to a user group, you can set the *Default User
Associations* to have all users be the member of a particular group in *Portal
Settings* in the Control Panel. The advantage of this is that it can be managed
entirely through the GUI and it's easy to configure. If you base your user
group's site on a template, you can use the *Enable propagation of changes from
the site template* option to manage all user pages simply by changing the
template. This is the recommended way to manage personal pages across the
portal. For more information on user group sites, see chapter 15.

**Portal Properties Configuration:** The legacy way to do this is with the
configuration file. You can specify a default layout and portlets for personal
pages in your `portal-ext.properties` file. Note that this method applies
changes to all users' personal sites. However, it does not provide as much
maintainability or as many customization options as does using user group sites.
User group sites allow you to choose what's modifiable by the user.  For more
information on the `portal-ext.properties` method, see *Default User Private
Layouts* and *Default User Public Layouts* in chapter 20.
    
Because it's the recommended method, we'll use the user group method to create
the layouts. As an administrator, go to the Control Panel and select *Site
Templates* from under the *Sites* section. Click *Add* and fill out the form.
We'll call our new site template *Social Layout*. Click *Save*.

![Figure 9.8: You can give your site template a custom name and description and also specify several configuration settings..](../../images/social-networking-site-template.png)

---

![tip](../../images/01-tip.png) Tip: Unchecking *Allow Site Administrators to
Modify the Pages Associated with this Site Template* only prevents users from
modifying the specific pages contained in the template but does not disable a
user's ability to add or modify additional pages.

---

Once you've created the template, choose *Actions &rarr; View Pages*. Let's
change the name of the page from the default to *My Profile* and add some
portlets to the page. Back in the Control Panel, select *User Groups* from the
*Users* section. Once there, click *Add* and name the group *Social Users*. When
creating a user group, you have the option to set a user group site; use this
option and select the Social Layout template for your Public Pages.

Now go to *Portal Settings* and select *Users* from the submenu. From the Users
page, go to the *Default User Associations* tab and enter *Social Users* in the
User Groups section. Now all users on the portal get a Social Profile page. Now
the question is, how do we encourage users to visit each others fancy new
profile pages?

#### Connecting Users Through Collaboration [](id=connecting-users-through-collaboration-liferay-portal-6-2-user-guide-09-en)

There are many ways that social networks connect users. These generally involve
some kind of mutual interest or experience. On a site like Facebook, you can
connect with people from school, from work or from other personal connections.
On a music based networking site like Last.fm, you can connect with people who
have similar tastes to yours. With Liferay's social networking collaboration is
the key to connection. 

Using our example site of lunar-resort.com, we can take a closer look at ways
users can be connected through hierarchies and ways they can connect to each
other. We'll look at a handful of portlets, both those designed specifically for
connecting users and those that can create connections as a side-effect of just
getting work done.

The Site Members Directory portlet can provide a simple way for users to
connect. If we have a site dedicated to Lunar Resort astronauts, we can place a
Site Members Directory portlet on that site, listing all the users that have
joined that site. Users can connect by sending requests to other users on that
list. This isn't the worst way to get users connected but it probably won't be
very effective. Why not? Well, other than sharing some very basic common
interests, we haven't really had any interactions.

The Activities portlet provides a similar but more effective means of
connection. Because it shows a list of what other users are doing, this portlet
helps users discover who is among the most active across the site or the portal,
and thus who might be a good connection. 

Probably the most effective way users can connect is by interacting with other
users. Every portlet in the Collaboration category provides information on who
is contributing, regardless of how. You can see who is creating a thread in a
message board, editing a wiki article, blogging or creating a calendar event.
Users can use these to connect based on content--if I find your blog
interesting, or if you answer my question on the message board, we can use that
as a point to connect as friends to further our interactions. This way, instead
of our connection being forced or arbitrary, we've connected based on the fact
that we've directly interacted and share a common interest--just like people did
before they had the internet.

"Friend" is only the default social relationship as implemented by Liferay's
social portlets. You can design things so that users are automatically connected
through Site and Organization membership. And there are many other relationship
types beyond Friend: your developers can take advantage of these by using
Liferay's social API. This is covered in *[Liferay in
Action](http://www.manning.com/sezov)* and the *Liferay Developer's Guide*. Now
that you've got all these social applications running on your system, you might
wonder: how can I measure social interaction? How do I identify the best
contributors to my site? Liferay has an answer: social activity measurements.

## Measuring Social Activity [](id=measuring-social-activity-liferay-portal-6-2-user-guide-09-en)

When you have a lot of user interaction on your web site, it can be helpful to
try to separate the signal from the noise. Liferay contains a lot of
applications which end users can use to communicate with each other and provide
information. Some of this information is good and helpful and some of it can be
rather unhelpful. Using Liferay's Social Activity feature will help show which
users are making real, valuable contributions.

![Figure 9.9: The Social Activity page of the Control Panel allows you to enable social activity for assets and specify points for participation and contributions.](../../images/05-social-equity.png)

To activate Social Activity, you'll first need to determine which collaboration
applications you want to use Social Activity. There are currently three types of
content you can use with Social Activity - Blogs Entries, Message Board
Messages, and Wiki Pages. Activation is a la carte - so you can use it on one,
two, or all three applications. Social Activity tracks three metrics from within
each of these applications two are for the user - *Participation* and
*Contribution* - and the other, *Popularity*, is for the asset involved.

Let's activate Social Activity for Blogs Entries. Check the box next to *Blog
Entry*. You now have options to set point values and limits on several different
actions for blogs. You'll notice each item on the list has dropdowns you can use
to set the number of participation and contribution points; popularity points
are tied directly to contribution points. In addition to that, you can expand
the box by clicking *Limits* in the top right of each list item. You can use
this to set a limit on how many times a user can perform this activity with a
specific asset and receive the requisite points. For some activities, you can
set limits on both participation and contribution points, but on new content
creation, you can only set limits on participation points.

It might not be immediately obvious, but for all actions that do not involve the
creation of a new asset, all of the contribution points go to the original asset
creator and all popularity points go to the original asset. That means if *Votes
on a Blog* is set to have 1 *Participation* point and 5 *Contribution* points
(and therefore 5 *Popularity* points), the user who votes on the asset will
receive 1 participation point, the user who created the asset will receive 5
contribution points, and the asset will receive 5 popularity points. 

![Figure 9.10: You can set limits for your site's social activity.](../../images/social-equity-limits.png)

It's easy to assign points--you can arbitrarily assign points for just about
anything--the challenge is making the points significant in some way. As
mentioned before, the primary purpose of social activity tracking is to make
sure that users who regularly contribute to the portal and participate in
discussions are recognized as such. So the central piece of the social equity
display is the *User Statistics* portlet.

The User Statistics portlet displays a list of users ranked by an amalgamation
of their participation and contribution scores. By clicking on the Configuration
icon for the portlet, you can change some of the specifics of the rankings.
There are five check boxes that you can enable or disable:

**Rank by Contribution**: If this is checked, a user's contribution score will
be used as a factor in calculating their rank.

**Rank by Participation**: If this is checked, a user's participation score will
be used as a factor in calculating their rank.

**Show Header Text**: Determines whether the title shows or only the rankings.

**Show Totals**: Toggles the display of the users activity score next to their
name.

**Display Additional Activity Counters**: You can toggle the display of any
number of other pieces of information next to the users name in the statistics,
ranging from the number of comments on assets a user has created to the number
of wiki articles that the user has created. If you want to display multiple data
points, you can click the *plus* button to add one and the *minus* button to
remove one. You can have as many data points displayed as you want, but
displaying too many might make your portlet a little unwieldy.

![Figure 9.11: The User Statistics portlet gives rankings to promote user contributions and participation.](../../images/user-statistics-portlet.png)

The *Group Statistics* portlet provides some more advanced data analytics. If
you add it to a page, and click on the configuration icon, you can select the
assets to track. You can click the *plus* button to add additional slots, and
choose from the various metrics available for each slot, covering virtually any
action that a user can perform on content in the portal. If you decide that
you're displaying too many metrics, you can click the *minus* button for a
particular slot on the configuration view to remove it.

There are a wide-ranging number of actions that you can provide social credit
for. Users can receive credit for everything from subscribing to a blog to
writing wiki articles. You can easily tweak the numbers in the Control Panel if
it becomes clear that certain activities are weighted too high or too low.

Social Activity can be an invaluable tool for portals that are heavily driven by
community-created content. It allows you to easily recognize users who are major
contributors and it indicates to new users whose advice will be most
trustworthy. Social Activity is easy to set up and can be configured differently
for each site, increasing the flexibility of your portal.

Beyond Liferay's social API, there is also support for the OpenSocial standard.

## Exporting Portal Applications as Widgets and OpenSocial Gadgets [](id=exporting-portal-applications-as-widget-liferay-portal-6-2-user-guide-09-en)

OpenSocial is a framework designed for the creation of socially themed
application programming interfaces (APIs). OpenSocial applications, called
*gadgets*, can be used in any web-based application that supports them. They are
characterized as simple, widely available, and easy to deploy. Gadgets are
especially popular on social networking sites. They can, however, be used in
many different ways throughout your site. 

Liferay allows any OpenSocial gadget to be used on a page. An OpenSocial gadget
is specified in an XML document and consists of embedded HTML and JavaScript.
Liferay allows gadgets to communicate with each other and with portlets. This
allows your gadgets to run seamlessly without your having to constantly check or
update their content. They automatically update based on their connections with
other applications. OpenSocial gadgets support numerous open web technologies
such as *OAuth*, which we'll discuss in more detail later in the chapter.

Gadgets are socially aware and can share data across a community of users. You
can define your own groups and create gadgets to communicate information based
on pages (community/team pages), applications (gadgets/widgets/portlets), data,
users, roles and authorization, and policies. In short, you can develop gadgets
to allow individuals to access and share data within their social networks.

### Adding OpenSocial Gadgets [](id=adding-opensocial-gadgets-liferay-portal-6-2-user-guide-09-en)

The OpenSocial plugin can be installed through Liferay Marketplace for both
Liferay CE and EE. Installing the OpenSocial plugin enables you to add
OpenSocial gadgets to pages, just like you'd add portlets. There are two types
of gadgets: 

- "Adhoc" gadgets that users can add to a page via URL
- Gadgets published by the Control Panel that are available portal-wide

First, we'll go through steps to add an Adhoc gadget to a page.

#### Adding Adhoc Gadgets [](id=adding-adhoc-gadgets-liferay-portal-6-2-user-guide-09-en)

This method is a quick way to add a gadget to a single page. To do this, go to
the *Add* &rarr; *More* menu and add *OpenSocial Gadget* to the page. The
portlet displays a link to pick a gadget for display:

![Figure 9.12: Configure a gadget to display in your portlet.](../../images/15-opensocial-1.png)

Click the configure link and a configuration window opens. Next, you need to
insert a URL to an OpenSocial gadget. We'll insert the URL for a colorful
calculator which is:

    http://www.labpixies.com/campaigns/calc/calc.xml

After pasting the URL into the text field, click *Save* and your new gadget is
visible on your page.

![Figure 9.13: The calculator gadget displays seemlessly on your page.](../../images/15-opensocial-2.png)

This particular gadget allows you to change its "skins" to fit your needs.
Likewise, there are many other user-friendly interactive gadgets that give you
flexibility to fit them into your themed sites. As you find gadgets that would
work nicely throughout your portal, you can publish them for portal-wide use.
You'll learn that next.

#### Adding Gadgets for Portal-wide Use [](id=adding-gadgets-for-portal-wide-use-liferay-portal-6-2-user-guide-09-en)

You can easily make gadgets available for adding to pages as you would any other
application. We'll demonstrate this by adding a *To-Do List* gadget for
portal-wide use.

1. Go to the Control Panel and select *OpenSocial Gadget Publisher* under the
   *Apps* heading

2. Click *Publish Gadget*

3. Insert the URL for the *To-Do List* gadget:
   `http://www.labpixies.com/campaigns/todo/todo.xml`

4. Select an appropriate category for your gadget

5. Click *Save*

![Figure 9.14: Configure new gadgets with ease.](../../images/15-opensocial-38.png)

Your *OpenSocial Gadget Publisher* should now look like this:

![Figure 9.15: Publish gadgets for portal-wide use via the OpenSocial Gadget Publisher.](../../images/15-opensocial-3.png)

Clicking *Actions* next to the gadget enables you to edit, refresh, change
permissions on, or delete the gadget. Here is a brief listing of what these four
buttons do:

*Edit:* allows you to change the URL or category. 

*Refresh:* manually refreshes the gadget cache to reflect changes that have been
made to the gadget that may not currently be displayed in the portlet. 

*Permissions:* gives you the basic *View*, *Update*, *Delete*, and *Permissions*
options for each role on your site. 

*Delete:* removes the listing for the gadget. 

If you navigate to *Add* &rarr; *Applications* &rarr; *Gadgets*, you should see the
*To-Do List* gadget.

![Figure 9.16: You can conveniently list your gadgets within the *Gadgets* category.](../../images/15-opensocial-4.png)

In the next section, we'll demonstrate how to share OpenSocial gadgets with
other sites.

### Sharing OpenSocial Gadgets [](id=sharing-opensocial-gadgets-liferay-portal-6-2-user-guide-09-en)

OpenSocial consists of a set of APIs for social networking. Liferay implements
the OpenSocial standard, so you can be assured that your gadgets run on Liferay.
That also means gadgets hosted by a Liferay Portal instance can be deployed and
run in any standard OpenSocial container. It may be beneficial for you to share
gadgets from your Liferay server with other sites, such as iGoogle. Google's
iGoogle lets users customize their own page and add gadgets to their page. Your
Liferay Portal users can share their portlets and other OpenSocial gadgets on
iGoogle or any other OpenSocial-compatible site. Let's try this now.

---

![Tip](../../images/01-tip.png) **Warning:** The iGoogle site will be removed
completely from Google on November 1, 2013:
[https://support.google.com/websearch/answer/2664197](https://support.google.com/websearch/answer/2664197).
Therefore, after the specified date, sharing your Liferay OpenSocial gadgets to
iGoogle will no longer be possible.

---

<!-- Since Google has discontinued iGoogle (see
http://googlesystem.blogspot.com/2012/07/igoogle-will-be-discontinued.html),
this isn't a good example. Can you find another example? -->

<!-- Liferay is only configured to share OpenSocial gadgets with iGoogle.
Therefore, there are no other examples that can be created. There are other
sites trying to replace iGoogle (www.ighome.com), but Liferay is not configured
to share with these sites. -->

For our example, we'll share Liferay's *Loan Calculator* on iGoogle.

1. Add the *Loan Calculator* portlet onto your Liferay page

2. Click the gear icon in the upper right corner of the portlet and select
   *Configuration*

3. Select the *Sharing* tab and the *OpenSocial Gadget* sub-tab

4. Check the box labeled *Allow users to add Loan Calculator to iGoogle*. Also,
   replace "localhost:8080" with the name of your public domain and port.

    ![Figure 9.17: Allow users to add your portlet as an OpenSocial Gadget in iGoogle.](../../images/15-opensocial-7.png)

5. Click Save

6. Close out the window and navigate back to the wrench icon in the upper right
   corner of your portlet. There is a new option named *Add to iGoogle*
   available. Click on this button to add your portlet to your iGoogle page.

    ![Figure 9.18: Users simply click the *Add to iGoogle* button to add your portlet to their iGoogle page.](../../images/15-opensocial-8.png)

Your portlet is now available on your iGoogle page!

By going through this process, Liferay shared the URL of your portlet to
iGoogle. The URL you provided is unique to your specific instance of the
portlet. This allows you to share multiple instances of the same portlet as
different Google Gadgets.

You could use this sharing capability to let users view what's happening on your
portal at a glance. As you can imagine, you can share all kinds of information
from your portal gadgets and portlets with your various circles of friends,
colleagues, and fellow community members.

### Gadget Personalization [](id=gadget-personalization-liferay-portal-6-2-user-guide-09-en)

Liferay allows gadgets on your site to be personalized with data from
third-party applications. Some of the third-party sites that authorize users to
access application data include Evernote, Facebook, Google, Netflix,
Photobucket, and Yahoo. Of course, many users feel uncomfortable giving away
their private credentials to access these applications. Fortunately, Liferay
allows you to use *OAuth* to protect your credentials while you access resources
on these sites.

OAuth is an open standard that authorizes third-party applications to interact
with a user's resources. Users can share their private resources from one site
with another site without supplying typical credentials, such as their user name
and password. OAuth uses request and access tokens as well as a token secret to
authenticate the users of your gadget. For more information on OAuth and how it
can be incorporated into your portal, visit the
[OAuth](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/liferay-utility-applications-liferay-portal-6-2-user-guide-13-en)
section of this guide.

<!-- Insert exact link when OAuth section is available on liferay.com -->

Let's see how to implement OAuth in a custom OpenSocial gadget!

#### OAuth Admin Configuration [](id=oauth-admin-configuration-liferay-portal-6-2-user-guide-09-en)

OpenSocial defines a specification that allows gadgets to incorporate OAuth to
access protected resources from service providers. A brief example is provided
to demonstrate how easy it is to leverage OAuth within gadgets on your site. 

For this example, we'll set up a demo Twitter account gadget using OAuth. First
we must configure your gadget. Follow the steps below to acquire the consumer
key and secret given by the service provider.

1. Similar to previous examples, add the Twitter Demo gadget to your page. Go to
   the Control Panel and click on *OpenSocial Gadget Publisher* under the
   *Apps* heading. Click *Publish Gadget* and insert the Twitter Demo URL:

        https://raw.github.com/dejuknow/opensocial-gadgets/master/Twitter/Twitter.xml

2. Click *Save*

3. For OAuth-enabled gadgets, you can select the *Manage OAuth* button from the
   *Actions* tab. Select *Manage OAuth* for your Twitter gadget. As shown below,
   you have several options under "twitter" that you must fill in to configure
   your gadget. You must also register your gadget with Twitter to access the
   Consumer Key and Consumer Secret.

    ![Figure 9.19: Twitter allows you to manage OAuth for your Twitter gadget.](../../images/15-opensocial-9.png)

4. Go to [https://dev.twitter.com](https://dev.twitter.com) and click *Get
   started with the platform* to begin registering your gadget.

    ![Figure 9.20: Select *Get started with the platform* from within the *Developers* page.](../../images/15-opensocial-11.png)

5. Navigate to the *REST API* heading and click *Manage & create your
applications*. Then click *Create New App*.    
    
6. Fill in the *Name*, *Description*, and *Website* fields with what you prefer.

    a. For the *Callback URL* field, enter Liferay's default callback URL:
    `http://myLiferayServer/opensocial-portlet/gadgets/oauthcallback`. Replace
    "myLiferayServer" with an appropriate value -- for this demonstration, we'll
    use 127.0.0.1:8080.
    
    b. Finally, select the *Create your Twitter application* tab at the bottom
    of the page.

    ![Figure 9.21: Fill in *Application Details* to setup connectivity between your Twitter gadget and your portal.](../../images/15-opensocial-12.png)

7. You are given the OAuth setting that you need to configure your gadget on
   Liferay. Copy the Consumer Key and Consumer Secret to your clipboard.

    ![Figure 9.22: Here are the *Consumer Key* and *Consumer Secret* (value is blacked out for security reasons).](../../images/15-opensocial-13.png)

8. Enter your Consumer Key and Consumer Secret under the *Manage OAuth* that you
   navigated to earlier. Also, select `HMAC_SYMMETRIC` for the *Key Type* and
   then click *Save*.

    Note: Liferay offers `PLAINTEXT` and `RSA_PRIVATE` as alternative key types.
    HMAC symmetric and RSA private are commonly used production key types,
    whereas plain text should never be used in real-world settings. 

9. Navigate to the *Settings* tab and under *Application Type*, select *Read and
   Write*. Also, select the *Allow this application to be used to Sign in with
   Twitter* checkbox. Then click *Update this Twitter application's settings* at
   the bottom of the page.

    ![Figure 9.23: Select the *Read and Write* option to enable two way communication.](../../images/15-opensocial-19.png)

10. Navigate back to the *Details* tab and, at the bottom of the page, click
    *Create my access token*.

Congratulations! Your Twitter gadget is now configured with OAuth.

Next, we'll configure the gadget within Liferay Portal.

### Incorporating OAuth Within Your Site [](id=incorporating-oauth-within-your-site-liferay-portal-6-2-user-guide-09-en)

Now that your gadget is registered with Twitter and is configured with OAuth,
you can add it to your Liferay Portal. The OAuth client you configured in the
previous section allows users to protect their credentials while accessing
resources on your site. For this section, we'll demonstrate how to add the
OAuth-configured gadget to your page.

1. Navigate to *My Private Pages* and click *Add* &rarr; *More...* &rarr;
   *Twitter Gadget*. If your gadget is configured correctly, it should appear
   like this:

    ![Figure 9.24: Your OAuth configured Twitter gadget awaits personalization with your Twitter account.](../../images/15-opensocial-14.png)

2. Click on *Personalize this gadget* to be redirected to the service provider.

3. Fill in your Twitter user name and password and select *Authorize app*

    ![Figure 9.25: Authorizing your OpenSocial application to use your account is straightforward.](../../images/15-opensocial-15.png)

4. Your Twitter Gadget should now show your last 20 tweets from your timeline.
   Your gadget should look similar to the snapshot below:

    ![Figure 9.26: Check out your Twitter gadget timeline!](../../images/15-opensocial-16.png)

5. Using this gadget, you can tweet your current status and have it display on
   your Liferay site and Twitter page. To change the amount of tweets displayed,
   click on the wrench icon in the upper right corner and select
   *Configuration*.  Under the *Setup* tab, you can type the number of tweets to
   display.

    ![Figure 9.27: Configure the number of Tweets to display.](../../images/15-opensocial-17.png)

6. Lastly, you can tweet and view your Twitter timeline. The snapshot below
   displays what the Twitter Gadget looks like when tweeting.

    ![Figure 9.28: Here is your Twitter gadget just the way you like it!](../../images/15-opensocial-20.png)

As you can see, OAuth is easy to configure and offers users the freedom to
securely add valuable data from third-party sites.

### Creating and Editing OpenSocial Gadgets [](id=creating-and-editing-opensocial-gadgets-liferay-portal-6-2-user-guide-09-en)

OpenSocial gadgets are XML documents, so as part of Liferay's OpenSocial
integration, a gadget editor is included. The gadget editor is a complete
development environment for gadgets providing syntax highlighting, a preview
function, undo/redo options, and built in tabs for working on multiple gadgets
at once. You can also organize and manage gadgets through a simple file manager
embedded into the portlet. To access the gadget editor, go to *Site
Administration* &rarr; *Configuration* &rarr; *Content* and click *OpenSocial
Gadget Editor*.

Once you have created and saved a gadget using the editor, click on the wrench
next to the file to rename, delete, publish or get the URL for your gadget. If
you want to display your gadget somewhere, click *Publish* to choose a category
and display your gadget in the application menu or click *Show URL* to get a URL
to display your gadget on any site that supports OpenSocial.

In addition to the social interactions that you can create on your portal,
Liferay can integrate with some other popular social networks. This enables you
to leverage their power and popularity for your portal's content.

## Integrating with Facebook [](id=integrating-with-facebook-liferay-portal-6-2-user-guide-09-en)

Facebook is currently the number one social network in the world with somewhere
over 1 billion active users. If you're trying to build acommunity on your 
portal, you don't want to neglect a bridge to that many possible users. With 
that in mind, Liferay provides a few easy ways for you to integrate your portal 
with Facebook.

### Facebook Sign On [](id=facebook-sign-on-liferay-portal-6-2-user-guide-09-en)

Like many web sites you may visit, any portal running on Liferay can be set up
to use Facebook for sign in. This makes it easier for users to sign in to your
site, since they won't need to remember another user name and password, For more
information on setting up Facebook sign on, see chapter 15.

### Using your Portlets as Facebook Applications [](id=using-your-portlets-as-facebook-applica-liferay-portal-6-2-user-guide-09-en)

You can add any Liferay portlet as an application on Facebook. To do this, you
must first get a developer key. A link for doing this is provided to you in the
Facebook tab in any portlet's Configuration screen. You will have to create the
application on Facebook and get the key and canvas page URL from Facebook. Once
you've done this you can copy and paste their values into the Facebook tab. Your
portlet is now available on Facebook.

This integration enables you to make things like Message Boards, Calendars,
Wikis, and other content on your portal available to a much larger audience
(unless you already have a billion users on your site, in which case, kudos to
you).

## Integrating with Twitter [](id=integrating-with-twitter-liferay-portal-6-2-user-guide-09-en)

Liferay Portal also provides integration with Twitter via the Twitter app on 
[Liferay Marketplace](http://liferay.com/marketplace). Once you register your 
Twitter username with the app, your tweets are shown in your Activities portlet 
along with your activity feed. Make sure that you get the appropriate version of 
the app for your Liferay instance. The Twitter CE app is intended for Liferay 
Portal CE, while the Twitter EE app is intended for Liferay Portal EE.

There are two ways to register your Twitter username with the app after you 
install it. The first is through the Twitter portlet after you add it to a page. 
Click the link in the portlet and then enter your Twitter username in the 
Twitter field. Alternatively, from the Dockbar select *User* &rarr; *My Account* 
&rarr; *Social Network* and then enter your Twitter username in the Twitter 
field.

![Figure 9.29: Register your Twitter account.](../../images/social-network-accounts.png)

It's important to note that your tweets are displayed *only* in an Activities 
portlet. If you want your tweets to show up on a page, there must be an 
Activities portlet on it. Also, you need to wait a few minutes after registering 
your Twitter username for your tweets to show up. Keep in mind that the 
Activities portlet shows your tweets in order according to the date and time 
that you originally made them. Therefore, if you haven't tweeted in a while, 
then your tweets might be pushed off the Activities feed by more recent 
activity. You can change the number of items shown in the Activities feed in the 
portlet's Configuration.

![Figure 9.30: Your tweets are displayed in an Activities portlet.](../../images/activities-feed-tweets.png)

## Summary [](id=summary-liferay-portal-6-2-user-guide-09-en)

<!-- | TODO: This is too thin. Please use the headings to summarize what the
chapter was about. | -->

Websites like Facebook and Twitter have attracted hundreds of millions of users
by simply giving users a way to connect and communicate with each other. With
Liferay, you have the power to either build a portal around social features or
enhance a portal built around content and collaboration by providing users with
the tools to connect and interact.

To get started, you can use a selection of portlets designed to make users'
personal public pages a place where they can interact with each other by
learning about other users and communicate using a simple messaging system.
Using the now ubiquitous concept of "friends," users can also form a long term
connection with other users they frequently work with or with whom they share
similar interests.

Outside of users' personal pages, you have a variety of portlets, like the
activity portlets, which are designed to help users identify other users that
might be working on similar projects, and keep track of what's going on around
the portal. You can even use the Social Activity feature to give credit where
credit is due and recognize the users who contribute the most.

Reaching even further out, Liferay provides integration with other sites and
services that enable you to connect with users outside of your portal, either by
pulling content from other websites using OpenSocial integration, or by pushing
content on your portal out to a broader audience using Facebook integration.
We've outlined the tools you have available, now it's up to you to leverage
Liferay's Social Networking features in the way that best fits your portal.
