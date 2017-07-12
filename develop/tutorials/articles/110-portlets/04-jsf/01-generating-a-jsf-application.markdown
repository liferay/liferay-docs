# Generating a JSF Application [](id=generating-a-jsf-application)

You can generate a Liferay Faces application without having to create your own
folder structure, descriptor files, and such manually. If you really want to do
that manually, you can examine the structure of a JSF application and create one
from scratch in the
[Creating a JSF Project Manually](/develop/tutorials/-/knowledge_base/7-0/creating-a-jsf-project-manually.markdown)
tutorial.

Before generating your JSF application, you should first visit
[liferayfaces.org](http://liferayfaces.org/), a great reference spot for JSF
application development targeted for @product@. This site lets you choose the
options for your JSF application and generates a Maven archetype command you
can execute to generate an application with your chosen options. You can select
the following archetype options:

- Liferay Portal Version
- JSF Version
- Component Suite

You can also choose a build framework (Gradle or Maven) and have a list of
dependencies generated for you and displayed on the page. The dependencies are
provided to you on the site page in a `pom.xml` or `build.gradle`, depending on
the build type you selected. This is useful because it gives you an idea of what
dependencies are required in your JSF application before generating it.

**Note:** Gradle developers can also use the `archetype:generate` command
because it generates both a `build.gradle` and a `pom.xml` file for you to use.

![Figure 1: You can select the Liferay Portal version, JSF version, and component suite for your archetype generation command.](../../../images/jsf-app-generation.png)

There are two ways you can generate your JSF application:

- Command line tool
- Liferay @ide@

You'll explore how to generate your app via the command line first.

## Using the Command Line

You'll generate an example JSF application (e.g., Liferay Portal 7 + JSF 2.2 +
JSF Standard) via command line using
[liferayfaces.org](http://liferayfaces.org/).

1.  Navigate to [liferayfaces.org](http://liferayfaces.org/) and select the
    following options:

    - **Liferay Portal:** 7
    - **JSF:** 2.2
    - **Component Suite:** JSF Standard

2.  Copy the archetype generation command and execute it. Make sure you've
    navigated to the folder where you want to generate your project.

That's it! Your JSF application is generated in the current folder!

You can also generate a Liferay JSF application using Maven's interactive
archetype UI. To do this, execute `mvn archetype:generate -Dfilter=liferay` and
select the JSF archetype you want to use. Then you'll step through each option
and select the version, group ID, artifact ID, etc. To learn more about this,
see the
[Generating New Projects Using Archetypes](/develop/tutorials/-/knowledge_base/7-0/generating-new-projects-using-archetypes)
tutorial.

+$$$

**Note:** Once you have your JSF application generated, you can
[import it into Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/using-maven-in-liferay-ide#importing-maven-projects)
and develop it further. You'll learn how to generate a JSF application using
Liferay @ide@ later.

$$$

You can build the project and deploy it to @product@ from the command line too!
If you're using Gradle, run the following command to build your JSF application:

    gradle build

For Maven, execute the following command:

    mvn package

Then copy the generated WAR to @product@'s `deploy` folder:

    [cp|copy] ./com.mycompany.my.jsf.portlet.war LIFERAY_HOME/deploy

Awesome! You've generated your JSF application and deployed it using the command
line. Next, you'll learn how to do this using Liferay @ide@.

## Using Liferay @ide@

You'll generate an example JSF project using Liferay @ide@. Open your @ide@
instance to get started.

1.  Navigate to *File* &rarr; *New* &rarr; *Project...*. This opens a new
    project wizard.

2.  Select the *Liferay* project and choose *Liferay JSF Project* from the
    listed subprojects. Then click *Next*.

    ![Figure 2: Choose the *Liferay JSF Project* option to begin creating a JSF project in @ide@.](../../../images/jsf-project-ide.png)

3.  Assign your JSF project a name, build framework (Gradle or Maven), and
    Component Suite. You have five component suites to choose from:

    - ICEFaces
    - JSF Standard
    - Liferay Faces Alloy
    - PrimeFaces
    - RichFaces

    ![Figure 3: Choose your preferred options for your JSF project.](../../../images/new-jsf-project-ide.png)

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

Fantastic! You're now able to quickly generate your Liferay JSF project via
command line tool or @ide@!