# Upgrading Service Builder Service Invocation

<div class="learn-path-step">
    <p>Upgrading Frameworks and Features<br>Step 2 of 4</p>
</div>

When upgrading a portlet leveraging
[Service Builder](/docs/appdev/7-2/-/knowledge_base/a/service-builder), you must
first decide if you're building your Service Builder logic as a WAR or
modularizing it.

| **Note:** Service Builder portlets automatically migrated to Liferay Workspace
| using the Code Upgrade Planner or Blade CLI's `convert` command automatically
| have its Service Builder logic converted to API and implementation modules.
| This is a best practice for @product-ver@.

If you prefer keeping your Service Builder logic as a WAR, you must implement a
service tracker to call services. See the
[Service Trackers](/docs/customization/7-2/-/knowledge_base/c/service-trackers)
article for more information.

If you're optimizing your Service Builder logic to invoke Liferay services from
a module, see the
[Invoking Local Services](/docs/appdev/7-2/-/knowledge_base/a/invoking-local-services)
article for more information.
