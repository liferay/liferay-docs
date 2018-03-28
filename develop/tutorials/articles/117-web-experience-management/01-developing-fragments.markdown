# Developing Fragments

The goal of Fragments is to take a design or mockup and realize it on a web page
as accurately as possible. To do this, the developers are given a space where
they have a "blank slate" to create it. You have three tools at your disposal 
to accomplish this:

*  **HTML**: The markup of the fragment. Fragments use standard HTML with  
    special tags to add dynamic behavior.

*  **CSS**: CSS styles and positions the fragment's markup. 

*  **JavaScript**: JavaScript provides dynamic behavior to the fragment.

The HTML, CSS, and JavaScript is all completely standard, just like you'd use
anywhere else on the web, but there are a few Liferay specific options as well. 
Some text areas can be specified as editable, allowing that text to be modified
before it is published. Liferay portlets can also be embedded in 
Fragments as "widgets" which enable you to create more dynamic content and pages
with Fragments.

## Making text editable

Making text editable allows the marketer or web admin to modify the text before 
publishing it. This could be to allow you, for example, to reuse a single 
fragment with different headings or different text for different pages.

You can make any text of a fragment editable by enclosing it in an 
`<lfr-editable>` tag like this:

    
    <lfr-editable id=”unique-id”>
       This is editable text!    
    </lfr-editable>
    

Only text that is included within that tag will be editable.

Let's test this out. You'll create a Fragment with editable text, add the 
Fragment to a Content Page, and then edit the text before publishing.

1.  Go to the *Fragments* page in Site Administration.

2.  Create a new Collection and a new Fragment inside of it.

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

3.  Create a new Collection and a New Page Template inside of it.

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

2.  Click on the Collection you created earlier (or create a new one if you 
    don't have any Collections.)
    
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

3.  Click on the Collection you created earlier (or create a new one if you 
    don't have any Collections.)

4.  Create a new Page Template.

5.  In the new Page Template, from the menu on the right hand side, add your 
    fragment to the page.
    
6.  Exit the template editor and it will save automatically.

7.  Go to the *Pages* tab.

8.  Click the `+` icon to create a new page.

9.  Select the template you just created with the embedded widget and save.

10. Now go back to your site, and select your new page.

<screenshot>

Embedding widgets in Fragments is a very powerful tool that opens a world of 
options.

## Developing a fragment using desktop tools

It’s possible to develop a fragment using any preferred desktop tools. Since 
the Fragment is simply HTML, CSS, and JavaScript, you could use a simple text 
editor or a specialized tool with its own built in previews, like the one's 
Liferay provides. If you use an external tool, you can copy/paste the code into the appropriate panes in the Fragment editor to publish it.

## Recommendations and Best Practices

In general all of your code should be semantic and highly reusable. One of the 
main concerns is making sure that everything is namespaced properly as to not
interfere with other elements on the page outside of the Fragment.

### CSS

While it’s possible to write any CSS desired in a fragment, it’s recommended to 
prefix it with a class specific to the fragment to avoid impacting other 
fragments. To facilitate this, when creating a new fragment, the HTML will 
include a div with an automatically generated class name and the CSS will show 
a sample selector using that class. Use it as the bases for all selectors you 
add.

### JavaScript

Avoid adding a lot of JavaScript code, since it won’t be highly reusable. Instead, reference external JS libraries.
