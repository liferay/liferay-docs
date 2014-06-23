# Using AlloyUI Carousel in Your Portlet

Image carousels are often the first thing people see when they visit sites. They
provide an interactive way of cycling through visual elements and are an
effective means of communicating information to users. AlloyUI's `aui-carousel`
module makes it very easy to get an image carousel up and running in no time
flat.

Here is an example of the carousel in a portlet:

![Figure 1: Image carousels can be a very handy tool to communicate information to users. Here is an example of the carousel in a portlet.](../../images/alloyui-carousel-in-portlet.png)

Now that you can see the appeal of adding a carousel to your design, it's time
to get down to business!

## Adding A Carousel to A Portlet

In order to get the carousel running in a portlet you have to do a few
things:

1.  Configure your portlet's `view.jsp`.
2.  Create a CSS file to display your carousel.
3.  Finally, deploy the portlet.

Go ahead and configure your portlet's view.jsp first.

### Step 1: Configure Your Portlet's `view.jsp`

First, you need to setup the `view.jsp` of your portlet to use the `aui-carousel`
module. The following example code shows how the `view.jsp` could look:

        ```
        <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

        <div id="myCarousel">
          <div id="image1"></div>
          <div id="image2"></div>  
        </div>
        
        <portlet:defineObjects />

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
        
The carousel automatically adjusts to the number of images in the `<div>`, so 
feel free to add more. If you deploy the portlet now, you'll notice that no 
images are displayed. This is because you need to write some CSS to tell the 
portlet how to display the carousel. You also need to define the file paths to 
the images defined by the image id's. These things are taken care of in the next 
step. 

### Step 2: Create A `main.css` File to Display Your Carousel

Create a `main.css` File in your portlet's `/docroot/css/` directory if it does 
not exist. Here is example code of what the `main.css` file could look like: 

        ```
        /* styling for the carousel body */
        div.carousel-item 
		{
			width: 700px;
			height: 250px;
			opacity: 100;
		}
		
		/* styling for div with id image1 */
		#image1
		{
			background-image: url("../img/YourImageFile1.jpg");
		}
		
		/* styling for div with id image2 */
		#image2
		{
			background-image: url("../img/YourImageFile2.jpg");
		}
        ```
        
The CSS is setup with some basic width, height, and opacity information for the 
divs with the class `carousel-item`. If you decide to use a different class, 
just be sure that the classes are the same in both the `view.jsp` and 
`main.css`. 

Also note that you need to replace `YourImageFile1.jpg` and `YourImageFile2.jpg` 
with the file names of your images in the `/docroot/img/` directory. If you 
don't yet have a `/docroot/img/` directory, create it and put your image files 
in it. 

Now that you have written the code to display the carousel, there is one last 
step. 

### Step 3: Deploy Your Portlet to Your Portal

Upon deployment you can see that your images display correctly. If your portlet 
is already deployed, you may need to undeploy and then redeploy it before your 
images show up. Give yourself a pat on the back. You've just successfully used 
the `aui-carousel` in a portlet! 

## Next Steps
<!-- URL needs updated to proper one once it is added to LDN-->
<!--[Customizing AlloyUI Carousel](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/customizing-alloyui-carousel-in-a-portlet)-->
