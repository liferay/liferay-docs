# npm Angular Portlet Template [](id=npm-angular-portlet-template)

In this article, you'll learn how to create an npm Angular portlet as a Liferay
module. To create an npm Angular portlet via the command line using Blade CLI or
Maven, use one of the commands with the following parameters:

    blade create -t npm-angular-portlet -v 7.0 [-p packageName] [-c className] projectName

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.npm.angular.portlet \
        -DartifactId=[projectName] \
        -Dpackage=[packageName] \
        -DclassName=[className]

You can also insert the `-b maven` parameter in the Blade command to generate
a Maven project using Blade CLI.

The template for this kind of project is `npm-angular-portlet`. Suppose you want
to create an npm Angular portlet project called `my-npm-angular-portlet` with a
package name of `com.liferay.npm.angular` and a class name of
`MyNpmAngularPortlet`. Also, you'd like to create a service of type
`javax.portlet.Portlet` that extends the
`com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet` class. Here,
*service* means an OSGi service, not a Liferay API. Another way to say *service
type* is to say *component type*. You could run the following command to
accomplish this:

    blade create -t npm-angular-portlet -v 7.0 -p com.liferay.npm.angular -c MyNpmAngularPortlet my-npm-angular-portlet

or

    mvn archetype:generate \
        -DarchetypeGroupId=com.liferay \
        -DarchetypeArtifactId=com.liferay.project.templates.npm.angular.portlet \
        -DgroupId=com.liferay \
        -DartifactId=my-npm-angular-portlet \
        -Dpackage=com.liferay.npm.angular \
        -Dversion=1.0 \
        -DclassName=MyNpmAngularPortlet \
        -DpackageJsonVersion=1.0.0

After running the command above, your project's directory structure looks like
this:

- `my-npm-angular-portlet`
    - `[gradle|.mvn]`
        - `wrapper`
            - `[gradle|maven]-wrapper.jar`
            - `[gradle|maven]-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/npm/angular`
                    - `constants`
                        - `MyNpmAngularPortletKeys.java`
                    - `portlet`
                        - `MyNpmAngularPortlet.java`
            - `resources`
                - `content`
                    - `Language.properties`
                - `META-INF`
                    - `resources`
                        - `js`
                            - `app`
                                - `app.component.ts`
                                - `app.module.ts`
                            - `angular-loader.ts`
                            - `main.ts`
                        - `init.jsp`
                        - `view.jsp`
    - `.babelrc`
    - `.npmbundlerrc`
    - `bnd.bnd`
    - `[build.gradle|pom.xml]`
    - `[gradlew|mvnw]`
    - `package.json`
    - `tsconfig.json`

The generated module is a working application and is deployable to a @product@
instance. To build upon the generated portlet, modify the project by adding
logic and additional files to the folders outlined above.
