
# Introduction [](id=introduct-12)

Welcome to the Liferay's Developers Guide, the official guide for all developers
that want to develop a portal based on Liferay or that want to develop an
application that anyone can use in their Liferay installation. This guide will
assume that you already know what a portal is and know how to use Liferay from
an end-user perspective. If you don't, it is recommended that you read the
[*What is a Portal?*](http://www.liferay.com/products/what-is-a-portal)
white-paper and the Introduction, Liferay Collaboration Suite chapters of [Using
Liferay Portal
6.1](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide).

This first chapter introduces the technologies and tools that you will be able
to use to develop applications and also to extend or customize functionality
that Liferay provides out of the box to adapt them to your needs.

The main mechanism that you as a developer will use to achieve all of the above
in a modular way are Liferay Plugins. Liferay Plugins are always distributed as
Web Archives (`.war` files), and deployed through Liferay's deploy mechanisms.
There are different types of plugins to use for different purposes. The
following sections describe how to develop applications for Liferay, how to
perform customizations and the types of plugins that you can use in each case.

We will discuss the following topics in this chapter:

- *Developing applications for Liferay:* Ways to develop new applications and
reuse existing applications

- *Extending and customizing Liferay:* Options for extending functionality and
customizing your portal applications, templates, and themes

- *Choosing the best tool for the job:* Comparison of tools available for
developing applications for Liferay

Let's get started and look at developing applications for Liferay.

## Developing applications for Liferay [](id=developing-applications-for-lifer-3)

According to Wikipedia "A web application is an application that is accessed
over a network such as the Internet or an intranet." A portal application is a
web application that can coexist with many other applications in a civilized way
and also that can leverage functionality provided by the portal platform to
reduce development time and deliver a more consistent experience to end users.

If you are reading this, you probably want to (or need to) develop an
application that runs on top of Liferay Portal. And you might be looking for an
answer to the question of *what is the best and quickest way to do it?* Liferay
provides two main ways to develop applications, and both are based on standards:

- Portlets: portlets are small web applications written in Java that follow a
certain set of rules to allow cohabitation within the same portal or even within
the same page. Portlets can be used to build applications as complex as you want
since the full suite of technologies and libraries for the Java platform can be
used.

- OpenSocial Gadgets: gadgets are usually small applications, written using
browser side technologies such as HTML and Javascript. One interesting benefit
of gadgets is that they are easy to develop and for that reason there are
thousands of them in repositories such as [iGoogle's
repository](http://www.google.com/ig/directory?synd=open). When the application
becomes more complicated you will need a complementary backend technology, such
as portlets or regular Java web applications.

The following sections describe these options in more detail.

### Portlets  [](id=lp-6-1-dgen01-portlets--0)

Portlets are small web applications written in Java that run in a portion of a
web page. The heart of any portal implementation is its portlets, because they
contain the actual functionality. The portlet container is only responsible for
aggregating the set of portlets that are to appear on any particular page.

Portlets are the least invasive form of extension to Liferay, as they are
entirely self contained. Consequentially portlets are also the the most forward
compatible development option. They are hot-deployed as plugins into a Liferay
instance, resulting in zero downtime. A single plugin can contain multiple
portlets, allowing you to split up your functionality into several smaller
pieces that can be arranged dynamically on a page. Portlets can be written using
any of the java web frameworks that support portlets development, including
Liferay's specific frameworks: MVCPortlet or AlloyPortlet.

### OpenSocial Gadgets [](id=lp-6-1-dgen01-opensocial-gadgets-0)

Like portlets, OpenSocial Gadgets provide a standard way to develop applications
for a portal environment. From a technology perspective, one key difference is
that they don't mandate a specific backend technology, such as Java EE, PHP,
Ruby, Python, etc. Another difference is that they have been designed
specifically to implement social applications, while portlets were designed for
any type of application. Because of this, OpenSocial Gadgets not only provide a
set of technologies to develop and run applications, but also a set of APIs that
allow the application to obtain information from the social environment such as
information about a user's profile, his activities or his friends.

An OpenSocial Gadget can be deployed in Liferay in one of two ways:

- Remote Gadget: A remote gadget is one that is executed in a remote server but
is shown to the user in a given page just as if it were another application of
the platform. This option is very simple but has the drawback that the portal
depends on the remote server for that gadget to work. This might not be an
option in some intranet environments that lack full access to Internet.

- Local Gadget: A local gadget is deployed in the Liferay server in a similar
manner as portlets are deployed. Since gadgets are defined in an XML file, all
you need to do to deploy the gadget is upload the XML file to the server. Some
people like to upload them directly through the file system, FTP or similar
protocols. In some other cases, just uploading it with the *Documents and Media
Library* portlet and copying the URL is enough. Once you have the URL, you can
go to the *Control Panel* &rarr; *OpenSocial Gadget Publisher*, click the
*Publish Gadget* button and enter the URL as shown in the following screenshot:

![Figure 1.1: Publish a new Gadget](../../images/open-social-gadget-new-ctrl-panel.png)

After this is done, the gadget will appear as an application that page
administrators can add to their pages.

One additional very interesting feature of Liferay is that it is possible to
expose to the outside world any applications developed as portlets, as
OpenSocial gadgets. That is, you can develop a portlet and then let anyone with
access to your portlet add it to the pages of other portals or social networks
as a remote gadget.

Note, since an OpenSocial gadget is defined in an XML file, there is no need to
create a plugin (that is a `.war` file) in order to deploy it. All you need to
do is make that XML file accessible within the same server or another server and
let Liferay know the URL.

### Reusing existing web applications  [](id=lp-6-1-dgen01-reusing-existing-web-applications--0)

Sometimes you don't start from scratch, because you have an existing application
which however has not been implemented using Portlets or OpenSocial Gadgets.
What can you do in this situation? There are many options available. Some of the
most popular options are:

-   Rewrite the application as a portlet

- Create simple portlets that interact with the application (possibly using Web
Services) and offer all or part of the functionality to end-users

- Create an OpenSocial gadget as a wrapper for the application. The Gadget will
use an IFrame to show part of the application in the portal page.

- Create a portlet that integrates the remote application either using an IFrame
or an HTTP proxy (e.g. using Liferay's WebProxy portlet). This requires
implementing a means to transfer authentication between the portal and the
application.

There are many more options and many reasons why you may want to choose one or
another. Reviewing each of them is out of the scope of this guide.

If the existing application has been developed as a Java EE application, Liferay
provides a technology called Web Application Integrator that allows prototyping
the integration and provides several nice features.

In order to use Web Application Integrator all you need to do is deploy the WAR
file of your web application as you would do with any Liferay plugin (for
example, by using the control panel or by copying it to the deploy directory).
As a result, Liferay automatically creates a portlet that integrates your
application using an IFrame.

### Supported Technologies  [](id=lp-6-1-dgen01-supported-technologies--0)

Liferay, as a platform, strives to provide compatibility with all Java
technologies that a developer may want to use to develop their own applications.
Thanks to the portlet and the Java EE specifications each portlet application
can use its own set of libraries and technologies regardless of whether they are
what Liferay uses itself or not. This section refers mainly to portlet plugins,
other plugin types are more restricted. For example, the Ext plugins can only
use libraries that are compatible with the ones used by the core Liferay code.

Since the choice of available frameworks and technologies is very broad, the
task can be daunting to newcomers. This section attempts to provide some advice
to help developers choose the best tools for their needs. This advice can be
summarized as follows:

1. **Use what you know:** If you already know a framework, that can be your
first option (Struts 2, Spring MVC, PHP, Ruby...)

2. **Adapt to your real needs:** Component based frameworks (JSF, Vaadin, GWT)
are specially good for desktop-like applications. MVC frameworks, on the other
hand, provide more flexibility.

3. **When in doubt, pick the simpler solution:** Portlet applications are often
much simpler than standalone web applications; so, when in doubt, use simpler
frameworks (e.g. MVC Portlet, Alloy Portlet).

Some of the frameworks mentioned above include their own JavaScript code to
provide a very high degree of interaction. That is the case with GWT, Vaadin or
JSF implementations such as IceFaces or Rich Faces. In other cases, developers
prefer to write their own JavaScript code. In such cases it's most often
recommended to use one of the available JavaScript libraries. Some of the most
common libraries that can be used with Liferay are jQuery, Dojo, YUI, Sencha
(previously known as ExtJs), Sproutcore, etc. Starting with version 6, Liferay
also provides its own library called AlloyUI which is based on YUI 3. AlloyUI
provides a large set of components specifically designed for very efficient and
interactive applications that work well in portal environments. Liferay's core
portlets use AlloyUI throughout their Javascript code. Developers can also use
AlloyUI for their custom portlets or choose any other JavaScript library as long
as those libraries do not conflict with libraries referenced by other portlets
deployed in the same portal.

Besides the frameworks and libraries mentioned in this section, there are
literally thousands more available to Java developers to handle persistence,
caching, connections to remote services, and much more. Liferay does not impose
specific requirements on the use of any of those frameworks so that portal
developers can choose the best tools for their projects.

## Extending and customizing Liferay [](id=extending-and-customizing-lifer-3)

Liferay provides many out-of-the-box features, including a fully featured
content management system, a social collaboration suite and several productivity
tools. For some portals, these features might be exactly what you need; but for
other portals, you might want to extend these features or customize how they
work or how they look.

Liferay provides several types of plugins that are specialized for different
types of customization. Yet, it is possible to combine several plugin types,
such as Themes and Layout Templates, into a single WAR file. The following
sections describe each type of plugin and how they may be used.

### Customizing the look and feel: Themes [](id=lp-6-1-dgen01-customizing-the-look-and-feel-themes-0)

Themes allow the look of the Liferay portal to be changed using a combination of
CSS and Velocity templates. In many cases, it is possible to adapt the default
Liferay theme to a desired look using only CSS, providing the most forward
compatibility. If CSS is insufficient, you can create a theme consisting of only
a subset of the required templates and rely on the remaining templates to be
copied from the default theme at deployment. Like portlets, themes are
hot-deployed as plugins into a Liferay instance.

### Adding new predefined page layouts: Layout Templates  [](id=lp-6-1-dgen01-adding-new-predefined-page-layouts-layout-templates--0)

Layouts are similar to themes, except that they change the *arrangement* of
portlets on a page rather than their look and feel. Layout templates are also
written in Velocity and are hot-deployable.

### Customizing or extending the out of the box functionality: Hook plugins  [](id=lp-6-1-dgen01-customizing-or-extending-the-out-of-the-box-functionality-h-0)

Hook plugins are the recommended method of customizing the the core
functionality of Liferay at many predefined extension points. Hook plugins can
be used to modify portal properties or to perform custom actions on startup,
shutdown, login, logout, session creation and session destruction. Using service
wrappers, it is possible for a hook plugin to replace any of the core Liferay
services with a custom implementation. Hook plugins can also replace the JSP
templates used by any of the default portlets, allowing you to customize the
appearance of the portlets as desired. Best of all, hooks are hot-deployable
plugins just like portlets.

### Advanced customization: Ext plugins  [](id=lp-6-1-dgen01-advanced-customization-ext-plugins--0)

Ext plugins provide the largest degree of flexibility in modifying the Liferay
core, and allow replacing essentially any class with custom implementations.
This flexibility comes at a cost, however, as it is highly unlikely that an Ext
plugin written for one version of Liferay will continue to work in the next
version, without modification. For this reason, Ext plugins are only recommended
for cases where an advanced customization is really needed and there is no other
way to accomplish the same goal. Also you should make sure that you are familiar
with the Liferay core to assure the Ext plugin doesn't have a negative effect on
existing funcitonalities. Even though Ext plugins are deployed as plugins, the
server must be restarted for their changes to take effect. For this reason, Ext
plugins should not be combined with other types of plugins.

---

 ![note](../../images/tip-pen-paper.png)**Note:** If you have developed for
 Liferay 5.2 or before, you may be familiar with what was known as the
 *Extension Environment*. Ext plugins were introduced in Liferay 6.0 to replace
 the extension environment in order to simplify development. It is possible to
 automatically convert any existing Extension Environment into a plugin. Check
 the *Migrating old extension environments* section of the *Ext plugins* chapter
 for detailed instructions.

---

## Choosing the best tool for the job [](id=choosing-the-best-tool-for-the-j-3)

The Java ecosystem is well known for providing a wide variety of options for
almost any type of developer work that must be done. This is a great advantage
because you can find the tool that best fits your needs and the way you work.
For that reason, once you have found a tool with which you are comfortable
using, you want to keep using it.

On the other hand, the wide variety of tools is often intimidating for
newcomers, because the newcomers must choose between tools without having the
necessary experience to know which tool is best for their needs.

In Liferay we have taken a pragmatic approach to make sure Liferay developers
can benefit from the variety of tool options while still provide a learning
curve that is as soft as possible. To do that we have chosen two Open Source
technologies that you can use if you don't already have a favorite:

- **Apache Ant and the Plugins SDK:** Liferay provides a development environment
called the *Plugins SDK* that allows you to develop all types of plugins by
executing a set of predefined commands (also known as *targets* in Ant's
nomenclature). You can use the Plugins SDK directly from the command-line and
use file editors like Emacs, Vi, EditPlus or even Notepad. You can also
integrate the Plugins SDK with your favorite IDE, since almost all of the IDEs
provide support for Apache Ant. The next chapter describes, in detail, how to
use the Plugins SDK.

- **Eclipse and the Liferay IDE:** Eclipse is the most popular and well known
Java IDE and provides a wide variety of features. *Liferay IDE* is a plugin for
Eclipse that extend its functionality to make development of all types of
Liferay plugins much easier. Liferay IDE uses the Plugins SDK underneath, but
you don't even need to know the SDK unless you are trying to perform an advanced
operation not directly supported by Liferay IDE.

This guide shows you how to develop for Liferay using the Plugins SDK. We have
done so to make sure that the guide is useful for as many developers as
possible, even if they don't like IDEs or if they don't use Eclipse.

The guide also has a full chapter on the Liferay IDE. If you are an IDE person
and especially if you are an Eclipse user, you may start by reading that chapter
first and then go back to reading from the second chapter. It won't be hard to
repeat the steps described in the guide using the Liferay IDE.

**What about if I don't like Apache Ant and I prefer to use Maven?** Many
developers prefer one of the alternatives to Apache Ant. The most popular of
these alternatives is Maven. To support developers that want to use Maven we
have *mavenized* Liferay artifacts so that they can easily be referenced in your
`pom.xml`. We are in the process of writing a chapter about using Maven for
Liferay development and will be adding it to this guide in the future.

**What if I don't like Eclipse and prefer to use Netbeans, IntelliJ IDEA or
other another IDE?** There are many IDEs out there and each one has its
strengths. We decided to build Liferay IDE on top of Eclipse because it is the
most popular Open Source option. But we also want to make sure developers can
use their IDE of choice. In fact quite a few core developers use Netbeans and
Intellij IDEA (who has gracefully provided an Open Source license to Liferay's
core developers). Both of these IDEs have support for integration with Apache
Ant, so you can use the Plugins SDK with them. Additionally, Sun Microsystems
developed an extension to Netbeans called the *Portal Pack* that is explicitly
designed to develop plugins for Liferay (and their Liferay flavor called
WebSpace). You can find more about the Portal Pack in the following URL:

[http://netbeans.org/kb/articles/portalpack.html](http://netbeans.org/kb/articles/portalpack.html)

That's it for the introduction. Let's get started with real development work!
