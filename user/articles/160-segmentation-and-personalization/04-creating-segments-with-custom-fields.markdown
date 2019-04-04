---
header-id: creating-segments-with-custom-fields-and-session-data
---

# Creating Segments with Custom Fields and Session Data

[TOC levels=1-4]

Now that you created a segment using the basic available fields, let's take 
things to the next level, and use a Custom Field to define segment criteria.

## Creating a Custom Field

First, create a custom field to use for the Segment:

1.  In the *Control Panel* go to *Configuration* &rarr; *Custom Fields*.

2.  Click on *User*.

3.  Click the [Add](../../images/icon-add.png) button in the top right.

4.  On the next page, click on *Dropdown*.

5.  For the *Field Name* enter "Cardholder Type".

6.  For values, enter "None," "Basic", "Gold", and "Platinum" on four separate lines.

7.  Click *Save*.

Now any time that a user is created, they will be prompted to enter the 
*Cardholder Type*, and existing users can select it from their user profile.

![Figure 1: You can easily create custom fields to capture whatever kind of data you need.](../../images/sp-create-custom-field.png)

For more information on creating Custom Fields, see [Creating Customer Fields](link)

## Creating the Premium Card Prospects Segment

Next, use a custom field to define another segment. 

1.  From Segments Administration, click the [Add](../../images/icon-add.png) button.

2.  Click in the text area where it says *Unnamed Segment* and enter the name 
    *Premium Card Prospects*.
    
3.  For *User Properties* select *Cardholder Type*.
    
4.  Click on the the operator field and set it to *equals*.
    
5.  Select *Basic* from the select box.

![Figure 2: The custom field you created is seamlessly integrated into segment creation.](../../images/sp-select-custom-field.png)

9.  Click *Save* to save your Segment.

As you can see, segment criteria can be easily defined using Liferay's build in
criteria or your own custom fields. Now, let use session data to make this 
Segment definition even more robust.

## Extending the Segment With Session Data

So far, you've used criteria derived from user profiles to determine whether or 
not they should be members of a segment; now it's time to use session data to
make your criteria more effective.

| Note: For this exercise to work, you would need to have set a cookie on the 
| specified page either through a widget or a JavaScript call.

1.  Click the icon to edit *Session Properties* from the *Premium Card 
    Prospects* segment.
    
2.  Set the comparator for *Session Properties* to *Or*.
    
3.  Drag *Cookies* into the *Session Properties* section.

4.  Change the selection box to *contains*.

5.  Enter "cards" in the first text box.

Now any user who has a cookie from visiting the *Cards* page will also be
evaluated as part of the *Premium Card Prospects* segment.
