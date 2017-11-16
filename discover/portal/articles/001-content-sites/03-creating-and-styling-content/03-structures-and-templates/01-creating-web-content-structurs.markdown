# Creating Structures

Now you'll create your first structure. This structure is for content on the 
Lunar Guides page, which needs a picture of each guide as well as their name and 
a link to their personal page. 

## Creating Your First Structure

1.  Open the *Menu* 
    (![Product Menu](../../../../images/icon-menu.png)) and select *The Lunar 
    Resort* &rarr; *Content* &rarr; *Web Content*. 

2.  At the top-right of the screen, select *Options* 
    (![Options](../../../../images/icon-options.png)) &rarr; *Structures*. This 
    takes you to the Structures page, which lists the available structures. 
    Currently, only the default Basic Web Content structure is available. 

    ![Figure x: Select *Structures* from the *Options* menu.](../../../../images/001-select-structures.png)

3.  Click the *Add* icon 
    (![Add](../../../../images/icon-add.png)) at the bottom-right of the page to 
    begin creating a new structure. This takes you to the structure creation 
    page. 

4.  Name the structure *Lunar Guides List*. 

5.  Now you're ready to define the structure's fields. You do this by dragging 
    and dropping the available field types from the *Fields* tab to the blank 
    canvas on the right. 

    ![Figure x: These fields are available when creating a structure.](../../../../images/001-structure-fields.png)

    This structure needs a Text field for a title, another Text field for the 
    guide's name, an Image field for the guide's image, and a Link to Page field 
    for the link to the guide's personal page. Follow these instructions to add 
    these fields:

    - Drag a *Text* field onto the canvas. 
    - Drag another *Text* field onto the canvas, below the first one.
    - Drag an *Image* field into the second Text field, to nest it under that 
    Text field. This creates a *field group*. 
    - Drag a *Link to Page* field into the field group, below the Image field. 
    Be careful to position this Link to Page field so that it's on the same 
    level as the Image field (not nested in the Image field). 

    ![Figure x: The canvas should look like this after you add the Text, Image, and Link to Page fields. Note that the Image and Link to Page fields are nested in the second Text field.](../../../../images/001-structure-group.png)

6.  Click the first Text field. In place of the available fields to the left of 
    the canvas, you now see a list of settings for the selected field. Change 
    the following settings: 

    - **Field Label:** This is the field's title that users see when creating 
    content with this structure. Set this to *Title*. 
    - **Name:** This is the field's internal name that you can access in a 
    template. Set this to *title*. 

7.  Click the second Text field. Change its *Field Label* to *Lunar Guide Name*, 
    and its *Name* to *name1*. 

8.  Click the *Image* field. Change its *Name* to *image1*. 

9.  Click the *Link to Page* field. Change its *Name* to *link1*. 

10. There are four lunar guides, so you need four field groups. Fortunately, you 
    don't have to create each additional field group manually. Hover over the 
    Lunar Guide Name field and click the *Duplicate* button 
    (![Add](../../../../images/icon-add-2.png)) that appears to the field's 
    right. This duplicates the entire field group. Click this button two more 
    times, so you have a total of four identical field groups. 

    +$$$

    **Note:** Instead of duplicating fields, you could have used each field's 
    *Repeatable* option. This lets users decide how many fields or field groups 
    to use when they create content. This is a more advanced option that you'll 
    learn about a bit later. For now, you'll keep things simple. 

    $$$

11. Now you'll change the duplicate fields' names. Repeat steps 7 through 9 
    above for each duplicate field group, matching the digit in each field name 
    to the field group's number. For example, name the second field group's 
    *Name*, *Image*, and *Link to Page* fields *name2*, *image2*, and *link2*, 
    respectively. Likewise, the digit in each field name should be *3* for the 
    third field group, and *4* for the fourth field group.

12. Click *Save*.

Great! Next, you'll create the template to go along with this structure. 
