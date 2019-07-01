---
header-id: creating-user-segments
---

# Creating User Segments

[TOC levels=1-4]

To learn how to use segmentation, you'll step through an example for defining
two segments for a site. The segments use user data like the *Job Title* field
and organization membership for evaluating segments. The first Segment you'll
create, *American Engineers*, uses standard fields as criteria.

To get started, navigate to the Segments page.

1.  Go to *Site Administration* for the Bank site.

2.  Open the *People* section and select *Segments*.

## Creating a Custom Segment

On the Segments page, you'll see a list of all the currently available segments,
if they're available. Create a segment named *American Engineers* for employees
of your company.

1.  Click the *Add* button (![Add](../../images/icon-add.png)) button.

2.  Click in the top text area and enter the name *American Engineers*.

3.  Open the *User* tab in the right side *Properties* menu and drag the *Job
    Title* property to the *Conditions* area.

4.  Click on the operator field and set it to *contains*.

5.  Click on the text field and enter *Engineer*.

    ![Figure 1: Setting the comparator to *contains* includes variations of "Engineer" like "Software Engineer" in the segment.](../../images/sp-set-date.png)

6.  Open the *Organization* properties and drag over an *Organization* field.

7.  Set the comparator between *User Properties* and *Organization Properties* to
    *And*.

8.  Set the *Organization* field to be *equals* and select the organization.

    ![Figure 2: You can prevent typos by directly selecting Organizations through the interface.](../../images/sp-select-orgs.png)

9.  Click *Save* to save your Segment.

As you edit, a count of members meeting the criteria appears at the top of the
page. You can click on *View Members* to see the list. This helps you determine
if you are correctly defining the Segment.

![Figure 3: You can view the list of Segment members at any time.](../../images/sp-segment-members.png)

## Managing Segments

After you create your Segment, you can see it in the list of Segments on the 
*People* &rarr; *Segments* page. From there you can edit the segment, delete 
it, or change the permissions for it. You cannot delete a segment if it's being
used in an experience. Also, changing permissions only affects who has 
access to manage the Segment; it doesn't change Segment membership or criteria.

1.  Go to *People* &rarr; *Segments*.

2.  Click the *Actions* button (![Actions](../../images/icon-actions.png)) next
    to the *American Engineers*.

You can manage the options here. You can also click on the Segment's name to
edit it.

![Figure 4: You can edit, delete or manage permissions from the options menu.](../../images/sp-options.png)

Next, you'll define a Custom Field and use it as part of your Segmentation 
criteria.
