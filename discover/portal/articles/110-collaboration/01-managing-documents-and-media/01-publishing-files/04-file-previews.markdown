# Viewing File Previews [](id=viewing-file-previews)

File previews help users browse and find media efficiently. To view a preview of 
a file, click the file's name in the Document Library. If the file is an image,
the image appears. If an app is installed that can render a preview of the file
type, a representative image of the file appears (e.g., the opening frame of
a video file or a presentation's first slide). If there are no such preview apps
for the file, a generic image based on the file type appears. 

![Figure 1: File previews let you view and manage a file.](../../../../images/dm-file-entry-details.png)

## File Preview Apps [](id=file-preview-apps)

Whenever possible, @product@ generates previews of documents added to the
Document Library. Out of the box, Java-based APIs generate previews. The only
tool available that is 100% Java and has a compatible license to be distributed
with @product@ is 
[PDFBox](https://pdfbox.apache.org). 
A separate thread generates a preview for PDFs when uploaded. This process may
last only a few seconds for a small file. The larger the file, the longer it
takes. 

While PDFBox provides a default implementation of image generation for document 
previews and thumbnails, you must install and configure additional tools to
harness the full power of document previews. These tools include: 

-   [OpenOffice](http://www.openoffice.org) or
    [LibreOffice](http://www.libreoffice.org): 
    Using one of these in server mode lets you generate thumbnails and previews 
    for supported file types (`.pdf`, `.docx`, `.odt`, `.ppt`, `.odp`, etc.), 
    view documents in your browser, and convert documents. 

-   [ImageMagick](http://www.imagemagick.org) (also requires
    [Ghostscript](http://www.ghostscript.com)): 
    Enables faster and higher-quality previews and conversions. 

-   [Xuggler](http://www.xuggle.com/xuggler): 
    Enables audio and video previews, lets you play audio and video files in 
    your browser, and extracts thumbnails from video files. 

After installing these tools, you can configure them via portal properties in 
the Control Panel's Server Administration screen, or in a 
`portal-ext.properties` file. To learn how to use these tools, refer to 
[Configuring @product@](/discover/portal/-/knowledge_base/7-1/configuring-liferay). 

With these tools installed and configured, a customized viewer displays 
Documents and Media content, depending on the content type. For example, you can 
view a document with a customized viewer that lets you navigate through the 
document's pages. You can also view and play multimedia documents (audio or 
video). If the browser supports HTML5, the viewer uses the browser's native 
player. Otherwise it falls back to a Flash player. 

## Managing Files [](id=managing-files)

You can also manage a file from its preview. The file's icon, name, author, 
upload timestamp, and rating appear above the preview area. The comments area 
(below the preview area) lets you comment on and subscribe to comments on the 
file. 

You can access more options from the *Options* menu 
(![Options](../../../../images/icon-options.png)) at the top-right of the 
screen. Here are the file options: 

**Download**: Download the file. 

**Edit**: Modify the file's name, description, document type, 
categorization, and 
[related assets](/discover/portal/-/knowledge_base/7-1/defining-content-relationships).
You can even upload a new file to replace it. Note that modifying the file
increments its version. 

**Edit With Image Editor**: Edit the image in the Image Editor. The Image 
Editor is explained in 
[Editing Images](/discover/portal/-/knowledge_base/7-1/editing-images). 

**Move**: Relocate the file to a different parent folder. 

**Checkout/Checkin**: Checkout prevents others from editing the document 
while you are working on it. Other users can still view the current version 
of the document, if they have permission. You can check in the document when 
you're done with it. 

**Permissions**: Specify which actions each role can perform on the file. 

**Move to Recycle Bin**: Move the file from the Documents and Media library 
to the Recycle Bin. 

Click the *Info* icon 
(![**i**](../../../../images/icon-information.png)) to view information about 
the file in an info panel that slides out from the right. A selector menu at the 
top of the info panel is set to *Details* by default. This shows the following 
information: 

**Version:** The file's version.

**Status:** The file's workflow status (e.g., Approved).

**Created:** The user that created the file, and when it was created. 

**Modified:** The user that last modified the file, and when it was last 
modified.

**Download:** A link to download the file. 

**URL:** A URL to access the file in the Document Library.

**WebDAV URL:** A WebDAV URL for accessing the file via a desktop.

**Automatically Extracted Metadata:** Any and all metadata automatically 
extracted from the file. When adding new documents or viewing existing 
documents, a process is triggered automatically that extracts the file's 
metadata. The library used by this process is TIKA and it's included out of the
box. Depending on your file's type and the metadata written with the file, you
can find out all kinds of details. In the case of audio or video files, the
media's duration is displayed.

To instead view the file's version history, select *Versions* from the selector 
menu. The info panel then changes to list the different versions of the file and 
lets you view, download, remove, and revert to specific file versions. File 
version history actions are explained in the guide 
[Collaborating on Files](/discover/portal/-/knowledge_base/7-0/collaborating-on-files). 
