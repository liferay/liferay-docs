# Creating and Managing Forms [](id=creating-and-managing-forms)

The Forms widget can do a lot of things really well, but if you just need
a simple form, how do you wade through all the features you don't need? Is your
simple survey going to make you late for that lunch outing you've been planning
with colleagues at that new shawarma place? No!

Let's create a simple yet elegant form, give access to the intended users, and
get you on your way to lunch.

At The Lunar Resort, it's important to capture guests' feedback about their stay
at the resort. After a (hopefully) safe journey home, guests should receive an
email with a link to brief survey that prompts them to rate their stay from
a list of selections, and add any additional comments they'd like in an optional
field.

![Figure 1: Get feedback from guests of The Lunar Resort.](../../images/forms-guest-survey.png)

## Viewing Forms [](id=viewing-forms)

Whether creating a form or managing existing forms, it all starts in the same
place: the Forms Application in your site's Content section. Access this in the
Menu, first choosing the site to work in (for example, The Lunar Resort) and
clicking *Content &rarr; Forms*. The first thing you'll see is a list of
existing forms (if there are any). This list is styled by the Display Style
selector next to the Add button (![Add](../../images/icon-add.png)). By
default, forms are displayed in List format.

![Figure 2: Forms are displayed in List format by default.](../../images/forms-list-view.png)

There's also a Table format. Change the style for a single site right here in 
the Forms site menu application, or change the default display style for the
system scope in Control Panel &rarr; Configuration &rarr; System Settings &rarr;
Forms (in the Content section). Click the *Forms* entry and find the Default
Display View property. Click *Update* and your changes are propagated to all
sites.

## Building a Form [](id=building-a-form)

To add a new form, 

1.  Click the *Add* button (![Add](../../images/icon-add.png)). The form builder
    view appears. 

2.  Name the form. Replace *Untitled Form* with *Guest Survey*.

3.  For Help Text enter *Tell us how your stay was!*

4.  Add the fields. Click the *Add* button (![Add](../../images/icon-add.png))
    to open the sidebar if it's not already opened.

    ![Figure 3: You can choose from nine field types when creating forms.](../../images/forms-sidebar.png)

5.  Drag a *Select from List* field onto the form builder and configure it like 
    this:

    **Label:** *Rate your visit to The Lunar Resort.*

    **Help Text:** Leave this blank for now. If you want a subheading for your
    field to provide additional guidance, this would be useful.

    Turn on the *Required Field* selector. At a minimum, this form must capture
    whether guests like their stay or not.

    Leave the manual option checked for creating the list of selections. To
    learn about populating the field with a data provider, read
    [here](/discover/portal/-/knowledge_base/7-1/data-providers).

    Add these options:
    
    - *It was out of this world!*
    - *I had a good time.*
    - *I'd rather go to the beach*
    - *I'll never come back*
    
    Typing in one of the fields automatically adds another blank selection line.
    Just leave the last one blank when you're done.

6.  To see additional options, click *Properties*. Close the sidebar when
    finished.

7.  Add a text field, using the same procedure you used for the select
    field.

    **Label:** *Want to tell us more?*

    **Help Text:** Leave this blank again to give the form a consistent look.

    **My text field has:** Choose *Multiple Lines*. Let guests prattle on about
    their stay if they want to.

    **Required Field:** Leave this unselected. Only require guests to fill out
    the select field and leave this one as optional.

8.  Close the sidebar.

9.  In the form builder, you can see the way the fields are laid out on the form
    page.

    ![Figure 4: The form builder page lets you preview your form layout, add a page to the form, or add some more fields.](../../images/forms-form-builder.png)

10.  When the form is finished, click *Save Form*. It's also auto-saved every
     minute by default.

11.  Click *Publish Form*. A dedicated URL to the form is generated, but nobody
     has the URL yet.

Now your form can be added to a page, and Lunar Resort guests can be emailed and
provided with a link to the page where the form is displayed.

## Accessing Forms [](id=accessing-forms)

Once the form is developed and published, there are two options for getting the
published form to targeted users:

1.  Place the Form widget on a site page. This approach lets users navigate to 
    the page in the site.

2. Copy the dedicated form URL and provide it to users (for example, via email).
   This limits access to the form to only those users who have the direct link.

To display the form on a site page in The Lunar Resort site:

1.  Add a page to the site (choose Full Page Application for the page type if
    you only want the form application on the page). Call it *Guest Survey*.

    ![Figure 5: Add a page for guests to view and fill out your new form.](../../images/forms-guest-survey-page.png)

2.  Add the Form widget to the page. If you used a full page application, click
    the *Configure Page* button (![Page Gear](../../images/icon-page-gear.png))
    and choose *Form* from the Full Page Application dropdown.

3.  Once the Form widget is on the page, click *Select Form*, choose the 
    *Guest Survey* form, and click *Save*.

4.  Close the Form dialog window and your form is ready for Lunar Resort site 
    users.

To display the form on a dedicated page accessed only by its link:

1.  In the form builder, click *Publish Form* if you haven't already.

    ![Figure 6: You must first publish a form before you can get a shareable link.](../../images/forms-link-grayed.png)

2.  Once published, click the link icon at the top right of the builder.

    ![Figure 7: Copy the link to your form.](../../images/forms-link.png)

3. Once you get the link out to users, it's showtime.

    ![Figure 8: Lunar Resort guests can use a simple form to record their feelings about the resort.](../../images/forms-guest-survey.png)

Next you'll learn how to view the form entries. Since there aren't any yet, fill
out and submit the form a few times. 

Now you know the basics of creating and managing forms, but this presentation
didn't do the Forms application justice. It's much more powerful than hinted at
here. The remaining articles in this section immerse you in more advanced form
building features.
