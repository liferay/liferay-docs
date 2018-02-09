# Portlets [](id=portlets)

Web apps in @product@ are called *portlets*. Like many web apps, portlets
process requests and generate responses. In the response, the portlet returns
content (e.g. HTML, XHTML) for display in browsers. One key difference between
portlets and other web apps is that portlets run in a portion of the web page.
When you're writing a portlet application, you only need to worry about that
application: the rest of the page--the navigation, the top banner, and any other
global component of the interface--is handled by other components. Another
difference is that portlets run only in a portal server. Portlets can therefore
use the portal's existing support for user management, authentication,
permissions, page management, and more. This frees you to focus on developing
the portlet's core functionality. In many ways, writing your application as
a portlet is easier than writing a standalone application. 

Portlets can be placed on pages by users (if they have permission) or portal
administrators, who can place several different portlets on a single page. For
example, a page in a community site could have a calendar portlet for community
events, an announcements portlet for important announcements, and a bookmarks
portlet for links of interest to the community. And because the portal controls
page layout, you can reposition and resize one or more portlets on a page
without altering any portlet code. Doing all this in other types of web apps
would require manual re-coding. Alternatively, a single portlet can take up an
entire page if it's the only app you need on that page. For example, a message
boards or wiki portlet is best suited on its own page. In short, portlets
alleviate many of the traditional pain points associated with developing web
apps. 

![Figure 1: You can place multiple portlets on a single page.](../../images/portlet-applications.png)

What's more, portals and portlets are standards-based. In 2003, Java Portlet 
Specification 1.0 
([JSR-168](https://jcp.org/en/jsr/detail?id=168)) 
first defined portal and portlet behavior. In 2008, Java Portlet Specification 
2.0 
([JSR-286](https://jcp.org/en/jsr/detail?id=286)) 
refined and built on JSR-168, while maintaining backwards compatibility, to 
define features like inter-portlet communication (IPC) and more. The recently 
released Java Portlet Specification 3.0 
([JSR-362](https://jcp.org/en/jsr/detail?id=362)) 
continues portal and portlet evolution. Liferay leads in this space by having a 
member in the Expert Group. 

So what do these specifications define? The links above show the complete
definition; here we'll briefly summarize how portlets differ from other types of
servlet-based web apps. 

Portlets handle requests in multiple phases. This makes portlets much more
flexible than servlets. Each portlet phase executes different operations: 

- **Render:** Generates the portlet's contents based on the portlet's current 
  state. When this phase runs on one portlet, it also runs on all other portlets 
  on the page. The Render phase runs when any portlets on the page complete the 
  Action or Event phases. 
- **Action:** In response to a user action, performs some operation that changes 
  the portlet's state. The Action phase can also trigger events that are 
  processed by the Event phase. Following the Action phase and optional Event 
  phase, the Render phase then regenerates the portlet's contents. 
- **Event:** Processes events triggered in the Action phase. Events are used for 
  IPC. Once the portlet processes all events, the portal calls the Render phase 
  on all portlets on the page. 
- **Resource-serving:** Serves a resource independent from the rest of the 
  lifecycle. This lets a portlet serve dynamic content without running the 
  Render phase on all portlets on a page. The Resource-serving phase handles 
  AJAX requests. 

Compared to servlets, portlets also have some other key differences. Since 
portlets only render a portion of a page, tags like `<html>`, `<head>`, and 
`<body>` aren't allowed. And because you don't know the portlet's page ahead of 
time, you can't create portlet URLs directly. Instead, the portlet API gives you 
methods to create portlet URLs programmatically. Also, because portlets don't 
have direct access to the `javax.servlet.ServletRequest`, they can't read query 
parameters  directly from a URL. Portlets instead access a 
`javax.portlet.PortletRequest` object. The portlet specification only provides 
a mechanism for a portlet to read its own URL parameters or those declared as 
public render parameters. @product@ does, however, provide utility methods that 
can access the `ServletRequest` and query parameters. Portlets also have a 
*portlet filter* available for each phase in the portlet lifecycle. Portlet 
filters are similar to servlet filters in that  they allow request and response
modification on the fly. 

Portlets also differ from servlets by having distinct modes and window states. 
Modes distinguish the portlet's current function: 

- **View mode:** The portlet's standard mode. Use this mode to access the 
  portlet's main functionality. 
- **Edit mode:** The portlet's configuration mode. Use this mode to configure a 
  custom view or behavior. For example, the Edit mode of a weather portlet could 
  let you choose a location to retrieve weather data from. 
- **Help mode:** A mode that displays the portlet's help information. 

Most modern applications use View Mode only. 

Portlet window states control the amount of space a portlet takes up on a page. 
Window states mimic window behavior in a traditional desktop environment: 

- **Normal:** The portlet can be on a page that contains other portlets. This is 
  the default window state. 
- **Maximized:** The portlet takes up an entire page. 
- **Minimized:** Only the portlet's title bar shows. 

When you develop portlets, you can leverage all the features defined by the
portlet specification. Depending on how you develop and package your portlet,
however, it may not be able to run on other portal containers. You may now be
saying, "Hold on a minute! I thought @product@ was standards-compliant? What
gives?" @product@ is standards-compliant, but it contains some sweeteners in the
form of APIs designed to make developers' lives easier. For example, @product@
contains an 
[MVC framework](/develop/tutorials/-/knowledge_base/7-1/liferay-mvc-portlet) 
that makes it simpler to implement MVC in your portlet. This framework, however,
is only available in @product@. Without modification, a portlet that uses this
framework won't run if deployed to a non-Liferay portal container. Note, though,
that we don't force you to use our MVC framework or any of its other unique
APIs. For example, you can develop your portlet with strictly
standards-compliant frameworks and APIs, package it in a WAR file, and then
deploy it on any standards-compliant portal container. 

@product@ also contains an OSGi runtime. This means that you don't have to 
develop and deploy your portlet as a traditional WAR file; you can do so as OSGi 
modules instead. We recommend the latter, so you can take advantage of the 
modularity features inherent in OSGi. For a detailed description of these 
features, see the tutorial 
[OSGi and Modularity](/develop/tutorials/-/knowledge_base/7-1/osgi-and-modularity-for-liferay-6-developers). 
Note, however, that portlets you develop as OSGi modules won't run on 
other portlet containers that lack an OSGi runtime. Even so, the advantages of 
modularity are so great that we still recommend you develop your portlets as 
OSGi modules. 

So what's the benefit to adopting Liferay's frameworks and APIs? There are
several: 

- They follow Liferay's design patterns. The better you understand them, the
better you understand @product@. 
- They are the result of nearly 15 years of portlet development. 
- They provide many conveniences that make development easier and faster. 
- They make your applications fit more naturally with the rest of the system. 
- If necessary, they're easy to migrate from, because they're built on top of
the standards. 

With that said, you can use a variety of technologies to develop portlets. This
section shows you how to develop portlets using the following frameworks and
techniques: 

- Liferay's `MVCPortlet`
- Soy Portlet
- Spring MVC
- JavaServer Faces (JSF) Portlets with Liferay Faces
- Making URLs Friendlier
- Preparing Your JavaScript Files for ES2015
- Applying Lexicon Styles to Your App
- Automatic Single Page Applications
- Creating Layouts Inside Custom Portlets
