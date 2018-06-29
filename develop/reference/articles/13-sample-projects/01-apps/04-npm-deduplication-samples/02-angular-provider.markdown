# Angular Provider [](id=angular-provider)

**Important:** This sample works for Liferay DXP Fix Pack 44+ and Liferay Portal
CE GA7+.

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Angular Provider sample generates an OSGi bundle that provides a
deduplicated instance of
[Angular](https://angular.io/) framework for portlets to share when rendering
their output.

This bundle must be deployed to the server for the
[Angular Consumer Portlet](angular-consumer-portlet) sample to run.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[npm development workflow support](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/using-npm-in-your-portlets).

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

This sample uses the [npm registry](https://www.npmjs.com/) to download project
dependencies and uses the
[liferay-npm-bundler tool](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler)
to bundle the project dependencies inside the OSGi bundle JAR file.

To accomplish bundling, the project's build process relies on a `build` script
inside its `package.json` file:

    "scripts": {
        "build": "liferay-npm-bundler"
    },

## Where Is This Sample? [](id=where-is-this-sample)

This sample is built with the following build tool:

<!--
There are three different versions of this sample, each built with a different
build tool:

TODO: Replace above when tool is available for other build tools. -Cody
-->

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/npm/angular-npm-portlet)
