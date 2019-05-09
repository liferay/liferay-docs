# Upgrading Code to @product-ver@

Upgrading to @product-ver@ involves migrating your installation and code (your
custom apps) to the new version. You'll learn how to upgrade your code in this
section.

Here are the code upgrade steps:

1.  [Upgrade Your Development Environment](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-your-development-environment)

    Legacy project environments should be upgraded to the latest version of
    Liferay Workspace to ensure you leverage all available features.

    1.  Set up Liferay Workspace

        1.  Create New Liferay Workspace

            Create a new Liferay Workspace.

        2.  Import Existing Liferay Workspace

            If you do not have an existing 7.x workspace, go to the previous
            step.

    2.  [Configure Liferay Workspace Settings](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-your-development-environment#configuring-liferay-workspace)

        Set the @product@ version in workspace's configuration you intend to
        upgrade to.
        
        1.  Configure Bundle URL

            Configure your bundle URL.

        2.  Configure Target Platform Version

            Configure your Target Platform version.
            
        3.  Initialize Server Bundle

            Initialize your Liferay server bundle.

2.  [Migrate Plugins SDK Projects](/docs/tutorials/7-2/-/knowledge_base/t/migrating-plugins-sdk-projects-to-liferay-workspace)

    Copy your Plugins SDK projects into workspace and convert them to
    Gradle/Maven projects.

    1.  Import Existing Plugins SDK Projects

        Import your existing Plugins SDK projects.

    2.  Migrate Existing Plugins to Workspace

        Migrate existing plugins to Workspace.

3.  [Upgrade Build Dependencies](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-build-dependencies)

    Optimize your workspace's build environment for the most efficient code
    upgrade experience.

    1.  Update Repository URL

        Update your repository URL.

    2. Update Workspace Plugin Version

        Update your Workspace Plugin version

    3.  Remove dependency Versions

        Remove dependency versions.

4.  [Fix Upgrade Problems](/docs/tutorials/7-2/-/knowledge_base/t/fixing-upgrade-problems)

    Fix common upgrade problems dealing with your project's dependencies and
    breaking changes.

    1.  Auto-Correct Upgrade Problems

        Leverage auto-correct to upgrade code.

    2.  Find Upgrade Problems

        Find upgrade problems.

    3.  Resolve Upgrade Problems

        Mark upgrade problems as resolved after addressing them.

    4.  Remove Problem Markers

        Remove problem markers.

5.  Upgrade Liferay Service Builder Services

    Upgrade your Liferay Service Builder services.

    1.  Remove Legacy Files

    2.  Migrate from Spring DI to OSGi Declarative Services
    
    3.  Rebuild Services
    
6.  Upgrade Customization Plugins

    1.  Upgrade Override/Extension Modules

    2.  Upgrade Liferay Core JSP Hooks

    3.  Upgrade Liferay Portlet JSP Hooks

    4.  Upgrade Service Wrapper Hooks

    5.  Upgrade Language Key Hooks

    6.  Upgrade Model Listener Hooks

    7.  Upgrade Event Actions Hooks

    8.  Upgrade Servlet Filter Hooks

    9.  Upgrade Portal Properties Hooks

    10. Upgrade Struts Action Hooks

7. [Upgrade Themes](https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-1/upgrading-6-2-themes-to-7-1)

    Upgrade your themes.

8. Upgrade Layout Templates

9. Upgrade Frameworks & Features

    1. Upgrade JNDI Data Source Usage

    2. Upgrade Service Builder Service Invocation

    3. Upgrade Service Builder

    4. Migrate Off of Velocity Templates

10. Upgrade Portlets

    Upgrade your portlets.

    1. Upgrade JSF Portlets

    2. Migrate from Spring Portlet MVC to PortletMVC4Spring

    3. Upgrade Liferay MVC Portlets

    4. Upgrade Generic Portlets

    5. Upgrade Servlet-base Portlets

    6. Upgrade Struts 1 Portlets

11. Upgrade Web Plugins

12. Upgrade Ext Plugins

<!--

1.  [Upgrade Your Development Environment](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-your-development-environment)

    Legacy project environments should be upgraded to the latest version of
    Liferay Workspace to ensure you leverage all available features.

    1.  [Install a Liferay Workspace](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-your-development-environment#installing-a-new-liferay-workspace)

    Create a new workspace or import and update an existing one.

    2.  [Configure Liferay Workspace](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-your-development-environment#configuring-liferay-workspace)

    Set the @product@ version in workspace's configuration you intend to upgrade
    to.

    3.  [Initialize a Server Bundle](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-your-development-environment#initializing-a-server-bundle)

    Download the @product@ bundle you're upgrading to.

    4.  [Upgrade Your Maven Build Environment](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-your-maven-build-environment)

    If you're upgrading a Maven build with Liferay Portal 6.2-compatible code or
    older, you must upgrade your Maven environment.

2.  [Migrate Plugins SDK Projects to Liferay Workspace](/docs/tutorials/7-2/-/knowledge_base/t/migrating-plugins-sdk-projects-to-liferay-workspace)

    Copy your Plugins SDK projects into workspace and convert them to
    Gradle/Maven projects.

3.  [Upgrade Build Dependencies](/docs/tutorials/7-2/-/knowledge_base/t/upgrading-build-dependencies)

    Optimize your workspace's build environment for the most efficient code
    upgrade experience.

4.  [Fix Upgrade Problems](/docs/tutorials/7-2/-/knowledge_base/t/fixing-upgrade-problems)

    Fix common upgrade problems dealing with your project's dependencies and
    breaking changes.

    1.  [Resolve a Project's Dependencies](/docs/tutorials/7-2/-/knowledge_base/t/resolving-a-projects-dependencies)

    2.  [Resolve Breaking Changes](/docs/tutorials/7-2/-/knowledge_base/t/resolving-breaking-changes)

To be continued...

-->

<!--

5. Upgrade Service Builder Services
    1. Remove Legacy Files
    2. Migrate from Spring DI to OSGi Declarative Services
    3. Rebuild Services
6. Upgrade Customization Plugins
    1. Upgrade Override/Extension Modules
    2. Upgrade Liferay Core JSP Hooks
    3. Upgrade Liferay Portlet JSP Hooks
    4. Upgrade Service Wrapper Hooks
    5. Upgrade Language Key Hooks
    6. Upgrade Model Listener Hooks
    7. Upgrade Event Actions Hooks
    8. Upgrade Servlet Filter Hooks
    9. Upgrade Portal Properties Hooks
    10. Upgrade Struts Action Hooks
7. Upgrade Themes
    1. [Upgrade a 7.x Theme to 7.2](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-7-x-themes-to-7-2)
    2. [Upgrade a 6.2 Theme to 7.2](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-6-2-themes-to-7-2)
8. Upgrade Layout Templates
9. Upgrade Frameworks & Features
    1. Upgrade JNDI Data Source Usage
    2. Upgrade Service Builder Service Invocation
    3. Upgrade Service Builder
    3. Migrate Off of Velocity Templates
10. Upgrade Portlets
    1. Upgrade JSF Portlets
    2. Migrate from Spring Portlet MVC to PortletMVC4Spring
    3. Upgrade Liferay MVC Portlets
    4. Upgrade Generic Portlets
    5. Upgrade Servlet-base Portlets
    6. Upgrade Struts 1 Portlets
11. Upgrade Web Plugins
12. Upgrade Ext Plugins

-->

Be sure to follow the upgrade topic details outlined above to ensure a
successful upgrade. You can also complete these steps automatically using the
[Code Upgrade Planner](/docs/reference/7-2/-/knowledge_base/r/code-upgrade-planner).

Once you've finished the code upgrade steps, your custom apps will be compatible
with @product-ver@!

<a class="go-link btn btn-primary" href="/docs/7-2/tutorials/-/knowledge_base/t/upgrading-your-development-environment">Let's Go!<span class="icon-circle-arrow-right"></span></a>
