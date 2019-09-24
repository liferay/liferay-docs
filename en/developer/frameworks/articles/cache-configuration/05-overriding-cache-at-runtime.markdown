---
header-id: overriding-cache-at-runtime
---

# Overriding Cache at Runtime

[TOC levels=1-4]

Liferay preconfigures cache for several different classes, service entities,
finder results. You may find it necessary to tune the cache differently to meet
your needs. For example, it may help to write cache overflow elements to disk,
increase the maximum number of cached elements, or make other adjustments. Using
a module and only one XML file, you override cache configurations at runtime. 

| **Warning:** Modifying an EhCache element flushes its cache. 

Here is how to override a cache configuration: 

1.  Identify the name of the cache you want to override. Refer to 
    the *Discovering and Viewing Cache Configurations* section in the
    [Cache Configuration](/docs/7-2/frameworks/-/knowledge_base/f/cache-configuration)
    introduction to find the cache name. 

2.  Create a module project.

    | **Tip:** create a project using the
    | [API project template](/docs/7-2/reference/-/knowledge_base/r/api-template)
    | and remove the Java class generated in the `src/main/java/` folder.

3.  In the `src/main/resources/META-INF` folder, add an XML file for the type of
    cache (multi-VM or single-VM) you're overriding.

    `module-multi-vm.xml` file:

    ```xml
    <ehcache
        dynamicConfig="true"
        monitoring="off"
        name="module-multi-vm"
        updateCheck="false"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:noNamespaceSchemaLocation="http://www.ehcache.org/ehcache.xsd"
    >
        <!-- cache elements go here -->
    </ehcache>
    ```

    `module-single-vm.xml` file:

    ```xml
    <ehcache
        dynamicConfig="true"
        monitoring="off"
        name="module-single-vm"
        updateCheck="false"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:noNamespaceSchemaLocation="http://www.ehcache.org/ehcache.xsd"
    >
        <!-- cache elements go here -->
    </ehcache>
    ```

4.  In the `<ehcache/>` element, add a `<cache/>` element and set its `name`
    attribute to the name of the cache you're overriding.

5.  Configure the cache to meet your needs using `<cache/>` element attributes 
    as described in the
    [ehcache.xsd](http://www.ehcache.org/ehcache.xsd)
    and
    [Ehcache documentation](http://www.ehcache.org/documentation/2.8/configuration/index.html). 

6.  [Deploy your module project](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project). 

Congratulations! Your cache modification is in effect. 
