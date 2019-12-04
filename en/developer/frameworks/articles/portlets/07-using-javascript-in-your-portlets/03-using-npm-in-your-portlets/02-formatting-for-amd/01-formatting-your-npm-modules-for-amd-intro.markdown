---
header-id: formatting-your-npm-modules-for-amd
---

# Formatting Your npm Modules for AMD

[TOC levels=1-4]

For @product@ to recognize your npm modules, they must be formatted for the 
Liferay AMD Loader. Luckily, the liferay-npm-bundler handles this for you, you 
just have to provide the proper configuration and add it to your build script. 
This article shows how to use the liferay-npm-bundler to set up npm-based 
portlet projects. 

Follow these steps to configure your project to use the liferay-npm-bundler:

1.  Install NodeJS >= [v6.11.0](http://nodejs.org/dist/v6.11.0/) if you don't 
    have it installed.

2.  Navigate to your portlet's project folder and initialize a `package.json` 
    file if it's not present yet.

    If you don't have a portlet already, create an empty MVC portlet project. 
    For convenience, you can use 
    [Blade CLI](/docs/7-2/reference/-/knowledge_base/r/installing-blade-cli) 
    to create an empty portlet with the [mvc portlet blade template](/docs/7-2/reference/-/knowledge_base/r/using-the-mvc-portlet-template). 

    If you don't have a `package.json` file, you can run `npm init -y` to create 
    an empty one based on the project directory's name. 

3.  Run this command to install the liferay-npm-bundler:

    ```bash
    npm install --save-dev liferay-npm-bundler
    ```

    | **Note:** Use npm from within your portlet project's root folder (where the
    | `package.json` file lives), as you normally do on a typical web project.

4.  Add the `liferay-npm-bundler` to your `package.json`'s build script to pack 
    the needed npm packages and transform them to AMD:

    ```json
    "scripts": {
          "build": "liferay-npm-bundler"
    }
    ```
    
5.  Configure your project for the bundler, using the `.npmbundlerrc` file 
    (create this file in your project's root folder if it doesn't exist). See 
    the [liferay-npm-bundler's `.npmbundlerrc` structure reference](/docs/7-2/reference/-/knowledge_base/r/understanding-the-npmbundlerrcs-structure) 
    for more information on the available options. Specify the 
    [loaders and rules](/docs/7-2/reference/-/knowledge_base/r/understanding-liferay-npm-bundlers-loaders) 
    to use for your project's source files. The example below processes the 
    JavaScript files in the project's `/src/` and `/assets/` folders with Babel 
    via the [`babel-loader`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-babel-loader):

    ```json
    {
      "sources": ["src", "assets"],
      "rules": [
        {
          "test": "\\.js$",
          "exclude": "node_modules",
          "use": [
            {
              "loader": "babel-loader",
              "options": {
                "presets": ["env"]
              }
            }
          ]
        }
      ]
    }
    ```

6.  Run `npm install` to install the required dependencies.

7.  Run the build script to bundle your dependencies with the 
    liferay-npm-bundler:

    ```bash
    npm run-script build
    ```

| **Note:** By default, the AMD Loader times out in seven seconds. You can 
| configure this value through System Settings. Open the Control Panel and 
| navigate to *Configuration* &rarr; *System Settings* &rarr; *PLATFORM* &rarr; 
| *Infrastructure*, and select *JavaScript Loader*. Set the 
| *Module Definition Timeout* configuration to the time you want and click 
| *Save*.

Great! Now you know how to use the liferay-npm-bundler to bundle your npm-based 
portlets for the Liferay AMD Loader. 

## Related Topics

- [Preparing Your JavaScript Files for ES2015+](/docs/7-2/frameworks/-/knowledge_base/f/using-javascript-in-your-portlets)
- [Understanding liferay-npm-bundler's Loaders and Rules](/docs/7-2/reference/-/knowledge_base/r/understanding-liferay-npm-bundlers-loaders) 
