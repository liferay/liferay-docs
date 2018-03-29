# Fragment Specific Tags

While HTML, CSS, and JavaScript are universal tools for building websites, 
@product@ includes some additional tools to make fragments more powerful. One
tag can make text editable not just in the HTML editor, but also at the time of 
publishing. The other enables you to embed Liferay portlets into your fragment in the form of "Widgets."

## Making text editable

Making text editable allows the marketer or web admin to modify the text before 
publishing it. This could be to allow you, for example, to reuse a single 
fragment with different headings or different text for different pages.
Fragments are intended to make creating beautiful content simple and easy and
this feature will also save you the work of duplicating work just to change
the text.

You can make any text of a fragment editable by enclosing it in an 
`<lfr-editable>` tag like this:

    
    <lfr-editable id=”unique-id”>
       This is editable text!    
    </lfr-editable>
    

The `lfr-editable` tag must include a unique `id` or it will not render, and 
only text that is included within that tag will be editable.

Now, create a Fragment with editable text, add the Fragment to a Content Page, 
and then edit the text before publishing.

1.  Go to the *Fragments* page in Site Administration.

2.  Go to the *Developing Fragments* collection and a new Fragment inside of it
    named "Editable Fragment".

3.  Inside the `<div>` in the HTML pane, enter the following code:
    
        <h1>
          <lfr-editable id="heading">
            Editable Heading
          </lfr-editable>
        </h1>

4. Click *Publish*.

<screenshot>

This creates a very simple fragment that contains only a heading with editable
text. Next add it to a Content Page to be published.

1.  Go to *Navigation* &rarr; *Site Pages*.

2.  Select the *Page Templates* tab at the top.

3.  Create a new Collection named "Templates for Developing Fragments" and a 
    New Page Template inside of it named "Editable Page Template"

4.  From the menu on the right hand side, add your fragment to the page.

5.  Click on the text that you defined as editable, and change it.

The template will save automatically, and when it is turned into a page, the new
text will be displayed.

## Including widgets within a fragment

You can add more dynamic behavior to a Fragment by including a widget. 
Currently, you can only embed a portlet as a widget, but other types of widgets
will be available in the future.

To include a widget you need to know its registered name. For example, the Site 
Navigation Menu portlet is registered as ‘nav’. Each portlet which is 
registered has an ‘lfr-app-[name]’ tag which is used to embed it. For example: 
the Navigation Menu tag is `<lfr-app-nav />`. You could embed it in a block 
like this:

    <div class=”nav-widget”>
        <lfr-app-nav />
    </div>

Let's demonstrate this in a new Fragment.

1.  Go to the *Fragments* page.

2.  Go to the *Developing Fragments* collection and a new Fragment inside of it
    named "Embedded Widget Fragment".
    
3.  Create a new Fragment.

4.  Insert the following code inside of the main `<div>` in the HTML pane:
    
        <div class=”container-fluid”>
          <div class=”row”>
            <div class=”col-md-2”>
               <img src=”logo.png”/>
            </div>
            <div class=”col-md-10”>
               <lfr-app-nav />
            </div>
          </div>
        </div>
    
5.  Click *Publish*

Now that the Fragment is created, you need to add it to a Content Page to 
display it.

1.  Go to *Navigation* &rarr; *Site Pages*.

2.  Select the *Page Templates* tab at the top.

3.  Go to the "Templates for Developing Fragments Collection".

4.  Create a new Page Template named "Widget Page Template".

5.  In the new Page Template, from the menu on the right hand side, add your 
    fragment to the page.
    
6.  Exit the template editor and it will save automatically.

7.  Go to the *Pages* tab.

8.  Click the `+` icon to create a new page.

9.  Select the "Widget Page Template" and save.

10. Now go back to your site, and select your new page.

<screenshot>

Embedding widgets in Fragments is a very powerful tool that opens a world of 
options.
