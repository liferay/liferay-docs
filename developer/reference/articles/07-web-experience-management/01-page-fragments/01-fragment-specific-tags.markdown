---
header-id: fragment-specific-tags
---

# Fragment Specific Tags

[TOC levels=1-4]

There are Liferay-specific tags for creating editable, text, image, and link 
fields, and for embedding widgets.

## Making Text Editable

You can make text of a fragment editable by enclosing it in an 
`<lfr-editable>` tag like this:
 
```html
<lfr-editable id="unique-id" type="text">
   This is editable text!
</lfr-editable>
```
 
If you need formatting options like text or color styles, use `rich-text`:
 
```html
<lfr-editable id="unique-id" type="rich-text">
   This is editable text that I can make bold or italic! 
</lfr-editable>
```
 
The `lfr-editable` tag doesn't render without a unique `id`. 

| **Note:** If you want to make text inside an HTML element editable, you must 
| use the `rich-text` type. The `text` type strips HTML formatting out of the 
| text before rendering.

## Making Images Editable

Images use the same `<lfr-editable>` tag as text, but with the `image` type, 
like this:
 
```html
<lfr-editable id="unique-id" type="image">
   <img src="...">
</lfr-editable>
```

After you add the `lfr-editable` tag with the type `image` to a Fragment, when 
you add that Fragment to a page, you can then click on the editable image to select an image or configure content mapping for the image.

To edit an image from the Content Page editor,

1.  Click on the image you want to replace.

2.  Click ![Image Properties](../../../images/icon-edit.png).

From here you can enter a URL in the *Image* field, click *Select* to upload an 
image or select it from Docs and Media to define an image. Click *Clear* to 
reset the image, or define a *URL* target for when a user clicks the image.

![Figure 1: You have several options for defining an image on a Content Page.](../../../images/fragment-image-editor.png)

Content mapping connects editable fields in your Fragment with fields from an 
Asset type like Web Content or Blogs. For example, you can map an image field 
to display a preview image for a Web Content Article. For more information on mapping fields, see the [Asset Display Page guide](user-doc-link).

## Creating Editable Links

There is also a specific syntax for creating editable link elements:
 
```html
<lfr-editable id="unique-id" type="link">
    <a href="default-target-url-goes-here">Link text goes here</a>
</lfr-editable>
```
 
Marketers can edit the link text, target URL, and basic link styling---primary
button, secondary button, link.

To edit a link from the content page editor,

1.  Click on the link or button that you want to edit.

2.  Click on ![Edit](../../../images/icon-edit.png) to edit the link text.

3.  Click on ![Link](../../../images/icon-link.png) to edit the link properties.

From the Link Properties popup, you can set the *URL* for the link, choose the 
link *Display Type*, and use the *Target* dropdown to define the link behavior. For more information on various aspects on editing and managing Content Pages, see the [Creating Content Pages guide](user-docs-link).

![Figure 2: You have several options for defining a link's appearance and behavior.](../../../images/fragment-link-editor.png)

## Including Widgets Within A Fragment 

To include a widget, you must know its registered name. For example, the Site 
Navigation Menu portlet is registered as `nav`. Each registered portlet has an
`lfr-widget-[name]` tag that's used to embed it. For example: the Navigation
Menu tag is `<lfr-widget-nav />`. You could embed it in a block like this:

```html
<div class="nav-widget">
    <lfr-widget-nav>
    </lfr-widget-nav>
</div>
```

These are the widgets that can be embedded and their accompanying tags:

| Widget Name    | Tag |
| -------- | --- |	
|DDL Display	|`<lfr-widget-dynamic-data-list>`  |
|Form           |`<lfr-widget-form>`               |
|Asset Publisher|`<lfr-widget-asset-list>`     |
|Breadcrumb	    |`<lfr-widget-breadcrumb>` |
|Categories Navigation |`<lfr-widget-categories-nav>` |
|Flash	|`<lfr-widget-flash>`|
|Media Gallery	|`<lfr-widget-media-gallery>`|
|Navigation Menu	|`<lfr-widget-nav>`|
|Polls Display	|`<lfr-widget-polls>`|
|Related Assets	|`<lfr-widget-related-assets>`|
|Site Map	|`<lfr-widget-site-map>`|
|Tag Cloud	|`<lfr-widget-tag-cloud>`|
|Tags Navigation	|`<lfr-widget-tags-nav>`|
|Web Content Display	|`<lfr-widget-web-content>`
|Rss Publisher (Deprecated)	|`<lfr-widget-rss>`|
|Iframe	|`<lfr-widget-iframe>`|


## Enabling Embedding for Your Widget

If you have a custom widget that you want to embed in a fragment, you can 
configure that widget to be embeddable. To embed your widget, it must be an OSGi
Component. Inside the `@Component` annotation for the portlet class you want to
embed, add this property:

```properties
com.liferay.fragment.entry.processor.portlet.alias=app-name
```

When you deploy your widget, it's available to add. The name you specify in the
property must be appended to the `lfr-widget` tag like this:

```html
<lfr-widget-app-name>
</lfr-widget-app-name>
```
| **NOTE:** According to the W3C HTML standards, custom elements cannot be self 
| closing. Therefore, even though you cannot add anything between the opening and
| closing `<lfr-widget...>` tags, you cannot use the self closing notation for 
| the tag.
