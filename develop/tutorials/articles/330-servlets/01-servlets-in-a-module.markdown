# Servlets in a Module [](id=servlets-in-a-module)

Servlets or
[JAX-RS](/develop/tutorials/-/knowledge_base/7-1/jax-rs)
can be used to provide a lightweight web integration in some non-standard
remoting setup or provide a web endpoint to a browser client for a similar
reason. Servlets, rather than either a rest endpoint or a portlet, however let
you control application's entire UI experience. Liferay supports servlet based
applications and embeds HTTP Whiteboard for servlets. 

Here you'll examine a
[servlet sample](#servlet-sample)
and
[create your own servlet based application](#creating-a-servlet). 

## Servlet Sample [](id=servlet-sample)

The
[servlet sample](/develop/reference/-/knowledge_base/7-1/servlet),
uses HTTP Whiteboard to respond to requests at URLs that match the pattern
`http://localhost:8080/o/blade/servlet/*`.  

![Figure 1: If a users visits `http://localhost:8080/o/blade/servlet`, the servlet sample shows the message `Hello World`.](../../images/servlet-sample.png)

Here's the sample servlet class:

    package com.liferay.blade.samples.servlet;

    import java.io.IOException;

    import javax.servlet.Servlet;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;
    import org.osgi.service.log.LogService;
    
    /**
     * @author Liferay
     */
    @Component(
        immediate = true,
        property = {
            "osgi.http.whiteboard.context.path=/",
            "osgi.http.whiteboard.servlet.pattern=/blade/servlet/*"
        },
        service = Servlet.class
    )
    public class BladeServlet extends HttpServlet {

        @Override
        public void init() throws ServletException {
            _log.log(LogService.LOG_INFO, "BladeServlet init");

            super.init();
        }

        @Override
        protected void doGet(
                HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

            _log.log(LogService.LOG_INFO, "doGet");

            _writeSampleHTML(response);
        }

        /**
         * Dummy contents
         *
         * @return dummy contents string
         */
        private String _generateSampleHTML() {
            StringBuffer sb = new StringBuffer();

            sb.append("<html>");
            sb.append("<head><title>Sample HTML</title></head>");
            sb.append("<body>");
            sb.append("<h2>Hello World</h2>");
            sb.append("</body>");
            sb.append("</html>");

            return new String(sb);
        }

        /**
         * Write sample HTML
         *
         * @param resp
         */
        private void _writeSampleHTML(HttpServletResponse resp) {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);

            try {
                resp.getWriter().write(_generateSampleHTML());
            }
            catch (Exception e) {
                _log.log(LogService.LOG_WARNING, e.getMessage(), e);

                resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
            }
        }

        private static final long serialVersionUID = 1L;

        @Reference
        private LogService _log;

    }

The sample servlet class uses the `@Component` annotation to declare itself an
OSGi service of type `Servlet`. It uses OSGi HTTP Whiteboard to respond to
requests at URLs matching `http://localhost:8080/o/blade/servlet/*`. Since the
component's `osgi.http.whiteboard.context.path` and
`osgi.http.whiteboard.servlet.pattern` properties configure the servlet
mapping--there's no need to specify one in a `WEB-INF/web.xml` descriptor. 

The Portal web application's `WEB-INF/web.xml` defines Liferay's Module
Framework Servlet mapping: 

      <servlet-mapping>
          <servlet-name>Module Framework Servlet</servlet-name>
          <url-pattern>/o/*</url-pattern>
      </servlet-mapping>

The servlet mapping starts at URL pattern `/o/*`. Combined with the `@Component`
property `"osgi.http.whiteboard.servlet.pattern=/blade/servlet/*"`, the servlet
sample matches URL pattern `/o/blade/servlet/*`. 

To develop your own servlet, you can copy and modify all (or part) of the
[Servlet sample module project](/develop/reference/-/knowledge_base/7-1/servlet#where-is-this-sample)
or create a servlet in your own module. 

## Creating a Servlet [](id=creating-a-servlet)

Here's how to create your own servlet:

1.  Create a
    [module project](/develop/tutorials/-/knowledge_base/7-1/starting-module-development). 

2.  Add the necessary dependencies. Here they are for Gradle:

        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.4.0"
        compileOnly group: "org.osgi", name: "org.osgi.service.log", version: "1.4.0"

3.  Create a servlet class that extends `javax.servlet.http.HttpServlet`. 

4.  Add the following `@Component` annotation. 

        @Component(
            property = {
                "osgi.http.whiteboard.context.path=/",
                "osgi.http.whiteboard.servlet.pattern=/blade/servlet/*"
            },
            service = Servlet.class
        ) 

    `service = Servlet.class`: Makes the component an OSGi service of type `Servlet`. 

5.  Set the following `@Component` `property` values to specify a context path
    and servlet URL pattern:
    
    `"osgi.http.whiteboard.context.path=/"`: Sets the servlet's context. Replace
    the value with your servlet's context path.  

    `"osgi.http.whiteboard.servlet.pattern=/blade/servlet/*"`: Sets the
    servlet's mapping pattern. Replace the value with your servlet's pattern. 

6.  Override `HttpServlet` methods to implement your servlet's behavior.

7.  Deploy your module. 

Your servlet is up and running. You're well on your way to delivering custom
user experiences using servlets. 

## Related Topics [](id=related-topics)

[Servlet Sample](/develop/reference/-/knowledge_base/7-1/servlet) 

[Servlet Filters](/develop/tutorials/-/knowledge_base/7-1/servlet-filters) 

[JAX-RS](/develop/tutorials/-/knowledge_base/7-1/jax-rs) 

[Portlets](/develop/tutorials/-/knowledge_base/7-1/portlets) 
