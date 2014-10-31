# Beginning Development with Liferay Faces [](id=beginning-development-with-liferay-faces)

Getting started with a JSF application for Liferay is very easy. This learning
path guides you step-by-step through creating and developing a JSF application.
In particular, you'll develop a guestbook application which users can use to add
messages to a page. When you're finished, the final guestbook application will
look like this: 

![Figure 1: The JSF guestbook can store and display entries for many different guestbooks.](../../images/final-jsf-guestbook.png)

You may be wondering how and why JSF is so easy to deploy to Liferay. Isn't JSF
a separate technology? Liferay supports JSF applications with the use of
[*Liferay Faces*](http://www.liferay.com/community/liferay-projects/liferay-faces/overview),
which is an umbrella project that provides support for the JSF standard.

JSF portlets use *Liferay Faces Bridge*, which supports deploying JSF web
applications as portlets on any JSR 286 (Portlet 2.0) compliant portlet
container, like Liferay Portal 5.2, 6.0, 6.1, and 6.2. You'll explore more about
the bridge later on, but you won't have to worry about it when creating your JSF
application, since it's configured behind the scenes. 

Ready to begin creating a JSF application for Liferay Portal? 
