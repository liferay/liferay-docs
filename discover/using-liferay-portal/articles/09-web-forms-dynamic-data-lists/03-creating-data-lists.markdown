# Creating Data Lists [](id=creating-data-lists)

Building out new lists really isn't all that different from creating new pieces
of web content. Just as you can create new Web Content Structures to control the
input of a particular type of web content, you can use Data List Definitions to
control the input of new list types. Similarly, just as you create a new piece
of web content, selecting the Structure you would like to use with it, `list.it`
users (we'll call them *Listies*) choose the Definition they want to use when
creating a new list. Now that a data definition is in place, all that remains is
to create a new data list to capture the information we're going after. This is
the easiest step in creating the list, with only a few clicks between a data
definition and robust data entry and delivery.

To create a new volunteer list with the "Volunteer Sign-Up" definition:

1. From the *Content* section of the Control Panel, select Dynamic Data Lists.

2. Click on *Add* to create a new list based on a data definition, which in our
   case is the volunteer sign-up.

3. Give the data list a name, like *Spring Move-In* and a description to assist
   administrative users in the future.

4. Last and most importantly, click *Select* under the *Data Definition*
   section--this is where you set the data model that drives this list.

5. Choose the *Volunteer Sign-Up* data definition you created, then click
   *Save*.

Now that you've created your brand new volunteer list, you can pester everyone
you know to sign up. But what would it look like for them to add an entry to
this list? The data definition you've previously created (or selected) defines
the layout as well, which means the form looks just the way you laid it out. 

![Figure 9.4: Entering a new data record.](../../images/05-ddl-add-record.png)

But how will this data appear? How will my awesome, new Volunteer Sign-Up sheet
or that boring Jobs Listing look? The answers to these pressing, burning
questions bring us to the mecca that is the display side of this equation.

## Using Data List Forms [](id=using-data-list-forms)

A nice way to enable people to use your forms is the Dynamic Data List Forms
portlet. This portlet is tailored to entering new records. When you deploy that
data list for your users to sign up for a retreat, or your family members to
volunteer to help you move, using the data list form allows you to simplify the
sign-up process and hide the contents of the list.

Using the Dynamic Data List Form is exactly the same as using the Web Content
Display portlet: just set it up, point it to a list (either existing or new) and
let it go. This is very easy to do. 

To display a list inside the portlet, add the Dynamic Data List Form portlet to
a page from the Dockbar: *Add &rarr; More &rarr; Dynamic Data List Form*. With
the portlet on the page, click on the small gear icon in the lower left corner.
This takes you to the configuration page, where you can select a list to use for
the form entries. Each time a user visits your page with the volunteer sign-up,
they are presented with a form to fill out. If they have already filled out an
entry, a message is displayed instead.

Unlike the Web Content Display portlet, however, the Dynamic Data List Forms
portlet may not be installed already in your portal. If not, just head over to
Liferay Marketplace, grab it and install it. 

You can publish your lists anywhere in your portal too. Read on to find out more
about that. 

## Using Default Displays [](id=using-default-displays)

Lists are published in the portal through the Dynamic Data List Display portlet.
If Listies don't customize the display, their lists look something like this:

![Figure 9.5: The default data list
display.](../../images/05-ddl-list-display.png)

This isn't all that exciting, but it allows users to see the list's contents,
and if they have permission, to add and/or edit list items. Within a site like
`list.it`, this type of interaction is used for display-only lists that the user
chooses to expose to others, or for the user's own private lists. But you can
improve the display. You can show the data in a spreadsheet, so you can view the
responses to your Volunteer Sign-Up in a comfortable, easy-to-read format. The
Dynamic Data List Display portlet provides an easy way for a user (such as a
member of a site) to interact with whatever list is active and available.

While it's possible to ask everyone to contribute to the data list within the
control panel, it's much better to give them a simple way to access the list.
Liferay provides the Dynamic Data List Display portlet to ease the integration
of your new list onto your site. With your list in hand, head over to the page
you want and add the portlet. It works much like the Web Content Display
portlet: use the gear icon to select a list for display or use the pen/paper
icon to add a new list. The default display spills out the contents of the list,
but can be configured to use a different display template, which is explored
later in this chapter. The two important configuration options to consider are:

**Editable:** allows users that have permission to add new entries to the list.
By default, this is disabled and when enabled, administrators are the only ones
with add permission. To easily grant access to other users, edit the permissions
on the list you'd like to grant access to, and grant the `Add Record`
permission.

**Spreadsheet View:** displays the list in a dynamic spreadsheet view. This
allows users with permission to interact with the list in the same way as in a
standard spreadsheet program.

![Figure 9.6: The spreadsheet view](../../images/05-ddl-spreadsheet-view.png)

Now, as useful as this default display is, and it's certainly useful for my to
do list and my memo notes, it can be an awkward way to ask my volunteers to sign
up. In fact, any time I want other Listies to interact with my lists and
contribute responses, I really just want a simple form to show them. They don't
need to see the full range of responses. And in some cases, it can be hazardous
to your health for everyone to see the responses. Then you don't have to explain
why your sister-in-law won't work with your brother on the same task because of
his B.O. problem. For reasons like that, you'll need to customize the data entry
form or the display of the list. Liferay lets you do exactly that using a custom
*detail template* or *list template*. 
