# Building a site with Liferay Web Experience [](id=building-a-site-with-liferay-web-experience)

When you log into @product@ and look at its default screen, one thing to notice is
that it's built for all clients that access the web, not just desktop browsers. 

![Figure 1: If you click this button, a preview appears that lets you see how the page would look when displayed at various device resolutions.](../../images/01-simulate.png)

All the page controls are right there for you. For example, to add content or
applications to the page, click the *+* button, and then you can add any kind
of content @product@ supports. The interface gets out of your way so you can do
your work. 

Liferay Web Experience Management scales to work for the tiniest of sites all
the way up to the largest of sites. You can get started quickly by clicking
that *Add* button and choosing *Add New* &rarr; *Basic Web Content*, where you
can immediately start typing content into a WYSIWYG editor, in place. 

If you are comfortable with developing websites, you can instead click the Menu
button on the left 
(![Menu](../../images/icon-menu.png)), select *Build* &rarr; *Page Fragments*, and use the Fragment Editor to build a page out of HTML, CSS, and JavaScript. 

![Figure 2: Fragments comprise HTML, CSS, and JavaScript, and can be used as building blocks for pages.](../../images/01-fragment-editor.png)

Large organizations can use @product@ to host many different websites, all
with their own domain names. Each site can take advantage of a separate staging
server, where content and pages are created by teams of people using structures
and templates, and updates to the production server are published on
a schedule, only after having gone through a multi-step approval process. 

That's powerful. 

By default, @product@ starts with a single site that has a single page. You
can build any website you wish out of this, complete with multi-nested page
hierarchies, as the figure below shows. 

![Figure 3: Liferay's page hierarchies are easy to create, using a tree structure that's familiar to anyone who has used a file manager.](../../images/01-page-hierarchy.png)

Pages can be added, removed, or reordered any time, and you have the full
flexibility of all the HTML page attributes like meta tags and robot file
declarations, as well as full control over the layout. 

Pages are also integrated with Liferay's powerful permissions system, so it's
easy to restrict access to certain portions of your site. You can give
individual users sites of their own, with public pages that have their content
and blog and private pages that contain their calendars and profiles. 

If you run a large website with lots of different sub-sites for individuals
and groups, you can use page templates and site templates. Page templates have
layouts and applications already on them, and site template predefine a whole
site made up of multiple page templates. 

Web content is one example of an *asset*. Assets are meta-data attached to
content types, and that meta-data aggregates similar assets together in
searches or as published content. One way to do this is to tag and categorize
content so it can be found more easily by users. 

There is much more to web content. You can create structures (pre-defined
content formats), templates (designs for formatting structures), schedule when
content is published and when it should be taken down (or reviewed), define
related assets, stage multiple variations of your site, and more. 

This is just the web content portion of the content management system.
@product@ is also great at managing file-based content. 

## Keeping Track of Documents, Images, Video, and More [](id=keeping-track-of-documents-images-video-and-more)

Liferay's file-based content management system is in an application called
*Documents and Media Library*. This application, as shown below, looks like the
file manager that you're already familiar with from your operating system. 

![Figure 4: @product@'s Documents and Media library was purposefully designed to be familiar to anyone who uses a computer.](../../images/01-docs-and-media.png)

Like a file manager, you can browse files and folders in nested hierarchies. You
can also mount other repositories that you might have in your environment, such
as any system that implements Content Management Interoperability Services
(CMIS). It generates previews of many document types. And, like a file manager,
you can upload, copy, and move files between folders by dragging and dropping
them. Of course, if you still want to use your operating system's file manager
you can, because the Documents and Media library supports WebDAV, using the
same credentials you use to log in. 

@product@'s Documents and Media library, however, is much more robust than
a file manager is, because it's a full content management system. You can define
ways of classifying files that may be of different types, but are meant for the
same, overarching purpose. 

For example, [*metadata sets*](/discover/portal/-/knowledge_base/7-1/metadata-sets)
are groups of fields describing attributes of a file. These can be combined to
define [*document types*](/discover/portal/-/knowledge_base/7-1/document-types). 
For example, you might create a document type called
Meeting Minutes. The file format doesn't matter: whether it's a Microsoft Word
document, an HTML file, or a text file, the document contains meeting minutes. 

Of course, the system goes much further than this. Folders can be set so that
only certain document types can be added to them. You can add workflow rules to
folders so files go through an approval process that you define. In short,
@product@'s file-based content management system gives you what you need
to manage and share files in a group. 

There are many other ways you can use @product@, starting with its
collaborative tools. 
