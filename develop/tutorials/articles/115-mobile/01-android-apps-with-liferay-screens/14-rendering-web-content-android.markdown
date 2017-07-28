# Rendering Web Content in Your Android App [](id=rendering-web-content-in-your-android-app)

Liferay represents 
[web content](/discover/portal/-/knowledge_base/7-0/creating-web-content) 
articles as `JournalArticle` entities. Liferay Screens provides several ways to 
render these entities in your apps. 

The simplest way to display a `JournalArticle`'s HTML in your app is to use 
[Web Content Display Screenlet](/develop/reference/-/knowledge_base/7-0/webcontentdisplayscreenlet-for-android). 
This Screenlet is very powerful and supports several complex use cases to fit 
your needs. You can also use 
[Web Content List Screenlet](/develop/reference/-/knowledge_base/7-0/web-content-list-screenlet-for-android) 
to display lists of web content articles. This tutorial shows you how to use 
both Screenlets to display web content in your apps. 

## Retrieving Basic Web Content [](id=retrieving-basic-web-content)

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
[Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/webcontentdisplayscreenlet-for-android).

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
another site's HTML, you can set the Web Content Display Screenlet's `groupId` 
with the `app:groupId` attribute. You can alternatively use the `setGroupId` 
method in the activity or fragment code that uses the Screenlet. 

## Using Templates [](id=using-templates)

Web Content Display Screenlet can also use 
[templates](/discover/portal/-/knowledge_base/7-0/designing-uniform-content#designing-web-content-with-templates) 
to render `JournalArticle` entities. For example, your Liferay instance may have 
a custom template specifically designed to display content on mobile devices. 

To use a template, specify its ID in the Screenlet XML's `templateId` property:

    <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:articleId="YOUR_ARTICLE_ID"
        app:templateId="YOUR_TEMPLATE_ID" />

## Using Structures [](id=using-structures)

Since mobile devices have limited screen space, you must often display only the 
most important parts of a web content article. If your web content is 
[structured](/discover/portal/-/knowledge_base/7-0/designing-uniform-content#creating-structured-web-content), 
you can do this by using Web Content Display Screenlet to display only specific 
fields from a `JournalArticle`'s structure. The simplest way to do this is to 
specify the structure's ID and a comma-delimited list of fields in the Screenlet 
XML's `structureId` and `labelFields` attributes, respectively. The following 
example illustrates this: 

    <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        liferay:articleId="YOUR_ARTICLE_ID"
        liferay:labelFields="YOUR_LABELS"
        liferay:layoutId="@layout/webcontentdisplay_structured_default"
        liferay:structureId="YOUR_STRUCTURE_ID" />

You can also use your own layout to render the structure fields exactly how you 
want. To do this, your layout should inherit from 
`WebContentStructuredDisplayView` and read the information parsed and stored in 
the `webContent` entity. By displaying two structure fields with such a custom 
layout, the 
[test app](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app) 
contains a complete example of this:

1. The 
   [layout file `webcontentdisplaystructured_example.xml`](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/layout/webcontentdisplaystructured_example.xml) 
   defines the custom layout: 

        <com.liferay.mobile.screens.testapp.webviewstructured.WebContentDisplayView 
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <TextView
                android:id="@+id/web_content_first_field"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:background="@android:color/holo_red_light" />

            <TextView
                android:id="@+id/web_content_second_field"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:background="@android:color/holo_green_light" />

        </com.liferay.mobile.screens.testapp.webviewstructured.WebContentDisplayView>

2. The 
   [`WebContentDisplayView` class](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/webviewstructured/WebContentDisplayView.java) 
   sets the custom layout's functionality: 

        public class WebContentDisplayView extends WebContentStructuredDisplayView {

            ...

            @Override
            public void showFinishOperation(WebContent webContent) {
                super.showFinishOperation(webContent);

                DDMStructure ddmStructure = webContent.getDDMStructure();

                TextView firstField = (TextView) findViewById(R.id.first_field);
                firstField.setText(String.valueOf(ddmStructure.getField(0).getCurrentValue()));

                TextView secondField = (TextView) findViewById(R.id.second_field);
                secondField.setText(String.valueOf(ddmStructure.getField(1).getCurrentValue()));
            }
        }

3. The 
   [Screenlet XML's](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/layout/web_content_display_structured.xml) 
   `layoutId` attribute specifies the custom layout to use: 

        <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            liferay:articleId="@string/liferay_article_structured_article_id"
            liferay:labelFields="@string/liferay_article_structured_label_fields_first_field"
            liferay:layoutId="@layout/webcontentdisplaystructured_example"
            liferay:offlinePolicy="REMOTE_FIRST"
            liferay:structureId="@string/liferay_article_structured_structure_id" />

Great! Now you know how to use structured web content with Web Content Display 
Screenlet. Next, you'll learn how to display a list of web content articles in 
your app. 

## Displaying a List of Web Content Articles [](id=displaying-a-list-of-web-content-articles)

The preceding examples show you how to use Web Content Display Screenlet to 
display a single web content article's contents in your app. But what if you 
want to display a list of articles instead? No problem! You can use Web Content 
List Screenlet for this. Web Content List Screenlet can retrieve the contents of 
a web content folder and display only the labels you want. The Screenlet is also 
aware of 
[structured](/discover/portal/-/knowledge_base/7-0/designing-uniform-content#creating-structured-web-content) 
content, so you can render each row with certain structure fields. You can also 
do this via a custom layout. 

To use a web content folder with Web Content List Screenlet, specify the 
folder's ID in the Screenlet XML's `folderId` attribute. To render a specific 
structure field for each article in the list, specify that field in the 
Screenlet XML's `labelFields` attribute. For example: 

	<com.liferay.mobile.screens.webcontent.list.WebContentListScreenlet
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		app:folderId="YOUR_FOLDER_ID"
		app:labelFields="Text" />

You can also see an example of this in the test app's 
[`web_content_display_list.xml` layout file](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/layout/web_content_display_list.xml). 

Also note that several methods in 
[Screens's `WebContent` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/webcontent/WebContent.java) 
help you render content from different locales. For example, 
`getLocalized(name)` receives a field's name and returns the value in the mobile 
device's current locale. Such methods help you render a custom view without 
worrying about the underlying structure, XML parsing, or HTTP calls. 

## Displaying a List of Assets [](id=displaying-a-list-of-assets)

To render a list of different assets in your app, including web content 
articles, you can use Asset List Screenlet. Asset List Screenlet can display a 
list of any assets from a Liferay instance. Like Web Content List Screenlet, you 
can also access a web content article's structure fields, or use a custom layout 
to render each asset type. For more information, see the 
[reference documentation for Asset List Screenlet](/develop/reference/-/knowledge_base/7-0/assetlistscreenlet-for-android). 

## Related Topics [](id=related-topics)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-views-in-android-screenlets)

[Web Content Display Screenlet for Android](/develop/reference/-/knowledge_base/7-0/webcontentdisplayscreenlet-for-android)

[Web Content List Screenlet for Android](/develop/reference/-/knowledge_base/7-0/web-content-list-screenlet-for-android)

[Asset List Screenlet for Android](/develop/reference/-/knowledge_base/7-0/assetlistscreenlet-for-android)
