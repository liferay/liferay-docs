# JAX-WS and JAX-RS

Liferay supports 
[JAX-WS]() and 
[JAX-RS]() via the 
[Apache CXF]() 
implementation. Apps can publish JAX web services to the CXF endpoints defined 
in your Liferay instance. CXF endpoints are effectively context paths the JAX 
web services are deployed to, and accessible from. CXF endpoints can be created 
programmatically or in Liferay’s Control Panel. Likewise, depending on the type 
of JAX web service being published, an administrator must also configure an 
extender. Extenders let Liferay administrators specify where the services are 
deployed and whether they are augmented with handlers, providers, and so on. 
There are two types of extenders that an administrator can configure: 

1. SOAP Extenders: Required to publish JAX-WS web services
2. REST Extenders: Required to publish JAX-RS web services

SOAP extenders and REST extenders are subsystems that track the services the app 
developer registers in OSGi (those matching the provided 
[OSGi filters]()), 
and deploy them under the specified CXF endpoints. For example, if you create 
the CXF endpoints `/soap` and `/rest`, you could later create a REST extender 
for `/rest` that publishes REST applications, and a SOAP extender for `/soap` 
that publishes SOAP services. Of course, this is only a rough example; you can 
fine tune things to your liking.

Administrators can configure CXF endpoints, SOAP extenders, and REST extenders 
in Control Panel &rarr; Configuration &rarr; System Settings &rarr; Foundation. 
This tutorial shows how to do this, and discusses the settings.

## CXF Endpoints

To publish any kind of JAX web service, one or more CXF endpoints must be 
defined in your Liferay instance. By configuring a CXF endpoint in the Control 
Panel, the administrator can choose where an application’s JAX web services are 
deployed to, and can be reached from. Alternatively, developers can configure 
CXF endpoints programmatically by using Liferay’s configurator extender. First, 
you’ll learn how to do this in the Control Panel.

To configure a CXF endpoint, first go to Control Panel &rarr; 
Configuration &rarr; System Settings &rarr; Foundation. Then select CXF 
Endpoints from the table. If there are any existing CXF endpoints, they’re shown 
here. To add a new one, select the Add button on the lower right-hand corner. 
The form that appears lets you configure your new CXF endpoint by filling out 
the following fields:

- **Context path:** The path the JAX web services are deployed to on the Liferay 
  server. For example, if you define the context path `/web-services`, any 
  services deployed there are available at 
  `http://your-server:your-port/o/web-services`.

- **`AuthVerifier` properties:** Any properties defined here are passed as-is to 
  the `AuthVerifier` filter. See the 
  [`AuthVerifier` documentation]() 
  for more details. 

- **Required extensions:** CXF normally loads its default extension classes, but 
  in some cases they can be overridden to replace the default behavior. By 
  specifying custom extensions here via 
  [OSGi filters](), 
  Liferay will wait until those extensions are registered in the OSGi framework 
  before creating the CXF servlet and passing the extensions to the servlet. 
  Note that in most cases, you can leave this field blank: overriding extensions 
  isn’t a typical use case. 

## SOAP Extenders

For an app to deploy JAX-WS web services, the Liferay instance’s administrator 
must configure a SOAP extender. Each SOAP extender can deploy the services to 
one or more CXF endpoints, and can augment the services using a set of 
[JAX-WS handlers](). 

To configure a SOAP extender, first go to Control Panel &rarr; 
Configuration &rarr; System Settings &rarr; Foundation. Then select SOAP 
Extenders from the table. If there are any existing SOAP extenders, they’re 
shown here. To add a new one, select the Add button on the lower right-hand 
corner. The form that appears lets you configure a new SOAP extender by filling 
out the following fields:

- **Context paths:** Specify at least one CXF endpoint here. This is where the 
  services affected by this extender are deployed. In the preceding CXF endpoint 
  example, this would be `/web-services`. Note that you can specify more than 
  one CXF endpoint here.

- **jax.ws.handler.filters:** Here you can specify a set of OSGi filters that 
  select the services registered in the OSGi framework that implement JAX-WS 
  Handlers, and augment the JAX-WS services specified in the 
  *jax.ws.service.filters* property. The JAX-WS handlers specified here apply to 
  each service selected in this extender.

- **jax.ws.service.filters:** Here you can specify a set of OSGi filters that 
  select the services registered in the OSGi framework that are to be deployed 
  to the CXF endpoints. These OSGi services must be 
  [proper JAX-WS services]().

- **soap.descriptor.builder:** Leave this option empty to use JAX-WS annotations 
  to describe the SOAP service. To use a different way to describe the SOAP 
  service, you can provide an OSGi filter here that selects an implementation of 
  `com.liferay.portal.remote.soap.extender.SoapDescriptorBuilder`. 

## REST Extenders

REST extenders for JAX-RS services are analogous to SOAP extenders for JAX-WS 
services. To create JAX-RS services that can work across different JAX-RS 
implementations, it’s necessary to provide an implementation of 
`javax.ws.rs.core.Application` to the OSGi framework. You can do this by 
registering an instance of this implementation as an OSGi service via 
`BundleContext` or the Declarative Services `@Component` annotation. The JAX-RS 
application encompasses the services that represent JAX-RS endpoints and the 
services that represent JAX-RS providers. By specifying OSGi filters in a REST 
extender, you can also dynamically add endpoints or JAX-RS providers to a JAX-RS application. 

To configure a REST extender, first go to Control Panel &rarr; 
Configuration &rarr; System Settings &rarr; Foundation. Then select *REST 
Extender* from the table. If there are any existing REST extenders, they’re 
shown here. To add a new one, select the *Add* button on the lower right-hand 
corner. The form that appears lets you configure a new REST extender by filling 
out the following fields:

- **Context paths:** Specify at least one CXF endpoint here. This is where the 
  services affected by this extender are deployed. In the preceding CXF endpoint 
  example, this would be `/web-services`. Note that you can specify more than 
  one CXF endpoint here. This works the same way as in SOAP Extenders. 

- **jax.rs.application.filters:** Here you can specify a set of OSGi filters 
  that select services registered in the OSGi framework that implement 
  `javax.ws.rs.core.Application`. These JAX-RS applications are deployed to the 
  CXF endpoints specified in the *Context paths* property.

- **jsx.rs.provider.filters:** Here you can specify a set of OSGi filters that 
  select services registered in the OSGi framework that implement any of the 
  interfaces supported by JAX-RS for providers. These JAX-RS providers are added 
  to the JAX-RS application as if they had been returned by the 
  `getSingletons()` method of `javax.ws.rs.core.Application`. The following 
  links list some of the supported JAX-RS providers: 
        - [https://jersey.java.net/documentation/latest/message-body-workers.html](https://jersey.java.net/documentation/latest/message-body-workers.html) 
        - [https://jersey.java.net/documentation/latest/filters-and-interceptors.html](https://jersey.java.net/documentation/latest/filters-and-interceptors.html)

- **jax.rs.service.filters:** Here you can specify a set of OSGi filters that 
  selects services registered in the OSGi framework that are valid JAX-RS 
  endpoints. These endpoints are added to the JAX-RS application just as if they 
  had been returned by the `getSingletons()` method of 
  `javax.ws.rs.core.Application`. 

## Configuring Endpoints and Extenders Programmatically

To configure endpoints or extenders programmatically, you must use Liferay’s 
configurator extender. The configurator extender provides a way for OSGi modules 
to deploy default configuration values. Modules that use the configurator 
extender must provide a `ConfigurationPath` header that points to the 
configuration files’ location inside the module. For example, the following code 
sets the `ConfigurationPath` to `src/main/resources/configuration`:

    Bundle-Name: Liferay Export Import Service JAX-WS
    Bundle-SymbolicName: com.liferay.exportimport.service.jaxws
    Bundle-Version: 1.0.0
    ConfigurationPath: /configuration
    Include-Resource: configuration=src/main/resources/configuration
    Liferay-Releng-Module-Group-Description:
    Liferay-Releng-Module-Group-Title: Data Management

There are two different configuration types in 
[the OSGi `ConfigurationAdmin`](https://osgi.org/javadoc/r4v42/org/osgi/service/cm/ConfigurationAdmin.html): 
single, and factory. Factory configurations can have several configuration 
instances per factory name. The examples here use factory configurations. You 
must provide a factory configuration’s default values in a `*.properties` file. 
In the properties file, use a suffix on the end of the configuration and then 
provide your settings. For example, the following code uses the `-staging` 
suffix on the PID (persistent identifier) and creates a CXF endpoint at the 
context path `/staging-ws`:

com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-staging:

contextPath=/staging-ws

As another example, the following code uses the suffix `-stagingjaxws` on the PID and creates a SOAP extender at the context path `/staging-ws`. This code also includes settings for the configuration fields `jaxWsHandlerFilterStrings` and `jaxWsServiceFilterStrings`.

com.liferay.portal.remote.soap.extender.configuration.SoapExtenderConfiguration-stagingjaxws:

contextPaths=/staging-ws
jaxWsHandlerFilterStrings=(staging.jax.ws.handler=true)
jaxWsServiceFilterStrings=(staging.jax.ws.service=true)

You must then use these configuration fields in the configuration class. For example, the following `SoapExtenderConfiguration` interface contains the configuration fields `contextPaths`, `jaxWsHandlerFilterStrings`, and `jaxWsServiceFilterStrings`: 

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

## Publishing JAX-WS Web Services

To publish JAX-WS web services via SOAP in a Liferay 7 module, annotate the class and its methods with standard JAX-WS annotations, and then register it as a service in the OSGi framework. For example, the following example class uses the `@WebService` annotation for the class, and `@WebMethod` annotations for its methods. You must also set the `jaxws` property to `true` in the class’s OSGi `@Component` annotation:

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
	public int substract(int a, int b) {
		return a - b;
	}

	@WebMethod
	public int sum(int a, int b) {
		return a + b;
	}

}

You should also make sure that you include `org.osgi.core` and `org.osgi.service.component.annotations` as dependencies to your project.

Publishing JAX-RS Web Services

You can publish JAX-RS web services in a Liferay module the same way you would outside of Liferay. You must also, however, register the class in the OSGi framework. Note that the services must match the OSGi filters provided in the respective extenders. This is how the instances that become services are selected. There’s no classpath scanning or other automatic mechanism at work here: it’s the developer’s responsibility to register the services in the OSGi framework.

The following example registers an OSGi component that publishes a JAX-RS web service at `/application-path/hello`. Get requests to this web service return a simple “Hello!”: 

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