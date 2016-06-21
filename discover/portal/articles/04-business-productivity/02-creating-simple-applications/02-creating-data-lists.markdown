# Creating Data Lists [](id=creating-data-lists)

<!-- Update Screen shots to Lunar Resort example -->

To create list applications, it's not enough to just create data definitions, as
you read about in the last article. Although the definitions are the central
component to your simple list application, controlling the data that can be
entered by users, you need to create a Dynamic Data List that uses the data
definition. Very simply, data lists create boundaries around the records
entered. For example, if you use the Activity Entry definition created in the
last article to create a Lunar Rover Race Sign-Up list, the records are stored
for you, even if someone else uses the same data definition to back their Lunar
Hike Sign-Up list. You can display both sign-up forms on the same page, backed
by the same data definition, and safely keep your records from being mixed up.

Building out new lists is similar to creating new web content articles. Just as
you can create new Web Content Structures to control the input of a particular
type of web content, you can use Data List Definitions to control the input of
new list types. Similarly, just as you create a new piece of web content,
selecting the Structure you would like to use with it, Lunar Resort site members
with the proper permissions choose the Definition they want to use when creating
a new list. Now that a data definition is in place, all that remains is to
create a new data list to capture the information we're going after. This is the
easiest step in creating the list, with only a few clicks between a data
definition and robust data entry and delivery.

To create a new sign-up form and list for a lunar rover race:

1. From the Product Menu, select the Site you want to create Dynamic Data Lists
in (the Lunar Resort if you're following the example), then click on *Content*
&rarr; *Dynamic Data Lists*.

2. Click the *Add* button (![Add Icon](../../../images/icon-add.png)) to create a new list based on a data definition.

3. Give the data list a name, like *Lunar Rover Race Sign-Up*, and a description to
   assist administrative users in the future.

4. Last and most importantly, click *Select* under the *Data Definition*
   section--this is where you set the data model that drives this list.

    Choose the *Activity Entry* data definition you created, then click
    *Save*.

    ![Figure 1: Give your list a name, a description, and select a data definition for the list.](../../../images/ddl-add-list.png)

Now that you've created your brand new sign-up, get the word out for guests to
sign up. But what would it look like for them to add an entry to this list? The
layout of the data definition you previously created (or selected) defines the
default layout of the form as well. You just need to add a Data List Display
portlet to a page, and set the permissions on the list so guests can add
records.

![Figure 2: You can enter a new data record directly from the Dynamic Data Lists portlet by clicking on *Content* &rarr; *Dynamic data Lists* from the from the Product Menu, clicking the list you want to add a record for, then click the *Add* button.  Alternatively, you can add the Dynamic Data List Display portlet to a page and configure it to allow users to submit new data records.](../../../images/ddl-displayportlet-add.png)

+$$$

**Note:** If you want to allow guests to submit
 DDL forms, you'll need to enable the *Allow Multiple Submissions* feature.
 Liferay treats the Guest user as one user. Therefore, if a guest submits a DDL
 form, Liferay assumes the Guest user filled out their one form and it won't
 allow any other guests to submit a form. Thus, allowing the Guest user to
 submit multiple submissions allows all your guests to submit DDL forms.

$$$

But how will the list of entries appear on a page? Whether its the awesome, new Lunar
Rover Race Sign-Up sheet or that boring Jobs Listing, the display side of the
equation is very important. 

## Using Default Displays [](id=using-default-displays)

Lists are published in the portal through the Dynamic Data List Display portlet.
If list creators don't customize the display, their lists use the default display
template.

![Figure 3: The default data list display in the Control Panel shows the list's records and allows record to added, edited, or removed (assuming the user has the correct permissions).](../../../images/ddl-default-display.png)

The default display template isn't exciting, but it allows users to see the
list's contents, and if they have permission, to add and/or edit list items.
This type of interaction is used for display-only lists that the user chooses to
expose to others, or for the user's own private lists. But you can improve the
display. You can show the data in a spreadsheet, so you can view the responses
to your Lunar Rover Race Sign-Up in a comfortable, easy-to-read format. The Dynamic
Data List Display portlet provides an easy way for a user (such as a member of a
site) to interact with whatever list is active and available.

While it's possible to ask everyone to contribute to the data list in the
control panel, it's simpler if the list is placed on a public or private
page in your site. Liferay's Dynamic Data List Display portlet lets you do just
that. Just navigate to whichever page you want and add the portlet. It works
much like the Web Content Display portlet. You can select a list for display,
add a list, or edit the currently displayed list. You can also add a *Form
Template* or a *Display Template* to modify the appearance of your lists. This
will be covered in the next article.

Open the Dynamic Display Portlet's configuration menu by clicking the Actions
button (![Configuration Menu](../../../images/icon-options.png)) and then
clicking *Configuration*. From this menu, you can select the list to display and
configure it how you like. You can select a different Display or Form Template,
or choose whether the list appears as *Editable* and whether to use the
*Spreadsheet View*. Checking *Editable* allows users that have permission to add
new entries to the list.  By default, this is enabled, but administrators are
the only ones with add permission. To grant access to other users, edit the
permissions on the list you'd like to grant access to, and grant the `Add
Record` permission. Choosing to use the *Spreadsheet View* displays the list in
a dynamic spreadsheet view.  This allows users with permission to interact with
the list in the same way as in a standard spreadsheet program.

![Figure 4: The Dynamic Data List Display portlet allows users to view the records belong to a list in either a standard or a spreadsheet view. This figure shows the spreadsheet view.](../../../images/ddl-spreadsheet-view.png)

As useful as the default display is, and it's certainly useful for data lists
like to do lists and memo notes, it can be an awkward way to ask Lunar Resort
guests to sign up for activities. Almost any time you want users to
interact with lists and contribute responses, you'll want a simple form to show
them, and you won't want to display all of the data that gets stored for each list
entry. Next, learn how to leverage template editors to control the display of
your forms and lists.
