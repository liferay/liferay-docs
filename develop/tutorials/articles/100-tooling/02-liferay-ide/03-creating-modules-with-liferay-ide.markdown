# Creating Modules with Liferay IDE [](id=creating-modules-with-liferay-ide)

IDE provides a Module Project Wizard for users to create a variety of
different module projects. You can create a new Liferay module project by
navigating to *File* &rarr; *New* &rarr; *Liferay Module Project*.

![Figure 1: When selecting *New* &rarr; *Liferay Module Project*, a Module Project Wizard appears.](../../../images/new-module-project.png)

You're given options for project name, location, build type, and template
type. Currently, you're only able to create a Gradle-based module. The project
templates will look familiar to you if you've already read through the
[Blade CLI tutorials](/develop/tutorials/-/knowledge_base/7-0/blade-cli). The
same templates that are available through Blade CLI are provided through this
wizard. Click *Next* and you're given additional configuration options for a
component class.

![Figure 2: Specify your component class's details in the Portlet Component Class Wizard.](../../../images/component-class-wizard.png)

You can specify your component class's name, package name, and its properties.
The properties you assign are the ones found in the `@Component` annotation's
`property = {...}` assignment.

Once you've configured your module project's component class, click *Finish* to
create your project.

## Creating Component Classes

You can also create a new component class for a pre-existing module project.
Navigate to *File* &rarr; *New* &rarr; *Liferay Component Class*. This is a
similar wizard to the previous component class wizard, except you can select a
component class template. There are many templates in the `Component Class
Template` list:

- *Auth Failure*: processes a verify login failure
- *Auth Max Failure*: processes maximum number of login failures
- *Authenticator*: authenticates processing
- *Friendly URL Mapper*: processes Friendly URLs
- *GOGO Command*: creates a custom Gogo command
- *Indexer Post Processor*: creates a new Indexer Post Processor
- *Login Pre Action*: creates a login pre action
- *Model Listener*: sets a model listener
- *Poller Processor*: creates a new poller processor
- *Portlet*: creates a new portlet class file
- *Portlet Action Command*: creates a new portlet action command
- *Portlet Filter*: creates a new portlet filter
- *Rest*: calls and wraps inner service in the way of Rest
- *Service Wrapper*: creates a new service wrapper
- *Struts in Action*: creates a new strutus action
- *Struts Portlet Action*: creates a new struts portlet action

## Possible Dependency Issues

When selecting the `Authenticator`, `Portlet Action Command`, `Rest`,
or `Service Wrapper` templates, you may run into some dependency issues that
could cause errors in your project. There is a set of steps outlined below that
you should follow, with sub-steps for each of the four templates that could
cause problems.

1. Open the module project's `build.gradle` file.

2. Check whether the appropriate dependencies exist. These are outlined below.

3. Right-click your project and select *Gradle* &rarr; *Refresh Gradle Project*.

4. If you're using the *Portlet Action Command* template, you'll also need to
   change the component class declaration from implementing the
   `FreeMarkerPortlet` class to extending it. For instance, your `*Portlet`
   component class should have the following declared:

    YourPortletClass extends FreeMarkerPortlet

The dependencies to check for when using each template are outlined below:

**Authenticator**

- `compile com.liferay.portal:com.liferay.portal.kernel:VERSION`
- `compile org.osgi:org.osgi.service.component.annotations:VERSION`

**Portlet Action Command**

- `compile javax.portlet:portlet-api:VERSION`
- `compile javax.servlet:javax.servlet-api:VERSION`
- `compile org.osgi:org.osgi.service.component.annotations:VERSION`
- `compile com.liferay.portal:com.liferay.portal.kernel:VERSION`
- `compile com.liferay.portal:com.liferay.util.bridges:VERSION`
- `compile com.liferay.portal:com.liferay.util.taglib:VERSION`

**Rest**

- `compile javax.ws.rs:javax.ws.rs-api:VERSION`

**Service Wrapper**

- The service wrapper class being used. For example, if you're using the
  `BookmarksEntryLocalServiceWrapper`, the following dependency would be
  required:

        compile com.liferay:com.liferay.bookmarks.api:VERSION

Make sure the replace the `VERSION` text with the appropriate version for each
specified dependency.

Once you've created your module project, you can configure your project's
presentation in the IDE's Project Explorer. To change the project's
presentation, select the default *Hierarchical* or *Flat* views. To do this,
navigate to the Project Explorer's *View Menu*
(![View Menu](../../../images/icon-ide-view-menu.png)), select *Projects
Presentation* and then select the presentation mode you'd like to display. The
Hierarchical view displays subfolders and subprojects under the project, whereas
the Flat view displays the modules separately from their project.

![Figure 3: The Hierarchical project presentation mode is set, by default.](../../../images/workspace-presentation.png)

IDE also provides a method to import existing module projects. You can import a
module project by navigating to *File* &rarr; *Import* &rarr; *Liferay* &rarr;
*Liferay Module Project(s)*. Then point to the project location and click
*Finish*.

![Figure 4: Select the *Liferay Module Project(s)* to import a module project.](../../../images/import-wizard.png)

You now have the knowledge to create a Liferay module project from Liferay
IDE. Now go out there and get stuff done!
