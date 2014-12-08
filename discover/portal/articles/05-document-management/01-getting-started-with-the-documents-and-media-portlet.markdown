# Getting Started with the Documents and Media Portlet [](id=getting-started-with-the-documents-and-media-portlet)

The Documents and Media portlet, like many other of Liferay's portlets, is
non-instanceable. This means that each page on your portal can host at most one
such portlet. Furthermore, if you add multiple Documents and Media portlets to
pages in the same site, these portlets by default share the same data sets since they
are scoped by site. However, you can add multiple Documents and Media *Display*
portlets to a page. Then you can choose specific subfolders from actual
Documents and Media repositories to display. Remember that users, by default,
have their own personal sites with public and private pages. They can use their
personal sites to host Documents and Media portlets for storing or sharing their
own files.

![Figure 5.1: The Documents and Media portlet offers a plethora of options to choose from.](../../images/05-document-library.png)

The Documents and Media portlet's default view displays the contents of the
*Home* folder. The links on the left side of the portlet windows are filters.
You can use these filters to choose what you want the main window of the portlet
to show. *Recent* displays documents users have recently uploaded, edited or
downloaded. *Mine* shows *your* documents; in other words, the documents you
have uploaded. *Browse by Type* shows a list of options you can choose to
help filter your results. If you click on one of these filters, the main portlet
window shows only documents that belong to the selected document type. When you
add custom document types, which we discuss below, they are added to the filter
list. Next, let's look at how to navigate around Documents and Media.

## Navigating the Documents and Media Portlet [](id=navigating-the-documents-and-media-portlet)

In the main window of the Documents and Media portlet, you can click on a
document to view details about it. Its version number, version history, status,
as well as who uploaded it and who last edited it. Depending on the document,
some automatically extracted metadata may also be displayed, such as the
document creator, author, title, content type, creation date, and last
modification date. In the case of audio or video files, the duration would also
be displayed. You can perform several actions on the document here:

**Download:** lets you download the document.

**Get URL:** displays the URL of the document on the server.

**Get WebDAV URL:** displays the WebDAV URL of the document on the server.
Specify this URL as the file name when opening the document from Microsoft
Office. 

**Edit:** lets you change contents of a document, point it to a different file,
change its title, description or document type, or add tags, categories or
related assets.

**Move:** lets you choose a new location in the Documents and Media repository
to store the document.

**Checkout/Checkin:** prevents others from modifying the document while you are
working. Other users can still view the current version of the document if they
have permission. You can check the document back in when you're done working.

**Permissions:** allows you to configure file-specific permissions for the
document.

**Move to the Recycle Bin:** lets you remove the document from the Documents and
Media library.

If comments are enabled, you can also view comments, add comments, or subscribe
to comments about documents. Comments are enabled by default.

![Figure 5.2: You can click an asset in the Documents and Media portlet to view its options and properties.](../../images/05-doclib-document-view.png)

The menu at the top of the Documents and Media portlet contains Actions, Add,
Sort By and Manage buttons. There are also buttons for switching between icon
view, descriptive view, and list view. If there are lots of documents in the
Documents and Media library, the search field can help you find the documents
you're looking for. If your portlet contains more documents than it can display
at once, you can use the navigation tool at the bottom of the portlet window to
either switch your view to another page or configure the page to display more
documents per page. 

When navigating among Documents and Media folders, look for the *Subscribe* link
at the top right corner of the folder view. Clicking the *Subscribe* link
subscribes you to the folder you're currently viewing so that you'll receive
email notifications. You'll be notified about any actions that take place in the
folder such as file additions, edits, or deletions.

### Actions [](id=actions)

The Actions menu will only be displayed if you have selected one or more
documents with the check boxes.

**Cancel Checkout:** lets you check in a document that you had checked out but
did not make any changes to. Using this option will prevent the Documents and
Media portlet from incrementing the document's version number and saving an
identical version of the document.

**Checkin:** lets you check in a document that you have edited. Its version
number will increment and the previous version will be saved.

**Checkout:** lets you check out a document that you would like to edit. This
option prevents anyone else from modifying it while you are working.

**Move:** allows you to choose a new location for a document or folder within
the portlet's file system. You can move multiple documents and folders at the
same time. Moving documents and folders is also possible via drag & drop.

**Move to the Recycle Bin:** allows you to remove a document or folder from the
portlet. You can move multiple documents and folders to the Recycle Bin at the
same time.

### Add [](id=add)

From the Add button, you can add documents, folders, and shortcuts just like on
your local file system.

**Folder:** lets you create a new location in your portlet's file system.

**Shortcut:** allows you to create a shortcut to any document that you have read
access for. You can set permissions on the shortcut to specify who can access
the original document through the shortcut. 

**Repository:** is a new feature of Liferay 6.1. This option allows you to add
an entirely new repository to your Documents and Media portlet. To do this you
need to specify the repository type and choose an ID. If you are using the
AtomPub protocol you'll also have to specify the AtomPub URL.

**Multiple Documents:** allows you to upload several documents at once.

**Basic Document:** allows you upload a single file that you would like the
default document type, "Basic Document," to apply to. By default, basic
documents are not described by any metadata sets.

The remaining items in the Add menu are default document types that are each
described by a unique metadata set. When you add a document belonging to a
specific document type, you're presented with a form to not only specify the
file to upload but also to fill out the fields defined by the document type's
metadata set. We describe the "Contract" document type by way of example.

**Contract:** lets you upload a file that you would like the "Contract" document
type to apply to. This document type is intended to be used to describe legal
contracts. By default, contracts are described by effective date, expiration
date, contract type, status, legal reviewer, signing authority and deal name
fields. Document types are discussed below. 

Any custom documents types that have been defined also appear in the Add menu.
If a document type has been created that matches the document you would like to
upload, you can select that document type from the Add menu. This will associate
the metadata fields associated with the document type to your document and you
will be asked to fill out the fields.

Another useful feature for Liferay's Documents and Media portlet is the
drag-and-drop upload feature. Instead of manually uploading an asset via the
portlet's menu, you can select and drag an asset from your desktop and drop it
onto your portlet. You can also drag-and-drop folders full of assets. When you
drag the asset onto your browser, the Documents and Media portlet is highlighted
blue indicating its compatibility for the drag-and-drop feature. When the asset
is finally dropped on the highlighted region, the asset is automatically
uploaded and available instantly.

Let's move on and discuss the Sort action.

### Sort [](id=sort)

You can sort the items displayed in the main window of the Documents and Media
portlet using the *Sort By* menu. You can sort by title, create date, modified
date, downloads or size.

**Title:** lets you alphabetically sort documents by title.

**Create Date:** lets you sort documents by the time they were created.

**Modified Date:** lets you sort documents by the last time they were modified.

**Downloads:** lets you sort documents by the number of times they were
downloaded.

**Size:** lets you sort documents by how much disk space they use.

Let's dive into the Manage action next!

### Manage [](id=manage)

The Manage menu allows you to view the names of document types and metadata
sets, as well as the last times they were edited.

**Document Types:** shows you a list of defined document types.

**Metadata Sets:** shows you a list of defined metadata sets as well as their
portal IDs.

Now that we know the basic functionality of the Documents and Media portlet,
let's explore document types and metadata sets.
