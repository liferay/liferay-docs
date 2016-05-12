# Creating Entity Actions [](id=creating-entity-actions)

To this point, you've created a JSF guestbook application that uses a database
to store its entries. Also, the application is integrated with Liferay's
permissions mechanism, allowing you to control who can access different parts of
the portlet. But what about managing the guestbook entries themselves? There is
currently no way to edit, delete, or set permissions on individual entries. 

In this learning path, you'll create portlet actions to edit entries,
set permissions for entries, and delete entries. You'll use many of the same
concepts you've already been introduced to, like adding action methods to a
managed bean and adding buttons that trigger those methods using a JSF view. 

![Figure 1: In this learning path, you'll add the *Edit*, *Permissions*, and *Delete* buttons for your *Entry* entities.](../../images/edit-permissions-delete.png)

In this learning path, you'll only create entity actions for your guestbook
entries. The same will be done for your guestbooks, but will be completed later
in the
[Adding a JSF Portlet to the Control Panel](/develop/learning-paths/jsf/-/knowledge_base/6-2/adding-a-jsf-portlet-to-the-control-panel)
learning path.

By providing entity actions, your users possess complete control over the
entries they create. 
