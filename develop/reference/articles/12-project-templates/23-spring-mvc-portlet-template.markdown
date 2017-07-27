# Spring MVC Portlet Template [](id=spring-mvc-portlet-template)

In this article, you'll learn how to create a Liferay Spring MVC portlet
application as a WAR. To create a Liferay Spring MVC portlet via the command
line using Blade CLI or Maven, use one of the commands with the following
parameters:

    blade create -t spring-mvc-portlet [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.spring.mvc.portlet \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

The template for this kind of project is `spring-mvc-portlet`. Suppose you want
to create a Spring MVC portlet project called `my-spring-mvc-portlet-project`
with a package name of `com.liferay.docs.springmvcportlet` and a class name of
`MySpringMvcPortlet`. Also, you'd like to create a Spring-annotated portlet
class named `MySpringMvcPortletViewController`.

    blade create -t spring-mvc-portlet -p com.liferay.docs.springmvcportlet -c MySpringMvcPortlet my-spring-mvc-portlet-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.spring.mvc.portlet \
        -DgroupId=com.liferay \
        -DartifactId=my-spring-mvc-portlet-project \
        -Dpackage=com.liferay.docs.springmvcportlet \
        -Dversion=1.0 \
        -DclassName=MySpringMvcPortlet \
        -Dauthor=Joe Bloggs

After running the command above, your project's directory structure looks like
this:

- `my-spring-mvc-portlet-project`
    - `gradle` (only in Blade CLI generated projects)
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/springmvcportlet/portlet`
                    - `MySpringMvcPortletViewController`
            - `resources`
                - `content`
                    - `Language.properties`
            - `webapp`
                - `css`
                    - `main.scss`
                - `WEB-INF`
                    - `jsp`
                        - `init.jsp`
                        - `view.jsp`
                    - `spring-context`
                        - `portlet`
                            - `my-spring-mvc-portlet-project.xml`
                        - `portlet-application-context.xml`
                    - `tld`
                        - `liferay-portlet.tld`
                        - `liferay-portlet-ext.tld`
                        - `liferay-security.tld`
                        - `liferay-theme.tld`
                        - `liferay-ui.tld`
                        - `liferay-util.tld`
                    - `liferay-display.xml`
                    - `liferay-plugin-package.properties`
                    - `liferay-portlet.xml`
                    - `portlet.xml`
                    - `web.xml`
                - `icon.png`
    - `build.gradle`
    - `[gradlew|pom.xml]`

The generated WAR is a working application and is deployable to a @product@
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
