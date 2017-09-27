# liferay-npm-bundler [](id=liferay-npm-bundler)

The liferay-npm-bundler is a bundler (like [Webpack](https://webpack.github.io/) 
or [Browserify](http://browserify.org/)) that targets @product@ as a platform 
and assumes that you are using your npm packages from portlets 
(as opposed to typical web applications). 

The workflow for running npm packages inside portlets is a bit different from 
standard bundlers (like Browserify or Webpack). Instead of bundling the 
JavaScript in a single file, you must *link* all packages together in the 
browser when the full web page is assembled. This lets portlets share common 
versions of modules instead of each one loading its own copy. The 
liferay-npm-bundler handles this for you. You can learn how it works next.

## How it Works Internally [](id=how-it-works-internally)

The liferay-npm-bundler takes a @product@ portlet project and outputs its files 
(including npm packages) to a build directory, so that the standard portlet 
build (Gradle) can produce an OSGi bundle for use in @product@. You can learn 
more about the build directory's structure in 
[The Structure of OSGi Bundles Containing NPM Packages](/develop/tutorials/-/knowledge_base/7-0/the-structure-of-osgi-bundles-containing-npm-packages) 
tutorial.

The portlet project's source files are run through the following workflow to 
produce the OSGi bundle:

1.  Copy the project's `package.json` file to the output directory.

2.  Traverse the project's dependency tree to determine which packages are 
    required to run it.

3.  For each npm package dependency:

    a. Copy the npm package to the output directory (in plain *package*@*version* 
        format, as opposed to the standard node_modules tree format).

    b. Pre-process the npm package with any configured plugins.

    c. Run [Babel](https://babeljs.io/) with configured plugin's for each `.js` 
        file inside the npm package.

    d. Post-process the npm package with any configured plugins.

The only difference between the pre-process and post-process steps are when they 
are run (before or after Babel is run, respectively). During this workflow, 
liferay-npm-bundler calls all the configured plugins, so that they can 
perform transformations on the npm packages (for instance, modifying its 
`package.json` file, or deleting or moving files).

Now you understand how the liferay-npm-bundler works!

## Related Topics [](id=related-topics)

[The Structure of OSGi Bundles Containing NPM Packages](/develop/tutorials/-/knowledge_base/7-0/the-structure-of-osgi-bundles-containing-npm-packages)

[How @product@ Publishes NPM Packages](/develop/tutorials/-/knowledge_base/7-0/how-liferay-portal-publishes-npm-packages)

[Liferay JavaScript APIs](/develop/tutorials/-/knowledge_base/7-0/liferay-javascript-apis)
