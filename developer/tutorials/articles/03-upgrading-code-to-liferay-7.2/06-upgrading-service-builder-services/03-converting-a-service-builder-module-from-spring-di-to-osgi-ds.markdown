---
header-id: converting-a-service-builder-module-from-spring-di-to-osgi-ds
---

# Converting a Service Builder Module from Spring DI to OSGi DS

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Upgrading Service Builder Services</p><p>Step 2 of 3</p>
</div>

Prior to @product-ver@, Service Builder modules could only use Spring for
dependency injection (DI). Now
[OSGi Declarative Services](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services)
(DS) is the default DI mechanism for new Service Builder modules. Although OSGi
DS is the default DI mechanism, Spring is still supported. Therefore, this is an
optional migration step.

To learn more about the decision to convert your Service Builder modules' DI
mechanism and how to complete the conversion process, see the
[Migrating a Service Builder Module from Spring DI to OSGi DS](/docs/7-2/appdev/-/knowledge_base/a/migrating-a-service-builder-module-from-spring-di-to-osgi-ds)
article.
