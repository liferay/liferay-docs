# Creating Data Lists

<!-- I need to find out what the use case for DDL is supposed to be. It seems to
be changing with the introduction of the new forms portlet and the removal of
the DDL Forms portlet, but not all of the old behavior can be replicated at this
point. -->

Building out new lists is similar to creating new web content articles. Just as
you can create new Web Content Structures to control the input of a particular
type of web content, you can use [Data List Definitions](LINK) to control the
input of new list types. Similarly, just as you create a new piece of web
content, selecting the Structure you would like to use with it, `list.it` users
(we'll call them *Listies*) choose the Definition they want to use when creating
a new list. Now that a data definition is in place, all that remains is to
create a new data list to capture the information we're going after. This is the
easiest step in creating the list, with only a few clicks between a data
definition and robust data entry and delivery.

To create a new volunteer list with the "Volunteer Sign-Up" definition:

1. From the Product Menu, click on *Sites* &rarr; *Content* and then on *Dynamic Data
   Lists*.

    ![Figure 1: Find the Dynamic Data Lists application by clicking *Sites* &rarr;
    *Content* from the Product Menu](../../images/ddl-product-menu.png)

2. Click on *Add* to create a new list based on a data definition, which in our
   case is the volunteer sign-up.

    ![Figure 2: Add a list to the Dynamic Data Lists application by clicking the
    *Add* button.](../../images/add-button.png)

3. Give the data list a name, like *Spring Move-In Sign Up*, and a description to
   assist administrative users in the future.

4. Last and most importantly, click *Select* under the *Data Definition*
   section--this is where you set the data model that drives this list.

5. Choose the *Volunteer Sign-Up* data definition you created, then click
   *Save*.

    ![Figure 3: Give your list a name, a description, and select a data
    definition for the list.](../../images/ddl-add-list.png)

Now that you've created your brand new volunteer list, you can pester everyone
you know to sign up. But what would it look like for them to add an entry to
this list? The layout of the data definition you previously created (or
selected) defines the default layout of the form as well.

![Figure 4: You can enter a new data record directly form the Dynamic Data Lists
portlet by clicking on *Sites* &rarr; *Content* from the from the Product Menu,
clicking the list you want to add a record for, then click the *Add* button.
Alternatively, you can add the Dynamic Data List Display portlet to a page and
configure it to allow users to submit new data
records.](../../images/ddl-form-template.png)

But how will the data list appear on a page? How will my awesome, new Volunteer
Sign-Up sheet or that boring Jobs Listing look? The answers to these pressing,
burning questions bring us to the display side of the equation.

<!-- DDL Forms is gone from plugins, and is incorporated into the
core under dynamic-data-lists-form-web/, though I haven't been able to replicate
the functionality perfectly. One of my Bus. Prod. team members says that the Forms Portlet
might be intended to do all of this now, though it is lacking in my
opinion. Many of the data types are not yet available in the forms portlet
(in fact, only radio, select, text, and checkbox are currently there), for
instance. -->

<!-- ## Using Data List Forms

Place the Dynamic Data List Display portlet on a page to let your users fill out
the forms you create. This portlet is tailored to entering new records. When you
deploy that data list for your users to sign up for a retreat, or your family
members to volunteer to help you move, using the display portlet allows you to
simplify the sign-up process and hide the contents of the list.

The Dynamic Data List Form portlet is not a core Liferay portlet like the Web
Content Display portlet. However, it's freely available on [Liferay Marketplace](http://www.liferay.com/marketplace). Just search for and install
the Dynamic Data List Form (or Dynamic Data List Form EE) portlet. Using the
Dynamic Data List Form is similar to using the Web Content Display portlet: just
set it up, point it to a list (either existing or new) and let it go. This is
very easy to do. 

To display a list inside the portlet, add the Dynamic Data List Form portlet to
a page: Click the *Add* button on the left side of the screen, click on
*Applications*, search for *Dynamic Data List Form*, then click *Add* next to
its name. With the portlet on the page, click on the *Select List* gear icon.
This opens a configuration popup, where you can select a list to use for the
form entries. When configuring the portlet, make sure to check the *Allow
Multiple Submissions* box if you'd like users to be able to add multiple records
for a list. If this box is not checked, once a user fills out an entry, the
portlet displays a message stating that a record has already been submitted. If
the *Allow Multiple Submissions* box is checked, each time a user visits your
page with the Sign-Up form, the Dynamic Data List Form portlet presents them
with a form to fill out.

+$$$

**Note:** If you want to allow guests to submit
 DDL forms, you'll need to enable the *Allow Multiple Submissions* feature.
 Liferay treats the Guest user as one user. Therefore, if a guest submits a DDL
 form, Liferay assumes the Guest user filled out their one form and it won't
 allow any other guests to submit a form. Thus, allowing the Guest user to
 submit multiple submissions allows all your guests to submit DDL forms.

$$$

Once records have been submitted for lists, you can publish the lists anywhere
in your portal. Read on to find out more about that. 
-->

## Using Default Displays

Lists are published in the portal through the Dynamic Data List Display portlet.
If Listies don't customize the display, their lists use the default display
template.

![Figure 5: The default data list display in the Control Panel shows the list's
records and allows record to added, edited, or removed (assuming the user has
the correct permissions).](../../images/ddl-default-display.png)

The default display template isn't exciting, but it allows users to see the
list's contents, and if they have permission, to add and/or edit list items.
This type of interaction is used for display-only lists that the user chooses to
expose to others, or for the user's own private lists. But you can improve the
display. You can show the data in a spreadsheet, so you can view the responses
to your Volunteer Sign-Up in a comfortable, easy-to-read format. The Dynamic
Data List Display portlet provides an easy way for a user (such as a member of a
site) to interact with whatever list is active and available.

<!-- I want to recommend the Forms Portlet for creating better looking forms for
users, but it's not really going to serve a similar purpose at the moment -->

While it's possible to ask everyone to contribute to the data list within the
control panel, it's simpler if the list is accessed from a public or private
page in your site. Liferay's Dynamic Data List Display portlet lets you do just
that. Just navigate to whichever page you want and add the portlet. It works
much like the Web Content Display portlet. You can select a list for display,
add a list, or edit the currently displayed list. You can also add a *Form
Template* or a *Display Template* to modify the appearance of your lists. This
will be covered in the [next article](LINK).

Open the Dynamic Display Portlet's configuration menu (click the vertical
ellipsis icon in the title bar of the portlet and click *Configuration*). From
this menu, you can select the list to display and configure it how you like. You
can select a different Display or Form Template, or choose whether the list
appears as *Editable* and whether to use the *Spreadsheet View*. Checking
*Editable* allows users that have permission to add new entries to the list.  By
default, this is enabled, but administrators are the only ones with add
permission. To grant access to other users, edit the permissions on the list
you'd like to grant access to, and grant the `Add Record` permission. Choosing
to use the *Spreadsheet View* displays the list in a dynamic spreadsheet view.
This allows users with permission to interact with the list in the same way as
in a standard spreadsheet program.

![Figure 6: The Dynamic Data List Display portlet allows users to view the records belong to a list in either a standard or a spreadsheet view. This figure shows the spreadsheet view.](../../images/ddl-spreadsheet-view.png)

As useful as the default display is, and it's certainly useful for to do lists
and memo notes, it can be an awkward way to ask volunteers to sign up. In
fact, any time I want other Listies to interact with my lists and contribute
responses, I really just want a simple form to show them. They don't need to see
the list of responses. 
