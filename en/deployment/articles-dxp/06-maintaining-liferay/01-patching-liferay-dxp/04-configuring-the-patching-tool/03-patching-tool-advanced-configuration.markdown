---
header-id: patching-tool-advanced-configuration
---

# Patching Tool Advanced Configuration

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/maintaining-a-liferay-installation/reference/configuring-the-patching-tool.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

By default, the Patching Tool's configuration file called `default.properties`
is in the tool's folder.

A Patching Tool configuration file typically looks like this:

```properties
patching.mode=binary
war.path=../tomcat-9.0.17/webapps/ROOT/
global.lib.path=../tomcat-9.0.17/lib/ext/
liferay.home=../
```

The properties above (described fully in [Patching Tool Configuration Properties](/docs/7-2/deploy/-/knowledge_base/d/patching-tool-configuration-properties)) 
define the location of
[Liferay Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home),
the patching mode (binary or source), the path to where WAR files are deployed
in the app server, and the global library path. The tool's auto-discovery bases
the OSGi module framework paths on the Liferay Home. If, however, you changed
the OSGi module framework paths to something different than those under the
default folder `[Liferay Home]/osgi`, you must manually specify the following
properties: 

```properties
module.framework.core.path=path_to_modules_core_dir
module.framework.marketplace.path=path_to_modules_marketplace_dir
module.framework.modules.path=path_to_modules_modules_dir
module.framework.portal.path=path_to_modules_portal_dir
module.framework.static.path=path_to_modules_static_dir
```

Using auto-discovery and working with the default profile `default.properties`
is the easiest way to use the Patching Tool, and is great for smaller, single
server installations. But many @product@ installations serve millions of pages
per day, and the Patching Tool has been designed for this as well. So if you're
running a small, medium, or large cluster of @product@ machines, you can use the
Patching Tool profiles to manage patching for all of them. 

## Using Profiles with the Patching Tool

You can create profiles for multiple runtimes by running auto-discovery or
creating them manually. To auto-discover other runtimes, run the Patching Tool
with parameters like this: 

```bash
./patching-tool.sh [name of profile] auto-discovery [path/to/Liferay Home]
```

This runs the same discovery process, but on the path you specify. It writes the
profile information to a file called `[name of profile].properties`.
Alternatively, you can manually create profile property files in your
`patching-tool` folder. 

See [Patching Tool configuration properties](/docs/7-2/deploy/-/knowledge_base/d/patching-tool-configuration-properties) 
(profile properties) for a complete list of the available configuration 
properties. 

You can have as many profiles as you want and use the same Patching Tool to
patch all of them. This helps to keep all your installations in sync.
