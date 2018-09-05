# Simple npm Portlet [](id=simple-npm-portlet)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Simple npm Portlet sample provides a portlet that uses the
[isarray npm package](https://www.npmjs.com/package/isarray) when rendering its
output.

![Figure 1: The portlet's status and actions are displayed as output.](../../../../images/simple-npm-sample.png)

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

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
        "build": "babel --source-maps -d build/resources/main/META-INF/resources src/main/resources/META-INF/resources && liferay-npm-bundler"
    },

## Where Is This Sample? [](id=where-is-this-sample)

There are two different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/npm/simple-npm-portlet)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/apps/npm/simple-npm-portlet)
