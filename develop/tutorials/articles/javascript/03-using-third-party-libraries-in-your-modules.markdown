# Using Third Party Libraries in JavaScript

In addition to being able to use the new es2015 JavaScript syntax in your code, 
you can now also use your favorite third party moduless using 
[Bower](http://bower.io/). This tutorial covers how to use a third party module 
in your JSPs and JavaScript.

Install your third party module as you normally would. Once your module has been
installed you can configure your `bower.json` file next.

## Configuring your bower.json

Bower installs packages using a `bower.json` file, so to use third party modules 
in your code you just need to save the module as a dependency in the `bower.json` 
file for the bundle. For example, the Sequences Explorer Portlet uses
[Dragula.js](http://bevacqua.github.io/dragula/) to allow users to drag and drop 
table entries. The dragula.js module is included as as dependency in the 
`bower.json`:

    {
        "name": "sequences-explorer",
        "version": "1.0.0",
        "dependencies": {
            "dragula.js": "~2.1.2"
        }
    }

Once you've saved it as a dependency, you can import it as you would any other 
module, and use it. Here is a code fragment from the Sequences Explorer Portlet:

    import dragula from '../dragula.min';
    
Once the module has been imported, the Sequences Explorer Portlet applies it to
the `<tbody>` element of the table:

    dragula([this.container.querySelector('tbody')]);
    
If you test out the portlet, you'll see that you are able to drag and drop the
table elements with ease. 

As you can see you can use your favorite third party modules in no time. This
shows the true expansive power that Liferay 7 offers you as a developer.  
