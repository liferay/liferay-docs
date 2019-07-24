---
header-id: loading-bundled-npm-modules-in-your-portlets
---

# Loading Bundled npm Modules in Your Portlets

[TOC levels=1-4]

Once you've 
[exposed your modules](/docs/7-2/frameworks/-/knowledge_base/f/using-javascript-in-your-portlets), 
you can use them in your portlet via the `aui:script` tag's `require` attribute. 
You can load the npm module in your portlet using the 
`npmResolvedPackageName` variable, which is available by default since 
@product-ver@. You can then create an alias to reference it in your portlet. 

Follow these steps:

1.  Provide a `Web-ContextPath` in your bundle's `bnd.bnd` file:

    ```properties
    Web-ContextPath: /my-module-web
    ```

2.  Make sure the `<liferay-frontend:defineObjects />` tag is included in your 
    portlet's `init.jsp`. This makes the `npmResolvedPackageName` variable 
    available, setting it to your project module's resolved name. For instance, 
    if your module is called `my-module` and is at version `2.3.0`, the implicit 
    variable `npmResolvedPackageName` is set to `my-module@2.3.0`. This lets you 
    prefix any JS module `require` or soy component rendering with this 
    variable.

3.  Use the `npmResolvedPackageName` variable along with the relative path to 
    your JavaScript module file to create an alias in the `<aui:script>`'s 
    `require` attribute. An example configuration is shown below:

    ```jsp
    <aui:script 
      require='<%= npmResolvedPackageName + 
      "/js/my-module.es as myModule" %>'>
    </aui:script>
    ```

4.  Use the alias inside the `aui:script` to refer to your module:

    ```jsp
    <aui:script 
      require='<%= npmResolvedPackageName + 
      "/js/my-module.es as myModule" %>'>
      
        myModule.default();
    </aui:script>
    ```

Now you know how to use an npm module's package! 

## Related Topics

- [Obtaining an OSGi bundle's Dependency npm Package Descriptors](/docs/7-2/frameworks/-/knowledge_base/f/obtaining-dependency-npm-package-descriptors)
- [liferay-npm-bundler](/docs/7-2/reference/-/knowledge_base/r/liferay-npm-bundler)
- [How liferay-npm-bundler Publishes npm Packages](/docs/7-2/reference/-/knowledge_base/r/how-the-liferay-npm-bundler-publishes-npm-packages)
