---
header-id: invoking-services-from-spring-service-builder-code
---

# Invoking Services from Spring Service Builder Code

[TOC levels=1-4]

When using Spring as the dependency injector, all the services created within
a Service Builder application are wired using an internal Spring Application
Context. This uses AOP proxies to adapt the services for transactions, indexing,
and security. In a module's `module-spring.xml` Spring Application Context file,
Service Builder defines each entity's `*LocalServiceImpl`, `*ServiceImpl`, and
`*PersistenceImpl` classes as Spring Beans. For example, Service Builder defines
Spring Beans for the `Foo` entity in the
[Liferay Blade Service Builder `basic-service` sample module's](/docs/7-2/reference/-/knowledge_base/r/service-builder-samples)
`src/main/resources/META-INF/spring/module-spring.xml` file:

```xml
<?xml version="1.0"?>

<beans
    default-destroy-method="destroy"
    default-init-method="afterPropertiesSet"
    xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd"
>
    <bean class="com.liferay.blade.samples.servicebuilder.service.impl.FooLocalServiceImpl" id="com.liferay.blade.samples.servicebuilder.service.FooLocalService" />
    <bean class="com.liferay.blade.samples.servicebuilder.service.impl.FooServiceImpl" id="com.liferay.blade.samples.servicebuilder.service.FooService" />
    <bean class="com.liferay.blade.samples.servicebuilder.service.persistence.impl.FooPersistenceImpl" id="com.liferay.blade.samples.servicebuilder.service.persistence.FooPersistence" parent="basePersistence" />
</beans>
```

Here's a summary of the beans the example context defines:

 **Interface ID** | **Implementation Class** |
 :------ | :--------- |
 `com.liferay.blade.samples.servicebuilder.service.FooLocalService` | `com.liferay.blade.samples.servicebuilder.service.impl.FooLocalServiceImpl` |
 `com.liferay.blade.samples.servicebuilder.service.FooService` | `com.liferay.blade.samples.servicebuilder.service.impl.FooServiceImpl` |
 `com.liferay.blade.samples.servicebuilder.service.persistence.FooPersistence` | `com.liferay.blade.samples.servicebuilder.service.persistence.impl.FooPersistenceImpl` |
 
Since these classes are Spring Beans and NOT OSGi Declarative Services
components, they don't use the `@Reference` Declarative Services annotation to
inject themselves. Here are the recommended Liferay annotations a Service
Builder Spring Bean can use.

- [Use `@BeanReference` to reference a Spring Bean that is in the Application Context.](#referencing-a-spring-bean-that-is-in-the-application-context)
- [Use `@ServiceReference` to reference an OSGi service.](#referencing-an-osgi-service)

| **Important:** When invoking service entity updates (e.g.,
| `fooService.update(object)`) for services that have MVCC enabled, make sure to
| do so in transactions. Propagate rejected transactions to the UI for the user to
| handle. For details, see
| [Multiversion concurrency control (MVCC)](/docs/7-2/appdev/-/knowledge_base/a/defining-global-service-information#multiversion-concurrency-control-mvcc).

The `@BeanReference` annotation is explained first. 

## Referencing a Spring Bean that is in the Application Context

A Service Builder Spring Bean class, such as a `*LocalServiceImpl` class, should
use Liferay's `@BeanReference` annotation to access other Spring Beans the
module's Spring Application Context defines.

For example, if your service module's `service.xml` file defines local services
for entities named `Foo` and `Bar`, Service Builder generates a
`module-spring.xml` file that defines local service Spring Beans for both
entities. To inject the `BarLocalService` Spring Bean into the
`FooLocalServiceImpl` class, for example, the `FooLocalServiceImpl` class must
declare a `BarLocalService` field and apply an `@BeanReference` annotation to
it. 

    @BeanReference
    private BarLocalService _barLocalService;

The `@BeanReference` tells Liferay's AOP to treat the bean reference for use in
transactions, search indexing, or security, if needed. The referencing class can
invoke the Spring Bean class's methods.

Besides the services Service Builder makes available for your application,
Service Builder Spring Bean classes can also access any service published in the
OSGi Registry. This means the following services are available:

- Beans defined in Liferay's core
- Beans created in other module app contexts
- Services declared using OSGi Declarative Services
- Services registered using the OSGi low level API

These are all OSGi services. The next section demonstrates a Service Builder
Spring Bean referencing OSGi services.

## Referencing OSGi Services

In many cases, your Service Builder code (Spring Beans) must use external
services. Liferay's `@ServiceReference` annotation lets Liferay Spring Beans
reference OSGi services. 

Suppose you're building an application with a simple entity your service module
defines in its `service.xml` file. The application must send an SMS every time
a new entity is created, and the `SMSService` is provided by a module installed
in the system.

Your `*LocalServiceImpl` (Spring Bean) could use an `@ServiceReference`
annotation to reference the *external* service.

    @ServiceReference
    private SMSService _smsService;

This annotation retrieves a reference to the OSGi service and provides some nice
benefits. None of the Spring context is created until the `SMSService` service
is available. Likewise, if the `SMSService` suddenly disappears, the whole
Spring Application Context is destroyed. This makes Liferay Spring apps robust
and versatile.

Fortunately, Service Builder generates this kind of code for every entity your
`service.xml` file references. For example, the
[Liferay Blade Service Builder sample project](/docs/7-2/reference/-/knowledge_base/r/service-builder-samples)
`basic-service` module's `service.xml` file defines a `Foo` entity that
references an `AssetEntry` entity:

```xml
<reference entity="AssetEntry" package-path="com.liferay.portlet.asset" />
```

Service Builder generated the `FooLocalServiceBaseImpl` class (the base class is
part of the `FooLocalServiceImpl` class's hierarchy), which references the
`AssetEntry` entity's local service `AssetEntryLocalService` using a field
annotated with `@ServiceReference`:

```java
@ServiceReference(type = com.liferay.asset.kernel.service.AssetEntryLocalService.class)
protected com.liferay.asset.kernel.service.AssetEntryLocalService assetEntryLocalService;
```

Great! You now know how to add a reference to any OSGi service to a Service
Builder Spring Bean. You also know how to add a reference to any other Spring
Bean in the Application Context of your Service Builder Spring Bean.

## Related Topics

[Invoking Local Services](/docs/7-2/appdev/-/knowledge_base/a/invoking-local-services)

[Invoking Remote Services](/docs/7-2/appdev/-/knowledge_base/a/invoking-remote-services)

[JSON Web Services Invoker](/docs/7-2/appdev/-/knowledge_base/a/json-web-services-invoker)

[Service Trackers](/docs/7-2/frameworks/-/knowledge_base/f/service-trackers)
