# Liferay as a Development Platform [](id=liferay-as-a-development-platform)

If you've been reading everything up to this point, you've heard all about
@product@'s architecture, modularity, and technologies. What's left is to tell
you what it's like to use Liferay's platform as a basis for your site by
customizing it or by developing applications on it. The platform is designed to
make this easy and pleasant, and to integrate with the tools you use every day. 

But you're likely not interested in a bunch of prolegomena about it. Read on to
learn the details. 

## Web Applications and Portlets [](id=web-applications-and-portlets)

Liferay as a development platform has always provided flexibility for both
administrators and developers by making it easy to have more than one
application on a single page. Applications written this way are called
*portlets*, and are a mainstay of Liferay's platform. You can use
[Liferay's MVC Portlet framework](/develop/tutorials/-/knowledge_base/7-1/liferay-mvc-portlet)
or common frameworks such as [Spring MVC](/develop/tutorials/-/knowledge_base/7-1/spring-mvc)
or [JSF](/develop/tutorials/-/knowledge_base/7-1/jsf-portlets-with-liferay-faces)
to write portlets. If you plan to have a web-based interface to your
application, and want its administrator to have a lot of flexibility configuring
it, portlets provide a very powerful model. In this model you can create several
portlets instead of a larger application and let the administrator choose how to
combine them with other pre-existing portlets into a larger interface. 

That's not to say you don't have other choices. Since Liferay decouples its
business logic from its UI (which is provided in separate modules), you have
freedom to implement the UI in any other technology.

Because of this, you can use Liferay as a headless platform, because it's easy
to create [web services](/develop/tutorials/-/knowledge_base/7-1/web-services)
based on [Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services),
[JAX-RS, and JAX-WS](/develop/tutorials/-/knowledge_base/7-1/jax-ws-and-jax-rs).
Then you can build standalone web applications using any front-end technology or
mobile technology you like. 

## Extensibility [](id=extensibility)

As you might imagine, the system described above contains all the tools
necessary to make a well designed system that lets you not only create
applications based on modules, but also to extend the existing functionality of
the system. Liferay can benefit from this now because the platform on which it
rests is designed for both application development and customization.

Components make developing extensions and customizations convenient. If you
compare this model to other products that aren't designed for customization,
you'll see just how convenient it can be. 

To customize an existing service, the only thing you need to do is deploy a
component that extends the existing implementation. If you want to remove your
implementation and revert back to the default behavior, you simply un-deploy
your component. 

Compare that with the traditional way of customizing software by downloading its
source and maintaining a set of patches against it. Each time the software is
updated, you have to re-download the source, re-apply your patches, and
recompile the software. 

With Liferay, your custom code is kept in your own modules, which the container
takes care of applying based on metadata you supply.

## Developer Tools [](id=developer-tools)

As you learned above, Liferay's OSGi container gives you these benefits: 

-   The container can start and stop components.
-   A component implements an OSGi service. 
-   A component may use or consume OSGi services.
-   The framework manages the binding of the services a component consumes 
    (just like Spring or EJBs, but dynamically).

If all of this sounds great to you (as it does to us), there's only one thing
left: how do you get started developing components?  We believe in providing an
easy path for new developers while at the same time preserving flexibility for
experienced developers with strong tooling preferences. To achieve that, Liferay
provides some great tools, and if you're an experienced developer, these also
integrate into what you likely already use. If you use any of the standard build
tools like Gradle or Maven, any text editor or common Java IDEs like Eclipse,
intelliJ, or NetBeans, or any testing framework like Spock or JUnit, you can use
them with Liferay to develop components. 

[Liferay's tools](/develop/tutorials/-/knowledge_base/7-1/tooling)
add some important enhancements: 

-   [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli)
    speeds you up by creating Gradle-based Liferay projects from
    [templates](/develop/reference/-/knowledge_base/7-1/project-templates). 
-   [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
    is an opinionated SDK based on Gradle that uses Blade CLI to integrate your
    projects and your runtime into one convenient, distributable  and sharable
    place. 
-   Liferay IDE is an Eclipse-based development environment that
    integrates all the convenience of Blade CLI and Liferay Workspace
    into a best-of-breed graphical environment with all the bells and
    whistles you'd expect. 
-   Liferay Developer Studio provides all that Liferay IDE
    provides, plus additional tools that enterprise developers need.
-   [Liferay Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder)
    helps you create your back-end faster by generating all your database
    tables, local services, and web services from a single XML file. 

You can choose to use or ignore Liferay's tools. The point is you have the
freedom to do that, because Liferay provides an open development framework
that's designed to meet you where you are. We hate proprietary lock-in as much
as you do, so our tools are designed to complement the tools you're using
already instead of replacing them.

Beyond build tools and IDEs are the frameworks you'll use to build applications.
Liferay's development frameworks include a lot of functionality--comments,
social relationships, user management, and lots more--to speed up development of
your applications. They help you build applications out of well-tested, modern,
scalable, skinnable building blocks. You wind up not only with a great,
functional application, but also with one that took less time to develop, looks
the way you want it to, and performs well. This doesn't mean you're limited only
to what Liferay provides; again, you can use third-party frameworks if that's
what you like to use. 

To develop portlets, Liferay provides a convenient and easy-to-use framework
called [MVCPortlet](/develop/tutorials/-/knowledge_base/7-1/liferay-mvc-portlet)
to make writing portlets easy, but developers are free to use any other
framework, such as Spring MVC, to create portlets. MVCPortlet uses components to
handle requests, benefiting from all the characteristics described above
(lifecycle, extensibility, ease of composition, etc.). If you don't have a
strong opinion on which framework to use, we recommend that you try it out.

Liferay also includes a utility called
[Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder)
that makes it easy to create back-end database tables, an object-relational map
in Java for accessing them, and a place to put your business logic. It can also
generate JSON or SOAP web services, giving developers a full stack for storing
and retrieving data using web or mobile clients. But that doesn't prevent you
from using Java Persistence (JPA) and generating JAX-WS web services. 

In addition to the tooling, Liferay also provides many reusable frameworks. 

## Frameworks and APIs [](id=frameworks-and-apis)

Liferay's development platform provides a great framework for application
development and also offers APIs. Lots of them. You can create applications by
leveraging Liferay's many frameworks that encapsulate features that today's
applications commonly need. For example, a commenting system lets you attach
comments to any asset that you define, whether they be assets you develop or
assets that ship with the system. [Assets](/develop/tutorials/-/knowledge_base/7-1/asset-framework)
are shared by the system and are represent many common elements, such as Users,
Organizations, Sites, User Groups, blog entries, and even folders and files.

Liferay also includes many frameworks for operating on assets. A
[workflow system](/develop/tutorials/-/knowledge_base/7-1/liferays-workflow-framework)
makes it easy to create applications that require an approval process for users
to follow. The recycle bin stores deleted assets for a specified period of time,
making it easy for users to restore data without the intervention of an
administrator. A file storage API with multiple available back-ends makes
storing and sharing files trivial. Search is built into the system as well, and
it is designed for you to integrate it with your applications. Many of the
frameworks you might need when developing complex applications are already in
Liferay; you just need to take advantage of them: a Social Networking API,
user-generated forms with data lists, a [message bus](/develop/tutorials/-/knowledge_base/7-1/message-bus),
an audit system, and much more.

## Example Liferay Projects [](id=example-liferay-projects)

Enough theory. It's time for practice. A good way to get the flavor of
developing on Liferay's platform across is to show you some projects. First,
you'll see a portlet developed with MVCPortlet, showcasing the use of components
as well. Once you've seen that, the next best thing is to see an extension. Both
of these examples show you how easy it is to build functionality following a
modular paradigm. 

It would be nice to show you the standard Hello World project, Liferay style,
but that would be too easy: the default template that [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli)
or [Liferay IDE](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)
creates already does that by default. Instead, you'll see the Hello *You*
portlet.  This does the same thing as Hello World, except it adds the first name
of the user to the message. If your name therefore is John, it returns Hello,
John. 

Here's what the project layout looks like: 

![The Hello You portlet has a simple project structure.](../../images/hello-you-portlet-layout.png)

No new files were created after this project was generated by Liferay's Blade
CLI tool, so this is as simple as it gets. You have your portlet class, which is
in the `.java` file. You also have two different kinds of resources:
[language properties](/develop/tutorials/-/knowledge_base/7-1/internationalization)
and JSP files. Finally, the `bnd.bnd` file describes the application's metadata
for the OSGi container, and the `build.gradle` file builds the project.

Any web developer that's familiar with Java can understand the JSPs, but some
explanation is in order because of the style. Liferay's coding style defines a
single `init.jsp` that contains all the imports and tag library initializations
necessary for the front-end. This way, any JSP can simply include `init.jsp`,
and all of its imports are satisfied. The `init.jsp` for this project was not
modified from the generated project, and it looks like this: 

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <%@ taglib uri="http://java.sun.com/portlet\_2\_0" prefix="portlet" %>

    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

    <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    <liferay-theme:defineObjects />

    <portlet:defineObjects />

As you can see, all it does is declare the tag libraries you probably want to
use, and then it calls a couple of tags that makes objects from the portlet
framework available. Since there's nothing really interesting here, you'll want
to look at `view.jsp` next: 

    <%@ include file="/init.jsp" %>

    <jsp:useBean id="userName" type="java.lang.String" scope="request" />

    <p>

        <b>Hello, <%=userName %>!</b>

    </p>

Now we've got something. The portlet class (the Controller, in MVC terms) has
made a `userName` string available in the request, and this JSP retrieves it and
uses it to say hello to the user. The real functionality, therefore is in the
portlet class: 

    @Component(
        immediate = true,
        property = {
            "com.liferay.portlet.display-category=category.sample",
            "com.liferay.portlet.instanceable=true",
            "javax.portlet.display-name=hello-you Portlet",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/view.jsp",
            "javax.portlet.resource-bundle=content.Language",
            "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
    )
    public class HelloYouPortlet extends MVCPortlet {

        @Override
        public void render(RenderRequest renderRequest, 
                        RenderResponse renderResponse)
                throws IOException, PortletException
        {
            ThemeDisplay themeDisplay = (ThemeDisplay)
                        renderRequest.getAttribute(WebKeys.THEME\_DISPLAY);

            User user = themeDisplay.getUser();

            renderRequest.setAttribute("userName", 
                        user.getFirstName());



            super.render(renderRequest, renderResponse);
        }

    }

Now we're talking; here's the real stuff. At the top is the `@Component`
annotation, which tells the OSGi container how it should treat this module. By
specifying `immediate=true`, you're saying that when this module is deployed and
all of its dependencies are satisfied, it should be started immediately instead
of being lazy-loaded. Next are several [properties specific to portlets](/develop/reference/-/knowledge_base/7-0/portlet-descriptor-to-osgi-service-property-map):
the category in which it should appear in Liferay's UI, its display name, its
default view, and more. Finally, the service--which is just a Java
Interface--that it implements is defined, which is the portlet class. 

Next, you have the class itself, which extends Liferay's
[`MVCPortlet` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
(that extends `GenericPortlet`, that implements `Portlet`). The only method
overridden is the `render()` method, and Liferay's API is used to get the user's
first name and put it in a request attribute called `userName`. 

So you can see how this works: the portlet runs and retrieves the user's first
name, puts that in the request, and then by the use of the template path and
view template properties specified in the `@Component` annotation, forwards
processing to `view.jsp`, where the user's first name is retrieved and
displayed. 

The only other item of interest is the `bnd.bnd` file:

    Bundle-SymbolicName: com.liferay.docs.hello.you
    Bundle-Version: 1.0.0

This declares the name of the module (sometimes also called a bundle). It's a
good practice to namespace it properly to avoid name conflicts in the container.
The version is also declared, which allows the container to manage dependencies
down to the version level of a module. This is called Semantic Versioning, and
is a discussion by itself. 

That's all there is to this portlet. Next, you'll see an extension, which in
many cases is even simpler than a portlet. 

Liferay's UI is divided up into several areas. There's the control menu and the
product menu, which contains the add menu and the simulation menu. You can
extend the UI by deploying a module that adds what you want. In this example,
you'll add a link to the product menu, which is the menu that by default sits in
the top right of the browser: 

![The product menu appears beneath the user's profile link.](../../images/product-menu-unchanged.png)

To this, you'll add a link to this website: 

![You can add links to the product menu by deploying a component.](../../images/product-menu-with-link.png)

As with the portlet project, this project's layout contains only a few items
that are easy to understand. 

![The product menu project is simpler than the portlet was.](../../images/product-menu-project-layout.png)

As before, you have a build script, a `bnd.bnd` file that declares the module's
name and version, and this time, only a Java class and a language properties
file. 

The Java class defines only four methods: 

    @Component(
        immediate = true,
        property = {
            "product.navigation.control.menu.category.key=" +
                     ProductNavigationControlMenuCategoryKeys.USER,
            "product.navigation.control.menu.entry.order:Integer=1"
        },
        service = ProductNavigationControlMenuEntry.class
    )
    public class DevProductNavigationControlMenuEntry
        extends BaseProductNavigationControlMenuEntry
        implements ProductNavigationControlMenuEntry {

        @Override
        public String getIcon(HttpServletRequest request) {

            return "link";
        }

        @Override
        public String getLabel(Locale locale) {

            ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());

            return LanguageUtil.get(resourceBundle, "custom-message");
        }

        @Override
        public String getURL(HttpServletRequest request) {

            return "https://dev.liferay.com";

        }

        @Override
        public boolean isShow(HttpServletRequest request) throws 
            PortalException {

            return true;
        }

    }

As before, this project was generated using a [template](/develop/reference/-/knowledge_base/7-1/project-templates)
from Blade CLI. The source code is part of the template; the only thing you'll
need to do is provide the link. 

The first method gets the Font Awesome icon you want to use in the menu. The
next gets the "label," the text that appears when a user hovers the mouse over
the link. This text is the value of the only property in the
`Language.properties` file: 

    custom-message=Liferay Developer Network

The next method returns the URL that's the destination for this link, and the
final method returns a boolean for showing or hiding the link. 

When you [deploy this module](/develop/reference/-/knowledge_base/7-1/project-templates)
and the module starts, the link appears in the menu. You don't have to mess
around looking in Liferay's JSP or JavaScript files to customize the menu: it's
an extension point, and it is designed to be customized. 

This is the modular paradigm for development. It helps you keep a clean
separation of your code, whether it be applications or extensions, from
Liferay's code, and it gives you the power to customize the system dynamically,
while it's running, to avoid downtime. It is a different way of doing things,
but we believe it's a better way.  When you start working with modules and see
the benefits you can gain, we think you'll agree. 
