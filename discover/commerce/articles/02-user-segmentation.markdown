# User Segmentation [](id=user-segmentation)

User Segmentation allows you to organize users into groups. This allows price
lists, discounts, and other features to be precisely targeted to appropriate
segments.

Segments can be defined in a variety of ways. You can select users individually,
by role, or select whole organizations or user groups to include. You are not
limited to one type; for example you could create a segment that consists of the
members of an organization plus a number of other users that you designate by
role.

+$$$

User segments are not static. At any point, you can return to a segment and make
changes to its membership. Any changes you make will be applied to any price
lists or other features associated with the segment.

$$$

## Creating a User Segment [](id=creating-a-user-segment)

Follow these steps:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *User Segments*.

2.  Click on the ![Add](../images/icon-add.png) button at the top right and
    enter a name for the segment when prompted. Click *Submit*.

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

1.  After creating a segment, click on the *Criteria* tab. Then click on the
    ![Add](../images/icon-add.png) button to open the *Add Criterion* form.

2.  Select a *Type* from the drop-down menu and choose whether to add *Users*,
    *Organizations*, *User Groups*, or *Roles* to the segment.

3.  When you make your choice, a *Select* button appears beneath the drop-down.
    Click on it and then check the box next to each user (or organization, user
    group, or role) you want to include in the segment. Click *Add*.

4.  Set a priority for the criterion and hit *Save*.

5.  To add additional criteria, repeat steps 1 through 4, selecting a different
    type in step 2.

+$$$

Note: When you create a user segment with more than one criteria, the criteria
are combined inclusively. For example, if you make a user segment including
members of the organization "Procurement" in addition to the user group
"Purchasing Managers", the resulting segment would include every user who is
a member of *either* group. It would not be limited to users who are members of
*both*.

$$$
