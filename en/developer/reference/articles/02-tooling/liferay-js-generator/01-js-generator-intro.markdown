---
header-id: js-generator
---

# Liferay JS Generator

[TOC levels=1-4]

The Liferay JS Generator generates JavaScript widgets using pure JavaScript 
tooling. You don't have to have a deep understanding of Java to write a widget 
for @product@. See the [Liferay JS Generator developer documentation](/docs/7-2/frameworks/-/knowledge_base/f/creating-and-bundling-javascript-widgets-with-javascript-tooling) 
for more information on configuring generated JavaScript widgets. This section 
covers these reference topics for the Liferay JS Generator:

- How to install the Liferay JS Generator and use it to create a JS widget
- An explanation of JS Portlet Extender's method signature
- A reference list of the available configuration options for system settings 
  and instance settings

| **Note:** The Liferay Bundle Generator is deprecated as of v2.7.1 of the 
| [Liferay JS Toolkit](https://github.com/liferay/liferay-js-toolkit). It has 
| been renamed the Liferay JS Generator. If you're still running the Liferay 
| Bundle Generator, we recommend that you install the Liferay JS Generator 
| instead at your earliest convenience, as the Liferay Bundle Generator will be 
| removed in future versions.

The available commands for bundles created with the Liferay JS Generator are 
listed below:

| Command | Description |
| --- | --- |
| `npm run build` | Places the output of liferay-npm-bundler in the designated output folder. The standard output is a JAR file that can be deployed manually to @product@. |
| `npm run deploy` | Deploys the application to the configured server. |
| `npm run start` | Tests the application in a local webpack installation instead of a @product@ server. This speeds up development because you can see live changes without the need to deploy. Note, however, that because it's outside a Liferay instance, you don't have access to Liferay's APIs. |
| `npm run translate` | Runs the translation features for your bundle. Note that this feature requires Microsoft Translator credentials. See [Using Translation Features in Your Widget](/docs/7-2/frameworks/-/knowledge_base/f/using-translation-features-in-your-widget) for more information. |

| **Note:** By default, the webpack server uses port 8080, which conflicts with
| the port used by Tomcat. You can point the 
| webpack server to a different port by setting the `port` key in `.npmbuildrc`:
| 
| ```json
| "webpack": {
|   "port": 2070
| }
| ```

Read this section to learn how to install the Liferay JS Generator and 
understand its configuration. 
