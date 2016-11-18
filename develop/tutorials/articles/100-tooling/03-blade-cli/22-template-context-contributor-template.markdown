# Template Context Contributor Template [](id=template-context-contributor-template)

In this tutorial, you'll learn how to create a Liferay template context
contributor as a Liferay module. To create a template context contributor as a
module, use a command with the following parameters: 

    blade create -t template-context-contributor [-p packageName] [-c className] projectName

The template for this kind of project is `template-context-contributor`. Suppose
you want to create a template context contributor project called
`my-template-context-contributor` with a package name of
`com.liferay.docs.theme.contributor` and a class name of
`SampleTemplateContextContributor`. You could run the following command to
accomplish this:

    blade create -t template-context-contributor -p com.liferay.docs -c Sample my-template-context-contributor

After running the command above, your project's directory structure would look
like this

- `my-template-context-contributor`
    - `gradle`
        - `wrapper`
            - `gradle-wrapper.jar`
            - `gradle-wrapper.properties`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/theme/contributor`
                    - `SampleTemplateContextContributor.java`
    - `bnd.bnd`
    - `build.gradle`
    - `gradlew`

The generated module is functional and is deployable to a Liferay instance. To
build upon the generated app, modify the project by adding logic and additional
files to the folders outlined above. You can visit the
[blade.template.context.contributor](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-gradle/blade.template.context.contributor)
sample project for a more expanded sample of a template context contributor.
Likewise, see the
[Context Contributors](/develop/tutorials/-/knowledge_base/7-0/context-contributors)
tutorial for instructions on customizing a template context contributor project.
