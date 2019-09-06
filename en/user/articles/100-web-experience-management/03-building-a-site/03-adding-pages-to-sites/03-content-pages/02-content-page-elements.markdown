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

### Editable Text

Editable text can be plain or rich text. Plain text has no special styling. Rich
text enables text styles, typographical emphasis, alignment, and list
formatting.

![Figure 4: The rich text editor provides a simple WYSIWYG interface with a number of formatting options.](../../../../../images/content-page-rich-text-editor.png)

### Editable Images

Editable image elements allow replacing the image URL or an image from your
Documents and Media library. You can provide a link target for the image.

![Figure 5: Editing an image allows you to enter a URL, select an image from Documents and Media or set a link for the image.](../../../../../images/content-page-image-editor.png)

Next you'll learn about the Content Page Editing Interface.
