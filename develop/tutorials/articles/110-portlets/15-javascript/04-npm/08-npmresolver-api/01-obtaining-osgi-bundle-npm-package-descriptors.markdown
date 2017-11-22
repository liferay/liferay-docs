# Obtaining an OSGi Bundle's npm Package Descriptors [](id=obtaining-npm-package-descriptors)

The 
[`JSPackage` interface](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/JSPackage.html) 
exposes the OSGi bundle's npm package name, version, modules, default entry 
point, etc. You can use this information to make your code easier to maintain. 
An example use case is covered in the next section.

## Using an Alias to Reference a Module's Package [](id=using-an-alias-to-reference-a-modules-package)

By default, @product@ automatically composes an npm module's JavaScript variable 
based on its name. For example, the module `my-package@1.0.0` translates to the 
variable `myPackage100` for the `<aui:script>` tag's `require` attribute. 
Instead, you can use the `JSPackage` interface to obtain the module's package 
name and create an alias to reference it. This ensures that you don't have to 
update the code each time a new version of the OSGi bundle's npm package is 
released.

Follow these steps:

1.  Retrieve a reference to the OSGi bundle's npm package using the 
    [`getJSPackage()` method](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html#getJSPackage): 

        JSPackage jsPackage = _npmResolver.getJSPackage();

2.  Next, grab the npm package's resolved ID (the current package version, 
    in the format `<package name>@<version>`, defined in the OSGi module's 
    `package.json`) using the 
    [`getResolvedId()` method](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/JSPackage.html#getResolvedId) 
    and alias it with the `as myVariableName` pattern. The example below 
    retrieves the npm module's resolved ID, sets it to the `bootstrapRequire` 
    variable, and assigns the entire value to the attribute `bootstrapRequire`. 
    This ensures that the package version is always up to date:

        renderRequest.setAttribute(
          "bootstrapRequire",
          jsPackage.getResolvedId() + " as bootstrapRequire");
          
3.  Include the reference to the 
    [`NPMResolver`](ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html):

        @Reference
        private NPMResolver _npmResolver;
        
4.  Resolve the `JSPackage` and `NPMResolver` imports:

        import com.liferay.frontend.js.loader.modules.extender.npm.JSPackage;
        import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;

5.  Next, in the portlet's JSP, retrieve the aliased attribute 
    (`bootstrapRequire` in the example):

        <%
        String bootstrapRequire =
        	(String)renderRequest.getAttribute("bootstrapRequire");
        %>

6.  Finally, use the attribute as the `<aui:script>` require attribute's value:

        <aui:script require="<%= bootstrapRequire %>">
        	bootstrapRequire.default();
        </aui:script>
    
    

Below is the full example `*Portlet` class:
	
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
    
And here is the corresponding example `view.jsp`:

    <%
    String bootstrapRequire =
      (String)renderRequest.getAttribute("bootstrapRequire");
    %>

    <aui:script require="<%= bootstrapRequire %>">
      bootstrapRequire.default();
    </aui:script>

Now you know how to obtain an OSGi bundle's npm package descriptors!

## Related Topics [](id=related-topics)

[Obtaining an OSGi bundle's Dependency npm Package Descriptors](/develop/tutorials/-/knowledge_base/7-0/obtaining-dependency-npm-package-descriptors)

[liferay-npm-bundler](/develop/tutorials/-/knowledge_base/7-0/liferay-npm-bundler)

[How @product@ Publishes npm Packages](/develop/tutorials/-/knowledge_base/7-0/how-liferay-portal-publishes-npm-packages)
