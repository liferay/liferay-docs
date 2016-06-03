# Working with Liferay Module Projects

Modules are OSGi-based projects used to develop Liferay applications. In Liferay
Workspaces, Gradle is the build tool used to create, compile, and publish
Liferay modules. Before you can begin creating and managing your Liferay module
projects, you'll need to create a Liferay Workspace, which is covered in the
[Creating a Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/creating-a-liferay-workspace)
tutorial. Once you've created your Liferay Workspace, you can begin using it to
create Gradle-based OSGi modules for @product@.

## Creating New Modules in Liferay IDE

IDE provides a *Module Project Wizard* for users to create a variety of
different module projects. You can create a new Liferay module project by
navigating to *File* &rarr; *New* &rarr; *Liferay Module Project*, inputting the
project name and project template, and clicking *Finish*.

![NewModuleProjects](images/NewModuleProject.png)

IDE also provides a method to import existing module projects. You can import a
module project by navigating to *File* &rarr; *Import* &rarr; *Liferay* &rarr;
*Liferay Module Project(s)*, then point to the project location. Once you've
selected the project to import, click *Finish*.

![ImportModuleWizard](images/ImportWizard.png)

![ChooseProjectLocation](images/ChooseProject.png)

## Updating Module Dependencies Via Gradle Menu

There are times when your module project will require dependency updates. You
can update your module dependencies manually via the command line, or you can do
this via the IDE Gradle Menu. Using IDE, double-click the
`build.gradle` file in a module project and add a dependency in the
`dependencies` code block, such as:

    dependencies {
        compile group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
        ...
    }

When adding a new dependency to a Gradle project, you must refresh the project
by right-clicking the project and selecting *Gradle* &rarr; *Refresh Gradle
Project*.

![RefreshGradleProject](images/RefreshGradleproject.png)

## Deploying Modules to Liferay in Servers View

IDE also supports module project deployment to a running @product@ instance in
the *Servers* view.

## Using Integrated Gogo Shell

For the deployed module project, if you want to check if it has been deployed
successfully, one way you could check if it added on the website. And another
way is you could use `"Gogo Shell"`. Right click started portal in server view
choose `"Open Gogo Shell"`, then in the gogo shell view input `"lb"` , then it
will list all the deployed project.

![OpenGogoShell](images/OpenGogoShell.png)

If the project status is active, then it already deployed successfully.

![CheckDeploySuccess](images/DeploySuccessful.png)

# 2 New liferay module project wizard

## 2.1 Creating new project

Liferay IDE will provide two types for users to create new project: `gradle
build type(has already finished)` and maven build type(will added in future ).

### 2.1.1 Gradle build type of detail

The screen will show all kinds of project templates of gradle-module’s build
types.

![ModuleTemplate](images/ModuleTemplate.png)

## 2.2 Explain all type options

Now IDE provide `six kinds of` types of module project.

### 2.2.1 Mvcportlet

This is the default template. The java class extends `MVCPortlet` by default. It
`includes jsp files` under resources folder (META-INF/resources).

### 2.2.2 Portlet

This is a general portlet, and it extends `GenericPortlet` by default. `No jsp
files`.

### 2.2.3 Activator

For this type, when `server / stop` will call the method of `start / stop`. And
for the `bnd.bnd` file the `"Activator" will include some message`.

such as :  `"Bundle-Activator: com.example.aaactivatorActivator"`

![Activator](images/Activator.png)

### 2.2.4 Servicebuilder

When finished create module-servicebuilder, it generates `three projects`
including one parent project and two sub projects.

![ModuleServicebuilder](images/ModuleServicebuilder.png)

User could set some properties in `service.xml file` then could run
`build-service` to generate some java classes by `"right click project-service
-> Liferay -> Gradle -> build-service"`. Also could connect database and add
some methods through these files that just generated for users. Notice when
finished run build-service, you need to `refresh parent project`.

![BuildService](images/BuildService.png)

![AfterBuildService](images/AfterBuildservice.png)

### 2.2.5 Service

	There are many services inside portal's. So you need to choose one "Service
	Name" on second page to implements. IDE-3.0 provides lots of services by
	default for users if haven’t set portal in Liferay IDE. Else it will read
	services from the portal that you set.

![ChooseOneService](images/ServiceChooseServicename.png)

### 2.2.6 Servicewrapper

    For module-servicewrapper, user could cover write services from portal's. It
    is similar with module-service, you need to choose one "Servicewrapper Name"
    on second page to extends. IDE provide lots of service wrappers by default
    for users if doesn't set portal in Liferay IDE. Else it will read service
    wrappers from the portal that you set.

![ChooseServicewrapper](images/SWChooseServicename.png)

`Tip:`For the Liferay Module Project except "activator", other templates
including next page, for users also could add properties on second page.Such as:

![SetProperties](images/SetProperties.png)

