# Displaying Basic Content

Before learning how to display content, it's useful to know the content 
hierarchy in @product@. You can loosely think of this as the scaffolding that 
@product@ uses to display content.

-   All content exists within a site (like The Lunar Resort).

-   All pages (like the Lunar Resort's Welcome page) exist within a site.

-   You can display a site's content on a page in that same site.

-   All content is displayed inside of an application on a page. For example, 
    the Web Content Display and Asset Publisher applications can display a 
    site's content once they're added to a page. 

If you followed the previous steps in this Learning Path, then you have a site 
that contains content and a page. Now you'll display that content on that page. 

1.  Return to The Lunar Resort's Welcome page. If you're still on the Web 
    Content page after creating the web content in the previous step, you can do 
    this by clicking *The Lunar Resort* in the top-left corner of the screen. 
    
2.  Open the *Add* menu. Select *Content* if it's not already selected.

![Figure X: The Add menu with your content.](../../../images/001-add-menu-content.png)

3.  Drag and drop *Lunar Resort Welcome* onto the page, above the *Hello World*
    portlet.

Behind the scenes, @product@ placed your content inside of a Web Content Display
application, and added that application to the page. You could have done this 
manually by adding a Web Content Display application to the page and then 
configuring it to display the content. But it's much easier to let @product@ do 
it for you!

![Figure X: Your content on a page.](../../../images/001-basic-content-on-page.png)

## Cleanup

You probably noticed a couple of sub-optimal things about your page--your 
content's title looks a bit odd, and the Hello World portlet is unnecessary now 
that the page has content. You'll fix this now.

1.  Mouseover your content to reveal the portlet bar.

2.  Click the portlet menu icon and select *Look and Feel Configuration*.

3.  Under *Application Decorators* select *Barebone*. This removes the content's 
    title, background, and border from the content display. Click *Save*.

![Figure X: Change the portlet's look and feel.](../../../images/001-select-barebone.png)

4.  Scroll down to the bottom of the page and mouseover the *Hello World* 
    portlet.
    
5.  Click the portlet menu icon and select *Remove*.

6.  Click *OK* when asked if you want to remove the component.

Great work! You've created content, displayed it on a page, and done some basic 
page management. Now that you know the basics, you'll dig deeper and create some 
more pages to fill with content.
