---
header-id: page-fragments
---

# Page Fragments

[TOC levels=1-4]

You can use Page Fragments to take your design vision and accurately realize it
on a web page. You start with a "blank slate." You then have three tools
at your disposal to accomplish your vision:

**HTML**: The markup of the fragment. Fragments use standard HTML with 
special tags to add dynamic behavior. 

**CSS**: Styles and positions the fragment's markup. 

**JavaScript**: Provides dynamic behavior to the fragment.

The HTML, CSS, and JavaScript are all completely standard, but can be 
enhanced with Liferay-specific features. You can specify text, images, and 
links as editable and provide for "rich" text with formatting.

You can also access the FreeMarker templates engine from your HTML using the
[alternative (square bracket) syntax](https://freemarker.apache.org/docs/dgui_misc_alternativesyntax.html).
Learn more about available FreeMarker objects in 
[Front-end Reference](/docs/7-2/reference/-/knowledge_base/r/front-end-reference).

Liferay portlets can also be embedded in Fragments as widgets, making pages with
Fragments more dynamic than regular web content.

Now you'll step through some Page Fragment basics.

## Developing Page Fragments

There are two types of Page Fragments: *Sections* and *Components*. A Section
defines columns, padding, and spacing on the page. A Component contains content
that is added to a Section.

Fragments are created inside of Collections. Collections provide an easy way to 
manage and share groups of related Fragments. Users navigate Collections when 
selecting Fragments to add to a page. To see examples, the admin page shows all
the out-of-the-box Fragments (and their code).

You can create and manage Fragments and Collections without using any external
tools, but you can also use your preferred web development tools. For an
explanation of Fragment creation using Liferay's built in tools, see 
[Creating a Fragment](/docs/7-2/frameworks/-/knowledge_base/f/creating-fragments). 

## Making a Fragment Configurable

| **Note:** Defining configurations for Page Fragments is available in Liferay
| DXP 7.2 SP1+ and Liferay Portal GA2+.

Page Fragments are also configurable: defining configuration options for your
fragment eliminates having to maintain multiple other fragments similar in
style. For example, if you want a dark background banner and a light background
banner, you can create one banner with a configuration option for background
type.

The following field types are supported for Fragment configurations:

- `checkbox`
- `colorPalette`
- `itemSelector`
- `select`
- `text`

This is available for all Fragment types (e.g., Fragment Renderer, etc.).

For more information, see
[Making a Fragment Configurable](/docs/7-2/frameworks/-/knowledge_base/f/making-a-fragment-configurable).

### Fragments CLI

To streamline fragment development, @product-ver@ provides command line tools
for generating, importing, and exporting fragments and fragment collections. For
more information about the CLI, see the 
[official Liferay Fragments CLI project](https://github.com/liferay/generator-liferay-fragments/blob/master/README.md)
reference. Using this CLI is also covered in 
[Developing a Fragment using Desktop Tools](/docs/7-2/frameworks/-/knowledge_base/f/page-fragments-desktop-tools).

## Contributed Collections

Most of the time, Page Fragments are created and imported through the Page
Fragments interface or created directly using the built-in tools. Any user with
the right permissions can update or edit Page Fragments created like this. You
may have certain situations, however, where you want 100% static fragments that
cannot be modified. In this case you can create a Contributed Fragment
Collection.

Contributed Fragment Collections are deployable modules containing Page
Fragments. Those fragments can be used just like regular fragments, but are not 
contained in the database, and cannot be modified except by updating the module 
they came from. Use the 
[Creating Contributed Collections guide](/docs/7-2/frameworks/-/knowledge_base/f/creating-contributed-fragment-collection)
to learn to create your own Contributed Collections.

## Fragment Specific Tags

In addition to standard HTML, CSS, and JavaScript you can use Liferay-specific
tags to make editable sections or embed widgets in your Fragment.

Editable elements can be modified before publication. This means that web
developers can create simple, reusable fragments that have identical formatting,
but contain elements that are adaptable to the specific context.

You can make text, images, and links in a fragment editable by using
an `<lfr-editable>` tag. The `<lfr-editable>` tag requires a unique `id`, a 
type, and some content of the specified type inside.

The following four `type` options are available in an `lfr-editable` 
tag:

`text`: Creates a space for editable plain text. 

`image`: Must contain a valid `<img>` tag which can then be replaced with an
image before publishing---including those from Documents and Media.
 
`rich-text`: Provides rich text formatting, such as bold, italics, underline,
links, and predefined styles.

`link`: Must contain a valid anchor tag for which the style, target URL, and 
link text can be edited before publishing.

The text or images you provide here are the default values for the fields. 
You may want to display them in the final version of the page, or you may want
filler text that should be replaced before the page is published.

All of these work together to help you create dynamic, reusable elements for 
building a site. For example, if you need a small text box with an image and 
link to provide a  product description, you can create a fragment containing
editable filler text, space for an editable image, the appropriate formatting,
and an editable link. That fragment can be added to multiple pages, and
marketers can define the image, text, and link for each product they need to
describe. 

You can make a Fragment even more dynamic by including a widget. Currently,
portlets are the only embeddable types of widgets, but other options are planned.

You can find a complete list and usage examples of these in the 
[Page Fragments Reference](/docs/7-2/reference/-/knowledge_base/r/fragment-specific-tags).

## Recommendations and Best Practices

In general all your code should be semantic and highly reusable. A main concern
is making sure that everything is namespaced properly so it won't interfere with
other elements on the page outside of the Fragment.

## CSS

While you can write any CSS in a fragment, it's recommended to prefix it with
a class specific to the fragment to avoid impacting other fragments. To
facilitate this, when creating a new fragment, the HTML includes a `div` with an
automatically generated class name and the CSS shows a sample selector using
that class. Use it as the basis for all selectors you add.

## JavaScript

Avoid adding a lot of JavaScript code, since it isn't easily reusable. Instead,
reference external JS libraries.
