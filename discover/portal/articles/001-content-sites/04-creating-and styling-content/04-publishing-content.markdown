# Publishing Content with Content Display Pages

So far you've primarily published content directly by adding it manually. Next,
you'll look at some other options for adding and displaying content using
Content Display Pages.

## Content Display Pages

Content Display Pages are a way that you can give content a "home." Content in 
Liferay lives in the Web Content library, and can be displayed on any page 
within the site, but with Content Display Pages, you provide a specific page 
which is only used for viewing content. You can craft a single page which is 
the home for multiple pieces of content, or you can create a single display
page for a single piece of content. First, let's create a simple content display
page which can be used for one piece of content.

### Creating a Content Display Page

First, you'll create the content display page. 

1. Open the main menu.

2. Open the *Navigation* section. 

3. Click the menu option next to *Public Pages* and select *Add Public Page*.

4. For the *Name* enter "Hazard Disclaimer."

5. For the *Type* select *Content Display Page* from under *Templates*.

6. Set *Hide from Navigation Menu* to *Yes*.

7. Set *Inherit Changes* to *No*.

8. Click *Add Page*.

This creates a Content Display Page that is hidden from the main navigation. 
Once you're done setting everything up, the only way to access the page will be
to click on a link to the content.

### Creating Content to Display

Now, create the content to be displayed on the page.

1. Go to *Content* &rarr; *Web Content* in Site Administration.

2. Click the `+` button in the bottom right corner of the screen and select
    *Basic Web Content*.

3. For the *Title* enter "Hazard Disclaimer"

4. For the content, enter the following:

        Potential Hazards of Space Travel and Lunar Exploration include but are not limited to:
        
        Accidental ejection from airlock into the cold vacuum of space
        Spacesuit failure
        Spacecraft engine explosion
        Running and jumping so fast that you achieve escape velocity and are no longer bound by the moon's gravity
        Accidental exposure alien spores or eggs
        Collisions with stray meteorites
        Excess exposure to solar radiation
        Muscular atrophy
        
        Neither the The Space Program, The Lunar Resort nor any of the their subsidiaries are responsible for injury or harm caused by these or similar accidents.

5. Open the *Display Page* section and click *Choose*.

6. Select the *Hazard Disclaimer* page.

7. Click *Publish*.

Now you have the content created, next you'll configure the link to the content
to display which will direct users to the content display page.

### Using Content Display Pages

To display the content, add an Asset Publisher to the booking page and configure
it to display a link to our new web content in context.

1.  Go to the *Book a Trip* page.

2.  Open the *Add* menu.

3.  Add a *Content Management* &rarr; *Asset Publisher* to the page.

4.  Click on the title of the Asset Publisher to edit it and change it to 
    "Waivers and Disclaimers"

5.  Open the Asset Publisher *Configuration*.

6.  Under *Asset Selection* choose *Manual*

7.  Open *Asset Entries* and click *Select* and choose *Basic Web Content*

![Figure x: Selecting individual content for display.](../../images/001-select-basic-web-content.png)

8.  On the next page, select *Hazard Disclaimer*.

9.  Click *Save*.

10. Click on *Display Settings*.

11. Under *Asset Link Behavior* select *View in Context*.

12. Click *Save* and close the configuration window.

You will see the content with a link that will take you to the display page:

![Figure x: Your content in the asset publisher.](../../images/001-view-disclaimer.png)

### Viewing Content in Context

Now that you created the content, created the display page, and configured the
content in the Asset Publisher to link to the display page, you can see your 
content displayed in context. The content display page also provides you with a 
friendly URL to your content that you can share.

Right now you're using the default configuration for the display page, but you 
could also configure your content display page to look however you want, or turn
an existing page into a content display page. That's what you'll do next.
