# Using ES2015 in Your Portlets [](id=using-es2015-in-your-portlets)

You can now write JavaScript that adheres to the new ECMAScript 2015 (ES2015)
syntax, leverage ES2015 advanced features in your modules, and publish them. To
do these things, you need make only minor adjustments to your JavaScript files
and projects. 

+$$$

**Note:** If you're deploying a portlet that requires the JS to be minified, 
either through a dependency or your own project, the minifier fails on Liferay 
DXP 7.0 de-69 and below. To resolve this, use 
[Grunt](https://gruntjs.com/getting-started) 
[uglify](https://www.npmjs.com/package/grunt-contrib-uglify) 
to remove any code causing the issue, such as JSDoc comments. This process may 
take a long time, depending on the number of files that require an update. 

$$$

This section of tutorials shows how to prepare modules for ES2015 and use them 
in your portlets.
