# Adding a Portlet to the Control Panel [](id=adding-a-portlet-to-the-control-panel)

Up to this point, you've created a fully-functional Guestbook portlet. You've
written a `service.xml` file to define your application's data model and used
Service Builder to generate back-end code including model, service, and
persistence layers. You've added custom service methods using the appropriate
extension points: your entities' `*LocalServiceImpl` classes. You've created a
`GuestbookPortlet` class extending Liferay's `MVCPortlet` class with action
methods that call the corresponding service methods. And you've created a user
interface using JSPs and Liferay's tag libraries, including the AlloyUI tag
library.

However, you're missing some functionality. Although as an administrator, you
can can use the Guestbook portlet to add new guestbooks, there's no way to
delete them. There's also no way to update a guestbook's name or to configure a
guestbook's permissions. In this learning path, you'll add this functionality to
a new portlet called the Guestbook Admin portlet which you'll add to the Control
Panel.

![Figure 1: The Guestbook Admin portlet lives in Liferay's Control Panel and allows administrators to add, edit, and delete guestbooks and to configure their permissions.](../../images/guestbook-admin-portlet.png)

