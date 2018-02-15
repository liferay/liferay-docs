# Upgrading App JSP Hooks [](id=upgrading-app-jsp-hook-plugins)

JSPs in OSGi modules can be customized using module fragments. The module
fragment attaches to the host module to alter the JSPs. To the OSGi runtime, the
fragment is part of the host module. Section 3.14 of the 
[OSGi Alliance's core specification document](https://www.osgi.org/developer/downloads/release-6/)
explains module fragments in detail. This tutorial shows you how to upgrade your
app JSP hooks to @product-ver@.

Liferay @ide@'s Code Upgrade Tool's
[*Convert Custom JSP Hooks* step](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
generates module fragments from app JSP hook plugins. The tool creates module
fragments in the same folder as your Plugins SDK root if your hook is in a
Plugins SDK or in the `[liferay_workspace]/modules` folder if your hook is in a
Liferay Workspace.

Module fragments follow this name convention: `[plugin_name]-[app]-fragment`.
For example, if the plugin's name is `app-jsp-hook` and it modifies a JSP in the
Blogs app, the Code Upgrade Tool generates a module fragment named
`app-jsp-hook-blogs-fragment`.

Here are the steps for upgrading app JSP hook plugins:

1.  [Declare the Fragment Host](#declare-the-fragment-host)
2.  [Update the JSP](#update-the-jsp)

## Declare the Fragment Host [](id=declare-the-fragment-host)

The module fragment's `bnd.bnd` file must specify an OSGi header `Fragment-Host`
set to the host module name and version. 

If the host module belongs to one of @product@'s app suites, the Code Upgrade
Tool generates a `bnd.bnd` file that specifies an appropriate `Fragment-Host`
header automatically. 

For example, here's a `Fragment-Host` that attaches a module fragment to the
Blogs Web module. 

    Fragment-Host: com.liferay.blogs.web;bundle-version="1.1.9"

Updating the JSP is straightforward too. 

## Update the JSP [](id=update-the-jsp)

The Code Upgrade Tool creates a module fragment that contains an upgraded
version of your custom app JSP. 

The following table shows the old and new JSP paths.

Liferay Portal version | JSP File Path |
-------------|------|
**6.2** | `docroot/META-INF/custom_jsps/html/portlet/[jsp_file_path]`
**7.1** | `src/main/resources/META-INF/resources/[jsp_file_path]`

For example, the Code Upgrade Tool generates a customized version of the Blogs
app's `init-ext.jsp` file here:

    src/main/resources/META-INF/resources/blogs/init-ext.jsp

The tool's 
[*Convert Custom JSP Hooks* step](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool)
lets you compare custom JSPs with originals:

- Compare your custom 6.2 JSP with the original 6.2 JSP.
- Compare your custom 7.1 JSP with your custom 6.2 JSP.

![Figure 1: The Code Upgrade Tools lets you compare custom JSPs with originals.](../../../../images/upgrading-app-jsp-hook-convert-custom-jsp-hook.png)

Make any additional needed changes in your 7.1 custom JSP. Then
[deploy](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#building-and-deploying-a-module)
your module fragment. This stops the host module momentarily, attaches the
fragment to the host, and then restarts the host module. The console output
reflects this process. 

Here's output from deploying a module fragment that attaches to the Blogs web
module.

    19:23:11,740 INFO  [Refresh Thread: Equinox Container: 00ce6547-2355-0017-1884-846599e789c4][BundleStartStopLogger:38] STOPPED com.liferay.blogs.web_1.1.9 [535]
    19:23:12,910 INFO  [Refresh Thread: Equinox Container: 00ce6547-2355-0017-1884-846599e789c4][BundleStartStopLogger:35] STARTED com.liferay.blogs.web_1.1.9 [535]

Your custom JSP is live.

## Related Topics [](id=related-topics)

[Overriding App JSPs](/develop/tutorials/-/knowledge_base/7-1/overriding-a-modules-jsps)

[Upgrading Core JSP Hooks](/develop/tutorials/-/knowledge_base/7-1/upgrading-core-jsp-hooks)

[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies)

[Upgrading the Liferay Maven Build](/develop/tutorials/-/knowledge_base/7-1/upgrading-the-liferay-maven-build)
