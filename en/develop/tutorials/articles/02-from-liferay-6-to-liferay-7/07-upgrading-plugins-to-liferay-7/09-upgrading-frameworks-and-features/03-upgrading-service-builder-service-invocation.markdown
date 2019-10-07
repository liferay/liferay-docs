---
header-id: upgrading-service-builder-service-invocation
---

# Upgrading Service Builder Service Invocation

[TOC levels=1-4]

When upgrading a portlet leveraging
[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder), you must
first decide if you're building your Service Builder logic as a WAR or
modularizing it.

| **Note:** Service Builder portlets automatically migrated to Liferay Workspace
| using the Upgrade Planner or Blade CLI's `convert` command automatically
| have its Service Builder logic converted to API and implementation modules.
| This is a best practice for @product-ver@.

If you prefer keeping your Service Builder logic as a WAR, you must implement a
service tracker to call services. See the
[Service Trackers](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers)
article for more information.

If you're optimizing your Service Builder logic to invoke Liferay services from
a module, see the
[Invoking Local Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-local-services)
article for more information.
