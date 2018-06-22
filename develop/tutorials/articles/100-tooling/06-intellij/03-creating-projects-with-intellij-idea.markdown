# Creating Projects with IntelliJ IDEA [](id=creating-projects-with-intellij-idea)

IntelliJ IDEA provides a New Liferay Modules wizard to create a variety of
different module projects. You can also use the same wizard to create theme
projects, WAR-style projects, and more. Before beginning, ensure you've
[created/imported a Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/creating-a-liferay-workspace-with-intellij-idea)
in your IntelliJ environment. Follow the steps below to create a @product@
module:

1.  Navigate to *File* &rarr; *New* &rarr; *Liferay Module*.

    ![Figure 1: Selecting *Liferay Module* opens the New Liferay Modules wizard.](../../../images/intellij-new-liferay-module.png)

2.  Select the project you want to create. Although the wizard characterizes
    itself for *modules*, there are many available projects that are not
    OSGi-based modules (e.g., `theme`, `war-mvc-portlet`, etc.). See the
    [Project Templates](/develop/reference/-/knowledge_base/7-1/project-templates)
    reference section for more information on the available templates.

    ![Figure 2: Choose the project template to create your module.](../../../images/intellij-modules.png)

3.  Configure your project's SDK (i.e., JDK), package name, class name, and
    service name, if necessary. Then click *Next*.

4.  Give your project a name. Then click *Finish*.

Awesome! Your project is available under its project type folder in your
workspace.
