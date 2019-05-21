---
header-id: portlets
---

# Portlets

[TOC levels=1-4]

@product@ started off as a portal server, designed to serve Java-based web
applications called *portlets* (see [JSR 168](https://jcp.org/en/jsr/detail?id=168), 
[JSR-286](https://jcp.org/en/jsr/detail?id=286), and 
[JSR-362](https://jcp.org/en/jsr/detail?id=362)). 
Portlets process requests and generate
responses like any other web application. One key difference, however, between
portlets and other web apps is that portlets run in a portion of the web page.
When you're writing a portlet application, you need only worry about that
application: the rest of the page---the navigation, the top banner, and any
other global components of the interface---is handled by other components.
Portlets run only in a portal server. They use the portal's existing support for
user management, authentication, permissions, page management, and more. This
frees you to focus on developing the portlet's core functionality. In many ways,
writing your application as a portlet is easier than writing a standalone
application. 

Many portlets can be placed on a single page by users (if they have permission)
or portal administrators. For example, a page in a community site could have
a calendar portlet for community events, an announcements portlet for important
announcements, and a bookmarks portlet for links of interest to the community.
You can drag and drop to reposition and resize portlets on a page without
altering any portlet code. Alternatively, a single portlet can take up an entire
page if it's the only app you need on that page. For example, message boards
or Wikis with complex user interfaces are best suited on their own pages. In
short, portlets alleviate many of the traditional pain points associated with
developing Java-based web apps. 

![Figure 1: You can place multiple portlets on a single page.](../../images/portlet-applications.png)

Portlets handle requests in multiple phases. This makes portlets much more
flexible than servlets. Each portlet phase executes different operations: 

-   **Render:** Generates the portlet's content based on its current state. When
    this phase runs on one portlet, it also runs on all other portlets on the
    page. The Render phase runs when any portlets on the page complete the
    Action or Event phases. 
-   **Action:** In response to a user action, the Action phase performs
    operations that change the portlet's state. The Action phase can also
    trigger events that are processed by the Event phase. Following the Action
    phase and optional Event phase, the Render phase then regenerates the
    portlet's contents. 
-   **Event:** Processes events triggered in the Action phase. Events are used 
    for inter-portlet communication (IPC). Once the portlet processes all
    events, the portal calls the Render phase on all portlets on the page. 
-   **Resource-serving:** Serves a resource independently from the rest of the 
    lifecycle. This lets a portlet serve dynamic content without running the
    Render phase on all portlets on a page. The Resource-serving phase handles
    AJAX requests. 
-   **Header:** Lets you specify resource dependencies, such as CSS, prior to 
    the Render phase. 

Compared to servlets, portlets also have some other key differences. Since
portlets only render a portion of a page, tags like `<html>`, `<head>`, and
`<body>` aren't allowed. And because you don't know the portlet's page ahead of
time, you can't create portlet URLs directly. Instead, the portlet API gives you
methods to create portlet URLs programmatically. Also, because portlets don't
have direct access to the `javax.servlet.ServletRequest`, they can't read query
parameters directly from a URL. Portlets instead access a
`javax.portlet.PortletRequest` object. The portlet specification provides a
mechanism for a portlet to read only its own URL parameters or those declared as
public render parameters. @product@ does, however, provide utility methods that
can access the `ServletRequest` and query parameters. Portlets also have a
*portlet filter* available for each phase in the portlet lifecycle. Portlet
filters are similar to servlet filters in that they allow request and response
modification on the fly. 

Portlets also differ from servlets by having distinct modes and window states. 
Modes distinguish the portlet's current function: 

-   **View mode:** The portlet's standard mode. Use this mode to access the 
    portlet's main functionality. 
-   **Edit mode:** The portlet's configuration mode. Use this mode to configure 
    a custom view or behavior. For example, the Edit mode of a weather portlet
    might let you choose a location to retrieve weather data from. 
-   **Help mode:** A mode that displays the portlet's help information. 

Most modern applications use View Mode only. 

Portlet window states control the amount of space a portlet takes on a page. 
Window states mimic window behavior in a traditional desktop environment: 

-   **Normal:** The portlet can be on a page that contains other portlets. This 
    is the default window state. 
-   **Maximized:** The portlet takes up an entire page. 
-   **Minimized:** Only the portlet's title bar shows.

All of the
[ways to develop web front-ends](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends)
on @product@ involve portlets. The JavaScript-based widgets use Liferay's JS
Portlet Extender behind the scenes and the Java-based web front-ends are
explicitly portlets. All of the web front-end types vary in their support of
Portlet 3.0,
[dependency injection (DI)](/docs/7-2/frameworks/-/knowledge_base/f/dependency-injection),
Model View Controller (MVC), and modularity, giving you plenty of good options
for developing portlets. 

## Related Topics 

[Bean Portlet](/docs/7-2/appdev/-/knowledge_base/a/bean-portlet)

[Spring Portlet MVC: PortletMVC4Spring](/docs/7-2/appdev/-/knowledge_base/a/spring-portlet-mvc)

[Liferay MVC Portlet](/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet)

[JSF Portlet](/docs/7-2/appdev/-/knowledge_base/a/jsf-portlet)
