# Display Page Example

To see this in practice, you'll create a new display page

To create a new Display Page:

1.  Go to *Build* &rarr; *Pages* in Site Administration.

2.  Go to the *Display Pages* tab.

3.  Click the *Add* button (![Add](../../../../images/icon-add.png)).

4.  Name your Display Page "Lunar Resort Display Page" and click *Submit*.

![Figure X: The Display Page creation interface.](../../../../images/create-display-page.png)

To build the Display Page, you can add any number of Fragments --- both those 
with editable content and those without editable content --- to the page to 
build your design. Any Fragments with editable content can have their editable 
fields mapped to be filled by a Web Content article. You can also base it on a 
specific Web Content Structure.

![Figure X: Editing a Display Page with some Fragments added.](../../../../images/display-page-with-fragments.png)

Notice that in our example, we have an editable title and text body, and a 
static footer with graphics and links. After you have added some fragments to 
the page, you can map them like this:

1.  Click on the *Mapping* tab inside of the *Fragments* section.

2.  Under *Asset Type* click *Select*.

3.  For this example, select *Web Content Article* for the Asset Type and 
    *Basic Web Content* for the subtype.

![Figure X: Selecting the Asset type and Subtype.](../../../../images/display-page-asset-type.png)

4.  Click *Save*.

5.  Now check the box that says *Show Editable Areas*. This will highlight 
    areas that you can map.

6.  Click on an editable text area and then click *Map*. A dialog will appear 
    with a list of fields that can be mapped to that area.
    
7.  Map your *Title* and *Content* to the appropriate area.

8.  Click *Publish* at the top of the page to save your work.

You now have a Display Page with some static graphics and a text area that will 
be replaced with whatever content you add to it.

+$$$

**Note:** You can map any data or metadata from a Web Content Article or 
Structure to a Display Page. For the Basic Web Content type, this includes 
structure defined fields like Summary, Title, and Content, as well as metadata 
fields like Publish Date, Categories, and Tags. In a user-defined structure, all
of the user selected fields would display here as well.

$$$

## Publishing with Display Pages

Now let's create a short article to display with this display page.

1.  Go to *Content* &rarr; *Web Content* in Site Administration.

2.  Add a Basic Web Content article.

3.  Name it "Thoughts About Space", and fill in some short content.

4.  Scroll down open the *Display Page* section and click choose.

5.  Select the *Lunar Resort Display Page* and click *Done*.

6.  Click *Publish*.

![Figure X: Selecting the Asset type and Subtype.](../../../../images/display-page-creating-content.png)

When you click publish, the content will be available to view at it's Friendly 
URL (you can find the Friendly URL while editing a Web Content article under 
*Friendly URL*) or when you click on the content in an Asset Publisher with 
*Asset Link Behavior* set to *View in Context*.

![Figure X: Selecting the Asset type and Subtype.](../../../../images/display-page-in-context.png)


