# Using Liferay Util Buffer [](id=using-liferay-util-buffer)

The buffer tag is not a self-closing tag. The content placed between the opening 
and closing of this tag is saved to a buffer and its output is assigned to the 
Java variable declared with the tag's `var` attribute. The output is returned as 
a String, letting you post-process it. For example, you can use this to 
[override a JSP's existing contents](/develop/tutorials/-/knowledge_base/7-1/jsp-overrides-using-osgi-fragments#provide-the-overridden-jsp). 

The example below saves the link's generated markup to a buffer and then uses 
the returned string as the argument for a `liferay-ui:message` key:

    <liferay-util:buffer
    		var="linkContent"
    >
    		<aui:a 
    			href="https://dev.liferay.com/en/develop/tutorials" 
    			target="_blank">the Liferay Developer Network's documentation
    		</aui:a>
    </liferay-util:buffer>

    <liferay-ui:message 
    		arguments="<%= linkContent %>" 
    		key="see-x-for-more-information" 
    		translateArguments="<%= false %>" 
    />

Now you know how to use the `<liferay-util:buffer>` tag to save content to a 
buffer. 

![Figure 1: You can use the Liferay Util Buffer tag to save pieces of markup to reuse in your JSP.](../../../images/liferay-util-buffer.png)
    
## Related Topics [](id=related-topics)

[JSP Overrides Using OSGi Fragments](/develop/tutorials/-/knowledge_base/7-1/jsp-overrides-using-osgi-fragments#provide-the-overridden-jsp)

[Using the Liferay Util Param Tag](/develop/tutorials/-/knowledge_base/7-1/using-liferay-util-param)

[Using the Liferay Front-End Taglibs](/develop/tutorials/-/knowledge_base/7-1/using-liferay-frontend-taglibs-in-your-portlet)
