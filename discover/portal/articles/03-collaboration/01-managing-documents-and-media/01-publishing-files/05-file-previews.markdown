## Viewing File Previews

You've added several files to the *Spacey Party* folder. In the Document 
Library, each file has a file entry view. To open its file entry view, click the 
file's name. The screen's central viewing area displays a preview image of the 
file. If the file is an image file, its image is displayed. If a preview plugin 
for the file type is installed, the plugin displays an image (e.g., the opening 
scene of a video file or a presentation's first slide) for the file. If there 
are no preview plugins for the file, @product@ displays a generic image based on 
the file's type. Let's take a moment and consider file preview plugins and some 
of the powerful features they offer. 

Whenever possible, @product@ generates previews of documents added to the 
Document Library. Out of the box, @product@ only ships with Java-based APIs to
generate previews for documents. The only tool available that is 100% Java and
has a compatible license to be distributed with @product@ is PDFBox. If you 
upload a PDF file to Documents and Media, @product@ generates a preview for the 
PDF in a separate thread. This process may last only a few seconds for a small 
file. The larger the file, the longer it takes.

While a default implementation of image generation for document previews and
thumbnails is provided via PDFBox, you must install and configure some
additional tools to harness the full power of @product@'s Documents and Media
library. These tools include [*OpenOffice*](http://www.openoffice.org) or
[*LibreOffice*](http://www.libreoffice.org),
[*ImageMagick*](http://www.imagemagick.org), which requires
[*Ghostscript*](http://www.ghostscript.com), and
[*Xuggler*](http://www.xuggle.com/xuggler). With these tools installed and
configured, Documents and Media content is displayed using a customized viewer
depending on the type of content. Configuring @product@ to use OpenOffice or
LibreOffice in server mode allows you to generate thumbnails and previews for
supported file types (.pdf, .docx, .odt, .ppt, .odp, etc.), lets you view
documents in your browser, and lets you convert documents. ImageMagick allows
for faster and higher-quality previews and conversions. Xuggler allows for audio
and video previews, lets you play audio and video files in your browser, and
extracts thumbnails from video files. You can configure the tools via portal
properties you can set in the Control Panel's Server Administration screen or in
a `portal-ext.properties` file. To learn how to use these tools, refer to
[Configuring @product@](/discover/portal/-/knowledge_base/7-0/configuring-liferay).

You can view a document with a customized viewer that allows you to navigate
through the different pages of the document and read its content. In addition,
you can view a multimedia document (audio or video) and play it online. If the
browser supports HTML5, it uses the browser's native player. Otherwise it falls
back to a Flash player.

![Figure 7: By leveraging services like Xuggler and ImageMagick, you can watch a video clip or even view a slideshow inside @product@'s Documents and Media app.](../../../../images/dm-file-entry-video-preview.png)

Document previews are powerful and help users browse media more successfully to
find what they're looking for.

Above the file viewing area are the file's icon and the file entry's name,
author, upload timestamp, and rating. You can rate the file too. The comments
area below the file viewing area lets you comment on the file, subscribe to
comments, and reply to comments.

A file's options are accessible from the *Options* icon
(![Options](../../../../images/icon-options.png)) at the top of the screen. Here
are the file options:

**Download**: Downloads the file to your device.

**Edit**: Lets you modify the file's name, description, document type,
categorization, and [related 
assets](/discover/portal/-/knowledge_base/7-0/defining-content-relationships).
You can even upload a new file to replace it. Note, modifying the file
increments its version.

**Edit With Image Editor**: Opens the Image Editor to modify the image. The 
Image Editor is explained in [Editing 
Images](/discover/portal/-/knowledge_base/7-0/editing-images).

**Move**: Relocate the file to a different parent folder.

**Checkout/Checkin**: Checkout prevents others from modifying the document while
you are working. Other users can still view the current version of the document
if they have permission. You can check in the document when you're done working.

**Permissions**: Lets you specify which actions each role can perform on the
file. You can granted a role permission to perform the following actions.

- **Update**: Edit, checkout, and checkin the file.

- **Override Checkout**: Checkout the file, revoking the file's current
checkout if it is checked out.

- **Permissions**: View and configure this file's permissions.

- **Delete**: Move the file to the Recycle Bin.

- **View**: View the file.

- **Update Discussion**: Edit another user's comment on the file.

- **Delete Discussion**: Delete any comments on the file.

- **Add Discussion**: Comment on the file. 

**Move to the Recycle Bin**: Remove the file from the Documents and Media
library to the Recycle Bin. 

Click the *Information* icon 
(![**i**](../../../../images/icon-information.png)) to view the file entry 
details. The top portion of this area lists the file's version number, status, 
modification timestamp, and name of the user who last modified it. Next are 
links to download the file and links to show its URL and WebDAV URL. You can 
specify the WebDAV URL as the file name when opening the document from a desktop 
environment.

The section *Automatically Extracted Metadata* lists any and all metadata that's
been extracted automatically from the file. When adding new documents or viewing
existing documents, a process is triggered automatically that extracts the
file's metadata. The library used by this process is TIKA and it's already
included in @product@ out of the box. Depending on your file's type and the
metadata written with the file, you can find out all kinds of details. In the
case of audio or video files, their duration is displayed.

To view the *Version History* click the drown-down arrow next to *Details* and 
select *Versions*. This section lists the different versions of the file and 
lets you view, download, remove, and revert to specific file versions. File 
version history actions are explained in the 
[Collaborating on Files](/discover/portal/-/knowledge_base/7-0/collaborating-on-files) 
guide.

Let's review what you've done so far. First, you created a role to manage a
specific set of files for your site. You assigned users to the role and created
a Documents and Media folder named *Resort Guest Media* for them to add and
organize files. Then as a member of the role, you added a subfolder named
*Spacey Party* and added files to it. And just now, you viewed individual file
entry information and actions. 

Next, you'll learn how to edit images in @product@. 
