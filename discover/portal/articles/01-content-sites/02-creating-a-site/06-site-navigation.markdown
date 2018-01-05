# Site Navigation [](id=site-navigation)

<div class="learn-path-step">
    <p>Creating A Site<br>Step 6 of 6</p>
</div>

You could have the greatest site in the multi-verse, but if users can't navigate
it, it's all for naught. Fortunately @product@ provides extensive support for
customizing your site's navigation. There are two main ways to define and
customize site navigation: 

1.  In the site's theme. This is the primary and most powerful way to manage
    site navigation. Themes in @product@ can customize any aspect of a site,
    including its navigation. Defining site navigation in the theme provides
    a uniform look and feel across the site. 

2.  With apps. Navigation apps can define navigation on a page-by-page basis.
    For example, the Navigation Menu app displays a navigable page hierarchy of
    the site. You can select which page in the hierarchy to use as the root.
    There's also the Breadcrumb app, which displays the trail of pages in the
    hierarchy that lead to the current page. Like a trail of breadcrumbs in the
    woods, this app lets users retrace their steps so they don't get lost. 

These two ways of defining site navigation can be used together. Like page 
templates, themes can define which apps appear in a site. You've already seen an 
example of this in action without being aware of it. The Lunar Resort uses the
@product@'s default theme, Classic. This theme embeds the Navigation Menu app as
the site navigation bar. Therefore, the Lunar Resort's navigation bar is nothing
more than a Navigation Menu app configured to display the entire site's page
hierarchy. 

You won't change the theme the Lunar Resort uses, but you'll fine-tune the 
site's navigation by adding another Navigation Menu app and a Breadcrumb app. 

## Adding a Navigation Menu App [](id=adding-a-navigation-menu-app)

The current Navigation Menu app does a fine job of defining the Lunar Resort's 
navigation bar, so why would you want to add another one? For footer navigation,
of course! Once a page requires scrolling, the navigation bar disappears. It's
therefore useful to have another one at the bottom of the page. Follow these
steps to add and configure such a Navigation Menu app: 

1.  Go to the Lunar Resort's *Welcome* page. 

2.  Click the *Add* button 
    (![Add](../../../images/icon-add-app.png)) on the upper right and expand the 
    *Applications* &rarr; *Content Management* category in the menu. 

3.  Drag a *Navigation Menu* app onto the page below the existing content. 

4.  Mouse over the Navigation Menu app and click the app's *Options* menu 
    (![Options](../../../images/icon-app-options.png)) on the right hand side of 
    the portlet bar. Then select *Look and Feel Configuration*.

5.  Set *Application Decorators* to *Barebone*, click *Save*, then close the 
    dialog box. 

6.  Now select *Configuration* from the Navigation Menu app's *Options* menu 
    (![Options](../../../images/icon-app-options.png)). 

7.  For *Display Template*, select *Bar minimally justified styled*. 

8.  Leave the rest of the default settings alone, then click *Save* and close 
    the dialog box. 

The navigation footer should look like this: 

![Figure x: The Welcome page now has a navigation footer.](../../../images/001-nav-footer.png)

Great! Next, you'll add a Breadcrumb app. 

## Adding a Breadcrumb App [](id=adding-a-breadcrumb-app)

To add the Breadcrumb app, you'll leverage the power of Page Templates. Recall
that you created the Lunar Guide pages using a template with the Blogs app in
the right hand column of the page. Since @product@ propagates template changes
to the pages, you'll now change the template to add a Breadcrumb app to each of
the Lunar Guide pages. Follow these steps to do so: 

1.  Open the Main Menu and select *Control Panel* &rarr; *Sites* &rarr; *Page
    Templates*. 

2.  In the list of page templates, click *Lunar Guide Page*. 

3.  Add a *Breadcrumb* application from the Content Management category to the
    page template's left column (next to the Blogs application). 

4.  Open Breadcrumb app's *Options* menu 
    (![Options](../../../images/icon-app-options.png)) and select *Look and Feel 
    Configuration*. 

5.  Change *Application Decorators* to *Barebone*, then click *Save* and close 
    the dialog. 

6.  Close the page template editing tab/window. 

7.  Navigate to one of the lunar guide pages. The Breadcrumb now appears 
    alongside the Blogs app. 

    ![Figure x: After adding the Breadcrumb app to the page template, the app appears on each lunar guide page, to the left of the Blogs app.](../../../images/001-breadcrumb-jim.png)

As you've seen, the Navigation Menu and Breadcrumb apps help users traverse page 
hierarchies. These apps are invaluable for any site with complex page 
hierarchies. 

Awesome! Now that you've created your site's framework with pages and
navigation, you'll fill the site with content. 
