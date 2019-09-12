---
header-id: generating-a-jsf-application
---

# Generating a JSF Project from the Command Line

[TOC levels=1-4]

You can generate a Liferay Faces application without having to create your own
folder structure, descriptor files, and such manually. If you really want to do
that manually, you can examine the structure of a JSF application and create one
from scratch in the
[Creating a JSF Project Manually](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-jsf-project-manually)
tutorial.

<div class="video-thumbnail"></div>

!P[Video Thumbnail](https://portal.liferay.dev/documents/113763090/113919826/jsf-vid-thumbnail.png)

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
provided to you on the site page in a `pom.xml` or `build.gradle` file,
depending on the build type you selected. This is useful because it gives you an
idea of what dependencies are required in your JSF application before generating
it.

**Note:** Gradle developers can also use the `archetype:generate` command
because it generates both a `build.gradle` and a `pom.xml` file for you to use.

![Figure 1: You can select the Liferay Portal version, JSF version, and component suite for your archetype generation command.](../../../images/jsf-app-generation.png)

Next you'll generate an example JSF application (e.g., Liferay Portal 7 + JSF
2.2 + JSF Standard) via command line using
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
[Generating New Projects Using Archetypes](/docs/7-1/tutorials/-/knowledge_base/t/generating-new-projects-using-archetypes)
tutorial.

Once you have your JSF application generated, you can
[import it into Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/using-maven-in-liferay-ide#importing-maven-projects)
and develop it further. To
[deploy it to your @product@ instance](/docs/7-1/tutorials/-/knowledge_base/t/deploying-projects-with-liferay-ide),
drag and drop it onto the @product@ server.

You can build the project and deploy it to @product@ from the command line too!
If you're using Gradle, run the following command to build your JSF application:

    ../gradlew build

For Maven, execute the following command:

    mvn package

Then copy the generated WAR to @product@'s `deploy` folder:

    [cp|copy] ./com.mycompany.my.jsf.portlet.war LIFERAY_HOME/deploy

Awesome! You've generated your JSF application and deployed it using the command
line.

<div class="video-wrapper" data-name="Developing a New JSF Portlet">
</div>

!V[Video Tutorial](https://portal.liferay.dev/documents/113763090/113919826/developing-a-new-jsf-portlet.mp4|https://portal.liferay.dev/documents/113763090/113919826/developing-a-new-jsf-portlet.mkv)
