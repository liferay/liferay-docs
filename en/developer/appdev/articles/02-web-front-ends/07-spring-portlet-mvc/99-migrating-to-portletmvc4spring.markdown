---
header-id: migrating-to-portletmvc4spring
---

# Migrating to PortletMVC4Spring

[TOC levels=1-4]

To continue developing a portlet to use Spring Framework version 5.0 onward,
migrate it from Spring Portlet MVC to
[PortletMVC4Spring](/docs/7-2/appdev/-/knowledge_base/a/portletmvc4spring). Here
are the steps: 

1.  In your `pom.xml` or `build.gradle` descriptor, use the Spring Framework 
    version 5.1.x artifacts by replacing dependencies on the
    `spring-webmvc-portlet` artifact with the
    `com.liferay.portletmvc4spring.framework` artifact. 

    Maven:

    ```xml
    <dependency>
        <groupId>com.liferay.portletmvc4spring</groupId>
        <artifactId>com.liferay.portletmvc4spring.framework</artifactId>
        <version>5.1.0</version>	
    </dependency>
    <dependency>
        <groupId>com.liferay.portletmvc4spring</groupId>
        <artifactId>com.liferay.portletmvc4spring.security</artifactId>
        <version>5.1.0</version>	
    </dependency>
    ```

    Gradle:

    ```groovy
    compile group: 'com.liferay.portletmvc4spring', name: 'com.liferay.portletmvc4spring.framework', version: '5.1.0'
	compile group: 'com.liferay.portletmvc4spring', name: 'com.liferay.portletmvc4spring.security', version: '5.1.0'
    ```

2.  In your `WEB-INF/portlet.xml` descriptor, replace uses of
    `org.springframework.web.portlet.DispatcherPortlet` with 
    [`com.liferay.portletmvc4spring.DispatcherPortlet`](https://liferay.github.io/portletmvc4spring/apidocs/com/liferay/portletmvc4spring/DispatcherPortlet.html). 

3.  Replace uses of the Spring Portlet MVC
    [`AnnotationMethodHandlerAdapter`](https://docs.spring.io/spring-framework/docs/4.3.4.RELEASE/javadoc-api/org/springframework/web/portlet/mvc/annotation/AnnotationMethodHandlerAdapter.html)
    class with the PortletMVC4Spring
    [`PortletRequestMappingHandlerAdapter`](https://liferay.github.io/portletmvc4spring/apidocs/com/liferay/portletmvc4spring/mvc/method/annotation/PortletRequestMappingHandlerAdapter.html)
    class. `PortletRequestMappingHandlerAdapter` uses the `HandlerMethod` infrastructure that [Spring Web MVC
    5.1.x](https://docs.spring.io/spring/docs/5.1.x/spring-framework-reference/web.html#spring-web)
    is based on. 

4.  If you specified `AnnotationMethodHandlerAdapter` as a `<bean>` in a Spring
    configuration descriptor, replace its fully-qualified class name 
    `org.springframework.web.portlet.mvc.annotation.AnnotationMethodHandlerAdapter`
    with
    `com.liferay.portletmvc4spring.mvc.method.annotation.PortletRequestMappingHandlerAdapter`. 

    Also address these bean property changes:
 
    -   [`customModelAndViewResolver`](https://docs.spring.io/spring-framework/docs/4.3.4.RELEASE/javadoc-api/org/springframework/web/portlet/mvc/annotation/AnnotationMethodHandlerAdapter.html#setCustomModelAndViewResolver-org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver-)
        (no longer available)

    -   [`customArgumentResolver`](https://docs.spring.io/spring-framework/docs/4.3.4.RELEASE/javadoc-api/org/springframework/web/portlet/mvc/annotation/AnnotationMethodHandlerAdapter.html#setCustomArgumentResolver-org.springframework.web.bind.support.WebArgumentResolver-)
        (no longer available)

    -   [`customArgumentResolvers`](https://liferay.github.io/portletmvc4spring/apidocs/com/liferay/portletmvc4spring/mvc/method/annotation/PortletRequestMappingHandlerAdapter.html#setCustomArgumentResolvers-java.util.List-)
        (specify a list of
        [`HandlerMethodArgumentResolver`](https://docs.spring.io/spring/docs/5.1.4.RELEASE/javadoc-api/org/springframework/web/method/support/HandlerMethodArgumentResolver.html)
        instead of a list of
        [`WebArgumentResolver`](https://docs.spring.io/spring-framework/docs/4.3.4.RELEASE/javadoc-api/org/springframework/web/bind/support/WebArgumentResolver.html))
 
5.  If you're using [Apache Commons
    Fileupload](https://commons.apache.org/proper/commons-fileupload/), 
    update your Spring configuration descriptor:

    1.  Replace this legacy bean:

        ```xml
        <bean id="portletMultipartResolver"
        	class="org.springframework.web.portlet.multipart.CommonsPortletMultipartResolver" />
        ```

        With this new one from PortletMVC4Spring:

        ```xml
        <bean id="portletMultipartResolver"
        	class="com.liferay.portletmvc4spring.multipart.CommonsPortletMultipartResolver" />
        ```

        | **Note:** Alternatively, you can use the native Portlet 3.0 file upload
        | support that PortletMVC4Spring provides by setting the
        | `portletMultipartResolver` `<bean>` element's `class` to
        | `com.liferay.portletmvc4spring.multipart.StandardPortletMultipartResolver`

    2.  Remove these dependencies from your `pom.xml` or `build.gradle` 
        descriptor:

        ```xml
        <dependency>
        	<groupId>commons-fileupload</groupId>
        	<artifactId>commons-fileupload</artifactId>
        </dependency>
        <dependency>
        	<groupId>commons-io</groupId>
        	<artifactId>commons-io</artifactId>
        </dependency>
        ```

5.  Throughout your project, replace all uses of the 
    `org.springframework.web.portlet` package path with
    `com.liferay.portletmvc4spring`. 

6.  Continue [developing your portlet using
    PortletMVC4Spring](/docs/7-2/appdev/-/knowledge_base/a/developing-a-portlet-using-portletmvc4spring).

7.  [Build and deploy your
    project](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project).

Congratulations! You migrated your project from Spring Portlet MVC to
PortletMVC4Spring. 

## Related Topics 

[PortletMVC4Spring](/docs/7-2/appdev/-/knowledge_base/a/portletmvc4spring)

[Developing a Portlet Using
PortletMVC4Spring](/docs/7-2/appdev/-/knowledge_base/a/developing-a-portlet-using-portletmvc4spring)

[Configuring
Dependencies](/docs/7-2/customization/-/knowledge_base/c/configuring-dependencies)
