# Servlet Filters [](id=servlet-filters)

Servlet filters can both pre-process requests as they arrive and post-process
responses before they go to the client browser. They let you apply functionality
to requests and responses for multiple servlets, without the servlets knowing.
Here are some common filter use cases:  

- Logging
- Auditing
- Transaction management
- Security

You can use patterns in descriptors to map the filters to servlet URLs. When
requests arrive at these URLs, your filters process them. Filter chaining lets
you apply filters in an order you want. Servlet Filter Hook plugins let you
deploy and undeploy filters without modifying the Liferay web application. Here
are the steps for creating and deploying a servlet filter:

1. [Create a Servlet Filter class](#step-1-create-a-servlet-filter-class)

2. [Map URLs to your Servlet Filter](#step-2-map-urls-to-your-servlet-filter) 

3. [Create a Liferay plugin descriptor](#step-3-create-a-liferay-plugin-descriptor) 

4. [Deploy your plugin](#step-4-deploy-your-plugin) 

For reference, you can download the example
[servlet filter project code](https://dev.liferay.com/documents/10184/1608802/sample-servlet-filter-hook.zip). 

In a traditional web application (`.war`) project, start with creating your
servlet filter class. 

+$$$

**Note:**
[Portlet filters](https://portals.apache.org/pluto/portlet-3.0-apidocs/javax/portlet/filter/PortletFilter.html)
let you apply functionality to portlet requests and responses. 
[JSP overrides](/develop/tutorials/-/knowledge_base/7-1/jsp-overrides-using-portlet-filters)
are one way to use portlet filters. 

$$$

## Step 1: Create a Servlet Filter class [](id=step-1-create-a-servlet-filter-class)

Create a class that implements 
[`javax.servlet.Filter`](https://tomcat.apache.org/tomcat-9.0-doc/servletapi/index.html).
Here's an example servlet filter class: 

    package com.liferay.sampleservletfilter.hook.filter;

    import com.liferay.portal.kernel.util.WebKeys;

    import java.io.IOException;

    import javax.servlet.Filter;
    import javax.servlet.FilterChain;
    import javax.servlet.FilterConfig;
    import javax.servlet.ServletException;
    import javax.servlet.ServletRequest;
    import javax.servlet.ServletResponse;

    public class SampleFilter implements Filter {

    	@Override
    	public void destroy() {
    		System.out.println("Called SampleFilter.destroy()");
    	}

    	@Override
    	public void doFilter(
    			ServletRequest servletRequest, ServletResponse servletResponse,
    			FilterChain filterChain)
    		throws IOException, ServletException {

    		String uri = (String)servletRequest.getAttribute(
    			WebKeys.INVOKER_FILTER_URI);

    		System.out.println(
    			"Called SampleFilter.doFilter(" + servletRequest + ", " +
    				servletResponse + ", " + filterChain + ") for URI " + uri);

    		filterChain.doFilter(servletRequest, servletResponse);
    	}

    	@Override
    	public void init(FilterConfig filterConfig) {
    		System.out.println(
    			"Called SampleFilter.init(" + filterConfig + ") where hello=" +
    				filterConfig.getInitParameter("hello"));
    	}

    }

Here are the
[`Filter`](https://tomcat.apache.org/tomcat-9.0-doc/servletapi/index.html) 
methods to implement:


1.  `init(FilterConfig)`: Configure the filter and perform any necessary 
    initializations.

    When `SampleFilter` is initialized, for example, its `init(FilterConfig)`
    method prints the `FilterConfig` object and the `hello` parameter's value:

        Called SampleFilter.init(com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterConfig@7c953747) where hello=world

2.  `doFilter(ServletRequest, ServletResponse, FilterChain)`: Filter on 
    requests and responses here. To apply your filter, invoke
    `filterChain.doFilter(servletRequest, servletResponse)`. 

    When users visit URLs mapped for `SampleFilter`, for example, its
    `doFilter(...)` method prints the `ServletResponse` object, `FilterChain`
    object, and the `ServletRequest` URI before passing control to the next
    filter by invoking
    `filterChain.doFilter(servletRequest, servletResponse)`. 

        Called SampleFilter.doFilter(org.apache.catalina.connector.RequestFacade@68be71e0, com.liferay.portal.servlet.filters.absoluteredirects.AbsoluteRedirectsResponse@2b598f1a, com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterChain@72220503) for URI /web/guest

3.  `destroy()`: Clean up the filter's unneeded resources. 

    When `SampleFilter` is destroyed, its `destroy()` method prints the message:
    `Called SampleFilter.destroy()`. 

It's time to map URLs to your servlet filter. 

## Step 2: Map URLs to your Servlet Filter [](id=step-2-map-urls-to-your-servlet-filter)

Traditionally, specifying a servlet filter and its filter mapping requires
modifying your web application's `web.xml` file. @product@, however, lets you
specify them in your plugin, so you don't need to modify the @product@ web
application. Specify your servlet filter mapping in a descriptor file
`WEB-INF/liferay-hook.xml`, like this one for Sample Filter: 

    <?xml version="1.0"?>
    <!DOCTYPE hook PUBLIC "-//Liferay//DTD Hook 7.1.0//EN" "http://www.liferay.com/dtd/liferay-hook_7_1_0.dtd">

    <hook>
        <servlet-filter>
            <servlet-filter-name>Sample Filter</servlet-filter-name>
            <servlet-filter-impl>com.liferay.sampleservletfilter.hook.filter.SampleFilter</servlet-filter-impl>
            <init-param>
                <param-name>hello</param-name>
                <param-value>world</param-value>
            </init-param>
        </servlet-filter>
        <servlet-filter-mapping>
            <servlet-filter-name>Sample Filter</servlet-filter-name>
            <before-filter>SSO Open SSO Filter</before-filter>
            <url-pattern>/group/*</url-pattern>
            <url-pattern>/user/*</url-pattern>
            <url-pattern>/web/*</url-pattern>
            <url-pattern>*.jsp</url-pattern>
            <dispatcher>REQUEST</dispatcher>
            <dispatcher>FORWARD</dispatcher>
        </servlet-filter-mapping>
    </hook>

Here's how to map URLs to your servlet filter: 

1.  Create a descriptor file `WEB-INF/liferay-hook.xml`, based on the
    [Liferay Hook DTD](@platform-ref@/7.1-latest/definitions/liferay-hook_7_1_0.dtd.html). 

2.  Add a `servlet-filter` element as a sub element of `hook`. Specify your 
    `servlet-filter` sub-elements.

    `servlet-filter-name`: Arbitrary name. (required)
 
    `servlet-filter-impl`: `Filter` implementation class. (required)

    `init-param` elements: Initialization parameters. (optional)

3.  Add a `servlet-filter-mapping` element as a sub element of `hook`. 

    `servlet-filter-name`: Match the one used in the `servlet-filter`. 
    (required)

    `after-filter`: Name of a `servlet-filter` for this filter to go after.
    (optional)

    `before-filter`: Name of a `servlet-filter` for this filter to go 
    before. (optional)

    `url-pattern` elements: URL patterns you want to filter 
    requests and responses for. (required)

    `dispatcher` elements: Specify
    [`Dispatcher` enumerated constants](https://docs.liferay.com/ce/portal/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/filters/invoker/Dispatcher.html#enum.constant.summary)
    to
    [constrain how the filter is applied to requests](https://docs.oracle.com/cd/E19798-01/821-1841/bnagf/index.html).
    (optional)

## Step 3: Create a Liferay plugin descriptor [](id=step-3-create-a-liferay-plugin-descriptor)

In a `WEB-INF/liferay-plugin-package.properties` file, specify the versions of
@product@ your plugin supports:

    liferay-versions=7.1.0+

## Step 4: Deploy your plugin [](id=step-4-deploy-your-plugin)

Specify compile-time dependencies on these artifacts:

- `com.liferay.portal.kernel`
- `javax.servlet-api` 

Gradle:

	compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.0.0"
    compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"

Maven:

    <dependency>
        <groupId>com.liferay.portal</groupId>
        <artifactId>com.liferay.portal.kernel</artifactId>
        <version>3.0.0</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.0.1</version>
    </dependency>

Build your plugin `.war` file and deploy it by copying it to the
`[LIFERAY_HOME]/deploy` folder. Liferay's auto-deployer copies the `.war` to the
`[LIFERAY_HOME]/osgi/war` folder. The
[WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
converts the `.war` to an OSGi Web Application Bundle (WAB) and installs it to
@product@'s runtime. The output in your console should look like this:

    2018-11-03 16:20:09.118 INFO  [fileinstall-C:/workspace_liferay/bundles/osgi/war][BaseAutoDeployListener:43] Copying hook plugin for C:\workspace_liferay\bundles\tomcat-9.0.6\temp\20181103162009108XCJZAKUY\sample-servlet-filter-hook.war
    2018-11-03 16:20:09.390 INFO  [fileinstall-C:/workspace_liferay/bundles/osgi/war][BaseDeployer:880] Deploying sample-servlet-filter-hook.war
    2018-11-03 16:20:10.019 INFO  [fileinstall-C:/workspace_liferay/bundles/osgi/war][BaseAutoDeployListener:50] Hook for C:\workspace_liferay\bundles\tomcat-9.0.6\temp\20181103162009108XCJZAKUY\sample-servlet-filter-hook.war copied successfully
    2018-11-03 16:20:10.730 INFO  [Refresh Thread: Equinox Container: 4023060a-c8de-0018-1c3a-ebee784b7a28][BundleStartStopLogger:35] STARTED sample-servlet-filter-hook_7.1.10.1 [963]
    2018-11-03 16:20:11.050 INFO  [Refresh Thread: Equinox Container: 4023060a-c8de-0018-1c3a-ebee784b7a28][HotDeployImpl:226] Deploying sample-servlet-filter-hook from queue
    2018-11-03 16:20:11.052 INFO  [Refresh Thread: Equinox Container: 4023060a-c8de-0018-1c3a-ebee784b7a28][PluginPackageUtil:1001] Reading plugin package for sample-servlet-filter-hook
    03-Nov-2018 16:20:11.066 INFO [Refresh Thread: Equinox Container: 4023060a-c8de-0018-1c3a-ebee784b7a28] org.apache.catalina.core.ApplicationContext.log Initializing Spring root WebApplicationContext
    2018-11-03 16:20:11.093 INFO  [Refresh Thread: Equinox Container: 4023060a-c8de-0018-1c3a-ebee784b7a28][HookHotDeployListener:457] Registering hook for sample-servlet-filter-hook
    Called SampleFilter.init(com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterConfig@7c953747) where hello=world
    2018-11-03 16:20:11.134 INFO  [Refresh Thread: Equinox Container: 4023060a-c8de-0018-1c3a-ebee784b7a28][HookHotDeployListener:533] Hook for sample-servlet-filter-hook is available for use

The servlet container calls your filter's `init` method.  Deploying
`SampleFilter`, for example, invokes its `init` method, which prints this
output:

    Called SampleFilter.init(com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterConfig@7c953747) where hello=world

Visiting the URLs mapped to your servlet filter invokes your filter's `doFilter`
method. Since the sample's servlet filter mapping includes the URL `/web/*`,
visiting http://localhost:8080/web/guest invokes `SampleFilter.doFilter`, which
prints this:

    Called SampleFilter.doFilter(org.apache.catalina.connector.RequestFacade@68be71e0, com.liferay.portal.servlet.filters.absoluteredirects.AbsoluteRedirectsResponse@2b598f1a, com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterChain@72220503) for URI /web/guest

Undeploying your servlet filter `.war` invokes its `destroy()` method. 

Congratulations on filtering requests to your site's URLs. 

## Related Topics [](id=related-topics)

[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies)
