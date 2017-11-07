#Resolving Javascript npm modules in the server

Sometimes it is necessary to be able to resolve a npm module version while 
running server code. For example, when bootstraping a portlet's Javascript code 
we need to know the version of the portlet's own npm module.

To accomplish such task, the Portal provides the `NPMResolver` API.

##The NPMResolver API

By using this API you may obtain the npm package descriptors (represented by the 
interface `JSPackage`) stored in the `NPMRegistry` and related to the OSGi 
bundle where the `NPMResolver` is obtained.

You can also resolve npm module names in the context of the OSGi bundle. 

To grab a reference to the `NPMResolver` API you may use OSGi declarative 
services to inject one into any of your components. For instance, you may inject 
a reference to `NPMResolver` in your portlet by using the following code:

```java	
public class MyPortlet extends MVCPortlet {
	
	@Reference
	private NPMResolver _npmResolver;
	
}
```

Keep in mind that each `NPMResolver` reference you get is only usable inside the 
OSGi bundle where you obtained it, as it is tied to the OSGi bundle's own npm 
package. This means, that the resolution context for the `NPMResolver` is the 
`package.json` file of the OSGi bundle.

###Obtaining an OSGi bundle's own npm package

To obtain the `JSPackage` describing an OSGi bundle's own package, you can call 
the `getJSPackage()` method. This way you can obtain the name, version, modules, 
default entry point, etc. of the npm package.

This can be useful to bootstrap a portlet's Javascript code in a maintainable 
way (meaning that you don't have to tweak it each time you change version 
numbers or package names), like in the following code:

**MyPortlet.java**

```java
	
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
```

**index.jsp**

```jsp
<%
String bootstrapRequire =
	(String)renderRequest.getAttribute("bootstrapRequire");
%>

<aui:script require="<%= bootstrapRequire %>">
	bootstrapRequire.default();
</aui:script>
```

As you can see, this code obtains a reference to the OSGi bundle's own npm 
package and passes its resolved id (a string with the format 
`<package name>@<version>`) to the JSP so that the call to `<aui:script>` tag 
can use it.

Pay attention to how we alias the `<package name>@<version>` Javascript variable 
as `bootstrapRequire` so that we can then use it inside `<aui:script>`'s code.

This is a new feature that previously was not available, as Javascript variables 
were automatically composed based on the module name such that, for example: a 
module named `my-package@1.0.0` was injected to `<aui:script>` code as a 
variable named `myPackage100`.

We could have decided not to alias the variable, but then, we would need to 
update its name each time we release a new version of the OSGi bundle's own npm 
package.

###Obtaining an OSGi bundle's dependency npm package

Imagine that you have the following `package.json` file in your OSGi bundle:

**package.json**

```json
{
	"dependencies": {
		"react": "15.6.2",
		"react-dom": "15.6.2"
	},
	.
	.
	.
}
```

And now, you want to obtain the resolved id of your `react` dependency in the 
server side.

To do that, you should simply obtain a reference to `NPMResolver` and call the 
`getDependencyJSPackage()` method like this:

```java
String reactResolvedId = 
	npmResolver.getDependencyJSPackage("react");
```

After that, the `reactResolvedId` variable will contain the value `react@15.6.2`.

###Resolving an OSGi bundle's dependency npm module

To finish with, you can also use the `resolveModuleName()` method, to directly 
resolve a npm module name. 

For instance, with the `package.json` given in the previous version, you may call:

```java
String resolvedModule = 
	npmResolver.resolveModuleName("react/dist/react-with-addons");
```

And that will set the `resolvedModule` variable to the value 
`react@15.6.2/dist/react-with-addons`.

This is sometimes useful to reference static resources inside npm packages 
(like CSS or image files).
