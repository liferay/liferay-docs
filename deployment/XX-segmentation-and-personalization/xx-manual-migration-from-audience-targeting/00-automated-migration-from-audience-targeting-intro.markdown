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
