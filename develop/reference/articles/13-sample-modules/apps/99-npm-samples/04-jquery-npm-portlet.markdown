# jQuery npm Portlet

## What does this sample do when it's deployed?

The jQuery npm Portlet sample provides a portlet that uses [jQuery](https://jquery.com/) framework to render its output.

![Screenshot of jQuery npm Portlet](screenshot.png)

## What API(s) and/or code components does this sample highlight?

This sample leverages the [npm development workflow support](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/introduction).

## How does this sample leverage the API(s) and/or code component?

This sample uses [npm registry](https://www.npmjs.com/) to download project dependencies and bundles them inside the OSGi bundle JAR file using the [liferay-npm-bundler tool](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler).

To accomplish the bundling, the project's build process relies on a `build` script inside its `package.json` file:

```
	...
	"scripts": {
		"build":
			"babel --source-maps -d build/resources/main/META-INF/resources src/main/resources/META-INF/resources && liferay-npm-bundler"
	},
	...
```
