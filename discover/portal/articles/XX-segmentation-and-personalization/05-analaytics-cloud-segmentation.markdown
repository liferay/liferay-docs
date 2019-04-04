# Using Analytics Cloud With User Segments

There are two different stories that User Segments can tell. One is the story of
using Segments to target content to specific audiences and encourage engagement 
and positive user experiences. The other is the story of using Segments to 
define groups of users and vistors to analyze their behavior and interactions 
with your site. To tell the second story, you need to integrate with Analytics 
Cloud.

Analytics Cloud is a Liferay service that provides in depth information on who
is using your site, and how they are using it. Analytics Cloud is a key 
component to fully utilizing Segments and Personalization, since it enables you 
to see the full picture of how users and visitors on your site are behaving and 
interacting with both standard and targeted content.

To get started, you'll need connect your DXP data source to Analytics Cloud and 
enable synchronization of user and analytics. For more information about 
Analytics Cloud, including instructions for connecting it with DXP, see the 
official [Analytics Cloud Documentation](link).

Once you're connected to Analytics Cloud, you can start working on creating 
Segments to analyze user behavior. In this case, you're looking at how users 
interact with your site after being directed to the site through a landing page 
for a marketing campaign.

## Creating a new Segment

Synchronization with Analytics Cloud is not instant, so once you have connected 
Analytics Cloud and Liferay DXP, you first must wait for the users to 
synchronize. After that completes you can create Segments in Analytics Cloud to 
capture data in DXP. First define the basic details of the Segment:

1.  Go to *Segments* and click *Create Segment*.

2.  For the *Name* enter "Landing Page Vistors".

3.  For *Segment Type* select *Dynamic*.

4.  Click *Create Criteria*.

5.  Set it to *Individuals with Behavior* &rarr; *Has* &rarr; *Visited* &rarr;
    select the landing page from your site &rarr; *Since* choose a relevant 
    date.

![Figure X: The Segment definition interface is different on Analytics Cloud.](../../images/segments-ac-segment-definition.png)

Dynamic Segments are defined based on the specified criteria, and will include 
any users that meet that criteria, while Static segments are manually selected, 
and do not update dynamically.

Once a Segment is created, you can view the analytics data for that Segment. 
Since there is currently not criteria defined, the data will not be 
particularly interesting or useful.

![Figure X: You can view all of the analytics data for your new segment in Analytics Cloud.](../../images/sements-ac-analytics.png)

Next, you can use this Segment to define behaviors on your server.

## Getting Segment Analytics

After you create a Segment in Analytics Cloud, and it finishes syncing, you can view it in @product@, and customize it there as well.

1.  Go to the *Segments* page.

2.  Click on *Landing Page Visitors*.

![Figure X: When you see Analytics Cloud Segments in the list of Segments, they will be marked with the Analytics Cloud icon.](../../images/segments-ac-list-item.png)

The analytics will be based on the criteria that you set on Analytics Cloud, 
but you can set additional criteria here to use this Segment for 
personalization in DXP. Changing the Segment criteria here will not affect the 
analytics data that is gathered, unless it is configured in some way that 
restricts its members from viewing content that you are using as an Analytics 
Cloud criteria.
