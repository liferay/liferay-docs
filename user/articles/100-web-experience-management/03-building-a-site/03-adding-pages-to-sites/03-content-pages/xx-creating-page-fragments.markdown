---
header-id: creating-page-fragments
---

# Creating Page Fragments

[TOC levels=1-4]

Fragments are one of the building blocks that you can use to create rich content
in Liferay. With all of the Fragments and Collections that are included in
@product-ver@, many projects can be completed using only the out-of-the-box
Fragments and capabilities. In many cases, though, you need to create your own
Fragments. Fragments are built using HTML, CSS, and JavaScript. For a deeper
dive into using these languages to develop a custom Fragment, see the
[Developing Fragments](/docs/7-2/frameworks/-/knowledge_base/f/creating-fragments)
article.

In this article, you'll learn about the Page Fragments interface.

## Creating and Managing Fragments

To navigate to the Page Fragments interface,

1.  Go to Site Administration. Make sure the Site where you want to work is 
    selected.

2.  Select *Site Builder* &rarr; *Page Fragments*

![Figure 1: Here is the Page Fragments page with no Fragments or Collections created.](../../../../../images/empty-fragments-page.png)

Fragments are organized in *Collections*. The main Page Fragments page shows
available Collections, provides the option to Import and Export, and enables you
to create Collections. You can also manage the organization and display of
Fragments and Collections once you have them created. To create a Fragment, you
must first create a Collection.

1.  Click *New* &rarr; *Collection* to add a Collection.

2.  Give the Collection a *Name* and *Description* and click *Save*.

Collections help you organize Fragments, and can be used to differentiate 
between different types of Fragments or Fragments used by different groups or 
departments. Next you want to create a Fragment inside the Collection you 
created.

1.  Click on the Collection you created.

2.  Click the *Add* icon (![New](../../../../../images/icon-add.png)) to create
    a Fragment.

3.  Choose either *Section* or *Component*, depending on the Fragment type you
    want to create.

3.  Give it a *Name* and click *Save*.

Now you're looking at the Fragment development environment. Each pane in the
editor has a different function:

- The top left pane is for entering HTML.
- The top right pane is for entering CSS.
- The bottom left pane is for entering JavaScript.
- The bottom right pane provides a live preview as you work in the other panes.

![Figure 2: The Fragments editor provides an environment for creating all the parts of a Fragment.](../../../../../images/fragments-editor.png)

In addition to standard HTML, CSS, and JavaScript, developers can also embed
widgets and provide fields with editable text and images. The text and images
can be edited during the final Content Page publication process. Fragment 
development is covered in depth in 
[Developing Fragments](/docs/7-1/tutorials/-/knowledge_base/t/developing-fragments).

You can also include default resources in your Fragment Collection that your
created Fragments can reference. This is helpful when exporting/importing
Fragments; their resources are automatically included instead of them being
stored somewhere else in Portal (e.g., Documents and Media) and having to
export/import separately. Click the *Resources* tab for your Collection and add
the resource (e.g., image, document, etc.) there. Then you can reference that
resource from your Fragment's code and never have to worry about its
availability. You can learn more about doing this in the
[]()
section.


After some Fragments have been created and published, you can start creating 
Page Templates to combine Fragments into pages.