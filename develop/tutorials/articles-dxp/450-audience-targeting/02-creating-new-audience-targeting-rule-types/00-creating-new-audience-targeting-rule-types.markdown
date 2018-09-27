# Creating New Audience Targeting Rule Types [](id=creating-new-audience-targeting-rule-types)

In the Audience Targeting application, a User Segment is defined as a group of
users that match a set of rules. Out of the box, Liferay provides several types
of rules that are based on characteristics such as age range, gender, location,
and so on. You combine these rules to create User Segments. For example, if you
want to target probable buyers of a shoe that has a particular style, you might
create a User Segment composed of Females over 40 who live in urban areas. 

The Audience Targeting app ships with many rules you can use make up User
Segments, but it's also extensible. This means that if there isn't a rule that
already fits your case, you can create it yourself! 

Creating a rule type involves targeting what you want to evaluate. Suppose you
own an Outdoor Sporting Goods store. On your website, you'd like to promote
goods that are appropriate for the current weather. If a user is from Los
Angeles and it's raining the day he or she visits your website, you could show
that user new umbrellas. If it's sunny, however, you could show the user
sunglasses instead. For this example, your evaluation entity would be weather
based on the user's location. To make this work, you'll need to do two things: 

1. Retrieve the user's location so you can obtain that location's weather. 

2. Let administrators set the value that should be compared with the user's
   current weather, using a UI component like a selection list of weather
   options. 

With this design, an administrator can set *rainy* as the value for the rule,
and the rule could be added to a user segment targeted for rain-related goods.
When users visit your site, their user segment assignments come from matching
the weather in their current locations with the rule's preset weather value
(rainy). On a match, you show rain-related content; otherwise, the user is part
of a different User Segment and sees that segment's content, like a promotion
for sunglasses.

![Figure 1: This diagram breaks down the evaluation process for the weather rule.](../../images-dxp/weather-rule-diagram.png)

Now that you have an idea of how to plan your custom rule's development, you'll
begin creating one yourself!
