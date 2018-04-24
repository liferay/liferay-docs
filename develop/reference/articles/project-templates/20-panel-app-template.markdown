# Panel App Template [](id=panel-app-template)

In this article, you'll learn how to create a Liferay panel app and category as
a Liferay module. To create a Liferay panel app and category via the command
line using Blade CLI or Maven, use one of the commands with the following
parameters:

    blade create -t panel-app -v 7.1 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.panel.app \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `panel-app`. Suppose you want to create
a panel app project called `my-panel-app-project` with a package name prefix of
`com.liferay.docs` and a class name prefix of `Sample`. You could run the
following command to accomplish this:

    blade create -t panel-app -v 7.1 -p com.liferay.docs -c Sample my-panel-app-project

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.panel.app \
        -DgroupId=com.liferay \
        -DartifactId=my-panel-app-project \
        -Dpackage=com.liferay.docs \
        -Dversion=1.0 \
        -DclassName=Sample \
        -Dauthor=Joe Bloggs

After running the Blade command above, your project's directory structure would
look like this

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

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is functional and is deployable to a @product@ instance.
The generated module, by default, creates a panel category with a panel app in
@product@'s Product Menu. To build upon the generated app, modify the project by
adding logic and additional files to the folders outlined above. You can visit
the [Customizing the Product Menu](/develop/tutorials/-/knowledge_base/7-0/customizing-the-product-menu)
tutorial for instructions on customizing a panel app project.
