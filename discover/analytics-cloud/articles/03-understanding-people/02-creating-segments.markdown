# Creating Segments [](id=creating-segments)

Individuals share common properties and behavior. Aggregating them into Segments
helps you understand groups of similar people. Segment analytics gives you
insight into people's interests. In the navigation panel, *Segments* is at the
top of the *People* section because it's where you'll spend most of your time
gaining customer insights. 

You can create Segments dynamically based on criteria or statically (comprising
hand-selected Individuals). Dynamic Segment criteria can be based on
Individuals' properties and behavior. You can use an *industry* property, for
example, as criteria. Analytics on industry Segments shows industries engaging
with you most and identifies your content that interests them.

## Dynamic Segments [](id=dynamic-segments)

Dynamic Segments aggregate Individuals based on their properties and interaction
with your @product@ site pages and assets. As Individuals match the criteria,
they are automatically added to the Segment. Here's how to create a Dynamic
Segment:

1. Click on *Segments* in the navigation panel. The list of existing Segments
   appears.

2. Click on the *Create Segment* button. The *Create Individuals Segment* screen
   appears. 

3. Name the Segment.

4. Select the *Dynamic* Segment Type. 

5. In the *Criteria* section, click on *Create Criteria*. The criteria options
   appear.

You can create these types of criteria:

- *Individuals with Property:* Matches Individuals described by the property 
  condition. 

- *Individuals with Behavior:* Matches Individuals exhibiting the behavior 
  condition.

![Figure 1: You can define Dynamic Segment criteria based on Individual properties and behavior.](../../images/create-dynamic-segment-on-property-or-behavior.png)

Start with defining property criteria for matching Individuals. 

### Property Criteria [](id=property-criteria)

Here's how to add criteria for an Individual property. 

1. In the *Criteria* section, select *Individuals with Property* from the
   selector.

2. Select a field (property) from the *Select Field* selector.

3. Set the *Condition* between the selected field and value. The condition type
   depends on the field type. Here are some common conditions:

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

4. Set the *Value* field. A preview list of matching Individuals appears on the
   right. *Search* lets find Individuals in the list. 

You've set a criteria based on an Individual property. Adding behavior criteria
is next.

### Behavior Criteria [](id=behavior-criteria)

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
[Segment profile](https://github.com/liferay/liferay-docs/blob/master/discover/analytics-cloud/articles/03-understanding-people/03-profiling-segments.markdown)
appears. 

![Figure 2: As you define criteria and chain them together using AND and OR logic, a preview of Individuals that match the criteria appears on the right.](../../images/create-dynamic-segment.png)

Static Segments provide analytics for hand-selected Individuals. 

## Static Segments [](id=static-segments)

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
