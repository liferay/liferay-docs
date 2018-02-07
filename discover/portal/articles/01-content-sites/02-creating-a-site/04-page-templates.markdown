# Page Templates [](id=page-templates)

<div class="learn-path-step">
    <p>Creating A Site<br>Step 4 of 6</p>
</div>

As you've now seen, creating pages can be repetitive. Wouldn't it be great if
you could create multiple pages from a single template? Well guess what: page
templates in @product@ let you do exactly that! They also inherit future changes
to those pages. 

Now you'll use page templates in The Lunar Resort. First you'll create a page
template, then you'll use it to create several pages. Later, you'll make changes
to the template and see @product@ propagate those changes to the pages you
created from the template. 

## Creating a Page Template [](id=creating-a-page-template)

Use these steps to create a page template:

1.  Open the Main Menu and select *Control Panel* &rarr; *Sites* &rarr; *Page 
    Templates*. The Page Templates page lists all the page templates in the 
    @product@ instance. Three page templates come bundled with @product@. *Blog*
    and *Wiki* are example layouts for the Blogs and Wiki applications. *Content
    Display Page* serves a special function that you'll work with later. 

    ![Figure x: The Page Templates page.](../../../images/001-page-templates-screen.png)

2.  Click the *Add* button 
    (![Add](../../../images/icon-add.png)) in the lower right corner. This takes 
    you to the page template creation page. 

3.  Name the template *Lunar Guide Page*. 

4.  For the description, enter *Page with information about a Lunar Guide*. 

5.  Click *Save*. This takes you back to the list of page templates, where you 
    can see the new template in the list. 

## Editing a Page Template [](id=editing-a-page-template)

Editing a page template is similar to editing any page. You drag and drop
applications onto the page and reposition or remove them as desired. The only
difference is that you can't directly add content, and some configuration and
display options are disabled (it's a template, after all). Follow these steps to
edit the page template you just created: 

1.  In the list of page templates, click *Lunar Guide Page*. This opens it in a 
    new browser tab or window. 

    ![Figure x: Click the page template to edit it.](../../../images/001-lunar-resort-template-edit.png)

    <!-- Recommend adding something about the Search and Navigation portlets
    which are added to the page template by default. They're conspicuous when
    you navigate to the page template. Feel free to disregard if we want to
    prioritize brevity.-->

2.  In the page template's edit toolbar click the *Add* button 
    (![Add](../../../images/icon-add-app.png)) on the upper right and expand the
    *Applications* &rarr; *Collaboration* category. Add a Blogs portlet to the
    page template's right column. 
 
3.  Close the page template's edit tab/window. @product@ automatically saves 
    your changes. 

Next, you'll create a page with this template. 

## Creating a Page with a Page Template [](id=creating-a-page-with-a-page-template)

Follow these steps to use the template to create a page: 

1.  Open the Main Menu and select *The Lunar Resort* &rarr; *Navigation*.

2.  In the Navigation menu, click the *Options* button 
    (![Options](../../../images/icon-options.png)) for *Public Pages* and select 
    *Add Public Page*. 

3.  Name the page *Cody* (Cody is one of our lunar guides).

4.  Under *Type*, select *Lunar Guide Page*. This selects your page template as 
    the source for the page. 

5.  Leave *Inherit Changes* set to *Yes*. This lets you edit this page in the 
    future by editing the template, but removes the ability to edit the page 
    directly.
 
6.  Click *Add Page*.

@product@ then creates the new page from your template. Next, you'll create more 
of these pages, one for each lunar guide (Cody is awesome, but he can't do 
everything by himself). Note, however, that Cody's page is in the navigation bar 
next to all the other pages. To get his page and those of the other lunar guides 
under the Lunar Guides page, you must nest the pages. You'll learn how to do 
this next. 
