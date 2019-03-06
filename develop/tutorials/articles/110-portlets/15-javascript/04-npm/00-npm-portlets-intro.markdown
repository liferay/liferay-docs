# Using npm in Your Portlets [](id=using-npm-in-your-portlets)

npm is a powerful tool, and almost a necessity for Front-End development. Since 
Liferay DXP Fix Pack 30 and Liferay Portal 7.0 CE GA5, you can use npm as your 
JavaScript package manager tool---including npm and npm packages---while 
developing portlets in your normal, everyday workflow. 
 
Deployed portlets leverage 
[Liferay AMD Loader](/develop/tutorials/-/knowledge_base/7-0/liferay-amd-module-loader) 
to share JavaScript modules and take advantage of semantic versioning when 
resolving modules among portlets on the same page. The liferay-npm-bundler helps 
prepare your npm modules for the Liferay AMD Loader. 

+$$$

**Note:** If you're deploying a portlet that requires the JS to be minified, 
either through a dependency or your own project, the minifier fails on Liferay 
DXP 7.0 de-69 and below. To resolve this, use 
[Grunt](https://gruntjs.com/getting-started) 
[uglify](https://www.npmjs.com/package/grunt-contrib-uglify) 
to remove any code causing the issue, such as JSDoc comments. This process may 
take a long time, depending on the number of files that require an update. 

$$$

This section of tutorials covers how to set up npm-based portlet projects and 
how @product@ supports them.
