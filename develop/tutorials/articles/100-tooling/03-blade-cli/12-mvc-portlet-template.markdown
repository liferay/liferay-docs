# MVC Portlet Template [](id=using-the-mvc-portlet-template)

In this tutorial, you'll learn how to create a Liferay MVC portlet application
as a Liferay module. To create a Liferay MVC portlet application as a module,
use a command with the following parameters:

    blade create -t mvc-portlet [-p packageName] [-c className] projectName

The template for this kind of project is `mvc-portlet`. Suppose you want to
create an MVC portlet project called `my-mvc-portlet-project` with a package
name of `com.liferay.docs.mvcportlet` and a class name of `MyMvcPortlet`. Also,
you'd like to create a service of type `javax.portlet.Portlet` that extends the
`com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet` class. Here,
*service* means an OSGi service, not a Liferay API. Another way to say *service
type* is to say *component type*. You could run the following command to
accomplish this:

    blade create -t mvc-portlet -p com.liferay.docs.mvcportlet -c MyMvcPortlet my-mvc-portlet-project

After running the command above, your project's directory structure looks like
this:

- `my-mvc-portlet-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/mvcportlet`
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

The generated module is a working application and is deployable to a Liferay
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
