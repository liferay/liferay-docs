---
header-id: collaboration
---

# Collaboration

[TOC levels=1-4]

Underlying the 
[collaboration suite](/discover/portal/-/knowledge_base/7-2/collaboration) 
is a set of powerful APIs that add collaboration features to your apps. For 
example, if your app contains a custom content type, you can use the 
collaboration suite's social API to enable comments and ratings for that 
content. You can also integrate your app with the Documents and Media Library, 
and much more. 

Here are a few of the things you can do with the collaboration suite's APIs. 

[TOC levels=4 hierarchy]

## Item Selector

An *Item Selector* is a UI component for selecting entities in a user-friendly
manner. Many Liferay apps use Item Selectors to select items such as images, 
videos, audio files, documents, and pages. For example, the Documents and Media 
Item Selector selects files. 

![Figure 1: Item Selectors select different kinds of entities.](../../images/item-selector-dialog-02.png)

The Item Selector API provides a framework for you to use, extend, and create 
Item Selectors in your apps. 

Here are some use cases for the Item Selector API: 

1.  Selecting entities with an Item Selector. 

2.  Configuring an Item Selector to select your app's custom entity. 

3.  Adding a new *selection view* to customize the selection experience.

## Adaptive Media

The 
[Adaptive Media](/discover/portal/-/knowledge_base/7-2/adapting-your-media-across-multiple-devices) 
app tailors the size and quality of images to the device displaying them. For 
example, you can configure Adaptive Media to send large, high-resolution images 
only to devices that can display them. Other devices get images that consume 
less bandwidth and processing power. 

By default, Adaptive Media integrates with Documents and Media, Blogs, and Web 
Content. You can also integrate it with your apps. Adaptive Media contains a 
taglib that displays the adapted image matching the file version you supply. You 
can also use Adaptive Media's finder API if you need to get adapted images that 
match other criteria (e.g., a specific resolution, a range of attributes, etc.). 
You can even customize the image scaling that Adaptive Media uses to produce 
adapted images. 

## Social API

Users interact with content via @product@'s social features. For example, users 
can provide feedback on content, share that content with others, subscribe to 
receive notifications, and more. Use the social API to enable such functionality 
in your apps. 

Here's an example of some functionality you can add to your apps via the social 
API: 

**Social Bookmarks:** Share content on social media. You can also create new 
social bookmarks if one doesn't exist for your social network of choice. 

**Comments:** Comment on content.

**Ratings:** Rate content. Administrators can also change the rating type (e.g., 
likes, stars, thumbs, etc.). 

**Flags:** Flag inappropriate content. 

## Documents and Media API

Users can use, manage, and share files in the Documents and Media Library. For 
example, users can embed files in content, organize them in folders, edit and 
collaborate on them with other users, and more. See the 
[user guide](/discover/portal/-/knowledge_base/7-2/managing-documents-and-media) 
for more information on the Documents and Media Library's features. 

A powerful API underlies the Documents and Media Library's functionality. You 
can leverage this API in your apps. For example, you could create an app that 
uploads files to the Documents and Media Library. Your app could even update, 
delete, and copy files. 

Here's an example of some things you can do with the Documents and Media API: 

-   Create files, folders, and shortcuts.
-   Delete entities.
-   Update entities.
-   Check out files for editing, and check them back in.
-   Copy and move entities.
-   Get entities.
