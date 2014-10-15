# Leveraging Social Portlets, Activities Tracking and User Connections [](id=leveraging-social-portlets-activities-tracking-and-user-connections)

Liferay has many portlets available for social interaction and collaboration.
Some of these portlets are designed to help you work together as a team, while
others are designed to foster social interactions between team members at your
organization.

Some of the social portlets should be used on the public pages of your portal,
while others should be used as part of a user's personal site. As you might
guess, the portlets for personal page use are more focused on simple social
interactions, while the ones which can be placed on any site help improve
productivity.

Unless otherwise noted, these portlets are all provided with minimal
configuration options. Most of them have two configuration options--the option
to change permissions for the portlet view and sharing options for connecting
the portlet to other web sites. They do not have any way to change options like
feed length or display styles. Some styling changes, however, can be made
through custom CSS.

## Installing the Social Portlets [](id=installing-the-social-portlets)

The social portlets are all included with the Liferay Community Edition
distribution, but need to be installed separately for Enterprise Edition. If
you're using Liferay Enterprise Edition, or had previously removed the social
portlets from Community Edition, you can use Liferay's plugin installer to
easily add social features to your portal.

If you're logged in as an administrator, go to the control panel and click on
*Plugins Installation* in the *Server* section. From here, click on *Install
More Portlets* and search for *Social Networking*. Once the results come up,
select the latest version of the Social Networking Portlet and click *Install*.
Once the install process finishes, you can start using the social networking
portlets. 

## Using Social Networking on Public Pages [](id=using-social-networking-on-public-pages)

There are several social portlets that are designed for use on public portal
pages. The goal of these is to use social connections to help a group work
together more closely. These include the **Members**, **Meetups**, **Summary**,
and **Activities** portlets.

![Figure 8.2: The Members
Portlet](../../images/XX-social-networking-members-portlet.png)

The Members portlet is a simple list of all the current site's members. The only
configuration options you have are permissions, which are the same for every
portlet. For example, you might change the permissions so only members of the
current site can view the portlet. 

![Figure 8.3: The Meetups
Portlet](../../images/XX-social-networking-meetups.png)

The Meetups portlet is a tool for creating casual meetings for users of your
portal. Anyone can create a "meetup" and give it a title, description,
date/time, maximum number of attendees, price and provide an image. Any meetups
that are created are displayed in the portlet for anyone to view. Users can
register for the meetup, which lets the organizer keep track of who's coming. 

The options for creating a meetup are essentially the same as those for creating
a calendar event and the Meetups portlet shares some functionality with the
Calendar. For more information on the Calendar portlet and configuring events,
see chapter 7.

![Figure 8.4: The Activities
Portlet](../../images/XX-social-networking-activities.png)

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

![Figure 8.5: The Map Portlet](../../images/map-portlet.png)

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
[http://code.google.com/apis/maps/documentation/javascript/v2/introduction.html#Obtaining_Key](http://code.google.com/apis/maps/documentation/javascript/v2/introduction.html#Obtaining_Key)
to learn how to obtain a valid Google API key. To configure the Map portlet
using the GeoLite City database, use the following steps:

1. Install the Social Networking plugin, if you haven't already done so.

2. Install the IP Geocoder portlet. (Both the Social Networking and IP Geocoder
   apps can be installed from Martketplace.)

3. Shut down your application server.

4. Download the Geo Lite City database from
   [http://www.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz](http://www.maxmind.com/download/geoip/database/GeoLiteCity.dat.gz).

5. Unzip the `.dat` file to your desired storage location on your server.

6. Create a `portlet-ext.properties` file in the
   `/{ROOT}/webapps/ip-geocoder-portlet/WEB-INF/classes/` directory of your
   Liferay installation.

7. Add the property `maxmind.database.file={GeoIP Lite City database .dat file
   path}` to this file.

8. Create a `portlet-ext.properties` file in the
   `/{ROOT}/webapps/social-networking-portlet/WEB-INF/classes/` directory of
   your Liferay installation.

9. Add the property `map.google.maps.api.key={Your API Key}` to this file. If
   you haven't done so already, you'll need to generate a [Google Maps API
   Key](http://code.google.com/apis/maps/signup.html).

10. Restart your application server.

11. Enjoy the Maps portlet!

Next, let's look at the social networking portlets designed for use on personal
pages.

## Using Social Networking on Personal Pages [](id=using-social-networking-on-personal-pages)

In addition to the portlets available for general use, there are a handful that
can only be used on personal pages. These include the Summary, Wall, Friends,
and Friends' Activities portlets. These portlets can be used to create profile
pages similar to Facebook's or Google+'s. 

![Figure 8.6: Social Networking Portlets in a Facebook-like
Layout](../../images/XX-social-networking-personal-portlets.png)

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

## Liferay's Social Tools in Action [](id=liferays-social-tools-in-action)

To get started with Liferay's social features, let's set up the public pages of
our users' personal sites to include social apps. Because of Liferay's flexible
page layout options, we have a large number of options for how to set the pages
up. For simplicity's sake, we'll make something that's fairly similar to the
original Facebook layout.

### Setting Up Users' Personal Pages [](id=setting-up-users-personal-pages)

Before we start adding portlets to pages, we should configure Liferay so that
everyone (or some subset of everyone) has the same social features. We have two
ways to do this with advantages and disadvantages to each.

**User Groups:** Placing users into a group enables you to create a user group
site for them. The pages and portlets defined by the user group site are copied
to members' personal sites. With the user group site, you can control whether
users can modify pages and you can push changes out to users in the future. Once
the site template is assigned to a user group, you can set the *Default User
Associations* to have all users be the member of a particular group in *Portal
Settings* in the control panel. The advantage of this is that it can be managed
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
the layouts. As an administrator, go to the control panel and select *Site
Templates* from under the *Portal* section. Click *Add* and fill out the form.
We'll call our new site template *Social Layout*. Click *Save*.

![Figure 8.7: Creating the Site
Template](../../images/social-networking-site-template.png)

+$$$

**Tip:** Unchecking *Allow Site Administrators to Modify the Pages Associated 
with this Site Template* only prevents users from modifying the specific pages 
contained in the template but does not disable a user's ability to add or modify 
additional pages.

$$$

Once you've created the template, choose *Actions &rarr; Manage Pages* for
*Social Layout* from the Site Templates page, then click *View Pages*. Let's
change the name of the page from the default to *My Profile* and add some
portlets to the page. In the screenshot below, we removed the borders to make
the page look more integrated, and also used Nested Portlets to make the layout
more interesting.

![Figure 8.8: Social Profile Site
Template](../../images/social-networking-profile-template.png)

Back in the control panel, select *Users and Organizations* from the *Portal*
section. Once there click *Add &rarr; User Group*. Name the group *Social
Users*. When creating a user group, you have the option to set a user group
site; use this option and select the Social Layout template for your Public
Pages.

Now go to *Portal Settings* and select *Users* from the submenu. From the Users
page, go to the *Default User Associations* tab and enter *Social Users* in the
User Groups section. Now all users on the portal get a Social Profile page. Now
the question is, how do we encourage users to visit each others fancy new
profile pages?

### Connecting Users Through Collaboration [](id=connecting-users-through-collaboration)

There are many ways that social networks connect users. These generally involve
some kind of mutual interest or experience. On a site like Facebook, you can
connect with people from school, from work or from other personal connections.
On a music based networking site like Last.fm, you can connect with people who
have similar tastes to yours. With Liferay's social networking collaboration is
the key to connection. 

Using our example site of nosester.com, we can take a closer look at ways users
can be connected through hierarchies and ways they can connect to each other.
We'll look at a handful of portlets, both those designed specifically for
connecting users and those that can create connections as a side-effect of just
getting work done.

The Directory portlet can provide a simple way for users to connect. If we have
a site dedicated to people with big noses, we can place a directory portlet on
that site, listing all the users that have joined that site. Users can connect
by sending requests to other users on that list. This isn't the worst way to get
users connected but it probably won't be very effective. Why not? Well, other
than sharing some very basic common interests, we haven't really had any
interactions.

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
wonder: how can I measure social interaction? How do I make identify the best
contributors to my site? Liferay has an answer: social activity measurements.
