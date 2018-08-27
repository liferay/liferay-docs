# Angular npm Deduplication Sample

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Angular npm Deduplication sample provides a portlet that uses the
[Angular](https://angular.io/) framework to render its output.

![Figure 1: Type custom text in the field and watch it instantaneously displayed in the portlet..](../../../../images/angular-npm-deduplication-sample.png)

This is done by providing a deduplicated instance of the Angular framework as an
OSGi bundle and then leveraging it from a sample portlet.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample is broken into two modules:

- `angular-consumer-portlet`
- `angular-provider`

The Angular Provider sample generates an OSGi bundle that provides a
deduplicated instance of the [Angular](https://angular.io/) framework that
portlets can share when rendering their output. The Angular Consumer portlet
uses the deduplicated instance of the Angular framework.

**Note:** Both modules must be deployed to the server for this sample to run.

This sample leverages the
[npm development workflow support](/develop/tutorials/-/knowledge_base/7-1/using-npm-in-your-portlets).

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

This sample uses the [npm registry](https://www.npmjs.com/) to download project
dependencies and uses the
[liferay-npm-bundler tool](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler)
to bundle the project dependencies inside the OSGi bundle JAR file.

To accomplish the bundling, the project's build process relies on a `build`
script inside its `package.json` file:

    "scripts": {
        "build": "tsc && liferay-npm-bundler"
    },

## Where Is This Sample? [](id=where-is-this-sample)

This sample is built with the following build tool:

 <!--
There are three different versions of this sample, each built with a different
build tool:
 TODO: Replace above when tool is available for other build tools. -Cody
-->

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/npm-deduplication-portlets/angular)
