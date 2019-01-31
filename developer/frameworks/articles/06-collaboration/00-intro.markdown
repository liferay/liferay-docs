# Collaboration [](id=collaboration)

The collaboration suite helps users interact and create content together. This
can be as simple as a quick conversation in the Message Boards app or as complex
as joint file management via the Documents and Media Library. Users can Blog
their experiences and share knowledge with the Wiki. Comments can be posted on
all content. Integrated applications mean the whole is greater than the sum of
the parts: together, users create something of value that couldn't exist if they
were working in isolation. 

Underlying the collaboration suite is a set of powerful APIs so you can leverage
these features in your own apps. For example, if your app lets users create
a custom content type, you can tie into the collaboration suite's social API to
let users comment on and rate that content. You can also let your app's users 
work with files in the Documents and Media Library, and much, much more.

Here are a few of the things you can do with the collaboration suite's APIs. 

## Item Selector

An *Item Selector* is a UI component for selecting entities in a user-friendly
manner. Many Liferay apps use Item Selectors to let users select items such as
images, videos, audio files, documents, and pages. For example, the Documents
and Media Library lets users select files. 

![Figure 1: Item Selectors let users browse and select different kinds of entities.](../../images/item-selector-dialog-02.png)

The Item Selector API provides a framework for you to use, extend, and create 
Item Selectors in your own apps. 

Here are some use cases for the Item Selector API: 

1.  Letting your app's users select entities via an Item Selector. 

2.  Configuring an Item Selector to select your app's custom entity. 

3.  Customizing the selection experience by adding a new *selection view* for an 
    entity. 

## Adaptive Media

The Adaptive Media app on 
[Liferay Marketplace](https://web.liferay.com/marketplace) 
lets administrators tailor the size and quality of images to the device 
displaying them. For example, administrators can use Adaptive Media to specify 
that large, high-resolution images should only be sent to devices that can 
display them. This optimizes the user experience for everyone. Users of large, 
high-resolution devices get a large, high-resolution image. Users of other 
devices get a more suitable image that also consumes less bandwidth and 
processing power to display. 

By default, Adaptive Media integrates with Documents and Media, Blogs, and Web 
Content. You can also integrate it with your custom apps. Adaptive Media 
contains a taglib that lets you display adapted images by specifying the version 
of the file to display. You can also use Adaptive Media's finder API if you need 
to get adapted images that match other criteria (e.g., a specific resolution, a 
range of attributes, etc.). You can even customize the image scaling that 
Adaptive Media uses to produce adapted images. 

## Social API

The social API lets users interact with content throughout the portal, including
content in your apps. For example, users can provide feedback on content, share 
that content with others, subscribe to receive notifications, and more. These 
features let users stay up to date on the latest and greatest that you have to 
share. 

Here's an example of some functionality you can add to your app via the social 
API: 

**Social Bookmarks:** Let users share your app's content on social media. You 
can also create new social bookmarks if one doesn't exist for the social network 
of your choice. 

**Comments:** Let users comment on your app's content.

**Ratings:** Let users rate your app's content. You can also let administrators 
change your app's rating type (e.g., likes, stars, thumbs, etc.).

**Flagging:** Let users flag inappropriate content in your app. 

## Documents and Media API

The Documents and Media library stores uploaded files so users can use, manage, 
and share them. For example, users can embed files in content, organize them in 
folders, edit and collaborate on them with other users, and more. See the 
[user guide](/discover/portal/-/knowledge_base/7-2/managing-documents-and-media) 
for more information on the Documents and Media library's features. 

A powerful API underlies the Documents and Media library's functionality. You
can leverage this API in your own apps. For example, you could create an app
that lets users upload files to the Documents and Media library. Your app could
even let users update, delete, and copy files. 

Here's an example of some things you can do with the Documents and Media API: 

-   Create files, folders, and shortcuts
-   Delete entities
-   Update entities
-   Check out files for editing, and check them back in
-   Copy and move entities
-   Get entities
