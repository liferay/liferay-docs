# Creating Fragments [](id=creating-a-fragment)

If you aren't sure about the basics of Fragments and Collections, you should 
read [Creating Page Fragments](link) first so you know what you're getting 
yourself into. Once, you're ready, start by creating the Collection:

1.  Go to *Site Administration* and for your selected site click *Build*
    &rarr; *Fragments*.
 
2.  Create a new Collection named *Developing Fragments*.

Next, create a *Section* and a *Component*.

## Creating a Section

The list of Collections appears on the left in the Page Fragments page.

1.  Ensure that you are in the *Developing Fragments* collection.

2.  Click the [Add](../../../images/icon-add.png) button and select *Section*.

3.  Name your Section "Basic Section"

You're now on the Fragment editing page. There are four panes on this screen.
You enter HTML in the top left pane, CSS in the top right, JavaScript in the
bottom left, and preview the results in the bottom right.

![Figure 1: The Fragment editor provides autocomplete for Liferay Fragment specific tags..](../../../images/fragment-editor-autocomplete.png)

You can look at the three editing panes as if each were writing to a separate 
file. Everything in the HTML pane goes to `index.html`, the CSS pane goes to 
`index.css`, and the JavaScript one goes to `index.js`. The preview pane renders
everything as it will look on the page. Here's how this works:

1.  Add the following code inside the HTML pane:
    **NEED A NEW EXAMPLE**

2.  Add the following code to the CSS pane inside the fragment block:

3. Click *Publish* to save your work and make it available to add to a content
   page.

| Note: When you start typing the name of a tag, the HTML editor provides 
| autocompletion for `lfr` tags like editable elements and embeddable widgets.

As you work, you can observe the preview in the preview pane.

![Figure 2: The Fragment editor with HTML and CSS code and a live preview.](../../../images/fragment-editor-basic.png)

## Creating a Component

Next create a Component:

1.  Add the following code inside the HTML pane:
    **NEED A NEW EXAMPLE**

2.  Add the following code to the CSS pane inside the fragment block:

3.  Click *Publish* to save your work and make it available to add to a content
    page.

From here, the Fragment can be added to a Page. To see this process in action, 
see the [Building Content Pages from Fragments](link) tutorial. Next, you'll 
explore some Fragment-specific tags. 

## Managing Fragments and Collections

After you create Collections and Fragments, you have a handful of options for 
managing them. To access the collections management menu,

1.  Select the Collection you want to manage from the *Collections* list.

2.  Click on the [Options](../../../images/icon-options.png) menu next to the 
    collection name.
    
3.  Select whether you want to *Edit*, *Export*, *Import*, or *Delete* the 
    collection.

**Edit**: change the name or description for the collection.

**Export**: download a `.zip` file containing the full collection.

**Import**: select a `.zip` file to upload with additional Fragments.

**Delete**: delete the current collection and all of its contents.

To access the fragment management menu,

1.  Select the Collection containing the Fragment you want to manage from the 
    *Collections* list.
    
2.  Click on the [Options](../../../images/icon-options.png) menu next to the 
    Fragment name.

3.  Select whether you want to *Edit*, *Rename*, *Move*, *Make a Copy*, *Change Thumbnail* *Export*, or *Delete* 
