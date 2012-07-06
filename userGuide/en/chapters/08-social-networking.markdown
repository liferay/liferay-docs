# Social Networking [](id=social-networking-with-liferay)

Since the first social networks rose to popularity, concepts such as *Friend* and later *Like*--previously reserved for direct human interaction--have taken on new meaning in an always-online, information driven culture. It could be argued that social networks have transformed the way people interact with their friends, relatives and colleagues. Friends, connections, followers, circles and lists have enabled people to connect and stay connected in ways they'd never been able to before. Initially, these concepts proved to be highly successful for casual web sites but they didn't take to the business world as quickly. But many organizations are now realizing the importance of leveraging social interactions for more than just recreation. Liferay's robust social features make it a great platform for business web sites, casual web sites and everything in between.

Liferay has a constantly improving set of social features which enable you to encourage social interactions on your own portal and to leverage the power and popularity of other social networks. In this chapter, we'll discuss:

-	General use social portlets
-	Social portlets for personal pages
-	Configuring personal pages for social networking
-	How to connect users using Liferay social relations
-	Social equity
-	Integrating Liferay with other social sites

When we're finished, you'll have a good idea how you can use Liferay to power your social network.

<!--
This says, "Leveraging ," but what is it leveraging? Social portlets?
Activities tracking seems to be in the next section, Measuring Social Activity

 i. Leveraging , Activities Tracking and User Connections (head ref) [](id=lp-6-1-ugen08-i-leveraging--activities-tracking-and-user-connections-head-0)
-->
## Leveraging Social Portlets, Activities Tracking and User Connections [](id=liferay-s-social-portlets)
	
Liferay has many portlets available for social interaction and collaboration. Some of these portlets are designed to help you work together as a team, while others are designed to foster social interactions between team members at your organization.

Some of the social portlets should be used on the public pages of your portal, while others should be used as part of a user's personal site. As you might guess, the portlets for personal page use are more focused on simple social interactions, while the ones which can be placed on any site help improve productivity.

Unless otherwise noted, these portlets are all provided with minimal configuration options. Most of them have two configuration options--the option to change permissions for the portlet view and sharing options for connecting the portlet to other web sites. They do not have any way to change options like feed length or display styles. Some styling changes, however, can be made through custom CSS.

### Installing the social portlets [](id=lp-6-1-ugen08-installing-the-social-portlets-0)

The social portlets are all included with the Liferay Community Edition distribution, but need to be installed separately for Enterprise Edition. If you're using Liferay Enterprise Edition, or had previously removed the social portlets from Community Edition, you can use Liferay's plugin installer to easily add social features to your portal.

If you're logged in as an adminstrator, go to the control panel and click on *Plugins Installation* in the *Server* section. From here, click on *Install More Portlets* and search for *Social Networking*. Once the results come up, select the latest version of the Social Networking Portlet and click *Install*. Once the install process finishes, you can start using the social networking portlets. 

### Using social networking on public pages [](id=lp-6-1-ugen08-using-social-networking-on-public-pages-0)

There are several social portlets that are designed for use on regular portal pages. The goal of these is to use social connections to help a group work together more closely. These include the **Members** portlet, **Meetups**, **Top Users**, **Summary** and **Activities**.

![Figure 8.1: The Members Portlet](../../images/XX-social-networking-members-portlet.png)

The Members portlet is a simple list of all the current site's members. The only configuration options you have are permissions, which are the same for every portlet. For example, you might change the permissions so only members of the current site can view the portlet. 

![Figure 8.2: The Meetups Portlet](../../images/XX-social-networking-meetups.png)

The Meetups portlet is a tool for creating casual meetings for users of your portal. Anyone can create a "meetup" and give it a title, description, date/time, maximum number of attendees, price and provide an image. Any meetups that are created are displayed in the portlet for anyone to view. Users can register for the meetup, which lets the organizer keep track of who's coming. 

The options for creating a meetup are essentially the same as those for creating a calendar event and the Meetups portlet shares some functionality with the Calendar. For more information on the Calendar portlet and configuring events, see chapter 7.

![Figure 8.3: The Activities Portlet](../../images/XX-social-networking-activities.png)

The Activities portlet comes in two varieties: the standard Activities portlet and the Members' Activities portlet. The basic function of the portlets are the same--they both display a feed of what users are doing on the portal. The difference is that Activities displays what's going on across the entire portal, while Members' Activities displays only what members of the current site have been doing.

### Using social networking on personal pages [](id=lp-6-1-ugen08-using-social-networking-on-personal-pages-0)

In addition to the portlets available for general use, there are a handful that can be used only on personal pages. These portlets can be used to create a profile page, similar to Facebook or Google+. 

![Figure 8.4: Social Networking Portlets in a Facebook-like Layout](../../images/XX-social-networking-personal-portlets.png)

The Summary portlet provides a quick overview of a user's profile. When posted in a user's personal site, it displays the user's name, profile picture and job title. Users can add additional personal information by clicking on *Edit* in the portlet and filling in information in the *About Me* section.

This portlet is also essential to any social implementation on Liferay, because it has the *Friend Request* button. This enables users to initiate social relationships. Note that this portlet simplifies a much more powerful underlying social networking API that defines many different kinds of relationships, including friends. Your developers can take advantage of this API to create powerful social applications. For more information on this, see [*Liferay in Action*](http://manning.com/sezov) (Manning Publications) or the *Liferay Developer's Guide*. 

The Wall portlet provides a place for users to leave messages on other users' profiles. The messages can only be plain text as no formatting or HTML is supported. Once a post is added to their wall, users have the ability delete it or respond to it with a quick link to post on the original poster's wall.

The Friends portlet shows a list of all the user's friends with links to their profiles. The Friends' Activities portlet shows things a user's friends are doing on the portal.

The map is a tool which enables you to view the locations of other users on the portal, both locally and internationally. In order to configure the map, you need to install the IP Geolocator portlet (available from Liferay's community repository) and configure Maxmind GeoIP or GeoLite on your server. For more information on configuring geolocation services, visit the MaxMind support page at [http://www.maxmind.com/app/installation?city=1](http://www.maxmind.com/app/installation?city=1)

![Figure 8.5: The Map Portlet](../../images/map-portlet.png)

Liferay ships with a nice suite of social networking applications that lets you power your site for social interaction. Now that we've discussed what these portlets do, let's put them all together and make a social web site. 

### Liferay's social tools in action [](id=lp-6-1-ugen08-liferays-social-tools-in-action-0)

To get started with Liferay's social features, let's set up the public pages of our users' personal sites to include social apps. Because of Liferay's flexible page layout options, we have a large number of options for how to set the pages up. For simplicity's sake, we'll make something that's fairly similar to the original Facebook layout.

#### Setting up users' personal pages [](id=lp-6-1-ugen08-setting-up-users-personal-pages-0)

Before we start adding portlets to pages, we should configure Liferay so that everyone (or some subset of everyone) has the same social features. We have two ways to do this with advantages and disadvantages to each.

**User Groups:** Placing users into a group enables you to create a user group site for them. The pages and portlets defined by the user group site are copied to members' personal sites. With the user group site, you can control whether users can modify pages and you can push changes out to users in the future. Once the site template is assigned to a user group, you can set the *Default User Associations* to have all users be the member of a particular group in *Portal Settings* in the control panel. The advantage of this is that it can be managed entirely through the GUI and it's easy to configure. If you base your user group's site on a template, you can use the *Enable propagation of changes from the site template* option to manage all user pages simply by changing the template. This is the recommended way to manage personal pages across the portal. For more information on user group sites, see chapter 15.

**Portal Properties Configuration:** The legacy way to do this is with the configuration file. You can specify a default layout and portlets for personal pages in your `portal-ext.properties` file. If you don't want users to be able to modify their own pages, you should set the layouts to be static by adding the following line to your `portal-ext.properties` file:

    layout.user.public.layouts.modifiable=false
    
Note that this method applies changes to the personal sites of all users of the portal. However, it does not provide as much maintainability or customizability as using user group sites. Using user group sites allows you to choose what's modifiable by the user. For more information on the `portal-ext.properties` method, see *Default User Private Layouts* and *Default User Public Layouts* in chapter 20.
    
Because it's the recommended method, we'll use the user group method to create the layouts. As an administrator, go to the control panel and select *Site Templates* from under the *Portal* section. Click *Add* and fill out the form. We'll call our new site template *Social Layout*. Click *Save*.

![Figure 8.6: Creating the Site Template](../../images/social-networking-site-template.png)

---

![tip](../../images/01-tip.png) Tip: Setting the property which disables modifications to personal page layouts disallows users across the portal from making modifications to their personal sites. Unchecking *Allow Site Administrators to Modify the Pages Associated with this Site Template* will only prevent users from modifying the specific pages contained in the template, but will not disable a user's ability to add or modify additional pages.

---

Once you've created the template, choose *Actions &rarr; Manage Pages* for *Social Layout* from the Site Templates page, then click *View Pages*. Let's change the name of the page from the default to *My Profile* and add some portlets to the page. In the screenshot below, we removed the borders to make the page look more integrated, and also used Nested Portlets to make the layout more interesting.

![Figure 8.7: Social Profile Site Template](../../images/social-networking-profile-template.png)

Back in the control panel, select *Users and Organizations* from the *Portal* section. Once there click *Add &rarr; User Group*. Name the group *Social Users*. When creating a user group, you have the option to set a user group site; use this option and select the Social Layout template for your Public Pages.

Now go to *Portal Settings* and select *Users* from the submenu. From the Users page, go to the *Default User Associations* tab and enter *Social Users* in the User Groups section. Now all users on the portal get a Social Profile page. Now the question is, how do we encourage users to visit each other's fancy new profile pages?

#### Connecting users through collaboration [](id=lp-6-1-ugen08-connecting-users-through-collaboration-0)

There are many ways that social networks connect users. These generally involve some kind of mutual interest or experience. On a site like Facebook, you can connect with people from school, from work or from other personal connections. On a music based networking site like Last.fm, you can connect with people who have similar tastes to yours. With Liferay's social networking collaboration is the key to connection. 

Using our example site of nosester.com, we can take a closer look at ways users can be connected through hierarchies and ways they can connect to each other. We'll look at a handful of portlets, both those designed specifically for connecting users and those that can create connections as a side-effect of just getting work done.

The Directory portlet can provide a simple way for users to connect. If we have a site dedicated to people with big noses, we can place a directory portlet on that site, listing all the users that have joined that site. Users can connect by sending requests to other users on that list. This isn't the worst way to get users connected but it probably won't be very effective. Why not? Well, other than sharing some very basic common interests, we haven't really had any interactions.

The Activities portlet provides a similar but more effective means of connection. Because it shows a list of what other users are doing, this portlet helps users discover who is among the most active across the site or the portal, and thus who might be a good connection.  

Probably the most effective way users can connect is by interacting with other users. Every portlet in the Collaboration category provides information on who is contributing, regardless of how. You can see who is creating a thread in a message board, editing a wiki article, blogging or creating a calendar event. Users can use these to connect based on content--if I find your blog interesting, or if you answer my question on the message board, we can use that as a point to connect as friends to further our interactions. This way, instead of our connection being forced or abitrary, we've connected based on the fact that we've directly interacted and share a common interest--just like people did before they had the internet.

"Friend" is only the default social relationship as implemented by Liferay's social portlets. You can design things so that users are automatically connected through Site and Organization membership. And there are many other relationship types beyond Friend: your developers can take advantage of these by using Liferay's social API. This is covered in *[Liferay in Action](http://www.manning.com/sezov)* and the *Liferay Developer's Guide*. Now that you've got all these social applications running on your system, you might wonder: how can I measure social interaction? How do I make identify the best contributors to my site? Liferay has an answer: social activity measurements.

## Measuring social activity [](id=measuring-social-activity)

<!-- | TODO This is all out of date. It's been reimplemented for Liferay 6.1. Please rewrite. | -->
When you have a lot of user interaction on your web site, it can be helpful to try to separate the signal from the noise. Liferay contains a lot of applications which end users can use to communicate with each other and provide information. Some of this information is good and helpful and some of it can be rather unhelpful. Using Liferay's Social Activity feature will help show which users are making real, valuable contributions.

![Figure 8.8: The Social Equity Page of the Control Panel](../../images/05-social-equity.png)

To activate Social Activity, you'll first need to determine which collaboration applications you want to use Social Activty. There are currently three types of content you can use with Social Activity - Blogs Entries, Message Board Messages, and Wiki Pages. Activation is a la carte - so you can use it on one, two, or all three applications. Social Activity tracks three metrics from within each of these applications two are for the user - *Participation* and *Contribution* - and the other, *Popularity*, is for the asset involved.

Let's activate Social Activity for Blogs Entries. Check the box next to *Blog Entry*. You now have options to set point values and limits on several different actions for blogs. You'll notice each item on the list has dropdowns you can use to set the number of participation and contribution points; popularity points are tied directly to contribution points. In addition to that, you can expand the box by clicking *Limits* in the top right of each list item. You can use this to set a limit on how many times a use can perform this activity with a specific asset and receive the requisite points. For some activities, you can set limits on both participation and contribution points but on new content creation you can only set limits on participation points.

It might not be immediately obvious, but for all actions that do not involve the creation of a new asset, all of the contribution points always go to the original asset creator and all popularity points go to the original asset. That means if *Votes on a Blog* is set to have 1 *Participation* point and 5 *Contribution* points (and therefore 5 *Popularity* points), the user who votes on the asset will receive 1 participation point, the user who created the asset will receive 5 contribution points, and the asset will receive 5 popularity points. 

![Figure 8.9: Setting limits in Social Activity](../../images/social-equity-limits.png)

It's easy to assign points--you can arbitrarily assign points for just about anything--the challenge is making the points significant in some way. As mentioned before, the primary purpose of social activity tracking is to make sure that users who regularly contribute to the portal and participate in discussions are recognized as such. So the central piece of the social equity display is the *User Statistics* portlet.

The User Statistics portlet displays a list of users ranked by an amalgamation of their participation and contribution scores. By clicking on the Configuration icon for the portlet, you can change some of the specifics of the rankings. There are four check boxes that you can enable or disable:

**Rank by Contribution**: If this is checked, a user's contribution score will be used as a factor in calculating their rank.

**Rank by Participation**: If this is checked, a user's participation score will be used as a factor in calculating their rank.

**Show Header Text**: Determines whether the title shows or only the rankings.

**Show Totals**: Toggles the display of the users activity score next to their name.

**Display Additional Activity Counters**: You can toggle the display of any number of other pieces of information next to the users name in the statistics, ranging from the number of comments on assets a user has created to the number of wiki articles that the user has created. If you want to display multiple data points, you can click the *plus* button to add one and the *minus* button to remove one. You can have as many data points displayed as you want, but displaying too many might make your portlet a little unwieldy.

![Figure 8.10: The User Statistics portlet](../../images/user-statistics-portlet.png)

The **Group Statistics** portlet provides some more advanced data analytics. If you add it to a page, and click on the configuration icon, you see that by default, it will display one metric *Activities on Assets*. You can click the *plus* button to add additional slots, and choose from the dozen or so metrics available for each slot, covering virtually any action that a user can perform on content on the portal. If you decide that you're displaying too many metrics, you can click the *minus* button for a particular slot on the configuration view to remove it.

There are a wide-ranging number of actions that you can provide social credit for. Users can receive credit for everything from subscribing to a blog to writing wiki articles. You can easily tweak the numbers in the control panel if it becomes clear that certain activities are weighted too high or too low.

Social Activity can be an invaluable tool for portals that are heavily driven by community-created content. It allows you to easily recognize users who are major contributors and it indicates to new users whose advice will be most trustworthy. Social Activity is easy to set up and can be configured differently for each site, increasing the flexibility of your portal.

Beyond Liferay's social API, there is also support for the OpenSocial standard.

## Exporting portal applications as widgets and OpenSocial gadgets* [](id=opensocial-integration)

OpenSocial is a framework designed for creating applications, called *gadgets*, that function on any social networking site that supports them. There are many existing gadgets available and anyone can create their own gadgets. With Liferay, you can integrate any OpenSocial gadget into a Liferay page and you can easily manage which gadgets are currently in use or available. You can also create your own OpenSocial Gadget using the Liferay's OpenSocial development environment.

### Using OpenSocial gadgets [](id=lp-6-1-ugen08-using-opensocial-gadgets-0)

The OpenSocial plugin is included with Liferay Community Edition and can be installed through Liferay Marketplace on Liferay EE. Installing the OpenSocial plugin will enable you to add OpenSocial gadgets to pages directly and to integrate them into your portal like a portlet to be added or removed by users as desired.

In some cases, you'll want to simply add a gadget to a single page. To this, go to a page, go to the *Add &rarr; More* menu and add *OpenSocial Gadget* to the page. Initially, the portlet simply displays with a link saying *Configure a gadget to be displayed in this portlet.* Click on this link, enter the URL for your gadget in the *URL* field (try *[Todo List Gadget](http://www.labpixies.com/campaigns/todo/todo.xml)* for an easy example) and click *Save*. That gadget will now be displayed as a portlet on your page. If, at any point, you want to change which gadget is displayed there, you can click on the configuration icon for the portlet and replace the URL with a new one. The OpenSocial Gadget portlet options allow you to change the permissions for who can view or edit the portlet, as well as options to share the contents of the portlet, just like you can with any other Liferay portlet.

![Figure 8.11: OpenSocial Gadget displayed on a page](../../images/open-social-gadget.png)

In addition to configuring individual gadgets, you can specify gadgets that are listed in the *Add &rarr; More* list from the Control Panel. Click on *OpenSocial Gadget Publisher* in the *Portal* section of the Control Panel. On this page there is a list of any gadgets that have been installed as well as buttons labeled *Publish Gadget* and *Refresh Gadgets*. Click on *Publish Gadget* and you'll see a form to enter a URL and choose a category. Enter the URL for an OpenSocial gadget (you can use the todo list from earlier) and click *Save*. The gadget will now be displayed in the list, and you can add it to any page on your portal.

![Figure 8.12: OpenSocial Gadget in your application list](../../images/open-social-in-context.png)

Clicking *Actions* next to the gadget will enable you to edit, delete, change permissions or refresh the gadget. *Edit* allows you to change the URL or category; *Permissions* gives you the basic *View*, *Update*, *Delete* and *Permissions* options; *Delete* will remove the listing for the gadget; and the refresh option manually refreshed the gadget cache for cases where changes have been made to the gadget but they are not displaying in the portlet.

### Creating and editing OpenSocial gadgets [](id=lp-6-1-ugen08-creating-and-editing-opensocial-gadgets-0)

OpenSocial gadgets are XML documents, so as part of Liferay's OpenSocial integration, a gadget editor is included. The gadget editor is a complete development environment for gadgets providing syntax highlighting, a preview function, undo/redo options and built in tabs for working on multiple gadgets at once. You can also organize and manage gadgets through a simple file manager embedded into the portlet. To access the gadget editor, go to the control panel and click on *OpenSocial Gadget Editor* in the content section.  

Once you have created and saved a gadget using the editor, click on the wrench next to the file to rename, delete, publish or get the URL for your gadget. If you want to display your gadget somewhere, click *Publish* to choose a category and display your gadget in the application menu or click *Show URL* to get a URL to display your gadget on any site that supports OpenSocial.

In addition to the social interactions that you can create on your portal, Liferay can integrate with some other popular social networks. This enables you to leverage their power and popularity for your portal's content.
	
## Integrating with Facebook [](id=facebook-integration)
	
Facebook is currently the number one social network in the world with somewhere in the neighborhood of 750 million active users. If you're trying to build a community on your portal, you don't want to neglect a bridge to nearly a billion possible users. With that in mind, Liferay provides a few easy ways for you to integrate your portal with Facebook.

### Facebook sign on [](id=lp-6-1-ugen08-facebook-sign-on-0)

Like many web sites you may visit, any portal running on Liferay can be set up to use Facebook for sign in. This makes it easier for users to sign in to your site, since they won't need to remember another user name and password, For more information on setting up Facebook sign on, see chapter 15.

### Using your portlets as Facebook applications [](id=lp-6-1-ugen08-using-your-portlets-as-facebook-applications-0)

You can add any Liferay portlet as an application on Facebook. To do this, you must first get a developer key. A link for doing this is provided to you in the Facebook tab in any portlet's Configuration screen. You will have to create the application on Facebook and get the key and canvas page URL from Facebook. Once you've done this you can copy and paste their values into the Facebook tab. Your portlet is now available on Facebook.

This integration enables you to make things like Message Boards, Calendars, Wikis and other content on your portal available to a much larger audience (unless you already have a billion users on your site, in which case, kudos to you). 

## Summary [](id=summ-30)

<!-- | TODO: This is too thin. Please use the headings to summarize what the chapter was about. | -->

Websites like Facebook and Twitter have attracted hundreds of millions of users by simply giving users a way to connect and communicate with each other. With Liferay, you have the power to either build a portal around social features or enhance a portal built around content and collaboration by providing users with the tools to connect and interact.

To get started, you can use a selection of portlets designed to make users' personal public pages a place where they can interact with each other by learning about other users and communicate using a simple messaging system. Using the now ubiquitous concept of "friends," users can also form a long term connection with other users they frequently work with or with whom they share similar interests. 

Outside of users' personal pages, you have a variety of portlets, like the activity portlets, which are designed to help users identify other users that might be working on similar projects, and keep track of what's going on around the portal. You can even use the Social Activity feature to give credit where credit is due and recognize the users who contribute the most.

Reaching even further out, Liferay provides integration with other sites and services that enable you to connect with users outside of your portal, either by pulling content from other websites using OpenSocial integration, or by pushing content on your portal out to a broader audience using Facebook integration. We've outlined the tools you have available, now it's up to you to leverage Liferay's Social Networking features in the way that best fits your portal.
