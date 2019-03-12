# Migrating an Angular App to @product@

Migrating an existing Angular app to @product@ involves a few minor 
modifications to the routes, dependencies, and CSS. You'll use the 
liferay-npm-bundler and the Liferay Bundle Generator to help automate the 
process.

Follow these steps:

1. Install the Liferay Bundle Generator, if it's not already installed.

2.  Overwrite the existing `app.component.html` file in the generated bundle's 
    `assets/app/` folder with your app's `app.component.html`. Copy any other 
    HTML templates you have over to the `assets/app/` folder as well.

3.  Copy all folders and `.ts` files from the `app/` folder over to the 
    generated bundle's `src/app` folder, making sure to overwrite existing files 
    when prompted.
        
4.  Update any relative paths for `templateUrl`s to use the generated portlet's 
    web context path, specified in the portlet's `.npmbundlerrc` file. An 
    example configuration is shown below:

        /o/[web-context]/app/add-entry.component.html

6.  Update the app to use portlet-level styling. Remove the `styleUrl`s from 
    your component classes, along with the selector properties, and copy the 
    `app.component.css` file, along with any other component CSS files you have 
    to `assets/css/` folder.

7.  Import `app.component.css` at the top of the `styles.css` file, along with 
    any other component CSS files you copied into the `css` folder in the last 
    step.

        @import "app.component.css";
        
8.  Update routing for @product@. Add `{provide: APP_BASE_HREF, useValue: '/'}` 
    to the `app.module.ts` `providers` to specify the base href for the router. 
    Also remove `AppComponent` from the `bootstrap` property. 

9.  Set `useHash` to `true` and `enableTracing` to `false` in the `@NgModule` 
    `imports` property of the `app-routing.module.ts` file. This enables 
    client-side routing for @product@, which @product@ requires.

10.  Still inside `app-routing.module.ts`, export your routing component views. 
     An example configuration is shown below:
     
        export 
        const routingComponents = [ViewGuestbookComponent, AddEntryComponent]

11.  Finally, deploy your portlet with the command below:

        npm run deploy

+$$$

**Note:** When deploying your app, you may run into EMFile errors on Windows, 
which prevent your project from building. To fix this issue, set 
`process-serially` to `true` in your project's `npmbundlerrc` file.

$$$