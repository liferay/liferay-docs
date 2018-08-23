# Using npm in Your Portlets [](id=using-npm-in-your-portlets)

npm is a powerful tool, and almost a necessity for Front-End development. You 
can use npm as your JavaScript package manager tool---including npm and npm
packages---while developing portlets in your normal, everyday workflow. 
 
Deployed portlets leverage 
[Liferay AMD Loader](/develop/tutorials/-/knowledge_base/7-1/loading-amd-modules-in-liferay) 
to share JavaScript modules and take advantage of semantic versioning when 
resolving modules among portlets on the same page. The liferay-npm-bundler helps 
prepare your npm modules for the Liferay AMD Loader.

This section of tutorials covers how to set up npm-based portlet projects. 
