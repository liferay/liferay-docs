# Soy Portlet Template [](id=soy-portlet-template)

In this article, you'll learn how to create a Soy portlet application as
a Liferay module. To create a Soy portlet as a module via the command
line using Blade CLI or Maven, use one of the commands with the following
parameters:

    blade create -t soy-portlet [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.soy.portlet \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

The template for this kind of project is `soy-portlet`. Suppose you want to
create an Soy portlet project called `my-soy-portlet-project` with a package
name of `com.liferay.docs.soyportlet` and a class name of `MySoyPortlet`. Also,
you'd like to create a service of type `javax.portlet.Portlet` that extends the
`com.liferay.portal.portlet.bridge.soy.SoyPortlet` class. Here,
*service* means an OSGi service, not a Liferay API. Another way to say *service
type* is to say *component type*. You could run the following command to
accomplish this:

    blade create -t soy-portlet -p com.liferay.docs.soyportlet -c MySoyPortlet my-soy-portlet-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.soy.portlet \
        -DgroupId=com.liferay \
        -DartifactId=my-soy-portlet-project \
        -Dpackage=com.liferay.docs.soyportlet \
        -Dversion=1.0 \
        -DclassName=MySoyPortlet \
        -Dauthor=Joe Bloggs

After running the command above, your project's directory structure looks like
this:

- `my-soy-portlet-project`
    - `gradle` (only in Blade CLI generated projects)
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/soyportlet`
                    - `constants`
                        - `MySoyPortletKeys.java`
                    - `portlet`
                        - `action`
                            - `MySoyPortletNavigationMVCRenderCommand.java`
                        - `MySoyPortlet.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `Footer.es`
                        - `Footer.soy`
                        - `Header.es`
                        - `Header.soy`
                        - `Navigation.es`
                        - `Navigation.soy`
                        - `View.es`
                        - `View.soy`
    - `bnd.bnd`
    - `build.gradle`
    - `package.json`
    - `[gradlew|pom.xml]`

The generated module is a working application and is deployable to a @product@
instance. To build upon the generated app, modify the project by adding logic
and additional files to the folders outlined above.
