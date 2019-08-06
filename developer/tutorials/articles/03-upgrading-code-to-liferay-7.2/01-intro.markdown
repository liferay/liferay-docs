---
header-id: upgrading-code-to-product-ver
---

# Upgrading Code to @product-ver@

[TOC levels=1-4]

Upgrading to @product-ver@ involves migrating your installation and code (your
custom apps) to the new version. You'll learn how to upgrade your code in this
section.

These tutorials assume you're using the
[Liferay Upgrade Planner](/docs/7-2/reference/-/knowledge_base/r/liferay-upgrade-planner).
To follow along with this section, install the planner and step through the
upgrade instructions. You can also use the planner to
[upgrade your data](/docs/7-2/deploy/-/knowledge_base/d/upgrading-to-product-ver);
this is a separate process that must be done independently from the code upgrade
process.

For convenience, this tutorial section also references documentation and
outlined steps to aid those opting to upgrade their code manually.

Here are the code upgrade steps:

1.  {.root}[Upgrade Your Development Environment](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-your-development-environment){.title}

    Legacy project environments should be upgraded to the latest version of
    Liferay Workspace to ensure you leverage all available features.{.summary}

    1.  [Set Up Liferay Workspace](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-your-development-environment#setting-up-liferay-workspace){.title}

        A Liferay Workspace is a generated environment that is built to hold
        and manage your Liferay projects. Create/import a workspace to get
        started.{.summary}

        1.  [Create New Liferay Workspace](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-your-development-environment#creating-new-liferay-workspace){.title commandId=create_new_liferay_workspace}

            If you don't have an existing 7.x Liferay Workspace, you must create
            one. Skip to the next step if you have an existing workspace.{.summary}

        2.  [Import Existing Liferay Workspace](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-your-development-environment#importing-existing-liferay-workspace){.title commandId=import_existing_liferay_workspace}

            Import an existing Liferay Workspace. If you don't have one, revisit
            the previous step.{.summary}

    2.  [Configure Liferay Workspace Settings](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-your-development-environment#configuring-liferay-workspace-settings){.title}

        Set the @product@ version in workspace's configuration you intend to
        upgrade to.{.summary}

        1.  [Configure Bundle URL](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-your-development-environment#configuring-bundle-url){.title commandId=configure_bundle_url}

            Configure your bundle URL that the @product@ bundle is downloaded
            from.{.summary}

        2.  [Configure Target Platform Version](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-your-development-environment#configuring-target-platform-version){.title commandId=configure_target_platform_version}

            Configure your Target Platform version, which provides the specific
            artifacts associated with a @product@ release.{.summary}

        3.  [Initialize Server Bundle](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-your-development-environment#initializing-server-bundle){commandId=initialize_server_bundle}

            Download the @product@ bundle you're upgrading to.

2.  [Migrate Plugins SDK Projects](/docs/7-2/tutorials/-/knowledge_base/t/migrating-plugins-sdk-projects-to-liferay-workspace){.title}

    Copy your Plugins SDK projects into workspace and convert them to
    Gradle/Maven projects.{.summary}

    1.  [Import Existing Plugins SDK Projects](/docs/7-2/tutorials/-/knowledge_base/t/migrating-plugins-sdk-projects-to-liferay-workspace#importing-existing-plugins-sdk-projects){.title commandId=import_existing_plugins_sdk}

        Import your existing Plugins SDK projects.{.summary}

    2.  [Migrate Existing Plugins to Workspace](/docs/7-2/tutorials/-/knowledge_base/t/migrating-plugins-sdk-projects-to-liferay-workspace#migrating-existing-plugins-to-workspace){.title commandId=migrate_existing_plugins_to_workspace}

        Migrate your existing plugins to workspace. This involves moving the
        plugin to workspace and converting it to the workspace's build
        environment.{.summary}

3.  [Upgrade Build Dependencies](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-build-dependencies){.title}

    Optimize your workspace's build environment for the most efficient code
    upgrade experience.{.summary}

    1.  [Update Repository URL](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-build-dependencies#updating-the-repository-url){.title commandId=update_repository_url}

        Update your repository URL to Liferay's frequently updated CDN
        repository.{.summary}

    2. [Update Workspace Plugin Version](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-build-dependencies#updating-the-workspace-plugin-version){.title commandId=update_workspace_plguin_version}

        Update your Workspace plugin version to leverage the latest features of
        Liferay Workspace.{.summary}

    3.  [Remove Dependency Versions](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-build-dependencies#removing-your-projects-build-dependency-versions){.title commandId=remove_dependency_version}

        Remove the project's dependency versions since it's leveraging target
        platform.{.summary}

4.  [Fix Upgrade Problems](/docs/7-2/tutorials/-/knowledge_base/t/fixing-upgrade-problems){.title}

    Fix common upgrade problems dealing with your project's dependencies and
    breaking changes.{.summary}

    1.  [Auto-Correct Upgrade Problems](/docs/7-2/tutorials/-/knowledge_base/t/fixing-upgrade-problems#auto-correcting-upgrade-problems){.title commandId=auto_correct_find_upgrade_problems}

        Auto-correct straightforward upgrade problems.{.summary}

    2.  [Find Upgrade Problems](/docs/7-2/tutorials/-/knowledge_base/t/fixing-upgrade-problems#finding-upgrade-problems){.title commandId=find_upgrade_problems}

        Find upgrade problems. These are problems that cannot be auto-corrected;
        you can update them manually according to the breaking changes
        documentation.{.summary}

    3.  [Resolve Upgrade Problems](/docs/7-2/tutorials/-/knowledge_base/t/fixing-upgrade-problems#resolving-upgrade-problems){.title}

        Mark upgrade problems as resolved after addressing them.{.summary}

    4.  [Remove Problem Markers](/docs/7-2/tutorials/-/knowledge_base/t/fixing-upgrade-problems#removing-problem-markers){.title commandId=remove_upgrade_problems_markers}

        After fixing your upgrade problems, remove the problem markers.{.summary}

    5.  [Resolving a Project's Dependencies](/docs/7-2/tutorials/-/knowledge_base/t/resolving-a-projects-dependencies)

    6.  [Resolving Breaking Changes](/docs/7-2/tutorials/-/knowledge_base/t/resolving-breaking-changes)

5.  [Upgrade Service Builder Services](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-service-builder-services){.title}

    Upgrade your Liferay Service Builder services.{.summary}

    1.  [Remove Legacy Files](/docs/7-2/tutorials/-/knowledge_base/t/removing-legacy-files){.title commandId=remove_legacy_files}

        Remove legacy files that are no longer leveraged by Service Builder.{.summary}

    2.  [Migrate from Spring DI to OSGi Declarative Services](/docs/7-2/tutorials/-/knowledge_base/t/converting-a-service-builder-module-from-spring-di-to-osgi-ds){.title}

        Leverage OSGi Declarative Services in your Service Builder project.{.summary}

    3.  [Rebuild Services](/docs/7-2/tutorials/-/knowledge_base/t/rebuilding-services){.title commandId=rebuild_services}

        Rebuild your project's services to persist your updates.{.summary}

6.  [Upgrade Customization Plugins](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-customization-plugins){.title}

    Upgrade your customization plugins so they're deployable to @product-ver@.{.summary}

    1.  [Upgrade Customization Modules](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-customization-modules){.title}

    2.  [Upgrade Core JSP Hooks](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-core-jsp-hooks){.title}

    3.  [Upgrade Portlet JSP Hooks](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-portlet-jsp-hooks){.title}

    4.  [Upgrade Service Wrapper Hooks](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-service-wrapper-hooks){.title}

    5.  [Upgrade Core Language Key Hooks](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-core-language-key-hooks){.title}

    6.  [Upgrade Portlet Language Key Hooks](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-portlet-language-key-hooks){.title}

    7.  [Upgrade Model Listener Hooks](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-model-listener-hooks){.title}

    8.  [Upgrade Event Action Hooks](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-event-action-hooks){.title}

    9.  [Upgrade Servlet Filter Hooks](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-servlet-filter-hooks){.title}

    10. [Upgrade Portal Properties Hooks](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-portal-property-hooks){.title}

    11. [Upgrade Struts Action Hooks](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-struts-action-hooks){.title}

7.  [Upgrade Themes](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-a-theme-to-7-2){.title}

    Upgrade your themes so they're deployable to @product-ver@.{.summary}

    1.  [Upgrade 6.2 Themes to 7.2](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-6-2-themes-to-7-2){.title}

    2.  [Upgrade 7.0 Themes to 7.2](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-7-0-themes-to-7-2){.title}

    3.  [Upgrade 7.1 Themes to 7.2](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-7-1-themes-to-7-2){.title}

8.  [Upgrade Layout Templates](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-a-layout-template-to-7-2){.title}

    Upgrade your layout templates so they're deployable to @product-ver@.{.summary}

9.  [Upgrade Frameworks & Features](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-frameworks-and-features){.title}

    1.  [Upgrade JNDI Data Source Usage](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-jndi-data-source-usage){.title}

        Use @product@'s class loader to access the app server's JNDI API.{.summary}

    2.  [Upgrade Service Builder Service Invocation](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-service-builder-service-invocation){.title}

        For Service Builder logic remaining in a WAR, you must implement a
        service tracker to call services. For logic divided into OSGi modules,
        you can leverage Declarative Services.{.summary}

    3.  [Upgrade Service Builder](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-service-builder){.title}

        Adapt your app to account for Service Builder-specific changes.{.summary}

    4.  [Migrate Off of Velocity Templates](/docs/7-2/tutorials/-/knowledge_base/t/migrating-off-of-velocity-templates){.title}

        Velocity template usage is deprecated for @product-ver@. You should
        convert your template to FreeMarker.{.summary}

10. [Upgrade Portlets](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-portlets){.title}

    Upgrade your portlets so they're deployable to @product-ver@.{.summary}

    1.  [Upgrade Generic Portlets](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-a-genericportlet){.title}

    2.  [Upgrade Liferay MVC Portlets](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-a-liferay-mvc-portlet){.title}

    3.  [Upgrade JSF Portlets](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-a-liferay-jsf-portlet){.title}

    4.  [Upgrade Servlet-based Portlets](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-a-servlet-based-portlet){.title}

    5.  [Upgrading Spring Portlet MVC Portlets](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-a-spring-portlet-mvc-portlet){.title}

    6.  [Upgrade Struts 1 Portlets](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-a-struts-1-portlet){.title}

11. [Upgrade Web Plugins](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-web-plugins){.title}

    Upgrade web plugins previously stored in the `webs` folder of your legacy
    Plugins SDK.{.summary}

12. [Upgrade Ext Plugins](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-ext-plugins){.title}

    Attempt to leverage an extension point instead of upgrading your Ext plugin.
    If an Ext plugin is necessary, you must review all changes between the
    previous Liferay Portal instance you were using and @product-ver@, and then
    manually modify your Ext plugin to merge your changes with @product@'s.{.summary}

Once you've finished the code upgrade steps, your custom apps will be compatible
with @product-ver@! 
