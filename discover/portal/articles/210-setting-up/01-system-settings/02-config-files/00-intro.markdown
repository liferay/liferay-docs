# Understanding System Configuration Files [](id=understanding-system-configuration-files)

The [System Settings application](/discover/portal/-/knowledge_base/7-1/system-settings) 
is convenient for making system-scoped configuration changes and setting default
configurations for other
[scopes](/discover/portal/-/knowledge_base/7-1/setting-up#configuration-scope).
But there's another supported configuration approach: configuration files. You
can use configuration files to transfer configurations from pre-production
systems to production systems, or between any other @product@ systems. Sometimes
developers choose to distribute the default configuration for their applications
via configuration file. Whatever the reason, configuration files offer another
configuration approach.

Configuration files use the `.config` property value format defined by the 
[Apache Felix Configuration Admin framework](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html).

+$$$

**Note:** The `.cfg` file format is common in OSGi environments, and it's a
supported format, but `.config` files are preferable. You can specify a property
value's type and have multi-valued properties. The syntax described in these
articles is for `.config` files. 

$$$


