---
header-id: display-page-template-example
---

# Display Page Template Example

[TOC levels=1-4]

Create a new Display Page Template:

1.  Go to *Site Builder* &rarr; *Pages* in Site Administration.

2.  Go to the *Display Page Templates* tab.

3.  Click the *Add* button (![Add](../../../../../images/icon-add.png)).

4.  Name your Display Page *Lunar Resort Display Page Template*, open the 
    *Content Type* dropdown and select *Web Content Article* and 
    *Basic Web Content* for the Subtype, and click *Save*.

![Figure 1: Selecting the Asset type and Subtype.](../../../../../images/display-page-asset-type.png)

![Figure 2: The Display Page Template creation interface.](../../../../../images/create-display-page.png)

To build the Display Page Template, you can add any number of Fragments---with
and without editable content---to the page to build your design. Fragments with
editable content can have their editable fields mapped to be filled by a Web
Content article. You can also base it on a specific Web Content Structure.

![Figure 3: Editing a Display Page Template with some Fragments added.](../../../../../images/display-page-with-fragments.png)

Notice that the example has an editable title and text body, with a static
footer containing graphics and links. After you've added some fragments to the
page, you can map them like this:


1.  Navigate to the *Section Builder* &rarr; *Basic Components* tab in the right
    menu.

2.  Add a *Heading 1* and *Text* components to the appropriate areas.

3.  Click on an editable text area and click the *Map* button (![Map](../../../../../images/icon-map.png)) 
    in the dialog that appears.

4.  Select a field to map the editable fragment to. The mapped field highlights 
    purple to indicate that it's mapped.

    ![Figure 4: Mapping the editable fragments to structure fields.](../../../../../images/display-page-map-field.png)

5.  Click *Publish* at the top of the page to save your work.

<!--5.  Now check the box that says *Show Editable Areas*. This highlights
areas that you can map.-->

You now have a Display Page Template with static graphics and a text area that's 
replaced with whatever content you add to it.

| **Note:** You can map any data or metadata from a Web Content Article or
| Structure to a Display Page Template. For the Basic Web Content type, this includes
| structure-defined fields like Summary, Title, and Content, as well as metadata
| fields like Publish Date, Categories, and Tags. In a user-defined structure, all
| user selected fields appear here as well. Custom fields are also available
| for display if they apply to the content type selected.

For more information on the right panel and the Fragment options available to
you, see 
[Content Page Elements](/docs/7-2/user/-/knowledge_base/u/content-page-elements)
and
[Content Page Interface](/docs/7-2/user/-/knowledge_base/u/content-page-management-interface). 
Content Pages and Display Page Templates share the same Page builder
tools.

## Publishing with Display Page Templates

Now create a short article to display with this display page template:

1.  Go to *Content & Data* &rarr; *Web Content* in Site Administration.

2.  Add a Basic Web Content article.

3.  Name it *Thoughts About Space* and fill in some short content.

4.  Open the *Display Page Template* section and under the dropdown, select
    *Specific Display Page Template*. Then click *Select*. 

5.  Select the *Lunar Resort Display Page Template* and click *Done*.

6.  Click *Publish*.

![Figure 5: Selecting the Asset type and Subtype.](../../../../../images/display-page-creating-content.png)

When published, you can view the content at its Friendly URL (you can find the
Friendly URL while editing a Web Content article under *Friendly URL*) or when
you click on the content in an Asset Publisher with *Asset Link Behavior* set to
*View in Context*.

When editing the article, you can preview what the display page template will 
look like with the *Preview* (![Preview Template](../../../../../images/icon-preview.png)) 
button located next to the selected display page template.

![Figure 6: Selecting the Asset type and Subtype.](../../../../../images/display-page-in-context.png)

You can go back and edit the display page template by navigating to *Site
Administration* &rarr; *Site Builder* &rarr; *Pages* &rarr; *Display Page
Templates* and clicking *Actions*
(![Actions](../../../../../images/icon-staging-bar-options.png)) &rarr; *Edit*
next to the display page template you want to edit. If you're viewing the
published display page, you can also select the *Edit* button
(![Edit](../../../../../images/icon-edit-pencil.png)) from the Control Menu.

Awesome! You now know how to create and configure a display page using display
page templates.
