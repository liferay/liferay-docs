# Document Management [](id=documents-and-media)

Liferay's Documents and Media library provides a mechanism for storing files
online using the same type of structure that you use to store files locally. You
can use it to store files of any kind; it serves as a virtual shared drive. The
Documents and Media portlet of Liferay 6.1 takes its name from the fact that it
represents a redesign of two portlets from previous versions: the Document
Library and the Image Gallery. First, the Documents and Media library serves as
a repository for all types of files; there's no need to store image files in a
separate Image Gallery anymore. Second, Liferay 6.1's Media Gallery portlet does
not serve as a repository but just displays selected content from the Documents
and Media library. It can display image, audio and video files. Other features
introduced with Liferay 6.1's Documents and Media library include customizable
document types and metadata sets, automatic document preview generation and
support for mounting multiple external repositories. The new document types and
metadata sets are an addition to, not a replacement for, the portal's system of
tags and categories. Let's start exploring how to use the Documents and Media
portlet.

## Getting Started with the Documents and Media [](id=lp-6-1-ugen04-getting-started-with-the-documents-and-media-0)

The Documents and Media portlet is non-instanceable. This means that each page
on your portal can host at most one such portlet. Furthermore, if you add
multiple Documents and Media portlets to pages in the same site, these portlets
will share the same data sets since they are scoped by site by default. However,
you can add multiple Documents and Media *Display* portlets to a page. Then you
can choose content from actual Documents and Media repositories to display.
Remember that users, by default, have their own personal sites with public and
private pages. They can use their personal sites to host Documents and Media
portlets for storing or sharing their own files.

![Figure 4.1: Initial View of the Documents and Media
Portlet](../../images/05-document-library.png)

The default view of the Documents and Media portlet displays the contents of the
*Home* folder. The links on the left side of the portlet windows are filters.
You can use these filters to choose what you want the main window of the portlet
to display. *Recent Documents* displays documents users have recently uploaded,
edited or downloaded. *My Documents* shows *your* documents; in other words, the
documents you have uploaded. *Basic Document* and the document types listed
below it are also filters. If you click on one of these filters, the main
portlet window shows only documents that belong to the selected document type.
When you add custom document types, which we discuss below, they are added to
the filter list. Next, let's look at how to navigate around Documents and Media. 

### Navigating the Documents and Media Portlet [](id=lp-6-1-ugen04-navigating-the-documents-and-media-portlet-0)

In the main window of the Documents and Media portlet, you can click on a
document to view details about it. Its version number, version history, status,
as well as its uploader and the user who last edited it. Depending on the
document, some automatically extracted metadata may also be displayed, such as
the document creator, author, title, content type, creation date and last
modification date. In the case of audio or video files, the duration would also
be displayed. You can perform several actions on the document here:

**Download:** lets you download the document.

**Get URL:** displays the URL of the document on the server.

**Get WebDAV URL:** displays the WebDAV URL of the document on the server. See
the WebDAV access section below for more information.

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

**Delete:** lets you remove the document from the Documents and Media library.

If comments are enabled, you can also view comments, add comments or subscribe
to comments about documents. Comments are enabled by default.

![Figure 4.2: Viewing a Document](../../images/05-doclib-document-view.png)

The menu at the top of the Documents and Media portlet contains Actions, Add,
Sort By and Manage buttons. There are also buttons for switching between icon
view, descriptive view and list view. If there are lots of documents in the
Documents and Media library, the search field can help you find the documents
you're looking for. If your portlet contains more documents than it can display
at once, you can use the navigation tool at the bottom of the portlet window to
either switch your view to another page or configure the page to display more
documents per page. 

#### Actions [](id=lp-6-1-ugen04-actions-0)

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

**Delete:** allows you to remove a document or folder from the portlet. You can
delete multiple documents and folders at the same time.

#### Add [](id=lp-6-1-ugen04-add-0)

From the Add button, you can add documents, folders and shortcuts just like on
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

#### Sort [](id=lp-6-1-ugen04-sort-0)

You can sort the items displayed in the main window of the Documents and Media
portlet using the Sort By menu. You can sort by title, create date, modified
date, downloads or size.

**Title:** lets you alphabetically sort documents by title.

**Create Date:** lets you sort documents by the time they were created.

**Modified Date:** lets you sort documents by the last time they were modified.

**Downloads:** lets you sort documents by the number of times they were
downloaded.

**Size:** lets you sort documents by how much disk space they use.

#### Manage [](id=lp-6-1-ugen04-manage-0)

The Manage menu allows you to view the names of document types and metadata
sets, as well as the last times they were edited.

**Document Types:** shows you a list of defined document types.

**Metadata Sets:** shows you a list of defined metadata sets as well as their
portal IDs.

## Document Types and Metadata Sets [](id=lp-6-1-ugen04-document-types-and-metadata-sets-0)

Customizable document types and metadata sets are new features in Liferay 6.1.
When a user assigns a document type to a document, the user is required to fill
out the fields defined by the metadata set of the document type. This encourages
users not to forget to enter important information about their documents. For
example, you could create a "copyrighted" document type and require users to
enter a license for all "copyrighted" documents. More importantly, document
types and metadata sets can improve document searchability. The values that
users enter into the fields determined by their document type's metadata set
become searchable entities within the portal. You can use Liferay's search
portlet to search for these terms. Document types and metadata sets are
accessible from the Manage Button at the top of the Documents and Media portlet
window.

![Figure 4.3: Document Types Dialog Box](../../images/05-document-types.png)

You can add a new document type using the Add button at the top of the dialog
box. To do so, you need to define one or more metadata sets to associate with
your document type. When creating a new document type, you can define "Main
Metadata Fields" or select "Additional Metadata Fields". Main metadata fields
are directly tied to their document type and cannot be made available to other
document types. Additional metadata fields, by contrast, can be defined
independently and can be used in many different document types. You can
differentiate the document types that implement the same additional metadata set
by defining different main metadata fields for them. However, Additional
metadata fields need to be defined and saved before creating a document type
that will implement them. 

![Figure 4.4: Adding a New Document Type](../../images/05-new-document-type.png)

As an example, we could create a document type called "Syllabus" and define a
metadata set. What metadata should we associate with syllabi? Let's choose for
our syllabi to have course title, professor, semester, course description and
course requirements fields. All syllabi in our portal should maintain entries
for these fields. This ensures that a syllabus will show up in a portal search
if its course title, professor or semester is searched for. Since we don't want
to use our metadata set for any document type other than "Syllabus," let's
create our metadata set under the Main Metadata Fields area. Alternatively, we
could create our metadata set independently using *Manage &rarr; Metadata Sets
&rarr; Add* and then select it as an Additional Metadata Field. 

![Figure 4.5: Selecting Additional Metadata
Sets](../../images/05-selecting-additional-metadata-sets.png)

You can view, edit or add metadata sets from the *Manage &rarr; Metadata*
window. A metadata set consists of a group of fields. If you click the Add
button, you can use same UI for defining a metadata set that you used in the Add
Document Type window.

![Figure 4.6: Adding a New Metadata Set](../../images/05-new-metadata-set.png)

Make sure the Fields tab is selected on the left. Then, to define a metadata
set, just choose fields to use from the area on the left and drag and drop them
into the area on the right. The drag and drop interface allows for nested fields
so you need to be careful about where you drop the fields. Default values,
mouse-over tips, widths and other settings can be configured for most fields. To
configure these settings, just double-click on a field from the area on the
right. This automatically selects the Settings tab on the left. Then
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

**File Upload:** lets you select file to upload from your local system.

**Integer:** lets you enter an integer. The value will be persisted as an int.

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

## Using External Repositories [](id=lp-6-1-ugen04-repositories-0)

Adding repositories in Documents and Media is a new feature in Liferay 6.1.
Content Management Interoperability Services (CMIS) is a specification for
improving interoperability between Enterprise Content Management systems.
Documents and Media allows users to connect to multiple third-party repositories
that support CMIS 1.0 with AtomPub and Web Services protocols.

Some of the features supported with third-party repositories include:

- CRUD operations of Documents and Folders
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
deployment of an appropriate Liferay plugin.

<!-- TODO note in paragraph above, where to find out plugins/hooks a repository
requires -->

Let's go through those steps, starting with setting our portal properties.

### Adjusting portal properties

The admin must ensure that the same credentials and authentication are being
used in Liferay and in the external repository. This is normally synchronized
using a mechanism like LDAP. If you don't have LDAP, you need to ensure manually
that the credentials and authentication are the same. In order to authenticate
with the third-party repository, you need to store passwords to the the user
sessions. Set the following portal property in your `portal-ext.properties`:

    session.store.password=true

Next, we need to make sure the login and password for Liferay are the same as 
the external repository. This is easily accomplished by using identical screen 
names, so in `portal-ext.properties` add the following: 

    company.security.auth.type=screenName

Alternatively, configure these properties in the Control Panel under *Portal 
Settings* &rarr; *Authentication*.

### Adding required repository users

You'll need to create a user in Liferay with a screen name and password matching
the administrative user of your external repository. Be sure to assign
appropriate roles (e.g. Administrator) to that user. Sign out of Liferay and
sign in again as that new user. See sections of the *Management* chapter on
adding and managing users.

### Adding the repository

You can add new repositories from the UI by clicking the *Add* button from the
Home folder. Repositories can only be mounted in the Home folder.

![Figure 4.7: Adding a new repository](../../images/05-new-repository.png)
    
The repository is displayed in the left side of the window in the Home folder.

![Figure 4.8: Viewing a repository](../../images/05-repository.jpg)

It may be helpful, at this time, to walk through an example of setting up an
external repository. As noted previously, there are several programs that work
well with Liferay using CMIS. One program familiar to many users is SharePoint.
In the exercise below, we'll set up a SharePoint as a Documents and Media
repository.

### Example Repository Setup: SharePoint

With Liferay Portal you can connect to an external SharePoint server and add it
as a Documents and Media repository. This will allow users to collaborate and
share documents more easily between both environments. We will mount a
SharePoint repository via CMIS AtomPub and SharePoint SOAP webservices. Liferay
uses a combination of SOAP and Representational State Transfer (REST), based on
the Atom convention, to connect to the SharePoint Repository.

SharePoint 2010 provides various SOAP services allowing access to modify and 
query the data from the document library. Liferay uses Axis2 to generate the 
SOAP calls to the SharePoint server. 

For documentation of SharePoint SOAP services, visit the MSDN Library at
[http://msdn.microsoft.com/en-us/library/default.aspx](http://msdn.microsoft.com/en-us/library/default.aspx)
and search for the Lists web service, the Versions web service, or other
SharePoint Foundation web services. For documentation of Axis2, visit Apache's
QuickStart Guide at 
[http://axis.apache.org/axis2/java/core/docs/quickstartguide.html#clients](http://axis.apache.org/axis2/java/core/docs/quickstartguide.html#clients)
and search for Axis2 Services and SOAP messages.

To use SharePoint as a Liferay Documents and Media repository, we'll
do the following on the SharePoint server:

- Configure the CMIS Connector on SharePoint.
- Activate a SharePoint site as a CMIS Producer.
- Enable Basic Authentication on the SharePoint host.

Note, this section is geared towards portal system administrators and system
administrators of your SharePoint server.

Before you can use SharePoint as an external repository with Liferay portal, you
need to verify that SharePoint is properly configured. Several services must be
set up on the SharePoint 2010 server before synchronizing with Liferay. First,
the CMIS Connector must be installed and configured. Second, the CMIS Producer
must be set up. Third, SharePoint's Basic Authentication must be enabled for the
connector and producer to work.

#### Configuring the CMIS Connector on SharePoint

SharePoint utilizes a CMIS Connector and a CMIS Producer to interface with
Liferay Portal. The Connector is installed by the SharePoint 2010 Administrator
Toolkit using a solution package called a Windows SharePoint file (.wsp). If you
don't have it already, install the SharePoint 2010 Administrator Toolkit for its
CMIS Connectors. Install and deploy the CMIS Connectors as a Farm Solution on
SharePoint.

Follow these steps to install the SharePoint 2010 Administrator Toolkit:

1. Download the SharePoint 2010 Administrator Toolkit and run the executable 
file. This will install the CMIS Connector as the file spcmis.wsp.

2. Select *Content Management Interoperability Services* and the required
features and click *Next*. The installer deploys a WSP to the directory
`C:\Program Files\Microsoft\SharePoint 2010 Administration Toolkit\Content
Management Interoperability Services (CMIS) Connectors` called `spcmis.wsp`.

Once this is done, it's time to deploy the WSP following these steps:

1. Go to the SharePoint 2010 Management Shell.
2. Run the following command: `Add-SPSolution -LiteralPath 'C:\Program
Files\Microsoft\Sharepoint 2010 Administration Toolkit\Content Management
Interoperability Services (CMIS) Connectors\spcmis.wsp'`.
3. Go to *Central Administration* &rarr; *System Settings* &rarr; *Manage Farm 
Solutions*.
4. Click *spscmis.wsp*, choose the appropriate deployment settings, and click 
*Deploy Solutions*.

When deployment is complete, Solution Properties should show that the solution
was successfully deployed to all target sites. Now it's time to configure the
CMIS Producer.

#### Activating a SharePoint site as a CMIS Producer

The Producer allows SharePoint repositories, called libraries, to be available
through the CMIS connector. Choose the SharePoint site where the document
libraries will be used as document repositories. Every document library in this
site will be available as a repository through the CMIS connector.

Follow these steps to enable the CMIS producer:

1. Go to the SharePoint site with the document library you want to use as a
repository. 
2. Go to *Site Actions* &rarr; *Site Settings* &rarr; *Manage Site Features*.
3. You should see *Content Management Interoperability Services (CMIS) Producer*
at the top of the list. 
4. Enable the CMIS Producer by clicking *Activate*. 

![Figure 4.XX: By enabling the CMIS producer, you allow SharePoint libraries to 
be used as repositories.](../../images/SharePoint_CMIS_producer.png)

Now any document library created under this site will be CMIS enabled.

To verify CMIS is working correctly, enter the CMIS REST AtomPub URL into a
browser. The basic structure of the AtomPub URL looks like the
following:

http://[server-name]/_vti_bin/cmis/rest/[repositoryid]?getRepositoryInfo.

The [server-name] is also the host name. The [repository id] is the list or
library GUID. It is easiest to find the list ID by accessing the SharePoint
repository using the Firefox browser while performing the following steps:

1. In SharePoint, open the desired library. We can use the CMIS Repository Test
we created earlier. Listed above the library is a set of menu options.
2. Under *Library Tools* select *Library*. 
3. Click on *Library Settings*, located to the far right. 
4. The browser window will refresh and the resulting URL will display the value
of the list ID between braces.

![Figure 4.XX: The repository ID, or the list ID, can be found by displaying the 
repository's URL in a Firefox browser.](../../images/List_ID.png)

As this figure indicates, the repository ID is the data displayed between the
braces in the URL. So for this URL, the list ID is
6DFDA9-B547-4D1D-BF85-976863CDF53306. Therefore, the AtomPub URL you'd use to
verify the CMIS would resemble this:

http://liferay-20jf4ic/CMIS/_vti_bin/cmis/rest/6DFDA9-B547-4D1D-BF85-976863CDF53306?getRepositoryInfo.

When you enter the AtomPub URL into a browser window, you should now see the
CMIS is working correctly.

---

![Note](../../images/tip.png) Note, the repository ID is an important piece of
data you want to save in a secure location for later use. You'll need this ID to
mount the external repository in Liferay's Documents and Media.

---

When we're satisfied the CMIS is working correctly, the next step is to enable
Basic Authentication in SharePoint.

#### Enabling Basic Authentication on the SharePoint host

For the CMIS connector and producer to work, you must enable Basic
Authentication on IIS. This will allow Liferay's SharePoint hook to authenticate
against web services. 

On Windows, run the following steps:

1. Go to *Start* &rarr; *All Programs* &rarr; *Administrative Tools* &rarr;
*Internet Information Services (IIS) Manager*.
2. Expand the *Sites* node and select the *SharePoint* site.
3. Select *Authentication* under IIS, then click *Open Feature* under Actions.
4. Change the status of Basic Authentication to *Enabled*.

![Figure 4.XX: The Windows panel Internet Information Services (IIS) Manager is where we enable SharePoint's Basic Authentication.](../../images/SharePoint_basic_authentication.png)

Those are the final steps for preparing SharePoint to be mounted as an external
repository. Now we need to prepare Liferay portal.

#### Setting up Liferay Portal to use the SharePoint repository

With the SharePoint server configured, we can now turn our attention to
Liferay. In order for Liferay to connect with the external repository, we must
configure the portal environment. We'll start by downloading the SharePoint hook
from Marketplace and installing it on portal. This will deploy the hook
automatically.

Mounting the SharePoint repository in Liferay is accomplished using the 
Documents and Media portlet. If you haven't already done so, add a Page and then
add the Documents and Media portlet to that page. 

Add a repository to Documents and Media by running the following steps:

1. In the portlet click *Add Repository*. 
2. Enter *SharePoint* as the Name and Description for the repository.
3. Under Repository Type select *SharePoint (AtomPub)*. 
4. In the AtomPub URL field enter your data using the following format:

http://[Host Name]/_vti_bin/cmis/rest/[Repository ID]?getRepositoryInfo.

The [Host Name] can be the server's IP address or the computer's name. The
[Repository ID] comes from the SharePoint server's URL. This is the same URL we
used above to verify that CMIS was working correctly using the Firefox browser.

5. Leave the *Repository ID* field blank. The system will search for the first 
repository using the given parameters and set it to that one.
6. In the *Site Path* field enter data using the following format:

http://[Host Name]/[Repository Name].

7. Click *Save*.

You should now see the repository listed in the left navigation panel of the
Documents and Media portlet.

---

![Tip](../../images/tip.png) In the example below, notice how the repository has
the two-word name, *Shared Documents*. This is how the SharePoint URL was
displayed in Firefox:

http://liferay-20jf4ic/CMIS/Shared Documents/Forms/AllItems.aspx.

The space between the words in the repository name must be accounted for when
setting the site path in Liferay. Replace the empty space with the string *%20*
so the site path now looks like this:

http://liferay-20jf4ic/CMIS/Shared%20Documents/Forms/AllItems.aspx.

This should alleviate any potential confusion.

---

That about does it for this exercise. Remember that connecting to an external
SharePoint server and adding it as a Documents and Media repository is a great
way to give users flexibility when collaborating and sharing documents. But this
is not the only way to share resources between Liferay portal and another
environment.

Documents and Media has another feature similar to an external repository that
is called *Access From Desktop*. This feature uses the WebDAV protocol to allow
users to upload and organize resources from both a web interface and the file
explorer of their desktop operating system. See the section on WebDAV access
later in this chapter for further details.

<!-- TODO add transition -->

## Configuring the Documents and Media portlet [](id=lp-6-1-ugen04-configuring-the-documents-and-media-portlet-0)

To configure the Documents and Media portlet, click on the wrench icon at the
top of the portlet window and select *Configuration*. The portlet-specific
customizations appear on the Setup tab. To change your Documents and Media
portlet's top-level folder, click *Select* next to *Root Folder*, browse to the
folder you'd like to be your new top-level folder, and click *Save*. The root
folder is the highest-level folder that's accessible from the Documents and
Media portlet. For example, suppose you created a folder called *My Documents*
in the Documents and Media portlet's default Home folder.  If you set the My
Documents folder to be your portlet's new root folder, the original Home folder
would no longer be accessible.

![Figure 4.9: To make portlet-specific configurations for Documents and Media,
click on the wrench icon at the top of the portlet window and select
*Configuration*.](../../images/docs-and-media-portlet-config.png)

By default, the Documents and Media portlet contains a search bar to help users
quickly find relevant files. If you'd like the search bar not to appear, uncheck
the *Show Search* box. The *Maximum Entries to Display* dropdown menu lets you
set a limit on how many folders and files can be displayed in the portlet window
at once. By default, the Documents and Media portlet contains three display
style views: icon, list, and descriptive. Icons for each appear in the portlet
window, allowing users to select the display style with which they're most
comfortable. Under the Display Style Views heading, you can select which display
styles users are able to choose and you can arrange the order of the selected
display styles. The topmost display style in the list becomes the portlet's
default display style.

Related assets are enabled by default for Documents and Media files. Related
assets allow users to link assets together even if the assets don't share any
tags or categories. To disable related assets for files in your Documents and
Media portlet, uncheck the *Related Assets* box. For more information on related
assets, see the section on defining content relationships in chapter 6.

Under the Show Columns heading, you can customize which columns appear when your
Documents and Media portlet uses the list display style. By default, file names,
sizes, downloads, and actions are displayed. You can also configure the portlet
to display files' create dates and modified dates. To add or remove columns from
being displayed, move them to the Current box or to the Available box. You can
arrange the columns in the Current box to control the order in which the columns
appear in the portlet: the topmost column in the box appears as the leftmost
column in the portlet.

Comment ratings are also enabled by default for Documents and Media files. If
users decide that a certain comment about a file is useful or informative, they
can rate it as good by clicking on the thumbs up icon next to the rating. If
they think the comment is unhelpful or misleading, they can click on the thumbs
down icon. If you'd like to disable comment ratings for files within your
portlet, uncheck the *Enable Comment Ratings* box.

## Automatic Previews and metadata [](id=lp-6-1-ugen04-automatic-previews-and-metadata-0)

Whenever possible, Liferay 6.1 generates previews of documents added to the
Documents and Media library. Out of the box, Liferay only ships with Java-based
APIs to generate previews for documents. The only tool available that is 100%
Java and has a compatible license to be distributed with Liferay is PDFBox. From
a vanilla installation of Liferay 6.1, if you upload a PDF file to the Documents
and Media portlet, Liferay will process the PDF in a separate thread to generate
a preview. This process may last only a few seconds for a small file. The larger
the file is, the longer it takes.

The first time you run a conversion like this, look for a console message that
indicates something like the following:

    Liferay is not configured to use ImageMagick for generating Document Library
    previews and will default to PDFBox. For better quality previews, install
    ImageMagick and enable it in portal-ext.properties.

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
documents in your browser and lets you convert documents. ImageMagick allows for
faster and higher-quality previews and conversions. Xuggler allows for audio and
video previews, lets you play audio and video files in your browser and extracts
thumbnails from video files. Please see the *External Services* section of
chapter 16 for instructions on how to configure Liferay to use these tools.

With the above tools installed and enabled, the Documents and Media library
looks like this:

![Figure 4.10: Previews in Documents and Media](../../images/05-previews.jpg)

You can view a document with a customized viewer that allows you to navigate
through the different pages of the document and read its content.

![Figure 4.11: Viewing an office document](../../images/05-document-preview.jpg)

You can view a multimedia document (audio or video) and play it online. If the
browser supports HTML5, it uses the native player of the browser. Otherwise it
falls back to a Flash player.

![Figure 4.12: Playing a video](../../images/05-video-preview.png)

Document previews are powerful and help users browse media more successfully to
find what they're looking for. 

### Automatic extraction of RAW Metadata [](id=lp-6-1-ugen04-automatic-extraction-of-raw-metadata-0)

When adding new documents or viewing existing documents, a process is triggered
automatically that extracts the file's metadata. The library used by this
process is TIKA and it's already included in Liferay out of the box.

You can see the metadata when viewing the document, in the right side of the
window.

### Document type restrictions and workflow per folder [](id=lp-6-1-ugen04-document-type-restrictions-and-workflow-per-folder-0)

You can force users to add only certain document types to a folder. By default,
child folders inherit the restrictions of their parent folder. You can change
this behavior by editing the folder and selecting the allowed document types.

![Figure 4.13: Restrict Marketing folder to use specific document
types](../../images/05-document-type-restriction.png)

If workflow is enabled, you can specify different workflow definitions per
folder. Furthermore, you can specify different workflow definitions per document
type and per folder. You can set this by editing the folder. Then the UI will
look like this:

![Figure 4.14: Restrict Marketing folder to use specific document types and
workflow](../../images/05-document-type-workflow-restriction.png)

Document types are a powerful way to enforce rules for documents uploaded by
users. Next, we'll see a way to make it incredibly easy for users to access
documents stored in Liferay's Documents and Media repositories. 

### WebDAV access [](id=lp-6-1-ugen04-webdav-access-0)

Wouldn't it be great if you could access documents and folders belonging to
Liferay's Documents and Media library from your own machine's file manager? You
can, thanks to the Documents and Media library's WebDAV integration. WebDAV
stands for Web-based Distributed Authoring and Versioning. It's a set of methods
based on HTTP that allows users to create, edit, move or delete files stored on
web servers. WebDAV is supported by most major operating systems and desktop
environments, including Linux (both KDE and GNOME), Mac OS and Windows.

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

![Figure 4.15: Select *Access from Desktop* to get the WebDAV URL of a
folder.](../../images/webdav-access-from-desktop.png)

Copy the WebDAV URL. On Windows, right-click on My Computer and select *Map
Network Drive*. Select an unused drive, paste the WebDAV URL, and click
*Finish*. You're prompted to enter your Liferay credentials and then, provided
you have the required permissions, the *Image Gallery* folder appears. You can
now add, edit, move or delete files in this directory. 

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
and Media portlet. In the next section we'll review some ways to organize and
manage your assets so you're getting the most out of your content.

## Liferay Sync [](id=lp-6-1-ugen04-liferay-sync-0)

Liferay Sync, released in September 2012, is an add-on product for Liferay 6.1
CE and EE that synchronizes files between your Liferay server and users' desktop
and mobile environments. With Liferay Sync, your users can publish and access
shared documents and files from their native environments without using a
browser. Windows and Mac OS desktops and Android and iOS-based mobile platforms
are currently supported. As users add and collaborate on documents and files,
Liferay Sync automatically synchronizes them across all configured Sync clients.
Liferay Sync is fully integrated into the Liferay Platform so that features such
as authentication, versioning, workflow, and social collaboration function in
the supported environments. Liferay Sync stores files locally so that they're
always available, even when you're offline. It automatically synchronizes your
files upon reconnection.

### How does it work? [](id=lp-6-1-ugen04-how-does-it-work-0)

Liferay Sync manages documents and site information through Liferay 6.1's
built-in web services. Clients securely communicate to Liferay using
user-supplied credentials such that each user can only access those documents
and sites for which they have permission. Changes made through Liferay Sync are
immediately available to the rest of the Liferay Platform, including users
accessing Liferay through traditional web-based interfaces.

For desktop environments, a new folder structure is created and used for
synchronizing files. Files found therein can be treated as any ordinary file.
Credentials, sync frequency, and other folder options can be configured
in-client. Native desktop notification events keep you abreast of what Sync is
doing, and native menu and taskbar integration keep Sync controls within easy
reach.

Mobile environments are naturally dependent on the way in which documents are
handled. For Android and iOS, documents are maintained in a file list, and can
be viewed by clicking on the files themselves. External files accessible from
other apps can be "opened" using Liferay Sync, thereby dropping them into your
Sync folder and synchronizing them across other Sync clients. In iOS devices,
"pulling down" on the Sync file list forces a refresh. In Android, click on the
*Refresh* icon within the menu.

Liferay Sync is designed to work with both Liferay 6.1 Community Edition and
Enterprise Edition. Using Sync with Liferay CE limits users to syncing one site.
Using Sync with Liferay EE enables users to synchronize documents and files
across all the sites which they can access.

Liferay Sync is also designed to work with Liferay Social Office. You can sync
one site from Social Office CE as well as one site from Liferay Portal CE. If
you've installed Social Office CE on Liferay Portal EE, then you can sync any
site from Portal, but only one from Social Office. If you've installed Social
Office EE on Liferay Portal EE, then you can sync any and all sites.

### Installing Liferay Sync [](id=lp-6-1-ugen04-installing-liferay-sync-0)

For Windows or Mac OS, visit the Liferay Sync product page [Liferay Sync Product
Page](http://www.liferay.com/products/liferay-sync/features), and click *Get it
Now* (on the right-side navigation menu) to download the client application for
your desktop environment. For Windows, the client application installer should
be named `liferay-sync-<version>-<date>.exe`. For Mac OS, it should be
`liferay-sync-<version>-<date>.dmg`. Follow the on-screen instructions of the
installer wizard to configure your client to connect to an existing Liferay 6.1
deployment using your Liferay credentials.

#### Windows [](id=lp-6-1-ugen04-windows-0)

Upon launching the Windows application installer, you'll be prompted to choose
an installation location for Liferay Sync. Browse to an appropriate location on
your machine and click *Next*.

![Figure 4.16: Use the Liferay Sync Installation wizard to choose an
installation location.](../../images/liferay-sync-setup.png)

Leave the *Run Liferay Sync* button checked to automatically start Liferay Sync
after you click *Finish*.

![Figure 4.17: You'll see the following screen once Liferay Sync has been
installed. Click *Finish* to exit the installation
wizard.](../../images/liferay-sync-setup-complete.png)

The first time you run Liferay Sync, you'll have to enter some account
information. Sync needs to know where you'd like to locally store the files it's
supposed to sync with your Liferay server. And, of course, it needs to know your
server's URL and the account credentials with which it should authenticate.

![Figure 4.18: The first time you run Liferay Sync, you'll have to tell it how to
communicate with your Liferay
server.](../../images/liferay-sync-new-account-info.png)

The options for the Mac OS application installer are similar.

#### Mac OS [](id=lp-6-1-ugen04-mac-os-0)

Liferay Sync for Mac is packaged in a DMG file. Double-clicking on a DMG mounts
it as a disk image, and opens a window showing the contents of the image. To
install Sync, drag the Liferay Sync icon to the Applications folder. Once it's
installed, go to your Applications folder to run it.

![Figure 4.19: Drag the Liferay Sync icon to the Applications
folder.](../../images/liferay-sync-mac-install.png)

When you launch Liferay Sync, the first thing you need to do is provide it with
the URL for the Liferay server that you'll be using Sync with, along with your
Liferay credentials. After that, you'll need to run through the brief setup
process that was described above for Windows.

![Figure 4.20: You can provide the same information requested by the Windows
application installer.](../../images/liferay-sync-mac-preferences.png)

Once you've finished your configuration and have clicked *OK*, Liferay Sync
starts running in the background, and an icon appears in your top menu bar. If
you wish to change any of your settings, click the icon to open the Liferay Sync
menu and click on *Preferences*. Note that on Windows, the Sync menu says
*Properties*, not *Preferences*.

#### Mobile [](id=lp-6-1-ugen04-mobile-0)

For iOS, visit the App Store, search for Liferay, and install the Liferay Sync
App.

For Android, go to Google Play, search for Liferay, and install the Liferay Sync
App.

Once the mobile apps are installed, follow the on-screen instructions as below.

### Using Liferay Sync on the Desktop [](id=lp-6-1-ugen04-using-liferay-sync-0)

Once installed, you'll see a Liferay Sync icon in your taskbar whenever it's
running. A green checkmark means Liferay Sync has a working connection to your
Liferay server and is updating the files in your Sync folder according to the
interval you specified in the wizard. Click the Liferay Sync icon in your
taskbar to bring up the menu.

![Figure 4.21: Open the Liferay Sync taskbar menu to access the following
options.](../../images/liferay-sync-taskbar-menu.png)

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

![Figure 4.22: Open the Liferay Sync menu and select *Properties*
(*Preferences*, on Mac OS) to edit the settings you configured during
setup.](../../images/liferay-sync-properties.png)

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

*Sync Now* instructs Liferay Sync to check the Liferay server for updates
immediately.

*Pause Syncing* instructs Liferay Sync to suspend syncing until further notice.
If someone added a very large file to one of your shared folders that's taking a
very long time to sync, you might want to use this option and resume syncing at
a later time.

*About* displays Liferay Sync version information, copyright information, and a
link to Liferay's home page.

*Check for Updates* checks to see if a new version of Liferay Sync is available
from [liferay.com](liferay.com) and allows you to set whether or not Liferay
Sync should automatically check for updates.

#### Using your Sync folder [](id=lp-6-1-ugen04-using-your-sync-folder-0)

Once Liferay Sync has been configured and is running, any files you add to or
modify in your Sync folder are automatically detected and uploaded to your
Liferay server. Also, changes from other users are downloaded to your Sync
folder.

Liferay Sync handles deletions via a special `liferay-sync.deletions` file. This
mechanism prevents users from accidentally deleting shared files. When you
delete files from your Sync folder, a `.liferay-sync.deletions` file is created
there with the names of the files you deleted. This lets Liferay Sync know that
you don't want these files in your Sync folder, so it won't download them the
next time it syncs. Note that the files listed in your `.liferay-sync.deletions`
file are only local deletions. You can remove entries from your
`.liferay-sync.deletions` file to have Liferay Sync download them the next time
it syncs. Of course, you can use Sync for more than just local deletions. If you
have the required permissions, you can delete files from the server.

You can run through the following exercise to familiarize yourself with how to
create, edit, download, and upload files with Liferay Sync. First, open your
Liferay Sync folder in your file manager (use the *Open Sync Folder* option of
the Liferay Sync menu from the taskbar), and create a new file called
`README.txt`. Edit this file and enter the word *test*. Then use the *Sync now*
option of the Liferay Sync menu to make sure that your `README.txt` file gets
uploaded to your Liferay server. Next, check that you can access this file from
your Liferay site. Open your browser, navigate to your Liferay site, and sign in
with your Liferay account credentials. Then, navigate to the control panel. Make
sure the site you chose to sync with is selected in the context menu selector
and click on *Documents and Media*. You should see your `README.txt` file listed
there.

![Figure 4.23: You can access the same files from Liferay Sync that you can from
Liferay's web interface.](../../images/liferay-sync-documents-and-media.png)

Download the file (click the small triangle icon at the top right corner of the
*README.txt* icon and select *Download*) to a convenient location on your
machine and check that it still says *test*. Now open the `README.txt` file in
your Sync folder and edit it so that it says *second test*. Choose *Sync now*
again, and then go back to your browser and refresh your Documents and Media
page. Click on the *README.txt* icon, look at the information displayed to the
right, and you'll see that its version number has incremented.

![Figure 4.24: Updating a file through Liferay Sync increments the file's
version number. You can view a file's version number through the web
interface.](../../images/liferay-sync-README.png)

Download the file again, and you'll see that it now says *second test*--your
edit was uploaded to the server. You can be confident that this edit was also
downloaded by all other Liferay Sync clients connected to your site.

#### Demonstrating Liferay Sync Permissions [](id=lp-6-1-ugen04-demonstrating-liferay-sync-permissions-0)

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
as the *secretagent* and use *Go to* &rarr; *Control Panel*, select *Secret
Site* in the context menu selector, and click on *Documents and Media*. Then
upload the *secret.txt* document.

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

### Using Liferay Sync Mobile [](id=lp-6-1-ugen04-using-liferay-sync-mobile-0)

Once you've installed Liferay Sync on your Android or iOS mobile environment,
you'll be able to access the same functionality that's available when using Sync
on a desktop environment. However, the interface differs from that of the Sync
desktop clients.

#### Android [](id=lp-6-1-ugen04-using-liferay-sync-android-0)

After installing Liferay Sync for Android, an empty screen appears asking you
to set up the app. This screen appears whenever preferences are missing.

![Figure 4.25: First screen](../../images/liferay-sync-android-empty.png)

Touch the screen and it displays the *Settings* view. You can always go back
to *Settings* by clicking on the wrench icon at the top right corner of the
screen. 

![Figure 4.26: Android settings](../../images/liferay-sync-android-settings.png)

Enter your Liferay server credentials by filling in your *Login*, *Password*,
and *Server* information. Your *Login* is either your user account's email
address or screen name. Use the same credentials you use to log in to the portal
in a browser.  In the *Server* field, enter your portal's URL. In this example,
the server URL is *http://in.liferay.com*. Click the key icon on the top right
to test your connection and check if everything is correct.

Note for Gingerbread users: If you can't see some of the features described
here, click on the menu button and it displays all possible actions. These
include options to refresh, open the settings menu, upload files, take photos,
test your connection, etc.

![Figure 4.27: Gingerbread](../../images/liferay-sync-android-gingerbread.png)

After you have successfully tested your connection, hit the *back* button and
you'll see a list of Liferay sites you have access to.

![Figure 4.28: Sites](../../images/liferay-sync-android-sites.png)

You can browse the files of a site by tapping on any of them. This opens a list
of the folders and files belonging to the site that you have permission to view.

![Figure 4.29: Folder and files](../../images/liferay-sync-android-files-list.png)

From here, you can click on a folder and browse deeper into the folder hierarchy
or click the *back* button to navigate back to parent folders up to the initial
*Sites* list.

Long press on any folder or file to find a list of actions you can take on a
given entry: *Open*, *Download* or  *Delete*. 

If you click on *Open*, you'll see a list of file viewer apps. If you have no
app installed that can open the selected file type, you'll see a message
informing you that no viewer is available and you need to install an app that
can open it. The *Open* action downloads the file if there's no local copy.

If you click on *Download*, it downloads and overwrites the local file copy.

Clicking on *Delete* deletes the file/folder from the remote portal, and 
other users won't be able to view or download it.

![Figure 4.30: Actions](../../images/liferay-sync-android-delete.png)

Some actions are not related to a specific folder or file. You can find these
actions in the menu on the top action bar (Gingerbread users need to click on
the device menu button). Depending on the device screen width, some icons may
overflow to the three dots button on the right. Click on it to see all available
actions.

![Figure 4.31: More options](../../images/liferay-sync-android-more.png)

The *Refresh* button fetches and updates the list of folders and files that have
been changed in the portal.

The *Camera* button allows you to quickly take a picture and upload the image to
the current folder. The image file name is automatically generated with a
time stamp.

The *New Folder* button asks you for the name of the folder you want to create
in the portal.

![Figure 4.32: Upload local files](../../images/liferay-sync-android-upload.png)

The *Upload* button displays the types of local files you can upload to the
portal. Choosing *Image*, for example, shows all images that are stored
locally in your device. Once you choose the file and confirm, this file is
uploaded to the portal and is placed in the current folder. By default, you can
upload images, videos, and audio files. If you have installed an app on your
device that can open and browse any type of file, you will also see an option
called *Other files*.

#### iOS  [](id=lp-6-1-ugen04-using-liferay-sync-ios-0)

After installing the iOS app, click on *Settings* in the toolbar, enter your
Liferay server credentials by filling in your *Login*, *Password*, and *Server*
information. Your *Login* is either your user account's email address or screen
name, whichever you use to log in to the portal in a browser. In the *Server*
field, enter your portal's URL. In this example, the server URL is
*http://in.liferay.com*. Click on *Test Connection* to check if your
configuration is correct.

![Figure 4.33: iOS Settings](../../images/liferay-sync-ios-settings.png)

After testing your connection, click on *Guest* in the *Sites* section to see a
list of available sites. These are the sites you belong to; choose one by
clicking on its name. Click on *Back* to switch to a different site at
any time.

![Figure 4.34: Sites](../../images/liferay-sync-ios-sites.png)

When you select a site, you can browse its folders and files by clicking on the
*Documents* toolbar section. You'll see a list containing all the folders and
files that you have permission to access. From here, you can click on any folder
to go deeper into the folder hierarchy.

![Figure 4.35: Files list](../../images/liferay-sync-ios-files-list.png)
 
When you click on a file, this file is downloaded from the remote portal
and, if a previewer for this file type is available, you can view the contents
of the file. The next time you open a file, it won't download it again; instead,
it opens the local copy.

![Figure 4.36: Opening a file](../../images/liferay-sync-ios-open.png)

You can refresh the list by pushing it down. All files and folders that have
been changed in the portal are then updated.
 
![Figure 4.37: Refreshing](../../images/liferay-sync-ios-refresh.png)

To delete a file or folder from the portal, swipe right and click on the
*Delete* button.

![Figure 4.38: Deleting a file](../../images/liferay-sync-ios-delete.png)

If you want to upload an image or video to the portal, click the *Add* button at
the top right corner. You should see three options: 

*Take a photo or video* opens your camera app and lets you take a photo or
record a video and upload it.

*Choose Existing* allows you to upload an existing photo or video.

*Create New Folder* lets you type the name of the folder and creates it in the
portal.

![Figure 4.39: Upload photos and videos](../../images/liferay-sync-ios-more.png)

All downloaded images are stored in your device indefinitely. 

![Figure 4.40: Deleting local copies](../../images/liferay-sync-ios-cache.png)

If you want to delete downloaded files locally but don't want to remove them
from the portal, go to *Settings* and click on the *Clear Cache* button.

## Summary [](id=lp-6-1-ugen04-summary-0)

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
for Liferay 6.1 that allows your Liferay server to directly synchronize files on
users' desktop and mobile environments.
