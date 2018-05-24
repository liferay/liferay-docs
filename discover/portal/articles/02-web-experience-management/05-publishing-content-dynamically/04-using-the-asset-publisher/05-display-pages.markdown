# Display Pages [](id=content-display-pages)

Each web content entry in Liferay has a canonical URL, which is the official 
location of the content that is referenced any time the content is displayed. A 
display page can be any page with an asset publisher configured to display any 
content associated with the page. When adding or editing web content, you can 
select a display page, but only pages with a configured asset publisher are 
available for selection.

To create a display page:

1.  Create a page.

2.  Add an Asset Publisher widget to the page.

3.  Open the configuration.

4.  Go to the Display Settings tab and check the *Set as the Default Asset 
    Publisher for This Page* box.

5.  Also in Display Settings, set the Display Style to *Simple* and the Asset 
    Link Behavior to *View in Context*.

Alternatively, you can use the *Content Display Page* page template included with @product@.

+$$$

**Note:** Web content linked in the Asset Publisher can be viewed by clicking
their asset links. With the *View in Context* behavior checked, the link
displays the web content in its configured display page. If the web content does
not have a configured display page, it is displayed in the Web Content Display
application to which the asset belongs.

$$$

You may now be thinking, "Wait, you just told me that each web content item has
its own URL, and that this is somehow related to pages where we display a whole
bunch of content on the same page?" That's right. Just watch--create a display
page called *My Web Content Display Page* somewhere in Liferay using the
*Content Display Page* template. Make sure the *Inherit Changes* selector is not
selected. Now, on a different page, add a Web Content Display application. Click
the *Add* button, enter a title and some content, click on *Display Page* at the
right, and select the Display Page you just created. Then click *Publish*.

![Figure 5: You can select a display page for a web content article when creating or editing one.](../../../images/web-content-display-page.png)

In the Asset Publisher of the *My Web Content Display Page*, click the *Read
More* link to display the full content. Notice that the canonical URL for
content appears in your browser's address bar. If you create your own custom
display page, any additional applications that you place on the page are
displayed along with the content when you access it via the canonical URL. If
you used the *Content Display Page* page template for your display page, it not
only features a configured Asset Publisher application but also a Tags
Navigation, a Categories Navigation, and a Search application. These tools help
users to quickly identify relevant content.

![Figure 6: A canonical URL for a *Lunar Resort Information* page would look similar to this screenshot.](../../../images/web-content-canonical-url.png)
