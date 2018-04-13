# MVC Portlet Template [](id=using-the-mvc-portlet-template)

In this article, you'll learn how to create a Liferay MVC portlet application as
a Liferay module. To create a Liferay MVC portlet application via the command
line using Blade CLI or Maven, use one of the commands with the following
parameters:

    blade create -t mvc-portlet -v 7.1 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.mvc.portlet \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `mvc-portlet`. Suppose you want to
create an MVC portlet project called `my-mvc-portlet-project` with a package
name of `com.liferay.docs.mvcportlet` and a class name of `MyMvcPortlet`. Also,
you'd like to create a service of type `javax.portlet.Portlet` that extends the
`com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet` class. Here,
*service* means an OSGi service, not a Liferay API. Another way to say *service
type* is to say *component type*. You could run the following command to
accomplish this:

    blade create -t mvc-portlet -v 7.1 -p com.liferay.docs.mvcportlet -c MyMvcPortlet my-mvc-portlet-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.mvc.portlet \
        -DgroupId=com.liferay \
        -DartifactId=my-mvc-portlet-project \
        -Dpackage=com.liferay.docs.mvcportlet \
        -Dversion=1.0 \
        -DclassName=MyMvcPortlet \
        -Dauthor=Joe Bloggs

After running the Blade command above, your project's directory structure looks
like this:

- `my-mvc-portlet-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/mvcportlet`
                    - `constants`
                        - `MyMvcPortletKeys.java`
                    -  `portlet`
                        - `MyMvcPortlet.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `init.jsp`
                        - `view.jsp`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is a working application and is deployable to a @product@
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
