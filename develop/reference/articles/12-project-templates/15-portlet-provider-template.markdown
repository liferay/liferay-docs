# Portlet Provider Template [](id=portlet-provider-template)

In this article, you'll learn how to create a Liferay portlet provider as a
Liferay module. To create a Liferay portlet provider via the command line using
Blade CLI or Maven, use one of the commands with the following parameters:

    blade create -t portlet-provider [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.portlet.provider \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

The template for this kind of project is `portlet-provider`. Suppose you want to
create a portlet provider project called `my-portlet-provider-project` with a
package name of `com.liferay.docs.portlet` and a class name prefix of `Sample`.
You could run the following command to accomplish this:

    blade create -t portlet-provider -p com.liferay.docs -c Sample my-portlet-provider-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.portlet.provider \
        -DgroupId=com.liferay \
        -DartifactId=my-portlet-provider-project \
        -Dpackage=com.liferay.docs \
        -Dversion=1.0 \
        -DclassName=Sample \
        -Dauthor=Joe Bloggs

After running the command above, your project's directory structure would look
like this

- `my-portlet-provider-project`
    - `gradle` (only in Blade CLI generated projects)
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/portlet`
                    - `SampleAddPortletProvider.java`
                    - `SamplePortlet.java`
            - `resources`
                - `META-INF`
                    - `resources`
                        - `init.jsp`
                        - `view.jsp`
    - `bnd.bnd`
    - `build.gradle`
    - `[gradlew|pom.xml]`

The generated module is functional and is deployable to a @product@ instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above. You can visit the
[Providing Portlets to Manage Requests](/develop/tutorials/-/knowledge_base/7-0/providing-portlets-to-manage-requests)
tutorial for instructions on customizing a portlet provider project.
