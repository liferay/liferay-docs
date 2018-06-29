# WAR MVC Portlet Template [](id=war-mvc-portlet-template)

In this article, you'll learn how to create a Liferay MVC portlet project as a
WAR file. To create a Liferay MVC portlet project as a WAR via the command line
using Blade CLI or Maven, use one of the commands with the following parameters:

    blade create -t war-mvc-portlet -v 7.1 [-p packageName] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.war.mvc.portlet \
        -DartifactId=[projectName]
        -Dpackage=[packageName]

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `war-mvc-portlet`. Suppose you want to
create a WAR MVC portlet project called `my-war-mvc-portlet-project` with a
package name of `com.liferay.docs.war.mvc` and a class name of
`MyWarMvcPortlet`. You could run the following command to accomplish this:

    blade create -t war-mvc-portlet -v 7.1 -p com.liferay.docs.war.mvc my-war-mvc-portlet-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.war.mvc.portlet \
        -DgroupId=com.liferay \
        -DartifactId=my-war-mvc-portlet-project \
        -Dpackage=com.liferay.docs.war.mvc \
        -Dversion=1.0

After running the Blade command above, your project's folder structure looks
like this: 

- `my-war-mvc-portlet-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/war/mvc`
            - `resources`
                - `content`
                    - `Language.properties`
            - `webapp`
                - `css`
                    - `main.scss`
                - `WEB-INF`
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
                - `init.jsp`
                - `view.jsp`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated WAR MVC portlet is functional and is deployable to a @product@ instance.
To build upon the generated project, modify the project by adding logic and
additional files to the folders outlined above. Deploying WAR MVC portlets is
supported for @product-ver@, however, it is recommended to optimize your WAR
portlet to a module project, if possible. You can visit the
[From Liferay Portal 6 to 7](/develop/tutorials/-/knowledge_base/7-0/from-liferay-6-to-liferay-7)
section for info on how to do this.
