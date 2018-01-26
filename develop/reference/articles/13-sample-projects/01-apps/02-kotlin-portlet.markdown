# Kotlin Portlet [](id=kotlin-portlet)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Kotlin Portlet sample provides an input form that accepts a name. Once
submitting a name, the portlet renders a greeting message.

![Figure 1: After saving the inputted name, it's is displayed as a greeting on the portlet page.](../../../images/kotlin-portlet.png)

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample highlights the use of the [Kotlin](https://kotlinlang.org/)
programming language in conjunction with Liferay's MVC framework.
Specifically, this sample leverages the
[MVCActionCommand](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)
interface.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

This sample uses the
[MVC Action Command](/develop/tutorials/-/knowledge_base/7-0/mvc-action-command)'s
`processAction(...)` method to process the inputted text (i.e., name). The text
is set as an attribute in the `KotlinGreeterActionCommandKt.kt` class using an
`ActionRequest` and then is retrieved in the JSP using a `RenderRequest`.

## Where Is This Sample? [](id=where-is-this-sample)

This sample is built with the following build tools:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/kotlin-portlet)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/apps/kotlin-portlet)
