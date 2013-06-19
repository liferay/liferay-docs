# Designing User Interfaces with AlloyUI

AlloyUI is a framework for building high performing user interface components.
Liferay uses AlloyUI to provide a consistent look and feel across Liferay
Portal. Liferay is build on Yahoo UI (YUI), leveraging all of its capabilities
and extending it with even more cutting edge components and features to help you
build terrific user interfaces (UIs).

One of the reasons why AlloyUI was built on YUI is because YUI allows you to
build quality production level widgets quickly. AlloyUI is easy to use on small
scale projects as well as large scale projects. Since AlloyUI includes all of
YUI, YUI's documentation applies to AlloyUI as well.

<!-- TODO List what we'll cover in this chapter

- Introduce AlloyUI
- Using AlloyUI - demonstrate using Alloy in HTML/JavaScript and within a JSP
in Liferay Portal
- Using AlloyUI taglibs
- AlloyUI's extensions to the YUI language
- Working with the AlloyUI project - describes the project files and how to
build it.
- Creating your own AlloyUI module and components

-->

AlloyUI is easy to use in your JavaScript and HTML anywhere on your web server.
Let's demonstrate by using Alloy's `aui-btn` module in an HTML file.

1. Insert the body of an HTML file on your web server:

        <button id="try-btn" class="aui-btn" type="button">
        Try me now!
        </button>

        <script src="http://cdn.alloyui.com/2.0.0pr5/aui/aui-min.js"></script>
        <script>
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
        </script>

2. Navigate to this HTML on your web server in your browser.
3. Click "Try me now!"

The button transitions from its original width to 500 pixels, as specified in
your script.

<!-- TODO Add screenshot of demo -->

Let's take a look at how we did this with AlloyUI. First we added our HTML -
that displays a button of AlloyUI's `aui-btn` module.

Then, we used a script element to reference Alloy's "seed" file, `aui-min.js`.
The seed file includes the bare minimum core code required for AlloyUI. Any
additional code is loaded dynamically by YUI.

    <script src="http://cdn.alloyui.com/2.0.0pr5/aui/aui-min.js"></script>

Lastly, our script selects the first element of `aui-btn` class on the page and
sets the button's width to 500 pixels on clicking it. How's that for dynamic
content!

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

    portal-dependency-tlds=aui.tld

Then, add the code for the button and the Alloy script to your portlet's JSP.
But, this time, we'll replace the `<script>` tag with the `<aui:script>` tag.
The `<aui:script>` tag combines all of the `<aui:script>` tag's used on a page
into one script block at the bottom of the page and it wraps the functions in an
`YUI.use()` call to bring in necessary modules if modules are passed in.

Without the need for the an explicit reference to AlloyUI's seed file and with
the replacement of the `<script>` tags with the `<aui:script>` tags the Alloy
code in your JSP looks like the following:


    <div style="text-align: center;">
      <button id="try-btn" class="aui-btn" type="button">Try me now!</button>
    </div>

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
fundamentals throughout this chapter. But first let's go over setting up a
simple environment for experimenting with AlloyUI.

Liferay Portal comes with AlloyUI in it, as it is used throughout Liferay
Portal and its core portlets. However, you can use AlloyUI in any web
environment. You may want to take advantage of this as you develop AlloyUI
scripts. For example, you can try out your AlloyUI scripts in an HTML file on a
simple web server on your machine. Let's download AlloyUI and set up a simple
development environment.

You can download AlloyUI from [http://alloyui.com/](http://alloyui.com/). The
`.zip` file you download from the AlloyUI website contains the following:

- `alloy-[version]/` - AlloyUI project root directory.

    - `build/` - Contains the AlloyUI and YUI3 modules used in Liferay.

    - `demos/` - Contains basic examples of the AlloyUI components.

    - `src/` - Contains the soruce code of the AlloyUI modules.

    - `.alloy.json` - Specifies how to build the modules.

    - `.shifter.json` - Specifies additional tasks used in building AlloyUI.

    - `LICENSE.md` - Defines the license agreement for AlloyUI.

    - `README.md` - Explains the AlloyUI project.

If you'd rather get the latest AlloyUI source code, go to
[https://github.com/liferay/alloy-ui/tree/2.0.x](https://github.com/liferay/alloy-ui/tree/2.0.x),
fork it, and clone it to your machine. We'll show you how to build it later in
this chapter. Regardless whether you're using AlloyUI from the `.zip` file or
cloned from the repository on Github, AlloyUI's modules are avilable in the
`build/` folder. You can access these modules from web pages deployed on a local
web server.

---

 ![important](../../images/tip-pen-paper.png) **Tip:** You may find it
 convenient to extract the AlloyUI installation into your web server, or
 create symbolic links from your AlloyUI installation to your web server.

---

As we did in our initial example, we'll refer to AlloyUI's seed file,
`aui-min.js`, found in your `alloy-2.0.0/build/aui/` folder. For example, if
your AlloyUI project root directory is `/home/joe.bloggs/alloy-[version]/`,
you'd refer to the seed file like this:

    <script src="/home/joe.bloggs/alloy-2.0.0/build/aui/aui-min.js"></script>

From that point on, you can use AlloyUI throughout your web page, like we did
in the example earlier in this section.

Before we go onto hard-core AlloyUI development, I'll show you how to build
AlloyUI. If you are not interested, feel free to skip these build instructions.

As we mentioned earlier, the AlloyUI project repository is available at
[https://github.com/liferay/alloy-ui/tree/2.0.x](https://github.com/liferay/alloy-ui/tree/2.0.x). You
can fork the repository and clone it to your local machine.

AlloyUI uses Yogi Alloy
([https://github.com/liferay/yogi-alloy](https://github.com/liferay/yogi-alloy))
to build its modules. For instructions on installing Yogi Alloy and its
dependencies, see the Yogi Alloy README.md file at
[https://github.com/liferay/yogi-alloy/blob/master/README.md](https://github.com/liferay/yogi-alloy/blob/master/README.md).
AlloyUI's build instructions are found in this README.md file at
[https://github.com/liferay/yogi-alloy#alloyui](https://github.com/liferay/yogi-alloy#alloyui).

To build all of the AlloyUI project and its dependencies, simply execute:

    ya init

The Yogi Alloy target prompts you before building different parts of the Alloy
UI project. Upon completion of the target, Yogi Alloy reports `.yogi [success]
done.`

Congratulations on building AlloyUI! Now it's time to dive head first into
developing your UI with AlloyUI.



