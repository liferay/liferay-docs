---
header-id: migrating-from-audience-targeting-to-segmentation-and-personalization
---

# Migrating From Audience Targeting to Segmentation and Personalization

[TOC levels=1-4]

@product-ver@ integrates all the Audience Targeting app's features into
Liferay's core as Segmentation and Personalization. This enables better
integration with other applications and provides developers with easier access
to Segmentation and Personalization features. Audience Targeting users must
migrate their user segments into the new Segments application. There are three
steps to the migration process:

1.  Upgrade to @product-ver@.

2.  Migrate custom rules.

3.  Migrate behavior-based features.

First, to upgrade to the latest version of @product@, follow the
[upgrade guide](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-code-to-product-ver).
Most of your Audience Targeting configuration is automatically transferred
into the new engine.

Next, any custom rules that were created in Audience Targeting must be
re-evaluated. Some custom rules may have an out-of-the-box equivalent now, while
others must be migrated. If a rule must be re-implemented, follow the
[Segmentation and Personalization development guide](/docs/7-2/frameworks/-/knowledge_base/f/segmentation-personalization).
You can check
[the list of rules that are automatically migrated](/docs/7-2/deploy/-/knowledge_base/d/migrating-user-segments)
to see how much additional work you have in store. You must also
[migrate display widgets](/docs/7-2/deploy/-/knowledge_base/d/manually-migrating-from-audience-targeting)
since the new Personalization features use different tools.

Finally, you must migrate behavior-based features, but since Audience
Targeting's analytics features are now part of Analytics Cloud, there isn't a
direct path to upgrade. See the 
[Analytics Cloud documentation](https://help.liferay.com/hc/en-us/articles/360006947671-Creating-Segments).
