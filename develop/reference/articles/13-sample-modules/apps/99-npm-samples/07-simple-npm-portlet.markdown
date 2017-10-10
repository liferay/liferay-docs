# Simple npm Portlet

## What does this sample do when it's deployed?

The Simple npm Portlet sample provides a portlet that uses the [isarray npm package](https://www.npmjs.com/package/isarray) when rendering its output.

![Screenshot of Simple npm Portlet](screenshot.png)

## What API(s) and/or code components does this sample highlight?

This sample leverages the [npm development workflow support](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/introduction).

## How does this sample leverage the API(s) and/or code component?

This sample uses [npm registry](https://www.npmjs.com/) to download project dependencies and bundles them inside the OSGi bundle JAR file using the [liferay-npm-bundler tool](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler).

To accomplish the bundling, the project's build process relies on a `build` script inside its `package.json` file:

```
	...
	"scripts": {
		"build": "babel --source-maps -d build/resources/main/META-INF/resources src/main/resources/META-INF/resources && liferay-npm-bundler"
	},
	...
```
