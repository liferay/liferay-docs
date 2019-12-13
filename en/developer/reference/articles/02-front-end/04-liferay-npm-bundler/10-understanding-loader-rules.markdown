---
header-id: understanding-liferay-npm-bundlers-loaders
---

# Understanding liferay-npm-bundler's Loaders

[TOC levels=1-4]

liferay-npm-bundler's mechanism is inspired by webpack. Like webpack, the 
liferay-npm-bundler processes files using a set of rules that include loaders 
that transform a project's source files before producing the final output. 

| **Note:** While webpack creates a single JS bundle file, liferay-npm-bundler 
| targets an AMD loader, so webpack and liferay-npm-bundler loaders are not 
| compatible.

Loaders are npm packages that export a function in their main module that 
receives source files and returns modified files, and optionally new files, 
based on the loader's configuration. For example, the [babel-loader](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-babel-loader) 
receives ES6+ JavaScript files, runs Babel on them, and returns transpiled ES5 
files along with a generated source map. You can use this pattern to 
[create custom loaders](/docs/7-2/frameworks/-/knowledge_base/f/creating-custom-loaders-for-the-liferay-npm-bundler). 
A few example loader functions are shown below:

- Pass JavaScript files through Babel or TSC
- Convert CSS files into JS modules that dynamically inject the CSS into the 
  HTML page
- Process CSS files with SASS
- Create tools that generate code based on [IDL](https://en.wikipedia.org/wiki/Interface_description_language) 
  files

Loaders are configured via the project's `.npmbundlerrc` file. A loader's 
configuration is specified using two key options: `sources` 
(the folders that contain the sources files to process) and `rules` 
(the loaders, options---if applicable---and regular expressions that determine 
which files to process). See [Understanding the `.npmbundlerrc`'s Structure](/docs/7-2/reference/-/knowledge_base/r/understanding-the-npmbundlerrcs-structure#package-processing-options) 
for more information on the configuration requirements and options. 

Loaders can be chained. Files are processed by the loaders in the order 
they are listed in the `use` property. The files are passed to the first loader, 
processed, sent to the next loader, and so on, until the files are 
processed by the rules. You can run complex processes, such as converting 
a SASS file into CSS with the sass-loader, and then convert it into a JavaScript 
module with the style-loader. Once the rules are applied, the 
liferay-npm-bundler continues with the pre, post, and babel phases of the 
bundler plugins. 
