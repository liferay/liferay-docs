# Rendering Web Content in Your iOS App

Liferay Screens provides several ways to render 
[web content](/discover/portal/-/knowledge_base/6-2/web-content-management) 
in your app. For historical reasons, web content articles are `JournalArticle` 
entities in Liferay. Using Web Content Display Screenlet is a simple and 
powerful way to display HTML from a `JournalArticle` in your app. To fit your 
needs, this Screenlet supports several use cases. This tutorial describes them. 

## Retrieving Basic Web Content

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
[Web Content Display Screenlet reference documentation](/develop/reference/-/knowledge_base/6-2/webcontentdisplayscreenlet-for-ios). 

As you can see, this is all fairly straightforward. What could go wrong? Famous 
last words. A common mistake is to use the default site ID (`groupId`) instead 
of the one for the site that contains your web content articles. To continue 
using a default `groupId` in your app, but use a different one for Web Content 
Display Screenlet, assign the Screenlet's *Group Id* property in Interface 
Builder. 

## Using Templates

Web Content Display Screenlet can also use 
[templates](/discover/portal/-/knowledge_base/6-2/advanced-content-with-structures-and-templates) 
to render web content articles. For example, your Liferay instance may have 
a custom template specifically designed to display content on mobile devices. To 
use a template, set the template's ID as the Screenlet's `templateId` property 
(*Template Id* in Interface Builder). 

Recall that structured web content in Liferay can have many templates. You can 
create your own template if there's not one suitable for displaying web content 
in your app. 

## Rendering Structured Web Content
<!-- You have to create a separate theme to display fields from structured web content? -->

Despite of most of your Web Contents can be rendered using a template, you're 
not making the most of a native app. If you want to use native components 
instead of a Web View, you'll need to create your own theme. Follow the next 
steps: 

- [Create a theme](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes) 
  to render your Web Content. If you've already created your own theme, you can 
  skip this step. 
- In your theme, create a new class called `WebContentDisplayView_themeName`, 
  extending from `BaseScreenletView`. This class will hold the `IBOutlets` and 
  `IBActions` associated to the Web Content's UI. 
- Create the UI in the `WebContentDisplayView_themeName.xib` file. In that file 
  you'll have an `UIView`, in inside that, you'll have whatever components you 
  need to render the Web Content's fields. For instance, if your structured Web 
  Content contains `latitude` and `longitude` fields, you can use an `MKMapView` 
  component to render the map point. 
- Once you have your components ready, change the root view's class to 
  `WebContentDisplayView_themeName` class (the one you already created) and 
  create all `IBOutlets` and `IBActions` you need to manage your UI components.
- Finally, in the `WebContentDisplayView_themeName` class, you need to conform 
  `WebContentDisplayViewModel` protocol. This protocol will force you to add two 
  properties: `htmlContents` and `recordContent`. The former is intended to be 
  used for HTML Web Contents (not our case), and the latter for structured Web 
  Contents. Get the field's value from the record and set corresponding outlet's 
  value.

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

## Displaying a List of Web Content Articles

The preceding examples show you how to use Web Content Display Screenlet to 
display a single web content article's contents in your app. But what if you 
want to display a list of articles instead? No problem! You can do this by using 
[Web Content List Screenlet](https://www.liferay.com/), 
or 
[Asset List Screenlet](/develop/reference/-/knowledge_base/6-2/assetlistscreenlet-for-ios). 

First, you'll learn how to use Web Content List Screenlet. 

### Using Web Content List Screenlet

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
[Web Content List Screenlet reference documentation](/develop/reference/-/knowledge_base/6-2/webcontentlistscreenlet-for-ios). 

### Using Asset List Screenlet
<!-- You have to create a separate theme to display a list of web content in Asset List Screenlet? -->

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
  [Asset List Screenlet reference documentation](https://dev.liferay.com/develop/reference/-/knowledge_base/6-2/assetlistscreenlet-for-ios) 
  for more details on customizing your asset list. 

## Related Topics

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-ios-apps)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-themes-in-ios-screenlets)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes)

[Web Content Display Screenlet for iOS](/develop/reference/-/knowledge_base/6-2/webcontentdisplayscreenlet-for-ios)

[Web Content List Screenlet for iOS](https://www.liferay.com/)

[Asset List Screenlet for iOS](/develop/reference/-/knowledge_base/6-2/assetlistscreenlet-for-ios)
