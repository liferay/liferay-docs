---
header-id: creating-fragments
---

# Creating Fragments

[TOC levels=1-4]

This tutorial assumes you understand Fragments and Collections. If you don't,
read [Creating Page Fragments](user-guide-link) first. Once, you're ready, start
by creating a Collection:

1.  From the menu for your selected site, click *Site Builder* &rarr; *Page
    Fragments*.
 
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

![Figure 1: The Fragment editor provides autocomplete for Liferay Fragment specific tags.](../../../images/fragment-editor-autocomplete.png)

You can look at the three editing panes as if each were writing to a separate 
file. Everything in the HTML pane goes to `index.html`, the CSS pane goes to 
`index.css`, and the JavaScript pane goes to `index.js`. The preview pane
renders everything as it looks on the page. 

A Section defines a work space. Now create a section with an editable rich text
area where content can be entered:

1.  Add the following code inside the HTML pane:

    ```html
    <div class="basic-section">
        <div class="container">
	        <div class="row">
                <div class="card-body">
			        <lfr-editable id="card00" type="rich-text">
				        <p class="card-text">This is a space for the user to enter content</p>
    				</lfr-editable>
        		</div>
	        </div>
    	</div>
    </div>
    ```

2.  Replace the code in the CSS pane with the following:

   ```css
   .basic-section {
   }

   .basic-section .aspect-ratio {
   	    max-width: inherit;
        width: 100%;
   }
   ```

3. Click *Publish* to save your work and make it available to add to a content
   page.

| Note: When you start typing the name of a tag, the HTML editor provides 
| auto-completion for `lfr` tags like editable elements and embeddable widgets.

As you work, you can observe the preview in the preview pane.

![Figure 2: The Fragment editor with HTML and CSS code and a live preview.](../../../images/fragment-editor-basic.png)

## Creating a Component

Components simple, reusable elements for building a parts of a page. Next create
a button with a link as a Component:

1.  Go back to the *Page Fragments* page, and the *Developing Fragments* 
    Collection.

2.  Click the [Add](../../../images/icon-add.png) button and select *Component*.

3.  Name it *Basic Component*.

4.  Back in the editor, add the following code inside the HTML pane:

   ```html
   <div class="basic-link-button">
   	<lfr-editable id="btn00" type="link">
   		<a href="#" class="btn btn-primary">Read More</a>
   	</lfr-editable>
   </div>
   ```

5.  Click *Publish* to save your work and make it available to add to a content 
    page.

This fragment did not require any CSS. For the button link, no target is 
provided by default, so the link will need to be configured when it is added
to the page.

From here, the Fragment can be added to a Page. To see this process in action, 
see the [Building Content Pages from Fragments](user-guide-link) tutorial.

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

3.  Select whether you want to *Edit*, *Rename*, *Move*, *Make a Copy*, *Change 
    Thumbnail* *Export*, or *Delete*.

Now that you've seen how to use Liferay's built-in tools to manage Fragments, 
you can see how to do it using your own tools of choice and the Fragments CLI.
