# Isomorphic npm Portlet

## What does this sample do when it's deployed?

The Isomorphic npm Portlet sample provides a portlet that uses
[isomorphic](http://isomorphic.net/) code (which runs both client and server
side) to render its output.

![This sample portlet displays code retrieved from the client and server side.](../../../../images/isomorphic-npm-sample.png)

This portlet showcases the ability to run both client and server side code.

## What API(s) and/or code components does this sample highlight?

This sample leverages the
[npm development workflow support](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/introduction).

## How does this sample leverage the API(s) and/or code component?

This sample uses the [npm registry](https://www.npmjs.com/) to download project
dependencies and uses the
[liferay-npm-bundler tool](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler)
to bundle the project dependencies inside the OSGi bundle JAR file.

To accomplish the bundling, the project's build process relies on a `build`
script inside its `package.json` file:

    "scripts": {
        "build": "babel --source-maps -d build/resources/main/META-INF/resources src/main/resources/META-INF/resources && liferay-npm-bundler"
    },
