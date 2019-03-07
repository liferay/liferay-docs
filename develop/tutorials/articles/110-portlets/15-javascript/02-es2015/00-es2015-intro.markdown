# Using ES2015 in Your Portlets [](id=using-es2015-in-your-portlets)

You can now write JavaScript that adheres to the new ECMAScript 2015 (ES2015)
syntax, leverage ES2015 advanced features in your modules, and publish them. To
do these things, you need make only minor adjustments to your JavaScript files
and projects. 

+$$$

**Note:** The minifier fails on Liferay DXP 7.0 when JSDoc is present in a 
portlet. To resolve this, use 
[Grunt](https://gruntjs.com/getting-started) 
[uglify](https://www.npmjs.com/package/grunt-contrib-uglify) 
to remove the JSDoc comments. This process may take a long time, depending on 
the number of files that require an update. 

$$$

This section of tutorials shows how to prepare modules for ES2015 and use them 
in your portlets.
