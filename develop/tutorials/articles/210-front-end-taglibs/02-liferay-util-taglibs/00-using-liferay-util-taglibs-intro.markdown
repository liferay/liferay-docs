# Using the Liferay Util Taglib [](id=using-the-liferay-util-taglib)

The Liferay Util taglib is used to pull other resources into a portlet or theme. 
You can use it to specify which resources to insert at the bottom or top of the 
page's HTML. 

To use the Liferay-Util taglib, add the following declaration to your JSP:

    <%@ taglib prefix="liferay-util" uri="http://liferay.com/tld/util" %>

The Liferay-Util taglib is also available via a macro for your FreeMarker theme 
templates and web content templates. Follow this syntax:

    <@liferay_util["tag-name"] attribute="string value" attribute=10 />

This section of tutorials covers the available Liferay Util tags you can use in 
your app to inject content into portlets and themes. 
