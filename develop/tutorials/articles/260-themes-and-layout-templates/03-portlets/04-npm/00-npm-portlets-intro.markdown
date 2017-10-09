# Using npm packages in Portlets [](id=using-npm-packages-in-portlets)

Since Liferay DXP Fix Pack 30 and Liferay Portal 7.0 CE GA5, you can use 
npm as your JavaScript package manager tool while developing portlets. This 
includes using npm, and npm packages, in a typical workflow that you're used to.
 
Deployed portlets leverage [Liferay AMD Loader](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/liferay-amd-module-loader) 
to share common JavaScript modules, and take advantage of semantic versioning 
when resolving common modules among portlets on the same page.

This section of tutorials covers how to set up an npm-based portlet project and 
how @product@ supports them.
