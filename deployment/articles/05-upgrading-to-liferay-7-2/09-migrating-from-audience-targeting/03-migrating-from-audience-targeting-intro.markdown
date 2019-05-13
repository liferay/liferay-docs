# Manual Migration

As explained in the previous section, some Audience Targeting rules do not have 
a direct equivalent in Liferay Portal 7.2 and therefore they cannot be 
automatically migrated. In this section you can see the recommended solution 
for each rule type.

## User Attribute Rules

Some User Attributes, like Gender or Age, do not have a direct equivalent in 
@product-ver@. User Attributes retrieved from external sources like Facebook 
also do not have a replacement. To replace these, you must create a [custom user field](user-docs-link) and use that to define your new Segment.

## Session Rules

For Session attributes that do not have a direct equivalent, the recommended 
solution is to use either a URL field to use the current URL or a previously 
visited URL on your site as criteria, or to use a Cookie for more advanced 
session tracking needs.

## Behavior Rules

In @product-ver@ analytics is now managed through Analytics Cloud. You can 
learn more about creating behavior based rules in the [Migrating Behavior-Based features](ac-link) section of  the Analytics Cloud documentation.

## Migrating Custom Rules

Audience Targeting segmentation features could be extended with custom rules. 
As part of the upgrade planning process the function of any such rules should 
be re-evaluated with the new Segmentation features of @product-ver@ in mind. 

First, check the [Segmentation reference](user-guide-linnk) if any new criteria 
fields can replace their function. In particular, custom fields, URL fields, 
and cookies might help you migrate your custom rules with little to no 
additional development. 

If none of them cover your requirements, follow the development guide for 
instructions on [how to add new criteria fields and contributors](dev-guide-link].

## Migrating Display Portlets

With Audience Targeting, you could display personalized content with the User Segment Display Content portlet or by using Asset Publisher with the Segments filter enabled [LINK to AT documentation in https://help.liferay.com/hc/en-us/articles/360018174271-Using-the-Audience-Targeting-Widgets-]. In @product-ver@, you will have to choose the most appropriate personalization option for your use cases. 

### User Segment Content Display

The User Segment Content Display portlet was used to display existing content 
based on segment membership rules. In @product-ver@, you can cover the same use 
case by defining manual content sets with variations for your different 
audiences, and applying it to an asset publisher. See the documentation for 
[creating personalized Content Sets](user-docs-link). With this feature, you 
can assign any number of assets to the Content List for the given audience, and 
then use the Asset Publisher to define how content is displayed on the page. 

### Asset Publisher Personalization

Finally, if you want to display a dynamic list of content for your different audiences based on a filter in the same way you did with in Audience Targeting with the Segments filter in the Asset Publisher, you can create a dynamic content set with variations for your audiences and apply it to an asset publisher [Link to Personalized Content Sets, dynamic section]. 

In addition, the new [Experience-based Content Page personalization](user-guide-link) may fulfill a use case that you were previously solving with one of the methods previously available.
