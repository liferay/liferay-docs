# JavaScript in @product@ [](id=javascript-in-liferay)

## What does JavaScript look like in @product@?

The frontend of @product@ is designed to be extendible, flexible, and future ready.

Like previos versions, many components are written using [AlloyUI](http://alloyui.com/). AlloyUI is based on [YUI](http://yuilibrary.com/), and is no longer under active development. Because of this we have included [jQuery](https://jquery.com/), and also have developed a new framework called [MetalJS](http://metaljs.com/).

## MetalJS [](id=metaljs)

[Metal.js](http://metaljs.com/docs/) is a JavaScript library for building UI components in a solid and flexible way. Metal is built from the ground up with performance in mind. Metal is flexible enough to be build as global objects, AMD modules, or jQuery plugins. By using [ECMAScript 6 (ES6)/ ECMAScript 2015 (ES2015)](http://www.ecma-international.org/ecma-262/6.0/), it has the benefit of easy-to-read and clean code as well as being on the cutting edge of JavaScript.

![Figure 1: Metal.js is a new framework for building UI components.](../../../images/metaljs-website.png)

## ES2015 [](id=es2015)

[ECMAScript 6 (ES6)/ ECMAScript 2015 (ES2015)](http://www.ecma-international.org/ecma-262/6.0/) is enabled and integrated by default in the out of the box build plugins, so you can write your own modules using the latest
improvements to the language.

You can learn more about how to leverage ES6 in your developments in the [Preparing Your JavaScript Files for ES2015](/develop/tutorials/-/knowledge_base/7-0/preparing-your-javascript-files-for-es2015) and [Using ES2015 Modules in your Portlet](/develop/tutorials/-/knowledge_base/7-0/using-es2015-modules-in-your-portlet) tutorials

## AlloyUI [](id=alloyui)

AlloyUI is an open source front-end framework built on top of [Yahoo! User Interface Library (YUI)](http://yuilibrary.com).
It leverages all of YUI's modules and adds even more cutting edge components and features to help you build terrific UIs.
* With AlloyUI you can create modern UI components that provide a consistent look & feel across Liferay Portal.
* It is also server-agnostic, which means that you can use it with any technology.

As of @product@, AlloyUI has been officially sunsetted. This means that we are no longer developing new features for it, but it is still included in the product and actively maintained.

![Figure 2: AlloyUI is sunsetted as of @product@.](../../../images/alloyui-website.png)

## jQuery [](id=jquery)

jQuery is a cross-platform JavaScript library designed to simplify the client-side scripting of HTML. It is the most popular JavaScript library in use today. The syntax is designed to make it easier to navigate a document, select DOM elements, create animations, handle events, and develop Ajax applications.

> While jQuery is great for small websites, once you start creating high scalable applications like Java portals you'll need a more robust solution.  That being the case, we strongly recommend you to use one of our other provided solutions.

![Figure 3: jQuery is a fast, small, and feature-rich JavaScript library.](../../../images/jquery-website.png)
