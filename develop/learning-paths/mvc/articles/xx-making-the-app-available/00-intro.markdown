# Making the Guestbook app available [](id=making-the-guestbook-app-available)

You've created a fully-functional Guestbook portlet. You've written a 
`service.xml` file to define your application's data model and used Service 
Builder to generate back-end code including model, service, and persistence 
layers. You've added custom service methods using the appropriate extension 
points: your entities' `*LocalServiceImpl` classes. You've created a
`GuestbookPortlet` class extending Liferay's `MVCPortlet` class with action
methods that call the corresponding service methods. 

However, you're missing some functionality. Although as an administrator, you
can use the Guestbook portlet to add new guestbooks, there's no way to delete 
them. There's also no way to update a guestbook's name or to configure a 
guestbook's permissions. In this learning path, you'll specify the display 
category for the Guestbook portlet and add the administrative functionality to a 
new Guestbook Admin portlet which you'll add to the Control Menu. 
