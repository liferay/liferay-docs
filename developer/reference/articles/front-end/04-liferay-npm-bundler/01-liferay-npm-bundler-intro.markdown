---
header-id: liferay-npm-bundler
---

# liferay-npm-bundler

[TOC levels=1-4]

The liferay-npm-bundler is a bundler (like 
[Webpack](https://webpack.github.io/) 
or 
[Browserify](http://browserify.org/)
) that targets @product@ as a platform and assumes you're using your npm 
packages from widgets (as opposed to typical web applications). 

The workflow for running npm packages inside widgets is slightly different from
standard bundlers. Instead of bundling the JavaScript in a single file, you must
*link* all packages together in the browser when the full web page is assembled.
This lets widgets share common versions of modules instead of each one loading
its own copy. The liferay-npm-bundler handles this for you. 

This section of reference docs covers how @product@ supports npm-based widget 
projects with the liferay-npm-bundler. 

| **Note:** You can also find information for the liferay-npm-bundler in the 
| project's 
| [Wiki](https://github.com/liferay/liferay-npm-build-tools/wiki).
