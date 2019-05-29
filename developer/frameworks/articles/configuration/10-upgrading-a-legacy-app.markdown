---
header-id: upgrading-a-legacy-app
---

# Upgrading a Legacy App

[TOC levels=1-4]

If you have an app was made configurable under an earlier version of @product@,
you can upgrade with have to reconfigure any of your app's instances.

If you have an app that was configurable using the mechanisms of Liferay Portal
6.2 and before, refer to 
[Transitioning from Portlet Preferences to the Configuration API](/develop/tutorials/-/knowledge_base/7-0/transitioning-from-portlet-preferences-to-the-configuration-api).

If you have an app with a configuration interface scoped to anything other than
`SYSTEM` and a custom UI for saving configuration values to
`PortletPreferences`, you have two options:

-   Keep using your custom UI. Deactivate the auto-generated UI in *Instance
    Settings* by setting the scope in your configuration interface to `SYSTEM`.
    (This is quick and easy, but won't make your code easier to maintain in the
    long term.)

    For other ways to disable the auto-generated UI, see
    [Excluding a Configuration UI](/docs/7-2/frameworks/-/knowledge_base/f/customizing-the-configuration-user-interface#exluding-a-configuration-ui)

-   Write an Upgrade Process to convert your configuration values in
    `PortletPreferences` to an instance-scoped OSGi configuration, using the
    `saveCompanyConfiguration` method in the `ConfigurationProvider` interface.

    |You don't have to use `saveCompanyConfiguration`, but doing so meets all
    |the necessary requirements for an upgrade process: it must be a factory
    |instance with a factory PID of `Unknown macro:[base-pid].scoped`, and it
    |must contain a `companyId` property.

    Then remove your custom UI. If you're reading configuration values
    using `ConfigurationProvider`'s `getCompanyConfiguration` method, the
    auto-generated UI will pick up where you left off, with no need to
    reconfigure anything.
