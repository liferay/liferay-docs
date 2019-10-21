---
header-id: adapting-existing-apps-to-run-on-product
---

# Adapting Existing Apps to Run on @product@

[TOC levels=1-4]

Since [#347](https://github.com/liferay/liferay-js-toolkit/issues/347), you can 
create projects with your favorites tools, such as [create-react-app](https://facebook.github.io/create-react-app/), 
and adapt them to use the Liferay JS Toolkit and run on @product@. This lets you 
use [JavaScript portlet features](/docs/7-2/frameworks/-/knowledge_base/f/creating-and-bundling-javascript-widgets-with-javascript-tooling) 
in your app. 

| **Note:** At the moment, only [`create-react-app`](https://facebook.github.io/create-react-app/) 
| projects are supported. Angular CLI and Vue CLI based projects will be 
| supported in the near future.

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

    ![Figure 3: Your adapted app runs in Liferay in no time.](../../../images/liferay-js-generator-adapt-deployed.png)

Great! Now you know how to use the Liferay JS Generator to adapt your existing 
apps to run on @product@.