# Packaging Your Marketplace App [](id=packaging-your-marketplace-app)

Liferay apps are "normal" Liferay plugins with additional information about
them. Therefore, most of the requirements are the same as those that exist for
other Liferay plugins, as explained in the tutorials on creating
[Liferay MVC Portlets](/develop/tutorials/-/knowledge_base/7-1/liferay-mvc-portlet).

In addition to those requirements, there are some Marketplace-specific ones to
keep in mind:

- **Target the Appropriate Java JRE**: Regardless of the tools you use to 
  develop your app, your app's bytecode must be compatible with the target Java
  JRE for your version of Liferay. Apps are rejected if their bytecode is not
  compatible with the Java JRE for the intended version of Liferay. In the
  Compatibility Matrix for your Liferay version, make sure your app's bytecode
  is compatible with the latest certified Java JDK version.

  If you use any Gradle-based build environment like 
  [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace), make
  sure your `targetCompatibility` is set to the latest certified Java JDK
  version. 

  Similarly, if you use Maven, you can set the bytecode compatibility like this: 

    <properties>
		<maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>  
	</properties>

  If you use the 
  [Liferay Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk)
  to develop your app for Liferay Portal 6.2 or 6.1, you can set the Java
  version by overriding  the `ant.build.javac.target` property in the Plugins
  SDK's `build.properties` file. 

- **WAR (`.war`) files**:
    - WAR files must contain a `WEB-INF/liferay-plugin-package.properties` file.
    - WAR files must not contain any `WEB-INF/liferay-plugin-package.xml` file.
    - WAR file names must not contain any commas.
    - WAR file names must conform to the following naming convention:

      *context_name*`-`*plugin_type*`-A.B.C.D.war`

      Where:

    - *context_name*: Alpha-numeric (including `-` and `_`) short name of
      your app. This name is used as the deployment context, and must not
      duplicate any other app's context (you'll be warned if you use a
      context name of any other app on the Marketplace).

    - *plugin_type*: one of the following: `hook`, `layouttpl`,
      `portlet`, `theme`, or `web`.

    - `A.B.C.D`: The four digit version of your WAR file. Four digits must
       be used.

        Example: `myapp-portlet-1.0.0.0.war`

- **`WEB-INF/`[`liferay-plugin-package.properties`](https://docs.liferay.com/ce/portal/7.1-latest/propertiesdoc/liferay-plugin-package_7_1_0.properties.html)
  file:**
    - Property `recommended.deployment.context` must not be set.
    - Setting property `security-manager-enabled` to `true` is mandatory for all
      paid apps on 6.1 EE/CE GA3 and later and on 6.2 EE/CE; the setting is
      optional for free apps. Setting this property to `true` enables Liferay's
      Plugin Security Manager. If you're enabling the security manager, you must
      also define your Portal Access Control List (PACL) in this file. Read
      [Plugins Security and PACL](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)
      for information on developing secure apps.
- **Deployment contexts**:
    - Liferay reserves the right to deny an application if any of its plugin
      deployment contexts is the same as a context of another plugin in the
      Marketplace.
    - Liferay reserves the right to replace app plugin WAR files that have
      the same deployment context as plugins built by Liferay.

There are some additional requirements for uploading Liferay DXP 7.x and Liferay
Portal CE 7.x apps:

- **OSGi modules in JAR (`.jar`) files**:
    - The manifest file must at minimum contain the following manifest headers:
        - `Bundle-SymbolicName`: a unique name for the module
        - `Bundle-Version`: the module's version
        - `Web-ContextPath`: the servlet context path
    - For more information, see 
      [OSGi and Modularity - Modules](/develop/tutorials/-/knowledge_base/7-1/osgi-and-modularity). 

- **Apps containing multiple file types:**
    - Liferay DXP 7.x and Liferay Portal CE 7.x apps can contain a mix of 
      WAR-based plugins and OSGi JAR-based plugins. Regardless of file type,
      each plugin must be able to run on Liferay DXP 7.x and Liferay Portal CE
      7.x. 

+$$$

**Important:** If you're developing a paid app or want your free app to satisfy
Plugin Security Manager on Liferay 6.2 or 6.1, make sure to specify PACLs for
your traditional plugins. See the article
[Plugin Security and PACL](/develop/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl) 
for details. Give yourself adequate time to develop your app's permission
descriptors and time to test your app thoroughly with the security manager
enabled. 

$$$

Apps usually consist of multiple plugins (e.g., multiple WAR or JAR files) and 
plugin types. In addition, you may want to consider how to package your app for 
running on different Liferay versions. 

## Considering Package Variations to Target Different Versions of Liferay [](id=considering-package-variations-to-target-different-versions-of-liferay)

Apps can be written to work across many different versions of Liferay. For
example, suppose you want to publish version 1.0 of your app, which you're
supporting on Liferay Portal 6.1 and 6.2. Due to incompatibilities between these
Liferay versions, it may be impossible to create a single binary WAR or JAR file
that works across both Liferay versions. In this case, you must compile your app
twice: once against Liferay Portal 6.1 and once against 6.2, producing two
different *packages* (also called variations) of your version 1.0 app. Each
package has the same functionality, but they're different files. You can upload
such packages to support your app on different Liferay versions. Packages are
sometimes referred to as files that make up your app. 

Now that you've prepared your app's files and specified its metadata, it's
time to submit it to Liferay for publishing on the Marketplace! 
