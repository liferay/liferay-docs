# Setting Permissions Using JSF [](id=setting-permissions-using-jsf)

To this point, your guestbook application is able to create guestbooks and
guestbook entries and store them as data to the database. However, everyone that
visits your site has the ability to do this, which could present problems as an
administrator. Most of the time, you'll want some of the data in your guestbook
application protected from random users on the web. For instance, you may want
to enable some users to add guestbook entries, while preventing others from
doing so. 

In the first half of this learning path, you'll learn how to implement Liferay's
permissions system in your guestbook portlet following JSF best practices. The
default permissions scheme will allow users to view guestbooks and enter
guestbook entries, while preventing anonymous web-browsing users from doing
either. Also, regular users should not be allowed to add guestbooks, so you'll
implement permissions that only give administrators the ability to create new
guestbooks. 

In the second half of this learning path, you'll dive further into permissioning
with JSF portlets by bolstering a user's permissions options. This will include
adding resources to the Guestbook portlet's service layer and creating new Java
wrapper classes. 

Overall, you'll learn just how easy it is to implement security for a JSF
application in Liferay Portal, and transfer permissioning into your own
applications. Time to get started! 
