---
header-id: gradle-in-dev-studio
---

# Gradle in Dev Studio

[Gradle](http://gradle.org/) is a popular open source build automation system.
You can take full advantage of Gradle in Liferay Dev Studio through
[Buildship](https://projects.eclipse.org/releases/photon), a collection
of Eclipse plugins that provide support for building software using Gradle
with Liferay Dev Studio. Buildship is bundled with Liferay Dev Studio versions
3.0 and higher.

![Figure 1: Navigate to *Help* &rarr; *Installation Details* to view plugins included in Dev Studio.](../../../images/buildship-in-liferayide.png)

This reference article highlights some useful tips for leveraging Gradle in Dev
Studio.

- [Creating Pure Gradle Projects](#creating-pure-gradle-projects)
- [Importing Pure Gradle Projects](#importing-pure-gradle-projects)
- [Gradle Tasks and Executions](#gradle-tasks-and-executions)

Note that
[creating Liferay Gradle projects](/developer/reference/-/knowledge_base/7-2/creating-a-project#liferay-dev-studio)
and
[deploying Gradle projects](/developer/reference/-/knowledge_base/7-2/deploying-a-project#liferay-dev-studio)
with Dev Studio are covered in their respective articles.

The first thing you'll learn about in this tutorial is creating pure Gradle
projects in Dev Studio.

## Creating Pure Gradle Projects

Most of Dev Studio's wizards rely on your usage of Liferay Workspace. This is
for good reason; it's the recommended developer environment for Liferay
projects. You can, however, create pure Gradle projects and manually configure
them to be deployable to @product@.

You can create a pure Gradle project by using the Gradle Project wizard.

1.  Navigate to *File* &rarr; *New* &rarr; *Project...*.

2.  Select *Gradle* &rarr; *Gradle Project*. Then click *Next* &rarr; *Next*.

3.  Enter a valid project name. You can also specify your project location and
    working sets.

4.  Optionally, you can navigate to the next page and specify your Gradle
    distribution and other advanced options. Once you're finished, select
    *Finish*.

![Figure 2: You can specify your Gradle distribution and advanced options such as home directories, JVM options, and program arguments.](../../../images/new-gradle-project.png)

Excellent! You've created a pure Gradle project using Dev Studio.

## Importing Pure Gradle Projects

You can also import existing pure Gradle projects in Dev Studio.

1.  Go to *File* &rarr; *Import...*.

2.  Select *Gradle* &rarr; *Existing Gradle Project* &rarr; *Next* &rarr;
    *Next*.

    ![Figure 3: You can specify what Gradle project to import from the *Import Gradle Project* wizard.](../../../images/import-gradle-project.png)

3.  Click the *Browse...* button to choose a Gradle project.

4.  Optionally, you can navigate to the next page and specify your Gradle
    distribution and other advanced options. Once you're finished, click *Next*
    again to review the import configuration. Select *Finish* once you've
    confirmed your Gradle project import.

Next you'll learn about Gradle tasks and executions, and learn how to run and
display them in Dev Studio.

## Gradle Tasks and Executions

Dev Studio provides two views to enhance your development experience using
Gradle: Gradle Tasks and Gradle Executions. You can open these views by
following the instructions below.

1. Go to *Window* &rarr; *Show View* &rarr; *Other...*.

2. Navigate to the *Gradle* folder and open *Gradle Tasks* and *Gradle
   Executions*.

Gradle tasks and executions views open automatically once you create or import a
Gradle project.

The Gradle Tasks view lets you display the Gradle tasks available for you to use
in your Gradle project. Users can execute a task listed under the Gradle project
by double-clicking it.

![Figure 4: Navigate into your preferred Gradle project to view its available Gradle tasks.](../../../images/gradle-tasks.png)

Once you've executed a Gradle task, you can open the Gradle Executions view to
inspect its output.

![Figure 5: The Gradle Executions view helps you visualize the Gradle build process.](../../../images/gradle-executions.png)

Keep in mind that if you change the Gradle build scripts inside your Gradle
projects (e.g., `build.gradle` or `settings.gradle`), you must refresh the
project so Dev Studio can account for the change and display it properly in
your views. To refresh a Gradle project, right-click on the project and select
*Gradle* &rarr; *Refresh Gradle Project*.

![Figure 6: Make sure to always refresh your Gradle project in Dev Studio after build script edits.](../../../images/refresh-gradle-project.png)

If you prefer Eclipse refresh your Gradle projects automatically, navigate to 
*Window* &rarr; *Preferences* &rarr; *Gradle* and enable the *Automatic Project
Synchronization* checkbox. If you'd like to enable Gradle's automatic
synchronization for just one Gradle project, you can right-click a Gradle
project and select *Properties* &rarr; *Gradle* and enable auto sync that way.

Excellent! You're now equipped with the knowledge to add, import, and build your
Gradle projects in Liferay Dev Studio!
