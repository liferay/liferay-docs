# Migrating From Audience Targeting to Segmentation and Personalization

@product-ver@ integrates all the features of the Audience Targeting app 
into Liferay's core as Segmentation and Personalization. This enables better 
integration with other applications and provides developers with easier access 
to Segmentation and Personalization features. Audience Targeting users will need to migrate their user segments into the new Segments application. There are three general steps to migration process:

1.  Upgrade to @product-ver@.

2.  Migrate custom rules.

3.  Migrate behavior based features.

First, to upgrade to the latest version, follow the [upgrade guide](). Most of your Audience Targeting configuration will be automatically transferred into the new engine.

Next, any custom rules that were created in Audience Targeting need to be re-evaluated. Some custom rules may have an out of the box equivalent now, while others need to be migrated. If a rule needs to be re-implemented, follow the [Segmentation and Personalization development guide](dev-link) You can check [the list of rules that are automatically migrated]() to see how much additional work you have in story. You will also need to [migrate display widgets]() since the new Personalization features use different tools.

Finally, you need to migrate behavior based features, but since Audience Targeting's analytics features are now part of Analytics Cloud, there isn't a direct path to upgrade, so you'll need to follow the guide for [Migrating Behavior-Based Features].