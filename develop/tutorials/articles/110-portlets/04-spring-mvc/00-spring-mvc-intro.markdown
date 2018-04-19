# Spring MVC [](id=spring-mvc)

Liferay is an open platform in an ecosystem of open platforms. Just because
Liferay has its own
[MVC framework](/develop/tutorials/-/knowledge_base/7-1/liferay-mvc-portlet)
doesn't mean you have to use it. It is perfectly valid to bring the
tools and experience you have from other development projects over to Liferay.
In fact, we expect you to. Liferay's development platform is standards-based,
making it an ideal choice for applications of almost any type. 

If you're already a wizard with Spring MVC, you can use it instead of Liferay's
[`MVCPortlet` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
with no limitations whatsoever. Since Spring MVC replaces only your
application's web application layer, you can still use
[Service Builder](/develop/tutorials/-/knowledge_base/7-1/what-is-service-builder) 
for your service layer. 

So what does it take to implement a Spring MVC application in Liferay? 

1. Develop as you normally do using Spring MVC. 

2. [Configure your application for Liferay.](/develop/tutorials/-/knowledge_base/7-1/configuring-a-spring-mvc-portlet)

3. [Deploy it to Liferay.](/develop/tutorials/-/knowledge_base/7-1/deploying-a-spring-mvc-portlet)

Since you already have your app, you'll start with configuration. 
