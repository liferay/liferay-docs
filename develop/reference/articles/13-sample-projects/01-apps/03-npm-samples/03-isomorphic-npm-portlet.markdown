# Isomorphic npm Portlet [](id=isomorphic-npm-portlet)

**Important:** This sample works for Liferay DXP Fix Pack 30+ and Liferay Portal
CE GA5+.

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Isomorphic npm Portlet sample provides a portlet that uses
[isomorphic](https://en.wikipedia.org/wiki/Isomorphic_JavaScript) code (i.e.,
can run from client and/or server side) on the client side. 

![Figure 1: This sample portlet displays the results of running code designed for the server in the browser.](../../../../images/isomorphic-npm-sample.png)

This portlet showcases running code designed to execute in the server
in the browser. Note that this portlet does **not** run JavaScript code in the
server; it's executing isomorphic JavaScript code in the browser.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[npm development workflow support](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/using-npm-in-your-portlets).

You can do many things with isomorphic code. You can run it in

- the server only (e.g., Node.js)
- the client only (e.g., browser)
- both the server and client (e.g., Node.js + browser)

Isomorphic code cannot run server-side because @product@ is Java based and
cannot execute JavaScript that way. This sample portlet shows how Liferay's npm
bundler can transform server-side code to make it work in the client (e.g.,
emulates some of Node.js' APIs in the client).

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

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.0/gradle/apps/npm/isomorphic-npm-portlet)
