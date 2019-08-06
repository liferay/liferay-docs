---
header-id: creating-segments-with-custom-fields-and-session-data
---

# Creating Segments with Custom Fields and Session Data

[TOC levels=1-4]

Now that you created a segment, you can take things to the next level and use
a Custom Field to define segment criteria.

## Creating a Custom Field

First, create a custom field to use for the Segment:

1.  In the *Control Panel* go to *Configuration* &rarr; *Custom Fields*.

2.  Click on *User*.

3.  Click the *Add* button (![Add](../../images/icon-add.png)) button in the top
    right.

4.  On the next page, click on *Dropdown*.

5.  For the *Field Name* enter *Cardholder Type*.

6.  For values, enter *None,* *Basic*, *Gold*, and *Platinum* on four separate
    lines.

7.  Click *Save*.

Now any time user is created, they are prompted to enter the *Cardholder Type*,
and existing users can select it from their user profiles.

![Figure 1: You can easily create custom fields to capture whatever kind of data you need.](../../images/sp-create-custom-field.png)

For more information on adding Custom Fields, see
[Adding Custom Fields](/docs/7-2/user/-/knowledge_base/u/custom-fields#adding-custom-fields).

## Defining a Segment with a Custom Field

Next, use a custom field to define another segment. 

1.  From Segments Administration, click the *Add* button
    (![Add](../../images/icon-add.png)).

2.  Click in the text area at the top of the page, enter the name *Premium Card
    Prospects*.

3.  For User Properties select *Cardholder Type*.

4.  Click on the operator field and set it to *equals*.

5.  Select *Basic* from the select box.

![Figure 2: The custom field you created is seamlessly integrated into segment creation.](../../images/sp-select-custom-field.png)

9.  Click *Save* to save your Segment.

As you can see, segment criteria can be easily defined using @product@'s
built-in criteria or your own custom fields. Now, you'll use session data to
make this Segment definition even more robust.

## Extending a Segment With Session Data

So far, you've used criteria derived from user profiles to determine if they
should be members of a segment; now it's time to use session data to make your
criteria more effective.

| **Note:** For this exercise to work, you must have set a cookie on the 
| specified page.

1.  Click the *Actions* button (![Actions](../../images/icon-actions.png)) next
    to the *Premium Card Prospects* segment and select *Edit*.

2.  Click the *Session* dropdown from the Properties menu.
 
3.  Set the comparator for Session Properties to *Or*.

4.  Drag *Cookies* into the Session Properties section.

5.  Change the selection box to *contains*.

6.  Enter *Cards* in the Key text box.

Now any user who has a cookie from visiting the *Cards* page is evaluated as
part of the *Premium Card Prospects* segment.
