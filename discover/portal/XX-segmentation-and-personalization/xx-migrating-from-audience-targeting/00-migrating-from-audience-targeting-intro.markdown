# Migrating From Audience Targeting to Segmentation and Personalization

@product-ver@ integrates all the features of the old Audience Targeting app into Liferay's core. 


the segmentation and personalization capabilities that were provided in previous versions through the Audience Targeting Marketplace Application [LINK TO DOC] have been integrated into the platform core. While this will make it easier for other applications (both within the platform and externally) to exploit these features, it also has some important consequences for existing Audience Targeting users.

First, since Audience Targeting won’t be available as an application in 7.2, marketers must transition to use the new Segments application to manage their user segments. To facilitate this transition, Liferay Portal provides an semi-automated migration of Audience Targeting user segments into 7.2 segments [LINK TO MIGRATING USER SEGMENTS SECTION]. [TBD. The same applies for Audience Targeting display portlets and the new Modern Site Building Experience-based personalization [LINK TO MIGRATING DISPLAY PORTLETS SECTION]]. 

Second, any custom rule that was developed for Audience Targeting needs to be re-evaluated (i.e. decide whether it is still needed given the new capabilities in 7.2) and eventually re-implemented following the Segmentation and Personalization development guide [LINK TO DOC]. You will find some useful guidelines in the Migrating Custom Rules section [LINK TO THE SECTION].

Finally, since Audience Targeting analytics features have been integrated in Analytics Cloud [LINK TO DOC], rules and reports based on user’s behavior do not have a direct migration path. Review the Migrating Behavior-Based Features section for more information on this topic [LINK TO THE SECTION].
