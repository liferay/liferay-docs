# How the Liferay npm Bundler Works Internally [](id=how-the-liferay-npm-bundler-works-internally)

The liferay-npm-bundler takes a portlet project and outputs its files 
(including npm packages) to a build folder, so the standard portlet build 
(Gradle) can produce an OSGi bundle. You can learn more about the build folder's 
structure in 
[The Structure of OSGi Bundles Containing NPM Packages](/develop/reference/-/knowledge_base/7-1/the-structure-of-osgi-bundles-containing-npm-packages) 
reference.

The liferay-npm-bundler uses the process below to create the OSGi bundle:

1.  Copy the project's `package.json` file to the output directory.

2.  Traverse the project's dependency tree to determine its dependencies.

3.  For each npm package dependency:

    a. Copy the npm package to the output folder and prefix the bundle's name
    to it. Note that the bundler stores packages in a plain
    *bundle-name$package*@*version* format, rather than the standard
    node_modules tree format.

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
