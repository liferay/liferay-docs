# Using the NPMResolver API to Resolve npm Module Information

Sometimes it is necessary to resolve npm packages and modules while running 
server code. @product@'s `NPMResolver` API lets you do just that. Available as 
an OSGi component that you can inject into your OSGi bundle, @product@'s 
`NPMResolver` API exposes an OSGi bundle's npm package descriptors, stored in 
the `NPMRegistry`, so you can access them in your server code. Each `NPMResolver` 
instance is tied to the `package.json` of the OSGi bundle that you inject it 
into. This means that every npm module and package is resolved based on the 
versions that are declared in the bundle's `package.json`. This can be very 
useful when developing npm-based portlets.

This tutorial shows how to use the `NPMResolver` API in your npm-based portlet 
to accomplish these tasks:

- resolve an OSGi bundle's npm package
- resolve an OSGi bundle's dependency npm package
- resolve an OSGi bundle's dependency npm module

Start by referencing the NPMResolver in your portlet.

## Injecting the NPMResolver in Your Portlet

To access the `NPMResolver` API in your portlet, you must first reference 
it. To do this, use declarative services in your Component class. For instance, 
you can inject a reference to `NPMResolver` in your portlet by using the 
following code:

    public class MyPortlet extends MVCPortlet {
    	
    	@Reference
    	private NPMResolver _npmResolver;
    	
    }
    
+$$$

**Note:** The `NPMResolver` reference can only obtain npm package information 
for the OSGi bundle that it's included in. It is tied directly to the OSGi 
bundle's `package.json` file, and can therefore only be used to retrieve npm 
module/package information provided by the OSGi bundle's `package.json`.

$$$

Now that the `NPMResolver` is injected into your portlet, you can use it to 
resolve your OSGi bundle's npm packages and modules.

## Obtaining an OSGi Bundle's npm Package Descriptors

So you want to resolve an OSGi bundle's own npm package? Use the `JSPackage` 
interface. The `JSPackage` interface exposes the OSGi bundle's npm package 
descriptors, providing the name, version, modules, default entry point, etc. of 
the bundle's npm package. This information has many practical applications. For 
instance, once you know the portlet's npm module version, you can reference it 
in your portlet's JSP. The example below obtains a reference to the module that 
bootstraps the portlet's JavaScript code, so the code doesn't have to be updated 
each time a new version is released:

First the class retrieves a reference to the OSGi bundle's npm package. Then it 
grabs the npm package's resolved ID (a string with the format 
`<package name>@<version>`) with the `JSPackage.getResolvedId()` method and 
includes it in the `bootstrapRequire` attribute. This ensures that the version 
is always up to date:
	
    public class MyPortlet extends MVCPortlet {
    	
    	@Override
    	public void doView(
    			RenderRequest renderRequest, RenderResponse renderResponse)
    		throws IOException, PortletException {

    		JSPackage jsPackage = _npmResolver.getJSPackage();

    		renderRequest.setAttribute(
    			"bootstrapRequire",
    			jsPackage.getResolvedId() + " as bootstrapRequire");

    		super.doView(renderRequest, renderResponse);
    	}
    	
    	@Reference
    	private NPMResolver _npmResolver;
    	
    }

Next, the portlet's JSP retrieves the `bootstrapRequire` attribute to use in the 
`<aui:script>` tag:

    <%
    String bootstrapRequire =
    	(String)renderRequest.getAttribute("bootstrapRequire");
    %>

    <aui:script require="<%= bootstrapRequire %>">
    	bootstrapRequire.default();
    </aui:script>

By default, @product@ automatically composes an npm module's JavaScript variable 
based on its name. For example, the module `my-package@1.0.0` translates to the 
variable `myPackage100` for the `<aui:script>` tag's `require` attribute. Using 
an alias instead, as shown in the example above, means that you don't have to 
update the code each time a new version of the OSGi bundle's npm package is 
released. The resolved ID retrieves the current version defined in the OSGi 
module's `package.json`!

Now that you know how to obtain an OSGi bundle's npm package descriptors, you 
can learn how to retrieve an OSGi bundle's dependency npm package descriptors. 

## Obtaining an OSGi bundle's Dependency npm Package Descriptors

The `npmResolver` API provides two methods for retrieving an OSGi bundle's 
dependency npm package descriptors--`getDependencyJSPackage()` to retrieve 
dependency npm packages and `resolveModuleName()` to retrieve dependency npm 
modules. Take the `package.json` below for example:

    {
    	"dependencies": {
    		"react": "15.6.2",
    		"react-dom": "15.6.2"
    	},
    	.
    	.
    	.
    }
    
To obtain an OSGi bundle's npm dependency package, pass the dependency package's 
name in as the `getDependencyJSPackage()` method's argument. The example below 
resolves the `react` dependency package:

    String reactResolvedId = npmResolver.getDependencyJSPackage("react");
    
`reactResolvedId`'s resulting value is `react@15.6.2`. To obtain a module in an 
npm dependency package, pass the module's relative path in as the 
`resolveModuleName()` method's argument. The example below resolves a module 
named `react-with-addons` for the `react` dependency package:

    String resolvedModule = 
    npmResolver.resolveModuleName("react/dist/react-with-addons");


The `resolvedModule` variable evaluates to `react@15.6.2/dist/react-with-addons`.
This is sometimes useful to reference static resources inside npm packages 
(like CSS or image files). Now you know how to use @product@'s `NPMResolver` API 
in your npm-based portlets!

## Related Topics

[liferay-npm-bundler](/develop/tutorials/-/knowledge_base/7-0/liferay-npm-bundler)

[Adding liferay-npm-bundler to Your Portlet](/develop/tutorials/-/knowledge_base/7-0/adding-liferay-npm-bundler-to-your-portlet)

[@product@ JavaScript APIs](/develop/tutorials/-/knowledge_base/7-0/liferay-javascript-apis)