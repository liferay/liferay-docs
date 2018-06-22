# Liferay Portal Classloader Hierarchy [](id=liferay-portal-classloader-hierarchy)

All @product@ applications live in its OSGi container. Portal is a web
application deployed on your application server. Portal's Module Framework
bundles (modules) live in the OSGi container and have classloaders. All the
classloaders from Java's Bootstrap classloader to classloaders for bundle
classes and JSPs are part of a hierarchy. 

This tutorial explains Liferay's classloader hierarchy and describes how it
works in the following contexts: 

-   Web application, such as Liferay Portal, deployed on the app server
-   OSGi bundle deployed in the Module Framework

The following diagram shows Liferay's classloader hierarchy.

![Figure 1.0: Here is Liferay's classloader hierarchy.](../../images/portal-classloader-hierarchy.png)

Here are the classloader descriptions:

-   **Bootstrap**: The JRE's classes (from packages `java.*`) and Java
    extension classes (from `$JAVA_HOME/lib/ext`). No matter the context,
    loading all `java.*` classes is delegated to the Bootstrap classloader.

-   **System**: Classes configured on the `CLASSPATH` and or passed in via the
    application server's Java classpath (`-cp` or `-classpath`) parameter. 

-   **Common**: Classes accessible globally to web applications on the
    application server.

-   **Web Application**: Classes in the application's `WEB-INF/classes` folder
    and `WEB-INF/lib/*.jar`. 

-   **Module Framework**: Liferay's OSGi module framework classloader which 
    is used to provide controlled isolation for the module framework bundles. 

-   **bundle**: Classes from a bundle's packages or from packages other
    bundles export. 

-   **JSP**: A classloader that aggregates the following bundle and
    classloaders:
    -   Bundle that contains the JSPs' classloader
    -   JSP servlet bundle's classloader
    -   Javax Expression Language (EL) implementation bundle's classloader
    -   Javax JSTL implementation bundle's classloader

-   **Service Builder**: Service Builder classes

The classloader used depends on context. Classloading rules vary between
application servers. Classloading in web applications and OSGi bundles differs
too. In all contexts, however, the Bootstrap classloader loads classes from
`java.*` packages. 

Classloading from a web application perspective is up next. 

## Web Application Classloading Perspective [](id=web-application-classloading-perspective)

Application servers dictate where and in what order web applications, such as
@product@, search for classes and resources. Application servers such as
[Apache Tomcat](https://tomcat.apache.org/tomcat-7.0-doc/class-loader-howto.html)
enforce the following default search order:

1.  Bootstrap classes
2.  Web app's `WEB-INF/classes`
3.  web app's `WEB-INF/lib/*.jar`
4.  System classloader
5.  Common classloader

First, the web application searches Bootstrap. If the class/resource isn't
there, the web application searches its own classes and JARs. If the
class/resource still isn't found, it checks the System classloader and then
Common classloader.  Except for the web application checking its own classes and
JARs, it searches the hierarchy in parent-first order. 

Application servers such as
[Oracle WebLogic](https://docs.oracle.com/cd/E19501-01/819-3659/beadf/index.html)
and IBM WebSphere have additional classloaders. They may also have a
different classloader hierarchy and search order. Consult your application
server's documentation for classloading details. 

## Other Classloading Perspectives [](id=other-classloading-perspectives)

The
[Bundle Classloading Flow](/develop/tutorials/-/knowledge_base/7-1/bundle-classloading-flow)
tutorial explains classloading from an OSGi bundle perspective. 

Classloading for JSPs and Service Builder classes is similar to that
of web applications and OSGi bundle classes. 

You now know @product@'s classloading hierarchy, understand it in context of web
applications, and have references to information on other classloading perspectives. 

## Related Topics [](id=related-topics)

[Bundle Classloading Flow](/develop/tutorials/-/knowledge_base/7-1/bundle-classloading-flow)
