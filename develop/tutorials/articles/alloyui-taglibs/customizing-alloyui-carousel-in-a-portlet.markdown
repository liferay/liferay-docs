# Customizing the AUI-Carousel

AlloyUI's carousel module has a lot to offer as it stands, but you can take it
to the next level by customizing it and making it your own. If you need to learn 
how to setup the basic `aui-carousel` please refer to the tutorial *Using 
AlloyUI Carousel in Your Portlet*. In this tutorial you'll learn how to modify 
the UI of the standard `aui-carousel` module in a portlet.

It's time to go back, back to the carousel! Where you're going you don't
need code; on the other hand yes, yes you do. Don't worry, there won't be many
more puns to put up with.

There are a few key steps you'll have to follow to customize your carousel:

1.  Configure the attributes of your portlet's `view.jsp`.
2.  Set the styles for the UI in your portlet's `main.css`.
3.  Finally, deploy the portlet to your portal.

With that out of the way it's time to kick this baby up to 88 mph and get the 
ball rolling!

## Step 1: Configuring the Attributes of `view.jsp`

First things first, you need to configure the attributes of the carousel to 
match the settings you desire. Here is example code for a possible configuration 
of the `view.jsp`:

        ```
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
        ```

The code above has some basic attributes that can be styled to customize the
carousel widget. The bounding box of the widget, used for positioning the
carousel, is set to the `#myCarousel` div. The transition between images is set 
to last for 1 second. The carousel is set to display each image for 2 seconds. 
Setting the `activeIndex` to 0 displays the first image listed in the 
`#myCarousel` div when the carousel starts. Finally, the divs are setup with a
unique id for each image in the carousel.

---

 ![Note](../../images/tip.png) **Note:** These are only a subset of the
 attributes that can be modified for a carousel. If you choose not to set
 values for these attributes their default values are used. For a full list
 of the attributes and their defaults, as well as further documentation on
 the AUI-Carousel, please visit <http://alloyui.com/api/classes/A.Carousel.html>.

---
 
Now that you've layed the groundwork for the carousel, it's time to give it some 
style.

## Step 2: Styling the Carousel's UI in `main.css`

There are a lot of moving parts involved with the carousel's UI. To keep things
organized, first customize your carousel's body and images. Then set the menu 
controls, the active and inactive index indicators, and finally the menu bar and 
list of controls. Below is a CSS map of the different elements for you to refer 
to:

![Figure 1: Here is a CSS map of the different carousel elements.](../../images/alloyui-carousel-css-map.png)

Now that you know the plan, you can go ahead and start with the styling of the
carousel's body and images.

### Styling the Carousel's Body and Images

Below is an example of a possible configuration for the carousel's body and 
images in the `main.css`:

        ```
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
        #image1
        {
        background-image: url("../img/1.jpg");
        }

        /* styling for div with id image2 */
        #image2
        {
        background-image: url("../img/2.jpg");
        }

        /* styling for div with id image3 */
        #image3
        {
        background-image: url("../img/3.jpg");
        }

        /* styling for div with id image4 */
        #image4
        {
        background-image: url("../img/4.jpg");
        }
        ```

The code starts off by setting the width and height for the carousel body, and 
giving it rounded edges with the `border-radius` property. Next, the carousel is 
centered in the middle of the portlet by setting the `margin` property of the 
`boundingBox` element (`#mycarousel` in this case) to `auto` for the left and 
right margins; the top margin is set to 0, while the bottom margin is set to 
40px to leave room for the menu's controls; if you do not wish to change the 
default border-radius and margins, you can omit these properties. Finally the
images are given a `background-image` property that points to their 
corresponding urls.

---

 ![Note](../../images/tip.png) **Note:** you can insert your own images by 
 placing them in a newly created `/docroot/img/` directory of your portlet.

---

Now that you have taken care of the styling for the body and images of the 
carousel, it's time to make the menu controls shine!

### Styling the Carousel's Menu Controls

Each menu control has an existing class that you need to identify in order to 
style it. You can identify the classes for the controls by viewing the 
DOM(Document Object Model) tree for them. Below is a simplified version of the 
DOM tree:

        ```
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
        ```

Although this is a simplified version of the DOM tree, it gives you an idea of 
the overall structure. You can see that the carousel menu controls lie within 
the `#myCarousel` div, inside a menu tag, inside a list tag, inside an anchor 
tag.

---

 ![Note](../../images/tip.png) **Note:** You can view the DOM tree by 
 right-clicking the carousel's *next menu button* and inspecting the element in 
 the browser (Firefox in this case).
 
---

Once you understand the DOM tree, you can go ahead and place the styling for
each of the menu buttons with their corresponding class. Each menu button has a
`background` property set to its image location and a `border-radius` property
for rounded edges; if you don't want a rounded edge you can omit the 
`border-radius` property from your styling. Below is example code of possible 
styling for the menu controls in `main.css`:

        ```
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
        ```

Once again, if you don't wish to have rounded edges you can skip the
`border-radius` property. Also remember to place the images you want to use for 
the menu controls in the `img` folder. Onward to the active and inactive index 
indicators!

### Styling the Carousel's Active and Inactive Index Indicators

As with the menu controls, the active and inactive index indicators are styled,
using existing classes. Please refer to the DOM tree to see the layout for 
yourself. Below is example code for the active and inactive index indicators in 
`main.css`:

        ```
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
        ```

The images are set with the `background-image` property and given the proper 
height and width. They are then assigned a color with the `background-color`
property. Again, remember to place the images you want to use for these 
indicators in the `img` folder. Now that you've taken care of the index 
indicators, it's time for one final style.

### Styling the Carousel's Menu Bar and Menu Controls List

As with the other styles, you can view the existing class for the menu bar and
controls list in the DOM tree. Below is one example for the code in `main.css`:

        ```
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
        ```
 
The left edge of the menu bar is set 518 px from the left. The top edge of the 
menu bar is set 250 px from the top, placing it just beneath the 250 px height 
carousel. The menu bar's width is scaled down to 26% the size of the carousel's 
width. To finish out the CSS, the list, which holds the menu controls, is set to 
adjust to the size and shape of the menu bar.

Now that you've completed the CSS, I think it's safe to say that this carousel 
has got some serious style! One final step to go!

## Step 3: Deploying the Portlet

You've layed the foundation, written the script, and given this carousel a fresh 
new look. Now that the hard part is over, go ahead and deploy your portlet to 
your portal instance and give yourself a pat on the back. If you are modifying 
an existing portlet that has already been deployed, you may need to undeploy and
redeploy it in order for the changes to take full effect.

## Summary

You learned how to take the basic `aui-carousel` module and modify it to suit 
your needs: First, you learned how to set some basic attributes to run the 
carousel to your liking. Next, you learned how to re-design the carousel by 
setting the styles of the carousel's body and images. Finally, you learned how 
to style the carousel's menu and controls to match your taste.

Excelsior! You have successfully learned how to give Alloy's Carousel your own 
flare and style!

Here is an example of a customized carousel using the configuration above:

![Figure 2: Here is an example of a customized carousel, using the scripting above.](../../images/alloyui-customized-carousel-in-portlet.png)

You can access a finished version of the customized portlet at 
<https://github.com/liferay/liferay-docs/tree/master/devGuide/code/12-working-with-alloyUI/customized-carousel-portlet>

Remember, your carousel is whatever you make it, so make it a good one!
