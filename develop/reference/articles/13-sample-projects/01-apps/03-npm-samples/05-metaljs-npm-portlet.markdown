# Metal.js npm Portlet [](id=metal-js-npm-portlet)

The Metal.js npm Portlet sample provides a portlet that uses the
[Metal.js](https://metaljs.com/) framework to render its output.

![Figure 1: Clicking the button returns displays a dialog window.](../../../../images/metaljs-npm-sample.png)

This portlet displays a Metal.js based dialog that has been rendered using SOY
templates.

+$$$

**Note:** If you're deploying a portlet that requires the JS to be minified, 
either through a dependency or your own project, the minifier fails on Liferay 
DXP 7.0 de-69 and below. To resolve this, use 
[Grunt](https://gruntjs.com/getting-started) 
[uglify](https://www.npmjs.com/package/grunt-contrib-uglify) 
to remove any code causing the issue, such as JSDoc comments. This process may 
take a long time, depending on the number of files that require an update. 

$$$

**Important:** This sample works for Liferay DXP Fix Pack 44+ and Liferay Portal
CE GA7+.

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
        "build": "metalsoy && babel --source-maps -d build/resources/main/META-INF/resources src/main/resources/META-INF/resources && liferay-npm-bundler"
    },

## Where Is This Sample? [](id=where-is-this-sample)

This sample is built with the following build tool:

<!--
There are three different versions of this sample, each built with a different
build tool:

TODO: Replace above when tool is available for other build tools. -Cody
-->

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.0/gradle/apps/npm/metaljs-npm-portlet)
