# Scope [](id=scope-lp-6-2-use-useportal)

As we learned earlier, roles can be scoped by the portal, by a site, or by an
organization. A role only takes effect within its scope. For example, a Message
Boards Administrator role with complete access to the Message Boards portlet
has different permissions based on the role's scope. If it's a portal role,
members have permission to administer message boards across the entire portal.
If it's a site role, members only have permission to administer message boards
within the site where they've been assigned the role. For organizations with
sites, site roles are automatically assigned to organization members based on
the organization roles they have. So for an organization-scoped Message Boards
administrator role, members only have permission to administer message boards
within the site of the organization that assigned the role to them. 

We also use the word *scope* to refer to the data set of a portlet. By default,
when a portlet is added to a page in a site, it is *scoped* for that site. This
means that its data belongs to that site. If the portlet is added to a page in
a different site, it employs a completely different data set. This enables you
to place a Message Boards portlet in one site with one set of categories and
threads, and place another Message Boards portlet in different site with a
different set of categories and threads.

Scoping by site means that you can only have one Message Boards portlet per
site. If you add one Message Boards portlet to a page in a site and add another
Message Boards portlet to a different page in the same site, the second Message
Boards portlet contains exactly the same data as the first. This is because, by
default, the Message Boards portlet is scoped by site. Most of Liferay's other
portlets also default to being scoped by site.

To avoid this limitation, many Liferay portlets can be scoped by page. In this
case, the data sets of page-scoped portlets serve a single page, not an entire
site. If you set the scope of a portlet to *page* instead of *site*, you can
add any number of these portlets to different pages, and then they have
different sets of data. This allows you to have more than one message board per
site if you wish. Most portlets, however, default to the "native"
configuration, and have their scopes set to the site where they are placed.

Unless otherwise noted, all the portlets in this chapter support scoping by
portal (global), site (default), or page . This grants you some flexibility in
how you want to set up your portal. You can configure the scope of a portlet
with just a few simple steps.

1.  Click the *Options* icon in the portlet window (the gear icon).

2.  Select *Configuration*.

3.  Select the *Scope* tab.

4.  Use the drop-down menu to set the scope.

![Figure 4.9: You can change the scope of your portlet by navigating to its Configuration menu.](../../images/05-changing-portlet-scope.png)

That's all it takes to change the scope for a particular portlet instance. By
setting the scope to the current page, you can add as many of these portlets to a
site as you want, provided they are all added to separate pages. 

Another useful feature of Liferay's portlets is Archived Setups.  
