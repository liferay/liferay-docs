# Spring MVC [](id=spring-mvc)

Liferay is an open platform in an ecosystem of open platforms. Just because
Liferay has its own [MVC framework](/develop/tutorials/-/knowledge_base/7-0/liferay-mvc-portlet),
therefore, doesn't mean you have to use it. It is perfectly valid to bring the
tools and experience you have from other development projects over to Liferay.
In fact, we expect you to. Liferay's development platform is standards-based,
making it an ideal choice for applications of almost any type. 

If you're already a wizard with Spring MVC, you can use it instead of Liferay's
[`MVCPortlet` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
with no limitations whatsoever. Since Spring MVC replaces only your
application's web application layer, you can still use
[Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder) 
for your service layer. 

So what does it take to implement a Spring MVC application in Liferay? Start by
considering how to package a Spring MVC application for @product-ver@.
