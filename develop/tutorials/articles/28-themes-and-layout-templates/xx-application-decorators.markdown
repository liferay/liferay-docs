Application Decorators

Replacing the Show Borders option as of Liferay 7

This document describes how to use and create Application Decorators in
Liferay 7.

[Motivation](#h.pyzlj21hlc15)

[User’s Guide](#h.dpn5fegvtn1t)

[Selecting an Application Decorator](#h.iw6aue6rezse)

[Application Decorators in the default theme](#h.3gc20y3keh8t)

[Developer’s Guide](#h.n03nqcoinuwn)

[Understanding Application Decorators](#h.fesukbd14dwx)

[Adding Application Decorators to a theme](#h.srbgwh5dlk41)

[Applying a default Application Decorator to embedded
portlets](#h.tomjbwcde5tp)

[Upgrading from previous versions](#h.hwncvvtdjjb0)

Motivation {#h.pyzlj21hlc15 .c3 .c5}
==========

In previous versions of Liferay, administrators could display/hide the
application borders through the “Show Borders” option of the Look & Feel
configuration. In Liferay 7.0 this option has been replaced with a more
powerful mechanism to customize the style of the application wrapper
through the Application Decorators.

User’s Guide {#h.dpn5fegvtn1t .c3 .c5}
============

Selecting an Application Decorator {#h.iw6aue6rezse .c3 .c5}
----------------------------------

To change the Application Decorator of an application, select the Look
And Feel Configuration option in the configuration options menu and then
select a different option in the Application Decorator field. Click save
to apply the changes and refresh the page to view the result.

![LookAndFeel.png](images/image00.png)

Application Decorators in the default theme {#h.3gc20y3keh8t .c3 .c5}
-------------------------------------------

Liferay 7 default theme “Classic” includes three Application Decorators:

-   Decorate: this is the default Application Decorator when using the
    Classic theme. When this decorator is applied, the application is
    wrapped in a white box with a border and the application custom
    title is displayed at the top.

![Screen Shot 2016-03-30 at 11.13.04 AM.png](images/image04.png)

-   Borderless: when this decorator is applied, the application is no
    longer wrapped in a white box, but the application custom title is
    displayed at the top.

![Screen Shot 2016-03-30 at 11.14.37 AM.png](images/image02.png)

-   Barebone: when this decorator is applied, neither the wrapping box
    nor the custom application title are shown. This option is
    recommended when you only want to display the bare application
    content.

![Screen Shot 2016-03-30 at 11.35.18 AM.png](images/image03.png)

Developer’s Guide {#h.n03nqcoinuwn .c3 .c5}
=================

Understanding Application Decorators {#h.fesukbd14dwx .c3 .c5}
------------------------------------

If you inspect the markup of your Liferay application when it's on a
page you'll observe that it is wrapped by two layers. Among other
things, these layers provide some common basic features like the drag
and drop or the application border style. In order to protect these
features, you cannot modify the markup of these layers with a theme.

Application Decorators provide a mechanism to add a custom CSS class to
one of this wrapping layers on user’s demand. By defining styles for
this class in your theme, you can change the look and feel of the
application instances where the Application Decorator is applied,
including its wrapper.

This image shows the markup of the layers wrapping a Liferay application
when the “Decorate” Application Decorator is applied:

![code.png](images/image01.png)

Adding Application Decorators to a theme {#h.srbgwh5dlk41 .c3 .c5}
----------------------------------------

Adding Application Decorators to your theme is quite similar to adding
Color Schemes. You just have to follow these steps:

1.  Declare the Application Decorators in your liferay-look-and-feel.xml

The Document Type Definition for the liferay-look-and-feel.xml in
Liferay 7 (see liferay-look-and-feel\_7\_0\_0.dtd) contains the
information and rules to add Application Decorators (codewise referred
as portlet-decorators) to your theme.

Taking Liferay default theme as an example, here is how to extend your
liferay-look-and-feel.xml file to add Application Decorators:

+--------------------------------------------------------------------------+
| \<?xml version="1.0"?\>\                                                 |
| \<!DOCTYPE look-and-feel PUBLIC "-//Liferay//DTD Look and Feel           |
| 7.0.0//EN"                                                               |
| "http://www.liferay.com/dtd/liferay-look-and-feel\_7\_0\_0.dtd"\>\       |
| \                                                                        |
| \<look-and-feel\>\                                                       |
|         \<compatibility\>\                                               |
|                 \<version\>7.0.0+\</version\>\                           |
|         \</compatibility\>\                                              |
|         ...\                                                             |
|         \<theme id="classic" name="Classic"\>\                           |
|                 ...\                                                     |
|                 \<portlet-decorator id="barebone" name="Barebone"\>\     |
|                         \<portlet-decorator-css-class\>portlet-barebone\ |
| </portlet-decorator-css-class\>\                                         |
|                 \</portlet-decorator\>\                                  |
|                 \<portlet-decorator id="borderless" name="Borderless"\>\ |
|                         \<portlet-decorator-css-class\>portlet-borderles |
| s\</portlet-decorator-css-class\>\                                       |
|                 \</portlet-decorator\>\                                  |
|                 \<portlet-decorator id="decorate" name="Decorate"\>\     |
|                         \<default-portlet-decorator\>true\</default-port |
| let-decorator\>\                                                         |
|                         \<portlet-decorator-css-class\>portlet-decorate\ |
| </portlet-decorator-css-class\>\                                         |
|                 \</portlet-decorator\>\                                  |
|         \</theme\>\                                                      |
|         ...\                                                             |
| \</look-and-feel\>                                                       |
+--------------------------------------------------------------------------+

The portlet-decorator element contains all the information about the
Application Decorator:

-   id: this required attribute contains a unique string that identifies
    this specific Application Decorator. This is the value that will be
    stored when applying an Application Decorator, and it can be used to
    refer this decorator in your theme templates.
-   name: this required attribute a user friendly identifier for the
    Application Decorator to be displayed in the Look and Feel UI.
-   portlet-decorator-css-class: this required element contains the name
    of the CSS class that will be added to the application wrapping
    layer when this Application Decorator is applied.
-   default-portlet-decorator: use this optional element to identify the
    default Application Decorator for your theme.

You can define as many Application Decorators as you want, but it’s
recommended to include at least one for the “decorate”, “borderless” and
“barebone” use cases.

2.  Define the styles for your Application Decorator CSS class

Once you’ve declared your Application Decorators, it’s time to define
their effect in the application look and feel. While the previous step
was quite straightforward, this depends on your creativity.

As an example, take a look to the \_portlet\_decorator.scss of the
Classic theme:

+--------------------------------------------------------------------------+
| .portlet-decorate .portlet-content {\                                    |
|         background: \#FFF;\                                              |
|         border: 1px solid \#DEEEEE;\                                     |
| }\                                                                       |
| \                                                                        |
| .portlet-barebone .portlet-content {\                                    |
|         padding: 0;\                                                     |
| }                                                                        |
+--------------------------------------------------------------------------+

3.  Optional: changing your application markup based on the Application
    Decorator:

        

        So far we’ve seen how to use Application Decorators to change
the look and feel of an application with styles.

It’s possible to go a step further and alter the markup of you
application (the one that is contained inside the wrapping layers) based
on the Application Decorator applied. For this, you simply have to edit
the portlet.ftl template under the \_diffs folder of your theme,
retrieve the portletDecoratorId of the current Application Decorator
(the one you declared in the first step) from the portlet\_display
object and make some decisions based on it.

For example, this is how the Classic theme shows the custom application
title when the “Barebone” Application Decorator is not applied.

+--------------------------------------------------------------------------+
| \<\#if portlet\_display.getPortletDecoratorId() != "barebone"\>\         |
|         \<h2 class="portlet-title-text"\>\${portlet\_title}\</h2\>\      |
| \</\#if\>                                                                |
+--------------------------------------------------------------------------+

Applying a default Application Decorator to embedded portlets {#h.tomjbwcde5tp .c3 .c5}
-------------------------------------------------------------

Once you have installed a theme that contains Application Decorators,
site administrators can apply them to an application instance by
selecting the Application Decorator in the Look and Feel Configuration
dialog as described in the [User’s Guide](#h.dpn5fegvtn1t).

If your theme contains embedded applications, it’s also possible to
apply an Application Decorator other than the default one by settings
its preferences. For instance, if your theme declares an Application
Decorator with id “barebone” and you want to embed the Navigation Menu
application and apply this decorator to it, you can use this code in
your theme templates:

+--------------------------------------------------------------------------+
| \<\#assign VOID =                                                        |
| freeMarkerPortletPreferences.setValue("portletSetupPortletDecoratorId",  |
| "barebone")\>                                                            |
|                                                                          |
| \<nav class="site-navigation" id="navigation" role="navigation"\>        |
|                                                                          |
|         \<div class="navbar-form navbar-right" role="search"\>           |
|                                                                          |
|                 \<@liferay.search                                        |
| default\_preferences="\${freeMarkerPortletPreferences}" /\>              |
|                                                                          |
|         \</div\>                                                         |
|                                                                          |
|         \<div class="navbar-right"\>                                     |
|                                                                          |
|                 \<@liferay.navigation\_menu                              |
| default\_preferences="\${freeMarkerPortletPreferences}" /\>              |
|                                                                          |
|         \</div\>                                                         |
|                                                                          |
| \</nav\>                                                                 |
|                                                                          |
| \<\#assign VOID = freeMarkerPortletPreferences.reset()\>                 |
+--------------------------------------------------------------------------+

Upgrading from previous versions {#h.hwncvvtdjjb0 .c3 .c5}
--------------------------------

Upgrading to Liferay 7 will automatically assign the “borderless”
decorator to those applications that had the “Show Borders” option set
to false in previous versions of Liferay.

As mentioned, you should consider to update your Liferay Themes to
provide at least one for the “decorate”, “borderless” and “barebone” use
cases.


