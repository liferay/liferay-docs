# What Changed Between Liferay npm Bundler 1.x and 2.x [](id=changes-between-liferay-npm-bundler-1x-and-2x)

This reference doc outlines the key changes between liferay-npm-bundler version 
1.x and 2.x.

## Automatically Formatting Modules for AMD [](id=automatically-formatting-modules-for-amd)

In version series 1.x of the bundler it was the developer's responsibility to 
wrap project modules in an AMD `define()` call. However, since 2.x the bundler 
does it for you, so the only requisite is that the project's code is 
transpiled/written for CommonJS modules model (the standard model for module 
handling in Node.js, that uses `require()` calls to load modules).

## Isolating Project Dependencies [](id=isolating-project-dependencies)

Package names are prefixed with the bundle name since version 2.0.0 of the 
bundler, but were left intact in previous versions. This strategy is used to 
isolate packages from different bundles. You can still deploy bundler 1.x 
packages (without prefix), and they will still work as they did for previous 
versions of the bundler.

## Improved Peer Dependency Support [](id=improved-peer-dependency-support)

In bundler 1.x, there was only one shared peer dependency package available 
between portlets. With isolated dependencies per portlet, it's easy to honor 
peer dependencies perfectly. Peer dependencies can be resolved exactly as stated 
in projects because their names are prefixed with the project's name. This is 
possible because of the new  [liferay-npm-bundler-plugin-inject-peer-dependencies](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-inject-peer-dependencies) 
plugin. It scans all JS modules for `require` calls. If the bundler finds a 
required package in the `main.js` file, but it is not declared in the 
`package.json`, it resolves it to the proper version that is found in the 
`node_modules` folder. The plugin then injects a new dependency in the output 
`package.json` for the required package.

Note that injected dependency version constraints are the specific version 
number required, without caret or any other semantic version operator. This is 
to honor the exact peer dependency found in the project. Injecting more relaxed 
semantic version expressions could lead to unstable results.

## Manually De-duplicating Through Importing [](id=manually-deduplicating-through-importing)

Namespacing means that each portlet gets its own dependencies. Only using the 
bundler this way obtains the same functionality as standard bundlers like 
webpack or Browserify, so you wouldn't need a specific tool like 
liferay-npm-bundler. Since @product@ is a portlet based architecture, sharing 
dependencies among different portlets would be very beneficial.

In bundler 1.x that deduplication was made automatically, but there was no 
control over it. However, with version 2.x, you may now import packages from an
external OSGi bundle, instead of using your own. This lets you put shared 
dependencies in one project, and reference them from the rest. Though This new 
way of de-duplication is not automatic, it leads to full control 
(during build time) of how each package is resolved.
