# Nesting Pages

When creating sites, you'll likely encounter situations in which you want to 
nest pages under other pages. Such child pages (also called nested pages) let 
you create page hierarchies to organize content and functionality. For example, 
the pages for each lunar guide should be nested under the Lunar Guides page. 
Although this is a simple use case, note that pages in @product@ can be nested 
to virtually unlimited levels. This lets your site support even the most 
demanding hierarchies. 

In this article, you'll nest the existing lunar guide page for Cody under the 
Lunar Guides page. You'll then create more lunar guide pages as child pages of 
the same Lunar Guides page. 

## Creating Child Pages

There are two ways to create a child page in @product@: 

1.  Create a new page as a child page of an existing page. This is the most 
    common way to create a child page.

2.  Turn an existing page into a child page of another existing page. You can 
    even do this via drag and drop. 

You'll start with the second option, since you already created a page (Cody) 
that you want nest under the Lunar Guides page. 

### Creating Child Pages with Drag and Drop

1.  Open the Main Menu and select *Lunar Resort* &rarr; *Navigation*. 

2.  Drag and drop *Cody* and so that it nests under *Lunar Guides*.

![Figure x: Nesting a page with drag and drop.](../../../images/001-drag-cody.png)

Pages can be dragged around and rearranged using the *Navigation* menu. Any 
changes made here are immediately applied to the main navigation structure. So 
now, the *Cody* page has been removed from the top level navigation, and can 
now be found as a sub-page of *Lunar Guides*.

![Figure x: The new nav hierarchy.](../../../images/001-nav-hierarchy-1.png)

### Creating New Sub-pages

Now you're going to create three more Lunar Guides pages. Each will be nested 
under the *Lunar Guides* page and each will be created using the *Lunar Guide 
Page* template.

1. Click on the menu for *Lunar Guides*.

2. Select *Add Child Page*

3. Name the page "Jim" and set the *Type* as *Lunar Guide Page*.

4. Leave *Inherit Changes* set to "Yes".

5. Click *Add Page*

6. Repeat these steps to create additional pages for "Steve" and "Russ".

Each page is created as specified by the template, with the Blogs to the right
and an empty space to the left.

![Figure x: All the nested pages.](../../../images/001-all-nested-pages.png)

Now that all of the pages are created, let's take a closer look at navigation 
and the various features of Liferay's Breadcrumb and Navigation portlets.
