# Adapting to Liferay 7's API [](id=adapting-to-liferay-7s-api)

If you have legacy applications for Liferay Portal 6.2 or earlier, you'll be
happy to know there's a clearly defined path for migrating them to Liferay 7. As
you know by now, many of Liferay's applications have migrated to OSGi modules,
but Liferay is still a JSR-286, standards compliant portal. For this reason, it
remains backwards compatible for WAR-style applications. This means two things:
you can write both module and WAR-style applications, and you can easily upgrade
your old 6.2 applications to new 7.0 APIs without converting them to modules
first. When considering migrating your application to Liferay 7, you can divide
the task into two general steps: 

**Step 1:  Convert your 6.2 WAR-style plugin to a 7.0 WAR-style plugin**

- Involves adapting the plugin to Liferay 7's APIs
- Allows you to use the plugin in Liferay 7.0
- Facilitates converting the plugin to a module

**Step 2:  Convert your WAR-style plugin to an OSGi module.**

- Allows you to leverage the most control over the plugin
- Makes the plugin more maintainable

This tutorial shows you **Step 1**: how to get your 6.2 WAR-style plugin
working on Liferay 7.0. Later, you can convert your plugin to an OSGi module,
following the tutorial [Modularizing Legacy Plugins](/develop/tutorials/-/knowledge_base/7-0/modularizing-legacy-plugins).

Follow these instructions adapt your plugin to Liferay 7:

1.  Copy your plugin into a development environment (e.g.,
    [Liferay 7.0 Plugins SDK](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/),
    [Liferay IDE](https://sourceforge.net/projects/lportal/files/Liferay%20IDE/),
    etc.) configured to use Liferay 7. 

2.  Resolve your plugin's dependencies on other modules by either manually
    copying the libraries into your plugin's `WEB-INF/lib` folder or specifying
    the libraries in a descriptor for the management dependency framework you're
    using, such as Ivy, Gradle, or Maven. Here's an example `ivy.xml` file
    snippet that specifies a dependency on Liferay's Journal API module:

        <ivy-module>
            ...
          <dependencies defaultconf="default">
             <dependency name="com.liferay.journal.api" org="com.liferay" rev="1.0.0" />
          </dependencies>
            ...
        </ivy-module>

    Refer to [Finding Liferay API Modules](/develop/reference/-/knowledge_base/7-0/finding-liferay-api-modules)
    to learn how to locate and specify dependencies on Liferay modules.

    +$$$

    Note: If you're using the Plugins SDK, running `ant clean` downloads the
    dependency JARs to your plugin's `WEB-INF/lib` folder. If you're already
    using Ivy and the JARs don't download, you might need to delete the
    `ivy.xml.MD5` file from your plugin's root folder and retry `ant clean`.

    $$$

3.  Refactor your imports so they match the new package names of the module's
    classes. For example, if one of your plugin's classes imports the
    `JournalArticleLocalServiceUtil` class, update the import.

    *Old way (6.2):*

        import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

    *New way (7.0):*

        import com.liferay.journal.service.JournalArticleLocalServiceUtil;

    Make sure to check other files--such as JSP files--for imports and
    references.

    Some services might require resolution from the OSGi registry. Here's an
    example of resolving a service for the `UserImporter` class.

        Bundle bundle = FrameworkUtil.getBundle(getClass());
        BundleContext bundleContext = bundle.getBundleContext();
        ServiceReference<UserImporter> serviceReference =
            sbundleContext.getServiceReference(UserImporter.class);
        
        UserImporter userImporter =
            bundleContext.getService(serviceReference);

4.  Open your `liferay-plugin-package.properties` file to make the following
    updates.

    Update the Liferay version:

        liferay-versions=7.0.0+

    Exclude all OSGi framework JARs (e.g., `osgi.core.jar`) and module JARs (e.g.,
    `com.liferay.journal.api.jar`). For example:

        deploy-excludes=\
            **/WEB-INF/lib/com.liferay.portal.journal.api.jar,\
            **/WEB-INF/lib/org.osgi.core.jar

    
    Specify properties such as `Export-Package`, `Provide-Capability`,
    `Require-Capability` and more, to use as [OSGi bundle
    headers](https://www.osgi.org/bundle-headers-reference/). The Module
    Framework's compatibility layer propagates them to a manifest it creates for
    the plugin. One such property is `Export-Package`, to which you can assign
    the packages your plugin exposes. Here's an example an `Export-Package`
    property that exposes specific packages from a plugin:

        Export-Package=\
            com.liferay.portal.sample,\
            com.liferay.portal.sample.package2

5.  Compile your plugin. If compilation fails, search the
    [Breaking Changes](/develop/reference/-/knowledge_base/7-0/what-are-the-breaking-changes-for-liferay-7-0)
    document for potential causes and resolutions.

6.  Build your plugin's WAR file (e.g., execute `ant war`). In the Plugins SDK,
    WAR files are generated to `/dist` folder.

    Inspect the WAR file to make sure its `lib/` folder doesn't contain any OSGi
    framework JARs or Liferay module JARs (e.g., `com.liferay.journal.api.jar`).
    If the plugin is deployed with such JARs they'll conflict with the JARs
    already installed in Liferay's Module Framework--identical JARs will exist
    in two different classloaders and cause class cast exceptions. 

    The easiest way to exclude such JARs is to list them in a `deploy-excludes`
    property in the `liferay-plugin-package.properties`, as explained in step 4.
    You must otherwise remove the JARs manually from the plugin WAR file.

7.  Deploy your plugin's WAR file (e.g., execute `ant deploy`). 

    Liferay's Module Framework converts the plugin WAR to a Web Application
    Bundle (WAB). The generated WAB essentially consists of the WAR file's
    contents and an OSGi bundle manifest. The generated manifest includes any
    headers the plugin's `liferay-plugin-package.properties` specified, support
    for JSPs, tag library definitions, and more.

    The Module Framework's web extender module detects and deploys the generated
    WAB to the `[liferay-home]/osgi/` folder.

8.  Verify your plugin's deployment. You can check the console for the plugin's
    deployment confirmation and/or verify the plugin in portal's UI.

    You can even check for your plugin module's deployment in the OSGi console.
    Open a terminal, run `telnet localhost 11311`, and then run `lb`. Your
    application should be in a listing like this:

        310|Active     |    1|Liferay Layout Type Controller Node (1.0.0)
        311|Active     |    1|Liferay Portlet Configuration Icon Edit Guest (1.0.0)
        316|Active     |    1|resources-importer-web-7-0.0.1 (7.0.0)

    +$$$

    **Tip**: If at first you don't see your plugin module listed, pipe the
    results through the grep command:

        lb | grep resources-importer-web

    $$$

Awesome! You've converted your legacy plugin to a 7.0 WAR-style
plugin that runs on Liferay 7!

## Related Topics [](id=related-topics)

[ConvertingLegacy Applications to Modules](/develop/tutorials/-/knowledge_base/7-0/converting-legacy-applications-to-modules)

[Finding Liferay API Modules](/develop/reference/-/knowledge_base/7-0/finding-liferay-api-modules)

[Development Reference](/develop/reference/-/knowledge_base/7-0/development-reference)
