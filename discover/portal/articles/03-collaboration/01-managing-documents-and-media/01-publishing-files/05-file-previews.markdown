## Viewing File Previews

File previews help users browse and find media efficiently. To view a preview of 
a file, click the file's name in the Document Library. If the file is an image, 
the image is displayed. If a preview app for the file type is installed, it 
displays an image (e.g., the opening scene of a video file or a presentation's 
first slide) for the file. If there are no preview apps for the file, @product@ 
displays a generic image based on the file's type. 

![Figure 6: A file's entry view lets you act on the file, preview it, and inspect its details. If you've installed an appropriate preview plugin for a file, its preview image displays in the preview area. By default, @product@ can preview many image types.](../../../../images/dm-file-entry-details.png)

## File Preview Apps

Whenever possible, @product@ generates previews of documents added to the 
Document Library. Out of the box, @product@ only ships with Java-based APIs to
generate such previews. The only tool available that is 100% Java and has a 
compatible license to be distributed with @product@ is PDFBox. If you upload a 
PDF file to Documents and Media, @product@ generates a preview for the 
PDF in a separate thread. This process may last only a few seconds for a small 
file. The larger the file, the longer it takes. 

While a default implementation of image generation for document previews and
thumbnails is provided via PDFBox, you must install and configure some
additional tools to harness the full power of @product@'s Documents and Media
library. These tools include: 

-   [OpenOffice](http://www.openoffice.org) or
    [LibreOffice](http://www.libreoffice.org): 
    Using one of these in server mode lets you generate thumbnails and previews 
    for supported file types (`.pdf`, `.docx`, `.odt`, `.ppt`, `.odp`, etc.), 
    view documents in your browser, and convert documents. 

-   [ImageMagick](http://www.imagemagick.org) (which requires
    [Ghostscript](http://www.ghostscript.com)): 
    Enables faster and higher-quality previews and conversions. 

-   [*Xuggler*](http://www.xuggle.com/xuggler): 
    Enables audio and video previews, lets you play audio and video files in your browser, and extracts 
    thumbnails from video files. 

With these tools installed and configured, Documents and Media content is 
displayed using a customized viewer depending on the type of content. You can 
configure the tools via portal properties in the Control Panel's Server 
Administration screen or in a `portal-ext.properties` file. To learn how to use 
these tools, refer to 
[Configuring @product@](/discover/portal/-/knowledge_base/7-1/configuring-liferay). 

You can view a document with a customized viewer that lets you navigate through 
the document's pages. You can also view and play multimedia documents (audio or 
video). If the browser supports HTML5, the viewer uses the browser's native 
player. Otherwise it falls back to a Flash player. 

## Managing Files

You can also manage a file from its preview. The file's icon, name, author, 
upload timestamp, and rating appear above the preview. The comments area below 
the preview lets you comment on the file and subscribe to comments.

You can access more options from the *Options* menu 
(![Options](../../../../images/icon-options.png)) at the top of the screen. Here
are the file options:

-   **Download**: Downloads the file to your device.

-   **Edit**: Lets you modify the file's name, description, document type,
    categorization, and 
    [related assets](/discover/portal/-/knowledge_base/7-1/defining-content-relationships).
    You can even upload a new file to replace it. Note, modifying the file
    increments its version.

-   **Edit With Image Editor**: Opens the Image Editor to modify the image. The 
    Image Editor is explained in 
    [Editing Images](/discover/portal/-/knowledge_base/7-0/editing-images).

-   **Move**: Relocate the file to a different parent folder.

-   **Checkout/Checkin**: Checkout prevents others from modifying the document 
    while you are working. Other users can still view the current version of the 
    document if they have permission. You can check in the document when you're 
    done working.

-   **Permissions**: Lets you specify which actions each role can perform on the
    file. You can granted a role permission to perform the following actions.

-   **Move to the Recycle Bin**: Remove the file from the Documents and Media
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
