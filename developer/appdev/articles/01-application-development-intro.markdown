# Application Development 

Writing applications on Liferay's standards-based platform makes your life
easier. Whether you create headless services for clients to access, full-blown
web applications with beautiful UIs, or anything in between, @product@
streamlines the process to help you get your job done faster. 

Liferay's framework embraces your existing tools and build environments like
[Maven](https://maven.apache.org) and [Gradle](https://gradle.org). You can work
with the standard technologies you know and leverage Liferay's APIs for
Documents, Permissions, Search, or Content when you need them. Here's a high
level view of what you can do: 

-   **Deployment of existing standards-based apps:** If you have an existing app 
    built outside of @product@, you can deploy it on @product@. The Liferay
    Bundler Generator and Liferay npm Bundler provide the project scaffolding
    and packaging to deploy [Angular](https://angular.io/), [React](https://reactjs.org/), and [Vue](https://vuejs.org/) web front-ends as Widgets.
    Spring Portlet MVC app conversion to [PortletMVC4Spring](https://github.com/liferay/portletmvc4spring) requires only a few
    steps. JSF applications work almost as-is. Portlet 3.0 or 2.0 compliant
    portlets deploy on @product@. 

-   **Back-end Java services, web services, and REST services:** Service Builder
    is an object-relational mapper where you describe your data model in
    a single `xml` file. From this, you can generate the tables, a Java API for
    accessing your data model, and web services. On top of these, REST Builder
    generates OpenAPI-based REST services your client applications can call. 

-   **Authentication and single-sign on (SSO):** OAuth 2.0, OpenID Connect, and
    SAML are built-in and ready to go. 

-   **Front-end web development using Java EE and/or JavaScript:** Use Java EE
    standard Portlet technology (JSR 168, JSR 286, JSR 362) with CDI and/or JSF.
    Prefer Spring? [PortletMVC4Spring](https://github.com/liferay/portletmvc4spring) brings the Spring MVC Framework to Liferay. 
    Rather have a client-side app? Write it in [Angular](https://angular.io/),
    [React](https://reactjs.org/), or [Vue](https://vuejs.org/). Been using
    @product@ for a while? Liferay MVC Portlet is better than ever. 

-   **Frameworks and APIs for every need:** Be more productive by using
    Liferay's built-in and well-tested APIs. Need to provide file
    upload/download? Use the [Documents API](/docs/7-2/frameworks/-/knowledge_base/f/documents-and-media-api). Need a robust 
    permissions system? Use [Liferay permissions](/docs/7-2/frameworks/-/knowledge_base/f/defining-application-permissions). 
    Want users to submit comments? Use Liferay's [comments](/docs/7-2/frameworks/-/knowledge_base/f/adding-comments-to-your-app). 
    Need to process data outside the request/response? Use the [Message Bus](/docs/7-2/frameworks/-/knowledge_base/f/message-bus). 
    Should users select items from a list? Use the [Item Selector](/docs/7-2/frameworks/-/knowledge_base/f/item-selector).
    @product@ offers many APIs for every need, from an entire workflow framework
    to a streamlined way of getting request parameters.

-   **Tool freedom:** Liferay provides Maven archetypes, [Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace),
    [Gradle](/docs/7-2/reference/-/knowledge_base/r/gradle-plugins) and [Maven](/docs/7-2/reference/-/knowledge_base/r/maven-plugins) plugins, a [Yeoman](http://yeoman.io/)-based [theme generator](/docs/7-2/reference/-/knowledge_base/r/theme-generator), and [Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli)
    to integrate with any development workflow. On top of that, you can use our
    [IntelliJ plugin](/docs/7-2/reference/-/knowledge_base/r/intellij) or the
    Eclipse-based [Liferay Developer Studio](/docs/7-2/reference/-/knowledge_base/r/liferay-dev-studio) if you need 
    a full-blown development environment. 

-   **Developer community:** The
    [@product@ community](https://liferay.dev)
    is helpful and active. 

As you prepare to develop an app, you can think of it in terms of the app's web
front-end and the services it uses. The services can be Liferay Headless
services, web services outside of @product@, or services built into the app.


