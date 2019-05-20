# Upgrading Code to @product-ver@

Upgrading to @product-ver@ involves migrating your installation and code (your
custom apps) to the new version. You'll learn how to upgrade your code in this
section.

These tutorials assume you're using the
[Code Upgrade Planner](/docs/reference/7-2/-/knowledge_base/r/code-upgrade-planner).
To follow along with this section, install the planner and step through the
upgrade instructions.

For convenience, this tutorial section also references documentation and
outlined steps to aid those opting to upgrade their code manually.

Here are the code upgrade steps:

1.  [Upgrade Your Development Environment](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-your-development-environment)

    Legacy project environments should be upgraded to the latest version of
    Liferay Workspace to ensure you leverage all available features.

    1.  [Set Up Liferay Workspace](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-your-development-environment#setting-up-liferay-workspace)

        A Liferay Workspace is a generated environment that is built to hold
        and manage your Liferay projects. Create/import a workspace to get
        started.

        1.  Create New Liferay Workspace {commandId='create_new_liferay_workspace'}

            If you don't have an existing 7.x Liferay Workspace, you must create
            one. Skip to the next step if you have an existing workspace.

        2.  Import Existing Liferay Workspace {commandId='import_existing_liferay_workspace'}

            Import an existing Liferay Workspace. If you don't have one, revisit
            the previous step.

    2.  [Configure Liferay Workspace Settings](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-your-development-environment#configuring-liferay-workspace)

        Set the @product@ version in workspace's configuration you intend to
        upgrade to.

        1.  Configure Bundle URL {commandId='configure_bundle_url'}

            Configure your bundle URL that the @product@ bundle is downloaded
            from.

        2.  Configure Target Platform Version {commandId='configure_target_platform_version'}

            Configure your Target Platform version, which provides the specific
            artifacts associated with a @product@ release.

        3.  Initialize Server Bundle {commandId='initialize_server_bundle'}

            Download the @product@ bundle you're upgrading to.

2.  [Migrate Plugins SDK Projects](/docs/tutorials/7-2/-/knowledge_base/t/migrating-plugins-sdk-projects-to-liferay-workspace)

    Copy your Plugins SDK projects into workspace and convert them to
    Gradle/Maven projects.

    1.  Import Existing Plugins SDK Projects {commandId='import_existing_plugins_sdk'}

        Import your existing Plugins SDK projects.

    2.  Migrate Existing Plugins to Workspace {commandId='migrate_existing_plugins_to_workspace'}

        Migrate your existing plugins to workspace. This involves moving the
        plugin to workspace and converting it to the workspace's build
        environment.

3.  [Upgrade Build Dependencies](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-build-dependencies)

    Optimize your workspace's build environment for the most efficient code
    upgrade experience.

    1.  Update Repository URL {commandId='update_repository_url'}

        Update your repository URL to Liferay's frequently updated CDN
        repository.

    2. Update Workspace Plugin Version {commandId='update_workspace_plguin_version'}

        Update your Workspace plugin version to leverage the latest features of
        Liferay Workspace.

    3.  Remove dependency Versions {commandId='remove_dependency_version'}

        Remove the project's dependency versions since it's leveraging target
        platform.

4.  [Fix Upgrade Problems](/docs/tutorials/7-2/-/knowledge_base/t/fixing-upgrade-problems)

    Fix common upgrade problems dealing with your project's dependencies and
    breaking changes.

    1.  Auto-Correct Upgrade Problems {commandId='auto_correct_find_upgrade_problems'}

        Auto-correct straightforward upgrade problems.

    2.  Find Upgrade Problems {commandId='find_upgrade_problems'}


        Find upgrade problems. These are problems that cannot be auto-corrected;
        you can update them manually according to the breaking changes
        documentation.

    3.  [Resolve Upgrade Problems](/docs/tutorials/7-2/-/knowledge_base/t/fixing-upgrade-problems#resolving-upgrade-problems)

        Mark upgrade problems as resolved after addressing them.

    4.  Remove Problem Markers {commandId='remove_upgrade_problems_markers'}

        Remove problem markers.

        After fixing your upgrade problems, remove the problem markers.

5.  [Upgrade Service Builder Services](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-service-builder-services)

    Upgrade your Liferay Service Builder services.

    1.  Remove Legacy Files {commandId='remove_legacy_files'}

        Remove legacy files that are no longer leveraged by Service Builder.

    2.  Migrate from Spring DI to OSGi Declarative Services

        Leverage OSGi Declarative Services in your Service Builder project.

    3.  Rebuild Services {commandId='rebuild_services'}

        Rebuild your project's services to persist your updates.

6.  [Upgrade Customization Plugins](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-customization-plugins)

    Upgrade your customization plugins so they're deployable to @product-ver@.

    1.  [Upgrade Customization Modules](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-customization-modules)

    2.  [Upgrade Core JSP Hooks](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-core-jsp-hooks)

    3.  [Upgrade Portlet JSP Hooks](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-portlet-jsp-hooks)

    4.  [Upgrade Service Wrapper Hooks](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-service-wrapper-hooks)

    5.  [Upgrade Core Language Key Hooks](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-core-language-key-hooks)

    6.  [Upgrade Portlet Language Key Hooks](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-portlet-language-key-hooks)

    7.  [Upgrade Model Listener Hooks](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-model-listener-hooks)

    8.  [Upgrade Event Action Hooks](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-event-action-hooks)

    9.  [Upgrade Servlet Filter Hooks](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-servlet-filter-hooks)

    10. [Upgrade Portal Properties Hooks](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-override-extension-hooks)

    11. [Upgrade Struts Action Hooks](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-struts-action-hooks)

7.  [Upgrade Themes]()

    Upgrade your themes.

8.  [Upgrade Layout Templates]()

9.  [Upgrade Frameworks & Features](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-frameworks-and-features)

    1.  [Upgrade JNDI Data Source Usage](/docs/customization/7-2/-/knowledge_base/c/connecting-to-jndi-data-sources)

        Use @product@'s classloader to access the app server's JNDI API.

    2.  [Upgrade Service Builder Service Invocation](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-service-builder-service-invocation)

        For Service Builder logic remaining in a WAR, you must implement a
        service tracker to call services. For logic divided into OSGi modules,
        you can leverage Declarative Services.

    3.  [Upgrade Service Builder](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-service-builder)

        Adapt your app to account for Service Builder-specific changes.

    4.  [Migrate Off of Velocity Templates]()

10. [Upgrade Portlets](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-portlets)

    Upgrade your portlets so they're deployable to @product-ver@.

    1.  [Upgrade Generic Portlets](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-a-genericportlet)

    2.  [Upgrade Liferay MVC Portlets](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-a-liferay-mvc-portlet)

    3.  [Upgrade JSF Portlets](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-a-liferay-jsf-portlet)

    4.  [Upgrade Servlet-based Portlets](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-a-servlet-based-portlet)

    5.  [Migrate from Spring Portlet MVC to PortletMVC4Spring]()

    6.  [Upgrade Struts 1 Portlets](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-a-struts-1-portlet)

11.  [Upgrade Web Plugins]()

12.  [Upgrade Ext Plugins]()



Once you've finished the code upgrade steps, your custom apps will be compatible
with @product-ver@!

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/upgrading-your-development-environment">Let's Go!<span class="icon-circle-arrow-right"></span></a>
