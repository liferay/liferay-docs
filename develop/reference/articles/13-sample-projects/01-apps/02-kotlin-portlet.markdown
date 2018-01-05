# Kotlin Portlet

## What does this sample do when it's deployed?

The Kotlin Portlet sample uses an input form that accepts a name. Once
submitting a name, the portlet renders a greeting message.

![Figure 1: After saving the inputted name, it's is displayed as a greeting on the portlet page.](../../../images/kotlin-portlet.png)

## What API(s) and/or code components does this sample highlight?

This sample highlights the use of the [Kotlin](https://kotlinlang.org/)
programming language in conjunction with the Liferay MVC framework.
Specifically, it leverages the
[MVCActionCommand](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)
interface.

## How does this sample leverage the API(s) and/or code component?

This sample uses the
[MVC Action Command](/develop/tutorials/-/knowledge_base/7-0/mvc-action-command)'s
`processAction(...)` method to process the inputted text (i.e., name). The text
is set as an attribute in the `KotlinGreeterActionCommandKt.kt` class using an
`ActionRequest` and then is retrieved in the JSP using a `RenderRequest`.

## Where Is This Sample?

This sample is built with the following build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/kotlin-portlet)
