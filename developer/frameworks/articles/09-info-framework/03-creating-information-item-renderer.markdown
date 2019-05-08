# Custom rendering of information with InfoItemRenderer

To demonstrate the `InfoItemRender`, implement a class that can render 
information provided through a custom class called MyOrder. 

1.  Create a [module](link-create-module) named `my-order`.

2.  Inside of `my-order`, create a package named `com.liferay.docs.info.myorder`

3.  Inside the package, create a class that implements `InfoItemRenderer` and 
    register it as a component.
    
        @Component(service = InfoItemRenderer.class)
        public class MyOrderRenderer implements InfoItemRenderer<MyOrder> {
            @Override
            public void render(
               MyOrder myOrder, HttpServletRequest httpServletRequest,
               HttpServletResponse httpServletResponse) {

            }
        }

4.  Next you must add the logic for the `render()` method. 

        @Override
        public void render(
           MyOrder myOrder, HttpServletRequest httpServletRequest,
           HttpServletResponse httpServletResponse) {

           StringBundler sb = new StringBundler(3);

           sb.append("<ul>");
           sb.append("<li>By: " + myOrder.getBy());
           sb.append("<li>When: " + myOrder.getWhen());
           sb.append("<li>Items: " + myOrder.getItems());
           sb.append("</ul>");

           try {
              PrintWriter printWriter = httpServletResponse.getWriter();

              printWriter.write(sb.toString());
           }
           catch (IOException ioe) {
              throw new RuntimeException(ioe);
           }
        }
        
    For this example you rendered everything through an StringBundler. In more 
    complex cases, you would use JSPs or another templating technology rather 
    than having it all inside of the Java class.

That’s it, the renderer is ready for use! In @product-ver@, Info Item Renderers 
are not widely used, but the usages and application will grow in future 
releases.
