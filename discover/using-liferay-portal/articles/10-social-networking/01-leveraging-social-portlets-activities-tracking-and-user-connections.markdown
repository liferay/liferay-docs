# Leveraging Social Portlets, Activities Tracking, and User Connections [](id=leveraging-social-portlets-activities-tracking-and-use)

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

## Using the Core Liferay Social Portlets 

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

## Installing the Social Networking Portlets 

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

## Using Social Networking on Public Pages 

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

## Using Social Networking on Personal Pages 

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

## Liferay's Social Tools in Action 

To get started with Liferay's social features, let's set up the public pages of
our users' personal sites to include social apps. Because of Liferay's flexible
page layout options, we have a large number of options for how to set the pages
up. For simplicity's sake, we'll make something that's fairly similar to the
original Facebook layout.

### Setting up Users' Personal Pages 

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

+$$$

Tip: Unchecking *Allow Site Administrators to
Modify the Pages Associated with this Site Template* only prevents users from
modifying the specific pages contained in the template but does not disable a
user's ability to add or modify additional pages.

$$$

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

### Connecting Users Through Collaboration 

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
