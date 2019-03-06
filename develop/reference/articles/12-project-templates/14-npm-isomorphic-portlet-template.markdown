# npm Isomorphic Portlet Template [](id=npm-isomorphic-portlet-template)

In this article, you'll learn how to create an npm Isomorphic portlet as a Liferay
module. To create an npm Isomorphic portlet via the command line using Blade CLI or
Maven, use one of the commands with the following parameters:

    blade create -t npm-isomorphic-portlet -v 7.0 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.npm.isomorphic.portlet \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className] \
        -DliferayVersion=7.0

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

+$$$

**Note:** If you're deploying a portlet that requires the JS to be minified, 
either through a dependency or your own project, the minifier fails on Liferay 
DXP 7.0 de-69 and below. To resolve this, use 
[Grunt](https://gruntjs.com/getting-started) 
[uglify](https://www.npmjs.com/package/grunt-contrib-uglify) 
to remove any code causing the issue, such as JSDoc comments. This process may 
take a long time, depending on the number of files that require an update. 

$$$

The template for this kind of project is `npm-isomorphic-portlet`. Suppose you want
to create an npm Isomorphic portlet project called `my-npm-isomorphic-portlet` with a
package name of `com.liferay.npm.isomorphic` and a class name of
`MyNpmIsomorphicPortlet`. Also, you'd like to create a service of type
`javax.portlet.Portlet` that extends the
`com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet` class. Here,
*service* means an OSGi service, not a Liferay API. Another way to say *service
type* is to say *component type*. You could run the following command to
accomplish this:

    blade create -t npm-isomorphic-portlet -v 7.0 -p com.liferay.npm.isomorphic -c MyNpmIsomorphicPortlet my-npm-isomorphic-portlet

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.npm.isomorphic.portlet \
        -DgroupId=com.liferay \
        -DartifactId=my-npm-isomorphic-portlet \
        -Dpackage=com.liferay.npm.isomorphic \
        -Dversion=1.0 \
        -DclassName=MyNpmIsomorphicPortlet \
        -DpackageJsonVersion=1.0.0 \
        -DliferayVersion=7.0

After running the command above, your project's directory structure looks like
this:

- `my-npm-isomorphic-portlet`
    - `[gradle|.mvn]`
        - `wrapper`
            - `[gradle|maven]-wrapper.jar`
            - `[gradle|maven]-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/npm/isomorphic`
                    - `constants`
                        - `MyNpmIsomorphicPortletKeys.java`
                    - `portlet`
                        - `MyNpmIsomorphicPortlet.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `js`
                            - `index.es.js`
                        - `init.jsp`
                        - `view.jsp`
    - `.babelrc`
    - `.npmbundlerrc`
    - `bnd.bnd`
    - `[build.gradle|pom.xml]`
    - `[gradlew|mvnw]`
    - `package.json`

The generated module is a working application and is deployable to a @product@
instance. To build upon the generated portlet, modify the project by adding
logic and additional files to the folders outlined above.
