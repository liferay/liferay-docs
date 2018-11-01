# Angular 6 npm Portlet [](id=angular-6-npm-portlet)

The Angular 6 npm Portlet sample provides a portlet that uses the
[Angular](https://angular.io/) framework (version 6) to render its output.

![Figure 1: Type custom text in the field and watch it instantaneously displayed in the portlet.](../../../../images/angular-6-npm-sample.png)

This portlet showcases Angular's newest version and how to leverage it in
@product@. See
[this article](https://blog.angular.io/version-6-of-angular-now-available-cc56b0efa7a4)
for more information on what's new with Angular 6.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[npm development workflow support](/develop/tutorials/-/knowledge_base/7-0/using-npm-in-your-portlets).

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

This sample uses the [npm registry](https://www.npmjs.com/) to download project
dependencies and uses the
[liferay-npm-bundler tool](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler)
to bundle the project dependencies inside the OSGi bundle JAR file.

To accomplish the bundling, the project's build process relies on a `build`
script inside its `package.json` file:

    "scripts": {
        "build": "tsc && liferay-npm-bundler",
        "tsc": "tsc"
    },

## Where Is This Sample? [](id=where-is-this-sample)

This sample is built with the following build tool:

<!--
There are three different versions of this sample, each built with a different
build tool:

TODO: Replace above when tool is available for other build tools. -Cody
-->

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.0/gradle/apps/npm/angular-npm-portlet)
