---
header-id: dynamic-includes
---

# Dynamic Includes

[TOC levels=1-4]

Dynamic includes expose extension points in JSPs for injecting additional 
HTML, adding resources, modifying editors, and more. Several dynamic includes 
are available. Once you know the dynamic include's key, you can use it to 
[create a module to inject your content](/docs/7-2/customization/-/knowledge_base/c/customizing-jsps-with-dynamic-includes). 

This section of tutorials lists the available dynamic include keys, along with a 
description of their use cases and a code example. 

The following extension points are covered in this section:

Extension Point | Purpose |
:---------: | :--------------: |
[bottom](/docs/7-2/customization/-/knowledge_base/c/bottom-jsp-dynamic-includes) | Load additional HTML or scripts in the bottom of the theme's body |
[top_head](/docs/7-2/customization/-/knowledge_base/c/top-head-jsp-dynamic-includes) | Load additional links in the theme's head |
[top_js](/docs/7-2/customization/-/knowledge_base/c/top-js-dynamic-include) | Load additional JS files in the theme's head |
[WYSIWYG](/docs/7-2/customization/-/knowledge_base/c/wysiwyg-editor-dynamic-includes) | Add resources to the editor, listen to events, update the configuration, etc. |
