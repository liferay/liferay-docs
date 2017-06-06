# Arquillian Example Sample Portlet [](id=arquillian-example-sample-portlet)

The sample portlet calculates the sum of two numbers. 

![Figure 1: The Arquillian Sample Portlet calculates the sum of two numbers.](../../../images/arquillian-example-portlet.png)

The portlet project comprises a portlet class, service classes, and JSPs. It
follows the standard OSGi module folder structure with Java files in
`src/main/java/`, resource files in `src/main/resources/META-INF/resources`, and
build files in the project root. 

Here are the primary files:

-   [`SampleService.java`](https://github.com/liferay-labs/arquillian-blade-example/blob/master/src/main/java/com/liferay/arquillian/sample/service/SampleService.java):
    Provides an interface that defines method `public long add(final int
    addend1, final int addend2)` for returning the sum of two numbers. 

-   [`SampleServiceImpl.java`](https://github.com/liferay-labs/arquillian-blade-example/blob/master/src/main/java/com/liferay/arquillian/sample/service/SampleServiceImpl.java):
    Uses OSGi Declarative Services to implement the
    `SampleService` interface. 

-   [`SamplePortlet.java`](https://github.com/liferay-labs/arquillian-blade-example/blob/master/src/main/java/com/liferay/arquillian/sample/portlet/SamplePortlet.java):
    Extends
    [Liferay `MVCPortlet`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
    and processes portlet action commands and renders the result of executing
    the `add` service.

-   [`bnd.bnd`](https://github.com/liferay-labs/arquillian-blade-example/blob/master/bnd.bnd):
    Specifies the module's name, symbolic name, and version.

-   [`init.jsp`](https://github.com/liferay-labs/arquillian-blade-example/blob/master/src/main/resources/META-INF/resources/init.jsp):
    Imports classes and tag libraries for the view layer. 

-   [`view.jsp`](https://github.com/liferay-labs/arquillian-blade-example/blob/master/src/main/resources/META-INF/resources/view.jsp):
    Provides a form for calculating the sum of two numbers.

You'll examine the tests next. 
