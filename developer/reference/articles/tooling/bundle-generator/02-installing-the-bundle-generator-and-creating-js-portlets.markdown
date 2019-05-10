---
header-id: installing-the-bundle-generator-and-generating-a-bundle
---

# Installing the Bundle Generator and Generating a Bundle

[TOC levels=1-4]

This article shows how to install the 
[Liferay Bundle Generator](https://www.npmjs.com/package/generator-liferay-bundle) 
and how to use it to create a new JavaScript portlets. See the 
[Angular widget](/docs/7-2/appdev/-/knowledge_base/a/angular-widget), 
[React widget](/docs/7-2/appdev/-/knowledge_base/a/react-widget), 
and 
[Vue widget](/docs/7-2/appdev/-/knowledge_base/a/vue-widget) 
articles to learn how to use your existing Angular, React, and Vue apps in 
@product@. 

| **Note:** To use the Liferay Bundle Generator, you must have the Liferay JS 
| Portlet Extender activated in your @product@ instance. It's activated by 
| default. You can confirm this by opening the Control Menu, navigating to the 
| *App Manager*, and searching for `com.liferay.frontend.js.portlet.extender`.

Follow these steps to create your JavaScript portlet:

1.  Install 
    [Node.js](http://nodejs.org/). 
    Note that Node Package Manager (npm) is installed with this as well. You’ll 
    use npm to install the remaining dependencies and generator, and 
    [configure your npm environment](/docs/7-2/reference/-/knowledge_base/r/setting-up-your-npm-environment).

2.  Install [Yeoman](http://yeoman.io/) for the generator:

        npm install -g yeoman

3.  Install the Liferay Bundle Generator:

        npm install -g generator-liferay-bundle

4.  Run the generator with the command below, select the JavaScript portlet you 
    want to create, and answer the prompts that follow.

        yo liferay-bundle

    ![Figure 1: The liferay-bundle generator prompts you for portlet options.](../../../images/liferay-bundle-generator-prompts.png)

5.  If you specified your app server information when your portlet was 
    generated, you can deploy your portlet by running the command below. You can 
    verify this by checking the value of the `liferayDir` entry in the portlet's 
    `.npmbuildrc`.

        npm run deploy

Great! Now you know how to install and run the Liferay Bundle Generator. 
