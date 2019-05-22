# Developing an Ext Plugin [](id=developing-an-ext-plugin)

An Ext plugin changes @product@ itself when the plugin is deployed; it's not a
separate component that you can easily remove at any time. For this reason, the
Ext plugin development process is different from other project types. It's
important to remember that once an Ext plugin is deployed, some of its files are
copied *inside* the Liferay installation; the only way to remove the changes is
by *redeploying* an unmodified @product@ application. You're also responsible
for checking that patches and fix packs do not conflict with your Ext plugin.
Additionally, Ext plugins aren't hot deployable. To deploy an	 Ext plugin, you
must restart your server. Additional steps are also required to	deploy or
redeploy to production systems.

It's strongly recommended to only develop/deploy one Ext plugin. This means that
all your customizations should live inside one Ext plugin. Liferay Workspace
does not check for conflicts among multiple Ext plugins stored in the `/ext`
folder, so do **not** develop/deploy multiple Ext plugins at once.

You can deploy and redeploy your Ext plugin during your development phase.
Redeployment involves manually removing the Ext plugin and *cleaning* your
application server (i.e., remove it ); this is recommended so any changes made
to the Ext plugin during development are properly applied, and files removed
from your plugin by previous changes aren't left behind in the @product@
application. Because of this added complexity, you should use another plugin
type to accomplish your goals whenever possible. 

Now it's time to set up the build environment. 

## Set Up the Build Environment [](id=set-up-the-build-environment)

If you're leveraging
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace),
you should create/develop your Ext module project in the `/ext` folder
(default); you can specify a different Ext folder name in workspace's
`gradle.properties` by adding

    liferay.workspace.ext.dir=EXT_DIR

If you are developing an Ext module project in standalone mode (not associated
with Liferay Workspace), you must declare the Ext Gradle plugin in your
`build.gradle`:

    apply plugin: 'com.liferay.ext.plugin'

Next you'll explore an Ext plugin's advanced configuration files.

## Using Advanced Configuration Files [](id=using-advanced-configuration-files)

@product@ uses several internal configuration files for its own architecture; in
addition, there are configuration files for the libraries and frameworks
@product@ depends on, like Struts and Spring. Configuration could be
accomplished using fewer files with more properties in each, but maintenance and
use is made easier by splitting up the configuration properties into several
files. For advanced customization needs, it may be useful to override the
configuration specified in multiple configuration files. @product@ provides a
clean way to do this from an Ext plugin without modifying the original files. 

All the configuration files in @product@ are listed below by their path in an
Ext plugin folder. Here are descriptions of what each file is for and the path
to the original file in @product@: 

- `extImpl/resources/META-INF/ext-model-hints.xml`
    - **Description:** Overrides the default properties of the fields of
      the data models used by @product@'s core portlets. These properties
      determine how the form fields for each model are rendered. 
    - **Original file in @product@:**
      `portal-impl/src/META-INF/portal-model-hints.xml` 
- `extImpl/resources/META-INF/ext-spring.xml`
    - **Description:** Overrides the Spring configuration used by
      @product@ and any of its core portlets. It's most commonly used to
      configure specific data sources or swap the implementation of a default
      service with a custom one.
    - **Original file in @product@:** `portal-impl/src/META-INF/*-spring.xml`
- `extImpl/resources/META-INF/portal-log4j-ext.xml`
    - **Description:** Allows overriding the Log4j configuration. It can be used
      to configure appenders for log file location, naming, and rotation. See
      the
      [Log4j XML Configuration Primer](https://wiki.apache.org/logging-log4j/Log4jXmlFormat). 
      [Increasing or decreasing the log level of a class or class hierarchy](/develop/tutorials/-/knowledge_base/7-1/adjusting-module-logging)
      is best done outside of an Ext plugin, in @product@'s' UI or a Log4j XML
      file in a module or the `osgi/log4j` folder. 
    - **Original file in Liferay:** `portal-impl/src/META-INF/portal-log4j.xml`
- `ext-web/docroot/WEB-INF/portlet-ext.xml`
    - **Description:** Overrides the core portlets' declarations. It's most commonly
      used to change the init parameters or the roles specified. 
    - **Original file in @product@:** `portal-web/docroot/WEB-INF/portlet-custom.xml`
- `ext-web/docroot/WEB-INF/liferay-portlet-ext.xml`
    - **Description:** Overrides the Liferay-specific core portlets'
      declarations. It core portlets included in @product@. Refer to the
      [liferay-portlet-app_7_0_0.dtd](@platform-ref@/7.0-latest/definitions/liferay-portlet-app_7_0_0.dtd.html)
      file for details on all the available options. Use this file with care;
      the code of the portlets may be assuming some of these options to be set
      to certain values. 
    - **Original file in @product@:** `portal-web/docroot/WEB-INF/liferay-portlet.xml`
- `ext-web/docroot/WEB-INF/liferay-display.xml`
    - **Description:** Overrides the portlets that are shown in the interface
      for adding applications and the categories in which they're organized.
    - **Original file in @product@:** `portal-web/docroot/WEB-INF/liferay-display.xml`

Next you'll deploy your Ext plugin. 
