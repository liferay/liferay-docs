---
header-id: installing-the-js-generator-and-generating-a-bundle
---

# Installing the JS Generator and Generating a Bundle

[TOC levels=1-4]

Here you'll learn how to install the 
[Liferay JS Generator](https://www.npmjs.com/package/generator-liferay-bundle) 
and use it to create JavaScript widgets. See the 
[Angular Application](/docs/7-2/appdev/-/knowledge_base/a/developing-an-angular-application), 
[React Application](/docs/7-2/appdev/-/knowledge_base/a/developing-a-react-application), 
and 
[Vue Application](/docs/7-2/appdev/-/knowledge_base/a/developing-a-vue-application) 
articles to learn how to use your existing Angular, React, and Vue apps in 
@product@. 

| **Note:** To use the Liferay JS Generator, you must have the Liferay JS 
| Portlet Extender activated in your @product@ instance. It's activated by 
| default. You can confirm this by opening the Control Menu, navigating to the 
| *App Manager*, and searching for `com.liferay.frontend.js.portlet.extender`.

Follow these steps to create your JavaScript widget:

1.  Install 
    [Node.js](http://nodejs.org/). 
    Note that Node Package Manager (npm) is installed with this as well. You'll 
    use npm to install the remaining dependencies and generator, and 
    [configure your npm environment](/docs/7-2/reference/-/knowledge_base/r/setting-up-your-npm-environment).

2.  Install [Yeoman](http://yeoman.io/) for the generator:

    ```bash
    npm install -g yeoman
    ```

3.  Install the Liferay JS Generator:

    ```bash
    npm install -g generator-liferay-js
    ```

4.  Run the generator with the command below, select the JavaScript widget you 
    want to create, and answer the prompts that follow.

    ```bash
    yo liferay-js
    ```

    ![Figure 1: The liferay-js generator prompts you for widget options.](../../../images/liferay-js-generator-prompts.png)

5.  If you specified your app server information when your widget was 
    generated, you can deploy your widget by running the command below. You can 
    verify this by checking the value of the `liferayDir` entry in the widget's 
    `.npmbuildrc`.

    ```bash
    npm run deploy
    ```

Great! Now you know how to install and run the Liferay JS Generator. 
