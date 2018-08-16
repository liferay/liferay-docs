# @product@ Startup Phases [](id=product-startup-phases)

Liferay startup phases are helpful to understand. Knowing them helps you troubleshoot startup failures. Also by learning the phase triggered events, you can listen for and act on them. This article describes the startup phases and identifies 
[phase events you can implement actions for](acting-on-events). 

Startup consists of these main phases:

1.  **Portal Context Initialization Phase:** focuses on low level tasks that 
    don't need to be aware of a web context.

2.  **Main Servlet Initialization Phase:** focuses on the portlet container and 
    the @product@ web application's UI features such as Struts, Themes, and
    more. 

The Portal Context Initialization Phase sets the stage for the Main Servlet
Initialization Phase. 

### Portal Context Initialization Phase [](id=portal-context-initialization-phase)

Here's the phase's activity sequence:

1.  Set up low level utilities such as logging and those in
    [`PortalUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/PortalUtil.html)
    and 
    [`InitUtil`](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/util/InitUtil.html). 

2.  OSGi framework is initialized. 

3.  Spring Phase 1: INFRASTRUCTURE beans are loaded. 

4.  INFRASTRUCTURE beans are published as
    [OSGi services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services). 

5.  OSGi framework starts. 

    1.  Static bundles are installed and started. 
    2.  Dynamic bundles are started. 

6.  OSGi framework starts the runtime.  

7.  Spring Phase 2: MAIN Spring beans are loaded. Triggers the 
    [`ModuleServiceLifecycle` event](#moduleservicelifecycle-events)
    `SPRING_INITIALIZED`. 

8.  MAIN Spring beans are published as 
    [OSGi services](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services). 

### Main Servlet Initialization Phase [](id=main-servlet-initialization-phase)

Here's the phase's activity sequence:

1.  The 
    [`ModuleServiceLifecycle` event](#moduleservicelifecycle-events)
    `DATABASE_INITIALIZED` fires. 

2.  The
    [Global Startup event](#portal-startup-events)
    fires. 

3.  For each portal instance, the
    [Application Startup events](#portal-startup-events)
    fire. 

4.  The 
    [`ModuleServiceLifecycle` event](#moduleservicelifecycle-events)
    `PORTAL_INITIALIZED` fires.

Now that you're acquainted with the startup phases, you can concentrate on the
events they fire. 

## Acting on Events [](id=acting-on-events)

The ways to act on events depends on the event type. These subsections describe
the event types. 

### ModuleServiceLifecycle Events [](id=moduleservicelifecycle-events)

[You can wait for and act on `ModuleServiceLifecycle` events.](/develop/tutorials/-/knowledge_base/7-1/waiting-on-lifecycle-events)

### Portal Startup Events [](id=portal-startup-events)

In your `liferay-portal-ext.properties` file, you can override the following
properties and add your own
[`LifecycleAction`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/events/LifecycleAction.html)
classes to the list of action classes to invoke on the events. 

**Global Startup Event** runs once when @product@ initializes. The 
[`global.startup.events` property](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Startup%20Events)
defines the event's default actions. 

**Application Startup Events** runs once for each site instance @product@ 
initializes. The
[`application.startup.events` property](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Startup%20Events)
defines the event's default actions. 
