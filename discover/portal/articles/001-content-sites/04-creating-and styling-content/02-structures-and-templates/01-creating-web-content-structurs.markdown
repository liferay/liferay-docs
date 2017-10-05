# Creating Web Content Structures

Now that you have a little bit of an understanding of what web content 
structures do, let's create one. The first one that you'll create is for the
*Lunar Guides* page. The final product will have a picture for each of our
Lunar Guides as well as their name, with a link to their personal page.

## Creating Your First Structure

1. Open the Main Menu and scroll down to Site Administration for The Lunar Resort.

2. Open *Content* and select *Web Content*

3. In the top right of the screen, click the menu and select Structures.

![Figure x: Navigating to Structures](../../../images/001-select-structures.png)

On this page you'll see a list of available structures. At this time, only the 
Basic Web Content provided by default is available.

4. Click the '+' button to add a new structure.

This will take you to the structure creation page. Here you will enter a Name
for your structure and build the structure from the available fields. You build 
a structure by dragging the fields you need. You can also nest fields, so that 
certain fields are seen as a group for the sake of building the structure, and
to help provide visual cues for any users creating content using this structure.

![Figure x: Navigating to Structures](../../../images/001-structure-fields.png)

For the Lunar Guides structure you need an text for the name, an image, and a 
link.


1. Drag a *Text* object onto the content area.

2. Drag an *Image* into the *Text* field to nest it.

3. Drag a *Link to Page* into the same group.

![Figure x: First Structure object](../../../images/001-structure-group.png)

4. Now click on the *Text* field that you added.

Where the fields were listed before, you now see a list of settings for that
particular field. The two you're most interested in here are *Field Label* and 
*Name*. The Field Label is the title for the field that users 
will see when they're creating content with this structure. The Name is what 
you'll need to use to access the data in this field when you create a Template. 

5.  Change the *Field Label* to "Lunar Guide Name".

6.  Change the *Name* to "name1".

7.  Click on the *Image* field.

8.  Change the *Name* to "image1".

9.  Click on the *Link to Page* field.

10. Change the *Name* to "link1".

What you've created is a structure with a name, image, and link. We have four
guides, so we want to provide four slots to place the information into. Let's 
create the additional fields.

1. Click the '+' button on the Lunar Guide Name field. This will duplicate the
    whole group

2. Click it two more times.

This generated the additional fields, but we need to make sure that they have 
the right names to be managed in the templates.

3. Click on the second Lunar Guide Name field and change the *Name* to "name2".

4. Click on the second Image field and change the *Name* to "image2".

5. Click on the second Link to Page field and change the *Name* to "link2".

6. Repeat these steps, iterating the number for the other two groups of fields 
    ("name3", "name4", etc.)

+$$$

You created four items because you know that we have four Lunar Guides, and 
when you create the template, the styling will be based on knowing the you have
four items to display. In other cases, you could have used the "Repeatable" 
option for fields to simply create one in the structure, and allow users to
add as many fields (or groups of fields) of that type as they want. For this 
example, we're keeping it a little bit simpler, but we'll get to the more 
complex stuff a little later.

+$$$

Next you'll create the Template to go along with this Structure.

