# Configuring the Liferay CDI Portlet Bridge

The Liferay CDI Portlet Bridge makes it possible to use CDI with your JSF
portlets on Liferay. Your JSF portlet projects must include the Liferay CDI
Portlet Bridge as a dependency.

For example, to specify the bridge dependency in a Maven project for Liferay
6.2, add the following `<dependency>` to your POM's `<dependencies>` element: 

    <dependency>    
        <groupId>com.liferay.cdi</groupId>    
        <artifactId>cdi-portlet-bridge-shared</artifactId>    
        <version>6.2.0.2</version>
    </dependency>

The `WEB-INF/portlet.xml` descriptor of the portlet must include the following
markup: 

    <filter>    
        <filter-name>CDIPortletFilter</filter-name>    
        <filter-class>com.liferay.cdi.portlet.bridge.CDIPortletFilter</filter-class>
        <lifecycle>ACTION_PHASE</lifecycle>
        <lifecycle>EVENT_PHASE</lifecycle>
        <lifecycle>RENDER_PHASE</lifecycle>
        <lifecycle>RESOURCE_PHASE</lifecycle>
    </filter>
    <filter-mapping>
        <filter-name>CDIPortletFilter</filter-name>    
        <portlet-name>my-portlet-name</portlet-name>
    </filter-mapping>

Additionally, the portlet's `WEB-INF/web.xml` descriptor must include the
following declarations: 

    <filter>
        <filter-name>CDICrossContextFilter</filter-name>
        <filter-class>com.liferay.cdi.portlet.bridge.CDICrossContextFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>CDICrossContextFilter</filter-name>
        <url-pattern>/*</url-pattern>
        <dispatcher>INCLUDE</dispatcher>
        <dispatcher>FORWARD</dispatcher>
        <dispatcher>ERROR</dispatcher>
    </filter-mapping>
    <listener>
        <listener-class>com.liferay.cdi.portlet.bridge.CDIContextListener</listener-class>
    </listener>

---

 ![Tip](../../images/tip.png) **Tip:** The Liferay Faces Project features the
 [jsf2-cdi-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-cdi-portlet)
 demo (which is a variant of the
 [jsf2-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
 demo). It's a good idea to download and deploy the jsf2-cdi-portlet demo in
 your development environment in order to verify that CDI functions properly. 

---

Configuring the Portlet Bridge for you JSF portlet is complete. <!--For other
configuration options dealing with CDI, visit the [Configuring CDI for JSF
Portlets](www.liferay.com) tutorial. -->

<!-- Update above link once tutorials are available. -Cody -->

Now that CDI is fully configured for your portlet, you're ready to begin
development with CDI. 
