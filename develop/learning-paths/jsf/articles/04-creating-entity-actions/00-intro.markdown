# Creating Entity Actions

To this point, you've created a JSF guestbook application that uses a database
to store its entries. Also, the application is integrated with Liferay's
permissions mechanism, allowing you to control who can access different parts of
the portlet. But what about managing the guestbooks and entries themselves? There is
currently no way to edit, delete, or set permissions on individual entities. 

In this learning path, you'll create portlet actions to edit entities,
set permissions for entities, and delete entities. You'll use many of the same
concepts you've already been introduced to, like adding action methods to a
managed bean and adding buttons that trigger those methods using a JSF view. 

![Figure 1: In this learning path, you'll add the *Edit*, *Permissions*, and *Delete* buttons for your *Guestbook* and *Entry* entities.](../../images/edit-permissions-delete.png)

By providing entity actions, your users possess complete control over their
guestbook and entries they create. 
