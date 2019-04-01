---
header-id: migrating-an-angular-app-to-product
---

# Migrating an Angular App to @product@

[TOC levels=1-4]

Migrating an existing Angular app to @product@ makes the app available as a
widget for using on site pages. The migration involves merging your files into a
portlet bundle, adapting your routes and CSS, and deploying your bundle. The
Liferay Bundle Generator and Liferay npm Bundler facilitate the process. 

![Figure 1: Apps like this Guestbook app are easy to migrate to @product@.](../../../images/appdev-angular-app-migrated.png)

Follow these steps:

1.  Install the Liferay Bundle Generator, using
    [npm](https://www.npmjs.com/):

        npm install -g yo generator-liferay-bundle

2.  Generate an Angular based portlet bundle project for deploying your app to 
    your 
    [@product@ installation](/deployment/docs/installing-product). 

        yo liferay-bundle

    Select `Angular based portlet` and opt for generating sample code. Here's the bundle's structure: 

    -   `[my-angular-portlet-bundle]`
        -   `assets/` &rarr; CSS, HTML templates, and resources
            -   `css/` &rarr; CSS files
                -   `styles.css` &rarr; Default CSS file
            -   `app/` &rarr; HTML templates
                -   `app.component.html` &rarr; Root component template
        - `features/` &rarr; @product@ bundle features
            -   `localization/` &rarr; Resource bundles
                -   `Language.properties` &rarr; Default language keys
        -   `src/` &rarr; JavaScript an TypeScript files
            -   `app/` &rarr; Application modules and Components
                -   `app.component.ts` &rarr; Main component
                -   `app.module.ts` &rarr; Root module
                -   `dynamic.loader.ts` &rarr; Loads an Angular component 
                    dynamically for the portlet to attach to
            -   `types/`
                -   `LiferayParams.ts` &rarr; Parameters passed by @product@ to
                    the JavaScript module
            -   `index.ts` &rarr; Main module invoked by the "bootstrap" module to initialize the portlet
            -   `polyfills.ts` &rarr; Fills in browser JavaScript implementation
                gaps
        -   `package.json` &rarr; npm bundle configuration
        -   `README.md`
        -   `.npmbuildrc` &rarr; Build configuration
        -   `.npmbundlerrc` &rarr; Bundler configuration
        -   `tsconfig.json` &rarr; TypeScript configuration

3.  Copy your app files, matching the types listed below, into your new project.

    | File type | Destination | Comments |
    | --------- | ----------- | -------- |
    | HTML | `assets/app/` | Merge your main component with the existing `app.component.html`. |
    | CSS  | `assets/css/` | Overwrite `styles.css`. |
    | TypeScript and JavaScript | `src/app/` |  Merge with all files **except** `app.module.ts`---the root module merge is explained in a later step. |

4.  Update your component class `templateUrl`s to use the `web-context` value 
    declared in your project's `.npmbundlerrc`  file. Here's the format: 

        templateUrl: `/o/[web-context]/app/[template]`

    Here's an example:

        templateUrl: '/o/my-angular-guestbook/app/add-entry/add-entry.component.html'

5.  Update your bundle to use portlet-level styling. 

    -   Import all component CSS files through the CSS file (default is 
        `styles.css`) your bundle's `package.json` file sets for your portlet.
        Here's the default setting:

        ```
        "portlet": {
		    "com.liferay.portlet.header-portlet-css": "/css/styles.css",
            ...
        ```

    -   Remove `selector` and `styleUrls` properties from your component 
        classes. 

6.  In your routing module's `@NgModule` decorator, configure the router option
    `useHash: true`. This tells Angular to use client-side routing in the form
    of `.../#/[route]`, which prevents client-side parameters (i.e., anything
    after `#`) from being sent back to @product@. 

    For example, your routing module class `@NgModule` decorator might look like
    this:

    ```javascript
    @NgModule({
      imports: [RouterModule.forRoot(routes,
          {useHash: true})],
      exports: [RouterModule]
    })
    export class AppRoutingModule { }
    ```

7.  Also in your routing module, export your view components for your root 
    module (discussed next) to use. For example,

    ```javascript
    export const routingComponents = [ViewComponent1, ViewComponent2]
    ```

8.  Merge your root module with `src/app/app.module.ts`, configuring it to 
    dynamically load components. 

    | **Note:** Components must be loaded dynamically to attach to the portlet's
    | DOM. The DOM is determined at run time when the portlet's page is
    | rendered. 

    -   Import the `routingComponents` constant and the app routing module class
        from your app routing module. For example,

        ```
        import { AppRoutingModule, routingComponents } from './app-routing.module';
        ```

    -   Specify the base href for the router to use in the navigation URLs. 

        ```
        import { APP_BASE_HREF } from '@angular/common';
        ...
        
        @NgModule({
            ...
            providers: [{provide: APP_BASE_HREF, useValue: '/'}]
        })
        ```

    -   Declare the `routingComponents` constant in your `@NgModule` decorator. 

        ```
        declarations: [
            routingComponents,
            ...
        ],
        ```

    -   Make sure your `@NgModule` `bootstrap` property has no components. All 
        components are loaded dynamically using the `entryComponents` array
        property. The empty `ngDoBootstrap()` method nullifies the default
        bootstrap implementation. 

        ```
        @NgModule({
    	    entryComponents: [AppComponent],
            bootstrap: []
            ...
        })
        export class AppModule {
            ngDoBootstrap() {}
            ...
        }
        ```

    -   Comment out any HTTP client and in-memory data module code before 
        deploying your bundle, as @product@ handles the requests and data via 
        the bundle's portlet. 

    Your root module `app.module.ts` should look like this. 

    ```javascript
    import { APP_BASE_HREF } from '@angular/common';
    import { AppRoutingModule, routingComponents } from './app-routing.module';
    // more imports ...

    @NgModule({
      declarations: [
        AppComponent,
        routingComponents, 
        // more declarations ...
      ],
      imports: [
        AppRoutingModule,
        // more imports ...
      ],
      entryComponents: [AppComponent],
      providers: [{provide: APP_BASE_HREF, useValue: '/'}],
      bootstrap: [],
      // more properties ...
    })
    export class AppModule {
        ngDoBootstrap() {}

        // ...
    }
    ```

9.  Merge your app `package.json` file's `dependencies` and `devDependencies` 
    into the bundle's `package.json`.
    
    | **Note:** To work around build errors caused by the `rxjs` dependency, set
    | the dependency to version `"6.0.0"`. See
    | [LPS-92848](https://issues.liferay.com/browse/LPS-92848)
    | for details. 

10. If you're building on Windows, set `"process-serially": true` in your 
    bundle's `.npmbundlerrc` file. 
 
11. Finally, deploy your bundle:

        npm run deploy

Congratulations! Your Angular app is deployed and now available as a widget that
you can add to site pages. 

The Liferay npm Bundler confirms the deployment:

    Report written to liferay-npm-bundler-report.html
    Deployed my-angular-guestbook-1.0.0.jar to c:\git\bundles

The @product@ console confirms your bundle started: 

    2019-03-22 20:17:53.181 INFO  [fileinstall-C:/git/bundles/osgi/modules][BundleStartStopLogger:39] STARTED my-angular-guestbook_1.0.0 [1695]

To find your widget, select the *Add* icon
(![Add](../../../images/icon-add-app.png)),
navigate to *Widgets* and then the category you specified to the Liferay Bundle
Generator (*Sample* is the default category). 

## Related Topics

- [Web Services](/docs/7-2/frameworks/-/knowledge_base/appdev/web-services
)

- [Service Builder](/docs/7-2/frameworks/-/knowledge_base/frameworks/service-builder
)

- [Localization](/docs/7-2/frameworks/-/knowledge_base/frameworks/localization
)
