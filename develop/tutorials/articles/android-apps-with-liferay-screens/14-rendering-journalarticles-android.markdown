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

By the default security policy, in Android, a WebView does not execute the javascript of a page. You can enable the execution by setting the `javascriptEnabled` property through XML or with the apropiate setter in java code.

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

```xml
<com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	liferay:articleId="YOUR_ARTICLE_ID"
	liferay:layoutId="YOUR_LAYOUT_ID"
	liferay:structureId="YOUR_STRUCTURE_ID" />
```

```xml
<com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	liferay:articleId="YOUR_ARTICLE_ID"
	liferay:labelFields="YOUR_LABELS"
	liferay:layoutId="@layout/webcontentdisplay_structured_default"
	liferay:structureId="YOUR_STRUCTURE_ID" />
```

```xml
	<com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		liferay:articleId="YOUR_ARTICLE_ID"
		liferay:labelFields="@string/liferay_article_structured_label_fields_first_field"
		liferay:layoutId="@layout/webcontentdisplaystructured_example"
		liferay:structureId="YOUR_STRUCTURE_ID" />
```


*in test-app*

## Displaying a list

```xml
	<com.liferay.mobile.screens.webcontent.list.WebContentListScreenlet
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		app:folderId="YOUR_FOLDER_ID"
		app:labelFields="YOUR_LABELS" />
```

*WebContentDisplayList*

*structure*

*localized fields*

## Displaying assets 

*assetlistscreenlet*



## Related Topics [](id=related-topics)

[WebContentDisplayScreenlet reference](/develop/reference/-/knowledge_base/6-2/webcontentdisplayscreenlet-for-android)