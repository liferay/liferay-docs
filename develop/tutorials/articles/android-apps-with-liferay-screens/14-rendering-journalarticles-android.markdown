# Rendering JournalArticles in Liferay Screens [](id=rendering-journal-articles)

Liferay Screens provides several options to render `JournalArticles` in an easy and powerful way.

One of simplest way of displaying html is using the `WebContentDisplayScreenlet`. This screenlet is very powerful and allows several complex use cases to fit your needs. We are going to describe in detail some of the most interesting interactions that the screenlet supports.

## Retrieving a basic web content

The most simple use case is retrieving the html of a `JournalArticle` to render it in a `WebView`. We just have to provide the `articleId` of the JournalArticle and the screenlet will take care of the rest, including decorating our screenlet with the needed css to render it in a small display.

The following example shows this:

```xml
<com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
			android:layout_width="match_parent"
			android:layout_height="match_parent"
			app:articleId="YOUR_ARTICLE_ID" />
```

You have to take into account that the returned html is not aware of the global CSS of the portal. If you want to render the content exactly the same as the mobile version of your webpage you will have to provide the css inline or use a specific template.

You can also modify the rendered html with a listener, as explained in the screenlet [reference](/develop/reference/-/knowledge_base/6-2/webcontentdisplayscreenlet-for-android).

By the default security policy, in Android, a WebView does not execute the javascript of a page. You can enable the execution by setting the `javascriptEnabled` property through XML or with the appropriate setter in java code.

A common mistake when rendering `JournalArticles` is using the default `groupId` of the site when the JournalArticles belong to a specific site. If you want to keep using a default groupId in your application but render the html of another site you can pass a different groupId to your screenlet with an xml attribute or setting the value with java code.

## Using templates

A common use case when building a native mobile application with Liferay is showing a `JournalArticle` with a custom template (maybe designed specifically for the mobile use case). 

The `WebContentDisplayScreenlet` accepts a property `templateId` to indicate which template to render.

The following example illustrates this use case:

```xml
<com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		app:articleId="YOUR_ARTICLE_ID"
		app:templateId="YOUR_TEMPLATE_ID" />
```

Remember that a `JournalArticle` can have an infinite number of templates, adapted to different use cases. A content designed for displaying in a computer screen (or even in a mobile phone) could not be easily translated to work in the context of a native application. You could implement a different template for that specific use case.

## Rendering a structure

Mobile phones have limited screen space and to offer a great experience to your users you will have to limit the information displayed. The `WebContentDisplayScreenlet` shines when rendering specific fields of a `DDMStructure` behind your `JournalArticle`.

The easiest way of displaying a specific field of a DDMStructure is to pass the `structureId` of the content and a list of fields to render, separated by commas, in the `labelFields` attribute. The following example illustrates that concept:

```xml
<com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	liferay:articleId="YOUR_ARTICLE_ID"
	liferay:labelFields="YOUR_LABELS"
	liferay:layoutId="@layout/webcontentdisplay_structured_default"
	liferay:structureId="YOUR_STRUCTURE_ID" />
```

But the most powerful way of using the `WebContentDisplayScreenlet` is to supply your own layout and render the `DDMStructure` and the values with the appearance you want. To do that, you just have to supply a layout that inherits from `WebContentDisplayView` and reads the information parsed and stored in the `webContent` entity. The following xml shows this concept:

```xml
<com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	liferay:articleId="YOUR_ARTICLE_ID"
	liferay:layoutId="YOUR_LAYOUT_ID"
	liferay:structureId="YOUR_STRUCTURE_ID" />
```

In the [test application](https://github.com/liferay/liferay-screens/tree/develop/android/samples/test-app) there is a full example, displaying two fields of the structure with a custom format. 

The xml code to use the screenlet:

```xml
	<com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		liferay:articleId="YOUR_ARTICLE_ID"
		liferay:labelFields="YOUR_LABEL_FIELDS"
		liferay:layoutId="@layout/webcontentdisplaystructured_example"
		liferay:structureId="YOUR_STRUCTURE_ID" />
```

And the following xml and java code defines the customisation of the layout:

```java

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
```

```xml
<com.liferay.mobile.screens.testapp.webviewstructured.WebContentDisplayView xmlns:android="http://schemas.android.com/apk/res/android"
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

```

## Displaying a list

If you want to display the contents of a `JournalArticle` folder, Liferay Screens has got you covered!

The `WebContentListScreenlet` can retrieve the contents of a folder and display only the labels you want. It's also aware of the `DDMStructure` behind the content, so you can create a custom view (passing a new layout with app:layoutId ) and render each row with the customization you like.

The following example illustrates how to render a folder, only showing the value of the *text* field in each row.

```xml
	<com.liferay.mobile.screens.webcontent.list.WebContentListScreenlet
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		app:folderId="YOUR_FOLDER_ID"
		app:labelFields="Text" />
```

The `WebContent` class has several methods to help you render content from different locales. For example `getLocalized(name)` receives the name of a field and returns the value in the mobile current locale. Those methods will help you render a custom view without worrying of the underline structure, xml parsing or http calls.

## Displaying assets

If you want to render different assets, including `JournalArticles`, the `AssetListScreenlet` is aware of the nature of the underlining asset. You can customize the layout to render each type of asset in a different way or access the `DDMStructure` of the `WebContent`.


## Related Topics [](id=related-topics)

[WebContentDisplayScreenlet reference](/develop/reference/-/knowledge_base/6-2/webcontentdisplayscreenlet-for-android)