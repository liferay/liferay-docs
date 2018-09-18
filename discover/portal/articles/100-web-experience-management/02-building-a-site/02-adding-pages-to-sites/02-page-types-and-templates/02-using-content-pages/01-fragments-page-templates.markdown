# Creating Page Fragments [](id=creating-fragments)

To create Content Pages, you must first have some Fragments. Fragments are one
of the building blocks that you can use to create rich content in Liferay.
Fragments are intended to be created by developers as they are built using HTML,
CSS, and JavaScript.

## Creating and Managing Fragments [](id=creating-and-managing-fragments)

Start in Site Administration. You can find Fragments in the Content section.

1.  Open the main menu.

2.  Under *Site Administration*, make sure the Site where you want to work is 
    selected.

3.  In the *Build* section, select *Page Fragments*.

![Figure 1: Here is the Page Fragments page with no Fragments or Collections created.](../../../../../../images/empty-fragments-page.png)

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

2.  Click the *Add* icon (
    ![New](../../../../../../images/icon-add.png)) to create a Fragment.

3.  Give it a *Name* and click *Submit*.

Now you're looking at the Fragment development environment. Each pane in the
editor has a different function:

*  The top left pane is for entering HTML.
*  The top right pane is for entering CSS.
*  The bottom left pane is for entering JavaScript.
*  The bottom right pane provides a live preview as you work in the other panes.

![Figure 2: The Fragments editor provides an environment for creating all the parts of a Fragment.](../../../../../../images/fragments-editor.png)

In addition to standard HTML, CSS, and JavaScript, developers can also embed
widgets and provide fields with editable text and images. The text and images
can be edited during the final Content Page publication process. Fragment 
development is covered in depth in 
[Developing Fragments](/develop/tutorials/-/knowledge_base/7-1/developing-fragments).

After some Fragments have been created and published, you can start creating 
Page Templates to combine Fragments into pages.
