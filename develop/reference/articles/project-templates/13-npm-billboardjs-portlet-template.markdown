# npm Billboard.js Portlet Template [](id=npm-billboard-js-portlet-template)

In this article, you'll learn how to create an npm Billboard.js portlet as a
Liferay module. To create an npm Billboard.js portlet via the command line using
Blade CLI or Maven, use one of the commands with the following parameters:

    blade create -t npm-billboardjs-portlet -v 7.1 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.npm.billboardjs.portlet \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `npm-billboardjs-portlet`. Suppose you
want to create an npm Billboard.js portlet project called
`my-npm-billboardjs-portlet` with a package name of
`com.liferay.npm.billboardjs` and a class name of `MyNpmBillboardjsPortlet`.
Also, you'd like to create a service of type `javax.portlet.Portlet` that
extends the `com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet` class.
Here, *service* means an OSGi service, not a Liferay API. Another way to say
*service type* is to say *component type*. You could run the following command
to accomplish this:

    blade create -t npm-billboardjs-portlet -v 7.1 -p com.liferay.npm.billboardjs -c MyNpmBillboardjsPortlet my-npm-billboardjs-portlet

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.npm.billboardjs.portlet \
        -DgroupId=com.liferay \
        -DartifactId=my-npm-billboardjs-portlet \
        -Dpackage=com.liferay.npm.billboardjs \
        -Dversion=1.0 \
        -DclassName=MyNpmBillboardjsPortlet \
        -DpackageJsonVersion=1.0.0

After running the Blade command above, your project's directory structure looks
like this:

- `my-npm-billboardjs-portlet`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/npm/billboardjs`
                    - `constants`
                        - `MyNpmBillboardjsPortletKeys.java`
                        - `MyNpmBillboardjsWebKeys.java`
                    - `portlet`
                        - `MyNpmBillboardjsPortlet.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `js`
                            - `data.json`
                            - `index.es.js`
                        - `init.jsp`
                        - `view.jsp`
    - `.babelrc`
    - `.npmbundlerrc`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`
    - `package.json`

The Maven-generated project includes a `pom.xml` file and does not include the
Gradle-specific files, but otherwise, appears exactly the same.

The generated module is a working application and is deployable to a @product@
instance. To build upon the generated portlet, modify the project by adding
logic and additional files to the folders outlined above.
