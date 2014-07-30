# Using Liferay as a web platform [](id=using-liferay-as-a-web-platform-lp-6-2-use-useportal)

We can't even begin to imagine what you're thinking of building, but whatever it
is, you're going to put your heart and soul into it. Building it on Liferay's
web platform can give you a leg up. It provides everything you need to support
your application, so you can concentrate solely on what *you're* building, and
not the rest of the features your users expect will come along with it. 

Imagine your application for a moment. Does it require users to register on
your site? Can users comment on content contained in your application? Is there
something that users can tag or categorize? If you think about the layout of
the application, would it benefit from modularization? Could you make use of a
rich JavaScript framework with many components built into it?  How about
security--will you need to make information available to some users, but not to
all users? Liferay Portal has all of this and more available to developers. 

Liferay Portal's development framework is a great help when you're building a
web application. While the framework itself is covered in other resources such
as the *Liferay Developer's Guide* or *Liferay in Action*, the strengths of
Liferay as a platform are also apparent once you've finished writing your
application. 

For example, bug fixes to your applications are easy to apply, because Liferay
applications are hot deployed to the running server. Liferay's Marketplace gives
you a ready-made shopping center for your applications. And Liferay's web
services and JSON architecture make it easy for you to share data from your
applications with other systems running on different platforms. 

You get all this--not to mention the automatic Facebook and OpenSocial
integration mentioned above--with Liferay's development platform. It's a very
powerful platform, and certainly worth your investigation. 

## A great integration platform 

If you're building an enterprise system, portals were designed in the first
place to be a single point of entry to your users' applications and content.
Since Liferay Portal integrates well with user directories such as LDAP and
Active Directory, and single sign-on systems such as SAML and OpenSSO, it fits
well into your enterprise systems. This allows you to use it as an integration
platform for existing applications. 

Liferay Portal, since it adheres to the JSR standard for portlets, was designed
from the ground up for application integration. You can add any application
installed on the system to any page in the portal. You can make use of APIs
provided by other systems to integrate their data into an application window in
Liferay. And applications you create with Liferay's Service Builder API are web
service-enabled from the start. 

## Hosting multiple sites on Liferay Portal 

Liferay Portal excels as a multi-site hosting platform. You can use it to host
multiple sites under the same overall architecture, or you could host several
completely different websites based solely on Liferay's ability to serve
multiple instances of itself from the same physical installation. 

In the first scenario, Liferay Portal's Sites architecture lets you create
multiple, different websites that have public and/or private sets of pages and
as many pages within those sets as you'd like. Users can join and leave open
sites with one click. Some sites can be defined as restricted or private, and
users can't access those unless they're added by site administrators. All of
these sites can have canonical domain names such as baseballcards.liferay.com or
progrock.liferay.com. 

Using this construct, you can build anything from Facebook, to Yahoo Groups, to
SourceForge, to the now-defunct-but-once-loved Geocities. There is no limit to
the number of sites you can have: some Liferay installations have only one or
two, but others have many thousands. For those larger installations, Liferay
Portal contains a complete site membership management framework that lets
administrators manage automatic site members for groups of users. It really is
built to scale to the size you need. 

In the second scenario, Liferay Portal lets you create completely separate
instances of itself from the same installation. Users, groups, organizations,
sites, and roles from each instance are kept completely separate. If a user
registers for a user id on one instance, he or she would have to register as a
new user on another instance as well. 

This lets you host many different, separate websites from one Liferay Portal
installation. Users of each instance have access to the same powerful content
management, collaboration, social, and web development platform that they'd have
if they were operating from a single, standalone installation.

Okay, so maybe this still isn't enough for you. Let's see how you can customize
Liferay Portal so that it looks and operates exactly the way you've envisioned
for your site. 
