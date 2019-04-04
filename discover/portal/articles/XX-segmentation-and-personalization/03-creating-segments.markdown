# Creating Basic User Segments

To demonstrate the various features of segmentation and its flexibility, 
define two segments for the fictional Westeros Bank site. The segments will use 
user data like the *Job Title* field as well as organization membership for  
evaluating segments. The first Segment, *American Engineers* will use standard 
fields as criteria.

To get started, navigate to the Segments page.

1.  Go to *Site Administration* for the Westeros Bank.

2.  Open the *Members* section and select *Segments*.

## Creating the Engineers Segment

On the Segments page, you see a list of all of the currently available 
segments. If you haven't created any Segments, this will only show the Default 
Segment. First, create a segment named *Engineers* which will be for employees 
of Westeros Bank.

1.  Click the [Add](../../images/icon-add.png) button.

2.  Click in the text area where it says *Unnamed Segment* and enter the name 
    *American Engineers*.
    
3.  For *User Properties* click the edit button, and drag the *Job Title* 
    property over.
    
4.  Click on the the operator field and set it to *contains*.
    
5.  Click on the text field and enter "Engineers".

![Setting the comparator to "contains" will allow variations of "Engineer" like "Software Engineer" be included in the segment.](../../images/sp-set-date.png)

6.  Set the comparator between *User Properties* and *Organization Properties* to *and*.

7.  Click the edit icon for *Organization Properties* and drag over one 
    *Organization* fields.
    
8.  Set the *Organization* field to be *equals* *Westeros America*.
    
![Figure X: You can prevent typos by directly selecting Organizations through the interface.](../../images/sp-select-orgs.png)

9.  Click *Save* to save your Segment.

Remember, as you edit, the number of members that meet the criteria will 
display at the top of the page, and you can click on *Members* to see the list 
of users who meet the criteria. This can be a critical step in determining if 
you are correctly defining the Segment.

![Figure X: You can view the list of Segment members at any time.](../../images/sp-segment-members.png)

Next, you'll define a Custom Field and use it as part of your Segmentation 
criteria.
