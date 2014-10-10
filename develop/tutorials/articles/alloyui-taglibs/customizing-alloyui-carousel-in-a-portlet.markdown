# Customizing the AUI-Carousel [](id=customizing-the-aui-carousel)

AlloyUI's carousel module has a lot to offer as is, but you can take it to the
next level by customizing it and making it your own. If you need to learn how to
set up the basic `aui-carousel`, please refer to the tutorial
[Using AlloyUI Carousel in Your Portlet](/develop/tutorials/-/knowledge_base/6-2/using-alloyui-carousel-in-your-portlet).
In this tutorial, you'll learn how to modify the UI of the standard
`aui-carousel` module in a portlet.

There are three key steps to follow as you customize your carousel:

1.  Assign an ID to the carousel and its images.

2.  Configure the carousel's attributes. 

3.  Style the carousel from the `main.css` file. 

Now that you know what lies ahead in this tutorial, it's time to start rolling! 

## Step 1: Assign an ID to the Carousel and Its Images

First, you must add an ID value for the carousel's `<div>` element and each of
its images. This was demonstrated in the tutorial
[Using AlloyUI Carousel in Your Portlet](/develop/tutorials/-/knowledge_base/6-2/using-alloyui-carousel-in-your-portlet), 

For example, if your carousel has four images, you might specify your carousel
and its images in your JSP using `<div>` elements like these:

    <div id="myCarousel">
      <div id="image1"></div>
      <div id="image2"></div>
      <div id="image3"></div>
      <div id="image4"></div>  
    </div>

Your carousel and its images are now identifiable, making them ready for
configuring and styling. 

## Step 2: Configure the Carousel

In addition to identifying your carousel and its images, you can configure the
carousel's attributes. The following example JSP code configures a carousel
named `myCarousel`:

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

There are some things worth pointing out about the above example carousel code.
The `myCarousel` `<div>` is the carousel widget's bounding box used for
positioning it. The carousel is set to display each image for two seconds. The
transition between images is set to last one second. Setting the `activeIndex`
to zero displays the first image listed in the `myCarousel` `<div>` when the
carousel starts. 

+$$$

**Note:** This is only a subset of the carousel's
modifiable attributes. If you choose not to set values for these attributes,
default values are used. For a full list of the carousel's attributes and their
defaults, as well as further documentation on it, please visit
[http://alloyui.com/api/classes/A.Carousel.html](http://alloyui.com/api/classes/A.Carousel.html).

$$$
 
Now that you've laid the configuration groundwork for the carousel, it's time to
style it.

## Step 3: Style the Carousel's UI from the main.css File

The carousel's UI has a lot of moving parts that you can style. First, to keep
things organized, you may want to customize your carousel's body and images.
Then, you may want to style its menu controls, its active and inactive index
indicators, and its menu bar and its list of controls. You'll learn how to style
all of these things. The figure below identifies different carousel elements
that you can manipulate with CSS. 

![Figure 1: Here are the carousel's key elements that you can style.](../../images/alloyui-carousel-css-map.png)

Now that you're familiar with the carousel's elements, you can start styling the
carousel's body and images.

### Styling the Carousel's Body and Images

You can specify your carousel's style in the `main.css` file in your portlet's
`/docroot/css/` folder. The example CSS code below is for styling a carousel's
body and images:

    /* styling for the carousel body */
    div.carousel-item {
        width: 700px;
        height: 250px;
        border-radius: 6px 6px 6px 6px;
    }

    /* styling for the boundingBox and ContentBox(in this case) */
    #myCarousel {
        margin: 0 auto 40px;
    }

    /* styling for div with id image1 */
    #image1 {
        background-image: url("../img/1.jpg");
    }

    /* styling for div with id image2 */
    #image2 {
        background-image: url("../img/2.jpg");
    }

    /* styling for div with id image3 */
    #image3 {
        background-image: url("../img/3.jpg");
    }

    /* styling for div with id image4 */
    #image4 {
        background-image: url("../img/4.jpg");
    }

The code starts off by setting the width and height for the carousel body and
then rounds the carousel's edges with the `border-radius` property. Next, the
carousel is centered in the middle of the portlet by setting the `margin`
property of the `boundingBox` element (`mycarousel` in this case) to `auto` for
the horizontal margins. The top margin is set to `0`, while the bottom margin is
set to `40px`, to leave room for the menu's controls. Finally, each image is
given a `background-image` property that points to its respective URL.

Now that you've styled your carousel's body and images, it's time to make its
menu controls shine!

### Styling the Carousel's Menu Controls

Each menu control has an existing class that you can reference via CSS. You can
identify the classes for the controls by viewing the Document Object Model (DOM)
tree for them. To give you an idea of the tree's overall structure, below is a
simplification of it:

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

You can see that the carousel menu controls lie within the `myCarousel` `<div>`,
inside a menu tag, inside a list item tag, inside an anchor tag. Your CSS code
must traverse the DOM tree to select the carousel's elements and style them. 

+$$$

**Note:** You can view the DOM tree by
right-clicking the carousel's *Next* menu button and inspecting its HTML code in
your browser.

$$$

Once you understand the DOM tree for each element, you can go ahead and specify
the styling for each of the menu buttons with their corresponding class. Each
menu button has a `background` property that you can set to an image file
location. Each button also has a `border-radius` property that lets you round
its edges. If you don't want rounded edges, you can omit the `border-radius`
property from your styling. The `main.css` file example code below demonstrates
styling carousel menu controls:

    /* Pause Button */
    #myCarousel menu li a.carousel-menu-item.carousel-menu-pause {
        background-image: url("../img/pause.png");
        height: 20px;
        width: 20px;
        border-radius: 90px;
    }

    /* Play Button */
    #myCarousel menu li a.carousel-menu-item.carousel-menu-play {
        background-image: url("../img/play.png");
        height: 20px;
        width: 20px;
        border-radius: 90px;
    }

    /* Prev Button */
    #myCarousel menu li a.carousel-menu-item.carousel-menu-prev {
        background-image: url("../img/prev.png");
        height: 20px;
        width: 20px;
        border-radius: 90px;
    }

    /* Next Button */
    #myCarousel menu li a.carousel-menu-item.carousel-menu-next {
        background-image: url("../img/next.png");
        height: 20px;
        width: 20px;
        border-radius: 90px;
    }

Make sure to place any custom background images for your menu controls in the
folder you specify for the control's `background-image` `url`. Now that you've
styled your carousel's menu controls, you can move onward to styling your
carousel's active and inactive index indicators!

### Styling the Carousel's Active and Inactive Index Indicators

As with the menu controls, you style the carousel's active and inactive index
indicators using existing classes. Please refer to your carousel's DOM tree to
see its structure for yourself. Below is example code for styling active and
inactive index indicators in a `main.css` file:

    /* active index indicator */
    #myCarousel menu li a.carousel-menu-item.carousel-menu-item.carousel-menu-index.carousel-menu-active {
        background-image: url("../img/active.png");
        background-color: rgba(0,0,0,0);
        width:20px;
        height:20px;
    }

    /* inactive index indicator */
    #myCarousel menu li a.carousel-menu-item.carousel-menu-item.carousel-menu-index {
        background-image: url("../img/inactive.png");
        background-color: rgba(0,0,0,0);
        width:20px;
        height:20px;
    }

The indicator images are set with the `background-image` property. The
indicators are then assigned a color with the `background-color` property.

Now that you've taken care of the index indicators, it's time to cover styling
the carousel's menu bar and menu controls list.

### Styling the Carousel's Menu Bar and Menu Controls List

As with the other styles, you can view the existing class for the menu bar and
controls list in the DOM tree. Below is one example from a `main.css` file:

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

The left edge of the menu bar is set 518 pixels from the carousel container's
left edge. The top edge of the menu bar is set 250 pixels from the container's
top edge, placing it just beneath the carousel, which is 250 pixels high. The
menu bar's width is scaled down to 26% the size of the carousel's width. To
finish out the CSS, the list, which holds the menu controls, is set to adjust to
the size and shape of the menu bar.

You've completed the CSS for your carousel to give it a fresh new look. Give
yourself a pat on the back for adding some seriously awesome style to your
carousel! When you're ready, go ahead and deploy your portlet to your portal
instance. If you've added the carousel to a portlet that has already been
deployed, you may need to undeploy and redeploy the portlet in order for your
changes to take full effect.

In this tutorial, you've learned how to take the basic `aui-carousel` module and
modify it to suit your needs. First, you learned how to set some basic
attributes to configure the carousel. Then, you learned how to re-design the
carousel by setting the styles of the carousel's body and images. Finally, you
styled your carousel's menu and controls to match your taste.

Excellent! You have successfully learned how to give AlloyUI's Carousel your own 
flare and style!

Here is an example of a carousel that uses customizations from this tutorial:

![Figure 2: Here is an example of a customized carousel, that uses this tutorial's scripts.](../../images/alloyui-customized-carousel-in-portlet.png)

You can access a finished version of the example customized portlet at 
[https://github.com/liferay/liferay-docs/tree/master/devGuide/code/12-working-with-alloyUI/customized-carousel-portlet](https://github.com/liferay/liferay-docs/tree/master/devGuide/code/12-working-with-alloyUI/customized-carousel-portlet).

Enjoy making your carousel the best that it can be!

## Related Topics

[Implementing a UI with Liferay Taglibs](/develop/learning-paths/-/knowledge_base/6-2/implementing-a-ui-with-liferay-taglibs)

[Liferay UI Taglibs](/develop/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

