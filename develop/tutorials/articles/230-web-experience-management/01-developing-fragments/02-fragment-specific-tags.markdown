# Fragment Specific Tags [](id=fragment-specific-tags)

While HTML, CSS, and JavaScript are universal tools for building websites, 
@product@ includes some additional tools to make fragments more powerful. One
tag can make text editable not just in the HTML editor, but also at the time of
publishing. The other enables you to embed Liferay portlets into your fragment
in the form of "Widgets."

## Making Text Editable [](id=making-text-editable)

Making text editable allows the marketer or web admin to modify the text before
publishing it. This way, you can reuse a single fragment with different headings
or different text for different pages. Fragments make creating content easy and
this feature can save you the work of duplicating work just to change the text.

You can make any text of a fragment editable by enclosing it in an 
`<lfr-editable>` tag like this:
    
    <lfr-editable id=”unique-id” type="text">
       This is editable text!    
    </lfr-editable>
 
The `lfr-editable` tag doesn't render without a unique `id`. 

Now add editable text to a Fragment, add the Fragment to a Content Page, 
and then edit the text before publishing:

1.  Go back into the Fragment you were working on before.

2.  Inside the `<h1>` in the HTML pane, surround the text with `<lfr-editable>` 
    tags so that it looks like this. 
    
        <h1>
          <lfr-editable id="heading" type="text">
              This text is styled, so it will look pretty!
          </lfr-editable>
        </h1>

4.  Click *Publish*.

Now your fragment contains editable text. Add it to a Content Page to be
published:

1.  Go to *Navigation* &rarr; *Site Pages*.

2.  Select the *Page Templates* tab at the top.

3.  Create a new Collection named *Templates for Developing Fragments* and a New
    Page Template inside of it named *Editable Page Template*. 

4.  From the menu on the right, add your fragment to the page.

5.  Click on the text that you defined as editable and change it.

![Figure 1: You can edit text in the Page Template editor.](../../../images/editing-fragment-text.png)

The template saves automatically, and when it is turned into a page, the new
text is displayed.

## Making Images Editable [](id=making-images-editable)

Like text, you can set images images as editable. An editable image can be
selected from the user's local files or from the Documents and Media Library.
You must be careful with defining styles for editable images since an image that
without the proper dimensions or that is forced into a poorly sized space can
have major negative effects on your layout. 

Images use the same `<lfr-editable>` tag as text, but with the `img` type, like 
this:
    
    <lfr-editable id="unique-id" type="img">
       <img src="...">
    </lfr-editable>

After you add the `lfr-editable` tag with the type `img` to a Fragment, when 
you add that Fragment to a page, you can then click on the editable image and 
select a replacement.

## Including Widgets Within A Fragment [](id=including-widgets-within-a-fragment)

You can add more dynamic behavior to a Fragment by including a widget. 
Currently, you can only embed a portlet as a widget, but other types of widgets
will be available in the future.

To include a widget you need to know its registered name. For example, the Site 
Navigation Menu portlet is registered as `nav`. Each portlet which is registered
has an `lfr-app-[name]` tag that's used to embed it. For example: the Navigation
Menu tag is `<lfr-app-nav />`. You could embed it in a block like this:

    <div class=”nav-widget”>
        <lfr-app-nav />
    </div>

Implement this in your Fragment:

1.  Go to the *Fragments* page.

2.  Go to the *Developing Fragments* collection and add a new Fragment inside 
    of it named *Widget Fragment*.

4.  Insert the following code in the main `<div>` in the HTML pane:
    
        <div class=”container-fluid”>
          <div class=”row”>
            <div class=”col-md-10”>
               <lfr-widget-nav />
            </div>
          </div>
        </div>
 
5.  Click *Publish*. 

Now you need to add it to a Content Page to display it.

1.  Go to *Navigation* &rarr; *Site Pages*.

2.  Select the *Page Templates* tab at the top.

3.  Go to the *Templates for Developing Fragments Collection*.

4.  Create a new Page Template named *Widget Page Template*.

5.  In the new Page Template, from the menu on the right, add your fragment to
    the page.
 
6.  Exit the template editor and it saves automatically.

7.  Go to the *Pages* tab.

8.  Click the `+` icon to create a new page.

9.  Select the *Widget Page Template* and save.

10. Now go back to your site, and select your new page.

## Embedding Your Widget in a Fragment [](id=embedding-your-widget-in-a-fragment)

If you have a custom widget that you want to embed in a fragment, you can 
configure that widget to be embeddable. In order to embed your widget, it must
be an OSGi Component. Inside the `@Component` annotation for the portlet class
that you want to embed, add this property:

    "com.liferay.fragment.entry.processor.portlet.alias=app-name"
 
When you deploy your widget, it's available to add. The name you specify in the
property must be appended to the `lfr-widget` tag like this:

    <lfr-widget-app-name /> 

Embedding widgets in Fragments opens a world of options. Now that you've
explored some of the power of Fragments, next you'll learn about best practices
for development.
