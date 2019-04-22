---
header-id: page-fragments
---

# Page Fragments

[TOC levels=1-4]

Page Fragments take your design vision and accurately realize it on a web page. 
To do this, developers are given a space where they have a "blank slate."  You 
have three tools at your disposal to accomplish this:

**HTML**: The markup of the fragment. Fragments use standard HTML with 
   special tags to add dynamic behavior.

**CSS**: CSS styles and positions the fragment's markup. 

**JavaScript**: JavaScript provides dynamic behavior to the fragment.

The HTML, CSS, and JavaScript are all completely standard, but are also 
enhanced with Liferay-specific features. You can specify text, images, and 
links as editable, as well as providing for "rich text": that is, text with 
additional formatting. Liferay portlets can also be embedded in Fragments as 
"widgets," making pages with Fragments more dynamic than regular web content.

## Creating Page Fragments

There are two types of Page Fragments, *Sections* and *Components*. A Section
is Page Fragment which defines columns, padding, and spacing on the page. A 
Component is a Page Fragment which contains content that is added to a Section.

Fragments are created inside of Collections. Collections provide an easy way to 
manage and share groups of related Fragments. They are also used when a user is 
selecting Fragments to add to a page. @product@ provides a robust interface for 
creating and managing Fragments and Collections without using any external 
tools, but you can also use your preferred tools for Web Development. For an 
explanation of basic Fragment creation using Liferay's built in tools, see 
[Creating a Fragment](/developer/frameworks/-/knowledge_base/7-2/creating-fragments). 

### Fragment CLI

To streamline fragment development, @product-ver@ provides command line tools 
for generating, importing, and exporting fragments and fragment collections. 
For more information about the CLI, see the [official Liferay Fragments CLI project](https://github.com/liferay/generator-liferay-fragments/blob/master/README.md) reference. Using this CLI is also covered in [Developing a Fragment using Desktop Tools](/developer/frameworks/-/knowledge_base/7-2/page-fragments-desktop-tools).


## Contributed Collections

In most cases, Page Fragments are created by web developers and imported 
through the Page Fragments interface, or created directly using the built-in 
tools. Page Fragments created like this can be updated and edited by any user 
with the necessary privileges and used however they want. This works for many 
cases, but you may have certain situations where you want to add fragments that 
will be 100% static, and that cannot be modified. In this case you can create a 
Contributed Fragment Collection.

Contributed Fragment Collections are deployable modules which contain Page 
Fragments. Those fragments can be used just like regular fragments, but are not 
contained in the database, and cannot be modified except by updating the module 
that they are deployed in. Use our [Creating Contributed Collections 
guide](link) to learn to create your own Contributed Collections.

## Fragment Specific Tags

In addition to the standard HTML, CSS, and JavaScript used for creating 
Fragments, there are a number of Liferay specific tags that you will need to 
use to make editable sections, or embed widgets in your Fragment.

Making elements editable allows the marketer or web admin to modify them before 
publishing it. This means that web developers can create simple, reusable 
fragments with identical formatting, but with elements that are easily 
adaptable to the specific context.

You can make text, images, and links inside of a fragment editable by using  
an `<lfr-editable>` tag. The `<lfr-editable>` tag require a unique `id`, a 
type, and some content of the specified type inside.

The following four `type` options are available inside of of an `lfr-editable` 
tag:

`text`: Creates a space for plain text that can be edited before publishing. 

`image`: Must contain a valid `<img>` tag which can then be replaced with any
image before publishing---including those from Documents and Media.
 
`rich-text`: Provides rich text formatting, such as bold, italics, underline,
links, and predefined styles.

`link`: Must contain a valid anchor tag for which the style, target URL, and 
link text can be edited before publishing.

The text or images that you provide here are the default values for the fields. 
In some cases, you may want to have default values that will be displayed in 
the final version of the page, but in other cases it will be filler text that 
is intended to be completely replaced before the page is published.

All of these work together to help you create dynamic, reusable elements for 
building a site. For example, if you need a small text box with an image and 
link to provide a  product description, you can create a fragment with filler 
text set to editable, space for an editable image, the appropriate formatting, 
and an editable link. Then that fragment can be added to multiple pages, and a 
marketer can define the image, text, and link for each product they need to 
describe. 

You can also add more dynamic behavior to a Fragment by including a widget. 
Currently, you can only embed a portlet as a widget, but other types of widgets
will be available in the future.

You can find a complete list and usage examples of these in the [Page Fragments Reference](/developer/reference/-/knowledge_base/7-2/fragment-specific-tags).
