#Page Templates 

When you're doing repetitive work you want to do anything you can to speed it 
up and reduce the amount of repeat work you need to do to complete your task. 
Page Templates take care of this on two levels. First, they enable you to 
create any pages from a template. Second they (optionally) enable you to manage any future changes to those pages from the template.

## Creating a Page Template

First let's create a page template, then you'll add several pages using it. 
Later you'll make changes to the template and see those changes propagated to
all of the pages that were based on it.

1. Open the main menu.

2. Open *Control Panel* &rarr; *Sites*.

3. Click on *Page Templates*.

![Figure x: Page Templates page.](../../../images/001-page-templates-screen.png)

There are three page templates that come bundled with Liferay. *Blog* and 
*Wiki* are essentially example layouts for the Blogs portlet and Wiki portlet 
respectively, while *Content Display Page* serves a special function that 
you'll work with later.

1. Click the '+' button, and you are taken to the Page Template creation page.

2. Name the template "Lunar Guide Page"

3. Enter "Page with information about a Lunar Guide." for the description.

4. Click *Save*.

5. You'll be taken back to the list of Page Templates. Click on the page 
    template title - "Lunar Guide Page" - to edit it. It will be automatically
    opened in a new tab or window in your browser.

![Figure x: Editing page template.](../../../images/001-lunar-resort-template-edit.png)

## Editing a Page Template

Editing a page template is very much the same as editing any page. You drag and 
drop applications on to the page. The only difference is that you can't 
directly add content, and some configuration or display options may be 
disabled, as they are only configurable on the page itself.

1. Open the *Add* menu.
    
2. Add a *Blogs* application from the *Collaboration* category to the right 
    column.
    
3. Close the tab or window where you were editing the template. It is saved
    automatically.

That doesn't seem like much, but you'll have more to add in a minute.

## Creating a Page with a Page Template

Now that you have the template completed, you can easily create a page with it.

1. Open the Main Menu.

2. Go to *The Lunar Resort* &rarr; *Navigation*

3. Click on the menu for *Public Pages* and select *Add Public Page*.

4. Name the page "Cody" (he's one of our Lunar Guides).

5. Under *Type* select *Lunar Guide Page*.

6. Leave the *Inherit Changes* set as "Yes" - this will enable you to edit this
    page in the future by editing the template, but will remove the ability to 
    edit the page directly.
    
7. Click *Add Page*

The new page will be created per your template. Next you're going to need to 
create a couple more of these pages, but you don't want them to be at the top 
navigation level, so you'll need to learn about nesting pages to get it right.
