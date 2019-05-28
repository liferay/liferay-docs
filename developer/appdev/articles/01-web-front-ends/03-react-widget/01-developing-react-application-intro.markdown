---
header-id: developing-a-react-application
---

# Developing a React Application

[TOC levels=1-4]

Running an existing React app on @product@ makes the app available as a 
widget for using on site pages. You must use the Liferay Bundle Generator and
Liferay npm Bundler to merge your files into a portlet bundle, adapt your static
resource paths, and deploy your bundle. 

![Figure 1: Apps like this Guestbook app are easy to migrate to @product@.](../../../images/appdev-react-app-migrated.png)

Follow these steps:

1.  Using [npm](https://www.npmjs.com/), install the Liferay Bundle Generator:

        npm install -g yo generator-liferay-bundle

2.  Generate a React based portlet bundle project for deploying your app to 
    your 
    [@product@ installation](/deployment/docs/installing-product). 

        yo liferay-bundle

    Select `React based portlet` and opt for generating sample code. Here's the 
    bundle's structure: 

    -   `my-react-portlet-bundle`
        -   `assets/` &rarr; CSS and resources
            -   `css/` &rarr; CSS files
                - `styles.css` &rarr; Default CSS file
        -   `features/` &rarr; @product@ bundle features
            -   `localization` &rarr; Resource bundles
                -   `Language.properties` &rarr; Default language keys
        -   `src/` &rarr; JavaScript and React component files
            -   `AppComponent.js` &rarr; Sample React component that you can remove
            -   `index.js` &rarr; Main module used to initialize the portlet
        -   `.babelrc` &rarr; Babel configuration
        -   `.npmbuildrc` &rarr; Build configuration
        -   `.npmbundlerrc` &rarr; Bundler configuration
        -   `package.json` &rarr; npm bundle configuration
        -   `README.md`

3.  Copy your app files, matching the types listed below, into your new project.

    | File type | Destination | Comments |
    | --------- | ----------- | -------- |
    | CSS  | `assets/css/` | Overwrite `styles.css`. |
    | JavaScript | `src/` |  Merge with all files **except** `index.js`---the main module merge is explained in a later step. |
    | Static resources | `assets/` |  Include resources such as image files here |

4.  Update your bundle to use portlet-level styling.

    -   Import all component CSS files through the CSS file (default is 
        `styles.css`) your bundle's `package.json` file sets for your portlet.
        Here's the default setting:

       ```json
        "portlet": {
		    "com.liferay.portlet.header-portlet-css": "/css/styles.css",
            ...
        }
       ```

    -   Remove any CSS imports you have in your JS files

5.  Update any static resource references to use the `web-context` value 
    declared in your project's `.npmbundlerrc`  file, and remove any imports for 
    the resource. For example, if you have an image file called `logo.png` in your 
    `assets` folder, you would use the format below. Note that the `assets` 
    folder is not included in the path.

    Here is the format:
 
    ```html
    /o/[web-context]/[resource]
    ```

    Here's an example image resource:

    ```html
    <img alt="React logo" src="/o/react-guestbook-migrated/logo.png">
    ```

6.  Merge your entry module with `src/index.js`, configuring it to dynamically 
    load components. 

    | **Note:** Components must be loaded dynamically to attach to the portlet's
    | DOM. The DOM is determined at run time when the portlet's page is
    | rendered. 

    -   Use the `HashRouter` for routing between component views, as @product@ 
        requires hash routing for proper portal navigation:
 
       ```javascript
        import { HashRouter as Router } from 'react-router-dom';
       ```

    -   Place your code inside the `main()` function.

    -   Render your app inside the `portletElementId` element that is passed in 
        the `main()` function. This is required to render the React app inside
        the portlet.

    Your entry module `index.js` should look like this. 

    ```javascript
    import React from 'react';
    import ReactDOM from 'react-dom';
    //import './index.css';//removed for Portal Migration
    import App from './App';
    import { HashRouter as Router } from 'react-router-dom';

    export default function main({portletNamespace, contextPath, 
    portletElementId}) {
          ReactDOM.render((
            <Router>
              <App/>
            </Router>
          ), document.getElementById(portletElementId));
    }
    ```

7.  Merge your app `package.json` file's `dependencies` and `devDependencies` 
    into the bundle's `package.json`.

8.  If you're building on Windows, set `"process-serially": true` in your 
    bundle's `.npmbundlerrc` file. 
 
9.  Finally, deploy your bundle:

        npm run deploy

Congratulations! Your React app is deployed and now available as a widget that 
you can add to site pages. 

The Liferay npm Bundler confirms the deployment:

    Report written to liferay-npm-bundler-report.html
    Deployed my-react-guestbook-1.0.0.jar to c:\git\bundles

The @product@ console confirms your bundle started: 

    2019-03-22 20:17:53.181 INFO  
    [fileinstall-C:/git/bundles/osgi/modules][BundleStartStopLogger:39] 
    STARTED my-react-guestbook_1.0.0 [1695]

To Find your widget, click the *Add* icon 
(![Add](../../../images/icon-add-app.png)), 
navigate to *Widgets* and then the category you specified to the Liferay Bundle 
Generator (*Sample* is the default category). 

## Related Topics

[Web Services](/docs/7-2/appdev/-/knowledge_base/a/web-services)

[Service Builder](/docs/7-2/frameworks/-/knowledge_base/f/service-builder)

[Localization](/docs/7-2/frameworks/-/knowledge_base/f/localization)
