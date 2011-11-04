# Social Networking with Liferay

Since MySpace rose to popularity in 2006, certain concepts such as "Friend" and later "Like"--previously reserved for direct human interaction--have taken on new meaning in an always-online, information driven culture. Initially, these concepts proved to be highly successful for casual web sites but they didn't take to the business world as quickly. Slowly, more and more organizations are realizing the importance of leveraging social interactions for more than just recreation. Liferay is a great platform for business web sites, casual web sites, and everything in between, and its social features reflect this. 

Liferay has a constantly improving set of social features which enable you to both encourage social interactions on your own portal, and leverage the power and popularity of other social networks. In this chapter, we'll discuss:

-	General Use Social Portlets
-	Social Portlets for Personal Pages
-	Configuring Personal Pages for Social Networking
-	How to Connect Users with Social Features
-	Social Equity
-	Integrating Liferay with other Social Sites

## Liferay's Social Portlets
	
Liferay has many portlets available for social interaction and collaboration. Some of these portlets are designed to help you work together as a team, while others are simply designed to foster social interactions between team members at your organization.

Some of the social portlets are designed for use on the public pages of your portal, while others are designed to be used as part of a user's personal site. As you might guess, the portlets which are desigend for personal page use tend to be more focused on simple social interactions, while the ones which can be placed on any site are more beneficial for improving productivity.

Unless otherwise noted, these portlets are all provided with minimal configuration options. They generally have two configuration options - the option to change permissions for the portlet view and sharing options for connecting the portlet to other websites. They do not have any way to change options like feed length or display styles. Some styling changes, however, could be done through custom CSS.

### General Use Portlets

There are several social portlets that are designed for use on regular portal pages. The goal of these is to use social connections to help a group work together more closely. These include the **Members** portlet, **Meetups**, **Top Users**, **Summary**, and **Activities**.

#### Members

This portlet is simply a list of all members of the current site. The only configuration options you have available are changing it's permission, so that, for example, someone who is not a member of the current site would be unable to view to portlet or any of the user information it contains.

![Figure 9.x: The Members Portlet](../../images/XX-social-networking-members-portlet.png)

#### Meetups

The Meetups portlet is designed to be a tool for creating casual meetings for users of your portal. Anyone can create a "meetup" and give it a title, description, date/time, maximum number of attendees, price, and provide an image. Any meetups that are created will be displayed in the portlet for anyone to view. Once a meetup is created, users will be able to register, to help the organizer keep track of who's coming, and ensure that there aren't too many people coming.

The options for creating a meetup are essentially the same as those for creating a calendar event, and the Meetups portlet shared some general functionality with the Calendar. For more information on the Calendar portlet and configuring events, see chapter 4.

![Figure 9.x: The Meetups Portlet](../../images/XX-social-networking-meetups.png)

#### Activities

The Activities portlet comes in two varieties: the standard Activities portlet and the Members' Activities portlet. The basic function of the portlet is the same--they both display a feed of the asset creation and management activities of users on the portal. The difference is that Activities will display what's going on across the entire portal, while Members' Activities will only display what members of the current site have been doing.

![Figure 9.x: The Activitiess Portlet](../../images/XX-social-networking-activities.png)

#### Top Users

Top Users is a portlet which integrates with Liferay's Social Equity system. It calculates a score based on the contributions that the user makes to the portal. These contributions can be made through collaboration portlets usch as the Blog or Wiki, or Message Board. Each user name is displayed with their picture, score, and rank.

Scores are updated in real time, while ranks are only updated daily, so it's possible that a user with a higher current score might be displayed with a lower rank until the ranks refresh for the day. 

#### Map

The map is a tool which enables you to view the locations of other users on the portal, both locally and internationally. In order to configure the map, you will need to install the IP Geolocator portlet (available from Liferay's community repository) and configure Maxmind GeoIP or GeoLite on your server. For more information on configuring geolocation services, visit the MaxMind support page at [http://www.maxmind.com/app/installation?city=1](http://www.maxmind.com/app/installation?city=1)

![Figure 9.x: The Map Portlet](../../images/xx-social-networking-map.png)

### Personal Portlets

In addition to the portlets available for general use, there are a handful that can only be used on personal pages. These portlets can be used to create a profile page, similar to Facebook or Google+. 

![Figure 9.x: Social Networking Portlets in a Facebook-like Layout](../../images/XX-social-networking-personal-portlets.png)


#### Summary

The Summary portlet provides a quick overview of a user. When posted on one of a user's personal pages, it will display the name, profile picture, and job title of the user. The user can also add some additional personal information by clicking on *Edit* in the portlet, and filling in information in the *About Me* section.
This portlet is also very important to any socal site, because it will have the *Friend Request* button, which enables users to iniate social interactions. You will also see that many of the other portlets are based around this concept of "Friends" as well.

#### Wall

The Wall portlet provides a place for users to leave messages on other users' profiles. The messages can only be plain text, and no formatting or HTML is supported. Once a post is added to their wall, a user has the ability delete it or respond to it with a quick link to post on the original poster's wall.

#### Friends and Friends Activities

The Friends portlet simply shows a list of all of the user's friends with links to their profiles. The Friends Activities portlet will show an overview of actions done by a user's friends on the portal or in various sites.

### Liferay's Social Tools in Action

Now that we've taken a good look at all of the tools that Liferay provides for creating a social experience on your portal, let's take a look out how we can use all of these tools to really leverage the power of social interactions.

#### Configuring User Pages

To get started with Liferay's social features, let's set up our users' personal public pages to include social apps. Because of Liferay's flexible page layout options, we have a large number of options for how to set the pages up. For simplicity's sake, we'll make something that's fairly similar to the original Facebook layout.

##### Personal Page Configuration Options

Before we actually do the layout, we need to configure Liferay so that everyone (or some subset of everyone) has the same social features available to them. We have a couple of options on how to do this, with some advantages and disadvantages to each.

- User Groups: Placing users into a group enables you to create a template for their personal public and private pages. Through *Portal Settings* in the control panel, we can set the *Default User Associations* to have all users be the member of a particular group. The advantage of this is that it can be managed entirely through the GUI, and it's fairly easy to configure. The disadvantage is that without further configuration, users would be able to modify the layouts, and potentially remove social features from their pages.

- Portal Properties Configuration: You can specify a default layout and portlets for personal pages in your `portal-ext.properties` file, either by leveraging an option to specify via plain text, or by pointing to a LAR file which contains the desired layout. The advantage of this method is that it will apply to all users of the portal. The disadvantage is that you will potentially need to do some trial and error editing to get the layout correct. For more information on this method, see *Default User Private Layouts* and *Default User Public Layouts* in **Chapter 14: Configuring Liferay's Properties**.

Whichever method you decide to use, if you don't want users to be able to modify their own pages, you will need to set the layouts to be static by adding the following line to your `portal-ext.properties` file:

    layout.user.public.layouts.modifiable=false
    
For our example, we'll use the user group method to create the layouts. As an administrator, go to the control panel, and select *Site Templates* from under the *Portal* section. Click *Add* and fill out the form, unchecking the boxes for *Allow Modifications* and *Allow Layout Additions*, and click *Save*.

![Figure 9.x: Creating the Site Template](../../images/social-networking-site-template.png)

Note: Setting your portal properties to not allow modifications to personal page layouts, will disable those modifications for all pages for all users. Disabling Layout Additions and Modifications in the Site Template will only disable them for the specific page, but will not disable a user's ability to add more pages, or modify additional pages.

Once you've created the template, choose *Actions &rarr; Manage Pages* for *Social Layout* from the Site Templates page, then click *View Pages*. Let's change the name of the page from the default to *My Profile* and add some portlets to the page. In the screenshot below, we removed the borders to make the page look more integrated, and also used Nested Portlets to make the layout more interesting.

![Figure 9.x: Social Profile Site Template](../../images/social-networking-profile-template.png)

Back in the control panel select *Users and Organizations* from the *Portal* section. Once there, click on *Add &rarr; User Group*. Name the group *Social Users*. When creating a user group, you have the option to set a **Personal Site Template**; use this option and select the Social Layout for your Public Pages.

Now, go to *Portal Settings* and select *Users* from the submenu. From the Users page, go to the *Default User Associations* tab and enter *Social Users* in the User Groups section. Now all users on the portal will get a Social Profile page. Now the question is, how do we encourage one user to visit another user's fancy new profile page?

##### Connecting Users Through Collaboration

There are many ways that social networks connect users. These generally involve some kind of mutual interest or experience. On a site like Facebook, you can connect with people that you went to school with, worked with, or that you have some other personal connection with. On a music based networking site like Last.fm, you can connect with people who like the same bands that you do, or have similar tastes. With Liferay's Social Networking, collaboration is the key to connection. 

Using to our example site of **nosester.com** we can take a closer look at ways that users can be connected through hierarchies, and ways that they can connect to each other. We'll take a look at a handful of portlets, both those that 

The Directory portlet can provide a simple way that users can connect. If we have a Site dedicated to people with big noses, we can place a directory portlet on that site, listing all of the users that have joined that particular site, and users can connect simply by requesting other users on that list become their friend. This wouldn't be the worst way to get users connected, but it probably won't be particularly effective. Why not? Well, other than sharing some very basic common interests, we haven't really had any interactions.

The Activities portlet can provide a similar, but more effective means of connection. The activities portlet can give users a good idea of who the active users are across the site or the portal, and thus who might be the best users to connect with. The potential flaw with this type of connection is that users who participate less won't be making new connections and might find little reason to continue using the site, while users who were initiatally participating more may be overwhelmed with the volume of new friends.

Probably the most effective way to connect users is connecting users with other users that they directly interact with. Every portlet in the Collaboration category provides information on the user's contributing, whether by creating a thread in a message board, editing a wiki article, blogging, or creating a calendar event. Users can use these to connect based on content - if I find your blog interesting, or if you answer my question on the message board, we can use that as a point to connect as friends to further our interactions. This way, instead of our connection being forced or abitrary, we've connected based on the fact that we've directly interacted and share a common interest--just like people did before they had the internet.

"Friend" is only the default way to connect users social on Liferay portal. Depending on your design, you can also connect people through Site and Organization membership. You can also configure relationships like "Enemy" --if you block another user, they become your "enemy." Advanced Social Networking features are covered in the Liferay Developer's Guide.

## OpenSocial Integration

OpenSocial is a framework designed for creating applications, called *gadgets* that will function on any social networking site that supports them. There are many existing gadgets available, and anyone can create their own gadgets. With Liferay, you can integrate any OpenSocial gadget into a Liferay page, and easily manage which gadgets are currently in use or available. You can also create your own OpenSocial Gadget using the OpenSocial Gadget Editor.

### Using OpenSocial Gadgets

The OpenSocial portlet is included with Liferay Community Edition, and can be easily installed through the Plugin Manager on Liferay EE. The portlet itself is fairly simple: once you add it to the page you just click on the configuration icon, and provide the URL of the gadget you want to use. The gadget will then display in the portlet container. The portlet options allow you to change the permissions for who can view or edit the portlet, as well as options to share the contents of the portlet, just like you can with any other Liferay portlet.

![Figure 9.x: OpenSocial Gadget displayed on a page](../../images/open-social-gadget.png)

In addition to configuring individual gadgets, you can specficy gadgets that will be listed in the *Add &rarr; More* list from the Control Panel. To do this, simply click on *OpenSocial* in the *Portal* section of the Control Panel, specify a URL for the Gadget, and select the category that it should appear in.

### Creating and Editing OpenSocial Gadgets

OpenSocial gadgets are essentially just XML documents, so as part of Liferay's OpenSocial integration, a gadget editor is included. With the gadget editor, you can edit existing gadgets or create your own from scratch. The editor itself is fairly robust and provides syntax highlighting, a preview function, undo/redo options, and built in tabs for working on multiple gadgets at once. You can also organize and manage them through a simple file manager embedded into the portlet.

Once you have created a gadget using the editor, you can add the gadget to the *Add &rarr; More* menu, or get a URL so that you can post it on any website that support OpenSocial gadgets.

## Social Equity

When you have a lot of user interaction on your web site, it can be helpful to try to separate the signal from the noise. Liferay contains a lot of applications which end users can make use of to communicate with each other and provide information. Some of this information is good and helpful and some of it can be rather unhelpful. In order to better show which users are making real, valuable contributions, Liferay is introducing the new Social Equity system with Liferay 6.

![Figure 4.24: The Social Equity Page of the Control Panel](../../images/05-social-equity.png)

To use Social Equity for your portal, check the Enable Social Equity box. Social Equity enables you to assign values and weight for each contribution that a user makes. Points are given to users for *Information* and *Participation*. This way, a user who writes several blogs and wiki articles can be shown as having a higher rank than a user who has only written comments and message board replies. The purpose of this is to better indicate which users on a web site are knowledgeable contributors, and to reward those users. Social Equity is available for Blogs, Message Boards, Wikis, and Web Content. Each category has a check box labeled "Unique." When this box is checked, users can only gain social equity points one time for an action. For example, if this box were checked for viewing blog entries, users could only gain points one time for viewing a given blog entry. If the box were unchecked, users could view a blog entry multiple times and gain social equity points each time. Each category also contains a rating for *Information Value*, *Information Lifespan*, *Daily Limit*, *Participation Value*, *Participation Lifespan*, and *Daily Limit*.

**Information Value:** indicates how many points a user receives for the information content of a specific action.

**Information Lifespan:** indicates, in number of days, how long it will be before the information points expire. Setting it to `0` means that they will never expire.

**Daily Limit:** is the maximum number of actions of this type that a user will receive information points for in a given day.

**Participation Value:** indicates how many points a user receives for the participating in the content creation of the website.

**Participation Lifespan:** indicates, in number of days, how long it will be before the participation points expire. Setting it to `0` means that they will never expire.

**Daily Limit:** is the maximum number of actions of this type that a user will receive participation points for in a given day.

There are a wide-ranging number of actions that you can provide social credit for. Users can receive credit for everything from viewing content to writing wiki articles. You can easily tweak the numbers in the control panel if it becomes clear that certain activities are weighted too high or too low.

Social Equity can be an invaluable tool for portals that are heavily driven by community-created conteny. It allows you to easily recognize users who are major contributors and it indicates to new users whose advice will be most trustworthy. Social Equity is easy to set up and can be configured differently for each site, increasing the flexibility of your portal.

## Other Social Integrations

In addition to the social interactions that you can create on your portal, Liferay has points of integration with some other popular social networks that will enable you to leverage their power and popularity for the content on your portal.
	
### Facebook Integration
	
Facebook is currently the number one social network in the world with somewhere in the neighborhood of 750 million active users. If you're trying to build a community on your portal, you don't want to neglect a bridge to nearly a billion possible users. With that in mind, Liferay provides a couple easy ways that you can integrate your portal with Facebook.

#### Facebook Sign On

Like many web sites that you may visit, any portal running on Liferay can be setup to user Facebook for sign in. This makes it easier for users to sign in to your site, since they won't need to remember another user name and password, and it makes it easier for you to integrate useful features like having certain activities done on your portal display on Facebook. For more information on setting up Facebook sign on, [[NEED CHAPTER FOR THAT]].

#### Using Your Portlets as Facebook Applications

You can add any Liferay portlet as an application on Facebook. To do this, you must first get a developer key. A link for doing this is provided to you in the Facebook tab. You will have to create the application on Facebook and get the key and canvas page URL from Facebook. Once you have done this, you can copy and paste their values into the Facebook tab. Your portlet will now be available on Facebook.

This integration enables you to make things like Message Boards, Calendars, Wikis, and other content on your portal available to a much larger audience (unless you already have in billion users on your site, in which case, kudos to you), and, if you have this coupled with Facebook Sign On, moving from the content on Facebook to your portal can be almost seamless.

## Summary

In this chapter, we explored a variety of ways to use Liferay's social features. As you have seen, you can use Liferay to create a website that is totally focused on connecting people through social interactions, or one that simply uses social interactions as part of a larger collaboration based website. Either way you do it, you can also use Liferay's features to integrate your portal with other websites like Facebook, OpenSocial, and iGoogle.

The large number of social options provided, along with features like Social Equity, means that just about any website can leverage Liferay portal to improve your users' experience and effectiveness.


