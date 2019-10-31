---
header-id: adapting-existing-apps-to-run-on-product
---

# Adapting Existing Apps to Run on @product@

[TOC levels=1-4]

There are two ways to get your existing front-end applications running on
@product@: 

1. [Migrate your project](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends) 
   to a Liferay JS Toolkit project. 

2. Since v2.14.0 of the Liferay JS Toolkit, create projects normally, like with
   [create-react-app](https://facebook.github.io/create-react-app/) and [Angular
   CLI](https://cli.angular.io/) (Vue CLI based projects will be supported
   soon), and adapt them to run on @product@. 

Only adapt your project if you intend it to be platform-agnostic. If
you want to integrate with @product@ fully and have access to all the 
[features and benefits](/docs/7-2/frameworks/-/knowledge_base/f/creating-and-bundling-javascript-widgets-with-javascript-tooling) 
that it provides, [migrate your project](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends) 
to a true Liferay JS Toolkit project instead.

The reason for this is some of @product@'s features may not be available because
the native frameworks expect certain things. For example, Angular assumes that
it controls a whole Single Page Application as opposed to the small portion of
the page that it controls in a portlet-based platform such as @product@. Since
webpack bundles all JavaScript in a single file to consume per app, if there are
five widgets on a page, you have five copies of the framework in the JavaScript
interpreter. To prevent this, 
[migrate your project](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends) to 
a true Liferay JS Toolkit instead. 

To adapt your project, it must have the structure shown below:

- **Angular CLI projects:** Must use `app-root` as the application's Dom 
  selector. 

- **creact-react-app projects:** Must use `ReactDom.render()` call in your entry 
  point with a `document.getElementById()` parameter.

When your project meets the requirements, you can follow these steps to use the 
Liferay JS Generator to adapt it:

1.  Open the command line and navigate to your project's folder.

2.  Run the Liferay JS Generator's `adapt` subtarget:

    ```bash
    yo liferay-js:adapt
    ```

    ![Figure 1: You can run the adapt subtarget of the Liferay JS Generator to adapt your existing apps for Liferay.](../../../images/liferay-js-generator-adapt-run.png)

3.  Answer the prompts. An example configuration appears below:

    ```bash
    ? Under which category should your widget be listed? category.sample
    ? Do you have a local installation of Liferay for development? Yes
    ? Where is your local installation of Liferay placed? /home/user/liferay
    ```

    Your project is adapted to use the Liferay JS Toolkit and run on @product@! 

    ![Figure 2: You can run the adapt subtarget of the Liferay JS Generator to adapt your existing apps for Liferay.](../../../images/liferay-js-generator-adapt-complete.png)

4.  The adapt process automatically addsa few npm scripts to the project's 
    `package.json` so you can build and deploy your project to @product@. Note 
    that you can swap `npm` for `yarn` below if you prefer to use yarn instead.

    Run the command below to add a deployable JAR to the `build.liferay` folder 
    in your project. For example, if you want to build the JAR and copy it to 
    another app server, you can run this command:

    ```bash
    npm run build:liferay
    ```

    Run the command below to deploy the adapted app to your @product@ instance:

    ```bash
    npm run deploy:liferay
    ```

Great! Now you know how to use the Liferay JS Generator to adapt your existing 
apps to run on @product@. See the 
[React Guestbook App](https://github.com/liferay/liferay-docs/tree/master/en/developer/reference/code/adapted-react-app/) 
for a working example of an adapted app. 

![Figure 3: Your adapted app runs in Liferay in no time.](../../../images/liferay-js-generator-adapt-deployed.png)
