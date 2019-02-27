# Migrating From Audience Targeting to Segmentation and Personalization

@product-ver@ integrates all the features of the old Audience Targeting app 
into Liferay's core as Segmentation and Personalization. This enables better 
integration with other applications and provides developers with easier access 
to Segmentation and Personalization features. Audience Targeting users will need to migrate their user segments into the new Segments application. To help you through this process, Liferay provides a semi-automated migration with the 7.2 platform upgrade [Link to upgrade instructions].

[TBD. The same applies for Audience Targeting display portlets and the new Modern Site Building Experience-based personalization [LINK TO MIGRATING DISPLAY PORTLETS SECTION]]. 

Any custom Audience Targeting rules that cannot be automatically updated must be evaluated to determine if it is still necesssary

Second, any custom rule that was developed for Audience Targeting needs to be re-evaluated (i.e. decide whether it is still needed given the new capabilities in 7.2) and eventually re-implemented following the Segmentation and Personalization development guide [LINK TO DOC]. You will find some useful guidelines in the Migrating Custom Rules section [LINK TO THE SECTION].

Finally, since Audience Targeting analytics features have been integrated in Analytics Cloud [LINK TO DOC], rules and reports based on user’s behavior do not have a direct migration path. Review the Migrating Behavior-Based Features section for more information on this topic [LINK TO THE SECTION].
