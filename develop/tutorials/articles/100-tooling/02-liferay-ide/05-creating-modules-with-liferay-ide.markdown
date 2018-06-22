# Creating Modules with Liferay Dev Studio [](id=creating-modules-with-liferay-ide)

Dev Studio provides a Module Project Wizard for users to create a variety of
different module projects. You can create a new Liferay module project by
navigating to *File* &rarr; *New* &rarr; *Liferay Module Project*.

![Figure 1: When selecting *New* &rarr; *Liferay Module Project*, a Module Project Wizard appears.](../../../images/new-module-project.png)

You're given options for project name, location, build type, and template type.
You can build your project using Gradle or Maven. If you're unsure for which
template type to choose, see the
[Project Templates](/develop/reference/-/knowledge_base/7-1/project-templates)
reference section. Click *Next* and you're given additional configuration
options based on the project template you selected. For example, if you selected
a template that requires a component class, you must configure it in the wizard.

![Figure 2: Specify your component class's details in the Portlet Component Class Wizard.](../../../images/component-class-wizard.png)

You can specify your component class's name, package name, and its properties.
The properties you assign are the ones found in the `@Component` annotation's
`property = {...}` assignment. See more about creating a component class in
Liferay Dev Studio in the [Creating Component Classes](#creating-component-classes)
section.

Once you've configured your module, click *Finish* to create your project.

Now that you've created your module project, you can configure your project's
presentation in the Dev Studio's Project Explorer. To change the project's
presentation, select the default *Hierarchical* or *Flat* views. To do this,
navigate to the Project Explorer's *View Menu*
(![View Menu](../../../images/icon-ide-view-menu.png)), select *Projects
Presentation* and then select the presentation mode you'd like to display. The
Hierarchical view displays subfolders and subprojects under the project, whereas
the Flat view displays the modules separately from their project.

![Figure 3: The Hierarchical project presentation mode is set, by default.](../../../images/workspace-presentation.png)

You now have the knowledge to create a Liferay module project from Liferay
Dev Studio.

## Creating Component Classes [](id=creating-component-classes)

You can also create a new component class for a pre-existing module project.
Navigate to *File* &rarr; *New* &rarr; *Liferay Component Class*. This is a
similar wizard to the previous component class wizard, except you can select a
component class template. There are many templates in the `Component Class
Template` list:

- *Auth Failures*: processes a verify login failure
- *Auth Max Failure*: processes maximum number of login failures
- *Authenticator*: authenticates processing
- *Friendly URL Mapper*: processes Friendly URLs
- *GOGO Command*: creates a custom Gogo command
- *Indexer Post Processor*: creates a new Indexer Post Processor
- *Login Pre Action*: creates a login pre action
- *MVC Portlet*: creates a new MVC portlet
- *Model Listener*: sets a model listener
- *Poller Processor*: creates a new poller processor
- *Portlet*: creates a new portlet class file
- *Portlet Action Command*: creates a new portlet action command
- *Portlet Filter*: creates a new portlet filter
- *Rest*: calls and wraps inner service in the way of Rest
- *Service Wrapper*: creates a new service wrapper
- *Struts in Action*: creates a new struts action
- *Struts Portlet Action*: creates a new struts portlet action

Next you'll learn how to import existing projects into Dev Studio.

## Importing Existing Module Projects [](id=importing-existing-module-projects)

Dev Studio also provides a method to import existing module projects. You can import a
module project by navigating to *File* &rarr; *Import* &rarr; *Liferay* &rarr;
*Liferay Module Project(s)*. Then point to the project location and click
*Finish*.

![Figure 4: Select the *Liferay Module Project(s)* to import a module project.](../../../images/import-wizard.png)

You're now equipped to import module projects into Liferay Dev Studio. Now go
out there and get stuff done!
