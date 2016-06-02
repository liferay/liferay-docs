# Liferay Module Projects Introduce

# 1 Working with Liferay Module Projects tutorial

## 1.1 What makes a Liferay module project in Eclipse

Module project is based on osgi technology to develop liferay portlet, using
gradle mode to create, compile and publish. It is a new kind of project type.

## 1.2 Creating new modules in Liferay IDE

Our IDE-3.0 provide a “module project wizard” for users to create all kinds of
module projects. It is very convenient. So how to use it? You could create new
liferay module project by `“File -> New -> Liferay Module Project”`, then input
project name,click finish. And you could also choose the project template which
you want. It’s very simple.

![NewModuleProjects](images/NewModuleProject.png)

## 1.3 Importing existing

IDE-3.0 also provides a method to import existing module project for users. As a
user, you could import existing module project by `“File -> Import -> Liferay ->
Liferay Module Project(s)”`, then point to the project location.

![ImportModuleWizard](images/ImportWizard.png)

![ChooseProjectLocation](images/ChooseProject.png)

## 1.4 Updating module dependencies through gradle menu

For some users, they want to add some dependencies on their project for some
other development, could add this through IDE.Users could double click
`“build.gradle”` on a module project then add dependency on the dependencies
part. Such as :
`{compile group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
...
}`

`Notice`:The most important thing is that when you finished add dependency, you have to run `“Refresh Gradle Project” by “right click project - > Gradle -> Refresh Gradle Project”`.

![RefreshGradleProject](images/RefreshGradleproject.png)

## 1.5 Deploying modules to Liferay DXP in Servers View

IDE-3.0 also supports deploy module project to the started portal in server view
such as Liferay DXP.

## 1.6 Using integrated gogo shell

For the deployed module project, if you want to check if it has been deployed
successfully, one way you could check if it added on the website. And another
way is you could use `“Gogo Shell”`. Right click started portal in server view
choose `“Open Gogo Shell”`, then in the gogo shell view input `“lb”` , then it
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
for the `bnd.bnd` file the `“Activator” will include some message`.

such as :  `“Bundle-Activator: com.example.aaactivatorActivator”`

![Activator](images/Activator.png)

### 2.2.4 Servicebuilder

When finished create module-servicebuilder, it generates `three projects`
including one parent project and two sub projects.

![ModuleServicebuilder](images/ModuleServicebuilder.png)

User could set some properties in `service.xml file` then could run
`build-service` to generate some java classes by `“right click project-service
-> Liferay -> Gradle -> build-service”`. Also could connect database and add
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

