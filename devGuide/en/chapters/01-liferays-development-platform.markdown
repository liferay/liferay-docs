# Realizing the Benefits of Liferay's Development Platform [](id=liferays-development-platform-liferay-portal-6-2-dev-guide-01-en)

Welcome to the Developer's Guide, Liferay's official guide for developers. If
you're interested in developing applications on Liferay portal or customizing
it, you're in the right place. This guide assumes you already know
what a portal is and how to use Liferay from an end-user perspective. If you
don't, please read the [What is a
Portal?](http://www.liferay.com/products/what-is-a-portal) article on
liferay.com and the [What is
Liferay?](http://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/what-is-liferay-liferay-portal-6-2-user-guide-01-en)
chapter in [*Using Liferay Portal 6.2*](http://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/using-liferay-portal-6-3). 

This chapter summarizes how to to develop applications for Liferay and how to
customize Liferay's built-in applications, themes, and settings. You will
develop Liferay plugins to encapsulate these applications and customizations.
Finally, we'll talk about technologies and tools available to use as you develop
your plugins. 

This chapter covers the following: 

- **Developing Applications for Liferay:** Ways to develop new applications and
  reuse existing applications 
- **Extending and Customizing Liferay:** Options for extending functionality and
  customizing your portal applications, themes, and templates 
- **Choosing Your Development Tools:** Comparison of tools available for
  developing applications for Liferay 

Let's talk about developing applications for Liferay. 

## Developing Applications for Liferay [](id=developing-applications-for-liferay-liferay-portal-6-2-dev-guide-01-en)

According to Wikipedia "A web application is an application that is accessed
over a network such as the Internet or an intranet." A portal application is a
web application that can civilly coexist with other applications. Portal
applications leverage functionality provided by the portal platform to reduce
development time and deliver a more consistent experience to end users. 

As a developer wanting to run your own applications on top of Liferay Portal,
you probably want to know *what's the best and quickest way to do it?* Liferay
supports two main, standards-based technologies for incorporating your
applications into Liferay: **Portlets** and **OpenSocial gadgets**. 

### Portlets [](id=portlets-liferay-portal-6-2-dev-guide-01-en)

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

### OpenSocial Gadgets [](id=opensocial-gadgets-liferay-portal-6-2-dev-guide-01-en)

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

### Reusing Existing Web Applications [](id=reusing-existing-web-applications-liferay-portal-6-2-dev-guide-01-en)

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

### Supported Technology Frameworks [](id=supported-technology-frameworks-liferay-portal-6-2-dev-guide-01-en)

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

Since version 6, however, Liferay has its own library called *AlloyUI* which is
based on YUI 3. AlloyUI has a large set of components specifically designed for
modern user interfaces. Liferay's core portlets make use AlloyUI. You can use
AlloyUI for your custom portlets or use another JavaScript library, as long as
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

## Extending and Customizing Liferay [](id=extending-and-customizing-liferay-portal-6-2-dev-guide-01-en)

Liferay provides many out-of-the-box features, including a fully featured
content management system, a social collaboration suite, and several
productivity tools. For most installations, these features are exactly what you
need; but sometimes you'll want to extend these features or customize their
behavior and appearance. 

Liferay is designed to be customized. Multiple plugins and plugin types can be
combined into a single WAR file. Let's take a look at these plugin types and how
they can be used. 

### Customizing the Look and Feel: Themes [](id=customizing-look-and-feel-with-themes-liferay-portal-6-2-dev-guide-01-en)

Themes let you dictate your site's look and feel. You can specify color schemes
and commonly used images. You'll apply styling for UI elements such as fonts,
links, navigation elements, page headers, and page footers, using a combination
of CSS and Velocity or FreeMarker templates. With Liferay's AlloyUI API
framework, you use a consistent interface to common UI elements that make up
your page. This makes it easy to create sites that respond well to the window
widths of your users' desktop, tablet, and mobile devices. Most importantly,
themes let you focus on designing your site's UI, while leaving its
functionality to the portlets. 

### Adding New Predefined Page Layouts: Layout Templates [](id=adding-new-predefined-page-layouts-liferay-portal-6-2-dev-guide-01-en)

Layouts are similar to themes, except they specify the *arrangement* of portlets
on a page rather than their look and feel. You can create custom layout
templates to arrange portlets just the way you like them. And you can even embed
commonly used portlets. Like themes, layout templates are also written in
Velocity and are hot-deployable. 

### Customizing or Extending the Out-of-Box Functionality: Hook Plugins [](id=customize-or-extend-functionality-liferay-portal-6-2-dev-guide-01-en)

Hook plugins are how you customize the core functionality of Liferay at many
predefined extension points. Hook plugins are used to modify portal properties
or to perform custom actions on startup, shutdown, login, logout, session
creation, and session destruction. Using service wrappers, a hook plugin can
replace any of the core Liferay services with a custom implementation. Hook
plugins can also replace the JSP templates used by any of the default portlets.
Best of all, hooks are hot-deployable plugins just like portlets. 

### Advanced Customization: Ext Plugins [](id=advanced-customization-with-ext-plugins-liferay-portal-6-2-dev-guide-01-en)

Ext plugins provide the largest degree of flexibility in modifying the Liferay
core, allowing you to replace essentially any class with a custom
implementation. However, it is highly unlikely that an Ext plugin written for
one version of Liferay will continue to work in the next version without
modification. For this reason, Ext plugins are only recommended for cases in
which an advanced customization is truly necessary, and there is no alternative.
Make sure you are familiar with the Liferay core so your Ext plugin doesn't
negatively effect existing funcitonality. Even though Ext plugins are deployed
as plugins, the server must be restarted for their customizations to take
effect. 

---

 ![note](../../images/tip-pen-paper.png) **Note:** If you have developed for
 Liferay 5.2 or prior releases, you may be familiar with what was known as the
 *Extension Environment*. Ext plugins were introduced in Liferay 6.0 to replace
 the extension environment in order to simplify development. For instructions on
 converting an existing Extension Environment into a plugin, see the section
 on migrating old extension environments in [Advanced Customization with Ext
 Plugins](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/advanced-customization-with-ext-plugins-liferay-portal-6-2-dev-guide-12-en).

---

Now that you're familiar with the best options for developing applications on
Liferay and customizing Liferay, let's consider some of the tools you'll be
using.

## Choosing Your Development Tools [](id=choosing-your-development-tools-liferay-portal-6-2-dev-guide-01-en)

The Java ecosystem is known for providing a variety of options for almost any
type of software development. This is advantageous because you can find the tool
that best fits your needs and the way you work. Naturally, when you get
comfortable with a tool, you want to keep using it. 

If you're a newcomer, the wide variety of tools available can be overwhelming.
Throughout this guide, we'll give you the best of both worlds showing you how to
develop plugins in two environments that use open source technologies 1) A
command-line environment that integrates with a wide variety of tools. 2) An
easy-to-use IDE that minimizes your learning curve while giving you powerful
development features. Here are those two environments:

**Apache Ant and the Plugins SDK:** Liferay provides a development environment
called the *Plugins SDK* that lets you develop plugins of all types by executing
a set of predefined commands (known as *targets*, in Ant's nomenclature). You
can use the Plugins SDK directly from the command-line and use file editors like
Emacs, Vim, EditPlus, or even Notepad. You can also integrate the Plugins SDK
with your favorite IDE, since most IDEs provide support for Apache Ant. The next
chapter includes a section on how to use the Plugins SDK. 

**Eclipse and the Liferay IDE:** Eclipse is the most popular and well known
Java IDE and it provides a wide variety of features. *Liferay IDE* is a plugin
for Eclipse that extends its functionality to facilitate developing all types of
Liferay plugins. Liferay IDE uses the Plugins SDK underneath, but you don't need
to know the SDK unless you're performing an advanced operation not directly
supported by Liferay IDE. To develop applications for Liferay Portal Enterprise
Edition (EE), use Liferay Developer Studio which extends Liferay IDE, providing
additional integration plugins such as the Kaleo Designer for Java. 

This guide shows you how to develop for Liferay using both the Plugins SDK and
Liferay IDE, to benefit you and other developers even if you don't like IDEs or
don't use Eclipse. If you use Eclipse, you'll be happy to know that we'll show
you how to develop apps using Liferay IDE in the next chapter.  

**What about if I don't like Apache Ant and I prefer to use Maven?** Many
developers prefer other command-line alternatives to Apache Ant. The most
popular of these alternatives is Maven. To support developers that want to use
Maven we have *mavenized* Liferay artifacts for referencing in your Maven
projects. See the next chapter for an in-depth look at developing plugins in
Maven. 

**What if I don't like Eclipse and prefer to use NetBeans, IntelliJ IDEA or
other another IDE?** There are many IDEs available, and each one has its
strengths. We built Liferay IDE on top of Eclipse because it's the most popular
open source option. We also want to make sure you can use the IDE of your
choice. In fact, many core developers use NetBeans and IntelliJ IDEA. Both of
these IDEs have support for integration with Apache Ant, so you can use the
Plugins SDK with them. Additionally, there is an extension to NetBeans called
the *Portal Pack* that is explicitly designed for develop plugins for Liferay. 
You can find more about the Portal Pack at
[http://contrib.netbeans.org/portalpack](http://contrib.netbeans.org/portalpack).

That's it for the introduction. Let's get started with real development work! 
