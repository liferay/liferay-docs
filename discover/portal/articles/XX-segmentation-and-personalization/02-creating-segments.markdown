# Creating User Segments

To demonstrate the various features of segmentation and its flexibility, next 
you will define two segments for the fictional Westeros Bank site. The segments will use two Organizations **Westeros Global** and **Westeros America**. You will create two segments, and then extend each of those segments.

## Creating a Segments

On the Segments page, you see a list of all of the currently available 
segments. If you haven't created any Segments, this will only show the Default 
Segment. To better understand Segments, create a new Segment now. For this 
example, define a segment that will be used to notify members of the Security
and Information Services team that their password and account may need to be updated.

1.  Click the [Add](../../images/icon-add.png) button.

2.  Click in the text area where it says *Unnamed Segment* and enter the name 
    *Account Needs Update*.
    
3.  For *User Properties* click the edit button, and drag the *Date Modified* 
    property over.
    
4.  Click on the the comparator field where it currently says *equals* and set
    it to *less than or equal to*.
    
5.  Click on the date field and select a date a few months in the past. Setting 
    "less than or equal to" a date will include any date older than the 
    selected date.

![Date related fields provide a pop-up calendar for selecting a date.](../../images/sp-set-date.png)

6.  Next, for *Organization Properties* click the edit icon and drag over two 
    *Organization* fields.
    
7.  Click on the operator between the two fields to change it from *and* to 
    *or*.

8.  Set one *Organization* field to be *equals* *Security*, and the other to 
    *equals* *Information Services* (If you don't currently have an 
    Organizations with these names you can create them in *Control Panel* 
    &rarr; *Users and Organizations* or substitute your own Organizations.)
    
![You can use an *And* or an *Or* operator to determine whether a segment requires a user to meet all criteria or any of the listed criteria.](../../images/sp-select-orgs.png)

9.  Click *Save* to save your Segment.

You can now use this Segment to display relevant content or alerts to users who
meet the criteria you defined. 

Remember, as you edit, the number of members that meet the criteria will 
display at the top of the page, and you can click on *Members* to see the list 
of users who meet the criteria. This can be a critical step in determining if 
you are correctly defining the Segment.

![You can view the list of Segment members at any time.](../../images/sp-segment-members.png)
