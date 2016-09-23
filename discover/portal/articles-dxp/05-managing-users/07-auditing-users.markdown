# Auditing Users [](id=auditing-users)

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

"Forget the ticket. Let's go see the admin now," you say. 

And off you go, two floors down, to the far end of the building where, as you
approach, you can already hear stress in the admin's voice as he tries to
reassure someone on the phone. 

"Yes, Mr. Jones. Yes, I'll fix it." (*Jones? The president of the company?* goes
through your mind.) "I'll get on it right away, Mr. Jones. It was just a
mistake; I'll fix it. Thank you, Mr. Jones," and he hangs up the phone. 

"Problems?" you ask the admin, whose name is Harry. He does look rather
harried. 

"Yeah, Tom," he says. "Somebody changed a bunch of permissions in @product@--it
wasn't me. I'm assuming you and Dick are here because of the same problem?" 

"Yup," you say. "I lost access to a document repository folder." 

"And I lost access to a wiki," Dick says helpfully. 

"It was probably due to some site membership change. Let's take a look at the
audit portlet in the control panel and see what happened." 

When in the course of human events it becomes necessary to see what users are
doing on your @product@, you'll find Liferay makes this easy. If you're a @product@
customer, you have access to the Audit application. In combination with some
settings in `portal-ext.properties`, the Audit application enables you to see
all the activity that occurs in your @product@. Using this, you can quickly find
out what changes were made and by whom. If you've delegated permission granting
to any group of people, this is an essential feature you're likely to use. 

We'll come back to Tom, Dick and Harry's story later in the chapter. For now,
let's look at how to configure and use Liferay's Audit application so you can do
the same thing Harry's about to do. 

## Using Audit Events [](id=using-audit-events)

It's easy to use audit events to view activities in your @product@. Navigate to the
Control Panel and you'll find an entry in the Configuration section called
*Audit*. Clicking on *Audit* shows you a list of the events Liferay has already
captured (see the figure below), along with an interface for searching for
events. You can browse the list but you'll likely need to use the search to find
what you're looking for.

![Figure 1: @product@ captures and stores events.](../../images-dxp/audit-list-events.png)

The figure above shows that Joe Bloggs logged in and performed some actions on
the site. To view details about any of these events, all you need to do is click
on an entry. You'll then see something like the figure below. 

![Figure 2: Clicking an event in the list shows the details of that event. This event shows that Test Test updated his user account. Specifically, it shows that he updated his `prefixId` from `0` to `101`. The `prefixId` value represents a prefix for a real name like "Dr.", "Mr.", "Mrs.", or "Ms."](../../images-dxp/audit-detail.png)

As you can see, depending on how many users you have, this list
can get populated very quickly. That's why page view events aren't displayed by
default. They'll clutter up your audit report, since they'll definitely be the
most often triggered event. 

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

## Viewing Audit Reports [](id=viewing-audit-reports)

Finding what you want in a big list of events is, to use the expression, like
searching for a needle in a haystack. This is why the Audit application provides a
robust searching mechanism. By default, it looks pretty simple: there's only a
single field for searching. Clicking the *gear* icon next to the search bar,
however, reveals an advanced search dialog broken out by various fields you can
use in your search. 

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
case, that the default administrative user removed the Power User role from
James Jeffries.

![Figure 4: If you've delegated administration to multiple users, you can use the Audit application to determine who made what change. And, of course, you'll never leave the default administrative user enabled on a production system, right?](../../images-dxp/audit-unassign-detail.png)

As you can see, Liferay's Audit application give you a lot of power to see what's
happening in your @product@. You can use this information to troubleshoot problems,
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

