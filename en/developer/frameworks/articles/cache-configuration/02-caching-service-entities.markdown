---
header-id: caching-service-entities
---

# Caching Service Entities

[TOC levels=1-4]

Configuring cache for service entities helps you retrieve them faster. 
Service Builder generates
[service entity](/docs/7-2/appdev/-/knowledge_base/a/defining-service-entities)
caching code automatically in
[persistence classes](/docs/7-2/appdev/-/knowledge_base/a/understanding-the-code-generated-by-service-builder).
The code maps entity primary keys to implementation objects to quicken entity
retrieval. The code is bypassed by default. Configuring cache for the entity
(EntityCache) enables the generated caching code. Here you'll configure cache
for a service entity so you can access the service entity instances faster. 

1.  Open your
    [`*-service` project](/docs/7-2/appdev/-/knowledge_base/a/creating-a-service-builder-project).

2.  Create a file called `module-multi-vm.xml` in your
    `src/main/resources/META-INF` folder.

3.  In your `module-multi-vm.xml` file, add an `<ehcache/>` root element named
    `module-multi-vm`, and add a `<cache/>` element like the one below, making
    sure to replace `ENTITY_IMPL_CLASS_NAME` with your implementation class name

    ```xml
    <ehcache
    	dynamicConfig="true"
    	monitoring="off"
    	name="module-multi-vm"
    	updateCheck="false"
    	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xsi:noNamespaceSchemaLocation="http://www.ehcache.org/ehcache.xsd"
    >
        <cache
            name="com.liferay.portal.kernel.dao.orm.EntityCache.ENTITY_IMPL_CLASS_NAME"
        >
        </cache>
    </ehcache>
    ```

4.  Replace the `<cache/>` element's `name` with one for your entity. The cache 
    configuration
    [introduction](/docs/7-2/frameworks/-/knowledge_base/f/cache-configuration)
    explains the EntityCache naming convention. 

5.  Configure the cache using `<cache/>` element attributes. Refer to the
    [ehcache.xsd](http://www.ehcache.org/ehcache.xsd)
    and
    [Ehcache documentation](http://www.ehcache.org/documentation/2.8/configuration/index.html)
    for details.

    For example, here is the element for caching the
    `com.liferay.portal.kernel.model.User` service entity:

    ```xml
    <cache
        eternal="false"
        maxElementsInMemory="100000"
        name="com.liferay.portal.kernel.dao.orm.EntityCache.com.liferay.portal.model.impl.UserImpl"
        overflowToDisk="false"
        timeToIdleSeconds="600"
    >
    </cache>
    ```

6.  [Deploy your project](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project).

Congratulations! You're caching your service entity.
