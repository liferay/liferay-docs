# Using AlloyUI Carousel in Your Portlet [](id=using-alloyui-carousel-in-your-portlet)

Image carousels are often the first thing people see when they visit sites.
Carousels provide an interactive way of cycling through visual elements and are
an effective means of communicating information to users. AlloyUI's
`aui-carousel` module makes it very easy to get an image carousel up and running
in no time flat. 

Here is an example of a
[Carousel](http://alloyui.com/api/classes/A.Carousel.html) being used in a
portlet: 

![Figure 1: Image carousels can be a very handy tool to communicate information to users. Here is an example of the carousel in a portlet.](../../images/alloyui-carousel-in-portlet.png)

Now that you can see the appeal of adding a carousel to your design, it's time
to get down to business!

In order to get the carousel running in a portlet you have to do a few
things:

- **Step 1:** Add Images to Your Portlet for Displaying in Your Carousel 
- **Step 2:** Add a Carousel to Your View JSP
- **Step 3:** Write CSS Code to Configure Your Carousel and to Map Images to the
Carousel

Walk through each of these steps to get a carousel up and running in your
portlet. 

### Step 1: Add Images to Your Portlet for Displaying in Your Carousel [](id=step-1-add-images-to-your-portlet-for-displaying-in-your-carousel)

Create a location in your portlet to store your carousel's images and then copy
the images into that folder. For example, you could create a folder named `img`
in your portlet's `docroot` folder and then put your image files in it. 

### Step 2: Add a Carousel to Your View JSP [](id=step-2-add-a-carousel-to-your-view-jsp)

1.  Open your view JSP. Create one if you don't already have one in your
portlet. 

2.  Add a directive to reference the `aui` taglib:

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

3.  Add a `<div>` element to reference all of the images that you want to
include in your carousel. Within this `<div>`, nest a `<div>` for each of the
carousel images. Make sure to assign a unique ID to the outer `<div>` element
and each of the nested `<div>` elements. 

    For example, the following HTML specifies an `<div>` named *myCarousel*
    that holds references to an images named *image1* and *image2*:

        <div id="myCarousel">
          <div id="image1"></div>
          <div id="image2"></div>  
        </div>

4.  To create a [Carousel](http://alloyui.com/api/classes/A.Carousel.html) in
which to display your images, you first add `<aui:script>...</aui:script>` tags
at the bottom of your JSP. Within those tags, add code that instantiates the
Carousel, passing a reference to the the outer `<div>` as the value for
Carousel's `contentBox` element. 

    For example, the following code creates a Carousel for a `<div>` named
    *myCarousel*: 

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

    To create the Carousel, you get the `AUI` object to use the `aui-carousel`
    module to create a Carousel component. You specify values for the new
    Carousel's `contentBox`, `height`, and `width` attributes. 

    The Carousel that AlloyUI creates automatically adjusts to the number of
    images in the `contentBox` element, letting you add as many as images as you
    like. 

5.  Save the JSP file. 

If you were to deploy your JSP's changes right now, however, no images would be
displayed. This is because the display of the carousel's *items* is not
configured and the carousel has no reference to the image files. Don't worry;
you can use CSS to configure the items and map the image files to your JSP. The
next step shows you how to take care of these requirements. 

### Step 3: Write CSS Code to Configure Your Carousel and to Map Images to the Carousel [](id=step-3-write-css-code-to-configure-your-carousel-and-to-map-images-to-the-c)

This step's instructions demonstrate using CSS to configure the carousel's
items. 

1.  Create a `main.css` file in your portlet's `/docroot/css/` folder, if it
does not exist. Then, open the `main.css` file.

2.  Specify the dimensions and opacity to use for each carousel item's `div`.
The following example CSS specifies basic width, height, and opacity information
for the divs using a class named `carousel-item`. As you use classes, make
sure to keep the class names consistent between your JSP and the `main.css`
file. 

        div.carousel-item 
        {
            width: 700px;
            height: 250px;
            opacity: 100;
        }

    If you wish, you can add more styling to the carousel items via the
    `div.carousel-item` class.

3.  Select the ID of each of your JSP's image `<div>` elements and apply a
background image to each one. 

    For example, if you have `<div>` elements with IDs *image1* and *image2*,
    then you could map them to their respective image source files with code
    that looks like this:

		#image1
		{
			background-image: url("../img/YourImageFile1.jpg");
		}

		#image2
		{
			background-image: url("../img/YourImageFile2.jpg");
		}

    Of course, you can style each image `<div>` here as well. 

4.  Save the CSS file. 

Now that you've written code to display your carousel, you can deploy your
plugin and see your images in the carousel. Give yourself a pat on the back.
You've just successfully used the `aui-carousel` in your portlet! 

