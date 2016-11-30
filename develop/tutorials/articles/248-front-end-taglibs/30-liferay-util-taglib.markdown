# @product@'s liferay-util Taglib

 The `<liferay-util>` taglib is used to pull in other resources into a portlet or theme, it can be used to dictate which resources need to be inserted at the bottom or top of the HTML source.

## Using `<liferay-util: />` Tags [](id=using-liferay-util-tags)

A list of the available `<liferay-util>` tags can be found [here](https://docs.liferay.com/portal/7.0/taglibs/util-taglib/). To use the taglib library you'll need to make sure the following line is in your jsp:

```html
<%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>
```

Now you are good to go! Each taglib has a list of attributes that can be passed to the tag. Some of these are required and some are optional, looking at the documentaiton can help you find which ones you'll need.

Since each of the `<liferay-util>` taglibs are so unique, we'll go over each one briefly

### Using `<liferay-util:body-bottom>` [](id=using-liferay-util-body-bottom)

This is not a self-closing tag. The content placed between the opening and closing of this tag will be moved to the bottom of the `body` tag. When something is passed using this taglib the [body_bottom.jsp](https://github.com/liferay/liferay-portal/blob/master/portal-web/docroot/html/common/themes/body_bottom.jsp#L25-L31) is passed markup and outputs in this jsp. The attribute `outputKey` is the reference key for this content.

Here is an example of using `<liferay-util:body-bottom>`:

```html
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@ page import="com.liferay.product.navigation.product.menu.web.constants.ProductNavigationProductMenuPortletKeys" %>

<liferay-theme:defineObjects />

<%
String portletNamespace = PortalUtil.getPortletNamespace(ProductNavigationProductMenuPortletKeys.PRODUCT_NAVIGATION_PRODUCT_MENU);
%>

<liferay-util:body-bottom outputKey="productMenu">
	<div class="lfr-product-menu-panel sidenav-fixed sidenav-menu-slider" id="<%= portletNamespace %>sidenavSliderId">
		<div class="product-menu sidebar sidenav-menu">
			<liferay-portlet:runtime portletName="<%= ProductNavigationProductMenuPortletKeys.PRODUCT_NAVIGATION_PRODUCT_MENU %>" />
		</div>
	</div>
</liferay-util:body-bottom>
```

### Using `<liferay-util:body-top>` [](id=using-liferay-util-body-top)

This is not a self-closing tag. The content placed between the opening and closing of this tag will be moved to the top of the `body` tag. When something is passed using this taglib the [body_top.jsp](https://github.com/liferay/liferay-portal/blob/master/portal-web/docroot/html/common/themes/body_top.jsp#L25-L31) is passed markup and outputs in this jsp. The attribute `outputKey` is the reference key for this content.

Here is an example of using `<liferay-util:body-top>`:

```html
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-util:body-top outputKey="topContent">
	<div>
		<h1>I'm at the top of the page!</h1>
	</div>
</liferay-util:body-top>
```

### Using `<liferay-util:buffer>` [](id=using-liferay-util-buffer)

This is not a self-closing tag. The content placed between the opening and closing of this tag is saved to the value of the `var` attribute. This allows a developer to build a piece of markup that will can be reused in a jsp.

Here is an example of using `<liferay-util:buffer>`:

```html
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-util:buffer var="myBuffer">
	<small class="text-capitalize text-muted">
		This is my buffer content
	</small>
</liferay-util:buffer>

<div class="container">
	<h1>Welcome!</h1>

	<%= myBuffer %>
</div>

<div class="container">
	<h1>A Wonderful Title!</h1>

	<%= myBuffer %>
</div>
```

### Using `<liferay-util:dynamic-inlude>` [](id=using-liferay-util-dynamic-include)

This taglib allows you to register some content with the `DynamicIncludeRegistry`. Read more about the [OSGi Service Registry here](http://docs.spring.io/osgi/docs/current/reference/html/service-registry.html). It makes it easier for other modules making use of the OSGi register to make use of the content included from the taglib.

Here is an example

```html
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-util:dynamic-include key="/path/to/jsp#pre" />

<div>
	<p>And here we have our content</p>
</div>

<liferay-util:dynamic-include key="/path/to/jsp#post" />
```

### Using `<liferay-util:get-url>` [](id=using-liferay-util-get-url)

This tag scraps the URL provided by the `url` attribute. If a value is provided for the `var` attribute the content from the screen scap is scoped to that variable. Otherwise the scrapped content will be displayed where the taglib is used.

Here is an example

```html
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-util:get-url url="https://www.google.com/" />

<!-- Using var attribute -->

<liferay-util:get-url url="https://www.google.com/" var="google" />

<div>
	<h2>We stole <a href="https://www.google.com/">Google</a>, here it is.</h2>

	<div class="google">
		<%= google %>
	</div>
</div>
```

### Using `<liferay-util:html-bottom>` [](id=using-liferay-util-html-bottom)

This is not a self-closing tag. The content placed between the opening and closing of this tag will be moved to the bottom of the `html` tag. When something is passed using this taglib the [bottom.jsp](https://github.com/liferay/liferay-portal/blob/master/portal-web/docroot/html/common/themes/bottom.jsp#L53-L59) is passed markup and outputs in this jsp. The attribute `outputKey` is the reference key for this content.

Here is an example of using `<liferay-util:html-bottom>`, many times the content passed to this taglib will be javascript:

```html
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-util:html-bottom outputKey="taglib_alert_user">
	<aui:script use="liferay-alert">
		new Liferay.Alert(
			{
				closeable: true,
				message: 'Just saying hello from the &lt;liferay-util:html-bottom&gt; taglib!',
				type: 'success'
			}
		).render(#wrapper);
	</aui:script>
</liferay-util:html-bottom>
```

### Using `<liferay-util:html-top>` [](id=using-liferay-util-html-top)

This is not a self-closing tag. The content placed between the opening and closing of this tag will be moved to the `head` tag. When something is passed using this taglib the [top_head.jsp](https://github.com/liferay/liferay-portal/blob/master/portal-web/docroot/html/common/themes/top_head.jsp#L147-L153) is passed markup and outputs in this jsp. The attribute `outputKey` is the reference key for this content.

Here is an example of using `<liferay-util:html-top>`;

```html
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-util:html-top>
	<link data-senna-track="permanent" href="/path/to/style.css" rel="stylesheet" type="text/css" />
</liferay-util:html-top>
```

### Using `<liferay-util:include>` [](id=using-liferay-util-include)

This tag can be used to include other JSP files in a portlet. It can help for readability in a portlet and a separation of concerns for JSP files. The `page` attribute is required and the value is the path to the JSP or JSPF to be included. The `servletContext` attribute refers to the request context that the included JSP should use. By passing `<%= application %>` to this attribute the included JSP can use the same `request` object and other objects that might be set in the prior JSP.


Here is an example of using `<liferay-util:include>`;

```html
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-util:include page="/path/to/view.jsp" servletContext="<%= application %>" />
```

### Using `<liferay-util:param>` [](id=using-liferay-util-param)

This tag can be used to add a parameter value to a url. It is useful when using in tandem with `<liferay-util:include>` for accessing new parameters values in another JSP.


Here is an example of using `<liferay-util:param>`;

```html
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-util:include page="/path/to/answer.jsp" servletContext="<%= application %>">
	<liferay-util:param name="answer" value="42" />
</liferay-util:include>
```

in `answer.jsp`

```html
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<div>
	<p>The answer to life the universe and everything is <%= ParamUtil.getString(request, "answer") %></p>
</div>
```

### Using `<liferay-util:whitespace-remover>` [](id=using-liferay-util-whitespace-remover)

This taglib is used for removing all whitespace from whatever is included between the opening and closing of the tag.


Here is an example of using `<liferay-util:whitespace-remover>`;

```html
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-util:whitespace-remover>
	<div class="nput-container">
		<label for="myInput">
			Is the &lt;liferay-util:whitespace-remover&gt; taglib fantastic!
		</label>

		<input class="input" id="myInput" name="myInput" type="checkbox">
	</div>
</liferay-util:whitespace-remover>
```