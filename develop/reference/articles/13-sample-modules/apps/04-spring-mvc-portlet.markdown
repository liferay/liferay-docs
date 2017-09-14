# Spring MVC Portlet [](id=spring-mvc-portlet)

The Spring MVC portlet provides a way to add various different fields into the
database and display them in a table. This project is a Spring MVC based portlet
WAR that implements the same functionality as the `blade.servicebuilder.web`
project. It manages JSP pages for display, uses a Spring-annotated portlet
class, and invokes the `blade.servicebuilder.api` to call services. Unlike the
`blade.servicebuilder.web` module, Spring MVC portlets must be delivered as
portlet WAR projects. This project builds to a WAR file but leverages all of the
Liferay Workspace tools and Gradle to build the WAR. You must build and deploy
the `blade.servicebuilder.api` and `blade.servicebuilder.svc` modules for this
sample to work properly. For more information on using Spring MVC portlets in
@product@, visit the
[Spring MVC](/develop/tutorials/-/knowledge_base/7-0/spring-mvc) tutorial.

![Figure 1: Click *Add* and fill out the sample fields to generate a custom entry in the portlet's table.](../../../images/spring-mvc-portlet.png)

You can easily modify this sample by customizing its
`SpringMVCPortletViewController` Java class or any of its JSPs stored in the
`src/main/webapp/WEB-INF/jsp` folder. For more information on customizing this
sample, see the Javadoc listed in the `SpringMVCPortletViewController` Java
class.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/springmvc-portlet)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/wars/springmvc-portlet)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/apps/springmvc-portlet)
