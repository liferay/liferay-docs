# Creating and Managing Forms [](id=creating-and-managing-forms)

The Forms application can do a lot of things really well, but if you just need
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

## Building a Form [](id=building-a-form)

Whether you're creating a simple or complex form, it all starts in the same
place: the Forms Application in your site's Content section. Access this
in the Menu by using the site selector to choose the site you want to work in
(The Lunar Resort in this case) and clicking *Content &rarr; Forms*. 

1.  Once you're in the Forms application, click the *Add* button
    (![Add](../../images/icon-add.png)). You're taken to the form builder
    view where you create the form. 

2.  The first step is to give the form a name. Replace *Untitled Form* with
    *Guest Survey*.

3.  For Help Text enter *Tell us how your stay was!*

4.  Now add the fields. Click the *Add* button
    (![Add](../../images/icon-add.png)) to open the sidebar if it's not
    already opened.

    ![Figure 2: You can choose from nine field types when creating forms.](../../images/forms-sidebar.png)

5.  Drag a *Select from List* field onto the form builder and configure it like this:

    **Label:** *Rate your visit to The Lunar Resort.*

    **Help Text:** Leave this blank for now. If you want a subheading for your
    field to provide additional guidance, this would be useful.

    Make sure you turn on the *Required Field* selector. You want to make sure
    that, at a minimum, you capture whether the guests had a favorable stay or
    not.

    Leave the manual option checked for creating the list of selections. If
    you're interested in populating the field from a data provider, read the
    article on data providers.

    Add these options: *It was out of this world!*, *I had a good time.*, *I'd
    rather go to the beach*, and *I'll never come back.* You'll notice that
    typing in one of the fields automatically adds another blank selection line.
    Just leave the last one blank when you're done.

6.  Close the sidebar when you're done configuring the field. To see additional
    options, click *Properties*. 

7.  Now you need a text field, so add one using the same procedure you used for
    the select field.

    Configure the field as follows:

    **Label:** *Want to tell us more?*

    **Help Text:** Leave this blank again to give the form a consistent
    look.

    **My text field has:** Choose *Multiple Lines*. Let guests prattle on about
    their stay if they want to.

    **Required Field:** Leave this unselected. Only require guests to fill out
    the select field and leave this one as optional.

8.  Close the sidebar when you're finished configuring the text field.

9.  Back on the form builder page, you can see the way the fields are laid
    out. This view shows the first (and only in this case) page of the form.

    ![Figure 3: The form builder page lets you preview your form layout, add a page to the form, or add some more fields.](../../images/forms-form-builder.png)

10.  This form is finished, so click *Save Form*. It's also auto-saved every
     minute by default.

11.  Click *Publish Form*. A dedicated URL to the form is generated, but nobody
     has the URL yet.

Now your form can be added to a page, and Lunar Resort guests can be emailed and
provided with a link to the page where the form is displayed.

## Accessing Forms [](id=accessing-forms)

Once the form is developed and published, there are two options for getting the
published form to targeted users:

1. Place the Form portlet on a site page. This approach allows users to navigate
   to the page in the site.

2. Copy the dedicated form URL and provide it to users (for example, via email).
   This limits access to the form to only those users who have the direct link.

To display the form on a site page in The Lunar Resort site:

1.  Add a page to the site (choose Full Page Application for the page type if
    you only want the form application on the page). Call it *Guest Survey*.

    ![Figure 4: Add a page for guests to view and fill out your new form.](../../images/forms-guest-survey-page.png)

2.  Add the Form portlet to the page. If you used a full page application, click
    the *Configure Page* button (![Page Gear](../../images/icon-page-gear.png))
    and choose *Dynamic Data Mapping Form* from the Full Page Application
    dropdown.

    3. Once the Form application is on the page, click *Select Form*, choose the *Guest
    Survey* form, and click *Save*.

4. Close the Form dialog window and your form is ready for use by users in The
   Lunar Resort site.

To display the form on a dedicated page accessed only by its link:

1.  In the form builder, click *Publish Form* if you haven't already.

    ![Figure 5: You must first publish a form before you can get a shareable
    link.](../../images/forms-link-grayed.png)

2.  Once published, click the link icon at the top right of the builder.

    ![Figure 6: Copy the link to your form.](../../images/forms-link.png)

3. Once you get the link out to users, it's showtime.

    ![Figure 7: Lunar Resort Guests can access a simple form to record their feelings about the resort.](../../images/forms-guest-survey.png)

Next you'll learn how to view the form entries. Since there aren't any yet, fill
out and submit the form a few times. 

Now you know the basics of creating and managing forms, but this presentation
didn't do the Forms application justice. It's much more powerful than hinted at
here. The remaining articles in this section will get you immersed in more
advanced form building features.
