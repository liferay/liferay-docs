---
header-id: using-npm-in-your-portlets
---

# Using npm in Your Portlets

[TOC levels=1-4]

npm is a powerful tool, and almost a necessity for Front-End development. You 
can use npm as your JavaScript package manager tool---including npm and npm 
packages---while developing portlets in your normal, everyday workflow. 
 
Deployed portlets leverage [Liferay AMD Loader](/docs/7-2/frameworks/-/knowledge_base/f/loading-amd-modules-in-liferay) 
to share JavaScript modules and take advantage of semantic versioning when 
resolving modules among portlets on the same page. The liferay-npm-bundler helps 
prepare your npm modules for the Liferay AMD Loader. 

The bundler copies the project and `node_modules`' JS files to the output and 
wraps them inside a `Liferay.Loader.define()` call so that the Liferay AMD 
Loader knows how to handle them. It also namespaces the module names in 
`require()` calls and inside the `Liferay.Loader.define()` call with the 
project's name prefix to achieve [dependency isolation](/docs/7-2/reference/-/knowledge_base/r/how-the-liferay-npm-bundler-publishes-npm-packages#isolated-package-dependencies). 
The bundler injects the dependencies in the `package.json` pertaining to 
the module to make them available at runtime. 

This section covers how to set up npm-based portlet projects. 
