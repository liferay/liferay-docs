# liferay-npm-bundler [](id=liferay-npm-bundler)

The liferay-npm-bundler is a bundler (like [Webpack](https://webpack.github.io/) 
or [Browserify](http://browserify.org/)) that targets @product@ as a platform 
and assumes you're using your npm packages from portlets (as opposed to typical
web applications). It is just one of the pieces of the Liferay JS Bundle 
Toolkit. Liferay JS Bundle Toolkit is an abstract umbrella term that refers to 
the following tools:

- [liferay-npm-bundler](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler)
- [Babel plugins](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages)
- [liferay-npm-bundler plugins](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages)
- [frontend-js-loader-modules-extender](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/apps/foundation/frontend-js/frontend-js-loader-modules-extender)
- [Javascript AMD loader](https://github.com/liferay/liferay-amd-loader)

+$$$

**Note:** The Liferay JS Bundle Toolkit is supported for Liferay DXP 7.0 Fix 
Pack 39 and up.

$$$

The workflow for running npm packages inside portlets is slightly different from
standard bundlers. Instead of bundling the JavaScript in a single file, you must
*link* all packages together in the browser when the full web page is assembled.
This lets portlets share common versions of modules instead of each one loading
its own copy. The liferay-npm-bundler handles this for you. You can learn how it
works next.

## How it Works Internally [](id=how-it-works-internally)

The liferay-npm-bundler takes a @product@ portlet project and outputs its files 
(including npm packages) to a build folder, so the standard portlet 
build (Gradle) can produce an OSGi bundle. You can learn more about the build
folder's structure in 
[The Structure of OSGi Bundles Containing NPM Packages](/develop/tutorials/-/knowledge_base/7-0/the-structure-of-osgi-bundles-containing-npm-packages) 
tutorial.

Here's what happens to produce the OSGi bundle:

1.  Copy the project's `package.json` file to the output directory.

2.  Traverse the project's dependency tree to determine its dependencies.

3.  For each npm package dependency:

    a. Copy the npm package to the output folder and prefix the bundle's name to 
       it. Note that the bundler stores packages in plain 
       *bundle-name$package*@*version* format, rather than the standard 
       node_modules tree format).

    b. Pre-process the npm package with any configured plugins.

    c. Run 
       [Babel](https://babeljs.io/) 
       with configured plugins for each `.js` file inside the npm package.

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

Now you understand how the liferay-npm-bundler works!

## Related Topics [](id=related-topics)

[The Structure of OSGi Bundles Containing NPM Packages](/develop/tutorials/-/knowledge_base/7-0/the-structure-of-osgi-bundles-containing-npm-packages)

[How @product@ Publishes NPM Packages](/develop/tutorials/-/knowledge_base/7-0/how-liferay-portal-publishes-npm-packages)

[Liferay JavaScript APIs](/develop/tutorials/-/knowledge_base/7-0/liferay-javascript-apis)
