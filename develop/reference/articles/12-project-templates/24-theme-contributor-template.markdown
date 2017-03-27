# Theme Contributor Template [](id=theme-contributor-template)

In this article, you'll learn how to create a Liferay theme contributor as a
Liferay module. To create a theme contributor via the command line using Blade
CLI or Maven, use one of the commands with the following parameters:

    blade create -t theme-contributor [--contributorType contributorType] [-p packageName] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.theme.contributor \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DcontributorType=[contributorType]

The template for this kind of project is `theme-contributor`. Suppose you want
to create a theme contributor project called `my-theme-contributor` with a
package name of `com.liferay.docs.theme.contributor` and a contributor type of
`my-contributor`. You could run the following command to accomplish this:

    blade create -t theme-contributor --contributorType my-contributor -p com.liferay.docs.theme.contributor my-theme-contributor

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.theme.contributor \
        -DgroupId=com.liferay \
        -DartifactId=my-theme-contributor \
        -Dpackage=com.liferay.docs.theme.contributor \
        -Dversion=1.0 \
        -DcontributorType=my-contributor

After running the command above, your project's folder structure would look
like this: 

- `my-theme-contributor`
    - `gradle` (only in Blade CLI generated projects)
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/theme/contributor`
            - `resources`
                - `META-INF`
                    - `resources`
                    	- `css`
                    		- `my-contributor`
                    			- `_body.scss`
                    			- `_control_menu.scss`
                    			- `_product_menu.scss`
                    			- `_simulation_panel.scss`
                    		- `my-contributor.scss`
                    	- `js`
                    		- `my-contributor.js`
    - `bnd.bnd`
    - `build.gradle`
    - `[gradlew|pom.xml]`

The generated module is functional and is deployable to a @product@ instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above. You can visit the
[Blade Theme Contributor](/develop/reference/-/knowledge_base/7-0/theme-contributor)
sample project for a more expanded sample of a theme contributor. Likewise, see
the
[Theme Contributors](/develop/tutorials/-/knowledge_base/7-0/theme-contributors)
tutorial for instructions on customizing a theme contributor project.
