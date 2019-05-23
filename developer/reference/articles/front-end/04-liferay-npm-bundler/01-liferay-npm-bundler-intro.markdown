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

| **Note:** You can also find information for the liferay-npm-bundler in the 
| project's 
| [Wiki](https://github.com/liferay/liferay-npm-build-tools/wiki).

## How the Liferay npm Bundler Works Internally

The liferay-npm-bundler takes a widget project and outputs its files 
(including npm packages) to a build folder, so the standard widget build 
(Gradle) can produce an OSGi bundle. You can learn more about the build folder's 
structure in 
[The Structure of OSGi Bundles Containing NPM Packages](/docs/7-2/reference/-/knowledge_base/r/the-structure-of-osgi-bundles-containing-npm-packages) 
reference. 

The liferay-npm-bundler uses the process below to create the OSGi bundle:

1.  Copy the project's `package.json` file to the output directory.

2.  Traverse the project's dependency tree to determine its dependencies.

3.  For each npm package dependency:

    a. Copy the npm package to the output folder and prefix the bundle's name 
    to it. Note that the bundler stores packages in a plain 
    *bundle-name$package*@*version* format, rather than the standard 
    node_modules tree format. To determine what is copied, the bundler invokes a 
    plugin to filter the package file list. 

    b. Pre-process the npm package with any configured plugins.

    c. Run 
       [Babel](https://babeljs.io/) with configured plugins for each `.js` file 
       inside the npm package.

    d. Post-process the npm package with any configured plugins.

4.  For the project:

    a. Pre-process the project's package with any configured plugins.

    c. Run 
       [Babel](https://babeljs.io/) with configured plugins for each `.js` file
       inside the project.

    d. Post-process the project package with any configured plugins.

The only difference between the pre-process and post-process steps are when they 
are run (before or after Babel is run, respectively). During this workflow, 
liferay-npm-bundler calls all the configured plugins so they can perform 
transformations on the npm packages (for instance, modifying their `package.json` 
files, or deleting or moving files). 

In this reference section, you'll learn more about the liferay-npm-bundler's 
configuration, default presets, format, and more. 
