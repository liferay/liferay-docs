# Factory Configurations [](id=factory-configurations)

Configurations supporting multiple entries are called *factory configurations*. 

+$$$

**Factory Configuration Example:** 
JAX-WS and JAX-RS web
services<!--(/develop/tutorials/-/knowledge_base/7-1/jax-ws-and-jax-rs)--> are
supported. These services must use a CXF Endpoint<!--
(/develop/tutorials/-/knowledge_base/7-1/jax-ws-and-jax-rs#cxf-endpoints)-->,
which is a context path where the web services are deployed and accessed.
Endpoints can be created via factory configuration by navigating to the CXF
Endpoints entry in System Settings (System Settings &rarr; Platform &rarr; Web
API &rarr; CXF Endpoints). Click *ADD*, enter the desired configuration values,
then repeat the process to add as many CXF Endpoint configurations as needed.
Creating CXF Endpoint configurations also creates CXF Endpoints themselves.
This is how factory configurations work. 

$$$

If a service is meant to support factory configurations, its System Settings
entry has an ADD button. 

![Figure 1: If a System Settings entry has an ADD button, it's suitable for factory configurations.](../../../../images/factory-configuration-entry.png)

As with single-instance configurations, you can set factory configurations in 
the System Settings interface (as described in the example above) or via 
configuration files. Name a standard single-instance configuration file like
this: 

    my.service.ServiceConfiguration.config

If your service supports factory configurations, use the convention of calling
the configuration's first instance `-default.config`: 

    my.service.ServiceConfiguration-default.config

The next instance contains a unique *subname* (something other than *default*).
It's good practice to use a descriptive name: 

    my.service.ServiceConfiguration-port9080.config

To follow the CXF Endpoints example described above, if Liferay's developers had
shipped an initial CXF Endpoint `.config` file with @product@, it would have
been named this: 

    com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-default.config

If this `-default.config` configuration specifies a context path for *REST* web 
services, and you create another endpoint with a different context path for 
*SOAP* web services, your second configuration file could be named: 

    com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-soap.config

+$$$

**Note:** Some System Settings entries (like the CXF Endpoints entry) don't ship
with a configuration file, so anything you create is the first occurrence.
However, if you configure one and export it to obtain the `.config` file, it
doesn't use the `-default.config` naming convention. Instead it's given a
guaranteed unique identifier for its subname, like this:

    com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration-a6f67e48-6dca-49c6-bf6b-8fd5e6016b2d.config

This guarantees that the file has a unique name. If you're exporting the 
configuration file for deployment in a separate system, you can rename 
the exported file to use a more descriptive subname. If you rename the file and 
deploy it to the same system it was exported from, the new subname marks it as 
an entirely new configuration. You'll end up with an additional configuration 
instance in this case, not just a renamed one. 

$$$

+$$$

**Warning::** For configuration entries supporting factory configurations,
omitting the subname from a `.config` file's name causes System Settings to
disallow adding new entries for the configuration (only the configuration entry
targeted by this `.config` file). This is caused by a known bug. See
[LPS-76352](https://issues.liferay.com/browse/LPS-76352) 
for more information. Once an improperly named configuration file is deployed,
you can't add any entries for the configuration in question from its System
Settings entry. For example, if you deploy the following file to configure a CXF
Endpoint, not only does this not add a CXF Endpoint, it also prevents you from
adding any CXF Endpoints via System Settings: 

    com.liferay.portal.remote.cxf.common.configuration.CXFEndpointPublisherConfiguration.config

Deploying an erroneous (lacking a subname) `.config` file doesn't disable
anything permanently. Just rename the file using the proper convention described
above or remove it entirely and start over. 

$$$

In many cases, configuration files can be used to force a factory configuration
scenario, but not all configurations can be used this way. It's best to stick to
the intended use cases. Use System Settings as described above to determine if
using factory configurations is a good idea. If not, stick to the single
occurrence mode of configuration (specifying only one configuration file for the
service). 
