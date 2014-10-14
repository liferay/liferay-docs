# Developing applications for Liferay [](id=developing-applications-for-liferay)

According to Wikipedia "A web application is an application that is accessed
over a network such as the Internet or an intranet." A portal application is a
web application that can civilly coexist with other applications. Portal
applications leverage functionality provided by the portal platform to reduce
development time and deliver a more consistent experience to end users. 

As a developer wanting to run your own applications on top of Liferay Portal,
you probably want to know *what's the best and quickest way to do it?* Liferay
supports two main, standards-based technologies for incorporating your
applications into Liferay: **Portlets** and **OpenSocial gadgets**. 

## Portlets [](id=portlets)

Portlets are small web applications written in Java that run in a portion of a
web page. The heart of any portal implementation is its portlets, because they
contain the actual functionality. The portlet container just aggregates the set 
of portlets to appear on each page. 

Since they're entirely self-contained, portlets are the least invasive mechanism
for extending Liferay, and are also the most forward compatible development
option. They are hot-deployed as plugins into Liferay instances, resulting in
zero downtime. A single plugin can contain multiple portlets, allowing you to
split up your functionality into several smaller pieces that can be arranged
dynamically on a page. Portlets can be written using any of the Java web
frameworks that support portlet development, including Liferay's specific
frameworks: MVC Portlet and Alloy Portlet. Portlets can be used to build complex
applications since they can leverage the full suite of technologies and
libraries for the Java platform.

## OpenSocial Gadgets [](id=opensocial-gadgets)

OpenSocial gadgets are usually small applications, written using browser-side
technologies such as HTML and JavaScript. Like portlets, OpenSocial gadgets
provide a standard way to develop applications for a portal environment. From a
technology perspective, one key difference is that they don't mandate a specific
back-end technology, such as Java EE, PHP, Ruby, Python, etc. Another difference
is that they have been designed specifically to implement social applications,
while portlets were designed for any type of application. Because of this,
OpenSocial gadgets not only provide a set of technologies to develop and run
applications, but also a set of APIs that allow the application to obtain
information from the social environment such as information about a user's
profile, his activities, and his friends. 

An OpenSocial gadget is deployed in Liferay as one of the following types: 

- **Remote gadget:** is executed in a remote server but presented in a given
  page as if it were another platform application. Remote gadget deployment is
  simple, but the portal depends on the remote server for the gadget to work.
  Deployment as a remote gadget is not a viable option in some Intranet
  environments that lack full access to the Internet.
- **Local gadget:** is deployed in the Liferay server in a similar manner to
  portlets. Since a gadget is defined in an XML file, uploading this file is all
  that's necessary to deploy the gadget.

Once you've saved your new gadget, it appears as an application that
administrators can add to their site's pages. 

Liferay lets you expose portlets to the outsde world as OpenSocial gadgets. That
is, you can develop a portlet and then let anyone with access to your portlet
add it as a remote gadget to pages on other portals or social networks. 

## Reusing existing web applications [](id=reusing-existing-web-applications)

What if you already have an existing application that has not been implemented
as a portlet or OpenSocial gadget? You have many options, including:

- Rewrite the application as a portlet. 
- Create simple portlets that interact with the application (possibly using Web
  Services) and offer that functionality to end-users. 
- Create an OpenSocial gadget as a wrapper for the application. The gadget can
  use an IFrame to show part of the application in the portal page. 
- Create a portlet that integrates the remote application either using an IFrame
  or an HTTP proxy (e.g., using Liferay's WebProxy portlet). This requires
  implementing single sign-on between the portal and the application. 
- If the application is implemented using Struts 1.x, it can be converted to a
  portlet application with only a few changes. 
- If the application is implemented using JSF, it can be converted to a portlet
  application with only a few changes. 

There are many more options, each with its own merits. Reviewing them all is out
of the scope of this guide; however, the above options are worth considering. 

Next let's consider some of the technology frameworks Liferay supports. 

## Supported technology frameworks [](id=supported-technology-frameworks)

Liferay, as a platform, strives to provide compatibility with any Java
technology you may want to use to develop your applications. Thanks to the
portlet and Java EE specifications, each portlet application can use its own
set of libraries and technologies, whether they are used by Liferay or not. This
section refers mainly to portlet plugins; other plugin types are more
restricted. For example, Ext plugins can only use libraries that are compatible
with the ones used by the core Liferay code. 

Since the choice of available frameworks and technologies is very broad,
choosing the appropriate one can be daunting. We'll provide some advice to help
you choose the best frameworks for your needs, summarized as follows:

1.  *Use what you know:* If you already know a framework, that can be your
    first option (Struts 2, Spring MVC, PHP, Ruby, etc). 

2.  *Adapt to your real needs:* Component-based frameworks, such as
    JavaServer&trade; Faces (JSF), Vaadin, and Google Web Toolkit (GWT), are
    especially good for desktop-like applications. MVC frameworks, on the other
    hand, provide more flexibility. 

3.  *When in doubt, pick the simpler solution:* Portlet applications are often
    more simple to implement than standalone web applications. When in
    doubt, use the simpler framework (e.g., Liferay's MVC Portlet or Alloy
    Portlet).  

Some of the frameworks mentioned above include their own JavaScript code to
provide a high degree of interaction. That is the case with GWT, Vaadin,
and JSF implementations (e.g. ICEfaces or Rich Faces). You may prefer to write
your own JavaScript code and leverage one of the JavaScript libraries available.
You can use any JavaScript library with Liferay, including jQuery, Dojo, YUI,
Sencha (previously known as ExtJs), and Sproutcore. 

Since version 6, however, Liferay has its own library called *Alloy UI* which is
based on YUI 3. Alloy UI has a large set of components specifically designed for
modern user interfaces. Liferay's core portlets make use Alloy UI. You can use
Alloy UI for your custom portlets or use another JavaScript library, as long as
the library does not conflict with libraries referenced by other portlets
deployed in the same portal. 

Liferay's Service Builder automates creating interfaces and classes for
database persistence and service layers. It generates most of the common code
that implements database access, letting you focus on higher level aspects of
service design. You implement the local interface with your business logic, and
the remote interface with your permission checks. Objects on the portal
instance interact with the local interface, while objects outside interact with
the remote interface via JSON, SOAP, and Java RMI. 

In addition to those mentioned above, there are thousands more frameworks and
libraries available to you for handling persistence, caching, connections to
remote services, and much more. Liferay does not impose specific requirements on
the use of any of those frameworks. You, the portal developer, choose the best
tools for your projects. 
