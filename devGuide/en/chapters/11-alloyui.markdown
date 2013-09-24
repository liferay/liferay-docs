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
architecture that easy to extend. It is useful in both small and large scale
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
 
You can use jQuery in Liferay Portal, but we strongly recommend you use
AlloyUI. AlloyUI is always loaded and available to you. If you use something
else, your page must load your library and AlloyUI, which 

---

For consistent powerful styling, AlloyUI includes Twitter Bootstrap. If you've
been working with AlloyUI, you're probably already leveraging the Sass extension
to CSS. Great! Even though Twitter Bootstrap uses the Less extension, AlloyUI
still uses Sass. Its framework leverages a 1-to-1 mapping of LESS to Sass so you
can continue using Sass. How's that for convenience! 

<!-- This paragraph is confusing. We already mentioned Twitter Bootstrap above.
Then it's mentioned again here, so as a reader, I assume you're going to talk
about it. Instead, you then say something about Sass, and then Less. If I don't
know what those things are, it makes my head spin with a whole bunch of terms
that haven't been defined, and that really aren't germane to introducing the
technology we're trying to introduce. Developers don't need to know (yet) about
SASS and Less. In fact, if we don't plan to cover issues around using them in
this chapter, we shouldn't mention it at all. I recommend deleting the above
paragraph. -Rich -->

By reading this chapter and following along with its exercises, you'll learn
what AlloyUI is and how to use it in Liferay Portal. For further details on the
AlloyUI project, tutorials, examples, and API documentation, be sure to visit
[http://alloyui.com](http://alloyui.com). We'll show you around AlloyUI in this
chapter by exploring the following topics: 

- Traversing the DOM with AlloyUI
- Using AlloyUI taglibs
- AlloyUI's extensions to the YUI language
- Styling your components with Twitter Bootstrap
- Creating your own UI component modules

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

2. Navigate to this HTML in your browser.
3. Click *Try me now!*

The button transitions from its original width to 500 pixels, as specified in
your script.

<!-- TODO Add screenshot of demo -->

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

This script uses YUI and AlloyUI in what is commonly referred to as a "sandbox."
Code is sandboxed when elements of the code are set off in their own namespaces.
Why do this? Because JavaScript, like many programming languages, has both a
local and a global scope. Code placed in a JavaScript function is locally
scoped, which means that nothing inside that function can be seen outside that
function. Another way to describe this code is that it has been sandboxed. 

<!-- I changed the above paragraph because when you're defining a term (such as
the concept of sandboxing), you can't use the term in the definition. -Rich -->

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

You'll get plenty of practice using AlloyUI as you go through its fundamentals
in this chapter. Next we'll give you a real workout by traversing a document
object model (DOM).

<!-- Add Section - Traversing the DOM with AlloyUI -->

<!-- insert content -->

<!--
- Select Node based on element ID
A.one('#someId')

- Set Node's value
shippingFirstnameNode.val(billingFirstnameNode.val() );
-->

<!-- insert transition -->

<!-- Add Section - Using AlloyUI taglibs -->

<!-- insert content -->

<!-- insert transition -->

<!-- Add Section - Styling your components with Twitter Bootstrap -->

<!-- insert content -->

<!-- insert transition -->

<!-- Add Section - Creating your own UI component modules -->

<!-- Explain the benefits of building UI components in the AlloyUI project -->

Before we create components, let's go over setting up the AlloyUI project. 

### Working with the AlloyUI project [](id=working-with-the-alloyui-project-liferay-portal-6-2-dev-guide-en)

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

As you did in the initial example, the first thing you'll call is AlloyUI's
`aui-min.js` seed file, in your `alloy-[version]/build/aui/` folder. For
example, if your AlloyUI project root directory is
`/home/joe.bloggs/alloy-2.0.0/`, you'll refer to the seed file like this: 

    <script src="/home/joe.bloggs/alloy-2.0.0/build/aui/aui-min.js"></script>

<!-- Note that the above call doesn't use a web server; it's using the local
file system. -Rich --> 

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

As we mentioned earlier, the AlloyUI project repository is available at
[https://github.com/liferay/alloy-ui](https://github.com/liferay/alloy-ui).
You can fork the repository and clone it to your local machine. 

<!-- We should mention the URLs only once, and in the appropriate place. -Rich
-->

AlloyUI uses Yogi Alloy
([https://github.com/liferay/yogi-alloy](https://github.com/liferay/yogi-alloy))
to build its modules. For instructions on installing Yogi Alloy and its
dependencies, follow the instructions in Yogi Alloy's `README.md` file at
[https://github.com/liferay/yogi-alloy/blob/master/README.md](https://github.com/liferay/yogi-alloy/blob/master/README.md).

<!-- If we need Yogi Alloy to build AlloyUI, we should document how to install
and use it here. -Rich -->

To build the AlloyUI project and its dependencies, execute: 

    ya init

The Yogi Alloy target prompts you before building different parts of the AlloyUI
project. Upon completion of the target, Yogi Alloy reports `.yogi [success]
done.` 

Congratulations on building AlloyUI! Now it's time to create your own UI
components in AlloyUI. 

<!-- Add Section - Creating components is easy with AlloyUI -->

<!-- Explain how to create components and modules in AlloyUI -->

<!-- Add Summary -->

