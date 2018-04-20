# npm Isomorphic Portlet Template [](id=npm-isomorphic-portlet-template)

In this article, you'll learn how to create an npm Isomorphic portlet as a Liferay
module. To create an npm Isomorphic portlet via the command line using Blade CLI or
Maven, use one of the commands with the following parameters:

    blade create -t npm-isomorphic-portlet -v 7.1 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.npm.isomorphic.portlet \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `npm-isomorphic-portlet`. Suppose you want
to create an npm Isomorphic portlet project called `my-npm-isomorphic-portlet` with a
package name of `com.liferay.npm.isomorphic` and a class name of
`MyNpmIsomorphicPortlet`. Also, you'd like to create a service of type
`javax.portlet.Portlet` that extends the
`com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet` class. Here,
*service* means an OSGi service, not a Liferay API. Another way to say *service
type* is to say *component type*. You could run the following command to
accomplish this:

    blade create -t npm-isomorphic-portlet -v 7.1 -p com.liferay.npm.isomorphic -c MyNpmIsomorphicPortlet my-npm-isomorphic-portlet

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.npm.isomorphic.portlet \
        -DgroupId=com.liferay \
        -DartifactId=my-npm-isomorphic-portlet \
        -Dpackage=com.liferay.npm.isomorphic \
        -Dversion=1.0 \
        -DclassName=MyNpmIsomorphicPortlet \
        -DpackageJsonVersion=1.0.0

After running the Blade command above, your project's directory structure looks
like this:

- `my-npm-isomorphic-portlet`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/npm/isomorphic`
                    - `constants`
                        - `MyNpmIsomorphicPortletKeys.java`
                        - `MyNpmIsomorphicWebKeys.java`
                    - `portlet`
                        - `MyNpmIsomorphicPortlet.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `js`
                            - `index.js`
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
