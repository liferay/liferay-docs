# Using Web Contents in Liferay Screens [](id=rendering-web-contents)

Liferay Screens provides several options to render Web Contents in an easy and powerful way. For historical reasons, Web Contents are usually called *Journal Articles* in Liferay. You can check the referene documentation for [Liferay's Web Content Management](/discover/portal/-/knowledge_base/6-2/web-content-management).

One of simplest way of displaying html is using the `WebContentDisplayScreenlet`. This screenlet is very powerful and allows several complex use cases to fit your needs. We are going to describe in detail some of the most interesting interactions that the screenlet supports.

## Retrieving a Basic Web Content

The most simple use case is retrieving the html of a Web Content to render it in a `UIWebView`. We just have to provide the `articleId` of the Web Content and the screenlet will take care of the rest, including caching, decorating our screenlet with the needed `css` to render it in a small display, etc.

You have to take into account that the returned html is not aware of the global CSS of the portal. If you want to render the content exactly the same as the mobile version of your webpage you will have to provide the css inline or use a specific template.

You can also modify the rendered html with a delegate, as explained in the [WebContentDisplayScreenlet reference](/develop/reference/-/knowledge_base/6-2/webcontentdisplayscreenlet-for-ios).

A common mistake when rendering Web Content is using the default `groupId` of the site when the Web Content belongs to a specific site. If you want to keep using a default groupId in your application but render the html of another site you can pass a different `groupId` to your screenlet.

## Using Templates

A common use case when building a native mobile application with Liferay is showing a Web Content with a custom template (probably designed specifically for the mobile use case). These 

The `WebContentDisplayScreenlet` accepts a property `templateId` to indicate which template to render.

Remember that a structured Web Content can have an large number of templates, suitable to be used in different use cases. A content designed for displaying in a computer screen (or even in a mobile phone) could not be easily translated to work in the context of a native application. You can implement a different template for that specific use case.

## Rendering a Structured Web Content

Despite of most of your Web Contents can be rendered using a template, you're not making the most of a native app. If you want to use native components instead of a Web View, you'll need to create your own theme.
Follow the next steps:

  - [Create a theme](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes) to render your Web Content. If you've already created your own theme, you can skip this step.
  - In your theme, create a new class called `WebContentDisplayView_themeName`, extending from `BaseScreenletView`. This class will hold the `IBOutlets` and `IBActions` associated to the Web Content's UI.
  - Create the UI in the `WebContentDisplayView_themeName.xib` file. In that file you'll have an `UIView`, in inside that, you'll have whatever components you need to render the Web Content's fields. For instance, if your structured Web Content contains `latitude` and `longitude` fields, you can use an `MKMapView` component to render the map point.
  - Once you have your components ready, change the root view's class to `WebContentDisplayView_themeName` class (the one you already created) and create all `IBOutlets` and `IBActions` you need to manage your UI components.
  -  Finally, in the `WebContentDisplayView_themeName` class, you need to conform `WebContentDisplayViewModel` protocol. This protocol will force you to add two properties: `htmlContents` and `recordContent`. The former is intended to be used for HTML Web Contents (not our case), and the latter for structured Web Contents. Get the field's value from the record and set corresponding outlet's value.

```
	public var htmlContent: String? {
		get {
			return nil
		}
		set {
			// not used for structured Web Contents
		}
	}

	public var recordContent: DDLRecord? {
		didSet {
			// set the outlets with record's values
			set.myOutlet.myProperty = recordContent?["my_field_name"]?.currentValueAsLabel
		}
	}
```

## Displaying a list

Besides rendering single Web Contents, one common use case is to render a list of them. For that you have two alternatives:

### Use AssetListScreenlet

[AssetListScreenlet](/develop/reference/-/knowledge_base/6-2/assetlistscreenlet-for-ios) allows you to retrieve a list of assets and show them in the screen.

Since Web Content is an Asset, you can use `AssetListScreenlet` for Web Contents as well.

Consider the following when you use an `AssetListScreenlet` with Web Contents:

  - In the delegate, `screenlet:onAssetListResponse` you'll get an array of `Asset` objects. These objects will be `WebContent`, given that `WebContent` is a child of `Asset`. In the `WebContent` type, you have the `html`, `structure` or `structuredRecord` properties.
  - If you want to render an `AssetListScreenlet` with `WebContent` objects, you need to create your own theme, then create a class in your theme extending from `AssetListView_default` and finally override `doFillLoadedCell` method. In this method, cast `object` parameter as `WebContent` and then retrieve fields' values from the web content's `structuredRecord` property. If you also want custom cells, you can override also `doRegisterCellNibs` and `doCreateCell` methods. Read [AssetListScreenlet reference](https://dev.liferay.com/develop/reference/-/knowledge_base/6-2/assetlistscreenlet-for-ios) for more details about how to customize your asset list.

### Use WebContentListScreenlet

[WebContentListScreenlet](/develop/reference/-/knowledge_base/6-2/webcontentlistscreenlet-for-ios) allows you to retrieve a list of Web Contents and show them in the screen.

This screenlet is quite similar to [AssetListScreenlet](/develop/reference/-/knowledge_base/6-2/assetlistscreenlet-for-ios) and it's used in a similar manner.

- Insert a `WebContentListScreenlet` in your View Controller. 
- Configure the `groupId` and `folderId` properties. Use zero `folderId` for root folder.
- You can conform `WebContentListScreenletDelegate` to receive events related to the list. In those event's you'll get `WebContent` objects.

For more information, read the [WebContentListScreenlet reference](/develop/reference/-/knowledge_base/6-2/webcontentlistscreenlet-for-ios) documentation.

## Related Topics [](id=related-topics)

[WebContentDisplayScreenlet reference](/develop/reference/-/knowledge_base/6-2/webcontentdisplayscreenlet-for-ios)

[AssetListScreenlet](/develop/reference/-/knowledge_base/6-2/assetlistscreenlet-for-ios)