---
header-id: default-liferay-npm-bundler-loaders
---

# Default liferay-npm-bundler Loaders

[TOC levels=1-4]

Several [loaders](/docs/7-2/frameworks/-/knowledge_base/f/understanding-liferay-npm-bundlers-loaders) 
are available for the liferay-npm-bundler by default:

[`babel-loader`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-babel-loader): 
processes source files with [Babel](https://babeljs.io/). This avoids an extra 
build step before the bundler.

[`copy-loader`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-copy-loader): 
copies source files (static assets) to the output folder. 

[`css-loader`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-css-loader): 
converts a CSS file into a JavaScript module that's inserted into the DOM once 
it's loaded.

[`json-loader`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-json-loader): 
generates JavaScript modules that export the contents of a JSON file as an 
object, so you can include JSON files with the `require()` call.

[`sass-loader`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-sass-loader): 
runs `node-sass` or `sass` on source files. This lets you generate static CSS 
files. It can be chained before `style-loader`. 

[`style-loader`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-style-loader): 
converts a CSS file into a JavaScript module that inserts the CSS contents into
the DOM once it's loaded. This lets you include CSS files with a `require()`
call.

See the [liferay-js-toolkit loaders showcase](https://github.com/izaera/liferay-js-toolkit-showcase/tree/loaders) 
for an example use case of the liferay-npm-bundler's loaders. If the default 
loaders don't meet your requirements, you can follow the instructions in 
[Creating Custom Loaders for the Bundler](/docs/7-2/frameworks/-/knowledge_base/f/creating-custom-loaders-for-the-liferay-npm-bundler) 
to create your own loaders. 
