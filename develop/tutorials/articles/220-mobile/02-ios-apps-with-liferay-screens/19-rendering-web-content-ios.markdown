# Rendering Web Content in Your iOS App [](id=rendering-web-content-in-your-ios-app)

Liferay Screens provides several ways to render 
[web content](/discover/portal/-/knowledge_base/7-0/creating-web-content) 
in your app. For historical reasons, web content articles are `JournalArticle` 
entities in Liferay. Using Web Content Display Screenlet is a simple and 
powerful way to display HTML from a `JournalArticle` in your app. To fit your 
needs, this Screenlet supports several use cases. This tutorial describes them. 

## Retrieving Basic Web Content [](id=retrieving-basic-web-content)

The simplest use case for Web Content Display Screenlet is to retrieve a web 
content article's HTML and render it in a 
[`UIWebView`](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UIWebView_Class/). 
To do this, provide the web content article's ID via the *Article Id* attribute 
in Interface Builder. The Screenlet takes care of the rest. This includes 
rendering the content to fit mobile devices, performing any required caching, 
and more. 

To render the content *exactly* as it appears on your mobile site, however, you 
must provide the CSS inline or use a template. The HTML returned isn't aware of 
a Liferay instance's global CSS. 

You can also modify the rendered HTML with a delegate, as explained in the 
[Web Content Display Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/webcontentdisplayscreenlet-for-ios). 

As you can see, this is all fairly straightforward. What could go wrong? Famous 
last words. A common mistake is to use the default site ID (`groupId`) instead 
of the one for the site that contains your web content articles. To continue 
using a default `groupId` in your app, but use a different one for Web Content 
Display Screenlet, assign the Screenlet's *Group Id* property in Interface 
Builder. 

## Using Templates [](id=using-templates)

Web Content Display Screenlet can also use 
[templates](/discover/portal/-/knowledge_base/7-0/designing-uniform-content) 
to render web content articles. For example, your Liferay instance may have a 
custom template specifically designed to display content on mobile devices. To 
use a template, set the template's ID as the Screenlet's `templateId` property 
(*Template Id* in Interface Builder). 

Recall that structured web content in Liferay can have many templates. You can 
create your own template if there's not one suitable for displaying web content 
in your app. 

## Rendering Structured Web Content [](id=rendering-structured-web-content)

To render 
[structured](/discover/portal/-/knowledge_base/7-0/designing-uniform-content) 
web content in Web Content Display Screenlet, you must create a custom theme 
capable of doing so. Also, you must create a custom theme for each structure you 
want to display in your app. In this case, you may find it convenient to create 
each theme inside a single parent theme and use compound naming to indicate this 
relationship. For example, if you have structures in your Liferay instance 
called *book*, *employee*, and *meeting*, you must create a custom theme for 
each. If you create these themes as children of another custom theme called 
*mytheme*, you could name them *mytheme.book*, *mytheme.employee*, and 
*mytheme.meeting*. 

Regardless of where you create your themes or what you name them, use the 
following steps to create them: 

1. [Create a theme](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes) 
   to render your web content. If you've already created your own theme, you can 
   skip this step. 

2. In your theme, create a new class called `WebContentDisplayView_themeName`, 
   extending from `BaseScreenletView`. This class will hold the outlets and 
   actions associated with the web content's UI. 

3. Create the UI in the `WebContentDisplayView_themeName.xib` file. This file 
   should have a `UIView` that contains the components you need to render the 
   web content's structure fields. For example, if your structured web content 
   contains `latitude` and `longitude` fields, you can use a `MKMapView` 
   component to render the map point. 

4. Once your components are ready, change the root view's class to 
   `WebContentDisplayView_themeName` (the class you created in the first step), 
   and create the outlets and actions you need to manage your UI components. 

5. Conform the `WebContentDisplayViewModel` protocol in the 
   `WebContentDisplayView_themeName` class. This protocol requires you to add 
   the `htmlContent` and `recordContent` properties. The `htmlContent` property 
   is intended for HTML web content; this isn't your theme's use case. Your 
   theme must display structured web content; use the `recordContent` property 
   for this content. In this property, set the structure field's value as the 
   corresponding outlet's value. For example: 

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

Next, you'll learn how to display a list of web content articles in your app. 

## Displaying a List of Web Content Articles [](id=displaying-a-list-of-web-content-articles)

The preceding examples show you how to use Web Content Display Screenlet to 
display a single web content article's contents in your app. But what if you 
want to display a list of articles instead? No problem! You can do this by using 
[Web Content List Screenlet](/develop/reference/-/knowledge_base/7-0/web-content-list-screenlet-for-ios), 
or 
[Asset List Screenlet](/develop/reference/-/knowledge_base/7-0/assetlistscreenlet-for-ios). 

First, you'll learn how to use Web Content List Screenlet. 

### Using Web Content List Screenlet [](id=using-web-content-list-screenlet)

Web Content List Screenlet lets you retrieve and display a list of web content 
articles from a web content folder. Follow these steps to use the Screenlet:

- Insert Web Content List Screenlet in your View Controller. 

- Configure the *Group Id* and *Folder Id* properties in Interface Builder. The 
  folder ID is the ID of the web content folder you want to display articles 
  from. To use the root folder, use `0` for the Folder Id. 

- To receive events related to the list, conform 
  `WebContentListScreenletDelegate`. The events contain the `WebContent` 
  objects. 

For more information on the Screenlet and its supported functionality, see the 
[Web Content List Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/web-content-list-screenlet-for-ios). 

### Using Asset List Screenlet [](id=using-asset-list-screenlet)

Asset List Screenlet is similar to Web Content Display Screenlet in that it can 
display a list of items from a Liferay instance. Asset List Screenlet, however, 
displays a list of assets. Since web content is an asset, you can use Asset List 
Screenlet to show a list of web content articles. Consider the following when 
doing this: 

- In the delegate, `screenlet:onAssetListResponse` gets an array of `Asset` 
  objects that represent `WebContent` objects. Since `WebContent` is a child 
  of `Asset`, you can cast the `Asset` objects to `WebContent`. Each 
  `WebContent` object has the `html`, `structure`, or `structuredRecord` 
  properties. 

- To render Asset List Screenlet with `WebContent` objects, you must create your 
  own theme. Create a class in your theme that extends `AssetListView_default`, 
  and override the `doFillLoadedCell` method. In this method, cast the `object` 
  parameter as `WebContent` and then retrieve field values from the web 
  content's `structuredRecord` property. If you want custom cells, you can also 
  override the `doRegisterCellNibs` and `doCreateCell` methods. See the 
  [Asset List Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/assetlistscreenlet-for-ios) 
  for more details on customizing your asset list. 

## Related Topics [](id=related-topics)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-ios-apps)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes)

[Web Content Display Screenlet for iOS](/develop/reference/-/knowledge_base/7-0/webcontentdisplayscreenlet-for-ios)

[Web Content List Screenlet for iOS](/develop/reference/-/knowledge_base/7-0/web-content-list-screenlet-for-ios)

[Asset List Screenlet for iOS](/develop/reference/-/knowledge_base/7-0/assetlistscreenlet-for-ios)
