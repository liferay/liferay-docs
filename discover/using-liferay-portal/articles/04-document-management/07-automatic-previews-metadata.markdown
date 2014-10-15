# Automatic Previews and Metadata [](id=automatic-previews-and-metadata)

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

![Figure 4.13: Previews in Documents and Media](../../images/05-previews.jpg)

You can view a document with a customized viewer that allows you to navigate
through the different pages of the document and read its content.

![Figure 4.14: Viewing an office document](../../images/05-document-preview.jpg)

You can view a multimedia document (audio or video) and play it online. If the
browser supports HTML5, it uses the native player of the browser. Otherwise it
falls back to a Flash player.

![Figure 4.15: Playing a video](../../images/05-video-preview.png)

Document previews are powerful and help users browse media more successfully to
find what they're looking for. 

## Automatic Extraction of RAW Metadata [](id=automatic-extraction-of-raw-metadata)

When adding new documents or viewing existing documents, a process is triggered
automatically that extracts the file's metadata. The library used by this
process is TIKA and it's already included in Liferay out of the box.

You can see the metadata when viewing the document, in the right side of the
window.

## Document Type Restrictions and Workflow per Folder [](id=document-type-restrictions-and-workflow-per-folder)

You can force users to add only certain document types to a folder. By default,
child folders inherit the restrictions of their parent folder. You can change
this behavior by editing the folder and selecting the allowed document types.

![Figure 4.16: Restrict Marketing folder to use specific document types](../../images/05-document-type-restriction.png)

If workflow is enabled, you can specify different workflow definitions per
folder. Furthermore, you can specify different workflow definitions per document
type and per folder. You can set this by editing the folder. Then the UI will
look like this:

![Figure 4.17: Restrict Marketing folder to use specific document types and workflow](../../images/05-document-type-workflow-restriction.png)

Document types are a powerful way to enforce rules for documents uploaded by
users. Next, we'll see a way to make it incredibly easy for users to access
documents stored in Liferay's Documents and Media repositories. 

## WebDAV Access [](id=webdav-access)

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

![Figure 4.18: Select *Access from Desktop* to get the WebDAV URL of a folder.](../../images/webdav-access-from-desktop.png)

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
