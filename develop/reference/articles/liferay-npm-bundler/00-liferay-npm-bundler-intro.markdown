# liferay-npm-bundler [](id=liferay-npm-bundler)

The liferay-npm-bundler is a bundler (like 
[Webpack](https://webpack.github.io/) 
or 
[Browserify](http://browserify.org/)
) that targets @product@ as a platform and assumes you're using your npm 
packages from portlets (as opposed to typical web applications). 

The workflow for running npm packages inside portlets is slightly different from
standard bundlers. Instead of bundling the JavaScript in a single file, you must
*link* all packages together in the browser when the full web page is assembled.
This lets portlets share common versions of modules instead of each one loading
its own copy. The liferay-npm-bundler handles this for you. 

This section of reference docs covers how Portal supports npm-based portlet 
projects with the liferay-npm-bundler.
