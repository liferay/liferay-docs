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
projects. YUI's is is also documented well at
[http://yuilibrary.com/yui/docs/](http://yuilibrary.com/yui/docs/). 

By using AlloyUI, therefore leveraging YUI, you can reap significant performance
benefits. YUI helps reduce the size of your up-front JavaScript request
download, and lazily loads other modules as needed. YUI manages dependencies for
you, by making sure modules are only downloaded once for a page and by
specifying modules your page needs in a single request. Also, Liferay's
`aui:script` tags collect the JavaScript embedded on your page for parsing only
*after* its HTML and CSS have been loaded. This often speeds up the availability
of your UI to your user. As you use YUI through AlloyUI, you'll realize these
benefits. 

We know that many developers in the community like to use jQuery. You can use
jQuery in Liferay Portal, but we strongly recommend you use AlloyUI. AlloyUI is
always loaded and available to you. If you use something else, your page must
load your library and AlloyUI, which slows down performance. 

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

1. Create an HTML file like this: 
        <!DOCTYPE html>

        <input type="text" id="some-input" />
        <span id="counter"></span> character(s) remaining

        <script src="http://cdn.alloyui.com/2.0.0/aui/aui-min.js"></script>

        <link href="http://cdn.alloyui.com/2.0.0/aui-css/css/bootstrap.min.css"
          rel="stylesheet"></link>

        <script>
        YUI().use(
          'aui-char-counter',
          function(Y) {
            new Y.CharCounter(
              {
                counter: '#counter',
                input: '#some-input',
                maxLength: 10
              }
            );
          }
        );
        </script>

        </html>

2. Open the HTML file in your browser.
3. Enter some characters into the text field.

AlloyUI's character counter reports the number of characters you can enter in
the text field before reaching the 10 character limit. 

![Figure 11.x: Using AlloyUI on any HTML page is easy. Try out AlloyUI's character counter in your own page using the code above.](../../images/alloyui-char-counter-in-html-file.png)

Let's look at how we did this with AlloyUI. First we added HTML that
displays an HTML `<input/>` element with `id=some-input`. Then we reference an
element called `counter`, followed by some text describing that counter. 

Then we used a script element to reference Alloy's seed file, `aui-min.js`
from a content delivery network (CDN). The seed file includes the bare minimum
core code required for AlloyUI. Any additional code is loaded dynamically by
YUI.

    <script src="http://cdn.alloyui.com/2.0.0/aui/aui-min.js"></script>

---

 ![Note](../../images/tip.png) **Note:** For performance reasons, it
 is almost always best to reference the seed file from the CDN rather than from
 a designated server. On receiving the request for the seed file, the CDN
 returns it from the nearest server on the CDN, minimizing latency time.

---

Lastly, the script reports the number of characters remaining in the text field.
As you enter or delete characters from the field, the script recalculates the
number of remaining characters and displays that number via the `counter`
element on your page. How's that for dynamic content! 

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
is the YUI object as parameter `Y`. Alloy's classes are stored in this `Y`
object. In this function, you place presentation logic, leveraging AlloyUI's API
via the mighty `Y` object. We'll get into more details on the API shortly.

Now that we've dissected the example, let's get it working in a portlet. Instead
of referencing AlloyUI's seed file, simply reference the `aui` taglib in your
JSP:

    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

Then add the code for the input element and the Alloy script to your portlet's
JSP. This time, however, replace the `<script>` tag with the `<aui:script>` tag.
The `<aui:script>` tag combines the contents of all `<aui:script>` tags used on
a page into one script block at the bottom of the page, and it wraps the
functions in a `YUI.use()` call to bring in necessary module dependencies. 

With all these simplifications, the AlloyUI code in your
JSP looks like this:

    <input type="text" id="some-input" />
    <span id="counter"></span> character(s) remaining

    <aui:script>
    YUI().use(
      'aui-char-counter',
      function(Y) {
        new Y.CharCounter(
          {
            counter: '#counter',
            input: '#some-input',
            maxLength: 10
          }
        );
      }
    );
    </aui:script>

Voila! You're using AlloyUI in Liferay!

![Figure 11.x: Using AlloyUI in your portlet JSPs is a snap. Try using the `aui-char-counter` from this example in your portlet's JSP.](../../images/alloyui-char-counter-in-portlet.png)

Now that you've gotten your feet wet using AlloyUI, let's go over setting up the
AlloyUI project for creating your own AlloyUI components. 

## Working with the AlloyUI project [](id=working-with-the-alloyui-project-liferay-portal-6-2-dev-guide-en)

Liferay Portal comes bundled with AlloyUI, as it's used throughout the portal
and core portlets. Conveniently, you can use AlloyUI in any project--it doesn't
have to run on Liferay. When you develop AlloyUI scripts and components for use
in portal, you can reuse them anywhere else. If you're using AlloyUI outside
Liferay, you might want to build it yourself. 

Here are some other reasons why you might use a local AlloyUI installation or
AlloyUI project build:

- Using AlloyUI Outside Liferay
- Working with the AlloyUI Project

Let's download AlloyUI and set it up for developing AlloyUI scripts and
components locally. 

You can download AlloyUI as a `.zip` file from
[http://alloyui.com](http://alloyui.com). The file contains the following files
and folders: 

- `alloy-[version]/` - AlloyUI project root directory 
    - `build/` - Contains the AlloyUI and YUI modules used in Liferay 
    - `demos/` - Contains basic examples of the AlloyUI components 
    - `src/` - Contains the source code of the AlloyUI modules 
    - `.alloy.json` - Specifies how to build the modules 
    - `LICENSE.md` - Defines AlloyUI's the license agreement 
    - `README.md` - Explains the AlloyUI project 

As you did in the initial example, the first thing you'll call is AlloyUI's
`aui-min.js` seed file, in your `alloy-[version]/build/aui/` folder. For
example, if your AlloyUI project root directory is
`/home/joe.bloggs/alloy-2.0.0/`, you'll refer to the seed file like this: 

    <script src="/home/joe.bloggs/alloy-2.0.0/build/aui/aui-min.js"></script>

Likewise, make sure to specify your local bootstrap seed file as well:

    <link src="/home/joe.bloggs/alloy-2.0.0/build/aui-css/css/bootstrap.min.css"
      rel="stylesheet"></link>

Go ahead and replace the remote seed file references from the example HTML file
we used at the beginning of this chapter with refernces to your local seed
files. Except for the paths to your seed files, your HTML content should look
similar to this:

        <!DOCTYPE html>

        <input type="text" id="some-input" />
        <span id="counter"></span> character(s) remaining

        <script src="/home/joe.bloggs/alloy-2.0.0/build/aui/aui-min.js"></script>

        <link src="/home/joe.bloggs/alloy-2.0.0/build/aui-css/css/bootstrap.min.css"
          rel="stylesheet"></link>

        <script>
        YUI().use(
          'aui-char-counter',
          function(Y) {
            new Y.CharCounter(
              {
                counter: '#counter',
                input: '#some-input',
                maxLength: 10
              }
            );
          }
        );
        </script>

        </html>

The figure below shows what your web page should look like.

![Figure 11.x: Using AlloyUI on any HTML page is easy. Try out AlloyUI's character counter in your own page using the code above.](../../images/alloyui-char-counter-in-html-file.png)

Great! Now you know how to use a local set of the AlloyUI tag libraries. This
may come in handy when you're working offline. Next, we'll show you how to work
with the AlloyUI source project. You'll have all of the source code at your
disposal to see how everything works. And we'll show you how to build it so you
can have experiment with the latest AlloyUI code whenever you want. 

### Working with the AlloyUI Project

To get the latest AlloyUI source code, go to the
project's repository at
[https://github.com/liferay/alloy-ui](https://github.com/liferay/alloy-ui),
fork the repository, and clone it to your machine. 

TODO, forking and cloning

Working with the AlloyUI Project
1. Download AlloyUI source. 
2. Build AlloyUI locally. 
3. Unzip and extract locally built distribution. 
4. Use in web page. 

Before we go on to creating your own AlloyUI components, let's build AlloyUI. If
you are not interested, feel free to skip these build instructions. 

If you've cloned the `alloy-ui` project repository, make sure to check out
its`2.0.x` branch. Liferay Portal 6.2 requires AlloyUI 2.0. 

AlloyUI 2.0 uses Yogi Alloy
([https://github.com/liferay/yogi-alloy](https://github.com/liferay/yogi-alloy))
to build its modules. For instructions on installing Yogi Alloy and its
dependencies, follow the instructions in Yogi Alloy's `README.md` file at
[https://github.com/liferay/yogi-alloy/blob/master/README.md](https://github.com/liferay/yogi-alloy/blob/master/README.md).

Yogi Alloy depends on the following software:
- [Node.js](http://nodejs.org) Node.js is platform for building applications. Yogi
Alloy requires Node.js version 0.8 or higher.
- Ruby 
- [Compass](http://compass-style.org) is an open-source CSS authoring framework.

The current version of Node.js is available at [http://nodejs.org/download/](http://nodejs.org/download/); while all versions are available at [http://nodejs.org/dist/](http://nodejs.org/dist/).

On Windows you can download the `.msi` installation file and run it.

On Linux, MacOS, or UNIX, you can download the `.tar.gz` file, unzip it, and
un-tar it. Then follow the instructions in its `README.md` file to build it. 

To install Ruby, follow its installation instructions at
[https://www.ruby-lang.org/en/downloads/](https://www.ruby-lang.org/en/downloads/).

To install Compass, follow its installation instructions at
[http://compass-style.org/install/](http://compass-style.org/install/).

Now that you've installed Yogi Alloy's dependencies, you can use the Node.js
package manager (`npm`) to install Yogi Alloy. Execute the following command:  

    npm install -g yogi yogi-alloy yuidocjs docpad shifter

To build the AlloyUI project and its dependencies, execute: 

    ya init

The Yogi Alloy target prompts you before building different parts of the AlloyUI
project. Upon completion of the target, Yogi Alloy reports `.yogi [success]
done.` 

Congratulations on building AlloyUI! 

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

