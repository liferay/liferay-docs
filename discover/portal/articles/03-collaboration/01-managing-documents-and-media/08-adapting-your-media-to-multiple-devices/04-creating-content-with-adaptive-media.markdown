# Creating Content with Adapted Images [](id=creating-content-with-adaptive-media-images)

Adaptive Media works in Blog entries and Web Content Articles directly out of 
the box. Just add your image(s) to your content and Adaptive Media prepares your 
images behind the scenes.

+$$$

**Note:** If Adaptive Media is uninstalled, the original images are displayed in 
the blog entries and web content articles as a fallback mechanism.

$$$

Adaptive Media delivers the latest available image resolutions in your content, 
even if the image resolution is added after the images are uploaded. This is 
possible because each image is identified with an HTML `data-fileentryid` 
attribute that is replaced with the latest image resolution when the user views 
the content.

## Including Adapted Images in Content [](id=including-adapted-images-in-content)

If adapted images are available, they are automatically used in the content when 
an image is included. Nothing more is required on your part. 
<!--You can add images in blog entries and web content articles by clicking the 
image button from the editor, and then selecting the image in the file selector. -->
Note that, when using the file selector to include an image, Adaptive Media only 
works for blog entries with images added from the *Blog Images*, *Documents and 
Media*, and *Upload* tabs. Likewise, Adaptive Media only works for web content 
articles with images added from the *Documents and Media* tab. Note that the 
*URL* tab doesn't work for Adaptive Media for either content because the image 
is linked directly from the URL and therefore provides no image file for 
Adaptive Media to copy. 

**Important:** Adaptive Media is only applied to the blog entry's content. 
Currently Adaptive Media does not apply to blog entry cover images.

When an image is dragged and dropped into a blog entry, it is automatically 
uploaded to the Blog Images repository, adapted, and included in the entry's 
content. You can see this by inspecting the HTML and checking that the 
`<img>` tag and the `data-fileentryid` attribute are added to the image.

+$$$

**Note:** Adaptive Media isn't supported for drag and drop in web content 
articles.

$$$

While editing a blog entry or web content article, the content's HTML uses the 
format described before: an `<img>` tag with a `data-fileentryid` attribute. 
When the blog entry or web content article is displayed in the Blogs application, 
or in the Asset Publisher application, the HTML is automatically replaced and 
looks similar to this:

    <picture>

        <source media="(max-width:850px)" 
        srcset="/o/adaptive-media/image/44147/med/photo.jpeg">

        <source media="(max-width:1200px) and (min-width:850px)" 
        srcset="/o/adaptive-media/image/44147/hd/photo.jpeg">

        <source media="(max-width:2000px) and (min-width:1200px)" 
        srcset="/o/adaptive-media/image/44147/ultra-hd/photo.jpeg">

        <img src="/documents/20140/0/photo.jpeg/1992-9143-85d2-f72ec1ff77a0">

    </picture>

The example above uses three different images with three different resolutions. 
Additionally, the original image is used as a fallback in case the adaptive 
media images are not available.

## Using Adapted Images in Advanced Web Content [](id=using-adapted-images-in-advanced-web-content)

To use adapted images in Advanced Web Content, you must include an image field 
in the web content's Structure. Then you can add the image field to the matching 
template by selecting it on the left side of the editor. Below is an example 
image field included in a template:

    <#if Imagecrrf.getData()?? && Imagecrrf.getData() !="">
      <img data-fileentryid="${Imagecrrf.getAttribute("fileEntryId")}" 
      alt="${Imagecrrf.getAttribute("alt")}" src="${Imagecrrf.getData()}" />
    </#if>

This snippet includes the `data-fileentryid` attribute to ensure that the image 
is dynamically replaced by Adaptive Media with the available image resolutions.

The image is included in the blog entry's content or web content article, and if 
you inspect the HTML in the code view of the editor you should see something 
like this:

    <img data-fileentryid="37308" 
    src="/documents/20143/0/photo.jpeg/85140258-1c9d-89b8-4e45-d79d5e262318?t=1518425" />

Note that there is an `<img>` tag with a `data-fileentryid` attribute. Adaptive 
Media uses the file entry ID to automatically replace the `<img>` element with a 
`<picture>` element containing all the available adapted images and the optimal 
device for each of them.

## Staging Adapted Images [](id=staging-adapted-images)

Adaptive Media is fully integrated with Staging and Export/Import functionality 
in @product@.

When a blog entry or a web content article is published from staging, Adaptive 
Media scans the content for images that can be adapted, adapts them, and 
publishes them to the live environment. The content is updated to match the new 
published adapted images in the live environment.

When blog entries or web content articles are exported, adapted images are 
exported in the LAR file as well. When a LAR file is imported that includes 
adapted images, the blog entry or web content article is updated to use the new 
imported adapted images.

Adapted images are not regenerated during the export/import process or staging 
publication process. Instead, Adaptive Media reuses the existing adapted images 
to improve the process's performance.
