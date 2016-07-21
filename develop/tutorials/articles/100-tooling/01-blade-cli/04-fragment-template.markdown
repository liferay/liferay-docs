# Fragment Template [](id=using-the-fragment-template)

In this tutorial, you'll learn how to create a Liferay fragment as a Liferay
module. To create a Liferay fragment as a module, use a command like the
following:

    blade create -t fragment -h com.liferay.login.web -H 1.0.0 my-fragment-project

The command above creates an activator project named `my-fragment-project` in
the current directory. You specified the host bundle name as
`com.liferay.login.web` and and the host bundle's version to `1.0.0`. The folder
structure is created, but there are no files. The only files created are the
`bnd.bnd` and `build.gradle` files, which specify your host bundle and its
information.

After running the command above, your project's directory structure looks like
this:

- `my-fragment-project`
    - `src`
        - `main`
            - `java`
            -`resources`
                -`META-INF`
                    -`resources`
    - `bnd.bnd`
    - `build.gradle`

The generated module is functional and is deployable to a Liferay instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above.
