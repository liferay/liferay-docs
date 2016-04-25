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

Since mobile devices have limited screen space, you must often display only the 
most important parts of a web content article. If your web content is 
[structured](/discover/portal/-/knowledge_base/6-2/advanced-content-with-structures-and-templates#using-structures), 
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

<!-- Changed WebContentDisplayView to WebContentStructuredDisplayView in 2nd sentence -->
You can also use your own layout to render the structure fields exactly how you 
want. To do this, your layout should inherit from 
`WebContentStructuredDisplayView` and read the information parsed and stored in 
the `webContent` entity. By displaying two structure fields with such a custom 
layout, the 
[test app](https://github.com/liferay/liferay-screens/tree/develop/android/samples/test-app) 
contains a complete example of this:

1. The 
   [layout file `webcontentdisplaystructured_example.xml`](https://github.com/liferay/liferay-screens/blob/develop/android/samples/test-app/src/main/res/layout/webcontentdisplaystructured_example.xml) 
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
   [`WebContentDisplayView` class](https://github.com/liferay/liferay-screens/blob/develop/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/webviewstructured/WebContentDisplayView.java) 
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
   [Screenlet XML's](https://github.com/liferay/liferay-screens/blob/develop/android/samples/test-app/src/main/res/layout/web_content_display_structured.xml) 
   `layoutId` attribute then specifies the custom layout to use: 

        <com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            liferay:articleId="@string/liferay_article_structured_article_id"
            liferay:labelFields="@string/liferay_article_structured_label_fields_first_field"
            liferay:layoutId="@layout/webcontentdisplaystructured_example"
            liferay:offlinePolicy="REMOTE_FIRST"
            liferay:structureId="@string/liferay_article_structured_structure_id" />

## Displaying a List of Web Content Articles

<!-- 
This code is in the following test-app layout file:

https://github.com/liferay/liferay-screens/blob/develop/android/samples/test-app/src/main/res/layout/web_content_display_list.xml
* change link to master branch instead of develop for publication
-->

The preceding examples show you how to use Web Content Display Screenlet to 
display a single web content article's contents in your app. But what if you 
want to display a list of articles instead? No problem! You can use Web Content 
List Screenlet for this. Web Content List Screenlet can retrieve the contents of 
a folder and display only the labels you want. It's also aware of the 
`DDMStructure` behind the content, so you can create a custom view (passing a 
new layout with app:layoutId) and render each row with the customization you 
like.

The following example illustrates how to render a folder, only showing the value 
of the *text* field in each row.

	<com.liferay.mobile.screens.webcontent.list.WebContentListScreenlet
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		app:folderId="YOUR_FOLDER_ID"
		app:labelFields="Text" />

The `WebContent` class has several methods to help you render content from 
different locales. For example `getLocalized(name)` receives the name of a field 
and returns the value in the mobile current locale. Those methods will help you 
render a custom view without worrying of the underline structure, xml parsing or 
http calls.

## Displaying assets

If you want to render different assets, including `JournalArticles`, the 
`AssetListScreenlet` is aware of the nature of the underlining asset. You can 
customize the layout to render each type of asset in a different way or access 
the `DDMStructure` of the `WebContent`.

## Related Topics [](id=related-topics)

[Web Content Display Screenlet for Android](/develop/reference/-/knowledge_base/6-2/webcontentdisplayscreenlet-for-android)