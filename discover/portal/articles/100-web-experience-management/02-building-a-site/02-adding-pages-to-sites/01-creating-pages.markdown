# Creating Pages [](id=creating-pages)

After you create a site for your Liferay instance, you'll want to add new 
pages or edit existing pages. You can do everything you need with pages from 
Site Administration.

1.  If you're not currently on the site you want to edit, click the *Site 
    Selector* button (![Compass](../../../../images/icon-compass.png)) next to 
    your current site name in the Menu and select your desired site.

2.  Go to *Site Administration* &rarr; *Build*.

3.  Click on *Site Pages*
 
![Figure 1: The Sites Pages page allows you to edit your site pages as a whole.](../../../../images/managing-site-pages.png)

From here, you'll create pages and page templates.

+$$$

Note: Pages are always part of page sets, and page sets are always associated with sites. Even users' personal pages are part of their personal sites. All pages belong to one of two types of page sets: public pages and private pages. By default, public pages are accessible to anyone, even non-logged in users (guests). Private pages are accessible only to users who are members of the site that owns the pages. This means the private pages of an organization's site are viewable only by site members and members of the organization. 

$$$

From *Site Pages* you can do several things:

1.  Click the `+` button in the top right corner to add a new page.

2.  Click *Filter and Order* to select whether you want to create pages in the Public or Private page sets using the selector.

3.  Create child pages by click the `+` button next to an existing page.

![Figure X: Understanding the options on Site Pages.](../../../../images/site-pages-breakdown.png)

Adding a child page creates child pages in the hierarchy below the page you've 
selected. You can nest pages as deep as you like and you can manage that 
hierarchy through Navigation Menus. 

Once you've clicked the `+` icon to add a page, you're asked to select the type
of page you are creating. There are two top options followed by other page 
types. First you have:

**Widget:** creates a page with a layout template that defines the number of rows and columns for adding widgets to your page.

**Content:** creates a Content Page with inline editing based on a Fragments.

Below those you have:

**Full Page Application:** creates a page that displays a single full page
application.

**Page Set** creates a container that you can create subpages under that is not 
actually a page itself. 

**Link to a Page of this Site** links to a Page within the same site. This is 
often used to make a page available it multiple parts of a sites hierarchy.

**Panel:** a page containing any number of applications as selected by an
administrator, but only one is displayed at a time. Users select the portlet
they want to use from a menu on the left side of the page, and the selected
portlet takes up the entire page. 

**Embedded:** displays content from another website inside your instance. An
administrator can set a URL from the page management interface and that page
appears in the context and within the navigation of your Liferay instance.

**Link to URL** creates a link to any URL. This could be an external page, or
a link across Sites in the same Liferay instance.

To the left, under Collections, you can also select between viewing the basic page types or viewing a collection of page templates. By default, only *Global Templates* appears, but any additional collections you create will appear here as well.

![Figure X: You must select a page type when adding pages.](../../../../images/page-types-adding.png)

After you've added a page, it may be difficult to track what kind of page 
you're currently viewing. The page type will display at the top of the page to 
help you determine what type of administration options you have available and where you need to go to configure the page.

![Figure X: Here are three different page with three different types as they as displayed in the heading.](../../../../images/page-type-guide.png)

Now that you know the basics of adding pages, you can start working on the
Lunar Resort site. If you're not currently on the right site, navigate to Site
Administration in the Menu, select the compass icon next to the current site
name, and select the site you wish to edit.

If you ever need to modify the page you've created for your site, select
*Configure* from the Options menu for the page from *Site Pages*. When
configuring a specific page, you're given more options than when you were
creating a new page. You can also read our [Configuring Sites and Pages](/discover/portal/-/knowledge_base/7-1/configuring-sites-and-pages).

There are also configuration options that are only available for either
individual pages or page groups only. You'll learn about options available for
both instances.

Next, you'll look at creating the main page types you'll use in Liferay.
