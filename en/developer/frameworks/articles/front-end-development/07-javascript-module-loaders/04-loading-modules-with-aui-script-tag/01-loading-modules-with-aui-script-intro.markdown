---
header-id: loading-modules-with-aui-script
---

# Loading Modules with AUI Script

[TOC levels=1-4]

The `aui:script` tag is a JSP tag that loads JavaScript on the page, while 
ensuring that certain resources are loaded before executing. 

| **Note:** AUI is deprecated and no longer in active development in 
| @product-ver@, but all the tags will remain fully functional in @product-ver@. 
| Eventually, these tags will be replaced with [Clay](https://claycss.com/) 
| tag counterparts.

The `aui:script` tag supports the following options:

| Option | Description |
| --- | --- |
| `require` | Requires an AMD module to load with the [Liferay AMD Module Loader](https://github.com/liferay/liferay-amd-loader#amd-module-loader). |
| `use` | Uses an AlloyUI/YUI module that is loaded via the YUI loader. |
| `position` | The position the script tag is put on the page. Possible options are `inline` or `auto`. |
| `sandbox` | Whether to wrap the script tag in an anonymous function. If set to `true`, in addition to the wrapping, `$` and `_` are defined for jQuery and underscore. |

This section covers how to load ES2015, Metal.js, and AUI modules with the AUI 
script tag. 
