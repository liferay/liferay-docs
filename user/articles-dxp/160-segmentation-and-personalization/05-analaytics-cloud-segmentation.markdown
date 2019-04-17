---
header-id: using-analytics-cloud-with-user-segments
---

# Using Analytics Cloud With User Segments

[TOC levels=1-4]

To use Analytics Cloud with User Segments, you must first connect your DXP data 
source to Analytics Cloud and enable synchronization of users and analytics. For 
more information about Analytics Cloud, including instructions for connecting 
it with DXP, see the official [Analytics Cloud Documentation](https://help.liferay.com/hc/en-us/articles/360006608732).

Once you're connected to Analytics Cloud, you can create Segments to analyze
user behavior. The case below describes an analysis of user interaction after
being directed to the site through a landing page for a marketing campaign.

## Creating a new Segment

Synchronization with Analytics Cloud is not instant, so once you have connected 
Analytics Cloud and Liferay DXP, you must first wait for the users and data to 
synchronize. After that completes you can create Segments in Analytics Cloud to 
capture data in DXP: 

1.  Go to *Segments* and click *Create Segment*.

2.  For the *Name* enter *Landing Page Visitors*.

3.  For *Segment Type* select *Dynamic*.

4.  Click *Create Criteria*.

5.  Set it to *Individuals with Behavior* &rarr; *Has* &rarr; *Visited* &rarr;
    select the landing page from your site &rarr; *Since* choose a relevant 
    date.

![Figure 1: The Segment definition interface is different on Analytics Cloud.](../../images/segments-ac-segment-definition.png)

Dynamic Segments are based on the specified criteria and include users that
meet that criteria. Static segments are manually selected and do not update
dynamically.

Once a Segment is created, you can view the analytics data for that Segment. 

![Figure 2: You can view all of the analytics data for your new segment in Analytics Cloud.](../../images/sements-ac-analytics.png)

Next, you can use this Segment to define behaviors on your server.

## Getting Segment Analytics

After you create and sync a Segment in Analytics Cloud, you can view it and
customize it in @product@.

1.  Go to the *Segments* page.

2.  Click on *Landing Page Visitors*.

![Figure 3: When you see Analytics Cloud Segments in the list of Segments, they are marked with the Analytics Cloud icon.](../../images/segments-ac-list-item.png)

Analytics are based on the criteria that you set on Analytics Cloud, 
but you can set additional criteria here to use this Segment for 
personalization in DXP. Changing the Segment criteria here doesn't affect the 
gathered analytics data, unless it is configured in some way that restricts its
members from viewing content that you are using as an Analytics Cloud criteria.

When you put it all together to provide personalized experiences and analyze 
user behavior, you can see the true power of Segmentation.
