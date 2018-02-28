# Preparing Your JavaScript Files for ES2015+ [](id=preparing-your-javascript-files-for-esplus)

To use the ES2015+ syntax in a JavaScript file, add the extension `.es` to its
name. For example, you rename file `filename.js` to `filename.es.js`. The
extension indicates it uses ES2015+ syntax and must therefore be transpiled by
[Babel](https://babeljs.io/) before deployment.

ES2015+ advanced features, such as
[generators](https://babeljs.io/docs/learn-es2015/#generators), 
are available to you if you import the `polyfillBabel` class from the 
`polyfill-babel` module:

    import polyfillBabel from 'polyfill-babel'

The 
[Babel Polyfill](http://babeljs.io/docs/usage/polyfill/) 
emulates a complete ES6 environment. Use it at your own discretion, as it loads 
a large amount of code. You can inspect 
[https://github.com/zloirock/core-js#core-js](https://github.com/zloirock/core-js#core-js) 
to see what's polyfilled.

Once you've completed writing your module, you can expose it by creating a 
`package.json` file that specifies your bundle's name and version. Make sure to 
create this in your module's root folder. Below is an example `package.json` 
file for a `js-logger` module:

    {
        "name": "js-logger",
        "version": "1.0.0"
    }

The Module Config Generator creates the module based on this information. There 
you have it! In just a few steps you can prepare your module to leverage the 
latest JavaScript standard features and publish it.

## Related Topics [](id=related-topics)

[Using ES2015+ Modules in Your Portlet](/develop/tutorials/-/knowledge_base/7-1/using-esplus-modules-in-your-portlet)
