# Users and Organizations

*Users* and *organizations* are fundamental entities in Liferay. If your portal
requires people (even just a set of site administrators) to have an account
that they sign into to do anything in the portal, you need to know about users.
If your users are at all divided hierarchically, like into departments,
you'll find that organizations are helpful. 

Whether or not you've ever used Liferay before, you're probably not surprised to
hear that users and organizations are managed in the Control Panel's *Users and
Organizations* section. If it were any different, it'd be weird. 

Consider the Lunar Resort portal. Consider what you'd do if 

- an employee leaves the company to join that pesky competitor, Martian Resort
  and Luxury Spa.
- an employee joins the resort as a new Mechanical Crew member.
- an employee is promoted from Crew Supervisor to Department Head, and needs the requisite
permissions within @product@.
- you need to organize the users by department.
- a new department is added to the Lunar Resort and the employees need their own
internal website.
- an employee gets married, and their name changes.

The user tasks listed above are all resolved in the Users and Organizations
section of the Control Panel.

## What are Users? [](id=what-are-users)

In case there's any confusion over the term, a User in Liferay is an entity that
can sign into the portal and do something. Generally a User has more
privileges, called Permissions, than a Guest of your site, who does not sign in.
Users are assigned a Role (the Power User Role by default), and a Role is what
defines the user's privileges.

Understanding users is pretty straightforward. Organizations are a bit trickier,
but a smart administrator like you is undoubtedly up to the challenge. Read more
about organizations
[here](/discover/deployment/-/knowledge_base/7-0/organizations).

The remaining articles in this section give you the practical guidance on
managing (creating, deleting, editing, and more) users and organizations.
