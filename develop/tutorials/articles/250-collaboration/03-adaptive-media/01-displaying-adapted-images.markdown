# Displaying Adapted Images in Your App [](id=displaying-adapted-images-in-your-app)

To display 
[adapted images](/discover/portal/-/knowledge_base/7-1/adapting-your-media-across-multiple-devices) 
in your apps, Adaptive Media offers a convenient tag library in 
[the module `com.liferay.adaptive.media.image.taglib`](https://github.com/liferay/com-liferay-adaptive-media/tree/master/adaptive-media-image-taglib). 
This taglib only has one mandatory attribute: `fileVersion`. This attribute 
indicates the file version of the adapted image that you want to display. You 
can also add as many attributes as needed, such as `class`, `style`, 
`data-sample`, and so on. Any attributes you add are then added to the adapted 
images in the markup the taglib renders. 

This tutorial uses 
[the Adaptive Media Samples app](https://github.com/sergiogonzalez/adaptive-media-samples) 
to show you how to use this taglib. When added to a page, this app displays all 
the adapted images from the current site's Documents and Media app, provided 
that 
[Adaptive Media image resolutions](/discover/portal/-/knowledge_base/7-1/adding-image-resolutions) 
and Documents and Media images exist. 

Follow these steps to use the taglib: 

1.  Include the module taglib dependency in your project. If you're using 
    Gradle, for example, you must add the following line in your project's 
    `build.gradle` file: 

        provided group: "com.liferay", name: "com.liferay.adaptive.media.image.taglib", version: "1.0.0"

    For example, 
    [the Adaptive Media Samples app's `build.gradle` file](https://github.com/sergiogonzalez/adaptive-media-samples/blob/master/adaptive-media-sample-web/build.gradle) 
    contains this taglib. 

2.  Declare the taglib in your JSP:

        <%@ taglib uri="http://liferay.com/tld/adaptive-media-image" prefix="liferay-adaptive-media" %>

    For example, 
    [the Adaptive Media Samples app's `init.jsp`](https://github.com/sergiogonzalez/adaptive-media-samples/blob/master/adaptive-media-sample-web/src/main/resources/META-INF/resources/init.jsp) 
    declares all the taglibs the app needs. 

3.  Use the taglib wherever you want the adapted image to appear in your app's 
    JSP files: 

        <liferay-adaptive-media:img class="img-fluid" fileVersion="<%= fileEntry.getFileVersion() %>" />

    For example, 
    [the Adaptive Media Samples app's `view.jsp`](https://github.com/sergiogonzalez/adaptive-media-samples/blob/master/adaptive-media-sample-web/src/main/resources/META-INF/resources/view.jsp) 
    uses the taglib to display the adapted images in a grid with the `col-md-6` 
    [column container class](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-manually). 
    Looking at the markup the app generates, you can see that it uses the 
    `<picture>` tag as described in the article 
    [Creating Content with Adapted Images](/discover/portal/-/knowledge_base/7-1/creating-content-with-adapted-images). 

    ![Figure 1: The Adaptive Media Samples app shows all the site's adapted images.](../../../images/adaptive-media-sample.png)

Well done! Now you know how to display adapted images in your app. 

## Related Topics [](id=related-topics)

[Finding Adapted Images](/develop/tutorials/-/knowledge_base/7-1/finding-adapted-images)

[Changing Adaptive Media's Image Scaling](/develop/tutorials/-/knowledge_base/7-1/changing-adaptive-medias-image-scaling)

[Adapting Your Media Across Multiple Devices](/discover/portal/-/knowledge_base/7-1/adapting-your-media-across-multiple-devices)
