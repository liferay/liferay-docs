# Creating Web Content Templates

You created a Web Content Structure, but it won't be very useful without a
template to go with it and tell it how to display and style the items inside of 
it. Now you'll use the Freemarker Templating language to create the necessary framework to display the content.

## Creating Your First Web Content Template

1. Go to Web Content for The Lunar Resort in Site Administration.

2. Open the menu in the top right and select *Templates*.

3. Click the '+' button to create a new template.

Now you'll find yourself on the Template creation page. In the Details section, 
you'll need to specify a name, choose a templating language, and link the 
Template to the Structure that it's intended to work with. In the Script 
section you'll use the Template editor to create your template with CSS, HTML, 
Javascript, and your templating language of choice (in this case, you will be 
choosing Freemarker). The sidebar will also provide variables that you can 
quickly drag into the code area. After you link a Structure, you can even 
directly access the Structure's field from the sidebar.

1. Set the *Name* as "Lunar Guides List".

2. Open *Details*, click *Select* under *Structure* and select the
    *Lunar Guides List* structure.

First create the CSS for the content. You'll be using a simple grid layout to 
define the positions of all the fields. You'll also define the styles for the
text headings.

3. Clear the code pane.

4. Insert the following code into the code pane:

        <style>
           .wrapper {
          display: grid;
          grid-template-columns: 300px, 300px, 300px, 300px;
          grid-gap: 10px;
          grid-auto-rows: minmax(auto, auto);
          text-align: center;
        }

        h1 {
            text-align: center;
        }

        h2 { 
           position: relative;
           color: black;
        }

        .item-one {
          grid-column: 2;
          grid-row: 1;
          max-width: 355px;
        }

        .item-two { 
          grid-column: 4;
          grid-row: 1;
          max-width: 355px;
        }

        .item-three {
          grid-column: 2;
          grid-row: 2;
          max-width: 355px;
        }

        .item-four {
          grid-column: 4;
          grid-row: 2;
          max-width: 355px;
        }
        </style>    
    
You created a 4 column, 2 row grid. The content will only be in columns 2 and 
4, while the other two rows exist for spacing. There are a number of other ways 
you could achieve the same result.

Next you need to add in the HTML portion. The sidebar can help you with adding 
the fields with the appropriate variables. *General Variables* provides quick 
access to universal information for your instance. *Fields* provides access to 
the variables that are defined by your Structure. Mousing over a field will 
display it's name. Clicking on a field will add the code to retrieve that data into your code.

![Figure x: IMAGE](../../../../images/001-field-mouse-over.png)

5. Add the following code block below the style section:

        <h1>${title.getData()}</h1>

        <div class="wrapper">
          <a class="item-one" href="${link1.getFriendlyUrl()}">
          <h2>${name1.getData()}</h2>
            <#if image1.getData()?? && image1.getData() != "">
        	 <img alt="${image1.getAttribute("alt")}" src="${image1.getData()}" />
            </#if>
          </a>

          <a class="item-two" href="${link2.getFriendlyUrl()}">
          <h2>${name2.getData()}</h2>
            <#if image2.getData()?? && image2.getData() != "">
        	 <img alt="${image2.getAttribute("alt")}" src="${image2.getData()}" />
            </#if>
          </a>

        <hr />
          
          <a class="item-three" href="${link3.getFriendlyUrl()}">
          <h2>${name3.getData()}</h2>
            <#if image3.getData()?? && image3.getData() != "">
        	 <img alt="${image3.getAttribute("alt")}" src="${image3.getData()}" />
            </#if>
          </a>

          <a class="item-four" href="${link4.getFriendlyUrl()}">
          <h2>${name4.getData()}</h2>
            <#if image4.getData()?? && image4.getData() != "">
        	  <img alt="${image4.getAttribute("alt")}" src="${image4.getData()}" />
            </#if>
          </a>

        </div>

This code gets the field values that the user entered, styles the text and 
image, and puts all the information into divs as defined in your CSS.

6. Click *Publish* to save your work.

It's as simple as that. Create some styles, create some HTML, use the fairly 
simple Freemarker functions to retrieve the necessary data, and you have a 
template. Next you'll need to create the content that fills out the template.

