# Implementing a UI with JSF Views

Your services and managed beans are all set for action, but how will your users
interact with the portlet? You'll need to implement a user interface for your
users to interact with using JSF views. 

You've briefly read about the views you'll need to incorporate. When you first
created your JSF guestbook portlet, a `view.xhtml` file was automatically
created with some default code. For the guestbook, you'll edit this file and
create the `guestbook`, `entry`, and `master` XHTML files. You'll learn about
each view's purpose, and then add the appropriate code to get your guestbook up
and running. 

Each view represents a page in your guestbook. For instance, if you were to
click an *Add Guestbook* button, the rendered page to add a guestbook would be
the `guestbook` view. The same concept applies for the `entry` view. The
`master` view represents all the pages a user views that is not related to
creating a new guestbook or entry entity. Lastly, the default `view`
communicates with your view bean, and handles the navigation between views. 

Now that you have a fundamental understanding of the views and what they do,
it's time to dive in and create them! 

## Editing the Default View


