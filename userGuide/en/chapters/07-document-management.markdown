## Documents and Media [](id=documents-and-media)

Liferay's Documents and Media portlet provides a mechanism for storing files online using the same type of structure that you use to store files locally. This portlet grant users the ability to share documents within the portal via uploading and downloading. The Documents and Media library can be used to store files of any kind, including ones that you might not consider to be "documents" or "media". The Documents and Media portlet is non-instanceable. This means that each page on your portal can host at most one such portlet. Furthermore, if you add multiple Documents and Media portlets to pages in the same site, these portlets will share the same data sets since they are scoped by site by default. However, you can add multiple Documents and Media *Display* portlets to a page. Then you can choose content from actual Documents and Media portlets to display. Remember that users, by default, have their own personal sites with public and private pages. They can use their personal sites to host document Documents and Media portlets for storing or sharing files.

### Getting Started with the Documents and Media Portlet [](id=lp-6-1-ugen04-getting-started-with-the-documents-and-media-portlet-0)

Liferay's Documents and Media portlet has been redesigned and renamed for Liferay 6.1. The previous version was called the Document Library portlet. The new Documents and Media portlet supports customizable document types and metadata sets. These document types and metadata sets are an addition to, not a replacement for, the portal's system of tags and categories. Additionally, Liferay 6.1 allows multiple repositories to be mounted onto the Documents and Media portlet. 

![Figure 4.32: Initial View of the Documents and Media Portlet](../../images/05-document-library.png)

The default view of the Documents and Media portlet displays the contents of the Home folder. The links on the left side of the portlet windows are filters. You can use these filters to choose what you want the main window of the portlet to display. Recent Documents displays documents that users have recently used. For example, recently uploaded, edited, or downloaded documents are displayed here. My Documents shows *your* documents; in other words, the documents that you have uploaded. Basic Document and the document types listed below it are also filters. If you click on one of these filters, the main portlet window shows only documents that belong to the selected document type. When you add custom Document types, which we discuss below, they are added to the filter list.

Let's look at how you'd navigate around Documents and Media. 

### Navigating the Documents and Media Portlet [](id=lp-6-1-ugen04-navigating-the-documents-and-media-portlet-0)

In the main window of the Documents and Media portlet, you can click on a document to view details about it. Its version number, version history, status, and content type will be displayed as well as its uploader and the user who last edited it. You can perform several actions on the document here. You can edit the document to change its contents, point it to a different file, change its title, description, or document type, or add tags, categories, or related assets. You can move the document to a new location. You can checkout the document to prevent other users from modifying the document while you are working. You can configure the file-specific permissions of the document. You can download the document or get its URL or Webdav URL. If comments are enabled, you can view comments, add comments, or subscribe to comments about this document.

![Figure 4.33: Viewing a Document](../../images/05-doclib-document-view.png)

The menu at the top of the Documents and Media portlet contains Actions, Add, Sort By, and Manage buttons. There are also buttons for switching between icon view, descriptive view, and list view and there is a search field. If your portlet contains more documents than it can display at once, you can use the navigation tool at the bottom of the portlet window to either switch your view to another page or configure the page to display more documents per page. 

#### Actions [](id=lp-6-1-ugen04-actions-0)

The Actions menu will only be displayed if you have selected one or more documents with the check boxes.

**Cancel Checkout:** lets you check in a document that you had checked out but did not make any changes to. Using this option will prevent the Documents and Media portlet from incrementing the document's version number and saving an identical version of the document.

**Checkin:** lets you check in a document that you have edited. Its version number will increment and the previous version will be saved.

**Checkout:** lets you check out a document that you would like to edit. This option prevents anyone else from modifying it while you are working. Other users can still view the current version of the document if they have permission. When you are done working on the document you can check it back in.

**Move:** allows you to choose a new location for a document or folder within the portlet's file system. You can move multiple documents and folders at the same time. Moving documents and folders is also possible via drag & drop.

**Delete:** allows you to remove a document or folder from the portlet. You can delete multiple documents and folders at the same time.

#### Add [](id=lp-6-1-ugen04-add-0)

From the Add button, you can add documents, folders, and shortcuts just like on your local file system.

**Folder:** lets you create a new location in your portlet's file system.

**Shortcut:** allows you to create a shortcut to any document that you have read access for. You can set permissions on the shortcut to specify who can access the original document through the shortcut. 

**Repository:** is a new feature of Liferay 6.1. This option allows you to add an entirely new repository to your Documents and Media portlet. To do this you need to specify the repository type and choose an ID. If you are using the AtomPub protocol you'll also have to specify the AtomPub URL.

**Multiple Documents:** allows you to upload several documents at once.

**Basic Document:** allows you upload a single file that you would like the default document type, "Basic Document," to apply to. By default, basic documents are not described by any metadata sets.

The remaining items in the Add menu are default document types that are each described by a unique metadata set. When you add a document belonging to a specific document type, you're presented with a form to not only specify the file to upload but also to fill out the fields defined by the document type's metadata set. We describe the "Contract" document type by way of example.

**Contract:** lets you upload a file that you would like the "Contract" document type to apply to. This document type is intended to be used to describe legal contracts. By default, contracts are described by effective date, expiration date, contract type, status, legal reviewer, signing authority, and deal name fields. Document types are discussed below. 

Any custom documents types that have been defined also appear in the Add menu. If a document type has been created that matches the document you would like to upload, you can select that document type from the Add menu. This will associate the metadata fields associated with the document type to your document and you will be asked to fill out the fields.

#### Sort [](id=lp-6-1-ugen04-sort-0)

You can sort the items displayed in the main window of the Documents and Media portlet using the Sort By menu. You can sort by title, create date, modified date, read count, or size.

**Title:** lets you alphabetically sort documents by title.

**Create Date:** lets you sort documents by the time they were created.

**Modified Date:** lets you sort documents by the last time they were modified.

**Read Count:** lets you sort documents by the number of times they were viewed.

**Size:** lets you sort documents by how much disk space they use.

#### Manage [](id=lp-6-1-ugen04-manage-0)

The Manage menu allows you to view the names of document types and metadata sets, as well as the last times they were edited.

**Document Types:** shows you a list of defined document types.

**Metadata Sets:** shows you a list of defined metadata sets as well as their portal IDs.

### Repositories [](id=lp-6-1-ugen04-repositories-0)

Adding repositories in Documents and Media is a new feature in Liferay 6.1. Documents and Media allows to connect to multiple third-party repositories that support CMIS 1.0 with AtomPUB and Web Services protocols. You can add new repositories from the UI by clicking the *Add* button from the Home folder. Repositories can only be mounted in the Home folder.

![Figure 4.34: Adding a new repository](../../images/05-new-repository.png)

The admin must ensure the same credentials and authentication are being used in Liferay and in the external repository. This is normally synchronized using a mechanism like LDAP. If you don't have LDAP you need to ensure manually that the credentials and authentication are the same. In order to authenticate with the third-party repository you need to enable the following property:

	session.store.password=false

The repository is displayed in the left side of the window in the Home folder.

![Figure 4.35: Viewing a repository](../../images/05-repository.jpg)

### Document Types and Metadata Sets [](id=lp-6-1-ugen04-document-types-and-metadata-sets-0)

Customizable document types and metadata sets are new features in Liferay 6.1. When a user assigns a document type to a document, the user is required to fill out the fields defined by the metadata set of the document type. This helps encourage users not to forget to enter important information about their documents. For example, you could create a "copyrighted" document type and require users to enter a license for all "copyrighted" documents. More importantly, document types and metadata sets can improve document searchability. The values that users enter into the fields determined by their document type's metadata set become searchable entities within the portal. You can use Liferay's search portlet to search for these terms. Document types and metadata sets are accessible from the Manage Button at the top of the Documents and Media portlet window.

![Figure 4.36: Document Types Dialog Box](../../images/05-document-types.png)

You can add a new document type using the Add button at the top of the dialog box. To do so, you need to define one or more metadata sets to associate with your document type. When creating a new document type, you can define "Main Metadata Fields" or select "Additional Metadata Fields". Main metadata fields are directly tied to their document type and cannot be made available to other document types. Additional metadata fields, by contrast, can be defined independently and can be used in many different document types. You can differentiate the document types that implement the same additional metadata set by defining different main metadata fields for them. However, Additional metadata fields need to be defined and saved before creating a document type that will implement them. 

![Figure 4.37: Adding a New Document Type](../../images/05-new-document-type.png)

Let's create a document type called "Syllabus" and define a metadata set. What metadata should we associate with syllabi? Let's choose for our syllabi to have course title, professor, semester, course description, and course requirements fields. All syllabi in our portal should maintain entries for these fields. This ensures that a syllabus will show up in a portal search if its course title, professor, or semester is searched for. Since we don't want to use our metadata set for any document type other than "Syllabus," let's create our metadata set under the Main Metadata Fields area. Alternatively, we could create our metadata set independently using *Manage &rarr; Metadata Sets &rarr; Add* and then select it as an Additional Metadata Field. 

![Figure 4.38: Selecting Additional Metadata Sets](../../images/05-selecting-additional-metadata-sets.png)

You can view, edit, or add metadata sets from the *Manage &rarr; Metadata* window. A metadata set consists of a group of fields. If you click the Add button, can use same UI for defining a metadata set that you used in the Add Document Type window.

![Figure 4.39: Adding a New Metadata Set](../../images/05-new-metadata-set.png)

Make sure the Fields tab is selected on the left. Then, to define a metadata set, just choose fields to use from the area on the left and drag and drop them into the area on the right. The drag and drop interface allows for nested fields so you need to be careful about where you drop the fields. Default values, mouse-over tips, widths, and other settings can be configured for most fields. To configure these settings, just double-click on a field from the area on the right. This automatically selects the Settings tab on the left. Then double-click on a value to edit. Liferay supports the following kinds of fields for metadata sets:

**Boolean:** is just a checkbox.

**Date:** lets you enter a date. A valid date format is required for the date field. However, you don't have to enter a date manually. When you select the date field a mini-calendar pops up which you can use to select a date.

**Decimal:** lets you enter a decimal number. The value will be persisted as a double.

**Documents and Media:** lets you select a file from one of the portal's Documents and Media libraries.

**File Upload:** lets you select file to upload from your local system.

**Integer:** lets you enter an integer. The value will be persisted as an int.

**Number:** lets you enter a decimal number or an integer. The value will be persisted either as a double or an int, depending on the type of input.

**Radio:** displays several clickable options. The default number is three but this is customizable. Only one option can be selected at a time.

**Select:** is just like the radio field except that the options are hidden and have to be accessed from a drop-down menu.

**Text:** lets you enter a single line of text.

**Text Box:** is just like the text field except you can enter multiple lines of text or separate paragraphs.

Remember that metadata sets created independently are reusable. Once they have been created they can be included in any number of document types as additional metadata sets. Next, let's take a look at tags. Tags can be attached to most forms of web content that can be created in Liferay, including documents.

### Automatic Preview Generation [](id=lp-6-1-ugen04-automatic-preview-generation-0)

Liferay 6.1 generates previews of the documents added to Documents and Media whenever it's possible. The content is displayed using a customized viewer depending on the type of the content.

**Office documents:** preview of supported office documents (.docx, .pdf, .odt, .ppt, etc.) and online reading.

**Multimedia:** play various multimedia (video and audio) formats from the browser.

Liferay uses external tools and libraries to support this feature. You may need to install them and enable them in Liferay.

**OpenOffice/LibreOffice:** for office documents start [*OpenOffice*](http://www.openoffice.org) or [*LibreOffice*](http://www.libreoffice.org) in server mode by running:

	soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;"

and enable either through External Services in Control Panel or in `portal-ext.properties`:

	openoffice.server.enabled=true

**ImageMagick:** PDF conversion is available out of the box. While a default implementation of image generation for document previews and thumbnails is provided, the quality can be improved using [*ImageMagick*](http://www.imagemagick.org), which also requires [*GhostScript*](http://www.ghostscript.com). This can be enabled in the external services control panel or in `portal-ext.properties`:

	imagemagick.enabled=true

**Xuggler:** Audio and video player configuration requires the installation of [*Xuggler*](http://www.xuggle.com/xuggler/downloads/build.jsp) and the configuration of related environment variables. This can be enabled in the external services control panel or in `portal-ext.properties`:

	xuggler.enabled=true

With the above tools installed and enabled, Documents and Media looks like this:

![Figure 4.40: Previews in Documents and Media](../../images/05-previews.jpg)

You can view a document with a customized viewer that allows you to navigate through the different pages of the document and read its content.

![Figure 4.41: Viewing an office document](../../images/05-document-preview.jpg)

You can view a multimedia document (audio or video) and play it online. If the browser supports HTML5, it uses the native player of the browser. Otherwise it falls back to a Flash player.

![Figure 4.42: Playing a video](../../images/05-video-preview.png)

Document previews are powerful, and help users to more successfully browse the media to find what they're looking for. 

### Automatic extraction of RAW Metadata [](id=lp-6-1-ugen04-automatic-extraction-of-raw-metadata-0)

When adding new documents or viewing existing documents, a process is triggered automatically that extracts the file's metadata. The library used by this process is TIKA and it's already included in Liferay out of the box.

You can see the metadata when viewing the document, in the right side of the window.

### Document type restrictions and workflow per folder [](id=lp-6-1-ugen04-document-type-restrictions-and-workflow-per-folder-0)

You can force users to add only certain document types to a folder. By default, child folders inherit the restrictions of their parent folder. You can change this behavior by editing the folder and selecting the allowed document types.

![Figure 4.43: Restrict Marketing folder to use specific document types](../../images/05-document-type-restriction.png)

If workflow is enabled, you can specify different workflow definitions per folder. Furthermore, you can specify different workflow definition per document type and per folder. You can set this by editing the folder. Then, the UI will look like this:

![Figure 4.44: Restrict Marketing folder to use specific document types and workflow](../../images/05-document-type-workflow-restriction.png)

Document types are a powerful way to enforce rules for documents uploaded by users. Next, we'll see a way to make it incredibly easy for users to access documents stored in Liferay's Documents and Media repositories. 

### WebDAV access [](id=lp-6-1-ugen04-webdav-access-0)

Wouldn't it be great if you could access documents and folders belonging to Liferay's Documents and Media library from your own machine's file manager? You can, thanks to the Documents and Media libray's WebDAV integration. WebDAV stands for Web-based Distributed Authoring and Versioning. It's a set of methods based on HTTP that allows users to create, edit, move, or delete files stored on web servers. WebDAV is supported by most major operating systems and desktop environments, including Linux (both KDE and GNOME), Mac OS, and Windows.

Suppose you've created an *Image Gallery* folder using a Documents and Media portlet and uploaded some images to it. Portal users with the appropriate permissions can access this folder and the image files it contains using a browser and Liferay's web interface. WebDAV provides an alternative way to do this using a file manager instead of a web browser. To access a folder stored in a Documents and Media portlet on a remote server, you'll need log in credentials for the portal and the WebDAV URL of the folder you'd like to access. 

Next, navigate to the Documents and Media portlet hosting the folder you'd like to access. Mouse over the folder (*Image Gallery* for our example) and select *Access from Desktop*.

![Figure 4.45: Select *Access from Desktop* to get the WebDAV URL of a folder.](../../images/webdav-access-from-desktop.png)

Copy the WebDAV URL. On Windows, right-click on My Computer and select *Map Network Drive*. Select an unused drive, paste the WebDAV URL, and click *Finish*. You're prompted to enter your Liferay credentials and then, provided you have the required permissions, the *Image Gallery* folder appears. You can now add, edit, move, or delete files in this directory. 

On Mac OS X, select *Go* &rarr; *Conect to Server* in Finder. Then enter the WebDAV URL of the folder you'd like to access in the Server Address field, click *Connect*, and you should be prompted for your Liferay credentials. 

On Linux, you must slightly modify the WebDAV URL of your folder in your file manager. For KDE's Dolphin, change the URL's protocol so that it says `webdav://` instead of `http://`. For GNOME's Nautilus, change the URL's protocol so that it says `dav://` instead of `http://`. Then press *Enter* and you're prompted for your Liferay credentials.

Note that Liferay increments the version numbers of files edited and uploaded via WebDAV so you don't have to worry that using your file manager will bypass the functionality of Liferay's web interface. The Documents and Media application is a powerful way to manage any types of files your users need to use. Next, let's look at how you can leverage Liferay Portal's Asset framework.
