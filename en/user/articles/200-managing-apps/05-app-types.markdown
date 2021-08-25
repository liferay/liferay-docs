---
header-id: app-types
---

# App Types

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

There are several different kinds of apps. Some apps can even contain other
apps. The types of apps you can install include:

-   OSGi Modules
-   Portlets
-   Web Plugins
-   Templates
-   Themes

Read on to learn about these app types.

## OSGi Modules

Since @product@ runs on OSGi, apps can be implemented as OSGi modules. An OSGi
module is a JAR file adapted to run on OSGi. Although it's possible for a single
module to implement a single app, an app typically consists of multiple modules
that are packaged together. Also note that apps in OSGi modules aren't required
to have a UI. For example, @product@ can run OSGi modules that expand built-in
APIs without requiring any user interaction. This is crucial for developers that
must leverage custom APIs. By providing such an API via one or more OSGi
modules, you can let developers leverage your API.

OSGi modules can also contain apps that have a UI: portlets. The next section
discusses these.

## Portlets

[Portlets](/docs/7-2/frameworks/-/knowledge_base/f/portlets) are small web
applications that run in a portion of a web page. For example, the built-in
Blogs app is a portlet. Portlet applications, like servlet applications, are a
Java standard implemented by various portal server vendors. The JSR-168 standard
defines the portlet 1.0 specification, the JSR-286 standard defines the portlet
2.0 specification, and the JSR-362 standard defines the portlet 3.0
specification. A Java standard portlet should be deployable on any portlet
container that supports the standard. Portlets are placed on the page in a
certain order by the end user and are served up dynamically by the portal
server. This means certain things that apply to servlet-based projects, such as
control over URLs or access to the `HttpServletRequest` object, don't apply in
portlet projects because the portal server generates these objects dynamically.

Portlets can be composed of OSGi modules (recommended), or contained in WAR
files. For information on developing portlets see [Web
Front-ends](/docs/7-2/appdev/-/knowledge_base/a/web-front-ends).

## Web Plugins

Web plugins are regular Java EE web modules designed to work with @product@. You
can integrate with various Enterprise Service Bus (ESB) implementations, as well
as Single Sign-On implementations, workflow engines, and so on. These are
implemented as web modules used by @product@ portlets to provide functionality.

## Templates and Themes

[Templates](/docs/7-2/frameworks/-/knowledge_base/f/layout-templates-intro) and
[themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction) are
plugins that change @product@'s appearance. Templates (layout templates) control
how you can arrange portlets on a page. They make up a page's body (the large
area into which you can drag and drop portlets). There are several built-in
layout templates. If you have a complex page layout (especially for your home
page), you may wish to create a custom layout template of your own.

Themes can completely transform @product@'s look and feel. Most organizations
have their own look and feel standards that apply to all of their web sites and
applications. By using a theme plugin, an organization can apply these standards
on @product@. There are many available theme plugins on Liferay's web site and
more are being added every day. This makes it easy for theme developers, as they
can customize existing themes instead of writing a new one from scratch.

## Liferay Marketplace App Packages

Regardless of app type, each [Liferay
Marketplace](https://web.liferay.com/marketplace) app is distributed in an LPKG
package. The LPKG package contains Marketplace  metadata and the files the app
needs to run. Note that it's possible for an LPKG  package to contain multiple
apps. For example, a single LPKG package can contain  several portlets. This is
common in cases where an app requires a Control Panel portlet for
administrators, and another portlet for end users.
