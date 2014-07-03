# Developing OSGi Plugins for Liferay

OSGi (Open Services Gateway initiative) is a framework for developing modular
Java applications. OSGi implements a complete and dynamic component model. OSGi
components, also called bundles, can be remotely installed, started, stopped,
updated, and uninstalled without requiring the OSGi runtime to be restarted. To
facilitate dependency management, each OSGi bundle must specify certain details
about its Java packages and classes. Bundle life cycle management is implemented
via APIs that allow for remote downloading of management policies. The service
registry allows bundles to detect the addition of new services, or the removal
of services, and adapt accordingly. Liferay 6.2 includes an OSGi runtime in
which Liferay plugins that are packaged as OSGi bundles can run. Many Liferay
services are available to Liferay's OSGi runtime and can be invoked from Liferay
OSGi plugins. In this tutorial, we explain how to create, package, and deploy
custom OSGi plugins for Liferay. 

---

![Tip](../../images/tip-pen-paper.png) **Note:** Liferay 6.2's OSGi runtime is
experimental and unsupported. It should be considered a technology preview at
this time. 

---

## Creating Liferay OSGi Plugins

OSGi web application bundles are usually distributed as JAR files. They're
architecturally similar to standard web application archives. OSGi web
application bundles differ from standard web applications archives, however, in
that they must include additional metadata that's required for operating in an
OSGi framework. The OSGi specification does not require a specific file
extension for web application bundles: web application bundles typically have
a `.jar` extension but a `.war` extension is also possible.

To create an OSGi plugin for Liferay, you need a Liferay Plugins SDK. You can
download a Plugins SDK from
[www.liferay.com/downloads](http://www.liferay.com/downloads). Just make sure
that the Plugins SDK version matches your Liferay Portal version. Once you've
extracted the Plugins SDK, navigate to its `shared` folder. Create a folder for
your OSGi plugin project in the `shared` folder. The name of your project must
end in `-shared`. E.g., create a new folder called
`my-osgi-liferay-plugin-shared`. In your plugin project folder, create two
files:

- `bnd.bnd`
- `build.xml`

Bnd is a tool that makes it easy to create OSGi bundles. See
[www.aquite.biz/Bnd/Bnd](www.aqute.biz/Bnd/Bnd) for details about what you can
specify in your `bnd.bnd` file. Basically, your `bnd.bnd` file contains
instructions about dependency management and how to create your OSGi bundle's
JAR file.

Your `build.xml` allows your project to take advantage of the Liferay Plugins
SDK's OSGi plugin support. Add the following code to your `build.xml`, replacing
the value of the `<project>` element's `name` attribute with the name of your
plugin:

    <?xml version="1.0"?>

    <project name="my-osgi-liferay-plugin" basedir="." default="compile">
            <property name="plugin.version" value="1" />

            <import file="../../build-common-osgi-plugin.xml" />
    </project>

Your `bnd.bnd` and `build.xml` files constitute all the OSGi-specific
configuration that's required! With these files in place, you're ready to write
the rest of your plugin.

## Packaging Liferay OSGi Plugins

When you're ready to package your OSGi bundle, just run `ant jar` from your
plugin project's root folder (the one with the `bnd.bnd` and `build.mxl` files
that you created). The bnd `.jar` in the Plugins SDK reads the instructions in
your `bnd.bnd` file and creates all the files required by OSGi, including the
`META-INF/MANIFEST.MF`. Your plugin is packaged as a JAR file which is created
in the Plugins SDK's `dist` folder.

## Deploying Liferay OSGi Plugins

When you're ready to deploy your OSGi bundle, make sure that you have the
following properties set in your `build.[username].properties` file in your
Plugins SDK's root folder:

    liferay.home=[path-to-your-liferay-installation]
    auto.deploy.dir=${liferay.home}/deploy

Once these properties are set, run `ant deploy` from your plugin project's root
folder. This both packages your bundle and copies it to `[Liferay Home]/deploy`.
Liferay Portal scans the `[Liferay Home]/deploy` directory for OSGi bundles, as
well as for standard Liferay plugins. When it detects an OSGi bundle, it
automatically deploys the bundle to `[Liferay Home]/data/osgi/modules`. Check
your log for confirmation that Liferay found and deployed the bundle. You should
see a message like this:

18:46:50,653 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:204] Processing my-osgi-liferay-plugin-shared-6.2.0.1.jar
18:46:50,655 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][ModuleAutoDeployListener:63] Copied module for .../my-osgi-liferay-plugin-shared-6.2.0.1.jar
18:46:50,656 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][ModuleAutoDeployListener:69] Module for .../my-osgi-liferay-plugin-shared-6.2.0.1.jar copied successfully. Deployment will start in a few seconds.

Finally, check your `[Liferay Home]/data/osgi/modules` directory to make sure
that your bundle was successfully deployed. To customize Liferay Portal's module
framework, including the OSGi framework's auto deploy directories, please see
the Module Framework sections of Liferay's `portal.properties` file:
[Module Framework Properties](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Module%20Framework).
For example, you can set a comma-delimited list of directories to scan for
modules to deploy, specify how to often to scan the directories for changes,
etc. Remember not to make customizations to the `portal.properties` file itself
but to instead add customized properties to a `portal-ext.properties` file in
your Liferay Home directory. 

## Next Steps

[Plugin Management](/use/-/knowledge_base/plugin-management-lp-6-2-use-useportal)
