# Using Liferay Util Include [](id=using-liferay-util-include)

The include tag lets you include other JSP files in your portlet's JSP, theme, 
or web content. This can increase readability as well as provide separation of 
concerns for JSP files. 

The `page` attribute is required and specifies the path to the JSP or JSPF to 
include. The `servletContext` refers to the request context that the included 
JSP should use. Passing `<%= application %>` to this attribute lets the included 
JSP use the same `request` object as other objects that might be set in the 
prior JSP. 

Below is an example configuration for the `<liferay-util:include>` tag:

    <liferay-util:include 
      page="/relative/path/to/file.jsp" 
      servletContext="<%= application %>"
    />

Now you know how to use the `<liferay-util:include>` tag to include other JSPs 
in your portlets, themes, and web content. 

## Related Topics [](id=related-topics)

[Using the Liferay Util Param Tag](/develop/tutorials/-/knowledge_base/7-1/using-liferay-util-param)

[Using the Liferay Util Dynamic Include Tag](/develop/tutorials/-/knowledge_base/7-1/using-liferay-util-dynamic-include)

[Using the Liferay Front-End Taglibs](/develop/tutorials/-/knowledge_base/7-1/using-liferay-frontend-taglibs-in-your-portlet)
