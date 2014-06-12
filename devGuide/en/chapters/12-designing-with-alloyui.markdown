# Designing User Interfaces with AlloyUI [](id=designing-user-interfaces-with-alloyui-liferay-portal-6-2-dev-guide-en)

Liferay's User Interface (UI) Team continually strives to provide stylish,
lightning-fast components that are extensible and built to last. I liken the
team to an elite band of metallurgists: cutting-edge scientists that extract
metals from craggy rocks deep beneath the earth's crust. They purify these
metals and mix them together to make some of the most awesome things known to
mankind--seemingly weightless bike frames, bridges with enough tensile strength
to withstand hurricanes, and swords that can cut through almost anything. The
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
projects. YUI is also documented well at
[http://yuilibrary.com/yui/docs/](http://yuilibrary.com/yui/docs/). 

By using AlloyUI, and therefore leveraging YUI, you can reap significant
performance benefits. YUI helps reduce the size of your up-front JavaScript
request download, and lazily loads other modules as needed. YUI manages
dependencies for you, by making sure modules are only downloaded once for a page
and by specifying modules your page needs in a single request. Also, AlloyUI
provides special tags that let you designate JavaScript for parsing only *after*
your page's HTML and CSS have been loaded. This often speeds up the availability
of your UI to your user. As you use YUI through AlloyUI, you'll realize these
benefits.

We know that many developers in the Liferay community like to use jQuery. You
can use jQuery in Liferay Portal, but we strongly recommend you use AlloyUI.
Note, AlloyUI is always loaded and available to you in Liferay. If you use
something else, your page must load your library *and* AlloyUI, which slows down
performance. 

By reading this chapter and following along with its exercises, you'll learn
what AlloyUI is and how to use it in Liferay Portal. For further details on the
AlloyUI project, tutorials, examples, and API documentation, make sure to visit
[http://alloyui.com](http://alloyui.com). We'll show you around AlloyUI in this
chapter by exploring the following topics: 

- A simple AlloyUI example
- Using an AlloyUI Carousel in Your Portlet
- Working with the AlloyUI project

To start things off right, let's go over a simple example using AlloyUI.

## A simple AlloyUI example [](id=simple-alloyui-example-liferay-portal-6-2-dev-guide-en)

AlloyUI is easy to use. Let's demonstrate by using AlloyUI in an HTML file.

1. Create an HTML file and insert the following lines: 

        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="utf-8">
          <title>Example</title>

          <script src="http://cdn.alloyui.com/2.0.0/aui/aui-min.js"></script>
          <link href="http://cdn.alloyui.com/2.0.0/aui-css/css/bootstrap.min.css"
          rel="stylesheet">
        </head>
        <body>
          <input type="text" id="some-input" />
          <span id="counter"></span> character(s) remaining

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
        </body>
        </html>

2. Open the HTML file in your browser.

3. Enter some characters into the text field.

AlloyUI's character counter reports the number of characters you can enter in
the text field before reaching the 10 character limit. 

![Figure 12.1: Using AlloyUI in HTML is easy. Try out AlloyUI's character counter in your own page using the code above.](../../images/alloyui-char-counter-in-html-file.png)

Let's look at how we did this with AlloyUI. First we added HTML that
displays an HTML `<input/>` element with `id=some-input`. Then we referenced an
element called `counter` and added some text describing that counter. 

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

Lastly, the code in the `script` element reports the number of characters
remaining in the text field. As you enter or delete characters from the field,
the script recalculates the number of remaining characters and displays that
number via the `counter` element on your page. How's that for dynamic content! 

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

`YUI().use(`*[package 1, ...  package n,]*` function(Y) { // Your code goes here });`

`YUI().use()` is a function call that instantiates modules for you to use. As
parameters, you pass in packages and a function containing your code. The
example code required Alloy's `aui-char-counter` package. The final argument
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

With all these simplifications, the AlloyUI code in your JSP looks like this:

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

![Figure 12.2: Using AlloyUI in your portlet JSPs is a snap. Try using the `aui-char-counter` from this example in your portlet's JSP.](../../images/alloyui-char-counter-in-portlet.png)

Now that we've gone over using a simple `aui-char-counter` in your portlet, 
let's move on to something a little more challenging.

## Using an AlloyUI Carousel in Your Portlet [](id=using-an-alloyui-carousel-in-your-portle-liferay-portal-6-2-dev-guide-12-en)

We went over a simple example of using the `aui-char-counter` in an HTML file
and then showed you how to place it into a portlet. Next, let's kick things up a
notch and use an AlloyUI component that has more versatility: an image carousel!

Image carousels are often the first thing people see when they visit sites. They
provide an interactive way of cycling through visual elements and are an
effective means of communicating information to users. AlloyUI's `aui-carousel`
module makes it easy to get an image carousel up and running in no time flat.
First, you'll learn how to set up a basic portlet with the carousel and see
what's happening behind the scenes. Then you'll have some real fun by
customizing the carousel to suit your individual needs.

To give you an idea of what's next, here is what the basic carousel looks like:

![Figure 12.3: Image carousels can be a very handy tool to communicate information to users. Here is what the finished bare-bones portlet looks like.](../../images/alloyui-carousel-in-portlet.png)

All right, enough discussing the future. Time to do the work!

### Adding a Carousel to a Portlet [](id=adding-a-carousel-to-a-portlet-liferay-portal-6-2-dev-guide-12-en)

To add a carousel to a portlet, follow these step: 

1. Insert this code in your portlet's view JSP:

        <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

        <div id="myCarousel">
          <div class="carousel-item" style="background: url(http://alloyui.com/carousel/img/1.jpg);"></div>
          <div class="carousel-item" style="background: url(http://alloyui.com/carousel/img/2.jpg);"></div>
          <div class="carousel-item" style="background: url(http://alloyui.com/carousel/img/3.jpg);"></div>
          <div class="carousel-item" style="background: url(http://alloyui.com/carousel/img/4.jpg);"></div>
        </div>

        <aui:script>
         AUI().use(
           'aui-carousel',
           function(Y) {
            new Y.Carousel(
              {
                contentBox: '#myCarousel',
                height: 250,
                width: 700
              }
            ).render();
          }
        );
        </aui:script>

    If you try to deploy the portlet now, you'll notice that no images are 
    displayed; this is because we need to write some CSS to tell the portlet how
    to display the carousel.

2. Create a `main.css` file in your portlet's `docroot/css/` directory and add
   this code to it: 

        div.carousel-item 
        {
        width: 700px;
        height: 250px;
        }

3. Deploy your portlet to your portal.

Your images display correctly. Give yourself a pat on the back; you've just
successfully used the `aui-carousel` in a portlet! Next, it's time to understand
the inner-workings of the carousel and see what makes it tick.

The JSP code you inserted in the portlet specified your carousel. You included
directives for using the `java` and `aui` taglibs. Below them, you specified a
`<div>` named `myCarousel`, to identify the carousel's images. The default
images are provided by [AlloyUI](http://alloyui.com). This set the foundation
for using the AUI script. 

The script uses the `aui-carousel` module. You gave it some basic attributes to
specify where to display the carousel and the size it should be. You told the
`aui-carousel` to display in the `myCarousel` `<div>` by placing the `<div>`'s
ID as the value of the `contentBox` attribute. You also set the `width` and
`height` attributes at the resolution of 700px X 250px. 

Finally, you used the `main.css` file to style the `carousel-item` divs, giving
them `width` and `height` property values to match the carousel's `width` and
`height` attributes, specified in the JSP. That wasn't so hard, was it?

Next you'll look at how you can customize the carousel to give it your own
flare.

### Customizing the AUI-Carousel [](id=customizing-the-aui-carousel-liferay-portal-6-2-dev-guide-12-en)

Now comes the really fun part: making the carousel your own!

1.  Open the same view JSP file and replace its code with the following code:
        
        <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

        <div id="myCarousel">
          <div id="image1"></div>
          <div id="image2"></div>
          <div id="image3"></div>
          <div id="image4"></div>  
        </div>

        <aui:script>
        AUI().use(
          'aui-carousel',
          function(Y) {
           new Y.Carousel(
             {
               contentBox: '#myCarousel',
               height: 250,
               width: 700,
               intervalTime: 2,
               animationTime: 1,
               activeIndex: 0,
               boundingBox: '#myCarousel'
             }
           ).render();
          }
        );
        </aui:script>

    The code above has some attributes that can be styled to customize the
    carousel widget. The bounding box of the widget, used for positioning the
    carousel, is set to the `#myCarousel` div. The transition between images is
    set to last for one second. The carousel is set to display each image for
    two seconds. Setting the `activeIndex` to 0 displays the first image listed
    in the `#myCarousel` `<div>` as the first one to display. Now that you've
    laid the groundwork for your carousel, you can go ahead and style it.

    ---

     ![Note](../../images/tip.png) **Note:** This is only a subset of the
     attributes that can be modified for a carousel. If you choose not to set
     values for these attributes, defaults are used. For a full list
     of the attributes and their defaults, as well as further documentation on
     the AUI-Carousel, please visit
     <http://alloyui.com/api/classes/A.Carousel.html>. 

    ---

2.  Open the `main.css` file and replace its code with the following CSS that 
    styles the carousel:

        /* styling for the carousel body */
        div.carousel-item {
            width: 700px;
            height: 250px;
            border-radius: 6px 6px 0 6px;
            opacity: 100;
        }

        /* styling for the boundingBox and ContentBox(in this case) */
        #myCarousel {
            margin: 0 auto 40px;
        }

        /* styling for div with id image1 */
        #image1 {
            background: url("../img/moon.jpg"); 
        }

        /* styling for div with id image2 */
        #image2 {
            background: url("../img/thor.jpg"); 
        }

        /* styling for div with id image3 */
        #image3 {
            background: url("../img/toy.jpg"); 
        }

        /* styling for div with id image4 */
        #image4 {
            background: url("../img/spock.jpg"); 
        }

        /* Pause Button */
        #myCarousel menu li a.carousel-menu-item.carousel-menu-pause {
            background-image: url("../img/icons.png");
            background-position: 0 43px;
            height: 20px;
            width: 20px;
            border-radius: 90px;
        }

        /* Play Button */
        #myCarousel menu li a.carousel-menu-item.carousel-menu-play {
            background-image: url("../img/icons.png");
            background-position: 20px 43px;
            height: 20px;
            width: 20px;
            border-radius: 90px;
        }

        /* Prev Button */
        #myCarousel menu li a.carousel-menu-item.carousel-menu-prev {
            background-image: url("../img/icons.png");
            background-position: 0 64px;
            height: 20px;
            width: 20px;
            border-radius: 90px;
        }

        /* Next Button */
        #myCarousel menu li a.carousel-menu-item.carousel-menu-next {
            background-image: url("../img/icons.png");
            background-position: 21px 0;
            height: 20px;
            width: 20px;
            border-radius: 90px;
        }

        /* active index indicator */
        #myCarousel menu li a.carousel-menu-item.carousel-menu-item.carousel-menu-index.carousel-menu-active {
            background-image: url("../img/icons.png");
            background-color: rgba(0,0,0,0); 
            background-position: 21px 22px; 
            width:20px;
            height:20px;
        }

        /* inactive index indicator */
        #myCarousel menu li a.carousel-menu-item.carousel-menu-item.carousel-menu-index {
            background-image: url("../img/icons.png");
            background-color: rgba(0,0,0,0); 
            background-position: 0px 22px; 
            width:20px;
            height:20px;
        }

        /* Menu Bar */
        #myCarousel menu {
            background: none repeat scroll 0 0 #0000C0;
            border-bottom: 3px solid #00CCE0;
            border-radius: 0 0 15px 15px;
            bottom: auto;
            display: table;
            left: 518px;
            padding: 1% 0;
            right: 0;
            top: 250px;
            width: 26%;
        }

        /* List of menu buttons */
        #myCarousel menu li {
            float: inherit;
        }
 
Some explanation of these styles is in order. The code starts off by setting the
width and height for the carousel body, giving it rounded edges with the
`border-radius` property. 

        /*styling for the carousel body*/
        div.carousel-item 
        {
           width: 700px;
           height: 250px;
           border-radius: 6px 6px 0 6px;
           border-radius: 6px 6px 0 6px;
        }
 
Next, the carousel is centered in the middle of the portlet by setting the
`margin` property of the `boundingBox` element (`#mycarousel` in this case) to
`auto` for the left and right margins. The top margin is set to `0` while the
bottom margin is set to `40px` to leave room for the menu controls.
                
        /* styling for the boundingBox and ContentBox(in this case) */
        #myCarousel
        {
           /* centers the carousel in the middle of the portlet */
           margin:0 auto 40px;
        }

Next, the carousel's images are set by pointing the `background-image`
properties of the corresponding `<div>`s to the location of each respective
image file, which reside in the `../img/` directory. 

        /* styling for div with id image1 */
        #image1
        {
           background-image: url("../img/moon.jpg");
        }
        
        /* styling for div with id image2 */
        #image2
        {
           background-image: url("../img/thor.jpg");
        }
        
        /* styling for div with id image3 */
        #image3
        {
           background-image: url("../img/toy.jpg");
        }
        
        /* styling for div with id image4 */
        #image4
        {
           background-image: url("../img/spock.jpg");
        }

Now that you've seen how to style the carousel's body, it's time to break down
the carousel menu's styling. Existing classes are referenced for the menu
controls. To understand how to determine the classes for the menu controls, you
need to understand the DOM tree:
        
        <div id="myCarousel">
         <menu>
          <li>
           <a class="carousel-menu-item carousel-menu-pause"></a>
           <a class="carousel-menu-item carousel-menu-play"></a>
           <a class="carousel-menu-item carousel-menu-prev"></a>
           <a class="carousel-menu-item carousel-menu-next"></a>
          </li>
         </menu>
        </div>
 
This is a simplified version of the DOM tree, but it gives you an idea of the
overall structure. You can see from the DOM tree that the carousel menu controls
lie within the `#myCarousel` `<div>`, inside a menu tag, inside a list tag,
inside an anchor tag.

---

![Note](../../images/tip.png) **Note:** You can see the DOM tree by 
right-clicking the carousel's *Next* menu button and inspecting the element in
the browser (Firefox in this case).
 
---

Once you understand the DOM tree, you can go ahead and place the styling for
each of the menu buttons with their corresponding class. Each menu button has a
`background` property set to its image location and a `border-radius` property
for rounding edges. If you don't want a rounded edge, you can omit the
`border-radius` property from your styling. Another important property to note
is the `background-position` property. You used an image sprite for the menu
controls, and so you configured a `background-position` property to tell the
buttons where exactly on the image they are. 

        /* Pause Button */
        #myCarousel menu li a.carousel-menu-item.carousel-menu-pause{
           background-image: url("../img/icons.png");
           background-position: 0 43px;
           height: 20px;
           width: 20px;
           border-radius:90px; /* in this case I have a circular icon */
        }
        
        /* Play Button */
        #myCarousel menu li a.carousel-menu-item.carousel-menu-play{
           background-image: url("../img/icons.png");
           background-position: 20 43px;
           height: 20px;
           width: 20px;
           border-radius:90px; /* in this case I have a circular icon */
        }
        
        /* Prev Button */
        #myCarousel menu li a.carousel-menu-item.carousel-menu-prev{
           background-image: url("../img/icons.png");
           background-color: rgba(0,0,0,0); 
           background-position: 0 64px;
           height: 20px;
           width: 20px;
           border-radius:90px; /* in this case I have a circular icon */
        }
        
        /* Next Button */
        #myCarousel menu li a.carousel-menu-item.carousel-menu-next{
           background-image: url("../img/icons.png");
           background-color: rgba(0,0,0,0); 
           background-position: 21px 0;
           height: 20px;
           width: 20px;
           border-radius:90px; /* in this case I have a circular icon */
        }

Next, you styled the active and inactive index indicators. Once again, in the
DOM tree there are existing classes that can be used for styling. The images are
set with the `background-image` and `background-position` properties and given
the proper height and width.

        /* active index indicator */
        #myCarousel menu li a.carousel-menu-item.carousel-menu-item.carousel-menu-index.carousel-menu-active{
           background-image: url("../img/icons.png");
           background-position: 21px 22px;
           width: 20px;
           height: 20px;
        }
        
        /* inactive index indicator */
        #myCarousel menu li a.carousel-menu-item.carousel-menu-item.carousel-menu-index {
           background-image: url("../img/icons.png");
           background-position: 0 22px;
           width: 20px;
           height: 20px;
        }

Finally, you styled the menu bar, which holds the menu controls that you also
styled. The left edge of the menu bar is set 518 px from the left. The top edge
of the menu bar is set 250 px from the top, placing it just beneath the 250 px
height carousel. The menu bar's width is scaled down to 26% of the size of the
carousel's width. To finish out the CSS, the list, which holds the menu buttons,
is set to adjust to the size and shape of the menu bar. 

    /* Menu Bar */
    #myCarousel menu {
       background: none repeat scroll 0 0 #0000C0;
       border-bottom: 3px solid #00CCE0;
       border-radius: 0 0 15px 15px;
       bottom: auto;
       display: table;
       left: 518 px;
       padding: 1% 0;
       right: 0;
       top: 250px;
       width: 26%
    }
        
    /* List of menu buttons */
    #myCarousel menu li {
       float: inherit;
    }

Here is an example of a customized carousel using the configuration above:

![Figure 12.4: Image carousels can be customized. Here is an example of a customized carousel, using the scripting above.](../../images/alloyui-customized-carousel-in-portlet.png)

You can access a finished version of the customized portlet at 
[https://github.com/liferay/liferay-docs/tree/master/devGuide/code/12-working-with-alloyUI/customized-carousel-portlet](https://github.com/liferay/liferay-docs/tree/master/devGuide/code/12-working-with-alloyUI/customized-carousel-portlet)

Now that you've gotten your feet wet using some of AlloyUI's components, next
you'll see how to work with the AlloyUI source so you can create your own
components. 

## Working with the AlloyUI project [](id=working-with-the-alloyui-project-liferay-portal-6-2-dev-guide-en)

Liferay bundles AlloyUI with the portal, as it's used throughout the portal
and core portlets. Conveniently, you can use AlloyUI in any project--it doesn't
have to run on Liferay. When you develop AlloyUI scripts and components for use
in the portal, you can reuse them anywhere else. If you're using AlloyUI outside
Liferay, you might want to build it yourself. 

Here are some other reasons why you might use a local AlloyUI installation or
AlloyUI project build:

- Creating and testing your own AlloyUI component modules 
- Using the latest AlloyUI project source code that is not yet released 
- Using AlloyUI on a closed network 
- Contributing and testing a fix or enhancement to AlloyUI 

Let's download AlloyUI and set it up for developing AlloyUI scripts and
components locally. 

### Working with an AlloyUI Project Release Zip File [](id=using-downloaded-alloyui-zip-liferay-portal-6-2-dev-guide-11-en)

You can download any AlloyUI version as a `.zip` file from
[https://github.com/liferay/alloy-ui/releases](https://github.com/liferay/alloy-ui/releases). The file contains the following files
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
we used at the beginning of this chapter with references to your local seed
files. Except for the paths to your seed files, your HTML content should look
similar to this:

        <!DOCTYPE html>
        <html lang="en">
        <head>
          <meta charset="utf-8">
          <title>Example</title>

          <script src="/home/joe.bloggs/alloy-2.0.0/build/aui/aui-min.js"></script>
          <link href="/home/joe.bloggs/alloy-2.0.0/build/aui-css/css/bootstrap.min.css"
          rel="stylesheet"></link>
        </head>
        <body>
          <input type="text" id="some-input" />
          <span id="counter"></span> character(s) remaining

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
        </body>
        </html>

The figure below shows what your web page should look like.

![Figure 12.5: Using AlloyUI on any HTML page is easy. Try out AlloyUI's character counter on your own page using the code above.](../../images/alloyui-char-counter-in-html-file.png)

Great! Now you know how to use a local set of the AlloyUI tag libraries. Next,
we'll show you how to work with the AlloyUI source project. You'll learn how to
build the project so you can experiment with the latest AlloyUI code whenever
you want. 

### Working with the AlloyUI Project Source [](id=working-with-the-alloyui-project-source-liferay-portal-6-2-dev-guide-11-en)

You may want to work with the latest cutting-edge AlloyUI code from time to
time. Liferay makes it easy do get your hands on. We use a public
[GitHub](https://github.com/) project named *alloy-ui* to store and share the
latest AlloyUI code. You can download the code so that you can build it and try
it locally. You can also leverage the alloy-ui project to create some AlloyUI
modules of your own. We'll show you just how easy it is to install the project
and use it. 

In this section, we'll demonstrate the following:

- Installing the required software for the AlloyUI project
- Installing the alloy-ui project
- Building the project
- Packaging the project in a distribution and using that distribution

Let's get started by installing AlloyUI's dependencies. 

#### Setting Up AlloyUI's Required Software [](id=set-up-alloyui-required-software-liferay-portal-6-2-dev-guide-11-en)

The alloy-ui project depends on the following software:

- [Node.js](http://nodejs.org) is a platform for building applications.
- [Ruby](https://www.ruby-lang.org/en/) is used in the alloy-ui project for
  downloading other software packages. 
- [Compass](http://compass-style.org) is an open-source CSS authoring framework.
- [Sass](http://sass-lang.com/) stands for Syntactically Awesome Stylesheets. It
  is a scripting language used for specifying CSS. 

Let's install Node.js first. You can download it from
[http://nodejs.org/](http://nodejs.org/). Linux, OS X, or UNIX users can
download its source in a `.tar.gz` file, unzip it, un-tar it, and build it per
the instructions in its `README.md` file. Windows users can download the `.msi`
installer file and run it.

---

 ![Warning](../../images/tip.png) **Warning:** On Windows, only install to
 locations that have UNIX-friendly paths. Paths like `C:\Program Files (x86)`
 that contain space characters and parentheses can prevent software from working
 properly. 

---

You can download Ruby from
[https://www.ruby-lang.org](https://www.ruby-lang.org). Alternatively, on
Windows, you can download RubyInstaller from
[http://rubyinstaller.org/](http://rubyinstaller.org/) and use it to install
Ruby. After installing Ruby, execute the following command from your terminal to
get its latest updates:

    gem update --system

Now, let's use Ruby's gem command to install Compass and Sass. Conveniently,
Sass comes bundled with Compass. To install both of them, simply execute the
following command: 

    gem install compass

Great! You've installed all of the software applications that the alloy-ui
project requires. Next, let's get our hands on the alloy-ui project. 

#### Installing the AlloyUI Project [](id=install-the-alloyui-project-liferay-portal-6-2-dev-guide-11-en)

Liferay's AlloyUI developers and AlloyUI community members contribute code to
the [alloy-ui](https://github.com/liferay/alloy-ui) project on
[GitHub](https://github.com/). To access the alloy-ui project and install it
locally, you'll need an account on GitHub and the Git tool on your machine.
Visit [https://github.com/](https://github.com/) for instructions on setting up
the account and see [http://git-scm.com/](http://git-scm.com/) for instructions
on installing Git. 

Here are some simple steps for forking the alloy-ui project on GitHub and
installing the project locally: 

1.  Go to the AlloyUI project repository at
    [https://github.com/liferay/alloy-ui](https://github.com/liferay/alloy-ui).

2.  Click *Fork* to copy Liferay's alloy-ui repository to your account on
    GitHub. 

3.  In your terminal or in GitBash, navigate to the location where you want to
    put the alloy-ui project. Then download a clone of the repository by
    executing the following  command, replacing `username` with your GitHub user
    name: 

        git clone git@github.com:username/alloy-ui

    Now you have your own personal copies of the project in GitHub and on your
    local machine. Before you start building the project, let's set it up with
    the `2.0.x` branch. AlloyUI 2.0 is the version used by Liferay Portal 6.2.  

4.  Navigate to your new alloy-ui directory in GitBash by running `cd alloy-ui`.

5.  To download Liferay's alloy-ui branches, you must first associate a remote
    branch to Liferay's alloy-ui repository and then fetch all of branches via
    that remote branch: 

        git remote add upstream git@github.com:liferay/alloy-ui.git
        git fetch upstream

6.  Lastly, create your own branch named `2.0.x` based on Liferay's `2.0.x`
    branch, by executing the following command:

        git checkout -b 2.0.x upstream/2.0.x

Great! Now that you have the `2.0.x` branch checked out, we can install and
initialize the project's remaining dependencies. Follow these steps:

1.  Install the global dependencies (exclude using `[sudo]` on Windows):

        [sudo] npm install -g grunt-cli shifter yogi yuidocjs phantomjs

2.  Then install the local dependencies:

        npm install
    
3.  Lastly, the alloy-ui project has a special target called `init` that clones
    and updates the GitHub software projects on which alloy-ui depends. These
    projects include [yui3](https://github.com/liferay/yui3.git),
    [ace-builds](https://github.com/ajaxorg/ace-builds.git),
    [alloy-bootstrap](https://github.com/liferay/alloy-bootstrap.git),
    [alloy-apidocs-theme](https://github.com/liferay/alloy-apidocs-theme.git), and
    [alloyui.com](https://github.com/liferay/alloyui.com.git).
    Initialize these projects for alloy-ui by executing this command:

        grunt init

Alright! You have the alloy-ui project and all of its dependencies. Next, we'll
build AlloyUI. 

#### Building the AlloyUI Project [](id=build-the-alloyui-project-liferay-portal-6-2-dev-guide-11-en)

The alloy-ui project contains source code for AlloyUI, YUI3, and Twitter
Bootstrap. The project uses a JavaScript build tool called Grunt to build all
kinds of things, including AlloyUI, YUI3, Twitter Bootstrap CSS, and AlloyUI API
documentation. The alloy-ui project has targets that simplify building several
sources at once and it has granular targets for building individual sets of
source code. We've provided a table of these targets below.

**The alloy-ui Project Grunt Targets**

 Target  | Command | Description |
-------- | ------- | ----------- |
 `build` | `grunt build` | Builds YUI and AlloyUI together |
 `build:yui` | `grunt build:yui` | Builds YUI only |
 `build:aui` | `grunt build:aui` | Builds AlloyUI only |
 `bootstrap` | `grunt bootstrap` | Builds and imports Bootstrap's CSS |

Let's build everything by executing the following command:

    grunt all

On successfully executing each of these commands, Grunt reports this message:
`Done, without errors.` Well done!

Note, to build a single AlloyUI module, you can execute the following (replace
`aui-module-name` with the module's name):

    grunt build:aui --src src/aui-module-name

When you're ready to try out your locally built version of AlloyUI, you can
package it up and use it. We'll do that next. 

#### Using Your Locally Built AlloyUI Distribution [](id=create-alloyui-distribution-liferay-portal-6-2-dev-guide-11-en)

Building a release distribution of your alloy-ui project is easy. And it's just
as easy using your distribution in your web pages. We'll do it together. 

To create your distribution `.zip` file of AlloyUI, execute the following
command: 

    grunt release

This creates zip file `alloy-[version].zip`. Unzip this file to an arbitrary
location. 

You can reference your AlloyUI distribution in your Liferay JSPs in the same
manner we demonstrated in the section *Working with an AlloyUI Project Release*.
That is, you reference AlloyUI's `aui-min.js` file as a seed file.

For example, you could specify the following seed file, replacing
`/home/joe.bloggs/` with the path to your unzipped distribution.

    <script src="/home/joe.bloggs/alloy-2.0.0/build/aui/aui-min.js"></script>

It's just that easy to use your very own cutting-edge copy of the AlloyUI code! 

## Summary [](id=alloyui-chapter-summary-liferay-portal-6-2-dev-guide-11-en)

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

Now that you know how to use AlloyUI components to build snazzy UIs, let's
consider what it takes to develop your apps for publishing to *Liferay
Marketplace*. 

