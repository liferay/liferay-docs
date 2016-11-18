# Portlet Provider Template [](id=portlet-provider-template)

In this tutorial, you'll learn how to create a Liferay portlet provider as a
Liferay module. To create a Liferay portlet provider as a module, use a
command with the following parameters: 

    blade create -t portlet-provider [-p packageName] [-c className] projectName

The template for this kind of project is `portlet-provider`. Suppose you want to
create a portlet provider project called `my-portlet-provider-project` with a
package name of `com.liferay.docs.portlet` and a class name prefix of `Sample`.
You could run the following command to accomplish this:

    blade create -t portlet-provider -p com.liferay.docs -c Sample my-portlet-provider-project

After running the command above, your project's directory structure would look
like this

- `my-portlet-provider-project`
    - `gradle`
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
    - `gradlew`

The generated module is functional and is deployable to a Liferay instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above. You can visit the
[Providing Portlets to Manage Requests](/develop/tutorials/-/knowledge_base/7-0/providing-portlets-to-manage-requests)
tutorial for instructions on customizing a portlet provider project.
