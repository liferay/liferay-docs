# Nesting Pages [](id=nesting-pages)

<div class="learn-path-step">
    <p>Creating A Site<br>Step 5 of 6</p>
</div>

When creating sites, you'll likely encounter situations where you want to nest
pages under other pages. Such child pages (also called nested pages) let you
create page hierarchies to organize content and functionality. For example, the
pages for each lunar guide should be nested under the Lunar Guides page.
Although this is a simple use case, note that pages in @product@ can be nested
to unlimited levels. This lets your site support even the most demanding
hierarchies, so long as you can design a UI to handle it. 

In this article, you'll nest the existing lunar guide page for Cody under the 
Lunar Guides page. You'll then create more lunar guide pages as child pages of 
the same Lunar Guides page. 

## Creating Child Pages [](id=creating-child-pages)

There are two ways to create a child page in @product@: 

1.  Create a new page as a child page of an existing page. This is the most 
    common way.

2.  Turn an existing page into a child page of another existing page. You can 
    even do this via drag and drop. 

You'll start with the second option, since you already created a page (Cody)
that you want to nest under the Lunar Guides page. 

### Creating Child Pages with Drag and Drop [](id=creating-child-pages-with-drag-and-drop)

Using a site's *Navigation* menu, you can nest pages via drag and drop. 
@product@ immediately applies any changes you make here to the site's navigation 
structure. Follow these steps to nest the *Cody* page under the *Lunar Guides* 
page: 

1.  Open the Main Menu and select *Lunar Resort* &rarr; *Navigation*. 

2.  Drag and drop the page *Cody* and so that it nests under the *Lunar Guides* 
    page.

    ![Figure x: Nesting a page with drag and drop.](../../../images/001-drag-cody.png)

3.  Refresh the page. The new page hierarchy now appears in the navigation bar. 

    ![Figure x: The page *Cody* is now nested under *Lunar Guides* page.](../../../images/001-nav-hierarchy-1.png)

Nice work! Next, you'll create the rest of the pages for the lunar guides as 
child pages of the *Lunar Guides* page. Cody is about to have some company. 

### Creating New Child Pages [](id=creating-new-child-pages)

The Lunar Resort's other lunar guides--Jim, Steve, and Russ--also need pages 
nested under the *Lunar Guides* page. You'll create these pages directly as 
child pages: 

1.  In the *Lunar Resort* &rarr; *Navigation* menu, click the *Options* button 
    (![Options](../../../images/icon-options.png)) for *Lunar Guides* and select 
    *Add Child Page*. 

2.  Name the page *Jim* and set its type as *Lunar Guide Page*. 

3.  Leave *Inherit Changes* set to *YES*, and click *Add Page*. 

4.  Repeat these steps to create pages for Steve and Russ. 

@product@ creates each page from the template, with the Blogs app in the right 
column and an empty space in the left column. Each page also appears in the 
navigation bar under the *Lunar Guides* page. 

![Figure x: Cody is no longer lonely!](../../../images/001-all-nested-pages.png)

Now that you've created all the pages, you'll learn more about site navigation 
and the various features of Liferay's Breadcrumb and Navigation apps. 
