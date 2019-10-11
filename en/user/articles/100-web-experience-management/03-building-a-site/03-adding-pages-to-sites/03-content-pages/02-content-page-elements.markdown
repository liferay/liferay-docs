---
header-id: content-page-elements
---

# Content Page Elements

[TOC levels=1-4]

Content Pages, like Widget Pages, are built by dragging and dropping elements
onto the page and then configuring the way those elements appear. There are
three kinds of elements: 

**Sections** are fragments that define a space to place other elements.
A section fills the entire width of the page. Sections can be thought of as
*complete* Fragments that serve a purpose by themselves. A large banner image
with a text overlay is an example of something you might build as a section.

![Figure 1: A Section named *Banner* being displayed while editing a Content Page.](../../../../../images/content-page-section-example.png)

**Layouts** are special Sections that define spaces where you can add fragments
or widgets. Each layout you add fills the width of the page. You can add any
number of layouts to the page.

![Figure 2: A 3 Column and 1 Column layout stacked on top of each other.](../../../../../images/content-page-layout-example.png)

**Components** are small design elements or pieces that add functionality to the
page. A component might be an image with formatting or a block of text with
styling pre-applied. Components must be added to the page inside a Layout. If
you add a component outside an existing Layout, a one column layout is
automatically added to contain the Component. While Sections should be complete
by themselves, Components work together to build pages piece by piece.

![Figure 3: Here are several of Liferay's out of the box components arranged in the layout you saw previously.](../../../../../images/content-page-component-example.png)

@product@ ships with a plethora of Layouts, Sections, and Components to use to
build pages, and a
[web developer can create their own Fragments](/docs/7-2/frameworks/-/knowledge_base/f/creating-fragments)
to add to these. 

## Editable Elements

Fragments can have editable elements. After a Fragment has been added to a page,
you can click on an editable area to provide your own text, image, or links in
place of the default defined in the Fragment.

You can also map these elements to content. You can set the *Content* for the
element (web content article, document, or blog) and choose its applicable
*Field* to display (e.g., title, author name, tags, etc.). You can configure
this by selecting the element's *Map* button
(![Map](../../../../../images/icon-map.png)).

| **Note:** Many mapping improvements were released in Liferay DXP 7.2 SP1+ and
| Liferay Portal 7.2 GA2+. For example, mapping editables to text/URL fields of
| existing content and mapping Fragment background images to image fields of
| existing content. You can also map
| [custom fields](/docs/7-2/user/-/knowledge_base/u/custom-fields). To ensure
| you leverage the latest editable element mapping features, upgrade to these
| versions.

For more information on developing these elements, see 
[Fragment Specific Tags](/docs/7-2/reference/-/knowledge_base/r/fragment-specific-tags).

Now you'll learn about each editable type.

### Editable Text

Editable text can be plain or rich text. Plain text has no special styling. Rich
text enables text styles, typographical emphasis, alignment, and list
formatting.

![Figure 4: The rich text editor provides a simple WYSIWYG interface with a number of formatting options.](../../../../../images/content-page-rich-text-editor.png)

### Editable Images

Editable image elements allow replacing the image URL or an image from your
Documents and Media library. You can provide a link target for the image.

To edit an image from the Content Page editor,

1.  Click on the image you want to replace.

2.  Click ![Image Properties](../../../../../images/icon-edit.png).

![Figure 5: Editing an image allows you to enter a URL, select an image from Documents and Media or set a link for the image.](../../../../../images/fragment-image-editor.png)

From here, you can click *Select* to upload an image from Docs and Media or
define an image URL. Click *Clear* to reset the image. You can also specify an
image description.

You can also specify a background image for a layout from Section Builder. Click
the Layout, select *Layout Background Image*, and define the image to display.

| **Note:** Mapping a Layout background image is available in Liferay DXP 7.2
| SP1+ and Liferay Portal 7.2 GA2+.

For more information on developing editable images, see 
[Making Images Editable](/docs/7-2/reference/-/knowledge_base/r/fragment-specific-tags#making-images-editable).

You can also define a link for your image. You'll learn about this next.

### Editable Links

Editable links can be associated with entities that redirect you to a content
type or Page (e.g., buttons).

To edit a link from the content page editor,

1.  Click on the link or button that you want to edit.

2.  Click on ![Edit](../../../../../images/icon-edit.png) to edit the link text.

3.  Click on ![Link](../../../../../images/icon-link.png) to edit the link properties.

4.  Click on ![Map](../../../../../images/icon-map.png) to edit the link mapping
    (described earlier).

From the Link Properties popup, you can define the following link options:

*Manual:* defines a manual link or map it to an existing content field.

- *URL:* sets the link's URL.
- *Target:* set the link's behavior.

*From Content Field:* 

- *Content:* sets the content type.
- *Field:* sets the field to display for the selected content.

Some of the content fields include

- Categories
- Tags
- Display Page URL
- Description
- Publish Date
- Summary
- Title
- Last Editor Name
- Author Name
- Basic Web Content

For more information on developing editable links, see 
[Creating Editable Links](/docs/7-2/reference/-/knowledge_base/r/fragment-specific-tags#creating-editable-links).

Next you'll learn about the Content Page Editing Interface.
