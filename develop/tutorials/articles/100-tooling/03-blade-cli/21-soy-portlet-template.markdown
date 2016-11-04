# Soy Portlet Template [](id=using-the-soy-portlet-template)

In this tutorial, you'll learn how to create a Soy portlet application
as a Liferay module. To create a Soy portlet application as a module,
use a command with the following parameters:

    blade create -t soyportlet -p [package name] -c [class name] [project name]

The template for this kind of project is `soyportlet`. Suppose you want to
create a Soy portlet project called `my-soy-portlet-project` with a package
name of `com.liferay.docs.soyportlet` and a class name of `MySoyPortlet`. Also,
you'd like to create a service of type `javax.portlet.Portlet` that extends the
`com.liferay.portal.portlet.bridge.soy.SoyPortlet` class. Here,
*service* means an OSGi service, not a Liferay API. Another way to say *service
type* is to say *component type*. You could run the following command to
accomplish this:

    blade create -t soyportlet -p com.liferay.docs.soyportlet -c MySoyPortlet my-soy-portlet-project

After running the command above, your project's directory structure looks like
this:

- `my-soy-portlet-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/soyportlet`
                    - `action`
                        - `MySoyPortletNavigationMVCRenderCommand.java`
                    - `MySoyPortlet.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `Footer.soy`
                        - `Footer.es.js`
                        - `Header.soy`
                        - `Header.es.js`
                        - `Navigation.soy`
                        - `Navigation.es.js`
                        - `View.soy`
                        - `View.es.js`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`


The generated module is a working application and is deployable to a Liferay
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
