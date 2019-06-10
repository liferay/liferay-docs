---
header-id: javascript-module-loaders
---

# JavaScript Module Loaders

[TOC levels=1-4]

A JavaScript module encapsulates code into a useful unit that exports its 
capability/value. This makes it easier to see the broader scope, easier to find 
what you're looking for, and keeps related code close together. A normal web 
page usually loads JavaScript files via HTML `script` tags. That's fine for 
small websites, but when developing large scale web applications, a more robust 
organization and loader is needed. A module loader lets an application load 
dependencies easily by specifying a string that identifies the JavaScript 
module's name. 

This section shows how to load JavaScript modules in @product@. 
