# Using Gradle in Liferay IDE [](id=using-gradle-in-liferay-ide)

[Gradle](http://gradle.org/) is a popular open source build automation system.
You can take full advantage of Gradle in Liferay IDE by utilizing
[Buildship](https://projects.eclipse.org/releases/neon), which is a collection
of Eclipse plugin-ins that provide support for building software using Gradle
with Liferay IDE. Buildship is bundled with Liferay IDE versions 3.0 and higher.

![Figure 1: Navigate to *Help* &rarr; *Installation Details* to view plugins included in Liferay IDE.](../../../images/buildship-in-liferayide.png)

The first thing you'll learn about in this tutorial is creating Gradle projects
in IDE.

## Creating and Importing Gradle Projects [](id=creating-and-importing-gradle-projects)

You can create a Gradle project by using the Gradle Project wizard.

1. Navigate to *File* &rarr; *New* &rarr; *Project...* and select *Gradle*
   &rarr; *Gradle Project*. Finally, click *Next* &rarr; *Next*.

2. Enter a valid project name. You can also specify your project location and
   working sets.

3. Optionally, you can navigate to the next page and specify your Gradle
   distribution and other advanced options. Once you're finished, select
   *Finish*.

![Figure 2: You can specify your Gradle distribution and advanced options such as home directories, JVM options, and program arguments.](../../../images/new-gradle-project.png)

You can also import existing Gradle projects in Liferay IDE.

1. Go to *File* &rarr; *Import...* &rarr; *Gradle* &rarr; *Gradle Project*
   &rarr; *Next* &rarr; *Next*.

    ![Figure 3: You can specify what Gradle project to import from the *Import Gradle Project* wizard.](../../../images/import-gradle-project.png)

2. Click the *Browse...* button to choose a Gradle project.

3. Optionally, you can navigate to the next page and specify your Gradle
   distribution and other advanced options. Once you're finished, click *Next*
   again to review the import configuration. Select *Finish* once you've
   confirmed your Gradle project import.

    ![Figure 4: You can preview your Gradle project's import information.](../../../images/import-configuration-overview.png)

Next you'll learn about Gradle tasks and executions, and learn how to run and
display them in Liferay IDE.

## Gradle Tasks and Executions [](id=gradle-tasks-and-executions)

Liferay IDE provides two views to enhance your developing experience using
Gradle: Gradle Tasks and Gradle Executions. You can open these views by
following the instructions below.

1. Go to *Window* &rarr; *Show View* &rarr; *Other...*.

2. Navigate to the *Gradle* folder and open *Gradle Tasks* and *Gradle
   Executions*.

Gradle tasks and executions views open automatically once you create or import a
Gradle project.

The Gradle Tasks view allows you to display the Gradle tasks available
for you to use in your Gradle project. Users can execute a task listed under the
Gradle project by double-clicking it.

![Figure 5: Navigate into your preferred Gradle project to view its available Gradle tasks.](../../../images/gradle-tasks.png)

Once you've executed a Gradle task, you can open the Gradle Executions view to
inspect its output.

![Figure 6: The Gradle Executions view helps you visualize the Gradle build process.](../../../images/gradle-executions.png)

Keep in mind that if you change the Gradle build scripts inside your Gradle
projects (e.g., `build.gradle` or `settings.gradle`), you must refresh the
project so Liferay IDE can account for the change and display it properly in
your views. To refresh a Gradle project, right-click on the project and select
*Gradle* &rarr; *Refresh Gradle Project*.

![Figure 7: Make sure to always refresh your Gradle project in Liferay IDE after build script edits.](../../../images/refresh-gradle-project.png)

Excellent! You're now equipped with the knowledge to add, import, and build your
Gradle projects in Liferay IDE!
