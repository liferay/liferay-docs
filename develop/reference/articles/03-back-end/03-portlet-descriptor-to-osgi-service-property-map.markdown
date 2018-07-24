# Portlet Descriptor to OSGi Service Property Map [](id=portlet-descriptor-to-osgi-service-property-map)

This article maps portlet XML descriptor values to OSGi service properties for
publishing OSGi Portlets. 

OSGi service definitions can use properties. OSGi service properties centralize
and simplify portlet configuration. They are typically represented as key-value
pairs or, more generally, as a Map-like object.

Portlet spec property keys are prefixed by

	javax.portlet.

Liferay property keys are prefixed by

	com.liferay.portlet.

The mappings essentially flatten what is found in the XML descriptor. The
property names resemble the original descriptor names. 

This article covers these descriptor mappings:

- [JSR-168 & JSR-286 Descriptor Mappings from `portlet.xml`](#jsr-168-jsr-286-descriptor-mappings)

- [Liferay Descriptor Mappings](#liferay-descriptor-mappings)

    - [From `liferay-display.xml`](#liferay-display)

    - [From `liferay-portlet.xml`](#liferay-portlet)

The standard portlet descriptor mappings are first. 

## JSR-168 & JSR-286 Descriptor Mappings [](id=jsr-168-jsr-286-descriptor-mappings)

**Note:** XPath notation derived from the **Portlet XSD** [4](#four) is used in
this document for simplicity.

| portlet.xml XPath | OSGi Portlet Service Property|
|----|----|
|`/portlet-app/container-runtime-option`|not supported|
|`/portlet-app/custom-portlet-mode`|not supported|
|`/portlet-app/custom-window-state`|not supported|
|`/portlet-app/default-namespace`|`javax.portlet.default-namespace=<String>`|
|`/portlet-app/event-definition`|not supported|
|`/portlet-app/filter`<br/>`/portlet-app/filter/init-param/name`<br/>`/portlet-app/filter-mapping`|[3](#three)<br/>`javax.portlet.init-param.<name>=<value>` [3](#three), [9](#nine)<br/>[3](#three)|
|`/portlet-app/listener`|not supported<br/>?`javax.portlet.PortletURLGenerationListener`?|
|`/portlet-app/public-render-parameter`|not supported|
|`/portlet-app/resource-bundle`|not supported|
|`/portlet-app/security-constraint`|not supported|
|`/portlet-app/user-attribute`|not supported|
|`/portlet-app/version`|`javax.portlet.version=<value>`|
|`/portlet-app/portlet/async-supported`|`javax.portlet.async-supported=<boolean>`|
|`/portlet-app/portlet/cache-scope`|not supported|
|`/portlet-app/portlet/container-runtime-option`|`javax.portlet.container-runtime-option.<name>=<value>` [2](#two)|
|`/portlet-app/portlet/dependency`|`javax.portlet.dependency=<name>;<scope>;<version>` [2](#two), [6](#six)|
|`/portlet-app/portlet/description`|`javax.portlet.description=<String>`|
|`/portlet-app/portlet/display-name`|`javax.portlet.display-name=<String>`|
|`/portlet-app/portlet/expiration-cache`|`javax.portlet.expiration-cache=<int>`|
|`/portlet-app/portlet/init-param/name`|`javax.portlet.init-param.<name>=<value>`|
|`/portlet-app/portlet/multipart-config/file-size-threshold`|`javax.portlet.multipart.file-size-threshold=<Integer>`|
|`/portlet-app/portlet/multipart-config/location`|`javax.portlet.multipart.location=<String>`|
|`/portlet-app/portlet/multipart-config/max-file-size`|`javax.portlet.multipart.max-file-size=<Long>`|
|`/portlet-app/portlet/multipart-config/max-request-size`|`javax.portlet.multipart.max-request-size=<Long>`|
|`/portlet-app/portlet/portlet-class`|[1](#one)|
|`/portlet-app/portlet/portlet-info/keywords`|`javax.portlet.info.keywords=<String>`|
|`/portlet-app/portlet/portlet-info/short-title`|`javax.portlet.info.short-title=<String>`|
|`/portlet-app/portlet/portlet-info/title`|`javax.portlet.info.title=<String>`|
|`/portlet-app/portlet/portlet-name`|`javax.portlet.name=<String>`|
|`/portlet-app/portlet/portlet-preferences`|`javax.portlet.preferences=<String>`<br/>OR<br/>`javax.portlet.preferences=classpath:<path_to_file_in_jar>`|
|`/portlet-app/portlet/resource-bundle`|`javax.portlet.resource-bundle=<String>`|
|`/portlet-app/portlet/security-role-ref`|`javax.portlet.security-role-ref=<String>[,<String>]`[2](#two)|
|`/portlet-app/portlet/supported-locale`|`javax.portlet.supported-locale=<String>` [2](#two)|
|`/portlet-app/portlet/supported-processing-event/name`|`javax.portlet.supported-processing-event=<String>`<br/>OR<br/>`javax.portlet.supported-processing-event=<String>;<QName>`[2](#two)|
|`/portlet-app/portlet/supported-public-render-parameter`|`javax.portlet.supported-public-render-parameter=<String>`[2](#two)|
|`/portlet-app/portlet/supported-publishing-event`|`javax.portlet.supported-publishing-event=<String>`<br/>OR<br/>`javax.portlet.supported-publishing-event=<String>;<QName>`[2](#two)|
|`/portlet-app/portlet/supports/mime-type`|`javax.portlet.mime-type=<mime-type>`|
|`/portlet-app/portlet/supports/portlet-mode`|`javax.portlet.portlet-mode=<mime-type>;<portlet-mode>[,<portlet-mode>]*`|
|`/portlet-app/portlet/supports/window-state`|`javax.portlet.window-state=<mime-type>;<window-state>[,<window-state>]*`|
|`/portlet-app/portlet/version`|`javax.portlet.version=<listener-class>;<ordinal>` [8](#eight)|

## Liferay Descriptor Mappings [](id=liferay-descriptor-mappings)

### Liferay Display [](id=liferay-display)

|liferay-display.xml XPath | OSGi Portlet Service Property|
|----|----|
|`/display/category\[@name\]`|`com.liferay.portlet.display-category=<value>`|

### Liferay Portlet [](id=liferay-portlet)

**Note:** XPath notation derived from **Liferay Portlet** [5](#five) is used in
this document for simplicity.

|liferay-portlet.xml XPath | OSGi Liferay Portlet Service Property|
|----|----|
|`/liferay-portlet-app/portlet/action-timeout`|`com.liferay.portlet.action-timeout=<int>`|
|`/liferay-portlet-app/portlet/action-url-redirect`|`com.liferay.portlet.action-url-redirect=<boolean>`|
|`/liferay-portlet-app/portlet/active`|`com.liferay.portlet.active=<boolean>`|
|`/liferay-portlet-app/portlet/add-default-resource`|`com.liferay.portlet.add-default-resource=<boolean>`|
|`/liferay-portlet-app/portlet/ajaxable`|`com.liferay.portlet.ajaxable=<boolean>`|
|`/liferay-portlet-app/portlet/asset-renderer-factory`|[3](#three)|
|`/liferay-portlet-app/portlet/atom-collection-adapter`|[3](#three)|
|`/liferay-portlet-app/portlet/autopropagated-parameters`|`com.liferay.portlet.autopropagated-parameters=<String>`[2](#two)|
|`/liferay-portlet-app/portlet/configuration-action-class`|[3](#three)|
|`/liferay-portlet-app/portlet/configuration-path`|`com.liferay.portlet.configuration-path=<String>`|
|`/liferay-portlet-app/portlet/control-panel-entry-category`|`com.liferay.portlet.control-panel-entry-category=<String>`|
|`/liferay-portlet-app/portlet/control-panel-entry-class`|[3](#three)|
|`/liferay-portlet-app/portlet/control-panel-entry-weight`|`com.liferay.portlet.control-panel-entry-weight=<double>`|
|`/liferay-portlet-app/portlet/css-class-wrapper`|`com.liferay.portlet.css-class-wrapper=<String>`|
|`/liferay-portlet-app/portlet/custom-attributes-display`|[3](#three)|
|`/liferay-portlet-app/portlet/ddm-display`|[3](#three)|
|`/liferay-portlet-app/portlet/facebook-integration`|`com.liferay.portlet.facebook-integration=<String>`|
|`/liferay-portlet-app/portlet/footer-portal-css`|`com.liferay.portlet.footer-portal-css=<String>`[2](#two)|
|`/liferay-portlet-app/portlet/footer-portal-javascript`|`com.liferay.portlet.footer-portal-javascript=<String>`[2](#two)|
|`/liferay-portlet-app/portlet/footer-portlet-css`|`com.liferay.portlet.footer-portlet-css=<String>`[2](#two)|
|`/liferay-portlet-app/portlet/footer-portlet-javascript`|`com.liferay.portlet.footer-portlet-javascript=<String>`[2](#two)|
|`/liferay-portlet-app/portlet/friendly-url-mapper-class`|[3](#three)|
|`/liferay-portlet-app/portlet/friendly-url-mapping`|`com.liferay.portlet.friendly-url-mapping=<String>`|
|`/liferay-portlet-app/portlet/friendly-url-routes`|`com.liferay.portlet.friendly-url-routes=<String>`|
|`/liferay-portlet-app/portlet/header-portal-css`|`com.liferay.portlet.header-portal-css=<String>`[2](#two)|
|`/liferay-portlet-app/portlet/header-portal-javascript`|`com.liferay.portlet.header-portal-javascript=<String>`[2](#two)|
|`/liferay-portlet-app/portlet/header-portlet-css`|`com.liferay.portlet.header-portlet-css=<String>`[2](#two)|
|`/liferay-portlet-app/portlet/header-portlet-javascript`|`com.liferay.portlet.header-portlet-javascript=<String>`[2](#two)|
|`/liferay-portlet-app/portlet/header-request-attribute-prefix`|`com.liferay.portlet.header-request-attribute-prefix=<String>` [7](#seven)|
|`/liferay-portlet-app/portlet/header-timeout`|`header-timeout=<int>`|
|`/liferay-portlet-app/portlet/icon`|`com.liferay.portlet.icon=<String>`|
|`/liferay-portlet-app/portlet/include`|not supported|
|`/liferay-portlet-app/portlet/indexer-class`|[3](#three)|
|`/liferay-portlet-app/portlet/instanceable`|`com.liferay.portlet.instanceable=<boolean>`|
|`/liferay-portlet-app/portlet/layout-cacheable`|`com.liferay.portlet.layout-cacheable=<boolean>`|
|`/liferay-portlet-app/portlet/maximize-edit`|`com.liferay.portlet.maximize-edit=<boolean>`|
|`/liferay-portlet-app/portlet/maximize-help`|`com.liferay.portlet.maximize-help=<boolean>`|
|`/liferay-portlet-app/portlet/open-search-class`|[3](#three)|
|`/liferay-portlet-app/portlet/parent-struts-path`|`com.liferay.portlet.parent-struts-path=<String>`|
|`/liferay-portlet-app/portlet/permission-propagator`|[3](#three)|
|`/liferay-portlet-app/portlet/poller-processor-class`|[3](#three)|
|`/liferay-portlet-app/portlet/pop-message-listener-class`|[3](#three)|
|`/liferay-portlet-app/portlet/pop-up-print`|`com.liferay.portlet.pop-up-print=<boolean>`|
|`/liferay-portlet-app/portlet/portlet-data-handler-class`|[3](#three)|)|
|`/liferay-portlet-app/portlet/portlet-layout-listener-class`|[3](#three)|
|`/liferay-portlet-app/portlet/portlet-name`|not supported|
|`/liferay-portlet-app/portlet/portlet-url-class`|[3](#three)|
|`/liferay-portlet-app/portlet/preferences-company-wide`|`com.liferay.portlet.preferences-company-wide=<boolean>`|
|`/liferay-portlet-app/portlet/preferences-owned-by-group`|`com.liferay.portlet.preferences-owned-by-group=<boolean>`|
|`/liferay-portlet-app/portlet/preferences-unique-per-layout`|`com.liferay.portlet.preferences-unique-per-layout=<boolean>`|
|`/liferay-portlet-app/portlet/private-request-attributes`|`com.liferay.portlet.private-request-attributes=<boolean>`|
|`/liferay-portlet-app/portlet/private-session-attributes`|`com.liferay.portlet.private-session-attributes=<boolean>`|
|`/liferay-portlet-app/portlet/remoteable`|`com.liferay.portlet.remoteable=<boolean>`|
|`/liferay-portlet-app/portlet/render-timeout`|`com.liferay.portlet.render-timeout=<int>`|
|`/liferay-portlet-app/portlet/render-weight`|`com.liferay.portlet.render-weight=<int>`|
|`/liferay-portlet-app/portlet/requires-namespaced-parameters`|`com.liferay.portlet.requires-namespaced-parameters=<boolean>`|
|`/liferay-portlet-app/portlet/restore-current-view`|`com.liferay.portlet.restore-current-view=<boolean>`|
|`/liferay-portlet-app/portlet/scheduler-entry`|[3](#three)|
|`/liferay-portlet-app/portlet/scopeable`|`com.liferay.portlet.scopeable=<boolean>`|
|`/liferay-portlet-app/portlet/show-portlet-access-denied`|`com.liferay.portlet.show-portlet-access-denied=<boolean>`|
|`/liferay-portlet-app/portlet/show-portlet-inactive`|`com.liferay.portlet.show-portlet-inactive=<boolean>`|
|`/liferay-portlet-app/portlet/single-page-application`|`com.liferay.portlet.single-page-application=<boolean>`|
|`/liferay-portlet-app/portlet/social-activity-interpreter-class`|[3](#three)|
|`/liferay-portlet-app/portlet/social-request-interpreter-class`|[3](#three)|
|`/liferay-portlet-app/portlet/social-interactions-configuration`|[3](#three)|
|`/liferay-portlet-app/portlet/staged-model-data-handler-class`|[3](#three)|
|`/liferay-portlet-app/portlet/struts-path`|`com.liferay.portlet.struts-path=<String>`|
|`/liferay-portlet-app/portlet/system`|`com.liferay.portlet.system=<boolean>`|
|`/liferay-portlet-app/portlet/template-handler`|[3](#three)|
|`/liferay-portlet-app/portlet/trash-handler`|[3](#three)|
|`/liferay-portlet-app/portlet/url-encoder-class`|[3](#three)|
|`/liferay-portlet-app/portlet/use-default-template`|`com.liferay.portlet.use-default-template=<boolean>`|
|`/liferay-portlet-app/portlet/user-notification-definitions`|not supported|
|`/liferay-portlet-app/portlet/user-notification-handler-class`|[3](#three)|
|`/liferay-portlet-app/portlet/user-principal-strategy`|`com.liferay.portlet.user-principal-strategy=<String>`|
|`/liferay-portlet-app/portlet/virtual-path`|`com.liferay.portlet.virtual-path=<String>`|
|`/liferay-portlet-app/portlet/webdav-storage-class`|[3](#three)|
|`/liferay-portlet-app/portlet/webdav-storage-token`|not supported|
|`/liferay-portlet-app/portlet/workflow-handler`|[3](#three)|
|`/liferay-portlet-app/portlet/xml-rpc-method-class`|[3](#three)|

-   [<a name="one">1</a>] Portlets are registered as concrete objects.
-   [<a name="two">2</a>] Multiples of these properties may be used. This 
    results in an array of values.
-   [<a name="three">3</a>] This type is registered as an OSGi service.
-   [<a name="four">4</a>] 
    http://java.sun.com/xml/ns/portlet/portlet-app_2_0.xsd
-   [<a name="five">5</a>] 
    [http://www.liferay.com/dtd/liferay-portlet-app_7_1_0.dtd](@platform-ref@/7.1-latest/definitions/liferay-portlet-app_7_1_0.dtd.html)
-   [<a name="six">6</a>] Here's an example of using multiple 
    `javax.portlet.dependency` properties.

    *Old:*
    
        <portlet>
        	...
        	<dependency>
        		<name>jquery</name>
        		<scope>com.jquery</scope>
        		<version>2.1.1</version>
        	</dependency>
        	<dependency>
        		<name>jsutil</name>
        		<scope>com.mycompany</scope>
        		<version>1.0.0</version>
        	</dependency>
        	...
        </portlet>

    *New:*

        @Component(
            immediate = true, property = {
                "javax.portlet.name=my_portlet",
                "javax.portlet.display-name=my-portlet",
                "javax.portlet.dependency=jquery;com.jquery;2.1.1",
                "javax.portlet.dependency=jsutil;com.mycompany;1.0.0"
            }, service = Portlet.class
        )
        public class MyPortlet extends GenericPortlet {
            ...
        } 


-   [<a name="seven">7</a>] Here's an example for the 
    `com.liferay.portlet.header-request-attribute-prefix` property.

    *Old:*

        <portlet>
        	...
        	<header-request-attribute-prefix>com.mycompany</header-request-attribute-prefix>
        	...
        </portlet>
    
    *New:* 

        @Component(
            immediate = true, property = {
                "javax.portlet.name=my_portlet",
                "javax.portlet.display-name=my-portlet",
                "javax.portlet.dependency=jquery;com.jquery;2.1.1",
                "javax.portlet.dependency=jsutil;com.mycompany;1.0.0",
                "com.liferay.portlet.header-request-attribute-prefix=com.mycompany"
            }, service = Portlet.class
        )
        public class MyPortlet extends GenericPortlet {
            ...
        } 
        
-   [<a name="eight">8</a>] Here's an example for the 
    `javax.portlet.version` property.

    *Old:*

        <portlet>
            ...
            <listener>
                <listener-class>com.mycompany.MyPortletURLGenerationListener</listener-class>
                <ordinal>1</ordinal>
            </listener>
            ...
        </portlet>

    *New:*

        @Component(
            immediate = true,
            property = {"javax.portlet.name=myPortlet",
                "javax.portlet.version=com.mycompany.MyPortletURLGenerationListener;1"
            }, service = Portlet.class
        )
        public class MyPortlet extends GenericPortlet {
            ...
        } 

-   [<a name="nine">9</a>] An `javax.portlet.init-param` property can be 
    declared like this: 

        @Component(
            immediate = true,
            property = {"javax.portlet.name=myPortlet", 
                "javax.portlet.init-param.myInitParam=1234"},
            service = PortletFilter.class
        )
        public class MyFilter implements RenderFilter {
            ...
        }
