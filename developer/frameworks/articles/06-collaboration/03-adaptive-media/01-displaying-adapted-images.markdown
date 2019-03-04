---
header-id: displaying-adapted-images-in-your-app
---

# Displaying Adapted Images in Your App

Follow these steps to display adapted images in your app with the Adaptive Media 
[taglib](https://github.com/liferay/com-liferay-adaptive-media/tree/master/adaptive-media-image-taglib). 
For more information on this taglib, see the 
[Adaptive Media introduction](liferay.com). 

1.  Include the module taglib dependency in your project. If you're using 
    Gradle, for example, you must add the following line in your project's 
    `build.gradle` file: 

        provided group: "com.liferay", name: "com.liferay.adaptive.media.image.taglib", version: "1.0.0"

2.  Declare the taglib in your JSP:

        <%@ taglib uri="http://liferay.com/tld/adaptive-media-image" prefix="liferay-adaptive-media" %>

3.  Use the taglib wherever you want the adapted image to appear in your app's 
    JSP files: 

        <liferay-adaptive-media:img class="img-fluid" fileVersion="<%= fileEntry.getFileVersion() %>" />

    For example, this `view.jsp` uses the taglib to display the adapted images 
    in a grid with the `col-md-6` 
    [column container class](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-manually). 
    Looking at the markup the app generates, you can see that it uses the 
    `<picture>` tag as described in the article 
    [Creating Content with Adapted Images](/discover/portal/-/knowledge_base/7-1/creating-content-with-adapted-images). 

        <%@ include file="/init.jsp" %>

        <div class="container">

        <%
        String[] mimeTypes = {"image/bmp", "image/gif", "image/jpeg", "image/pjpeg", "image/png", "image/tiff", "image/x-citrix-jpeg", "image/x-citrix-png", "image/x-ms-bmp", "image/x-png", "image/x-tiff"};

        List<FileEntry> fileEntries = DLAppServiceUtil.getFileEntries(scopeGroupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, mimeTypes);

        int columns = 0;

        for (FileEntry fileEntry : fileEntries) {
                boolean row = ((columns % 2) == 0);
        %>

                <c:if test="<%= row %>">
                        <c:if test="<%= columns != 0 %>">
                                </div>
                        </c:if>

                        <div class="row">
                </c:if>

                <div class="col-md-6">
                        <liferay-adaptive-media:img class="img-fluid" fileVersion="<%= fileEntry.getFileVersion() %>" />
                </div>

                <%
                columns++;
        }
        %>

        </div>

    ![Figure 1: The Adaptive Media Samples app shows all the site's adapted images.](../../../images/adaptive-media-sample.png)
