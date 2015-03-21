# Dynamically Adding JSF Portlets to Liferay Portal [](id=dynamically-adding-jsf-portlets-to-liferay-portal)

For this tutorial, you'll learn how to dynamically add portlets to Liferay
Portal pages. Here are the places in which you can add a portlet: 

- Inside the FreeMarker template or [Velocity
  template](http://www.liferay.com/community/wiki/-/wiki/Main/Embedding+a+portlet+in+the+theme)
  of a theme with `$theme.runtime()` 
- Inside a [layout template](http://www.liferay.com/community/wiki/-/wiki/Main/Add+runtime+portlets+to+a+layout)
  with `$processor.processPortlet()` 
- Inside a JSP with `<liferay-portlet:runtime />` 

Unfortunately, as described in
[FACES-244](http://issues.liferay.com/browse/FACES-244), dynamically adding JSF
portlets doesn't work very well. It's actually not limited to JSF portlets--
this problem happens with any portlet that needs to add JS/CSS resources to
the `<head>`...`</head>` section of the portal page. Since JSF portlets require
the `jsf.js` resource to perform Ajax requests, the `jsf.js` resource must be
loaded when the portal page is initially rendered. 

There are two workarounds: 

1.  For plain JSF portlets, add a `<link />` element for the `jsf.js` resource
    in the `<head>`...`</head>` section of the `portal_normal.vm` or
    `portal_normal.ftl` file in the theme. The first few lines of `jsf.js`
    prevent double-instantiation in case it gets included multiple times on a
    page. This can occur when a JSF portlet is dynamically included and another
    JSF portlet is added statically. Unfortunately, this approach doesn't work
    for PrimeFaces, since `primefaces.js` does not prevent double-instantiation.

2.  Use an IFrame: 

        <div id="${request.portlet-namespace}my_runtime_portlet">
            <script type="text/javascript">
                AUI().use('liferay-portlet-url', 'aui-resize-iframe', function(A) {
                var portletURL = Liferay.PortletURL.createRenderURL();
                portletURL.setPortletId('1_WAR_mypluginportlet');
                portletURL.setPlid(themeDisplay.getPlid());
                var html = '<iframe frameborder="0" id="${request.portlet-namespace}my_runtime_portlet_frame" src="' + portletURL.toString() + '" scrolling="no" width="100%"></iframe>';
                A.one('#${request.portlet-namespace}my_runtime_portlet').append(html);
                A.one('#${request.portlet-namespace}my_runtime_portlet_frame').plug(A.Plugin.ResizeIframe);
                });
            </script>
        </div>
 
In order to avoid the *You do not have the roles required to access this
portlet* error message, add the following directive to the
`WEB-INF/liferay-portlet.xml` descriptor: 

    <add-default-resource>true</add-default-resource>

Alternatively, you can place the portlet alone on a hidden portal page and
then use a portlet URL referring to the `plid` of the hidden portal page. This
approach is more appropriate for portlets that perform security-sensitive
actions. 

Note, when an end-user dynamically adds any JSF 2 portlet to a portal page, the
JSF 2 standard `jsf.js` JavaScript code is not automatically executed. In order
for the `jsf.js` to be executed, the page must be fully refreshed. 

As a workaround, Liferay Portal provides configuration parameters that allow the
developer to specify that a full page refresh is required. Specifying this
ensures that JSF 2 is properly initialized. You specify the required
`<render-weight>` and `<ajaxable>` parameter elements in the
`WEB-INF/liferay-portlet.xml` configuration file. 

    <liferay-portlet-app>
        <portlet>
            <portlet-name>my_portlet</portlet-name>
            <instanceable>false</instanceable>
            <render-weight>1</render-weight>
            <ajaxable>false</ajaxable>
        </portlet>
    </liferay-portlet-app>

Now, you know the options you have in dynamically adding your JSF portlets at
runtime. 

**Related Topics**

[Liferay Faces Alloy UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-alloy-ui-components)

[Liferay Faces Bridge UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-bridge-ui-components)

[Understanding Liferay Faces Portal](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-portal)

[Understanding Liferay Faces Alloy](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-alloy)
