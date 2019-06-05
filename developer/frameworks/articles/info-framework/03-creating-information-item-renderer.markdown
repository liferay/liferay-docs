---
header-id: custom-rendering-of-information-with-infoitemrenderer
---

# Custom rendering of information with `InfoItemRenderer`

[TOC levels=1-4]

To demonstrate the `InfoItemRenderer`, implement a class that can render 
information provided through a custom class called `MyOrder`. 

1.  Create a [module](/docs/7-2/reference/-/knowledge_base/r/creating-a-project) named `my-order`.

2.  In `my-order`, create a package named `com.liferay.docs.info.myorder`

3.  In the package, create a class that implements `InfoItemRenderer` and 
    register it as a component.

    ```java
    @Component(service = InfoItemRenderer.class)
    public class MyOrderRenderer implements InfoItemRenderer<MyOrder> {
        @Override
        public void render(
           MyOrder myOrder, HttpServletRequest httpServletRequest,
           HttpServletResponse httpServletResponse) {

        }
    }
    ```

4.  Next you must add the logic for the `render()` method. 

    ```java
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
    ```

For this example you rendered everything through a `StringBundler`. In more 
complex cases, you would use JSPs or another templating technology.

The renderer is ready for use! In @product-ver@, Info Item Renderers are not
widely used, but the usages and application will grow in future releases.
