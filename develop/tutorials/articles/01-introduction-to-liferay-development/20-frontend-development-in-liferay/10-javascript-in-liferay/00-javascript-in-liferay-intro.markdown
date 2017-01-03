# JavaScript in @product@ [](id=javascript-in-liferay)

@product@'s front-end is extendable, flexible, and future ready.

Like previous versions, many components are written using [AlloyUI](http://alloyui.com).
AlloyUI is based on [YUI](http://yuilibrary.com/), and is no longer under active 
development. Because of this, we have included [jQuery](https://jquery.com) 
and also have developed a new framework called [MetalJS](http://metaljs.com).

## MetalJS [](id=metaljs)

Metal.js is a JavaScript library for building UI components in a solid and 
flexible way. Metal is built from the ground up with performance in mind and is
flexible enough to be built as global objects, AMD modules, or jQuery plugins.
Metal is cutting edge JavaScript, using [ECMAScript 6 (ES6)/ ECMAScript 2015 (ES2015)](http://www.ecma-international.org/ecma-262/6.0/), 
which provides you with clean code that's easy-to-read.

![Figure 1: Metal.js is a new framework for building UI components.](../../../../images/metaljs-website.png)

For more information see the [Metal.js docs](http://metaljs.com/docs/).

## ES2015 [](id=es2015)

[ECMAScript 6 (ES6)/ ECMAScript 2015 (ES2015)](http://www.ecma-international.org/ecma-262/6.0/) 
is enabled by default in your plugins, so you can write your own modules using 
the latest improvements to the language.

You can learn more about how to leverage ES6 in your modules in the 
[Preparing Your JavaScript Files for ES2015](/develop/tutorials/-/knowledge_base/7-0/preparing-your-javascript-files-for-es2015) 
and [Using ES2015 Modules in your Portlet](/develop/tutorials/-/knowledge_base/7-0/using-es2015-modules-in-your-portlet) 
tutorials.

## AlloyUI [](id=alloyui)

AlloyUI is an open source front-end framework built on top of 
[Yahoo! User Interface Library (YUI)](http://yuilibrary.com). It leverages all 
of YUI's modules and adds even more cutting edge components and features to help 
you build terrific UIs. AlloyUI provides the following key benefits:

- Create modern UI components that provide a consistent look & feel across 
  @product@.
- Server-agnostic, so you can use it with any technology.

As of @product-ver@, AlloyUI has been officially sunsetted. This means that we are 
no longer developing new features for it, but it is still included in the 
product and actively maintained.

![Figure 2: AlloyUI is sunsetted as of @product-ver@.](../../../../images/alloyui-website.png)

## jQuery [](id=jquery)

jQuery is a cross-platform JavaScript library designed to simplify the 
client-side scripting of HTML. It is the most popular JavaScript library in use 
today. The syntax is designed to make it easier to navigate a document, select 
DOM elements, create animations, handle events, and develop Ajax applications.

While jQuery is great for small websites, once you start creating highly 
scalable applications like Java portals, you'll need a more robust solution. 
That being the case, we strongly recommend you to use one of our other provided 
solutions mentioned above.

![Figure 3: jQuery is a fast, small, and feature-rich JavaScript library.](../../../../images/jquery-website.png)
