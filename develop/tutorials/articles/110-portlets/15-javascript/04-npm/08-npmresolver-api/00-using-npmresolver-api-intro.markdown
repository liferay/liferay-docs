# Using the NPMResolver API in Your Portlets [](id=using-the-npmresolver-api-in-your-portlets)
<!-- Need to update release version once it's known -->
While developing npm-based portlets, you may need to retrieve an OSGi bundle's 
npm information. Since Liferay Portal CE GA6 and Liferay DXP Fix Pack ?, 
@product@'s 
[`NPMResolver` OSGi component](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html) 
exposes an OSGi bundle's npm package descriptors, stored in the `NPMRegistry`, 
so you can access their information in your server code. This lets you resolve 
an OSGi bundle's npm packages and its dependency npm packages and modules in 
your npm-based portlets.

To use the `NPMResolver` in your portlet follow these steps:

1.  Add the import below to your portlet's Component class:

        import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;

2.  Use the `@Reference` annotation to inject the `NPMResolver` OSGi component
    into your portlet's Component class, as shown below:

        public class MyPortlet extends MVCPortlet {
          
          @Reference
          private NPMResolver `_npmResolver`;
          
        }

+$$$

**Note:** The `NPMResolver` reference is tied directly to the OSGi bundle's 
`package.json` file, and can therefore only be used to retrieve npm 
module/package information provided by the OSGi bundle's `package.json`. You 
can't use the `NPMResolver` to retrieve npm package information for another OSGi 
bundle.

$$$
 
Now that the `NPMResolver` is added to your portlet, you can use it to retrieve 
your OSGi bundle's npm package and module information. This section of tutorials 
shows how to use the `NPMResolver` APIs in your npm-based portlet projects.
