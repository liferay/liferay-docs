# Creating a User Interface [](id=creating-a-user-interface)

All of the Guestbook Admin portlet's back-end code is developed; the only thing
left to do is create the portlet's user interface. The default view should have
a button for adding new guestbooks, and it should also display a list of the
guestbooks that have been added to the site. 

![Figure 1: The Guestbook Admin's UI will contain buttons to add, edit, delete, and control permissions of guestbook entities.](../../images/guestbook-admin.png)

Each guestbook should also be accompanied with the action buttons Edit, Delete,
and Permissions. You'll begin by creating the buttons, then you'll check for
each button's permissions. 

## Creating JSF Views for the User Interface

The first thing you'll do is configure the existing
`views/guestbook-admin/view.xhtml` file to facilite the UI's navigation. Similar
to the original `views/view.xhtml`, this view will serve as the navigation view,
checking boolean properties in the `GuestbookBacking` bean, and displaying the
appropriate view according to those properties' value. 

1. Open the `guestbook-admin/view.xhtml` file













## Checking for Permissions in JSF Views
