# JAX-WS and JAX-RS [](id=jax-ws-and-jax-rs)

Liferay supports 
[JAX-WS](https://en.wikipedia.org/wiki/Java_API_for_XML_Web_Services) and 
[JAX-RS](https://en.wikipedia.org/wiki/Java_API_for_RESTful_Web_Services) 
via the 
[Apache CXF](http://cxf.apache.org/) 
implementation. Apps can publish JAX web services to the CXF endpoints defined 
in your Liferay instance. CXF endpoints are effectively context paths the JAX 
web services are deployed to and accessible from. To publish any kind of JAX 
web service, one or more CXF endpoints must be defined in your Liferay instance. 
To access JAX web services, an *extender* must also be configured in your 
Liferay instance. Extenders specify where the services are deployed and whether 
they are augmented with handlers, providers, and so on. There are two types of 
extenders: 

1. **SOAP Extenders:** Required to publish JAX-WS web services. Each SOAP 
   extender can deploy the services to one or more CXF endpoints and can use a 
   set of 
   [JAX-WS handlers](https://jax-ws.java.net/articles/handlers_introduction.html) 
   to augment the services. 

2. **REST Extenders:** Required to publish JAX-RS web services. REST extenders 
   for JAX-RS services are analogous to SOAP extenders for JAX-WS services. To 
   create JAX-RS services that can work across different JAX-RS implementations, 
   you must provide an implementation of `javax.ws.rs.core.Application` to the 
   OSGi framework. You can do this by registering an instance of this 
   implementation as an OSGi service via `BundleContext` or the Declarative 
   Services `@Component` annotation. The JAX-RS application encompasses the 
   services that represent JAX-RS endpoints and the services that represent 
   JAX-RS providers. By specifying OSGi filters in a REST extender, you can also 
   dynamically add endpoints or JAX-RS providers to a JAX-RS application. 

SOAP extenders and REST extenders are subsystems that track the services the app 
developer registers in OSGi (those matching the provided 
[OSGi filters](https://osgi.org/javadoc/r6/core/org/osgi/framework/Filter.html)), 
and deploy them under the specified CXF endpoints. For example, if you create 
the CXF endpoints `/soap` and `/rest`, you could later create a REST extender 
for `/rest` that publishes REST applications, and a SOAP extender for `/soap` 
that publishes SOAP services. Of course, this is only a rough example: you can 
fine tune things to your liking. 

CXF endpoints and both types of extenders can be created programmatically or 
with Liferay's Control Panel. This tutorial shows you how to do both, and then 
shows you how to publish JAX-WS and JAX-RS web services. The following topics 
are covered:

- [Configuring Endpoints and Extenders with the Control Panel](/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs#configuring-endpoints-and-extenders-with-the-control-panel)

    - [CXF Endpoints](/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs#cxf-endpoints)

    - [SOAP Extenders](/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs#soap-extenders)

    - [REST Extenders](/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs#rest-extenders)

- [Configuring Endpoints and Extenders Programmatically](/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs#configuring-endpoints-and-extenders-programmatically)

- [Publishing JAX-WS Web Services](/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs#publishing-jax-ws-web-services)

- [Publishing JAX-RS Web Services](/develop/tutorials/-/knowledge_base/7-0/jax-ws-and-jax-rs#publishing-jax-rs-web-services)

## Configuring Endpoints and Extenders with the Control Panel [](id=configuring-endpoints-and-extenders-with-the-control-panel)

Liferay's Control Panel lets administrators configure endpoints and extenders 
for JAX web services. Note that you must be an administrator in your Liferay
instance to access the settings here. First, you'll learn how to create CXF
endpoints. 

### CXF Endpoints [](id=cxf-endpoints)

To configure a CXF endpoint with the Control Panel, first go to *Control Panel*
&rarr; *Configuration* &rarr; *System Settings* &rarr; *Foundation*. Then select
*CXF Endpoints* from the table. If there are any existing CXF endpoints, they're
shown here. To add a new one, select the *Add* button
(![Add](../../images/icon-add.png)) in the lower right-hand corner. The form 
that appears lets you configure a new CXF endpoint by filling out these fields: 

- **Context path:** The path the JAX web services are deployed to on the Liferay 
  server. For example, if you define the context path `/web-services`, any 
  services deployed there are available at 
  `http://your-server:your-port/o/web-services`. 

- **`AuthVerifier` properties:** Any properties defined here are passed as-is to 
  the `AuthVerifier` filter. See the 
  [`AuthVerifier` documentation](https://dev.liferay.com/discover/deployment/-/knowledge_base/7-0/authentication-verifiers) 
  for more details. 

- **Required extensions:** CXF normally loads its default extension classes, but 
  in some cases you can override them to replace the default behavior. In most
  cases, you can leave this field blank: overriding extensions isn't common. By
  specifying custom extensions here via [OSGi filters](https://osgi.org/javadoc/r6/core/org/osgi/framework/Filter.html), 
  Liferay waits until those extensions are registered in the OSGi framework 
  before creating the CXF servlet and passing the extensions to the servlet. 

![Figure 1: Fill out this form to create a CXF endpoint.](../../images/cxf-endpoint-form.png)

Next, you'll learn how to use the Control Panel to create SOAP extenders for 
JAX-WS web services. 

### SOAP Extenders [](id=soap-extenders)

For an app to deploy JAX-WS web services, you must configure a SOAP extender. To 
configure a SOAP extender with the Control Panel, first go to 
*Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; 
*Foundation*. Then select *SOAP Extenders* from the table. If there are any 
existing SOAP extenders, they're shown here. To add a new one, select the *Add* 
button 
(![Add](../../images/icon-add.png)) in the lower right-hand corner. The form 
that appears lets you configure a new SOAP extender by filling out these fields: 

- **Context paths:** Specify at least one CXF endpoint here. This is where the 
  services affected by this extender are deployed. In the preceding CXF endpoint 
  example, this would be `/web-services`. Note that you can specify more than 
  one CXF endpoint here. 

- **jax.ws.handler.filters:** Here you can specify a set of 
  [OSGi filters](https://osgi.org/javadoc/r6/core/org/osgi/framework/Filter.html) 
  that select certain services registered in the OSGi framework. The selected 
  services should implement JAX-WS handlers and augment the JAX-WS services 
  specified in the *jax.ws.service.filters* property. These JAX-WS handlers 
  apply to each service selected in this extender. 

- **jax.ws.service.filters:** Here you can specify a set of OSGi filters that 
  select the services registered in the OSGi framework that will be deployed 
  to the CXF endpoints. These OSGi services must be 
  [proper JAX-WS services](https://docs.oracle.com/javaee/7/tutorial/jaxws001.htm). 

- **soap.descriptor.builder:** Leave this option empty to use JAX-WS annotations 
  to describe the SOAP service. To use a different way to describe the SOAP 
  service, you can provide an OSGi filter here that selects an implementation of 
  `com.liferay.portal.remote.soap.extender.SoapDescriptorBuilder`. 

![Figure 2: Fill out this form to create a SOAP extender.](../../images/soap-extenders-form.png)

Next, you'll learn how to use the Control Panel to create REST extenders for 
JAX-RS web services. 

### REST Extenders [](id=rest-extenders)

To configure a REST extender with the Control Panel, first go to 
*Control Panel* &rarr; *Configuration* &rarr; *System Settings* &rarr; 
*Foundation*. Then select *REST Extender* from the table. If there are any 
existing REST extenders, they're shown here. To add a new one, select the *Add* 
button 
(![Add](../../images/icon-add.png)) in the lower right-hand corner. The form 
that appears lets you configure a new REST extender by filling out these fields: 

- **Context paths:** Specify at least one CXF endpoint here. This is where the 
  services affected by this extender are deployed. In the preceding CXF endpoint 
  example, this would be `/web-services`. Note that you can specify more than 
  one CXF endpoint here. This works the same way as the *Context paths* setting 
  in SOAP Extenders. 

- **jax.rs.application.filters:** Here you can specify a set of OSGi filters 
  that select services that implement `javax.ws.rs.core.Application`. These
  JAX-RS applications are deployed to the CXF endpoints specified in the
  *Context paths* property. 

- **jsx.rs.provider.filters:** Here you can specify a set of OSGi filters that 
  select services registered in the OSGi framework. The selected services should 
  implement any of the interfaces supported by JAX-RS for providers. These 
  JAX-RS providers are added to the JAX-RS application as if they had been 
  returned by the `getSingletons()` method of `javax.ws.rs.core.Application`. 
  The following links list some of the supported JAX-RS providers: 

    - [JAX-RS Entity Providers](https://jersey.java.net/documentation/latest/message-body-workers.html) 
    - [Filters and Interceptors](https://jersey.java.net/documentation/latest/filters-and-interceptors.html) 

- **jax.rs.service.filters:** Here you can specify a set of OSGi filters that 
  selects services registered in the OSGi framework that are valid JAX-RS 
  endpoints. These endpoints are added to the JAX-RS application as if they 
  had been returned by the `getSingletons()` method of 
  `javax.ws.rs.core.Application`. 

![Figure 3: Fill out this form to create a REST extender.](../../images/rest-extender-form.png)

Next, you'll learn how to configure endpoints and extenders programmatically.

## Configuring Endpoints and Extenders Programmatically [](id=configuring-endpoints-and-extenders-programmatically)

To configure endpoints or extenders programmatically, you must use Liferay's 
configurator extender. The configurator extender provides a way for OSGi modules 
to deploy default configuration values. Modules that use the configurator 
extender must provide a `ConfigurationPath` header that points to the 
configuration files' location inside the module. For example, the following
configuration sets the `ConfigurationPath` to
`src/main/resources/configuration`:

    Bundle-Name: Liferay Export Import Service JAX-WS
    Bundle-SymbolicName: com.liferay.exportimport.service.jaxws
    Bundle-Version: 1.0.0
    Liferay-Configuration-Path: /configuration
    Include-Resource: configuration=src/main/resources/configuration
    Liferay-Releng-Module-Group-Description:
    Liferay-Releng-Module-Group-Title: Data Management

+$$$

**Note:** If you're using any version before Liferay CE GA4 or Liferay DXP
Fixpack 22, the `Liferay-Configuration-Path` directive above is
`Configuration-Path`. As of 
[LPS-62571](https://issues.liferay.com/browse/LPS-62571), Liferay-specific Bnd
instructions are prefixed with `Liferay` to avoid conflicts. 

$$$

There are two different configuration types in 
[OSGi's `ConfigurationAdmin`](https://osgi.org/javadoc/r4v42/org/osgi/service/cm/ConfigurationAdmin.html): 
single, and factory. Factory configurations can have several configuration 
instances per factory name. @product@ uses factory configurations. You 
must provide a factory configuration's default values in a `*.properties` file. 
In this properties file, use a suffix on the end of the PID (persistent 
identifier) and then provide your settings. For example, the following code uses 
the `-staging` suffix on the PID and creates a CXF endpoint at the context path 
`/staging-ws`: 

    com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-staging.properties:

    contextPath=/staging-ws

As another example, the following code uses the suffix `-stagingjaxws` on the 
PID and creates a SOAP extender at the context path `/staging-ws`. This code 
also includes settings for the configuration fields `jaxWsHandlerFilterStrings` 
and `jaxWsServiceFilterStrings`: 

    com.liferay.portal.remote.soap.extender.configuration.SoapExtenderConfiguration-stagingjaxws.properties:

    contextPaths=/staging-ws
    jaxWsHandlerFilterStrings=(staging.jax.ws.handler=true)
    jaxWsServiceFilterStrings=(staging.jax.ws.service=true)

You must then use these configuration fields in the configuration class. For 
example, the `SoapExtenderConfiguration` interface below contains the 
configuration fields `contextPaths`, `jaxWsHandlerFilterStrings`, and 
`jaxWsServiceFilterStrings`: 

    @ExtendedObjectClassDefinition(
	    category = "foundation", factoryInstanceLabelAttribute = "contextPaths"
    )
    @Meta.OCD(
        factory = true,
        id = "com.liferay.portal.remote.soap.extender.configuration.SoapExtenderConfiguration",
        localization = "content/Language", name = "soap.extender.configuration.name"
    )
    public interface SoapExtenderConfiguration {

        @Meta.AD(required = false)
        public String[] contextPaths();

        @Meta.AD(name = "jax.ws.handler.filters", required = false)
        public String[] jaxWsHandlerFilterStrings();

        @Meta.AD(name = "jax.ws.service.filters", required = false)
        public String[] jaxWsServiceFilterStrings();

        @Meta.AD(name = "soap.descriptor.builder", required = false)
        public String soapDescriptorBuilderFilter();

    }

You can use similar techniques to create REST extenders. For example, see 
[the `RestExtenderConfiguration` interface](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/portal-remote/portal-remote-rest-extender/src/main/java/com/liferay/portal/remote/rest/extender/configuration/RestExtenderConfiguration.java) 
in Liferay's source code. 

Next, you'll learn how to publish JAX-WS web services. 

## Publishing JAX-WS Web Services [](id=publishing-jax-ws-web-services)

To publish JAX-WS web services via SOAP in a @product-ver@ module, annotate the 
class and its methods with standard JAX-WS annotations, and then register it as 
a service in the OSGi framework. For example, the following class uses the 
`@WebService` annotation for the class and `@WebMethod` annotations for its 
methods. You must also set the `jaxws` property to `true` in the OSGi 
`@Component` annotation: 

    import javax.jws.WebMethod;
    import javax.jws.WebService;

    import org.osgi.service.component.annotations.Component;

    @Component(
        immediate = true, property = "jaxws=true", service = Calculator.class
    )
    @WebService
    public class Calculator {

        @WebMethod
        public int divide(int a, int b) {
            return a / b;
        }

        @WebMethod
        public int multiply(int a, int b) {
            return a * b;
        }

        @WebMethod
        public int subtract(int a, int b) {
            return a - b;
        }

        @WebMethod
        public int sum(int a, int b) {
            return a + b;
        }

    }

You should also make sure that you include `org.osgi.core` and 
`org.osgi.service.component.annotations` as dependencies to your project. 

Next, you'll learn how to publish JAX-RS web services. 

## Publishing JAX-RS Web Services [](id=publishing-jax-rs-web-services)

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

## Related Topics [](id=related-topics)

[Service Builder Web Services](/develop/tutorials/-/knowledge_base/7-0/service-builder-web-services)
