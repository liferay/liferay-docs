# What is Liferay? 

If you're reading this, I'm going to make a wild guess and say that you're probably looking for some software to help you run your web site. Creating a dynamic web site that's attractive for your users is complicated these days. You have to think about all kinds of things like user registration, security, community-building, collaboration, and of course your own, unique functionality and the design of your site. 

To create all this, you can pursue one of several strategies. You can decide to build the whole thing yourself from scratch. The reason usually given for pursuing this strategy is that you have the most freedom, since you can write whatever you want. A closer inspection, however, reveals that instead of freedom, you become a slave, writing all kinds of code that's not core to your web site. You have to write code for user registration, login, security, and session management; code for standards support, such as Facebook login, OpenID, OpenSocial, or RSS feeds; and code for general features, such as comments, tags, and ratings. You also take the most risk: you and/or your development team is responsible for every bug, glitch, user interface issue, or security hole--and the consequences for these--that can be found in your site. This strategy also requires the most *time* to implement, because you're building everything from scratch. When considering this option, therefore, you really need to think hard about whether you and your development team have the time and the expertise to handle building everything from the ground up. 

Another option is to scrounge the web for software that has some of the features you want, and then "glue" it together into one integrated site. For example, every site has some kind of content, so you might pick a content management system. But you want users to interact in some way, so you also grab forum software. Prominent users might get a blog, so you'll need software to handle that. And, of course, don't forget that you have to write your own application(s). Now your job is to integrate all of these together so that users only have to log in once, the forum software can power comments on your content, and blogs are published in web content on the home page. You might get all this working, but then you have a different problem down the road: all of these software packages will be updated by their development teams separately, on different schedules, to fix bugs, add features, and plug security holes. Now that you've customized them all, every upgrade of every software package you've chosen becomes an exercise in re-implementing your "glue" code to make everything work. 

You could also try using something like a blog that has a lot of functionality that can kind of be used as a content management system. But then, of course, things don't work exactly as you'd like them to, and features you need are missing. You either have to build those features and integrate them in, or do without them. 

As you can see, all of these options are somewhat less than ideal. But there is another solution. This solution gives you all the freedom of creating your site from the ground up, all the benefits of existing, integrated, robust applications, and a development framework that makes your application a first class citizen. It's called Liferay Portal. 

## What makes Liferay Portal unique

Liferay Portal is a web experience platform that contains all of the common applications you'd use as building blocks for your web site. Because of this, using Liferay completely changes the way you'd approach building your site, because Liferay includes all the functionality mentioned above and more. In fact, depending on what you're building, it's possible to build some sites without writing any code at all! If you've got a great application in mind, then Liferay is also a fantastic web application development platform that you can use to your advantage to release your application faster. Why? Because you can focus on your application, and use the user management, security, standards support, and general features provided by Liferay Portal without having to write them yourself. 

Of course, there are more features than just those. You can take advantage of functionality enabling users to connect with each other in social networks, to collaborate on whatever interests them, to create content, and so much more. In fact, it'll take this whole book to describe fully what Liferay can do, but we'll summarize it as best we can in the rest of the chapter. 

In short, Liferay Portal is more than a development platform, more than a content management system, more than a social network, and more than a set of collaboration tools. It's the best way to build a web site. 

## Using Liferay Portal as a content management system

One of the most common uses of Liferay Portal is as a content management system. In fact, many use Liferay Portal just for content management, whether it be web content management or management of file-based content (documents, media files, and the like). They do this because Liferay Portal's content management system is so powerful and feature-rich that it could be offered as a totally separate, standalone system of its own. Of course, the fact that it's integrated with the rest of the applications in Liferay Portal makes it all the more attractive. 

So what can it do? We'll answer that question, but take it in two parts. First, we'll look at Liferay Portal's role as a web content management system, and then we'll see how Liferay Portal excels at file-based content management. 

### Effectively building a site with Liferay WCM

The first thing you'll want to understand about Liferay WCM is that it scales to work for the tiniest of sites all the way up to the largest of sites. For example, on the small end of things you can fire up Liferay Portal, drop the Web Content Display application onto a page, and immediately start typing content into a WYSIWYG editor, in place. On the large scale of things, you can set up Liferay Portal to host many different web sites for many different purposes, all with their own domain names. Each site can take advantage of a separate staging server, where content and pages are created by teams of people using structures and templates, and updates to the production server are published on a schedule, only after having gone through a multi-step approval process. 

That's powerful. 

By default, Liferay Portal starts with a single site that has a single page. You can build any web site you wish out of this, complete with multi-nested page hierarchies if you wish, as the figure below shows. 

![Figure 1.x: ](../../images/01-page-hierarchy.png)

These pages can have any layout you like: Liferay Portal ships with several built-in, and you can create your own custom layouts and deploy them easily. Pages can be added, removed, or reordered any time, and you have the full flexibility of all the page attributes, such as meta tags and robot files, that you need. 

Pages are also integrated with Liferay's powerful permissions system, so it's easy to restrict access to certain portions of your site. You can give individual users sites of their own, with public pages that have content they create, as well as their blog, and private pages that contain their calendar and email. 

If you're running a large web site where you'll be creating and managing lots of different sub-sites for individuals and groups, you can take advantage of page templates and site templates. The former enables you to set up templates of pages with predefined layouts and applications already on them, and the latter enables you to create a whole site made up of multiple, predefined pages. 

There's even more. If you have a very large site, you might need multiple people to work on it. And you certainly don't want the live site changing before your users' eyes. For that reason, Liferay Portal provides a feature called *staging*, that lets you place your changes in a holding area while they're being worked on. You can have a local staging server, where the staged site resides on the same server as the live site, or you can have a remote staging server, where all web content work happens on a separate server from your live site. In either case, when you're ready, site changes can be pushed to the live site, either manually or on a schedule. 

![Figure 1.x: Staging supports publishing manually or on a schedule. ](../../images/04-web-content-staging-publish.png)

Liferay Portal's web content creation tools are easy and intuitive to use at all levels. If you need only basic content management capabilities for your site, you can jump right in. From the Dockbar, you can add the Web Content Display application anywhere in your page layout and enter content in place. It's easy to go from this basic level of content management to more sophisticated levels of functionality. 

For example, suppose you wanted to build an online news-oriented site. Most of the content you'll publish is an article of some kind. Liferay's web content management system lets you create a *structure* for this, so that you can capture all the information from your writers that you'd need in an article. The figure below shows what this structure might look like to a journalist who'd be entering his or her article into the system. 

![Figure 1.x: Structures allow you to specify exactly the type of data that makes up your content. You can also include tooltips to help your users understand what each field is for.](../../images/01-content-structure.png)

As you can see, you can use structures to make sure writers provide the title of the story, what type of story it will be, and the byline (i.e., the writer's name). You've made sure that all the relevant information for the story is captured in the system. 

Web content is one example of what in Liferay is called an *asset*. Assets can have meta-data attached to them, and that metadata can be used to aggregate similar assets together in searches or as published content. One way to do this in the example above is that writers can tag and categorize their stories so they can be found more easily by users. 

This is just one example, of course. But the concept is applicable to any kind of site you'd want to build. For example, if you were building a site for a zoo, you could use web content structures to help users enter data about animals in the zoo, such as their common names, their scientific names, their species, their locations in the wild, and more.  

When it comes time to publish content, structures are combined with *templates*. Templates are instructions for how to display structures, written most of the time in Velocity or Freemarker--both of which are well-known templating languages used for mixing HTML with programmatic elements. Because of this, they're very easy to write, and can help you ensure that your site has a consistent look and feel. For example, one template (you can specify multiple templates per structure if you wish) for displaying the Story example we used above might look like this: 

	#if ($storyType == "article") 

		<div id="storyTitle">
				$storyTitle.getData()
		</div>

		<div id="storyByLine">
			$storyByLine.getData()
		</div>

		<div id="storyContent">
			$content.getData()
		</div>

	#end
	
All this template does is check to see if the type of story is an article. If it is, it displays the contents of each field in the structure inside a `<div>` tag with a special ID. The theme for this content could contain CSS that displays this content in its proper context: with the title in a larger font, and so on. 

There is much more to web content. You can create abstracts, schedule when content is published and when it should be taken down (or reviewed), define related assets, and more. 

This is just the web content portion of Liferay's content management system. Liferay Portal is also great at managing file-based content. 

### Keeping track of file-based content

It's rare to find in an open source project a full-featured content management system. Most of the time, you'll find web content management systems and file-based content management systems as separate projects. Liferay Portal, however, provides you with both. As shown above, the web content management system is as robust as any other you'll find, and its file-based content management system is just as robust. 

Liferay Portal keeps the UI of its file-based content management system in an application called *Documents and Media Library*. This application resides in the control panel or can be added to any page, and, as shown below, looks very much like the file manager that you're already familiar with from your operating system. 

![Figure 1.x: Liferay Portal's Documents and Media library was purposefully designed to be familiar to anyone who uses a computer. ](../../images/01-docs-and-media.png)

Like a file manager, you can browse files and folders in nested hierarchies. You can also mount other repositories that you might have in your environment, such as Documentum (EE only) or any system that implements Content Management Interoperability Services (CMIS). It provides previews of just about every document type you can think of. And, like a file manager, you can copy and move files between folders by dragging and dropping them. Of course, if you still want to use your operating system's file manager, you can, because Liferay's Documents and Media library supports WebDAV, using the same credentials you use to log in to Liferay. 

Liferay Portal's Documents and Media library, however, is much more robust than a file manager is, because it's a full content management system. You can define ways of classifying files that may be of different types, but are meant for the same, overarching purpose. 

For example, you can define *metadata sets*, which are groups of fields describing attributes of a file. One of these that ships with the product is called *meeting metadata*, and it contains fields such as Meeting Name, Date, Time, Location, Description, and Participants. This is a generic set of fields that go together and that you'd want to use as a group. You can create as many of these as you want. 

For files, you can define *document types*. These are how users add files to the system. For example, you might create a document type called Meeting Minutes. Once you've created the document type, you can attach the Meeting Metadata set that contains many of the fields you'd want, and you can also add extra fields, such as a field for action items. When users want to add a file containing their notes for meeting minutes, they can also add all the relevant metadata about the meeting (such as the time, location, and action items). 

Of course, the system goes much further than this. Folders can be set so that only certain document types can be added to them. Workflow rules can also be added to folders to run files through an approval process that you define. In short, Liferay's file-based content management system gives you all the features you need to manage and share files in a group.  

Many Liferay Portal users see it as a robust content management system, and they use it primarily for that purpose. Now, hopefully, you can see why. We'll cover the system in-depth in the body of this book, but for now we need to look at some of the other ways you can use Liferay Portal, starting with its fantastic collaborative tools. 

## Using Liferay Portal as a collaborative platform

Many sites have grown organically. Starting with straight HTML pages for a site seems easy until you have a lot of content. That's when you want to add a content management system. In a similar fashion, as a site grows, it gains a community. It helps a site to grow even more if that community is given an opportunity to interact. That's where collaborative applications can make the difference between a site that grows and a site that stagnates. 

Liferay Portal represents an ideal collaborative platform, because it offers every standard collaborative application that's available. These applications span the complete spectrum of your needs, from personal productivity applications like a calendar and email, to community-building applications like message boards, polls, and wikis. 

![Figure 1.x: Liferay Portal's message boards are as fully featured as any standalone forum application, with the added benefit that they're integrated with the rest of the system.](../../images/01-message-boards.png)

Nowhere else will you find a suite of integrated applications with all the features of similar, standalone applications. Liferay Portal's message boards have everything you'd need in a forums application: categories and subcategories, message threads, captcha, profanity filtering (EE only), RSS feeds, email notification, posting via email, and much more. But more than this, it's integrated with everything the rest of Liferay Portal's framework. Users log in, and their profiles are used automatically by the message boards and all the other collaborative applications. And as we'll see later, functionality from the built in applications can be added to your own to provide features like comments in your own software, and you don't have to write any code to do it. 

Liferay Portal's wiki is another example of a full-featured collaborative application. It supports three different syntaxes for wiki code. Users can comment on wiki articles, and it keeps a full history of every change that's been made, allowing users to revert back to any change. It also supports RSS feeds (just about every Liferay application does) so you can subscribe to see new articles as they are posted. Each site can have one or more wikis, and each wiki can have one or more top-level nodes. 

![Figure 1.x: Liferay Portal's wiki enables users to collaboratively create complex articles. Clicking the *Details* link shows the full history of the article, including the author of each change.](../../images/01-wiki-article.png)

We could go through all of Liferay Portal's collaborative applications in a similar fashion, but let's save that for the body of the book. Liferay Portal's suite of collaborative applications includes a blog (complete with blog aggregation features so you can publish multiple users' blog entries in one place), a chat application for users who are online at the same time, message boards, a wiki, a knowledge base that you can use to publish a library of technical articles, a polling system you can use to have users vote on certain questions, and personal productivity applications like a calendar and email.

Liferay Portal includes every application you'll need to enable users to collaborate. Next, we'll see how you can use Lifeay Portal as a social platform. 

## Using Liferay as a social platform

Whether you plan to build a social network or enable social features as part of your overall user experience, Liferay Portal has what you need. Starting with a built-in API to handle all kinds of relationships between users as well as publish their activities to each other, Liferay Portal also provides a suite of applications, including a profile summary, activities feeds, social requests, a wall, and more. 

![Figure 1.x: Liferay Portal provides feeds of social activities. These feeds can contain entries from any of Liferay's built-in applications or applications that you write.](../../images/01-social-activities.png)

Social relationships within Liferay Portal work well in all types of applications, whether you're building a public social network or want to enable social features in your corporate Intranet. Users can create relationships within the system, allowing them to see updates from those whose activity they need to track. That's far more powerful than trying to subscribe to multiple individual RSS feeds or visiting multiple profiles, because the system keeps track of it for you. 

More than this, however, Liferay is a great integration platform for social applications. It fully supports the OpenSocial framework. You can use Liferay Portal's built-in OpenSocial gadget editor to create and serve your own OpenSocial gadgets. 

![Figure 1.x: Liferay Portal's OpenSocial gadget editor lets you rapidly create social applications that can be served across the web to any other OpenSocial container.](../../images/01-opensocial-gadget-editor.png)

Liferay Portal also supports the creation of Facebook applications; in fact, no additional coding is necessary to publish your Liferay applications on Facebook (you would, of course, need to use Facebook's API to use Facebook-specific features--such as posting on users' timelines). The HTML from your application is converted seamlessly to FBML, and the only thing you need to do is get an API key and canvas page URL from Facebook. 

![Figure 1.x: Any Liferay application can be published to multiple social networks with a few clicks.](../../images/01-facebook-integration.png)

As you can see, Liferay Portal is an ideal platform if you want to do anything social: add social features to your web site, create a social network of your own, create social applications to be published on other web sites, or build a social application for Facebook. 

As with social applications, Liferay Portal is also ideally suited as a platform for any web application you're considering writing. In addition to this, Liferay Portal is easily configured to be used as a shared hosting platform for multiple web sites. Let's look at the benefits you can reap by using Liferay's development platform instead of writing your application from the ground up. 

## Using Liferay as a web platform


