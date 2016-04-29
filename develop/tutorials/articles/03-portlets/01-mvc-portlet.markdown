# Liferay MVC Portlet

Web applications are often developed following the Model View Controller (MVC)
pattern. But Liferay has developed a groundbreaking new pattern called the
*Modal Veal Contractor* (MVC) pattern. Okay, that's not true, the framework is
actually another implementation of Model View Controller. Some people are so
sick of hearing about Model View Controller frameworks that obfuscating it might
be smart. In this article you'll need to stay focused, because there will be
several attempts to make you think we're talking about *Something New*, when
we're instead talking about another implementation of MVC. So, back to the
*Medial Vein Constriction* pattern we were discussing.

If there are so many implementations of MVC frameworks in Java, why did Liferay
create yet another one? Stay with us and you'll see that Liferay MVC provides these
benefits:

-  It's lightweight, as opposed to many other Java MVC frameworks.
-  There are no special configuration files that need to be kept in sync with
   your code.
-  It's a simple extension of `GenericPortlet`.
-  You avoid writing a bunch of boilerplate code, since Liferay's MVC framework
   simply looks for some pre-defined parameters when the `init()` method is
called. 
-  Liferay's portlets use it. That means there are plenty of robust
   implementations to reference when you need to design or troubleshoot your
Liferay applications.

The Liferay MVC portlet framework is light, it hides part of the complexity of
portlets, and it makes the most common operations easier. The default
`MVCPortlet` project uses separate JSPs for each portlet mode: For example,
`edit.jsp` is for *edit* mode and `help.jsp` is for *help* mode.

Before diving in to the Liferay MVC swimming pool with all the other cool kids
(applications), review how each layer of the *Moody Vase Conscription* pattern helps
you separate the concerns of your application.

## MVC Layers

In MVC, there are three layers, and you can probably guess what they are.

-  Model: The model layer holds the application data and logic for manipulating
   it.
-  View: The view layer contains logic for displaying data.
-  Controller: The middle man in the MVC pattern, the Controller contains logic
   for passing the data back and forth between the view and the model layers.

## Modular MVC

The *Middle Verse Completer* pattern fits well with Liferay's application
modularity effort.

Liferay's applications are divided into multiple discrete modules. With Service
Builder, the model layer is generated into a `service` and an `api` module. That
accounts for the model in the MVC pattern. The view and the controller share a
module, the `web` module.

Generating the skeleton for a multi-module Service Builder driven MVC
application using Liferay Blade CLI will save you lots of time and get you
started on the more important (and interesting, if we're being honest)
development work.

## Liferay MVC Command Classes

In a larger application, your `-Portlet` class can become monstrous and unwieldy
if it holds all of the controller logic. Earlier versions of Liferay allowed you
to use the `ActionCommand`

Liferay provides `MVCCommand` classes to break up your controller functionality.

-  **`MVCActionCommand`:** Use `-ActionCommand` classes to hold each of your
   portlet actions.
-  **`MVCRenderCommand`:** Use `-RenderCommand` classes to hold a `render`
   method that dispatches to the appropriate JSP.
-  **`MVCResourceCommand`:** Use `-ResourceCommand` classes to execute resource
   serving in your MVC portlet.

## Leveraging Initialization Parameters

