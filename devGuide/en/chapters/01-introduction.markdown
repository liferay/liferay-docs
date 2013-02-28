# Introduction

Welcome to Liferay's Developer's Guide, the official guide for all developers
that want to develop portals based on Liferay or that want to develop
applications that anyone can use in their Liferay portals. This guide assumes
you already know what a portal is and how to use Liferay from an end-user
perspective. If you don't, please read the [What is a
Portal?](http://www.liferay.com/products/what-is-a-portal) article on
liferay.com and the [What is
Liferay](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/what-is-lifera-1)
chapter in *Using Liferay Portal*. 

This chapter provides an overview of how to develop applications for Liferay and
how to customize Liferay's built-in applications. You will use Liferay plugins
to do these things and use different types of Liferay plugins to use for
different purposes. We'll explain the technologies and tools available for you
to use in developing your plugins. 

This chapter covers the following: 

- **Developing applications for Liferay:** Ways to develop new applications and
reuse existing applications 

- **Extending and customizing Liferay:** Options for extending functionality and
customizing your portal applications, themes, and templates 

- **Choosing the best tool for the job:** Comparison of tools available for
developing applications for Liferay 

Let's get started developing applications for Liferay. 

## Developing applications for Liferay

According to Wikipedia "A web application is an application that is accessed
over a network such as the Internet or an intranet." A portal application is a
web application that can coexist with many other applications in a civilized way
and can leverage functionality provided by the portal platform to
reduce development time and deliver a more consistent experience to end users. 

If you are reading this, you probably want to (or need to) develop applications
that run on top of Liferay Portal. And you might be looking for an
answer to the question *what is the best and quickest way to do it?* Liferay
supports two main technologies for incorporating applications, and both are
based on standards: 

- **Portlets:** are small web applications written in Java that follow a certain
set of rules to allow cohabitation of applications within the same portal or
even within the same page. Portlets can be used to build applications as complex
as you want since the full suite of technologies and libraries for the Java
platform can be used. 

- **OpenSocial gadgets:** are usually small applications, written using
browser-side technologies such as HTML and JavaScript. Where "standard"
applications work with data on a per-user basis, "social" applications share
data within well defined networks, facilitating communication of information
between groups of users. Gadgets lend themselves well to sharing, are simple to
write, and are easy to deploy. However if a gadget application becomes more
complicated you will need a complimentary back-end technology, such as portlets
or regular Java web applications. 

The following sections describe these two technologies in more detail. 

### Portlets

Portlets are small web applications written in Java that run in a portion of a
web page. The heart of any portal implementation is its portlets, because they
contain the actual functionality. The portlet container is only responsible for
aggregating the set of portlets that are to appear on any particular page. 

Portlets are the least invasive form of extension to Liferay, as they are
entirely self contained. Consequentially portlets are also the the most forward
compatible development option. They are hot-deployed as plugins into Liferay
instances, resulting in zero downtime. A single plugin can contain multiple
portlets, allowing you to split up your functionality into several smaller
pieces that can be arranged dynamically on a page. Portlets can be written using
any of the Java web frameworks that support portlet development, including
Liferay's specific frameworks: MVC Portlet and Alloy Portlet. 

### OpenSocial gadgets

Like portlets, OpenSocial gadgets provide a standard way to develop applications
for a portal environment. From a technology perspective, one key difference is
that they don't mandate a specific back-end technology, such as Java EE, PHP,
Ruby, Python, etc. Another difference is that they have been designed
specifically to implement social applications, while portlets were designed for
any type of application. Because of this, OpenSocial gadgets not only provide a
set of technologies to develop and run applications, but also a set of APIs that
allow the application to obtain information from the social environment such as
information about a user's profile, his activities, and his friends. 

An OpenSocial gadget can be deployed in Liferay as one of the following types: 

- **Remote gadget:** is executed in a remote server but is shown to the user in
a given page just as if it were another application of the platform. This option
is very simple but has the drawback that the portal depends on the remote server
for that gadget to work. This might not be an viable option in some intranet
environments that lack full access to Internet. 

- **Local gadget:** is deployed in the Liferay server in a similar manner as
portlets are deployed. Since a gadget is defined in an XML file, you need only
upload the XML file to deploy the gadget. Some people like to upload them
directly through the file system, FTP, or similar protocols. Alternatively, you
can publish the XML file to your portal via Liferay's *Documents and Media*
portlet. Then copy its URL, go to *Control Panel* &rarr; *OpenSocial Gadget
Publisher*, click the *Publish Gadget* button, and paste the URL as shown in the
following screenshot: 

    ![Figure 1.1: All you need is your OpenSocial gadget's URL to publish it in
Liferay.](../../images/open-social-gadget-new-ctrl-panel.png)

On saving your new gadget, it appears as an application that page administrators
can add to their pages. 

One additional very interesting feature of Liferay is it lets you expose
portlets to the outsde world as OpenSocial gadgets. That is, you can develop a
portlet and then let anyone with access to your portlet add it as a remote
gadget to pages of other portals or social networks. 

Note, since an OpenSocial gadget is defined in an XML file, there is no need to
create a plugin (that is a WAR file) in order to deploy it. You need only make
that XML file accessible within the same server or another server and let
Liferay know the URL. 

### Reusing existing web applications

Sometimes you don't start from scratch, because you have an existing application
which has not been implemented as a portlet or OpenSocial gadget.
What can you do in this situation? There are many options available. Some of the
most popular options are to: 

- Rewrite the application as a portlet. 

- Create simple portlets that interact with the application (possibly using Web
Services) and offer all or part of the functionality to end-users. 

- Create an OpenSocial gadget as a wrapper for the application. The gadget will
use an IFrame to show part of the application in the portal page. 

- Create a portlet that integrates the remote application either using an IFrame
or an HTTP proxy (e.g. using Liferay's WebProxy portlet). This requires
implementing a means to transfer authentication between the portal and the
application. 

There are many more options and many reasons why you may want to choose one
option over another. Reviewing every option is out of the scope of this guide;
however, the above options are worth considering. 

If the existing application has been developed as a Java EE application, Liferay
provides a technology called Web Application Integrator that prototypes the
integration and provides several nice features. 

In order to use Web Application Integrator all you need to do is deploy the WAR
file of your web application as you would any Liferay plugin (for example, by
using the control panel or by copying it to the hot-deploy directory). As a
result, Liferay automatically creates a portlet that integrates your application
using an IFrame. 

Now that we've gone over some options for developing new applications and for
adapting existing applicationsn for Liferay, let's consider some of the
technology frameworks Liferay supports. 

### Supported technology frameworks

Liferay, as a platform, strives to provide compatibility with all Java
technologies that a developer may want to use to develop their own applications.
Thanks to the portlet and the Java EE specifications each portlet application
can use its own set of libraries and technologies regardless of whether they are
what Liferay uses itself or not. This section refers mainly to portlet plugins,
other plugin types are more restricted. For example, the Ext plugins can only
use libraries that are compatible with the ones used by the core Liferay code. 

Since the choice of available frameworks and technologies is very broad, the
task of choosing them can be daunting to newcomers. This section attempts to
provide some advice to help developers choose the best frameworkds for their
needs. This advice can be summarized as follows: 

1. *Use what you know:* If you already know a framework, that can be your
first option (Struts 2, Spring MVC, PHP, Ruby, etc). 

2. *Adapt to your real needs:* Component based frameworks, such as
JavaServer&trade; Faces (JSF), Vaadin, and Google Web Toolkit (GWT), are
especially good for desktop-like applications. MVC frameworks, on the other
hand, provide more flexibility. 

3. *When in doubt, pick the simpler solution:* Portlet applications are often
much more simple to implement than standalone web applications; so, when in
doubt, use the simpler framework (e.g. Liferay's MVC Portlet or Alloy Portlet).  

Some of the frameworks mentioned above include their own JavaScript code to
provide a very high degree of interaction. That is the case with GWT, Vaadin,
and JSF implementations (e.g. IceFaces or Rich Faces). In other cases, you may
prefer to write your own JavaScript code and leverage one of the JavaScript
libraries available. Some of the most common libraries that can be used with
Liferay are jQuery, Dojo, YUI, Sencha (previously known as ExtJs), and
Sproutcore. Since version 6, Liferay has provided its own library called *Alloy
UI* which is based on YUI 3. Alloy UI has a large set of components specifically
designed for very efficient interactive applications, such as those in portal
environments. Liferay's core portlets use Alloy UI throughout their JavaScript
code. You can use Alloy UI for your custom portlets or choose any other
JavaScript library, as long as the library does not conflict with libraries
referenced by other portlets deployed in the same portal. 

Besides the frameworks and libraries mentioned in this section, there are
literally thousands more available to Java developers to handle persistence,
caching, connections to remote services, and much more. Liferay does not impose
specific requirements on the use of any of those frameworks letting you, the
portal developer, choose the best tools for your projects. 

## Extending and customizing Liferay

Liferay provides many out-of-the-box features, including a fully featured
content management system, a social collaboration suite, and several
productivity tools. For some portals, these features might be exactly what you
need; but for other portals, you might want to extend these features or
customize their behavior and appearance. 

Liferay provides several types of plugins that are specialized for different
types of customization. Multiple plugins of various plugin types can be combined
into a single WAR file. The following sections describe these plugin types and
how they can be used. 

### Customizing the look and feel: Themes

Themes let you specify the look of your Liferay portal using a combination of
CSS and Velocity templates. In many cases, it is possible to adapt the default
Liferay theme to a desired look using only CSS; this is the most forward
compatible approach. If CSS is insufficient, you can create a theme consisting
of only a subset of the required templates and rely on the remaining templates
being copied from the default theme at deployment. Like portlets, themes are
hot-deployed as plugins into Liferay instances. 

### Adding new predefined page layouts: Layout Templates

Layouts are similar to themes, except they specify the *arrangement* of portlets
on a page rather than their look and feel. You can create custom layout
templates to arrange portlets just the way you like them. And you can even embed
commonly used portlets. Like themes, layout templates are also written in
Velocity and are hot-deployable. 

### Customizing or extending the out-of-box functionality: Hook plugins

Hook plugins are the recommended method of customizing the the core
functionality of Liferay at many predefined extension points. Hook plugins can
be used to modify portal properties or to perform custom actions on startup,
shutdown, login, logout, session creation, and session destruction. Using service
wrappers, it is possible for a hook plugin to replace any of the core Liferay
services with a custom implementation. Hook plugins can also replace the JSP
templates used by any of the default portlets, letting you customize the
appearance of the portlets. Best of all, hooks are hot-deployable
plugins just like portlets. 

### Advanced customization: Ext plugins

Ext plugins provide the largest degree of flexibility in modifying the Liferay
core, and allow replacing essentially any class with a custom implementation.
This flexibility comes at a cost, however, as it is highly unlikely that an Ext
plugin written for one version of Liferay will continue to work in the next
version, without modification. For this reason, Ext plugins are only recommended
for cases in which an advanced customization is really needed and there is no
other way to accomplish it. Also you should make sure you are very familiar with
the Liferay core to assure your Ext plugin doesn't have a negative effect on
existing funcitonalities. Even though Ext plugins are deployed as plugins, the
server must be restarted for their customizations to take effect. For this
reason, Ext plugins should not be combined with other types of plugins. 

---

 ![note](../../images/tip-pen-paper.png)**Note:** If you have developed for
 Liferay 5.2 or prior releases, you may be familiar with what was known as the
 *Extension Environment*. Ext plugins were introduced in Liferay 6.0 to replace
 the extension environment in order to simplify development. It is possible to
 automatically convert any existing Extension Environment into a plugin. For
 more detailed instructions, see the section on migrating old extension
 environments in chapter 8. 

---

Now that you are familiar with options for developing applications on Liferay
and customizating Liferay, let's consider some tools to help you do your work. 

## Choosing the best tool for the job

The Java ecosystem is well known for providing a wide variety of options for
almost any type of software development. This is a great advantage because you
can find the tool that best fits your needs and the way you work. Naturally,
when you get comfortable with a tool, you want to keep using it. 

If you are a newcomer, the wide variety of tools available can be intimidating
as you must choose between tools without having the necessary experience to know
which tools will best address your needs. 

In Liferay, we have taken a pragmatic approach to make sure Liferay developers
can benefit from the variety of tool options while still providing a learning
curve that is as soft as possible. To do that we have chosen two open source
technologies that you can use if you don't already have a favorite: 

- **Apache Ant and the Plugins SDK:** Liferay provides a development environment
called the *Plugins SDK* that lets you develop all types of plugins by executing
a set of predefined commands (known as *targets*, in Ant's nomenclature). You
can use the Plugins SDK directly from the command-line and use file editors like
Emacs, Vi, EditPlus, or even Notepad. You can also integrate the Plugins SDK
with your favorite IDE, since most IDEs provide support for Apache Ant. The next
chapter describes, in detail, how to use the Plugins SDK. 

- **Eclipse and the Liferay IDE:** Eclipse is the most popular and well known
Java IDE and it provides a wide variety of features. *Liferay IDE* is a plugin
for Eclipse that extends its functionality to facilitate developing all types of
Liferay plugins. Liferay IDE uses the Plugins SDK underneath, but you don't even
need to know the SDK unless you are trying to perform an advanced operation not
directly supported by Liferay IDE. To develop applications for Liferay Portal
Enterprise Edition (EE) use Liferay Developer Studio which extends Liferay IDE,
providing additional integration plugins such as the Kaleo Designer for Java. 

This guide shows you how to develop for Liferay using the Plugins SDK and
Liferay IDE. We have done so to make sure we benefit as many developers as
possible, even if they don't like IDEs or if they don't use Eclipse. If you are
an Eclipse user, you may want to start reading about Liferay IDE in chapter 10
first and then go back to reading about the Plugins SDK. 

**What about if I don't like Apache Ant and I prefer to use Maven?** Many
developers prefer other command-line alternatives to Apache Ant. The most
popular of these alternatives is Maven. To support developers that want to use
Maven we have *mavenized* Liferay artifacts for referencing in your Maven
projects. See chapter 8 for an in-depth look at developing plugins in Maven. 

**What if I don't like Eclipse and prefer to use Netbeans, IntelliJ IDEA or
other another IDE?** There are many IDEs out there and each one has its
strengths. We decided to build Liferay IDE on top of Eclipse because it is the
most popular open source option. But we also want to make sure you can use the
IDE of your choice. In fact quite a few core developers use Netbeans and
Intellij IDEA (who has gracefully provided an open source license to Liferay's
core developers). Both of these IDEs have support for integration with Apache
Ant, so you can use the Plugins SDK with them. Additionally, Sun Microsystems
developed an extension to Netbeans called the *Portal Pack* that is explicitly
designed for develop plugins for Liferay (and their Liferay flavor called
WebSpace). You can find more about the Portal Pack at the following URL: 

 [http://netbeans.org/kb/articles/portalpack.html](http://netbeans.org/kb/articles/portalpack.html)

That's it for the introduction. Let's get started with real development work! 
