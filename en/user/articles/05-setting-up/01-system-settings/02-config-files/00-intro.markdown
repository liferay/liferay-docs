---
header-id: understanding-system-configuration-files
---

# Understanding System Configuration Files

[TOC levels=1-4]

The [System Settings
application](/docs/7-2/user/-/knowledge_base/u/system-settings) is convenient
for making system-scoped configuration changes and setting default
configurations for other
[scopes](/docs/7-2/user/-/knowledge_base/u/setting-up#configuration-scope). But
there's another supported configuration approach: configuration files. You can
use configuration files to transfer configurations from pre-production systems
to production systems, or between any other @product@ systems. Sometimes
developers choose to distribute the default configuration for their applications
via configuration file. Whatever the reason, configuration files offer another
configuration approach.

Configuration files use the `.config` property value format defined by the
[Apache Felix Configuration Admin
framework](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html). 

| **Important:** Content generated using templates (e.g., FreeMarker templates and
| Application Display Templates) is cached. Cached content might not reflect
| configuration changes until the cache is invalidated (cleared). The
| [Server Administration &rarr; Resources tab](/docs/7-2/user/-/knowledge_base/u/server-administration-resources)
| provides cache clearing options.
| 
| **Note:** The `.cfg` file format is common in OSGi environments, and it's a
| supported format, but `.config` files are preferable since they allow specifying
| a property value's type, and allow multi-valued properties. The syntax described
| in these articles is for `.config` files.
