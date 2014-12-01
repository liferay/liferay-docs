# Audit Trails [](id=audit-trails)

![EE Only Feature](../../images/ee-feature-web.png)

You've just finished lunch and are ready to get back to work. You have a site in
Liferay you use to manage your project, and before you left, you were about to
create a folder in your Documents and Media library for sharing some
requirements documentation. Sitting down at your desk, you navigate to the
repository and attempt to create the folder. 

*You do not have permission to perform this action*, Liferay helpfully tells
you. 

"*What?*" you blurt accidentally in surprise. "This is *my* project!" 

"Ah, you too?" asks a co-worker helpfully from over the cube wall. "I lost
access to a wiki I was updating just a few minutes ago. I was about to enter a
support ticket for it." 

"Forget the ticket. Let's go see the portal admin now," you say. 

And off you go, two floors down, to the far end of the building where, as you
approach, you can already hear stress in the portal admin's voice as he tries to
reassure someone on the phone. 

"Yes, Mr. Jones. Yes, I'll fix it." (*Jones? The president of the company?* goes
through your mind.) "I'll get on it right away, Mr. Jones. It was just a
mistake; I'll fix it. Thank you, Mr. Jones," and he hangs up the phone. 

"Problems?" you ask the portal admin, whose name is Harry. He does look rather
harried. 

"Yeah, Tom," he says. "Somebody changed a bunch of permissions in the portal--it
wasn't me. I'm assuming you and Dick are here because of the same problem?" 

"Yup," you say. "I lost access to a document repository folder." 

"And I lost access to a wiki," Dick says helpfully. 

"It was probably due to some site membership change. Let's take a look at the
audit portlet in the control panel and see what happened." 

When in the course of human events it becomes necessary to see what users are
doing on your portal, you'll find Liferay makes this easy. If you're a Liferay
Enterprise Edition customer, you have access to two plugins--a hook and a
portlet--that, in combination with some settings in `portal-ext.properties`,
enable you to see all the activity that occurs in your portal. Using this, you
can quickly find out what changes were made and by whom. If you've delegated
permission granting to any group of people, this is an essential feature you're
likely to use. 

We'll come back to Tom, Dick and Harry's story later in the chapter. For now,
let's look at how to install Liferay's audit plugins so you can do the same
thing Harry's about to do. 

## Installing and Configuring the Audit Plugins [](id=installing-and-configuring-the-audit-plugins)

Liferay's audit functionality is composed of two parts: a back-end piece that
hooks into Liferay events and a front-end piece that gives you an interface to
see what's happening. Both of these plugins are included in the Audit EE app
which is available on Liferay Marketplace. Please refer to this guide's chapter
on [Leveraging the Liferay
Marketplace](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/leveraging-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en)
for information on installing plugins.

Once installed, you can set two properties in your `portal-ext.properties` file
to tweak the default settings. 

**com.liferay.portal.servlet.filters.audit.AuditFilter:** By default, this is
set to `false`, because the audit plugins aren't installed by default. When you
set it to `true`, the audit hook is able to capture more information about
events, such as the client host and the client's IP address. 

**audit.message.com.liferay.portal.model.Layout.VIEW:** In Liferay's code, pages
are referred to as *layouts*. Setting this to `true`, therefore, records audit
events for page views. It's turned off by default because this may be too
fine-grained for most installations. 

Once you've decided if you're going to use one or both of the two settings
above, place them in your `portal-ext.properties` file and restart your Liferay
server. Once it comes up, audit events are captured by Liferay, and you'll be
able to use them to see what's happening in your portal. 

## Using Audit Events [](id=using-audit-events)

Now that you're capturing audit events, it's easy to use them to view activities
in your portal. Navigate to the Control Panel and you'll find a new entry in the
Configuration section called *Audit Reports* (see the figure below). 

![Figure 16.14: Once the Audit EE app has been installed, an Audit Reports entry appears in the Control Panel.](../../images/control-panel-audit-reports.png)

Clicking on *Audit Reports* shows you a list of the events Liferay has already
captured (see the figure beblow), along with an interface for searching for
events. You can browse the list but you'll likely need to use the search to find
what you're looking for.

![Figure 16.15: Liferay captures and stores events as soon as the Audit EE app has been installed.](../../images/audit-list-events.png)

The figure above shows that Joe Bloggs logged in and performed some actions on
the site. To view details about any of these events, all you need to do is click
on an entry. You'll then see something like the figure below. 

![Figure 16.16: Clicking an event in the list shows the details of that event. This event shows that Joe Bloggs updated his user account. Specifically, it shows that he updated his `prefixId` from `0` to `11015`. The `prefixId` value represents a prefix for a real name like "Dr.", "Mr.", "Mrs.", or "Ms."](../../images/audit-detail.png)

As you can see, depending on how many users you have in your portal, this list
can get populated very quickly. For this reason, it's a good idea to keep the
`audit.message.com.liferay.portal.model.Layout.VIEW` property set to `false`.
This way, you don't clutter up your audit events with multiple page view events,
which will definitely be the most often triggered event in your portal. 

Now that you know how to browse and view audit events, let's learn how to search
for specific events.

## Viewing Audit Reports [](id=viewing-audit-reports)

Finding what you want in a big list of events is, to use the expression, like
searching for a needle in a haystack. This is why the audit portlet provides a
robust searching mechanism. By default, it looks pretty simple: there's only a
single field for searching. Clicking the *gear* icon next to the search bar,
however, reveals an advanced search dialog broken out by various fields you can
use in your search. 

Let's look at the options we have for search. 

**Match:** You can search for matches to *all* the fields you've specified or
*any* single field. 

**User ID:** Specify the user ID you'd like to search for. This would be the
user who performed some action in the portal you'd like to audit. 

**User Name:** Specify the user name you'd like to search for. This is often
easier than searching for a user ID, especially if you don't have access to the
Liferay database to find the user ID. 

**Resource ID:** Specify the ID of the resource that was modified or viewed in
this audit record. 

**Resource Name:** Specify the name of the resource that was modified or viewed
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

Using this form, if you wanted to check to see if someone in the portal
unassigned a user from a particular role, you might search for a resource name
of *user* and a resource action of *unassign*. The results of such a search
might look something like the figure below. 

<!-- Need to wait for UI of the Audit Portlet EE to be updated for 6.2 before
the screenshot below can be updated. -->

![Figure 16.17: Searching for audit events is easy with the advanced search form provided by the audit portlet. You can specify various search criteria to find the types of events you're looking for.](../../images/audit-unassign-search.png)

Once you have the results of your search, you can click on any of the records
returned to see the detail page for that record. The figure below shows, in this
case, that the default administrative user removed the Power User role from Joe
Bloggs.

![Figure 16.18: If you've delegated portal administration to multiple users, you can use the audit plugins to determine who made what change. And, of course, you'll never leave the default administrative user enabled on a production system, right?](../../images/audit-unassign-detail.png)

As you can see, Liferay's audit portlets give you a lot of power to see what's
happening in your portal. You can use this information to troubleshoot problems,
determine ownership of particular actions, or, as Harry is about to do, find out
who made permission changes they weren't supposed to make. 

## Conclusion of the Story [](id=conclusion-of-the-story)

"Okay," says Harry, "let's fire up Liferay's audit system and see if we can
figure out what happened." 

You and Dick stand behind Harry's chair and watch as he enters a query into a
form on the audit portlet. Clicking *search*, the screen fills up with audit
events. 

"Wow, that's a lot of unassign events." Harry says. "And look who the culprit
is," he adds sarcastically. 

"Who's Melvin Dooitrong?" Dick asks. 

"That's my new intern," Harry says. "I'm gonna kill him." Harry pushes out his
chair and walks down the row of cubes to the end, where a kid no more than 20
years old with disheveled hair sits, earbuds in his ears. 

"Hey Melvin," Harry says as Melvin turns around to face him. "Didn't I ask you
to move that set of users from site membership to organization membership?" 

"Yeah," Melvin says, "I did that already." 

"How'd you do it?"

"It was going to take a while to do it manually, so I wrote a script and
executed it in the scripting host," Melvin replies, matter-of-factly. 

"You did, did you? Well, guess what? Your script removed *everybody* from *all*
sites."

"*What?*" 

"Yeah, and now you're going to start adding them back, one by one, manually,
starting with Mr. Jones...." 

Tom and Dick back away slowly from Melvin's cube as Harry and Melvin continue to
have their--let's call it a discussion. One thing is clear: they're having a
better day than Melvin is.

