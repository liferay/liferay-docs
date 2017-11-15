# Creating Content with Adapted Images [](id=creating-content-with-adaptive-media-images)

Adaptive Media is mostly transparent for creators of blog entries and web 
content. Once a normal image is added to the content, the app works behind the 
scenes to deliver the appropriate adapted image for the device consuming that 
content. Content creators select a normal image when adding it to their 
content--they don't have to select an adapted image (adapted images can't be 
selected manually). There are, however, a few things to be aware of when 
authoring content that may contain adapted images. 

First, you should know how Adaptive Media inserts adapted images in content. 
Adaptive Media identifies each adapted image in the content's HTML with a 
`data-fileentryid` attribute that is replaced with the latest adapted image when 
the user views the content. This lets Adaptive Media deliver the latest adapted 
images to your content, even if the content existed prior to those images. 

+$$$

**Note:** If Adaptive Media is uninstalled, the original images are displayed in 
the blog entries and web content articles as a fallback mechanism.

$$$

## Including Adapted Images in Content [](id=including-adapted-images-in-content)

Since Adaptive Media delivers the adapted images behind the scenes, content 
creators should add normal images to 
[blog entries](/discover/portal/-/knowledge_base/7-0/publishing-blogs) 
and 
[web content](/discover/portal/-/knowledge_base/7-0/creating-web-content) 
as usual: by clicking the image button in the editor and then selecting the 
image in the file selector. 

However, there are some important caveats for this. When using the file selector 
to include an image, Adaptive Media only works for blog entries with images 
added from the *Blog Images*, *Documents and Media*, and *Upload* tabs. 
Likewise, Adaptive Media only works for web content articles with images added 
from the file selector's *Documents and Media* tab. Adaptive Media doesn't work 
with images added from the file selector's *URL* tab, for either content type. 
This is because the image is linked directly from the URL and therefore provides 
no image file for Adaptive Media to copy. 

Adaptive Media has some additional limitations with blogs and web content. 
Adapted images can only be applied to a blog entry's content--cover images are 
excluded. Also, Adaptive Media doesn't deliver adapted images for images added 
to web content articles via drag and drop. 

Adaptive Media does, however, work for images added to a blog entry via drag and 
drop. In this case, the image is automatically uploaded to the Blog Images 
repository, adapted, and then included in the blog entry's content. You can see 
this by inspecting the HTML and checking that the image contains the `<img>` tag 
and `data-fileentryid` attribute. 

Note that you can see the `<img>` tag and `data-fileentryid` attribute in the 
HTML of a blog entry or a web content article while you're writing it. When the 
content is displayed, the HTML is automatically replaced and looks similar to 
this: 

    <picture>

        <source media="(max-width:850px)" 
        srcset="/o/adaptive-media/image/44147/med/photo.jpeg">

        <source media="(max-width:1200px) and (min-width:850px)" 
        srcset="/o/adaptive-media/image/44147/hd/photo.jpeg">

        <source media="(max-width:2000px) and (min-width:1200px)" 
        srcset="/o/adaptive-media/image/44147/ultra-hd/photo.jpeg">

        <img src="/documents/20140/0/photo.jpeg/1992-9143-85d2-f72ec1ff77a0">

    </picture>

This example uses three different images, each with a different resolution. A 
`source` tag defines each of these images. Also note the original image (`img`) 
is used as a fallback in case the adapted images aren't available. 

## Using Adapted Images in Structured Web Content [](id=using-adapted-images-in-structured-web-content)

To use adapted images in 
[structured web content](/discover/portal/-/knowledge_base/7-0/designing-uniform-content), 
content creators must manually include an image field in the web content's 
structure. Then they can reference that image field in the matching template by 
selecting it on the left side of the editor. Here's an example snippet of an 
image field named `Imagecrrf` included in a template: 

    <#if Imagecrrf.getData()?? && Imagecrrf.getData() !="">
      <img data-fileentryid="${Imagecrrf.getAttribute("fileEntryId")}" 
      alt="${Imagecrrf.getAttribute("alt")}" src="${Imagecrrf.getData()}" />
    </#if>

This snippet includes the `data-fileentryid` attribute to ensure that Adaptive 
Media replaces the image with an adapted image. If you inspect the resulting web 
content's HTML in the editor's code view, you should see something like this:

    <img data-fileentryid="37308" 
    src="/documents/20143/0/photo.jpeg/85140258-1c9d-89b8-4e45-d79d5e262318?t=1518425" />

Note that there's an `<img>` tag with a `data-fileentryid` attribute. Adaptive 
Media uses the file entry ID to automatically replace the `<img>` element with a 
`<picture>` element that contains the available adapted images for each 
resolution (see the `<picture>` example above).

## Staging Adapted Images [](id=staging-adapted-images)

Adaptive Media is fully integrated with @product@'s 
[content staging](/discover/portal/-/knowledge_base/7-0/staging-content-for-publication) 
and 
[export/import](/discover/portal/-/knowledge_base/7-0/exporting-importing-app-data) 
functionality. Adaptive Media includes adapted images in staged content when 
published, and can update those images to match any new resolutions. 

Similarly, when content that contains adapted images is exported, Adaptive Media 
exports those images in the LAR file. That LAR file can then be imported to 
restore or transfer that content, along with its adapted images.

Note that Adaptive Media doesn't regenerate adapted images during export/import 
or the publication of staged content. To improve performance, Adaptive Media 
instead reuses the existing adapted images. 

Awesome! Now you know how create content that contains adapted images. You also 
know how Adaptive Media includes adapted images in the content's HTML. 
