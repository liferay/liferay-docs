# Using Liferay Util Body Top [](id=using-liferay-util-body-top)

The body top tag is not a self-closing tag. The content placed between the 
opening and closing of this tag is moved to the top of the `body` tag. When 
something is passed using this taglib, the 
[body_top.jsp](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-web/docroot/html/common/themes/body_top.jsp#L25-L31) 
is passed markup and outputs in this JSP. 

The example configuration below uses the `<liferay-util:body-top>` tag to 
include JavaScript provided by the portlet's bundle:

    <liferay-util:body-top outputKey="contenttop" >
    	<script 
      src="/o/my-liferay-util-portlet/js/my_custom_javascript_body_top.js" 
      type="text/javascript"></script>
    </liferay-util:body-top>

Now you know how to use the `<liferay-util:body-top>` tag to include additional 
resources in the top of the page's body. 

## Related Topics [](id=related-topics)

[Using the Liferay Util HTML Body Bottom Tag](/develop/tutorials/-/knowledge_base/7-1/using-liferay-util-body-bottom)

[Using the Liferay Util HTML Bottom Tag](/develop/tutorials/-/knowledge_base/7-1/using-liferay-util-html-bottom)

[Using the Clay Taglib](/develop/tutorials/-/knowledge_base/7-1/using-the-clay-taglib-in-your-portlets)