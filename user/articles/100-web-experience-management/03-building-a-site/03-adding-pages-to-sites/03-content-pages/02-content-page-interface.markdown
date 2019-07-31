---
header-id: content-page-management-interface
---

# Content Page Management Interface

[TOC levels=1-4]

Content Pages, like Widget Pages, are built by dragging and dropping elements 
on to the page, and then configuring the way those elements display. There are 
several types of elements that you can add to the page.

## Content Page Elements

**Sections** are fragments that define a space to work in. A section fills 
the entire width of the page. Sections can be thought of as *complete*
Fragments which serve a purpose by themselves. A large banner image with a text 
overlay is an example of something you might build as a section.

![Figure 1: A Section named *Banner* being displayed while editing a Content Page.](../../../../../images/content-page-section-example.png)

**Layouts** are special Sections that define spaces where you can add fragments
*or widgets. Each layout you add fills the width of the page. You can add  any
*number of layouts to the page.

![Figure 2: A 3 Column and 1 Column layout stacked on top of each other.](../../../../../images/content-page-layout-example.png)

**Components** are small design elements or pieces which add some functionality 
to the page. A component might be an image with specific formatting or a block 
of text with specific styling pre-applied. Components must be added to the page 
inside of a Layout. If you add a component outside of an existing Layout, a one 
column layout is automatically added with the Component to contain it. 
While Sections should be complete in and of themselves, Components are intended 
to work together to build something piece by piece.

![Figure 3: Here are several of Liferay's out of the box components arranged in the layout you saw previously.](../../../../../images/content-page-component-example.png)

Out of the box, you are provided with a plethora of Layouts, Sections, and 
Components to use to build page, and a
[web developer can create their own Fragments](/docs/7-2/frameworks/-/knowledge_base/f/page-fragments)
to add to these. 

## Editable Elements

Fragments can be 100% static, or they can have editable elements. After a 
Fragment has been added to a page, you can click on an editable area to provide 
your own text, image, or links in place of the default defined in the Fragment.

### Editable Text

Editable text can be plain text or rich text. Plain text can only be simple 
text with no special styling. Rich text provides a number of editing and 
formatting options including different text styles, typographical emphasis, 
alignment, and list formatting.

![Figure 4: The rich text editor provides a simple WYSIWYG interface with a number of formatting options.](../../../../../images/content-page-rich-text-editor.png)

### Editable Images

For an editable image element, you can replace the image with a URL or with an 
image from your Documents and Media library. You can provide a link target for 
the image.

![Figure 5: Editing an image allows you to enter a URL, select an image from Documents and Media or set a link for the image.](../../../../../images/content-page-image-editor.png)

## The Content Page Editing Interface

Unlike Widget Pages, Content Pages can only be edited through the *Site 
Builder* and cannot be edited live on the page. Any edits that you make to a 
page are saved as a draft until you publish the page. Subsequent changes 
after the initial publication are again saved as a draft, without affecting the 
live page, until the page is published again. To create a Content Page,

1.  Go to *Site Management* &rarr; *Site Builder* &rarr; *Pages*.

2.  Click ![Add](../../../../../images/icon-add.png).

3.  On the next page, select *Content Page* and provide a name for the page.

    You will be brought to the Content Page management interface.
    
    ![Figure 6: Each Content Page starts as a blank page.](../../../../../images/content-page-edit-blank-page.png)

To edit an existing Content Page,

1.  Go to *Site Administration* &rarr; *Site Builder* &rarr; *Pages*.

2.  Click *Actions* (![Actions](../../../../../images/icon-staging-bar-options.png))
    &rarr; *Edit* next to the Content Page you want to edit.

You can also get to this page by selecting the *Edit* button
(![Edit](../../../../../images/icon-edit-pencil.png)) from the Control Menu if
you're viewing the published Content Page.

On this page you can view a preview of your page, add Fragments and Widgets, 
and manage the configuration for the page or any Fragments and Widgets 
currently residing on the page.

Your tools for building the page are all found on the right side of the page. 
From top to bottom, the options are

- Sections
- Section Builder
- Widgets
- Page Structure
- Look and Feel

### Sections

When you open *Sections* you see a list of Collections available. Initially, 
you only have the **Basic Sections** Collection which is included with the 
product. You can open the Collection and drag Sections directly onto the page.

![Figure 7: *Sections* contains Fragments that fully define spaces on your page.](../../../../../images/content-page-sections-editor.png)

Once a section is added to a page, you can edit its background color,
background image, and spacing. Since these options are available to marketers
and administrators editing a page, the options are limited, and the color
palette can be set by the Fragment developer.

![Figure 8: The Section managment tool provide powerful tools, but with the training wheels still on.](../../../../../images/content-page-sections-config.png)

### Section Builder

In Section Builder, you start with *Layouts* and *Basic Components*. Add 
Layouts to the page to provide a spaces where you can add Components.

![Figure 9: *Sections Builder* contains *Component* Fragments which are intended to be combined to create Sections.](../../../../../images/content-page-section-builder-editor.png)

### Widgets

The Widgets section functions just like the *Add* menu on a Widget Page. The 
full list of available widgets is displayed, and you can add them to the page.

![Figure 10: The Widgets section provides a list of Widgets that can be added inside of a Layout.](../../../../../images/content-page-widget-editor.png)

The main difference is that only the main configuration options for widgets on 
Content Pages. Various other configurations like *Look and Feel* are only 
available for widgets on Widget Pages.

### Page Structure


Page Structure provides a high level view of every Fragment and every field
within each Fragment on the page.

![Figure 11: *Page Structure* shows you a hierarchy of your page.](../../../../../images/content-page-page-structure-editor.png)

Clicking on a field in Page Structure will  highlight it on the page. On large
complicated page, this helps you keep on top  of where everything is and also
access items that might be hard to click on  directly.

### Look and Feel

The last icon on the right side of the page will bring you to the *Look and 
Feel* configuration for the page where you can change the theme or manage other 
options for the page. These options are fully documented in the
[Page Management](docs/7-2/user/-/knowledge_base/user/creating-pages) section.

### The Title Bar

The title bar provides navigation back to the Main Menu, a link to page
configuration, and the ability to search for other pages. The title bar is
covered in more detail in the
[Page Management](docs/7-2/user/-/knowledge_base/user/creating-pages) section.

![Figure 12: The title bar has several tools built into it.](../../../../../images/content-page-edit-title-bar.png)

Great! Now you know how to use the content page interface!
