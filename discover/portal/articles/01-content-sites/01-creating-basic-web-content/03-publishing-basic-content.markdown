# Publishing Basic Content [](id=publishing-basic-content)

<div class="learn-path-step">
    <p>Creating Basic Web Content<br>Step 3 of 3</p>
</div>

Before you publish content, it's useful to know the content hierarchy in
@product@. You can loosely think of this as the scaffolding that @product@ uses
to display content.

-   A Site (like The Lunar Resort) contains pages and content.

-   Pages contain applications. 

-   You can publish a site's content in an application on a page in the site.
    For example, the Web Content Display and Asset Publisher applications can
    display a site's content once they're added to a page. 
    
<div class="video-link">
<img src="../../../images/vid-publishing-basic-content-thumbnail.png" alt="video-thumbnail"/>
</div>

If you followed the previous steps, you have a site that contains content and
a page. Now you'll publish that content on that page. 

1.  Return to The Lunar Resort's Welcome page. If you're still on the Web 
    Content page after creating the web content in the previous step, click *The
    Lunar Resort* in the top-left corner of the screen. 
 
2.  Open the *Add* menu. Select *Content* if it's not already selected.

    ![Figure X: The Add menu with your content.](../../../images/001-add-menu-content.png)

3.  Drag and drop *Lunar Resort Welcome* onto the page above the *Hello World*
    portlet.

Behind the scenes, @product@ placed your content inside a Web Content Display
application and added that application to the page. You could have done this 
manually by adding a Web Content Display application to the page and then 
configuring it to display the content. But it's much easier to let @product@ do 
it for you!

![Figure X: Your content on a page.](../../../images/001-basic-content-on-page.png)

## Cleanup [](id=cleanup)

You probably noticed a couple of sub-optimal things about your page: your 
content's title looks a bit odd, and the Hello World portlet is unnecessary now 
that the page has content. You'll fix this now.

<!-- Russ QA: So we're supposed to rename the Liferay Site to The Lunar Resort? Guessing
this is so if the Hello World Portlet is deployed there by default. I don't see how this is
done though.-->

1.  Mouse over your content to reveal the portlet bar.

2.  Click the portlet menu icon and select *Look and Feel Configuration*.

3.  Under *Application Decorators* select *Barebone*. This removes the content's 
    title, background, and border from the content display. Click *Save*.

    ![Figure X: Change the portlet's look and feel.](../../../images/001-select-barebone.png)

4.  Scroll down to the bottom of the page and mouse over the *Hello World* 
    portlet.
 
5.  Click the portlet menu icon and select *Remove*.

6.  Click *OK* when asked if you want to remove the component.

Great work! You've created content, displayed it on a page, and done some basic 
page management. Now that you know the basics, you'll dig deeper and create some 
more pages to fill with content.

<div class="video-tag" data-name="Publishing Basic Content">
  <video width="100%" height="100%" controls>
    <source src="https://dev.liferay.com/documents/10184/1058908/publishing-basic-web-content.mp4" type="video/mp4">
    <source src="https://dev.liferay.com/documents/10184/1058908/publishing-basic-web-content.mkv" type="video/webm">
    Your browser does not support HTML5 video.
  </video>
</div>
