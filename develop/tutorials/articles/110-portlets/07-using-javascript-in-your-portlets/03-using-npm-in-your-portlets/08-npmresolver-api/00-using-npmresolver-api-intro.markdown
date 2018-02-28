# Using the NPMResolver API in Your Portlets [](id=using-the-npmresolver-api-in-your-portlets)

If you're developing an npm-based portlet, your OSGi bundle's `package.json` is 
a treasure-trove of information. It contains everything that's stored in the 
npm registry about your bundle: default entry point, dependencies, modules, 
package names, versions, and more. The 
[`NPMResolver` APIs](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html) 
expose this information so you can access it in your portlet. If it's defined 
in the OSGi bundle's `package.json`, you can retrieve the information in your 
portlet with the `NPMResolver` API. For instance, you can use this API to 
[reference an npm package's static resources](/develop/tutorials/-/knowledge_base/7-1/obtaining-dependency-npm-package-descriptors) 
(such as CSS files) and even to
[make your code more maintainable](/develop/tutorials/-/knowledge_base/7-1/obtaining-npm-package-descriptors#using-an-alias-to-reference-a-modules-package). 

To enable the `NPMResolver` in your portlet, use the `@Reference` annotation to 
inject the `NPMResolver` OSGi component into your portlet's Component class, as 
shown below:

    import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;

    public class MyPortlet extends MVCPortlet {
      
      @Reference
      private NPMResolver `_npmResolver`;
      
    }

+$$$

**Note:** Because the `NPMResolver` reference is tied directly to the OSGi 
bundle's `package.json` file, it can only be used to retrieve npm module and 
package information from that file. You can't use the `NPMResolver` to retrieve 
npm package information for other OSGi bundles.

$$$
 
Now that the `NPMResolver` is added to your portlet, the tutorials in this 
section describe retrieving your OSGi bundle's npm package and module 
information.
