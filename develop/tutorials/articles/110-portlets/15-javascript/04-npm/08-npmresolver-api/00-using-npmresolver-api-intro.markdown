# Using the NPMResolver API in Your Portlets [](id=using-the-npmresolver-api-in-your-portlets)
<!-- Need to update release version once it's known -->
Since the release of Liferay Portal CE GA6 and Liferay DXP Fix 
Pack ?, @product@'s 
[`NPMResolver` OSGi component](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html) 
exposes an OSGi bundle's npm package descriptors, stored in the `NPMRegistry`, 
so you can access them in your server code. This lets you resolve an OSGi 
bundle's npm packages and its dependency npm packages and modules in your 
npm-based portlets. How you use this information is up to you. For instance, you 
can 
[reference an OSGi bundle's JavaScript package version number](/develop/tutorials/-/knowledge_base/7-0/obtaining-npm-package-descriptors#using-an-alias-to-reference-a-modules-package) 
in your JSP instead of hardcoding it. This makes your code much easier to 
maintain since you don't have to update the code each time the module version is 
updated. 

To enable the `NPMResolver` in your portlet follow these steps:

1.  Add the import below to your portlet's Component class:

        import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;

2.  Use the `@Reference` annotation to inject the `NPMResolver` OSGi component
    into your portlet's Component class, as shown below:

        public class MyPortlet extends MVCPortlet {
          
          @Reference
          private NPMResolver `_npmResolver`;
          
        }

+$$$

**Note:** Because the `NPMResolver` reference is tied directly to the OSGi 
bundle's `package.json` file, it can only be used to retrieve npm 
module/package information from that file. You can't use the `NPMResolver` to 
retrieve npm package information for other OSGi bundles.

$$$
 
Now that the `NPMResolver` is added to your portlet, see the following 
tutorials in this section for instructions on retreiving your OSGi bundle's npm 
package and module information.
