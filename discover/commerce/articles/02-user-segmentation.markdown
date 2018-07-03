# User Segmentation [](id=user-segmentation)

User Segmentation organizes users into groups, so price lists, discounts, and
other features can be precisely targeted to those users.

You can define segments by individual users, by Role, or by whole Organizations
or User Groups. You are not limited to one type: a segment can have members of
an Organization plus other users that you designate by Role.

+$$$

User segments are not static. At any point, you can return to a segment and make
changes to its membership. Any changes you make will be applied to any price
lists or other features associated with the segment.

$$$

## Creating a User Segment [](id=creating-a-user-segment)

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *User Segments*.

2.  Click ![Add](../images/icon-add.png) and enter a name for the segment when
    prompted. Click *Submit*.

3.  Click on the user group to edit it. Fill in the following fields:

    **Key:** A unique identifier for referring to this segment programmatically.
    This field is filled automatically, so most users can safely leave it alone.

    **Priority:** If a user is in more than one segment and a conflict occurs,
    the segment with the higher priority is used. Segments are prioritized in
    numerical order.

    **Active:** Uncheck this box to disable the segment and treat its users as
    if they were not members.

4.  Click *Save*.

Now you've created a user segment, but it has no members. Follow these steps to
add users:

1.  After creating a segment, choose the *Criteria* tab. Then click 
    ![Add](../images/icon-add.png) to open the *Add Criterion* form.

2.  Select a *Type* from the drop-down menu and choose *Users*, *Organizations*,
    *User Groups*, or *Roles* to add. 

3.  When you make your choice, a *Select* button appears beneath the drop-down.
    Click it and then check the box next to each User (or Organization, User
    Group, or Role) to include in the segment. Click *Add*.

4.  Set a priority for the criterion and hit *Save*.

5.  To add additional criteria, repeat steps 1 through 4, selecting a different
    type in step 2.

+$$$

Note: When you create a user segment with more than one criterion, the criteria
are combined inclusively. For example, if a user segment includes members of the
Organization *Procurement* and the User Group *Purchasing Managers*, the
resulting segment includes every user who is a member of *either* group. It is
not limited to users who are members of *both*.

$$$
