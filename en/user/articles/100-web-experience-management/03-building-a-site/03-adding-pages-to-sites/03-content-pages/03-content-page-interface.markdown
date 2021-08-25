---
header-id: content-page-management-interface
---

# Content Page Management Interface

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/site-building/creating-pages/building-and-managing-content-pages/content-pages-overview.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Unlike Widget Pages, Content Pages can only be edited through the *Site 
Builder* and cannot be edited live on the page. Any edits that you make to a 
page are saved as a draft until you publish the page. Subsequent changes 
after the initial publication are again saved as a draft, without affecting the 
live page, until the page is published again. To create a Content Page,

1.  Go to *Site Management* &rarr; *Site Builder* &rarr; *Pages*.

2.  Click ![Add](../../../../../images/icon-add.png).

3.  On the next page, select *Content Page* and provide a name for the page.

    You will be brought to the Content Page management interface.
 
    ![Figure 1: Each Content Page starts as a blank page.](../../../../../images/content-page-edit-blank-page.png)

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

- [Sections](#sections)
- [Section Builder](#section-builder)
- [Widgets](#widgets)
- [Page Structure](#page-structure)
- [Look and Feel](#look-and-feel)

### Sections

When you open *Sections* you see a list of Collections available. Initially, 
you only have the **Basic Sections** Collection which is included with the 
product. You can open the Collection and drag Sections directly onto the page.

![Figure 2: *Sections* contains Fragments that fully define spaces on your page.](../../../../../images/content-page-sections-editor.png)

Once a section is added to a page, you can edit its background color,
background image, and spacing. Since these options are available to marketers
and administrators editing a page, the options are limited, and the color
palette can be set by the Fragment developer.

![Figure 3: The Section managment tool provide powerful tools, but with the training wheels still on.](../../../../../images/content-page-sections-config.png)

### Section Builder

In Section Builder, you start with *Layouts* and *Basic Components*. Add 
Layouts to the page to provide a spaces where you can add Components.

![Figure 4: *Sections Builder* contains *Component* Fragments which are intended to be combined to create Sections.](../../../../../images/content-page-section-builder-editor.png)

### Widgets

The Widgets section functions just like the *Add* menu on a Widget Page. The 
full list of available widgets is displayed, and you can add them to the page.

![Figure 5: The Widgets section provides a list of Widgets that can be added inside of a Layout.](../../../../../images/content-page-widget-editor.png)

The main difference is that only the main configuration options are available for widgets on 
Content Pages. Various other configurations like *Look and Feel* are only 
available for widgets on Widget Pages.

### Page Structure

Page Structure provides a high level view of every Fragment and every field
within each Fragment on the page.

![Figure 6: *Page Structure* shows you a hierarchy of your page.](../../../../../images/content-page-page-structure-editor.png)

Clicking on a field in Page Structure will  highlight it on the page. On large
complicated page, this helps you keep on top  of where everything is and also
access items that might be hard to click on  directly.

### Look and Feel

Click the *Look and Feel* icon 
(![Look and Feel](../../../../../images/icon-look-and-feel.png)) 
to change the theme or manage other options for the page. These options are 
fully documented in 
[Creating Pages](/docs/7-2/user/-/knowledge_base/u/creating-pages). 

### Comments

You can also comment on any page fragments. This allows discussion and 
collaboration for teams creating content pages. 

Comments are disabled by default, but administrators can enable them from 
*Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; *Pages* 
&rarr; *Content Page Editor*. Select the *Comments Enabled* checkbox and click 
*Update*. This enables content page comments for all instances. To control this 
on an instance-by-instance basis, navigate to the same setting in 
*Instance Settings* (instead of System Settings). 

![Figure 7: Administrators can enable comments for content pages.](../../../../../images/enable-content-page-comments.png)

If comments are enabled, you can access them via the *Comments* icon 
(![Comments](../../../../../images/icon-comments.png)). 
The comments appear for the selected fragment. You can take the following 
actions in the comments UI for a fragment:

-   Add new comments and reply to any existing ones. 
-   Resolve comments by clicking the checkbox for each. Resolving a comment 
    hides it from view, unless *Show Resolved Comments* is selected. 
-   Edit and delete your own comments via the Actions button 
    (![Actions](../../../../../images/icon-actions.png)) 
    for each. 

If you de-select a fragment or enter the comments UI without a fragment selected, 
a list of the fragments on the page appears with the number of comments for 
each. Selecting a fragment then shows its comments. 

![Figure 8: When creating content pages, you and your team can comment on any fragments.](../../../../../images/content-page-comments.png)

### The Title Bar

The title bar provides navigation back to the Main Menu, a link to page
configuration, and the ability to search for other pages. The title bar is
covered in more detail in 
[Creating Pages](/docs/7-2/user/-/knowledge_base/u/creating-pages). 

![Figure 9: The title bar has several tools built into it.](../../../../../images/content-page-edit-title-bar.png)

Great! Now you know how to use the content page interface!
