---
header-id: kotlin-portlet
---

# Kotlin Portlet

[TOC levels=1-4]

The Kotlin Portlet sample provides an input form that accepts a name. Once
submitting a name, the portlet renders a greeting message.

![Figure 1: After saving the inputted name, it's displayed as a greeting on the portlet page.](../../../images/kotlin-portlet.png)

## What API(s) and/or code components does this sample highlight?

This sample highlights the use of the [Kotlin](https://kotlinlang.org/)
programming language in conjunction with Liferay's MVC framework.
Specifically, this sample leverages the
[MVCActionCommand](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)
interface.

## How does this sample leverage the API(s) and/or code component?

This sample uses the MVC Action Command's `processAction(...)` method to process 
the inputted text (i.e., name). The text is set as an attribute in the 
`KotlinGreeterActionCommandKt.kt` class using an `ActionRequest` and then is 
retrieved in the JSP using a `RenderRequest`. 

<!-- Add back link for 'MVC Action Command' once mvc-action-command article is available-->

## Where Is This Sample?

This sample is built with the following build tools:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.2/gradle/apps/kotlin-portlet)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.2/liferay-workspace/apps/kotlin-portlet)
