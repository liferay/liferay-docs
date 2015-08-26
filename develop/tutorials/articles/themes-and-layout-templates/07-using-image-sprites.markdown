# Using Image Sprites [](id=using-image-sprites)

You might already know that every image on a site generates an HTTP request.
Page load time is impacted every time the browser has to negotiate another HTTP
request. The more image files a page has, the slower the page loads. The way to
combat this is to combine all the images into one larger image sprite. This way,
there's only one HTTP request for images, and you can refer to the individual
images by their coordinates in the sprite. This technique helps you avoid
traffic jams so that your site loads icons as efficiently as possible. 

Normally, you have to create these sprites on your own, but Liferay can generate
them for you automatically. If this has piqued your interest, it's time to learn
how! 

1.  Create image `.png` files for your icons and small images. For an image
    to qualify as an image sprite in Liferay, its dimensions must not exceed
    16 pixels by 16 pixels and its file size must not exceed 10240 bytes. Image
    files that don't meet this criteria can't be used as sprite images and are
    processed, instead, as individual image files. 

    **Portlet image sprite files** belong in the portlet's `docroot/icons/`
    folder. 

    **Theme image sprite files** that replace existing image files must use the
    same file name as the original and use the same file path, but belong in the
    `/docroot/_diffs/` folder. 

2.  To display the images in JSPs, you can reference them in 
    [`liferay-ui:icon`](http://docs.liferay.com/portal/6.2/taglibs/liferay-ui/icon.html)
    taglib tags. All of Liferay's taglibs are described
    [here](https://docs.liferay.com/portal/6.2/taglibs/). 

3.  Deploy your plugin.

Your plugin now leverages images as image sprites!

Note that to use image sprites outside of Liferay, you'd have to construct your
own combined image sprite file and use CSS styles to specify each image's
location in the combined image sprite file. Liferay, however, takes care of the
heavy lifting: it generates a `_sprite.png` file composed of the plugin's images
that meet Liferay's image sprite criteria and it generates a
`_sprite.properties` file that specifies each image's coordinates within the
`_sprite.png` file. 

Image sprite files are generated and deployed to the following locations:

- Theme plugin sprite files are stored in file
`[PORTLET-WEB-CONTEXT]/sprite/images/<IMAGE-FOLDER-NAME>/_sprite.png`
- Custom portlet plugin sprite images are stored in file
`[PORLTET-WEB-CONTEXT]/sprite/icons/_sprite.png`.
- Built-in portlet sprite files are stored in file
`[PORTAL-WEB-CONTEXT]/sprite/html/<IMAGE-FOLDER-PATH>/_sprite.png`

You've mastered the art of making sprites so that you can kick your portal's
design and performance up a notch--BAM! 

**Related Topics**

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

[Customizing Liferay Portal](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[MVC Portlets](/develop/tutorials/-/knowledge_base/6-2/developing-jsp-portlets-using-liferay-mvc)

[User Interfaces with the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[User Interfaces with the AlloyUI Taglib](/develop/tutorials/-/knowledge_base/6-2/alloyui)
