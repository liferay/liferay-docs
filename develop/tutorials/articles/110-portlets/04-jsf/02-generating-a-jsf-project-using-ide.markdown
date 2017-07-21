# Generating a JSF Project Using @ide@ [](id=generating-a-jsf-project-using-ide)

You can generate a Liferay Faces application without having to create your own
folder structure and descriptor files manually using Liferay @ide@. If you're
interested in creating the structure of a JSF application manually or if you
want to examine a basic JSF application structure, visit the
[Creating a JSF Project Manually](/develop/tutorials/-/knowledge_base/7-0/creating-a-jsf-project-manually.markdown)
tutorial.

In this tutorial, you'll generate an example JSF project using Liferay @ide@.
Open your @ide@ instance to get started.

1.  Navigate to *File* &rarr; *New* &rarr; *Project...*. This opens a new
    project wizard.

2.  Select the *Liferay* project and choose *Liferay JSF Project* from the
    listed subprojects. Then click *Next*.

    ![Figure 1: Choose the *Liferay JSF Project* option to begin creating a JSF project in @ide@.](../../../images/jsf-project-ide.png)

3.  Assign your JSF project a name, build framework (Gradle or Maven), and
    Component Suite. You have five component suites to choose from:

    - ICEFaces
    - JSF Standard
    - Liferay Faces Alloy
    - PrimeFaces
    - RichFaces

    ![Figure 2: Choose your preferred options for your JSF project.](../../../images/new-jsf-project-ide.png)

4.  Click *Finish* to generate your Liferay JSF project.

You've generated a Liferay JSF project using @ide@! The project you generated
contains a simple portlet that you can customize.

![Figure 3: The generated JSF portlet project displays basic build information.](../../../images/jsf-ide-generated-project.png)

+$$$

**Note:** There is another option in @ide@'s *File* &rarr; *New* menu named
*Liferay JSF Portlet*. This is intended to add portlets to existing JSF
projects. Currently, this is only configured to create Liferay Portal 6.2 JSF
portlets. Do not use this option if you're developing for @product-ver@.

$$$

To
[deploy the new JSF project to your @product@ instance](/develop/tutorials/-/knowledge_base/7-0/deploying-modules-with-liferay-ide),
drag and drop it onto the Liferay server.

Fantastic! You're now able to quickly generate your Liferay JSF project using
Liferay @ide@!
