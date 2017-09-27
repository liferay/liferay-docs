# Site Navigation

Within a given site, you have several options for navigation. You can manage
navigation via the theme,

## Theme Navigation

The primary navigation that you use in the Lunar Resort is provided by the 
theme. In the case of the Liferay Classic theme, a Navigation portlet is configured and imbedded in the theme. That navigation will appear on every page that you create, providing a uniform feel through the site.

In some cases you need to provide additional navigation options, and that's 
where the Breadcrumb and Navigation portlets come in. You can use them to 
provide customized navigation around the site.

## Portlet Based Navigation

Let's add additional navigation options in two places. First you'll add 
footer navigation to the Welcome page, to make it easier for users to access the
navigation if they've scrolled down below the fold. Then you have a space to add
a Breadcrumb portlet to the the Lunar Guide Page template that you created earlier.

### Navigation Portlet

1. Go to the *Welcome* page for the *Lunar Resort*.

2. Open the *Add* menu and select *Applications*.

3. Find the *Navigation Menu* portlet from the *Content Management* category.

4. Drag it onto the page below the existing content.

Now you have the Navigation Menu on the page, but it could use a little bit of 
styling help. Next, remove the border decoration, and change the layout 
configuration.

1. Open the *Look and Feel Configuration*.

2. Set the *Application Decorators* to *Barebone*, click *Save*, and close the
    dialog box.

3. Open the *Configuration* menu for the portlet.

4. For *Display Template*, choose *Bar minimally justified styled*.

5. Click *Save* and close the dialog box.

You now have an almost seamless footer navigation for you Welcome page, just like that!

### Breadcrumb Portlet

To add the Breadcrumb, you can use the power of Page Templates to add one to 
all of the template controlled pages.

1. Open the main menu, and go to *Control Panel* &rarr; *Sites* &rarr; *Page
    Templates

2. Click on *Lunar Guide Page*.

3. Add a *Breadcrumb* application from the *Content Management* category to the
    left column.
    
4. Open the portlet menu for the Breadcrumb and select *Look and Feel 
        Configuration*.
    
5. Change the *Application Decorators* to *Barebone*.

6. Close the Page Template editing tab or window.

7. Navigate to one of the Lunar Guide individual pages.

![Figure x: The new nav hierarchy.](../../../images/001-breadcrumb-jim.png)

The Breadcrumb will show you your current page and any pages above it in the
hierarchy. Since the default Navigation will only show you the top two levels 
of pages, the Navigation and Breadcrumb portlets are invaluable for any 
situation with deeper page nesting.

Now you've created the basic framework for your site, with pages and navigation, next you need to fill it with content.
