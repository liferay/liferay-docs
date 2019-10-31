---
header-id: portletmvc4spring-configuration-files
---

# PortletMVC4Spring Configuration Files

[TOC levels=1-4]

A PortletMVC4Spring application has these descriptors, Spring contexts, and
properties files in its `WEB-INF` folder: 

-   `web.xml` &rarr; Web application descriptor
-   `portlet.xml` &rarr; Portlet application descriptor
-   `liferay-portlet.xml` &rarr; Liferay-specific portlet descriptor 
-   `liferay-display.xml` &rarr; Liferay-specific display 
    descriptor 
-   `spring-context/portlet-application-context.xml` &rarr; Portlet application 
    context
-   `spring-context/portlet/[portlet]-context.xml` &rarr; Portlet context
-   `liferay-plugin-package.properties` &rarr; Packaging descriptor 

Examples of each file are provided and portlet-specific content is highlighted. 

## web.xml

The servlet container processes the `web.xml`. This file specifies the servlet
that render's the portlet and the portlet application's context, servlet,
filters, listeners, and more. Here's an example `web.xml`:

```xml
<?xml version="1.0"?>

<web-app version="3.1" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd">
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring-context/portlet-application-context.xml</param-value>
	</context-param>
	<servlet>
		<servlet-name>ViewRendererServlet</servlet-name>
		<servlet-class>com.liferay.portletmvc4spring.ViewRendererServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>ViewRendererServlet</servlet-name>
		<url-pattern>/WEB-INF/servlet/view</url-pattern>
	</servlet-mapping>
	<filter>
		<filter-name>delegatingFilterProxy</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>delegatingFilterProxy</filter-name>
		<url-pattern>/WEB-INF/servlet/view</url-pattern>
		<dispatcher>FORWARD</dispatcher>
		<dispatcher>INCLUDE</dispatcher>
	</filter-mapping>
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
</web-app>
```

The `<context-param/>` element gives the path to the portlet application context
(discussed later):

```xml
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/spring-context/portlet-application-context.xml</param-value>
</context-param>
```

The `<servlet/>` and `<servlet-mapping/>` elements set the servlet and the
internal location for its views. 

```xml
<servlet>
    <servlet-name>ViewRendererServlet</servlet-name>
    <servlet-class>com.liferay.portletmvc4spring.ViewRendererServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>ViewRendererServlet</servlet-name>
    <url-pattern>/WEB-INF/servlet/view</url-pattern>
</servlet-mapping>
```

The
[`ViewRendererServlet`](https://liferay.github.io/portletmvc4spring/apidocs/com/liferay/portletmvc4spring/ViewRendererServlet.html). 
converts portlet requests into servlet requests and
enables the view to be rendered using the Spring Web MVC infrastructure and the
infrastructure's renderers for JSP, Thymeleaf, Velocity, and more. 

The filter and filter mappings are set to forward and include servlet views as
necessary. 

```xml
<filter>
    <filter-name>delegatingFilterProxy</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
<filter-mapping>
    <filter-name>delegatingFilterProxy</filter-name>
    <url-pattern>/WEB-INF/servlet/view</url-pattern>
    <dispatcher>FORWARD</dispatcher>
    <dispatcher>INCLUDE</dispatcher>
</filter-mapping>
```

A listener is configured for processing the application's contexts. 

```xml 
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

Liferay's project archetypes generate all this boilerplate code.  

## portlet.xml

The `portlet.xml` file describes the portlet application to the portlet
container. Here's an example:

```xml
<?xml version="1.0"?>

<portlet-app xmlns="http://xmlns.jcp.org/xml/ns/portlet" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/portlet http://xmlns.jcp.org/xml/ns/portlet/portlet-app_3_0.xsd" version="3.0">
	<portlet>
		<portlet-name>portlet1</portlet-name>
		<display-name>com.mycompany.my.form.jsp.portlet</display-name>
		<portlet-class>com.liferay.portletmvc4spring.DispatcherPortlet</portlet-class>
		<init-param>
			<name>contextConfigLocation</name>
			<value>/WEB-INF/spring-context/portlet/portlet1-context.xml</value>
		</init-param>
		<expiration-cache>0</expiration-cache>
		<supports>
			<mime-type>text/html</mime-type>
			<portlet-mode>view</portlet-mode>
		</supports>
		<resource-bundle>content.portlet1</resource-bundle>
		<portlet-info>
			<title>com.mycompany.my.form.jsp.portlet</title>
			<short-title>com.mycompany.my.form.jsp.portlet</short-title>
			<keywords>com.mycompany.my.form.jsp.portlet</keywords>
		</portlet-info>
		<security-role-ref>
			<role-name>administrator</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>guest</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>power-user</role-name>
		</security-role-ref>
		<security-role-ref>
			<role-name>user</role-name>
		</security-role-ref>
	</portlet>
	<filter>
		<filter-name>SpringSecurityPortletFilter</filter-name>
		<filter-class>com.liferay.portletmvc4spring.security.SpringSecurityPortletFilter</filter-class>
		<lifecycle>ACTION_PHASE</lifecycle>
		<lifecycle>RENDER_PHASE</lifecycle>
		<lifecycle>RESOURCE_PHASE</lifecycle>
	</filter>
	<filter-mapping>
		<filter-name>SpringSecurityPortletFilter</filter-name>
		<portlet-name>portlet1</portlet-name>
	</filter-mapping>
</portlet-app>
```

This application has one portlet named `portlet1`.

```xml
<portlet-name>portlet1</portlet-name>
<display-name>com.mycompany.my.form.jsp.portlet</display-name>
<portlet-class>com.liferay.portletmvc4spring.DispatcherPortlet</portlet-class>
```

The `<portlet-name/>` is internal and the `<display-name/>` is shown to users.
`<portlet-class/>` specifies the portlet's Java class. 

**Important:** All PortletMVC4Spring portlets must specify
`<portlet-class>com.liferay.portletmvc4spring.DispatcherPortlet</portlet-class>`. 

The `<supports/>` element must declare the mime type that the portlet templates
use. 

The `<resource-bundle/>` sets the path to the portlet's localized Java message
properties. For example, the element refers to properties at
`content/portlet1.properties`: 

```xml
<resource-bundle>content.portlet1</resource-bundle>
```

The `<portlet-info/>` element lists the portlet's titles and reserved keyword. 

The `<security-role-ref/>` elements declare default user roles the portlet
accounts for. 

Lastly, the `<filter/>` named 
[`SpringSecurityPortletFilter`](https://liferay.github.io/portletmvc4spring/apidocs/index.html)
prevents Cross-Site Request Forgery (CSRF). 

```xml
<filter>
    <filter-name>SpringSecurityPortletFilter</filter-name>
    <filter-class>com.liferay.portletmvc4spring.security.SpringSecurityPortletFilter</filter-class>
    <lifecycle>ACTION_PHASE</lifecycle>
    <lifecycle>RENDER_PHASE</lifecycle>
    <lifecycle>RESOURCE_PHASE</lifecycle>
</filter>
<filter-mapping>
    <filter-name>SpringSecurityPortletFilter</filter-name>
    <portlet-name>portlet1</portlet-name>
</filter-mapping>
```

The
[`portlet XSD`](https://docs.liferay.com/portlet-api/3.0/portlet-app_3_0.xsd)
defines the `portlet.xml`. The Liferay-specific portlet descriptor is next. 

## liferay-portlet.xml

The `liferay-portlet.xml` file applies Liferay-specific settings that provide
more developer features. Here's an example:

```xml 
<?xml version="1.0"?>
<!DOCTYPE liferay-portlet-app PUBLIC "-//Liferay//DTD Portlet Application 7.1.0//EN" "http://www.liferay.com/dtd/liferay-portlet-app_7_1_0.dtd">

<liferay-portlet-app>
	<portlet>
		<portlet-name>portlet1</portlet-name>
		<icon>/resources/images/icon.png</icon>
		<requires-namespaced-parameters>false</requires-namespaced-parameters>
	</portlet>
	<role-mapper>
		<role-name>administrator</role-name>
		<role-link>Administrator</role-link>
	</role-mapper>
	<role-mapper>
		<role-name>guest</role-name>
		<role-link>Guest</role-link>
	</role-mapper>
	<role-mapper>
		<role-name>power-user</role-name>
		<role-link>Power User</role-link>
	</role-mapper>
	<role-mapper>
		<role-name>user</role-name>
		<role-link>User</role-link>
	</role-mapper>
</liferay-portlet-app>
```

This `<portlet/>` element associates an icon with the portlet and indicates
that name-spaced parameters aren't required. 

The `<role-mapper/>` elements associate the portlet with default @product@ user
roles. 

The
[liferay-portlet-app DTD](@platform-ref@/7.2-latest/definitions/liferay-portlet-app_7_2_0.dtd.html)
defines the `liferay-portlet.xml` file. 

## liferay-display.xml

The `liferay-display.xml` applies display characteristics to the portlet. For
example, this descriptor associates the portlet with a Widget category in
@product@'s Add Widget menu. 

```xml
<?xml version="1.0"?>
<!DOCTYPE display PUBLIC "-//Liferay//DTD Display 7.2.0//EN" "http://www.liferay.com/dtd/liferay-display_7_2_0.dtd">

<display>
<category name="category.sample">
    <portlet id="portlet1" />
</category>
</display>
```

See the
[liferay-display DTD](@platform-ref@/7.2-latest/definitions/liferay-display_7_2_0.dtd.html)
for details. 

It's time to look at the application contexts. 

## Portlet Application Context 

This context applies to all of the application's portlets. This is where you
specify view resolvers, resource bundles, security beans, proxies, and more.
Here's an example:

```xml 
<?xml version="1.0"?>

<beans
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
	<context:annotation-config />
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="viewResolver">
		<property name="contentType" value="text/html;charset=UTF-8" />
		<property name="prefix" value="/WEB-INF/views/" />
		<property name="suffix" value=".jspx" />
		<property name="viewClass" value="com.liferay.portletmvc4spring.PortletJstlView" />
	</bean>
	<bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
		<property name="basenames">
			<list>
				<value>content.portlet1</value>
			</list>
		</property>
		<property name="defaultEncoding" value="UTF-8" />
	</bean>
	<bean id="springSecurityPortletConfigurer" class="com.liferay.portletmvc4spring.security.SpringSecurityPortletConfigurer" />
	<bean id="delegatingFilterProxy" class="org.springframework.web.filter.DelegatingFilterProxy">
		<property name="targetBeanName" value="springSecurityFilterChain" />
	</bean>
</beans>
```

The view resolver bean above handles JSPX view templates. To resolve Thymeleaf
view templates, for example, you could specify these beans: 

```xml 
<bean class="org.thymeleaf.templateresolver.ServletContextTemplateResolver" id="templateResolver">
    <property name="prefix" value="/WEB-INF/views/"/>
    <property name="suffix" value=".html"/>
    <property name="templateMode" value="HTML"/>
</bean>
<bean class="org.thymeleaf.spring5.SpringTemplateEngine" id="templateEngine">
    <property name="templateResolver" ref="templateResolver"></property>
    <property name="enableSpringELCompiler" value="true" />
</bean>
<bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver" id="viewResolver">
    <property name="templateEngine" ref="templateEngine"/>
    <property name="order" value="1"/>
</bean>
``` 

The context's `springSecurityPortletConfigurer` bean facilitates using Spring
Security: 

```xml
<bean id="springSecurityPortletConfigurer" 
    class="com.liferay.portletmvc4spring.security.SpringSecurityPortletConfigurer" />
```

You can also designate contexts for each portlet in the application. 

## Portlet Contexts

Beans specific to a portlet, go in the portlet's context. Since annotations are
the easiest way to develop PortletMVC4Spring portlets, you should specify MVC
annotation scanning in the portlet context:

```xml 
<?xml version="1.0"?>

<beans
    xmlns="http://www.springframework.org/schema/beans"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <context:component-scan base-package="portlet1**"/>
    <mvc:annotation-driven/>
</beans>
```

The portlet context naming convention is `[portlet-name]-context.xml`. To
associate your portlet with its own context, edit your application's
`portlet.xml` file and add an `<init-param/>` element that maps the `<portlet/>`
element to the portlet's context: 

```xml 
<init-param>
    <name>contextConfigLocation</name>
    <value>/WEB-INF/spring-context/portlet/portlet1-context.xml</value>
</init-param>
```

What's left is to describe your application package. 

## liferay-plugin-package.properties 

This file specifies the application's name, version, Java package
imports/exports, and OSGi metadata. Here's an example package properties file:

```properties 
author=N/A
change-log=
licenses=N/A
liferay-versions=7.1.0+
long-description=
module-group-id=com.mycompany
module-incremental-version=1
name=com.mycompany.my.form.jsp.portlet
page-url=
short-description=my portlet short description
tags=myTag
Bundle-Version: 1.0.0
Import-Package: com.liferay.portal.webserver,com.liferay.portal.kernel.servlet.filters.invoker
```

It uses this OSGi metadata header to
[import required Java packages](/docs/7-2/customization/-/knowledge_base/c/importing-packages):

```properties
Import-Package: com.liferay.portal.webserver,\
com.liferay.portal.kernel.servlet.filters.invoker
```

On deploying the portlet application WAR file, the
[WAB Generator](/docs/7-2/customization/-/knowledge_base/c/deploying-wars-wab-generator)
adds the specified OSGi metadata to the resulting web application bundle
(WAB) that's deployed to Liferay's runtime framework.

The
[liferay-plugin-package reference document](@platform-ref@/7.2-latest/propertiesdoc/liferay-plugin-package_7_2_0.properties.html)
describes the `liferay-plugin-package.properties` file. 

Congratulations! You've successfully toured the PortletMVC4Spring configuration
files. 

## Related Topics 

[PortletMVC4Spring Annotations](/docs/7-2/reference/-/knowledge_base/r/portletmvc4spring-annotations)

[Migrating to PortletMVC4Spring](/docs/7-2/appdev/-/knowledge_base/a/migrating-to-portletmvc4spring)
