# Using Liferay Util HTML Bottom [](id=using-liferay-util-html-bottom)

The HTML bottom tag is not a self-closing tag. Content placed between the 
opening and closing of this tag is moved to the bottom of the `<html>` tag. When 
something is passed using this taglib, the 
[bottom.jsp](https://github.com/liferay/liferay-portal/blob/master/portal-web/docroot/html/common/themes/bottom.jsp#L53-L59) 
is passed markup and outputs in this JSP. 

The example configuration below uses the `<liferay-util:html-bottom>` tag to 
include JavaScript (a common use case) provided by the portlet's bundle:
    
    <liferay-util:html-bottom outputKey="addscript">

        <script src="/o/my-liferay-util-portlet/js/my_custom_javascript.js" 
        type="text/javascript"></script>

    </liferay-util:html-bottom>

Now you know how to use the `<liferay-util:html-bottom>` tag to include 
additional resources in the bottom of the page's HTML tag. 

## Related Topics [](id=related-topics)

[Using the Liferay Util HTML Body Bottom Tag](/develop/tutorials/-/knowledge_base/7-1/using-liferay-util-body-bottom)

[Using the Liferay Util HTML Top Tag](/develop/tutorials/-/knowledge_base/7-1/using-liferay-util-html-top)

[Using the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/7-1/using-the-liferay-ui-taglib-in-your-portlets)