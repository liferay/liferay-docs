# React npm Portlet

## What does this sample do when it's deployed?

The React npm Portlet sample provides a portlet that uses the
[React](https://reactjs.org/) framework to render its output.

![You can play the game Tic-tac-toe with this sample portlet.](../../../../images/react-npm-sample.png)

This portlet showcases the how efficiently React can render components based on
user interaction.

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
