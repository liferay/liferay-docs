---
header-id: creating-segments
---

# Creating Segments

Individuals share common properties and behavior. Aggregating them into Segments
helps you understand groups of similar people. Segment analytics gives you
insight into people's interests and activities. In the navigation panel, 
*Segments* is at the top of the *People* section because it's where you'll spend 
most of your time gaining customer insights. 

![Figure 1: You can access Segments in the navigation panel.](../../images/ac-segments-panel.png)

There are two types of Segments: 

[**Dynamic Segments:**](#dynamic-segments) 
Individuals aggregated automatically based on criteria that you specify. 
Criteria can be based on Individuals' properties and interaction with your 
Liferay DXP Site pages and assets. For example, you can use an *industry* 
property as criteria. Analytics on industry Segments show which industries 
engage with you the most, and what content they're interested in. 

[**Static Segments:**](#static-segments) 
Individuals aggregated manually. Static Segments are therefore comprised of 
hand-selected individuals. 

## Dynamic Segments

Follow these steps to create a Dynamic Segment:

1.  Click *Segments* in the navigation panel. A table with existing Segments 
    appears.

2.  Click *Create Segment* &rarr; *Dynamic Segment*. The 
    *Create Individuals Segment* screen appears for creating a Dynamic Segment. 

3.  Click the *Edit* icon next to *Unnamed Segment*, and give your segment a 
    name. 

4.  To create your Segment's criteria, drag and drop them from the panel on the 
    right to the canvas on the center of the screen. The selector menu at the 
    top of the panel lets you select from these criteria types: 

    **Individual Properties:** Properties that belong to a person (e.g., age, 
    country, industry, etc.). 

    **Account Properties:** Properties that belong to a Salesforce Account 
    (e.g., account name, industry, website, etc.). 

    **Interests:** Topics and content types that interest the person.  

    **Session Properties:** Properties that belong to a person's web session 
    (e.g., browser, geolocation, etc.). 

    **Web Behaviors:** Actions taken by a person (e.g., submitted a form, viewed 
    a blog, etc.). 

    For further instructions, see 
    [Creating Criteria](#creating-criteria). 

5.  Anonymous users are excluded from Segments by default. To include them, 
    enable the *Include Anonymous* toggle. 

6.  Click *Save Segment* when you're finished. 

### Creating Criteria

The criteria creation canvas is very flexible. Once added to the canvas, you can 
move, delete, or duplicate any criterion: 

**To move:** Click and drag the criterion using the vertical dots 
(![Handle](../../images/icon-handle.png)) 
on its left. 

**To delete:** Click the criterion's trash icon 
(![Trash](../../images/icon-trash.png)). 
Alternatively, you can click the criterion's Actions icon 
(![Actions](../../images/icon-actions.png)) 
and select *Delete*. 

**To duplicate:** Click the criterion's Actions icon 
(![Actions](../../images/icon-actions.png)) 
and select *Duplicate*. 

Each criterion that you add contains fields that let you customize it to your 
needs. The first field is typically a selector menu in which you specify a 
condition for any remaining fields. The condition's values depend on the data 
type for the remaining fields. Here are some common condition values: 

- Contains (text)
- Equals
- Greater than (number)
- Is known
- Less than (number)
- Does not contain (text)
- Does not equal
- Is not known
- Greater than or equals (number)
- Less than or equals (number)
- Is
- Is before (date)
- Is after (date)

For example, the `birthDate` criterion's first field is a selector menu with 
these options: 

-   is before
-   is
-   is after

The second field is a date field. You can therefore, for example, specify a 
criterion in which only Individuals with a birthday after 31 December 1980 are 
part of the Segment. 

You can also control the way adjacent criteria interact with each other. For 
example, if you place criteria next to each other, a small box appears between 
them with the text *AND*. This means that the two criteria are joined by a 
logical *And*. Clicking the box changes it to *OR*, which (as you probably 
guessed) represents a logical *Or*. Selecting *And* narrows the Segment's 
selection of Individuals; *Or* broadens it. 

For example, joining two `birthDate` criteria with the following conditions 
creates a Segment targeting the Millennial generation (born 1981 - 1996): 

-   is after 31 December 1980
-   AND
-   is before 01 January 1997

You can also form subgroups of criteria by dragging and dropping criteria onto 
each other. An AND/OR box then appears between the subgroup and any adjacent 
criteria. Together, these tools let you build complex criteria for your Segment. 

![Figure 2: If you're selling avocado toast, you need to target Millennials.](../../images/criteria-combine.png)

![Figure 3: Millennials interested in Liferay love avocado toast.](../../images/criteria-groups.png)

<!-- Double-check Behavior Criteria section for stuff to add -->

### Behavior Criteria

This type of criteria matches Individuals based on their interaction (behavior)
with your Liferay DXP pages and assets. Here's how you set behavior criteria:

1. In the *Criteria* section for the Segment, select *Individuals with Behavior*
   from the selector.

2. Select a page or asset activity from the *Select Activity* selector.

    - *Downloaded:* Asset downloads.
    - *Visited:* Page visits.
    - *Submitted:* Form submissions.

3. Select the page or asset acted on from the *Select Asset* selector. 

4. Select a timeframe from the *Since* selector. A preview list of matching 
Individuals appears on the right. *Search* lets you find Individuals in the
list. 

You've set a behavior criteria.

Now that you know how to add property and behavior criteria, you can use logical
AND and OR to add and chain together all the criteria your Segment needs. 

- *AND:* Click on the *AND* button to combine a new criteria with the previous 
  criteria. This narrows the Segment's selection of Individuals. 

- *OR:* Click on the *OR* button to add a alternative criteria an alternative to
  the previous criteria. This broadens the selection of Individuals.

Once you've completed defining your Segment, click on the *Create* button. Your
[Segment profile](https://github.com/liferay/liferay-docs/blob/7.1.x/discover/analytics-cloud/articles/03-understanding-people/03-profiling-segments.markdown)
appears. 

![Figure 2: As you define criteria and chain them together using AND and OR logic, a preview of Individuals that match the criteria appears on the right.](../../images/create-dynamic-segment.png)

Static Segments provide analytics for hand-selected Individuals. 

## Static Segments

To analyze specific Individuals together, add them to a Static Segment.

1. Click on *Segments* in the navigation panel. The list of existing Segments
   appears.

2. Click on the *Create Segment* button. The *Create Individuals Segment* screen
   appears. 

3. Name the Segment.

4. Select the *Static* Segment Type. A paginated list of all Individuals shows
   below the Segment configuration.

5. Search for and select specific Individuals, and click on the *Add Member*
   button to add them to the Segment. 

6. Once you've added all the Individuals, click on the *Create* button.

Congratulations on creating a Static Segment with hand-selected Individuals. Now
that you've created Segments, it's time to learn about them by viewing their
profiles. 
