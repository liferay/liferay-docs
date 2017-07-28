# Metal.js [](id=metal-js)

Metal.js is a lightweight, easy-to-use JavaScript framework that lets you create
UI Components with ease, thanks to its integration with templating languages.

![Figure 1: You can create UI's easily, thanks to Metal.js.](../../../../images/metal-js-home-page.png)

Metal.js is built with you in mind, offering flexibility with how your rendering 
logic is handled. You can use template languages to write your rendering logic 
or keep your rendering logic and business logic within the same file if you 
prefer.

By default, Metal.js offers integration points with Google closure templates and 
Facebook JSX templates. The rendering layer is completely customizable though, 
so you can add more rendering options if needed.

Below is an example of a closure(Soy) template written for Metal.js:

    {template .render}
    // ...
    <button onClick="{$close}" type="button" class="close">
    // ...
    {/template}

Metal.js has two main classes: `State`, and `Component` which extends from
`State`.  The `Component` class adds additional rendering features for your
Component. If your Component doesn't require rendering, you can just use
`State`.

The figure below illustrates the architecture for Metal.js:

![Figure 2:](../../../../images/metal-js-architecture.png)

Metal.js takes full advantage of the [ECMAScript 6](http://www.ecma-international.org/ecma-262/6.0/)
(AKA ECMAScript 2015) language, so you can use the latest features that the
language has to offer. Below is a list of some of the great features that you
get with ES6:

- Class syntax like other OO languages. Classes support prototype-based 
  inheritance, super calls, instance and static methods and constructors.
- Arrow method syntax. `var odds = numbers.map(v => v + 1);`
- Language-level support for modules for Component definition. Codifies patterns 
  from popular JavaScript module loaders like AMD (as shown below):

        export function sum(x, y) {
          return x + y;
        }
        export var pi = 3.141593;

These are just a few of the ES6 features available that you can use in your
Metal.js components.

## Related Topics [](id=related-topics)

[Creating a Soy Portlet](/develop/tutorials/-/knowledge_base/7-0/creating-a-soy-portlet)

[JavaScript Module Loaders](/develop/tutorials/-/knowledge_base/7-0/javascript-module-loaders)
