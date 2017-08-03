# Writing the Guestbook Admin Application

You now have the same functionality in the real application as you had in the
prototype: users can add and view guestbook entries. You designed the back-end,
however, to be much more powerful: theoretically, the application could support
many guestbooks and their associated entries. In the application, however, you
have no user interface to support this. 

Additionally, you want to make sure that not just any user can add guestbooks.
Administrators should be the only users who can do that. 

To accomplish these things, you must create a Guestbook Admin portlet and place
that portlet within @product@'s administrative interface--specifically, within
the Content menu. This way, the Guestbook Admin portlet is accessible only to
Site Administrators, and users can use the Guestbook portlet to create entries. 

In short, this is a simple application with a simple interface: 

![Figure x: The Guestbook Admin portlet lets administrators manage Guestbooks.](../../../images/admin-app-start.png)

Are you ready to begin? 

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/creating-the-classes">Let's Go!<span class="icon-circle-arrow-right"></span></a>