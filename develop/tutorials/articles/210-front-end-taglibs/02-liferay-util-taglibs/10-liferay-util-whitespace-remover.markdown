# Using Liferay Util Whitespace Remover [](id=using-liferay-util-whitespace-remover)

The whitespace remover tag removes line breaks and tabs from code blocks 
included between the opening and closing of the tag. Below is an example 
configuration for the `<liferay-util:whitespace-remover>` tag:

with remover:

    <liferay-util:whitespace-remover>
    	<p>Here is some text with        tabs.</p>
    </liferay-util:whitespace-remover>

result:

    Here is some text withtabs.

Now you know how to use the `<liferay-util:whitespace-remover>` tag to ensure 
that your code formatting is consistent. 

## Related Topics [](id=related-topics)

[Using the Liferay Util Param Tag](/develop/tutorials/-/knowledge_base/7-1/using-liferay-util-param)

[Using the Liferay Util Buffer Tag](/develop/tutorials/-/knowledge_base/7-1/using-liferay-util-buffer)

[Using the AUI Taglib](/develop/tutorials/-/knowledge_base/7-1/using-aui-taglibs-in-your-portlets)
