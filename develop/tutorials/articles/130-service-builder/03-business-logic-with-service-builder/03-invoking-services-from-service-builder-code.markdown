# Invoking Services from Service Builder Code [](id=invoking-services-from-service-builder-code)

All the services created within a Service Builder application are wired using an
internal Spring Application Context. This uses AOP proxies to adapt the services
for transactions, indexing, and security. In a module's `module-spring.xml`
Spring Application Context file, Service Builder defines each entity's
`*LocalServiceImpl`, `*ServiceImpl`, and `*PersistenceImpl` classes as Spring
Beans. For example, Service Builder defines Spring Beans for the `Foo` entity in
the
[Liferay Blade Service Builder `basic-service` sample module's](/develop/reference/-/knowledge_base/7-1/service-builder-samples)
`src/main/resources/META-INF/spring/module-spring.xml` file:

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

Here's a summary of the beans the example context defines:

 **Interface ID** | **Implementation Class** |
 :------ | :--------- |
 `com.liferay.blade.samples.servicebuilder.service.FooLocalService` | `com.liferay.blade.samples.servicebuilder.service.impl.FooLocalServiceImpl` |
 `com.liferay.blade.samples.servicebuilder.service.FooService` | `com.liferay.blade.samples.servicebuilder.service.impl.FooServiceImpl` |
 `com.liferay.blade.samples.servicebuilder.service.persistence.FooPersistence` | `com.liferay.blade.samples.servicebuilder.service.persistence.impl.FooPersistenceImpl` |
 
Since these classes are Spring Beans and NOT OSGi Declarative Services
components, they use annotations other than the `@Reference` Declarative
Services annotation to inject Spring Beans and OSGi services. Here are the
recommended Liferay annotations a Service Builder Spring Bean can use.

- [Use `@BeanReference` to reference a Spring Bean that is in the Application Context.](#referencing-a-spring-bean-that-is-in-the-application-context)
- [Use `@ServiceReference` to reference an OSGi service.](#referencing-an-osgi-service)

The `@BeanReference` annotation is explained first. 

## Referencing a Spring Bean that is in the Application Context [](id=referencing-a-spring-bean-that-is-in-the-application-context)

A Service Builder Spring Bean class, such as a `*LocalServiceImpl` class, should
use Liferay's `@BeanReference` annotation to access other Spring Beans the
module's Spring Application Context defines.

For example, if your service module's `service.xml` file defines local services
for entities named `Foo` and `Bar`, Service Builder generates a
`module-spring.xml` file that defines local service Spring Beans for both
entities. To inject the `BarLocalService` Spring Bean into the
`FooLocalServiceImpl` class, for example, the `FooLocalServiceImpl` class would
declare a `BarLocalService` field and apply an `@BeanReference` annotation to
it. 

    @BeanReference
    private BarLocalService _barLocalService;

The `@BeanReference` lets Liferay's AOP treat the bean reference for use in
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

## Referencing OSGi Services [](id=referencing-an-osgi-service)

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
Spring Application Context is destroyed. This makes Liferay Spring apps much
more robust and versatile.

Fortunately, Service Builder generates this kind of code for every entity your
`service.xml` file references. For example, the
[Liferay Blade Service Builder sample project](/develop/reference/-/knowledge_base/7-1/service-builder-samples)
`basic-service` module's `service.xml` file defines a `Foo` entity that
references an `AssetEntry` entity:

    <reference entity="AssetEntry" package-path="com.liferay.portlet.asset" />

Service Builder generated the `FooLocalServiceBaseImpl` class (the base class is
part of the `FooLocalServiceImpl` class's hierarchy), which references the
`AssetEntry` entity's local service `AssetEntryLocalService` using a field
annotated with `@ServiceReference`:

    @ServiceReference(type = com.liferay.asset.kernel.service.AssetEntryLocalService.class)
    protected com.liferay.asset.kernel.service.AssetEntryLocalService assetEntryLocalService;

Great! You now know how to add a reference to any OSGi service to a Service
Builder Spring Bean. You also know how to add a reference to any other Spring
Bean in the Application Context of your Service Builder Spring Bean.

## Related Topics [](id=related-topics)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-1/invoking-local-services)

[Invoking Remote Services](/develop/tutorials/-/knowledge_base/7-1/invoking-remote-services)

[JSON Web Services Invoker](/develop/tutorials/-/knowledge_base/7-1/json-web-services-invoker)

[Service Trackers](/develop/tutorials/-/knowledge_base/7-1/service-trackers)
