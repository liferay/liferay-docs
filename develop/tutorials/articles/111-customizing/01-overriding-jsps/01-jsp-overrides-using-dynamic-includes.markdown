# JSP Overrides Using Dynamic Includes [](id=jsp-overrides-using-dynamic-includes)

The
[`liferay-util:dynamic-include` tag](@platform-ref@/7.0/taglibs/util-taglib/liferay-util/dynamic-include.html)
is placeholder into which you can inject content. Every JSP's dynamic include
tag can be dynamically replaced with content. All you have to do is create a
module that has content you want to insert, register that content with the
dynamic include tag you want to replace, and deploy your module. 

+$$$

**Note**: If the JSP you want to override has no `liferay-util:dynamic-include`
tags, you must use a different override approach, such as
[portlet filters](TODO).

$$$

We'll demonstrate how dynamic includes work using the Blogs entries. For reference, you can download the
[example module](https://dev.liferay.com/documents/10184/656312/example-dynamic-include-blogs-master.zip). 

1.  Find the `liferay-util:dynamic-include` tag you want to replace in the
    JSP and note the tag's key. 

    The Blogs app's `view_entry.jsp` has a dynamic include tag at the top and
    another at the very bottom. 

        <%@ include file="/blogs/init.jsp" %>

        <liferay-util:dynamic-include key="com.liferay.blogs.web#/blogs/view_entry.jsp#pre" />

            ... JSP content is here

        <liferay-util:dynamic-include key="com.liferay.blogs.web#/blogs/view_entry.jsp#post" />

    Here are the Blogs view entry dynamic include keys:

    - `key="com.liferay.blogs.web#/blogs/view_entry.jsp#pre"`
    - `key="com.liferay.blogs.web#/blogs/view_entry.jsp#post"`

2.  Create a module for dynamically including your content and make sure it
    specifies compile-only dependencies like these Gradle dependencies:

        dependencies {
        	compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
        	compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
        	compileOnly group: "com.liferay", name: "com.liferay.petra.string", version: "1.0.0"
        	compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
        	compileOnly group: "org.osgi", name: "osgi.cmpn", version: "6.0.0"
        }

3.  Create an OSGi component class that implements the
    [`DynamicInclude` interface](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/servlet/taglib/DynamicInclude.html).

    Here's an example dynamic include implementation for Blogs:

        import java.io.IOException;
        import java.io.PrintWriter;
        
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        
        import org.osgi.service.component.annotations.Component;
        
        import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
        
        @Component(
        	immediate = true,
        	service = DynamicInclude.class
        )
        public class BlogsDynamicInclude implements DynamicInclude {
        
        	@Override
        	public void include(
        			HttpServletRequest request, HttpServletResponse response,
        			String key)
        		throws IOException {
        
        		PrintWriter printWriter = response.getWriter();
        
        		printWriter.println(
        			"<h2>Added by Blogs Dynamic Include!</h2><br />");
        	}
        
        	@Override
        	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
        		dynamicIncludeRegistry.register(
        			"com.liferay.blogs.web#/blogs/view_entry.jsp#pre");
        	}
        
        }
        
    Giving the class a `@Component` annotation that has the service attribute
    `service = DynamicInclude.class` makes the class a `DynamicInclude` service
    component.

        @Component(
            immediate = true,
            service = DynamicInclude.class
        )

    In the `include` method, you write the content you want to replace a dynamic
    include tag with. The example `include` method writes a heading. 

        @Override
        public void include(
                HttpServletRequest request, HttpServletResponse response,
                String key)
            throws IOException {

            PrintWriter printWriter = response.getWriter();

            printWriter.println(
            "<h2>Added by Blogs Dynamic Include!</h2><br />");
        }

    In the `register` method, specify the dynamic include tag you want to
    replace. The example register method targets the dynamic include at the top
    of the Blogs `view_entry.jsp`. 

        @Override
    	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
    		dynamicIncludeRegistry.register(
    			"com.liferay.blogs.web#/blogs/view_entry.jsp#pre");
    	}
 
Once you've
[deployed your module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module),
the overridden JSP dynamically includes your content. Congratulations on
injecting dynamic content into a JSP! 
