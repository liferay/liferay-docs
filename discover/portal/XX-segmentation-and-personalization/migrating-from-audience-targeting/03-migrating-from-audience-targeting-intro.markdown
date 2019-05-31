# Manual Migration From Audience Targeting to Segmentation and Personalization

Some Audience Targeting rules will be automatically converted into rules for 
Segmentation and Personalization application as part the upgrade process, but 
other rules will need to be manually recreated through the system or 
re-implemented with custom development.

## Audience Targeting Rules

Any custom Audience Targeting rules that cannot be automatically updated must 
be evaluated to determine if custom development is still necessary or if the 
case can be managed with the provided segmentation capabilities. If not, it 
will need to be re-implemented following the Segmentation and Personalization 
development guide [LINK TO DOC]. See the Migrating Custom Rules section [LINK TO THE SECTION] for guidance on whether you need to re-implemented your custom rule or if you can use @product-ver@'s enhancement capabilities to create it.

## Analytics Cloud Integration

Audience Targeting's analytics features are now part of Analytics Cloud [LINK TO DOC]. Rules and reports based on user’s behavior do not have a direct migration path. Review the Migrating Behavior-Based Features section for more information on this topic [LINK TO THE SECTION].

# Migrating Custom Rules

As explained in the previous section, some Audience Targeting rules do not have a direct equivalent in Liferay Portal 7.2 and therefore they cannot be automatically migrated. Nevertheless, in this section we suggest some alternatives for their manual migration:

## User Attribute Rules

For Audience Targeting rules based on User Attributes that do not have a direct replacement in 7.2, such as gender or age, we suggest the use of custom fields for its migration. The same applies for user attributes retrieved from external sources, as in Facebook rules.

## Session Rules

For Audience Targeting rules based on Session attributes, such as Previous Visited Site, we recommend the use of the URL criteria field (e.g. matching some query parameter) or the Cookie criteria field (e.g. by adding the relevant session info in a cookie and matching its value). 

## Behavior Rules

For Audience Targeting rules based on behavior (i.e. Analytics), such as Visited Content, Visited Page or Score Points, please refer to the Migrating Behavior-Based features section [LINK TO THE SECTION]

## Migrating Custom Rules

Audience Targeting segmentation features were extensible through custom rules. As part of the planning for the upgrade to Liferay Portal 7.2, their function should be re-evaluated in the view of the features in the new version. 

First, you should check if the existing criteria fields can replace their function. Some criteria fields, such as custom fields, URL or cookies are very powerful and might help you migrate your custom rules with minimal or no additional development. 

If none of them cover your requirements, you can follow the development guide for instructions on how to add new criteria fields and contributors [LINK TO SEGMENTS DEV GUIDE].
