# What is Liferay?  [](id=lp-6-1-ugen01-what-is-liferay--0)

If you're reading this, we're going to make a wild guess and say that you're
probably looking for some software to help you run your web site. Creating a
dynamic web site that's more than brochureware is complicated these days. You
have to think about all kinds of things like user registration, security,
community-building, collaboration, and of course your own, unique functionality
and the design of your site. 

To create all this, you can pursue several different strategies. You can decide
to build the whole thing yourself from scratch. The reason usually given for
pursuing this strategy is that you have the most freedom, since you can write
whatever you want. A closer inspection, however, reveals that instead of
freedom, you become a slave, writing all kinds of code that's not core to your
web site. You have to write code for user registration, login, security, and
session management; code for standards support, such as Facebook login, OpenID,
OpenSocial, or RSS feeds; and code for general features, such as comments, tags,
and ratings. You also take the most risk: you and/or your development team are
responsible for every bug, glitch, user interface issue, or security hole--and
the consequences for these--that can be found in your site. This strategy also
requires the most *time* to implement, because you're building everything from
scratch. When considering this option, therefore, you really need to think hard
about whether you and your development team have the time and the expertise to
handle building everything from the ground up. 

Another option is to scrounge the web for software that has some of the features
you want, and then "glue" it together into one integrated site. For example,
every site has some kind of content, so you might pick a content management
system. But you want users to interact in some way, so you also grab forum
software. Prominent users might get a blog, so you'll need software to handle
that. And, of course, don't forget that you have to write your own
application(s). You're attempting to build a super app out of a collection of
disparate, but best-of-breed individual applications. You've collected the
applications, and now your job is to integrate all of these together so that
users only have to log in once, the forum software can power comments on your
content, and blogs are published in a nice, customizable feed on the home page.
As you work on this, you find that gluing all these applications together isn't
as easy as you thought. You might get all of it working, but then you have a
different problem down the road: a maintenance nightmare. All of these software
packages will be updated by their development teams separately, on different
schedules, to fix bugs, add features, and plug security holes. Now that you've
customized them all, every upgrade of every software package you've chosen
becomes an exercise in re-implementing your "glue" code to make it all work. 

You could also try using something like a blog that has a lot of functionality
that can kind of be used as a content management system. But then, of course,
things don't work exactly as you'd like them to, and features you need are
missing. You either have to build those features and integrate them in, or do
without them. 

One final option, if you have a budget, is to buy something that mostly fits
what you want to do, with the intention of customizing it to fit your needs. The
goal here is to get something up and running as quickly as possible, by taking
advantage of someone's product and support. The devil here, though, is in the
details: it's the customization--particularly with products that are not open
source--that can be most difficult. License agreements need to be negotiated to
get you access to non-documented, internal APIs, and many products are simply
not designed to be customized to the extent that you might want to have them
customized. It can take more time to customize than you though, and you usually
run into some limitation that keeps you from building exactly what you need.
Alternatively, you may wind up having to pay expensive consultants to get the
result that you want, and after that, you've paid more than you initially
thought you would, and you have a complicated situation when it comes to
upgrading your system. 

As you can see, all of these options have pros and cons to them. There are times
when building from scratch makes sense, and there are times when integration or
purchasing a solution make sense. But what if you could have all of these
strategies combined? What if there was a product that gave you the freedom to
create whatever you want, had tons of reusable functionality, was designed for
customization, and was a single, unified product with a clear upgrade path? 

There is. This solution gives you all the freedom of creating your site from the
ground up, all the benefits of existing, integrated, robust applications, and a
development framework that makes your application a first class citizen. It's
called Liferay Portal. 

## What makes Liferay Portal unique [](id=lp-6-1-ugen01-what-makes-liferay-portal-unique-0)

Liferay Portal is a web experience platform that contains all of the common
applications you'd use as building blocks for your web site. Because of this,
using Liferay completely changes the way you'd approach building your site,
because Liferay includes all the functionality mentioned above and more. In
fact, depending on what you're building, it's possible to build some sites
without writing any code at all! If you've got a great application in mind, then
Liferay is also a fantastic web application development platform that you can
use to your advantage to release your application faster. Why? Because you can
focus on your application, and use the user management, security, standards
support, and general features provided by Liferay Portal without having to write
them yourself. 

Of course, there are more features than just those. You can take advantage of
functionality enabling users to connect with each other in social networks, to
collaborate on whatever interests them, to create content, and so much more. In
fact, it'll take this whole book to describe fully what Liferay can do, but
we'll summarize it as best we can in the rest of this chapter. 

In short, Liferay Portal is more than a development platform, more than a
content management system, more than a social network, and more than a set of
collaboration tools. It's the best way to build a web site. 

## Using Liferay Portal as a content management system [](id=lp-6-1-ugen01-using-liferay-portal-as-a-content-management-system-0)

One of the most common uses of Liferay Portal is as a content management system.
In fact, many use Liferay Portal just for content management, whether it be web
content management or management of file-based content (documents, media files,
and the like). They do this because Liferay Portal's content management system
is so powerful and feature-rich that it could be offered as a totally separate,
standalone system of its own. Of course, the fact that it's integrated with the
rest of the applications in Liferay Portal makes it all the more attractive. 

So what can it do? We'll answer that question, but take it in two parts. First,
we'll look at Liferay Portal's role as a web content management system, and then
we'll see how Liferay Portal excels at file-based content management. 

### Effectively building a site with Liferay WCM [](id=lp-6-1-ugen01-effectively-building-a-site-with-liferay-wcm-0)

The first thing you'll want to understand about Liferay WCM is that it scales to
work for the tiniest of sites all the way up to the largest of sites. For
example, on the small end of things you can fire up Liferay Portal, drop the Web
Content Display application onto a page, and immediately start typing content
into a WYSIWYG editor, in place. On the large scale of things, you can set up
Liferay Portal to host many different web sites for many different purposes, all
with their own domain names. Each site can take advantage of a separate staging
server, where content and pages are created by teams of people using structures
and templates, and updates to the production server are published on a schedule,
only after having gone through a multi-step approval process. 

That's powerful. 

By default, Liferay Portal starts with a single site that has a single page. You
can build any web site you wish out of this, complete with multi-nested page
hierarchies, as the figure below shows. 

![Figure 1.1: ](../../images/01-page-hierarchy.png)

These pages can have any layout you like: Liferay Portal ships with several
built-in, and you can create your own custom layouts and deploy them easily.
Pages can be added, removed, or reordered any time, and you have the full
flexibility of all the HTML page attributes, such as meta tags and robot file
declarations, that you need. 

Pages are also integrated with Liferay's powerful permissions system, so it's
easy to restrict access to certain portions of your site. You can give
individual users sites of their own, with public pages that have their content
and blog, and private pages that contain their calendar and email. 

If you're running a large web site where you'll be creating and managing lots of
different sub-sites for individuals and groups, you can take advantage of page
templates and site templates. The former enables you to set up templates of
pages with predefined layouts and applications already on them, and the latter
enables you to create a whole site made up of multiple, predefined pages. 

There's even more. If you have a very large site, you might need multiple people
to work on it. And you certainly don't want the live site changing before your
users' eyes. For that reason, Liferay Portal provides a feature called
*staging*, that lets you place your changes in a holding area while they're
being worked on. You can have a local staging server, where the staged site
resides on the same server as the live site, or you can have a remote staging
server, where all web content work happens on a separate server from your live
site. In either case, when you're ready, site changes can be pushed to the live
site, either manually or on a schedule. 

![Figure 1.2: Staging supports publishing manually or on a
schedule.](../../images/04-web-content-staging-publish.png)

Liferay Portal's web content creation tools are easy and intuitive to use at all
levels. If you need only basic content management capabilities for your site,
you can jump right in. From the Dockbar, you can add the Web Content Display
application anywhere in your page layout and enter content in place. It's easy
to go from this basic level of content management to more sophisticated levels
of functionality. 

For example, suppose you wanted to build an online news-oriented site. Most of
the content you'll publish is an article of some kind. Liferay's web content
management system lets you create a *structure* for this, so that you can
capture all the information from your writers that you'd need in an article. The
figure below shows what this structure might look like to a journalist who'd be
entering his or her article into the system. 

![Figure 1.3: Structures allow you to specify exactly the type of data that
makes up your content. You can also include tooltips to help your users
understand what each field is for.](../../images/01-content-structure.png)

As you can see, you can use structures to make sure writers provide the title of
the story, what type of story it will be, and the byline (i.e., the writer's
name). You've made sure that all the relevant information for the story is
captured in the system. 

Web content is one example of what in Liferay is called an *asset*. Assets can
have meta-data attached to them, and that metadata can be used to aggregate
similar assets together in searches or as published content. One way to do this
in the example above is that writers can tag and categorize their stories so
they can be found more easily by users. 

This is just one example, of course. But the concept is applicable to any kind
of site you'd want to build. For example, if you were building a site for a zoo,
you could use web content structures to help users enter data about animals in
the zoo, such as their common names, their scientific names, their species,
their locations in the wild, and more.  

When it comes time to publish content, structures are combined with *templates*.
Templates are instructions for how to display structures, written most of the
time in Velocity or Freemarker--both of which are well-known templating
languages used for mixing HTML with programmatic elements. Because of this,
they're very easy to write, and can help you ensure that your site has a
consistent look and feel. 

There is much more to web content. You can create abstracts, schedule when
content is published and when it should be taken down (or reviewed), define
related assets, and more. 

This is just the web content portion of Liferay's content management system.
Liferay Portal is also great at managing file-based content. 

### Keeping track of documents, images, video, and more [](id=lp-6-1-ugen01-keeping-track-of-documents-images-video-and-more-0)

It's rare to find in an open source project a full-featured content management
system. Most of the time, you'll find web content management systems and
file-based content management systems as separate projects. Liferay Portal,
however, provides you with both. As shown above, the web content management
system is as robust as any other you'll find, and its file-based content
management system is the same.  

Liferay Portal keeps the UI of its file-based content management system in an
application called *Documents and Media Library*. This application resides in
the control panel or can be added to any page, and, as shown below, looks very
much like the file manager that you're already familiar with from your operating
system. 

![Figure 1.4: Liferay Portal's Documents and Media library was purposefully
designed to be familiar to anyone who uses a computer.
](../../images/01-docs-and-media.png)

Like a file manager, you can browse files and folders in nested hierarchies. You
can also mount other repositories that you might have in your environment, such
as Documentum (EE only) or any system that implements Content Management
Interoperability Services (CMIS). It provides previews of just about every
document type you can think of. And, like a file manager, you can copy and move
files between folders by dragging and dropping them. Of course, if you still
want to use your operating system's file manager, you can, because Liferay's
Documents and Media library supports WebDAV, using the same credentials you use
to log in to Liferay. 

Liferay Portal's Documents and Media library, however, is much more robust than
a file manager is, because it's a full content management system. You can define
ways of classifying files that may be of different types, but are meant for the
same, overarching purpose. 

For example, you can define *metadata sets*, which are groups of fields
describing attributes of a file. One of these that ships with the product is
called *meeting metadata*, and it contains fields such as Meeting Name, Date,
Time, Location, Description, and Participants. This is a generic set of fields
that go together and that you'd want to use as a group. You can create as many
of these as you want. 

For files, you can define *document types*. They provide a more natural way of
working with files. For example, you might create a document type called Meeting
Minutes, because this is how we as humans conceptualize our documents. It
doesn't really matter whether it's a Microsoft Word document, an HTML file, or a
text file--the document contains meeting minutes. Once you've created the
document type, you can attach the Meeting Metadata set that contains many of the
fields you'd want, and you can also add extra fields, such as a field for action
items. When users want to add a file containing their notes for meeting minutes,
they can also add all the relevant metadata about the meeting (such as the time,
location, and action items). This captures the context information that goes
with the document, and it provides a much more natural way of working with
documents than just dumping them into a shared file system. 

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

## Using Liferay Portal as a collaborative platform [](id=lp-6-1-ugen01-using-liferay-portal-as-a-collaborative-platform-0)

Many sites have grown organically. You may have grown your community by using
separate tools: first a forums application, and then a wiki for collaborative
documentation, and maybe even a chat application. It can be hard (and
error-prone) to integrate all of these applications so your users can use them
seamlessly. Thankfully, Liferay includes a suite of collaborative applications
you can use, and they're all integrated together.  

Liferay Portal offers every standard collaborative application that's available.
These applications range from personal productivity applications like a calendar
and email, to community-building applications like message boards, polls, and
wikis. 

![Figure 1.5: Liferay Portal's message boards are as fully featured as any
standalone forum application, with the added benefit that they're integrated
with the rest of the system.](../../images/01-message-boards.png)

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

![Figure 1.6: Liferay Portal's wiki enables users to collaboratively create
complex articles. Clicking the *Details* link shows the full history of the
article, including the author of each change.](../../images/01-wiki-article.png)

We could go through all of Liferay Portal's collaborative applications in a
similar fashion, but let's save that for the body of the book. Liferay Portal's
suite of collaborative applications includes a blog (complete with blog
aggregation features so you can publish multiple users' blog entries in one
place), a chat application for users who are online at the same time, message
boards, a wiki, a knowledge base that you can use to publish a library of
technical articles, a polling system you can use to have users vote on certain
questions, and personal productivity applications like a calendar and email.

Liferay Portal includes every application you'll need to enable users to
collaborate. Next, we'll see how you can use Lifeay Portal as a social platform. 

## Using Liferay as a social platform [](id=lp-6-1-ugen01-using-liferay-as-a-social-platform-0)

Whether you plan to build a social network or enable social applications as part
of your overall user experience, Liferay Portal has the tools to make those
features work for you. Starting with a suite of applications--including a
profile summary, activities feeds, social requests, a wall, and more--and
rounding things out with an API to handle relationships between users as well as
publish their activities to each other, Liferay Portal helps you implement
common features of social networks to enhance your existing site. 

![Figure 1.7: Liferay Portal provides feeds of social activities. These feeds
can contain entries from any of Liferay's built-in applications or applications
that you write.](../../images/01-social-activities.png)

Social relationships within Liferay Portal are ideally suited for many different
kinds of implementations, whether you're building a public social network or
want to enable social features in your corporate Intranet. Users can create
relationships within the system, allowing them to see updates from those whose
activity they need to track. That's far more powerful than having them subscribe
to multiple individual RSS feeds or visit multiple profiles, because the system
keeps track of the updates from those with whom you have a relationship,
automatically. 

More than this, however, Liferay is a great integration platform for social
applications. It fully supports the OpenSocial framework. You can use Liferay
Portal's built-in OpenSocial gadget editor to create and serve your own
OpenSocial gadgets. 

![Figure 1.8: Liferay Portal's OpenSocial gadget editor lets you rapidly create
social applications that can be served across the web to any other OpenSocial
container.](../../images/01-opensocial-gadget-editor.png)

Liferay Portal also supports the creation of Facebook applications; in fact, no
additional coding is necessary to publish your Liferay applications on Facebook
(you would, of course, need to use Facebook's API to use Facebook-specific
features--such as posting on users' timelines). The only thing you need to do is
get an API key and canvas page URL from Facebook. 

![Figure 1.9: Any Liferay application can be published to multiple social
networks with a few clicks.](../../images/01-facebook-integration.png)

As you can see, Liferay Portal is built for social applications: adding social
features to your web site, creating a social network of your own, creating
social applications to be published on other web sites, or building a social
application for Facebook. 

As with social applications, Liferay Portal is also an easy to use, robust
platform for any web application you're considering writing. In addition to
this, Liferay Portal is easily configured to be used as a shared hosting
platform for multiple web sites. Let's look at the benefits you can reap by
using Liferay Portal in these ways. 

## Using Liferay as a web platform [](id=lp-6-1-ugen01-using-liferay-as-a-web-platform-0)

We can't even begin to imagine what you're thinking of building, but whatever it
is, you're going to put your heart and soul into it. Building it on Liferay's
web platform can give you a leg up, by providing to you everything you need to
support your application, so you can concentrate solely on what *you're*
building, and not the rest of the features your users expect will come along
with it. 

### Liferay as an application development platform [](id=lp-6-1-ugen01-liferay-as-an-application-development-platform-0)

Imagine your application for a moment. Does it require users to register with
your site? Will they be able to comment on content contained within your
application? Is there some asset that users can tag or categorize? If you think
about the layout of the application, would it benefit from modularization? Could
you make use of a rich JavaScript framework with many components built into it?
How about security--will you need to make information available to some users,
but not to all users? Liferay Portal has all of this and more available to the
developer, so you don't have to write it yourself. 

Liferay Portal's development framework is a great help when you're building a
web application. While the framework itself is covered in other resources such
as the *Liferay Developer's Guide* or *Liferay in Action*, the strengths of
Liferay as a platform are also apparent once you've finished writing your
application. 

For example, bug fixes to your applications are easy to apply, because Liferay
applications are hot deployed to the running server. Liferay's Marketplace gives
you a ready-made shopping center for your applications. And Liferay's web
services and JSON architecture make it easy for you to share data from your
applications to other systems running on different platforms. 

You get all this--not to mention the automatic Facebook and OpenSocial
integration mentioned above--simply by using Liferay's development platform.
It's a very powerful platform, and certainly worth your investigation. 

### A great integration platform [](id=lp-6-1-ugen01-a-great-integration-platform-0)

If you're building an enterprise system, portals were designed in the first
place to be a single point of entry to your users' applications and content.
Since Liferay Portal integrates well with user directories such as LDAP and
Active Directory, and single sign-on systems such as SAML and OpenSSO, it fits
well into your enterprise systems. This allows you to use it as an integration
platform for existing applications. 

Liferay Portal, since it adheres to the JSR standard for portlets, was designed
from the ground up for application integration. You can mix and match any
application installed in the system on any page within the portal. You can make
use of any APIs provided by other systems to integrate their data into an
application window in Liferay. And applications you create with Liferay's
Service Builder API are web service-enabled from the start. 

### Hosting multiple sites on Liferay Portal [](id=lp-6-1-ugen01-hosting-multiple-sites-on-liferay-portal-0)

Liferay Portal excels as a multi-site hosting platform. You can use it to host
multiple sites under the same overall architecture (like Facebook, MySpace, or
Pinterest offer to their users), or you could host several completely different
web sites based solely on Liferay's ability to serve multiple instances of
itself from the same physical installation. 

In the first scenario, Liferay Portal's Sites architecture lets you create
multiple, different web sites that have public and/or private sets of pages and
as many pages within those sets as you'd like. Users join the web site, and once
they're members, they can join and leave open sites with one click. Some sites
can be defined as restricted or private, and users can't access those unless
they're added by site administrators. All of these sites can have canonical
domain names such as baseballcards.liferay.com or progrock.liferay.com. 

Using this construct, you can build anything from Facebook, to Yahoo Groups, to
SourceForge, to the now-defunct-but-once-loved Geocities. There is no limit to
the number of sites you can have: some Liferay installations have only one or
two, but others have many thousands. 

In the second scenario, Liferay Portal lets you create completely separate
instances of itself from the same installation. Users, groups, organizations,
sites, and roles from each instance are kept completely separate. If a user
registers for a user id on one instance, he or she would have to register as a
new user on another instance as well. 

This lets you host many different, separate web sites from one Liferay Portal
installation. Users of each instance have access to the same powerful content
management, collaboration, social, and web development platform that they'd have
if they were operating from a single, standalone installation.

## Extending and customizing Liferay for your own needs [](id=lp-6-1-ugen01-extending-and-customizing-liferay-for-your-own-needs--0)

Beyond using Liferay as a development platform for new applications, Liferay
Portal has also been designed to be extended and modified. As an open source
project, its source code is available, but Liferay Portal's developers have
designed the product to make it easy to build whatever you want out of it. 

Special software components called *hook* and *ext* plugins enable developers to
change any aspect of Liferay's interface and behavior--without having to modify
any of Liferay Portal's source code. This provides you all the benefits of the
"build from scratch" strategy we mentioned earlier, but without all the effort
to build from scratch. If you want to make a change to the user registration
screens, add support for a proprietary single sign-on mechanism that you've
written, revise the user interface for the message boards application, or
anything else, you can make those customizations. And if you're a developer,
we're sure you know that it's a whole lot easier to customize something that
*almost* does things exactly the way you want than it is to write that feature
from scratch. With Liferay Portal, you *can* have your cake and eat it too. 

## Summary [](id=lp-6-1-ugen01-summary-0)

So what is Liferay? As you can see, it's hard to describe, because it does so
much. What we've essentially done is say it's a totally awesome content and
document managing, user collaborating, socially enabling, application
developing, corporate integrating, completely customizable platform for building
the Internet. If we'd said that up front, you'd probably have doubted us.
Hopefully now, you can see that it's true. 

If you're interested in using Liferay Portal for *your* product, continue
reading. We'll go through all of these features (and more that we couldn't
mention) throughout the rest of the book. 
