# Using Analytics Cloud With User Segments

Analytics Cloud is a Liferay service that provides in depth information on who
is using your site, and how they are using. It can also be integrated with 
Segmentation to expand your ability to personalize content for your users. For 
more information about Analytics Cloud, including instructions for connecting
you server to it, read the [Analytics Cloud Documentation](link).

Once you're connected to Analytics Cloud, follow these steps to create a 
Segment that will track the behavior of users that have visited the homepage of 
your site in the past week to demonstrate the way that Analytics Cloud and 
Segments interact.

## Creating a new Segment

Once you have connected Analytics Cloud and Liferay DXP, you can create 
Segments in Analytics Cloud to capture data in DXP. First define the basic details of the Segment:

1.  Go to *Segments* and click *Create Segment.

2.  For the *Name* enter "Homepage Vistors" .

3.  For *Segment Type* select *Dynamic*.

![Figure X: The Segment definition interface is different on Analytics Cloud.](../../images/segments-ac-segment-definition.png)

Dynamic Segments are defined based on the specified criteria, and will include 
any users that meet that criteria, while Static segments are manually selected, 
and do not update dynamically.

## Defining Segment Criteria in Analytics Cloud

The Segment criteria for Analytics Cloud is different than the Segment criteria 
for creating Segments in @product@. See the [Analytics Cloud 
documentation](link) for more information. Now, define the criteria for your
segment:
    
1.  Click *Create Criteria*.

2.  For the criteria type, select *Individuals with Behavior* and leave the 
    second selector as *Has*.

3.  Click *Select Activity* and select *Visited*.

4.  Click *Select Asset* and select your *Welcome Page*.

5.  Click *Since* and select *7 Days Ago*.
    
6.  Click Create.

You will see a preview of the number of users as you create the segment. From 
here you can view a customizable collection of analytics data for the 
users in your segments.

![Figure X: You can view all of the analytics data for your new segment in Analytics Cloud.](../../images/segments-ac-segment-definition.png)

Next, you can use this Segment to define behaviors on your server.

## Getting Segment Analytics

After you create a Segment in Analytics Cloud, in addition to viewing it 
in @product@, you can also use or customize it there.

1.  Go to the *Segments* page.

2.  Click on *Homepage Visitors*.

So far this Segment is blank, since while you defined criteria for analytics, 
you have not defined any specific behavior to define it for this specific portal. [need more specific data on the usage here]

