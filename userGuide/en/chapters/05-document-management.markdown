# Document Management [](id=document-management-liferay-portal-6-2-user-guide-05-en)

Liferay's Documents and Media library provides a mechanism for storing files
online using the same type of structure that you use to store files locally. You
can use it to store files of any kind; it serves as a virtual shared drive, and
can mount and browse external repositories. Its companion portlet, the Media
Gallery, displays selected content from the Documents and Media library. It can
display image, audio and video files. Other features in the Documents and Media
library include customizable document types and metadata sets, automatic
document preview generation, and support for mounting multiple external
repositories. The new document types and metadata sets are an addition to, not a
replacement for, the portal's system of tags and categories. Let's start
exploring how to use the Documents and Media portlet.

## Getting Started with the Documents and Media Portlet [](id=getting-started-with-the-documents-and--liferay-portal-6-2-user-guide-05-en)

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

### Navigating the Documents and Media Portlet [](id=navigating-the-documents-and-media-port-liferay-portal-6-2-user-guide-05-en)

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

#### Actions [](id=actions-liferay-portal-6-2-user-guide-05-en)

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

#### Add [](id=add-liferay-portal-6-2-user-guide-05-en)

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

#### Sort [](id=sort-liferay-portal-6-2-user-guide-05-en)

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

#### Manage [](id=manage-liferay-portal-6-2-user-guide-05-en)

The Manage menu allows you to view the names of document types and metadata
sets, as well as the last times they were edited.

**Document Types:** shows you a list of defined document types.

**Metadata Sets:** shows you a list of defined metadata sets as well as their
portal IDs.

Now that we know the basic functionality of the Documents and Media portlet,
let's explore document types and metadata sets.

## Document Types and Metadata Sets [](id=document-types-and-metadata-sets-liferay-portal-6-2-user-guide-05-en)

When a user assigns a document type to a document, the user is required to fill
out the fields defined by the metadata set of the document type. This encourages
users not to forget to enter important information about their documents. For
example, you could create a *copyrighted* document type and require users to
enter a license for all "copyrighted" documents. More importantly, document
types and metadata sets can make it easier to find documents. The values that
users enter into the fields determined by their document type's metadata set
become searchable entities within the portal. You can use Liferay's search
portlet to search for these terms. Document types and metadata sets are
accessible from the *Manage* button at the top of the Documents and Media
portlet window.

![Figure 5.3: Navigate to the Document Types Dialog Box by clicking *Manage* &rarr; *Document Types*.](../../images/05-document-types.png)

You can add a new document type using the Add button at the top of the dialog
box. To do so, you need to define one or more metadata sets to associate with
your document type. When creating a new document type, you can define *Main
Metadata Fields* or select *Additional Metadata Fields*. Main metadata fields
are directly tied to their document type and cannot be made available to other
document types. Additional metadata fields, by contrast, can be defined
independently and can be used in many different document types. You can
differentiate the document types that implement the same additional metadata set
by defining different main metadata fields for them. However, additional
metadata fields need to be defined and saved before creating a document type
that will implement them. 

As an example, we could create a document type called *Syllabus* and define a
metadata set. What metadata should we associate with syllabi? Let's choose for
our syllabi to have course title, professor, semester, course description, and
course requirements fields. All syllabi in our portal should maintain entries
for these fields. This ensures that a syllabus shows up in a portal search
if its course title, professor or semester is searched for. Since we don't want
to use our metadata set for any document type other than *Syllabus*, let's
create our metadata set under the Main Metadata Fields area. Alternatively, we
could create our metadata set independently using *Manage &rarr; Metadata Sets
&rarr; Add* and then select it as an Additional Metadata Field. 

You can view, edit, or add metadata sets from the *Manage &rarr; Metadata*
window. A metadata set consists of a group of fields. If you click the *Add*
button, you can use same UI for defining a metadata set that you used in the Add
Document Type window.

![Figure 5.4: Adding a New Metadata Set is easier than ever with Liferay's advanced editor.](../../images/05-new-metadata-set.png)

<!-- Needs to be replaced for 6.2 -->
<!-- Editor had icons missing during image updating cycle -->

Make sure the Fields tab is selected on the left. Then, to define a metadata
set, just choose fields to use from the area on the left and drag and drop them
into the area on the right. The drag and drop interface allows for nested fields
so you need to be careful about where you drop the fields. Default values,
mouse-over tips, widths, and other settings can be configured for most fields.
To configure these settings, just click on a field from the area on the right.
This automatically selects the Settings tab on the left. Alternatively, you can
access the Settings tab by clicking the fields *Settings* button (wrench). Then
double-click on a value to edit. Liferay supports the following kinds of fields
for metadata sets:

**Boolean:** is a checkbox.

**Date:** lets you enter a date. A valid date format is required for the date
field, but you don't have to enter a date manually. When you select the date
field a mini-calendar pops up which you can use to select a date.

**Decimal:** lets you enter a decimal number. The value will be persisted as a
double.

**Documents and Media:** lets you select a file from one of the portal's
Documents and Media libraries.

**HTML:** an area that uses a WYSIWYG editor to enhance the content.

**Integer:** lets you enter an integer. The value will be persisted as an int.

**Link to Page:** lets you link to another page in the same site.

**Number:** lets you enter a decimal number or an integer. The value will be
persisted either as a double or an int, depending on the type of input.

**Radio:** displays several clickable options. The default number is three but
this is customizable. Only one option can be selected at a time.

**Select:** is just like the radio field except that the options are hidden and
have to be accessed from a drop-down menu.

**Text:** lets you enter a single line of text.

**Text Box:** is just like the text field except you can enter multiple lines of
text or separate paragraphs.

Remember that metadata sets created independently are reusable. Once they have
been created they can be included in any number of document types as additional
metadata sets. Next, let's take a look at tags. Tags can be attached to most
forms of web content that can be created in Liferay, including documents.

## Alternative File Repository Options [](id=alternative-file-repository-options-liferay-portal-6-2-user-guide-05-en)

By default, Liferay stores documents and media files on the file system of the
server on which it's running. You can choose a specific location for the
document library store's root directory by adding the following property to your
`portal-ext.properties` file and replacing the default path with your custom
path:

    dl.store.file.system.root.dir=${liferay.home}/data/document_library

You can also use an entirely different method for storing documents and media
files. You can use any of the following documents and media library stores with
Liferay:

- Advanced File System Store
- CMIS Store (Content Management Interoperability Services)
- DBStore (Database Storage)
- File System Store
- JCRStore (Java Content Repository)
- S3Store (Amazon Simple Storage)

For example, you can store documents and media files in your Liferay instance's
database using DBStore. To enable DStore, add the following line to your
`portal-ext.properties` file:

    dl.store.impl=com.liferay.portlet.documentlibrary.store.DBStore

Remember to restart your Liferay server after updating your
`portal-ext.properties` file in order for your customizations to take effect.
Please refer to the Document Library Portlet section of your `portal.properties`
file to find a complete list of supported customizations. You can customize
features such as the maximum allowed size of documents and media files, the list
of allowed file extensions, which types of files should be indexed, etc.

## Using External Repositories [](id=using-external-repositories-liferay-portal-6-2-user-guide-05-en)

Content Management Interoperability Services (CMIS) is a specification for
improving interoperability between Enterprise Content Management systems.
Documents and Media allows users to connect to multiple third-party repositories
that support CMIS 1.0 with AtomPub and Web Services protocols.

Some of the features supported with third-party repositories include:

- Reading/writing documents and folders
- Document check-in, check-out, and undo check-out
- Downloading documents
- Moving folders and documents within the repository
- Getting revision history
- Reverting to revision

There are some subtle differences in setting up the different kinds of
third-party repositories for use in Documents and Media. But there are plenty of
similarities too.

Common Liferay configuration steps:

- Adjust the portal properties.
- Add any user accounts required by the repository.
- Add the repository.

Lastly, keep in mind your third-party repository may require installation and
deployment of an appropriate Liferay plugin. Plugins for SharePoint and
Documentum are available through Liferay's Marketplace.

Let's go through those steps, starting with setting our portal properties.

##### Adjusting portal properties [](id=adjusting-portal-properties-liferay-portal-6-2-user-guide-05-en)

The admin must ensure that the same credentials and authentication are being
used in Liferay and in the external repository. This is normally synchronized
using a mechanism like LDAP. If you don't have LDAP, you need to ensure manually
that the credentials and authentication are the same. In order to authenticate
with the third-party repository, you need to store passwords for the user
sessions. Set the following portal property in your `portal-ext.properties`:

    session.store.password=true

Next, we need to make sure the login and password for Liferay are the same as 
the external repository. This is easily accomplished by using identical screen 
names, so in `portal-ext.properties` add the following: 

    company.security.auth.type=screenName

Alternatively, configure these properties in the Control Panel under *Portal 
Settings* &rarr; *Authentication*.

##### Adding required repository users [](id=adding-required-repository-users-liferay-portal-6-2-user-guide-05-en)

Once these properties are set, you must create a user in Liferay with a
screen name and password matching the administrative user of your external
repository. Be sure to assign appropriate roles (e.g. Administrator) to that
user. Sign out of Liferay and sign in again as that new user. See sections of
the *Management* chapter on adding and managing users.

##### Adding the repository [](id=adding-the-repository-liferay-portal-6-2-user-guide-05-en)

You can add new repositories from the UI by clicking the *Add* button from the
Home folder. Repositories can only be mounted in the Home folder.

![Figure 5.5: You can add a new repository by navigating to *Add* &rarr; *Repository* in the Documents and Media portlet.](../../images/05-new-repository.png)

All fields in this form are required, except for *Repository ID*. Leave this
field blank, and a repository ID is automatically generated by the system. When
finished, the repository is displayed on the left side of the window in the Home
folder.

Using this information, we can now add an example repository. As noted
previously, there are several repositories that work well with Liferay using
CMIS. One that is familiar to many users is SharePoint. In the exercise below,
we'll set up SharePoint as a Documents and Media repository.

### Example Repository Setup: SharePoint [](id=example-repository-setup-sharepoint-liferay-portal-6-2-user-guide-05-en)

<!-- Sharepoint currently does not work on Liferay. We're monitoring the
Sharepoint status and will update when it's working. Thus, Sharepoint repository
sections are not updated for 6.2. -->

With Liferay Portal you can connect to an external SharePoint server and add it
as a Documents and Media repository. This lets users collaborate and
share documents more easily between both environments. We will mount a
SharePoint repository via CMIS AtomPub and SharePoint SOAP web services.

Liferay uses a combination of SOAP and Representational State Transfer (REST),
based on the Atom convention, to connect to the SharePoint repository.
SharePoint provides various SOAP services for modifying and querying data from
its document library. Liferay uses Axis2 to generate SOAP calls to the
SharePoint server.

To use SharePoint as a Liferay Documents and Media repository, we'll do the
following:

- Configure the CMIS Connector on SharePoint.
- Activate a SharePoint site as a CMIS Producer.
- Acquire your SharePoint document library's repository ID.
- Enable Basic Authentication on the SharePoint host.
- Add SharePoint as a Liferay Documents and Media repository.

Note that this section is geared towards portal system administrators and
SharePoint system administrators.

Before you can use SharePoint as an external repository with Liferay portal, you
must verify that SharePoint is properly configured. Several services must be
set up on the SharePoint server before synchronizing with Liferay.

#### Configuring the CMIS Connector on SharePoint [](id=configuring-the-cmis-connector-on-share-liferay-portal-6-2-user-guide-05-en)

SharePoint utilizes a CMIS Connector and a CMIS Producer to interface with
Liferay Portal. The Connector is installed with the SharePoint Administrator
Toolkit using a solution package called a Windows SharePoint file (.wsp). If you
don't have it already, install the SharePoint Administrator Toolkit for its CMIS
Connector. Install and deploy the CMIS Connector as a Farm Solution on
SharePoint.

The folder `Content Management Interoperability Services (CMIS) Connectors`
contains the `spscmis.wsp` file. Choose the appropriate deployment settings and
deploy that file. When deployment completes, Solution Properties shows the
solution is successfully deployed to all target sites. Now it's time to
configure the CMIS Producer.

#### Activating a SharePoint site as a CMIS Producer [](id=activating-a-sharepoint-site-as-a-cmis--liferay-portal-6-2-user-guide-05-en)

The Producer makes SharePoint repositories available through the CMIS Connector.
Choose the SharePoint site containing the document libraries to be used as
document repositories. Every document library in this site is made available as
a repository through the CMIS connector.

Go to *Site Actions* &rarr; *Site Settings* &rarr; *Manage Site Features*.
Enable the *Content Management Interoperability Services (CMIS) Producer* by
clicking *Activate*.

Now any document library created under this site is CMIS enabled. Before we
leave our SharePoint console, let's take note of our SharePoint document
library's repository ID.

#### Acquiring the SharePoint document library's repository ID [](id=acquiring-the-sharepoint-document-libra-liferay-portal-6-2-user-guide-05-en)

Acquiring your SharePoint document library's repository ID, or list ID, is
important as it must be specified in the AtomPub URL Liferay uses to connect
with the external repository. Finding it, however, can be a little confusing.
The easiest way to find the repository ID is by accessing the SharePoint
repository using a browser such as Mozilla Firefox.

Follow these steps to get the repository ID:

1. In SharePoint, open the desired library. 

2. Under Library Tools select *Library*.

3. Click on *Library Settings*, located to the far right.

4. The browser window refreshes displaying the repository ID between curly
braces '{' and '}' in the browser's address bar.

    ![Figure 5.6: The repository ID can be found by displaying the repository's URL in a Firefox browser.](../../images/05-sharepoint-list-id.png)

    The repository ID is highlighted in the figure above. For this 
    example, the repository ID is `6DFDA9-B547-4D1D-BF85-976863CDF533`.
    Therefore, the AtomPub URL you'd use when adding this repository in
    Documents and Media would resemble this: 

        http://liferay-20jf4ic/CMIS/_vti_bin/cmis/rest/6DFDA9-B547-4D1D-BF85-976863CDF533?getRepositoryInfo.

Be sure to copy down this URL so you can use it to configure SharePoint as a
repository in Documents and Media. Next, let's enable Basic Authentication on
the SharePoint host.

#### Enabling Basic Authentication on the SharePoint host [](id=enabling-basic-authentication-on-the-sh-liferay-portal-6-2-user-guide-05-en)

For the CMIS connector and producer to work, Basic Authentication on IIS must be
enabled. This lets Liferay's SharePoint hook authenticate against the SharePoint
web services. Enable Basic Authentication on your SharePoint host.

You are now prepared to mount SharePoint as an external repository.

#### Adding SharePoint as a Liferay Documents and Media repository [](id=adding-sharepoint-as-a-liferay-document-liferay-portal-6-2-user-guide-05-en)

With the SharePoint server configured, we now turn our attention to Liferay. As
mentioned in the common steps for adding an external repository, be sure to
adjust the portal properties and add any user accounts required by the
repository.

Here are the steps specific to configuring Liferay to use SharePoint:

1. Download and install the SharePoint hook from Marketplace. See the
*Downloading and Installing Apps* section of the *Leveraging the Liferay
Marketplace* chapter of this document for more information.

2. Add the Documents and Media portlet to a page, if you haven't done so
already.

3. In the Documents and Media portlet click *Add Repository* and enter the
following information:

    **Name:** Enter an arbitrary name for the repository.

    **Description:** Describe the repository.

    **Repository Type:** Select *SharePoint (AtomPub)*.

    **AtomPub URL:** Enter the applicable URL using the format below,
    substituting the SharePoint server's host name for *[host]* and the
    SharePoint document library's repository ID for *[repository ID]*:

        http://[host]/CMIS/_vti_bin/cmis/rest/[repository ID]?getRepositoryInfo

    **Repository ID:** Leave this field empty. Liferay searches for the first
    repository using the given parameters and sets this value to that
    repository's ID.

    **Site Path:** Enter data using the format below, the SharePoint server's
    host information for *[host]* and the SharePoint document library's
    repository name for *[repository path]*:

		http://[host]/[repository path]

4. Click *Save*.

The left navigation panel of your Documents and Media portlet now lists your
new repository.

---

![Tip](../../images/tip.png) **Tip:** In the site path example below, notice how the
repository path has a folder `Shared Documents` consisting of two words.

		http://liferay-20jf4ic/CMIS/Shared Documents/Forms/AllItems.aspx

The space between the words in the repository name must be accounted for when
setting the site path in Liferay. Replace the empty space with the string *%20*
so the site path value now looks like this:

		http://liferay-20jf4ic/CMIS/Shared%20Documents/Forms/AllItems.aspx

This should be done for any multi-word repository name.

---

Remember that connecting to an external SharePoint server and adding it as a
Documents and Media repository is a great way to give users flexibility for
sharing and collaborating on Microsoft Office documents.

To further enhance your use of Microsoft Office documents with Documents and
Media, Liferay provides integration directly with Microsoft Office. Let's look
at that next.

## Microsoft Office integration [](id=microsoft-office-integration-liferay-portal-6-2-user-guide-05-en)

Liferay lets you open Microsoft Office files that reside in
Documents and Media. You can open the files from Microsoft Internet
Explorer or open them directly from your Microsoft Office applications.
Let's open a file from your browser first.

Liferay lets you launch Microsoft Office right from folders in Documents and
Media. First, find the Microsoft Office file that you want to edit. Then
click the drop-down icon in the upper-left corner of the document's thumbnail
and click the *Open in MS Office* link. Liferay launches Microsoft Office 
and prompts you to log in using your Portal credentials. Once logged in, the
file opens in your Microsoft Office application. 

![Figure 5.7: Open Microsoft Office files from within your Internet Explorer browser by clicking the *Open in MS Office* link.](../../images/05-open-in-MS-office.png)

<!-- Needs to be replaced for 6.2 -->
<!-- This feature only works for 32 bit browsers/Microsoft Office. -->

When you save the file, it is stored automatically in the Documents and Media
folder from which you opened it. 

Note that 64-bit Microsoft Internet Explorer and 64-bit Microsoft Office
versions do not currently support this feature. It is only supported on 32-bit
Microsoft Internet Explorer versions with 32-bit versions of Microsoft Office.
See
[http://issues.liferay.com/browse/LPS-28718](http://issues.liferay.com/browse/LPS-28718)
for more information. 

Next we'll show you how to navigate your Documents and Media folders and
open a file from the Microsoft Office application. 

Liferay lets you access Microsoft Office files in Documents and Media directly
from your Microsoft Office applications. Liferay implements the MS-DWSS
SharePoint protocol to allow saving and retrieving documents from Liferay Portal
as if it were a SharePoint server. You can conveniently update your Microsoft
Office (Office) files without having to exit your Office program.

For example, if you are working in Microsoft Word locally on your
machine, you can open a file from Documents and Media to view or edit it.
Simply select *File*&rarr; *Open* in Word and enter
`http://localhost:8080/sharepoint/` in the file name field. Click *Open* and log
in using your Portal credentials.

![Figure 5.8: Enter the URL of your `sharepoint` location on Liferay to access Documents and Media.](../../images/office-path.png)

In the list of folders displayed, navigate to *guest* &rarr; *document_library*
and select *All Files* to see your Documents and Media files.
Open the desired Word file to make changes. Click *Save* when you are finished
and close the file. 

Now anyone with appropriate permission can see the latest version of the file
with these updates. Liferay takes care of version control as well as file
check out and check in. Users can add comments, ratings, and tags.

Liferay's integration with Microsoft Office lets users leverage Documents and
Media in managing their Office files. Collaboration is simplified as users share
their most up-to-date versions of Office files.

Now let's look at configuring the Documents and Media portlet.

## Configuring the Documents and Media portlet [](id=configuring-the-documents-and-media-por-liferay-portal-6-2-user-guide-05-en)

To configure the Documents and Media portlet, click on the gear icon at the top
of the portlet window and select *Configuration*. The portlet-specific
customizations appear on the Setup tab. To change your Documents and Media
portlet's top-level folder, click *Select* below *Root Folder*, browse or create
the folder you'd like to be your new top-level folder, and click *Save*. The
root folder is the highest-level folder that's accessible from the Documents and
Media portlet. For example, suppose you created a folder called *My Documents*
in the Documents and Media portlet's default Home folder. If you set the My
Documents folder to be your portlet's new root folder, the original Home folder
would no longer be accessible.

![Figure 5.9: To make portlet-specific configurations for Documents and Media, click on the gear icon at the top of the portlet window and select *Configuration*.](../../images/docs-and-media-portlet-config.png)

By default, the Documents and Media portlet contains a search bar to help users
quickly find relevant files. If you'd like the search bar not to appear, uncheck
the *Show Search* box. The *Maximum Entries to Display* dropdown menu lets you
set a limit on how many folders and files can be displayed in the portlet window
at once. By default, the Documents and Media portlet contains three display
style views: Icon, List, and Descriptive. Icons for each appear in the portlet
window, allowing users to select the display style with which they're most
comfortable. Under the Display Style Views heading, you can select which display
styles users are able to choose and you can arrange the order of the selected
display styles. The topmost display style in the list becomes the portlet's
default display style.

Related assets are enabled by default for Documents and Media files. Related
assets allow users to link assets together even if the assets don't share any
tags or categories. To disable related assets for files in your Documents and
Media portlet, uncheck the *Enable Related Assets* box. For more information on
related assets, see the section on defining content relationships in chapter 6.

Under the Show Columns heading, you can customize which columns appear when your
Documents and Media portlet uses the list display style. By default, file names,
sizes, statuses, downloads, and actions are displayed. You can also configure
the portlet to display files' create dates and modified dates. To add or remove
columns from being displayed, move them to the Current box or to the Available
box. You can arrange the columns in the Current box to control the order in
which the columns appear in the portlet: the topmost column in the box appears
as the leftmost column in the portlet.

Ratings and comment ratings are also enabled by default for Documents and Media
files. If users decide that a file or certain comment about a file is useful or
informative, they can rate it as good by clicking on the thumbs up icon next to
the rating. If they think the file or comment is unhelpful or misleading, they
can click on the thumbs down icon. If you'd like to disable ratings or comment
ratings for files, uncheck the *Enable Ratings* or *Enable Comment Ratings*
box, respectively.

## Automatic Previews and metadata [](id=automatic-previews-and-metadata-liferay-portal-6-2-user-guide-05-en)

Whenever possible, Liferay 6.2 generates previews of documents added to the
Documents and Media library. Out of the box, Liferay only ships with Java-based
APIs to generate previews for documents. The only tool available that is 100%
Java and has a compatible license to be distributed with Liferay is PDFBox. If
you upload a PDF file to the Documents and Media portlet, Liferay generates a
preview for the PDF in a separate thread. This process may last only a few
seconds for a small file. The larger the file, the longer it takes.

The first time you run a conversion like this, look for the following console
message: 

    Liferay is not configured to use ImageMagick for generating Document Library
    previews and will default to PDFBox. For better quality previews, install
    ImageMagick and enable it in portal-ext.properties.
    
<!-- Did not receive this message when clicking on a PDF file for 6.2. Was able
to produce message in 6.1. -->

While a default implementation of image generation for document previews and
thumbnails is provided via PDFBox, you'll need to install and configure some
additional tools to harness the full power of Liferay's Documents and Media
library. These tools include [*OpenOffice*](http://www.openoffice.org) or
[*LibreOffice*](http://www.libreoffice.org),
[*ImageMagick*](http://www.imagemagick.org), which requires
[*Ghostscript*](http://www.ghostscript.com), and
[*Xuggler*](http://www.xuggle.com/xuggler). With these tools installed and
configured, Documents and Media content is displayed using a customized viewer
depending on the type of content. Configuring Liferay to use OpenOffice or
LibreOffice in server mode allows you to generate thumbnails and previews for
supported file types (.pdf, .docx, .odt, .ppt, .odp, etc.), lets you view
documents in your browser, and lets you convert documents. ImageMagick allows
for faster and higher-quality previews and conversions. Xuggler allows for audio
and video previews, lets you play audio and video files in your browser, and
extracts thumbnails from video files. Please see the *External Services* section
of chapter 17 for how to configure Liferay to use these tools.

With the above tools installed and enabled, the Documents and Media library
looks like this:

![Figure 5.10: After configuring additional tools, previews in the Documents and Media portlet are more advanced.](../../images/05-doc-previews.png)

You can view a document with a customized viewer that allows you to navigate
through the different pages of the document and read its content. In addition,
you can view a multimedia document (audio or video) and play it online. If the
browser supports HTML5, it uses the native player of the browser. Otherwise it
falls back to a Flash player.

![Figure 5.11: You can watch a video clip or even view a slideshow inside Liferay's Documents and Media portlet.](../../images/05-document-preview.png)

Document previews are powerful and help users browse media more successfully to
find what they're looking for. 

### Automatic extraction of RAW Metadata [](id=automatic-extraction-of-raw-metadata-liferay-portal-6-2-user-guide-05-en)

When adding new documents or viewing existing documents, a process is triggered
automatically that extracts the file's metadata. The library used by this
process is TIKA and it's already included in Liferay out of the box.

You can see the metadata when viewing the document, in the right side of the
window.

### Document type restrictions and workflow per folder [](id=document-type-restrictions-and-workflow-liferay-portal-6-2-user-guide-05-en)

You can force users to add only certain document types to a folder. By default,
child folders inherit the restrictions of their parent folder. You can change
this behavior by editing the folder and selecting the allowed document types.

If workflow is enabled, you can specify different workflow definitions per
folder. Furthermore, you can specify different workflow definitions per document
type and per folder. You can set this by editing the folder. Then the UI will
look like this:

![Figure 5.12: Restrict Marketing folder to use specific document types and workflow](../../images/05-document-type-workflow-restriction.png)

Document types are a powerful way to enforce rules for documents uploaded by
users. Next, we'll see a way to make it incredibly easy for users to access
documents stored in Liferay's Documents and Media repositories. 

### WebDAV access [](id=webdav-access-liferay-portal-6-2-user-guide-05-en)

Wouldn't it be great if you could access documents and folders belonging to
Liferay's Documents and Media library from your own machine's file manager? You
can, thanks to the Documents and Media library's WebDAV integration. WebDAV
stands for Web-based Distributed Authoring and Versioning. It's a set of methods
based on HTTP that allows users to create, edit, move, or delete files stored on
web servers. WebDAV is supported by most major operating systems and desktop
environments, including Linux (both KDE and GNOME), Mac OS, and Windows.

Suppose you've created an *Image Gallery* folder using a Documents and Media
portlet and uploaded some images to it. Portal users with the appropriate
permissions can access this folder, and the image files it contains, using a
browser and Liferay's web interface. WebDAV provides an alternative way to do
this using a file manager instead of a web browser. To access a folder stored in
a Documents and Media portlet on a remote server, you'll need log in credentials
for the portal and the WebDAV URL of the folder you'd like to access. 

Next, navigate to the Documents and Media portlet hosting the folder you'd like
to access. Mouse over the folder (*Image Gallery* for our example) and select
*Access from Desktop*.

![Figure 5.13: Select *Access from Desktop* to get the WebDAV URL of a folder.](../../images/webdav-access-from-desktop.png)

Copy the WebDAV URL. On Windows, right-click on *Computer* and select *Map
Network Drive*. Select an unused drive, paste the WebDAV URL, and click
*Finish*. You're prompted to enter your Liferay credentials and then, provided
you have the required permissions, the *Image Gallery* folder appears. You can
now add, edit, move, or delete files in this directory.

On Mac OS X, select *Go* &rarr; *Connect to Server* in Finder. Then enter the
WebDAV URL of the folder you'd like to access in the Server Address field, click
*Connect* and you should be prompted for your Liferay credentials. 

On Linux, you must slightly modify the WebDAV URL of your folder in your file
manager. For KDE's Dolphin, change the URL's protocol so that it says
`webdav://` instead of `http://`. For GNOME's Nautilus, change the URL's
protocol so that it says `dav://` instead of `http://`. Then press *Enter* and
you're prompted for your Liferay credentials.

Note that Liferay increments the version numbers of files edited and uploaded
via WebDAV so you don't have to worry that using your file manager will bypass
the functionality of Liferay's web interface. The Documents and Media
application is a powerful way to manage any types of files your users need to
use. Next, let's look at how you can leverage Liferay Portal's Asset framework.

Now you know just how easy it is to store your files using Liferay's Documents
and Media portlet. In the next section, we'll review some ways to organize and
manage your assets so you're getting the most out of your content.

## Liferay Sync [](id=liferay-sync-liferay-portal-6-2-user-guide-05-en)

Liferay Sync is an add-on product for Liferay 6.1 GA2 CE and EE, and later
versions, that synchronizes files between your Liferay server and users' desktop
and mobile environments. With Liferay Sync, your users can publish and access
shared documents and files from their native environments without using a
browser. Windows and Mac OS desktops and Android and iOS-based mobile platforms
are currently supported, while Linux users have an unsupported beta they can
use. As users add and collaborate on documents and files, Liferay Sync
automatically synchronizes them across all configured Sync clients. Liferay Sync
is fully integrated into the Liferay Platform so that features such as
authentication, versioning, and social collaboration function in the supported
environments. Liferay Sync stores files locally so that they're always
available, even when you're offline. It automatically synchronizes your files
upon reconnection.

### How does it work? [](id=how-does-it-work-liferay-portal-6-2-user-guide-05-en)

Liferay Sync manages documents and site information through Liferay's built-in
web services. Clients securely communicate to Liferay using user-supplied
credentials such that each users can only access those documents and sites for
which they have permission. Changes made through Liferay Sync are immediately
available to the rest of the Liferay Platform, including users accessing Liferay
through traditional web-based interfaces.

For desktop environments, a new folder structure is created and used for
synchronizing files. Files found therein can be treated as any ordinary file.
Credentials, sync frequency, and other options can be configured in-client.
Native desktop notification events keep you abreast of what Sync is doing, and
native menu and taskbar integration keep Sync controls within easy reach.

Mobile environments are naturally dependent on the way in which documents are
handled. For Android and iOS, documents are maintained in a file list, and can
be viewed by clicking on the files themselves. External files accessible from
other apps can be "opened" using Liferay Sync, thereby dropping them into your
Sync folder and synchronizing them across other Sync clients. In iOS devices,
"pulling down" on the Sync file list forces a refresh. In Android, click on the
*Refresh* icon within the menu.

Liferay Sync is designed to work with both Liferay 6.1 GA2 CE and EE, and later
versions. Using Sync with Liferay CE limits users to syncing one site.  Using
Sync with Liferay EE enables users to synchronize documents and files across all
the sites they can access.

Liferay Sync is also designed to work with Liferay Social Office. You can sync
one site from Social Office CE as well as one site from Liferay Portal CE. If
you've installed Social Office CE on Liferay Portal EE, then you can sync any
site from Portal, but only one from Social Office. If you've installed Social
Office EE on Liferay Portal EE, then you can sync any and all sites.

### Installing Liferay Sync [](id=installing-liferay-sync-liferay-portal-6-2-user-guide-05-en)

For Windows or Mac OS, visit the Liferay Sync product page [Liferay Sync Product
Page](http://www.liferay.com/products/liferay-sync/features), and click *Get it
Now* (on the right-side navigation menu) to download the client application for
your desktop environment. For Windows, the client application installer should
be named `liferay-sync-<version>-<date>.exe`. For Mac OS, it should be
`liferay-sync-<version>-<date>.dmg`. There is also a Linux beta version
available. Follow the on-screen instructions of the installer wizard to
configure your client to connect to an existing Liferay 6.2 deployment using 
your Liferay credentials.

Prior to Liferay 6.2, Liferay Portal's Documents and Media services contained
all the logic used by Liferay Sync. As of Liferay 6.2, Sync processing has been
removed from the portal and been placed in a plugin. Make sure that the Sync
plugin has been installed on your Liferay server before trying to connect to
your portal via a Liferay Sync client. The Sync plugin's name is *sync-web* and
is installed by default in Liferay Portal bundles. The Sync plugin is also
available on Liferay Marketplace and can be downloaded and installed on your
Liferay server just like any other Marketplace app. To find the app on
Marketplace, search for *Sync CE* or *Sync EE*, depending on your portal
version. To receive new features, optimizations, and bug fixes, make sure to the
update your Sync app whenever a new version is available.

#### Windows [](id=windows-liferay-portal-6-2-user-guide-05-en)

Upon launching the Windows application installer, you'll be prompted to choose
an installation location for Liferay Sync. Browse to an appropriate location on
your machine and click *Install*. After the installation is complete, you'll
need to complete the Liferay Sync setup. Leave the *Run Liferay Sync* button
checked to automatically start Liferay Sync after you click *Finish*.

The first time you run Liferay Sync, you'll have to enter some account
information. Sync needs to know where you'd like to locally store the files it's
supposed to sync with your Liferay server. And, of course, it needs to know your
server's URL and the account credentials with which it should authenticate.

![Figure 5.14: The first time you run Liferay Sync, you'll have to tell it how to communicate with your Liferay server.](../../images/liferay-sync-new-account-info.png)

The options for the Mac OS application installer are similar.

#### Mac OS [](id=mac-os-liferay-portal-6-2-user-guide-05-en)

Liferay Sync for Mac is packaged in a DMG file. Double-clicking on a DMG mounts
it as a disk image, and opens a window showing the contents of the image. To
install Sync, drag the Liferay Sync icon to the Applications folder. Once it's
installed, go to your Applications folder to run it.

![Figure 5.15: Drag the Liferay Sync icon to the Applications folder.](../../images/liferay-sync-mac-install.png)

When you launch Liferay Sync, the first thing you need to do is provide it with
the URL for the Liferay server that you'll be using Sync with, along with your
Liferay credentials. After that, you'll need to run through the brief setup
process that was described above for Windows.

Once you've finished your configuration and have clicked *OK*, Liferay Sync
starts running in the background, and an icon appears in your top menu bar. If
you wish to change any of your settings, click the icon to open the Liferay Sync
menu and click on *Preferences*. Note that on Windows, the Sync menu says
*Properties*, not *Preferences*.

#### Mobile [](id=mobile-liferay-portal-6-2-user-guide-05-en)

For iOS, visit the App Store, search for Liferay, and install the Liferay Sync
App.

For Android, go to Google Play, search for Liferay, and install the Liferay Sync
App.

Once the mobile apps are installed, follow the on-screen instructions as below.

### Using Liferay Sync on the Desktop [](id=using-liferay-sync-on-the-desktop-liferay-portal-6-2-user-guide-05-en)

Once installed, you'll see a Liferay Sync icon in your taskbar whenever it's
running. A green checkmark means Liferay Sync has a working connection to your
Liferay server and is updating the files in your Sync folder according to the
interval you specified in the wizard. Click the Liferay Sync icon in your
taskbar to bring up the menu. The options availabe from the menu are listed
below:

<!-- Check the list below: Some menu items may have changed for Sync Desktop
1.1.1. -->

*Open Sync Folder* opens your Liferay Sync folder in your native file manager.

*Open Website* provides links to the pages containing the Documents and Media
portlets which you have permission to access. By default, you can find links to
your personal Documents and Media repository as well as links to the Documents
and Media repositories of all the other sites you belong to. 

---

![Tip](../../images/tip.png) Note for administrators: If you don't have a
Documents and Media portlet anywhere on a site that's been selected for syncing,
you'll have to add the portlet. Otherwise, users will get a *The requested
resource was not found* error when they try to use the *Open Website* link from
their Sync menus.

---

*Recent Files* shows a list of recently created or modified files from all the
repositories you can access.

*Properties* (*Preferences*, on Mac OS) lets you change properties like starting
on login, desktop notifications, and sync frequency. It also allows you to edit
the account information you provided when you started Sync for the first time.
For example, you can enter a new URL for your Liferay server and enter a
different set of Liferay credentials.

![Figure 5.16: Open the Liferay Sync menu and select *Properties* (*Preferences*, on Mac OS) to edit the settings you configured during setup.](../../images/liferay-sync-properties.png)

There are three items listed in the *General Settings* section. *Start Liferay
Sync on Login* is checked by default. If you don't want Sync to start
automatically, uncheck this. *Show Desktop Notifications* is also checked by
default. Unless you uncheck this, when a file that you have synced is changed, a
small notification will appear in the corner of your screen. The *Check Server
For Updates Every:* field enables you to set how frequently it will check to see
if anything has changed. This can be set anywhere between 5 seconds and 30
minutes.

Click the *Edit Settings* button in the *Account Settings* section to specify
your server's URL and enter your Liferay credentials. Use the *Test Connection*
button to make sure Liferay Sync can communicate with the server. Editing your
settings also allows you to specify your Sync folder, the folder where Sync will
store files on your machine. By default, files are stored in the *liferay-sync*
subfolder of your personal Documents folder.

Finally, the *Site Settings* section allows you to choose which sites you wish
to sync media from. By default, it will list all of the sites that you are a
member of, but you can uncheck any of those sites if you don't want to sync
those files.

*About* displays Liferay Sync version information, copyright information, and a
link to Liferay's home page.

*Check for Updates* checks to see if a new version of Liferay Sync is available
from [liferay.com](liferay.com) and allows you to set whether or not Liferay
Sync should automatically check for updates.

#### Using your Sync folder [](id=using-your-sync-folder-liferay-portal-6-2-user-guide-05-en)

Once Liferay Sync has been configured and is running, any files you add to or
modify in your Sync folder are automatically detected and uploaded to your
Liferay server. Also, changes from other users are downloaded to your Sync
folder.

If you delete a file your sync folder, it will only be deleted locally. It will
not be deleted on the server. This mechanism prevents users from accidentally
deleting shared files. When you delete a file from you Sync folder, Sync will no
longer download changes to this file the next time it syncs. If you want to go
back to syncing a file, simply restore it from you recycle bin or trash can.
Once the file is restored back to the Sync folder, Sync keeps that file in sync
by uploading any of your changes and downloading any changes from the server.

You can run through the following exercise to familiarize yourself with how to
create, edit, download, and upload files with Liferay Sync. First, open your
Liferay Sync folder in your file manager (use the *Open Sync Folder* option of
the Liferay Sync menu from the taskbar), and create a new file called
`README.txt`. Edit this file and enter the word *test*. Next, check that you can
access this file from your Liferay site. Open your browser, navigate to your
Liferay site, and sign in with your Liferay account credentials. First, make
sure that that you're on the site you want to sync with. Then navigate to *Site
Administration* &rarr; *Documents and Media*. You should see your `README.txt`
file listed there.

Download the file (click the small triangle icon at the top right corner of the
*README.txt* icon and select *Download*) to a convenient location on your
machine and check that it still says *test*. Now open the `README.txt` file in
your Sync folder and edit it so that it says *second test*. Once the changes are
synced go back to your browser and refresh your Documents and Media page. 
Click on the *README.txt* icon, look at the information displayed to the right, 
and you'll see that its version number has incremented.

![Figure 5.17: Updating a file through Liferay Sync increments the file's version number. You can view a file's version number through the web interface.](../../images/liferay-sync-README.png)

You'll see that it now says *second test*--your edit was uploaded to the server.
You can be confident that this edit was also downloaded by all other Liferay
Sync clients connected to your site. 

#### Demonstrating Liferay Sync Permissions [](id=demonstrating-liferay-sync-permissions-liferay-portal-6-2-user-guide-05-en)

Liferay Sync uses the default Liferay permissions to determine which files and
folders are synced to the user's machine. This means that whatever files a user
can access from a certain site are the ones that will be pulled down by Liferay
Sync if that site is selected in the Sync client. You can test the functionality
of Liferay Sync permissions by performing the following steps. First, create a
new file on your desktop called *secret.txt*. Enter the text *classified
information* into this file. Then use your browser to log into Liferay and
create a new user called *secretagent* with the email address
*secretagent@liferay.com* and the password *test*. Also, create a new private
site called *Secret Site*. Then assign the *secretagent* user to the *Secret
Site* and grant the *Site Administrator* role to this user. There will be no
other members of this site unless they are assigned by an administrator. Log in
as the *secretagent* and select *Secret Site* from the *My Sites* tab on the
Dockbar. Then click on *Documents and Media* and upload the *secret.txt*
document.

Next, we'll configure our Liferay Sync client to log in with the *secretagent*
user's credentials and access the *Secret Site*. Open the Liferay Sync menu from
the taskbar and select *Properties*. Click on the *Edit Settings* button, choose
a new Sync folder, enter your server's URL, and enter the secret agent's
credentials: *secretagent@liferay.com* and *test*. Lastly, uncheck all Liferay
sites except the *Secret Site*, and click *OK*. Confirm that the *secret.txt*
file that you uploaded to the *Secret Site*, is downloaded to your new Sync
folder. Open it and check that it says *classified information*. If you
reconfigure your Sync client connect to your Liferay instance using the
credentials of another user who doesn't belong to the *Secret Site*, the
*secret.txt* will not be downloaded. Congratulations! You've successfully set up
a Liferay Sync folder that can only be accessed by the *secretagent* user and
your administrators.

### Using Liferay Sync Mobile [](id=using-liferay-sync-mobile-liferay-portal-6-2-user-guide-05-en)

Once you've installed Liferay Sync on your Android or iOS mobile environment,
you'll be able to access the same functionality that's available when using Sync
on a desktop environment. However, the interface differs from that of the Sync
desktop clients. Let's discuss the usage of Liferay Sync Mobile for Android
first.

#### Android [](id=android-liferay-portal-6-2-user-guide-05-en)

After installing Liferay Sync for Android, an empty screen appears asking you to
set up the app. This screen appears whenever preferences are missing. Touch the
screen and it displays the *Settings* view. You can always go back to *Settings*
by clicking on the wrench icon at the top right corner of the screen.

![Figure 5.18: Click the wrench icon to access your Liferay server credentials.](../../images/liferay-sync-android-settings.png)

Enter your Liferay server credentials by filling in your *Login*, *Password*,
and *Server* information. Your *Login* is either your user account's email
address or screen name. Use the same credentials you use to log in to the portal
in a browser. In the *Server* field, enter your portal's URL. In this example,
the server URL is *http://www.liferay.com*. Click the key icon on the top right
to test your connection and check if everything is correct.

Note for Gingerbread users: If you can't see some of the features described
here, click on the menu button to view a list of all possible actions. This
includes options to refresh, open the settings menu, upload files, take photos,
test your connection, etc. After you have successfully tested your connection,
hit the *back* button and you'll see a list of Liferay sites you have access to.

![Figure 5.19: Sites](../../images/liferay-sync-android-sites.png)

You can browse the files of a site by tapping on any of them. This opens a list
of the folders and files belonging to the site that you have permission to view.
From here, you can click on a folder and browse deeper into the folder hierarchy
or click the *Back* button to navigate back to parent folders up to the initial
*Sites* list.

Single-tap on a file to open it. If the file has never been downloaded before,
Sync will download it and open after it has finished downloading. You can only
view the file's contents if your device has an app installed that can open the
file type. For example, in order to open a PDF, you must have at least one PDF
viewer app installed. Otherwise, you will see a message informing you that no
viewer is available and you need to install an app that can open the file.

Long-press on any folder or file to find a list of actions you can take on it:
*Add to Favorites*, *View Details*, *Download*, *Rename* or *Delete*. This
actions menu varies depending on which entry type is selected: file or folder. 

On Gingerbread, the actions menu appears in the middle of the screen. On Ice
Cream Sandwich and above, you can find the action icons and menu at the top
right.

Clicking on *Add to Favorites* (Gingerbread) or the gray star (Ice Cream
Sandwich) adds the selected file to the *Favorites* list. *Favorites* are
special files that can be accessed and viewed even when you are offline (more
details below). If a file is already marked as a favorite, you'll see a
*Remove from Favorites* or blue star instead. Clicking on it removes the
selected file from the *Favorites* list.

Clicking on *View Details* (Gingerbread) or the round icon with the letter "i"
(Ice Cream Sandwich) opens the details view, which displays the entry's metadata
such as creation date, author, version, description, etc.:

![Figure 5.20: Liferay Sync's details view offers several options.](../../images/liferay-sync-android-details.png)

If you click on *Download* (floppy disk icon on Ice Cream Sandwich), it
downloads and overwrites the local file copy.

You can rename a folder or file by clicking on the *Rename* option. This renames
the entry in the portal.

Clicking on *Delete* deletes the file/folder from the remote portal, and 
other users won't be able to view or download it. On Ice Cream Sandwich and
above, you can select multiple entries for deletion.

Some actions are not related to a specific folder or file. You can find these
actions in the menu on the top action bar when no entry is selected (Gingerbread
users need to click on the device menu button). Depending on the device screen
width, some icons may overflow to the three dots button on the right. Click on
this button to see all of the available actions.

![Figure 5.21: More options are available by selecting the three dots button.](../../images/liferay-sync-android-more.png)

The *Refresh* button fetches and updates the list of folders and files that have
been changed in the portal.

The *Camera* button allows you to quickly take a picture and upload the image to
the current folder. The image file name is automatically generated with a time
stamp.

The *New Folder* button asks you for the name of the folder you want to create
in the portal.

The *Upload* button displays the types of local files you can upload to the
portal. Choosing *Image*, for example, shows all images that are stored locally
on your device. Once you choose the files and confirm, these files are uploaded
to the portal and are placed in the current folder. By default, you can upload
images, videos, and audio files. If you have installed an app on your device
that can open and browse any type of file, you will also see an option called
*Other files*.

The *Favorites* menu option opens the favorites list. All files that have been
marked as favorites show up in this list. You should mark your most important
files as favorites because, as mentioned earlier, the *Favorites* feature gives
you quick offline access to them. You can view the contents of items in the
*Favorites* list, view their metadata and, of course, remove them from the list.

Next, let's look at the iOS Sync app.

#### iOS [](id=ios-liferay-portal-6-2-user-guide-05-en)

After installing Liferay Sync for iOS, an empty screen appears asking you
to set up the app. This screen appears whenever preferences are missing.

Click on *Settings* in the toolbar and enter your Liferay server credentials by
filling in your *Login*, *Password*, and *Server* information. Your *Login*
is either your user account's email address or screen name, whichever you use
to log in to the portal in a browser. In the *Server* field, enter your
portal's URL. In this example, the server URL is *http://www.liferay.com*. Click
on *Test Connection* to check if your configuration is correct.

After you have successfully tested your connection, tap on the *Documents*
toolbar section and you'll see a list of Liferay sites you have access to. You
can browse the files of a site by tapping on its name or icon. This opens a list
of the folders and files belonging to the site that you have permission to view.

![Figure 5.22: Liferay Sync offers a folder and files menu.](../../images/liferay-sync-ios-files-list.png)

From here, you can click on a folder to browse deeper into the folder hierarchy.
You can also click on the *Back* button to navigate back to parent folders up to
the initial *Sites* list.

You can refresh the list by pushing it down. This updates all the files and
folders that have been changed in the portal.

When you click on a file, this file is downloaded from the remote portal
and, if a previewer for this file type is available, you can view the contents
of the file. The next time you open a file, it won't download it again; instead,
it opens the local copy.

![Figure 5.23: Local files are stored after they are downloaded.](../../images/liferay-sync-ios-open.png)

There are 3 icons at the bottom of the screen when you open a file:

- Round icon with the letter "i" opens the details view, which displays the
entry's metadata such as creation date, author, version, description, etc.

- Star icon at the center adds the selected file to the *Favorites* list.
*Favorites* are special files that can be accessed and viewed even when you are
offline (more details below). If a file has already been marked as a favorite,
clicking on the star icon removes the file from the *Favorites* list.

- Share icon displays sharing options. You can, for example, send the file as an
email attachment, print the file, or copy it to your clipboard. Some external
apps may also appear in this list. For example, you can share your file with
social apps and messengers if they are available.f

In the file list, there's an Edit button. Clicking on it switches the app to
the edit mode as shown below:

![Figure 5.24: Edit mode is a key feature in Liferay Sync mobile.](../../images/liferay-sync-ios-edit.png)

Selecting one or more files or folders and clicking on the *Delete* button
deletes the selected files or folders from the remote portal. Once you delete
files or folders from the remote portal, other users won't be able to view or
download them.

Selecting only one file or folder enables the *Rename* button. Click on it to
change the entry's name locally and remotely.

To quickly delete a file or folder from the portal, you can also swipe right and
click on the *Delete* button in the file list view.

If you want to upload an image or video to the portal, click the *Plus* button
at the top right corner. You should see three options: 

- *Take a photo or video* opens your camera app and lets you take a photo or
record a video and upload it.

- *Choose Existing* allows you to upload multiple photos or videos stored on
your device.

- *Create New Folder* lets you type the name of the folder and creates it in the
portal.

The *Favorites* toolbar section opens the favorites list. All files that have
been marked as favorites show up in this list. You should mark your most
important files as favorites because, as mentioned earlier, the *Favorites*
feature gives you quick offline access to them. You can view the contents of
items in the *Favorites* list, view their metadata and, of course, remove them
from the list.

All downloaded files are stored on your device indefinitely. If you want to
delete downloaded files locally but don't want to remove them from the portal,
go to *Settings* and click on the *Clear Cache* button.

## Summary [](id=summary-liferay-portal-6-2-user-guide-05-en)

In this chapter, we examined Liferay's Documents and Media Library, a powerful
and customizable virtual shared drive. Liferay 6.1 introduced the ability to
mount multiple external repositories to the Documents and Media library. The
Documents and Media library can be used to store files of any kind. The
Documents and Media Display portlet is meant to be configured to show chosen
hierarchies of folders and files from the Documents and Media library. The Media
Gallery is meant for presenting media files such as images or videos. 

Document types and metadata sets provide a flexible way to distinguish between
different types of files and to define custom metadata fields for them. Document
previews are automatically generated by default, but Liferay supports
integration with external tools that offer greater speed, higher quality, and
additional functionality. Finally, we discussed Liferay Sync, an add-on product
for Liferay 6.2 that allows your Liferay server to directly synchronize files on
users' desktop and mobile environments.
