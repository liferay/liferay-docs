# Adding Files to a Document Library

There are several reasons why you might want to upload files to your site. You
might want to share photos and video files or embed them in web content. Or you
might simply want to back them up from your device to your site. You may have
important documents that you want to share with other users. And you might want
to collaborate on documents with other users. All of these examples include
first uploading a files to your site.

To demonstrate adding files to a Document Library, we'll implement a use case
for the Lunar Resort. Resort photographers take lots of photos and video footage
of resort guests having the time of their lives. As a courtesy to the guests the
staff stores each party's photos and videos on the resort site and shares them
with the parties. At first, you might think "How does a resort's sharing of
pictures and videos relate to me and my site?" Well, even if your company isn't
in the tourism business, it probably has similar requirements for managing and
publishing files. One common requirement is the ability to enable specific
groups of users to store, organize, and share files. And no matter what kinds of
files you work with, the fundamentals of granting file operation permissions is
the same. Let's explore how to do this for the Lunar Resort.

## Granting File Permissions and Roles

The Lunar Resort needs to manage carefully who can add, view, and update groups
of files. The resort stores in their Documents and Media libraries all kinds of
files for various purposes. The libraries not only contain guest media but also
departmental documents and media. Most of the documents are meant to be
disseminated within the departments only. Using @product@, we can
implement the resort's use cases. You see, @product@'s [Roles and
Permissions](/discover/portal/-/knowledge_base/7-0/roles-and-permissions) let
you precisely specify access to documents and media files. In addition, the
Document Library's folder permissions help you organize media. Let's get started
on the right foot with the Lunar Resorts guest media files by leveraging roles,
permissions, and folders in Documents and Media.

Since the Lunar Resort's *Souvenir and Memorabilia* staff members must be able
to upload and organize guest media, let's enable a role for these purposes. Sign
in as the @product@ admin and open the *Menu* (![Product 
Menu](../../../../images/icon-menu.png)). Then navigate to *Control Panel &rarr; 
Users &rarr; Roles*. If the staff group is a part of an Organization, and that 
organization has a site, create an Organization Role. Otherwise, create it as a 
Site Role. Name the role *Resort Guest Media Manager*. Once you've created it, 
click the role's Actions 
icon (![Actions](../../../../images/icon-actions.png)) and select *Define 
Permissions*. A screen appears for you to define the role's permissions.

![Figure 1: It's often helpful to define a role for specific users to access Documents and Media from Site Administration.](../../../../images/dm-define-role-permissions.png)

In the role's permission definition screen, navigate to *Site Administration
&rarr; Content &rarr; Documents and Media*. In the *General Permissions*
section, select *Access in Site Administration* and click *Save*. This role will
manage your special set of media files. Once you've created the role, assign it
to the users who manage the media. Snappy Fingers (username: *snappy*) is the
Lunar Resort's trigger-happy photographer. We assigned her to the *Resort Guest
Media Manager* role.

+$$$

**Note:** To learn how to create a role and define a role's permissions, refer
to
[Roles and Permissions](/discover/portal/-/knowledge_base/7-0/roles-and-permissions).

$$$

You've created your specialized role and assigned users to it. In the Documents
and Media library, you need a place for that role to manage Lunar Resort guest
media. A Documents and Media folder fits the bill. In the folder we'll create,
the role will add, organize, and share the guest media files. 

## Using the Add Menu

Open the Menu, click on your site's name, and navigate to *Content &rarr;
Documents and Media* for your site. The Documents and Media screen appears and
displays the Documents and Media library's *Home* (its root folder). As you add
files and folders to the document library, they're listed here.

![Figure 2: The Documents and Media's *Home* folder starts empty. But the Add menu lets you upload and add all kinds of documents to the library.](../../../../images/dm-admin-add-menu.png)

Click the *Add* icon (![Add](../../../../images/icon-add.png)) to show what you 
can add to the Document Library. You can add documents, folders, and shortcuts 
the same as you do on your local file system. You can even configure access to 
an entirely different repository. Here are the Add menu's options:

**Folder**: lets you create a new location in the app's file system.

**Shortcut**: allows you to create a shortcut to any document that you can view.
You can set permissions on the shortcut to specify who can access the original
document through the shortcut.

**Repository**: allows you to add access to an external repository. Refer to
[Repostory Types](/discover/portal/-/knowledge_base/7-0/liferay-repository-types)
to learn how to add access to them. 

**Multiple Documents**: allows you to upload several documents at once.

**Basic Document**: allows you upload a single file that you would like the
default document type, "Basic Document," to apply to. By default, basic
documents are not described by any metadata sets.

**Google Docs**: available through an additional Liferay Plugin for Google 
Drive&trade; from the [Marketplace](https://web.liferay.com/marketplace). This 
option lets you create a file entry that links to a Google document. The section
[Accessing Google Docs&trade;](/discover/portal/-/knowledge_base/7-0/accessing-google-docs)
explains how to use this feature.

The remaining items in the Add menu are default [document 
types](/participate/liferaypedia/-/wiki/Main/document+types)
that are each described by a unique [metadata 
set](/participate/liferaypedia/-/wiki/Main/Metadata+Set). When you add a 
document belonging to a specific document type, a form appears for picking the 
file to upload and entering the data defined by the document type's metadata 
set. The *Contract* type appears below as an example. 

**Contract**: lets you upload a file that you would like the "Contract" document
type to apply to. This document type is intended to be used to describe legal
contracts. By default, contracts are described by effective date, expiration
date, contract type, status, legal reviewer, signing authority and deal name
fields. 

Any custom documents types that have been defined also appear in the Add menu.
If a document type has been created that matches the document you would like to
upload, you can select that document type from the Add menu. The Document
Library applies the metadata fields associated with the document type to your
document. On selecting a document type, you're prompted to fill in the fields
associated with it. 
