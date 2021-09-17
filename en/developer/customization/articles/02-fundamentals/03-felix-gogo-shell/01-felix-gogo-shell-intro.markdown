---
header-id: using-the-felix-gogo-shell
---

# Using the Felix Gogo Shell

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120"> This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/liferay-internals/fundamentals/using-the-gogo-shell.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

The Gogo shell provides a way to interact with @product@'s module framework. You
can

- dynamically install/uninstall bundles
- examine package dependencies
- examine extension points
- list service references
- etc.

There are two ways you can access the Gogo shell.

The recommended way to access the Gogo shell for a production environment is
through the Control Panel. Accessing it there is the most secure way to use the
Gogo shell. You can set permissions in your @product@ instance to only give
certain people access to it. The Gogo shell is extremely powerful and should
only be given to trusted admins, as you can manipulate the platform's core
functionality. You can access the Gogo shell in the Control Panel by navigating
to *Configuration* &rarr; *Gogo Shell*.

You can also interact with @product@'s module framework via a local telnet
session. This is only recommended when you're developing your @product@
instance. This is not recommended for production environments. 
 
To open the Gogo shell via telnet, execute the following command: 

```bash
telnet localhost 11311
```

Running this command requires a local running instance of @product@ and your
machine's telnet command line utilities enabled. You must also have
[Developer Mode enabled](/docs/7-2/frameworks/-/knowledge_base/f/using-developer-mode-with-themes#enabling-developer-mode-manually).

To disconnect the session, execute the `disconnect` command. Avoid using the
following commands, which stop the OSGi framework:

- `close`
- `exit`
- `shutdown`

If you have [Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli)
installed and the telnet capability enabled, you can run the Gogo shell via
Blade command too:

```bash
blade sh <gogoShellCommand>
```

Here are some useful Gogo shell commands:

`b [BUNDLE_ID]`: lists information about a specific bundle including the
bundle's symbolic name, bundle ID, data root, registered (provided) and used
services, imported and exported packages, and more

`diag [BUNDLE_ID]`: lists information about why the specified bundle is not
working (e.g., unresolved dependencies, etc.)

`headers [BUNDLE_ID]`: lists metadata about the bundle from the bundle's
`MANIFEST.MF` file

`help`: lists all the available Gogo shell commands. Notice that each command
has two parts to its name, separated by a colon. For example, the full name of
the `help` command is `felix:help`. The first part is the command scope while
the second part is the command function. The scope allows commands with the same
name to be disambiguated. E.g., scope allows the `felix:refresh` command to be
distinguished from the `equinox:refresh` command.

`help [COMMAND_NAME]`: lists information about a specific command including a
description of the command, the scope of the command, and information about any
flags or parameters that can be supplied when invoking the command.

`inspect capability service [BUNDLE_ID]`: lists services exposed by a bundle

`install [PATH_TO_JAR_FILE]`: installs the specified bundle into Liferay's
module framework

`lb`: lists all of the bundles installed in Liferay's module framework. Use
the `-s` flag to list the bundles using the bundles' symbolic names.

`packages [PACKAGE_NAME]`: lists all of the named package's dependencies 

`scr:list`: lists all of the components registered in the module framework
(*scr* stands for service component runtime)

`scr:info [COMPONENT_NAME]`: lists information about a specific component
including the component's description, services, properties, configuration,
references, and more.

`services`: lists all of the services that have been registered in Liferay's
module framework

`start [BUNDLE_ID]`: starts the specified bundle

`stop [BUNDLE_ID]`: stops the specified bundle

`uninstall [BUNDLE_ID]`: uninstalls the specified bundle from Liferay's module
framework. This does not remove the specified bundle from Liferay's module
framework; it's hidden from Gogo's `lb` command, but is still present. Adding
a new version of the uninstalled bundle, therefore, will not reinstall it; it
will update the currently hidden uninstalled version. To remove a
bundle from Liferay's module framework permanently, manually delete it from the
`LIFERAY_HOME/osgi` folder. For more information on the `uninstall` command, see
OSGi's
[uninstall](https://osgi.org/javadoc/r6/core/org/osgi/framework/Bundle.html#uninstall\(\))
documentation.

For more information about the Gogo shell, visit
[Apache's official documentation](http://felix.apache.org/documentation/subprojects/apache-felix-gogo.html).
