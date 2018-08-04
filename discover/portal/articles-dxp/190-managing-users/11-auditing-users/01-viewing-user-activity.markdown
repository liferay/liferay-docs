# Viewing Audit Events [](id=using-audit-events)

The Audit app lets you view activities in your @product@ installation. Access it 
by navigating to *Control Panel* &rarr; *Configuration* &rarr; *Audit*. The app 
displays a searchable list of captured events. You can browse the list, but 
searching it is typically faster. 

This figure shows that John Watson logged in and performed some actions on the 
site. Click an entry to view details about any of these events. 

![Figure 1: The Audit app displays the events it captures in a searchable list.](../../../images-dxp/audit-list-events.png)

![Figure 2: Click an event in the list to show its details. The details for this event show that John Watson updated his user account's `prefixId` from `1` to `4`. The `prefixId` represents a name prefix like Dr., Mr., Mrs., or Ms.](../../../images-dxp/audit-detail.png)

As you can see, depending on how many users you have, this list can get
populated very quickly. That's why page view events aren't displayed by default.
They'll clutter up your audit report, since they'll definitely be the most 
frequent event. 

+$$$

**Note:** You can add page view events to your audit report, but keep in mind
that doing so will add LOTS of events. To do so, add this property to your
`portal-ext.properties` file:

    audit.message.com.liferay.portal.kernel.model.Layout.VIEW=true

@product@'s code refers to pages as *layouts*. Setting this property to `true` 
therefore records audit events for page views. It's turned off by default
because this is too fine-grained for most installations. 

Once you've added the property, restart your server. 

$$$

## Finding Audit Events[](id=finding-audit-events)

Finding what you want in a big list of events is like searching for a needle in
a haystack. This is why the Audit app has a robust search mechanism. By default, 
it looks pretty simple: there's only a single search field. Clicking the 
*magnifier* icon next to the search bar, however, reveals an advanced search 
dialog broken out by various fields you can use in your search. 

Here are the available search options:

-   **Match:** Search for matches to *all* the fields you've specified or *any* 
    single field. 

-   **User ID:** The user ID to search for. This would be the user who performed 
    some action you'd like to audit. 

-   **User Name:** The user name to search for. This is often easier than 
    searching for a user ID, especially if you don't have access to the database 
    containing the user ID. 

-   **Resource ID:** The ID of the resource that was modified or viewed in this 
    audit record. 

-   **Class Name:** The name of the resource that was modified or viewed in this 
    audit record. For example, you could search for user resources to see if 
    someone modified a user's account. 

-   **Resource Action:** An action performed on the resource. This could be any 
    one of the following: `add`, `assign`, `delete`, `impersonate`, `login`, 
    `login_failure`, `logout`, `unassign`, or `update`. 

-   **Session ID:** The session ID to search for. You can use this to correlate 
    a session ID from your web server logs with activity in @product@. 

-   **Client IP:** The IP address of the client that performed the activity you 
    wish to audit. 

-   **Client Host:** The host name of the client that performed the activity you 
    wish to audit. 

-   **Server Name:** The name of the server in which the activity occurred. If 
    you're using a cluster, each member of the cluster can be individually 
    queried. 

-   **Server Port:** The server port in which the activity occurred. You need 
    this if you run a vertical cluster of multiple VMs on the same machine. 

-   **Start Date:** The low end of the date range you wish to search for. 

-   **End Date:** The high end of the date range you wish to search. 

For example, to check if someone unassigned a user from a particular role, you 
might search for a resource name of *user* and a resource action of *unassign*. 
Once you have the search results, you can click any of the returned records to 
see that record's detail page. 

![Figure 3: Searching for audit events is easy with the Audit app's advanced search form. You can specify various search criteria to find the types of events you're looking for.](../../../images-dxp/audit-unassign-search.png)

![Figure 4: This record shows that the default administrative user removed the Power User role from the user Test Test.](../../../images-dxp/audit-unassign-detail.png)

As you can see, the Audit app gives you a lot of power to see what's happening. 
Use this information to troubleshoot problems, determine ownership of particular 
actions, or, as Harry (from the story in the introduction) is about to do, find 
out who made permission changes they weren't supposed to make. 
