# npm Portlet Template [](id=npm-portlet-template)

In this article, you'll learn how to create an npm portlet as a Liferay module.
To create an npmportlet via the command line using Blade CLI or Maven, use one
of the commands with the following parameters:

    blade create -t npm-portlet -v 7.0 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.npm.portlet \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className] \
        -DliferayVersion=7.0

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

+$$$

**Note:** The minifier fails on Liferay DXP 7.0 when JSDoc is present in a 
portlet. To resolve this, use 
[Grunt](https://gruntjs.com/getting-started) 
[uglify](https://www.npmjs.com/package/grunt-contrib-uglify) 
to remove the JSDoc comments. This process may take a long time, depending on 
the number of files that require an update. 

$$$

The template for this kind of project is `npm-portlet`. Suppose you want to
create an npm portlet project called `my-npm-portlet` with a package name of
`com.liferay.npm` and a class name of `MyNpmPortlet`. Also, you'd like to create
a service of type `javax.portlet.Portlet` that extends the
`com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet` class. Here,
*service* means an OSGi service, not a Liferay API. Another way to say *service
type* is to say *component type*. You could run the following command to
accomplish this:

    blade create -t npm-portlet -v 7.0 -p com.liferay.npm -c MyNpmPortlet my-npm-portlet

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.npm.portlet \
        -DgroupId=com.liferay \
        -DartifactId=my-npm-portlet \
        -Dpackage=com.liferay.npm \
        -Dversion=1.0 \
        -DclassName=MyNpmPortlet \
        -DpackageJsonVersion=1.0.0 \
        -DliferayVersion=7.0

After running the command above, your project's directory structure looks like
this:

- `my-npm-portlet`
    - `[gradle|.mvn]`
        - `wrapper`
            - `[gradle|maven]-wrapper.jar`
            - `[gradle|maven]-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/npm`
                    - `constants`
                        - `MyNpmPortletKeys.java`
                    - `portlet`
                        - `MyNpmPortlet.java`
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
