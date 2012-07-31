# Social Networking [](id=social-networking-with-liferay)

Since the first social networks rose to popularity, concepts such as *Friend* and later *Like*--previously reserved for direct human interaction--have taken on new meaning in an always-online, information driven culture. It could be argued that social networks have transformed the way people interact with their friends, relatives and colleagues. Friends, connections, followers, circles and lists have enabled people to connect and stay connected in ways they'd never been able to before. Initially, these concepts proved to be highly successful for casual web sites but they didn't take to the business world as quickly. But many organizations are now realizing the importance of leveraging social interactions for more than just recreation. Liferay's robust social features make it a great platform for business web sites, casual web sites and everything in between.

Social applications have many differences when compared to Standard applications that are vital to a social networking site. Standard applications have general and user specific data, whereas social applications can share data within a defined network. This variation is a huge advantage when trying to communicate important information to a large group of people. This difference in communication settings is illustrated below:

![Figure 8.1: Standard Apps vs. Social Apps](../../images/15-opensocial-5.png)

Liferay has a constantly improving set of social features which enable you to encourage social interactions on your own portal and to leverage the power and popularity of other social networks. In this chapter, we'll discuss:

-	General use social portlets
-	Social portlets for personal pages
-	Configuring personal pages for social networking
-	How to connect users using Liferay social relations
-	Social equity
-	Integrating Liferay with other social sites

When we're finished, you'll have a good idea how you can use Liferay to power your social network.

## Leveraging Social Portlets, Activities Tracking and User Connections [](id=liferay-s-social-portlets)
	
Liferay has many portlets available for social interaction and collaboration. Some of these portlets are designed to help you work together as a team, while others are designed to foster social interactions between team members at your organization.

Some of the social portlets should be used on the public pages of your portal, while others should be used as part of a user's personal site. As you might guess, the portlets for personal page use are more focused on simple social interactions, while the ones which can be placed on any site help improve productivity.

Unless otherwise noted, these portlets are all provided with minimal configuration options. Most of them have two configuration options--the option to change permissions for the portlet view and sharing options for connecting the portlet to other web sites. They do not have any way to change options like feed length or display styles. Some styling changes, however, can be made through custom CSS.

### Installing the social portlets [](id=lp-6-1-ugen08-installing-the-social-portlets-0)

The social portlets are all included with the Liferay Community Edition distribution, but need to be installed separately for Enterprise Edition. If you're using Liferay Enterprise Edition, or had previously removed the social portlets from Community Edition, you can use Liferay's plugin installer to easily add social features to your portal.

If you're logged in as an administrator, go to the control panel and click on *Plugins Installation* in the *Server* section. From here, click on *Install More Portlets* and search for *Social Networking*. Once the results come up, select the latest version of the Social Networking Portlet and click *Install*. Once the install process finishes, you can start using the social networking portlets. 

### Using social networking on public pages [](id=lp-6-1-ugen08-using-social-networking-on-public-pages-0)

There are several social portlets that are designed for use on regular portal pages. The goal of these is to use social connections to help a group work together more closely. These include the **Members** portlet, **Meetups**, **Top Users**, **Summary** and **Activities**.

![Figure 8.2: The Members Portlet](../../images/XX-social-networking-members-portlet.png)

The Members portlet is a simple list of all the current site's members. The only configuration options you have are permissions, which are the same for every portlet. For example, you might change the permissions so only members of the current site can view the portlet. 

![Figure 8.3: The Meetups Portlet](../../images/XX-social-networking-meetups.png)

The Meetups portlet is a tool for creating casual meetings for users of your portal. Anyone can create a "meetup" and give it a title, description, date/time, maximum number of attendees, price and provide an image. Any meetups that are created are displayed in the portlet for anyone to view. Users can register for the meetup, which lets the organizer keep track of who's coming. 

The options for creating a meetup are essentially the same as those for creating a calendar event and the Meetups portlet shares some functionality with the Calendar. For more information on the Calendar portlet and configuring events, see chapter 7.

![Figure 8.4: The Activities Portlet](../../images/XX-social-networking-activities.png)

The Activities portlet comes in two varieties: the standard Activities portlet and the Members' Activities portlet. The basic function of the portlets are the same--they both display a feed of what users are doing on the portal. The difference is that Activities displays what's going on across the entire portal, while Members' Activities displays only what members of the current site have been doing.

### Using social networking on personal pages [](id=lp-6-1-ugen08-using-social-networking-on-personal-pages-0)

In addition to the portlets available for general use, there are a handful that can be used only on personal pages. These portlets can be used to create a profile page, similar to Facebook or Google+. 

![Figure 8.5: Social Networking Portlets in a Facebook-like Layout](../../images/XX-social-networking-personal-portlets.png)

The Summary portlet provides a quick overview of a user's profile. When posted in a user's personal site, it displays the user's name, profile picture and job title. Users can add additional personal information by clicking on *Edit* in the portlet and filling in information in the *About Me* section.

This portlet is also essential to any social implementation on Liferay, because it has the *Friend Request* button. This enables users to initiate social relationships. Note that this portlet simplifies a much more powerful underlying social networking API that defines many different kinds of relationships, including friends. Your developers can take advantage of this API to create powerful social applications. For more information on this, see [*Liferay in Action*](http://manning.com/sezov) (Manning Publications) or the *Liferay Developer's Guide*. 

The Wall portlet provides a place for users to leave messages on other users' profiles. The messages can only be plain text as no formatting or HTML is supported. Once a post is added to their wall, users have the ability delete it or respond to it with a quick link to post on the original poster's wall.

The Friends portlet shows a list of all the user's friends with links to their profiles. The Friends' Activities portlet shows things a user's friends are doing on the portal.

The map is a tool which enables you to view the locations of other users on the portal, both locally and internationally. In order to configure the map, you need to install the IP Geolocator portlet (available from Liferay's community repository) and configure Maxmind GeoIP or GeoLite on your server. For more information on configuring geolocation services, visit the MaxMind support page at [http://www.maxmind.com/app/installation?city=1](http://www.maxmind.com/app/installation?city=1)

![Figure 8.6: The Map Portlet](../../images/map-portlet.png)

Liferay ships with a nice suite of social networking applications that lets you power your site for social interaction. Now that we've discussed what these portlets do, let's put them all together and make a social web site. 

### Liferay's social tools in action [](id=lp-6-1-ugen08-liferays-social-tools-in-action-0)

To get started with Liferay's social features, let's set up the public pages of our users' personal sites to include social apps. Because of Liferay's flexible page layout options, we have a large number of options for how to set the pages up. For simplicity's sake, we'll make something that's fairly similar to the original Facebook layout.

#### Setting up users' personal pages [](id=lp-6-1-ugen08-setting-up-users-personal-pages-0)

Before we start adding portlets to pages, we should configure Liferay so that everyone (or some subset of everyone) has the same social features. We have two ways to do this with advantages and disadvantages to each.

**User Groups:** Placing users into a group enables you to create a user group site for them. The pages and portlets defined by the user group site are copied to members' personal sites. With the user group site, you can control whether users can modify pages and you can push changes out to users in the future. Once the site template is assigned to a user group, you can set the *Default User Associations* to have all users be the member of a particular group in *Portal Settings* in the control panel. The advantage of this is that it can be managed entirely through the GUI and it's easy to configure. If you base your user group's site on a template, you can use the *Enable propagation of changes from the site template* option to manage all user pages simply by changing the template. This is the recommended way to manage personal pages across the portal. For more information on user group sites, see chapter 15.

**Portal Properties Configuration:** The legacy way to do this is with the configuration file. You can specify a default layout and portlets for personal pages in your `portal-ext.properties` file. If you don't want users to be able to modify their own pages, you should set the layouts to be static by adding the following line to your `portal-ext.properties` file:

    layout.user.public.layouts.modifiable=false
    
Note that this method applies changes to all users' personal sites. However, it does not provide as much maintainability or customization options as user group sites do. User group sites allow you to choose what's modifiable by the user. For more information on the `portal-ext.properties` method, see *Default User Private Layouts* and *Default User Public Layouts* in chapter 20.
    
Because it's the recommended method, we'll use the user group method to create the layouts. As an administrator, go to the control panel and select *Site Templates* from under the *Portal* section. Click *Add* and fill out the form. We'll call our new site template *Social Layout*. Click *Save*.

![Figure 8.7: Creating the Site Template](../../images/social-networking-site-template.png)

---

![tip](../../images/01-tip.png) Tip: Setting the property which disables modifications to personal page layouts disallows users across the portal from making modifications to their personal sites. Unchecking *Allow Site Administrators to Modify the Pages Associated with this Site Template* will only prevent users from modifying the specific pages contained in the template, but will not disable a user's ability to add or modify additional pages.

---

Once you've created the template, choose *Actions &rarr; Manage Pages* for *Social Layout* from the Site Templates page, then click *View Pages*. Let's change the name of the page from the default to *My Profile* and add some portlets to the page. In the screenshot below, we removed the borders to make the page look more integrated, and also used Nested Portlets to make the layout more interesting.

![Figure 8.8: Social Profile Site Template](../../images/social-networking-profile-template.png)

Back in the control panel, select *Users and Organizations* from the *Portal* section. Once there click *Add &rarr; User Group*. Name the group *Social Users*. When creating a user group, you have the option to set a user group site; use this option and select the Social Layout template for your Public Pages.

Now go to *Portal Settings* and select *Users* from the submenu. From the Users page, go to the *Default User Associations* tab and enter *Social Users* in the User Groups section. Now all users on the portal get a Social Profile page. Now the question is, how do we encourage users to visit each others fancy new profile pages?

#### Connecting users through collaboration [](id=lp-6-1-ugen08-connecting-users-through-collaboration-0)

There are many ways that social networks connect users. These generally involve some kind of mutual interest or experience. On a site like Facebook, you can connect with people from school, from work or from other personal connections. On a music based networking site like Last.fm, you can connect with people who have similar tastes to yours. With Liferay's social networking collaboration is the key to connection. 

Using our example site of nosester.com, we can take a closer look at ways users can be connected through hierarchies and ways they can connect to each other. We'll look at a handful of portlets, both those designed specifically for connecting users and those that can create connections as a side-effect of just getting work done.

The Directory portlet can provide a simple way for users to connect. If we have a site dedicated to people with big noses, we can place a directory portlet on that site, listing all the users that have joined that site. Users can connect by sending requests to other users on that list. This isn't the worst way to get users connected but it probably won't be very effective. Why not? Well, other than sharing some very basic common interests, we haven't really had any interactions.

The Activities portlet provides a similar but more effective means of connection. Because it shows a list of what other users are doing, this portlet helps users discover who is among the most active across the site or the portal, and thus who might be a good connection.  

Probably the most effective way users can connect is by interacting with other users. Every portlet in the Collaboration category provides information on who is contributing, regardless of how. You can see who is creating a thread in a message board, editing a wiki article, blogging or creating a calendar event. Users can use these to connect based on content--if I find your blog interesting, or if you answer my question on the message board, we can use that as a point to connect as friends to further our interactions. This way, instead of our connection being forced or arbitrary, we've connected based on the fact that we've directly interacted and share a common interest--just like people did before they had the internet.

"Friend" is only the default social relationship as implemented by Liferay's social portlets. You can design things so that users are automatically connected through Site and Organization membership. And there are many other relationship types beyond Friend: your developers can take advantage of these by using Liferay's social API. This is covered in *[Liferay in Action](http://www.manning.com/sezov)* and the *Liferay Developer's Guide*. Now that you've got all these social applications running on your system, you might wonder: how can I measure social interaction? How do I make identify the best contributors to my site? Liferay has an answer: social activity measurements.

## Measuring social activity [](id=measuring-social-activity)

<!-- | TODO This is all out of date. It's been reimplemented for Liferay 6.1. Please rewrite. | -->
When you have a lot of user interaction on your web site, it can be helpful to try to separate the signal from the noise. Liferay contains a lot of applications which end users can use to communicate with each other and provide information. Some of this information is good and helpful and some of it can be rather unhelpful. Using Liferay's Social Activity feature will help show which users are making real, valuable contributions.

![Figure 8.9: The Social Equity Page of the Control Panel](../../images/05-social-equity.png)

To activate Social Activity, you'll first need to determine which collaboration applications you want to use Social Activity. There are currently three types of content you can use with Social Activity - Blogs Entries, Message Board Messages, and Wiki Pages. Activation is a la carte - so you can use it on one, two, or all three applications. Social Activity tracks three metrics from within each of these applications two are for the user - *Participation* and *Contribution* - and the other, *Popularity*, is for the asset involved.

Let's activate Social Activity for Blogs Entries. Check the box next to *Blog Entry*. You now have options to set point values and limits on several different actions for blogs. You'll notice each item on the list has dropdowns you can use to set the number of participation and contribution points; popularity points are tied directly to contribution points. In addition to that, you can expand the box by clicking *Limits* in the top right of each list item. You can use this to set a limit on how many times a use can perform this activity with a specific asset and receive the requisite points. For some activities, you can set limits on both participation and contribution points but on new content creation you can only set limits on participation points.

It might not be immediately obvious, but for all actions that do not involve the creation of a new asset, all of the contribution points always go to the original asset creator and all popularity points go to the original asset. That means if *Votes on a Blog* is set to have 1 *Participation* point and 5 *Contribution* points (and therefore 5 *Popularity* points), the user who votes on the asset will receive 1 participation point, the user who created the asset will receive 5 contribution points, and the asset will receive 5 popularity points. 

![Figure 8.10: Setting limits in Social Activity](../../images/social-equity-limits.png)

It's easy to assign points--you can arbitrarily assign points for just about anything--the challenge is making the points significant in some way. As mentioned before, the primary purpose of social activity tracking is to make sure that users who regularly contribute to the portal and participate in discussions are recognized as such. So the central piece of the social equity display is the *User Statistics* portlet.

The User Statistics portlet displays a list of users ranked by an amalgamation of their participation and contribution scores. By clicking on the Configuration icon for the portlet, you can change some of the specifics of the rankings. There are four check boxes that you can enable or disable:

**Rank by Contribution**: If this is checked, a user's contribution score will be used as a factor in calculating their rank.

**Rank by Participation**: If this is checked, a user's participation score will be used as a factor in calculating their rank.

**Show Header Text**: Determines whether the title shows or only the rankings.

**Show Totals**: Toggles the display of the users activity score next to their name.

**Display Additional Activity Counters**: You can toggle the display of any number of other pieces of information next to the users name in the statistics, ranging from the number of comments on assets a user has created to the number of wiki articles that the user has created. If you want to display multiple data points, you can click the *plus* button to add one and the *minus* button to remove one. You can have as many data points displayed as you want, but displaying too many might make your portlet a little unwieldy.

![Figure 8.11: The User Statistics portlet](../../images/user-statistics-portlet.png)

The **Group Statistics** portlet provides some more advanced data analytics. If you add it to a page, and click on the configuration icon, you see that by default, it will display one metric *Activities on Assets*. You can click the *plus* button to add additional slots, and choose from the dozen or so metrics available for each slot, covering virtually any action that a user can perform on content on the portal. If you decide that you're displaying too many metrics, you can click the *minus* button for a particular slot on the configuration view to remove it.

There are a wide-ranging number of actions that you can provide social credit for. Users can receive credit for everything from subscribing to a blog to writing wiki articles. You can easily tweak the numbers in the control panel if it becomes clear that certain activities are weighted too high or too low.

Social Activity can be an invaluable tool for portals that are heavily driven by community-created content. It allows you to easily recognize users who are major contributors and it indicates to new users whose advice will be most trustworthy. Social Activity is easy to set up and can be configured differently for each site, increasing the flexibility of your portal.

Beyond Liferay's social API, there is also support for the OpenSocial standard.

## Exporting portal applications as widgets and OpenSocial gadgets [](id=opensocial-integration)

OpenSocial is a framework designed for the creation of socially themed application programming interfaces (APIs). OpenSocial applications, called *gadgets*, can be used in any web-based application that supports them. They are characterized as simple, widely available, and easy to deploy. Gadgets are especially popular on social networking sites. They can, however, be used in many different ways throughout your site. 

Liferay allows any OpenSocial gadget to be used on a page. An OpenSocial gadget is specified in an XML document and consists of embedded HTML and JavaScript. Liferay allows gadgets to communicate with each other and with portlets. This allows your gadgets to run seamlessly without your having to constantly check or update their content. They automatically update based on their connections with other applications. OpenSocial gadgets support numerous open web technologies such as *OAuth*, which we'll discuss in more detail later in the chapter.

Gadgets are socially aware and can share data across a community of users. You can define your own groups and create gadgets to communicate information based on pages (community/team pages), applications (gadgets/widgets/portlets), data, users, roles and authorization, and policies. In short, you can develop gadgets to allow individuals to access and share data within their social networks.

### Adding OpenSocial gadgets [](id=lp-6-1-ugen08-using-opensocial-gadgets-0)

The OpenSocial plugin is included with Liferay Community Edition and can be installed through Liferay Marketplace on Liferay EE. Installing the OpenSocial plugin enables you to add OpenSocial gadgets to pages, just like you'd add portlets. There are two types of gadgets: 

- "Adhoc" gadgets that users can add to a page via URL
- Gadgets published by the Control Panel that are available portal-wide

First, we'll go through steps to add an Adhoc gadget to a page.

#### Adding Adhoc gadgets [](id=lp-6-1-ugen08-adding-adhoc-gadgets-0)

This method is a quick way to add a gadget to a single page. To do this, go to the *Add* &rarr; *More* menu and add *OpenSocial Gadget* to the page. The portlet displays a link to pick a gadget for display:

![Figure 8.12: Configure a gadget to display in your portlet.](../../images/15-opensocial-1.png)

Click the configure link and a configuration window opens. Next, you need to insert a URL to an OpenSocial gadget. We'll insert the URL for a colorful calculator which is:

	http://www.labpixies.com/campaigns/calc/calc.xml

![Figure 8.13: Configuring an adhoc gadget with your portlet is as easy as pasting the gadget's URL.](../../images/15-opensocial-37.png)

After pasting the URL into the text field, click *Save* and your new gadget is visible on your page.

![Figure 8.14: The calculator gadget displays seemlessly on your page.](../../images/15-opensocial-2.png)

This particular gadget allows you to change its "skins" to fit your needs. Likewise, there are many other user-friendly interactive gadgets that give you flexibility to fit them into your themed sites. As you find gadgets that would work nicely throughout your portal, you can publish them for portal-wide use. You'll learn that next.

#### Adding gadgets for portal-wide use [](id=lp-6-1-ugen08-adding-gadgets-for-portal-wide-use-0)

You can easily make gadgets available for adding to pages as you would any other application. We'll demonstrate this by adding a *To-Do List* gadget for portal-wide use.

1. Go to the Control Panel and select *OpenSocial Gadget Publisher* under the *Portal* heading
2. Click *Publish Gadget*
3. Insert the URL for the *To-Do List* gadget: `http://www.labpixies.com/campaigns/todo/todo.xml`
4. Select an appropriate category for your gadget
5. Click *Save*

![Figure 8.15: Configure new gadgets with ease.](../../images/15-opensocial-38.png)

Your *OpenSocial Gadget Publisher* should now look like this:

![Figure 8.16: Publish gadgets for portal-wide use via the OpenSocial Gadget Publisher.](../../images/15-opensocial-3.png)

Clicking *Actions* next to the gadget enables you to edit, refresh, change permissions on, or delete the gadget. Here is a brief listing of what these four buttons do:

*Edit:* allows you to change the URL or category. 

*Refresh:* manually refreshes the gadget cache to reflect changes that have been made to the gadget that may not currently be displayed in the portlet. 

*Permissions:* gives you the basic *View*, *Update*, *Delete*, and *Permissions* options for each role on your site. 

*Delete:* removes the listing for the gadget. 

If you navigate to *Add* &rarr; *More* &rarr; *Gadgets*, you should see the *To-Do List* gadget.

![Figure 8.17: You can conveniently list your gadgets within the *Gadgets* category.](../../images/15-opensocial-4.png)

In the next section, we will demonstrate how to share OpenSocial gadgets with other sites.

### Sharing OpenSocial Gadgets [](id=lp-6-1-ugen08-sharing-opensocial-gadgets-0)

OpenSocial consists of a set of APIs for social networking. Liferay implements the OpenSocial standard, so you can be assured that your gadgets run on Liferay. That also means gadgets hosted by a Liferay Portal instance can be deployed and run in any standard OpenSocial container. It may be beneficial for you to share gadgets from your Liferay server with other sites, such as iGoogle. Google's iGoogle lets users customize their own page and add gadgets to their page. Your Liferay Portal users can share their portlets and other OpenSocial gadgets on iGoogle or any other OpenSocial-compatible site. Let's try this now.

<!-- Since Google has discontinued iGoogle (see http://googlesystem.blogspot.com/2012/07/igoogle-will-be-discontinued.html), this isn't a good example. Can you find another example? -->

For our example, we'll share Liferay's *Loan Calculator* on iGoogle.

1. Add the *Loan Calculator* portlet onto your Liferay page

2. Click the wrench icon in the upper right corner of the portlet and select *Configuration*

	![Figure 8.18: Select the *Configuration* button.](../../images/15-opensocial-6.png)

3. Select the *Sharing* tab and the *OpenSocial Gadget* sub-tab

4. Check the box labeled *Allow users to add Loan Calculator to iGoogle*. Also, replace "localhost:8080" with the name of your public domain and port.

	![Figure 8.19: Allow users to add your portlet as an OpenSocial Gadget in iGoogle.](../../images/15-opensocial-7.png)

5. Click Save

6. Close out the window and navigate back to the wrench icon in the upper right corner of your portlet. There is a new option named *Add to iGoogle* available. Click on this button to add your portlet to your iGoogle page.

	![Figure 8.20: Users simply click the *Add to iGoogle* button to add your portlet to their iGoogle page.](../../images/15-opensocial-8.png)

Your portlet is now available on your iGoogle page!

By going through this process, Liferay shared the URL of your portlet to iGoogle. The URL you provided is unique to your specific instance of the portlet. This allows you to share multiple instances of the same portlet as different Google Gadgets.

You could use this sharing capability to let users view what's happening on your portal at a glance. As you can imagine, you can share all kinds of information from your portal gadgets and portlets with your various circles of friends, colleagues, and fellow community members.

### Gadget Personalization [](id=lp-6-1-ugen08-gadget-personalization-0)

Liferay allows gadgets on your site to be personalized with data from third-party applications. Some of the third-party sites that authorize users to access application data include Evernote, Facebook, Google, Netflix, Photobucket, and Yahoo. Of course, many users feel uncomfortable giving away their private credentials to access these applications. Fortunately, Liferay allows you to use *OAuth* to protect your credentials while you access resources on these sites.

Keep these concepts in mind when going through the OAuth sections:

**Service Provider:** a web application that uses OAuth for access
**Protected Resources:** data controlled by the service provider, which can be accessed by the gadget through authentication
**Consumer Key:** a value used by the gadget to identify itself to the service provider
**Consumer Secret:** a secret the gadget uses to establish ownership of the consumer key
**Request Token:** a value the gadget uses to obtain user authorization, which is exchanged for an access token
**Access Token:** a value the gadget uses to gain access to the protected resources on behalf of the user, instead of using the user's service provider credentials

OAuth is an open standard that authorizes third-party applications to interact with a user's resources. Users can share their private resources from one site with another site without supplying typical credentials, such as their user name and password. OAuth uses request and access tokens as well as a token secret to authenticate the users of your gadget.

A popular characterization for the OAuth client is the "valet key for your web services." Let's say you're hosting Liferay Portal and have users and customers coming to your web site. You want them to have access to a third party resource, like Twitter, and be able to access their accounts from your site. In the past, they would have to provide their Twitter user names and passwords, but not if you use OAuth. OAuth is a "handshake mechanism" where, instead of requiring personal information, Liferay redirects users to Twitter, where they can tell Twitter to allow Liferay limited access to their accounts. This example is similar to our earlier "valet key" characterization. You wouldn't want a valet driver opening your glove box, storage spaces, hood, and other personal compartments within your vehicle. You would only want him or her to access things he or she needs to park your car. OAuth is based on this same idea: it allows a site just enough information to do what it needs and nothing more. This assures the user that his personal information is safe but gives him freedom to take advantage of valuable resources he typically uses from the service provider's site.

#### OAuth Admin Configuration [](id=lp-6-1-ugen08-oauth-admin-configuration-0)

OpenSocial defines a specification that allows gadgets to incorporate OAuth to access protected resources from service providers. A brief example is provided to demonstrate how easy it is to leverage OAuth within gadgets on your site. 

For this example, we'll set up a demo Twitter account gadget using OAuth. First we must configure your gadget. Follow the steps below to acquire the consumer key and secret given by the service provider.

1. Similar to previous examples, add the Twitter Demo gadget to your page. Go to the Control Panel and click on *OpenSocial Gadget Publisher* under the *Portal* heading. Click *Publish Gadget* and insert the Twitter Demo URL:

		http://opensocialdeju.googlecode.com/svn-history/r15/Twitter/TwitterDemo.xml

2. Click *Save*

3. For OAuth-enabled gadgets, you can select the *Manage OAuth* button from the *Actions* tab. Select *Manage OAuth* for your Twitter gadget. As shown below, you have several options under "twitter" that you must fill in to configure your gadget. You must also register your gadget with Twitter to access the Consumer Key and Consumer Secret.

	![Figure 8.21: Twitter allows you to manage OAuth for your Twitter gadget.](../../images/15-opensocial-9.png)

4. Go to [https://www.twitter.com](https://www.twitter.com) and, before logging in, scroll to the bottom of the page and select *Developers*. Then click *Create an app* to begin registering your gadget.

	![Figure 8.22: Select the *Developers* tab on Twitter.](../../images/15-opensocial-10.png)

	![Figure 8.23: Select *Create an app* from within the *Developers* page.](../../images/15-opensocial-11.png)

5. Fill in the *Name*, *Description*, and *Website* fields with what you prefer.

	a. For the *Callback URL* field, enter Liferay's default callback URL:

			http://myLiferayServer/opensocial-portlet/gadgets/oauthcallback

		Replace "myLiferayServer" with an appropriate value -- for this demonstration, we'll use 127.0.0.1:8080. 

	b. Finally, select the *Create your Twitter application* tab at the bottom of the page.

	![Figure 8.24: Fill in *Application Details* to setup connectivity between your Twitter gadget and your portal.](../../images/15-opensocial-12.png)

6. You are given the OAuth setting that you need to configure your gadget on Liferay. Copy the Consumer Key and Consumer Secret to your clipboard.

	![Figure 8.25: Here are the *Consumer Key* and *Consumer Secret* (values are blacked out for security).](../../images/15-opensocial-13.png)

7. Enter your Consumer Key and Consumer Secret under the *Manage OAuth* that you navigated to earlier. Also, select `HMAC_SYMMETRIC` for the *Key Type* and then click *Save*.

	Note: Liferay offers `PLAINTEXT` and `RSA_PRIVATE` as alternative key types. HMAC symmetric and RSA private are commonly used production key types, whereas plain text should never be used in real-world settings. 

8. Navigate to the *Settings* tab

	![Figure 8.26: Configure the settings for your Twitter gadget.](../../images/15-opensocial-18.png)

9. Under *Application Type*, select *Read and Write*. Then click *Update this Twitter application's settings* at the bottom of the page.

	![Figure 8.27: Select the *Read and Write* option to enable two way communication.](../../images/15-opensocial-19.png)

Congratulations! Your Twitter gadget is now configured with OAuth.

Next, we'll configure the gadget within Liferay Portal.

### Incorporating OAuth within your site [](id=lp-6-1-ugen08-incorporating-oauth-within-your-site-0)

Now that your gadget is registered with Twitter and is configured with OAuth, you can add it to your Liferay Portal. The OAuth client you configured in the previous section allows users to protect their credentials while accessing resources on your site. For this section, we'll demonstrate how to add the OAuth-configured gadget to your page.

1. Navigate to *My Private Pages* and click *Add* &rarr; *More...* &rarr; *Twitter Gadget*. If your gadget is configured correctly, it should appear like this:

	![Figure 8.28: Your OAuth configured Twitter gadget awaits personalization with your Twitter account.](../../images/15-opensocial-14.png)

2. Click on *Personalize this gadget* to be redirected to the service provider.

3. Fill in your Twitter user name and password and select *Authorize app*

	![Figure 8.29: Authorizing your OpenSocial application to use your account is straightforward.](../../images/15-opensocial-15.png)

4. Your Twitter Gadget should now show your last 20 tweets from your timeline. Your gadget should look similar to the snapshot below:

	![Figure 8.30: Check out your Twitter gadget timeline!](../../images/15-opensocial-16.png)

5. Using this gadget, you can tweet your current status and have it display on your Liferay site and Twitter page. To change the amount of tweets displayed, click on the wrench icon in the upper right corner and select *Configuration*. Under the *Setup* tab, you can type the number of tweets to display.

	![Figure 8.31: Configure the number of Tweets to display.](../../images/15-opensocial-17.png)

6. Lastly, you can tweet and view your Twitter timeline. The snapshot below displays what the Twitter Gadget looks like when tweeting.

	![Figure 8.32: Here is your Twitter gadget just the way you like it!](../../images/15-opensocial-20.png)

As you can see, OAuth is easy to configure and offers users the freedom to securely add valuable data from third-party sites.

### Creating and editing OpenSocial gadgets [](id=lp-6-1-ugen08-creating-and-editing-opensocial-gadgets-0)

OpenSocial gadgets are XML documents, so as part of Liferay's OpenSocial integration, a gadget editor is included. The gadget editor is a complete development environment for gadgets providing syntax highlighting, a preview function, undo/redo options, and built in tabs for working on multiple gadgets at once. You can also organize and manage gadgets through a simple file manager embedded into the portlet. To access the gadget editor, go to the control panel and click on *OpenSocial Gadget Editor* in the *Content* section.

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
