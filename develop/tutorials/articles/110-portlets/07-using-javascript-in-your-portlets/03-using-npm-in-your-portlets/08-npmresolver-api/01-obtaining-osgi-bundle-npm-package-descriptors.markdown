# Referencing an npm Module's Package to Improve Code Maintenance [](id=referencing-an-npm-modules-package)

Once you've 
[exposed your modules](/develop/tutorials/-/knowledge_base/7-1/preparing-your-javascript-files-for-es2015), 
you can use them in your portlet via the `aui:script` tag's `require` attribute. 
By default, @product@ automatically composes an npm module's JavaScript variable 
based on its name. For example, the module `my-package@1.0.0` translates to the 
variable `myPackage100` for the `<aui:script>` tag's `require` attribute. This 
means that each time a new version of the OSGi bundle's npm package is released, 
you must update your code's variable to reflect the new version. You can use the 
[`JSPackage` interface](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/JSPackage.html) 
to obtain the module's package name and create an alias to reference it, so the 
variable name always reflects the latest version number! 

Follow these steps:

1.  Retrieve a reference to the OSGi bundle's npm package using the 
    [`getJSPackage()` method](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/js/loader/modules/extender/npm/NPMResolver.html#getJSPackage): 

        JSPackage jsPackage = _npmResolver.getJSPackage();

2.  Grab the npm package's resolved ID (the current package version, 
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

5.  In the portlet's JSP, retrieve the aliased attribute (`bootstrapRequire` in 
    the example):

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

Now you know how to reference an npm module's package!

## Related Topics [](id=related-topics)

[Obtaining an OSGi bundle's Dependency npm Package Descriptors](/develop/tutorials/-/knowledge_base/7-1/obtaining-dependency-npm-package-descriptors)

[liferay-npm-bundler](/develop/tutorials/-/knowledge_base/7-1/liferay-npm-bundler)

[How @product@ Publishes npm Packages](/develop/tutorials/-/knowledge_base/7-1/how-liferay-portal-publishes-npm-packages)
