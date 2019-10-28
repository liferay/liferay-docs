---
header-id: adapting-existing-apps-to-run-on-product
---

# Adapting Existing Apps to Run on @product@

[TOC levels=1-4]

Since v2.14.0 of the Liferay JS Toolkit, you can create projects with your 
favorite tools, such as [create-react-app](https://facebook.github.io/create-react-app/) 
and [Angular CLI](https://cli.angular.io/) (Vue CLI based projects will be 
supported in the near future), and adapt them to run on @product@. You should 
only adapt your project if you intend it to be platform-agnostic. If you want to 
fully integrate with @product@ and have access to all the 
[features and benefits](/docs/7-2/frameworks/-/knowledge_base/f/creating-and-bundling-javascript-widgets-with-javascript-tooling) 
that it provides, [migrate your project](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends) 
to a true Liferay JS Toolkit project instead.

| **Note:** Some of @product@'s features may not be available due to what the 
| native frameworks expect for the underlying platform. For example, Angular 
| assumes that it controls a whole Single Page Application as opposed to the 
| small portion of the page that it controls in a portlet-based platform such as
| @product@. Also, because framework builds are typically based on webpack, 
| which bundles all JavaScript in a single file to consume, if, for example, you 
| deploy five portlets, you will have five copies of the framework in the 
| JavaScript interpreter. If this isn't what you intend, we recommend that you 
| [migrate your project](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends) to 
| a true Liferay JS Toolkit instead. 

Follow these steps to use the Liferay JS Generator to adapt your existing 
project:

1.  Open the command line and navigate to your project's folder.

2.  Run the Liferay JS Generator's `adapt` subtarget:

    ```bash
    yo liferay-js:adapt
    ```

    ![Figure 1: You can run the adapt subtarget of the Liferay JS Generator to adapt your existing apps for Liferay.](../../../images/liferay-js-generator-adapt-run.png)
  
3.  Answer the prompts. An example configuration is shown below:

    ```bash
    ? Under which category should your widget be listed? category.sample
    ? Do you have a local installation of Liferay for development? Yes
    ? Where is your local installation of Liferay placed? /home/user/liferay
    ```
    
    Your project is adapted to use the Liferay JS Toolkit and run on @product@! 

    ![Figure 2: You can run the adapt subtarget of the Liferay JS Generator to adapt your existing apps for Liferay.](../../../images/liferay-js-generator-adapt-complete.png)
    
4.  The adapt process automatically added a few npm scripts to the project's 
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
apps to run on @product@. See the [React Guestbook App](https://github.com/liferay/liferay-docs/tree/master/en/developer/reference/code/adapted-react-app/) 
for a working example of an adapted app. 

![Figure 3: Your adapted app runs in Liferay in no time.](../../../images/liferay-js-generator-adapt-deployed.png)