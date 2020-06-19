---
header-id: creating-fragments
---

# Developing Fragments

[TOC levels=1-4]

This tutorial assumes you understand Fragments and Collections. If you don't,
read the
[Creating Page Fragments](/docs/7-2/user/-/knowledge_base/u/creating-page-fragments)
article first. Once, you're ready, start by creating a Collection:

1.  From the menu for your selected site, click *Site Builder* &rarr; *Page
    Fragments*.
 
2.  Create a new Collection named *Developing Fragments*.

First, you'll create a *Section*.

## Creating a Section

The list of Collections appears on the left in the Page Fragments page.

1.  Ensure that you are in the *Developing Fragments* collection.

2.  Click the *New* button ![New](../../../images/icon-add.png) and select
    *Section*.

3.  Name your Section *Basic Section*

You're now on the Fragment editing page. There are four panes on this screen.
You enter HTML in the top left pane, CSS in the top right, JavaScript in the
bottom left, and preview the results in the bottom right. The Fragment Editor
even comes with autocomplete functionality!

![Figure 1: The Fragment editor provides autocomplete for Liferay Fragment specific tags.](../../../images/fragment-editor-autocomplete.png)

You can look at the three editing panes as if each were writing to a separate 
file. Everything in the HTML pane goes to `index.html`, the CSS pane goes to 
`index.css`, and the JavaScript pane goes to `index.js`. The preview pane
renders everything as it looks on the page. 

| **Warning:** Including images inline in base64 in your Page Fragments can 
| increase publishing, import, and export times for pages using those Fragments. 
| Use 
| [references to resources](/docs/7-2/frameworks/-/knowledge_base/f/including-default-resources-in-fragments) 
| in your Page Fragments instead.

A Section defines a work space. Now create a section with an editable rich text
area where content can be entered:

1.  Add the following code inside the HTML pane:

    ```html
    <div class="banner py-6 py-md-8 text-white" data-lfr-background-image-id="banner">
        <div class="container my-lg-6">
            <div class="row">
                <div class="col-12 col-md-8 col-xl-6">
                    <h1>
                        <lfr-editable id="01-title" type="rich-text">
                            Banner Title Example
                        </lfr-editable>
                    </h1>

                    <div class="mb-4 lead">
                        <p>
                            <lfr-editable id="02-subtitle" type="rich-text">
                                This is a simple banner component that you can use must provide extra information.
                            </lfr-editable>
                        </p>
                    </div>

                    <lfr-editable id="03-link" type="link">
                        <a href="#" class="btn btn-primary">Go Somewhere</a>
                    </lfr-editable>
                </div>
            </div>
        </div>
    </div>
    ```

2.  Replace the code in the CSS pane with the following:

    ```css
    .banner {
        background-color:#415fa9;
        background-position: center;
        background-size: cover;
    }
    ```

3.  Click *Publish* to save your work and make it available to add to a content
    page.

| **Note:** When you start typing the name of a tag, the HTML editor provides 
| auto-completion for `lfr` tags like editable elements and embeddable widgets.

As you work, you can observe your changes in the preview pane.

![Figure 2: The Fragment editor with HTML and CSS code and a live preview.](../../../images/fragment-editor-basic.png)

## Creating a Component

Components are simple, reusable elements for building parts of a page. Next
create a button with a link as a Component:

1.  Go back to the *Site Builder* &rarr; *Page Fragments* page and select the
    *Developing Fragments* Collection.

2.  Click the *New* button ![New](../../../images/icon-add.png) and select
    *Component*.

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
provided by default, so the link must be configured when it is added to the
page.

From here, the Fragment can be added to a Page. To see this process in action, 
see the
[Building Content Pages](/docs/7-2/user/-/knowledge_base/u/building-content-pages)
article.
