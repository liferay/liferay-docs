# Writing an Administrative Portlet [](id=writing-an-administrative-portlet)

Like the prototype, the real application lets users add and view guestbook 
entries. The application's back-end, however, is much more powerful. It can 
support many guestbooks and their associated entries. Despite this, there's no 
UI to support these added features. When you create this UI, you must also make 
sure that only administrators can add guestbooks. 

To accomplish this, you must create a Guestbook Admin portlet and place it in 
@product@'s administrative interface---specifically, within the Content menu. 
This way, the Guestbook Admin portlet is accessible only to Site Administrators, 
and users can use the Guestbook portlet to create entries. 

In short, this is a simple application with a simple interface: 

![Figure 1: The Guestbook Admin portlet lets administrators manage Guestbooks.](../../../images/admin-app-start.png)

Are you ready to begin? 

<a class="go-link btn btn-primary" href="/develop/tutorials/-/knowledge_base/7-0/creating-the-classes">Let's Go!<span class="icon-circle-arrow-right"></span></a>
