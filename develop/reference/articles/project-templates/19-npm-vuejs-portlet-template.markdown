# npm Vue.js Portlet Template [](id=npm-vue-js-portlet-template)

In this article, you'll learn how to create an npm Vue.js portlet as a
Liferay module. To create an npm Vue.js portlet via the command line using
Blade CLI or Maven, use one of the commands with the following parameters:

    blade create -t npm-vuejs-portlet -v 7.1 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.npm.vuejs.portlet \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

You can also insert the `-b maven` parameter in the Blade command to generate a
Maven project using Blade CLI.

The template for this kind of project is `npm-vuejs-portlet`. Suppose you want
to create an npm Vue.js portlet project called `my-npm-vuejs-portlet` with a
package name of `com.liferay.npm.vuejs` and a class name of
`MyNpmVuejsPortlet`. Also, you'd like to create a service of type
`javax.portlet.Portlet` that extends the
`com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet` class. Here,
*service* means an OSGi service, not a Liferay API. Another way to say *service
type* is to say *component type*. You could run the following command to
accomplish this:

    blade create -t npm-vuejs-portlet -v 7.1 -p com.liferay.npm.vuejs -c MyNpmVuejsPortlet my-npm-vuejs-portlet

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.npm.vuejs.portlet \
        -DgroupId=com.liferay \
        -DartifactId=my-npm-vuejs-portlet \
        -Dpackage=com.liferay.npm.vuejs \
        -Dversion=1.0 \
        -DclassName=MyNpmVuejsPortlet \
        -DpackageJsonVersion=1.0.0

After running the Blade command above, your project's directory structure looks
like this:

- `my-npm-vuejs-portlet`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/npm/vuejs`
                    - `constants`
                        - `MyNpmVuejsPortletKeys.java`
                        - `MyNpmVuejsWebKeys.java`
                    - `portlet`
                        - `MyNpmVuejsPortlet.java`
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
