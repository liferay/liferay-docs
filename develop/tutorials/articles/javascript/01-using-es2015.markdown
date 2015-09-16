# Preparing your JavaScript files for ES2015 [](id=preparing-your-javascript-files-for-es2015)

You can now write your JavaScript using the new ECMAScript 2015 syntax. In order
for the syntax to be recognized, you'll need to make some adjustments to your 
code. 

This tutorial covers how you can enable your JavaScript to use the new standard 
syntax and advanced features.

## Indicating your file uses ES2015 Syntax [](id=indicating-your-file-uses-es2015-syntax)

In order to use the new syntax you will need to add the `.es` extension to your
JavaScript file:

    filename.es.js
        
The `.es` extenstion indicates that this file uses the new ES2015 syntax and 
needs to be transpiled by [Babel](https://babeljs.io/) before being deployed. 
The `.es` extension allows you to use the standard syntax for ES2015, but in 
order to use the advanced features you will need to make an addition to your 
code.

## Using ES2015 Advanced Features in your Modules [](id=using-es2015-advanced-features-in-your-modules)

In order to use the advanced es2015 features, such as generators, you will need 
to import the `polyfill-babel` class. This class is exposed by another module 
that is included by default in Liferay 7, so you will just need to import it 
into your current module to use it:
    
    `import polyfillBabel from 'polyfill-babel'`

The Babel Polyfill emulates a full ES6 environment for you. Note that this is an 
advanced feature and should be used at your own discretion, as it loads a large 
amount of code. You can see what is polyfilled [here](https://github.com/zloirock/core-js#core-js) 
if you are unsure whether to import the module.

Once you've completed writing your module, you'll need to expose it so that it 
can be available to use in your other modules.

## Exposing Your Modules [](id=exposing-your-modules)

To expose your modules you just need to create a `bower.json` file with the name 
and version of your bundle, in the root folder. For example, the 
`frontend-js-numsequences-web` module uses the following code:

    {
        "name": "js-numsequences",
        "version": "1.0.0"
    }

The module Config generator will use this to generate your module names for you
to use later. There you have it! With just a few steps you can use the new 
JavaScript standard features in your code.

## Related Topics [](id=related-topics)

[Using ES2015 Modules in Your Portlet](/develop/tutorials/-/knowledge_base/7-0/using-es2015-modules-in-your-portlet)

[Using Third Party Libraries in Your JavaScript](/develop/tutorials/-/knowledge_base/7-0/using-third-party-libraries-in-your-javascript)

[Understanding Liferay's Module Framework](/develop/tutorials/-/knowledge_base/7-0/understanding-liferays-module-framework)
