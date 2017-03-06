# Generating a JSF Application

Liferay offers you several options to generate a JSF application without forcing
you to manually create your own folder structure, descriptor files, etc. If
you'd like to examine the structure of a JSF application and create one from
scratch, see the
[Getting Started with JSF Applications](/develop/tutorials/-/knowledge_base/7-0/getting-started-with-jsf-applications)
tutorial.

Before generating your JSF application, you should first visit
[liferayfaces.org](http://liferayfaces.org/), which serves as a great reference
spot for JSF application development targeted for @product@. This site lets you
choose the options for your JSF application, and generates a Maven archetype
command you can execute to generate an application with your chosen options. You
can select the following archetype options:

- Liferay Portal Version
- JSF Version
- Component Suite

You also have the option to choose the build framework you'd like your JSF
application to use. When choosing between Gradle or Maven, the generated
`pom.xml` or `build.gradle` file is provided for you, including the dependencies
required based on your options chosen above. Gradle developers can also use the
`archetype:generate` command because it generates a `build.gradle` file for you
to use, if you like.

[!Figure 1: You can select the Liferay Portal version, JSF version, and component suite for your archetype generation command.](../../../images/jsf-app-generation.png)

Next, you'll generate an example JSF application (e.g., Liferay Portal 7 + JSF
2.2 + JSF Standard) using [liferayfaces.org](http://liferayfaces.org/), and
deploy it to your @product@ instance.

1.  Navigate to [liferayfaces.org](http://liferayfaces.org/) and select the
    following options from the dropdowns:

    - **Liferay Portal:** 7
    - **JSf:** 2.2
    - **Component Suite:** JSF Standard

2.  Copy the generated archetype generation command and execute it into your
    command line tool.


















