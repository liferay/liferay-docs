---
header-id: using-liferay-util-param
---

# Using Liferay Util Param

[TOC levels=1-4]

The param tag lets you set a parameter for an 
[included JSP page](/docs/7-2/reference/-/knowledge_base/r/using-liferay-util-include). 
This configuration requires two JSPs. JSP A, the main view of the app, includes 
JSP B and sets its parameter value. This lets you dynamically set content when 
you include the JSP. 

For example, say you have your main functionality in `my-app.jsp`, and you have 
additional functionality provided by `more-content.jsp`. You could have the 
example configuration shown below:

`more-content.jsp`:

```html
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%
String answer = ParamUtil.getString(request, "answer");
%>

<div>
  <p>The answer to life, the universe and everything is <%= answer %>.</p>
</div>
```

Then in `my-app.jsp`, you can include `more-content.jsp` and set the value of 
the `answer` parameter:

```html    
<liferay-util:include page="/path/to/more-content.jsp" servletContext="<%= application %>">
  <liferay-util:param name="answer" value="42" />
</liferay-util:include>
```

This results in the following output in `my-app.jsp`:

```html
The answer to life, the universe and everything is 42.
```

Now you know how to use the `<liferay-util:param>` tag to set parameters for 
included JSPs. You can use this approach to include common reusable pieces of 
code in your apps. 

## Related Topics

- [Using the Liferay Util Include Tag](/docs/7-2/reference/-/knowledge_base/r/using-liferay-util-include)
- [Using the Liferay Util Body Top Tag](/docs/7-2/reference/-/knowledge_base/r/using-liferay-util-body-top)
- [Using the Chart Taglib](/docs/7-2/reference/-/knowledge_base/r/using-the-chart-taglib-in-your-portlets)
