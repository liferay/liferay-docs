# Using Liferay Portal as a Content Management System [](id=using-liferay-portal-as-a-content-management-system)

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

## Effectively Building a Site with Liferay WCM [](id=effectively-building-a-site-with-liferay-wcm)

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

![Figure 1.1: Liferay's page hierarchies are easy to create, using a tree
structure that's familiar to anyone who has used a file
manager.](../../images/01-page-hierarchy.png)

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

## Keeping Track of Documents, Images, Video, and More [](id=keeping-track-of-documents-images-video-and-more)

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
