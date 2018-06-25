# Widget Scope [](id=widget-scope)

As you learned earlier, roles can be scoped by the instance, by Site, or by an
organization. A role only takes effect within its scope. For example, a Message
Boards Administrator role with complete access to the Message Boards
has different permissions based on the role's scope. If it's a instance role,
members have permission to administer message boards across the entire instance.
If it's a Site role, members only have permission to administer message boards
within the Site where they've been assigned the role. For organizations with
Sites, Site roles are automatically assigned to organization members based on
the organization roles they have. So for an organization-scoped Message Boards
administrator role, members only have permission to administer message boards
within the Site of the organization that assigned the role to them. 

You've also heard the word *scope* refer to the data set of an widget. By 
default, when a widget is added to a page in a Site, it is *scoped* for that 
Site. This means that its data belongs to that Site. If the widget is added to 
a page in a different Site, it employs a completely different data set. This 
enables you to place a Message Boards widget in one Site with one set of 
categories and threads, and place another Message Boards widget in different 
Site with a different set of categories and threads.

Scoping by Site means that you can only have one Message Boards widget per 
Site. If you add one Message Boards widget to a page in a Site and add another 
Message Boards widget to a different page in the same Site, the second Message 
Boards widget contains exactly the same data as the first. This is because, by 
default, the Message Boards widget is scoped by Site. Most of @product@'s other 
widgets also default to being scoped by Site.

To avoid this limitation, many Liferay widgets can be scoped by page. The data 
sets of page-scoped widget serve a single page, not an entire Site. If you set 
the scope of a widget to *page* instead of *Site*, you can add any number of 
these widgets to different pages, and then they have different sets of data. 
This allows you to have more than one message board per Site if you wish. Most 
widgets, however, default to the "native" configuration, and have their scopes 
set to the Site where they are placed.

Unless otherwise noted, all the widget in this chapter support scoping by 
instance (global), Site (default), or page. This grants you some flexibility in 
how you want to set up your Liferay instance. You can configure the scope of an 
app with just a few simple steps.

1.  Click the *Options* icon (![Options](../../../images/icon-options.png)) in
    the app window.

2.  Select *Configuration*.

3.  Select the *Scope* tab.

4.  Use the drop-down menu to set the scope.

![Figure 1: You can change the scope of your application by navigating to its Configuration menu.](../../../images/changing-widget-scope.png)

Once you've defined a page scope for an widget, the Menu provides a
*Default Scope* dropdown that allows you to select the page you want to create
content on.

That's all it takes to change the scope for a particular widget. By setting the 
scope to the current page, you can add as many of these widgets to a site as 
you want, provided they are all added to separate pages. 

Another useful feature of Liferay's apps is Configuration Templates.  
