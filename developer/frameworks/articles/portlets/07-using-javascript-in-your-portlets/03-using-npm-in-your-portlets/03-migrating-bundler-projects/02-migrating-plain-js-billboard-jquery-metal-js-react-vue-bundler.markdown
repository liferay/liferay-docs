---
header-id: migrating-plain-js-billboard-jquery-metal-js-react-vue-project
---

# Migrating a Plain JavaScript, Billboard JS, JQuery, Metal JS, React, or Vue JS Project to Use Bundler 2.x

[TOC levels=1-4]

After following the steps covered in the intro to this section, follow these 
remaining steps to migrate the framework projects shown below to 2.x:

- plain JS project
- Billboard.js project
- JQuery project
- Metal.js project
- React project
- Vue.js project

While Babel is required to transpile your source files, you must remove any 
Babel preset used for transformations from your project that bundler 1.x 
imposed. liferay-npm-bundler 2.x handles these transformations by default:

1.  Remove the *liferay-project* preset from your project's `.babelrc` file. All 
    that should remain is the `es2015` preset shown below:

    ```json
    {
      "presets": ["es2015"]
    }
    ```

    If your project uses React, make sure the `react` preset remains as well:

    ```json    
    {
      "presets": ["es2015", "react"]
    }
    ```

2.  Remove the `babel-preset-liferay-project` dependency from your 
    `package.json`.

Awesome! Your project is migrated to use the new version of the 
liferay-npm-bundler. 

## Related Topics

- [Formatting Your npm Modules for AMD](/docs/7-2/frameworks/-/knowledge_base/f/formatting-your-npm-modules-for-amd)
- [Using the NPMResolver API in Your Portlets](/docs/7-2/frameworks/-/knowledge_base/f/using-the-npmresolver-api-in-your-portlets)
- [What Changed between liferay-npm-bundler 1.x and 2.x](/docs/7-2/reference/-/knowledge_base/r/what-changed-between-liferay-npm-bundler-1-x-and-2-x)
