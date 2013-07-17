# Designing User Interfaces with AlloyUI [](id=designing-user-interfaces-with-alloyui-liferay-portal-6-2-dev-guide-en)

---

![Note](../../images/tip-pen-paper.png) This chapter has still under
construction. 

---

Liferay's User Interface (UI) Team continually strives to provide stylish,
lightning-fast components that are extensible and built to last. I liken the
team to an elite band of metallurgists: cutting-edge scientists that extract
metals from craggy rocks deep beneath the earth's crust. They purify these
metals and mix them together to make some of the most awesome things known to
mankind--seemingly weightless bike frames, bridges with enough tensile strength
to withstand huricanes, and swords that can cut through almost anything. The
Liferay UI Team is like these scientists. They've put forth equal sweat and
ingenuity to bring you a mixture, or *alloy*, of the best UI technologies and
have wrapped it up in one ultimate framework--AlloyUI! 

AlloyUI gives you skinnable, scalable UI components, so you can provide a
consistent look and feel for your application. It's a framework containing 
JavaScript extensions to Yahoo UI (YUI) that leverages all of YUI's modules and
adds its own components to help you build terrific UIs. AlloyUI also
incorporates Twitter Bootstrap to make styling components a snap. 

AlloyUI was built on YUI for several reasons. First, YUI facilitates building
high quality production-level widgets quickly. YUI has a flexible, elegant
architecture that is easy to extend. It is useful in both small and large scale
projects. Lastly, YUI is documented well at
[http://yuilibrary.com/yui/docs/](http://yuilibrary.com/yui/docs/). As you use
YUI through AlloyUI, you'll realize these benefits. 

---

![note](../../images/tip-pen-paper.png) Given the popularity of jQuery,
you may be wondering why Liferay chose to build on YUI instead of jQuery.
The most important reason for using YUI over jQuery is that YUI performs so much
better. You'll see for yourself as you use AlloyUI in Liferay Portal. 

<!-- Do you have a source for this? We should footnote a benchmark here. If we
don't have a benchmark to cite, we should remove this sidebar. As it is, we
shouldn't have a sidebar in the introduction if we can help it. -Rich
-->
 
You can use jQuery in Liferay Portal, but we strongly recommend you use AlloyUI.
AlloyUI is always loaded and available to you. If you use something else, your
page must load your library and AlloyUI, which slows down performance. 

---

By reading this chapter and following along with its exercises, you'll learn
what AlloyUI is and how to use it in Liferay Portal. For further details on the
AlloyUI project, tutorials, examples, and API documentation, be sure to visit
[http://alloyui.com](http://alloyui.com). We'll show you around AlloyUI in this
chapter by exploring the following topics: 

- A simple AlloyUI example
- Working with the AlloyUI project

To start things off right, let's go over a simple example using AlloyUI.

## A simple AlloyUI example [](id=simple-alloyui-example-liferay-portal-6-2-dev-guide-en)

AlloyUI is easy to use. Let's demonstrate by using Alloy's `node` module in an
HTML file.

1. Create an HTML page like this:

        <!DOCTYPE html>

			<head>
              <link href="http://cdn.alloyui.com/2.0.0pr5/aui-css/css/bootstrap.css"
                  rel="stylesheet">
	
                <script src="http://cdn.alloyui.com/2.0.0pr5/aui/aui-min.js" />
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

2. Navigate to this HTML file in your browser.
3. Click *Try me now!*

The button transitions from its original width to 500 pixels, as specified in
your script.

![Figure 11.x: It's easy to set and reset dimensions of AlloyUI components, such as this `aui-btn` component. Clicking the *Try me now!* button on our page, triggers our function to expand the button's width to 500 pixels.](../../images/alloyui-simple-example-try-me.png)

Let's look at how we did this with AlloyUI. First we added HTML that
displays a button using AlloyUI's `aui-btn` module.

Then we used a script element to reference Alloy's seed file, `aui-min.js`
from a content delivery network (CDN). The seed file includes the bare minimum
core code required for AlloyUI. Any additional code is loaded dynamically by
YUI.

    <script src="http://cdn.alloyui.com/2.0.0pr5/aui/aui-min.js"></script>

---

 ![note](../../images/tip-pen-paper.png) **Note:** For performance reasons, it
 is almost always best to reference the seed file from the CDN rather than from
 a designated server. On receiving the request for the seed file, the CDN
 returns it from the nearest server on the CDN, minimizing latency time.

---

Lastly, our script selects the first element of class `aui-btn` found on the
page and sets a callback to change its width to 500 pixels when it is clicked.
How's that for dynamic content!

This script uses YUI and AlloyUI in what is commonly referred to as a "sandbox."
Code is sandboxed when elements of the code are set off in their own namespaces.
Why do this? Because JavaScript, like many programming languages, has both a
local and a global scope. Code placed in a JavaScript function is locally
scoped, which means that nothing inside that function can be seen outside that
function. Another way to describe this code is that it has been sandboxed. 

This reminds me of the countless hours I spent as a child using my die-cast
metal toy tractor to plant imaginary crops in my toy sandbox. It was a wonderful
place to let my imagination go wild and grow acres and acres of fictitious corn
fields.

The UI sandbox is similar to a toy sandbox--but safer and perhaps more fun.
Unlike my childhood sandbox that was inevitably raided by friends, siblings,
and my dog, your UI sandbox avoids namespace clashes with code in other
sandboxes on your page.

In the example above, the sandbox is the callback where you run your code. It
follows this format:

`YUI().use(`*[package 1, ...  package n,]*` function(A) { // Your code goes here });`

`YUI().use()` is a function call that instantiates modules for you to use. As
parameters, you pass in packages and a function containing your code. The
example code required YUI's `event` and `node` packages. The final argument
is the AlloyUI object as parameter `A` (you could use any other value--YUI
uses `Y`). Alloy's classes are stored in the `A` object. In this function, you 
place presentation logic, leveraging AlloyUI's API via its mighty `A` object.
We'll get into more details on the API shortly.

Now that we've dissected the example, let's get it working in a portlet.
First, specify the AlloyUI's taglib as a dependency in the
`liferay-plugin-package.properties` for the portlet's project. You can specify
this dependency via Design view of the file in Liferay IDE, or you can open
the file and add the following assignment:

<!-- TODO Add screenshot of Liferay IDE's Design view -->

    portal-dependency-tlds=aui.tld

Then add the code for the button and the Alloy script to your portlet's JSP.
This time, however, replace the `<script>` tag with the `<aui:script>` tag.
The `<aui:script>` tag combines the contents of all `<aui:script>` tags used on
a page into one script block at the bottom of the page, and it wraps the
functions in a `YUI.use()` call to bring in necessary module dependencies. 

Another convenience you get by using the tag is there's no explicit reference to
AlloyUI's seed file. With all these simplifications, the AlloyUI code in your
JSP looks like this:

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

Voila! You're using AlloyUI in Liferay!

Let's go over setting up the AlloyUI project for creating your own AlloyUI
components. 

## Working with the AlloyUI project [](id=working-with-the-alloyui-project-liferay-portal-6-2-dev-guide-en)

Liferay Portal comes bundled with AlloyUI, as it's used throughout the portal
and core portlets. Conveniently, you can use AlloyUI in any project--it doesn't
have to run on Liferay. When you develop AlloyUI scripts and components for use
in portal, you can reuse them anywhere else. If you're using AlloyUI outside
Liferay, you might want to build it yourself. 

Here are some other reasons why you might use a local AlloyUI installation or
AlloyUI project build:

- Creating and testing your own AlloyUI component modules 
- Using the latest AlloyUI project source code that is not yet released 
- Using AlloyUI on a closed network 
- Contributing and testing a fix or enhancement to AlloyUI 

Let's download AlloyUI and set it up for developing AlloyUI scripts and
components locally. 

<!-- I've been removing all references to a local web server, because AFAIK, you
don't need one. The file system is fine. -Rich -->

You can download AlloyUI as a `.zip` file from
[http://alloyui.com](http://alloyui.com). The file contains the following files
and folders: 

- `alloy-[version]/` - AlloyUI project root directory 
    - `build/` - Contains the AlloyUI and YUI modules used in Liferay 
    - `demos/` - Contains basic examples of the AlloyUI components 
    - `src/` - Contains the source code of the AlloyUI modules 
    - `.alloy.json` - Specifies how to build the modules 
    - `.shifter.json` - Specifies additional tasks used to build AlloyUI 
    - `LICENSE.md` - Defines AlloyUI's the license agreement 
    - `README.md` - Explains the AlloyUI project 

If you'd rather build AlloyUI from the its latest source code, go to the
project's repository at
[https://github.com/liferay/alloy-ui](https://github.com/liferay/alloy-ui),
fork the repository, and clone it to your machine. Whether you're using AlloyUI
pre-built from the `.zip` file or cloned from the repository on Github,
AlloyUI's modules are available in the `alloy-[version]/build/` folder. You can
access these modules from your local web pages. 

<!-- Consider adding back the following tip
 ![tip](../../images/tip-pen-paper.png) **Tip:** You may find it
 convenient to extract the AlloyUI installation into your web server installation, or
 create symbolic links from your AlloyUI installation to your web server. 
- Jim -->

As you did in the initial example, the first thing you'll call is AlloyUI's
`aui-min.js` seed file, in your `alloy-[version]/build/aui/` folder. For
example, if your AlloyUI project root directory is
`/home/joe.bloggs/alloy-2.0.0/`, you'll refer to the seed file like this: 

    <script src="/home/joe.bloggs/alloy-2.0.0/build/aui/aui-min.js"></script>

Now that you've specified your local seed file, you can use AlloyUI throughout
your web page. 

<!-- I think this section needs to be reorganized, as it jumps around and gets
confusing. We should start one thing, then finish it, then start the next thing.
One possible order of steps we could use would be this: 

Using AlloyUI Outside Liferay
1. Download AlloyUI. 
2. Unzip and extract
3. Use in web page. 

Working with the AlloyUI Project
1. Download AlloyUI source. 
2. Build AlloyUI locally. 
3. Unzip and extract locally built distribution. 
4. Use in web page. 

I also removed the link to the specific version in these URLs and made the links
go directly to the project. -Rich
-->

Before we go on to creating your own AlloyUI components, let's build AlloyUI. If
you are not interested, feel free to skip these build instructions. 

If you've cloned the `alloy-ui` project repository, make sure to check out
its`2.0.x` branch. Liferay Portal 6.2 requires AlloyUI 2.0. 

AlloyUI 2.0 uses Yogi Alloy
([https://github.com/liferay/yogi-alloy](https://github.com/liferay/yogi-alloy))
to build its modules. For instructions on installing Yogi Alloy and its
dependencies, follow the instructions in Yogi Alloy's `README.md` file at
[https://github.com/liferay/yogi-alloy/blob/master/README.md](https://github.com/liferay/yogi-alloy/blob/master/README.md).

Yogi Alloy depends on the following:
- [Node.js](nodejs.org) v0.8 Node.js is platform for building applications. Yogi
Alloy requires Node.js version 0.8.
- [Compass](http://compass-style.org) is an open-source CSS authoring framework.

Node.js version 0.9 is available at [http://nodejs.org/dist/v0.8.2/](http://nodejs.org/dist/v0.8.2/).

On Windows you can download the `.msi` installation file and run it.

On Linux, MacOS, or UNIX, you can download the `.tar.gz` file, unzip it, and
un-tar it. Then follow the instructions in its `README.md` file to build it. 

To install Compass, follow its installation instructions at
[http://compass-style.org/install/](http://compass-style.org/install/).

Now that you've installed Yogi Alloy's dependencies, you can use the Node.js
package manager (`npm`) to install Yogi Alloy. Execute the following command:  

    npm -g install yogi yogi-alloy yuidocjs docpad shifter

To build the AlloyUI project and its dependencies, execute: 

    ya init

The Yogi Alloy target prompts you before building different parts of the AlloyUI
project. Upon completion of the target, Yogi Alloy reports `.yogi [success]
done.` 

Congratulations on building AlloyUI! Now it's time to create your own UI
components in AlloyUI. 

## Summary

In this chapter, we've only scratched the surface of showing you what AlloyUI
has to offer you in designing user interfaces in Liferay. We've introduced you
to AlloyUI to show you what it is and to explain how it integrates so well with
Liferay. We've provided a simple example that demonstrates using AlloyUI
components and we've shown you how to set up the AlloyUI project environment so
that you can build your own AlloyUI components. You should visit AlloyUI's
official website, [http://alloyui.com/](http://alloyui.com/), regularly to get
the latest information on the AlloyUI framework. But, we'll continue to add
examples to this chapter to demonstrate more ways you can use AlloyUI in your
portal. So, make sure to periodically check back with this guide. 

Now that you know how to use AlloyUI components to build snazzy UIs, why not
boost your portal's social appeal with OpenSocial gadgets? In the next chapter,
we'll show you how to incorporate OpenSocial gadgets in your portal. 

