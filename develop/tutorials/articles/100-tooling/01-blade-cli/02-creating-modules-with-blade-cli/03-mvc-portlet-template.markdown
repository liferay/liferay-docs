# MVC Portlet Template [](id=using-the-mvc-portlet-template)

In this tutorial, you'll learn how to create a Liferay MVC portlet application
as a Liferay module. To create a Liferay MVC portlet application as a module,
use a command like the following:

    blade create -t mvcportlet -p com.liferay.docs.mvcportlet -c MyMvcPortlet my-mvc-portlet-project

The command above creates an MVC portlet project named `my-mvc-portlet-project`
in the current directory. You set the package name to
`com.liferay.docs.mvcportlet` and your portlet component class name to
`MyMvcPortlet`. Note that in that class, you're creating a service of type
`javax.portlet.Portlet` and extending the
`com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet` class. Here,
*service* means an OSGi service, not a Liferay API. Another way to say *service
type* is to say *component type*.

After running the command above, your project's directory structure looks like
this:

- `my-mvc-portlet-project`
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

The generated module is a working application and is deployable to a Liferay
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
