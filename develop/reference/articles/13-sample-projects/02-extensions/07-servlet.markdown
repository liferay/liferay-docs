# Servlet [](id=servlet)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

The Servlet sample provides an OSGi Whiteboard Servlet in @product@. When
deploying this sample and configuring the servlet, a *Hello World* message is
displayed when accessing the servlet page URL. Log info is also outputted to
your console.

![Figure 1: The servlet displays *Hello World* from the configured servlet page URL.](../../../images/servlet-sample.png)

![Figure 2: The servlet also logs info in the console.](../../../images/servlet-sample-log.png)

To configure the servlet in @product@, complete the following steps:

1.  Navigate to the *Control Panel* &rarr; *Configuration* &rarr; *Server
    Administration* &rarr; *Log Levels*.

2.  Select *Add Category*.

3.  Insert *com.liferay.blade.samples.servlet.BladeServlet* for the Logger Name
    and *INFO* for the Log Level.

4.  Navigate to the http://localhost:8080/o/blade/servlet URL.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[HttpServlet](https://tomcat.apache.org/tomcat-5.5-doc/servletapi/javax/servlet/http/HttpServlet.html)
API.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

To customize this sample, modify its
`com.liferay.blade.samples.servlet.BladeServlet` class. This class extends the
`HttpServlet` class. Creating your own servlet for @product@ is useful when you
need to implement servlet actions. For example, if you wanted to implement the
CMIS server by yourself with [Apache Chemistry](https://chemistry.apache.org/),
you would need to implement your own servlet, managing requests at a low level.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.0/gradle/extensions/servlet)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.0/liferay-workspace/extensions/servlet)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/7.0/maven/extensions/servlet)
