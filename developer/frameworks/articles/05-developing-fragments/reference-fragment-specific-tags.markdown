# Fragment Specific Tags

You can make text, images, and links inside of a fragment editable by using it  
an `<lfr-editable>` tag. The `<lfr-editable>` tag require a unique `id`, a 
type, and some content of the specified type inside.

The following three `type` options are available inside of of an `lfr-editable` 
tag:

`text`: Creates a space for plain text that can be edited before publishing. 

`image`: Must contain a valid `<img>` tag which can then be replaced with any
image before publishing---including those from Documents and Media.
 
`rich-text`: Provides rich text formatting, such as bold, italics, underline,
links, and predefined styles.

`link`: Provides a link for which the style, target URL, and link text can be 
edited before publishing.

The text or images that you provide here are the default values for the fields. 
In some cases, you may want to have default values that will be displayed in the final version of the page, but in other cases it will be filler text that is intended to be completely replaced before the page is published.

## Making Text Editable

You can make any text of a fragment editable by enclosing it in an 
`<lfr-editable>` tag like this:
    
    <lfr-editable id="unique-id" type="text">
       This is editable text!    
    </lfr-editable>
 
If you need to provide formatting options, like applying text styles, you must 
use `rich-text`:
    
    <lfr-editable id="unique-id" type="rich-text">
       This is editable text that I can make bold or italic!    
    </lfr-editable>
 
The `lfr-editable` tag doesn't render without a unique `id`. 


+$$$

**Note:** If you want to make text inside an HTML element editable, you must use
the `rich-text` type. The `text` type strips HTML formatting out of the text
before rendering.

$$$

SCREENSHOT!

## Making Images Editable

Images use the same `<lfr-editable>` tag as text, but with the `image` type, 
like this:
    
    <lfr-editable id="unique-id" type="image">
       <img src="...">
    </lfr-editable>

After you add the `lfr-editable` tag with the type `image` to a Fragment, when 
you add that Fragment to a page, you can then click on the editable image and 
select a replacement.

SCREENSHOT!

## Creating Editable Links


    
    <lfr-editable id="unique-id" type="link">
        <a href="default-target-url-goes-here">Link text goes here</a>
    </lfr-editable>
    
After you add the `lfr-editable` tag with the type `image` to a Fragment, when 
you add that Fragment to a page, you can then click on the editable image and 
select a replacement.

## Including Widgets Within A Fragment [](id=including-widgets-within-a-fragment)


To include a widget you need to know its registered name. For example, the Site 
Navigation Menu portlet is registered as `nav`. Each portlet which is registered
has an `lfr-widget-[name]` tag that's used to embed it. For example: the Navigation
Menu tag is `<lfr-widget-nav />`. You could embed it in a block like this:

    <div class="nav-widget">
        <lfr-widget-nav>
        </lfr-widget-nav>
    </div>

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
configure that widget to be embeddable. In order to embed your widget, it must
be an OSGi Component. Inside the `@Component` annotation for the portlet class
that you want to embed, add this property:

    "com.liferay.fragment.entry.processor.portlet.alias=app-name"
 
When you deploy your widget, it's available to add. The name you specify in the
property must be appended to the `lfr-widget` tag like this:

    <lfr-widget-app-name>
    </lfr-widget-app-name>


+$$$

**NOTE:** According to the W3C HTML standards, custom elements cannot be self 
closing. Therefore, even though you cannot add anything between the opening and
closing `<lfr-widget...>` tags, you cannot use the self closing notation for 
the tag.

$$$

Embedding widgets in Fragments opens a world of options. Now that you've
explored some of the power of Fragments, next you'll learn about best practices
for development.
