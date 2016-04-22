# Rendering Web Content in Your Android App

Liferay represents 
[web content](/discover/portal/-/knowledge_base/6-2/web-content-management) 
articles as `JournalArticle` entities. Liferay Screens provides several ways to 
render these entities in your apps. 

The simplest way to display a `JournalArticle`'s HTML in your app is to use 
[Web Content Display Screenlet](/develop/reference/-/knowledge_base/6-2/webcontentdisplayscreenlet-for-android). 
This screenlet is very powerful and allows several complex use cases to fit your 
needs. This tutorial illustrates some of this Screenlet's most common use cases. 

## Retrieving Basic Web Content

Web Content Display Screenlet's simplest use case is to render a 
`JournalArticle`'s HTML in an 
[Android `WebView`](http://developer.android.com/guide/webapps/webview.html). 
Simply provide the `JournalArticle`'s `articleId` in the Screenlet XML, and the 
Screenlet takes care of the rest (including decorating itself with the CSS 
needed to render it in a small display). The following Screenlet XML shows this: 

    <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:articleId="YOUR_ARTICLE_ID" />

To render the content *exactly* as it appears on your mobile site, however, you 
must provide the CSS inline or use a template. The HTML returned isn't aware of 
a Liferay instance's global CSS. 

You can also use a listener to modify the HTML, as explained in the 
[screenlet reference](/develop/reference/-/knowledge_base/6-2/webcontentdisplayscreenlet-for-android).

In the default security policy, an Android `WebView` doesn't execute a page's 
JavaScript. You can enable such JavaScript execution by setting the 
`javascriptEnabled` property via XML:

    <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:articleId="YOUR_ARTICLE_ID"
        app:javascriptEnabled="true" />

Alternatively, you can set this property in your app's fragment or activity 
class that contains the Screenlet: 

    ...
    screenlet.setJavascriptEnabled(true);
    ...

You can also use the `isJavascriptEnabled()` method to check this property's 
setting. 

As you can see, this is all straightforward. What could go wrong? Famous last 
words. A common mistake is to use the default `groupId` instead of the one for 
the site that contains your `JournalArticle` entities. 

If you need to use a default `groupId` in the rest of your app, but render 
another site's HTML, you can set the Web Content Display screenlet's `groupId` 
with the `app:groupId` attribute. You can alternatively use the `setGroupId` 
method in the activity or fragment code that uses the Screenlet. 

## Using Templates

Web Content Display Screenlet can also use 
[templates](/discover/portal/-/knowledge_base/6-2/advanced-content-with-structures-and-templates) 
to render `JournalArticle` entities. For example, your Liferay instance may have 
a custom template specifically designed to display content on mobile devices. 

To use a template, specify its ID in the Screenlet XML's `templateId` property:

    <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:articleId="YOUR_ARTICLE_ID"
        app:templateId="YOUR_TEMPLATE_ID" />

## Using Structures
<!-- 
The code in this section is in the following test-app layout file:

https://github.com/liferay/liferay-screens/blob/develop/android/samples/test-app/src/main/res/layout/web_content_display_structured.xml
* change link to master branch instead of develop for publication
-->

<!-- 
question: What's the purpose of using a structure in Web Content Display 
Screenlet? Structures are for creating web content, not displaying it. Web 
Content Display Screenlet only displays web content. 
-->

    <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        liferay:articleId="YOUR_ARTICLE_ID"
        liferay:layoutId="YOUR_LAYOUT_ID"
        liferay:structureId="YOUR_STRUCTURE_ID" />

    <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        liferay:articleId="YOUR_ARTICLE_ID"
        liferay:labelFields="YOUR_LABELS"
        liferay:layoutId="@layout/webcontentdisplay_structured_default"
        liferay:structureId="YOUR_STRUCTURE_ID" />

    <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        liferay:articleId="YOUR_ARTICLE_ID"
        liferay:labelFields="@string/liferay_article_structured_label_fields_first_field"
        liferay:layoutId="@layout/webcontentdisplaystructured_example"
        liferay:structureId="YOUR_STRUCTURE_ID" />

## Displaying a list

<!-- 
This code is in the following test-app layout file:

https://github.com/liferay/liferay-screens/blob/develop/android/samples/test-app/src/main/res/layout/web_content_display_list.xml
* change link to master branch instead of develop for publication
-->

    <com.liferay.mobile.screens.webcontent.list.WebContentListScreenlet
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:folderId="YOUR_FOLDER_ID"
        app:labelFields="YOUR_LABELS" />

<!-- question: are examples/text coming for the below items? -->
*WebContentDisplayList*

*structure*

*localized fields*

## Displaying assets 

*assetlistscreenlet*

## Related Topics [](id=related-topics)

[Web Content Display Screenlet for Android](/develop/reference/-/knowledge_base/6-2/webcontentdisplayscreenlet-for-android)