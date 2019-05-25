---
header-id: installing-the-bundle-generator-and-generating-a-bundle
---

# Installing the Bundle Generator and Generating a Bundle

[TOC levels=1-4]

This tutorial shows how to install the 
[Liferay JS Generator](https://www.npmjs.com/package/generator-liferay-js) 
and how to use it to create JavaScript widgets. 

| **Note:** The Liferay Bundle Generator is deprecated as of v2.7.1 of the
| [Liferay JS Toolkit](https://github.com/liferay/liferay-js-toolkit). It has been
| renamed the Liferay JS Generator. If you're still running the Liferay Bundle
| Generator, we recommend that you install the Liferay JS Generator instead at
| your earliest convenience, as the Liferay Bundle Generator will be removed in
| future versions.

| **Important:** To use the Liferay JS Generator, you must have the Liferay JS
| Portlet Extender installed in your @product@ instance. The JS Portlet Extender
| is a Labs application available from Liferay Marketplace for
| [Liferay Digital Enterprise 7.1](https://web.liferay.com/marketplace/-/mp/application/115543020)
| and
| [Liferay Portal CE 7.1](https://web.liferay.com/marketplace/-/mp/application/115542926).
| Apps designated as Labs are experimental and not supported by Liferay. They're
| released to accelerate the availability of useful and cutting-edge features.
| This status may change without notice. Please download and use Labs apps at your
| own discretion.

Follow these steps to create your JavaScript widget:

1.  Install 
    [Node.js](http://nodejs.org/). 
    Note that Node Package Manager (npm) is installed with this as well. You’ll 
    use npm to install the remaining dependencies and generator, and 
    [configure your npm environment](/docs/7-1/reference/-/knowledge_base/r/setting-up-your-npm-environment).

2.  Install [Yeoman](http://yeoman.io/) for the generator:

        npm install -g yeoman

3.  Install the Liferay JS Generator:

        npm install -g generator-liferay-js

4.  Run the generator with the command below, select the JavaScript widget you 
    want to create, and answer the prompts that follow.

        yo liferay-js

    ![Figure 1: The liferay-bundle generator prompts you for widget options.](../../../images/liferay-js-generator-prompts.png)

5.  If you specified your app server information when your widget was 
    generated, you can deploy your widget by running the command below. You can 
    verify this by checking the value of the `liferayDir` entry in the widget's 
    `.npmbuildrc`.

        npm run deploy

Great! Now you know how to install and run the Liferay JS Generator. 

## Related Topics

[liferay-npm-bundler](/docs/7-1/reference/-/knowledge_base/r/liferay-npm-bundler)

[Using npm in Your Portlets](/docs/7-1/tutorials/-/knowledge_base/t/using-npm-in-your-portlets)

[Applying Clay Styles to Your App](/docs/7-1/tutorials/-/knowledge_base/t/applying-clay-styles-to-your-app)
