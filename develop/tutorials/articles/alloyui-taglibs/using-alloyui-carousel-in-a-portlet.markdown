# Using AlloyUI Carousel in Your Portlet

Image carousels are often the first thing people see when they visit sites; they
provide an interactive way of cycling through visual elements and are a very
effective means of communicating information to users. AlloyUI's `aui-carousel`
module makes it very easy to get an image carousel up and running in no time
flat.

Here is an example of the carousel in a portlet:

![Figure 1: Image carousels can be a very handy tool to communicate information to users. Here is an example of the carousel in a portlet.](../../images/alloyui-carousel-in-portlet.png)

Now that you can see the appeal of adding a carousel to your design, it's time
to get down to business!

## Adding A Carousel to A Portlet

In order to get the carousel running in a portlet you'll have to do a few
things:

1.  Configure your portlet's `view.jsp`.
2.  Create a CSS file to display your carousel.
3.  Finally, deploy the portlet.

Go ahead and configure your portlet's view.jsp first.

### Step 1: Configure Your Portlet's `view.jsp`

You'll need to setup the `view.jsp` of your portlet to use the `aui-carousel`
module. The following example code shows what the `view.jsp` looks like for a
carousel that uses sample images:

        ```
        <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

        <div id="myCarousel">
          <div class="carousel-item" style="background: url(../img/1.jpg);"></div>
          <div class="carousel-item" style="background: url(../img/2.jpg);"></div>
          <div class="carousel-item" style="background: url(../img/3.jpg);"></div>
          <div class="carousel-item" style="background: url(../img/4.jpg);"></div>
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
        ```
The carousel automatically adjust to the number of images in the div, so feel 
free to add more. You can replace the example images with your own by placing 
them in the `/docroot/img/` directory of your portlet.

If you try to deploy the portlet now, you'll notice that no images are displayed; 
this is because you need to write some CSS to tell the portlet how to display 
the carousel, so you'll get to do that next.

### Step 2: Create A `main.css` File to Display Your Carousel

Create a `main.css` File in your portlet's `/docroot/css/` directory if it does 
not exist. Here is example code of what the `main.css` file could look like: 

        ```
        div.carousel-item 
        {
        width: 700px;
        height: 250px;
        }
        ```
The CSS is setup with some basic width and height information for the divs with 
the class `carousel-item`; if you decide to use a different class, just be sure 
that the classes are the same in both the `view.jsp` and `main.css`. Now that 
you have written the code to display the carousel, there is one last step.

### Step 3: Deploy Your Portlet to Your Portal

Upon deployment you can see that your images display correctly. Give yourself a 
pat on the back; you've just successfully used the `aui-carousel` in a portlet! 

Excellent! If you want to explore AlloyUI's carousel in more depth, you can 
check out the AlloyUI Carousel Custimization tutorial here: 
<!-- insert link to custimization tutorial here -->
