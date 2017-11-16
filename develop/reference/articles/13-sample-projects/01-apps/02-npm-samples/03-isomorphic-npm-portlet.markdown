# Isomorphic npm Portlet [](id=isomorphic-npm-portlet)

**Important:** This sample works for Liferay DXP Fix Pack 30+ and Liferay Portal
CE GA5+.

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Isomorphic npm Portlet sample provides a portlet that uses
[isomorphic](http://isomorphic.net/) code (which runs both client and server
side) to render its output.

![Figure 1: This sample portlet displays the results of running code designed for the server in the browser.](../../../../images/isomorphic-npm-sample.png)

This portlet showcases the ability to run code designed for the server in the
browser.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[npm development workflow support](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/using-npm-in-your-portlets).

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

This sample is built with the following build tool:

<!--
There are three different versions of this sample, each built with a different
build tool:

TODO: Replace above when tool is available for other build tools. -Cody
-->

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/npm/isomorphic-npm-portlet)
