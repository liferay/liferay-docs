# Fragment Template [](id=using-the-fragment-template)

In this tutorial, you'll learn how to create a Liferay fragment as a Liferay
module. You can learn more about fragment modules in the
[Declaring a Fragment Host](/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-jsps#declaring-a-fragment-host)
article and in section 3.14 of the
[OSGi Alliance's core specification document](https://www.osgi.org/developer/downloads/release-6/).

To create a Liferay fragment as a module, use a command with the following
parameters:

    blade create -t fragment [-h hostBundleName] [-H hostBundleVersion] projectName

The template for this kind of project is `fragment`. Suppose you want to create
a fragment project called `my-fragment-project` with a host bundle name of
`com.liferay.login.web` and host bundle version of `1.0.0`. You could run the
following command to accomplish this:

    blade create -t fragment -h com.liferay.login.web -H 1.0.0 my-fragment-project

The folder structure is created, but there are no files. The only files created
are the `bnd.bnd` and `build.gradle` files, which specify your host bundle and
its information. After running the command above, your project's directory
structure looks like this:

- `my-fragment-project`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
            -`resources`
                -`META-INF`
                    -`resources`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The generated module is functional and is deployable to a Liferay instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above.
