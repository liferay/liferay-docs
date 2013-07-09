# Designing User Interfaces with AlloyUI

Liferay's User Interface (UI) Team continually strives to provide the most
stylish lightning-fast components that are extensible and built to last. I liken
the team to an elite band of metallurgists--you know, those cutting edge
scientists that extract metals from craggy rocks found deep beneath the earth's
crust. They purify these metals and mix them together to make some of the most
awesome things known to mankind--bike frames that are seemingly weightless,
bridges that have enough tensile strength to withstand huricanes, and swords
that can cut through almost anything. The Liferay UI Team is no different from
these scientists. They've put forth equal sweat and ingenuity to bring you a
mixture, or *alloy*, of the best UI technologies and wrap it in one ultimate
framework--AlloyUI! 

With AlloyUI you can create highly scalable UI components that provide a
consistent look and feel across Liferay Portal. AlloyUI is a framework that
contains a set of JavaScript extensions to Yahoo UI (YUI). It leverages all of
YUI's modules and adds even more cutting edge components and features to help
you build terrific UIs. AlloyUI also incorporates Twitter Bootstrap for you to
use its HTML features and powerful cascading style sheets (CSS) capabilities. 

AlloyUI was built on YUI for several reasons. Foremostly, YUI facilitates
building high quality production-level widgets quickly. YUI has an elegant
architecture that is very flexible and easy to extend. It lends itself well for
using in small scale projects as well as large scale projects. Lastly, YUI is
documented well at
[http://yuilibrary.com/yui/docs/](http://yuilibrary.com/yui/docs/). As you use
AlloyUI on top of YUI, you'll realize these benefits. 

---

 ![note](../../images/tip-pen-paper.png) Given the popularity of jQuery,
 you may be wondering why Liferay chose to build on YUI instead of jQuery.
 We've told you about YUI's benefits, but the most important reason for using
 YUI over jQuery is that YUI performs so much better. You'll see for yourself as
 you use AlloyUI in Liferay Portal.  
 
 Note, you can use jQuery in Liferay Portal, but we strongly recommend you use
 AlloyUI. AlloyUI is always loaded and available to you. If you use another
 library, then your page will load more slowly as it will still load AlloyUI and
 also need to load your other library. 

---

For consistent powerful styling, AlloyUI includes Twitter Bootstrap. If you've
been working with AlloyUI, you're probably already leveraging the Sass extension
to CSS. Great! Even though Twitter Bootstrap uses the Less extension, AlloyUI
still uses Sass. Its framework leverages a 1-to-1 mapping of LESS to Sass so you
can continue using Sass. How's that for convenience! 

By reading this chapter and following along with its exercises, you'll learn
what AlloyUI is and how to use it in Liferay Portal. Consider it a sampling of
what AlloyUI has to offer. For complete details on the AlloyUI project,
tutorials, examples, and API documentation, be sure to visit
[http://alloyui.com](http://alloyui.com). But we'll start showing you around
AlloyUI in this chapter, by exploring the following topics: 

- Traversing the DOM with AlloyUI
- Using AlloyUI taglibs
- AlloyUI's extensions to the YUI language
- Styling your components with Twitter Bootstrap
- Creating your own UI component modules

To start things off right, let's go over a simple example using AlloyUI.

## A simple AlloyUI example

AlloyUI is easy to use in your JavaScript and HTML anywhere on your web server.
Let's demonstrate by using Alloy's `node` module in an HTML file.

1. Create an HTML page like this:

    <!DOCTYPE html>

    <head>
      <link href="http://cdn.alloyui.com/2.0.0pr5/aui-css/css/bootstrap.css" rel="stylesheet">

      <script src="http://cdn.alloyui.com/2.0.0pr5/aui/aui-min.js"></script>
    </head>

    <body>
      <button id="try-btn" class="btn" type="button">
      Try me now!
      </button>

      <script>
        AUI().use(
          'node',
          'transition',
          function(A) {
            A.one('.btn').on(
              'click', function() {
                this.transition(
                  {
                    width: '500px'
                  }
                );
              }
            );
          }
        );
      </script>
    </body>

    </html>

2. Navigate to this HTML on your web server in your browser.
3. Click "Try me now!"

The button transitions from its original width to 500 pixels, as specified in
your script.

<!-- TODO Add screenshot of demo -->

Let's take a look at how we did this with AlloyUI. First we added our HTML -
that displays a button of AlloyUI's `aui-btn` module.

Then, we used a script element to reference Alloy's "seed" file, `aui-min.js`
from a content delivery network (CDN). The seed file includes the bare minimum core code required for AlloyUI. Any
additional code is loaded dynamically by YUI.

    <script src="http://cdn.alloyui.com/2.0.0pr5/aui/aui-min.js"></script>

---

 ![note](../../images/tip-pen-paper.png) **Note:** For performance reasons, it
 is almost always best to reference the seed file from the CDN rather than from
 a designated server. On receiving the request for the seed file, the CDN
 returns it from the nearest server on the CDN, minimizing latency time.

---

Lastly, our script selects the first element of class `aui-btn` found on the
page and sets a callback to set its width to 500 pixels when it is clicked.
How's that for dynamic content!

Let's take a closer look at this script. It uses YUI and AlloyUI in what is
commonly referred to as a "sandbox". Sandbox is a pattern in which the content
of one sandbox stays separate from that of other sandboxes.

I've entertained the analogy, thinking of the countless hours I spent as a child
using my die-cast metal toy tractor to plant imaginary crops in my toy sandbox.
It was wonderful place to let my imagination go wild and grow acres and acres of
fictitious corn fields.

The UI sandbox is similar to a toy sandbox, but safer, and perhaps more fun.
Unlike my childhood sandbox, that inevitably was raided by friends, siblings,
and my dog, your UI sandbox keeps your code safe from namespace clashes with
code in other sandboxes on your page.

The sandbox is simply a callback where you run your code. If follows this
format:

`YUI().use(`*[package 1, ...  package n,]*` function(A) { // Your code goes here });`

`YUI()` is a function call onto which you attach `.use(...)` to use your code.
As parameters, you pass in packages required by your code and finally a function
containing your code. Our example code required YUI's `event` and `node`
packages. As our final argument, we passed in our function with *the* Alloy
object as parameter `A` (we could name it with any other value, e.g. YUI uses `Y`).
Alloy's classes are stored in the `A` object. Within our function, we perform
our presentation logic, leveraging Alloy's API via its mighty `A` object. We'll
get into more details on the API shortly.

Now that we've disected our example, let's get it working in a portlet.
First, specify the AlloyUI's taglib as a dependency in the
`liferay-plugin-package.properties` for the portlet's project. You can specify
this dependency via *Design* view of the file in Liferay IDE. Or you could open
the file and add the following assignment:

<!-- TODO Add screenshot of Liferay IDE's Design view -->

    portal-dependency-tlds=aui.tld

Then, add the code for the button and the Alloy script to your portlet's JSP.
But, this time, we'll replace the `<script>` tag with the `<aui:script>` tag.
The `<aui:script>` tag combines all of the `<aui:script>` tag's used on a page
into one script block at the bottom of the page and it wraps the functions in an
`YUI.use()` call to bring in necessary modules if modules are passed in.

Without the need for the an explicit reference to AlloyUI's seed file and with
the replacement of the `<script>` tags with the `<aui:script>` tags the Alloy
code in your JSP looks like the following:


    <button id="try-btn" class="aui-btn" type="button">Try me now!</button>

    <aui:script>
    YUI().use(
      'node',
      'transition',
      function (A) {
        A.one('.aui-btn').on(
          'click', function() {
            this.transition(
              {
                width: '500px'
              }
            );
          }
        );
      }
    );
    </aui:script>

Voila! You're using AlloyUI in Liferay!!

We'll give you plenty of practice using AlloyUI as we teach you its
fundamentals throughout this chapter. Next we're going to give you a real workout
by taking you through an exercise in traversing a document object model (DOM).

## Traversing the DOM with AlloyUI

<!-- insert content -->

- Select Node based on element ID
A.one('#someId')

- Set Node's value
shippingFirstnameNode.val(billingFirstnameNode.val() );

<!-- insert transition -->

## Using AlloyUI taglibs

<!-- insert content -->

<!-- insert transition -->

## Styling your components with Twitter Bootstrap

<!-- insert content -->

<!-- insert transition -->

## Creating your own UI component modules

<!-- Explain the benefits of building UI components in the AlloyUI project -->

Before we create components, let's go over setting up the AlloyUI project.  

### Working with the AlloyUI project

Liferay Portal comes with AlloyUI packaged in it, as it uses it throughout the
portal and core portlets. Conveniently, you're not restricted to using AlloyUI
in the portal; you can use it on any web server. As you develop AlloyUI scripts
and components for use in portal, you can simply try them out on a simple web
server on your machine.

Here are some other cases that warrant using a local AlloyUI installation or
AlloyUI project build:

- Creating and testing your own new AlloyUI component modules. 
- Using the latest AlloyUI project source code that is not yet released. 
- Using AlloyUI on a closed network. 
- Contributing and testing a fix or enhancement to AlloyUI. 

Let's download AlloyUI and set it up for developing AlloyUI scripts and
components on a local web server. 

You can download AlloyUI as a `.zip` file from
[http://alloyui.com](http://alloyui.com). The file contains the following: 

- `alloy-[version]/` - AlloyUI project root directory. 
    - `build/` - Contains the AlloyUI and YUI modules used in Liferay. 
    - `demos/` - Contains basic examples of the AlloyUI components. 
    - `src/` - Contains the source code of the AlloyUI modules. 
    - `.alloy.json` - Specifies how to build the modules. 
    - `.shifter.json` - Specifies additional tasks used to build AlloyUI. 
    - `LICENSE.md` - Defines AlloyUI's the license agreement. 
    - `README.md` - Explains the AlloyUI project. 

If you'd rather build AlloyUI from the its latest source code, go to the
project's repository at
[https://github.com/liferay/alloy-ui/tree/2.0.x](https://github.com/liferay/alloy-ui/tree/2.0.x),
fork the repository, and clone it to your machine. We'll show you how to build
it shortly. Regardless of whether you're using AlloyUI pre-built from the `.zip`
file or cloned from the repository on Github, AlloyUI's modules are available in
the `alloy-[version]/build/` folder. You can access these modules from web pages deployed on
your local web server. 

---

 ![tip](../../images/tip-pen-paper.png) **Tip:** You may find it
 convenient to extract the AlloyUI installation into your web server installation, or
 create symbolic links from your AlloyUI installation to your web server. 

---

As we did in our initial example, we'll refer to AlloyUI's `aui-min.js` seed
file, found in your `alloy-[version]/build/aui/` folder. For example, if your
AlloyUI project root directory is `/home/joe.bloggs/alloy-2.0.0/`, you'll refer
to the seed file like this: 

    <script src="/home/joe.bloggs/alloy-2.0.0/build/aui/aui-min.js"></script>

Now that you've specified your local seed file, you can use AlloyUI throughout
your web page, like you've been doing throughout this chapter, only this time
you'll be using your local AlloyUI installation. 

Before we go on to creating your own AlloyUI components, let's build AlloyUI. If
you are not interested, feel free to skip these build instructions. 

As we mentioned earlier, the AlloyUI project repository is available at
[https://github.com/liferay/alloy-ui/tree/2.0.x](https://github.com/liferay/alloy-ui/tree/2.0.x).
You can fork the repository and clone it to your local machine. 

AlloyUI uses Yogi Alloy
([https://github.com/liferay/yogi-alloy](https://github.com/liferay/yogi-alloy))
to build its modules. For instructions on installing Yogi Alloy and its
dependencies, follow the instructions in Yogi Alloy's `README.md` file at
[https://github.com/liferay/yogi-alloy/blob/master/README.md](https://github.com/liferay/yogi-alloy/blob/master/README.md).

To build all of the AlloyUI project and its dependencies, simply execute: 

    ya init

The Yogi Alloy target prompts you before building different parts of the AlloyUI
project. Upon completion of the target, Yogi Alloy reports `.yogi [success]
done.` 

Congratulations on building AlloyUI! For instructions on building AlloyUI, visit 
[https://github.com/liferay/yogi-alloy#alloyui](https://github.com/liferay/yogi-alloy#alloyui).
Now it's time to create your own UI components in AlloyUI. 

### Creating components is easy with AlloyUI

<!-- Explain how to create components and modules in AlloyUI -->

## Summary

