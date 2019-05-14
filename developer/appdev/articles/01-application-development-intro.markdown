# Application Development 

How many times have you had to start over from scratch? Probably almost as many
times as you've started a new project, because each time you have to write not
only the code to build the project, but also the underlying code that supports
the project. It's never a good feeling to have to write the same kind of code
over and over again. But each new project that you do after a while can feel
like that: you're writing a new set of database tables, a new API, a new set of
CSS classes and HTML, a new set of JavaScript functions.

Wouldn't it be great if there was a platform that provided a baseline set of
features that gave you a head start on all that repetitive code? Something that
lets you get right to the features of your app or site, rather than making you
start over every time with the basic building blocks? There is such a thing, and
it's called @product@.

![Figure 1: With @product@, you never have to start from scratch.](../images/dont-start-from-scratch.png)

@product@ offers you a complete platform for building web apps and
web services quickly, using features and frameworks designed for rapid
development, good performance, and ease of use. The base platform is already
there, and it's built as a robust container for applications that you can put
together in far less time than you would from scratch.

Liferay's makes app development easier, and fun, by addressing these needs:

-   **Web front-end development using standards and widely adopted 
    technologies:** @product@ supports Portlet 3.0, which also makes it 
    compatible with Portlet 2.0. The Liferay Bundler Generator and Liferay npm
    Bundler makes it possible to develop Widgets that use popular JavaScript
    libraries, such as Angular, React, and Vue. Spring Framework technologies
    are supported using PortletMVC4Spring. @product@ supports Java EE standards,
    including JSF and CDI. These are just a few of the standards and
    technologies @product@ supports. 

-   **Deployment of existing standard-based apps:** If you have an existing app 
    built outside of @product@, you Liferay facilitates deploying it on
    @product@. The Liferay Bundler Generator and Liferay npm Bundler provide the
    project scaffolding and packaging to deploy Angular, React, and Vue web
    front-ends as Widgets. Spring Portlet MVC app conversion to
    PortletMVC4Spring requires only a few steps. Portlet 3.0 or 2.0 compliant
    portlets deploy on @product@. 

-   **Services development using JAX-RS/REST and JAX-WS:** Service Builder 
    provides a way to define model entities and generates Java classes and SQL
    for creating database tables, interacting with those tables, and executing
    CRUD operations. The Java classes comprise business services for which
    JAX-WS JSON and SOAP web services can be generated automatically. Last, but
    not least, REST Builder generates REST services for your business services. 

-   **Authentication and single-sign on (SSO):** @product@ supports using OAuth 
    2.0 and SAML. 

-   **Code reuse:** It's all about reusing, providing reusable frameworks and 
    libraries and allowing you to create your own. Liferay Service Builder and
    REST Builder, for example, implement persistence and enable web services,
    based on the models and business services you define. These things allow you
    to focus on features, business logic, user experience and application look
    and feel. 

-   **Tooling support and freedom:** Liferay provides tooling and templates for 
    the command line and IDEs and support for popular build environments, while
    remaining tool agnostic. Blade CLI uses templates to generate projects that
    use Gradle and Maven build environments. Liferay @ide@ integrates with
    Eclipse and IntelliJ. You can use these tools to the extent you want and are
    free to use other tooling that you like. 

-   **Developer community:** The
    [@product@ community](https://community.liferay.com/)
    is helpful, active, and easy to plug into. [Liferay
    projects](https://community.liferay.com/projects), including @product@ and
    projects for Liferay tooling and some frameworks, have dedicated sites. It's
    an environment that encourages
    [blogging](https://community.liferay.com/blogs), [forum
    discussion](https://community.liferay.com/forums),
    [chat](https://community.liferay.com/chat), and more. 

As you prepare to develop an app, you can think of it in terms of the app's web
front-end and the services it uses. The services can be Liferay Headless
services, web services outside of @product@, or services built into the app.

## Web Front-end Development 

You can build an app's services and web front-end simultaneously. But this guide starts with developing web front-ends using these widely adopted JavaScript libraries:

-   [Angular](/docs/7-2/frameworks/-/knowledge_base/appdev/angular-widget)
-   [React](/docs/7-2/frameworks/-/knowledge_base/appdev/react-widget) 
-   [Vue](/docs/7-2/frameworks/-/knowledge_base/appdev/vue-widget)

You'll find steps for deploying existing apps that use each library. Liferay
Bundle Generator generates a portlet project for each library type and Liferay
npm Bundler packages and deploys the project to @product@ as a Widget. 

The next front-end development options use Java-based standards and technologies
are shown. [Bean
Portlet](/docs/7-2/frameworks/-/knowledge_base/appdev/bean-portlet) and
[JSF](/docs/7-2/frameworks/-/knowledge_base/appdev/jsf-portlet) covers Java EE
development standards. Bean Portlet includes full support of Portlet 3.0 and CDI
beans. 

If you like developing with Spring, check out app development using
[PortletMVC4Spring](/docs/7-2/frameworks/-/knowledge_base/appdev/portlet-mvc-for-spring-portletmvc4spring).
Liferay forked the Spring Portlet MVC project to improve and maintain it for
compatibility with the latest versions of the Spring Framework and the Portlet
API. Liferay adopted Spring Portlet MVC in March of 2019 and renamed it to
*PortletMVC4Spring*. 

Last, but not least, [Liferay MVC
Portlet](/docs/7-2/frameworks/-/knowledge_base/appdev/liferay-mvc-portlet)
demonstrates modular development used in Liferay's @product@ core and apps. Most
of Liferay's @product@ portlets are Liferay MVC Portlets. Getting familiar with
Liferay MVC Portlet can facilitate learning how to customize @product@ and
Liferay apps. 

A web front-end requires backend services. 

## Services Development 

Services development starts with developing the business and persistence using
Liferay Service Builder. In your service project you'll define models and their
relationships and then run Service Builder to generate database tables and a
Java API for executing CRUD operations on the models. 

Then it's on to making the services available as web services. You'll define
JAX-RS services using REST Builder and generate much of the code automatically.
JAX-WS service development is demonstrated using Service Builder and Liferay
Headless Services are also covered. You'll implement authentication using OAuth
2.0 and SSO using SAML. 

Throughout this guide, we hope you'll find familiar development technologies and
frameworks. It's typically easier to go with what you know. We'll also mention
frameworks and tooling that facilitate development and make it fun. Start with
developing web front-ends. 
