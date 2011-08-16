#Social Networking with Liferay

##Colaborating on the Social Network

Since MySpace rose to popularity in 2006 certain concepts, such as "Friend" and later "Like," previously reserved for direct human interaction, have taken on new meaning in on always-online, information driven culture. Initially, these concepts were seen as being great for web sites people spent their leisure time on, but not really valuable in the business world. Slowly, more and more organizations are realizing the importance of leveraging social interactions for more than just recreation.

Liferay has a constantly improving set of social features which will enable you to both encourage social interactions on your own portal, and leverage the power and popularity of other social networks.

##Liferay's Social Portlets
	
Liferay has many portlets available for social interaction and collaboration. Some of these portlets are designed to help you work together as a team, while others are simply designed to foster social interactions between team members at your organization.

Some of the social portlets are designed for use on the public pages of your portal, while others are designed to be used as part of a users personal pages. As you might guess, the portlets which are desigend for personal page use tend to be more focused on simple social interactions, while the ones which can be placed on any site are more beneficial for improving productivity. 

###General Use Portlets

There are several social portlets that are designed for use on regular portal pages. The goal of these is to use social connections to help a group work together more closely. These include the **Members** portlet, **Meetups**, **Top Users**, **Summary**, and **Activities**.


####Members
This portlet is simply a list of all of the Members of the current site. The only configuration options you have available are changing it's permission, so that, for example, someone who is not a member of the current site would be unable to view to portlet or any of the user information it contains.
[[SCREESNSHOT NEEDED]]


####Meetups
The Meetups portlet is designed to be a tool for creating casual meetings for users of your portal. Anyone can create a "Meetup" and give it a Title, Description, Date/Time, a maximum number of attendees, Price, and provide an image. Any meetups that are created will be displayed in the portlet for anyone to view. Once a meetup is created, users will be able to register, to help the organizer keep track of who's coming, and ensure that there aren't too many people coming.
[[SCREENSHOT NEEDED]]


####Activities
The Activities portlet comes in two varieties: the standard Activities portlet and the Members' Activities portlet. The basic function of the portlet is the same - they both displaya feed of the asset creation and management activities of users on the portal. The difference is that Activities will display what's going on across the entire portal, while Members' Activities will only display what members of the current site have been doing.
[[SCREENSHOT NEEDED]]

####Top Users
Top Users is a portlet which integrates with Liferay's Social Equity system (See pg. 123 for more information on Social Equity). It calculates a score based on the contributions that the user makes to the portal, whether by creating in portlets like the Blog and Wiki, or by answering questions on the Message Boards. Each user name is displayed with their picture, score, and rank.

Scores are updated in real time, while ranks are only updated daily, so it's possible that a user with a higher current score might be displayed with a lower rank until the ranks refresh for the day. 


###Personal Portlets

In addition to the portlets available for general use, there are a handful that can only be used on personal pages. These portlets can be used to create a profile page, similar to Facebook or Google+. 
[[SCREENSHOT OF ALL PORTLETS ON PAGE NEEDED]]

####Summary
The Summary portlet provides a quick overview of a user. When posted on one of a user's personal pages, it will display the name, profile picture, and job title of the user. The user can also add some additional personal information by clicking on *Edit* in the portlet, and filling in information in the *About Me* section.
This portlet is also very important to any socal site, because it will have the *Friend Request* button, which enables users to iniate social interactions. You will also see that many of the other portlets are based around this concept of "Friends" as well.

####Wall
The Wall portlet provides a place for users to leave messages on other users' profiles. The messages can only be plain text, and no formatting or HTML is supported. Once a post is added to their wall, a user has the ability delete it or respond to it with a quick link to post on the original poster's wall.

####Friends and Friends Activities
The Friends portlet simply shows a list of all of the user's friends with links to their profiles. The Friends Activities portlet will show an overview of actions done by a user's friends on the portal or in various sites.


##Social Equity

When you have a lot of user interaction on your web site, sometimes it is helpful to try to separate the signal from the noise. Liferay contains a lot of applications which end users can make use of to communicate with each other and provide information. Some of this information is good and helpful and some of it can be rather unhelpful. In order to better show which users are making real, valuable contributions, Liferay introduced Social Equity system with Liferay 6.0.

Currently Social Equity is available for the Blogs, Message Boards, and Wiki applications. Each category contains a rating for Information Value, Information Lifespan, Daily Limit, Participation Value, Participation Lifespan, and Daily Limit.

[[SCREENSHOT NEEDED]]

**Information Value:** Indicates how many points a user receives for the information content of a specific action.

**Information Lifespan:** Indicates, in number of days, how long it will be before the information points expire. Setting it to 0 means that they will never expire.

**Daily Limit:** This is the maximum number of actions of this type that a user will receive information points for in a given day.

**Participation Value:** Indicates how many points a user receives for the participating in the content creation of the website.

**Participation Lifespan:** Indicates, in number of days, how long it will be before the participation points expire. Setting it to 0 means that they will never expire.

**Daily Limit:** This is the maximum number of actions of this type that a user will receive participation points for in a given day.

The actions available that you can provide credit for are wide ranging. Users can receive credit for everything from writing wiki pages to simply viewing content. You can also easily tweak these numbers in the control panel if it becomes clear that certain activities are weighted too high or too low.

If you have a portal that is heavily driven by community created content, Social Equity is an invaluable tool to recognize users who are major contributors, as well as to indicate to new users whose advice is going to be the most sound and helpful. It's easy to set up, and can be configured differently for each community and organization on your portal, giving you increased flexibility across your web site as a whole.


##Other Social Integrations

In addition to the social interactions that you can create on your portal, Liferay has points of integration with some other popular social networks that will enable you to leverage their power and popularity for the content on your portal.
	
###Facebook Integration
	
Facebook is currently the number one social network in the world with somewhere in the neighborhood of 750 million active users. If you're trying to build a community on your portal, you don't want to neglect a bridge to nearly a billion possible users. With that in mind, Liferay provides a couple easy ways that you can integrate your portal with Facebook.

####Facebook Sign On
Like many web sites that you may visit, any portal running on Liferay can be setup to user Facebook for sign in. This makes it easier for users to sign in to your site, since they won't need to remember another user name and password, and it makes it easier for you to integrate useful features like having certain activities done on your portal display on Facebook. For more information on setting up Facebook sign on, [[NEED CHAPTER FOR THAT]].

####Using Your Portlets as Facebook Applications
You can add any Liferay portlet as an application on Facebook. To do this, you must first get a developer key. A link for doing this is provided to you in the Facebook tab. You will have to create the application on Facebook and get the key and canvas page URL from Facebook. Once you have done this, you can copy and paste their values into the Facebook tab. Your portlet will now be available on Facebook.

This integration enables you to make things like Message Boards, Calendars, Wikis, and other content on your portal available to a much larger audience (unless you already have in billion users on your site, in which case, kudos to you), and, if you have this coupled with Facebook Sign On, moving from the content on Facebook to your portal can be almost seamless.

