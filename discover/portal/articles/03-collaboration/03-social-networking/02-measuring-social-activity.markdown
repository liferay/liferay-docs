# Measuring Social Activity [](id=measuring-social-activity)

When you have a lot of user interaction on your web site, it can be helpful to
try to separate the signal from the noise. Liferay contains a lot of
applications which end users can use to communicate with each other and provide
information. Some of this information is good and helpful and some of it can be
rather unhelpful. Using Liferay's Social Activity feature helps show which
users are making real, valuable contributions.

![Figure 1: The Social Activity page of the Control Panel allows you to enable social activity for assets and specify points for participation and contributions.](../../../images/social-activity.png)

To activate Social Activity, you'll first need to determine the collaboration
applications you want to use Social Activity to track. There are currently three
types of content you can use with Social Activity: Blogs Entries, Message Board
Messages, and Wiki Pages. Activation is *a la carte*, so you can use it on one,
two, or all three applications. Social Activity tracks three metrics from within
each of these applications. Two are for the user (*Participation* and
*Contribution*) and the other, *Popularity*, is for the asset involved.

You can find Social Activity in your Site's menu under Configuration. Let's
activate Social Activity for Blogs Entries. Check the box next to *Blogs Entry*.
You now have options to set point values and limits on several different actions
for blogs. You'll notice each item on the list has selectors you can use to set
the number of participation and contribution points; popularity points are tied
directly to contribution points. In addition to that, you can expand the box by
clicking *Limit* in the top right of each list item. You can use this to set a
limit on how many times a user can perform this activity with a specific asset
and receive the requisite points. For some activities, you can set limits on
both participation and contribution points, but on new content creation, you can
only set limits on participation points.

It might not be immediately obvious, but for all actions that do not involve the
creation of a new asset, all the contribution points go to the original asset
creator and all popularity points go to the original asset. That means if *Votes
on a Blog* is set to have 1 *Participation* point and 5 *Contribution* points
(and therefore 5 *Popularity* points), the user who votes on the asset will
receive 1 participation point, the user who created the asset will receive 5
contribution points, and the asset will receive 5 popularity points. 

It's easy to assign points--you can arbitrarily assign points for just about
anything--the challenge is making the points significant in some way. As
mentioned before, the primary purpose of social activity tracking is to make
sure that users who regularly contribute to the portal and participate in
discussions are recognized as such. So the central piece of the social activity
display is the *User Statistics* application.

The User Statistics application displays a list of users ranked by an amalgamation
of their participation and contribution scores. By going to the application's
Configuration dialog, you can change some of the specifics of the rankings. There
are five check boxes that you can enable or disable:

**Rank by Contribution**: If this is checked, users' contribution scores are
used as a factor in calculating their ranks.

**Rank by Participation**: If this is checked, users' participation scores are
used as a factor in calculating their ranks.

**Show Header Text**: Determines whether the title shows or only the rankings.

**Show Totals**: Toggles the display of the users activity score next to their
name.

**Display Additional Activity Counters**: You can toggle the display of any
number of other pieces of information next to the user's name in the statistics,
ranging from the number of comments on assets a user has created to the number
of wiki articles that the user has created. If you want to display multiple data
points, you can click the *plus* button to add one and the *minus* button to
remove one. You can have as many data points displayed as you want, but
displaying too many might make the display a little unwieldy.

![Figure 2: The User Statistics portlet gives rankings to promote user contributions and participation.](../../../images/social-statistics.png)

The *Group Statistics* portlet provides some more advanced data analytics. If
you add it to a page and go to its configuration dialog, you can select the
assets to track. You can click the *plus* button to add additional slots and
choose from the various metrics available for each slot, covering virtually any
action that a user can perform on content in the portal. If you decide that
you're displaying too many metrics, you can click the *minus* button for a
particular slot on the configuration view to remove it.

There are a wide-ranging number of actions that you can provide social credit
for. Users can receive credit for everything from subscribing to a blog to
writing wiki articles. You can easily tweak the numbers in the Control Panel if
it becomes clear that certain activities are weighted too high or too low.

Social Activity can be a valuable tool for websites that are heavily driven by
community-created content. It allows you easily to recognize users who are major
contributors, and it indicates to new users whose advice may be most
trustworthy. Social Activity is easy to set up and can be configured differently
for each site, increasing the flexibility of your website.

