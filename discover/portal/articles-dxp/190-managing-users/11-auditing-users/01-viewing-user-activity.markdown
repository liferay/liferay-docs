# Viewing Audit Events [](id=using-audit-events)

It's easy to use audit events to view activities in your @product@. Go to
*Control Panel* &rarr; *Configuration* &rarr; *Audit* to see a list of the
events Liferay has already captured (see the figure below), along with an
interface for searching for events. You can browse the list but you'll likely
need to use the search to find what you're looking for.

![Figure 1: @product@ captures and stores events.](../../images-dxp/audit-list-events.png)

The figure above shows that John Watson logged in and performed some actions on
the site. To view details about any of these events, all you need to do is click
on an entry. You'll then see something like the figure below. 

![Figure 2: Clicking an event in the list shows the details of that event. This event shows that Test Test updated his user account. Specifically, it shows that he updated his `prefixId` from `1` to `4`. The `prefixId` value represents a prefix for a real name like "Dr.", "Mr.", "Mrs.", or "Ms."](../../images-dxp/audit-detail.png)

As you can see, depending on how many users you have, this list can get
populated very quickly. That's why page view events aren't displayed by default.
They'll clutter up your audit report, since they'll definitely be the most often
triggered event. 

+$$$

**Note:** You can add page view events to your audit report, but keep in mind
that doing so will add LOTS of events. Add this property to your
`portal-ext.properties` file:

    audit.message.com.liferay.portal.kernel.model.Layout.VIEW=true

In Liferay's code, pages are referred to as *layouts*. Setting this to `true`,
therefore, records audit events for page views. It's turned off by default
because this is too fine-grained for most installations. 

Once you've added the property, restart your Liferay server.

$$$

Now that you know how to browse and view audit events, let's learn how to search
for specific events.

## Finding Audit Events[](id=finding-audit-events)

Finding what you want in a big list of events is like searching for a needle in
a haystack. This is why the Audit application provides a robust search
mechanism. By default, it looks pretty simple: there's only a single field for
searching. Clicking the *magnifier* icon next to the search bar, however,
reveals an advanced search dialog broken out by various fields you can use in
your search. 

Let's look at the options we have for search. 

**Match:** You can search for matches to *all* the fields you've specified or
*any* single field. 

**User ID:** Specify the user ID you'd like to search for. This would be the
user who performed some action you'd like to audit. 

**User Name:** Specify the user name you'd like to search for. This is often
easier than searching for a user ID, especially if you don't have access to the
Liferay database to find the user ID. 

**Resource ID:** Specify the ID of the resource that was modified or viewed in
this audit record. 

**Class Name:** Specify the name of the resource that was modified or viewed
in this audit record. For example, you could search for User resources to see if
someone modified a user's account. 

**Resource Action:** Specify an action that was performed on the resource. This
could be any one of the following: `add`, `assign`, `delete`, `impersonate`,
`login`, `login_failure`, `logout`, `unassign`, or `update`. 

**Session ID:** Specify the session ID to search for. You'd use this if you were
correlating a session ID from your web server logs with activity in Liferay. 

**Client IP:** Specify the IP address of the client that performed the activity
you wish to audit. 

**Client Host:** Specify the host name of the client that performed the activity
you wish to audit. 

**Server Name:** Specify the server name upon which the activity occurred. If
you're using a cluster, each member of the cluster can be individually queried. 

**Server Port:** Specify the server port upon which the activity occurred. You'd
need this if you run a "vertical" cluster of multiple VMs on the same machine. 

**Start Date:** Specify the low end of the date range you wish to search. 

**End Date:** Specify the high end of the date range you wish to search. 

Using this form, if you wanted to check to see if someone unassigned a user from
a particular role, you might search for a resource name of *user* and a resource
action of *unassign*.

![Figure 3: Searching for audit events is easy with the advanced search form provided by the Audit application. You can specify various search criteria to find the types of events you're looking for.](../../images-dxp/audit-unassign-search.png)

Once you have the results of your search, you can click on any of the records
returned to see the detail page for that record. The figure below shows, in this
case, that the default administrative user removed the Power User role from John
Watson.

![Figure 4: If you've delegated administration to multiple users, you can use the Audit application to determine who made what change. And, of course, you'll never leave the default administrative user enabled on a production system, right?](../../images-dxp/audit-unassign-detail.png)

As you can see, the Audit application gives you a lot of power to see what's
happening. Use this information to troubleshoot problems, determine ownership of
particular actions, or, as Harry (from the story in the introduction) is about
to do, find out who made permission changes they weren't supposed to make. 
