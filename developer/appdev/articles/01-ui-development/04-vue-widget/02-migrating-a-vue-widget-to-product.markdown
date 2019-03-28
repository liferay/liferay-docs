---
header-id: migrating-a-vue-app-to-product
---

# Migrating a Vue App to @product@

[TOC levels=1-4]

Migrating an existing Vue app to @product@ makes the app available as a
widget for using on site pages. The migration involves merging your files into a
portlet bundle, adapting your static resource paths, and deploying your 
bundle. The Liferay Bundle Generator and liferay-npm-bundler facilitate the 
process. The steps below demonstrate how easy it is to migrate a Vue app that 
uses single file components (`.vue` files) with multiple views. 

Follow these steps:

1.  Install the Liferay Bundle Generator, using
    [npm](https://www.npmjs.com/):

        npm install -g yo generator-liferay-bundle

2.  Generate a Vue based portlet bundle project for deploying your app to 
    your 
    [@product@ installation](/deployment/docs/installing-product). 

        yo liferay-bundle

    Select `Vue based portlet` and opt for generating sample code. Here's the 
    bundle's structure: 

    -   `my-vue-portlet-bundle`
        -   `assets` &rarr; CSS and resources
            -   `css` &rarr; You can remove this folder since the CSS is 
                included in the `.vue` file.
        - `features` &rarr; @product@ bundle features
            -   `localization` &rarr; Resource bundles
                -   `Language.properties` &rarr; Default language keys
        -   `src` &rarr; JavaScript and Vue files
            -   `index.js` &rarr; Main module used to initialize the portlet
        -   `.babelrc` &rarr; Babel configuration
        -   `.npmbuildrc` &rarr; Build configuration
        -   `.npmbundlerrc` &rarr; Bundler configuration
        -   `package.json` &rarr; npm bundle configuration
        -   `README.md`

3.  Copy your app files, matching the types listed below, into your new project.

    | File type | Destination | Comments |
    | --------- | ----------- | -------- |
    | VUE and JS| `src` | Merge your main component with the existing `index.js`. More info on that below. |
    | Static resources | `assets` |  Include resources such as image files here |

4.  Update any static resource references to use the `web-context` value 
    declared in your project's `.npmbundlerrc`  file. Here's the format: 

    ```html
    /o/[web-context]/[resource]
    ```

    Here's an example image resource:

    ```html
    <img alt="Vue logo" src="/o/vue-guestbook-migrated/logo.png">
    ```

5.  Merge your entry module with `src/index.js`, following these steps to 
    dynamically load components. 

    | **Note:** Components must be loaded dynamically to attach to the portlet's
    | DOM. The DOM is determined at run time when the portlet's page is
    | rendered. 

    -   Use Vue's runtime + compiler module 
        (`import Vue from 'vue/dist/vue.common';`) so you don't have to process 
        templates during build time. This is imported by default at the top of 
        the file.

    -   Remove the `node` const and `node.innerHTML=...` block from the `main()` 
        function, and replace it with your router code.

    -   Replace the `new Vue()` configuration with the one below to render your 
        app inside the portlet:
    
        ```javascript
        new Vue({
          el: `#${portletElementId}`,
          render: h => h(App),
          router
        })
        ```

    Your entry module `index.js` should look like this. 

    ```javascript
    import Vue from 'vue/dist/vue.common';
    import App from './App.vue'
    import VueRouter from 'vue-router'
    //Component imports

    export default function main({portletNamespace, contextPath, portletElementId}) {

      Vue.config.productionTip = false

      Vue.use(VueRouter)

      const router = new VueRouter({
          routes: [
              {
                ...
              }
          ]
      })
      new Vue({
        el: `#${portletElementId}`,
        render: h => h(App),
        router
      })
    }
    ```

6.  Merge your app `package.json` file's `dependencies` and `devDependencies` 
    into the project's `package.json`, and replace the `babel-cli` and 
    `babel-preset-env` dev dependencies with the newer `"@babel/cli": "^7.0.0"` 
    and `"@babel/preset-env": "^7.4.2"` packages instead. Also include the 
    `"vueify": "9.4.1"` dev dependency.

7.  Update the `.babelrc` file to use `@babel/preset-env` instead of 
    `env`:

    ```json
    "presets": ["@babel/preset-env"]
    ```

8.  Replace the build script in the `package.json` with the one below to use 
    `vue-cli-service`. The updated build script uses vue-cli to access the main 
    entrypoint for the app (`index.js` in the example below) and combines all 
    the Vue templates and JS files into one single file named `index.common.js`:

    ```json
    "scripts": {
      "build": "babel --source-maps -d build src && vue-cli-service build --dest 
      build/ --formats commonjs --target lib --name index ./src/index.js && npm 
      run",
      "copy-assets": "lnbs-copy-assets",
      "deploy": "npm run build && lnbs-deploy",
      "start": "lnbs-start"
    }
    ```

9.  Update the `main` entry of the `package.json` to match the new 
    [CommonJS](http://www.commonjs.org/) 
    file name specified in the previous step:
    
    ```json
    "main": "index.common"
    ```

10. If you're building on Windows, set `"process-serially": true` in your 
    bundle's `.npmbundlerrc` file. 
 
11. Finally, deploy your portlet bundle:

        npm run deploy

Congratulations! Your Vue app is deployed and now available as a widget that you 
can add to site pages. 

The liferay-npm-bundler confirms the deployment:

    Report written to liferay-npm-bundler-report.html
    Deployed my-vue-guestbook-1.0.0.jar to c:\git\bundles

The @product@ console confirms your bundle started: 

    2019-03-22 20:17:53.181 INFO  
    [fileinstall-C:/git/bundles/osgi/modules][BundleStartStopLogger:39] 
    STARTED my-vue-guestbook_1.0.0 [1695]

Find your widget by selecting the *Add* icon
(![Add](../../../images/icon-add-app.png))
and navigating to *Widgets* and the category you specified to the Liferay Bundle
Generator (*Sample* is the default category). 

## Related Topics

- [Web Services](/docs/7-2/frameworks/-/knowledge_base/appdev/web-services)

- [Service Builder](/docs/7-2/frameworks/-/knowledge_base/frameworks/service-builder)

- [Localization](/docs/7-2/frameworks/-/knowledge_base/frameworks/localization)
