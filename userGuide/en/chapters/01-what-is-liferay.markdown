# What is Liferay? [](id=what-is-liferay-liferay-portal-6-2-user-guide-01-en)

Let's attempt some mind reading: you're reading this book because you need to
build a website. You're thinking about using Liferay Portal for your site, or
you've already decided to use it and you want to learn all about it. Either
way, you've come to the right place. What you'll find here is that Liferay
Portal is the right decision, and we'll be happy to tell you all the reasons
why. But since you might be in a rush, we'll give you all the reasons in a
nutshell right here, and then you can read the details in the rest of the
chapter. 

The reasons to use Liferay Portal for your website are simple: it provides a
robust *platform* to serve your site to all clients, be they desktop, mobile, or
anything in between; it provides all the standard *applications* you need to run
on your site; and it provides an easy to use development *framework* for new
applications or customization. In addition to this, Liferay Portal is developed
using an open source methodology, by people from around the world. The code base
is solid, and has been proved to be reliable and stable in mission critical
deployments in diverse industries. 

But don't just take our word for it. Let us *show* you how Liferay does all
this. Probably the first thing you want to do after you install Liferay Portal
to manage your site is to get your content published. Let's dive right in and
see how Liferay Portal handles that task. 

## Building a site with Liferay Web Content [](id=building-a-site-with-liferay-web-conten-liferay-portal-6-2-user-guide-01-en)

When you log into Liferay and look at its default screen, one thing should jump
out at you right away: it's built for all clients that access the web, not just
desktop browsers. 

![Figure 1.1: When designing pages, Liferay includes a preview that lets you see how it would look when displayed at resolutions for computers, tablets, and phones.](../../images/01-preview.png)

Another thing to notice is that all the page controls are right there for you.
For example, to add something, you click the *+* button, and then
you can add any kind of content Liferay supports. The interface--particularly in
Liferay 6.2--has been designed to get out of your way and let you do your work. 

Liferay WCM scales to work for the tiniest of sites all the way up to the
largest of sites. For example, you can click that *Add* button, choose *Web
Content Article*, and immediately start typing content into a WYSIWYG editor, in
place. Or you can set up Liferay Portal to host many different web sites, all
with their own domain names. Each site can take advantage of a separate staging
server, where content and pages are created by teams of people using structures
and templates, and updates to the production server are published on a schedule,
only after having gone through a multi-step approval process. 

That's powerful. 

By default, Liferay Portal starts with a single site that has a single page. You
can build any website you wish out of this, complete with multi-nested page
hierarchies, as the figure below shows. 

![Figure 1.2: Liferay's page hierarchies are easy to create, using a tree structure that's familiar to anyone who has used a file manager.](../../images/01-page-hierarchy.png)

These pages can have any layout you like: Liferay Portal ships with several
built-in, and you can create your own custom layouts and deploy them easily.
Pages can be added, removed, or reordered any time, and you have the full
flexibility of all the HTML page attributes, such as meta tags and robot file
declarations, that you need. 

Pages are also integrated with Liferay's powerful permissions system, so it's
easy to restrict access to certain portions of your site. You can give
individual users sites of their own, with public pages that have their content
and blog, and private pages that contain their calendars and email. 

If you're running a large website that has lots of different sub-sites for
individuals and groups, you can use page templates and site templates. The
former enables you to set up templates of pages with predefined layouts and
applications already on them, and the latter enables you to create a whole site
made up of multiple, predefined pages. 

There's even more. If you have a very large site, you might need multiple people
to work on it. And you certainly don't want the live site changing before your
users' eyes. For that reason, Liferay Portal provides a feature called
*staging* that lets you place your changes in a holding area while they're
being worked on. You can have a local staging server, where the staged site
resides on the same server as the live site, or you can have a remote staging
server, where all web content work happens on a separate server from your live
site. In either case, when you're ready, site changes can be pushed to the live
site, either manually or on a schedule. 

![Figure 1.3: Staging supports publishing manually or on a schedule.](../../images/web-content-staging-publish.png)

Liferay Portal's web content creation tools are easy and intuitive to use at all
levels. If you need only basic content management capabilities for your site,
you can jump right in. You can add the Web Content Display application anywhere
in your page layout and enter content in place. It's easy to go from this basic
level of content management to more sophisticated levels of functionality. 

For example, suppose you wanted to build a news-oriented site. Most of the
content you'll publish is an article of some kind. Liferay's web content
management system lets you create a *structure* for this, so that you can
capture all the information from your writers that you'd need in an article. The
figure below shows what this structure might look like to a journalist who'd be
entering his or her article into the system. 

![Figure 1.4: Structures allow you to specify exactly the type of data that makes up your content. You can also include tooltips to help your users understand what each field is for.](../../images/01-content-structure.png)

As you can see, you can use structures to make sure writers provide the title of
the story, what type of story it will be, and the byline (i.e., the writer's
name). You've made sure that all the relevant information for the story is
captured in the system. 

Web content is one example of an *asset*. Assets can have meta-data attached to
them, and that meta-data is used to aggregate similar assets together in
searches or as published content. One way to do this in the example above is
to tag and categorize stories so they can be found more easily by users. 

This is just one example, of course. But the concept is applicable to any kind
of site you'd want to build. For example, if you were building a site for a zoo,
you could use web content structures to help users enter data about animals in
the zoo, such as their common names, their scientific names, their species,
their locations in the wild, and more. 

When it comes time to publish content, structures are combined with *templates*.
Templates are instructions for how to display structures, written most of the
time in Freemarker or Velocity--both of which are well-known templating
languages used for mixing HTML with programmatic elements. Because of this,
they're very easy to write and can help you ensure that your site has a
consistent look and feel. 

There is much more to web content. You can create abstracts, schedule when
content is published and when it should be taken down (or reviewed), define
related assets, and more. 

This is just the web content portion of Liferay's content management system.
Liferay Portal is also great at managing file-based content. 

### Keeping track of documents, images, video, and more [](id=keeping-track-of-documents-images-video-liferay-portal-6-2-user-guide-01-en)

It's rare to find in an open source project a full-featured content management
system. Most of the time, you'll find web content management systems and
file-based content management systems as separate projects. Liferay Portal,
however, provides you with both. As shown above, the web content management
system is as robust as any other you'll find, and its file-based content
management system is the same. 

Liferay Portal keeps the UI of its file-based content management system in an
application called *Documents and Media Library*. This application resides on
the Site Administration page or can be added to any page, and, as shown below,
looks very much like the file manager that you're already familiar with from
your operating system. 

![Figure 1.5: Liferay Portal's Documents and Media library was purposefully designed to be familiar to anyone who uses a computer.](../../images/01-docs-and-media.png)

Like a file manager, you can browse files and folders in nested hierarchies. You
can also mount other repositories that you might have in your environment, such
as Documentum (enterprise subscribers only) or any system that implements
Content Management Interoperability Services (CMIS). It provides previews of
just about every document type you can think of. And, like a file manager, you
can upload, copy, and move files between folders by dragging and dropping them.
Of course, if you still want to use your operating system's file manager, you
can, because Liferay's Documents and Media library supports WebDAV, using the
same credentials you use to log in to Liferay. 

Liferay Portal's Documents and Media library, however, is much more robust than
a file manager is, because it's a full content management system. You can define
ways of classifying files that may be of different types, but are meant for the
same, overarching purpose. 

For example, *metadata sets*, are groups of fields describing attributes of a
file. One that ships with the product is called *meeting metadata*, and it
contains fields such as Meeting Name, Date, Time, Location, Description, and
Participants. This is a generic set of fields that go together and that you'd
want to use as a group. You can create as many of these as you want. 

For files, you can define *document types*. They provide a more natural way of
working with files. For example, you might create a document type called Meeting
Minutes. The file format doesn't matter: whether it's a Microsoft Word document,
an HTML file, or a text file, the document contains meeting minutes. Once
you've created the document type, you can attach the Meeting Metadata set that
contains many of the fields you'd want, and you can also add extra fields, such
as a field for action items. When users want to add files containing their
notes for meeting minutes, they can also add all the relevant metadata about the
meeting (such as the time, location, and action items). This captures the
context information that goes with the document, and it provides a much more
natural way of working with documents than just dumping them into a shared file
system. 

Of course, the system goes much further than this. Folders can be set so that
only certain document types can be added to them. Workflow rules can also be
added to folders to run files through an approval process that you define. In
short, Liferay's file-based content management system gives you all the features
you need to manage and share files in a group. 

Many Liferay Portal users see it as a robust content management system, and they
use it primarily for that purpose. Now, hopefully, you can see why. We'll cover
the system in-depth in the body of this book, but for now we need to look at
some of the other ways you can use Liferay Portal, starting with its fantastic
collaborative tools. 

## Using Liferay Portal as a collaborative platform [](id=using-liferay-portal-as-a-collaborative-liferay-portal-6-2-user-guide-01-en)

Many sites have grown organically. You may have grown your community by using
separate tools: first a forums application, and then a wiki for collaborative
documentation, and maybe even a chat application. It can be hard (and
error-prone) to integrate all these applications so your users can use them
seamlessly. Thankfully, Liferay includes a suite of collaborative applications,
and they're all integrated together. 

Liferay Portal offers every standard collaborative application that's available.
These applications range from personal productivity applications like a calendar
and email, to community-building applications like message boards, polls, and
wikis. 

![Figure 1.6: Liferay Portal's message boards are as fully featured as any standalone forum application, with the added benefit that they're integrated with the rest of the system.](../../images/01-message-boards.png)

This is a suite of integrated applications with all the features of similar,
standalone applications. For example, Liferay Portal's message boards include
categories and subcategories, message threads, captcha, RSS feeds, email
notification, posting via email, and much more. But more than this, the
applications are integrated with the rest of Liferay Portal's framework. Users
log in, and their profiles are used automatically by the message boards and all
the other collaborative applications. And as we'll see later, functionality from
the built in applications can be added to your own to provide features like
comments in your own software, and you don't have to write any code to do it. 

Liferay Portal's wiki is another example of a full-featured collaborative
application. It has support for authoring pages in a WYSWYG editor, or more
advanced users can use the easy-to-learn standard Wiki Creole syntax. Users can
comment on wiki articles, and it keeps a full history of every change that's
been made, allowing users to revert back to any change. It also supports RSS
feeds (just about every Liferay application does) so you can subscribe to see
new articles as they are posted. Each site can have one or more wikis, and each
wiki can have one or more top-level nodes. 

One important feature of all the collaborative applications--as well as web
content and documents--is the Recycle Bin. If users delete content that needs to
be restored later, you don't have to find it in your backups: it's in the
Recycle Bin. 

![Figure 1.7: The Recycle Bin can hold any kind of content.](../../images/recycle-bin-overview.png)

We could go through all of Liferay Portal's collaborative applications, but
let's save that for the body of the book. Liferay Portal's suite of
collaborative applications includes a blog (complete with blog aggregation
features so you can publish multiple users' blog entries in one place), a chat
application for users who are online at the same time, message boards, a wiki, a
knowledge base that you can use to publish a library of technical articles, a
polling system you can use to have users vote on certain questions, and personal
productivity applications like a calendar and email.

Liferay Portal includes every application you'll need to enable users to
collaborate. Next, we'll see how you can use Liferay Portal as a social
platform. 

## Using Liferay as a social platform [](id=using-liferay-as-a-social-platform-liferay-portal-6-2-user-guide-01-en)

Whether you plan to build a social network or enable social applications as part
of your overall user experience, Liferay Portal has the tools to make those
features work for you. Starting with a suite of applications--including a
profile summary, activities feeds, social requests, a wall, and more--and
rounding things out with an API to handle relationships between users as well as
publish their activities to each other, Liferay Portal helps you implement
common features of social networks. 

![Figure 1.8: Liferay Portal provides feeds of social activities. These feeds can contain entries from any of Liferay's built-in applications or applications that you write.](../../images/01-social-activities.png)

Social relationships in Liferay Portal are ideally suited for everything from
public social networks to enabling social features in your corporate Intranet.
Users can form relationships with one another, allowing them to see updates from
those whose activity they need to track. That's far more powerful than having
them subscribe to multiple individual RSS feeds or visit multiple profiles,
because the system keeps track of the updates from those with whom you have a
relationship, automatically. 

More than this, however, Liferay is a great integration platform for social
applications. It fully supports the OpenSocial framework. You can publish
gadgets you find online, or you can use Liferay Portal's built-in OpenSocial
gadget editor to create and serve your own OpenSocial gadgets. 

![Figure 1.9: Liferay Portal's OpenSocial gadget editor lets you rapidly create social applications that can be served across the web to any other OpenSocial container.](../../images/01-opensocial-gadget-editor.png)

Liferay Portal also supports the creation of Facebook applications; in fact, no
additional coding is necessary to publish your Liferay applications on Facebook
(you would, of course, need to use Facebook's API to use Facebook-specific
features like posting on users' timelines). All you need to do is get an API key
and canvas page URL from Facebook. 

![Figure 1.10: Any Liferay application can be published to multiple social networks with a few clicks.](../../images/01-facebook-integration.png)

As you can see, Liferay Portal is built with social applications in mind. It's
highly likely, however that you also have your own application you
need to run on your website. The Liferay Portal platform is ideal for the web
application you're considering writing. In addition to this, Liferay Portal is
easily configured to be used as a shared hosting platform for multiple web
sites. Let's look at the benefits you can reap by using Liferay Portal in these
ways. 

## Using Liferay as a web platform [](id=using-liferay-as-a-web-platform-liferay-portal-6-2-user-guide-01-en)

We can't even begin to imagine what you're thinking of building, but whatever it
is, you're going to put your heart and soul into it. Building it on Liferay's
web platform can give you a leg up. It provides everything you need to support
your application, so you can concentrate solely on what *you're* building, and
not the rest of the features your users expect will come along with it. 

Imagine your application for a moment. Does it require users to register on
your site? Can users comment on content contained in your application? Is there
something that users can tag or categorize? If you think about the layout of
the application, would it benefit from modularization? Could you make use of a
rich JavaScript framework with many components built into it?  How about
security--will you need to make information available to some users, but not to
all users? Liferay Portal has all of this and more available to developers. 

Liferay Portal's development framework is a great help when you're building a
web application. While the framework itself is covered in other resources such
as the *Liferay Developer's Guide* or *Liferay in Action*, the strengths of
Liferay as a platform are also apparent once you've finished writing your
application. 

For example, bug fixes to your applications are easy to apply, because Liferay
applications are hot deployed to the running server. Liferay's Marketplace gives
you a ready-made shopping center for your applications. And Liferay's web
services and JSON architecture make it easy for you to share data from your
applications with other systems running on different platforms. 

You get all this--not to mention the automatic Facebook and OpenSocial
integration mentioned above--with Liferay's development platform. It's a very
powerful platform, and certainly worth your investigation. 

### A great integration platform [](id=a-great-integration-platform-liferay-portal-6-2-user-guide-01-en)

If you're building an enterprise system, portals were designed in the first
place to be a single point of entry to your users' applications and content.
Since Liferay Portal integrates well with user directories such as LDAP and
Active Directory, and single sign-on systems such as SAML and OpenSSO, it fits
well into your enterprise systems. This allows you to use it as an integration
platform for existing applications. 

Liferay Portal, since it adheres to the JSR standard for portlets, was designed
from the ground up for application integration. You can add any application
installed on the system to any page in the portal. You can make use of APIs
provided by other systems to integrate their data into an application window in
Liferay. And applications you create with Liferay's Service Builder API are web
service-enabled from the start. 

### Hosting multiple sites on Liferay Portal [](id=hosting-multiple-sites-on-liferay-porta-liferay-portal-6-2-user-guide-01-en)

Liferay Portal excels as a multi-site hosting platform. You can use it to host
multiple sites under the same overall architecture, or you could host several
completely different websites based solely on Liferay's ability to serve
multiple instances of itself from the same physical installation. 

In the first scenario, Liferay Portal's Sites architecture lets you create
multiple, different websites that have public and/or private sets of pages and
as many pages within those sets as you'd like. Users can join and leave open
sites with one click. Some sites can be defined as restricted or private, and
users can't access those unless they're added by site administrators. All of
these sites can have canonical domain names such as baseballcards.liferay.com or
progrock.liferay.com. 

Using this construct, you can build anything from Facebook, to Yahoo Groups, to
SourceForge, to the now-defunct-but-once-loved Geocities. There is no limit to
the number of sites you can have: some Liferay installations have only one or
two, but others have many thousands. For those larger installations, Liferay
Portal contains a complete site membership management framework that lets
administrators manage automatic site members for groups of users. It really is
built to scale to the size you need. 

In the second scenario, Liferay Portal lets you create completely separate
instances of itself from the same installation. Users, groups, organizations,
sites, and roles from each instance are kept completely separate. If a user
registers for a user id on one instance, he or she would have to register as a
new user on another instance as well. 

This lets you host many different, separate websites from one Liferay Portal
installation. Users of each instance have access to the same powerful content
management, collaboration, social, and web development platform that they'd have
if they were operating from a single, standalone installation.

Okay, so maybe this still isn't enough for you. Let's see how you can customize
Liferay Portal so that it looks and operates exactly the way you've envisioned
for your site. 

## Extending and customizing Liferay for your own needs [](id=extending-and-customizing-liferay-for-y-liferay-portal-6-2-user-guide-01-en)

Beyond using Liferay as a development platform for new applications, Liferay
Portal has also been designed to be extended and modified. As an open source
project, its source code is available, but Liferay Portal's developers have
designed the product to make it easy to build whatever you want out of it. 

The first (and easiest) way of customizing parts of Liferay Portal is with
Application Display templates. These let you change the way built-in
applications look. For example, if you don't like the Documents and Media
Library's file manager view with large icons, you can create an Application
Display template that shows documents in a list view. If you don't like the
layout of the Blogs portlet, you can change it so that it has the look you want. 

Liferay Portal goes far beyond this, though. Special software components called
*hook* and *ext* plugins enable developers to change any aspect of Liferay's
interface and behavior--without having to modify any of Liferay Portal's source
code. This provides you all the benefits of building your site from scratch, but
without all the effort to actually build from scratch. If you want to make a
change to the user registration screens, add support for a proprietary single
sign-on mechanism that you've written, add a feature to the message boards
application, or anything else, you can make those customizations. And if you're
a developer, we're sure you know that it's a whole lot easier to customize
something that almost does things exactly the way you want than it is to write
that feature from scratch. With Liferay Portal, you *can* have your cake and eat
it too. 

## Summary [](id=summary-liferay-portal-6-2-user-guide-01-en)

So what is Liferay? As you can see, it's hard to describe, because it does so
much. What we've essentially done is say it's a totally awesome content and
document managing, user collaborating, socially enabling, application
developing, corporate integrating, completely customizable platform for building
the Internet. If we'd said that up front, you'd probably have doubted us.
Hopefully now, you can see that it's true. 

If you're interested in using Liferay Portal for *your* product, continue
reading. We'll go through all of these features (and more that we couldn't
mention) throughout the rest of the book. 
