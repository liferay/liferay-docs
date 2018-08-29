# JAX-WS and JAX-RS



## Publishing JAX-RS Web Services

You can publish JAX-RS web services in a Liferay module the same way you would 
outside of Liferay. You must also, however, register the class in the OSGi 
framework. Note that the services must match the OSGi filters provided in the 
respective extenders. This is how the instances that become services are 
selected. There's no classpath scanning or other automatic mechanism at work 
here: it's the developer's responsibility to register the services in the OSGi 
framework. 

The following example registers an OSGi component that publishes a JAX-RS web 
service at `/application-path/hello`. Get requests to this web service return a 
simple *"Hello!"*: 

    import org.osgi.service.component.annotations.Component;

    import javax.ws.rs.ApplicationPath;
    import javax.ws.rs.GET;
    import javax.ws.rs.Path;
    import javax.ws.rs.Produces;
    import javax.ws.rs.core.Application;
    import java.util.Collections;
    import java.util.Set;

    @Component(immediate = true, service = Application.class)
    @ApplicationPath("/application-path")
    public class RestEndpoint extends Application {

        public Set<Object> getSingletons() {
            return Collections.<Object>singleton(this);
        }

        @GET
        @Path("/hello")
        @Produces("text/text")
        public String sayHello() {
            return "Hello!";
        }
    }

Nice work! Now you know how JAX-WS and JAX-RS works in Liferay. 

## Related Topics

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-1/service-builder-web-services)
