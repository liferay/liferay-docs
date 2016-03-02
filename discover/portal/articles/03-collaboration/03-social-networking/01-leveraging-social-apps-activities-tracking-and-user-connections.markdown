# Leveraging Social Applications, Activities Tracking, and User Connections 

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

## Using the Core Liferay Social Apps 

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
Developer Tutorials](https://dev.liferay.com/develop/tutorials/7-0) or
the
[Javadocs](http://http://docs.liferay.com/portal/7.0/javadocs/portal-service/com/liferay/social/kernel/service/package-summary.html)
for information about Liferay's social API. 

## Liferay's Social Tools in Action [](id=liferays-social-tools-in-action)

To get started with Liferay's social features, you first want to add social apps
to your users' public personal pages. You can set things up any way you want,
but for simplicity's sake, this example shows something that's fairly similar to
the original Facebook layout.

### Setting up Users' Personal Pages [](id=setting-up-users-personal-pages)

Before you start adding apps to pages, you should configure Liferay so that
everyone (or some subset of everyone) has the same social features. You can do
this in two ways: 

**User Groups:** You can create a user group site by placing users into a group.
The pages and apps defined by the user group site are copied to members'
personal sites. This lets you control whether users can modify pages, and you can
push changes out to users in the future. Once the site template is assigned to a
user group, you can set the *Default User Associations* to have all users be 
members of a particular group in *Configuration* &rarr; *Instance Settings* in
the Control Panel. The advantage of this is that it can be managed entirely
through the GUI and it's easy to configure. If you base your user group's site
on a template, you can use the *Enable propagation of changes from the site
template* option to manage all user pages simply by changing the template. This
is the recommended way to manage personal pages across the portal. 

**Portal Properties Configuration:** The legacy way to do this is with the
configuration file. You can specify a default layout and portlets for personal
pages in your `portal-ext.properties` file. Note that this method applies
changes to all users' personal sites. However, it does not provide as much
maintainability or as many customization options as does using user group sites.
User group sites allow you to choose what's modifiable by the user.  For more
information on the `portal-ext.properties` method, search for *Default User
Private Layouts* and *Default User Public Layouts* in the [properties documentation](http://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html).

Because it's the recommended method, you want to use the user group method to
create the layouts. As an administrator, go to the Control Panel and select
*Site Templates* from under the *Sites* section. Click *Add* (![Add](../../../images/icon-add.png)) 
and fill out the form. Call your new site template *Social Layout*. Click *Save*.

![Figure x: You can give your site template a custom name and description and also specify several configuration settings..](../../../images/social-networking-site-template.png)

+$$$

Tip: Disabling *Allow Site Administrators to
Modify the Pages Associated with this Site Template* only prevents users from
modifying the specific pages contained in the template. It does not keep a user
from adding or modifying additional pages.

$$$

Once you've created the template, click the *Go to Other Site* button. Now you
want to change the name of the page from the default to *My Profile* and add
some portlets to the page. Back in the Control Panel, select *User Groups* from
the *Users* section. Once there, click *Add* and name the group *Social Users*.
When creating a user group, you have the option to set a user group site; use
this option and select the Social Layout template for your Public Pages.

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
