# Obtaining an OSGi bundle's Dependency npm Package Descriptors [](id=obtaining-dependency-npm-package-descriptors)

While writing your npm portlet, you may need to reference a dependency package 
or its modules. For instance, you can retrieve an npm dependency package 
module's CSS file and use it in your portlet. The 
[`NPMResolver` OSGi component](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html) 
provides two methods for retrieving an OSGi bundle's dependency npm package 
descriptors:
[`getDependencyJSPackage()`](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html#getDependencyJSPackage) 
to retrieve dependency npm packages and 
[`resolveModuleName()`](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html#resolveModuleName) 
to retrieve dependency npm modules. This tutorial references the `package.json` 
below to help demonstrate these methods:

    {
    	"dependencies": {
    		"react": "15.6.2",
    		"react-dom": "15.6.2"
    	},
    	.
    	.
    	.
    }
 
To obtain an OSGi bundle's npm dependency package, pass the package's name in as 
the `getDependencyJSPackage()` method's argument. The example below resolves the 
`react` dependency package:

    String reactResolvedId = npmResolver.getDependencyJSPackage("react");
 
`reactResolvedId`'s resulting value is `react@15.6.2`.

You can use the `resolveModuleName()` method To obtain a module in an npm 
dependency package. To do this, pass the module's relative path in as the 
`resolveModuleName()` method's argument. The example below resolves a module 
named `react-with-addons` for the `react` dependency package:

    String resolvedModule = 
    npmResolver.resolveModuleName("react/dist/react-with-addons");

The `resolvedModule` variable evaluates to `react@15.6.2/dist/react-with-addons`. 
You can also use this to reference static resources inside npm packages (like 
CSS or image files), as shown in the example below:

    String cssPath = npmResolver.resolveModuleName(
          "react/lib/css/main.css"); 

Now you know how to obtain an OSGi bundle's dependency npm packages descriptors!

## Related Topics [](id=related-topics)

[Obtaining an OSGi bundle's npm Package Descriptors](/develop/tutorials/-/knowledge_base/7-1/obtaining-npm-package-descriptors)

[The Structure of OSGi Bundles Containing npm Packages](/develop/tutorials/-/knowledge_base/7-1/the-structure-of-osgi-bundles-containing-npm-packages)

[How @product@ Publishes npm Packages](/develop/tutorials/-/knowledge_base/7-1/how-liferay-portal-publishes-npm-packages)
