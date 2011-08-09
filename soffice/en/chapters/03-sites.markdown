# Sites

Sites are the core of Social Office—they are both the *organization of
people* and the *collection of pages* that hold the tools for team
collaboration. Sites are the structure Social Office uses to bring
together the various groups within your organization and enable them to
work together.

In this chapter we'll discuss the basics of creating and configuring
Sites, and take a look at the pieces that hold a Site together. We'll
also take a look at each application and how they all fit together to
break down the barriers between team members and enable them to work
together.

## Adding a Site

Any user with sufficient permissions can add a Site via the Sites
application or the Control Panel. From the Sites application, a user
simply needs to click on *Add a Site* and set the name and permissions
for the Site. From the Control Panel, a user would go to *Sites* on the
left side and then click *Add a Site*. Each Site is not only a
collection of pages, but also a community of which users can become
members.

## Browsing Sites

Each user by default should be equipped with a Sites application on his
or her home page with which he or she can navigate to the various Sites
in Social Office. From other pages in Social Office, users can navigate
via the *My Sites* link in the global navigation at the top right corner
of the screen.

Each Site is composed of several pages which are accessible by tabs. The
home page for each Site is a hub containing applications which aggregate
data from the primary applications on the Site. The other pages contain
one application each—Calendar, Blog, Wiki, Forums, Documents, and
Members. We'll go over these later in the chapter.

## Managing Permissions for Sites

Sites can be Open or Private. Anyone can view the content of an Open
Site and anyone can join. For Private Sites, nonmembers cannot access
the Site directly and must be invited to become a member.

The person who creates a Site is the Owner of the Site. That person has
full control over permissions, applications settings, and layout
throughout the Site. Social Office Administrators also have this ability
(For more information on Administrators and other user roles, see
Chapter 4: The Control Panel).

In addition to the overarching Site permissions, individual applications
and entities within those applications have configurable permissions.
For example, the Blogs application could be setup so that anyone can
create an entry, so that only members can create a blog entry, or so
that only specific users can create an entry. On top of that, individual
entries within the blog could also be set so that anyone can view or
comment on them, or so that only members of the Site can view or
comment, even if the blog itself is public.

#### Site Roles

In order to maintain a proper Site, certain users are assigned as
administrators. The Control Panel chapter covers the creation and
assignment of User Roles. In general, you'll create the roles in the
Control Panel and set the abilities of that role within the specific
applications in the Site.

## Site Applications

In your initial Social Office Setup, each Site will contain six core
applications on separate pages, accessible via the top tab navigation:
Calendar, Documents, Forums, Blogs, Wiki, and Members. Most of these
applications also work with the smaller applications which are displayed
on the Site's home page. More details on these interactions are
discussed below.

### Calendar

Social Office's Calendar application is a complete calendaring solution.
You can schedule any number of events of different types, receive alarms
via email or text message, import and export your calendar, and much
more. Additionally, you can import and export the calendar to the
popular iCalendar format for use in other applications.

On the home page for a Site, there is by default an application called
Today's Events. This application will display any events currently
scheduled on that Site's Calendar to occur on the current date. The
events are displayed based on the event's permissions—so a guest user
viewing the page will only see public events, and only the creator of
the event will see private events.

![image](../../images/03-sites_html_m129e846b.png)*Illustration 7: Main Calendar
View.*

\

On the Calendar page, the default view is to see the entire month of
events, but you can change that to show a week or a day at a time.
Arrows will appear to the left and right of the current span of time
that you're viewing—you can use these to view the next day, week, or
month as needed.

![image](../../images/03-sites_html_5ca7018f.png)*Illustration 8: Adding an Event*

\
\

To create an event, go to the Calendar application page and click *Add
Event*. From here, you'll be given the following options for setting up
the event:

**Start Date/Time:** The date and time the event starts.

**Duration:** How long the event will last.

**All Day Event:** Check this box to disassociate time from the event
and make it last all day.

**Time Zone Sensitive:** Leave this box checked to make sure that the
portal keeps track of the event regardless of time zone.

**Title:** The title of the event.

**Description:** A description of the event.

**Type**: Select from a number of pre-configured event types. You can
change these in the *portal-ext.properties* file.

**Permissions:** This field contains a check box that enables you to
toggle whether the event is public or private, and the ability to
configure more specific options. Clicking *configure* will allow you to
choose in more detail what guests or members of the community are
allowed to see and edit.

**Categories:** Calendar events can have one or more categories. For
information about categories, please see the section on categories in
Chapter 4: The Control Panel.

**Tags:**Calendar events can have one or more tags. For information
about tags, please see the section on tags in in Chapter 4: The Control
Panel.

**Repeat:** If the event repeats on a schedule, select the schedule.

**End Date:** If the event repeats on a schedule but there is an end to
the set of meetings, enter the end date.

**Reminders:** Select whether to send a reminder, how long before the
event to send it, and through what medium (email, text message, or
instant message) to send it. Note that this feature is integrated with
your profile on the portal, so you'll need to fill out your mobile phone
number and / or instant messenger IDs in order to use those features.

Click *Save* to add the event to the Calendar.

#### Importing and Exporting Calendars

If you're migrating to Social Office from an existing platform, there's
a good chance that you already have significant amount of Calendar data
that you don't want to lose. To help make your life easier, Social
Office supports importing calendar data in .ics format.

-   To import an existing Calendar, go to the Calendar application page.

-   Scroll down to the bottom and click *Import.*

-   On the Import screen you can click *Choose File* and select the .ics
    file to import to the Calendar.

-   You can also Export the Social Office calendar to .ics if necessary.

### Documents

One of the keys to collaborating in a workplace is good handling of
various documents which multiple members of a team might be working on
simultaneously. Social Office contains a Document Library application
which can help streamline this.

![image](../../images/03-sites_html_33df79b.png)*Illustration 9: Adding a Folder.*

\

Let's say you're a graphic designer for the marketing department and
you've just finished up an advertisement for several magazines. Before
you can move on to the next steps, you'll need your team and a handful
of executives to review and sign off on the content.

Because your company has only recently adopted Social Office, the first
thing you'll need to do is create the appropriate folders. Using the
above scenario, begin by creating a *Print Advertisements*folder.
Because you might want people from other departments to review documents
posted here, select the *Viewable by Anyone (Guest Role)* entry in the
Permissions drop-down list. Let's say that you also want to create a
subfolder of *Print Advertisements* called *Source Files* to use for the
original working files so that members of your team can make edits
directly to the file. Even though anyone can view the root folder, you
may not want everyone to have access to the source files, so when you
create this one, you would make sure that the *Viewable by
Community*option in the Permissions drop-down list is selected. By
clicking *More Options*, you can also open up additional permission
options to grant specific permissions to different roles.

You can continue to nest folders as deeply as necessary to keep things
organized. In larger departments running multiple projects at once,
having deep folder structures is a necessity to keep out of each others'
way.

To add a new document, click *Add Document.* After you're brought to the
next screen, click on *Browse.* Your browser will open a dialog box that
you can use to select one or more files to upload; once the transfer is
complete, you can set any details of the document from the folder in
which it was placed. If you click *Classic Uploader,* you'll be
presented with a web page that allows you to set the name, description,
and permissions, as well as select a single file to upload.

A very useful feature of the Documents application is that it keeps
track of past versions of a file after it's been updated. That way, if
any information is overwritten or removed, you can easily access an
older version of the file. It also features a simple locking mechanism,
whereby a user can “lock” the file to make some changes and unlock it
once he or she is finished. Use this feature to prevent duplicate work
or important changes from being lost in the shuffle of multiple updates.

There are two places where you can lock a file. While viewing the list
of files in a folder, you can simply click on the lock icon, or while on
the *Edit* screen, you can click *Lock*.

![image](../../images/03-sites_html_m1491c6aa.png)*Illustration 10: This file is
currently not locked; clicking on the lock icon will lock it.*

\

To access more options for a file or a folder, click on the icon that
looks like a wrench. You'll be presented with a menu containing the
following items:

**Edit:** Change the details of the file or folder. Clicking on this for
a file will enable you to upload a new version as well as leave comments
or ratings. This way, discussions about a document are kept with the
document itself.

**Permissions:** You can define who can view or edit a file or folder.

**Delete:** Remove the file or directory. Removing a directory will also
remove any subdirectories and files contained in that directory.

**Add Subfolder (Folders only):** Add a folder inside of the existing
folder.

**Add Document (Folders only):** Add a document inside of the existing
folder.

**Add Shortcut (Folders only):** If you can read a document then you can
create a shortcut to enable others to access the document. You need to
set permissions on the shortcut to specify which users will have access
to the document.

**Access from Desktop (Folders Only):** Enables you to access files and
folders directly from your desktop operating system by specifying the
WebDAV URL.

**Download (Files Only):** Enables you to download a file. Use this
option if you need to change the details of a file in order to upload a
new version.

**Lock (Files Only):** Enables you to lock a file.

#### **Editing a File**

The Edit screen is where most of the action is going to happen. Clicking
on the name of a file will take you to the Edit page for that file. The
Document properties section displays information about various
attributes of the file, such as the name, the author, and the date of
the last revision. It also displays the WebDAV URLs if you wish to
access this file through your operating system. In the Social Data and
Revision History section, you can leave comments or rate the file. There
are a large number of fields on this page, and it can be confusing at
first. Let's take a moment to go over the purpose and function of each
field.

Document Properties:

**Name:** This is the name of the file that is always displayed. You can
upload a file with a different name, but this will remain the same. The
name also serves as a link to download the file.

**Title**: You can change the displayed name of the file here. Simply
uploading a file with a different name will not change the name of the
file in Social Office.

**Description**: You can fill in any notes or details about the file
that other users will need to know here.

**Current Revision**: Social Office keeps track of the number of times a
file has been edited and displays the latest version of the file. Each
time the file is updated, Social Office will increment the Current
Revision number.

**Size:** The size of the file on disk.

**Author**: The author of the file.

**Creator**: The creator of the file.

**Create Date**: The date the file was created.

**Last Modified By**: The user who last modified the file.

**Modified Date**: The date when the file was last modified.

**Number of Downloads:** The number of times users have downloaded the
file.

**URL:** This is a URL that you can provide to users who may need access
to a file hosted on the Site, but aren't members of that Site. Please
note that in order for this to work, the file permissions must be set so
that a guest can view it.

**WebDAV URL:** Fills the same purpose as the URL, but with the WebDAV
protocol.

\
\

![image](../../images/03-sites_html_m35b9b3f8.png)*Illustration 11: The screen for
editing existing files.*

\

\
\

Social Data and Revision History:

**Ratings:** Each user can rate a file, by giving it anywhere from 1 to
5 stars. This could be useful for peer or executive review of files.

**Average Rating**: This field displays the average rating of all the
ratings the file has received.

**Version History**: You can view past versions of a file; this can be
useful for tracking down specific changes to a file or to recover
information that was accidentally removed in a newer version.

**Comments:** Along with rating a file, users can also leave comments.
This is useful for discussion about the file.

There are also some useful links in the Document Actions section:

**Lock Document**: Clicking this allows to lock a document, as described
above. No one can edit a locked document until you unlock it or the lock
expires.

**Move to**: This allows you to move the file to a different location.
You can choose a folder to move the file to or you can create a
subfolder for your file within a chosen folder.

**Edit Properties**: This opens a window where you can change the
following attributes of your file: the title, name, author, and
description. You can also add tags to your file here. Tags are used
throughout the site to enable users to search properly categorized data
more quickly. For more information on tagging, see Chapter 5: The
Control Panel.

**Edit Permissions**: This allows you to give different permissions
related to the file to different users with various roles. You can allow
certain users to view your file, edit your file, or not have any access
to your file at all.

**Delete Document and Revisions**: Clicking this button will permanently
delete your file and its entire revision history. You will receive a
warning prompt before the deletion actually takes place.

### Blogs

Social Office has a Blogs application which allows you to provide a
blogging service to your users. Each site will have its own unique blog,
and by default, all members of the site can post blog entries. Let's go
over how to use the Blogs application to create an optimal blogging
system.

*Illustration 12: Initial view of the Blogs application.*

![image](../../images/03-sites_html_mb4fbde0.png)\

On the Blogs page, you'll see four main options: *RSS*, *Subscribe to
this Blog*, *Add Blog Entry,* and *Permissions*.

Clicking on *RSS* will open the RSS feed for this blog in a new window.
This enables you to publish your blog as a feed to various feed readers
that your users might be using.

Click *Subscribe to this Blog* to subscribe to the blog without opening
a new window.

Click on *Permissions* and you'll be presented with a screen where you
can set which users have to ability to blog. If you want to limit this
to specific users, you can create a User Role for bloggers in the
Control Panel and give that role permissions to add entries here.

The core functionally of this application is of course, writing blog
entries. Click the *Add Blog Entry* button to get started.

**Title:** This is the title of the blog entry. It is displayed at the
top of the post.

**Display Date:** You don't have to make the blog entry appear
immediately. If you prefer to have it display later in the day or a few
days later, you can set the display date here.

![image](../../images/03-sites_html_m1bcfbfcd.png)*Illustration 13: Adding a Blog
entry.*

\

**Content:** The content entry area consists of two parts: the text
entry field and the toolbar. The text entry field is where you do your
typing, and the toolbar controls text formatting as well as embedding
images, videos, or even adding various emoticons to the post. You also
have the option to use either the default WYSIWYG text editor or enter
your own HTML.

**Tags:** The tags field give you the option to add tags to your entries
to make them easier to find with Social Office's search feature. You can
find more information about tagging and categories in Chapter 5: The
Control Panel. Clicking *Suggestions* will automatically generate
possible tags from your entry based on looking for keywords in your
text.

**Save as Draft, Publish, Cancel:** Clicking on *Save Draft* will save
your work. Note also that as you type, the entry is automatically saved
as a draft at periodic intervals. This gives you peace of mind in using
the application from within your browser, as you won't lose your entry
in the event of a browser crash or network interruption. *Publish* will
save and post your entry on the Blogs page, and *Cancel* will discard
all of your work and send you back to Blogs page.

**Permissions:** Like almost anything else in Social Office, you have
the ability to restrict what users can view or comment on your entry.
You can also allow other users to edit it (in the case of a team blog).

**Allow Incoming Trackbacks:**If you're running Social Office on the
Internet and not an Intranet, you might want to enable trackbacks to
make it easier to track when other bloggers link to your blog. This
could also potentially be useful on larger Intranets to reference other
blogs that are part of other Social Office or Liferay Portal
installations.

**Trackbacks to Send:** If you're referencing another blog that supports
trackbacks in your post, you'll want to make sure that they receive the
proper credit. Entering trackback URLs in his field will notify the
owners of the other blogs that you referenced them in your post. Once
you have finished your blog entry, click *Publish*. You'll go back to
the list of entries, and you'll see your entry.

### Forums

Social Office's Forums are one of the most widely used collaboration
features provided by the product. The application is a state of the art
forum application similar to many forums in which you may have
participated. There are countless web sites out there where it's clearly
evident that there is no link whatsoever between the main site and the
message boards. In some cases, users are even required to register
twice: once for the main site and once for the message boards. By
providing a message boards application along with all of the other
applications, Social Office can provide a unique, integrated approach,
which is essential when creating an environment for people to work
together and collaborate. We've already taken care of the integration of
applications, so you can concentrate on using the tools we provide to
streamline your workflow and improve the efficiency of business
processes.

![image](../../images/03-sites_html_20b9df54.png)*Illustration 14: Editing Message
Board Permissions.*

\

The Forums application is very straightforward to set up. To start,
you'll want to configure who has access to the Moderator functions of
the Forums, so click on *Permissions*.

#### Permissions

This page allows you to define which roles have the ability to add a
category of threads or to ban abusive users from the forums. Select the
roles and permissions you want to configure and then click *Submit*. You
may want to set up a specific User Role for this. User Roles are covered
in Chapter 5: The Control Panel.

#### Adding Categories and Mailing Lists

You're now ready to add categories to your forums. Click the *Add
Category* button. Enter a name for the category and a description of the
category. At the bottom of the form is a check box which allows you to
enable the mailing list function.

The mailing list function works in concert with the message notification
emails. If a user subscribes to a message board category, he or she will
get emails when someone posts messages to that category. Enabling the
mailing list function allows those users to simply reply to the
notification messages in their email clients, and those replies are
posted to the thread automatically.

To enable this functionality, you'll need a mail account for the
category. Once you click the check box, a number of other options will
appear.

**Email Address:** Enter the email address of the account that will
receive the messages.

Next, there are two tabs: *Incoming* and *Outgoing*. These define the
mail settings for receiving mail and for sending mail. The Incoming tab
has the following options:

**Protocol:** Select POP or IMAP.

**Server Name:** Enter the host name of the mail server you're using.

**Server Port:** Enter the port on which your mail service is running.

**Use a Secure Network Connection:** Check this box to use an encrypted
connection if your server supports it.

**User Name:** The login name on the mail server.

**Password:** The password for the account on the server.

**Read Interval (Minutes):** Social Office will poll the server at this
interval looking for new messages to post.

The Outgoing tab has the following options:

**Email Address:** Enter the email address that messages from this
category should come from. If you want your users to reply to the
categories using email, this should be the same address configured on
the Incoming tab.

**Use Custom Outgoing Server:** If you need to use a different mail
server than the one that's configured for the portal, check this box. If
you check the box, a number of other options will appear.

**Server Name:** Enter the host name of the SMTP mail server you're
using.

**Server Port:** Enter the port on which your mail service is running.

**Use a Secure Network Connection:** Check this box to use an encrypted
connection if your server supports it.

**User Name:** Enter the login name on the mail server.

**Password:** Enter the password for the account on the mail server.

When finished adding your category, click *Save*. Add as many categories
to your forums as you wish. Note that categories can have subcategories.
You can add a number of top-level categories and then click on each one
to add categories under that (to an unlimited level). For usability
reasons, you don't want to nest your categories too deep, or your users
will have trouble finding them. You can always add more categories as
your forums grow.

#### Using the Forums

Upon seeing Social Office's Forums application, your users will
immediately recognize that the interface is similar to many other
implementations they've seen before. Forums are nothing new to the
Internet, and many people have been using them for quite a long time.
For that reason, Social Office's forums will seem very familiar to your
users.

Threads can be viewed in many ways. At the left of the page is a set of
quick links: *Categories, My Posts, My Subscriptions, Recent Posts*, and
for administrative users, *Statistics* and *Banned Users*. The
Categories tab allows users to browse the categories for an appropriate
place to post a thread. The My Posts link shows all of the posts for the
user who's currently logged in. This is a convenient way to get back to
a conversation you had once in order to retrieve some pertinent
information. The My Subscriptions link allows a user to manage thread
subscriptions. If you lose interest in a particular topic, you may want
to visit this tab and unsubscribe from a thread. The Recent Posts tab
shows all posts from all categories by date, so you can keep up on all
the most recent discussions in the forums.

![image](../../images/03-sites_html_677d5764.png)*Illustration 15: Editing a forums
post. You can see the emoticons that are available in the editor, insert
emoticons, add preformatted text, and more.*

\

For administrators, the Statistics link shows the number of categories,
the number of posts, and the number of participants in your forums. It
also has a list of who the top posters to your forums are. The Banned
Users tab shows all of the users who have been banned from posting on
the forums.

To post a new message thread, go to the Categories link, click on a
category, and then click on the Post New Thread button. You'll see a
message editing form. The body field on this form is different from that
of the other applications in Social Office. The reason for this is to
support BBCode, which is a standard form of markup used in many message
board products. Users who have Moderator access to the board can modify
the priority of messages. You can also use the editor to quote from
messages that you're replying to.

Messages that are posted to the forums are shown by default in a
threaded view, so that replies are attached to the proper parent
message. This makes it easy to follow along with conversations.

When viewing a message board thread, users are given several options. At
the top right of the thread are three icons, allowing users to view
threads in a flat view, in a tree view, or in a combination view. A flat
view shows all of the messages in the order in which they are posted. A
tree view shows all of the messages in a threaded view, so that replies
are next to the messages they are replying to. A combination view shows
the threads at the top as subjects only, with the flat view underneath.

When viewing a thread, users can click links allowing them to post a new
thread, subscribe to the thread they are viewing, or if they have
administrative access, move a thread to another category. Subscribing to
a thread causes Social Office to send the user an email whenever a new
message is posted to the thread. If you have enabled the mailing list
feature for the category in which the thread resides, users can simply
reply to these messages in order to post back to the thread, without
having to visit your site.

The Forums application is also highly integrated with Social Office's
user management features. Posts on the message board show users' profile
pictures if they have uploaded one for themselves, as well as the dates
that users created an ID on your site.

#### Forums Administrative Functions

The Forums application provides for the day-to-day administration of the
message threads. You may wish to separate this function out by a role,
and then delegate that role to one or more of your users. That would
free you up to concentrate on other areas of your web site. To do this,
you can create a role called Message Board Administrator.

This role can be scoped by Social Office or a Site. If you have a Social
Office scoped role, members of this role can administer any Message
Boards in any Site to which they have access. If it's a Site scoped
role, members of this role can administer a Message Boards portlet in
only the Site in which they have the role.

Go to the Control Panel and create this role. Once it's created, click
*Actions → Define Permissions*. Click the *Portlet Permissions* button.
Browse the list until you find the Message Boards portlet and then click
on it. You'll then see a screen which allows you to configure the
various permissions on the portlet.

Grant the permissions you wish message board administrators to have and
then click *Save*. You can then add users to this role and they will
inherit the permissions.

Message Board administrators can perform all of the functions you have
already seen, including creating and deleting categories and posting
threads. In addition to these, a number of other functions are
available.

##### Moving Threads

Many times a user will post a thread in the wrong category.
Administrators may in this case want to move a thread to the proper
category. This is very easy to do. First click on the *Actions* (wrench)
icon at the right. If you have administrative access, there is a link in
the list of options labeled *Move Thread*. Click this link. You'll be
presented with a simple form which allows you to select a category to
which to move the thread and a check box which allows you to post a
message explaining why the thread was moved. This message is posted as a
reply to the thread you're moving. When finished, click the *Move
Thread* button and the thread is moved.

##### Deleting Threads

Users with administrative access to the forums can delete threads.
Sometimes users begin discussing topics that are inappropriate or which
reveal information which should not be revealed. In this case, you can
simply delete the thread from the forums. This is easy to do. First,
view the list of threads. Next to every thread is an Actions button.
Click *Actions → Delete* to delete the thread. This doesn't prevent
users from re-posting the information, so you'll need to be vigilant in
deleting threads or consider the next option.

##### Banning Users

Unfortunately, sometimes certain users can become abusive. If you wind
up with a user like this, you can certainly make attempts to warn him or
her that the behavior he or she is displaying is unacceptable. If this
doesn't work, you can ban the user from posting on the forums.

Again, this is very easy to do. Find any post which was written by the
abusive user. Underneath the user's name / profile picture is a link
called *Ban this User*. Click this link to ban the user from the forums.

If after taking this action the user apologizes and agrees to stop his
or her abusive behavior, you can choose to reinstate the user. To do
this, click the Banned Users tab at the top of the Forums application.
This will show a list of all banned users. Find the user in the list and
click the *Unban this User* link.

##### Splitting Threads

Sometimes a thread will go on for a while and the discussion completely
changes into something else. In this case, you can split the thread
where the discussion diverges and create a whole new thread for the new
topic. Administrative users will see a *Split Thread* link on each post.
To split the thread, click the link. You'll be brought to a form which
allows you to add an explanation post to the split thread. Click *Ok* to
split the thread.

##### Editing Posts

Administrative users can edit not only their own posts, but also all
others. Sometimes users will post links to copyrighted material or
unsuitable pictures. You can edit these posts, which allows you to
redact information that should not be posted or to censor profanity
that's not allowed on your forums.

##### Permissions

Permissions can be set not only on threads, but also on individual
posts. You can choose to limit a particular conversation or a post to
only a select group of people. To do this, click the *Permissions* link
on the post and then select among the *Delete, Permissions, Subscribe,
Update*, and *View* permissions for the particular role to which you
want to grant particular access. This function can be used to make it so
some privileged users can post on a certain thread, but others are only
allowed to view it, or any combination of the above permissions.

### Wikis

Social Office's Wiki application, like the Forums application, is a
full-featured wiki application which has all of the features you would
expect of a state of the art wiki. Again, though, it has the benefit of
all of the features of the Social Office platform. As such, it's
completely integrated with Social Office's user management, tagging, and
security features.

So what is a wiki? Put simply, a wiki is an application which allows
users to collaborate on information. This, of course, has many
applications—the most famous of which is Wikipedia, which is a full
encyclopedia developed collaboratively by users from all over the world,
using a wiki. Another example would be Liferay's wiki, which is used for
collaborative documentation for the Community Edition of the product.

A wiki application allows users to create and edit documents and then
link them to each other. To accomplish this, a special form of markup is
used which is sometimes called *wikitext*. Unfortunately, the
proliferation of many different wiki applications resulted in slightly
different syntax for wikitext in the various products, as each new wiki
tried to focus on new features that other wikis didn't have. For that
reason, a project called WikiCreole was started. This project resulted
in the release of WikiCreole 1.0 in 2007, which is an attempt to define
a standard wiki markup that all wikis can support.

Rather than define another wikitext syntax, Social Office's Wiki
application supports WikiCreole as its syntax. This syntax is a
best-of-breed wiki syntax and should be familiar for users of other
wikis. The application provides a handy cheat sheet for the syntax on
the page editing form, with a link to the full documentation if you wish
to use some of WikiCreole's advanced features.

#### Navigating in the Wiki application

You'll start on the default page, which is called FrontPage. Initially,
of course, it's blank—you'll have to fill it with your own wiki content.
On the left are the *Quick Links*, which contain a set of navigational
links:

**Manage Wikis:** This link takes you to a list of available wikis, with
options to make administrative changes.

**FrontPage:** Takes you to the main page of the main wiki.

**Recent Changes:** Takes you to a page which shows all of the recently
updated pages.

**All Pages:** Takes you to a flat, alphabetical list of all pages
currently stored in the wiki.

**Orphan Pages:** This link takes you to a list of pages that have no
links to them. This can happen if you take a link out of a wiki page in
an edit without realizing it's the only link to a certain page. This
area allows you to review wiki pages that are orphaned in this way so
that you can re-link to them or delete them from the wiki if they are no
longer relevant.

**Search:** Enter a term here and click the Search button to search for
items in the wiki. If the search term is not found, a link is displayed
which allows you to create a new wiki page on the topic for which you
searched.

#### Getting Started with the Social Office Wiki

The Wiki application can contain many wikis. By default, it contains
only one, called Main. If you wish to add, modify, or delete wikis,
click on the *Manage Wikis* link in the quick links. You'll see that the
Main wiki has already been added for you.

Clicking the *Add Wiki* button brings you to a screen which allows you
to give the wiki a name and a description. You can also set up some
default permissions. When you create a new wiki, it'll appear in a list
at the top of the main page of the application.

Next to each wiki in the list of wiki nodes is an Actions button. This
button contains several options:

**Edit**: Lets you edit the name and description of the wiki.

**Permissions**: Lets you define what roles can add attachments to wiki
pages, add pages to the wiki, delete pages, import pages to the wiki,
set permissions on the wiki, subscribe to the wiki, update existing
pages, and view the wiki.

**Import** **Pages:** You can import your data from other wikis. This
allows you to migrate off of another wiki which you may be using and use
the Social Office wiki instead. You may wish to do this if you're
migrating your site from a set of disparate applications (i.e., a
separate forum or a separate wiki) to Social Office, which provides all
of these features. Currently, MediaWiki is the only wiki that's
supported, but others will likely be supported in the future.

**RSS:**This will bring you to an RSS feed of updates to the wiki.

**Subscribe**: A user can subscribe to a wiki node and any time a page
is added or updated, Social Office will send an email to the user
informing him or her what happened.

**Delete**: Deletes the wiki node. To go back to your wiki, click on its
name in the list of wikis.

#### Adding and Editing Wiki Pages

By default, there is one page added to your wiki, called FrontPage. To
get started adding data to your wiki, click the *Edit* link at the top
right of the application. You'll be brought to a blank editing page.

You can now begin to add content to the page. Notice that there is a
very convenient “cheat sheet” which can help with the wiki syntax. You
can use this syntax to format your wiki pages. Consider for example the
following wiki document:

== Welcome to Our Wiki! ==

This is our new wiki, which should allow us to collaborate on
documentation. Feel free to add pages showing people how to do stuff.
Below are links to some sections that have already been added.

[[Introduction ]]

[[Getting Started]]

[[Configuration]]

[[Development]]

[[Support]]

[[Community]]

This would produce the following wiki page:

*Illustration 11: Adding a wiki page*

![image](../../images/03-sites_html_2eab94bc.png)\

\

This adds a simple heading, a paragraph of text, and several links to
the page. Notice that the links are red, instead of the normal blue
color. This indicates that the page behind that link doesn't yet exist,
and therefore needs to be created. If you click one of those links,
you'll be brought immediately to the editing screen you were on
previously when you edited the front page, except this time you'll be
creating the page behind the link you just clicked. Social Office will
display a notice at the top of the page stating that the page doesn't
exist yet, and that you're creating it right now. As you can see, it's
very easy to create wiki pages. All you have to do is create a link from
an existing page.

Note that at the top of the screen you can select from the Creole wiki
format and the HTML editor that comes with Social Office. We generally
recommend that you stick with the Creole format, as it allows for a much
cleaner separation of content and code. If you want all of your users to
use the Creole format, you can disable the HTML format using the
*portal-ext.properties* file. See the *Liferay Portal Administrator's
Guide* for further information on this configuration file.

At the bottom of the page editing screen, you can select categories or
tags for the article. Categories are a hierarchical list of headings
under which you can create wiki pages, and tags are lists of topics
covered by your articles. Both allow you to organize your content for
searching. Tags can be created inline; categories can be created using
the Control Panel in the *Tags and Categories* section.

#### Page Details

When viewing a page, you can view its details by clicking the *Details*
link which appears in the top right of the page. This allows you to view
many properties of the page. There are several tabs which organize all
of the details into convenient categories.

##### Details

The Details tab shows various statistics about the page and also
contains a few actions that you can perform on the page.

**Title:** Displays the title of the page.

**Format:** Displays the format for the page—either Creole or HTML.

**Latest Version:** Displays the latest version of the page. The wiki
application automatically keeps track of page versions whenever a page
has been edited.

**Created By:** Displays the user who created the page.

**Last Changed By:** Displays the user who last modified the page.

**Attachments:** Displays the number of attachments to the page.

**RSS Subscription:** Displays links which allow you to subscribe to the
page as an RSS feed in three formats: RSS 1.0, RSS 2.0, and Atom 1.0.

**Email Subscription:** Contains links allowing you to subscribe to the
entire wiki or just to this page.

**Advanced Actions:** Contains links allowing you to modify the
permissions on the page, make a copy of the page, move (rename) the
page, or delete the page.

##### History

This tab shows a list of all of the versions of the wiki page since it
was created. You can revert a page back to a previous state and you can
also compare the differences between versions by selecting the versions
and then clicking the *Compare Versions* button.

##### Incoming / Outgoing Links

The next two tabs are for incoming and outgoing links. These are wiki
links to and from the page. You can use this tab to examine how this
page links to other pages and how other pages link back to this page.

##### Attachments

The last tab is for attachments. You can attach any file to the wiki.
This is mostly used to attach images to wiki articles which can then be
referenced in the text. Referencing them using the proper WikiCreole
syntax renders the image inline, which is a nice way to include
illustrations in your wiki documents.

### Members

In order for a site to function properly, it must have members. Site
owners have the ability to allow anyone to join or to restrict
membership to invitation only. They also have the option to allow anyone
to view the site or to restrict site viewing to members only. Individual
applications within each site also have configurable permissions as to
whether they can be viewed or edited by anyone or only by members of the
site. In most cases you'll want to restrict membership of a site, but
allow everyone to view certain sections of the site.

As an example, a company's Marketing Site might have, among other
things, a Brand Style Guide and a Marketing Events Calendar. These are
things that they would want accessible to various people in the company,
such as web developers or retail associates. They would not, however,
want everyone to view specifics on upcoming promotions still in
development—or the time and locations of meetings with vendors. Using
Social Office, you can easily separate items like this—with some
documents and items on a Calendar being viewable to everyone, while
others would be invisible to everyone who wasn't a member of the site.

The standard permissions available to users are *Add Discussion*,
*Delete*, *Delete Discussion*, *Permissions*, *Update*, *Update
Discussion*, and *View*. These are very granular throughout the various
applications, so you have a lot of flexibility in determining who can do
what, not just for the application itself, but for specific files,
events, discussions, or just about anything else.

### 

![image](../../images/03-sites_html_2f234f4f.png)*Illustration 12: Add Applications
Dialog*

\

Other Site Options

While the applications we've been describing above are included by
default in Social Office's Sites and are where users are going to spend
the majority of their time, there are a few other tools and
configuration options that you'll want to know about when you're setting
up Sites.

#### Adding/Removing Applications

By default, there is a fairly large number of applications on users'
personal home pages and the Site home pages. In some cases, you may want
exactly what's there by default, but in others, you may want less or
more.

If a user has permission to remove a particular application, an X will
appear in the top right corner of the application box; clicking the X
will remove the application. If a user has permission to add an
application to a page, they will see an *Applications* button at the top
of the screen. Clicking on it will display a menu on the left side of
the screen with a list of applications that they can add to a page. The
applications are organized by category.

Users own their personal home pages and are free to make changes to
them. Any changes made by one user affects only his or her personal home
page. Site home pages, on the other hand, belong to the Site, so changes
made by one user will affect all users of that Site. This is why only
the Site Owner, Social Office Administrator, or someone given increased
permissions via User Roles in the Control Panel can modify the Site home
page.

#### Layouts

Along with adding or removing applications, pages have customizable
layouts. Each page has a selectable Layout template (accessed through
the *Layout* button at the top of the screen). Simply choose the best
layout for your page. You can also drag and drop individual applications
anywhere on the page; however, the layout template will determine the
size and shape of the Application box, as well as details of its exact
placement on the screen.

![image](../../images/03-sites_html_m2d9629b9.png)*Illustration 13: Layout
Selection.*

\

You can also apply layouts to Site and personal home pages. We suggest
experimenting with a variety of layouts to determine what works best for
your site and your users.

#### Announcements

The Announcements application is Social Office's main method of
conveying information to larger groups of users at once. You can make
announcements to all other users or only to members of a specific site.

The Announcements application is displayed in two places: the user's
home page and the site home page. The Announcements application displays
different information for each user based on the user's site membership.
For example, if a user is a member of the Sales site, but is browsing
the home page of the Marketing site, he will see announcements from
Sales in the Announcements application, not announcements from
Marketing.

![image](../../images/03-sites_html_26e4faf5.png)*Illustration 14: The Add
Announcements Screen.*

\

By default, only Administrators or site owners can post announcements.
They can also create a User Role (via the Control Panel) with the
ability to post announcements. See Chapter 5: The Control Panel, for
more information about creating User Roles.

To create an announcement, a user with sufficient permissions simply
needs to click the *Add Announcement* button and fill in the necessary
fields:

**To:**This is the Site where the announcement is posted.****If a user
is accessing this application through a Site home page, or if he or she
can only make announcements for one Site, this will only display one
value. However, if a user with announcement privileges on multiple Sites
accesses the application from his or her personal home page, he or she
will see a selection box with all of the available Sites.

**Title:** You can enter a title here that serves as the headline for
the announcement.

**URL:** This field enables you to set a URL for the announcement. This
is useful if you want to link directly to the announcement from
somewhere else.

**Content:** This is where you enter the main content for the
announcement. Unlike some of the more advanced text entry fields in
other applications, this field only accepts plain text characters.

**Type:** You can set the type as *General*, *News*, or *Test*,
depending on what type of announcement you're making.

**Priority:** Mark this as *Important* or *Normal*. Marking an item
*Important* will make it stay persistently above all *Normal* entries
until you edit the entry and change its priority, delete it, or the item
expires.

**Display Date / Expiration Date:** You can set a date range for when
the item will first appear and when it will stop displaying. You can use
this feature to have announcements post or expire off the list when
you're unavailable to manually make changes. By default, all
announcements expire one month after they are first posted, but you can
increase or decrease the amount of time each item is displayed.

Clicking the *Manage Entries* button will bring you to a screen
displaying all announcements and allow you to edit or delete them. You
can use this to fix any errors in existing entries or to remove unwanted
entries. You can also add entries from this page.

![image](../../images/03-sites_html_27cc81f6.png)*Illustration 20: Manage Entries
Screen.*

#### Chat

The Chat application is integrated throughout the whole of Social
Office. It's a convenient way of allowing your users to IM each other
when they're logged into your web site at the same time. It appears as a
bar at the bottom of every page—displaying who's logged on, whether
they're available, and any chats the logged-in user has open.

The Chat application is very simple to use. To change the settings,
click *Settings*. Here you can set your status (online/offline) or have
the application play a sound if someone sends you a message when the
window or tab is in the background.

Additionally, the application shows the number of your friends online.
To chat with one of them, click the *Online Friends* link, click the
friend's name, and then begin chatting. That's it! You can have multiple
chats open at a time, and even have one or more of them minimized. Your
friends are anyone that shares membership of a Site with you.

## Summary

Social Office provides an environment for easy collaboration with one
another. All of the applications are geared toward working together and
informing the team about everyone's activities. This environment
facilitates the easy sharing of data and seamless communication among
teammates. You'll find that it's much easier to work together on common
tasks when using Social Office.
