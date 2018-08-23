# JSP Overrides Using Portlet Filters [](id=jsp-overrides-using-portlet-filters)

Portlet filters let you intercept portlet requests before they're processed and
portlet responses after they're processed but before they're sent back to the
client. You can operate on the request and / or response to modify the JSP
content. Unlike dynamic includes, portlet filters give you access to all the
content sent back to the client. 

This demonstration uses a portlet filter to modify content in Liferay's Blogs
portlet. For reference, you can download the 
[example module](https://dev.liferay.com/documents/10184/656312/example-portlet-filter-customize-jsp-master.zip).

1.  Create a new module and make sure it specifies these compile-only
    dependencies, shown here in Gradle format:

        dependencies {
            compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
            compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
            compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
            compileOnly group: "org.osgi", name: "osgi.cmpn", version: "6.0.0"
        }

2.  Create an OSGi component class that implements the
    `javax.portlet.filter.RenderFilter` interface. 

    Here's an example portlet filter implementation for Blogs:

        import java.io.IOException;

        import javax.portlet.PortletException;
        import javax.portlet.RenderRequest;
        import javax.portlet.RenderResponse;
        import javax.portlet.filter.FilterChain;
        import javax.portlet.filter.FilterConfig;
        import javax.portlet.filter.PortletFilter;
        import javax.portlet.filter.RenderFilter;
        import javax.portlet.filter.RenderResponseWrapper;

        import org.osgi.service.component.annotations.Component;

        import com.liferay.portal.kernel.util.PortletKeys;

        @Component(
            immediate = true,
            property = {
                    "javax.portlet.name=" + PortletKeys.BLOGS
            },
            service = PortletFilter.class
        )
        public class BlogsRenderFilter implements RenderFilter {

            @Override
            public void init(FilterConfig config) throws PortletException {

            }

            @Override
            public void destroy() {

            }

            @Override
            public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
                    throws IOException, PortletException {
                
                RenderResponseWrapper renderResponseWrapper = new BufferedRenderResponseWrapper(response);

                chain.doFilter(request, renderResponseWrapper);

                String text = renderResponseWrapper.toString();
                
                if (text != null) {
                    String interestingText = "<input  class=\"field form-control\"";

                    int index = text.lastIndexOf(interestingText);

                    if (index >= 0) {
                        String newText1 = text.substring(0, index);
                        String newText2 = "\n<p>Added by Blogs Render Filter!</p>\n";
                        String newText3 = text.substring(index);
                        
                        String newText = newText1 + newText2 + newText3;
                        
                        response.getWriter().write(newText);
                    }
                }
            }

        }

3.  Make your class a `PortletFilter` service component by giving it the
    `@Component` annotation that has the service attribute `service
    = PortletFilter.class`. Target the portlet whose content you're overriding
    by assigning it a javax.portlet.name property that's the same as your
    portlet's key. Here's the example `@Component` annotation:

        @Component(
           immediate = true,
           property = {
                   "javax.portlet.name=" + PortletKeys.BLOGS
           },
           service = PortletFilter.class
        )

4.  Override the `doFilterMethod` to operate on the request or response to
    produce the content you want. The example appends a paragraph stating `Added
    by Blogs Render Filter!` to the portlet content:

        @Override
        public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
                throws IOException, PortletException {

            RenderResponseWrapper renderResponseWrapper = new BufferedRenderResponseWrapper(response);

            chain.doFilter(request, renderResponseWrapper);

            String text = renderResponseWrapper.toString();

            if (text != null) {
                String interestingText = "<input  class=\"field form-control\"";

                int index = text.lastIndexOf(interestingText);

                if (index >= 0) {
                    String newText1 = text.substring(0, index);
                    String newText2 = "\n<p>Added by Blogs Render Filter!</p>\n";
                    String newText3 = text.substring(index);

                    String newText = newText1 + newText2 + newText3;

                    response.getWriter().write(newText);
                }
            }
        }

    The example uses a `RenderResponseWrapper` extension class called
    `BufferedRenderResponseWrapper`. `BufferedRenderResponseWrapper` is a helper
    class whose `toString` method returns the current response text and whose
    `getWriter` method lets you write data to the response before it's sent back to
    the client. 

        import java.io.CharArrayWriter;
        import java.io.IOException;
        import java.io.OutputStream;
        import java.io.PrintWriter;

        import javax.portlet.RenderResponse;
        import javax.portlet.filter.RenderResponseWrapper;

        public class BufferedRenderResponseWrapper extends RenderResponseWrapper {

            public BufferedRenderResponseWrapper(RenderResponse response) {
                super(response);

                charWriter = new CharArrayWriter();
            }

            public OutputStream getOutputStream() throws IOException {
                if (getWriterCalled) {
                    throw new IllegalStateException("getWriter already called");
                }

                getOutputStreamCalled = true;

                return super.getPortletOutputStream();
            }

            public PrintWriter getWriter() throws IOException {
                if (writer != null) {
                    return writer;
                }

                if (getOutputStreamCalled) {
                    throw new IllegalStateException("getOutputStream already called");
                }

                getWriterCalled = true;

                writer = new PrintWriter(charWriter);

                return writer;
            }

            public String toString() {
                String s = null;

                if (writer != null) {
                    s = charWriter.toString();
                }

                return s;
            }

            protected CharArrayWriter charWriter;
            protected PrintWriter writer;
            protected boolean getOutputStreamCalled;
            protected boolean getWriterCalled;

        }

Once you've
[deployed your module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#building-and-deploying-a-module),
the portlet's JSP shows your custom content.

Your portlet filter operates directly on portlet response content. Unlike
dynamic includes, portlet filters let you work with all of a JSP's content. 
