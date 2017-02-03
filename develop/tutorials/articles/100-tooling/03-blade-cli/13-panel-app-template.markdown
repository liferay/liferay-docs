# Panel App Template [](id=panel-app-template)

In this tutorial, you'll learn how to create a Liferay panel app and category as
a Liferay module. To create a Liferay panel app and category as a module, use a
command with the following parameters: 

    blade create -t panel-app [-p packageName] [-c className] projectName

The template for this kind of project is `panel-app`. Suppose you want to create
a panel app project called `my-panel-app-project` with a package name prefix of
`com.liferay.docs` and a class name prefix of `Sample`. You could run the
following command to accomplish this:

    blade create -t panel-app -p com.liferay.docs -c Sample my-panel-app-project

After running the command above, your project's directory structure would look
like this

- `my-panel-app-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/`
                    - `application/list`
                        - `SamplePanelApp.java`
                        - `SamplePanelCategory.java`
                    - `constants`
                        - `SamplePanelCategoryKeys.java`
                        - `SamplePortletKeys.java`
                    - `portlet`
                        - `SamplePortlet.java`
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

The generated module is functional and is deployable to a Liferay instance. The
generated module, by default, creates a panel category with a panel app in
@product@'s Product Menu. To build upon the generated app, modify the project by
adding logic and additional files to the folders outlined above. You can visit
the
[Customizing the Product Menu](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu)
tutorial for instructions on customizing a panel app project.
