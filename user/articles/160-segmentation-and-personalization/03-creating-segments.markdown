---
header-id: creating-user-segments
---

# Creating User Segments

[TOC levels=1-4]

To learn how to use segmentation, define two segments for the fictional Westeros
Bank site. The segments use user data like the *Job Title* field and
organization membership for evaluating segments. The first Segment, *American
Engineers* uses standard fields as criteria.

To get started, navigate to the Segments page.

1.  Go to *Site Administration* for the Westeros Bank.

2.  Open the *Members* section and select *Segments*.

## Creating the Engineers Segment

On the Segments page, you see a list of all currently available segments. If you
haven't created any Segments, this shows only the Default Segment. First, create
a segment named *Engineers* for employees of Westeros Bank.

1.  Click the [Add](../../images/icon-add.png) button.

2.  Click in the text area where it says *Unnamed Segment* and enter the name 
    *American Engineers*.

3.  For *User Properties* click the edit button and drag the *Job Title* 
    property over.

4.  Click on the operator field and set it to *contains*.

5.  Click on the text field and enter *Engineers*.

    ![Figure 1: Setting the comparator to *contains* includes variations of "Engineer" like "Software Engineer" in the segment.](../../images/sp-set-date.png)

6.  Set the comparator between *User Properties* and *Organization Properties* to *and*.

7.  Click the edit icon for *Organization Properties* and drag over an
    *Organization* field.

8.  Set the *Organization* field to be *equals* *Westeros America*.

    ![Figure 2: You can prevent typos by directly selecting Organizations through the interface.](../../images/sp-select-orgs.png)

9.  Click *Save* to save your Segment.

As you edit, a count of members meeting the criteria appears at the top of the
page. You can click on *Members* to see the list. This helps you determine if
you are correctly defining the Segment.

![Figure 3: You can view the list of Segment members at any time.](../../images/sp-segment-members.png)

## Managing Segments

After you create your Segment, you can see it in the list of Segments on the 
*People* &rarr; *Segments* page. From there you can edit the segment, delete 
it, or change the permissions for it. Changing permissions only affects who has 
access to manage the Segment; it doesn't change Segment membership or criteria.

1.  Go to *People* &rarr; *Segments*.

2.  Click [Options](../../images/icon-options.png) Next to the *American 
    Engineers*.

You can manage the options here. You can also click on the Segment's name to
edit it.

![Figure 3: You can edit, delete or manage permissions from the options menu.](../../images/sp-options.png)

Next, you'll define a Custom Field and use it as part of your Segmentation 
criteria.
