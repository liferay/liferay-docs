# Configuring CDI for JSF Portlets

<!-- Explain what CDI is, the benefits of using it and any drawbacks to using
it. - Jim -->

<!-- I think the below paragraph does some of that. It explains that CDI is the
dependency injection standard for Java (as opposed to Spring). It explains that
it's a part of both Java EE 6 and 7, and that it's required by the Faces Flows
feature; thus you'll need to know it and use it if you're using that feature.
-Rich -->

In December 2009, [JSR 299](http://jcp.org/en/jsr/detail?id=299) introduced the
Contexts and Dependency Injection (CDI) 1.0 standard into the Java EE 6
platform. In April 2013, [JSR 346](http://jcp.org/en/jsr/detail?id=346) updated
CDI to version 1.1 for Java EE 7. In addition, [JSR
344](http://jcp.org/en/jsr/detail?id=344), the JSF 2.2 specification which is
another component of Java EE 7, introduced a dependency on the CDI API for the
`javax.faces.view.ViewScoped` annotation and for the Faces Flows feature. JBoss
[Weld](http://seamframework.org/Weld) is the Reference Implementation (RI) for
CDI, and Apache [OpenWebBeans](http://openwebbeans.apache.org/) is another open
source implementation. 

Still wondering what CDI accomplishes? Visit the [CDI
Specs](http://cdi-spec.org/) to learn about CDI and why it has become a popular
component for JSF. 

In this tutorial you'll cover how to configure CDI on Liferay Portal. To do
this, you'll configure Weld on Liferay Portal to leverage CDI with JSF portlets. 

## Configuring CDI on Liferay Portal

You must use one of the following portal/app-server combinations to use Weld
with Liferay Portal: 

- Liferay Portal 6.1/6.2 (Tomcat)
- Liferay Portal 6.1/6.2 (GlassFish)
    - Apply patch attached to [LPS-35558](http://issues.liferay.com/browse/LPS-35558).
    - [Upgrade Mojarra in GlassFish](http://www.liferay.com/community/wiki/-/wiki/Main/Upgrading+Mojarra+in+Oracle+GlassFish)
      to version 2.1.21 (or higher).
    - [Upgrade Weld in GlassFish](http://www.liferay.com/community/wiki/-/wiki/Main/Upgrading+Weld+in+Oracle+GlassFish)
      to version 1.1.10.Final (or higher).
- Liferay Portal 6.1/6.2 (JBoss AS)
    - Apply patch attached to [LPS-35558](http://issues.liferay.com/browse/LPS-35558)
    - [Upgrade Mojarra in JBoss AS](http://www.liferay.com/community/wiki?p_p_id=36&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-2&p_p_col_count=1&_36_struts_action=%2Fwiki%2Fedit_page&_36_redirect=http%3A%2F%2Fwww.liferay.com%2Fcommunity%2Fwiki%2F-%2Fwiki%2FMain%2FDeveloping%2BJSF%2BPortlets%2Bwith%2BCDI&p_r_p_185834411_nodeId=1071674&p_r_p_185834411_title=%5Bhttp%3A%2F%2Fwww.liferay.com%2Fcommunity%2Fwiki%2F-%2Fwiki%2FMain%2FUpgrading%2BMojarra%2Bin%2BJBoss%2BAS)
      to version 2.1.21 (or higher). 
    - [Upgrade Weld in JBoss AS](http://www.liferay.com/community/wiki?p_p_id=36&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-2&p_p_col_count=1&_36_struts_action=%2Fwiki%2Fedit_page&_36_redirect=http%3A%2F%2Fwww.liferay.com%2Fcommunity%2Fwiki%2F-%2Fwiki%2FMain%2FDeveloping%2BJSF%2BPortlets%2Bwith%2BCDI&p_r_p_185834411_nodeId=1071674&p_r_p_185834411_title=%5Bhttp%3A%2F%2Fwww.liferay.com%2Fcommunity%2Fwiki%2F-%2Fwiki%2FMain%2FUpgrading%2BWeld%2Bin%2BJBoss%2BAS)
      to version 1.1.10.Final (or higher). 
- Liferay Portal 6.1/6.2 (Resin)

When developing portlets with CDI 1.0, you must include a `WEB-INF/beans.xml`
descriptor in your JSF portlet plugin's WAR deployment, so that the CDI
implementation can detect the CDI-related annotations of your classes when it
scans the classpath. 

Here's an example `WEB-INF/beans.xml` descriptor: 

    <beans xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
        http://java.sun.com/xml/ns/javaee/beans_1_0.xsd">
    </beans>
 
For JBoss AS 7, you must also include a `WEB-INF/jboss-deployment-structure.xml`
descriptor in your portlet plugin's WAR deployment to include the CDI-related
modules. Here's an example of a `WEB-INF/jboss-deployment-structure.xml`
descriptor for JBoss: 

    <?xml version="1.0"?>
    <jboss-deployment-structure xmlns="urn:jboss:deployment-structure:1.0">
        <deployment>
            <exclusions>
                <module name="javaee.api" />
                <module name="org.apache.log4j" />
            </exclusions>
            <dependencies>
                <module name="com.liferay.portal" />
                <module name="javax.annotation.api" />
                <module name="javax.enterprise.api" />
                <module name="javax.inject.api" />
                <module name="javax.interceptor.api" />
                <module name="javax.validation.api" />
                <module name="javax.mail.api" />
                <module name="org.jboss.modules" />
            </dependencies>
        </deployment>
    </jboss-deployment-structure>

<!-- Recommend we take out all the app server configuration stuff below and
bake it into the app server sections in the User Guide. -Rich -->

Next, you'll cover Weld configuration on the app server. There are some
different configuration steps for different app servers. You'll learn about the
most common configuration steps first. 

For most app servers (excluding Resin), the portlet's `WEB-INF/web.xml`
descriptor must include the following filter and filter mapping: 

    <filter>    
        <filter-name>WeldCrossContextFilter</filter-name>    
        <filter-class>org.jboss.weld.servlet.WeldCrossContextFilter</filter-class>
    </filter>
    <filter-mapping>    
        <filter-name>WeldCrossContextFilter</filter-name>    
        <url-pattern>/*</url-pattern>    
        <dispatcher>INCLUDE</dispatcher>    
        <dispatcher>FORWARD</dispatcher>    
        <dispatcher>ERROR</dispatcher>
    </filter-mapping>

If you are using Resin as your app server, you don't need JBoss Weld, as Resin
includes the [CanDI](http://www.caucho.com/candi-java-dependency-injection/)
implementation of CDI by default. 

The next section contains information about specifically configuring Tomcat, so
developers running other application servers can skip it. 

## Additional Weld Configuration for Tomcat

If Weld is running in a Java EE application server like Oracle GlassFish or
JBoss AS, then Weld is automatically included in the global classpath. However,
on Tomcat, it is necessary to include the `weld-servlet.jar` dependency in
either the `tomcat/lib` global classpath, or directly in the `WEB-INF/lib`
folder of a portlet: 

    <!-- Required only for Tomcat -->
    <dependency>    
        <groupId>org.jboss.weld.servlet</groupId>    
        <artifactId>weld-servlet</artifactId>    
        <version>1.1.10.Final</version>
    </dependency>

<!-- The above instructions look like Maven stuff, and Maven isn't mentioned
here. -Rich -->

Additionally, the following listener must be added to the `WEB-INF/web.xml`
descriptor: 

    <!-- Required only for Tomcat -->
    <listener>    
        <listener-class>org.jboss.weld.environment.servlet.Listener</listener-class>
    </listener>

As you can see, configuring CDI for your JSF portlets is a snap! 
