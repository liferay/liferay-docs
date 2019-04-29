---
header-id: segmentation-and-personalization
---

# Segmentation and Personalization

[TOC levels=1-4]

Liferay's Segmentation and Personalization shows the right content to the 
right people at the right time. It provides the tools you need to manage 
different audiences and dynamically provide personalized experiences for people 
using your site. For example, if you're creating a campaign to promote new 
financial service products, you need a way to display offers to customers who 
are likely to be interested in those offers. You don't want to display 
information on a basic free checking account for an "advanced" customer who 
carries a high balance across several types of accounts, but you do want to 
show that information to a visitor who entered the site through a landing page 
from a promotional at a local college. At the same time, you probably don't 
want to recommend options for optimizing retirement account contributions to 
the college student, but the other customer might be a great target for that 
campaign. By using data like user attributes or visitor interactions, you can
dynamically target relevant content to your site's guests.

## Defining Segments

The first part of the equation is defining the types of segments that you need. 
You can create Segments to capture every case. Segments are composed of
different criteria. In the previous example you might have a segment for *Free
Checking Account Prospects* that contains criteria based on user data, like
customers that don't currently have an open checking account, or based on user
behavior, like visitors who came to the site through specific channels. To learn
more about Segmentation options, see the 
[overview of the Segment editor](/docs/7-2/user/-/knowledge_base/user/the-segment-editor), practice
[creating basic Segments](/docs/7-2/user/-/knowledge_base/user/creating-basic-user-segments),
or create more complicated 
[Segments with custom fields and session data](/docs/7-2/user/-/knowledge_base/user/creating-segments-with-custom-fields-and-session-data).

## Integrating Segments with Analytics Cloud

There are two different stories that User Segments can tell. One is targeting
content to specific audiences that encourages engagement and positive user
experiences. The other is defining groups of users and visitors to analyze their
behavior and interactions with your site. To tell the second story, you must
integrate with Analytics Cloud.

Analytics Cloud is a Liferay service that provides in-depth information on who
uses your site and how they use it. Analytics Cloud is a key component to fully
utilizing Segments and Personalization, since it enables you to see the full
picture of how users and visitors on your site behave and interact with
both standard and targeted content. You can learn more about this in 
[Using Analytics Cloud with User Segments](/documentation/user/-/knowledge_base/7-2/using-analytics-cloud-with-user-segments).


## Personalizing Experiences

The most important piece of the puzzle isn't defining groups or analyzing use 
behavior. It's the final step of using all of that to provide users and site 
visitors with the best possible experience, and driving campaigns and content 
engagement. If you strategically create segments, you can then use that to 
enhance user experiences, and make sure that users are seeing the content and 
promotions that you want them to see. There are two primary pieces of this 
*Page Personalization* and *Content Personalization*.

### Page Personalization

Page Personalization describes changes to page layout and structure based on
the person viewing the page. The same page can be used to provide drastically 
different experiences by displaying different widgets and even different 
layouts depending on who is viewing the page. 

### Content Personalization

Content Personalization describes changes to the specific pieces of content
displayed to a user or visitor based on their segment criteria. For example, if 
a user has already viewed a specific ad on your site today, Content 
Personalization can prevent that ad from being displayed again to improve their 
experience.
