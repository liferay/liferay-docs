# Displaying Basic Content

The basic hierarchy for displaying content in Liferay goes something like this: 

* All content exists within a site (like The Lunar Resort).

* All pages also exist within a site (like the Welcome page for the Lunar 
    Resort).

* You can display content from a site on a page in that same site.

* All content is displayed inside of an application like the Web Content Display
    or Asset Publisher.
    
You have a site. You have a page. You have content. Let's add that content to 
that page.

1. Click on *The Lunar Resort* in the top left corner of the screen to go back 
    to the *Welcome* page.
    
2. Open the *Add* menu. *Content* will be selected.

![Figure X: The add menu with your content.](../../../images/001-add-menu-content.png)

3. Click and drag *Lunar Resort Welcome* onto the page above the *Hello World*
    portlet.
    
Behind the scenes, Liferay placed your content inside of a Web Content Display
application, and added that to the page to display your content. You could have also added a Web Content Display application to the page yourself and configured it to display this content.
    
![Figure X: Your content on a page.](../../../images/001-basic-content-on-page.png)

## Cleanup

You probably noticed a couple things - the way the title of the content is 
displaying is a little awkward, and the "Hello World" portlet is unnecessary at 
this point. You can fix that.

1. Mouseover your content to reveal the portlet bar.

2. Click on the portlet menu icon and select *Look and Feel Configuration*.

3. Under *Application Decorators* select *Barebone*. This will remove the 
    content title, background, and border from the content display.

![Figure X: Your content on a page.](../../../images/001-select-barebone.png)

4. Scroll down to the bottom of the page and mouseover the *Hello World* 
    portlet.
    
5. Open the menu and select *Remove*.

6. When prompted "Are you sure you want to remove this component?" click *OK*.

Great work! You've created content, displayed it on a page, and done some basic 
page management to make sure that it's displaying properly. Now that you know
the basics, we'll dig deeper and create some pages that you can fill with
content.
