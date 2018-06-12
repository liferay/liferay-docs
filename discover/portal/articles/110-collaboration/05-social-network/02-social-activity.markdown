# Measuring Social Activity

The Social Activity feature in @product@ lets you recognize users who are major 
contributors, and indicate to new users whose advice may be the most 
trustworthy. 

+$$$

**Note:** As of @product-ver@, Social Activity is deprecated. This includes the 
User Statistics and Group Statistics widgets. 

$$$

![Figure 1: The Social Activity page of the Control Panel lets you enable social activity for assets and specify points for participation and contributions.](../../../images/social-activity.png)

To activate Social Activity, you must first determine the content types you want 
Social Activity to track. You can track one or all of these content types: 

-   Blogs Entries
-   Documents
-   Message Board Messages
-   Wiki Pages

For each content type, Social Activity tracks these metrics:

-   Participation
-   Contribution
-   Popularity

Participation and Contribution are user metrics, while Popularity is an asset 
metric. 

You can access Social Activity via the Menu 
(![Menu](../../../images/icon-menu.png)) 
by navigating to Site Administration (your site's menu) &rarr; *Configuration* 
&rarr; *Social Activity*

On the left-hand side of Social Activity, select the content types to track. The 
options on the right let you set point values and limits for actions a user can 
take on the content type. Note that you can't set the popularity points 
directly---they're tied to contribution points. To set a limit on how many times 
a user can perform an activity and still receive points, click *Limit* for the 
activity and set the limit via the selectors that appear. For some activities, 
you can set limits on both participation and contribution points. On new content 
creation, however, you can only set limits on participation points. 

It might not be immediately obvious, but for all actions that don't involve the
creation of a new asset, all the contribution points go to the original asset
creator and all popularity points go to the original asset. For example, if 
*Votes on a Blog* is set to 1 participation point and 5 contribution points (and 
therefore 5 popularity points): 

-   The user who votes on the blog entry receives 1 participation point. 
-   The user who created the blog entry receives 5 contribution points. 
-   The asset receives 5 popularity points. 

It's easy to assign points--you can arbitrarily assign points for just about
anything--the challenge is making the points significant in some way. 

## Displaying Social Activity

To display the social activity points, use the User Statistics widget. This 
widget displays a list of users ranked by their participation and contribution 
scores. Add the widget to a page from the *Add* 
(![Add](../../../images/icon-add-app.png)) 
&rarr; *Widgets* &rarr; *Social* menu. You can configure the widget in its 
*Options* 
(![Options](../../../images/icon-app-options.png)) 
&rarr; *Configuration* menu. There are 5 check boxes that you can enable or 
disable:

-   **Rank by Contribution**: Whether to use users' contribution scores as a 
    factor in calculating their ranks.
-   **Rank by Participation**: Whether to use users' participation scores as a 
    factor in calculating their ranks.
-   **Show Header Text**: Whether the title shows.
-   **Show Totals**: Whether to display the users' activity score next to their
    name.
-   **Display Additional Activity Counters**: Whether to display the information 
    in the selector menu next to the user's name. For example, this could be the 
    number of comments on assets a user has created, the number of wiki articles 
    a user has created, and more. To display multiple pieces of such 
    information, can click the `+` button to add one. Similarly, click the `-` 
    button to remove one. You can displayed as many as you want, but displaying 
    too many might make the display unwieldy. 

![Figure 2: The User Statistics widget gives rankings to promote user contributions and participation.](../../../images/social-user-statistics.png)

The Group Statistics widget provides more advanced data analytics. If you add it 
to a page and go to its Configuration dialog, you can select the assets to 
track. You can click the `+` button to add additional slots and choose from the 
various metrics available for each slot, covering virtually any action that a 
user can perform on content in the portal. Click the `-` button for a particular 
slot in the configuration view to remove it. 
