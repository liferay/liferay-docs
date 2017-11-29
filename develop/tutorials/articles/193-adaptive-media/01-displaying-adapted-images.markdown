# Displaying Adapted Images in Your App [](id=displaying-adapted-images-in-your-app)

+$$$

**Note:** This documentation is currently in beta. 

$$$

Adaptive Media offers a convenient taglib to display adapted images in your 
apps. This taglib is in 
[the module `com.liferay.adaptive.media.image.taglib`](https://github.com/liferay/com-liferay-adaptive-media/tree/master/adaptive-media-image-taglib). 
The taglib has only one mandatory attribute: `fileVersion`. This attribute 
indicates the file version of the adapted image that you want to display. 
You can also add as many attributes as needed, such as `class`, `style`, 
`data-sample`, and so on. Any attributes you add are then added to the adapted 
images in the markup rendered by the taglib. 

To use the taglib, you must follow these steps: 

1.  Include the module taglib dependency in your project. If you're using 
    Gradle, for example, you must add the following line in your project's 
    `build.gradle` file: 

        provided group: "com.liferay", name: "com.liferay.adaptive.media.image.taglib", version: "1.0.0"

2.  Declare the taglib in your JSP:

        <%@ taglib uri="http://liferay.com/tld/adaptive-media-image" prefix="liferay-adaptive-media" %>

3.  Use the taglib wherever you want the adapted image to appear in your 
    portlet's JSP files: 

        <liferay-adaptive-media:img class="img-fluid" fileVersion="<%= fileEntry.getFileVersion() %>" />

For a real-world example, see 
[https://github.com/sergiogonzalez/adaptive-media-samples](https://github.com/sergiogonzalez/adaptive-media-samples). 
This is a sample portlet that can be added to a page to display all the adapted 
images stored in the current site's Documents and Media app. To test this sample 
app, be sure to add some Adaptive Media 
[image resolutions](/discover/portal/-/knowledge_base/7-0/adding-image-resolutions) 
and some images to your Documents and Media app. 

The Adaptive Media sample app looks like this. You can see different images, 
and if you look at the generated markup you can see that it uses the `<picture>` 
tag.

![Figure 1: The Adaptive Media sample app shows all the site's adapted images.](../../images/adaptive-media-sample.png)

Well done! Now you know how to display adapted images in your app. 

## Related Topics [](id=related-topics)

[Finding Adapted Images](/develop/tutorials/-/knowledge_base/7-0/finding-adapted-images)

[Changing Adaptive Media's Image Processing](/develop/tutorials/-/knowledge_base/7-0/changing-adaptive-medias-image-processing)

[Adapting Your Media Across Multiple Devices](/discover/portal/-/knowledge_base/7-0/adapting-your-media-across-multiple-devices)
