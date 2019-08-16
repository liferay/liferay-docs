---
header-id: creating-new-audience-targeting-rule-types
---

# Creating New Audience Targeting Rule Types

[TOC levels=1-4]

In Audience Targeting, a User Segment is defined as a group of users that match 
a set of rules. Out of the box, Liferay provides several types of rules that are
based on characteristics such as age range, gender, or location. You combine
these rules to create User Segments. For example, if you want to target probable
buyers of a shoe that has a particular style, you might create a User Segment
composed of Females over 40 who live in urban areas. 

Audience Targeting ships with many rules for User Segments, but it's also 
extensible: if there isn't a rule that fits your case, you can create it 
yourself! 

Creating a rule type involves targeting what you want to evaluate. Suppose you
own an Outdoor Sporting Goods store. On your website, you want to promote goods
appropriate for the current weather. If a user is from Los Angeles and it's
raining the day he or she visits your website, you could show that user new
umbrellas. If it's sunny, however, you could show the user sunglasses instead.
For this example, your evaluation entity would be weather based on the user's
location. To make this work, you must do two things: 

1.  Retrieve the user's location so you can obtain that location's weather. 

2.  Let administrators set the value to compare with the user's current weather,
    using a UI component like a selection list of weather options. 

With this design, an administrator can set *rainy* as the value for the rule,
and the rule could be added to a user segment targeted for rain-related goods.
When users visit your site, their user segment assignments come from matching
the weather in their current locations with the rule's preset weather value
(rainy). On a match, you show rain-related content; otherwise, the user is part
of a different user segment and sees that segment's content, like a promotion
for sunglasses.

There are four steps to create a custom rule type:

1.  Create a module and ensure it has the necessary Content Targeting API
    dependencies.

2.  Define how your rule works by implementing the 
    [`com.liferay.content.targeting.api.model.Rule`](https://repository.liferay.com/nexus/service/local/artifact/maven/redirect?r=liferay-public-releases&g=com.liferay.content-targeting&a=com.liferay.content.targeting.api&v=5.0.0&e=jar&c=javadoc)
    interface's methods.

3.  Create the rule evaluation criteria.

4.  Define the Rule's UI.

![Figure 1: This diagram breaks down the evaluation process for the weather rule.](../../../images-dxp/weather-rule-diagram.png)

Now that you have an idea of how to plan your custom rule's development, you can
create one!
