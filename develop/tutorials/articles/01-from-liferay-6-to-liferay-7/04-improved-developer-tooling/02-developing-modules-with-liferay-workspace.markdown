# Developing Modules with Liferay Workspace [](id=developing-modules-with-liferay-workspace)

Workspace is a great Liferay module development environment because of these
features: 

- Templates that bootstrap module creation
- Gradle and Maven build systems for managing dependencies and assembling
  modules
- Module deployment and runtime management capabilities

[Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli) (Blade), which is
a part of Workspace, has over thirty templates for Gradle and Maven-based module
projects--and more are being added. The templates stub out classes and resource
files for you to fill in with business logic and key information.

Here are some of the template's names:

- Activator
- API
- Content Targeting Report
- Content Targeting Rule
- Content Targeting Tracking Action
- Control Menu Entry
- MVC Portlet
- Panel App
- Portlet
- Portlet Configuration Icon
- Portlet Provider
- Portlet Toolbar Contributor
- Service
- Service Builder
- Service Wrapper
- Simulation Panel Entry
- Template Context Contributor
- etc..

[Blade creates modules](/develop/tutorials/-/knowledge_base/7-1/creating-projects-with-blade-cli)
based on these templates. For a full list of these templates, visit the
[Project Templates](/develop/reference/-/knowledge_base/7-1/project-templates)
reference section.

For example, the following Blade command creates a Liferay MVC Portlet module
called `my-module`:

    blade create -t mvc-portlet -p com.liferay.docs.mymodule -c MyMvcPortlet my-module

Liferay @ide@'s 
[module project wizard](/develop/tutorials/-/knowledge_base/7-1/creating-modules-with-liferay-ide)
creates Workspace modules from the templates too.

![Figure 1: Liferay @ide@ lets developers select templates to stub out modules.](../../../images/improved-tooling-module-wizard.png)

Liferay @ide@'s component wizard facilitates 
[creating component classes](/develop/tutorials/-/knowledge_base/7-1/creating-modules-with-liferay-ide#creating-component-classes)
for portlets, service wrappers, Struts actions, and more.

![Figure 2: Liferay @ide@'s component wizard facilitates creating component classes.](../../../images/improved-tooling-component-wizard.png)

Building and deploying modules in a Workspace is a snap using
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/deploying-modules-with-liferay-ide)
and
[Blade](/develop/tutorials/-/knowledge_base/7-1/deploying-modules-with-blade-cli).
Workspace uses BndTools to generate each module's OSGi headers in a
`META-INF/MANIFEST.MF` file. Workspace deploys modules to the OSGi container
using Felix File Install commands.

Liferay @ide@ lets you deploy modules by dragging them onto your Portal server.

![Figure 3: Liferay @ide@ lets you deploy modules using drag-and-drop.](../../../images/improved-tooling-drag-n-drop-onto-server.png)

To learn more about Workspace and using it in Liferay @ide@, see
[this tutorial](/develop/tutorials/-/knowledge_base/7-1/creating-a-liferay-workspace-with-liferay-ide). 

In a terminal, you can deploy modules using Blade's `deploy` command. For
example, the following command deploys the current module and 
["watches" for module changes to redeploy automatically](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#redeploying-module-changes-automatically).

    blade deploy -w

Make sure to check out the tutorial
[Starting Module Development](/develop/tutorials/-/knowledge_base/7-1/starting-module-development) 
for even more information on module development.
