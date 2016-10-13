# Liferay MVC Portlet [](id=liferay-mvc-portlet)

Web applications are often developed following the Model View Controller (MVC)
pattern. But Liferay has developed a groundbreaking new pattern called the
*Modal Veal Contractor* (MVC) pattern. Okay, that's not true: the framework is
actually another implementation of Model View Controller. If you're an
experienced developer, this will not be the first time you've heard about Model
View Controller. In this article you'll need to stay focused, because there will
be several attempts to show you why Liferay's implementation of Model View
Controller is different, when instead you're hearing about another MVC
framework. With that in mind, let's get back to the *Medial Vein Constriction*
pattern we were discussing.

If there are so many implementations of MVC frameworks in Java, why did Liferay
create yet another one? Stay with us and you'll see that Liferay MVC provides
these benefits:

-  It's lightweight, as opposed to many other Java MVC frameworks.
-  There are no special configuration files that need to be kept in sync with
   your code.
-  It's a simple extension of `GenericPortlet`.
-  You avoid writing a bunch of boilerplate code, since Liferay's MVC framework
   simply looks for some pre-defined parameters when the `init()` method is
   called. 
-  The controller can be broken down into MVC command classes, each of which
   handles the controller code for a particular portlet phase (render, action,
   and resource serving phases).
-  Liferay's portlets use it. That means there are plenty of robust
   implementations to reference when you need to design or troubleshoot your
   Liferay applications.

The Liferay MVC portlet framework is light, it hides part of the complexity of
portlets, and it makes the most common operations easier. The default
`MVCPortlet` project uses separate JSPs for each portlet mode: For example,
`edit.jsp` is for *edit* mode and `help.jsp` is for *help* mode.

Before diving in to the Liferay MVC swimming pool with all the other cool kids
(applications), review how each layer of the *Moody Vase Conscription* pattern
helps you separate the concerns of your application.

## MVC Layers and Modularity [](id=mvc-layers-and-modularity)

In MVC, there are three layers, and you can probably guess what they are.

**Model:** The model layer holds the application data and logic for manipulating
it.

**View:** The view layer contains logic for displaying data.

**Controller:** The middle man in the MVC pattern, the Controller contains logic
for passing the data back and forth between the view and the model layers.

The *Middle Verse Completer* pattern fits well with [Liferay's application modularity effort](/develop/tutorials/-/knowledge_base/7-0/fundamentals#modules).

Liferay's applications are divided into multiple discrete modules. With Service
Builder, the model layer is generated into a `service` and an `api` module. That
accounts for the model in the MVC pattern. The view and the controller share a
module, the `web` module.

Generating the skeleton for a multi-module Service Builder driven MVC
application using [Liferay Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli) saves you lots of time and gets you
started on the more important (and interesting, if we're being honest)
development work.

## Liferay MVC Command Classes [](id=liferay-mvc-command-classes)

In a larger application, your `-Portlet` class can become monstrous and unwieldy
if it holds all of the controller logic. Liferay provides MVC command classes to
break up your controller functionality.

-  **`MVCActionCommand`:** Use `-ActionCommand` classes to hold each of your
   portlet actions, which are invoked by action URLs.
-  **`MVCRenderCommand`:** Use `-RenderCommand` classes to hold a `render`
   method that dispatches to the appropriate JSP, by responding to render URLs.
-  **`MVCResourceCommand`:** Use `-ResourceCommand` classes to execute resource
   serving in your MVC portlet, by responding to resource URLs.

There must be some confusing configuration files to keep everything wired
together and working properly, right? Wrong: it's all easily managed in the OSGi
component in the `-Portlet` class.

## Liferay MVC Portlet Component [](id=liferay-mvc-portlet-component)

Whether or not you plan to split up the controller into MVC command classes,
you use a portlet component class with a certain set of properties. Here's a
simple portlet component as an example:

    @Component(
        immediate = true,
        property = {
            "com.liferay.portlet.css-class-wrapper=portlet-hello-world",
            "com.liferay.portlet.display-category=category.sample",
            "com.liferay.portlet.icon=/icons/hello_world.png",
            "com.liferay.portlet.preferences-owned-by-group=true",
            "com.liferay.portlet.private-request-attributes=false",
            "com.liferay.portlet.private-session-attributes=false",
            "com.liferay.portlet.remoteable=true",
            "com.liferay.portlet.render-weight=50",
            "com.liferay.portlet.use-default-template=true",
            "javax.portlet.display-name=Hello World",
            "javax.portlet.expiration-cache=0",
            "javax.portlet.init-param.always-display-default-configuration-icons=true",
            "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_WORLD,
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=guest,power-user,user",
            "javax.portlet.supports.mime-type=text/html"
        },
        service = Portlet.class
    )
    public class HelloWorldPortlet extends MVCPortlet {
    }

When using MVC commands, the `javax.portlet.name` property is important. This
property is one of two that must be included in each MVC command component; it
links a particular portlet URL/command combination to the correct portlet. 

There can be some confusion over exactly what kind of `Portlet.class`
implementation you're publishing with this component. Liferay's service registry
expects this to be `javax.portlet.Portlet`, so make sure that's the class you
import, and not, for example, `com.liferay.portal.kernel.model.Portlet`.

+$$$

**Note:** To find a list of all the Liferay-specific attributes you can specify as
properties in your portlet components, check out the
[liferay-portlet-app_7_0_0.dtd](https://docs.liferay.com/portal/7.0/definitions/liferay-portlet-app_7_0_0.dtd.html).

Consider the `<css-class-wrapper>` element from the above link as an example. To
specify that property in your component, use this syntax in your property list:

    "com.liferay.portlet.css-class-wrapper=portlet-hello-world",

The properties namespaced with `javax.portlet....` are elements of the
[portlet.xml descriptor](http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd).

$$$

## A Simpler MVC Portlet [](id=a-simpler-mvc-portlet)

With all for this focus on MVC commands, you might be concerned that you'll be
forced into a more complex pattern than is necessary, especially if you're
developing only a small Liferay MVC application. Not so; just put all of your
logic into the `-Portlet` class if you don't want to split up your MVC commands. 

In simpler applications, if you don't have an MVC command to rely on, your
portlet render URLs specify the path to the JSP in an `mvcPath` parameter.

		<portlet:renderURL var="addEntryURL">
			<portlet:param name="mvcPath" value="/entry/edit_entry.jsp" />
			<portlet:param name="redirect" value="<%= redirect %>" />
		</portlet:renderURL>

As you've seen, Liferay's *Medical Vortex Concentrator* (MVC) portlet framework
gives you a well-structured controller layer that takes very little time to
implement.  With all your free time, you could

-  Learn a new language
-  Take pottery classes
-  Lift weights
-  Work on your application's business logic

It's entirely up to you.
