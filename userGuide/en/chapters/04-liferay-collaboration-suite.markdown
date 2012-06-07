# Liferay Collaboration Suite [](id=liferay-collaboration-sui-4)

Liferay Portal ships with a robust suite of collaboration applications which you can use to build communities of users for your site. These applications provide all the features that you would expect of standalone versions outside of a portal setting. The difference with Liferay's collaboration suite, however, is that all of the applications share a common look and feel, security model, and architecture. They inherit the strengths of being part of Liferay's development platform so you can use them in combination with Liferay's user management and content management features to build a well-integrated, feature-rich web site.

This chapter focuses on how to use Liferay's collaboration suite. We explain how to set up and administer:

-   Blogs
-   Calendars
-   Chat
-   Mail
-   Message Boards
-   Wikis
-   Knowledge Base Articles
-   Documents and Media Libraries

We'll discuss how these features work together to facilitate information flow within your portal and provide an enhanced experience for your users.

## Understanding Liferay's common configuration options [](id=understanding-liferay-s-common-configuration-options)

Just like siblings have common features inherited from their parents, applications that ship with Liferay also share common features. These are centered around scoping, sharing, permissions, and archives. So before we get into the nitty gritty of the applications themselves, it's best to cover these common features first, starting with the concept of scope.  

### Scope [](id=lp-6-1-ugen04-scope-0)

As we learned earlier, roles can be scoped by the portal, by a site, or by an organization. A role only takes effect within its scope. For example, a Message Boards Administrator role with complete access to the Message Boards portlet has different permissions based on the role's scope. If it's a portal role, members have permission to administer message boards across the entire portal. If it's a site role, members only have permission to administer message boards within the site where they've been assigned the role. For organizations with sites, site roles are automatically assigned to organization members based on the organization roles they have. So for an organization-scoped Message Boards administrator role, members only have permission to administer message boards within the site of the organization that assigned the role to them. 

We also use the word *scope* to refer to the data set of a portlet. By default, when a portlet is added to a page in a site, it is *scoped* for that site. This means that its data belongs to that site. If the portlet is added to a page in a different site, it employs a completely different data set. This enables you to place a Message Boards portlet in one site with one set of categories and threads, and place another Message Boards portlet in different site with a different set of categories and threads.

Scoping by site means that you can only have one Message Boards portlet per site. If you add one Message Boards portlet to a page in a site and add another Message Boards portlet to a different page in the same site, the second Message Boards portlet contains exactly the same data as the first. This is because, by default, the Message Boards portlet is scoped by site. Most of Liferay's other portlets also default to being scoped by site.

To avoid this limitation, many Liferay portlets can be scoped by page. In this case, the data sets of page-scoped portlets serve a single page, not an entire site. If you set the scope of a portlet to *page* instead of *site*, you can add any number of these portlets to different pages, and then they have different sets of data. This allows you to have more than one message board per site if you wish. Most portlets, however, default to the "native" configuration, and have their scopes set to the site where they are placed.

Unless otherwise noted, all the portlets in this chapter support scoping by portal (global), site (default), or page (select layout &rarr; current page). This grants you some flexibility in how you want to set up your portal. You can configure the scope of a portlet with just a few simple steps.

1.  Click the *Menu* icon in the portlet window (the wrench).

2.  Select *Configuration*.

3.  Select the *Scope* tab.

4.  Use the drop-down menu to set the scope.

5.  Click *Save*.

![Figure 4.1: Changing the scope of a portlet](../../images/05-changing-portlet-scope.png)

That's all it takes to change the scope for a particular portlet instance. By setting the scope to *Current Page*, you can add as many of these portlets to a site as you want, provided they are all added to separate pages. 

Another useful feature of Liferay's portlets is Archived Setups.  

### Archived Setups [](id=lp-6-1-ugen04-archived-setups-0)

Once you've configured a portlet, Archived Setups enables you to save those settings in an "archive". If someone goes in and changes the settings of a particular portlet, it then becomes easy to revert those changes back to the original, archived configuration.

To create an archived setup, click the *Configuration* option from the menu in the portlet's title bar. If the current settings of the portlet you're configuring are the ones you want to archive, click the *Archive/Restore Setup* link. If not, change and save the settings until you have the portlet configured the way you want it, and then click the *Archive/Restore Setup* link.

There is only one field to fill out. Enter a name for your archive and click *Save*. You should now see your archive in the list. If you ever need to revert the portlet to these archived settings, you can click *Actions &rarr; Restore* next to the archived setup you want to restore.

Unless otherwise noted, all of the portlets in this chapter support this feature. This is particularly useful for portlets that have a lot of configuration options, such as the Message Boards portlet. 

Next, we'll see how permissions apply to Liferay portlets in general. 

### Permissions [](id=lp-6-1-ugen04-permissions-0)

All of Liferay's portlets support Liferay's robust, fine-grained permissions system. Some higher level permissions can be configured in the permissions tab of the portlet configuration dialog box. You can grant roles permission to add the portlet to a page, configure the portlet, or view the portlet. To set these permissions, go to the *Configuration* menu and click on *Permissions*. This shows you a table of roles defined in the portal. Use the check boxes to grant certain permissions to different roles. Click *Submit* after you have made your selections. 

Beyond this, specific permissions are generally defined for specific applications. For example, the message boards portlet contains a *Ban User* permission. This makes no sense in the context of another portlet, say, the blogs portlet. We'll go over permissions for specific applications in the sections for those applications. For now, let's move on to sharing applications. 

### Sharing [](id=lp-6-1-ugen04-sharing-0)

The web was once thought of as a number of islands of applications in a vast universe of "cyberspace." Many web sites attempted to make their island the biggest. Some succeeded to a large extent and some failed. More recently, the concept of the web as an application itself has taken hold, and so widgets have become very popular nowadays. This concept is part of the "Web 2.0" concept and is very much enabled by widgets. What is a widget? A widget is a small piece of code which provides a piece of functionality, can be included on any web site, but does not necessarily have to be hosted by that web site. If you have ever embedded a YouTube video on your own web site so that users could watch a video without actually having to visit [http://youtube.com](http://youtube.com/), then you've already used a widget.

Liferay supports serving its portlets as widgets. You can embed a particular instance of a portlet running on your site into another site, such as Facebook. This opens up a whole new avenue of exposure to your web site that you would not have had otherwise. In fact, this is how all those Facebook games work.

![Figure 4.2: Sharing Tab of the Portlet Configuration Dialog Box](../../images/liferay-collaboration-portlet-configuration-sharing.png)

To share one of your portlets as a widget, open the *Configuration* dialog box from the portlet's title bar and select the *Sharing* tab. There are five subtabs under sharing: Any Web Site, Facebook, Google Gadget, Netvibes, and Friends.

#### Any Web Site [](id=lp-6-1-ugen04-any-web-site-0)

Copy and paste the provided snippet of JavaScript code into the web site to which you want to add the portlet as a widget. That's all you need to do. When a user loads the page on the other web site, the code will pull the relevant portlet from your site and display it.

#### Facebook [](id=lp-6-1-ugen04-facebook-0)

You can add any Liferay portlet as an application on Facebook. To do this, you must first get a developer key. A link for doing this is provided to you in the Facebook tab. You'll have to create the application on Facebook and get the key and canvas page URL from Facebook. Once you've done this, you can copy and paste their values into the Facebook tab. Your portlet will now be available on Facebook as a Facebook application.

![Figure 4.3: Liferay's Forums on Facebook](../../images/05-liferay-forum-facebook.png)

Incidentally, this makes Liferay a fantastic platform upon which to build applications for Facebook. See the *Liferay Developer's Guide* or [*Liferay in Action*](http://manning.com/sezov) for more details. 

#### OpenSocial Gadget [](id=lp-6-1-ugen04-opensocial-gadget-0)

OpenSocial comprises a container and a set of APIs for social networking and other web applications. iGoogle is a service provided by Google that lets users create a customizable page and add *Gadgets* to that page. Liferay can serve up portlets to be used as Open Social Gadgets on iGoogle or other OpenSocial-compatible pages.

To serve a Liferay portlet on iGoogle, check the box labeled *Allow users to add [portlet-name] to iGoogle*. Then copy and paste the URL provided into Google's *Add a feed or gadget* feature on the iGoogle configuration page. Your Liferay portal instance will serve that portlet directly onto your iGoogle page. The URL provided is unique to the specific instance of the portlet, so you could serve multiple instances of the same portlet as different Google Gadgets.

You could use this feature to allow users to view what's happening on your portal at a glance, using asset publishers or custom RSS feeds. You could also use Liferay's API to build your own portlet and provide the URL for users to place on their iGoogle pages.

#### Netvibes [](id=lp-6-1-ugen04-netvibes-0)

Netvibes offers a similar service to iGoogle--users can log in, create their own personal portal, called a *dashboard*, and add customizable widgets to the dashboard that they create. To set up Netvibes support for a particular portlet, check the *Allow users to add [portlet-name] to Netvibes pages* box. You can then use the provided URL to create a custom Netvibes widget based on the instance of the portlet that you're using.

#### Friends [](id=lp-6-1-ugen04-friends-0)

The final sub-tab of the *Sharing* tab is called *Friends*. This tab has a single check box that allows you to give your friends permission to add the application as a widget to another web site. This could be particularly useful for your blog or calendar if you wish to share them.

Now that we've seen all the common options available in Liferay's portlet applications, we can move on to specific applications, starting with blogs.

## Blogs [](id=blo-4)

The word *Blog* is an apostrophe-less contraction of the two words *web* and *log*. Blogs were first popularized by web sites such as Slashdot ([http://slashdot.org](http://slashdot.org)) which have the format of a running list of entries to which users could attach comments. Over time, more and more sites such as Digg, del.icio.us, and Newsvine adopted the format, empowering users to share their opinions and generating lively discussions.

Over the course of time, blogging sites and applications began to appear, such as blogger.com, blogspot.com. TypePad, WordPress, and Web Roller. These applications allow *individuals* to run their own web sites in the same format: a running list of short articles to which readers who are registered with the site can attach threaded comments. People who run a blog are called *bloggers*, and sometimes they build a whole community of readers who are interested in their blog posts. Anyone can have a blog, in fact, there are several famous people who run their own blogs. It gives people an outlet for self-expression that they would not otherwise have, and the ubiquity and wide reach of the Internet ensures that if you have something important and interesting to say, somebody will read it.

![Figure 4.4: Slashdot, one of the first blogs on the Internet](../../images/05-slashdot.jpg)

Liferay Portal has a Blogs portlet which allows you to provide a blogging service to users of your web site. In fact, Liferay extensively uses the Blogs portlet on [http://www.liferay.com](http://www.liferay.com) to provide employees with blogs of their own. In addition to the Blogs portlet, there's also a Blogs Aggregator portlet which can take entries from multiple users' blogs and put them all in one larger list. We will go over how to use both of these portlets to create a blogging site for your users.

### The Blogs Portlet [](id=lp-6-1-ugen04-the-blogs-portlet-0)

The Blogs portlet is available from the *Collaboration* section of the *Add &rarr; More* menu. Notice that it is an instanceable portlet, meaning that it supports scopes. This allows you to use the Blogs portlet to create a shared blog to build a site like Slashdot or to create multiple personal blogs to build a site like [http://blogger.com](http://blogger.com). What's the difference? Adding the Blogs portlet to a site page creates a shared blog for members of the site that the page belongs to. Adding the Blogs portlet to a user's personal site creates a blog just for that user. The Blogs portlet works the same way in both cases. And of course, you can change the Blog portlet's scope to have different blogs on different pages in the same site.

![Figure 4.5: Initial View of the Blogs Portlet](../../images/05-initial-view-blogs-portlet.jpg)

By default, the Blogs portlet displays the latest entry in its entirety. When you first add the portlet to a page, it has no entries, so the portlet is empty. There are several display options to let you configure it to look the way you want it to look. Before we start adding entries, let's configure the portlet so that it displays entries the way you want them.

#### Configuring the Blogs Portlet [](id=lp-6-1-ugen04-configuring-the-blogs-portlet-0)

The Blogs portlet is easy to configure. Click on the *Menu* icon in the portlet's title bar and select *Configuration*. Beneath the Setup tab, there is another row of options.

**Email From:** defines the *From* field in the email messages that users receive from the Blogs portlet.

**Entry Added Email:** defines a subject and body for the emails sent out when a new Blog entry has been added.

**Entry Updated Email:** defines a subject and body for the emails sent out when a new Blog entry has been updated.

**Display Settings:** changes various display options for the Blogs portlet. To choose the right settings, you should think about the best way to display your entries as well as how you want users to interact with bloggers.

![Figure 4.6: Blogs Configuration](../../images/05-blogs-configuration.png)

*Maximum Items to Display:* choose the total number of blog entries to display on the initial page. You can select up to one hundred to be displayed.

*Display Style:* choose between full Content, abstract, or just the title. Setting this to Abstract shows the abstract, or if there isn't one, only the first 30 words of your blog entries, with a Read More link at the bottom of each that expands to the whole entry.

*Enable Flags:* flag content as inappropriate and send an email to the administrators.

*Enable Related Assets:* select related content from other portlets to pull into their blog entry for readers to view.

![Figure 4.7: Related Assets](../../images/05-related-assets.png)

*Enable Ratings:* lets readers rate your blog entries from one to five stars.

*Enable Comments:* lets readers comment on your blog entries.

*Enable Comment Ratings:* lets readers rate the comments which are posted to your blog entries.

*Enable Social Bookmarks:* lets users tweet, Facebook like, or +1 (Google Plus) about blog posts.

*Maximum Items to Display:* determine how many blog entries will be displayed at once. The default is set to twenty.

*Display Style:* select a simple, vertical, or horizontal display style for your blog posts.

*Display Position:* choose a top or a bottom position for your blog posts.

**RSS:** choose how blogs are displayed to RSS readers. Here, you can choose how you want your blog entries to be published as feeds to readers and outside web sites.

*Maximum Items to Display:* choose the total number of RSS feeds to display on the initial page. You can choose up to one hundred to be displayed.

*Display Style:* choose between full content, abstract, and title. These options work just like the ones above for blog entries.

*Format:* choose which format you want to deliver your blogs: RSS 1.0, RSS 2.0, or Atom 1.0.

Now that you have the Blogs portlet looking the way you want it, you'll want to review permissions for it--especially if you're working on a shared blog. 

#### Permissions [](id=lp-6-1-ugen04-permissions-1)

If you have a personal blog, the default permissions should work well for you. If you have a shared blog, you may want to modify the permissions on the blog. The default settings make it so only the owner of the site to which the portlet has been added is able to add entries. This, of course, is great if the Blogs portlet has been added to a user's personal pages, but doesn't work so well for a shared blog. But don't worry: it's easy to share a blog with multiple users.

First, create a role for your bloggers and add them to the role (roles are covered in chapter 12 of Part 2). Next, click the *Permissions* button on the Blogs portlet. A list of both portal and site roles is displayed, and currently only the owner is checked. Check off any other role or team that should have the ability to add blog entries, and then click *Save*. Once this is done, users in the roles or teams that you selected are able to post to the shared blog. 

Now that everyone's able to post, let's look at how posts work. 

#### Adding Blog Entries [](id=lp-6-1-ugen04-adding-blog-entries-0)

Now you're ready to begin adding blog entries. Click the *Add Blog Entry* button. The following data entry screen appears: 

![Figure 4.8: Adding a Blog Entry](../../images/05-new-blog-entry.png)

There isn't much difference between this screen and any other data entry screen within Liferay Portal. You get a title, a way of scheduling when the entry is to appear, and a rich editor that allows you to format your entry the way you want, complete with embedded images, videos, and the like. Note also that as you type, the entry is automatically saved as a draft at periodic intervals. This gives you peace of mind in using the portlet from within your browser, since you won't lose your entry in the event of a browser crash or network interruption. You can also tag your entries using the same tagging mechanism found everywhere else in the portal.

The Blogs portlet also supports trackbacks and pingbacks. Trackbacks are special links that let you notify another site that you linked to them. For example, if you wanted to write an entry in your blog and reference some other site's entry, you might put the URL to the other entry in the *Trackbacks to Send* field. If you have multiple URLs you want to send trackbacks to, separate them with spaces. 

If you want others who link to your blog to let you know about the link via trackbacks, leave the *Allow Trackbacks* box checked. This generates a URL that is displayed with your blog entry. Others who want to link to your entry can use this URL for the link, to send trackbacks to your blog.

Note that trackbacks only work when the protocol is supported by both the linker and the linkee. A newer way to support similar link notification functionality is *pingbacks*. Pingbacks are XML-RPC requests that are similar to trackbacks except they're automatically sent when you link to another site. They're easier to use because you don't have to do anything extra: if you link to another site in your blog entry, Liferay sends a pingback to the other site to notify that site that you linked to it. Similarly, if someone links to your blog entry, Liferay can receive a pingback from that person's site and record the link.

You can enter a description of your post beneath the Abstract heading, and this can be used by the Abstract display style. Below this is the Categorization heading, where you can attach tags and/or categories to your blog entry. You should definitely consider doing this: it improves search results for blog entries, and it gives you more navigation options that you can pass on to your users. For example, you can add the Tags Navigation portlet to another column on your blogs page, allowing users to browse blog entries by tag. 

Below this is the Related Assets heading. If there's some other content in the portal that's related to your blog, you can choose it here. For example, you might want to write a blog entry talking about a particular discussion that happened on the forums. To link those two assets together, select the forum thread under Related Assets. 

Once you've finished your blog entry, click *Publish*. You'll go back to the list of entries, and now your entry is displayed. Here is what it looks like when the display style is set to *Full Content* and the number of entries is set to ten:

![Figure 4.9: First Blog Entry Added](../../images/05-first-blog-entry-added.png)

You can see that in the summary view, you don't see the trackback/pingback link, and you only see the number of comments that have been added. If you were to click the *Read More* link, you would see the entirety of the article, all the comments in a threaded view, and the trackback/pingback link which others can use to link back to your blog entry.

The full view of the blog entry also contains links to share blog entries on social networks, such as Twitter, Facebook, and Google Plus. This gives your readers an easy way to share blog entries with friends, potentially driving further traffic to your site. As you can see, the Blogs portlet is a full-featured blogging application that gives you and your users the ability to enter the blogosphere with an application that supports anything a blogger needs.

Of course, Liferay is a portal, and as a portal, it excels at aggregating information from multiple places. For that reason, it also includes the Blogs Aggregator portlet so that you can "bubble up" blog entries from multiple users and highlight them on your site. Let's look next at how that works. 

### Aggregating Blog Entries [](id=lp-6-1-ugen04-aggregating-blog-entries-0)

You can set up a whole web site devoted just to blogging if you wish. The Blogs Aggregator portlet allows you to publish entries from multiple bloggers on one page, giving further visibility to blog entries. This portlet is also very straightforward to set up. You can add it to a page from the Collaboration category in the Dockbar's *Add &rarr; More* menu.

If you click *Configuration* from the menu button in the title bar of the portlet, the Blogs Aggregator's configuration page appears. From here, you can set several configuration options.

![Figure 4.10: Blogs Aggregator Configuration](../../images/05-blogs-aggregator-configuration.png)

**Selection Method:** select Users or Scope here. If you select Users, the Blogs Aggregator aggregates the entries of every blogger on your system. If you want to refine the aggregation, you can select an organization by which to filter the users. If you select Scope, the Blogs Aggregator contains only entries of users who are in the current scope. This limits the entries to members of the site where the Blogs Aggregator portlet resides.

**Organization:** select which organization's blogs you want to aggregate.

**Display Style:** select from several different styles for displaying blog entries: title, abstract, body, image, or quote.

**Maximum Items to Display:** select maximum number of entries the portlet displays.

**Enable RSS Subscription:** creates an RSS feed out of the aggregated entries. This lets users subscribe to an aggregate feed of all your bloggers. 

**Show Tags:** for each entry, displays all the tags associated with the blogs.

When you've finished setting the options in the portlet, click *Save*. Then close the dialog box. You'll notice the Blogs Aggregator looks very much like the Blogs portlet, except that the entries come from more than one author. This makes it nice and familiar for your users to navigate. 

### The Blogs Admininistrator Portlet [](id=lp-6-1-ugen04-the-blogs-admininistrator-portlet-0)

In the control panel there's a portlet for managing your site's blog entries. Most of the time, the Blogs portlet is the only tool you'll need to manage your blog entries. If, however, you need to massively delete blog entries, the blogs administrator portlet is the perfect tool for you. 

![Figure 4.11: The Blogs Administrator portlet lets you delete large sets of blog entries.](../../images/04-blogs-administrator.png)

Note that it's only for batch processing of blog entries; for the full set of tools for managing blog entries, your best bet is to use the Blogs portlet.  

We've already talked a little bit about connecting content across the portal by using Related Assets; now let's take a look at the Related Assets portlet.

##Related Assets [](id=related-assets)

Related Assets is a new feature in Liferay 6.1 that enables you to connect any number of assets within a site or across the portal, even if they don't share any tags and aren't in the same category. We've already seen that you can show related assets within the display for a specific asset, and with the Related Assets portlet you can show links to any assets which are related to content displayed on that page.

The Related Assets portlet is based on the Asset Publisher, and possseses essentially the same interface with one key difference. The Asset publisher displays any content that meets the criteria selected in the portlet configuration. The Related Assets portlet only displays content that meets the criteria, and also is listed as a related asset for a piece of content that is currently published on the page where it is placed.

Let's move on, now, to the Calendar portlet.

## Calendar [](id=calend-4)

Liferay's Calendar portlet is a complete calendaring solution. You can schedule any number of events of different types, receive alarms via email or text message, import and export your calendar, and much more. Additionally, you can import and export the calendar to and from the popular iCalendar format for use in other applications.

![Figure 4.12: The Liferay Calendar Portlet](../../images/05-calendar-portlet.png)

In a similar way to the Blogs portlet, you can use the Calendar portlet as a shared calendar on a web site or as a personal calendar -- or both. Let's look at how to configure it. 

### Configuring the Calendar Portlet [](id=lp-6-1-ugen04-configuring-the-calendar-portlet-0)

Open the *Configuration* dialog box from the menu in the portlet's title bar. The Setup tab allows you to configure three different options in subtabs: *Email From*, *Event Reminder Email*, and *Display Settings*.

**Email From:** set the *Name* and *Email Address* system generated emails come from. The address that you enter in the Email Address field must be properly formatted, but it doesn't need to be an address that actually exists. 

**Event Reminder Email:** customize the email message the Calendar sends for event reminders. It contains the same rich text editor that you see everywhere else in Liferay, and this allows you to HTML format the message for easy recognition. As with other Liferay email notifications, there are several variables which allow you to insert runtime values into the message, and these are listed underneath the text editor so that you can use them in the appropriate place in your template. For example, you might want the event start date and time and the event title included in the email reminder that you receive. Use the variables to insert that information in the appropriate place. 

**Display Settings:** customize how the portlet shows itself to the user. There are several settings here: 

- *Default Tab:* select which tab of the calendar portlet is displayed to the user by default. 

- *Summary Tab:* select whether it has a horizontal or vertical layout. You can also use checkboxes to choose whether or not the calendar portlet shows a mini month, shows today's events, or enables related assets, comments, or ratings. 

Now that you've successfully set up your calendar, let's look at how to use it. 

### Using the Calendar Portlet [](id=lp-6-1-ugen04-using-the-calendar-portlet-0)

The Calendar portlet inherits its interface from the rest of Liferay's portlet library, so you should find shared features in the same place that you find them in other Liferay portlets.

To get started, you may want to click the *Permissions* button. Here you'll find an interface that should be becoming familiar: a list of roles with check boxes. For the Calendar, these denote whether the role has the *Add Event* or the *Export All Events* permission. By default, only the owner has permission to do these things, which means that the Calendar portlet is set up for personal use. The reason for this is that out of the box, Liferay puts a Calendar portlet on all users' private pages. If you want to use the Calendar as a shared calendar, you'll need to make an additional configuration. 

First, create a portal or site role. Then add the users responsible for maintaining the calendar to this role. This process is described in chapter 12. You can add multiple roles if you like. Once you have the role or roles set up, come back to the Calendar portlet and click the *Permissions* button. Check the boxes next to the roles that should have access to one or both of the functions *Add Event* and *Export All Events*. Then click *Submit*.

Now you are ready to begin using your calendar. Click the *Add Event* button. A form appears that allows you to fill out all the information for your event.

**Start Date/Time:** enter a date and time for the event.

**Duration:** specify how long the event will last.

**All Day Event:** disassociate time from the event and make it last all day.

**Time Zone Sensitive:** make sure that the portal keeps track of the event regardless of time zone.

**Title:** provide a title for the event.

**Description:** describe your event.

**Location:** specify the physical location of the event.

**Type:** select a pre-configured event type. You can change these in the `portal-ext.properties` file.

**Permissions:** manage who can view and edit the event.

**Categorizations:** tag and categorize the event.

**Related Assets:** attach a piece of content from another portlet to the event.

**Repeat:** select the schedule (daily, weekly, monthly. etc.) for repeatable events.

**End Date:** enter the end date for events that repeat on a schedule but have a specified last meeting.

**Reminders:** select whether to send a reminder, how long before the event to send it, and through what medium (email, SMS text message, or instant message) to send it. Note that this feature is integrated with users' profiles on the portal, so users need their information filled out and current in order to receive notifications.

When you have finished adding your event, click *Save*. You can view calendar events by day, week, month, year, or in a simple list. 

As you can see, the Calendar portlet is easy to use and convenient for users of the portal. Next, we'll look at how Liferay handles live chat. 

## Chat [](id=ch-4)

Liferay's Chat portlet provides a convenient way of allowing users to send each other instant messages when they are logged into your web site. It appears as a bar at the bottom of every page, showing who is logged on, their statuses, and any chats the logged-in user has open.

![Figure 4.13: Liferay's Chat Portlet](../../images/05-liferay-chat-portlet.jpg)

The Chat portlet is distributed with the Liferay bundles, but is not included as part of the `.war` distribution, as it is a separate plugin. If you installed the Liferay `.war` manually on your application server, you can install the Chat portlet by going to the Control Panel, clicking *Plugins Installation*, and then clicking the *Install More Portlets* button. Find the Chat portlet in the list, click on it, and then click *Install*.

The Chat portlet is very simple to use. To change the settings, click *Settings* (found near the lower right corner next to *Online Friends*). Here you can set your status, choose whether or not to show that you are online, and whether or not to play a sound if someone sends you a message while you have the window or tab in the background. The Chat portlet displays the number of your friends who are online. Click the *Online Friends* link and then click on a friend's name to open a chat window. You can have multiple chats open at a time, and can have one or more of them minimized.

### Jabber Server Integration [](id=lp-6-1-ugen04-jabber-server-integration-0)

Liferay 6.1 introduced Jabber server integration to Liferay's Chat portlet. Jabber is the original name of the XMPP (Extensible Messaging and Presence Protocol) protocol, an open-standard communications protocol based on XML. Using a chat server helps Liferay's chat scale to very large installations and allows for communication between different chat clients. For example, Jabber server integration allows users using the chat portlet in their browser windows to chat with other users using desktop clients like Empathy, Pidgin, or Kopete. 

Jabber server integration is not enabled by default since it requires a running Jabber server. Once you have installed and started a Jabber server, you can enable Jabber server integration by creating a `portlet-ext.properties` file to override some properties of your Chat portlet's `portlet.properties` file. You could modify your Chat portlet's `portlet.properties` file directly, but it's a best practice to override it instead.

#### Installation Steps [](id=lp-6-1-ugen04-installation-steps-0)

You can use any chat server that supports Jabber. The Chat portlet's Jabber server integration feature was tested with versions 3.7.0 and 3.7.1 of Openfire, a real time collaboration server distributed under the Open Source Apache License. You can download Openfire from [http://www.igniterealtime.org/projects/openfire/](http://www.igniterealtime.org/projects/openfire/). To enable Jabber chat integration, follow these steps:

1. Start your chat server. If you are using Openfire on a Linux/Mac system, you can start/stop the chat server by executing the openfire shell script in the `openfire/bin` directory. Usage: `./openfire start` or `./openfire stop`

2. Override the `portlet.properties` file in your /chat-portlet/WEB-INF/src/ directory with a `portlet-ext.properties` file in the same directory. When you deploy the portlet, the properties files should be copied to your /chat-portlet/WEB-INF/classes/ directory. If you have already deployed the Chat portlet, create the `portlet-ext.properties` file in your /chat-portlet/WEB-INF/classes/ directory. The contents of your `portlet-ext.properties` file should like this:

	jabber.enabled=true 
	jabber.import.user.enabled=true
	jabber.host=localhost
	jabber.port=5222   
	jabber.service.name=<Host Name>
	jabber.resource=Liferay    
	jabber.sock5.proxy.enabled=false
	jabber.sock5.proxy.port=-1

Note that you must change `jabber.service.name` to the "Host Name". If you are using Openfire, you can find the Host Name by using the Openfire administration web tool. If you did not set up administrative credentials when you started Openfire, the default credentials are username: admin, password: admin.

![Figure 4.14: Openfire Administration Web Tool](../../images/jabber.service.name.png)

Additionally, make sure that you set `jabber.enabled` to `true` and have added the correct values to `jabber.host` and `jabber.port`. If you installed your chat server on a remote machine or chose to not use the default port, change `jabber.host` and `jabber.port` accordingly.

3. Deploy your Chat portlet. Remember that this portlet must be of version 6.1 or higher.

#### Single Sign On [](id=lp-6-1-ugen04-single-sign-on-0)

If the property `jabber.import.user.enabled` is set to `true`, the Chat portlet will import the user automatically to Jabber after he logs in to the portal. Once the user is imported, he can use any Jabber client using the same screen name and password he uses to log in to the portal. His buddies will be also imported as they become online in the Chat portlet.

Note that it's a "lazy import". Users are imported only after they log in to the portal and their buddies will be added to his list only if they see each other within the Chat portlet. They won't be able to use other Jabber chat clients until they log in to the portal.

If `jabber.import.user.enabled` is set to `false`, users need to create their Jabber account and add buddies manually. They have to create their accounts using the same screen name and password they use in the portal. If they don't, the Chat portlet won't be able to connect to their Jabber account.

Alternatively, since Openfire integrates with LDAP, if you are using Openfire and your portal is also using LDAP for authentication, you can disable the `jabber.import.user.enabled` property.

Next, let's look at how you can integrate your email addresses with Lifray's Mail portlet.

## Mail [](id=ma-4)

Liferay's Mail portlet enables your users to interact with their email using an easy to use, ubiquitous web interface. If your mail system supports the IMAP protocol, you can use the Mail portlet to integrate your users' mail with the rest of your web site. You can also connect the Mail portlet to a mail account provided by Google.

The Mail portlet is distributed with the Liferay bundles, but is not included as part of the `.war` distribution, as it is a separate plugin. If you installed the Liferay `.war` manually on your application server, you can install the Mail portlet by going to the Control Panel, clicking *Plugins Installation*, and then clicking the *Install More Portlets* button. Find the *Mail* portlet in the list, click on it, and then click *Install*.

![Figure 4.15: Liferay's Mail Portlet](../../images/05-mail-portlet.png)

To connect the Mail portlet with an email account, click the *Add a New Email Account* link. From there, you are given a choice between a Custom email Account or a Gmail Account. Choose the option that you wish, and fill out the form that appears.

For a Gmail account, all you need to do is provide your email address and your password, and the portlet will take care of the rest.

For a Custom Mail Account, the following fields are necessary:

**Address**: lets you enter the email address which receives mail for this account.

**Login**: lets you choose a user name for logging into the account.

**Password**: lets you choose a password for logging into the account.

**Incoming Settings**: allows you to specify the host name for your IMAP (Internet Mail Access Protocol) or POP server.

**Incoming Port**: allows you to specify the port upon which the IMAP or POP service is running.

**Use Secure Incoming Connection**: allows you to use an encrypted connection to the server provided that your server supports it.

**Outgoing SMTP Server**: lets you enter the host name of your SMTP (Simple Mail Transfer Protocol) server.

**Outgoing Port**: allows you to specify the port upon which the SMTP service is running.

**Use Secure Outgoing Connection**: allows you to use an encrypted connection to the server provided that your server supports it.

When finished, click *Save*. Your new email account now appears as a tab at the top of the page along with the button for adding a mail account. In this way, you can add as many mail accounts as you want in order to view them in the portlet.

Click the tab for the mail account you just configured to be brought to an interface which allows you to read your mail and compose new messages. To read a message, click on it. To compose a new message, click the *Compose Email* link on the left side of the portlet. A form appears which allows you to compose an email message using the same rich text editor that appears everywhere else in Liferay. You can read, reply, and create messages, as well as manage all of your folders in Liferay's Mail portlet. 

The Mail portlet is a great way to integrate a familiar service with other the collaboration features that Liferay provides. Next, let's look at one of the most widely used applications provided by Liferay: its message boards.

## Message Boards [](id=message-boar-4)

Liferay's Message Boards portlet is a state of the art forum application similar to many forums in which you may have participated. The difference, of course, is that Liferay's message boards can inherit the abilities of the Liferay development platform to provide an integrated experience that others cannot match.

There are countless web sites out there where it is clearly evident that there is no link whatsoever between the main site and the message boards. In some cases, users are even required to register twice: once for the main site and once for the message boards. Sometimes it is three times: for the site, for the message boards, and for the shopping cart. By providing a message boards portlet along with all of the other applications, Liferay provides a unique, integrated approach to building web sites. You can concentrate on building your site while Liferay does the integration work for you.

The Message Boards portlet has a lot of configuration options, but they are straightforward to use and are the reason why this portlet is a full-featured forum application for your web site. To get started, add a Message Boards portlet to your site. Once it is added, click the *Menu* icon in the portlet's title bar and click *Configuration*. There are two rows of tabs. The first tab in the top row is titled *Setup*. This is where you can configure the application the way you want it to behave on your site.

### General [](id=lp-6-1-ugen04-general-0)

The first tab beneath *Setup* is labeled *General*. Here, you can enable anonymous posting, subscribe by default, flags, ratings, and thread as question by default. You can also choose whether you want the message format to be BBcode or HTML. Anonymous posting, subscribe by default, flags, and ratings are selected by default and the default message format is BBcode. 

Anonymous posting lets those without an account on the system post messages to your message boards. You may or may not want to do this, depending on the type of community you are building. Allowing anonymous posting opens your site to anyone who might want to spam your forums with unwanted or off topic advertising messages. For this reason, most of those who implement message boards turn anonymous posting off by unchecking this box.

The subscribe by default option automatically subscribes users to threads they participate in. The thread as question by default automatically checks the mark as question box in the new thread window. Threads marked as questions display the flag "waiting for an answer." Subsequent replies to the original message can be marked as an answer.

Flags allow your users to flag content which they consider to be objectionable. If you are allowing anonymous posting, you might use flags in combination with it if you have someone administering your message boards on a day-to-day basis. That way, any unwanted messages can be flagged by your community, and you can review those flagged messages and take whatever action is necessary. Using flags is also a good practice even if you're not allowing anonymous posting.

Ratings enable your users to give certain posts a score. This score is used by Liferay Portal's social equity system to rank your site members by how helpful their contributions are. You can read more about social equity later in this chapter and in chapter 9.

### Email From [](id=lp-6-1-ugen04-email-from-0)

This tab allows you to configure the email address that messages from the Message Boards portlet come from. By default, the name is `Joe Bloggs` and the email address is `test@liferay.com`.

### Message Added Email [](id=lp-6-1-ugen04-message-added-email-0)

This tab allows you to customize the email message that users receive when a message is added to a topic to which they are subscribed.

**Enabled:** allows you to turn on the automatic emails to subscribed users. Uncheck the box to disable the message added emails.

**Subject Prefix:** lets you choose a prefix to be prepended to the subject line of the email. This is usually done so that users can set up message filters to filter the notifications to a specific folder in their email clients.

**Body:** allows you to write some content that should appear in the body of the email.

**Signature:** lets you add some content to appear as the signature of the email.

Below the fields is a section called *Definition of Terms* which defines certain variables which you can use in the fields above to customize the email message. Some of these variables are for the message board category name, the site name, and more.

### Message Updated Email [](id=lp-6-1-ugen04-message-updated-email-0)

The Message Updated Email tab is identical to the Message Added Email tab, except it defines the email message that users receive whenever a topic is updated.

### Thread Priorities [](id=lp-6-1-ugen04-thread-priorities-0)

You can define custom priorities for message threads on this tab. These allow administrators to tag certain threads with certain priorities in order to highlight them for users. By default, three priorities are defined: Urgent, Sticky, and Announcement. To define a thread priority, enter its name, a URL to the image icon that represents it, and a priority number which denotes the order in which that priority should appear.

There is also a field on this form that allows you to select a localized language for your priorities. If you need to do this, you can select the language from the selection box.

### User Ranks [](id=lp-6-1-ugen04-user-ranks-0)

On this tab, users can be ranked according to the number of messages they have posted. You can set up custom ranks here. Defaults have been provided for you, going from zero messages all the way up to one thousand.

In addition to ranks, you can also select who is a "moderator"? by what roles are held. Defaults are there for you which show you how to do this.

    Moderator=site-role:Message Boards Administrator

    Moderator=organization:Message Boards Administrator

    Moderator=organization-role:Message Boards Administrator

    Moderator=regular-role:Message Boards Administrator

    Moderator=user-group:Message Boards Administrator

As you can see, all you need to do is set the rank, the collection type, and the name of the type. In the example above, anyone who has a site role, an organization role, a regular role, or is in a user group called *Message Boards Administrator*, or anyone who is the organization owner gets the moderator rank.

As with thread priorities, on this tab you can define whether your ranks are localized in a particular language.

### RSS [](id=lp-6-1-ugen04-rss-0)

Message board threads can be published as RSS feeds. This tab allows you
to define how the feeds are generated.

**Maximum Items to Display:** lets you select the number of items to display in the feed.

**Display Style:** lets you select the style. You can publish the full content, an abstract, or just the title of a thread.

**Format:** allows you to choose the format: RSS 1.0, RSS 2.0, or Atom 1.0.

### Permissions [](id=lp-6-1-ugen04-permissions-2)

The default page that the Message Boards portlet displays has three buttons on it. Click the one labeled *Permissions*. This allows you to define which roles have the ability to add a category of threads or to ban abusive users from the message boards. Select the roles and permissions you want to configure and then click *Submit*.

### Adding Categories and Mailing Lists [](id=lp-6-1-ugen04-adding-categories-and-mailing-lists-0)

You are now ready to add categories to your message boards. Click the *Add Category* button. You may merge with a Parent Category by enabling the *Merge with Parent Category* check box and clicking the *Select* button. Enter a name for the category and a description of the category.

Starting with Liferay 6.1, categories can have different display styles. The available categories must be set in portal property `message.boards.category.display.styles` and the default category in `message.boards.category.display.styles.default`. When creating a new category, you can select the display style you like for that category. By default, Liferay provides two predefined display styles, although many more can be easily added:

**Default:** classic display style for general purpose and discussions.

**Question:** designed for discussions in a format of questions and answers.

![Figure 4.16: Editing a Message Boards Category](../../images/05-editing-message-board-category.png)

At the bottom of the form is a check box that allows you to enable the mailing list function.

The mailing list function works in concert with the message notification emails. If a user subscribes to a message board category, he or she will get emails when someone posts messages to that category. Enabling the mailing list function allows those users to simply reply to the notification messages in their email clients, and those replies will be posted to the thread automatically.

To enable this functionality, you will need a mail account for the category. Once you click the check box, a number of other options will appear.

**Email Address:** lets you enter the email address of the account that will receive the messages.

Next, there are two sections: *Incoming* and *Outgoing*. These define the mail settings for receiving mail and for sending mail. The Incoming tab has the following options:

**Protocol:** lets you select POP or IMAP.

**Server Name:** lets you enter the host name of the mail server you are using.

**Server Port:** allows you to specify the port on which your mail service is running.

**Use a Secure Network Connection:** lets you use an encrypted connection if your server supports it.

**User Name:** lets you enter the login name on the mail server.

**Password:** lets you enter the password for the account on the server.

**Read Interval (Minutes):** allows you to specify how often Liferay will poll the server looking for new messages to post.

The Outgoing section has the following options:

**Email Address:** lets you enter the email address that messages from this category should come from. If you want your users to be able to reply to the categories using email, this should be the same address configured on the *Incoming* tab.

**Use Custom Outgoing Server:** allows you to use a different mail server than the one that is configured for the portal. If you check this box, more options appear.

**Server Name:** lets you enter the host name of the SMTP mail server you are using.

**Server Port:** allows you to specify the port on which your mail service is running.

**Use a Secure Network Connection:** allows you to use an encrypted connection if your server supports it.

**User Name:** lets you enter the login name on the mail server.

**Password:** lets you enter the password for the account on the mail server.

When finished adding your category, click *Save*. Add as many categories to your message boards as you wish.

Note that categories can have subcategories. You can add a number of top-level categories and then click on each one and add categories under that, to an unlimited level. For usability reasons, you don't want to nest your categories too deep, or your users will have trouble finding them. You can always add more categories as your message boards grow.

### Using the Message Boards [](id=lp-6-1-ugen04-using-the-message-boards-0)

Upon seeing Liferay's Message Boards portlet, your users will immediately recognize that the interface is similar to many other implementations they've seen before. Message boards are nothing new to the Internet, and many people have been using them for quite a long time. For that reason, Liferay's message boards will seem very familiar to your users.

Threads can be viewed in many ways. At the top of the portlet is a set of tabs: *Recent posts*, *My Posts*, *My Subscriptions*, and for administrative users, *Statistics* and *Banned Users*. The Recent Posts tab shows all posts from all categories by date, so you can keep up on all the most recent discussions in the message boards. The My Posts tab shows all of the posts for the user that is currently logged in. This is a convenient way to get back to a previous conversation in order to retrieve some pertinent information. The My Subscriptions tab allows a user to manage thread subscriptions. If you lose interest in a particular topic, you may want to visit this tab and unsubscribe from a thread.

For administrators, the Statistics tab shows the number of categories, the number of posts, and the number of participants in your message boards. It also has a list of who the top posters to your message boards are. The Banned Users tab shows all of the users who have been banned from posting on the message boards.

### Posting New Threads [](id=lp-6-1-ugen04-posting-new-threads-0)

To post a new thread simply select the *Post New Thread* button. You will see a message editing form. The body field on this form is different from that of the other portlets in Liferay. The reason for this is to support *BBCode*, which is a standard form of markup used in many message board products. Before BBCode was invented, many message board products would allow users to enter HTML to format their messages. This, however, enabled attackers to insert malicious code into the message board. BBCode was invented to provide users a way of formatting their messages without allowing them to enter HTML. Similarly, Liferay supports BBCode in the message boards portlet because the other editor--which is used for the Content Management System, the Blogs portlet, and other portlets--produces HTML. This is appropriate for those other portlets, as they are only used by privileged users, but it is not appropriate for the message boards. Besides this, many users of message boards are familiar with BBCode and are used to it, and the editor that is provided for Liferay's Message Boards portlet makes it very easy to use.

![Figure 4.17: Editing a Message Boards Post](../../images/05-editing-message-board-post.png)

The message boards editor is quite rich. It supports bold, italicized, underlined, and crossed-out text, links, images, colors, lists, tables, alignments, quotation blocks, code blocks, different fonts and font sizes, and more. There are even a bunch of smiley faces that you can use.

![Figure 4.18: Emoticons Available in the Editor](../../images/05-emoticons.png)

Users who have Moderator access to the board can modify the priority of messages. You can also use the editor to quote from messages that you are replying to, to insert emoticons, to add preformatted text, and more. Messages that are posted to the message boards are shown by default in a threaded view so that replies are attached to the proper parent message. This makes it easy to follow along with conversations.

When viewing a message board thread, users are given several options. At the top right of the thread are three icons, allowing users to view threads in a flat view, in a tree view, or in a combination view. A flat view shows all of the messages in the order in which they are posted. A tree view shows all of the messages in a threaded view, so that replies are next to the messages they are replying to. A combination view shows the threads at the top as subjects only, with the flat view underneath.

When viewing a thread, users can click links allowing them to post a new thread, subscribe to the thread they are viewing, or if they have administrative access, lock a thread or move a thread to another category. Subscribing to a thread causes Liferay to send the user an email whenever a new message is posted to the thread. If you have enabled the mailing list feature for the category in which the thread resides, users can simply reply to these messages in order to post back to the thread, without having to visit your site.

The Message Boards portlet is also highly integrated with Liferay's user management features. Posts on the message board show users' pictures if they have uploaded one for themselves, as well as the dates that users created an ID on your site.

### Message Board Administrative Functions [](id=lp-6-1-ugen04-message-board-administrative-functions-0)

The Message Boards portlet provides for the day to day administration of the message threads. You may wish to separate this function out by a role, and then delegate that role to one or more of your users. That would free you up to concentrate on other areas of your web site. To do this, you can create a role called Message Board Administrators. This role can be scoped by the portal, an organization, or a site. If you have a portal scoped role, members of this role will be able to administer any Message Boards portlet in the portal. If it is an organization or site scoped role, members of this role will be able to administer a Message Boards portlet in only the organization or site which assigned the role to them.

Go to the control panel and create this role. Once it is created, click *Actions &rarr; Define Permissions*. Click the *Add Permissions* dropdown list. Browse the list until you find the Message Boards portlet under the Site Content section and then click on it. You will then see a screen which allows you to configure the various permissions on the portlet.

![Figure 4.19: Defining Permissions for the Message Board Administrators Role](../../images/05-defining-permissions-message-board-admin-role.png)

Select the permissions you would like message board administrators to have and then click *Save*. You can add users to this role and they will inherit the permissions. Message Board administrators can perform all of the functions we have already presented, including creating and deleting categories and posting threads. In addition to these, a number of other functions are available.

#### Moving Threads [](id=lp-6-1-ugen04-moving-threads-0)

Many times a user will post a thread in the wrong category. Administrators may in this case want to move a thread to the proper category. This is very easy to do. You can select the *Actions* menu to the right of the thread and choose *Move Thread*. Or, if you are already viewing the thread and you have administrative access, there is a link at the top of the thread labeled *Move Thread*. Click this link. You will be presented with a simple form which allows you to select a category to which to move the thread and a check box which allows you to post a message explaining why the thread was moved. This message will be posted as a reply to the thread you are moving. When finished, click the *Move Thread* button and the thread will be moved.

#### Deleting Threads [](id=lp-6-1-ugen04-deleting-threads-0)

Users with administrative access to the message boards can delete threads. Sometimes users begin discussing topics that are inappropriate or that reveal confidential information. In this case, you can simply delete the thread from the message boards. This is easy to do. First, view the list of threads. Next to every thread is an *Actions* button. Click *Actions &rarr; Delete* to delete the thread. This does not prevent users from re-posting the information, so you may need to be vigilant in deleting threads or consider the next option.

#### Banning Users [](id=lp-6-1-ugen04-banning-users-0)

Unfortunately, sometimes certain users become abusive. If you wind up with a user like this, you can certainly make attempts to warn him or her that the behavior he or she is displaying is unacceptable. If this does not work, you can ban the user from posting on the message boards.

Again, this is very easy to do. Find any post which was written by the abusive user. Underneath the user's name/profile picture is a link called *Ban this User*. Click this link to ban the user from the message boards.

If after taking this action the user apologizes and agrees to stop his or her abusive behavior, you can choose to reinstate the user. To do this, click the *Banned Users* tab at the top of the Message Boards portlet. This will show a list of all banned users. Find the user in the list and select *Unban this User*.

#### Splitting Threads [](id=lp-6-1-ugen04-splitting-threads-0)

Sometimes a thread will go on for a while and the discussion completely changes into something else. In this case, you can split the thread where the discussion diverges and create a whole new thread for the new topic. Administrative users will see a *Split Thread* link on each post. To split the thread, click the link. You will be brought to a form which allows you to add an explanation post to the split thread. Click *Ok* to split the thread.

#### Editing Posts [](id=lp-6-1-ugen04-editing-posts-0)

Administrative users can edit anyone's posts, not just their own. Sometimes users will post links to copyrighted material or unsuitable pictures. You can edit these posts, which allows you to redact information that should not be posted or to censor profanity that is not allowed on your message boards.

#### Permissions [](id=lp-6-1-ugen04-permissions-3)

Permissions can be set not only on threads, but also on individual posts. You can choose to limit a particular conversation or a post to only a select group of people. To do this, click the *Permissions* link on the post and then select among the *Delete, Permissions, Subscribe, Update, and View* permissions for the particular role to which you want to grant particular access. This function can be used, for example, to allow some privileged users to post on a certain thread, while others are only allowed to view it. Other combinations of the above permissions are also possible. Next, let's discuss Liferay's Wiki portlet.

## Wikis [](id=wik-4)

Liferay's Wiki portlet, like the Message Boards portlet, is a full-featured wiki application which has all of the features you would expect in a state of the art wiki. Again, though, it has the benefit of being able to take advantage of all of the features of the Liferay platform. As such, it is completely integrated with Liferay's user management, tagging, and security features.

So, what is a wiki? Basically, a wiki is an application which allows users to collaboratively build a repository of information. There are, of course, many implementations of this idea, the most famous of which is Wikipedia. Wikipedia is a full online encyclopedia developed collaboratively by users from all over the world, using a wiki. Another example would be Liferay's wiki, which is used for collaborative documentation of the Community Edition of Liferay Portal.

A wiki application allows users to create and edit documents and link them to each other. To accomplish this, a special form of markup is used which is sometimes called wikitext. Unfortunately, the proliferation of many different wiki applications resulted in slightly different syntax for wikitext in the various products, as each new wiki tried to focus on new features that other wikis did not have. For that reason, a project called WikiCreole was started. This project resulted in the release of WikiCreole 1.0 in 2007, which is an attempt to define a standard wiki markup that all wikis can support.

Rather than define another wikitext syntax, Liferay's Wiki portlet supports WikiCreole as its syntax. This syntax is a best-of-breed wiki syntax and should be familiar to users of other wikis. The portlet provides a handy cheat sheet for the syntax on the page editing form, with a link to the full documentation if you wish to use some of WikiCreole's advanced features.

### Getting Started with the Liferay Wiki [](id=lp-6-1-ugen04-getting-started-with-the-liferay-wiki-0)

The Wiki portlet works just like the other portlets developed by Liferay. Add the portlet to a page using the *Add &rarr; More* menu and then click *Configuration* in the portlet menu in the Wiki portlet's title bar. You'll see some options are likely to be familiar to you by now such as sharing the application with websites, Facebook, Google Gadgets, etc. You will also notice that the communication tab has some additional options not seen in the other portlets.

![Figure 4.20: Communication Tab of the Wiki Portlet](../../images/05-wiki-configuration.png)

The communication tab of the configuration window allows you to configure communication across portlets, using predefined public render parameters. From here you can modify six public render parameters: categoryId, nodeId, nodeName, resetCur, tag, and title. For each parameter you can:

-   Ignore the values for this parameter that come from other portlets. For example, the wiki portlet can be used along with the tags navigation portlet. When a user clicks on a tag in the tags navigation portlet, the wiki shows a list of pages with that tag. In some cases an administrator may want the wiki portlet to always show the front page independently of any tag navigation done through other portlets. This can be achieved by checking the Ignore check box so that the values of the parameter coming from those other portlets are ignored.

-   Read the value of a parameter from another portlet. This is an advanced but very powerful option that allows portlets to communicate without configuring it beforehand. For example, imagine that the wiki portlet is used to publish information about certain countries. Imagine further that a custom portlet that allows browsing countries for administrative reasons was written and placed on the same page. We could associate to this second portlet a public render parameter called *country* to designate the name of the country. Using this procedure, we can cause the wiki to show the information from the country being browsed through in the other portlet. You can do this here for the wiki by setting the value for the title parameter to be read from the country parameter of the other portlet.

Once you have set the options the way you want them, click *Save*.

### Managing Wikis [](id=lp-6-1-ugen04-managing-wikis-0)

The Wiki portlet can contain many wikis. By default, it contains only one, called *Main*. To manage Wikis, navigate to the *Control Panel* and select *Wiki*. This page allows you to add, modify, and delete wikis. The Main wiki has already been added for you.

At the top of this screen is a *Permissions* button. Clicking this allows you to define which roles have access to create wikis. If you have created a specific role for creating wikis, you can click the box in the *Add Node* column and then click *Submit*, and that role will have access to create new wikis in this portlet.

Clicking the *Add Wiki* button prompts you to enter a name and description for the new wiki. You can also set up some default permissions. When you create a new wiki, it appears in a list at the top of the main page of the Wiki portlet.

Next to each wiki in the list of wiki nodes is an *Actions* button. This button contains several options:

**Edit:** lets you edit the name and description of the wiki.

**Permissions:** lets you define what roles can add attachments to wiki pages, add pages to the wiki, delete pages, import pages to the wiki, set permissions on the wiki, subscribe to the wiki, update existing pages, and view the wiki.

**Import Pages:** allows you to import data from other wikis. This lets you migrate off of another wiki which you may be using and use the Liferay wiki instead. You might wish to do this if you are migrating your site from a set of disparate applications (i.e. a separate forum, a separate wiki, a separate content management system) to Liferay, which provides all of these features. Currently, MediaWiki is the only wiki that is supported, but others are likely to be supported in the future.

**RSS:** opens a new page where you can subscribe to an RSS feed using Live Bookmarks, Google, or Yahoo.

**Subscribe:** allows you to subscribe to a wiki node, and any time a page is added or updated Liferay will send you an email informing you what happened.

**Delete:** deletes the wiki node.

To go back to your wiki, click on its name in the list of wikis. Note that there is also a wrench icon leading to a configuration menu on this portlet in the control panel. This contains several other options which you may have seen on other portlets.

The *Email From*, *Page Added Email*, and *Page Updated Email* tabs are similar to the ones for notification email settings for other portlets, allowing you to customize who wiki emails come from and the format and text of the email that is sent when a page is added or updated.

The *Display Settings* tab gives you several options for how the wiki should be displayed. *Enable Related Assets*, *Enable Page Ratings*, *Enable Comments*, and *Enable Comment Ratings* are similar to the same options in other portlets. They give you the ability to set how you want users to interact with wiki documents: a little, a lot, or not at all. Below this, you can set which wikis are visible in the Wiki portlet by default and which are hidden. You might host two wikis on a given site, exposing one to the public and keeping the other private for site members.

Finally, the Wiki portlet also supports RSS feeds as the other collaboration portlets do, and you can configure its options in the *RSS* tab.

### Adding and Editing Wiki Pages [](id=lp-6-1-ugen04-adding-and-editing-wiki-pages-0)

By default, there is one page added to your wiki, called *FrontPage*. To get started adding data to your wiki, click the *Edit* link at the top right of the portlet. You will be brought to a blank editing page.

![Figure 4.21: Editing the Default Page in the Wiki Portlet](../../images/05-editing-wiki-page.jpg)

You can now begin to add content to the page. Notice that there is a very convenient "cheat sheet"? which can help with the wiki syntax. You can use this syntax to format your wiki pages. Consider, for example, the following wiki document:

== Welcome to Our Wiki! ==

This is our new wiki, which should allow us to collaborate on
documentation. Feel free to add pages showing people how to do stuff.
Below are links to some sections that have already been added.

[[Introduction]]

[[Getting Started]]

[[Configuration]]

[[Development]]

[[Support]]

[[Community]]

This would produce the following wiki page:

![Figure 4.22: Wiki Text Added to Front Page](../../images/05-wiki-front-page.png)

This adds a simple heading, a paragraph of text, and several links to the page. Since the pages behind these links have not been created yet, clicking one of those links takes you to an editing screen to create the page. This editing screen looks just like the one you used previously when you wrote the front page. Liferay displays a notice at the top of the page stating that the page does not exist yet, and that you are creating it right now. As you can see, it is very easy to create wiki pages. All you have to do is create a link from an existing page. Note that at the top of the screen you can select from the Creole wiki format and the HTML editor that comes with Liferay. We recommend that you stick with the Creole format, as it allows for a much cleaner separation of content and code. If you want all of your users to use the Creole format, you can disable the HTML format using the `portal-ext.properties` file. See chapter 14 for details about how to configure this.

At the bottom of the page editing screen, you can select *Categories* for the article. Categories are hierarchical lists of headings under which you can create wiki pages. This allows you to organize your content in a more formal fashion. You can create categories using the control panel, in the *Categories* section.

### Page Details [](id=lp-6-1-ugen04-page-details-0)

When viewing a page, you can view its details by clicking the *Details* link which appears in the top right of the page. This allows you to view many properties of the page. There are several tabs which organize all of the details into convenient categories.

#### Details [](id=lp-6-1-ugen04-details-0)

The Details tab shows various statistics about the page, and also allows you to perform some actions on the page.

**Title:** displays the title of the page.

**Format:** displays the format for the page -- either Creole or HTML.

**Latest Version:** displays the latest version of the page. The wiki portlet automatically keeps track of page versions whenever a page has been edited.

**Created By:** displays the user who created the page.

**Last Changed By:** displays the user who last modified the page.

**Attachments:** displays the number of attachments to the page.

**RSS Subscription:** displays links which allow you to subscribe to the page as an RSS feed in three formats: RSS 1.0, RSS 2.0, and Atom 1.0.

**Email Subscription:** contains links allowing you to subscribe to the entire wiki or just to this page.

**Advanced Actions:** contains links allowing you to modify the permissions on the page, make a copy of the page, move (rename) the page, or delete the page.

#### History [](id=lp-6-1-ugen04-history-0)

This tab shows a list of all of the versions of the wiki page since it was created. You can revert a page back to a previous state and you can also compare the differences between versions by selecting the versions and then clicking the *Compare Versions* button.

#### Incoming/Outgoing Links [](id=lp-6-1-ugen04-incomingoutgoing-links-0)

The next two tabs are for incoming and outgoing links. These are wiki links to and from the page. You can use this tab to examine how this page links to other pages and how other pages link back to this page.

#### Attachments [](id=lp-6-1-ugen04-attachments-0)

The last tab is for attachments. You can attach any file to the wiki. This is mostly used to attach images to wiki articles which can then be referenced in the text. Referencing them using the proper WikiCreole syntax renders the image inline, which is a nice way to include illustrations in your wiki documents.

### Navigating in the Wiki Portlet [](id=lp-6-1-ugen04-navigating-in-the-wiki-portlet-0)

At the top of the portlet is a list of links which allow you to navigate around the wiki. Next to the *Manage Wikis* button is a list of wikis that are currently created in the portlet. Simply click on the wiki's name to begin browsing that wiki. After this is a set of navigation links:

**Recent Changes:** takes you to a page which shows all of the recently updated pages.

**All Pages:** takes you to a flat, alphabetical list of all pages currently stored in the wiki.

**Orphan Pages:** takes you to a list of pages that have no links to them. This can happen if you take a link out of a wiki page in an edit without realizing it's the only link to a certain page. This area allows you to review wiki pages that are orphaned in this way so that you can re-link to them or delete them from the wiki if they are no longer relevant.

**Draft Pages:** takes you to a list of pages which have not yet been published. Users can edit pages and save their changes as drafts. They can come back later to finish their page changes and publish them once they have been approved.

**Search:** allows you to a term here and click the *Search* button to search for items in the wiki. If the search term is not found, a link will be displayed which allows you to create a new wiki page on the topic for which you searched. 

Next, let's look at Liferay's Knowledge Base, an application that's a little bit similar to the Wiki portlet.

## Knowledge Base [](id=knowledge-base)

Liferay's Knowledge Base portlet provides a means for creating and organizing articles within a site. The knowledge base is perfect for creating and organizing information more formally than in a wiki. For example, it can be used to organize and display professional product documentation. It's easy to set up the knowledge base with a workflow that requires articles to be approved before they are published. Additionally, it allows administrators to create article templates. Templates can be used to insure that certain kinds of articles possess a common structure and include certain kinds of information. Knowledge base articles can be categorized to make them easy to find. They can also be organized hierarchically to form complete books or guides. The Knowledge Base portlet is available as an app from Liferay Marketplace. Please see chapter 2 for installation instructions.

### Knowledge Base Display Portlet [](id=lp-6-1-ugen04-knowledge-base-display-portlet-0)

The Knowledge Base app actually consists of four portlets that can be placed on site pages as well as one that adds a page to the control panel. The four portlets that can be placed on a page are Knowledge Base (Display), Knowledge Base Search, Knowledge Base Article, and Knowledge Base Section. When placed on a page, the Knowledge Base display portlet presents many of the same options to an administrator that are available from the Knowledge Base page of the control panel.

![Figure 4.23: Knowledge Base Display Portlet](../../images/liferay-collaboration-kb-display-portlet.png)

You can use the four links at the top of the Knowledge Base display portlet to control what it displays.

*Knowledge Base Home:* shows you a list of all top level articles.

*Recent Articles:* shows you a list of articles in order from most recent activity to least recent activity.

*Administrator:* shows you a list of all articles, regardless of which ones are parents or children of the others.

*My Subscriptions:* shows you a list of articles you are subscribed to.

The *Add Article* button is available from the Knowledge Base Home or Administrator view of the Knowledge Base display portlet or from the Articles tab of the Knowledge Base page of the control panel. Use this button to create an article for the knowledge base. When creating an article, you can use the same WYSIWYG editor that you used to create wiki pages. Articles, however, are not the same as wiki pages: they must be created in HTML, not MediaWiki or Creole. Click the *Source* button in the editor to view the HTML source of what you've written or write some HTML yourself.

![Figure 4.24: New Knowledge Base Article](../../images/liferay-collaboration-kb-new-article.png)

In addition to entering a title and creating content for your article, you can use the editor to add attachments, add tags, and set permissions. By default, view permission is granted to the guest role, meaning that anyone can view your article. After you're done using the editor, you can save it as draft and continue working on it later, or you can submit it for publication. Your article may need to be approved before being published, depending on the workflow defined for your portal.

You can find the *Permissions* button next to the Add Article button in the Knowledge Base display portlet or on the Knowledge Base page of the control panel. Click this button to define permissions that apply to the Knowledge Base display portlet generally, not to particular articles. Here, you can define which roles can add articles and templates, which are granted knowledge base administrator privileges, which can change permissions on articles, which can subscribe to articles, and which can view templates. 

![Figure 4.25: Knowledge Base Permissions](../../images/liferay-collaboration-kb-permissions.png)

Users may need to be granted access to the knowledge base page of the control panel in order to exercise some of the above permissions. For example, suppose that the user role has been granted the Add Article and the View Templates permissions. A user will be able to add articles from the knowledge base display portlet but will need access to the knowledge base page of the control panel in order to view templates. Note that the Knowledge Base (Display) permissions are distinct from the Knowledge Base (Admin) portlet. The display permissions define what a user can do with the Knowledge Base display portlet on a page while the admin permissions define what a user can do on the Knowledge Base page of the control panel.

### Knowledge Base Page of the Control Panel [](id=lp-6-1-ugen04-knowledge-base-page-of-the-control-panel-0)

The Knowledge Base page of the control panel has two tabs: one for articles and one for templates. The articles tab shows all the articles in the knowledge base and lets you perform actions on them. The templates tab shows all the templates defined in the knowledge base and lets you perform actions on them.

![Figure 4.26: Knowledge Base Control Panel Page](../../images/liferay-collaboration-kb-control-panel.png)

Administrators can perform the following actions on an article:

*View:* displays an article. From here, you can add a child article, edit the article, change its permissions, move it, or delete it.

*Edit:* allows you to change the title and content of an article as well as add attachments, select topics, and add tags.

*Permissions:* lets you configure the permissions on a specific article.

*Subscribe:* lets you to choose to be notified of any updates to a particular article.

*Move:* lets you change an article's position in the hierarchy by choosing a new parent article for it.

*Delete:* lets you remove an article from the knowledge base.

These actions are similar to the ones that can be performed from the Administrator view of the Knowledge Base display portlet. However, the Knowledge Base display portlet is intended to be placed on a page for the end user so an additional action is available: *RSS* is a link to an RSS feed of an article. Also, the *View* action is only available from the control panel since the Knowledge Base Article portlet can be used to display an article on a page.

The templates tab of the Knowledge Base page of the control panel allows administrators to create templates to facilitate the creation of articles. A template basically functions like a starting point for the creation of certain types of articles. Click the *Add Template* button on the Templates tab of the Knowledge Base page of the control panel to create a new template. 

![Figure 4.27: Adding a New Template From the Control Panel](../../images/liferay-collaboration-kb-new-template.png)

Navigate back to the templates tab of the Knowledge Base page of the control panel. You can perform the following actions on a template:

*View:* displays a template. From here, you can use the template to create an article, edit the template, modify the permissions on the template, or delete it. 

*Edit:* allows you to change the title and content of a template.

*Permissions:* allows you to configure the permissions on a template. You can choose roles to have permission to update, view, delete, or change the permissions on templates.

*Delete:* lets you remove a template from the knowledge base.

To use a template to create a new article, you have view the template and then click *Use this Template*. This brings you to the New Article editor with the contents of the template copied for you.

### Knowledge Base Article Portlet [](id=lp-6-1-ugen04-knowledge-base-article-portlet-0)

The Knowledge Base Article portlet can be placed on a page to display a an entire article. When you first place this portlet on a page it displays the message *Please configure this portlet to make it visible to all users*. This message is a link to the configuration dialog box for the portlet. Click *Select Article* to choose an article to display. Pick an article and then click *Save*. When your page refreshes it will display the article in the portlet. 

![Figure 4.28: Knowledge Base Article Portlet](../../images/liferay-collaboration-kb-article-portlet.png)

The Knowledge Base Article portlet allows users to rate and comment on the article it displays. There are also links at the top of the portlet that users can use to subscribe to an RSS feed of the knowledge base, subscribe to the article, view the history of the article, or print the article.

### Knowledge Base Section Portlet [](id=lp-6-1-ugen04-knowledge-base-section-portlet-0)

The Knowledge Base Section portlet allows administrators to selectively show articles associated with a specific section. For example, a news site might have a *World* section, a *Politics* section, a *Business* section, and an *Entertainment* section. In order to use sections, you need to set the `admin.kb.article.sections` property in your knowledge base portlet's `portlet.properties` file and redeploy the portlet. You can find the `portlet.properties` file in the knowledge base portlet's source directory. Updating the one in your server's directory won't work. Use comma delimited section names to set the property, like `admin.kb.article.sections=World,Politics,Business,Entertainment`, for example. 

Once you have defined some sections in your knowledge base's `portlet.properties` file, your users will see a multi-select box in the Add Article and Edit Article screens that allows them to select which section an article belongs to. You can add any number of Knowledge Base section portlets to a page and you can configure each portlet to display articles from any number of sections.

![Figure 4.29: Knowledge Base Section Portlets](../../images/liferay-collaboration-kb-section-portlet.png)

The Knowledge Base section portlet has some additional configurations that allow an administrator to select a display style (title or abstract), an article window state (maximized or normal), how to order the articles, how many articles to display per page, and whether or not to show pagination.

### Knowledge Base Navigation [](id=lp-6-1-ugen04-knowledge-base-navigation-0)

Wikis often have deeply nested articles that can be hard to find by browsing. Liferay's knowledge base's ability to selectively display articles makes it easier to browse than a Wiki. The knowledge base also features some other aids to navigation. The Knowledge Base Search portlet allows you to search for articles in the knowledge base. This portlet presents the search results to you in order from most relevant to least relevant. 

![Figure 4.30: Knowledge Base Search Portlet](../../images/liferay-collaboration-kb-search-portlet.png)

You can also use the Categories Navigation portlet in conjunction with the Knowledge Base display portlet. When both of these portlets are placed on a page you can select a topic in the Categories Navigation portlet and the Knowledge Base display portlet will show all of the articles that match the topic. You can create topics from the Categories page of the control panel.

![Figure 4.31: Knowledge Base Category Navigation Portlet](../../images/liferay-collaboration-kb-catnavandkbdisplay-portlets.png)

You can select topics for articles when you are creating or editing them. Next, let's take a look at the Documents and Media library, Liferay's general mechanism for creating and storing files in the portal.

## Documents and Media [](id=documents-and-media)

Liferay's Documents and Media library provides a mechanism for storing files online using the same type of structure that you use to store files locally. You can use it to store files of any kind; it serves as a virtual shared drive. The Documents and Media portlet of Liferay 6.1 takes its name from the fact that it represents a redesign of two portlets from previous versions: the Document Library and the Image Gallery. First, the Documents and Media library serves as a repository for all types of files; there's need to store image files in a separate Image Gallery anymore. Second, Liferay 6.1's Media Gallery portlet does not serve as a repository but just displays selected content from the Documents and Media library. It can display image, audio, and video files. Other features introduced with Liferay 6.1's Documents and Media library include customizable document types and metadata sets, automatic document preview generation, and support for mounting multiple external repositories. The new document types and metadata sets are an addition to, not a replacement for, the portal's system of tags and categories. Let's start exploring how to use the Documents and Media portlet.

### Getting Started with the Documents and Media Portlet [](id=lp-6-1-ugen04-getting-started-with-the-documents-and-media-portlet-0)

The Documents and Media portlet is non-instanceable. This means that each page on your portal can host at most one such portlet. Furthermore, if you add multiple Documents and Media portlets to pages in the same site, these portlets will share the same data sets since they are scoped by site by default. However, you can add multiple Documents and Media *Display* portlets to a page. Then you can choose content from actual Documents and Media repositories to display. Remember that users, by default, have their own personal sites with public and private pages. They can use their personal sites to host document Documents and Media portlets for storing or sharing their own files.

![Figure 4.32: Initial View of the Documents and Media Portlet](../../images/05-document-library.png)

The default view of the Documents and Media portlet displays the contents of the *Home* folder. The links on the left side of the portlet windows are filters. You can use these filters to choose what you want the main window of the portlet to display. *Recent Documents* displays documents that users have recently uploaded, edited, or downloaded. *My Documents* shows *your* documents; in other words, the documents that you have uploaded. *Basic Document* and the document types listed below it are also filters. If you click on one of these filters, the main portlet window shows only documents that belong to the selected document type. When you add custom document types, which we discuss below, they are added to the filter list. Next, let's look at how to navigate around Documents and Media. 

### Navigating the Documents and Media Portlet [](id=lp-6-1-ugen04-navigating-the-documents-and-media-portlet-0)

In the main window of the Documents and Media portlet, you can click on a document to view details about it. Its version number, version history, status, as well as its uploader and the user who last edited it. Depending on the document, some automatically extracted metadata may also be displayed, such as the document creator, author, title, content type, creation date, and last modification date. In the case of audio or video files, the duration would also be displayed. You can perform several actions on the document here:

**Download:** lets you download the document.

**Get URL:** displays the URL of the document on the server.

**Get WebDAV URL:** displays the WebDAV URL of the document on the server. See the WebDAV access section below for more information.

**Edit:** lets you change contents of a document, point it to a different file, change its title, description, or document type, or add tags, categories, or related assets.

**Move:** lets you choose a new location in the Documents and Media repository to store the document.

**Checkout/Checkin:** prevents others from modifying the document while you are working. Other users can still view the current version of the document if they have permission. You can check the document back in when you're done working.

**Permissions:** allows you to configure file-specific permissions for the document.

**Delete:** lets you remove the document from the Documents and Media library.

If comments are enabled, you can also view comments, add comments, or subscribe to comments about documents. Comments are enabled by default.

![Figure 4.33: Viewing a Document](../../images/05-doclib-document-view.png)

The menu at the top of the Documents and Media portlet contains Actions, Add, Sort By, and Manage buttons. There are also buttons for switching between icon view, descriptive view, and list view. If there are lots of documents in the Documents and Media library, the search field can help you find the documents you're looking for. If your portlet contains more documents than it can display at once, you can use the navigation tool at the bottom of the portlet window to either switch your view to another page or configure the page to display more documents per page. 

#### Actions [](id=lp-6-1-ugen04-actions-0)

The Actions menu will only be displayed if you have selected one or more documents with the check boxes.

**Cancel Checkout:** lets you check in a document that you had checked out but did not make any changes to. Using this option will prevent the Documents and Media portlet from incrementing the document's version number and saving an identical version of the document.

**Checkin:** lets you check in a document that you have edited. Its version number will increment and the previous version will be saved.

**Checkout:** lets you check out a document that you would like to edit. This option prevents anyone else from modifying it while you are working.

**Move:** allows you to choose a new location for a document or folder within the portlet's file system. You can move multiple documents and folders at the same time. Moving documents and folders is also possible via drag & drop.

**Delete:** allows you to remove a document or folder from the portlet. You can delete multiple documents and folders at the same time.

#### Add [](id=lp-6-1-ugen04-add-0)

From the Add button, you can add documents, folders, and shortcuts just like on your local file system.

**Folder:** lets you create a new location in your portlet's file system.

**Shortcut:** allows you to create a shortcut to any document that you have read access for. You can set permissions on the shortcut to specify who can access the original document through the shortcut. 

**Repository:** is a new feature of Liferay 6.1. This option allows you to add an entirely new repository to your Documents and Media portlet. To do this you need to specify the repository type and choose an ID. If you are using the AtomPub protocol you'll also have to specify the AtomPub URL.

**Multiple Documents:** allows you to upload several documents at once.

**Basic Document:** allows you upload a single file that you would like the default document type, "Basic Document," to apply to. By default, basic documents are not described by any metadata sets.

The remaining items in the Add menu are default document types that are each described by a unique metadata set. When you add a document belonging to a specific document type, you're presented with a form to not only specify the file to upload but also to fill out the fields defined by the document type's metadata set. We describe the "Contract" document type by way of example.

**Contract:** lets you upload a file that you would like the "Contract" document type to apply to. This document type is intended to be used to describe legal contracts. By default, contracts are described by effective date, expiration date, contract type, status, legal reviewer, signing authority, and deal name fields. Document types are discussed below. 

Any custom documents types that have been defined also appear in the Add menu. If a document type has been created that matches the document you would like to upload, you can select that document type from the Add menu. This will associate the metadata fields associated with the document type to your document and you will be asked to fill out the fields.

#### Sort [](id=lp-6-1-ugen04-sort-0)

You can sort the items displayed in the main window of the Documents and Media portlet using the Sort By menu. You can sort by title, create date, modified date, read count, or size.

**Title:** lets you alphabetically sort documents by title.

**Create Date:** lets you sort documents by the time they were created.

**Modified Date:** lets you sort documents by the last time they were modified.

**Read Count:** lets you sort documents by the number of times they were viewed.

**Size:** lets you sort documents by how much disk space they use.

#### Manage [](id=lp-6-1-ugen04-manage-0)

The Manage menu allows you to view the names of document types and metadata sets, as well as the last times they were edited.

**Document Types:** shows you a list of defined document types.

**Metadata Sets:** shows you a list of defined metadata sets as well as their portal IDs.

### Repositories [](id=lp-6-1-ugen04-repositories-0)

Adding repositories in Documents and Media is a new feature in Liferay 6.1. Documents and Media allows to connect to multiple third-party repositories that support CMIS 1.0 with AtomPUB and Web Services protocols. You can add new repositories from the UI by clicking the *Add* button from the Home folder. Repositories can only be mounted in the Home folder.

![Figure 4.34: Adding a new repository](../../images/05-new-repository.png)

The admin must ensure that the same credentials and authentication are being used in Liferay and in the external repository. This is normally synchronized using a mechanism like LDAP. If you don't have LDAP, you need to ensure manually that the credentials and authentication are the same. In order to authenticate with the third-party repository you need to enable the following property:

	session.store.password=false

The repository is displayed in the left side of the window in the Home folder.

![Figure 4.35: Viewing a repository](../../images/05-repository.jpg)

### Document Types and Metadata Sets [](id=lp-6-1-ugen04-document-types-and-metadata-sets-0)

Customizable document types and metadata sets are new features in Liferay 6.1. When a user assigns a document type to a document, the user is required to fill out the fields defined by the metadata set of the document type. This encourages users not to forget to enter important information about their documents. For example, you could create a "copyrighted" document type and require users to enter a license for all "copyrighted" documents. More importantly, document types and metadata sets can improve document searchability. The values that users enter into the fields determined by their document type's metadata set become searchable entities within the portal. You can use Liferay's search portlet to search for these terms. Document types and metadata sets are accessible from the Manage Button at the top of the Documents and Media portlet window.

![Figure 4.36: Document Types Dialog Box](../../images/05-document-types.png)

You can add a new document type using the Add button at the top of the dialog box. To do so, you need to define one or more metadata sets to associate with your document type. When creating a new document type, you can define "Main Metadata Fields" or select "Additional Metadata Fields". Main metadata fields are directly tied to their document type and cannot be made available to other document types. Additional metadata fields, by contrast, can be defined independently and can be used in many different document types. You can differentiate the document types that implement the same additional metadata set by defining different main metadata fields for them. However, Additional metadata fields need to be defined and saved before creating a document type that will implement them. 

![Figure 4.37: Adding a New Document Type](../../images/05-new-document-type.png)

As an example, we could create a document type called "Syllabus" and define a metadata set. What metadata should we associate with syllabi? Let's choose for our syllabi to have course title, professor, semester, course description, and course requirements fields. All syllabi in our portal should maintain entries for these fields. This ensures that a syllabus will show up in a portal search if its course title, professor, or semester is searched for. Since we don't want to use our metadata set for any document type other than "Syllabus," let's create our metadata set under the Main Metadata Fields area. Alternatively, we could create our metadata set independently using *Manage &rarr; Metadata Sets &rarr; Add* and then select it as an Additional Metadata Field. 

![Figure 4.38: Selecting Additional Metadata Sets](../../images/05-selecting-additional-metadata-sets.png)

You can view, edit, or add metadata sets from the *Manage &rarr; Metadata* window. A metadata set consists of a group of fields. If you click the Add button, can use same UI for defining a metadata set that you used in the Add Document Type window.

![Figure 4.39: Adding a New Metadata Set](../../images/05-new-metadata-set.png)

Make sure the Fields tab is selected on the left. Then, to define a metadata set, just choose fields to use from the area on the left and drag and drop them into the area on the right. The drag and drop interface allows for nested fields so you need to be careful about where you drop the fields. Default values, mouse-over tips, widths, and other settings can be configured for most fields. To configure these settings, just double-click on a field from the area on the right. This automatically selects the Settings tab on the left. Then double-click on a value to edit. Liferay supports the following kinds of fields for metadata sets:

**Boolean:** is just a checkbox.

**Date:** lets you enter a date. A valid date format is required for the date field. However, you don't have to enter a date manually. When you select the date field a mini-calendar pops up which you can use to select a date.

**Decimal:** lets you enter a decimal number. The value will be persisted as a double.

**Documents and Media:** lets you select a file from one of the portal's Documents and Media libraries.

**File Upload:** lets you select file to upload from your local system.

**Integer:** lets you enter an integer. The value will be persisted as an int.

**Number:** lets you enter a decimal number or an integer. The value will be persisted either as a double or an int, depending on the type of input.

**Radio:** displays several clickable options. The default number is three but this is customizable. Only one option can be selected at a time.

**Select:** is just like the radio field except that the options are hidden and have to be accessed from a drop-down menu.

**Text:** lets you enter a single line of text.

**Text Box:** is just like the text field except you can enter multiple lines of text or separate paragraphs.

Remember that metadata sets created independently are reusable. Once they have been created they can be included in any number of document types as additional metadata sets. Next, let's take a look at tags. Tags can be attached to most forms of web content that can be created in Liferay, including documents.

### Automatic Preview Generation [](id=lp-6-1-ugen04-automatic-preview-generation-0)

Liferay 6.1 generates previews of the documents added to Documents and Media whenever it's possible. The content is displayed using a customized viewer depending on the type of the content.

**Office documents:** preview of supported office documents (.docx, .pdf, .odt, .ppt, etc.) and online reading.

**Multimedia:** play various multimedia (video and audio) formats from the browser.

Liferay uses external tools and libraries to support this feature. You may need to install them and enable them in Liferay.

**OpenOffice/LibreOffice:** for office documents start [*OpenOffice*](http://www.openoffice.org) or [*LibreOffice*](http://www.libreoffice.org) in server mode by running:

	soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;"

and enable either through External Services in Control Panel or in `portal-ext.properties`:

	openoffice.server.enabled=true

**ImageMagick:** PDF conversion is available out of the box. While a default implementation of image generation for document previews and thumbnails is provided, the quality can be improved using [*ImageMagick*](http://www.imagemagick.org), which also requires [*GhostScript*](http://www.ghostscript.com). This can be enabled in the external services control panel or in `portal-ext.properties`:

	imagemagick.enabled=true

**Xuggler:** Audio and video player configuration requires the installation of [*Xuggler*](http://www.xuggle.com/xuggler/downloads/build.jsp) and the configuration of related environment variables. This can be enabled in the external services control panel or in `portal-ext.properties`:

	xuggler.enabled=true

With the above tools installed and enabled, Documents and Media looks like this:

![Figure 4.40: Previews in Documents and Media](../../images/05-previews.jpg)

You can view a document with a customized viewer that allows you to navigate through the different pages of the document and read its content.

![Figure 4.41: Viewing an office document](../../images/05-document-preview.jpg)

You can view a multimedia document (audio or video) and play it online. If the browser supports HTML5, it uses the native player of the browser. Otherwise it falls back to a Flash player.

![Figure 4.42: Playing a video](../../images/05-video-preview.png)

Document previews are powerful, and help users to more successfully browse the media to find what they're looking for. 

### Automatic extraction of RAW Metadata [](id=lp-6-1-ugen04-automatic-extraction-of-raw-metadata-0)

When adding new documents or viewing existing documents, a process is triggered automatically that extracts the file's metadata. The library used by this process is TIKA and it's already included in Liferay out of the box.

You can see the metadata when viewing the document, in the right side of the window.

### Document type restrictions and workflow per folder [](id=lp-6-1-ugen04-document-type-restrictions-and-workflow-per-folder-0)

You can force users to add only certain document types to a folder. By default, child folders inherit the restrictions of their parent folder. You can change this behavior by editing the folder and selecting the allowed document types.

![Figure 4.43: Restrict Marketing folder to use specific document types](../../images/05-document-type-restriction.png)

If workflow is enabled, you can specify different workflow definitions per folder. Furthermore, you can specify different workflow definition per document type and per folder. You can set this by editing the folder. Then, the UI will look like this:

![Figure 4.44: Restrict Marketing folder to use specific document types and workflow](../../images/05-document-type-workflow-restriction.png)

Document types are a powerful way to enforce rules for documents uploaded by users. Next, we'll see a way to make it incredibly easy for users to access documents stored in Liferay's Documents and Media repositories. 

### WebDAV access [](id=lp-6-1-ugen04-webdav-access-0)

Wouldn't it be great if you could access documents and folders belonging to Liferay's Documents and Media library from your own machine's file manager? You can, thanks to the Documents and Media libray's WebDAV integration. WebDAV stands for Web-based Distributed Authoring and Versioning. It's a set of methods based on HTTP that allows users to create, edit, move, or delete files stored on web servers. WebDAV is supported by most major operating systems and desktop environments, including Linux (both KDE and GNOME), Mac OS, and Windows.

Suppose you've created an *Image Gallery* folder using a Documents and Media portlet and uploaded some images to it. Portal users with the appropriate permissions can access this folder and the image files it contains using a browser and Liferay's web interface. WebDAV provides an alternative way to do this using a file manager instead of a web browser. To access a folder stored in a Documents and Media portlet on a remote server, you'll need log in credentials for the portal and the WebDAV URL of the folder you'd like to access. 

Next, navigate to the Documents and Media portlet hosting the folder you'd like to access. Mouse over the folder (*Image Gallery* for our example) and select *Access from Desktop*.

![Figure 4.45: Select *Access from Desktop* to get the WebDAV URL of a folder.](../../images/webdav-access-from-desktop.png)

Copy the WebDAV URL. On Windows, right-click on My Computer and select *Map Network Drive*. Select an unused drive, paste the WebDAV URL, and click *Finish*. You're prompted to enter your Liferay credentials and then, provided you have the required permissions, the *Image Gallery* folder appears. You can now add, edit, move, or delete files in this directory. 

On Mac OS X, select *Go* &rarr; *Conect to Server* in Finder. Then enter the WebDAV URL of the folder you'd like to access in the Server Address field, click *Connect*, and you should be prompted for your Liferay credentials. 

On Linux, you must slightly modify the WebDAV URL of your folder in your file manager. For KDE's Dolphin, change the URL's protocol so that it says `webdav://` instead of `http://`. For GNOME's Nautilus, change the URL's protocol so that it says `dav://` instead of `http://`. Then press *Enter* and you're prompted for your Liferay credentials.

Note that Liferay increments the version numbers of files edited and uploaded via WebDAV so you don't have to worry that using your file manager will bypass the functionality of Liferay's web interface. The Documents and Media application is a powerful way to manage any types of files your users need to use. Next, let's look at how tags work in the portal.

## Getting Started with Liferay Sync [](id=lp-6-1-ugen04-getting-started-with-liferay-sync-0)

Liferay Sync, released in February 2012, is an add-on product for Liferay 6.1 CE and EE that synchronizes files from your Liferay server directly to users' desktop and mobile environments. With Liferay Sync, your users can publish and access shared documents and files from their native environments without using a browser. Windows and Mac OS desktops, and iOS-based mobile platforms are currently supported. As users add and collaborate on documents and files, Liferay Sync automatically synchronizes them across all configured Sync clients. Liferay Sync is fully integrated into the Liferay Platform so that features such as authentication, versioning, workflow, and social collaboration extend naturally into the supported environments. Liferay Sync also makes documents available offline with automatic synchronization upon reconnection.

### How does it work? [](id=lp-6-1-ugen04-how-does-it-work-0)

Liferay Sync manages documents and site information through Liferay 6.1's built-in web services. Clients securely communicate to Liferay using user-supplied credentials such that each user can only access those documents and sites for which they have permission. Changes made through Liferay Sync are immediately available to the rest of the Liferay Platform, including users accessing Liferay through traditional web-based interfaces.

For desktop environments, a new folder structure is created and used for synchronizing files. Files found therein can be treated as any ordinary files. Credentials, sync frequency, and other folder options can be configured in-client. Native desktop notification events keep you abreast of what Sync is doing, and native menu and taskbar integration keep Sync controls within easy reach.

Mobile environments are naturally dependent on the way in which documents are handled. For iOS, documents are maintained in a file list, and can be viewed by clicking on the files themselves. External files accessible from other apps can be "opened" using Liferay Sync, thereby dropping them into your Sync folder and synchronizing them across other Sync clients. "Pulling down" on the Sync file list forces a refresh (automatic sync frequency can be configured as well).

This Liferay Sync release is designed to work with both Liferay 6.1 Community Edition and Enterprise Edition. When used with the Enterprise Edition of Liferay, Sync will enable users to synchronize documents and files across all of the sites for which they have access.

### Installing Liferay Sync [](id=lp-6-1-ugen04-installing-liferay-sync-0)

For Windows or Mac OS, visit the Liferay Sync product page [Liferay Sync Product Page](http://www.liferay.com/products/liferay-sync/features), and click *Get it Now* (on the right-side navigation menu) to download the client application for your desktop environment. For Windows, the client application installer should be named `liferay-sync-<version>-<date>.exe`. For Mac OS, it should be `liferay-sync-<version>-<date>.dmg`. Follow the on-screen instructions of the installer wizard to configure your client to connect to an existing Liferay 6.1 deployment using your Liferay credentials.

#### Windows [](id=lp-6-1-ugen04-windows-0)

Upon launching the Windows application installer, you'll be prompted to choose an installation location for Liferay Sync. Browse to an appropriate location on your machine and click *Next*.

![Figure 4.46: Use the Liferay Sync Installation wizard to choose an installation location.](../../images/liferay-sync-setup.png)

On the next screen, you can configure some properties for Liferay Sync. There are three items listed in the *General Settings* section. *Start Liferay Sync on Login* is checked by default. If you don't want Sync to start automatically, uncheck this. *Show Desktop Notifications* is also checked by default. Unless you uncheck this, when a file that you have synced is changed, a small notification will appear in the corner of your screen. The *Check Server For Updates Every:* field enables you to set how frequently it will check to see if anything has changed. This can be set anywhere between 5 seconds and 30 minutes.

Click the *Edit Settings* button in the *Account Settings* section to specify your server's URL and enter your Liferay credentials. Use the *Test Connection* button to make sure Liferay Sync can communicate with the server. Editing your settings also allows you to specify your Sync folder, the folder where Sync will store files on your machine. By default, files are stored in the *liferay-sync* subfolder of your personal Documents folder.

Finally, the *Site Settings* section allows you to choose which sites you wish to sync media from. By default, it will list all of the sites that you are a member of, but you can uncheck any of those sites if you don't want to sync those files.

![Figure 4.47: Open the Liferay Sync menu and select *Properties* to edit the settings you configured during setup.](../../images/liferay-sync-properties.png)

On the last screen, leave the *Run Liferay Sync* button checked to automatically start Liferay Sync after you click *Finish*.

![Figure 4.48: You'll see the following screen once Liferay Sync has been installed. Click *Finish* to exit the installation wizard.](../../images/liferay-sync-setup-complete.png)

The options for the Mac OS application installer are similar.

#### Mac OS [](id=lp-6-1-ugen04-mac-os-0)

Liferay Sync for Mac is packaged in a DMG file. Double-clicking on a DMG mounts it as a disk image, and opens a window showing the contents of the image. To install Sync, drag the Liferay Sync icon to the Applications folder. Once it's installed, go to your Applications folder to run it.

![Figure 4.49: Drag the Liferay Sync icon to the Applications folder.](../../images/liferay-sync-mac-install.png)

When you launch Liferay Sync, the first thing you need to do is provide it with the URL for the Liferay server that you'll be using Sync with, along with your Liferay credentials. After that, you'll need to run through a brief setup process that was described above for Windows.

![Figure 4.50: You can provide the same information requested by the Windows application installer.](../../images/liferay-sync-mac-preferences.png)

Once you've finished your configuration and have clicked *OK*, Liferay Sync starts running in the background, and an icon appears in your top menu bar. If you wish to change any of your settings, click the icon to open the Liferay Sync menu and click on *Properties*.

#### iOS [](id=lp-6-1-ugen04-ios-0)

For iOS, visit the App Store, search for Liferay, and install the Liferay Sync App. Once installed, follow the on-screen instructions as above.

### Using Liferay Sync [](id=lp-6-1-ugen04-using-liferay-sync-0)

Once installed, you'll see a Liferay Sync icon in your taskbar whenever it's running. A green checkmark means Liferay Sync has a working connection to your Liferay server and is updating the files in your Sync folder according to the interval you specified in the wizard. Click the Liferay Sync icon in your taskbar to bring up the menu.

![Figure 4.51: Open the Liferay Sync taskbar menu to acces the following options.](../../images/liferay-sync-taskbar-menu.png)

*Open Sync Folder* opens your Liferay Sync folder in your native file manager.

*Open Site* provides links to the Control Panel pages of all the Documents and Media repositories which you have permission to access. By default, you can find links to your personal Documents and Media repository as well as links to the Documents and Media repositories of all the other sites you belong to. If your Liferay user is an administrator, you'll also find a link to the global Documents and Media repository.

*Recent Files* shows a list of recently created or modified files from all the repositories you can access.

*Properties* opens the Liferay Sync Properties page that the installation wizard presented you. The properties page lets you enter your Liferay server's URL, enter your credentials, and change properties like automatic login, desktop notifications, and sync frequency.

*Sync Now* instructs Liferay Sync to check the Liferay server for updates immediately.

*Pause Syncing* instructs Liferay Sync to suspend syncing until further notice. If someone added a very large file to one of your shared folders that's taking a very long time to sync, you might want to use this option and resume syncing at a later time.

*About* displays Liferay Sync version information, copyright information, and a link to Liferay's home page.

*Check for Updates* checks to see if a new version of Liferay Sync is available from [liferay.com](liferay.com) and allows you to set whether or not Liferay Sync should automatically check for updates.

#### Using your Sync folder [](id=lp-6-1-ugen04-using-your-sync-folder-0)

Once Liferay Sync has been configured and is running, any files you add to or modify in your Sync folder are automatically detected and uploaded to your Liferay server. Also, changes from other users are downloaded to your Sync folder.

Liferay Sync handles deletions via a special `liferay-sync.deletions` file. This mechanism prevents users from accidentally deleting shared files. When you delete files from your Sync folder, a `.liferay-sync.deletions` file is created there with the names of the files you deleted. This lets Liferay Sync know that you don't want these files in your Sync folder, so it won't download them the next time it syncs. Note that the files listed in your `.liferay-sync.deletions` file are only local deletions since only the owner of a file can delete it from the Liferay server. If you really want to delete a file and its entire version history from the server, you'll have to use your browser to log in to the portal and delete it through the web interface. You can remove entries from your `.liferay-sync.deletions` file to have Liferay Sync download them the next time it syncs. 

You can run through the following exercise to familiarize yourself with how to create, edit, download, and upload files with Liferay Sync. First, open your Liferay Sync folder in your file manager (use the *Open Sync Folder* option of the Liferay Sync menu from the taskbar), and create a new file called `README.txt`. Edit this file and enter the word *test*. Then use the *Sync now* option of the Liferay Sync menu to make sure that your `README.txt` file gets uploaded to your Liferay server. Next, check that you can access this file from your Liferay site. Open your browser, navigate to your Liferay site, and sign in with your Liferay account credentials. Then, navigate to the control panel. Make sure the site you chose to sync with is selected in the context menu selector and click on *Documents and Media*. You should see your `README.txt` file listed there.

![Figure 4.52: You can access the same files from Liferay Sync that you can from Liferay's web interface.](../../images/liferay-sync-documents-and-media.png)

Download the file (click the small triangle icon at the top right corner of the *README.txt* icon and select *Download*) to a convenient location on your machine and check that it still says *test*. Now open the `README.txt` file in your Sync folder and edit it so that it says *second test*. Choose *Sync now* again, and then go back to your browser and refresh your Documents and Media page. Click on the *README.txt* icon, look at the information displayed to the right, and you'll see that its version number has incremented.

![Figure 4.53: Updating a file through Liferay Sync increments the file's version number. You can view a file's version number through the web interface.](../../images/liferay-sync-README.png)

Download the file again, and you'll see that it now says *second test*--your edit was uploaded to the server and downloaded again by Liferay Sync. You can be confident that this edit was also downloaded by all other Liferay Sync clients connected to your site.

#### Demonstrating Liferay Sync Permissions [](id=lp-6-1-ugen04-demonstrating-liferay-sync-permissions-0)

Liferay Sync uses the default Liferay permissions to determine which files and folders are synced to the user's machine. This means that whatever files a user can access from a certain site are the ones that will be pulled down by Liferay Sync if that site is selected in the Sync client. You can test the functionality of Liferay Sync permissions by performing the following steps. First, create a new file on your desktop called *secret.txt*. Enter the text *classified information* into this file. Then use your browser to log into Liferay and create a new user called *secretagent* with the email address *secretagent@liferay.com* and the password *test*. Also, create a new private site called *Secret Site*. Then assign the *secretagent* user to the *Secret Site* and grant the *Site Administrator* role to this user. There will be no other members of this site unless they are assigned by an administrator. Log in as the *secretagent* and use *Go to* &rarr; *Control Panel*, select *Secret Site* in the context menu selector, and click on *Documents and Media*. Then upload the *secret.txt* document.

Next, we'll configure our Liferay Sync client to log in with the *secretagent* user's credentials and access the *Secret Site*. Open the Liferay Sync menu from the taskbar and select *Properties*. Click on the *Edit Settings* button, choose a new Sync folder, enter your server's URL, and enter the secret agent's credentials: *secretagent@liferay.com* and *test*. Lastly, uncheck all Liferay sites except the *Secret Site*, and click *OK*. Confirm that the *secret.txt* file that you uploaded to the *Secret Site*, is downloaded to your new Sync folder. Open it and check that it says *classified information*. If you reconfigure your Sync client connect to your Liferay instance using the credentials of another user who doesn't belong to the *Secret Site*, the *secret.txt* will not be downloaded. Congratulations! You've successfully set up a Liferay Sync folder that can only be accessed by the *secretagent* user and your administrators. 

Now that we've learned how to use Liferay Sync, let's move on to Tags and Categories.

## Tags [](id=ta-3)

Tags are an important tool that you can use to help organize information on your portal and make it easier for your users to find content that they're looking for. Tags are words or phrases that you can attach to any content on the website. Tagging content will make your search results more accurate, and enable you to use tools like the Asset Publisher to display content in an organized fashion on a web page. There are two ways to create tags: you can do it through the administrative console in the control panel, or on the fly as content is created.

![Figure 4.54: The Add Tag Dialog](../../images/05-add-tag.png)

To create tags in the control panel, select the site that you want to create tags for, and select *Tags*. From this screen, you will be able to view any existing tags and make new ones. To create a new tag, simply click *Add Tag*. You'll then be asked for the name of the tag, and you'll have the ability to set permissions for viewing or managing the tag. You can also add properties to a tag. Properties basically act like tags for your tags. Structurally, properties are key-value pairs associated with specific tags that provide information about your tags. You can edit existing tags from the *Tags* window of on the control panel. You can change the tag name, change the tag's permissions, delete the tag, or add properties.

Tags are not the only portal-wide mechanism for describing content: you can also use categories.

## Categories [](id=categori-3)

Categories are similar in concept to tags, but are designed for use by administrators, not regular users. Hierarchies of categories can be created, and categories can be grouped together in *vocabularies*. While tags represent an ad hoc method for users to group content together, categories exist to allow administrators to organize content in a more official, hierarchical structure. You can think of tags like the index of a book and categories like its table of contents. Both serve the same purpose: to help the user find the information he or she seeks.

Adding vocabularies and categories is similar to adding tags. Once you've selected the site you want to work on, select *Categories* from the content section of the control panel, and you will be presented with the categories administration page.

![Figure 4.55: Categories Administration Page](../../images/05-categories.png)

Clicking on a vocabulary on the left will display any categories that have been created under that vocabulary. You can create new vocabularies simply by clicking *Add Vocabulary* and providing a name for it. You can create categories in a similar fashion by choosing a vocabulary on the left, and then selecting *Add Category*. Like tags, you can also provide properties for categories. Once you have created some vocabularies and categories, you can take advantage of the full capabilities of categories by creating a nested hierarchy of categories. To nest categories, select what you want to be the parent category, then drag any category that you want to become a child category onto it. You will see a plus sign appear next to the name of the category you are dragging if you can add it to the selected parent category; if you see a red *x* that means that you cannot add that category as a subcategory of parent category that you have selected.

Once you have created a hierarchy of categories, your content creators will have them available to apply to content that they create. Navigate to the Web Content page of the control panel and click *Add Content*. Click the Categorization link from the right-side menu and click *Select* on the vocabulary use would like to use. A dialog box will appear with your categories. Select any relevant categories by checking the box next to them, and they will be applied to the content.

## Summary [](id=summ-26)

We have explored many of the portlets in Liferay's collaboration suite. You have seen how you can configure all of the portlets in a similar fashion using a unified user interface. After this, we went over all of the portlets in succession.

The Blogs and Blogs Aggregation portlets can be used to manage shared blogs or blogs belonging to a group of people at once. These portlets have all the features you would want in a blog, including rich text editing, links to news aggregators, tags, RSS feeds, and more.

The Calendar portlet likewise can be used to manage a shared calendar or a group calendar. It includes features for events, event notification, repeatable events, and import and export to and from the standard iCalendar format.

Integrating mail with your portal is easy with the Mail portlet. You can add as many custom or Gmail mail accounts as you wish, and this portlet can keep them all organized in one place, together with the rest of the things Liferay is aggregating for you.

Discussion becomes easy with Liferay's Message Boards portlet. This portlet can be used to manage heavily trafficked discussion forums with ease. It inherits all of the security features of the Liferay platform and includes administrative functions for thread priorities, moving threads, nested discussion categories, banning users, and more.

Liferay's Wiki portlet is a state of the art wiki application that users can make use of to collaborate on web pages. Again, it inherits the strengths of the Liferay platform in the form of security, interface, and search. You can use the wiki portlet to manage several wiki nodes or use many wiki portlets to manage one node each.

The Knowledge Base portlet provides administrators with a more formal way to create and organize articles than the Wiki. Administrators can create article templates which can be used to create articles that share a common structure. Knowledge base articles are easy to categorize hierarchically which makes them easy to browse and search. The Knowledge Base Article portlet can be used to display a single article and the Knowledge Base Section portlet can be configured to display a group of articles that belong to the same section. 

Liferay's Documents and Media portlet allows users to store information online in the same type of directory structure that they use locally. The Documents and Media library supports customizable document types and metadata sets. It also has a new API that is based on repositories. This allow multiple repositories to be mounted to a single Documents and Media library. Liferay Sync allows you to access Documents and Media files without using a browser. You can even access files offline. When online, Liferay Sync automatically syncs with the Documents and Media repository, uploading files you've modified and pulling down changes from others.

Tying all of these applications together are tags and categories. Tags can be added to any content by end users, and hierarchies of categories can be added to the system by administrators to be applied to content anywhere in the portal. These help your users to find the content that is most relevant to them, empowering the search to be as accurate as possible.

Liferay's collaboration platform is a full suite of integrated applications that empower users to work together. You can use them to great effect to enhance your portal and to build a vibrant, active community. 

