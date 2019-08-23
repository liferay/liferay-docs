---
header-id: upgrading-plugins-to-liferay-7
---

# Upgrading Code to @product-ver@

[TOC levels=1-4]

Upgrading to @product-ver@ involves migrating your installation and code (your
custom apps) to the new version. You'll learn how to upgrade your code in this
section.

The first upgrade process step is to adapt your existing plugin's code to
@product-ver@'s APIs. The great news is that Liferay's Upgrade Planner makes
this easier than ever. It identifies Liferay API changes affecting your code,
explains the API changes, and offers resolution steps. And the tool offers
auto-correction where it can. 

You might be tempted to optimize your existing plugins right away to benefit
from the new things @product@ offers, but you shouldn't. It's much better to
upgrade your plugins according to these tutorials. In this way, you'll get your
plugins running in @product@ as fast as possible, and at the same time you'll
have prepared the plugins for the optimizations you can implement later. 

These tutorials assume you're using the
[Liferay Upgrade Planner](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner).
To follow along with this section, install the planner and step through the
upgrade instructions.

For convenience, this tutorial section also references documentation and
outlined steps to aid those opting to upgrade their code manually.

Here are the code upgrade steps:

1.  [Upgrade Your Development Environment](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment)

    Legacy project environments should be upgraded to the latest version of
    Liferay Workspace to ensure you leverage all available features.

    1.  [Set Up Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#setting-up-liferay-workspace)

        A Liferay Workspace is a generated environment that is built to hold
        and manage your Liferay projects. Create/import a workspace to get
        started.

        1.  [Create New Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#creating-new-liferay-workspace)

            If you don't have an existing 7.x Liferay Workspace, you must create
            one. Skip to the next step if you have an existing workspace.

        2.  [Import Existing Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#importing-existing-liferay-workspace)

            Import an existing Liferay Workspace. If you don't have one, revisit
            the previous step.

    2.  [Configure Liferay Workspace Settings](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#configuring-liferay-workspace-settings)

        Set the @product@ version in workspace's configuration you intend to
        upgrade to.

        1.  [Configure Bundle URL](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#configuring-bundle-url)

            Configure your bundle URL that the @product@ bundle is downloaded
            from.

        2.  [Configure Target Platform Version](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#configuring-target-platform-version)

            Configure your Target Platform version, which provides the specific
            artifacts associated with a @product@ release.

        3.  [Initialize Server Bundle](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-your-development-environment#initializing-server-bundle)

            Download the @product@ bundle you're upgrading to.

2.  [Migrate Plugins SDK Projects](/docs/7-1/tutorials/-/knowledge_base/t/migrating-plugins-sdk-projects-to-liferay-workspace)

    Copy your Plugins SDK projects into workspace and convert them to
    Gradle/Maven projects.

    1.  [Import Existing Plugins SDK Projects](/docs/7-1/tutorials/-/knowledge_base/t/migrating-plugins-sdk-projects-to-liferay-workspace#importing-existing-plugins-sdk-projects)

        Import your existing Plugins SDK projects.

    2.  [Migrate Existing Plugins to Workspace](/docs/7-1/tutorials/-/knowledge_base/t/migrating-plugins-sdk-projects-to-liferay-workspace#migrating-existing-plugins-to-workspace)

        Migrate your existing plugins to workspace. This involves moving the
        plugin to workspace and converting it to the workspace's build
        environment.

3.  [Upgrade Build Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-build-dependencies)

    Optimize your workspace's build environment for the most efficient code
    upgrade experience.

    1.  [Update Repository URL](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-build-dependencies#updating-the-repository-url)

        Update your repository URL to Liferay's frequently updated CDN
        repository.

    2. [Update Workspace Plugin Version](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-build-dependencies#updating-the-workspace-plugin-version)

        Update your Workspace plugin version to leverage the latest features of
        Liferay Workspace.

    3.  [Remove Dependency Versions](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-build-dependencies#removing-your-projects-build-dependency-versions)

        Remove the project's dependency versions since it's leveraging target
        platform.

4.  [Fix Upgrade Problems](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems)

    Fix common upgrade problems dealing with your project's dependencies and
    breaking changes.

    1.  [Auto-Correct Upgrade Problems](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems#auto-correcting-upgrade-problems)

        Auto-correct straightforward upgrade problems.

    2.  [Find Upgrade Problems](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems#finding-upgrade-problems)

        Find upgrade problems. These are problems that cannot be auto-corrected;
        you can update them manually according to the breaking changes
        documentation.

    3.  [Resolve Upgrade Problems](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems#resolving-upgrade-problems)

        Mark upgrade problems as resolved after addressing them.

    4.  [Remove Problem Markers](/docs/7-1/tutorials/-/knowledge_base/t/fixing-upgrade-problems#removing-problem-markers)

        After fixing your upgrade problems, remove the problem markers.

    5.  [Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

    6.  [Resolving Breaking Changes](/docs/7-1/tutorials/-/knowledge_base/t/resolving-breaking-changes)

5.  [Upgrade Customization Plugins](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-hook-plugins)

    Upgrade your customization plugins so they're deployable to @product-ver@.

    1.  [Upgrade Customization Modules](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-customization-modules)

    2.  [Upgrade Core JSP Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-core-jsp-hooks)

    3.  [Upgrade Portlet JSP Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-app-jsp-hooks)

    4.  [Upgrade Service Wrapper Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-service-wrappers)

    5.  [Upgrade Core Language Key Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-core-language-key-hooks)

    6.  [Upgrade Portlet Language Key Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portlet-language-key-hooks)

    7.  [Upgrade Model Listener Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-model-listener-hooks)

    8.  [Upgrade Event Action Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portal-property-and-event-action-hooks)

    9.  [Upgrade Servlet Filter Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-servlet-filter-hooks)

    10. [Upgrade Portal Properties Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portal-property-and-event-action-hooks)

    11. [Upgrade Struts Action Hooks](/docs/7-1/tutorials/-/knowledge_base/t/converting-strutsactionwrappers-to-mvccommands)

6.  [Upgrade Themes](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-6-2-themes-intro)

    Upgrade your themes so they're deployable to @product-ver@.

7.  [Upgrade Layout Templates](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-6-2-layout-templates)

    Upgrade your layout templates so they're deployable to @product-ver@.

8.  [Upgrade Frameworks & Features](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-frameworks-and-features)

    1.  [Upgrade JNDI Data Source Usage](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-jndi-data-source-usage)

        Use @product@'s class loader to access the app server's JNDI API.

    2.  [Upgrade Service Builder Service Invocation](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-service-builder-service-invocation)

        For Service Builder logic remaining in a WAR, you must implement a
        service tracker to call services. For logic divided into OSGi modules,
        you can leverage Declarative Services.

    3.  [Upgrade Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-service-builder)

        Adapt your app to account for Service Builder-specific changes.

    4.  [Migrate Off of Velocity Templates](/docs/7-1/tutorials/-/knowledge_base/t/migrating-off-of-velocity-templates)

        Velocity template usage is deprecated for @product-ver@. You should
        convert your template to FreeMarker.

9. [Upgrade Portlets](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-portlet-plugins)

    Upgrade your portlets so they're deployable to @product-ver@.

    1.  [Upgrade Generic Portlets](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-genericportlet)

    2.  [Upgrade Liferay MVC Portlets](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-liferay-mvc-portlet)

    3.  [Upgrade JSF Portlets](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-liferay-jsf-portlet)

    4.  [Upgrade Servlet-based Portlets](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-servlet-based-portlet)

    5.  [Upgrading Spring MVC Portlets](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-spring-mvc-portlet)

    6.  [Upgrade Struts 1 Portlets](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-a-struts-1-portlet)

10. [Upgrade Web Plugins](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-web-plugins)

    Upgrade web plugins previously stored in the `webs` folder of your legacy
    Plugins SDK.

11. [Upgrade Ext Plugins](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-ext-plugins)

    Attempt to leverage an extension point instead of upgrading your Ext plugin.
    If an Ext plugin is necessary, you must review all changes between the
    previous Liferay Portal instance you were using and @product-ver@, and then
    manually modify your Ext plugin to merge your changes with @product@'s.

Once you've finished the code upgrade steps, your custom apps will be compatible
with @product-ver@! 
