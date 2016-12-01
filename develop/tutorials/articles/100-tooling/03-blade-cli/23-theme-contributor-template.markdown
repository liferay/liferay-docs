# Theme Contributor Template [](id=theme-contributor-template)

In this tutorial, you'll learn how to create a Liferay theme contributor as
a Liferay module. To create a theme contributor as a module, use a command
with the following parameters:

    blade create -t theme-contributor [--contributorType contributorType] [-p packageName] projectName

The template for this kind of project is `theme-contributor`. Suppose you want
to create a theme contributor project called `my-theme-contributor` with a
package name of `com.liferay.docs.theme.contributor` and a contributor type of
`my-contributor`. You could run the following command to accomplish this:

    blade create -t theme-contributor --contributorType my-contributor -p com.liferay.docs.theme.contributor my-theme-contributor

After running the command above, your project's folder structure would look
like this: 

- `my-theme-contributor`
    - `gradle`
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
    - `gradlew`

The generated module is functional and is deployable to a Liferay instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above. You can visit the
[blade.theme.contributor](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-gradle/blade.theme.contributor)
sample project for a more expanded sample of a theme contributor. Likewise, see
the
[Theme Contributors](/develop/tutorials/-/knowledge_base/7-0/theme-contributors)
tutorial for instructions on customizing a theme contributor project.
