# Application Display Templates [](id=application-display-templates)

<div class="learn-path-step">
    <p>Creating Content<br>Step 6 of 7</p>
</div>

For the final piece of the site that you'll construct with Liferay's Web
Experience Management system, you need to create a more engaging front page
graphic. To do this, you'll use multiple images and an Application Display 
Template (ADT) to display them as a carousel.

ADTs are essentially the same as Web Content Template, but rather than have the 
variables and fields defined by a structure, they're defined by the application 
that you're applying the template to.

## ADTs and Scope [](id=adts-and-scope)

Liferay comes bundled with several default ADTs which exist at the 
*Global* scope. Scope, in @product@ simply means where content can be used or 
viewed. All of the content that you have created so far has been in the scope 
of The Lunar Resort site. If you were to create a second site on the same 
Liferay server, you would not be able to access content that you created for
The Lunar Resort in the second site, but everything created in the Global scope 
is available for both.

To create your own ADT, switch to the Global scope and create it there alongside
all of the system default ADTs.

## Creating the ADT [](id=creating-the-adt)

1. Open the main menu.

2. Next the *The Lunar Resort*, click the compass icon to open the *Select Site*
    dialog.
    
3. From there, click *My Sites* and then select the *Global* site.

![Figure x: Site selection.](../../../images/001-select-site.png)

Everything you create in this context will be for the Global scope. Now to 
create the ADT.

4. Click on *Configuration* &rarr; *Application Display Template*.

5. Click the `+` button and select *Documents and Media Tempalte* to add yours 
    to the list.

6. Name the template *Frontpage Carousel*.

7. In the code area, paste in the styles first:

        <style>
        	  .slides {
        	    margin: auto; 
        	    width: 100%;
        	    height: auto;
        	    border-radius: 5%;
        	  }
	  
        	  h1 { 
               position: absolute;
               color: white;
               top: 100px; 
               width: 100%; 
               text-align: center;
               font-size: 50px;
              }
        </style>

    You have a style which manages the slides and a style that manages the 
    heading text.
    
8. Then add the main HTML section:

        <#if entries?has_content>
        	<div id="<@portlet.namespace />carousel">
        		<#assign imageMimeTypes = propsUtil.getArray("dl.file.entry.preview.image.mime.types") />

        		<#list entries as entry>
        			<#if imageMimeTypes?seq_contains(entry.getMimeType())>
            	      <img class="slides" src="${dlUtil.getPreviewURL(entry, entry.getFileVersion(), themeDisplay, "")}">
        			</#if>
        		</#list>
        		    <div class="flex-container" style="height: 100%">
                        <h1>Make Memories at the Lunar Resort<h1>
                      <div class="flex-item-center text-center" style="width: 100%" />
                    </div>  
        	</div>
    
    This calls the script (which you'll add momentarily) that sets the mime
    type for display (images), gets the list of `entries` that will be
    displayed, and creates the container. It's all wrapped in an `#if`
    statement which will only display this block if there are entries to
    display.
    
9. Next add the script:

        <script>
        	var slideIndex = 0;
                carousel();
            function carousel() {
            var i;
            var x = document.getElementsByClassName("slides");
            for (i = 0; i < x.length; i++) {
              x[i].style.display = "none"; 
            }
            slideIndex++;
            if (slideIndex > x.length) {slideIndex = 1} 
            x[slideIndex-1].style.display = "block"; 
            setTimeout(carousel, 3000); // Change image every 3 seconds
            }
        </script>
        </#if>
    
    This is a fairly straightforward Javascript carousel. You get the slides,
    and iterate through them to display them.
    
Once you Save the template, you're all done creating the ADT. Now let's go set
it up.

## Using the ADT [](id=using-the-adt)

To use the ADT, you'll add a Media Gallery to the page, point it at the 
appropriate folder, and set it to use the display template we created.

1. Go back to the Lunar Resort homepage.

2. Remove the Web Content Display from earlier by clicking on the portlet menu
    and selecting *Remove*, then *OK* when prompted.

3. Open the add menu, go to *Applications* and add a *Content Management* 
    &rarr; *Media Gallery* to the page.

4. Open the portlet menu for the Media Gallery and select *Configuration*.

5. Scroll down to *Folders Listing* and click *Select*.

6. Click *Choose* to select the *Frontpage Images* folder.

7. For *Display Template* select *Frontpage Carousel*

8. Click *Save*.

9. Now go to *Look and Feel Configuration* and set the *Application Decorator* 
    to *Barebone*

![Figure x: Site selection.](../../../images/001-final-frontpage.png)

Good work! Your Welcome page is complete! 
