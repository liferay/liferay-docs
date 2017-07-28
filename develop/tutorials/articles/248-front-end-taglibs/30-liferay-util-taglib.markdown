# Using the Liferay Util Taglib [](id=using-the-liferay-util-taglib)

The `<liferay-util>` taglib is used to pull in other resources into a portlet 
or theme, it can be used to dictate which resources need to be inserted at the 
bottom or top of the HTML source.

## Using Liferay Util Tags [](id=using-liferay-util-tags)

A list of the available `<liferay-util>` tags can be found [here](@platform-ref@/7.0-latest/taglibs/util-taglib/). 
To use the Liferay-Util taglib library you must add the following declaration to
your JSP:

    <%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>

Each taglib has a list of attributes that can be passed to the tag. Some of 
these are required and some are optional. See the [taglibdocs](https://docs.liferay.com/portal/7.0-latest/taglibs/util-taglib/liferay-util/tld-summary.html) 
to view the requirements for each tag.

Since each of the `<liferay-util>` taglibs is unique, each tag is covered
briefly in the sections that follow.

### Using Liferay Util Body Bottom [](id=using-liferay-util-body-bottom)

The `<liferay-util:body-bottom>` tag is not a self-closing tag. The content 
placed between the opening and closing of this tag is moved to the bottom 
of the `body` tag. When something is passed using this taglib, the 
[body_bottom.jsp](https://github.com/liferay/liferay-portal/blob/master/portal-web/docroot/html/common/themes/body_bottom.jsp#L25-L31) 
is passed markup and outputs in this JSP. The attribute `outputKey` is the 
reference key for this content.

Below is an example configuration for the `<liferay-util:body-bottom>` tag:

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
            <div class="lfr-product-menu-panel sidenav-fixed sidenav-menu-slider" 
            id="<%= portletNamespace %>sidenavSliderId">
                    <div class="product-menu sidebar sidenav-menu">
                            <liferay-portlet:runtime portletName="<%= ProductNavigationProductMenuPortletKeys.PRODUCT_NAVIGATION_PRODUCT_MENU %>" />
                    </div>
            </div>
    </liferay-util:body-bottom>

### Using Liferay Util Body Top [](id=using-liferay-util-body-top)

The `<liferay-util:body-top>` tag is not a self-closing tag. The content placed 
between the opening and closing of this tag is moved to the top of the 
`body` tag. When something is passed using this taglib the [body_top.jsp](https://github.com/liferay/liferay-portal/blob/master/portal-web/docroot/html/common/themes/body_top.jsp#L25-L31) 
is passed markup and outputs in this JSP. The attribute `outputKey` is the 
reference key for this content.

Below is an example configuration for the `<liferay-util:body-top>` tag:

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
    
    <liferay-util:body-top outputKey="topContent">
            <div>
                    <h1>I'm at the top of the page!</h1>
            </div>
    </liferay-util:body-top>

### Using Liferay Util Buffer [](id=using-liferay-util-buffer)

`<liferay-util:buffer>` is not a self-closing tag. The content placed between 
the opening and closing of this tag is saved to the value of the `var` attribute. 
This allows a developer to build a piece of markup that can be reused in a JSP.

Below is an example configuration for the `<liferay-util:buffer>` tag:

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

### Using Liferay Util Dynamic Include [](id=using-liferay-util-dynamic-include)

The `<liferay-util:dynamic-inlude>` tag allows you to register some content with 
the `DynamicIncludeRegistry`. You can read more about the OSGi Service Registry 
[here](http://docs.spring.io/osgi/docs/current/reference/html/service-registry.html). 
It's easier for modules using the OSGi registry to use the content that you
include with this tag.

Below is an example configuration for the `<liferay-util:dynamic-inlude>` tag:

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
    
    <liferay-util:dynamic-include key="/path/to/jsp#pre" />
    
    <div>
            <p>And here we have our content</p>
    </div>
    
    <liferay-util:dynamic-include key="/path/to/jsp#post" />

### Using Liferay Util Get URL [](id=using-liferay-util-get-url)

The `<liferay-util:get-url>` tag scrapes the URL provided by the `url` attribute. 
If a value is provided for the `var` attribute, the content from the screen 
scrape is scoped to that variable. Otherwise, the scraped content is 
displayed where the taglib is used.

Below is a basic example configuration for the `<liferay-util:get-url>` tag:

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
    
    <liferay-util:get-url url="https://www.google.com/" />
 
Here is an example that uses the `var` attribute:

    <liferay-util:get-url url="https://www.google.com/" var="google" />
    
    <div>
            <h2>We stole <a href="https://www.google.com/">Google</a>, here it is.</h2>
    
            <div class="google">
                    <%= google %>
            </div>
    </div>

### Using Liferay Util HTML Bottom [](id=using-liferay-util-html-bottom)

The `<liferay-util:html-bottom>` tag is not a self-closing tag. The content 
placed between the opening and closing of this tag will be moved to the bottom 
of the `html` tag. When something is passed using this taglib the [bottom.jsp](https://github.com/liferay/liferay-portal/blob/master/portal-web/docroot/html/common/themes/bottom.jsp#L53-L59) 
is passed markup and outputs in this JSP. The attribute `outputKey` is the 
reference key for this content.

Below is an example of using `<liferay-util:html-bottom>`. Commonly, JavaScript
is passed to this tag:

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
    
    <liferay-util:html-bottom outputKey="taglib_alert_user">
            <aui:script use="liferay-alert">
                    new Liferay.Alert(
                            {
                                    closeable: true,
                                    message: 'Just saying hello from the 
                                    &lt;liferay-util:html-bottom&gt; taglib!',
                                    type: 'success'
                            }
                    ).render(#wrapper);
            </aui:script>
    </liferay-util:html-bottom>

### Using Liferay Util HTML Top [](id=using-liferay-util-html-top)

The `<liferay-util:html-top>` tag is not a self-closing tag. The content placed 
between the opening and closing of this tag is moved to the `head` tag. 
When something is passed using this taglib the [top_head.jsp](https://github.com/liferay/liferay-portal/blob/master/portal-web/docroot/html/common/themes/top_head.jsp#L147-L153) 
is passed markup and outputs in this JSP. The attribute `outputKey` is the 
reference key for this content.

Below is an example configuration for the `<liferay-util:html-top>` tag:

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
    
    <liferay-util:html-top>
            <link data-senna-track="permanent" href="/path/to/style.css" rel="stylesheet" type="text/css" />
    </liferay-util:html-top>

### Using Liferay Util Include [](id=using-liferay-util-include)

The `<liferay-util:include>` tag can be used to include other JSP files in a 
portlet. This can increase readability as well as provide separation of 
concerns for JSP files.

Below is an explanation of some of the available attributes:

- `page`: This attribute is required. The value of this attribute is the path 
  to the JSP or JSPF to be included.

- `servletContext`: Refers to the request context that the included JSP should 
  use. Passing `<%= application %>` to this attribute allows the included JSP to 
  use the same `request` object and other objects that might be set in the prior 
  JSP.

Below is an example configuration for the `<liferay-util:include>` tag:

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
    
    <liferay-util:include page="/path/to/view.jsp" servletContext="<%= application %>" />

### Using Liferay Util Param [](id=using-liferay-util-param)

The `<liferay-util:param>` tag can be used to add a parameter value to a URL. 
This tag is best used when combined with the `<liferay-util:include>` tag for 
accessing new parameter values in another JSP.

Below is an example configuration for the `<liferay-util:param>` tag:

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
    
    <liferay-util:include page="/path/to/answer.jsp" servletContext="<%= application %>">
            <liferay-util:param name="answer" value="42" />
    </liferay-util:include>

The new parameter can then be used in `answer.jsp` like this:

    <%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
    
    <div>
            <p>The answer to life the universe and everything is 
            <%= ParamUtil.getString(request, "answer") %></p>
    </div>

### Using Liferay Util Whitespace Remover [](id=using-liferay-util-whitespace-remover)

The `<liferay-util:whitespace-remover>` tag is used for removing all whitespace 
from whatever is included between the opening and closing of the tag.

Below is an example configuration for the `<liferay-util:whitespace-remover>` 
tag:

    <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
    
    <liferay-util:whitespace-remover>
            <div class="input-container">
                    <label for="myInput">
                            Is the &lt;liferay-util:whitespace-remover&gt; taglib 
                            fantastic!
                    </label>
    
                    <input class="input" id="myInput" name="myInput" type="checkbox">
            </div>
    </liferay-util:whitespace-remover>

Now you know how to use the Liferay-Util taglib!

## Related Topics [](id=related-topics)

[Loading Modules with the AUI Script in Liferay](/develop/tutorials/-/knowledge_base/7-0/loading-modules-with-aui-script-in-liferay)

[Using the Liferay UI Taglib](/develop/tutorials/-/knowledge_base/7-0/using-the-liferay-ui-taglib)
