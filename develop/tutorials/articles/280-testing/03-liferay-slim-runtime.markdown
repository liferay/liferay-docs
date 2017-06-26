# Liferay Slim Runtime [](id=liferay-slim-runtime)

The Liferay Slim Runtime provides the bare necessities for running Service
Builder modules. It's useful for testing applications quickly in a Liferay
runtime environment free of Liferay add-ons.

The Liferay Slim Runtime provides

- Caching infrastructure
- Database infrastructure
- HTTP support
- JAX-RS support
- Limited set of Liferay utility classes
- OSGi framework for running modules
- Service Builder runtime for Service Builder modules
- Spring infrastructure
- Transaction infrastructure

It does **not** provide

- Authentication/Authorization layers
- Layout templates
- Permissions
- Portlet support (no portlet container)
- Sites
- Themes
- etc.

Building and launching a Liferay Slim Runtime is much quicker than a typical
@product@ bundle. Because of decreased build and startup times, the Slim Runtime
provides a great environment for testing. You'll learn how to build one next.

## Build [](id=build)

To build the Slim Runtime, you must have the
[liferay-portal](https://github.com/liferay/liferay-portal) Github repository
[forked](https://help.github.com/articles/fork-a-repo/) and
[cloned](https://help.github.com/articles/cloning-a-repository/) to your local
machine. Navigate to the repository's root folder and execute the following Ant
command:

    ant all -Dbuild.profile=slim

It's built in the server directory specified by the `app.server.properties`
file's `app.server.parent.dir` property. Note that the Slim Runtime only
supports Apache Tomcat 8+. This limitation simplifies packaging and
configuration.

## Launch [](id=launch)

To launch the Slim Runtime, run the Tomcat start scripts found in the runtime's
`<tomcat>/bin` directory:

    ./startup.[sh|bat]

## Deploying Modules [](id=deploying-modules)

You can deploy modules from any of the default directories the
[portal.properties](@platform-ref@/7.0-latest/propertiesdoc/portal.properties.html)
file defines (see properties below) or from a custom
auto-deploy directory you add to the `module.framework.auto.deploy.dirs`
property.

    module.framework.base.dir=${liferay.home}/osgi

    module.framework.configs.dir=${module.framework.base.dir}/configs
    module.framework.marketplace.dir=${module.framework.base.dir}/marketplace
    module.framework.modules.dir=${module.framework.base.dir}/modules
    module.framework.war.dir=${module.framework.base.dir}/war

    module.framework.auto.deploy.dirs=\
        ${module.framework.configs.dir},\
        ${module.framework.marketplace.dir},\
        ${module.framework.modules.dir},\
        ${module.framework.war.dir}

By default, a pristine Slim Runtime has no UI or apps. Requests to it result in
404 errors.

The modules you add provide all the functionality.

## Adding Functionality [](id=adding-functionality)

A web endpoint is the simplest type of function.

The following snippet demonstrates a simple servlet that responds to all
requests to `http://localhost:8080[/*]`:

    package web.sample;

    import java.io.IOException;
    import java.io.PrintWriter;

    import javax.servlet.Servlet;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

    @Component(
        immediate = true,
        property = {
            HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN + "=/*"
        },
        service = Servlet.class
    )
    public class SampleServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        @Override
        protected void service(
                HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            response.setContentType("text/html");

            PrintWriter writer = response.getWriter();

            writer.println("<h2>Hello You!</h2>");
        }

    }

## The Database [](id=the-database)

The Slim Runtime creates the database schema automatically the first time it
runs.

    MariaDB [lportal]> show tables;
    +------------------+
    | Tables_in_lportal|
    +------------------+
    | ClassName_       |
    | Configuration_   |
    | Counter          |
    | Release_         |
    | ServiceComponent |
    +------------------+
    5 rows in set (0.00 sec)

Only the following core services are available:

- `ClassNameLocalService`
- `CounterLocalService`
- `ReleaseLocalService`
- `ServiceComponentLocalService`

No other services are provided! Therefore, any service deployed to this Slim
Runtime that depends on services other than these won't work.

## Service Builder [](id=service-builder)

The Service Builder runtime bootstraps all deployed Service Builder services
(API and service modules).

For example, deploying the `com.liferay.contacts.api` and
`com.liferay.contacts.service` modules adds the `Contacts_Entry` table to the
database:

    MariaDB [lportal]> show tables;
    +------------------+
    | Tables_in_lportal|
    +------------------+
    | ClassName_       |
    | Configuration_   |
    | Contacts_Entry   |
    | Counter          |
    | Release_         |
    | ServiceComponent |
    +------------------+
    6 rows in set (0.00 sec)

### A Basic Service Builder Web App [](id=a-basic-service-builder-web-app)

The servlet in the following snippet implements a simple web app that uses the
contacts service.

    package web.sample;

    import java.io.IOException;
    import java.io.PrintWriter;
    import java.util.List;

    import javax.servlet.Servlet;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;
    import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

    import com.liferay.contacts.model.Entry;
    import com.liferay.contacts.service.EntryLocalService;
    import com.liferay.counter.kernel.service.CounterLocalService;
    import com.liferay.portal.kernel.dao.orm.DynamicQuery;
    import com.liferay.portal.kernel.dao.orm.QueryUtil;
    import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
    import com.liferay.portal.kernel.util.ParamUtil;
    import com.liferay.portal.kernel.util.Validator;

    @Component(
        immediate = true,
        property = {
            HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN + "=/*"
        },
        service = Servlet.class
    )
    public class SampleServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;

        @Override
        protected void service(
                HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            response.setContentType("text/html");

            PrintWriter writer = response.getWriter();

            String fullNameParameter = ParamUtil.getString(request, "fullName");

            if (Validator.isNull(fullNameParameter)) {
                writer.println("<h2>Hello You!</h2>");
                writer.println("Do you want to sign up for this thing?<br/>");
                writer.println("<form action='/join' method='post'>");
                writer.println("<input type='text' name='fullName' placeholder='Full Name'><br>");
                writer.println("<input type='text' name='emailAddress' placeholder='Email Address'><br>");
                writer.println("<input type='submit' value='Sign Up'><br>");
                writer.println("</form>");

                List<Entry> entries = _entryLocalService.getEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

                if (entries.isEmpty()) {
                    writer.println("I'm so lonely! :(<br/>");
                }
                else {
                    writer.println("Here's a list of others who've already signed up:<br/>");

                    for (Entry entry : _entryLocalService.getEntries(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
                        writer.println(String.format("%s &lt;%s><br/>", entry.getFullName(), entry.getEmailAddress()));
                    }
                }

                return;
            }

            String emailAddressParameter = ParamUtil.getString(request, "emailAddress");

            if (Validator.isNull(emailAddressParameter)) {
                writer.println(String.format("Ooops! %s, you forgot your emailAddress :(<br/>", fullNameParameter));
                writer.println("<a href='/'>Retry?</a>");

                return;
            }

            DynamicQuery dynamicQuery = _entryLocalService.dynamicQuery();

            dynamicQuery.add(RestrictionsFactoryUtil.eq("emailAddress", emailAddressParameter));

            long count = _entryLocalService.dynamicQueryCount(dynamicQuery);

            if (count > 0) {
                writer.println(String.format("Ooops! Someone already registered with the email address &lt;%s> :(<br/>", emailAddressParameter));
                writer.println("<a href='/'>Retry?</a>");

                return;
            }

            long entryId = _counterLocalService.increment();

            Entry entry = _entryLocalService.createEntry(entryId);

            entry.setFullName(fullNameParameter);
            entry.setEmailAddress(emailAddressParameter);

            _entryLocalService.updateEntry(entry);

            writer.println(String.format("Great! Thanks for signing up %s :D<br/>", fullNameParameter));
            writer.println("<a href='/'>Go Back!</a>");
        }

        @Reference
        private CounterLocalService _counterLocalService;

        @Reference
        private EntryLocalService _entryLocalService;

    }

Note how it uses OSGi Declarative Services to reference an instance of Liferay
Core's `CounterLocalService` and Contacts API's `EntryLocalService`.

## Related Topics [](id=related-topics)

[Arquillian Extension for Liferay Example](/develop/tutorials/-/knowledge_base/7-0/arquillian-extension-for-liferay-example)

[Unit Testing with JUnit](/develop/tutorials/-/knowledge_base/7-0/unit-testing-with-junit)
