# Leveraging Social Applications, Activities Tracking, and User Connections [](id=leveraging-social-applications-activities-tracking-and-user-connections)

Liferay has many apps available for social interaction and collaboration.
Some of these apps are designed to help you work together as a team, while
others are designed to foster social interactions between team members at your
organization.

Some of the social networking apps should be used on your site's public
pages, while others should be used as part of a user's personal site. As you
might expect, the apps intended for a personal page foster simple social
interactions, while the ones that can be placed on any site help teams interact
and improve productivity.

Unless otherwise noted, these apps have minimal configuration options. Some of
them provide additional options for customizing feed lengths or display styles.
Additional styling changes can be made through custom CSS.

## Liferay's Social Tools in Action [](id=liferays-social-tools-in-action)

To get started with Liferay's social features, you first want to add social apps
to your users' public personal pages. You can set things up any way you want,
but for simplicity's sake, this example shows something that's fairly similar to
the original Facebook layout. You'll give your users a way to send and receive
connection request using their Profile and Dashboard pages. 

### Setting up Users' Personal Pages [](id=setting-up-users-personal-pages)

Before you start adding apps to pages, you should configure Liferay so that
everyone (or some subset of everyone) has the same social features. You can do
this in two ways: 

**User Groups:** You can create a user group site by placing users into a group.
The pages and apps defined by the user group site are copied to members'
personal sites. This lets you control whether users can modify pages, and you can
push changes out to users in the future. Once the site template is assigned to a
user group, you can make it so all users are members of this group. The
advantage of this is that it can be managed entirely through the interface, and
it's easy to configure. If you base your user group's site on a template, you
can use the *Enable propagation of changes from the site template* option to
manage all user pages by changing the template. This is the recommended way to
manage personal pages across the portal. 

**Portal Properties Configuration:** The legacy way to do this is with the
configuration file. You can specify a default layout and applications for
personal pages in your `portal-ext.properties` file. Note that this method
applies changes to all users' personal sites. It does not, however, provide as
much maintainability or as many customization options as user group sites does.
User group sites allow you to choose what's modifiable by the user.  For
more information on the `portal-ext.properties` method, search for *Default User
Private Layouts* and *Default User Public Layouts* in the [properties documentation](http://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html).

Because it's the recommended method, use the user group method to create the
layouts. As an administrator, go to the Control Panel and select
*Site Templates* from under the *Sites* section. Click *Add* (![Add](../../../images/icon-add.png)) 
and fill out the form. Call your new site template *Social Layout Public*. Click *Save*.

![Figure 1: You can give your site template a custom name and description and also specify several configuration settings..](../../../images/social-networking-site-template.png)

+$$$

Tip: Disabling *Allow Site Administrators to
Modify the Pages Associated with this Site Template* only prevents users from
modifying the specific pages contained in the template. It does not keep a user
from adding or modifying additional pages.

$$$

Now repeat the process and create a site template called *Social Layout
Private*. 

You need to give your users a way to request connections and also to respond to
connection requests. To do that, you'll use the Requests application and the
Summary application. The Requests application goes on users' dashboard pages, as
they are private pages just for that user. The Summary application goes on
users' profile pages which are public. This way, users can visit the profile
and request a connection. 

Once you've created the templates, click the *Go to Other Site* button. Click
the link for the Social Layout Public site. Now you want to change the name of
the page from the default to *Welcome*. Under Navigation &rarr; Pages, edit
the default page (labeled *Home*) and change its name to *Welcome*. Click
*Save*.  Now you'll add an application to the page. Click the *Go to Site* link
in the menu and then click the *Add*
button (![Add](../../../images/icon-add-app.png)). Under *Applications* &rarr;
*Social*, drag the *Summary* application to the column on the left. 

Awesome! You've now set up a site template that contains the Summary
application for users' public profiles. Now you need to do the same thing for
the Requests application and users' private dashboards. Click the *Go to Other
Site* button, but this time click the link for the Social Layout Private site.
As you did before, change the name of the default page from *home* to *Welcome*.
Then click the *Go to Site* link, click the *Add* button, and under
*Applications* &rarr; *Social*, drag the Requests application and drop it into
the leftmost column. 

You're almost there. Click *Control Panel* and select *User Groups* from the
*Users* section. Once there, click *Add* and name the group *Social Users*. When
creating a user group, you can select site templates to apply to those users'
profiles and dashboards. For *My Profile*, select *Social Layout Public*. For
*My Dashboard*, select *Social Layout Private*. Click *Save*. 

Now go to *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings* and
select the *Users* section. Go to the *Default User Associations* tab and enter
*Social Users* in the User Groups section. Now all users on the portal get a
Social Profile page. 

Awesome! You've now enabled your users to make social connections. Now the
question is, how do we encourage users to visit each others' fancy new profile
pages?

### Connecting Users Through Collaboration [](id=connecting-users-through-collaboration)

There are many ways that social networks connect users. These generally involve
some kind of mutual interest or experience. On a site like Facebook, you can
connect with people from school, from work or from other personal connections.
On a music based networking site like Last.fm, you can connect with people who
have similar tastes to yours. With Liferay's social networking, collaboration is
the key to connection. 

The Site Members Directory can provide a simple way for users to connect. If you
have a site dedicated to Lunar Resort astronauts, you can place a Site Members
Directory on that site. Because it lists all the users that have joined that
site, users can connect by sending requests to other users on that list. This
isn't the worst way to get users connected but it probably won't be very
effective. Why not? Well, other than sharing some very basic common interests,
users don't interact this way.

The Activities application provides a similar but more effective means of
connection. Because it shows a list of what other users are doing, this
application helps users discover who is among the most active across the site or
the instance, and thus who might be a good connection. This application is
covered below.

Probably the most effective way users can connect is by interacting with other
users. Every application in the Collaboration category provides information on
who is contributing, regardless of how. You can see who is creating a thread in
a message board, editing a wiki article, blogging or creating a calendar event.
Users can use these to connect based on content--if I find your blog
interesting, or if you answer my question on the message board, we can use that
as a point to connect as friends to further our interactions. This way, instead
of our connection being forced or arbitrary, we've connected based on our direct
interaction and share a common interest--just like people did before they had
the Internet.


## Using the Core Liferay Social Apps [](id=using-the-core-liferay-social-apps)

Out-of-the box, Liferay provides seven social apps:

- Activities
- Group Statistics
- Meetups 
- Requests
- Summary
- User Statistics
- Wall

These apps provide a default implementation of Liferay's social API. You may use
them out of the box, but they really only scratch the surface of the platform's
capability. It's also possible to develop your own implementation of Liferay's
social API to use different social relationships. Please refer to the [Liferay
Developer Tutorials](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-development) 
or the [Javadocs](http://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/social/kernel/service/package-summary.html)
for information about Liferay's social API. 

Probably the core Social application is Activities. It displays information
about user activity on the site where you added the app. User activities tracked
include updates to the Documents and Media library, blog posts, message boards
posts, wiki pages, and bookmarks.  Liferay also tracks information about web
content but only displays this information if the logged-in user is a site
administrator. This application provides a summary of recent site activity. You
can use it on a site's public or private pages to show what site members have
been up to or you can use it on the public or private pages of a user's personal
site. When added to a personal site, the Activities portlet just shows the
activities of a single user.

![Figure 2: The Activities app shows information about asset-related user activity in the current site. It only displays information about web content if the current user is a site administrator.](../../../images/social-activities.png)

Note that the app provides links to the assets listed in the feed.  The links to
the assets won't work, however, unless there's a way to display the assets on
the page. For example, suppose that the user Joe Bloggs uploaded a document
called *Lunar Resort happenings for August* to a site. An Activites app on that
site would show a link to the *Lunar Resort happenings for August* document.

In addition to the Activities application, there are several other social
applications you can use. 

![Figure 3: Meetups allow users to schedule meetings and hangouts.](../../../images/social-meetups.png)

The Meetups application is a tool for creating casual meetings for users of your
site. Anyone can create a "meetup" and give it a title, description,
date/time, maximum number of attendees, price, and provide an image. Meetups are
displayed in the application for anyone to view. Users can register for the
meetup, which lets the organizer keep track of who's coming. 

The options for creating a meetup are essentially the same as those for creating
a calendar event.

The Wall application provides a place for users to leave messages on other users'
profiles. The messages can only be plain text as no formatting or HTML is
supported. Once a post is added to a user's wall, that user can delete it
or respond to it with a quick link to post on the original poster's wall.

"Friend" is only the default social relationship as implemented by Liferay's
social portlets. You can design things so that users are automatically connected
through Site and Organization membership. And there are many other relationship
types beyond Friend: your developers can take advantage of these by using
Liferay's social API. 

Now that you've got all these social applications running on your system, you
might wonder: how can I measure social interaction? How do I identify the best
contributors to my site? Liferay has an answer: social activity measurements.
